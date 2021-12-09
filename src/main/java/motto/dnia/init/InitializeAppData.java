package motto.dnia.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import motto.dnia.model.Motto;
import motto.dnia.model.Quiz;

public class InitializeAppData implements ServletContextListener {

	Motto createMottoFromLine(String line) {
		String[] arrSplit = line.split(";");
		if (arrSplit.length==2) {
			Motto motto = new Motto();
			motto.setMottoText(arrSplit[0]);
			motto.setMottoAuthor(arrSplit[1]);
			return motto;
		}
		return null;

	}

	Quiz createQuestionFromLine(String line) {
		String[] arrSplit = line.split(";");
		if (arrSplit.length==5) {
			Quiz quiz = new Quiz();
			quiz.setId(Long.parseLong(arrSplit[0]));
			quiz.setQuestion(arrSplit[1]);
			quiz.setAnswer1(arrSplit[2]);
			quiz.setAnswer2(arrSplit[3]);
			quiz.setAnswer3(arrSplit[4]);
			return quiz;
		}
		return null;
	}

	public void contextInitialized(ServletContextEvent e) {

		loadAppData("mottos.csv", FileTypeEnum.MOTTO);
		loadAppData("quizQuestions.csv", FileTypeEnum.QUIZ);

	}

	private void loadAppData(String fileName, FileTypeEnum fileTypeEnum) {
		try {
			InputStream is = InitializeAppData.class.getClassLoader().getResourceAsStream(fileName);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				if (fileTypeEnum==FileTypeEnum.MOTTO) {
					Motto motto = createMottoFromLine(line);
					if (motto != null) {
						GlobalVariables.mottos.add(motto);
					}

				} else if (fileTypeEnum==FileTypeEnum.QUIZ) {
					Quiz quiz = createQuestionFromLine(line);
					if (quiz != null) {
						GlobalVariables.quizQuestions.add(quiz);
					}

				}

			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent e) {
		//	    Connection con =
		//     (Connection) e.getServletContext().getAttribute("con");
		//try { con.close(); } 
		//catch (SQLException ignored) { } // close connection
		System.out.println("contextDestroyed(ServletContextEvent e)");
	}
}