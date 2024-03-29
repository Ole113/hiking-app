package com.hikingapp.view;

import com.hikingapp.model.ModifyFile;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.Timer;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Displays the information about the hike in GUI form.
 */
public class HikeInfoFrame extends javax.swing.JFrame {

    private final HashMap<String, String> HIKE_INFO;
    private int hikeTimerSeconds;
    private Timer timer;
    private boolean timerPaused;
    private HikeResultFrame resultFrame;
    
    /**
     * Initializes the instance variables and initializes the form components.
     * @param hikeInfo The HashMap that contains all of the information about the hike such as name, distance, etc.
     */
    public HikeInfoFrame(HashMap<String, String> hikeInfo) {
        this.HIKE_INFO = hikeInfo;
        this.hikeTimerSeconds = 0;
        this.timerPaused = true;
        initComponents();
    }

    /**
     * Gets the name of the hike marked up with HTML.
     * @return The hike's name.
     */
    public String getHikeName() {
        return "<html><h1>" + this.HIKE_INFO.get("name") + "</h1></html>";
    }
    
    /**
     * Gets the URL of the hike's image.
     * @return The URL.
     */
    public String getHikeImgUrl() {
        return this.HIKE_INFO.get("imgUrl");
    }
    
    /**
     * Method needs to return a String because it is setting the inside of a JLabel and the required parameter cannot be modified to be a different type.
     * @return A blank string. If the string is filled with a value the value will be printed next to the image.
     */
    public String getHikeImage() {
        try {
            URL url = new URL(getHikeImgUrl());
            Image image = ImageIO.read(url);
            hikeImage.setIcon(new ImageIcon(image));
        } catch(IOException error) {
            System.out.println("An IOException occured in getHikeImage(). " + error);
        }
        return "";
    }
    
    /**
     * Gets the stars for the hike and the star votes.
     * The stars is the rating out of 5 and the star votes is the number of people who have voted for the rating.
     * @return The hike rating.
     */
    public String getHikeStars() {
        return "Hike rating: " + this.HIKE_INFO.get("stars") + "(" + this.HIKE_INFO.get("starVotes") + ")";
    }
    
    /**
     * Gets the difficulty of the hike.
     * @return Hike difficulty.
     */
    public String getHikeDifficulty() {
        return "Hike difficulty: " + this.HIKE_INFO.get("difficulty");
    }
    
    /**
     * Gets the summary of the hike.
     * @return Hike summary.
     */
    public String getHikeSummary() {
        return this.HIKE_INFO.get("summary");
    }
    
    /**
     * Gets the ascent of the hike.
     * @return Hike ascent.
     */
    public String getHikeAscent() {
        return "Ascent: " + this.HIKE_INFO.get("ascent");
    }
    
    /**
     * Gets the descent of the hike.
     * @return Hike descent.
     */
    public String getHikeDescent() {
        return "Descent: " + this.HIKE_INFO.get("descent");
    }
    
    /**
     * Gets the highest point of the hike.
     * @return Hike high.
     */
    public String getHikeHigh() {
        return "High High: " + this.HIKE_INFO.get("high");
    }
    
    /**
     * Gets the lowest point of the hike.
     * @return Hike low.
     */
    public String getHikeLow() {
        return "Hike Low: " + this.HIKE_INFO.get("low");
    }
    
    /**
     * Gets the location of the hike.
     * @return Hike location.
     */
    public String getHikeLocation() {
        return this.HIKE_INFO.get("location");
    }
    
    /**
     * Gets the length of the hike.
     * @return Hike Length.
     */
    public String getHikeLength() {
        return "Hike length: " + this.HIKE_INFO.get("length") + " miles";
    }
    
    /**
     * Gets the condition of the hike.
     * The condition details is commonly empty.
     * @return Hike condition
     */
    public String getHikeCondition() {
        return "Hike status: " + this.HIKE_INFO.get("conditionStatus") + ", " + (!this.HIKE_INFO.get("conditionDetails").isEmpty() ? this.HIKE_INFO.get("conditionDetails") : "N/A");
    }
    
    /**
     * Gets the URL of the website where more information is available about the hike.
     * @return Website URL.
     */
    public String getHikeMoreInfo() {
        return "To learn more go to this link: " + this.HIKE_INFO.get("url");
    }
    
    /**
     * Gets the date that the hike condition was last updated at.
     * @return A string with the date.
     */
    public String getHikeConditionDate() {
        return "Last updated: " + this.HIKE_INFO.get("conditionDate");
    }
    
