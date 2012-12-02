package net.hogedriven.backpaper0.javaeeadventcalendar;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@RequestScoped
@Path("calc")
public class Calc {

    @Inject
    CalcBean calcBean;

    @GET
    @Path("add")
    public String add(@QueryParam("x") int x, @QueryParam("y") int y) {
        int result = calcBean.add(x, y);
        return x + " + " + y + " = " + result;
    }

}
