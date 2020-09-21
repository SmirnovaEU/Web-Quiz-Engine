package engine;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Quiz {
    private String title;
    private String text;
    private String[] options;
    private int id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int answer;
    private static int count = 0;

    public Quiz() {
        this.id = count++;
    }

    public Quiz(String title, String text, String[] options, int answer) {
        this.id = count++;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public int getAnswer() {
        return answer;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }
}
