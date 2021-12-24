# [896. Monotonic Array](https://leetcode.com/problems/monotonic-array)

[中文文档](/solution/0800-0899/0896.Monotonic%20Array/README.md)

## Description

<p>An array is <em>monotonic</em> if it is either monotone increasing or monotone decreasing.</p>

<p>An array <code>A</code> is monotone increasing if for all <code>i &lt;= j</code>, <code>A[i] &lt;= A[j]</code>.&nbsp; An array <code>A</code> is monotone decreasing if for all <code>i &lt;= j</code>, <code>A[i] &gt;= A[j]</code>.</p>

<p>Return <code>true</code> if and only if the given array <code>A</code> is monotonic.</p>

<p>&nbsp;</p>

<ol>

</ol>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[1,2,2,3]</span>

<strong>Output: </strong><span id="example-output-1">true</span>

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-2-1">[6,5,4,4]</span>

<strong>Output: </strong><span id="example-output-2">true</span>

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-3-1">[1,3,2]</span>

<strong>Output: </strong><span id="example-output-3">false</span>

</pre>

<div>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-4-1">[1,2,4,5]</span>

<strong>Output: </strong><span id="example-output-4">true</span>

</pre>

<div>

<p><strong>Example 5:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-5-1">[1,1,1]</span>

<strong>Output: </strong><span id="example-output-5">true</span>

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 50000</code></li>
	<li><code>-100000 &lt;= A[i] &lt;= 100000</code></li>
</ol>

</div>

</div>

</div>

</div>

</div>

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
