---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1567.Maximum%20Length%20of%20Subarray%20With%20Positive%20Product/README_EN.md
rating: 1710
source: Weekly Contest 204 Q2
tags:
    - Greedy
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [1567. Maximum Length of Subarray With Positive Product](https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product)

[中文文档](/solution/1500-1599/1567.Maximum%20Length%20of%20Subarray%20With%20Positive%20Product/README.md)

## Description

<!-- description:start -->

<p>Given an array of integers <code>nums</code>, find the maximum length of a subarray where the product of all its elements is positive.</p>

<p>A subarray of an array is a consecutive sequence of zero or more values taken out of that array.</p>

<p>Return <em>the maximum length of a subarray with positive product</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-2,-3,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The array nums already has a positive product of 24.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,-2,-3,-4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The longest subarray with positive product is [1,-2,-3] which has a product of 6.
Notice that we cannot include 0 in the subarray since that&#39;ll make the product 0 which is not positive.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,-2,-3,0,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The longest subarray with positive product is [-1,-2] or [-2,-3].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define two arrays $f$ and $g$ of length $n$, where $f[i]$ represents the length of the longest subarray ending at $\textit{nums}[i]$ with a positive product, and $g[i]$ represents the length of the longest subarray ending at $\textit{nums}[i]$ with a negative product.

Initially, if $\textit{nums}[0] > 0$, then $f[0] = 1$, otherwise $f[0] = 0$; if $\textit{nums}[0] < 0$, then $g[0] = 1$, otherwise $g[0] = 0$. We initialize the answer $ans = f[0]$.

Next, we iterate through the array $\textit{nums}$ starting from $i = 1$. For each $i$, we have the following cases:

-   If $\textit{nums}[i] > 0$, then $f[i]$ can be transferred from $f[i - 1]$, i.e., $f[i] = f[i - 1] + 1$, and the value of $g[i]$ depends on whether $g[i - 1]$ is $0$. If $g[i - 1] = 0$, then $g[i] = 0$, otherwise $g[i] = g[i - 1] + 1$;
-   If $\textit{nums}[i] < 0$, then the value of $f[i]$ depends on whether $g[i - 1]$ is $0$. If $g[i - 1] = 0$, then $f[i] = 0$, otherwise $f[i] = g[i - 1] + 1$, and $g[i]$ can be transferred from $f[i - 1]$, i.e., $g[i] = f[i - 1] + 1$.
-   Then, we update the answer $ans = \max(ans, f[i])$.

After the iteration, we return the answer $ans$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

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

### Solution 2: Dynamic Programming (Space Optimization)

We observe that for each $i$, the values of $f[i]$ and $g[i]$ only depend on $f[i - 1]$ and $g[i - 1]$. Therefore, we can use two variables $f$ and $g$ to record the values of $f[i - 1]$ and $g[i - 1]$, respectively, thus optimizing the space complexity to $O(1)$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

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
