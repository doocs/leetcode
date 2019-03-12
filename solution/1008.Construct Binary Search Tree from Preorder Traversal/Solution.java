{\rtf1\ansi\ansicpg936\cocoartf1671\cocoasubrtf200
{\fonttbl\f0\fswiss\fcharset0 Helvetica;\f1\fnil\fcharset134 PingFangSC-Regular;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural\partightenfactor0

\f0\fs24 \cf0 /**\
 * Definition for a binary tree node.\
 * public class TreeNode \{\
 *     int val;\
 *     TreeNode left;\
 *     TreeNode right;\
 *     TreeNode(int x) \{ val = x; \}\
 * \}\
 */\
class Solution \{\
    public TreeNode bstFromPreorder(int[] preorder) \{\
        if (preorder == null || preorder.length == 0) \{\
            return null;\
        \}\
        // 
\f1 \'bd\'f8\'c8\'eb\'b7\'d6\'d6\'ce\'b7\'a8\'b5\'c4\'b5\'dd\'b9\'e9
\f0 \
        return helper(preorder, 0, preorder.length - 1);\
    \}\
    \
    private TreeNode helper(int[] preorder, int start, int end) \{\
        // System.out.println("start: " + start + " end: " + end);\
        // 
\f1 \'c8\'b7\'c8\'cf\'b5\'dd\'b9\'e9\'bd\'e1\'ca\'f8\'b5\'c4\'b1\'ea\'d6\'be\'a3\'ac\'b5\'b1
\f0  start == end 
\f1 \'ca\'b1\'a3\'ac\'b1\'ed\'ca\'be\'b8\'c3\'c7\'f8\'bc\'e4\'d6\'bb\'ca\'a3\'cf\'c2\'d2\'bb\'b8\'f6
\f0  subRoot 
\f1 \'bd\'da\'b5\'e3
\f0 \
        if (start > end) \{\
            return null;\
        \}\
        if (start == end) \{\
            return new TreeNode(preorder[start]);\
        \}\
        // 
\f1 \'c7\'b0\'d0\'f2\'b1\'e9\'c0\'fa\'a3\'ac\'ca\'d7\'cf\'c8\'b1\'e9\'c0\'fa\'b5\'bd\'b5\'c4\'ce\'aa\'b8\'f9
\f0 \
        TreeNode root = new TreeNode(preorder[start]);\
        int leftEnd = start;\
        while (leftEnd <= end) \{\
            if (preorder[leftEnd] > preorder[start]) \{\
                break;\
            \}\
            leftEnd++;\
        \}\
        // System.out.println("leftEnd:" + leftEnd + " num: " + preorder[leftEnd]);\
        root.left = helper(preorder, start + 1, leftEnd - 1);\
        root.right = helper(preorder, leftEnd, end);\
        return root;\
    \}\
\}}