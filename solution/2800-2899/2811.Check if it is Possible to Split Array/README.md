---
comments: true
difficulty: 中等
rating: 1543
source: 第 357 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [2811. 判断是否能拆分数组](https://leetcode.cn/problems/check-if-it-is-possible-to-split-array)

[English Version](/solution/2800-2899/2811.Check%20if%20it%20is%20Possible%20to%20Split%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的数组 <code>nums</code> 和一个整数 <code>m</code> 。请你判断能否执行一系列操作，将数组拆分成 <code>n</code> 个 <strong>非空 </strong>数组。</p>

<p>一个数组被称为 <strong>好</strong> 的，如果：</p>

<ul>
	<li>子数组的长度为 1 ，或者</li>
	<li>子数组元素之和 <strong>大于或等于</strong>&nbsp; <code>m</code> 。</li>
</ul>

<p>在每一步操作中，你可以选择一个 <strong>长度至少为 2</strong> 的现有数组（之前步骤的结果） 并将其拆分成 <strong>2</strong> 个子数组，而得到的 <strong>每个</strong> 子数组都需要是好的。</p>

<p>如果你可以将给定数组拆分成 <code>n</code> 个满足要求的数组，返回 <code>true</code><em> </em>；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [2, 2, 1], m = 4</span></p>

<p><span class="example-io"><b>输出：</b>true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>将 <code>[2, 2, 1]</code> <span class="example-io">切分为</span> <code>[2, 2]</code> 和&nbsp;<code>[1]</code>。数组 <code>[1]</code> 的长度为 1，数组 <code>[2, 2]</code> 的元素之和等于 <code>4 &gt;= m</code>，所以两者都是好的数组。</li>
	<li>将 <code>[2, 2]</code> <span class="example-io">切分为</span> <code>[2]</code> 和&nbsp;<code>[2]</code>。两个数组的长度都是 1，所以都是好的数组。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b></span><span class="example-io">nums = [2, 1, 3], m = 5</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<p>第一步必须是以下之一：</p>

<ul>
	<li>将&nbsp;<code>[2, 1, 3]</code> <span class="example-io">切分为</span> <code>[2, 1]</code> 和&nbsp;<code>[3]</code>。数组&nbsp;<code>[2, 1]</code> 既不是长度为 1，也没有大于或等于 <code>m</code> 的元素和。</li>
	<li>将 <code>[2, 1, 3]</code> <span class="example-io">切分为</span> <code>[2]</code> 和 <code>[1, 3]</code>。数组&nbsp;<code>[1, 3]</code> 既不是长度为 1，也没有大于或等于 <code>m</code> 的元素和。</li>
</ul>

<p>因此，由于这两个操作都无效（它们没有将数组分成两个好的数组），因此我们无法将 <code>nums</code> 分成 <code>n</code> 个大小为 1 的数组。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b></span><span class="example-io">nums = [2, 3, 3, 2, 3], m = 6</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><span class="example-io">将&nbsp;<code>[2, 3, 3, 2, 3]</code>&nbsp;切分为&nbsp;<code>[2]</code> 和&nbsp;<code>[3, 3, 2, 3]</code>。</span></li>
	<li><span class="example-io">将 <code>[3, 3, 2, 3]</code> 切分为 <code>[3, 3, 2]</code> 和 <code>[3]</code>。</span></li>
	<li><span class="example-io">将 <code>[3, 3, 2]</code> 切分为 <code>[3, 3]</code> 和 <code>[2]</code>。</span></li>
	<li><span class="example-io">将 <code>[3, 3]</code> 切分为 <code>[3]</code> 和 <code>[3]</code>。</span></li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= m &lt;= 200</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

<!-- thinking:start -->

> **思考**
>
> 每次拆分要求切出的两段要么长度为 $1$，要么元素和至少为 $m$。区间状态有 $O(n^2)$ 个，可以记忆化。预处理前缀和后，$dfs(i,j)$ 枚举切点 $k$，检查左右段是否合法并递归两端。

<!-- thinking:end -->

我们先预处理得到前缀和数组 $s$，其中 $s[i]$ 表示数组 $nums$ 的前 $i$ 个元素之和。

接下来，我们设计一个函数 $dfs(i, j)$，表示对于数组 $nums$ 的下标范围 $[i, j]$，是否存在一种满足条件的拆分方法。如果存在，返回 `true`，否则返回 `false`。

函数 $dfs(i, j)$ 的计算过程如下：

如果 $i = j$，那么只有一个元素，不需要拆分，返回 `true`；

否则，我们枚举拆分点 $k$，其中 $k \in [i, j]$，如果满足以下条件，那么就可以拆分成 $nums[i,.. k]$ 和 $nums[k + 1,.. j]$ 两个子数组：

- 子数组 $nums[i,..k]$ 只有一个元素，或者子数组 $nums[i,..k]$ 的元素之和大于等于 $m$；
- 子数组 $nums[k + 1,..j]$ 只有一个元素，或者子数组 $nums[k + 1,..j]$ 的元素之和大于等于 $m$；
- $dfs(i, k)$ 和 $dfs(k + 1, j)$ 都为 `true`。

为了避免重复计算，我们使用记忆化搜索的方法，用一个二维数组 $f$ 记录所有的 $dfs(i, j)$ 的返回值，其中 $f[i][j]$ 表示 $dfs(i, j)$ 的返回值。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canSplitArray(self, nums: List[int], m: int) -> bool:
        @cache
        def dfs(i: int, j: int) -> bool:
            if i == j:
                return True
            for k in range(i, j):
                a = k == i or s[k + 1] - s[i] >= m
                b = k == j - 1 or s[j + 1] - s[k + 1] >= m
                if a and b and dfs(i, k) and dfs(k + 1, j):
                    return True
            return False

        s = list(accumulate(nums, initial=0))
        return dfs(0, len(nums) - 1)
```

#### Java

```java
class Solution {
    private Boolean[][] f;
    private int[] s;
    private int m;

    public boolean canSplitArray(List<Integer> nums, int m) {
        int n = nums.size();
        f = new Boolean[n][n];
        s = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums.get(i - 1);
        }
        this.m = m;
        return dfs(0, n - 1);
    }

    private boolean dfs(int i, int j) {
        if (i == j) {
            return true;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        for (int k = i; k < j; ++k) {
            boolean a = k == i || s[k + 1] - s[i] >= m;
            boolean b = k == j - 1 || s[j + 1] - s[k + 1] >= m;
            if (a && b && dfs(i, k) && dfs(k + 1, j)) {
                return f[i][j] = true;
            }
        }
        return f[i][j] = false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canSplitArray(vector<int>& nums, int m) {
        int n = nums.size();
        vector<int> s(n + 1);
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int f[n][n];
        memset(f, -1, sizeof f);
        function<bool(int, int)> dfs = [&](int i, int j) {
            if (i == j) {
                return true;
            }
            if (f[i][j] != -1) {
                return f[i][j] == 1;
            }
            for (int k = i; k < j; ++k) {
                bool a = k == i || s[k + 1] - s[i] >= m;
                bool b = k == j - 1 || s[j + 1] - s[k + 1] >= m;
                if (a && b && dfs(i, k) && dfs(k + 1, j)) {
                    f[i][j] = 1;
                    return true;
                }
            }
            f[i][j] = 0;
            return false;
        };
        return dfs(0, n - 1);
    }
};
```

#### Go

```go
func canSplitArray(nums []int, m int) bool {
	n := len(nums)
	f := make([][]int, n)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	for i := range f {
		f[i] = make([]int, n)
	}
	var dfs func(i, j int) bool
	dfs = func(i, j int) bool {
		if i == j {
			return true
		}
		if f[i][j] != 0 {
			return f[i][j] == 1
		}
		for k := i; k < j; k++ {
			a := k == i || s[k+1]-s[i] >= m
			b := k == j-1 || s[j+1]-s[k+1] >= m
			if a && b && dfs(i, k) && dfs(k+1, j) {
				f[i][j] = 1
				return true
			}
		}
		f[i][j] = -1
		return false
	}
	return dfs(0, n-1)
}
```

#### TypeScript

```ts
function canSplitArray(nums: number[], m: number): boolean {
    const n = nums.length;
    const s: number[] = new Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        s[i] = s[i - 1] + nums[i - 1];
    }
    const f: number[][] = Array(n)
        .fill(0)
        .map(() => Array(n).fill(-1));
    const dfs = (i: number, j: number): boolean => {
        if (i === j) {
            return true;
        }
        if (f[i][j] !== -1) {
            return f[i][j] === 1;
        }
        for (let k = i; k < j; ++k) {
            const a = k === i || s[k + 1] - s[i] >= m;
            const b = k === j - 1 || s[j + 1] - s[k + 1] >= m;
            if (a && b && dfs(i, k) && dfs(k + 1, j)) {
                f[i][j] = 1;
                return true;
            }
        }
        f[i][j] = 0;
        return false;
    };
    return dfs(0, n - 1);
}
```

#### Rust

```rust
impl Solution {
    pub fn can_split_array(nums: Vec<i32>, m: i32) -> bool {
        let n = nums.len();
        if n <= 2 {
            return true;
        }
        for i in 1..n {
            if nums[i - 1] + nums[i] >= m {
                return true;
            }
        }
        false
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：脑筋急转弯

<!-- thinking:start -->

> **思考**
>
> 记忆化仍要枚举切点。元素非负，反复拆分后总会留下某段长度为 $2$ 的子数组，且更长段的和只会更大。因此当 $n>2$ 时，存在相邻之和不小于 $m$ 的一对即充分必要；$n\le 2$ 则无需拆分。

<!-- thinking:end -->

不论如何操作，最终总会剩下一个 `length == 2` 的子数组，又因为元素数值不存在负数，所以随着分割操作的进行，子数组的长度和总和都会逐渐变小，其它 `length > 2` 子数组之和肯定要比该子数组之和更大，进而，我们只需要考虑，是否存在一个 `length == 2` 且总和大于等于 `m` 的子数组即可。

> 📢 注意，当 `nums.length <= 2` 时，无需进行操作。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### TypeScript

```ts
function canSplitArray(nums: number[], m: number): boolean {
    const n = nums.length;
    if (n <= 2) {
        return true;
    }
    for (let i = 1; i < n; i++) {
        if (nums[i - 1] + nums[i] >= m) {
            return true;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
