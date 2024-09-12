---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3284.Sum%20of%20Consecutive%20Subarrays/README.md
---

<!-- problem:start -->

# [3284. Sum of Consecutive Subarrays 🔒](https://leetcode.cn/problems/sum-of-consecutive-subarrays)

[English Version](/solution/3200-3299/3284.Sum%20of%20Consecutive%20Subarrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>We call an array <code>arr</code> of length <code>n</code> <strong>consecutive</strong> if one of the following holds:</p>

<ul>
	<li><code>arr[i] - arr[i - 1] == 1</code> for <em>all</em> <code>1 &lt;= i &lt; n</code>.</li>
	<li><code>arr[i] - arr[i - 1] == -1</code> for <em>all</em> <code>1 &lt;= i &lt; n</code>.</li>
</ul>

<p>The <strong>value</strong> of an array is the sum of its elements.</p>

<p>For example, <code>[3, 4, 5]</code> is a consecutive array of value 12 and <code>[9, 8]</code> is another of value 17. While <code>[3, 4, 3]</code> and <code>[8, 6]</code> are not consecutive.</p>

<p>Given an array of integers <code>nums</code>, return the <em>sum</em> of the <strong>values</strong> of all <strong>consecutive </strong><span data-keyword="subarray-nonempty">subarrays</span>.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9 </sup>+ 7.</code></p>

<p><strong>Note</strong> that an array of length 1 is also considered consecutive.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">20</span></p>

<p><strong>Explanation:</strong></p>

<p>The consecutive subarrays are: <code>[1]</code>, <code>[2]</code>, <code>[3]</code>, <code>[1, 2]</code>, <code>[2, 3]</code>, <code>[1, 2, 3]</code>.<br />
Sum of their values would be: <code>1 + 2 + 3 + 3 + 5 + 6 = 20</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,5,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">16</span></p>

<p><strong>Explanation:</strong></p>

<p>The consecutive subarrays are: <code>[1]</code>, <code>[3]</code>, <code>[5]</code>, <code>[7]</code>.<br />
Sum of their values would be: <code>1 + 3 + 5 + 7 = 16</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,6,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">32</span></p>

<p><strong>Explanation:</strong></p>

<p>The consecutive subarrays are: <code>[7]</code>, <code>[6]</code>, <code>[1]</code>, <code>[2]</code>, <code>[7, 6]</code>, <code>[1, 2]</code>.<br />
Sum of their values would be: <code>7 + 6 + 1 + 2 + 13 + 3 = 32</code>.</p>
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
