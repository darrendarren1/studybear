package sample.fabrics.io.study;

/**
 * Created by earlybirdcamp on 6/15/16.
 */
public class HelpfulTips {

    public HelpfulTips(){

    }

    public static String getTip(){
        String[] tips = {"study when sleepy | study close to bedtime", "space it out | start far in advance",
                "tell a tale | use material to create a a story", "move around | study in different places",
                "switch it up | study different topics", "put yourself to the test | quiz yourself",
                "write it out | handwriting > typing", "shout the facts | loud = mentally storing",
                "drink up | hydrate", "treat yo'self | eat snacks", "take a time out | breaks will help",
                "work it out | exercise is your friend", "daaaance to the music | relax and listen",
                "doze off | get sleep, don't lose it", " learn what works | use this app",
                "feel free to inhale | take a deep breathe", "exit facebook | studying > social life"};


        int num = (int) (Math.random() * tips.length);

        return tips[num];
    }
}
