# [2915. Length of the Longest Subsequence That Sums to Target](https://leetcode.com/problems/length-of-the-longest-subsequence-that-sums-to-target)

[中文文档](/solution/2900-2999/2915.Length%20of%20the%20Longest%20Subsequence%20That%20Sums%20to%20Target/README.md)

<!-- tags:Array,Dynamic Programming -->

## Description

<p>You are given a <strong>0-indexed</strong> array of integers <code>nums</code>, and an integer <code>target</code>.</p>

<p>Return <em>the <strong>length of the longest subsequence</strong> of</em> <code>nums</code> <em>that sums up to</em> <code>target</code>. <em>If no such subsequence exists, return</em> <code>-1</code>.</p>

<p>A <strong>subsequence</strong> is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5], target = 9
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 subsequences with a sum equal to 9: [4,5], [1,3,5], and [2,3,4]. The longest subsequences are [1,3,5], and [2,3,4]. Hence, the answer is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,1,3,2,1,5], target = 7
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 5 subsequences with a sum equal to 7: [4,3], [4,1,2], [4,2,1], [1,1,5], and [1,3,2,1]. The longest subsequence is [1,3,2,1]. Hence, the answer is 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,5,4,5], target = 3
<strong>Output:</strong> -1
<strong>Explanation:</strong> It can be shown that nums has no subsequence that sums up to 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= target &lt;= 1000</code></li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

We define $f[i][j]$ as the length of the longest subsequence that selects several numbers from the first $i$ numbers and the sum of these numbers is exactly $j$. Initially, $f[0][0]=0$, and all other positions are $-\infty$.

For $f[i][j]$, we consider the $i$th number $x$. If we do not select $x$, then $f[i][j]=f[i-1][j]$. If we select $x$, then $f[i][j]=f[i-1][j-x]+1$, where $j\ge x$. Therefore, we have the state transition equation:

$$
f[i][j]=\max\{f[i-1][j],f[i-1][j-x]+1\}
$$

The final answer is $f[n][target]$. If $f[n][target]\le0$, there is no subsequence with a sum of $target$, return $-1$.

The time complexity is $O(n\times target)$, and the space complexity is $O(n\times target)$. Here, $n$ is the length of the array, and $target$ is the target value.

We notice that the state of $f[i][j]$ is only related to $f[i-1][\cdot]$, so we can optimize the first dimension and reduce the space complexity to $O(target)$.

<!-- tabs:start -->

```python
class Solution:
    def lengthOfLongestSubsequence(self, nums: List[int], target: int) -> int:
        n = len(nums)
        f = [[-inf] * (target + 1) for _ in range(n + 1)]
        f[0][0] = 0
        for i, x in enumerate(nums, 1):
            for j in range(target + 1):
                f[i][j] = f[i - 1][j]
                if j >= x:
                    f[i][j] = max(f[i][j], f[i - 1][j - x] + 1)
        return -1 if f[n][target] <= 0 else f[n][target]
```

```java
class Solution {
    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int n = nums.size();
        int[][] f = new int[n + 1][target + 1];
        final int inf = 1 << 30;
        for (int[] g : f) {
            Arrays.fill(g, -inf);
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            int x = nums.get(i - 1);
            for (int j = 0; j <= target; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= x) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - x] + 1);
                }
            }
        }
        return f[n][target] <= 0 ? -1 : f[n][target];
    }
}
```

```cpp
class Solution {
public:
    int lengthOfLongestSubsequence(vector<int>& nums, int target) {
        int n = nums.size();
        int f[n + 1][target + 1];
        memset(f, -0x3f, sizeof(f));
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            int x = nums[i - 1];
            for (int j = 0; j <= target; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= x) {
                    f[i][j] = max(f[i][j], f[i - 1][j - x] + 1);
                }
            }
        }
        return f[n][target] <= 0 ? -1 : f[n][target];
    }
};
```

```go
func lengthOfLongestSubsequence(nums []int, target int) int {
	n := len(nums)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, target+1)
		for j := range f[i] {
			f[i][j] = -(1 << 30)
		}
	}
	f[0][0] = 0
	for i := 1; i <= n; i++ {
		x := nums[i-1]
		for j := 0; j <= target; j++ {
			f[i][j] = f[i-1][j]
			if j >= x {
				f[i][j] = max(f[i][j], f[i-1][j-x]+1)
			}
		}
	}
	if f[n][target] <= 0 {
		return -1
	}
	return f[n][target]
}
```

```ts
function lengthOfLongestSubsequence(nums: number[], target: number): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(target + 1).fill(-Infinity));
    f[0][0] = 0;
    for (let i = 1; i <= n; ++i) {
        const x = nums[i - 1];
        for (let j = 0; j <= target; ++j) {
            f[i][j] = f[i - 1][j];
            if (j >= x) {
                f[i][j] = Math.max(f[i][j], f[i - 1][j - x] + 1);
            }
        }
    }
    return f[n][target] <= 0 ? -1 : f[n][target];
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def lengthOfLongestSubsequence(self, nums: List[int], target: int) -> int:
        f = [0] + [-inf] * target
        for x in nums:
            for j in range(target, x - 1, -1):
                f[j] = max(f[j], f[j - x] + 1)
        return -1 if f[-1] <= 0 else f[-1]
```

```java
class Solution {
    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int[] f = new int[target + 1];
        final int inf = 1 << 30;
        Arrays.fill(f, -inf);
        f[0] = 0;
        for (int x : nums) {
            for (int j = target; j >= x; --j) {
                f[j] = Math.max(f[j], f[j - x] + 1);
            }
        }
        return f[target] <= 0 ? -1 : f[target];
    }
}
```

```cpp
class Solution {
public:
    int lengthOfLongestSubsequence(vector<int>& nums, int target) {
        int f[target + 1];
        memset(f, -0x3f, sizeof(f));
        f[0] = 0;
        for (int x : nums) {
            for (int j = target; j >= x; --j) {
                f[j] = max(f[j], f[j - x] + 1);
            }
        }
        return f[target] <= 0 ? -1 : f[target];
    }
};
```

```go
func lengthOfLongestSubsequence(nums []int, target int) int {
	f := make([]int, target+1)
	for i := range f {
		f[i] = -(1 << 30)
	}
	f[0] = 0
	for _, x := range nums {
		for j := target; j >= x; j-- {
			f[j] = max(f[j], f[j-x]+1)
		}
	}
	if f[target] <= 0 {
		return -1
	}
	return f[target]
}
```

```ts
function lengthOfLongestSubsequence(nums: number[], target: number): number {
    const f: number[] = Array(target + 1).fill(-Infinity);
    f[0] = 0;
    for (const x of nums) {
        for (let j = target; j >= x; --j) {
            f[j] = Math.max(f[j], f[j - x] + 1);
        }
    }
    return f[target] <= 0 ? -1 : f[target];
}
```

<!-- tabs:end -->

<!-- end -->
