---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3177.Find%20the%20Maximum%20Length%20of%20a%20Good%20Subsequence%20II/README.md
rating: 2364
source: 第 132 场双周赛 Q4
tags:
    - 数组
    - 哈希表
    - 动态规划
---

<!-- problem:start -->

# [3177. 求出最长好子序列 II](https://leetcode.cn/problems/find-the-maximum-length-of-a-good-subsequence-ii)

[English Version](/solution/3100-3199/3177.Find%20the%20Maximum%20Length%20of%20a%20Good%20Subsequence%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个 <strong>非负</strong>&nbsp;整数&nbsp;<code>k</code>&nbsp;。如果一个整数序列&nbsp;<code>seq</code>&nbsp;满足在范围下标范围&nbsp;<code>[0, seq.length - 2]</code>&nbsp;中存在 <strong>不超过</strong>&nbsp;<code>k</code>&nbsp;个下标 <code>i</code>&nbsp;满足&nbsp;<code>seq[i] != seq[i + 1]</code>&nbsp;，那么我们称这个整数序列为&nbsp;<strong>好</strong>&nbsp;序列。</p>

<p>请你返回 <code>nums</code>&nbsp;中&nbsp;<strong>好</strong> <span data-keyword="subsequence-array">子序列</span>&nbsp;的最长长度</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,1,1,3], k = 2</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>最长好子序列为&nbsp;<code>[<em><strong>1</strong></em>,<em><strong>2</strong></em>,<strong><em>1</em></strong>,<em><strong>1</strong></em>,3]</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3,4,5,1], k = 0</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<p>最长好子序列为&nbsp;<code>[<strong><em>1</em></strong>,2,3,4,5,<strong><em>1</em></strong>]</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>3</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= min(50, nums.length)</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][h]$ 表示以 $nums[i]$ 结尾，且有不超过 $h$ 个下标满足条件的最长好子序列的长度。初始时 $f[i][h] = 1$。答案为 $\max(f[i][k])$，其中 $0 \le i < n$。

我们考虑如何计算 $f[i][h]$。我们可以枚举 $0 \le j < i$，如果 $nums[i] = nums[j]$，那么 $f[i][h] = \max(f[i][h], f[j][h] + 1)$；否则如果 $h > 0$，那么 $f[i][h] = \max(f[i][h], f[j][h - 1] + 1)$。即：

$$
f[i][h]=
\begin{cases}
\max(f[i][h], f[j][h] + 1), & \text{if } nums[i] = nums[j], \\
\max(f[i][h], f[j][h - 1] + 1), & \text{if } h > 0.
\end{cases}
$$

最终答案为 $\max(f[i][k])$，其中 $0 \le i < n$。

时间复杂度 $O(n^2 \times k)$，空间复杂度 $O(n \times k)$。其中 $n$ 是数组 `nums` 的长度。

由于本题数据范围较大，上述解法会超时，需要进行优化。

根据状态转移方程，如果 $nums[i] = nums[j]$，那么我们只需要获取 $f[j][h]$ 的最大值，我们可以用一个长度为 $k + 1$ 的数组 $mp$ 来维护。如果 $nums[i] \neq nums[j]$，我们需要记录 $f[j][h - 1]$ 的最大值对应的 $nums[j]$，最大值和次大值，我们可以用一个长度为 $k + 1$ 的数组 $g$ 来维护。

时间复杂度 $O(n \times k)$，空间复杂度 $O(n \times k)$。其中 $n$ 是数组 `nums` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumLength(self, nums: List[int], k: int) -> int:
        n = len(nums)
        f = [[0] * (k + 1) for _ in range(n)]
        mp = [defaultdict(int) for _ in range(k + 1)]
        g = [[0] * 3 for _ in range(k + 1)]
        ans = 0
        for i, x in enumerate(nums):
            for h in range(k + 1):
                f[i][h] = mp[h][x]
                if h:
                    if g[h - 1][0] != nums[i]:
                        f[i][h] = max(f[i][h], g[h - 1][1])
                    else:
                        f[i][h] = max(f[i][h], g[h - 1][2])
                f[i][h] += 1
                mp[h][nums[i]] = max(mp[h][nums[i]], f[i][h])
                if g[h][0] != x:
                    if f[i][h] >= g[h][1]:
                        g[h][2] = g[h][1]
                        g[h][1] = f[i][h]
                        g[h][0] = x
                    else:
                        g[h][2] = max(g[h][2], f[i][h])
                else:
                    g[h][1] = max(g[h][1], f[i][h])
                ans = max(ans, f[i][h])
        return ans
