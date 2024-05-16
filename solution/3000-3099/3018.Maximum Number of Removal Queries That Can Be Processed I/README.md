---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3018.Maximum%20Number%20of%20Removal%20Queries%20That%20Can%20Be%20Processed%20I/README.md
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3018. 可处理的最大删除操作数 I 🔒](https://leetcode.cn/problems/maximum-number-of-removal-queries-that-can-be-processed-i)

[English Version](/solution/3000-3099/3018.Maximum%20Number%20of%20Removal%20Queries%20That%20Can%20Be%20Processed%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个下标&nbsp;<strong>从&nbsp;0 开始</strong>&nbsp;的数组&nbsp;<code>nums</code> 和一个下标&nbsp;<strong>从</strong>&nbsp;<strong>0 开始&nbsp;</strong>的数组&nbsp;<code>queries</code>。</p>

<p>你可以在开始时执行以下操作 <strong>最多一次</strong>：</p>

<ul>
	<li>用&nbsp;<code>nums</code>&nbsp;的 <span data-keyword="subsequence-array">子序列</span> 替换&nbsp;<code>nums</code>。</li>
</ul>

<p>我们以给定的<code>queries</code>顺序处理查询；对于<code>queries[i]</code>，我们执行以下操作：</p>

<ul>
	<li>如果&nbsp;<code>nums</code> 的第一个 <strong>和</strong> 最后一个元素 <strong>小于</strong>&nbsp;<code>queries[i]</code>，则查询处理 <strong>结束</strong>。</li>
	<li>否则，从&nbsp;<code>nums</code>&nbsp;选择第一个 <strong>或</strong> 最后一个元素，要求其<strong>大于或等于</strong> <code>queries[i]</code>，然后将其从&nbsp;<code>nums</code> 中 <strong>删除</strong>。</li>
</ul>

<p>返回通过以最佳方式执行该操作可以处理的&nbsp;<strong>最多&nbsp;</strong>次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4,5], queries = [1,2,3,4,6]
<strong>输出：</strong>4
<strong>解释：</strong>我们不执行任何操作，并按如下方式处理查询：
1- 我们选择并移除 nums[0]，因为 1 &lt;= 1，那么 nums 就变成 [2,3,4,5]。
2- 我们选择并移除 nums[0]，因为 2 &lt;= 2，那么 nums 就变成 [3,4,5]。
3- 我们选择并移除 nums[0]，因为 3 &lt;= 3，那么 nums 就变成 [4,5]。
4- 我们选择并移除 nums[0]，因为 4 &lt;= 4，那么 nums 就变成 [5]。
5- 我们不能从 nums 中选择任何元素，因为它们不大于或等于 5。
因此，答案为 4。
可以看出，我们不能处理超过 4 个查询。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,3,2], queries = [2,2,3]
<b>输出：</b>3
<strong>解释：</strong>我们不做任何操作，按如下方式处理查询：
1- 我们选择并移除 nums[0]，因为 2 &lt;= 2，那么 nums 就变成 [3,2]。
2- 我们选择并移除 nums[1]，因为 2 &lt;= 2，那么 nums 就变成 [3]。
3- 我们选择并移除 nums[0]，因为 3 &lt;= 3，那么 nums 就变成 []。
因此，答案为 3。
可以看出，我们不能处理超过 3 个查询。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,4,3], queries = [4,3,2]
<strong>输出：</strong>2
<strong>解释：</strong>首先，我们用 nums 的子序列 [4,3] 替换 nums。
然后，我们可以按如下方式处理查询：
1- 我们选择并移除 nums[0]，因为 4 &lt;= 4，那么 nums 就变成 [3]。
2- 我们选择并移除 nums[0]，因为 3 &lt;= 3，那么 nums 就变成 []。
3- 我们无法处理更多查询，因为 nums 为空。
因此，答案为 2。
可以看出，我们不能处理超过 2 个查询。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= queries.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], queries[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 表示区间 $[i, j]$ 的数还没有被删除时，我们能够处理的查询的最大数量。

考虑 $f[i][j]$：

-   如果 $i \gt 0$，此时 $f[i][j]$ 的值可以由 $f[i - 1][j]$ 转移而来。如果 $nums[i - 1] \ge queries[f[i - 1][j]]$，那么我们可以选择删除 $nums[i - 1]$，因此我们有 $f[i][j] = f[i - 1][j] + (nums[i - 1] \ge queries[f[i - 1][j]])$。
-   如果 $j + 1 \lt n$，此时 $f[i][j]$ 的值可以由 $f[i][j + 1]$ 转移而来。如果 $nums[j + 1] \ge queries[f[i][j + 1]]$，那么我们可以选择删除 $nums[j + 1]$，因此我们有 $f[i][j] = f[i][j + 1] + (nums[j + 1] \ge queries[f[i][j + 1]])$。
-   如果 $f[i][j] = m$，那么我们就可以直接返回 $m$。

最后的答案即为 $\max\limits_{0 \le i \lt n} f[i][i] + (nums[i] \ge queries[f[i][i]])$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maximumProcessableQueries(self, nums: List[int], queries: List[int]) -> int:
        n = len(nums)
        f = [[0] * n for _ in range(n)]
        m = len(queries)
        for i in range(n):
            for j in range(n - 1, i - 1, -1):
                if i:
                    f[i][j] = max(
                        f[i][j], f[i - 1][j] + (nums[i - 1] >= queries[f[i - 1][j]])
                    )
                if j + 1 < n:
                    f[i][j] = max(
                        f[i][j], f[i][j + 1] + (nums[j + 1] >= queries[f[i][j + 1]])
                    )
                if f[i][j] == m:
                    return m
        return max(f[i][i] + (nums[i] >= queries[f[i][i]]) for i in range(n))
```

```java
class Solution {
    public int maximumProcessableQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int[][] f = new int[n][n];
        int m = queries.length;
        for (int i = 0; i < n; ++i) {
            for (int j = n - 1; j >= i; --j) {
                if (i > 0) {
                    f[i][j] = Math.max(
                        f[i][j], f[i - 1][j] + (nums[i - 1] >= queries[f[i - 1][j]] ? 1 : 0));
                }
                if (j + 1 < n) {
                    f[i][j] = Math.max(
                        f[i][j], f[i][j + 1] + (nums[j + 1] >= queries[f[i][j + 1]] ? 1 : 0));
                }
                if (f[i][j] == m) {
                    return m;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, f[i][i] + (nums[i] >= queries[f[i][i]] ? 1 : 0));
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumProcessableQueries(vector<int>& nums, vector<int>& queries) {
        int n = nums.size();
        int f[n][n];
        memset(f, 0, sizeof(f));
        int m = queries.size();
        for (int i = 0; i < n; ++i) {
            for (int j = n - 1; j >= i; --j) {
                if (i > 0) {
                    f[i][j] = max(f[i][j], f[i - 1][j] + (nums[i - 1] >= queries[f[i - 1][j]] ? 1 : 0));
                }
                if (j + 1 < n) {
                    f[i][j] = max(f[i][j], f[i][j + 1] + (nums[j + 1] >= queries[f[i][j + 1]] ? 1 : 0));
                }
                if (f[i][j] == m) {
                    return m;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, f[i][i] + (nums[i] >= queries[f[i][i]] ? 1 : 0));
        }
        return ans;
    }
};
```

```go
func maximumProcessableQueries(nums []int, queries []int) (ans int) {
	n := len(nums)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
	}
	m := len(queries)
	for i := 0; i < n; i++ {
		for j := n - 1; j >= i; j-- {
			if i > 0 {
				t := 0
				if nums[i-1] >= queries[f[i-1][j]] {
					t = 1
				}
				f[i][j] = max(f[i][j], f[i-1][j]+t)
			}
			if j+1 < n {
				t := 0
				if nums[j+1] >= queries[f[i][j+1]] {
					t = 1
				}
				f[i][j] = max(f[i][j], f[i][j+1]+t)
			}
			if f[i][j] == m {
				return m
			}
		}
	}
	for i := 0; i < n; i++ {
		t := 0
		if nums[i] >= queries[f[i][i]] {
			t = 1
		}
		ans = max(ans, f[i][i]+t)
	}
	return
}
```

```ts
function maximumProcessableQueries(nums: number[], queries: number[]): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array.from({ length: n }, () => 0));
    const m = queries.length;
    for (let i = 0; i < n; ++i) {
        for (let j = n - 1; j >= i; --j) {
            if (i > 0) {
                f[i][j] = Math.max(
                    f[i][j],
                    f[i - 1][j] + (nums[i - 1] >= queries[f[i - 1][j]] ? 1 : 0),
                );
            }
            if (j + 1 < n) {
                f[i][j] = Math.max(
                    f[i][j],
                    f[i][j + 1] + (nums[j + 1] >= queries[f[i][j + 1]] ? 1 : 0),
                );
            }
            if (f[i][j] == m) {
                return m;
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, f[i][i] + (nums[i] >= queries[f[i][i]] ? 1 : 0));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
