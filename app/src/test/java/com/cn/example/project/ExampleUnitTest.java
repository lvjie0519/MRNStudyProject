package com.cn.example.project;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        long long1 = 1604385251206l;
        long long2 = 1604385251263l;
        long long3 = 1604385251405l;
        long long4 = 1604385251728l;
        long long5 = 1604385252227l;
        long long6 = 1604385252440l;

        long t1 = (long2-long1);
        long t2 = (long3-long2);
        long t3 = (long4-long3);
        long t4 = (long5-long4);
        long t5 = (long6-long5);


        System.out.println("onclick-->receive msg: "+t1);
        System.out.println("receive msg-->start onCreate: "+t2+"  onclick-->start onCreate: "+(t2+t1));
        System.out.println("start onCreate-->start onResume: "+t3+"  onclick-->start onResume: "+(t3+t2+t1));
        System.out.println("start onResume-->start willMount: "+t4+"  onclick-->start willMount: "+(t4+t3+t2+t1));
        System.out.println("start willMount-->start didMount: "+t5+"  onclick-->start didMount: "+(t5+t4+t3+t2+t1));
    }
}