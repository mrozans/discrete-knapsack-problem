import lombok.AllArgsConstructor;
import lombok.Value;
import pl.edu.pw.elka.pszt.Application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Stats {
    public static void main(String args2[]) {
        new Stats().run();

    }
    @Value
    static class Record{
        private final long items;
        private final long time;
        @Override
        public String toString(){
            return items + " " + time + "\n";
        }
    }

    private void run(){
        final int start = 2_000;
        final int end = 2_000;
        System.out.println(Integer.MAX_VALUE);
        final String file = "example_input_files/inputExample2000.txt";
        clearFile(file);
        for(int i=start; i<=end;++i){
            appendToFile(file, new Record(i, getTime(i, 1)).toString());
            System.out.println(i);
        }
    }
    private void appendToFile(String path,String data) {
        try (FileWriter fr = new FileWriter(new File(path), true)) {
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long getTime(int i,final int j) {
        final String input = "example_input_files/stats.txt";
        final String[] args = new String[]{input,"example_input_files/out.txt"};
        String data = createData(i,0.8,100);
        createFile(input, data);
        System.gc();
        final long before = System.currentTimeMillis();
//        for (int j1 = 0;j1<j; ++j1){
//            Application.main(args);
//            //System.out.println(j1);
//        }
        final long after = System.currentTimeMillis();
        return (after-before)/j;
    }

    private String createData(int i, double v,int bound) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random(System.currentTimeMillis());
        long sum = 0;
        for (int j = 0; j < i; j++) {
            final int volume = random.nextInt(bound);
            final int value = random.nextInt(bound);
            sum += volume;
            stringBuilder.append(volume).append(" ").append(value).append("\n");
        }
        stringBuilder.insert(0,(int)(v*sum)+"\n");
        return stringBuilder.toString();
    }
    private void createFile(String path, String data){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void clearFile(String path){
        createFile(path,"");
    }

}