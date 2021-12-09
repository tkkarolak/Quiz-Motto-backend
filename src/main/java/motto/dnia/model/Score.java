package motto.dnia.model;

import java.util.Comparator;

public class Score {
	String name;
	Integer score;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Name: " + name + ", score: " + score;
	}

	public static Comparator<Score> sortByScoreDescending = new Comparator<Score>() {
		public int compare(Score o1, Score o2) {
			return o2.getScore().compareTo(o1.getScore());
		}
	};
}

