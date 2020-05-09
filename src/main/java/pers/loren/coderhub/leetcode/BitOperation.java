package pers.loren.coderhub.leetcode;

import java.util.Arrays;

/**
 * Copyright © 2020/5/7 by loren
 */
public class BitOperation {
    public static void main(String[] args) {
        //大小写转化
        System.out.println(('A' | ' ') == 'a');
        System.out.println(('b' & '_') == 'B');
        System.out.println(('c' ^ ' ') == 'C');
        System.out.println(('C' ^ ' ') == 'c');
        //判断异号
        int x = 3;
        int y = -9;
        System.out.println("x,y异号:" + ((x ^ y) < 0));
        //交换数
        int a = 3;
        int b = 6;
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println("a=3,b=6 交换:" + a + " , " + b);
        //加减1
        int c = 3;
        c = -~c;
        System.out.println("c=-~c " + c);
        c = ~-c;
        System.out.println("c=~-c " + c);
        System.out.println("-~c " + -~c);
        System.out.println("~-c " + ~-c);
        //>>>表示不带符号向右移动二进制数，移动后前面统统补0；两个箭头表示带符号移动，
        //没有<<<这种运算符，因为左移都是补零，没有正负数的区别
        System.out.println("10>>>1="+(10>>>1));
        System.out.println("-10>>>2="+(-10>>2));
        System.out.println("-10<<1="+(-10<<1));
        //>> :负数右移用1补位 正数右移用0补位
        //>>>:无论正负，都用0补位
        System.out.println(Integer.toBinaryString(-16));
        System.out.println(Integer.toBinaryString(-16>>>2));
    }
}
