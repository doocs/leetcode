# [1837. Sum of Digits in Base K](https://leetcode.com/problems/sum-of-digits-in-base-k)

[中文文档](/solution/1800-1899/1837.Sum%20of%20Digits%20in%20Base%20K/README.md)

## Description

<p>Given an integer <code>n</code> (in base <code>10</code>) and a base <code>k</code>, return <em>the <strong>sum</strong> of the digits of </em><code>n</code><em> <strong>after</strong> converting </em><code>n</code><em> from base </em><code>10</code><em> to base </em><code>k</code>.</p>

<p>After converting, each digit should be interpreted as a base <code>10</code> number, and the sum should be returned in base <code>10</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 34, k = 6
<strong>Output:</strong> 9
<strong>Explanation: </strong>34 (base 10) expressed in base 6 is 54. 5 + 4 = 9.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 10, k = 10
<strong>Output:</strong> 1
<strong>Explanation: </strong>n is already in base 10. 1 + 0 = 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>2 &lt;= k &lt;= 10</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def sumBase(self, n: int, k: int) -> int:
        res = 0
        while n != 0:
            n, t = divmod(n, k)
            res += t
        return res
```

### **Java**

```java
class Solution {
    public int sumBase(int n, int k) {
        int res = 0;
        while (n != 0) {
            res += (n % k);
            n /= k;
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
