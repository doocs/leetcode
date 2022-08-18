# [16.13. Bisect Squares](https://leetcode.cn/problems/bisect-squares-lcci)

[中文文档](/lcci/16.13.Bisect%20Squares/README.md)

## Description

<p>Given two squares on a two-dimensional plane, find a line that would cut these two squares in half. Assume that the top and the bottom sides of the square run parallel to the x-axis.</p>

<p>Each square consists of three values,&nbsp;the coordinate of bottom left corner&nbsp;<code>[X,Y] = [square[0],square[1]]</code>, and the side length&nbsp;of the square <code>square[2]</code>. The line will intersect to the two squares in four points. Return the coordinates of two intersection points <code>[X<sub>1</sub>,Y<sub>1</sub>]</code>&nbsp;and&nbsp;<code>[X<sub>2</sub>,Y<sub>2</sub>]</code>&nbsp;that the forming segment covers the other two intersection points in format of <code>{X<sub>1</sub>,Y<sub>1</sub>,X<sub>2</sub>,Y<sub>2</sub>}</code>. If <code>X<sub>1</sub> != X<sub>2</sub></code>, there should be&nbsp;<code>X<sub>1</sub> &lt; X<sub>2</sub></code>, otherwise there should be&nbsp;<code>Y<sub>1</sub> &lt;= Y<sub>2</sub></code>.</p>

<p>If there are more than one line that can cut these two squares in half, return the one that has biggest slope (slope of a line parallel to the y-axis is considered as infinity).</p>

<p><strong>Example: </strong></p>

<pre>



<strong>Input: </strong>



square1 = {-1, -1, 2}



square2 = {0, -1, 2}



<strong>Output:</strong> {-1,0,2,0}



<strong>Explanation:</strong> y = 0 is the line that can cut these two squares in half.



</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>square.length == 3</code></li>
	<li><code>square[2] &gt; 0</code></li>
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
