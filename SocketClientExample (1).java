package QuickSort;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class SocketClientExample {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        Socket s = new Socket("localhost", 888);

        // to send data to the server
        DataOutputStream dos
                = new DataOutputStream(
                s.getOutputStream());

        // to read data coming from the server
        BufferedReader br
                = new BufferedReader(
                new InputStreamReader(
                        s.getInputStream()));

        // to read data from the keyboard
        BufferedReader kb
                = new BufferedReader(
                new InputStreamReader(System.in));
        int[] array1 = new int[0];
        String str, str1;
        String array = Arrays.toString(array1);

        double res = 0;
        while (!(array= kb.readLine()).equals("exit") && !(str = kb.readLine()).equals("\n")) {

            // send to the server
            dos.writeBytes(str+ "\n");
            dos.writeBytes(array + "\n");

            // receive from the server
            str1 = br.readLine();
            String str2 = br.readLine();
            String str3 = br.readLine();
            String str4 = br.readLine();
            res = Double.parseDouble(str1);
            Double res1 = Double.parseDouble(str4);

            System.out.printf("Quicksort.sort with multiple threads in %.2f milliseconds.\n", res);
            System.out.println(str2);

            System.out.printf("Quicksort.sort with 1 thread in %.2f milliseconds.\n", res1);
            System.out.println(str3);


        }

        // close connection.
        dos.close();
        br.close();
        kb.close();
        s.close();


    }
}
