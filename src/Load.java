import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class to load csv files into akari game.
 */
class Load {
    private int[][] map;
    private int x;
    private int y;

    /**
     *
     * @param filename Path to file.
     * @param a Akari game throwing load.
     * @throws IOException No file found or file obstructed.
     */
    Load(String filename,Akari a) throws IOException{
        a.wipe();
        read(filename);
        new Akari(x,y,map);
    }

    /**
     * Method to read CSV.
     * @param fileName Path to file.
     * @throws IOException No file found or file obstructed.
     */
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
