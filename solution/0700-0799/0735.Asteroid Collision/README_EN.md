# [735. Asteroid Collision](https://leetcode.com/problems/asteroid-collision)

[中文文档](/solution/0700-0799/0735.Asteroid%20Collision/README.md)

## Description
<p>

We are given an array <code>asteroids</code> of integers representing asteroids in a row.

</p><p>

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left).  Each asteroid moves at the same speed.

</p><p>

Find out the state of the asteroids after all collisions.  If two asteroids meet, the smaller one will explode.  If both are the same size, both will explode.  Two asteroids moving in the same direction will never meet.

</p>



<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> 

asteroids = [5, 10, -5]

<b>Output:</b> [5, 10]

<b>Explanation:</b> 

The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.

</pre>

</p>



<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> 

asteroids = [8, -8]

<b>Output:</b> []

<b>Explanation:</b> 

The 8 and -8 collide exploding each other.

</pre>

</p>



<p><b>Example 3:</b><br />

<pre>

<b>Input:</b> 

asteroids = [10, 2, -5]

<b>Output:</b> [10]

<b>Explanation:</b> 

The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.

</pre>

</p>



<p><b>Example 4:</b><br />

<pre>

<b>Input:</b> 

asteroids = [-2, -1, 1, 2]

<b>Output:</b> [-2, -1, 1, 2]

<b>Explanation:</b> 

The -2 and -1 are moving left, while the 1 and 2 are moving right.

Asteroids moving the same direction never meet, so no asteroids will meet each other.

</pre>

</p>



<p><b>Note:</b>

<li>The length of <code>asteroids</code> will be at most <code>10000</code>.</li>

<li>Each asteroid will be a non-zero integer in the range <code>[-1000, 1000].</code>.</li>

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