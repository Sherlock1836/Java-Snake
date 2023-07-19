import java.io.*;
public class ScoreRecorder {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private FileReader fileReader;
    private File saveFile;
    
    //This constructor checks to see if a save file and the path for it exists, and if not it creates it
    public ScoreRecorder(String pathString, String fileName) {
        saveFile = new File(pathString);
        if(!saveFile.exists()) {
            saveFile.mkdirs();
            try{
                new File(pathString + fileName).createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        saveFile = new File(pathString + fileName);
    }

    public int loadBestScore(){
        int bestScore = 0;
        try{
            fileReader = new FileReader(saveFile);
            bufferedReader = new BufferedReader(fileReader);
            
            String line;
            if((line = bufferedReader.readLine()) != null) {
                bestScore = Integer.parseInt(line);
            } else {            //saves 0 as best score if no best exists
                saveBestScore(bestScore);
                bestScore = Integer.parseInt(bufferedReader.readLine());
            }
            bufferedReader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return bestScore;
    }

    public void saveBestScore(int newBestScore) {
        try{
            fileWriter = new FileWriter(saveFile);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.toString(newBestScore));
            bufferedWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
