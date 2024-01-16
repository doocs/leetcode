# [1955. 统计特殊子序列的数目](https://leetcode.cn/problems/count-number-of-special-subsequences)

[English Version](/solution/1900-1999/1955.Count%20Number%20of%20Special%20Subsequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>特殊序列</strong> 是由&nbsp;<strong>正整数</strong>&nbsp;个 <code>0</code>&nbsp;，紧接着&nbsp;<strong>正整数</strong>&nbsp;个 <code>1</code>&nbsp;，最后 <strong>正整数</strong>&nbsp;个 <code>2</code>&nbsp;组成的序列。</p>

<ul>
	<li>比方说，<code>[0,1,2]</code> 和&nbsp;<code>[0,0,1,1,1,2]</code>&nbsp;是特殊序列。</li>
	<li>相反，<code>[2,1,0]</code>&nbsp;，<code>[1]</code>&nbsp;和&nbsp;<code>[0,1,2,0]</code>&nbsp;就不是特殊序列。</li>
</ul>

<p>给你一个数组&nbsp;<code>nums</code>&nbsp;（<strong>仅</strong>&nbsp;包含整数&nbsp;<code>0</code>，<code>1</code>&nbsp;和&nbsp;<code>2</code>），请你返回 <b>不同特殊子序列的数目</b>&nbsp;。由于答案可能很大，请你将它对&nbsp;<code>10<sup>9</sup> + 7</code> <strong>取余</strong> 后返回。</p>

<p>一个数组的 <strong>子序列</strong>&nbsp;是从原数组中删除零个或者若干个元素后，剩下元素不改变顺序得到的序列。如果两个子序列的 <strong>下标集合</strong>&nbsp;不同，那么这两个子序列是 <strong>不同的</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [0,1,2,2]
<b>输出：</b>3
<b>解释：</b>特殊子序列为 [<strong>0</strong>,<strong>1</strong>,<strong>2</strong>,2]，[<strong>0</strong>,<strong>1</strong>,2,<strong>2</strong>] 和 [<strong>0</strong>,<strong>1</strong>,<strong>2</strong>,<strong>2</strong>] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,2,0,0]
<b>输出：</b>0
<b>解释：</b>数组 [2,2,0,0] 中没有特殊子序列。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [0,1,2,0,1,2]
<b>输出：</b>7
<b>解释：</b>特殊子序列包括：
- [<strong>0</strong>,<strong>1</strong>,<strong>2</strong>,0,1,2]
- [<strong>0</strong>,<strong>1</strong>,2,0,1,<strong>2</strong>]
- [<strong>0</strong>,<strong>1</strong>,<strong>2</strong>,0,1,<strong>2</strong>]
- [<strong>0</strong>,<strong>1</strong>,2,0,<strong>1</strong>,<strong>2</strong>]
- [<strong>0</strong>,1,2,<strong>0</strong>,<strong>1</strong>,<strong>2</strong>]
- [<strong>0</strong>,1,2,0,<strong>1</strong>,<strong>2</strong>]
- [0,1,2,<strong>0</strong>,<strong>1</strong>,<strong>2</strong>]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 2</code></li>
</ul>

## 解法

### 方法一：动态规划

我们定义 $f[i][j]$ 表示前 $i+1$ 个元素中，以 $j$ 结尾的特殊子序列的个数。初始时 $f[i][j]=0$，如果 $nums[0]=0$，则 $f[0][0]=1$。

对于 $i \gt 0$，我们考虑 $nums[i]$ 的值：

如果 $nums[i] = 0$：如果我们不选择 $nums[i]$，则 $f[i][0] = f[i-1][0]$；如果我们选择 $nums[i]$，那么 $f[i][0]=f[i-1][0]+1$，因为我们可以在任何一个以 $0$ 结尾的特殊子序列后面加上一个 $0$ 得到一个新的特殊子序列，也可以将 $nums[i]$ 单独作为一个特殊子序列。因此 $f[i][0] = 2 \times f[i - 1][0] + 1$。其余的 $f[i][j]$ 与 $f[i-1][j]$ 相等。

如果 $nums[i] = 1$：如果我们不选择 $nums[i]$，则 $f[i][1] = f[i-1][1]$；如果我们选择 $nums[i]$，那么 $f[i][1]=f[i-1][1]+f[i-1][0]$，因为我们可以在任何一个以 $0$ 或 $1$ 结尾的特殊子序列后面加上一个 $1$ 得到一个新的特殊子序列。因此 $f[i][1] = f[i-1][1] + 2 \times f[i - 1][0]$。其余的 $f[i][j]$ 与 $f[i-1][j]$ 相等。

