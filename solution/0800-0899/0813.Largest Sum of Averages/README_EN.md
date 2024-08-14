---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0813.Largest%20Sum%20of%20Averages/README_EN.md
tags:
    - Array
    - Dynamic Programming
    - Prefix Sum
---

<!-- problem:start -->

# [813. Largest Sum of Averages](https://leetcode.com/problems/largest-sum-of-averages)

[中文文档](/solution/0800-0899/0813.Largest%20Sum%20of%20Averages/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>. You can partition the array into <strong>at most</strong> <code>k</code> non-empty adjacent subarrays. The <strong>score</strong> of a partition is the sum of the averages of each subarray.</p>

<p>Note that the partition must use every integer in <code>nums</code>, and that the score is not necessarily an integer.</p>

<p>Return <em>the maximum <strong>score</strong> you can achieve of all the possible partitions</em>. Answers within <code>10<sup>-6</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [9,1,2,3,9], k = 3
<strong>Output:</strong> 20.00000
<strong>Explanation:</strong> 
The best choice is to partition nums into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
We could have also partitioned nums into [9, 1], [2], [3, 9], for example.
That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6,7], k = 4
<strong>Output:</strong> 20.50000
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Memoized Search

We can preprocess to obtain the prefix sum array $s$, which allows us to quickly get the sum of subarrays.

Next, we design a function $\textit{dfs}(i, k)$, which represents the maximum sum of averages when dividing the array starting from index $i$ into at most $k$ groups. The answer is $\textit{dfs}(0, k)$.

The execution logic of the function $\textit{dfs}(i, k)$ is as follows:

-   When $i = n$, it means we have traversed to the end of the array, and we return $0$.
-   When $k = 1$, it means there is only one group left, and we return the average value from index $i$ to the end of the array.
-   Otherwise, we enumerate the starting position $j$ of the next group in the interval $[i + 1, n)$, calculate the average value from $i$ to $j - 1$ as $\frac{s[j] - s[i]}{j - i}$, add the result of $\textit{dfs}(j, k - 1)$, and take the maximum value of all results.

The time complexity is $O(n^2 \times k)$, and the space complexity is $O(n \times k)$. Here, $n$ represents the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestSumOfAverages(self, nums: List[int], k: int) -> float:
        @cache
        def dfs(i: int, k: int) -> float:
            if i == n:
                return 0
            if k == 1:
                return (s[n] - s[i]) / (n - i)
            ans = 0
            for j in range(i + 1, n):
                ans = max(ans, (s[j] - s[i]) / (j - i) + dfs(j, k - 1))
            return ans

        n = len(nums)
        s = list(accumulate(nums, initial=0))
        return dfs(0, k)
```

#### Java

```java
class Solution {
    private Double[][] f;
    private int[] s;
    private int n;