    /**
     * Gets the time that the hike timer has been running in a string format separated with colons.
     * @return Hike Time.
     */
    public String getHikeTime() {
        int[] timeNumbers = { 0, 0, 0 };
        String timeString = "";
        
        timeNumbers[0] = (int) (this.hikeTimerSeconds / 3600);
        timeNumbers[1] = (int) (this.hikeTimerSeconds / 60 % 60);
        timeNumbers[2] = (int) (this.hikeTimerSeconds % 60);

        if(timeNumbers[0] < 10) timeString += "0" + timeNumbers[0];
        else timeString += timeNumbers[0];
        if(timeNumbers[1] < 10) timeString += ":0" + timeNumbers[1];
        else timeString += ":" + timeNumbers[1];
        if(timeNumbers[2] < 10) timeString += ":0" + timeNumbers[2];
        else timeString += ":" + timeNumbers[2];

        return timeString;
    }
    
    /**
     *
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hikeName = new javax.swing.JLabel();
        hikeImage = new javax.swing.JLabel();
        hikeStars = new javax.swing.JLabel();
        hikeDifficulty = new javax.swing.JLabel();
        hikeSummary = new javax.swing.JLabel();
        hikeAscent = new javax.swing.JLabel();
        highDescent = new javax.swing.JLabel();
        hikeHigh = new javax.swing.JLabel();
        hikeLow = new javax.swing.JLabel();
        hikeLength = new javax.swing.JLabel();
        hikeLocation = new javax.swing.JLabel();
        hikeCondition = new javax.swing.JLabel();
        hikeStart = new javax.swing.JButton();
        hikeMoreInfo = new javax.swing.JLabel();
        hikeConditionDate = new javax.swing.JLabel();
        hikeTime = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        hikePause = new javax.swing.JButton();
        hikeStop = new javax.swing.JButton();
        exportHike = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        hikeName.setText(getHikeName());

        hikeImage.setText(getHikeImage());
        hikeImage.setMaximumSize(new java.awt.Dimension(34, 16));

        hikeStars.setText(getHikeStars());

        hikeSummary.setText(getHikeSummary());

        hikeAscent.setText(getHikeAscent());

        highDescent.setText(getHikeDescent());

        hikeHigh.setText(getHikeHigh());

        hikeLow.setText(getHikeLow());

        hikeLength.setText(getHikeLength());

        hikeLocation.setText(getHikeLocation());

        hikeCondition.setText(getHikeCondition());

        hikeStart.setText("Start Hike Timer");
        hikeStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hikeStartActionPerformed(evt);
            }
        });

        hikeMoreInfo.setText(getHikeMoreInfo());

        hikeConditionDate.setText(getHikeConditionDate());

        hikePause.setText("Pause Hike Timer");
        hikePause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hikePauseActionPerformed(evt);
            }
        });

        hikeStop.setText("Stop Hike Timer");
        hikeStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hikeStopActionPerformed(evt);
            }
        });

        exportHike.setText("Export Hike");
        exportHike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportHikeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(hikeName, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exportHike))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hikeImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hikeSummary)
                                    .addComponent(hikeLocation)
                                    .addComponent(hikeMoreInfo)
                                    .addComponent(hikeTime)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(time)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(hikeAscent)
                                                .addComponent(hikeStars)
                                                .addComponent(hikeHigh))
                                            .addGap(147, 147, 147)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(hikeLow)
                                                .addComponent(hikeDifficulty)
                                                .addComponent(highDescent)))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(hikeLength)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(hikeStart))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hikeCondition)
                                    .addComponent(hikeConditionDate))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(hikeStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(hikePause, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(32, 32, 32))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hikeName)
                    .addComponent(exportHike))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time)
                .addGap(4, 4, 4)
                .addComponent(hikeImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hikeStars)
                    .addComponent(hikeDifficulty))
                .addGap(18, 18, 18)
                .addComponent(hikeLocation)
                .addGap(42, 42, 42)
                .addComponent(hikeSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(highDescent)
                            .addComponent(hikeAscent))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hikeHigh)
                            .addComponent(hikeLow))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hikeLength)
                            .addComponent(hikeStart))
                        .addGap(20, 20, 20)
                        .addComponent(hikeCondition))
                    .addComponent(hikePause))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hikeStop)
                .addGap(18, 18, 18)
                .addComponent(hikeConditionDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hikeMoreInfo)
                .addGap(82, 82, 82)
                .addComponent(hikeTime)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * When the hike start button is clicked the hike timer is started.
     * @param evt The parameter that is passed in by the action being performed.
     */
    private void hikeStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hikeStartActionPerformed
        if(this.hikeTimerSeconds == 0) {
            ActionListener updateTime = (ActionEvent evt1) -> {
            this.hikeTimerSeconds++;
            this.time.setText(getHikeTime());
        };
        //1000 millisecond delay.
        this.timer = new Timer(1000 ,updateTime);
        
        this.timer.start();
        this.timerPaused = false;
        }
    }//GEN-LAST:event_hikeStartActionPerformed

    /**
     * When the hike stop button is clicked then the results of the hike timer will show.
     * @param evt The parameter that is passed in by the action being performed.
     */
    private void hikeStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hikeStopActionPerformed
        if(!(this.timer == null)) {
            this.timer.stop();

            this.resultFrame = new HikeResultFrame(this.hikeTimerSeconds, getHikeTime(), this.HIKE_INFO.get("imgUrl"), this.HIKE_INFO.get("length"), this.HIKE_INFO.get("ascent"), this.HIKE_INFO.get("descent"), getHikeName());
            this.resultFrame.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_hikeStopActionPerformed

    /**
     * Toggle for pausing/unpausing the hike.
     * @param evt The parameter that is passed in by the action being performed.
     */
    private void hikePauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hikePauseActionPerformed
        if(!this.timerPaused) {
            this.timer.stop();
            this.timerPaused = true;
        } else {
            this.timer.start();
            this.timerPaused = false;
        }
        
    }//GEN-LAST:event_hikePauseActionPerformed

    /**
     * Exports the hike to the chosen file.
     * @param evt The parameter that is passed in by the action being performed.
     */
    private void exportHikeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportHikeActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File chosenFile = fileChooser.getSelectedFile();
            
            JSONObject hikeInfoObject = new JSONObject();
            
            try {
                hikeInfoObject.put("name", this.HIKE_INFO.get("name"));
                hikeInfoObject.put("summary", this.HIKE_INFO.get("summary"));
                hikeInfoObject.put("difficulty", this.HIKE_INFO.get("difficulty"));
                hikeInfoObject.put("stars", this.HIKE_INFO.get("stars"));
                hikeInfoObject.put("starVotes", this.HIKE_INFO.get("starVotes"));
                hikeInfoObject.put("location", this.HIKE_INFO.get("location"));
                hikeInfoObject.put("imgUrl", this.HIKE_INFO.get("imgUrl"));
                hikeInfoObject.put("length", this.HIKE_INFO.get("length"));
                hikeInfoObject.put("ascent", this.HIKE_INFO.get("ascent"));
                hikeInfoObject.put("descent", this.HIKE_INFO.get("descent"));
                hikeInfoObject.put("high", this.HIKE_INFO.get("high"));
                hikeInfoObject.put("low", this.HIKE_INFO.get("low"));
                hikeInfoObject.put("conditionStatus", this.HIKE_INFO.get("conditionStatus"));
                hikeInfoObject.put("conditionDetails", this.HIKE_INFO.get("conditionDetails"));
                hikeInfoObject.put("conditionDate", this.HIKE_INFO.get("conditionDate"));
                hikeInfoObject.put("url", this.HIKE_INFO.get("url"));
                
                ModifyFile modifier = new ModifyFile();

                //Checks if there's anything in the file that the user has chosen, if there is then it is cleared.
                if(!(new String(modifier.readFromFile(chosenFile, 0, (int) chosenFile.length())).equals(""))) {
                    modifier.clearContents(chosenFile);
                }
                
                modifier.writeToFile(chosenFile, hikeInfoObject.toString(), 0);

            } catch (JSONException | IOException ex) {
                Logger.getLogger(HikeInfoFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_exportHikeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exportHike;
    private javax.swing.JLabel highDescent;
    private javax.swing.JLabel hikeAscent;
    private javax.swing.JLabel hikeCondition;
    private javax.swing.JLabel hikeConditionDate;
    private javax.swing.JLabel hikeDifficulty;
    private javax.swing.JLabel hikeHigh;
    private javax.swing.JLabel hikeImage;
    private javax.swing.JLabel hikeLength;
    private javax.swing.JLabel hikeLocation;
    private javax.swing.JLabel hikeLow;
    private javax.swing.JLabel hikeMoreInfo;
    private javax.swing.JLabel hikeName;
    private javax.swing.JButton hikePause;
    private javax.swing.JLabel hikeStars;
    private javax.swing.JButton hikeStart;
    private javax.swing.JButton hikeStop;
    private javax.swing.JLabel hikeSummary;
    private javax.swing.JLabel hikeTime;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