```

#### Java

```java
class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] f = new int[n][k + 1];
        Map<Integer, Integer>[] mp = new HashMap[k + 1];
        Arrays.setAll(mp, i -> new HashMap<>());
        int[][] g = new int[k + 1][3];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int h = 0; h <= k; ++h) {
                f[i][h] = mp[h].getOrDefault(nums[i], 0);
                if (h > 0) {
                    if (g[h - 1][0] != nums[i]) {
                        f[i][h] = Math.max(f[i][h], g[h - 1][1]);
                    } else {
                        f[i][h] = Math.max(f[i][h], g[h - 1][2]);
                    }
                }
                ++f[i][h];
                mp[h].merge(nums[i], f[i][h], Integer::max);
                if (g[h][0] != nums[i]) {
                    if (f[i][h] >= g[h][1]) {
                        g[h][2] = g[h][1];
                        g[h][1] = f[i][h];
                        g[h][0] = nums[i];
                    } else {
                        g[h][2] = Math.max(g[h][2], f[i][h]);
                    }
                } else {
                    g[h][1] = Math.max(g[h][1], f[i][h]);
                }
                ans = Math.max(ans, f[i][h]);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumLength(vector<int>& nums, int k) {
        int n = nums.size();
        vector<vector<int>> f(n, vector<int>(k + 1));
        vector<unordered_map<int, int>> mp(k + 1);
        vector<vector<int>> g(k + 1, vector<int>(3));
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int h = 0; h <= k; ++h) {
                f[i][h] = mp[h][nums[i]];
                if (h > 0) {
                    if (g[h - 1][0] != nums[i]) {
                        f[i][h] = max(f[i][h], g[h - 1][1]);
                    } else {
                        f[i][h] = max(f[i][h], g[h - 1][2]);
                    }
                }
                ++f[i][h];
                mp[h][nums[i]] = max(mp[h][nums[i]], f[i][h]);
                if (g[h][0] != nums[i]) {
                    if (f[i][h] >= g[h][1]) {
                        g[h][2] = g[h][1];
                        g[h][1] = f[i][h];
                        g[h][0] = nums[i];
                    } else {
                        g[h][2] = max(g[h][2], f[i][h]);
                    }
                } else {
                    g[h][1] = max(g[h][1], f[i][h]);
                }
                ans = max(ans, f[i][h]);
            }
        }

        return ans;
    }
};
```

#### Go

```go
func maximumLength(nums []int, k int) int {
	n := len(nums)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	mp := make([]map[int]int, k+1)
	for i := range mp {
		mp[i] = make(map[int]int)
	}
	g := make([][3]int, k+1)
	ans := 0

	for i := 0; i < n; i++ {
		for h := 0; h <= k; h++ {
			f[i][h] = mp[h][nums[i]]
			if h > 0 {
				if g[h-1][0] != nums[i] {
					if g[h-1][1] > f[i][h] {
						f[i][h] = g[h-1][1]
					}
				} else {
					if g[h-1][2] > f[i][h] {
						f[i][h] = g[h-1][2]
					}
				}
			}
			f[i][h]++
			if f[i][h] > mp[h][nums[i]] {
				mp[h][nums[i]] = f[i][h]
			}
			if g[h][0] != nums[i] {
				if f[i][h] >= g[h][1] {
					g[h][2] = g[h][1]
					g[h][1] = f[i][h]
					g[h][0] = nums[i]
				} else if f[i][h] > g[h][2] {
					g[h][2] = f[i][h]
				}
			} else {
				if f[i][h] > g[h][1] {
					g[h][1] = f[i][h]
				}
			}
			if f[i][h] > ans {
				ans = f[i][h]
			}
		}
	}

	return ans
}
```

#### TypeScript

```ts
function maximumLength(nums: number[], k: number): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array(k + 1).fill(0));
    const mp: Map<number, number>[] = Array.from({ length: k + 1 }, () => new Map());
    const g: number[][] = Array.from({ length: k + 1 }, () => Array(3).fill(0));
    let ans = 0;

    for (let i = 0; i < n; i++) {
        for (let h = 0; h <= k; h++) {
            f[i][h] = mp[h].get(nums[i]) || 0;
            if (h > 0) {
                if (g[h - 1][0] !== nums[i]) {
                    f[i][h] = Math.max(f[i][h], g[h - 1][1]);
                } else {
                    f[i][h] = Math.max(f[i][h], g[h - 1][2]);
                }
            }
            f[i][h]++;
            mp[h].set(nums[i], Math.max(mp[h].get(nums[i]) || 0, f[i][h]));
            if (g[h][0] !== nums[i]) {
                if (f[i][h] >= g[h][1]) {
                    g[h][2] = g[h][1];
                    g[h][1] = f[i][h];
                    g[h][0] = nums[i];
                } else {
                    g[h][2] = Math.max(g[h][2], f[i][h]);
                }
            } else {
                g[h][1] = Math.max(g[h][1], f[i][h]);
            }
            ans = Math.max(ans, f[i][h]);
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
