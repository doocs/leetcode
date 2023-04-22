# [1027. 最长等差数列](https://leetcode.cn/problems/longest-arithmetic-subsequence)

[English Version](/solution/1000-1099/1027.Longest%20Arithmetic%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>，返回 <code>nums</code>&nbsp;中最长等差子序列的<strong>长度</strong>。</p>

<p>回想一下，<code>nums</code> 的子序列是一个列表&nbsp;<code>nums[i<sub>1</sub>], nums[i<sub>2</sub>], ..., nums[i<sub>k</sub>]</code> ，且&nbsp;<code>0 &lt;= i<sub>1</sub> &lt; i<sub>2</sub> &lt; ... &lt; i<sub>k</sub> &lt;= nums.length - 1</code>。并且如果&nbsp;<code>seq[i+1] - seq[i]</code>(&nbsp;<code>0 &lt;= i &lt; seq.length - 1</code>) 的值都相同，那么序列&nbsp;<code>seq</code>&nbsp;是等差的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,6,9,12]
<strong>输出：</strong>4
<strong>解释： </strong>
整个数组是公差为 3 的等差数列。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [9,4,7,2,10]
<strong>输出：</strong>3
<strong>解释：</strong>
最长的等差子序列是 [4,7,10]。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [20,1,15,3,10,5,8]
<strong>输出：</strong>4
<strong>解释：</strong>
最长的等差子序列是 [20,15,10,5]。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 500</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i][j]$ 表示以 $nums[i]$ 结尾且公差为 $j$ 的等差数列的最大长度。初始时 $f[i][j]=1$，即每个元素自身都是一个长度为 $1$ 的等差数列。

> 由于公差可能为负数，且最大差值为 $500$，因此，我们可以将统一将公差加上 $500$，这样公差的范围就变成了 $[0, 1000]$。

考虑 $f[i]$，我们可以枚举 $nums[i]$ 的前一个元素 $nums[k]$，那么公差 $j=nums[i]-nums[k]+500$，此时有 $f[i][j]=\max(f[i][j], f[k][j]+1)$，然后我们更新答案 $ans=\max(ans, f[i][j])$。

最后返回答案即可。

> 如果初始时 $f[i][j]=0$，那么我们需要在最后返回答案时加上 $1$。

时间复杂度 $O(n \times (d + n))$，空间复杂度 $O(n \times d)$。其中 $n$ 和 $d$ 分别是数组 $nums$ 的长度以及数组 $nums$ 中元素的最大值与最小值的差值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestArithSeqLength(self, nums: List[int]) -> int:
        n = len(nums)
        f = [[1] * 1001 for _ in range(n)]
        ans = 0
        for i in range(1, n):
            for k in range(i):
                j = nums[i] - nums[k] + 500
                f[i][j] = max(f[i][j], f[k][j] + 1)
                ans = max(ans, f[i][j])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int[][] f = new int[n][1001];
        for (int i = 1; i < n; ++i) {
            for (int k = 0; k < i; ++k) {
                int j = nums[i] - nums[k] + 500;
                f[i][j] = Math.max(f[i][j], f[k][j] + 1);
                ans = Math.max(ans, f[i][j]);
            }
        }
        return ans + 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestArithSeqLength(vector<int>& nums) {
        int n = nums.size();
        int f[n][1001];
        memset(f, 0, sizeof(f));
        int ans = 0;
        for (int i = 1; i < n; ++i) {
            for (int k = 0; k < i; ++k) {
                int j = nums[i] - nums[k] + 500;
                f[i][j] = max(f[i][j], f[k][j] + 1);
                ans = max(ans, f[i][j]);
            }
        }
        return ans + 1;
    }
};
```

### **Go**

```go
func longestArithSeqLength(nums []int) int {
	n := len(nums)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, 1001)
	}
	ans := 0
	for i := 1; i < n; i++ {
		for k := 0; k < i; k++ {
			j := nums[i] - nums[k] + 500
			f[i][j] = max(f[i][j], f[k][j]+1)
			ans = max(ans, f[i][j])
		}
	}
	return ans + 1
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function longestArithSeqLength(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    const f: number[][] = Array.from({ length: n }, () =>
        new Array(1001).fill(0),
    );
    for (let i = 1; i < n; ++i) {
        for (let k = 0; k < i; ++k) {
            const j = nums[i] - nums[k] + 500;
            f[i][j] = Math.max(f[i][j], f[k][j] + 1);
            ans = Math.max(ans, f[i][j]);
        }
    }
    return ans + 1;
}
```

### **...**

```

```

<!-- tabs:end -->
