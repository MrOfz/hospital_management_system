package com.zy.test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Test1 {
    public boolean flag;
    @Test
    public void testByBoolean(){
        System.out.println(flag);
    }

    @Test void testByString(){
//        String s1 = "nowcoder";
//        String s2 = "nowcoder";
//        System.out.println(s1 == s2);
//        System.out.println(s1.equals(s2));
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < 100; i++) {
            list.add(i);
        }
        int page = 2;
        int pageSize = 10;
        System.out.println(list.subList((page - 1) * pageSize, page * pageSize));
    }
}
