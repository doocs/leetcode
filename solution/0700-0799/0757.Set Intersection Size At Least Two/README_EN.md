# [757. Set Intersection Size At Least Two](https://leetcode.com/problems/set-intersection-size-at-least-two)

[中文文档](/solution/0700-0799/0757.Set%20Intersection%20Size%20At%20Least%20Two/README.md)

## Description
<p>

An integer interval <code>[a, b]</code> (for integers <code>a < b</code>) is a set of all consecutive integers from <code>a</code> to <code>b</code>, including <code>a</code> and <code>b</code>.

</p><p>

Find the minimum size of a set S such that for every integer interval A in <code>intervals</code>, the intersection of S with A has size at least 2.

</p>



<p><b>Example 1:</b><br />

<pre>

<b>Input:</b> intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]

<b>Output:</b> 3

<b>Explanation:</b>

Consider the set S = {2, 3, 4}.  For each interval, there are at least 2 elements from S in the interval.

Also, there isn't a smaller size set that fulfills the above condition.

Thus, we output the size of this set, which is 3.

</pre>

</p>



<p><b>Example 2:</b><br />

<pre>

<b>Input:</b> intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]

<b>Output:</b> 5

<b>Explanation:</b>

An example of a minimum sized set is {1, 2, 3, 4, 5}.

</pre>

</p>



<p><b>Note:</b><br><ol>

<li><code>intervals</code> will have length in range <code>[1, 3000]</code>.</li>

<li><code>intervals[i]</code> will have length <code>2</code>, representing some integer interval.</li>

<li><code>intervals[i][j]</code> will be an integer in <code>[0, 10^8]</code>.</li>

</ol></p>


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