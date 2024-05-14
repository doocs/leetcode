# [2811. 判断是否能拆分数组](https://leetcode.cn/problems/check-if-it-is-possible-to-split-array)

[English Version](/solution/2800-2899/2811.Check%20if%20it%20is%20Possible%20to%20Split%20Array/README_EN.md)

<!-- tags:贪心,数组,动态规划 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的数组 <code>nums</code> 和一个整数 <code>m</code> 。请你判断能否执行一系列操作，将数组拆分成 <code>n</code> 个 <strong>非空 </strong>数组。</p>

<p>在每一步操作中，你可以选择一个 <strong>长度至少为 2</strong> 的现有数组（之前步骤的结果） 并将其拆分成 <strong>2</strong> 个子数组，而得到的 <strong>每个</strong> 子数组，<strong>至少</strong> 需要满足以下条件之一：</p>

<ul>
	<li>子数组的长度为 1 ，或者</li>
	<li>子数组元素之和 <strong>大于或等于</strong>&nbsp; <code>m</code> 。</li>
</ul>

<p>如果你可以将给定数组拆分成 <code>n</code> 个满足要求的数组，返回 <code>true</code><em> </em>；否则，返回 <code>false</code> 。</p>

<p><strong>注意：</strong>子数组是数组中的一个连续非空元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2, 2, 1], m = 4
<strong>输出：</strong>true
<strong>解释：</strong>
第 1 步，将数组 nums 拆分成 [2, 2] 和 [1] 。
第 2 步，将数组 [2, 2] 拆分成 [2] 和 [2] 。
因此，答案为 true 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2, 1, 3], m = 5 
<strong>输出：</strong>false
<strong>解释：
</strong>存在两种不同的拆分方法：
第 1 种，将数组 nums 拆分成 [2, 1] 和 [3] 。
第 2 种，将数组 nums 拆分成 [2] 和 [1, 3] 。
然而，这两种方法都不满足题意。因此，答案为 false 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2, 3, 3, 2, 3], m = 6
<strong>输出：</strong>true
<strong>解释：</strong>
第 1 步，将数组 nums 拆分成 [2, 3, 3, 2] 和 [3] 。
第 2 步，将数组 [2, 3, 3, 2] 拆分成 [2, 3, 3] 和 [2] 。
第 3 步，将数组 [2, 3, 3] 拆分成 [2] 和 [3, 3] 。
第 4 步，将数组 [3, 3] 拆分成 [3] 和 [3] 。
因此，答案为 true 。 </pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= m &lt;= 200</code></li>
</ul>

## 解法

### 方法一：记忆化搜索

我们先预处理得到前缀和数组 $s$，其中 $s[i]$ 表示数组 $nums$ 的前 $i$ 个元素之和。

接下来，我们设计一个函数 $dfs(i, j)$，表示对于数组 $nums$ 的下标范围 $[i, j]$，是否存在一种满足条件的拆分方法。如果存在，返回 `true`，否则返回 `false`。

函数 $dfs(i, j)$ 的计算过程如下：

如果 $i = j$，那么只有一个元素，不需要拆分，返回 `true`；

否则，我们枚举拆分点 $k$，其中 $k \in [i, j]$，如果满足以下条件，那么就可以拆分成 $nums[i,.. k]$ 和 $nums[k + 1,.. j]$ 两个子数组：

-   子数组 $nums[i,..k]$ 只有一个元素，或者子数组 $nums[i,..k]$ 的元素之和大于等于 $m$；
-   子数组 $nums[k + 1,..j]$ 只有一个元素，或者子数组 $nums[k + 1,..j]$ 的元素之和大于等于 $m$；
-   $dfs(i, k)$ 和 $dfs(k + 1, j)$ 都为 `true`。

为了避免重复计算，我们使用记忆化搜索的方法，用一个二维数组 $f$ 记录所有的 $dfs(i, j)$ 的返回值，其中 $f[i][j]$ 表示 $dfs(i, j)$ 的返回值。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

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

### 方法二：脑筋急转弯

不论如何操作，最终总会剩下一个 `length == 2` 的子数组，又因为元素数值不存在负数，所以随着分割操作的进行，子数组的长度和总和都会逐渐变小，其它 `length > 2` 子数组之和肯定要比该子数组之和更大，进而，我们只需要考虑，是否存在一个 `length == 2` 且总和大于等于 `m` 的子数组即可。

> 📢 注意，当 `nums.length <= 2` 时，无需进行操作。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

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

<!-- end -->
