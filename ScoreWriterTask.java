package studentlogger;

import java.io.IOException;
import java.util.List;

public class ScoreWriterTask implements Runnable {
    private final List<StudentRecord> records;
    private final SafeFileWriter fileWriter;
    private final int pauseTime;

    public ScoreWriterTask(List<StudentRecord> records, SafeFileWriter fileWriter, int pauseTime) {
        this.records = records;
        this.fileWriter = fileWriter;
        this.pauseTime = pauseTime;
    }

    @Override
    public void run() {
        for (StudentRecord record : records) {
            try {
                fileWriter.writeEntry(record.toCsvFormat());
                if (pauseTime > 0) Thread.sleep(pauseTime);
            } catch (IOException e) {
                System.err.println("Error during file write: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
