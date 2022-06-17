/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer2;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author MWIGO-JON-MARK
 */
public class FXMLController implements Initializable
{
    EventHandler<MouseEvent> mouseEntered;

    @FXML
    private Button playlistBtn;
    @FXML
    private Button currentPlayBtn;
    @FXML
    private Button addFileBtn;
    @FXML
    private Button addFolderBtn;
    @FXML
    private Button removeFileBtn;
    @FXML
    private Button settingsBtn;
    @FXML
    private Label currentSongLbl;
    @FXML
    private Label currentTimeLbl;
    @FXML
    private Slider timeSeeker;
    @FXML
    private Label defaultTimeLbl;
    @FXML
    private Button shuffleBtn;
    @FXML
    private Button previousBtn;
    @FXML
    private Button playBtn;
    @FXML
    private Button nextBtn;
    @FXML
    private Button repeatBtn;
    @FXML
    private Button muteBtn;
    @FXML
    private Slider volumeSeeker;
    private File file;
    private FileChooser fileChooser;
    private ExtensionFilter  fileExtension;
    private Media media;
    public static MediaPlayer mediaPlayer;
    private Duration defaultPlayerTime;
    private Duration currentPlayerTime;
    private Double defaultPlayerVolume;
    private Status playerStatus;
    private File directoryList;
    private Iterator iterator;
    private int INDEXPOS = 0;
    private boolean REPEATONE = true;
    private boolean REPEATALL = true;
    private boolean REPEAT = false;
    private DirectoryChooser directoryChooser;
    private List<Path> songPathList;
    @FXML
    private ListView songPathListView;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO

    }    

    @FXML
    private void playlistBtnExited(MouseEvent event)
    {
        playlistBtn.setStyle("-fx-background-color: #0c4d5c;");
    }

    @FXML
    private void playlistBtnEntered(MouseEvent event)
    {
        playlistBtn.setStyle("-fx-background-color: #0c5f5c;");
    }

    @FXML
    private void playlistBtnClicked(MouseEvent event)
    {
        playlistBtn.setStyle("-fx-background-color: #0c4d1a;");
        playlistBtn.setStyle("-fx-background-color: #0c5f5c;");
        //EVENT HANDLING CODE HERE.
    }

    @FXML
    private void currentPlayBtnExited(MouseEvent event)
    {
        currentPlayBtn.setStyle("-fx-background-color: #0c4d5c;");
    }

    @FXML
    private void currentPlayBtnEntered(MouseEvent event)
    {
        currentPlayBtn.setStyle("-fx-background-color: #0c5f5c;");
    }

    @FXML
    private void currentPlayBtnClicked(MouseEvent event)
    {
        currentPlayBtn.setStyle("-fx-background-color: #0c4d1a;");
        currentPlayBtn.setStyle("-fx-background-color: #0c5f5c;");
        //EVENT HANDLING CODE HERE.
    }

    @FXML
    private void addFileBtnExited(MouseEvent event)
    {
        addFileBtn.setStyle("-fx-background-color: #0c4d5c;");
    }

    @FXML
    private void addFileBtnEntered(MouseEvent event)
    {
        addFileBtn.setStyle("-fx-background-color: #0c5f5c;");
//        addFileBtn.addEventHandler(event.MOUSE_ENTERED, mouseEntered = e -> {addFileBtn.setStyle("-fx-background-color: #0c4d5b;");});
    }

    @FXML
    private void addFileBtnClicked(MouseEvent event)
    {
            addFileBtn.setStyle("-fx-background-color: #0c4d1a;");
            addFileBtn.setStyle("-fx-background-color: #0c5f5c;");
            fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Audio Files", "*.mp3", "*.ogg", "*.mp4", "*.wav"));
            file = fileChooser.showOpenDialog(null);
            player(file);
    }

    @FXML
    private void addFolderBtnExited(MouseEvent event)
    {
        addFolderBtn.setStyle("-fx-background-color: #0c4d5c;");

    }

    @FXML
    private void addFolderBtnEntered(MouseEvent event)
    {
        addFolderBtn.setStyle("-fx-background-color: #0c5f5c;");
    }

    @FXML
    private void addFolderBtnClicked(MouseEvent event)
    {
        addFolderBtn.setStyle("-fx-background-color: #0c4d1a;");
        addFolderBtn.setStyle("-fx-background-color: #0c5f5c;");
        directoryChooser = new DirectoryChooser();
//            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Folder", "*.*"));
        directoryList = directoryChooser.showDialog(null);
        if(directoryList != null)
        {
            String folder = directoryList.getAbsolutePath();
            folderTreeWalker(folder);

            player(songPathList.get(INDEXPOS).toFile());
//                System.out.println(songPathList.get(INDEXPOS).toFile());
//                songPathListView.setItems(FXCollections.observableArrayList(songPathList));
            songPathListView.setItems(songView(songPathList));
//                iterator = songPathList.iterator();
//                file = (File) iterator.next();
//                player(songPathList.get(INDEXPOS).toFile());
//                mediaPlayer.play();
        }
    }

    @FXML
    private void removeFileBtnExited(MouseEvent event)
    {
        removeFileBtn.setStyle("-fx-background-color: #0c4d5c;");

    }

    @FXML
    private void removeFileBtnEntered(MouseEvent event)
    {
        removeFileBtn.setStyle("-fx-background-color: #0c5f5c;");
    }

    @FXML
    private void removeFileBtnClicked(MouseEvent event)
    {
        removeFileBtn.setStyle("-fx-background-color: #0c4d1a;");
        removeFileBtn.setStyle("-fx-background-color: #0c5f5c;");
        //EVENT HANDLING CODE HERE.
    }

    @FXML
    private void settingsBtnExited(MouseEvent event)
    {
        settingsBtn.setStyle("-fx-background-color: #0c4d5c;");
        
    }

    @FXML
    private void settingsBtnEntered(MouseEvent event)
    {
        settingsBtn.setStyle("-fx-background-color: #0c5f5c;");
    }

    @FXML
    private void settingsBtnClicked(MouseEvent event)
    {
        settingsBtn.setStyle("-fx-background-color: #0c4d1a;");
        settingsBtn.setStyle("-fx-background-color: #0c5f5c;");
        //EVENT HANDLING CODE HERE.
    }

    @FXML
    private void timeSeekerDragged(MouseEvent event)
    {
        timeSeeker.valueProperty().addListener(e2 -> {
        });

        timeSeeker.valueProperty().addListener(e3 -> {
            if(timeSeeker.isValueChanging())
            {
                if(defaultPlayerTime != null)
                {
                    mediaPlayer.seek(defaultPlayerTime.multiply(timeSeeker.getValue() / 100.0));
                }
                iterator = songPathList.iterator();
                if(mediaPlayer.getCurrentTime().equals(mediaPlayer.getMedia().getDuration()))
                {
                    playBtn.setText("Pause");


                    if(iterator.hasNext())
                    {
                        iterator.remove();
                        file = (File) iterator.next();
//                            iterator.remove();
                        player(file);
                        mediaPlayer.play();
                    }
                }
                valueUpdater();
            }
        });
    }
    
    
    @FXML
    private void timeSeekerClicked(MouseEvent event)
    {
        timeSeeker.valueProperty().addListener(e2 -> {
        });

        timeSeeker.valueProperty().addListener(e3 -> {
            if(timeSeeker.isValueChanging())
            {
                if(defaultPlayerTime != null)
                {
                    mediaPlayer.seek(defaultPlayerTime.multiply(timeSeeker.getValue() / 100.0));
                }
                iterator = songPathList.iterator();
                if(mediaPlayer.getCurrentTime().equals(mediaPlayer.getMedia().getDuration()))
                {
                    playBtn.setText("Pause");


                    if(iterator.hasNext())
                    {
                        file = (File) iterator.next();
                        iterator.remove();
                        player(file);
                        mediaPlayer.play();
                    }
                }
                valueUpdater();
            }
        });
    }

    @FXML
    private void shuffleBtnExited(MouseEvent event)
    {
        shuffleBtn.setStyle("-fx-background-color: #0c4d5c;");

    }

    @FXML
    private void shuffleBtnEntered(MouseEvent event)
    {
        shuffleBtn.setStyle("-fx-background-color: #0c5f5c;");
    }

    @FXML
    private void shuffleBtnClicked(MouseEvent event)
    {
        shuffleBtn.setStyle("-fx-background-color: #0c4d1a;");
        shuffleBtn.setStyle("-fx-background-color: #0c5f5c;");
        //EVENT HANDLING CODE HERE.
    }

    @FXML
    private void pKeyPressed(KeyEvent event)
    {
        if(event.getCode() == KeyCode.P)
        {
            playerStatus = mediaPlayer.getStatus();
            if(playerStatus == Status.UNKNOWN || playerStatus == Status.DISPOSED || playerStatus == Status.HALTED || playerStatus == Status.STALLED){}
            if(playerStatus == Status.PAUSED || playerStatus == Status.READY || playerStatus == Status.STOPPED)
            {
                mediaPlayer.play();
                playBtn.setText("Pause");
            }
            else if(playerStatus != Status.PAUSED && playerStatus == Status.READY && playerStatus != Status.STOPPED)
            {
                player(songPathList.get(INDEXPOS).toFile());
                mediaPlayer.play();
            }
            else
            {
                mediaPlayer.pause();
                playBtn.setText("Play");
            }
        }
    }

    @FXML
    private void previousBtnExited(MouseEvent event)
    {
        previousBtn.setStyle("-fx-background-color: #0c4d5c;");
    }

    @FXML
    private void previousBtnEntered(MouseEvent event)
    {
        previousBtn.setStyle("-fx-background-color: #0c5f5c;");
    }

    @FXML
    private void previousBtnClicked(MouseEvent event)
    {
        previousBtn.setStyle("-fx-background-color: #0c4d1a;");
        previousBtn.setStyle("-fx-background-color: #0c5f5c;");
        if(iterator.hasNext())
        {
            mediaPlayer.stop();
            INDEXPOS -= 1;
            if(INDEXPOS >= 0) player(songPathList.get(INDEXPOS).toFile());
            mediaPlayer.play();
        }
        else
        {
            mediaPlayer.stop();
            if(INDEXPOS < 0) INDEXPOS = 0;
            mediaPlayer.play();
        }
    }

    @FXML
    private void sKeyPressed(KeyEvent event)
    {
        if(event.getCode() == KeyCode.SPACE)
        {
            playerStatus = mediaPlayer.getStatus();
            if(playerStatus == Status.UNKNOWN || playerStatus == Status.DISPOSED || playerStatus == Status.HALTED || playerStatus == Status.STALLED){}
            if(playerStatus == Status.PAUSED || playerStatus == Status.READY || playerStatus == Status.STOPPED)
            {
                mediaPlayer.play();
                playBtn.setText("Pause");
            }
            else
            {
                mediaPlayer.pause();
                playBtn.setText("Play");
            }
        }
    }

    @FXML
    private void playBtnExited(MouseEvent event)
    {
        playBtn.setStyle("-fx-background-color: #0c4d5c;");

    }

    @FXML
    private void playBtnEntered(MouseEvent event)
    {
        playBtn.setStyle("-fx-background-color: #0c5f5c;");
    }

    @FXML
    private void playBtnClicked(MouseEvent event)
    {
        playBtn.setStyle("-fx-background-color: #0c4d1a;");
        playBtn.setStyle("-fx-background-color: #0c5f5c;");
        playerStatus = mediaPlayer.getStatus();
        if(playerStatus == Status.UNKNOWN || playerStatus == Status.DISPOSED || playerStatus == Status.HALTED || playerStatus == Status.STALLED){}
        if(playerStatus == Status.PAUSED || playerStatus == Status.READY || playerStatus == Status.STOPPED)
        {
            mediaPlayer.play();
            playBtn.setText("Pause");
        }
        else if(playerStatus != Status.PAUSED && playerStatus == Status.READY && playerStatus != Status.STOPPED)
        {
            player(songPathList.get(INDEXPOS).toFile());
            mediaPlayer.play();
        }
        else
        {
            mediaPlayer.pause();
            playBtn.setText("Play");
        }
    }

    @FXML
    private void nKeyPressed(KeyEvent event)
    {
    }

    @FXML
    private void nextBtnExited(MouseEvent event)
    {
        nextBtn.setStyle("-fx-background-color: #0c4d5c;");

    }

    @FXML
    private void nextBtnEntered(MouseEvent event)
    {
        nextBtn.setStyle("-fx-background-color: #0c5f5c;");
    }

    @FXML
    private void nextBtnClicked(MouseEvent event)
    {
        nextBtn.setStyle("-fx-background-color: #0c4d1a;");
        nextBtn.setStyle("-fx-background-color: #0c5f5c;");
        if(iterator.hasNext())
        {
            mediaPlayer.stop();
            INDEXPOS += 1;
            if((INDEXPOS + 1) <= songPathList.size()) player(songPathList.get(INDEXPOS).toFile());
            mediaPlayer.play();
        }
        else
        {
            mediaPlayer.stop();
            INDEXPOS = 0;
            mediaPlayer.play();
        }
    }

    @FXML
    private void repeatBtnExited(MouseEvent event)
    {
        repeatBtn.setStyle("-fx-background-color: #0c4d5c;");

    }

    @FXML
    private void repeatBtnEntered(MouseEvent event)
    {
        repeatBtn.setStyle("-fx-background-color: #0c5f5c;");
    }

    @FXML
    private void repeatBtnClicked(MouseEvent event)
    {
        repeatBtn.setStyle("-fx-background-color: #0c4d1a;");
        repeatBtn.setStyle("-fx-background-color: #0c5f5c;");
        playerStatus = mediaPlayer.getStatus();
        if(playerStatus == Status.UNKNOWN || playerStatus == Status.DISPOSED || playerStatus == Status.HALTED || playerStatus == Status.STALLED){}
        if(playerStatus == Status.PAUSED || playerStatus == Status.READY || playerStatus == Status.STOPPED)
        {
            if(REPEATONE)
            {
                REPEATALL = false;
                iterator = songPathList.iterator();
                if(mediaPlayer.getCurrentTime().equals(mediaPlayer.getMedia().getDuration()))
                {
                    playBtn.setText("Pause");
                    if(iterator.hasNext())
                    {
                        iterator.remove();
                        file = (File) iterator.next();
    //                    iterator.remove();
                        player(file);
                        mediaPlayer.play();
                    }
                }
            }
            else if(REPEATALL)
            {
                REPEATONE = false;
                if((INDEXPOS + 1) == songPathList.size())
                {
                    INDEXPOS = 0;
                    player(songPathList.get(INDEXPOS).toFile());
                }
            }
            else
            {}
            playBtn.setText("Pause");
        }
    }

    @FXML
    private void muteBtnExited(MouseEvent event)
    {
        muteBtn.setStyle("-fx-background-color: #0c4d5c;");
    }

    @FXML
    private void muteBtnEntered(MouseEvent event)
    {
        muteBtn.setStyle("-fx-background-color: #0c5f5c;");
    }

    @FXML
    private void muteBtnClicked(MouseEvent event)
    {
        muteBtn.setStyle("-fx-background-color: #0c4d1a;");
        muteBtn.setStyle("-fx-background-color: #0c5f5c;");                         
        if(mediaPlayer.getStatus() == Status.PLAYING || mediaPlayer.getStatus() == Status.PAUSED || mediaPlayer.getStatus() == Status.READY || mediaPlayer.getStatus() == Status.STOPPED)
        {
            if(!mediaPlayer.isMute())
            {
                mediaPlayer.setMute(true);
                muteBtn.setText("Muted");
            }
            else
            {
                mediaPlayer.setMute(false);
                muteBtn.setText("Mute");
            }
        }
    }

    @FXML
    private void volumeSeekerClicked(MouseEvent event)
    {
        volumeSeeker.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(javafx.beans.Observable observable)
            {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        volumeSeeker.valueProperty().addListener(e2 -> {
            if(volumeSeeker.isValueChanging())
            {
                if(defaultPlayerVolume != null)
                {
                    mediaPlayer.setVolume(defaultPlayerVolume);
                    mediaPlayer.setVolume(volumeSeeker.getValue() / 100.0);
                }
//                    valueUpdater();
            }
        });
    }

    @FXML
    private void volumeSeekerDragged(MouseEvent event)
    {
        volumeSeeker.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(javafx.beans.Observable observable)
            {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        volumeSeeker.valueProperty().addListener(e2 -> {
            if(volumeSeeker.isValueChanging())
            {
                if(defaultPlayerVolume != null)
                {
                    mediaPlayer.setVolume(defaultPlayerVolume);
                    mediaPlayer.setVolume(volumeSeeker.getValue() / 100.0);
                }
                valueUpdater();
            }
        });
    }
        
    public void player(File file)
    {
        if(file != null)
        {
            media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            volumeSeeker.setValue(mediaPlayer.getVolume() * 100);
            mediaPlayer.setAutoPlay(true);
            playBtn.setText("Pause");
            currentSongLbl.setText(file.getName());
            mediaPlayer.currentTimeProperty().addListener(e -> valueUpdater());
        }
    }
    
    public void valueUpdater()
    {
        defaultPlayerTime = mediaPlayer.getMedia().getDuration();
        currentPlayerTime = mediaPlayer.getCurrentTime();
        defaultPlayerVolume = mediaPlayer.getVolume();
        
        int currentMillis = (int) Math.floor(currentPlayerTime.toMillis());
        int currentSecond = (int) Math.floor(currentMillis / 1000);
        int currentMinute = (int) Math.floor((currentSecond / 60));
        int currentHour = (int) Math.floor(currentSecond / (60 * 60));
        
        int defaultMillis = (int) Math.floor(defaultPlayerTime.toMillis());
        int defaultSecond = (int) Math.floor(defaultMillis / 1000);
        int defaultMinute = (int) Math.floor(defaultSecond / 60);
        int defaultHour = (int) Math.floor(defaultSecond / (60 * 60));
        
        String currentHourTimeFormat = String.format("%d:%02d:%02d", currentHour, currentMinute, currentSecond);
        String currentMinuteTimeFormat = String.format("%02d:%02d", currentMinute, currentSecond);
        String currentSecondTimeFormat = String.format("%02d", currentSecond);
        
        String defaultHourTimeFormat = String.format("%d:%02d:%02d", defaultHour, defaultMinute, defaultSecond);
        String defaultMinuteTimeFormat = String.format("%02d:%02d", defaultMinute, defaultSecond);
        String defaultSecondTimeFormat = String.format("%02d", defaultSecond);

        if(defaultPlayerTime != null && defaultPlayerVolume != null && !timeSeeker.isValueChanging() && !volumeSeeker.isValueChanging())
        {
            if(defaultPlayerTime != null && !timeSeeker.isValueChanging())
            {
                timeSeeker.setValue(currentPlayerTime.divide(defaultPlayerTime).toMillis()*100.0);
                if(defaultMinute > 59)
                {
                    defaultTimeLbl.setText(defaultHourTimeFormat);
                    currentTimeLbl.setText(currentHourTimeFormat);
                }
                if(defaultSecond > 59)
                {
                    defaultTimeLbl.setText(defaultMinuteTimeFormat);
                    currentTimeLbl.setText(currentMinuteTimeFormat);
                }
                else
                {
                    defaultTimeLbl.setText(defaultSecondTimeFormat);
                    currentTimeLbl.setText(currentSecondTimeFormat);
                }
            }
            if(defaultPlayerVolume != null && !volumeSeeker.isValueChanging())
            {
                if(defaultPlayerVolume != null)
                {
                    volumeSeeker.setValue((defaultPlayerVolume));
                }
                if(!volumeSeeker.isValueChanging())
                {
                    volumeSeeker.setValue(mediaPlayer.getVolume() * 100.0);
                }
            }
            iterator = songPathList.iterator();
//            if(mediaPlayer.getCurrentTime().equals(mediaPlayer.getMedia().getDuration()))
//            {
//                playBtn.setText("Pause");
//                if(iterator.hasNext())
//                {
//                    iterator.remove();
//                    file = (File) iterator.next();
////                    iterator.remove();
//                    player(file);
//                    mediaPlayer.play();
//                }
//            }
//            if(!songPathList.isEmpty())
//            {
//                for(int i = 0; i <= songPathList.size() - 1; i++)
//                {
//                    System.out.println(songPathList.get(i));
//                    songPathList.add(songPathList.get(i).toFile());
//                }
//                for(int i = 0; i <= songPathList.size() - 1; i++)
//                {
//                    System.out.println(songPathList.get(i));
//                }
//            }
        }
    }
    
    private List<Path> folderTreeWalker(String directory)
    {
        try(Stream <Path> paths = Files.walk(Paths.get(directory), Integer.MAX_VALUE))
        {
//            songPathList = new ArrayList();
//            paths.filter(Files::isRegularFile).forEach(songPathList::add); //Doesn't add the items.
            songPathList = paths.filter(Files::isRegularFile).collect(Collectors.toList());
            return songPathList;
            
        } catch(Exception err)
        {
            System.out.println(err);
        }
        return null;
    }
    
    private ObservableList<Path> songView(List el_list){
        ObservableList<Path> showList = (ObservableList<Path>) el_list;
        return showList;
    }

    @FXML
    private void songListViewClicked(MouseEvent event)
    {
    }
}
