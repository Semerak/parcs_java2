import parcs.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Bluck{

    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("BoyerMoore.jar");

//        String text = textFromFile( curtask.findFile("input") );
//        String pattern = patternFromFile( curtask.findFile("pattern"));

        String[] file = readLines("input.txt");
        String target = file[0];
        int global_start = Integer.parseInt(file[1]);
        int global_finish = Integer.parseInt(file[2]);
        int n = Integer.parseInt(file[3]);

        long startTime = System.nanoTime();

        AMInfo info = new AMInfo(curtask, null);

//        int N = 10;
//        int n = text.length() / N;
//        int M = pattern.length();

//        List<String> texts = new ArrayList<>();
//        List<Integer> shifts = new ArrayList<>();
//
//        for (int i = 0; i < N; i++) {
//            int l = i * n;
//            int r = (i + 1) * n;
//            String textPart = text.substring(l, r);
//            texts.add(textPart);
//            shifts.add(l);
//            if (i < N - 1) {
//                int ll = r - (M - 1);
//                int rr = r + M - 1;
//                String text1 = text.substring(ll, rr);
//                texts.add(text1);
//                shifts.add(ll);
//            }
//        }

        List<point> points = new ArrayList<>();
        List<channel> channels = new ArrayList<>();

        int delta = (global_finish- global_start)/n;

        for (int i = 0; i < n; i++) {
            int s = i*delta;
            int f = (i+1)*delta-1;


//            String t = texts.get(i);
//            Integer shift = shifts.get(i);

            point p = info.createPoint();
            channel c = p.createChannel();

            points.add(p);
            channels.add(c);

            Input input = new Input(target, s,f);

            p.execute("BoyerMoore");
            c.write(input);
            //c.write(target);

//            System.out.println("Waiting for result .. ");
//
//            String result = (String) (c.readObject());
//            System.out.println("For "+ s +" - "+ f + ": "+result);
//            if (ins.size() > 0) {
//                System.out.println("Pattern ins : {");
//                for (int index : ins) {
//                    System.out.print("{Shift: " + shift + " index: " + index + "} ");
//                }
//                System.out.println("}");
//                System.out.println("Size: " + ins.size());
//            }
        }
        List<String> list=new ArrayList<String>();
        for(parcs.channel channel : channels){
            System.out.println("Waiting for result .. ");

            String result = (String) (channel.readObject());
            //System.out.println("Res "+result);
            list.add(result);
        }
        double estimatedTime = (double) (System.nanoTime() - startTime) / 1000000000;
        System.out.println("Time total (excluding IO): " + estimatedTime);

        System.out.println("Result: "+String.join(", ", list));


        curtask.end();
    }
    public static String textFromFile(String filename) throws Exception {

        String text = new Scanner(new File(filename)).useDelimiter("\\Z").next();

        return text;
    }
    public static String patternFromFile(String filename) throws Exception {
        String pattern = "";

        Scanner sc = new Scanner(new File(filename));

        pattern = sc.nextLine();

        return pattern;
    }
    public static String[] readLines(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;

        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }

        bufferedReader.close();

        return lines.toArray(new String[lines.size()]);
    }
}
