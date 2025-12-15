---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3196.Maximize%20Total%20Cost%20of%20Alternating%20Subarrays/README.md
rating: 1846
source: 第 403 场周赛 Q3
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3196. 最大化子数组的总成本](https://leetcode.cn/problems/maximize-total-cost-of-alternating-subarrays)

[English Version](/solution/3100-3199/3196.Maximize%20Total%20Cost%20of%20Alternating%20Subarrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>。</p>

<p>子数组 <code>nums[l..r]</code>（其中 <code>0 &lt;= l &lt;= r &lt; n</code>）的 <strong>成本 </strong>定义为：</p>

<p><code>cost(l, r) = nums[l] - nums[l + 1] + ... + nums[r] * (−1)<sup>r − l</sup></code></p>

<p>你的任务是将 <code>nums</code> 分割成若干子数组，使得所有子数组的成本之和 <strong>最大化</strong>，并确保每个元素 <strong>正好 </strong>属于一个子数组。</p>

<p>具体来说，如果 <code>nums</code> 被分割成 <code>k</code> 个子数组，且分割点为索引 <code>i<sub>1</sub>, i<sub>2</sub>, ..., i<sub>k − 1</sub></code>（其中 <code>0 &lt;= i<sub>1</sub> &lt; i<sub>2</sub> &lt; ... &lt; i<sub>k - 1</sub> &lt; n - 1</code>），则总成本为：</p>

<p><code>cost(0, i<sub>1</sub>) + cost(i<sub>1</sub> + 1, i<sub>2</sub>) + ... + cost(i<sub>k − 1</sub> + 1, n − 1)</code></p>

<p>返回在最优分割方式下的子数组成本之和的最大值。</p>

<p><strong>注意：</strong>如果 <code>nums</code> 没有被分割，即 <code>k = 1</code>，则总成本即为 <code>cost(0, n - 1)</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,-2,3,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">10</span></p>

<p><strong>解释：</strong></p>

<p>一种总成本最大化的方法是将 <code>[1, -2, 3, 4]</code> 分割成子数组 <code>[1, -2, 3]</code> 和 <code>[4]</code>。总成本为 <code>(1 + 2 + 3) + 4 = 10</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,-1,1,-1]</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>一种总成本最大化的方法是将 <code>[1, -1, 1, -1]</code> 分割成子数组 <code>[1, -1]</code> 和 <code>[1, -1]</code>。总成本为 <code>(1 + 1) + (1 + 1) = 4</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [0]</span></p>

<p><strong>输出：</strong> 0</p>

<p><strong>解释：</strong></p>

<p>无法进一步分割数组，因此答案为 0。</p>
</div>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,-1]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>选择整个数组，总成本为 <code>1 + 1 = 2</code>，这是可能的最大成本。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

根据题目描述，如果当前数没取反，那么下一个可以取反，也可以不取反；如果当前数取反了，那么下一个只能不取反。

因此，我们定义一个函数 $\textit{dfs}(i, j)$，表示从第 $i$ 个数开始，第 $i$ 个数是否能取反，其中 $j$ 表示第 $i$ 个数是否取反。如果 $j = 0$，表示第 $i$ 个数不能取反，否则可以取反。答案为 $\textit{dfs}(0, 0)$。

函数 $dfs(i, j)$ 的执行过程如下：

- 如果 $i \geq \textit{len}(nums)$，表示已经遍历完了数组，返回 $0$；
- 否则，第 $i$ 个数可以不取反，此时答案为 $nums[i] + \textit{dfs}(i + 1, 1)$；如果 $j = 1$，表示第 $i$ 个数可以取反，此时答案为 $\max(\textit{dfs}(i + 1, 0) - nums[i])$。我们取两者的最大值即可。

