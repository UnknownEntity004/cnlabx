import java.util.Arrays;
import java.util.Scanner;

public class selectiveRepeat {

    static class Frame {
        int frameNumber;
        boolean received;

        Frame(int frameNumber) {
            this.frameNumber = frameNumber;
            this.received = false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the total number of frames to be sent: ");
        int totalFrames = scanner.nextInt();

        System.out.print("Enter the window size: ");
        int windowSize = scanner.nextInt();

        Frame[] frames = new Frame[totalFrames];
        for (int i = 0; i < totalFrames; i++) {
            frames[i] = new Frame(i);
        }

        int[] receivedAcknowledgments = new int[totalFrames];
        Arrays.fill(receivedAcknowledgments, -1);

        int sentFrames = 0;
        int ackedFrames = 0;

        while (ackedFrames < totalFrames) {
            for (int i = 0; i < windowSize && (sentFrames + i) < totalFrames; i++) {
                if (!frames[sentFrames + i].received) {
                    System.out.println("Sending frame: " + frames[sentFrames + i].frameNumber);
                }
            }

            System.out.print("Enter the acknowledgment received (or -1 if no acknowledgment): ");
            int ack = scanner.nextInt();

            if (ack != -1 && ack < totalFrames && !frames[ack].received) {
                frames[ack].received = true;
                receivedAcknowledgments[ackedFrames] = ack;
                ackedFrames++;
                System.out.println("Acknowledgment received for frame: " + ack);
            } else {
                System.out.println("No valid acknowledgment received or frame already acknowledged.");
            }

            for (int i = 0; i < windowSize && (sentFrames + i) < totalFrames; i++) {
                if (frames[sentFrames + i].received) {
                    sentFrames++;
                } else {
                    break;
                }
            }
        }

        System.out.println("All frames have been sent and acknowledged successfully.");

        scanner.close();
    }
}