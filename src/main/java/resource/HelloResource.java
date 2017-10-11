package resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path(value = "/api/version")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {

  @GET
  public String sayHello(@Context HttpServletRequest httpRequest) throws Exception {

    StringBuffer requestURL = httpRequest.getRequestURL();
    String queryString = httpRequest.getQueryString();

    if (queryString == null) {
      return "hello! I am at " + requestURL.toString();
    } else {
      return "hello! I am at  " + requestURL.append('?').append(queryString).toString();
    }
  }

}