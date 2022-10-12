# [1916. Count Ways to Build Rooms in an Ant Colony](https://leetcode.com/problems/count-ways-to-build-rooms-in-an-ant-colony)

[中文文档](/solution/1900-1999/1916.Count%20Ways%20to%20Build%20Rooms%20in%20an%20Ant%20Colony/README.md)

## Description

<p>You are an ant tasked with adding <code>n</code> new rooms numbered <code>0</code> to <code>n-1</code> to your colony. You are given the expansion plan as a <strong>0-indexed</strong> integer array of length <code>n</code>, <code>prevRoom</code>, where <code>prevRoom[i]</code> indicates that you must build room <code>prevRoom[i]</code> before building room <code>i</code>, and these two rooms must be connected <strong>directly</strong>. Room <code>0</code> is already built, so <code>prevRoom[0] = -1</code>. The expansion&nbsp;plan is given such that once all the rooms are built, every room will be reachable from room <code>0</code>.</p>

<p>You can only build <strong>one room</strong> at a time, and you can travel freely between rooms you have <strong>already built</strong> only if they are <strong>connected</strong>.&nbsp;You can choose to build <strong>any room</strong> as long as its <strong>previous room</strong>&nbsp;is already built.</p>

<p>Return <em>the <strong>number of different orders</strong> you can build all the rooms in</em>. Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1916.Count%20Ways%20to%20Build%20Rooms%20in%20an%20Ant%20Colony/images/d1.jpg" style="width: 200px; height: 212px;" />

<pre>

<strong>Input:</strong> prevRoom = [-1,0,1]

<strong>Output:</strong> 1

<strong>Explanation:</strong>&nbsp;There is only one way to build the additional rooms: 0 &rarr; 1 &rarr; 2

</pre>

<p><strong class="example">Example 2:</strong></p>

<strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1916.Count%20Ways%20to%20Build%20Rooms%20in%20an%20Ant%20Colony/images/d2.jpg" style="width: 200px; height: 239px;" /></strong>

<pre>

<strong>Input:</strong> prevRoom = [-1,0,0,1,2]

<strong>Output:</strong> 6

<strong>Explanation:

</strong>The 6 ways are:

0 &rarr; 1 &rarr; 3 &rarr; 2 &rarr; 4

0 &rarr; 2 &rarr; 4 &rarr; 1 &rarr; 3

0 &rarr; 1 &rarr; 2 &rarr; 3 &rarr; 4

0 &rarr; 1 &rarr; 2 &rarr; 4 &rarr; 3

0 &rarr; 2 &rarr; 1 &rarr; 3 &rarr; 4

0 &rarr; 2 &rarr; 1 &rarr; 4 &rarr; 3

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>n == prevRoom.length</code></li>

    <li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>

    <li><code>prevRoom[0] == -1</code></li>

    <li><code>0 &lt;= prevRoom[i] &lt; n</code> for all <code>1 &lt;= i &lt; n</code></li>

    <li>Every room is reachable from room <code>0</code> once all the rooms are built.</li>

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
