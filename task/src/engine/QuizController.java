package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizController {

    List<Quiz> quizzes = new ArrayList<>();
    private final String trueFeedback = "Congratulations, you're right!";
    private final String falseFeedback = "Wrong answer! Please, try again.";

    @PostMapping(path = "/api/quizzes")
    public Quiz createQuiz(@RequestBody Quiz quizInfo) {
        Quiz quiz = new Quiz(quizInfo.getTitle(), quizInfo.getText(), quizInfo.getOptions(), quizInfo.getAnswer());
        quizzes.add(quiz);
        return quiz;
    }

    @GetMapping(path = "/api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        if (id >= quizzes.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found");
        }
        return quizzes.get(id);
    }

    @GetMapping(path = "/api/quizzes")
    public List<Quiz> getList() {
        return quizzes;
    }

    @PostMapping(path = "/api/quizzes/{id}/solve")
    public Response postAnswer(@RequestParam("answer") int answer, @PathVariable int id) {
        Quiz currentQuiz = quizzes.get(id);

        if (currentQuiz!= null && answer == currentQuiz.getAnswer()) {
            return new Response(true, trueFeedback);
        } else {
            return new Response(false, falseFeedback);
        }
    }
}
