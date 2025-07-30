package com.abp.utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

/**
 * Utilidad para grabación de video de las pruebas automatizadas
 * Proporciona evidencia visual completa de la ejecución de tests
 */
public class VideoRecorder extends ScreenRecorder {
    
    private static VideoRecorder videoRecorder;
    private String testName;
    
    public VideoRecorder(GraphicsConfiguration cfg, Rectangle captureArea, 
                        Format fileFormat, Format screenFormat, 
                        Format mouseFormat, Format audioFormat, File movieFolder) throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
    }
    
    /**
     * Inicia la grabación de video para un test específico
     */
    public static void startRecording(String testName) {
        try {
            File recordingDir = new File("videos");
            if (!recordingDir.exists()) {
                recordingDir.mkdirs();
            }
            
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle captureSize = new Rectangle(0, 0, screenSize.width, screenSize.height);
            
            GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice().getDefaultConfiguration();
            
            videoRecorder = new VideoRecorder(gc, captureSize,
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                            QualityKey, 1.0f,
                            KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                            FrameRateKey, Rational.valueOf(30)),
                    null, recordingDir);
            
            videoRecorder.testName = testName;
            videoRecorder.start();
            
            System.out.println("🎥 Iniciando grabación de video para test: " + testName);
            
        } catch (Exception e) {
            System.err.println("Error al iniciar grabación de video: " + e.getMessage());
        }
    }
    
    /**
     * Detiene la grabación de video
     */
    public static void stopRecording() {
        try {
            if (videoRecorder != null) {
                videoRecorder.stop();
                System.out.println("🎬 Grabación de video detenida");
                videoRecorder = null;
            }
        } catch (Exception e) {
            System.err.println("Error al detener grabación de video: " + e.getMessage());
        }
    }
    
    /**
     * Genera nombre de archivo personalizado para cada test
     */
    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {
        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timestamp = dateFormat.format(new Date());
        String fileName = String.format("Test_%s_%s.avi", 
                                       testName != null ? testName.replaceAll("[^a-zA-Z0-9]", "_") : "Unknown",
                                       timestamp);
        
        return new File(movieFolder, fileName);
    }
}
