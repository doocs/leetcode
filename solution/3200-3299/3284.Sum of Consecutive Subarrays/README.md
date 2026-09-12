---
comments: true
difficulty: 中等
tags:
    - 数组
    - 双指针
    - 动态规划
---

<!-- problem:start -->

# [3284. 连续子数组的和 🔒](https://leetcode.cn/problems/sum-of-consecutive-subarrays)

[English Version](/solution/3200-3299/3284.Sum%20of%20Consecutive%20Subarrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果一个长度为&nbsp;<code>n</code>&nbsp;的数组&nbsp;<code>arr</code>&nbsp;符合下面其中一个条件，可以称它 <strong>连续</strong>：</p>

<ul>
	<li>对于所有的&nbsp;<code>1 &lt;= i &lt; n</code>，<code>arr[i] - arr[i - 1] == 1</code>。</li>
	<li>对于所有的&nbsp;<code>1 &lt;= i &lt; n</code>，<code>arr[i] - arr[i - 1] == -1</code>。</li>
</ul>

<p>数组的 <strong>值</strong> 是其元素的和。</p>

<p>例如，<code>[3, 4, 5]</code>&nbsp;是一个值为 12 的连续数组，并且&nbsp;<code>[9, 8]</code>&nbsp;是另一个值为 17 的连续数组。而&nbsp;<code>[3, 4, 3]</code> 和&nbsp;<code>[8, 6]</code>&nbsp;都不连续。</p>

<p>给定一个整数数组&nbsp;<code>nums</code>，返回所有 <strong>连续</strong> <span data-keyword="subarray-nonempty">子数组</span> 的 <strong>值</strong> 之和。</p>

<p>由于答案可能很大，返回它对&nbsp;<code>10<sup>9 </sup>+ 7</code>&nbsp;<strong>取模</strong>&nbsp;的值。</p>

<p><strong>注意</strong>&nbsp;长度为 1 的数组也被认为是连续的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,2,3]</span></p>

<p><span class="example-io"><b>输出：</b>20</span></p>

<p><strong>解释：</strong></p>

<p>连续子数组为：<code>[1]</code>，<code>[2]</code>，<code>[3]</code>，<code>[1, 2]</code>，<code>[2, 3]</code>，<code>[1, 2, 3]</code>。<br />
它们的值之和为：<code>1 + 2 + 3 + 3 + 5 + 6 = 20</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [1,3,5,7]</span></p>

<p><span class="example-io"><b>输出：</b>16</span></p>

<p><strong>解释：</strong></p>

<p>连续子数组为：<code>[1]</code>，<code>[3]</code>，<code>[5]</code>，<code>[7]</code>。<br />
它们的值之和为：<code>1 + 3 + 5 + 7 = 16</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [7,6,1,2]</span></p>

<p><strong>输出：</strong><span class="example-io">32</span></p>

<p><strong>解释：</strong></p>

<p>连续子数组为：<code>[7]</code>，<code>[6]</code>，<code>[1]</code>，<code>[2]</code>，<code>[7, 6]</code>，<code>[1, 2]</code>。<br />
它们的值之和为&nbsp;<code>7 + 6 + 1 + 2 + 13 + 3 = 32</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递推

<!-- thinking:start -->

> **思考**
>
> 连续子数组指相邻差恰为 $\pm 1$ 的段，要求和（单点也算）。$n$ 较大时不能枚举段。以 $i$ 结尾的递增段、递减段的个数与和可递推。
>
> 相邻差为 $1$ 则延长递增段并更新段内和；为 $-1$ 则延长递减段；否则只加单点。注意差为 $\pm 1$ 时单点已含在对应段和中，避免重复。滚动四个变量即可。

<!-- thinking:end -->

我们定义两个变量 $f$ 和 $g$，分别表示以当前元素结尾的递增子数组的长度和以当前元素结尾的递减子数组的长度，用另外两个变量 $s$ 和 $t$ 分别表示以当前元素结尾的递增子数组的和和以当前元素结尾的递减子数组的和。初始时 $f = g = 1$，而 $s = t = \textit{nums}[0]$。

