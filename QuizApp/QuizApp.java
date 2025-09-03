import java.util.*;

class Question {
    String question;
    String[] options;
    int correctAnswer; // index (0-based)

    Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // List of questions
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("1. What are Java loops?",
                new String[]{"Used for conditional branching", "Used to repeat code", "Used for inheritance", "Used for exceptions"}, 1));
        questions.add(new Question("2. What is enhanced for-loop?",
                new String[]{"Loop with break only", "Simplified loop for collections", "Used in switch cases", "Not in Java"}, 1));
        questions.add(new Question("3. How do you handle multiple user inputs?",
                new String[]{"Using Scanner / BufferedReader", "Using Loops", "Both A and B", "None"}, 2));
        questions.add(new Question("4. How is a switch-case different from if-else?",
                new String[]{"Faster than if-else", "Works with limited data types", "Clear syntax", "All of the above"}, 3));
        questions.add(new Question("5. What are collections in Java?",
                new String[]{"Framework to store & manipulate groups of objects", "A type of array", "A package for exceptions", "None"}, 0));

        // Shuffle questions (optional)
        Collections.shuffle(questions);

        int score = 0;
        int qNo = 1;

        for (Question q : questions) {
            System.out.println("\n" + qNo + ". " + q.question);
            for (int i = 0; i < q.options.length; i++) {
                System.out.println((i + 1) + ". " + q.options[i]);
            }

            System.out.print("Your Answer (1-4): ");
            int ans = sc.nextInt() - 1;

            if (ans == q.correctAnswer) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Wrong! Correct answer: " + q.options[q.correctAnswer]);
            }
            qNo++;
        }

        System.out.println("\n🎯 Quiz Finished!");
        System.out.println("Your Score: " + score + "/" + questions.size());

        sc.close();
    }
}