---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1567.Maximum%20Length%20of%20Subarray%20With%20Positive%20Product/README.md
rating: 1710
source: 第 204 场周赛 Q2
tags:
    - 贪心
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [1567. 乘积为正数的最长子数组长度](https://leetcode.cn/problems/maximum-length-of-subarray-with-positive-product)

[English Version](/solution/1500-1599/1567.Maximum%20Length%20of%20Subarray%20With%20Positive%20Product/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>&nbsp;，请你求出乘积为正数的最长子数组的长度。</p>

<p>一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。</p>

<p>请你返回乘积为正数的最长子数组长度。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp; 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,-2,-3,4]
<strong>输出：</strong>4
<strong>解释：</strong>数组本身乘积就是正数，值为 24 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,-2,-3,-4]
<strong>输出：</strong>3
<strong>解释：</strong>最长乘积为正数的子数组为 [1,-2,-3] ，乘积为 6 。
注意，我们不能把 0 也包括到子数组中，因为这样乘积为 0 ，不是正数。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,-2,-3,0,1]
<strong>输出：</strong>2
<strong>解释：</strong>乘积为正数的最长子数组是 [-1,-2] 或者 [-2,-3] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10^5</code></li>
	<li><code>-10^9 &lt;= nums[i]&nbsp;&lt;= 10^9</code></li>
</ul>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义两个长度为 $n$ 的数组 $f$ 和 $g$，其中 $f[i]$ 表示以 $\textit{nums}[i]$ 结尾的乘积为正数的最长子数组的长度，而 $g[i]$ 表示以 $\textit{nums}[i]$ 结尾的乘积为负数的最长子数组的长度。

初始时，如果 $\textit{nums}[0] > 0$，则 $f[0] = 1$，否则 $f[0] = 0$；如果 $\textit{nums}[0] < 0$，则 $g[0] = 1$，否则 $g[0] = 0$。我们初始化答案 $ans = f[0]$。

接下来，我们从 $i = 1$ 开始遍历数组 $\textit{nums}$，对于每个 $i$，我们有以下几种情况：

-   如果 $\textit{nums}[i] > 0$，那么 $f[i]$ 可以由 $f[i - 1]$ 转移而来，即 $f[i] = f[i - 1] + 1$，而 $g[i]$ 的值取决于 $g[i - 1]$ 是否为 $0$，如果 $g[i - 1] = 0$，则 $g[i] = 0$，否则 $g[i] = g[i - 1] + 1$；
-   如果 $\textit{nums}[i] < 0$，那么 $f[i]$ 的值取决于 $g[i - 1]$ 是否为 $0$，如果 $g[i - 1] = 0$，则 $f[i] = 0$，否则 $f[i] = g[i - 1] + 1$，而 $g[i]$ 可以由 $f[i - 1]$ 转移而来，即 $g[i] = f[i - 1] + 1$。
-   然后，我们更新答案 $ans = \max(ans, f[i])$。

遍历结束后，返回答案 $ans$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getMaxLen(self, nums: List[int]) -> int:
        n = len(nums)
        f = [0] * n
        g = [0] * n
        f[0] = int(nums[0] > 0)
        g[0] = int(nums[0] < 0)
        ans = f[0]
        for i in range(1, n):
            if nums[i] > 0:
                f[i] = f[i - 1] + 1
                g[i] = 0 if g[i - 1] == 0 else g[i - 1] + 1
            elif nums[i] < 0:
                f[i] = 0 if g[i - 1] == 0 else g[i - 1] + 1
                g[i] = f[i - 1] + 1
            ans = max(ans, f[i])
        return ans
