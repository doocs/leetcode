# [16.22. Langtons Ant](https://leetcode.cn/problems/langtons-ant-lcci)

[中文文档](/lcci/16.22.Langtons%20Ant/README.md)

## Description

<p>An ant is sitting on an infinite grid of white and black squares. It initially faces right. All squares are white initially.</p>

<p>At each step, it does the following:</p>

<p>(1) At a white square, flip the color of the square, turn 90 degrees right (clockwise), and move forward one unit.</p>

<p>(2) At a black square, flip the color of the square, turn 90 degrees left (counter-clockwise), and move forward one unit.</p>

<p>Write a program to simulate the first K moves that the ant makes and print the final board as a grid.</p>

<p>The grid should be represented as an array of strings, where each element represents one row in the grid. The black square is represented as <code>&#39;X&#39;</code>, and the white square is represented as <code>&#39;_&#39;</code>, the square which is occupied by the ant is represented as <code>&#39;L&#39;</code>, <code>&#39;U&#39;</code>, <code>&#39;R&#39;</code>, <code>&#39;D&#39;</code>, which means the left, up, right and down orientations respectively. You only need to return the minimum matrix that is able to contain all squares that are passed through by the ant.</p>

<p><strong>Example 1:</strong></p>

<pre>



<strong>Input:</strong> 0



<strong>Output: </strong>[&quot;R&quot;]



</pre>

<p><strong>Example 2:</strong></p>

<pre>



<strong>Input:</strong> 2



<strong>Output:



</strong>[



&nbsp; &quot;_X&quot;,



&nbsp; &quot;LX&quot;



]



</pre>

<p><strong>Example 3:</strong></p>

<pre>



<strong>Input:</strong> 5



<strong>Output:



</strong>[



&nbsp; &quot;_U&quot;,



&nbsp; &quot;X_&quot;,



&nbsp; &quot;XX&quot;



]



</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>K &lt;= 100000</code></li>
</ul>

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