为了避免重复计算，我们可以使用记忆化搜索，将已经计算过的结果保存起来。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumTotalCost(self, nums: List[int]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i >= len(nums):
                return 0
            ans = nums[i] + dfs(i + 1, 1)
            if j == 1:
                ans = max(ans, -nums[i] + dfs(i + 1, 0))
            return ans

        return dfs(0, 0)
```

#### Java

```java
class Solution {
    private Long[][] f;
    private int[] nums;
    private int n;

    public long maximumTotalCost(int[] nums) {
        n = nums.length;
        this.nums = nums;
        f = new Long[n][2];
        return dfs(0, 0);
    }

    private long dfs(int i, int j) {
        if (i >= n) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        f[i][j] = nums[i] + dfs(i + 1, 1);
        if (j == 1) {
            f[i][j] = Math.max(f[i][j], -nums[i] + dfs(i + 1, 0));
        }
        return f[i][j];
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumTotalCost(vector<int>& nums) {
        int n = nums.size();
        long long f[n][2];
        fill(f[0], f[n], LLONG_MIN);
        auto dfs = [&](this auto&& dfs, int i, int j) -> long long {
            if (i >= n) {
                return 0;
            }
            if (f[i][j] != LLONG_MIN) {
                return f[i][j];
            }
            f[i][j] = nums[i] + dfs(i + 1, 1);
            if (j) {
                f[i][j] = max(f[i][j], -nums[i] + dfs(i + 1, 0));
            }
            return f[i][j];
        };
        return dfs(0, 0);
    }
};
```

#### Go

```go
func maximumTotalCost(nums []int) int64 {
	n := len(nums)
	f := make([][2]int64, n)
	for i := range f {
		f[i] = [2]int64{-1e18, -1e18}
	}
	var dfs func(int, int) int64
	dfs = func(i, j int) int64 {
		if i >= n {
			return 0
		}
		if f[i][j] != -1e18 {
			return f[i][j]
		}
		f[i][j] = int64(nums[i]) + dfs(i+1, 1)
		if j > 0 {
			f[i][j] = max(f[i][j], int64(-nums[i])+dfs(i+1, 0))
		}
		return f[i][j]
	}
	return dfs(0, 0)
}
```

#### TypeScript

```ts
function maximumTotalCost(nums: number[]): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array(2).fill(-Infinity));
    const dfs = (i: number, j: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i][j] !== -Infinity) {
            return f[i][j];
        }
        f[i][j] = nums[i] + dfs(i + 1, 1);
        if (j) {
            f[i][j] = Math.max(f[i][j], -nums[i] + dfs(i + 1, 0));
        }
        return f[i][j];
    };
    return dfs(0, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

我们可以将方法一中的记忆化搜索转换为动态规划。

定义 $f$ 和 $g$ 为两个状态，其中 $f$ 表示当前数不取反的最大值，而 $g$ 表示当前数取反的最大值。

遍历数组 $nums$，对于第 $i$ 个数，我们可以根据 $f$ 和 $g$ 的状态更新 $f$ 和 $g$ 的值：

- 如果当前数不取反，那么 $f$ 的值为 $\max(f, g) + x$，表示当前数不取反，那么前一个数可以取反或不取反；
- 如果当前数取反，那么 $g$ 的值为 $f - x$，表示当前数取反，那么前一个数不能取反

最终答案为 $\max(f, g)$。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumTotalCost(self, nums: List[int]) -> int:
        f, g = -inf, 0
        for x in nums:
            f, g = max(f, g) + x, f - x
        return max(f, g)
```

#### Java

```java
class Solution {
    public long maximumTotalCost(int[] nums) {
        long f = Long.MIN_VALUE / 2, g = 0;
        for (int x : nums) {
            long ff = Math.max(f, g) + x;
            long gg = f - x;
            f = ff;
            g = gg;
        }
        return Math.max(f, g);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumTotalCost(vector<int>& nums) {
        long long f = LLONG_MIN / 2, g = 0;
        for (int x : nums) {
            long long ff = max(f, g) + x, gg = f - x;
            f = ff;
            g = gg;
        }
        return max(f, g);
    }
};
```

#### Go

```go
func maximumTotalCost(nums []int) int64 {
	f, g := math.MinInt64/2, 0
	for _, x := range nums {
		f, g = max(f, g)+x, f-x
	}
	return int64(max(f, g))
}
```

#### TypeScript

```ts
function maximumTotalCost(nums: number[]): number {
    let [f, g] = [-Infinity, 0];
    for (const x of nums) {
        [f, g] = [Math.max(f, g) + x, f - x];
    }
    return Math.max(f, g);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
