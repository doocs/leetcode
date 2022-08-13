# [1551. 使数组中所有元素相等的最小操作数](https://leetcode.cn/problems/minimum-operations-to-make-array-equal)

[English Version](/solution/1500-1599/1551.Minimum%20Operations%20to%20Make%20Array%20Equal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>存在一个长度为 <code>n</code> 的数组 <code>arr</code> ，其中 <code>arr[i] = (2 * i) + 1</code> （ <code>0 &lt;= i &lt; n</code> ）。</p>

<p>一次操作中，你可以选出两个下标，记作 <code>x</code> 和 <code>y</code> （ <code>0 &lt;= x, y &lt; n</code> ）并使 <code>arr[x]</code> 减去 <code>1</code> 、<code>arr[y]</code> 加上 <code>1</code> （即 <code>arr[x] -=1 </code>且 <code>arr[y] += 1</code> ）。最终的目标是使数组中的所有元素都 <strong>相等</strong> 。题目测试用例将会 <strong>保证</strong> ：在执行若干步操作后，数组中的所有元素最终可以全部相等。</p>

<p>给你一个整数 <code>n</code>，即数组的长度。请你返回使数组 <code>arr</code> 中所有元素相等所需的 <strong>最小操作数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 3
<strong>输出：</strong>2
<strong>解释：</strong>arr = [1, 3, 5]
第一次操作选出 x = 2 和 y = 0，使数组变为 [2, 3, 4]
第二次操作继续选出 x = 2 和 y = 0，数组将会变成 [3, 3, 3]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 6
<strong>输出：</strong>9
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^4</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

数组 arr 的前 n 项和为 `(1 + (2 * n - 1)) * n / 2 = n * n`，若数组所有元素相等，那么每一项元素应该都是 n，因此只需累计数组前半部分的元素操作次数 `n - (2 * i + 1)` 即可，即 n ∈ `[0, n / 2)`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minOperations(self, n: int) -> int:
        ans = 0
        for i in range(n >> 1):
            ans += n - (2 * i + 1)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minOperations(int n) {
        int ans = 0;
        for (int i = 0; i < (n >> 1); i++) {
            ans += (n - (2 * i + 1));
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minOperations(int n) {
        int ans = 0;
        for (int i = 0; i < (n >> 1); ++i) ans += (n - (2 * i + 1));
        return ans;
    }
};
```

### **Go**

```go
func minOperations(n int) int {
	ans := 0
	for i := 0; i < (n >> 1); i++ {
		ans += (n - (2*i + 1))
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
