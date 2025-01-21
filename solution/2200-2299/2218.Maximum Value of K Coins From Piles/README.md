---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2218.Maximum%20Value%20of%20K%20Coins%20From%20Piles/README.md
rating: 2157
source: 第 286 场周赛 Q4
tags:
    - 数组
    - 动态规划
    - 前缀和
---

<!-- problem:start -->

# [2218. 从栈中取出 K 个硬币的最大面值和](https://leetcode.cn/problems/maximum-value-of-k-coins-from-piles)

[English Version](/solution/2200-2299/2218.Maximum%20Value%20of%20K%20Coins%20From%20Piles/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一张桌子上总共有 <code>n</code>&nbsp;个硬币 <b>栈</b>&nbsp;。每个栈有 <strong>正整数</strong>&nbsp;个带面值的硬币。</p>

<p>每一次操作中，你可以从任意一个栈的 <strong>顶部</strong>&nbsp;取出 1 个硬币，从栈中移除它，并放入你的钱包里。</p>

<p>给你一个列表&nbsp;<code>piles</code>&nbsp;，其中&nbsp;<code>piles[i]</code>&nbsp;是一个整数数组，分别表示第 <code>i</code>&nbsp;个栈里 <strong>从顶到底</strong>&nbsp;的硬币面值。同时给你一个正整数&nbsp;<code>k</code>&nbsp;，请你返回在&nbsp;<strong>恰好</strong>&nbsp;进行&nbsp;<code>k</code>&nbsp;次操作的前提下，你钱包里硬币面值之和&nbsp;<strong>最大为多少</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2218.Maximum%20Value%20of%20K%20Coins%20From%20Piles/images/e1.png" style="width: 600px; height: 243px;" /></p>

<pre>
<b>输入：</b>piles = [[1,100,3],[7,8,9]], k = 2
<b>输出：</b>101
<strong>解释：</strong>
上图展示了几种选择 k 个硬币的不同方法。
我们可以得到的最大面值为 101 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
<b>输出：</b>706
<strong>解释：
</strong>如果我们所有硬币都从最后一个栈中取，可以得到最大面值和。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == piles.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= piles[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= sum(piles[i].length) &lt;= 2000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划（分组背包）

我们定义 $f[i][j]$ 表示从前 $i$ 组中取出 $j$ 个硬币的最大面值和，那么答案为 $f[n][k]$，其中 $n$ 为栈的数量。

对于第 $i$ 组，我们可以选择取前 $0$, $1$, $2$, $\cdots$, $k$ 个硬币。我们可以通过前缀和数组 $s$ 来快速计算出取前 $h$ 个硬币的面值和。

状态转移方程为：

$$
f[i][j] = \max(f[i][j], f[i - 1][j - h] + s[h])
$$

其中 $0 \leq h \leq j$，而 $s[h]$ 表示第 $i$ 组中取前 $h$ 个硬币的面值和。

时间复杂度 $O(k \times L)$，空间复杂度 $O(n \times k)$。其中 $L$ 为所有硬币的数量，而 $n$ 为栈的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxValueOfCoins(self, piles: List[List[int]], k: int) -> int:
        n = len(piles)
        f = [[0] * (k + 1) for _ in range(n + 1)]
        for i, nums in enumerate(piles, 1):
            s = list(accumulate(nums, initial=0))
            for j in range(k + 1):
                for h, w in enumerate(s):
                    if j < h:
                        break
                    f[i][j] = max(f[i][j], f[i - 1][j - h] + w)
        return f[n][k]
```

#### Java

```java
class Solution {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int n = piles.size();
        int[][] f = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            List<Integer> nums = piles.get(i - 1);
            int[] s = new int[nums.size() + 1];
            s[0] = 0;
            for (int j = 1; j <= nums.size(); j++) {
                s[j] = s[j - 1] + nums.get(j - 1);
            }
            for (int j = 0; j <= k; j++) {
                for (int h = 0; h < s.length && h <= j; h++) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - h] + s[h]);
                }
            }
        }
        return f[n][k];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxValueOfCoins(vector<vector<int>>& piles, int k) {
        int n = piles.size();
        vector<vector<int>> f(n + 1, vector<int>(k + 1));
        for (int i = 1; i <= n; i++) {
            vector<int> nums = piles[i - 1];
            vector<int> s(nums.size() + 1);
            for (int j = 1; j <= nums.size(); j++) {
                s[j] = s[j - 1] + nums[j - 1];
            }
            for (int j = 0; j <= k; j++) {
                for (int h = 0; h < s.size() && h <= j; h++) {
                    f[i][j] = max(f[i][j], f[i - 1][j - h] + s[h]);
                }
            }
        }
        return f[n][k];
    }
};
```

#### Go

```go
func maxValueOfCoins(piles [][]int, k int) int {
	n := len(piles)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	for i := 1; i <= n; i++ {
		nums := piles[i-1]
		s := make([]int, len(nums)+1)
		for j := 1; j <= len(nums); j++ {
			s[j] = s[j-1] + nums[j-1]
		}

		for j := 0; j <= k; j++ {
			for h, w := range s {
				if j < h {
					break
				}
				f[i][j] = max(f[i][j], f[i-1][j-h]+w)
			}
		}
	}
	return f[n][k]
}
```

#### TypeScript

```ts
function maxValueOfCoins(piles: number[][], k: number): number {
    const n = piles.length;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    for (let i = 1; i <= n; i++) {
        const nums = piles[i - 1];
        const s = Array(nums.length + 1).fill(0);
        for (let j = 1; j <= nums.length; j++) {
            s[j] = s[j - 1] + nums[j - 1];
        }
        for (let j = 0; j <= k; j++) {
            for (let h = 0; h < s.length && h <= j; h++) {
                f[i][j] = Math.max(f[i][j], f[i - 1][j - h] + s[h]);
            }
        }
    }
    return f[n][k];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划（空间优化）

我们可以发现，对于第 $i$ 组，我们只需要用到 $f[i - 1][j]$ 和 $f[i][j - h]$，因此我们可以将二维数组优化为一维数组。

时间复杂度 $O(k \times L)$，空间复杂度 $O(k)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxValueOfCoins(self, piles: List[List[int]], k: int) -> int:
        f = [0] * (k + 1)
        for nums in piles:
            s = list(accumulate(nums, initial=0))
            for j in range(k, -1, -1):
                for h, w in enumerate(s):
                    if j < h:
                        break
                    f[j] = max(f[j], f[j - h] + w)
        return f[k]
```

#### Java

```java
class Solution {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int[] f = new int[k + 1];
        for (var nums : piles) {
            int[] s = new int[nums.size() + 1];
            for (int j = 1; j <= nums.size(); ++j) {
                s[j] = s[j - 1] + nums.get(j - 1);
            }
            for (int j = k; j >= 0; --j) {
                for (int h = 0; h < s.length && h <= j; ++h) {
                    f[j] = Math.max(f[j], f[j - h] + s[h]);
                }
            }
        }
        return f[k];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxValueOfCoins(vector<vector<int>>& piles, int k) {
        vector<int> f(k + 1);
        for (auto& nums : piles) {
            vector<int> s(nums.size() + 1);
            for (int j = 1; j <= nums.size(); ++j) {
                s[j] = s[j - 1] + nums[j - 1];
            }
            for (int j = k; j >= 0; --j) {
                for (int h = 0; h < s.size() && h <= j; ++h) {
                    f[j] = max(f[j], f[j - h] + s[h]);
                }
            }
        }
        return f[k];
    }
};
```

#### Go

```go
func maxValueOfCoins(piles [][]int, k int) int {
	f := make([]int, k+1)
	for _, nums := range piles {
		s := make([]int, len(nums)+1)
		for j := 1; j <= len(nums); j++ {
			s[j] = s[j-1] + nums[j-1]
		}
		for j := k; j >= 0; j-- {
			for h := 0; h < len(s) && h <= j; h++ {
				f[j] = max(f[j], f[j-h]+s[h])
			}
		}
	}
	return f[k]
}
```

#### TypeScript

```ts
function maxValueOfCoins(piles: number[][], k: number): number {
    const f: number[] = Array(k + 1).fill(0);
    for (const nums of piles) {
        const s: number[] = Array(nums.length + 1).fill(0);
        for (let j = 1; j <= nums.length; j++) {
            s[j] = s[j - 1] + nums[j - 1];
        }
        for (let j = k; j >= 0; j--) {
            for (let h = 0; h < s.length && h <= j; h++) {
                f[j] = Math.max(f[j], f[j - h] + s[h]);
            }
        }
    }
    return f[k];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
