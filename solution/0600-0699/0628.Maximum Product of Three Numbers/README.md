---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0628.Maximum%20Product%20of%20Three%20Numbers/README.md
tags:
    - 数组
    - 数学
    - 排序
---

<!-- problem:start -->

# [628. 三个数的最大乘积](https://leetcode.cn/problems/maximum-product-of-three-numbers)

[English Version](/solution/0600-0699/0628.Maximum%20Product%20of%20Three%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整型数组 <code>nums</code> ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>6
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>24
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,-2,-3]
<strong>输出：</strong>-6
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 <= nums.length <= 10<sup>4</sup></code></li>
	<li><code>-1000 <= nums[i] <= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 分类讨论

我们先对数组 $\textit{nums}$ 进行排序，接下来分两种情况讨论：

-   如果 $\textit{nums}$ 中全是非负数或者全是非正数，那么答案即为最后三个数的乘积，即 $\textit{nums}[n-1] \times \textit{nums}[n-2] \times \textit{nums}[n-3]$；
-   如果 $\textit{nums}$ 中既有正数也有负数，那么答案可能是两个最小负数和一个最大整数的乘积，即 $\textit{nums}[n-1] \times \textit{nums}[0] \times \textit{nums}[1]$；也可能是最后三个数的乘积，即 $\textit{nums}[n-1] \times \textit{nums}[n-2] \times \textit{nums}[n-3]$。

最后返回两种情况的最大值即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumProduct(self, nums: List[int]) -> int:
        nums.sort()
        a = nums[-1] * nums[-2] * nums[-3]
        b = nums[-1] * nums[0] * nums[1]
        return max(a, b)
```

#### Java

```java
class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int a = nums[n - 1] * nums[n - 2] * nums[n - 3];
        int b = nums[n - 1] * nums[0] * nums[1];
        return Math.max(a, b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumProduct(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int a = nums[n - 1] * nums[n - 2] * nums[n - 3];
        int b = nums[n - 1] * nums[0] * nums[1];
        return max(a, b);
    }
};
```

#### Go

```go
func maximumProduct(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	a := nums[n-1] * nums[n-2] * nums[n-3]
	b := nums[n-1] * nums[0] * nums[1]
	if a > b {
		return a
	}
	return b
}
```

#### TypeScript

```ts
function maximumProduct(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const a = nums[n - 1] * nums[n - 2] * nums[n - 3];
    const b = nums[n - 1] * nums[0] * nums[1];
    return Math.max(a, b);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：一次遍历

我们可以不用对数组进行排序，而是维护五个变量，其中 $\textit{mi1}$ 和 $\textit{mi2}$ 表示数组中最小的两个数，而 $\textit{mx1}$, $\textit{mx2}$ 和 $\textit{mx3}$ 表示数组中最大的三个数。

最后返回 $\max(\textit{mi1} \times \textit{mi2} \times \textit{mx1}, \textit{mx1} \times \textit{mx2} \times \textit{mx3})$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumProduct(self, nums: List[int]) -> int:
        top3 = nlargest(3, nums)
        bottom2 = nlargest(2, nums, key=lambda x: -x)
        return max(top3[0] * top3[1] * top3[2], top3[0] * bottom2[0] * bottom2[1])
```

#### Java

```java
class Solution {
    public int maximumProduct(int[] nums) {
        final int inf = 1 << 30;
        int mi1 = inf, mi2 = inf;
        int mx1 = -inf, mx2 = -inf, mx3 = -inf;
        for (int x : nums) {
            if (x < mi1) {
                mi2 = mi1;
                mi1 = x;
            } else if (x < mi2) {
                mi2 = x;
            }
            if (x > mx1) {
                mx3 = mx2;
                mx2 = mx1;
                mx1 = x;
            } else if (x > mx2) {
                mx3 = mx2;
                mx2 = x;
            } else if (x > mx3) {
                mx3 = x;
            }
        }
        return Math.max(mi1 * mi2 * mx1, mx1 * mx2 * mx3);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumProduct(vector<int>& nums) {
        const int inf = 1 << 30;
        int mi1 = inf, mi2 = inf;
        int mx1 = -inf, mx2 = -inf, mx3 = -inf;
        for (int x : nums) {
            if (x < mi1) {
                mi2 = mi1;
                mi1 = x;
            } else if (x < mi2) {
                mi2 = x;
            }
            if (x > mx1) {
                mx3 = mx2;
                mx2 = mx1;
                mx1 = x;
            } else if (x > mx2) {
                mx3 = mx2;
                mx2 = x;
            } else if (x > mx3) {
                mx3 = x;
            }
        }
        return max(mi1 * mi2 * mx1, mx1 * mx2 * mx3);
    }
};
```

#### Go

```go
func maximumProduct(nums []int) int {
	const inf = 1 << 30
	mi1, mi2 := inf, inf
	mx1, mx2, mx3 := -inf, -inf, -inf
	for _, x := range nums {
		if x < mi1 {
			mi1, mi2 = x, mi1
		} else if x < mi2 {
			mi2 = x
		}
		if x > mx1 {
			mx1, mx2, mx3 = x, mx1, mx2
		} else if x > mx2 {
			mx2, mx3 = x, mx2
		} else if x > mx3 {
			mx3 = x
		}
	}
	return max(mi1*mi2*mx1, mx1*mx2*mx3)
}
```

#### TypeScript

```ts
function maximumProduct(nums: number[]): number {
    const inf = 1 << 30;
    let mi1 = inf,
        mi2 = inf;
    let mx1 = -inf,
        mx2 = -inf,
        mx3 = -inf;
    for (const x of nums) {
        if (x < mi1) {
            mi2 = mi1;
            mi1 = x;
        } else if (x < mi2) {
            mi2 = x;
        }
        if (x > mx1) {
            mx3 = mx2;
            mx2 = mx1;
            mx1 = x;
        } else if (x > mx2) {
            mx3 = mx2;
            mx2 = x;
        } else if (x > mx3) {
            mx3 = x;
        }
    }
    return Math.max(mi1 * mi2 * mx1, mx1 * mx2 * mx3);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
