package pers.loren.coderhub.leetcode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Arrays;

/**
 * Copyright © 2019-07-11 by loren
 */
public class LeetCode {

    public static void main(String[] args) {
        //两数相加
        ListNode l2 = new ListNode(9);
        ListNode l22 = new ListNode(9);
        l2.next = l22;
        Long start = System.currentTimeMillis();
//        1.两数相加
//        addTwoNumbers(l1, l2);
//        addTwoNumbersDirect(l1, l2);
//        2.找两个有序数组中位数
        findMedianSortedArrays(new int[]{2}, new int[]{1, 3, 4});
        System.out.println("耗时:" + (System.currentTimeMillis() - start) + "ms");
    }

    //归并排序=>返回中位数 时间复杂度O(m+n) 但要求为:O(log(m+n）
    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] temp = new int[nums1.length + nums2.length];
        int i = 0;
        int j = 0;
        int t = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                temp[t++] = nums1[i++];
            } else {
                temp[t++] = nums2[j++];
            }
        }
        while (i < nums1.length) {
            temp[t++] = nums1[i++];
        }
        while (j < nums2.length) {
            temp[t++] = nums2[j++];
        }
        System.out.println("temp = " + Arrays.toString(temp));
        double b = (temp[(temp.length - 1) / 2] + temp[temp.length / 2]) * 1.0 / 2;
        System.out.println(b);
        return b;
    }

    //每位分别相加
    private static ListNode addTwoNumbersDirect(ListNode l1, ListNode l2) {
        int increase = 0;
        ListNode root = new ListNode(-1);
        ListNode nextNode = new ListNode(-1);
        root.next = nextNode;
        while (null != l1 || null != l2) {
            int total = (null == l1 ? 0 : l1.val) + (null == l2 ? 0 : l2.val) + increase;
            int num = total % 10;
            increase = total / 10;
            System.out.println("l1 = [" + (null == l1 ? 0 : l1.val) + "], l2 = [" + (null == l2 ? 0 : l2.val) + "]");
            System.out.println("num = [" + num + "], increase = [" + increase + "]");
            ListNode temp = new ListNode(num);
            if (nextNode.val == -1) {
                nextNode.val = temp.val;
            } else {
                nextNode.next = temp;
                nextNode = temp;
            }
            l1 = null == l1 ? null : l1.next;
            l2 = null == l2 ? null : l2.next;
            if (null == l1 && null == l2 & increase != 0) {
                nextNode.next = new ListNode(increase);
            }
        }
        System.out.println(JSON.toJSONString(root.next, SerializerFeature.DisableCircularReferenceDetect));
        return root.next;
    }

    //转为数字->相加->转为链表
    //为int时正常,当很大时必须改为long类型
    //由于题目规定为int,故不可行
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long num1 = 0;
        long num2 = 0;
        long zeroCount = 1;
        if (l1.next == null) {
            num1 = l1.val;
        }
        if (l2.next == null) {
            num2 = l2.val;
        }
        while (null != l1.next || null != l2.next) {
            if (l1.next != null) {
                if (num1 == 0) {
                    num1 = l1.val * zeroCount;
                }
                num1 += l1.next.val * zeroCount * 10;
                l1 = l1.next;
            }
            if (l2.next != null) {
                if (num2 == 0) {
                    num2 = l2.val * zeroCount;
                }
                num2 += l2.next.val * zeroCount * 10;
                l2 = l2.next;
            }
            zeroCount *= 10;
        }
        System.out.println("num1结果: " + num1 + "\nnum2结果: " + num2);
        long num3 = num1 + num2;
        System.out.println("num3结果: " + num3);
        ListNode root = new ListNode(-1);
        ListNode nextNode = new ListNode(-1);
        root.next = nextNode;
        int numCount = Long.toString(num3).length();
        for (int i = 0; i < numCount; i++) {
            ListNode temp = new ListNode(Math.toIntExact(num3 % 10));
            System.out.println(temp.val);
            num3 /= 10;
            if (i == 0) {
                nextNode.val = temp.val;
            } else {
                nextNode.next = temp;
                //记录最深节点
                nextNode = temp;
            }
        }
        System.out.println(JSON.toJSONString(root.next, SerializerFeature.DisableCircularReferenceDetect));
        return root.next;
    }


}
