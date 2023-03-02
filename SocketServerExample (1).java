package QuickSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerExample {

    public static void main(String args[]) throws IOException, ClassNotFoundException, IOException {
        ServerSocket ss = new ServerSocket(888);

        // connect it to client socket
        Socket s = ss.accept();
        System.out.println("Connection established");

        // to send data to the client
        PrintStream ps
                = new PrintStream(s.getOutputStream());

        // to read data coming from the client
        BufferedReader br
                = new BufferedReader(
                new InputStreamReader(
                        s.getInputStream()));



        // server executes continuously
        while (true) {

            String str, str1;
            String array;

            // read from client

            while ((array = br.readLine()) != null) {

                str = br.readLine();
                long startTime1 = System.nanoTime();
                String str2="";
                int cores = Integer.valueOf(str);

                array = array.replace("[","");
                array = array.replace("]","");


                String[] strArr = array.split(" ");



                int[] arr = new int[strArr.length];
                for (int i=0; i<arr.length; i++) {
                    arr[i] = Integer.parseInt(strArr[i]);
                }
                int[] array1 = arr.clone();
                int[] array2 = arr.clone();



                Quicksort.quicksort(array1,0,array1.length -1);
                long endTime1 = System.nanoTime();
                double res1 = (endTime1 - startTime1)/(1e2);


                startTime1 = System.nanoTime();
                Quicksort.parallelQuicksort(array2, 0, array2.length-1, cores);
                endTime1 = System.nanoTime();
                double res = (endTime1 - startTime1)/(1e2);


                for(int i=0; i<array2.length; i++){
                    str2+=array2[i];
                    str2+=" ";
                }


                String str3 = "";

                for(int i=0; i<array1.length; i++){
                    str3+=array1[i];
                    str3+=" ";
                }

                String str4 = Double.toString(res1);

                str1 = Double.toString(res);
                ps.println(str1);
                ps.println(str2);


                ps.println(str3);
                ps.println(str4);
            }


            // close connection
            ps.close();
            br.close();
            ss.close();
            s.close();

            // terminate application
            System.exit(0);

        } // end of while
    }
}