如果 $nums[i] = 2$：如果我们不选择 $nums[i]$，则 $f[i][2] = f[i-1][2]$；如果我们选择 $nums[i]$，那么 $f[i][2]=f[i-1][2]+f[i-1][1]$，因为我们可以在任何一个以 $1$ 或 $2$ 结尾的特殊子序列后面加上一个 $2$ 得到一个新的特殊子序列。因此 $f[i][2] = f[i-1][2] + 2 \times f[i - 1][1]$。其余的 $f[i][j]$ 与 $f[i-1][j]$ 相等。

综上，我们可以得到如下的状态转移方程：

$$
\begin{aligned}
f[i][0] &= 2 \times f[i - 1][0] + 1, \quad nums[i] = 0 \\
f[i][1] &= f[i-1][1] + 2 \times f[i - 1][0], \quad nums[i] = 1 \\
f[i][2] &= f[i-1][2] + 2 \times f[i - 1][1], \quad nums[i] = 2 \\
f[i][j] &= f[i-1][j], \quad nums[i] \neq j
\end{aligned}
$$

最终的答案即为 $f[n-1][2]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

我们注意到，上述的状态转移方程中，$f[i][j]$ 的值仅与 $f[i-1][j]$ 有关，因此我们可以去掉第一维，将空间复杂度优化到 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def countSpecialSubsequences(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        n = len(nums)
        f = [[0] * 3 for _ in range(n)]
        f[0][0] = nums[0] == 0
        for i in range(1, n):
            if nums[i] == 0:
                f[i][0] = (2 * f[i - 1][0] + 1) % mod
                f[i][1] = f[i - 1][1]
                f[i][2] = f[i - 1][2]
            elif nums[i] == 1:
                f[i][0] = f[i - 1][0]
                f[i][1] = (f[i - 1][0] + 2 * f[i - 1][1]) % mod
                f[i][2] = f[i - 1][2]
            else:
                f[i][0] = f[i - 1][0]
                f[i][1] = f[i - 1][1]
                f[i][2] = (f[i - 1][1] + 2 * f[i - 1][2]) % mod
        return f[n - 1][2]
```

```java
class Solution {
    public int countSpecialSubsequences(int[] nums) {
        final int mod = (int) 1e9 + 7;
        int n = nums.length;
        int[][] f = new int[n][3];
        f[0][0] = nums[0] == 0 ? 1 : 0;
        for (int i = 1; i < n; ++i) {
            if (nums[i] == 0) {
                f[i][0] = (2 * f[i - 1][0] % mod + 1) % mod;
                f[i][1] = f[i - 1][1];
                f[i][2] = f[i - 1][2];
            } else if (nums[i] == 1) {
                f[i][0] = f[i - 1][0];
                f[i][1] = (f[i - 1][0] + 2 * f[i - 1][1] % mod) % mod;
                f[i][2] = f[i - 1][2];
            } else {
                f[i][0] = f[i - 1][0];
                f[i][1] = f[i - 1][1];
                f[i][2] = (f[i - 1][1] + 2 * f[i - 1][2] % mod) % mod;
            }
        }
        return f[n - 1][2];
    }
}
```

```cpp
class Solution {
public:
    int countSpecialSubsequences(vector<int>& nums) {
        const int mod = 1e9 + 7;
        int n = nums.size();
        int f[n][3];
        memset(f, 0, sizeof(f));
        f[0][0] = nums[0] == 0;
        for (int i = 1; i < n; ++i) {
            if (nums[i] == 0) {
                f[i][0] = (2 * f[i - 1][0] % mod + 1) % mod;
                f[i][1] = f[i - 1][1];
                f[i][2] = f[i - 1][2];
            } else if (nums[i] == 1) {
                f[i][0] = f[i - 1][0];
                f[i][1] = (f[i - 1][0] + 2 * f[i - 1][1] % mod) % mod;
                f[i][2] = f[i - 1][2];
            } else {
                f[i][0] = f[i - 1][0];
                f[i][1] = f[i - 1][1];
                f[i][2] = (f[i - 1][1] + 2 * f[i - 1][2] % mod) % mod;
            }
        }
        return f[n - 1][2];
    }
};
```

```go
func countSpecialSubsequences(nums []int) int {
	const mod = 1e9 + 7
	n := len(nums)
	f := make([][3]int, n)
	if nums[0] == 0 {
		f[0][0] = 1
	}
	for i := 1; i < n; i++ {
		if nums[i] == 0 {
			f[i][0] = (2*f[i-1][0] + 1) % mod
			f[i][1] = f[i-1][1]
			f[i][2] = f[i-1][2]
		} else if nums[i] == 1 {
			f[i][0] = f[i-1][0]
			f[i][1] = (f[i-1][0] + 2*f[i-1][1]) % mod
			f[i][2] = f[i-1][2]
		} else {
			f[i][0] = f[i-1][0]
			f[i][1] = f[i-1][1]
			f[i][2] = (f[i-1][1] + 2*f[i-1][2]) % mod
		}
	}
	return f[n-1][2]
}
```

```ts
function countSpecialSubsequences(nums: number[]): number {
    const mod = 1e9 + 7;
    const n = nums.length;
    const f: number[][] = Array(n)
        .fill(0)
        .map(() => Array(3).fill(0));
    f[0][0] = nums[0] === 0 ? 1 : 0;
    for (let i = 1; i < n; ++i) {
        if (nums[i] === 0) {
            f[i][0] = (((2 * f[i - 1][0]) % mod) + 1) % mod;
            f[i][1] = f[i - 1][1];
            f[i][2] = f[i - 1][2];
        } else if (nums[i] === 1) {
            f[i][0] = f[i - 1][0];
            f[i][1] = (f[i - 1][0] + ((2 * f[i - 1][1]) % mod)) % mod;
            f[i][2] = f[i - 1][2];
        } else {
            f[i][0] = f[i - 1][0];
            f[i][1] = f[i - 1][1];
            f[i][2] = (f[i - 1][1] + ((2 * f[i - 1][2]) % mod)) % mod;
        }
    }
    return f[n - 1][2];
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def countSpecialSubsequences(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        n = len(nums)
        f = [0] * 3
        f[0] = nums[0] == 0
        for i in range(1, n):
            if nums[i] == 0:
                f[0] = (2 * f[0] + 1) % mod
            elif nums[i] == 1:
                f[1] = (f[0] + 2 * f[1]) % mod
            else:
                f[2] = (f[1] + 2 * f[2]) % mod
        return f[2]
```

```java
class Solution {
    public int countSpecialSubsequences(int[] nums) {
        final int mod = (int) 1e9 + 7;
        int n = nums.length;
        int[] f = new int[3];
        f[0] = nums[0] == 0 ? 1 : 0;
        for (int i = 1; i < n; ++i) {
            if (nums[i] == 0) {
                f[0] = (2 * f[0] % mod + 1) % mod;
            } else if (nums[i] == 1) {
                f[1] = (f[0] + 2 * f[1] % mod) % mod;
            } else {
                f[2] = (f[1] + 2 * f[2] % mod) % mod;
            }
        }
        return f[2];
    }
}
```

```cpp
class Solution {
public:
    int countSpecialSubsequences(vector<int>& nums) {
        const int mod = 1e9 + 7;
        int n = nums.size();
        int f[3]{0};
        f[0] = nums[0] == 0;
        for (int i = 1; i < n; ++i) {
            if (nums[i] == 0) {
                f[0] = (2 * f[0] % mod + 1) % mod;
            } else if (nums[i] == 1) {
                f[1] = (f[0] + 2 * f[1] % mod) % mod;
            } else {
                f[2] = (f[1] + 2 * f[2] % mod) % mod;
            }
        }
        return f[2];
    }
};
```

```go
func countSpecialSubsequences(nums []int) int {
	const mod = 1e9 + 7
	n := len(nums)
	f := [3]int{}
	if nums[0] == 0 {
		f[0] = 1
	}
	for i := 1; i < n; i++ {
		if nums[i] == 0 {
			f[0] = (2*f[0] + 1) % mod
		} else if nums[i] == 1 {
			f[1] = (f[0] + 2*f[1]) % mod
		} else {
			f[2] = (f[1] + 2*f[2]) % mod
		}
	}
	return f[2]
}
```

```ts
function countSpecialSubsequences(nums: number[]): number {
    const mod = 1e9 + 7;
    const n = nums.length;
    const f: number[] = [0, 0, 0];
    f[0] = nums[0] === 0 ? 1 : 0;
    for (let i = 1; i < n; ++i) {
        if (nums[i] === 0) {
            f[0] = (((2 * f[0]) % mod) + 1) % mod;
            f[1] = f[1];
            f[2] = f[2];
        } else if (nums[i] === 1) {
            f[0] = f[0];
            f[1] = (f[0] + ((2 * f[1]) % mod)) % mod;
            f[2] = f[2];
        } else {
            f[0] = f[0];
            f[1] = f[1];
            f[2] = (f[1] + ((2 * f[2]) % mod)) % mod;
        }
    }
    return f[2];
}
```

<!-- tabs:end -->

<!-- end -->
