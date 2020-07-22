# [547. Friend Circles](https://leetcode.com/problems/friend-circles)

[中文文档](/solution/0500-0599/0547.Friend%20Circles/README.md)

## Description
<p>

There are <b>N</b> students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a <b>direct</b> friend of B, and B is a <b>direct</b> friend of C, then A is an <b>indirect</b> friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

</p>



<p>

Given a <b>N*N</b> matrix <b>M</b> representing the friend relationship between students in the class. If M[i][j] = 1, then the i<sub>th</sub> and j<sub>th</sub> students are <b>direct</b> friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

</p>



<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> 

[[1,1,0],

 [1,1,0],

 [0,0,1]]

<b>Output:</b> 2

<b>Explanation:</b>The 0<sub>th</sub> and 1<sub>st</sub> students are direct friends, so they are in a friend circle. <br/>The 2<sub>nd</sub> student himself is in a friend circle. So return 2.

</pre>

</p>



<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> 

[[1,1,0],

 [1,1,1],

 [0,1,1]]

<b>Output:</b> 1

<b>Explanation:</b>The 0<sub>th</sub> and 1<sub>st</sub> students are direct friends, the 1<sub>st</sub> and 2<sub>nd</sub> students are direct friends, <br/>so the 0<sub>th</sub> and 2<sub>nd</sub> students are indirect friends. All of them are in the same friend circle, so return 1.

</pre>

</p>





<p><b>Note:</b><br>

<ol>

<li>N is in range [1,200].</li>

<li>M[i][i] = 1 for all students.</li>

<li>If M[i][j] = 1, then M[j][i] = 1.</li>

</ol>

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