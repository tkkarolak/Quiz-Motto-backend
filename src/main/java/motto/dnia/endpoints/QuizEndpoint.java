package motto.dnia.endpoints;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import motto.dnia.init.GlobalVariables;
import motto.dnia.model.Quiz;
import motto.dnia.model.Score;

@Path ("/quiz")
public class QuizEndpoint {

	@GET
	@Path("/getQuizList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Quiz> quizList() {

		List<Quiz> questionPool = new ArrayList<Quiz>();
		List<Integer> questionId = new ArrayList<Integer>();

		Random r = new Random();
		int index;

		while(questionPool.size() != 5) {

			index = r.nextInt(GlobalVariables.quizQuestions.size() - 1);
			if (!questionId.contains(index)) {
				questionId.add(index);
				questionPool.add(GlobalVariables.quizQuestions.get(index));
			}
		}
		return questionPool;
	}
	
	@POST
	@Path("/sendScore")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Score publishScore(Score score) {
		GlobalVariables.scores.add(score);
		return score;
	}
	
	@GET
	@Path("/getLeaderboard")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Score> sortLeaderboard(){
		List<Score> podiumList = new ArrayList<Score>();
		int scoreSize = GlobalVariables.scores.size();
		
		Collections.sort(GlobalVariables.scores, Score.sortByScoreDescending);
		
		for (int i = 0; scoreSize != i; i++) {
			if (podiumList.size() == 3) {break;}
			podiumList.add(GlobalVariables.scores.get(i));
		}
		return podiumList;
	}
}
