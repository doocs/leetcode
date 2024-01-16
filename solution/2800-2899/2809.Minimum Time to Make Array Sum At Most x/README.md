# [2809. 使数组和小于等于 x 的最少时间](https://leetcode.cn/problems/minimum-time-to-make-array-sum-at-most-x)

[English Version](/solution/2800-2899/2809.Minimum%20Time%20to%20Make%20Array%20Sum%20At%20Most%20x/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个长度相等下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;。每一秒，对于所有下标&nbsp;<code>0 &lt;= i &lt; nums1.length</code>&nbsp;，<code>nums1[i]</code>&nbsp;的值都增加&nbsp;<code>nums2[i]</code>&nbsp;。操作&nbsp;<strong>完成后</strong>&nbsp;，你可以进行如下操作：</p>

<ul>
	<li>选择任一满足&nbsp;<code>0 &lt;= i &lt; nums1.length</code>&nbsp;的下标 <code>i</code>&nbsp;，并使&nbsp;<code>nums1[i] = 0</code>&nbsp;。</li>
</ul>

<p>同时给你一个整数&nbsp;<code>x</code>&nbsp;。</p>

<p>请你返回使&nbsp;<code>nums1</code>&nbsp;中所有元素之和 <strong>小于等于</strong>&nbsp;<code>x</code>&nbsp;所需要的 <strong>最少</strong>&nbsp;时间，如果无法实现，那么返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums1 = [1,2,3], nums2 = [1,2,3], x = 4
<b>输出：</b>3
<b>解释：</b>
第 1 秒，我们对 i = 0 进行操作，得到 nums1 = [0,2+2,3+3] = [0,4,6] 。
第 2 秒，我们对 i = 1 进行操作，得到 nums1 = [0+1,0,6+3] = [1,0,9] 。
第 3 秒，我们对 i = 2 进行操作，得到 nums1 = [1+1,0+2,0] = [2,2,0] 。
现在 nums1 的和为 4 。不存在更少次数的操作，所以我们返回 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums1 = [1,2,3], nums2 = [3,3,3], x = 4
<b>输出：</b>-1
<b>解释：</b>不管如何操作，nums1 的和总是会超过 x 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= nums1[i] &lt;= 10<sup>3</sup></code></li>
	<li><code>0 &lt;= nums2[i] &lt;= 10<sup>3</sup></code></li>
	<li><code>nums1.length == nums2.length</code></li>
	<li><code>0 &lt;= x &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

### 方法一：排序 + 动态规划

我们注意到，如果我们多次操作同一个数，那么只有最后一次操作是有意义的，其余的对该数的操作，只会使得其它数字增大。因此，我们最多对每个数操作一次，也即是说，操作次数在 $[0,..n]$ 之间。

我们不妨假设进行了 $j$ 次操作，操作的数字下标分别为 $i_1, i_2, \cdots, i_j$，那么对于这 $j$ 次操作，每一次可以使得数组元素和减少的值为：

$$
\begin{aligned}
& d_1 = nums_1[i_1] + nums_2[i_1] \times 1 \\
& d_2 = nums_1[i_2] + nums_2[i_2] \times 2 \\
& \cdots \\
& d_j = nums_1[i_j] + nums_2[i_j] \times j
\end{aligned}
$$

从贪心的角度考虑，为了使得数组元素和的减少量最大，我们应当让 $nums_2$ 中的较大元素尽可能放在后面操作。因此，我们可以对 $nums_1$ 和 $nums_2$ 按照 $nums_2$ 的元素值从小到大进行排序。

接下来，我们考虑动态规划的实现。我们用 $f[i][j]$ 表示对于数组 $nums_1$ 的前 $i$ 个元素，进行 $j$ 次操作，所能减少的数组元素和的最大值。我们可以得到如下的状态转移方程：

$$
f[i][j] = \max \{f[i-1][j], f[i-1][j-1] + nums_1[i] + nums_2[i] \times j\}
$$

最后，我们枚举 $j$，找到最小的满足 $s_1 + s_2 \times j - f[n][j] \le x$ 的 $j$ 即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是数组的长度。

我们注意到，状态 $f[i][j]$ 只与 $f[i-1][j]$ 和 $f[i-1][j-1]$ 有关，因此我们可以优化掉第一维，将空间复杂度降低到 $O(n)$。

<!-- tabs:start -->

```python
class Solution:
    def minimumTime(self, nums1: List[int], nums2: List[int], x: int) -> int:
        n = len(nums1)
        f = [[0] * (n + 1) for _ in range(n + 1)]
        for i, (a, b) in enumerate(sorted(zip(nums1, nums2), key=lambda z: z[1]), 1):
            for j in range(n + 1):
                f[i][j] = f[i - 1][j]
                if j > 0:
                    f[i][j] = max(f[i][j], f[i - 1][j - 1] + a + b * j)
        s1 = sum(nums1)
        s2 = sum(nums2)
        for j in range(n + 1):
            if s1 + s2 * j - f[n][j] <= x:
                return j
        return -1
```

```java
class Solution {
    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size();
        int[][] f = new int[n + 1][n + 1];
        int[][] nums = new int[n][0];
        for (int i = 0; i < n; ++i) {
            nums[i] = new int[] {nums1.get(i), nums2.get(i)};
        }
        Arrays.sort(nums, Comparator.comparingInt(a -> a[1]));
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j];
                if (j > 0) {
                    int a = nums[i - 1][0], b = nums[i - 1][1];
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + a + b * j);
                }
            }
        }
        int s1 = 0, s2 = 0;
        for (int v : nums1) {
            s1 += v;
        }
        for (int v : nums2) {
            s2 += v;
        }

        for (int j = 0; j <= n; ++j) {
            if (s1 + s2 * j - f[n][j] <= x) {
                return j;
            }
        }
        return -1;
    }
}
```

```cpp
class Solution {
public:
    int minimumTime(vector<int>& nums1, vector<int>& nums2, int x) {
        int n = nums1.size();
        vector<pair<int, int>> nums;
        for (int i = 0; i < n; ++i) {
            nums.emplace_back(nums2[i], nums1[i]);
        }
        sort(nums.begin(), nums.end());
        int f[n + 1][n + 1];
        memset(f, 0, sizeof(f));
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j];
                if (j) {
                    auto [b, a] = nums[i - 1];
                    f[i][j] = max(f[i][j], f[i - 1][j - 1] + a + b * j);
                }
            }
        }
        int s1 = accumulate(nums1.begin(), nums1.end(), 0);
        int s2 = accumulate(nums2.begin(), nums2.end(), 0);
        for (int j = 0; j <= n; ++j) {
            if (s1 + s2 * j - f[n][j] <= x) {
                return j;
            }
        }
        return -1;
    }
};
```

```go
func minimumTime(nums1 []int, nums2 []int, x int) int {
	n := len(nums1)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	type pair struct{ a, b int }
	nums := make([]pair, n)
	var s1, s2 int
	for i := range nums {
		s1 += nums1[i]
		s2 += nums2[i]
		nums[i] = pair{nums1[i], nums2[i]}
	}
	sort.Slice(nums, func(i, j int) bool { return nums[i].b < nums[j].b })
	for i := 1; i <= n; i++ {
		for j := 0; j <= n; j++ {
			f[i][j] = f[i-1][j]
			if j > 0 {
				a, b := nums[i-1].a, nums[i-1].b
				f[i][j] = max(f[i][j], f[i-1][j-1]+a+b*j)
			}
		}
	}
	for j := 0; j <= n; j++ {
		if s1+s2*j-f[n][j] <= x {
			return j
		}
	}
	return -1
}
```

```ts
function minimumTime(nums1: number[], nums2: number[], x: number): number {
    const n = nums1.length;
    const f: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
    const nums: number[][] = [];
    for (let i = 0; i < n; ++i) {
        nums.push([nums1[i], nums2[i]]);
    }
    nums.sort((a, b) => a[1] - b[1]);
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j <= n; ++j) {
            f[i][j] = f[i - 1][j];
            if (j > 0) {
                const [a, b] = nums[i - 1];
                f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + a + b * j);
            }
        }
    }
    const s1 = nums1.reduce((a, b) => a + b, 0);
    const s2 = nums2.reduce((a, b) => a + b, 0);
    for (let j = 0; j <= n; ++j) {
        if (s1 + s2 * j - f[n][j] <= x) {
            return j;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def minimumTime(self, nums1: List[int], nums2: List[int], x: int) -> int:
        n = len(nums1)
        f = [0] * (n + 1)
        for a, b in sorted(zip(nums1, nums2), key=lambda z: z[1]):
            for j in range(n, 0, -1):
                f[j] = max(f[j], f[j - 1] + a + b * j)
        s1 = sum(nums1)
        s2 = sum(nums2)
        for j in range(n + 1):
            if s1 + s2 * j - f[j] <= x:
                return j
        return -1
```

```java
class Solution {
    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size();
        int[] f = new int[n + 1];
        int[][] nums = new int[n][0];
        for (int i = 0; i < n; ++i) {
            nums[i] = new int[] {nums1.get(i), nums2.get(i)};
        }
        Arrays.sort(nums, Comparator.comparingInt(a -> a[1]));
        for (int[] e : nums) {
            int a = e[0], b = e[1];
            for (int j = n; j > 0; --j) {
                f[j] = Math.max(f[j], f[j - 1] + a + b * j);
            }
        }
        int s1 = 0, s2 = 0;
        for (int v : nums1) {
            s1 += v;
        }
        for (int v : nums2) {
            s2 += v;
        }

        for (int j = 0; j <= n; ++j) {
            if (s1 + s2 * j - f[j] <= x) {
                return j;
            }
        }
        return -1;
    }
}
```

```cpp
class Solution {
public:
    int minimumTime(vector<int>& nums1, vector<int>& nums2, int x) {
        int n = nums1.size();
        vector<pair<int, int>> nums;
        for (int i = 0; i < n; ++i) {
            nums.emplace_back(nums2[i], nums1[i]);
        }
        sort(nums.begin(), nums.end());
        int f[n + 1];
        memset(f, 0, sizeof(f));
        for (auto [b, a] : nums) {
            for (int j = n; j; --j) {
                f[j] = max(f[j], f[j - 1] + a + b * j);
            }
        }
        int s1 = accumulate(nums1.begin(), nums1.end(), 0);
        int s2 = accumulate(nums2.begin(), nums2.end(), 0);
        for (int j = 0; j <= n; ++j) {
            if (s1 + s2 * j - f[j] <= x) {
                return j;
            }
        }
        return -1;
    }
};
```

```go
func minimumTime(nums1 []int, nums2 []int, x int) int {
	n := len(nums1)
	f := make([]int, n+1)
	type pair struct{ a, b int }
	nums := make([]pair, n)
	var s1, s2 int
	for i := range nums {
		s1 += nums1[i]
		s2 += nums2[i]
		nums[i] = pair{nums1[i], nums2[i]}
	}
	sort.Slice(nums, func(i, j int) bool { return nums[i].b < nums[j].b })
	for _, e := range nums {
		a, b := e.a, e.b
		for j := n; j > 0; j-- {
			f[j] = max(f[j], f[j-1]+a+b*j)
		}
	}
	for j := 0; j <= n; j++ {
		if s1+s2*j-f[j] <= x {
			return j
		}
	}
	return -1
}
```

```ts
function minimumTime(nums1: number[], nums2: number[], x: number): number {
    const n = nums1.length;
    const f: number[] = new Array(n + 1).fill(0);
    const nums: number[][] = [];
    for (let i = 0; i < n; ++i) {
        nums.push([nums1[i], nums2[i]]);
    }
    nums.sort((a, b) => a[1] - b[1]);
    for (const [a, b] of nums) {
        for (let j = n; j > 0; --j) {
            f[j] = Math.max(f[j], f[j - 1] + a + b * j);
        }
    }
    const s1 = nums1.reduce((a, b) => a + b, 0);
    const s2 = nums2.reduce((a, b) => a + b, 0);
    for (let j = 0; j <= n; ++j) {
        if (s1 + s2 * j - f[j] <= x) {
            return j;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- end -->
