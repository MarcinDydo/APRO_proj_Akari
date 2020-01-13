import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Load {
    private int[][] map;
    private int x;
    private int y;

    Load(String filename,Akari a) throws IOException{
        a.wipe();
        read(filename);
        new Akari(x,y,map);
    }
    private void read(String fileName) throws IOException {
        BufferedReader br= new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        String separator = ";";
        x = Integer.parseInt(line.split(separator)[0]);
        y = Integer.parseInt(line.split(separator)[1]);
        int j =0;
        map = new int[x][y];
        while ((line = br.readLine()) != null) {
            for(int i = 0; i<line.split(separator).length; i++){
                map[j][i]=Integer.parseInt(line.split(separator)[i]);
            }
            j++;
        }
        br.close();
    }

}
