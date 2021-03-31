import parcs.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class BoyerMoore implements AM {
    static int NO_OF_CHARS = 256;


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
            if (target.equals(sha3Hex)){
                list.add(originaString);
            }
        }
        return  String.join(", ", list);
    }

    public void run(AMInfo info) {
        System.out.println("Start " );
        Input input = (Input) info.parent.readObject();
        String target = input.getTarget();
        Integer start = input.getStart();
        Integer finish = input.getFinish();

        System.out.println("Input : start = " + String.valueOf(start) + ", finish = " + String.valueOf(finish));

        info.parent.write(finder(target,start,finish));
    }
}