```

#### Java

```java
class Solution {
    public int getMaxLen(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        int[] g = new int[n];
        f[0] = nums[0] > 0 ? 1 : 0;
        g[0] = nums[0] < 0 ? 1 : 0;
        int ans = f[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > 0) {
                f[i] = f[i - 1] + 1;
                g[i] = g[i - 1] > 0 ? g[i - 1] + 1 : 0;
            } else if (nums[i] < 0) {
                f[i] = g[i - 1] > 0 ? g[i - 1] + 1 : 0;
                g[i] = f[i - 1] + 1;
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int getMaxLen(vector<int>& nums) {
        int n = nums.size();
        vector<int> f(n, 0), g(n, 0);
        f[0] = nums[0] > 0 ? 1 : 0;
        g[0] = nums[0] < 0 ? 1 : 0;
        int ans = f[0];

        for (int i = 1; i < n; ++i) {
            if (nums[i] > 0) {
                f[i] = f[i - 1] + 1;
                g[i] = g[i - 1] > 0 ? g[i - 1] + 1 : 0;
            } else if (nums[i] < 0) {
                f[i] = g[i - 1] > 0 ? g[i - 1] + 1 : 0;
                g[i] = f[i - 1] + 1;
            }
            ans = max(ans, f[i]);
        }
        return ans;
    }
};
```

#### Go

```go
func getMaxLen(nums []int) int {
	n := len(nums)
	f := make([]int, n)
	g := make([]int, n)
	if nums[0] > 0 {
		f[0] = 1
	}
	if nums[0] < 0 {
		g[0] = 1
	}
	ans := f[0]

	for i := 1; i < n; i++ {
		if nums[i] > 0 {
			f[i] = f[i-1] + 1
			if g[i-1] > 0 {
				g[i] = g[i-1] + 1
			} else {
				g[i] = 0
			}
		} else if nums[i] < 0 {
			if g[i-1] > 0 {
				f[i] = g[i-1] + 1
			} else {
				f[i] = 0
			}
			g[i] = f[i-1] + 1
		}
		ans = max(ans, f[i])
	}
	return ans
}
```

#### TypeScript

```ts
function getMaxLen(nums: number[]): number {
    const n = nums.length;
    const f: number[] = Array(n).fill(0);
    const g: number[] = Array(n).fill(0);

    if (nums[0] > 0) {
        f[0] = 1;
    }
    if (nums[0] < 0) {
        g[0] = 1;
    }

    let ans = f[0];
    for (let i = 1; i < n; i++) {
        if (nums[i] > 0) {
            f[i] = f[i - 1] + 1;
            g[i] = g[i - 1] > 0 ? g[i - 1] + 1 : 0;
        } else if (nums[i] < 0) {
            f[i] = g[i - 1] > 0 ? g[i - 1] + 1 : 0;
            g[i] = f[i - 1] + 1;
        }

        ans = Math.max(ans, f[i]);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划（空间优化）

我们发现，对于每个 $i$，$f[i]$ 和 $g[i]$ 的值只与 $f[i - 1]$ 和 $g[i - 1]$ 有关，因此我们可以使用两个变量 $f$ 和 $g$ 分别记录 $f[i - 1]$ 和 $g[i - 1]$ 的值，从而将空间复杂度优化至 $O(1)$。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getMaxLen(self, nums: List[int]) -> int:
        n = len(nums)
        f = int(nums[0] > 0)
        g = int(nums[0] < 0)
        ans = f
        for i in range(1, n):
            ff = gg = 0
            if nums[i] > 0:
                ff = f + 1
                gg = 0 if g == 0 else g + 1
            elif nums[i] < 0:
                ff = 0 if g == 0 else g + 1
                gg = f + 1
            f, g = ff, gg
            ans = max(ans, f)
        return ans
```

#### Java

```java
class Solution {
    public int getMaxLen(int[] nums) {
        int n = nums.length;
        int f = nums[0] > 0 ? 1 : 0;
        int g = nums[0] < 0 ? 1 : 0;
        int ans = f;

        for (int i = 1; i < n; i++) {
            int ff = 0, gg = 0;
            if (nums[i] > 0) {
                ff = f + 1;
                gg = g == 0 ? 0 : g + 1;
            } else if (nums[i] < 0) {
                ff = g == 0 ? 0 : g + 1;
                gg = f + 1;
            }
            f = ff;
            g = gg;
            ans = Math.max(ans, f);
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int getMaxLen(vector<int>& nums) {
        int n = nums.size();
        int f = nums[0] > 0 ? 1 : 0;
        int g = nums[0] < 0 ? 1 : 0;
        int ans = f;

        for (int i = 1; i < n; i++) {
            int ff = 0, gg = 0;
            if (nums[i] > 0) {
                ff = f + 1;
                gg = g == 0 ? 0 : g + 1;
            } else if (nums[i] < 0) {
                ff = g == 0 ? 0 : g + 1;
                gg = f + 1;
            }
            f = ff;
            g = gg;
            ans = max(ans, f);
        }

        return ans;
    }
};
```

#### Go

```go
func getMaxLen(nums []int) int {
	n := len(nums)
	var f, g int
	if nums[0] > 0 {
		f = 1
	} else if nums[0] < 0 {
		g = 1
	}
	ans := f
	for i := 1; i < n; i++ {
		ff, gg := 0, 0
		if nums[i] > 0 {
			ff = f + 1
			gg = 0
			if g > 0 {
				gg = g + 1
			}
		} else if nums[i] < 0 {
			ff = 0
			if g > 0 {
				ff = g + 1
			}
			gg = f + 1
		}
		f, g = ff, gg
		ans = max(ans, f)
	}
	return ans
}
```

#### TypeScript

```ts
function getMaxLen(nums: number[]): number {
    const n = nums.length;
    let [f, g] = [0, 0];
    if (nums[0] > 0) {
        f = 1;
    } else if (nums[0] < 0) {
        g = 1;
    }
    let ans = f;
    for (let i = 1; i < n; i++) {
        let [ff, gg] = [0, 0];
        if (nums[i] > 0) {
            ff = f + 1;
            gg = g > 0 ? g + 1 : 0;
        } else if (nums[i] < 0) {
            ff = g > 0 ? g + 1 : 0;
            gg = f + 1;
        }
        [f, g] = [ff, gg];
        ans = Math.max(ans, f);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
