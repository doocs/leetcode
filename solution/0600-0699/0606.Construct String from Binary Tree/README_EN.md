# [606. Construct String from Binary Tree](https://leetcode.com/problems/construct-string-from-binary-tree)

[中文文档](/solution/0600-0699/0606.Construct%20String%20from%20Binary%20Tree/README.md)

## Description

<p>You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.</p>

<p>The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.</p>

<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> Binary tree: [1,2,3,4]

       1

     /   \

    2     3

   /    

  4     



<b>Output:</b> "1(2(4))(3)"

<br/><b>Explanation:</b> Originallay it needs to be "1(2(4)())(3()())", <br/>but you need to omit all the unnecessary empty parenthesis pairs. <br/>And it will be "1(2(4))(3)".

</pre>

</p>

<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> Binary tree: [1,2,3,null,4]

       1

     /   \

    2     3

     \  

      4 



<b>Output:</b> "1(2()(4))(3)"

<br/><b>Explanation:</b> Almost the same as the first example, <br/>except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.

</pre>

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->
