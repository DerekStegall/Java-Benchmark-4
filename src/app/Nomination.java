package app;

import java.io.Serializable;
import java.util.Dictionary;

public class Nomination implements Serializable {

    private static final long serialVersionUID = 1L;

    Dictionary<String, String> teacherInfo;
    Dictionary<String, String> studentInfo;

    public Nomination(Dictionary<String, String> teacherInfo, Dictionary<String, String> studentInfo) {

        this.teacherInfo = teacherInfo;
        this.studentInfo = studentInfo;

    }

}