# [338. Counting Bits](https://leetcode.com/problems/counting-bits)

[中文文档](/solution/0300-0399/0338.Counting%20Bits/README.md)

## Description

<p>Given an integer <code>num</code>, return <em>an array of the number of</em> <code>1</code><em>&#39;s in the binary representation of every number in the range</em> <code>[0, num]</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 2
<strong>Output:</strong> [0,1,1]
<strong>Explanation:</strong>
0 --&gt; 0
1 --&gt; 1
2 --&gt; 10
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 5
<strong>Output:</strong> [0,1,1,2,1,2]
<strong>Explanation:</strong>
0 --&gt; 0
1 --&gt; 1
2 --&gt; 10
3 --&gt; 11
4 --&gt; 100
5 --&gt; 101
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>It is very easy to come up with a solution with run time <code>O(32n)</code>. Can you do it in linear time <code>O(n)</code> and possibly in a single pass?</li>
	<li>Could you solve it in <code>O(n)</code> space complexity?</li>
	<li>Can you do it without using any built-in function (i.e., like <code>__builtin_popcount</code> in C++)?</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **C++**

```cpp
class Solution {
public:
    vector<int> countBits(int n) {
        vector<int> res(n + 1);
        for (int i = 1; i <= n; i++) {
            res[i] = res[i & (i - 1)] + 1;
        }

        return res;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
