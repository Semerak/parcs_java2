import parcs.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class BoyerMoore implements AM {


    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();


    }

    private static String finder(String target, int start, int finish){

        List<String> list=new ArrayList<String>();
        for(int i=start;i<=finish;i++) {
            String originaString = String.valueOf(i);
            MessageDigest digest = null;

            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            byte[] encodedhash = digest.digest(
                    originaString.getBytes(StandardCharsets.UTF_8));
            String sha3Hex = bytesToHex(encodedhash);
            if (sha3Hex.equals(target)){
                list.add(originaString);
                System.out.println("\nFound: "+originaString+"\n");
            }
            else{
                System.out.println("\t: "+ sha3Hex+" != "+ target);
            }
            if (i == 4){
                System.out.println("\n \n 4: "+ sha3Hex+" != "+ target);
            }
        }
        System.out.println(list);
        return  String.join(", ", list);
    }

    public void run(AMInfo info) {
        System.out.println("Start " );
        Input input = (Input) info.parent.readObject();
        String target = input.getTarget();
        int start = input.getStart();
        int finish = input.getFinish();
        //String target = (String) (info.parent.readObject());
//        String target = "4b227777d4dd1fc61c6f884f48641d02b4d121d3fd328cb08b5531fcacdabf8a";
//        int start=0;
//        int finish = 100;
        System.out.println("Input : start = " + start + ", finish = " + finish);
        System.out.println("Finding");
        String output = finder(target,start,finish);
        System.out.println("Result: "+output);
        info.parent.write(output);
    }
}
