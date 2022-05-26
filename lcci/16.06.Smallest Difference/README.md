# [面试题 16.06. 最小差](https://leetcode-cn.com/problems/smallest-difference-lcci)

[English Version](/lcci/16.06.Smallest%20Difference/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定两个整数数组<code>a</code>和<code>b</code>，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong>{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
<strong>输出：</strong> 3，即数值对(11, 8)
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>1 <= a.length, b.length <= 100000</code></li>
<li><code>-2147483648 <= a[i], b[i] <= 2147483647</code></li>
<li>正确结果在区间[-2147483648, 2147483647]内</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def smallestDifference(self, a: List[int], b: List[int]) -> int:
        a.sort()
        b.sort()
        i, j, res = 0, 0, 2147483647
        m, n = len(a), len(b)
        while i < m and j < n:
            if a[i] == b[j]: return 0
            res = min(res, abs(a[i] - b[j]))
            if a[i] > b[j]: j += 1
            else: i += 1
        return res

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int m = a.length, n = b.length;
        int i = 0, j = 0;
        long res = Long.MAX_VALUE;
        while (i < m && j < n) {
            if (a[i] == b[j]) return 0;
            res = Math.min(res, Math.abs((long) a[i] - (long) b[j]));
            if (a[i] > b[j]) ++j;
            else ++i;
        }
        return (int) res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
