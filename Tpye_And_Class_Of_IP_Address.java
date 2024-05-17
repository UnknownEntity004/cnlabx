import java.util.Scanner;

public class Tpye_And_Class_Of_IP_Address {
  static void printIpClass(String sourceString) {
    short len = 0;

    int oct[] = new int[4];
    String buf = "";

    short cnt = 0;
    short i = 0;

    len = (short) sourceString.length();
    for (i = 0; i < len; i++) {
      if (sourceString.charAt(i) != '.') {
        buf += sourceString.charAt(i);
      }
      if (sourceString.charAt(i) == '.' || i == len - 1) {
        oct[cnt++] = Integer.parseInt(buf);
        buf = "";
      }
    }

    System.out.println("Octate1 : " + oct[0]);
    System.out.println("Octate2 : " + oct[1]);
    System.out.println("Octate3 : " + oct[2]);
    System.out.println("Octate4 : " + oct[3]);

    if (oct[0] >= 0 && oct[0] <= 127)
      System.out.printf("Class A Ip Address.\n");
    else if (oct[0] > 127 && oct[0] < 191)
      System.out.printf("Class B Ip Address.\n");
    else if (oct[0] > 191 && oct[0] < 224)
      System.out.printf("Class C Ip Address.\n");
    else if (oct[0] > 224 && oct[0] <= 239)
      System.out.printf("Class D Ip Address.\n");
    else if (oct[0] > 239)
      System.out.printf("Class E Ip Address.\n");
    
    boolean isPrivate = false;
    if (oct[0] == 10 || (oct[0] == 172 && (oct[1]) >= 16 && (oct[1]) <= 31) || (oct[0] == 192 && (oct[1]) == 168)) {
        isPrivate = true;
    }
    System.out.println("Type: " + (isPrivate ? "Private" : "Public"));


  }

  public static void main(String[] args) {
    Scanner SC = new Scanner(System.in);

    String ip;
    int i = 0;

    System.out.print("Enter valid IP address: ");
    ip = SC.next();

    printIpClass(ip);
  }
}
