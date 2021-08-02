# [1304. Find N Unique Integers Sum up to Zero](https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero)

[中文文档](/solution/1300-1399/1304.Find%20N%20Unique%20Integers%20Sum%20up%20to%20Zero/README.md)

## Description

<p>Given an integer <code>n</code>, return <strong>any</strong> array containing <code>n</code> <strong>unique</strong> integers such that they add up to <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> [-7,-1,1,3,4]
<strong>Explanation:</strong> These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> [-1,0,1]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> [0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java
class Solution {
  public int[] sumZero(int n) {
    if (n == 2)
      return new int[] { 1, -1 };
    int preSum = 0;
    int[] ret = new int[n];
    for (int i = 0; i < n - 1; i++) {
      preSum += i;
      ret[i] = i;
    }
    ret[n - 1] = -preSum;
    return ret;
  }
}
```

### **...**

```

```

<!-- tabs:end -->
