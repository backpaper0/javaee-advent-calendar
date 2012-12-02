package net.hogedriven.backpaper0.javaeeadventcalendar;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class CalcBean {

    public int add(int x, int y) {
        return x + y;
    }

}
