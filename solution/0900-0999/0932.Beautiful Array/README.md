# [932. 漂亮数组](https://leetcode.cn/problems/beautiful-array)

[English Version](/solution/0900-0999/0932.Beautiful%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>对于某些固定的&nbsp;<code>N</code>，如果数组&nbsp;<code>A</code>&nbsp;是整数&nbsp;<code>1, 2, ..., N</code>&nbsp;组成的排列，使得：</p>

<p>对于每个&nbsp;<code>i &lt; j</code>，都<strong>不存在</strong>&nbsp;<code>k</code> 满足&nbsp;<code>i &lt; k &lt; j</code>&nbsp;使得&nbsp;<code>A[k] * 2 = A[i] + A[j]</code>。</p>

<p>那么数组 <code>A</code>&nbsp;是漂亮数组。</p>

<p>&nbsp;</p>

<p>给定&nbsp;<code>N</code>，返回<strong>任意</strong>漂亮数组&nbsp;<code>A</code>（保证存在一个）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>4
<strong>输出：</strong>[2,1,4,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>5
<strong>输出：</strong>[3,1,2,5,4]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= N &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：分治**

根据题意，漂亮数组 $A$ 需要满足对于任意 $i<k<j$, $A_k*2 \neq A_i+A_j$。

我们可以发现，不等式左侧一定是偶数，那么我们只要保证不等式右侧 $A_i$ 和 $A_j$ 分别是一奇一偶，那么不等式就恒成立。

利用分治，我们将 $n$ 缩小规模为原来的一半，递归调用，可以得到两个漂亮数组 $left$, $right$。我们将 $left$ 中每个元素 $x_i$ 变为 $x_i*2-1$ 可以得到一个奇数数组；将 $right$ 中每个元素 $x_i$ 变为 $x_i*2$，可以得到一个偶数数组。这两个数组仍然是漂亮数组。

> 基于一个性质，将漂亮数组中的每个元素 $x_i$ 变换为 $kx_i+b$，得到的数组仍然是漂亮数组。

将这两个漂亮数组合并在一起，由于满足一奇一偶，那么合并后的数组也是漂亮数组，从而得到了答案。

时间复杂度 $O(nlogn)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def beautifulArray(self, n: int) -> List[int]:
        if n == 1:
            return [1]
        left = self.beautifulArray((n + 1) >> 1)
        right = self.beautifulArray(n >> 1)
        left = [x * 2 - 1 for x in left]
        right = [x * 2 for x in right]
        return left + right
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] beautifulArray(int n) {
        if (n == 1) {
            return new int[]{1};
        }
        int[] left = beautifulArray((n + 1) >> 1);
        int[] right = beautifulArray(n >> 1);
        int[] ans = new int[n];
        int i = 0;
        for (int x : left) {
            ans[i++] = x * 2 - 1;
        }
        for (int x : right) {
            ans[i++] = x * 2;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> beautifulArray(int n) {
        if (n == 1) return {1};
        vector<int> left = beautifulArray((n + 1) >> 1);
        vector<int> right = beautifulArray(n >> 1);
        vector<int> ans(n);
        int i = 0;
        for (int& x : left) ans[i++] = x * 2 - 1;
        for (int& x : right) ans[i++] = x * 2;
        return ans;
    }
};
```

### **Go**

```go
func beautifulArray(n int) []int {
	if n == 1 {
		return []int{1}
	}
	left := beautifulArray((n + 1) >> 1)
	right := beautifulArray(n >> 1)
	var ans []int
	for _, x := range left {
		ans = append(ans, x*2-1)
	}
	for _, x := range right {
		ans = append(ans, x*2)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
