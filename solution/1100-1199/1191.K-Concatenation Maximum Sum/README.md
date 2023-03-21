# [1191. K 次串联后最大子数组之和](https://leetcode.cn/problems/k-concatenation-maximum-sum)

[English Version](/solution/1100-1199/1191.K-Concatenation%20Maximum%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组&nbsp;<code>arr</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;，通过重复&nbsp;<code>k</code>&nbsp;次来修改数组。</p>

<p>例如，如果&nbsp;<code>arr = [1, 2]</code>&nbsp;，<meta charset="UTF-8" />&nbsp;<code>k = 3</code>&nbsp;，那么修改后的数组将是 <code>[1, 2, 1, 2, 1, 2]</code> 。</p>

<p>返回修改后的数组中的最大的子数组之和。注意，子数组长度可以是 <code>0</code>，在这种情况下它的总和也是 <code>0</code>。</p>

<p>由于&nbsp;<strong>结果可能会很大</strong>，需要返回的<meta charset="UTF-8" />&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code>&nbsp;的&nbsp;<strong>模</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2], k = 3
<strong>输出：</strong>9
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,-2,1], k = 5
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [-1,-2], k = 7
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>
<meta charset="UTF-8" />

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 分类讨论**

我们记数组 $arr$ 所有元素之和为 $s$，最大前缀和为 $mxPre$，最小前缀和为 $miPre$，最大子数组和为 $mxSub$。

遍历数组 $arr$，对于每个元素 $x$，我们更新 $s = s + x$, $mxPre = max(mxPre, s)$, $miPre = min(miPre, s)$, $mxSub = max(mxSub, s - miPre)$。

接下来，我们考虑 $k$ 的取值情况：

-   当 $k = 1$ 时，答案为 $mxSub$。
-   当 $k \ge 2$ 时，如果最大子数组横跨两个 $arr$，那么答案为 $mxPre + mxSuf$，其中 $mxSuf = s - miPre$。
-   当 $k \ge 2$ 且 $s > 0$ 时，如果最大子数组横跨三个 $arr$，那么答案为 $(k - 2) \times s + mxPre + mxSuf$。

最后，我们返回答案对 $10^9 + 7$ 取模的结果。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $arr$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def kConcatenationMaxSum(self, arr: List[int], k: int) -> int:
        s = mx_pre = mi_pre = mx_sub = 0
        for x in arr:
            s += x
            mx_pre = max(mx_pre, s)
            mi_pre = min(mi_pre, s)
            mx_sub = max(mx_sub, s - mi_pre)
        ans = mx_sub
        mod = 10**9 + 7
        if k == 1:
            return ans % mod
        mx_suf = s - mi_pre
        ans = max(ans, mx_pre + mx_suf)
        if s > 0:
            ans = max(ans, (k - 2) * s + mx_pre + mx_suf)
        return ans % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        long s = 0, mxPre = 0, miPre = 0, mxSub = 0;
        for (int x : arr) {
            s += x;
            mxPre = Math.max(mxPre, s);
            miPre = Math.min(miPre, s);
            mxSub = Math.max(mxSub, s - miPre);
        }
        long ans = mxSub;
        final int mod = (int) 1e9 + 7;
        if (k == 1) {
            return (int) (ans % mod);
        }
        long mxSuf = s - miPre;
        ans = Math.max(ans, mxPre + mxSuf);
        if (s > 0) {
            ans = Math.max(ans, (k - 2) * s + mxPre + mxSuf);
        }
        return (int) (ans % mod);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int kConcatenationMaxSum(vector<int>& arr, int k) {
        long s = 0, mxPre = 0, miPre = 0, mxSub = 0;
        for (int x : arr) {
            s += x;
            mxPre = max(mxPre, s);
            miPre = min(miPre, s);
            mxSub = max(mxSub, s - miPre);
        }
        long ans = mxSub;
        const int mod = 1e9 + 7;
        if (k == 1) {
            return ans % mod;
        }
        long mxSuf = s - miPre;
        ans = max(ans, mxPre + mxSuf);
        if (s > 0) {
            ans = max(ans, mxPre + (k - 2) * s + mxSuf);
        }
        return ans % mod;
    }
};
```

### **Go**

```go
func kConcatenationMaxSum(arr []int, k int) int {
	var s, mxPre, miPre, mxSub int
	for _, x := range arr {
		s += x
		mxPre = max(mxPre, s)
		miPre = min(miPre, s)
		mxSub = max(mxSub, s-miPre)
	}
	const mod = 1e9 + 7
	ans := mxSub
	if k == 1 {
		return ans % mod
	}
	mxSuf := s - miPre
	ans = max(ans, mxSuf+mxPre)
	if s > 0 {
		ans = max(ans, mxSuf+(k-2)*s+mxPre)
	}
	return ans % mod
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
