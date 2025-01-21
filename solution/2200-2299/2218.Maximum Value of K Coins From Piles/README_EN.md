---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2218.Maximum%20Value%20of%20K%20Coins%20From%20Piles/README_EN.md
rating: 2157
source: Weekly Contest 286 Q4
tags:
    - Array
    - Dynamic Programming
    - Prefix Sum
---

<!-- problem:start -->

# [2218. Maximum Value of K Coins From Piles](https://leetcode.com/problems/maximum-value-of-k-coins-from-piles)

[中文文档](/solution/2200-2299/2218.Maximum%20Value%20of%20K%20Coins%20From%20Piles/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> <strong>piles</strong> of coins on a table. Each pile consists of a <strong>positive number</strong> of coins of assorted denominations.</p>

<p>In one move, you can choose any coin on <strong>top</strong> of any pile, remove it, and add it to your wallet.</p>

<p>Given a list <code>piles</code>, where <code>piles[i]</code> is a list of integers denoting the composition of the <code>i<sup>th</sup></code> pile from <strong>top to bottom</strong>, and a positive integer <code>k</code>, return <em>the <strong>maximum total value</strong> of coins you can have in your wallet if you choose <strong>exactly</strong></em> <code>k</code> <em>coins optimally</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2218.Maximum%20Value%20of%20K%20Coins%20From%20Piles/images/e1.png" style="width: 600px; height: 243px;" />
<pre>
<strong>Input:</strong> piles = [[1,100,3],[7,8,9]], k = 2
<strong>Output:</strong> 101
<strong>Explanation:</strong>
The above diagram shows the different ways we can choose k coins.
The maximum total we can obtain is 101.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
<strong>Output:</strong> 706
<strong>Explanation:
</strong>The maximum total can be obtained if we choose all coins from the last pile.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == piles.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= piles[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= sum(piles[i].length) &lt;= 2000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming (Grouped Knapsack)

We define $f[i][j]$ as the maximum value sum of taking $j$ coins from the first $i$ piles. The answer is $f[n][k]$, where $n$ is the number of piles.

For the $i$-th pile, we can choose to take the first $0$, $1$, $2$, $\cdots$, $k$ coins. We can use a prefix sum array $s$ to quickly calculate the value sum of taking the first $h$ coins.

The state transition equation is:

$$
f[i][j] = \max(f[i][j], f[i - 1][j - h] + s[h])
$$

where $0 \leq h \leq j$, and $s[h]$ represents the value sum of taking the first $h$ coins from the $i$-th pile.

The time complexity is $O(k \times L)$, and the space complexity is $O(n \times k)$. Here, $L$ is the total number of coins, and $n$ is the number of piles.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxValueOfCoins(self, piles: List[List[int]], k: int) -> int:
        n = len(piles)
        f = [[0] * (k + 1) for _ in range(n + 1)]
        for i, nums in enumerate(piles, 1):
            s = list(accumulate(nums, initial=0))
            for j in range(k + 1):
                for h, w in enumerate(s):
                    if j < h:
                        break
                    f[i][j] = max(f[i][j], f[i - 1][j - h] + w)
        return f[n][k]
```

#### Java

```java
class Solution {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int n = piles.size();
        int[][] f = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            List<Integer> nums = piles.get(i - 1);
            int[] s = new int[nums.size() + 1];
            s[0] = 0;
            for (int j = 1; j <= nums.size(); j++) {
                s[j] = s[j - 1] + nums.get(j - 1);
            }
            for (int j = 0; j <= k; j++) {
                for (int h = 0; h < s.length && h <= j; h++) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - h] + s[h]);
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
    int maxValueOfCoins(vector<vector<int>>& piles, int k) {
        int n = piles.size();
        vector<vector<int>> f(n + 1, vector<int>(k + 1));
        for (int i = 1; i <= n; i++) {
            vector<int> nums = piles[i - 1];
            vector<int> s(nums.size() + 1);
            for (int j = 1; j <= nums.size(); j++) {
                s[j] = s[j - 1] + nums[j - 1];
            }
            for (int j = 0; j <= k; j++) {
                for (int h = 0; h < s.size() && h <= j; h++) {
                    f[i][j] = max(f[i][j], f[i - 1][j - h] + s[h]);
                }
            }
        }
        return f[n][k];
    }
};
```

#### Go

```go
func maxValueOfCoins(piles [][]int, k int) int {
	n := len(piles)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	for i := 1; i <= n; i++ {
		nums := piles[i-1]
		s := make([]int, len(nums)+1)
		for j := 1; j <= len(nums); j++ {
			s[j] = s[j-1] + nums[j-1]
		}

		for j := 0; j <= k; j++ {
			for h, w := range s {
				if j < h {
					break
				}
				f[i][j] = max(f[i][j], f[i-1][j-h]+w)
			}
		}
	}
	return f[n][k]
}
```

#### TypeScript

```ts
function maxValueOfCoins(piles: number[][], k: number): number {
    const n = piles.length;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    for (let i = 1; i <= n; i++) {
        const nums = piles[i - 1];
        const s = Array(nums.length + 1).fill(0);
        for (let j = 1; j <= nums.length; j++) {
            s[j] = s[j - 1] + nums[j - 1];
        }
        for (let j = 0; j <= k; j++) {
            for (let h = 0; h < s.length && h <= j; h++) {
                f[i][j] = Math.max(f[i][j], f[i - 1][j - h] + s[h]);
            }
        }
    }
    return f[n][k];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming (Space Optimization)

We can observe that for the $i$-th pile, we only need to use $f[i - 1][j]$ and $f[i][j - h]$, so we can optimize the two-dimensional array to a one-dimensional array.

The time complexity is $O(k \times L)$, and the space complexity is $O(k)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxValueOfCoins(self, piles: List[List[int]], k: int) -> int:
        f = [0] * (k + 1)
        for nums in piles:
            s = list(accumulate(nums, initial=0))
            for j in range(k, -1, -1):
                for h, w in enumerate(s):
                    if j < h:
                        break
                    f[j] = max(f[j], f[j - h] + w)
        return f[k]
```

#### Java

```java
class Solution {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int[] f = new int[k + 1];
        for (var nums : piles) {
            int[] s = new int[nums.size() + 1];
            for (int j = 1; j <= nums.size(); ++j) {
                s[j] = s[j - 1] + nums.get(j - 1);
            }
            for (int j = k; j >= 0; --j) {
                for (int h = 0; h < s.length && h <= j; ++h) {
                    f[j] = Math.max(f[j], f[j - h] + s[h]);
                }
            }
        }
        return f[k];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxValueOfCoins(vector<vector<int>>& piles, int k) {
        vector<int> f(k + 1);
        for (auto& nums : piles) {
            vector<int> s(nums.size() + 1);
            for (int j = 1; j <= nums.size(); ++j) {
                s[j] = s[j - 1] + nums[j - 1];
            }
            for (int j = k; j >= 0; --j) {
                for (int h = 0; h < s.size() && h <= j; ++h) {
                    f[j] = max(f[j], f[j - h] + s[h]);
                }
            }
        }
        return f[k];
    }
};
```

#### Go

```go
func maxValueOfCoins(piles [][]int, k int) int {
	f := make([]int, k+1)
	for _, nums := range piles {
		s := make([]int, len(nums)+1)
		for j := 1; j <= len(nums); j++ {
			s[j] = s[j-1] + nums[j-1]
		}
		for j := k; j >= 0; j-- {
			for h := 0; h < len(s) && h <= j; h++ {
				f[j] = max(f[j], f[j-h]+s[h])
			}
		}
	}
	return f[k]
}
```

#### TypeScript

```ts
function maxValueOfCoins(piles: number[][], k: number): number {
    const f: number[] = Array(k + 1).fill(0);
    for (const nums of piles) {
        const s: number[] = Array(nums.length + 1).fill(0);
        for (let j = 1; j <= nums.length; j++) {
            s[j] = s[j - 1] + nums[j - 1];
        }
        for (let j = k; j >= 0; j--) {
            for (let h = 0; h < s.length && h <= j; h++) {
                f[j] = Math.max(f[j], f[j - h] + s[h]);
            }
        }
    }
    return f[k];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
