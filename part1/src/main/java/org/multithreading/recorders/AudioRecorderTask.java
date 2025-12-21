package org.multithreading.recorders;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioRecorderTask implements Runnable {

    private final String pathToFolder = "/home/shamim/Music/VoiceRecordsByProgram/";
    private final File outputFile;
    private final AudioFormat format;
    private TargetDataLine microphone;
    private volatile boolean running = true;

    public AudioRecorderTask() {
        this.outputFile = new File(pathToFolder+"audio.wav");
        this.format = new AudioFormat(
                16000, // sample rate
                16,    // sample size
                1,     // mono
                true,  // signed
                true   // big endian
        );
    }

    @Override
    public void run() {
        try {
            DataLine.Info info =
                    new DataLine.Info(TargetDataLine.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Microphone not supported");
                return;
            }

            microphone =
                    (TargetDataLine) AudioSystem.getLine(info);

            microphone.open(format);
            microphone.start();

            System.out.println("Recording started...");

            AudioInputStream audioStream =
                    new AudioInputStream(microphone);

            AudioSystem.write(
                    audioStream,
                    AudioFileFormat.Type.WAVE,
                    outputFile
            );

        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void stopRecording() {
        running = false;
        if (microphone != null) {
            microphone.stop();
            microphone.close();
            System.out.println("Recording stopped.");
        }
    }
}
