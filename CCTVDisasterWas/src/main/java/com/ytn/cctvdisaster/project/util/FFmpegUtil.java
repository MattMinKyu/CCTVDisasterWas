package com.ytn.cctvdisaster.project.util;

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
    
    /*
    @Value("${spring.servlet.multipart.location}")
    private String ffmpegBasePath;
    */
    
    @Value("${ffmpeg.convert.savepath}")
    private String convertSavePath;
    
    @Value("${ffmpeg.exe.location}")
    private String ffmpegExecPath;
    
    @Value("${ffprobe.exe.location}")
    private String ffmpegProbeExecPath;
    
    /*
    public FFmpegProbeResult getProbeResult(String filePath) {
        FFmpegProbeResult ffmpegProbeResult;
        
        try {
            ffmpegProbeResult = ffProbe.probe(filePath);
            
            logger.info("비트레이트 : {}", ffmpegProbeResult.getStreams().get(0).bit_rate);
            logger.info("채널 : {}", ffmpegProbeResult.getStreams().get(0).channels);
            logger.info("코덱 명 : {}", ffmpegProbeResult.getStreams().get(0).codec_name);
            logger.info("코덱 유형 : {}", ffmpegProbeResult.getStreams().get(0).codec_type);
            logger.info("해상도(너비) : {}", ffmpegProbeResult.getStreams().get(0).width);
            logger.info("해상도(높이) : {}", ffmpegProbeResult.getStreams().get(0).height);
            logger.info("포맷(확장자) : {}", ffmpegProbeResult.getFormat());
            

        } catch (IOException e) {
        	logger.error(e.toString());
            throw new RuntimeException(e);
        }

        return ffmpegProbeResult;
    }
	*/
    
	public void exportThumbnailImg(String inputFilePath, String imageName, String exportType) throws Exception {
			
		FFmpeg ffmpeg = new FFmpeg(ffmpegExecPath);		// ffmpeg.exe 파일 경로
		FFprobe ffprobe = new FFprobe(ffmpegProbeExecPath);	// ffprobe.exe 파일 경로
		
		//FFmpegBuilder를 통해 FFmpeg 명령어를 만들 수 있음
		FFmpegBuilder builder = new FFmpegBuilder()	
				.overrideOutputFiles(true)					// output 파일을 덮어쓸 것인지 여부(false일 경우, output path에 해당 파일이 존재할 경우 예외 발생 - File 'C:/Users/Desktop/test.png' already exists. Exiting.)
	            .setInput(inputFilePath)     					// 썸네일 이미지 추출에 사용할 영상 파일의 절대 경로
	            .addExtraArgs("-ss", "00:00:01") 			// 영상에서 추출하고자 하는 시간 - 00:00:01은 1초를 의미 
	            .addOutput(convertSavePath + imageName+"."+exportType) 		// 저장 절대 경로(확장자 미 지정 시 예외 발생 - [NULL @ 000002cc1f9fa500] Unable to find a suitable output format for 'C:/Users/Desktop/test')
	            .setFrames(1)								
	            .done();    											
	
		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);		// FFmpeg 명령어 실행을 위한 FFmpegExecutor 객체 생성
		executor.createJob(builder).run();									// one-pass encode
		//executor.createTwoPassJob(builder).run();							// two-pass encode
		
	}


}
