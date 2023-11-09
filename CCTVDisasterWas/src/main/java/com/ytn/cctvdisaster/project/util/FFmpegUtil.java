package com.ytn.cctvdisaster.project.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

@Component
@RequiredArgsConstructor
public class FFmpegUtil {
	private static final Logger logger = LoggerFactory.getLogger(FFmpegUtil.class);
    
    @Value("${ffmpeg.convert.savepath}")
    private String convertSavePath;
    
    @Value("${ffmpeg.exe.location}")
    private String ffmpegExecPath;
    
    @Value("${ffprobe.exe.location}")
    private String ffmpegProbeExecPath;
    
    
	public void exportThumbnailImg(String inputFilePath, String imageName, String exportType) throws Exception {
		Path file = Paths.get(convertSavePath + imageName+"."+exportType);
		Boolean ffmpegCallType = Boolean.FALSE;
		
		if(file.toFile().exists()) {
			try {
				SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
	            // 파일 생성일자 찾기
	            FileTime creationTime = (FileTime) Files.getAttribute(file, "lastModifiedTime");
	            
	            Date fileTime = timeFormat.parse(timeFormat.format(new Date(creationTime.toMillis())));
	            Date nowTime = timeFormat.parse(timeFormat.format(new Date()));
	            
	            long Min = (nowTime.getTime() - fileTime.getTime()) / 60000; // 분
	            
	            if(Min >= 3) {
	            	ffmpegCallType = Boolean.TRUE;
	            }
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}else {
			ffmpegCallType = Boolean.TRUE;
		}
        
        
		if(ffmpegCallType){
			FFmpeg ffmpeg = new FFmpeg(ffmpegExecPath);		// ffmpeg.exe 파일 경로
			FFprobe ffprobe = new FFprobe(ffmpegProbeExecPath);	// ffprobe.exe 파일 경로
			
			//FFmpegBuilder를 통해 FFmpeg 명령어를 만들 수 있음
			FFmpegBuilder builder = new FFmpegBuilder()	 
					.overrideOutputFiles(true)					// output 파일을 덮어쓸 것인지 여부(false일 경우, output path에 해당 파일이 존재할 경우 예외 발생 - File 'C:/Users/Desktop/test.png' already exists. Exiting.)
		            .setInput(inputFilePath)     					// 썸네일 이미지 추출에 사용할 영상 파일의 절대 경로
		            .addExtraArgs("-timeout", "5000")
		            .addExtraArgs("-ss", "00:00:01") 			// 영상에서 추출하고자 하는 시간 - 00:00:01은 1초를 의미 
		            .addOutput(convertSavePath + imageName+"."+exportType) 		// 저장 절대 경로(확장자 미 지정 시 예외 발생 - [NULL @ 000002cc1f9fa500] Unable to find a suitable output format for 'C:/Users/Desktop/test')
		            .setFrames(1)
		            .done();    		
		
			FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);		// FFmpeg 명령어 실행을 위한 FFmpegExecutor 객체 생성
			
			executor.createJob(builder).run();									// one-pass encode
			//executor.createTwoPassJob(builder).run();							// two-pass encode
		}
	}

}
