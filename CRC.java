import java.util.Scanner;

public class CRC {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get Frame
        System.out.print("Enter Frame size: ");
        int fs = scanner.nextInt();
        int[] f = new int[20];
        System.out.println("Enter Frame:");
        for (int i = 0; i < fs; i++) {
            f[i] = scanner.nextInt();
        }

        // Get Generator
        System.out.print("Enter Generator size: ");
        int gs = scanner.nextInt();
        int[] g = new int[20];
        System.out.println("Enter Generator:");
        for (int i = 0; i < gs; i++) {
            g[i] = scanner.nextInt();
        }

        // Sender Side
        System.out.println("\nSender Side:");
        System.out.print("Frame: ");
        for (int i = 0; i < fs; i++) {
            System.out.print(f[i]);
        }

        System.out.print("\nGenerator: ");
        for (int i = 0; i < gs; i++) {
            System.out.print(g[i]);
        }

        // Append 0's
        int rs = gs - 1;
        System.out.println("\nNumber of 0's to be appended: " + rs);
        for (int i = fs; i < fs + rs; i++) {
            f[i] = 0;
        }

        int[] temp = new int[20];
        for (int i = 0; i < 20; i++) {
            temp[i] = f[i];
        }

        System.out.print("Message after appending 0's: ");
        for (int i = 0; i < fs + rs; i++) {
            System.out.print(temp[i]);
        }

        // Division
        for (int i = 0; i < fs; i++) {
            int j = 0, k = i;

            // Check whether it is divisible or not
            if (temp[k] >= g[j]) {
                for (j = 0, k = i; j < gs; j++, k++) {
                    if ((temp[k] == 1 && g[j] == 1) || (temp[k] == 0 && g[j] == 0)) {
                        temp[k] = 0;
                    } else {
                        temp[k] = 1;
                    }
                }
            }
        }

        // CRC
        int[] crc = new int[15];
        for (int i = 0, j = fs; i < rs; i++, j++) {
            crc[i] = temp[j];
        }

        System.out.print("\nCRC bits: ");
        for (int i = 0; i < rs; i++) {
            System.out.print(crc[i]);
        }

        System.out.print("\nTransmitted Frame: ");
        int[] tf = new int[15];
        for (int i = 0; i < fs; i++) {
            tf[i] = f[i];
        }

        for (int i = fs, j = 0; i < fs + rs; i++, j++) {
            tf[i] = crc[j];
        }

        for (int i = 0; i < fs + rs; i++) {
            System.out.print(tf[i]);
        }

        // Receiver side
        System.out.print("\nReceiver side:");
        System.out.print("\nReceived Frame: ");
        for (int i = 0; i < fs + rs; i++) {
            System.out.print(tf[i]);
        }

        for (int i = 0; i < fs + rs; i++) {
            temp[i] = tf[i];
        }

        // Division
        for (int i = 0; i < fs + rs; i++) {
            int j = 0, k = i;
            if (temp[k] >= g[j]) {
                for (j = 0, k = i; j < gs; j++, k++) {
                    if ((temp[k] == 1 && g[j] == 1) || (temp[k] == 0 && g[j] == 0)) {
                        temp[k] = 0;
                    } else {
                        temp[k] = 1;
                    }
                }
            }
        }

        System.out.print("\nRemainder: ");
        int[] rrem = new int[15];
        for (int i = fs, j = 0; i < fs + rs; i++, j++) {
            rrem[j] = temp[i];
        }

        for (int i = 0; i < rs; i++) {
            System.out.print(rrem[i]);
        }

        int flag = 0;
        for (int i = 0; i < rs; i++) {
            if (rrem[i] != 0) {
                flag = 1;
            }
        }

        if (flag == 0) {
            System.out.println("\nSince Remainder Is 0 Hence Message Transmitted from Sender To Receiver Is Correct");
        } else {
            System.out.println("\nSince Remainder Is Not 0 Hence Message Transmitted from Sender To Receiver Contains Error");
        }

        scanner.close();
    }
}