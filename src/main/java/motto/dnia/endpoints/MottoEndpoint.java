package motto.dnia.endpoints;

import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import motto.dnia.init.GlobalVariables;
import motto.dnia.model.Motto;

@Path("/motto")
public class MottoEndpoint {
	
	@GET
	@Path("/getMotto")
	@Produces(MediaType.APPLICATION_JSON)
	public Motto sayHtmlHello() {

		Random r = new Random();
		int rand = r.nextInt(GlobalVariables.mottos.size() - 1);
		return GlobalVariables.mottos.get(rand);
	}
}
