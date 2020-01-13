import java.io.*;

class Save {
    private Akari akari;

    Save(Akari akari) {
        this.akari = akari;
    }

    /**
     * converts map of buttons back to
     * integer array
     * @return 2d array
     */
    private int[][] converter(){
        int[][] map = new int[akari.buttons.length][akari.buttons[0].length];
        for (int i=0; i<map.length;i++){
            for (int j = 0; j <map[0].length; j++) {
                map[i][j] = akari.buttons[i][j].state.getValue();
            }
        }
        return map;
    }

    /**
     * creates csv file with map and saves it
     * if folder "maps"
     * @param savePath name of the file
     */
    void saveToCSV(String savePath){
        int[][] map = converter();
        int counter=0;
        File dir = new File("./maps");
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        File csvFile = new File("./maps/" + savePath + ".csv");
        if(savePath.isEmpty()) {
            do {
                csvFile = new File("./maps/test#" + counter + ".csv");
                counter++;
            } while (csvFile.exists());
        }
        try (PrintWriter writer = new PrintWriter(csvFile)) {
            StringBuilder sb = new StringBuilder();
            sb.append(akari.buttons.length);
            sb.append(';');
            sb.append(akari.buttons[0].length);
            sb.append('\n');
            for (int[] i:map){
                for (int j:i) {
                    sb.append(j);
                    sb.append(';');
                }
                sb.append('\n');
            }
            writer.write(sb.toString());
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}
