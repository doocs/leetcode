# [896. 单调数列](https://leetcode-cn.com/problems/monotonic-array)

[English Version](/solution/0800-0899/0896.Monotonic%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果数组是单调递增或单调递减的，那么它是<em>单调的</em>。</p>

<p>如果对于所有 <code>i &lt;= j</code>，<code>A[i] &lt;= A[j]</code>，那么数组 <code>A</code> 是单调递增的。 如果对于所有 <code>i &lt;= j</code>，<code>A[i]&gt; = A[j]</code>，那么数组 <code>A</code> 是单调递减的。</p>

<p>当给定的数组 <code>A</code>&nbsp;是单调数组时返回 <code>true</code>，否则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[1,2,2,3]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[6,5,4,4]
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>[1,3,2]
<strong>输出：</strong>false
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>[1,2,4,5]
<strong>输出：</strong>true
</pre>

<p><strong>示例&nbsp;5：</strong></p>

<pre><strong>输入：</strong>[1,1,1]
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 50000</code></li>
	<li><code>-100000 &lt;= A[i] &lt;= 100000</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

遍历数组：

- 出现递减，将 `increase` 置为 `false`；
- 出现递增，将 `decrease` 置为 `false`；
- 既非递增也非递减，提前返回 `false`；
- 遍历结束，若出现递增或递减，返回 `true`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