接下来，我们从第二个元素开始遍历数组，对于当前元素 $\textit{nums}[i]$，我们分别考虑以 $\textit{nums}[i]$ 结尾的递增子数组和递减子数组。

如果 $\textit{nums}[i] - \textit{nums}[i - 1] = 1$，那么 $\textit{nums}[i]$ 可以加入到以 $\textit{nums}[i - 1]$ 结尾的递增子数组中，此时我们更新 $f$ 和 $s$，并将 $s$ 加到答案中；

如果 $\textit{nums}[i] - \textit{nums}[i - 1] = -1$，那么 $\textit{nums}[i]$ 可以加入到以 $\textit{nums}[i - 1]$ 结尾的递减子数组中，此时我们更新 $g$ 和 $t$，并将 $t$ 加到答案中。

否则，$\textit{nums}[i]$ 无法加入到以 $\textit{nums}[i - 1]$ 结尾的递增子数组或递减子数组中，我们将 $\textit{nums}[i]$ 加到答案中。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getSum(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        f = g = 1
        s = t = nums[0]
        ans = nums[0]
        for x, y in pairwise(nums):
            if y - x == 1:
                f += 1
                s += f * y
                ans = (ans + s) % mod
            else:
                f = 1
                s = y
            if y - x == -1:
                g += 1
                t += g * y
                ans = (ans + t) % mod
            else:
                g = 1
                t = y
            if abs(y - x) != 1:
                ans = (ans + y) % mod
        return ans
```

#### Java

```java
class Solution {
    public int getSum(int[] nums) {
        final int mod = (int) 1e9 + 7;
        long s = nums[0], t = nums[0], ans = nums[0];
        int f = 1, g = 1;
        for (int i = 1; i < nums.length; ++i) {
            int x = nums[i - 1], y = nums[i];
            if (y - x == 1) {
                ++f;
                s += 1L * f * y;
                ans = (ans + s) % mod;
            } else {
                f = 1;
                s = y;
            }
            if (y - x == -1) {
                ++g;
                t += 1L * g * y;
                ans = (ans + t) % mod;
            } else {
                g = 1;
                t = y;
            }
            if (Math.abs(y - x) != 1) {
                ans = (ans + y) % mod;
            }
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int getSum(vector<int>& nums) {
        const int mod = 1e9 + 7;
        long long s = nums[0], t = nums[0], ans = nums[0];
        int f = 1, g = 1;
        for (int i = 1; i < nums.size(); ++i) {
            int x = nums[i - 1], y = nums[i];
            if (y - x == 1) {
                ++f;
                s += 1LL * f * y;
                ans = (ans + s) % mod;
            } else {
                f = 1;
                s = y;
            }
            if (y - x == -1) {
                ++g;
                t += 1LL * g * y;
                ans = (ans + t) % mod;
            } else {
                g = 1;
                t = y;
            }
            if (abs(y - x) != 1) {
                ans = (ans + y) % mod;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func getSum(nums []int) int {
	const mod int = 1e9 + 7
	f, g := 1, 1
	s, t := nums[0], nums[0]
	ans := nums[0]

	for i := 1; i < len(nums); i++ {
		x, y := nums[i-1], nums[i]

		if y-x == 1 {
			f++
			s += f * y
			ans = (ans + s) % mod
		} else {
			f = 1
			s = y
		}

		if y-x == -1 {
			g++
			t += g * y
			ans = (ans + t) % mod
		} else {
			g = 1
			t = y
		}

		if abs(y-x) != 1 {
			ans = (ans + y) % mod
		}
	}

	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function getSum(nums: number[]): number {
    const mod = 10 ** 9 + 7;
    let f = 1,
        g = 1;
    let s = nums[0],
        t = nums[0];
    let ans = nums[0];

    for (let i = 1; i < nums.length; i++) {
        const x = nums[i - 1];
        const y = nums[i];

        if (y - x === 1) {
            f++;
            s += f * y;
            ans = (ans + s) % mod;
        } else {
            f = 1;
            s = y;
        }

        if (y - x === -1) {
            g++;
            t += g * y;
            ans = (ans + t) % mod;
        } else {
            g = 1;
            t = y;
        }

        if (Math.abs(y - x) !== 1) {
            ans = (ans + y) % mod;
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