    public double largestSumOfAverages(int[] nums, int k) {
        n = nums.length;
        s = new int[n + 1];
        f = new Double[n][k + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        return dfs(0, k);
    }

    private double dfs(int i, int k) {
        if (i == n) {
            return 0;
        }
        if (k == 1) {
            return (s[n] - s[i]) * 1.0 / (n - i);
        }
        if (f[i][k] != null) {
            return f[i][k];
        }
        double ans = 0;
        for (int j = i + 1; j < n; ++j) {
            ans = Math.max(ans, (s[j] - s[i]) * 1.0 /(j - i) + dfs(j, k - 1));
        }
        return f[i][k] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    double largestSumOfAverages(vector<int>& nums, int k) {
        int n = nums.size();
        int s[n + 1];
        double f[n][k + 1];
        memset(f, 0, sizeof(f));
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        auto dfs = [&](auto&& dfs, int i, int k) -> double {
            if (i == n) {
                return 0;
            }
            if (k == 1) {
                return (s[n] - s[i]) * 1.0 / (n - i);
            }
            if (f[i][k] > 0) {
                return f[i][k];
            }
            double ans = 0;
            for (int j = i + 1; j < n; ++j) {
                ans = max(ans, (s[j] - s[i]) * 1.0 / (j - i) + dfs(dfs, j, k - 1));
            }
            return f[i][k] = ans;
        };
        return dfs(dfs, 0, k);
    }
};
```

#### Go

```go
func largestSumOfAverages(nums []int, k int) float64 {
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	f := make([][]float64, n)
	for i := range f {
		f[i] = make([]float64, k+1)
	}
	var dfs func(int, int) float64
	dfs = func(i, k int) float64 {
		if i == n {
			return 0
		}
		if f[i][k] > 0 {
			return f[i][k]
		}
		if k == 1 {
			return float64(s[n]-s[i]) / float64(n-i)
		}
		ans := 0.0
		for j := i + 1; j < n; j++ {
			ans = math.Max(ans, float64(s[j]-s[i])/float64(j-i)+dfs(j, k-1))
		}
		f[i][k] = ans
		return ans
	}
	return dfs(0, k)
}
```

#### TypeScript

```ts
function largestSumOfAverages(nums: number[], k: number): number {
    const n = nums.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; i++) {
        s[i + 1] = s[i] + nums[i];
    }
    const f: number[][] = Array.from({ length: n }, () => Array(k + 1).fill(0));
    const dfs = (i: number, k: number): number => {
        if (i === n) {
            return 0;
        }
        if (f[i][k] > 0) {
            return f[i][k];
        }
        if (k === 1) {
            return (s[n] - s[i]) / (n - i);
        }
        for (let j = i + 1; j < n; j++) {
            f[i][k] = Math.max(f[i][k], dfs(j, k - 1) + (s[j] - s[i]) / (j - i));
        }
        return f[i][k];
    };
    return dfs(0, k);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

We can transform the memoized search from Solution 1 into dynamic programming.

Define $f[i][j]$ to represent the maximum sum of averages when dividing the first $i$ elements of the array $\textit{nums}$ into at most $j$ groups. The answer is $f[n][k]$.

For $f[i][j]$, we can enumerate the end position $h$ of the previous group, calculate $f[h][j-1]$, add the result of $\frac{s[i] - s[h]}{i - h}$, and take the maximum value of all results.

The time complexity is $O(n^2 \times k)$, and the space complexity is $O(n \times k)$. Here, $n$ represents the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestSumOfAverages(self, nums: List[int], k: int) -> float:
        n = len(nums)
        f = [[0] * (k + 1) for _ in range(n + 1)]
        s = list(accumulate(nums, initial=0))
        for i in range(1, n + 1):
            f[i][1] = s[i] / i
            for j in range(2, min(i + 1, k + 1)):
                for h in range(i):
                    f[i][j] = max(f[i][j], f[h][j - 1] + (s[i] - s[h]) / (i - h))
        return f[n][k]
```

#### Java

```java
class Solution {
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[][] f = new double[n + 1][k + 1];
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        for (int i = 1; i <= n; ++i) {
            f[i][1] = s[i] * 1.0 / i;
            for (int j = 2; j <= Math.min(i, k); ++j) {
                for (int h = 0; h < i; ++h) {
                    f[i][j] = Math.max(f[i][j], f[h][j - 1] + (s[i] - s[h]) * 1.0 / (i - h));
                }
            }
        }
        return f[n][k];
    }
}
```

#### C++

```cpp
class Solution {
public:
    double largestSumOfAverages(vector<int>& nums, int k) {
        int n = nums.size();
        int s[n + 1];
        s[0] = 0;
        double f[n + 1][k + 1];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        for (int i = 1; i <= n; ++i) {
            f[i][1] = s[i] * 1.0 / i;
            for (int j = 2; j <= min(i, k); ++j) {
                for (int h = 0; h < i; ++h) {
                    f[i][j] = max(f[i][j], f[h][j - 1] + (s[i] - s[h]) * 1.0 / (i - h));
                }
            }
        }
        return f[n][k];
    }
};
```

#### Go

```go
func largestSumOfAverages(nums []int, k int) float64 {
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	f := make([][]float64, n+1)
	for i := range f {
		f[i] = make([]float64, k+1)
	}
	for i := 1; i <= n; i++ {
		f[i][1] = float64(s[i]) / float64(i)
		for j := 2; j <= min(i, k); j++ {
			for h := 0; h < i; h++ {
				f[i][j] = max(f[i][j], f[h][j-1]+float64(s[i]-s[h])/float64(i-h))
			}
		}
	}
	return f[n][k]
}
```

#### TypeScript

```ts
function largestSumOfAverages(nums: number[], k: number): number {
    const n = nums.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; i++) {
        s[i + 1] = s[i] + nums[i];
    }
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    for (let i = 1; i <= n; ++i) {
        f[i][1] = s[i] / i;
        for (let j = 2; j <= Math.min(i, k); ++j) {
            for (let h = 0; h < i; ++h) {
                f[i][j] = Math.max(f[i][j], f[h][j - 1] + (s[i] - s[h]) / (i - h));
            }
        }
    }
    return f[n][k];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
