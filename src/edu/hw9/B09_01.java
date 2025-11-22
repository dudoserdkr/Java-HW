package edu.hw9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class B09_01 {

    private static final String INPUT_FILE = "src/edu/hw9/09_01_input.txt";
    private static final String OUTPUT_FILE_FIRST = "src/edu/hw9/09_01_output_1.txt";
    private static final String OUTPUT_FILE_SECOND = "src/edu/hw9/09_01_output_2.txt";

    private static final String POISON_PILL = "__END__";

    private static final long T1 = 2;
    private static final long T2 = 3;
    private static final long T3 = 5;

    private static final int CONSUMER_COUNT = 2;

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        Thread producer = new Thread(new Producer(queue));
        Thread consumerOne = new Thread(new Consumer(queue, OUTPUT_FILE_FIRST, T2));
        Thread consumerTwo = new Thread(new Consumer(queue, OUTPUT_FILE_SECOND, T3));

        producer.start();
        consumerOne.start();
        consumerTwo.start();

        producer.join();
        consumerOne.join();
        consumerTwo.join();

        System.out.println("Processing finished");
    }

    private static class Producer implements Runnable {
        private final BlockingQueue<String> queue;

        Producer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Thread.sleep(T1);
                    queue.put(line);
                }
            } catch (IOException | InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                for (int i = 0; i < CONSUMER_COUNT; i++) {
                    try {
                        queue.put(POISON_PILL);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        }
    }

    private static class Consumer implements Runnable {
        private final BlockingQueue<String> queue;
        private final String outputFile;
        private final long processTime;

        Consumer(BlockingQueue<String> queue, String outputFile, long processTime) {
            this.queue = queue;
            this.outputFile = outputFile;
            this.processTime = processTime;
        }

        @Override
        public void run() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                while (true) {
                    String line = queue.take();
                    if (POISON_PILL.equals(line)) {
                        break;
                    }
                    Thread.sleep(processTime);
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException | InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
