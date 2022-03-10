# [896. Monotonic Array](https://leetcode.com/problems/monotonic-array)

[中文文档](/solution/0800-0899/0896.Monotonic%20Array/README.md)

## Description

<p>An array is <strong>monotonic</strong> if it is either monotone increasing or monotone decreasing.</p>

<p>An array <code>nums</code> is monotone increasing if for all <code>i &lt;= j</code>, <code>nums[i] &lt;= nums[j]</code>. An array <code>nums</code> is monotone decreasing if for all <code>i &lt;= j</code>, <code>nums[i] &gt;= nums[j]</code>.</p>

<p>Given an integer array <code>nums</code>, return <code>true</code><em> if the given array is monotonic, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2,3]
<strong>Output:</strong> true
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,5,4,4]
<strong>Output:</strong> true
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,2]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isMonotonic(self, A: List[int]) -> bool:
        increase = decrease = True
        for i in range(1, len(A)):
            if not increase and not decrease:
                return False
            if A[i] < A[i - 1]:
                increase = False
            elif A[i] > A[i - 1]:
                decrease = False
        return increase or decrease
```

### **Java**

```java
class Solution {
    public boolean isMonotonic(int[] A) {
        boolean increase = true, decrease = true;
        for (int i = 1, n = A.length; i < n; ++i) {
            if (!increase && !decrease) return false;
            if (A[i] < A[i - 1]) decrease = false;
            else if (A[i] > A[i - 1]) increase = false;
        }
        return increase || decrease;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
