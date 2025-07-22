---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3176.Find%20the%20Maximum%20Length%20of%20a%20Good%20Subsequence%20I/README_EN.md
rating: 1849
source: Biweekly Contest 132 Q3
tags:
    - Array
    - Hash Table
    - Dynamic Programming
---

<!-- problem:start -->

# [3176. Find the Maximum Length of a Good Subsequence I](https://leetcode.com/problems/find-the-maximum-length-of-a-good-subsequence-i)

[中文文档](/solution/3100-3199/3176.Find%20the%20Maximum%20Length%20of%20a%20Good%20Subsequence%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and a <strong>non-negative</strong> integer <code>k</code>. A sequence of integers <code>seq</code> is called <strong>good</strong> if there are <strong>at most</strong> <code>k</code> indices <code>i</code> in the range <code>[0, seq.length - 2]</code> such that <code>seq[i] != seq[i + 1]</code>.</p>

<p>Return the <strong>maximum</strong> possible length of a <strong>good</strong> <span data-keyword="subsequence-array">subsequence</span> of <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,1,1,3], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum length subsequence is <code>[<u>1</u>,<u>2</u>,<u>1</u>,<u>1</u>,3]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5,1], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum length subsequence is <code>[<u>1</u>,2,3,4,5,<u>1</u>]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 500</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt;= min(nums.length, 25)</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][h]$ as the length of the longest good subsequence ending with $nums[i]$ and having no more than $h$ indices satisfying the condition. Initially, $f[i][h] = 1$. The answer is $\max(f[i][k])$, where $0 \le i < n$.

We consider how to calculate $f[i][h]$. We can enumerate $0 \le j < i$, if $nums[i] = nums[j]$, then $f[i][h] = \max(f[i][h], f[j][h] + 1)$; otherwise, if $h > 0$, then $f[i][h] = \max(f[i][h], f[j][h - 1] + 1)$. That is:

$$
f[i][h]=
\begin{cases}
\max(f[i][h], f[j][h] + 1), & \textit{if } nums[i] = nums[j], \\
\max(f[i][h], f[j][h - 1] + 1), & \textit{if } h > 0.
\end{cases}
$$

The final answer is $\max(f[i][k])$, where $0 \le i < n$.

The time complexity is $O(n^2 \times k)$, and the space complexity is $O(n \times k)$. Where $n$ is the length of the array `nums`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumLength(self, nums: List[int], k: int) -> int:
        n = len(nums)
        f = [[1] * (k + 1) for _ in range(n)]
        ans = 0
        for i, x in enumerate(nums):
            for h in range(k + 1):
                for j, y in enumerate(nums[:i]):
                    if x == y:
                        f[i][h] = max(f[i][h], f[j][h] + 1)
                    elif h:
                        f[i][h] = max(f[i][h], f[j][h - 1] + 1)
            ans = max(ans, f[i][k])
        return ans
```

#### Java

```java
class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] f = new int[n][k + 1];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int h = 0; h <= k; ++h) {
                for (int j = 0; j < i; ++j) {
                    if (nums[i] == nums[j]) {
                        f[i][h] = Math.max(f[i][h], f[j][h]);
                    } else if (h > 0) {
                        f[i][h] = Math.max(f[i][h], f[j][h - 1]);
                    }
                }
                ++f[i][h];
            }
            ans = Math.max(ans, f[i][k]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumLength(vector<int>& nums, int k) {
        int n = nums.size();
        int f[n][k + 1];
        memset(f, 0, sizeof(f));
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int h = 0; h <= k; ++h) {
                for (int j = 0; j < i; ++j) {
                    if (nums[i] == nums[j]) {
                        f[i][h] = max(f[i][h], f[j][h]);
                    } else if (h) {
                        f[i][h] = max(f[i][h], f[j][h - 1]);
                    }
                }
                ++f[i][h];
            }
            ans = max(ans, f[i][k]);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumLength(nums []int, k int) (ans int) {
	f := make([][]int, len(nums))
	for i := range f {
		f[i] = make([]int, k+1)
	}
	for i, x := range nums {
		for h := 0; h <= k; h++ {
			for j, y := range nums[:i] {
				if x == y {
					f[i][h] = max(f[i][h], f[j][h])
				} else if h > 0 {
					f[i][h] = max(f[i][h], f[j][h-1])
				}
			}
			f[i][h]++
		}
		ans = max(ans, f[i][k])
	}
	return
}
```

#### TypeScript

```ts
function maximumLength(nums: number[], k: number): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array(k + 1).fill(0));
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        for (let h = 0; h <= k; ++h) {
            for (let j = 0; j < i; ++j) {
                if (nums[i] === nums[j]) {
                    f[i][h] = Math.max(f[i][h], f[j][h]);
                } else if (h) {
                    f[i][h] = Math.max(f[i][h], f[j][h - 1]);
                }
            }
            ++f[i][h];
        }
        ans = Math.max(ans, f[i][k]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Optimized Dynamic Programming

According to the state transition equation in Solution 1, if $nums[i] = nums[j]$, then we only need to get the maximum value of $f[j][h]$. We can maintain this with an array $mp$ of length $k + 1$. If $nums[i] \neq nums[j]$, we need to record the maximum value of $f[j][h - 1]$ corresponding to $nums[j]$, the maximum value and the second maximum value. We can maintain these with an array $g$ of length $k + 1$.

The time complexity is $O(n \times k)$, and the space complexity is $O(n \times k)$. Where $n$ is the length of the array `nums`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumLength(self, nums: List[int], k: int) -> int:
        n = len(nums)
        f = [[0] * (k + 1) for _ in range(n)]
        mp = [defaultdict(int) for _ in range(k + 1)]
        g = [[0] * 3 for _ in range(k + 1)]
        ans = 0
        for i, x in enumerate(nums):
            for h in range(k + 1):
                f[i][h] = mp[h][x]
                if h:
                    if g[h - 1][0] != nums[i]:
                        f[i][h] = max(f[i][h], g[h - 1][1])
                    else:
                        f[i][h] = max(f[i][h], g[h - 1][2])
                f[i][h] += 1
                mp[h][nums[i]] = max(mp[h][nums[i]], f[i][h])
                if g[h][0] != x:
                    if f[i][h] >= g[h][1]:
                        g[h][2] = g[h][1]
                        g[h][1] = f[i][h]
                        g[h][0] = x
                    else:
                        g[h][2] = max(g[h][2], f[i][h])
                else:
                    g[h][1] = max(g[h][1], f[i][h])
                ans = max(ans, f[i][h])
        return ans
```

#### Java

```java
class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] f = new int[n][k + 1];
        Map<Integer, Integer>[] mp = new HashMap[k + 1];
        Arrays.setAll(mp, i -> new HashMap<>());
        int[][] g = new int[k + 1][3];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int h = 0; h <= k; ++h) {
                f[i][h] = mp[h].getOrDefault(nums[i], 0);
                if (h > 0) {
                    if (g[h - 1][0] != nums[i]) {
                        f[i][h] = Math.max(f[i][h], g[h - 1][1]);
                    } else {
                        f[i][h] = Math.max(f[i][h], g[h - 1][2]);
                    }
                }
                ++f[i][h];
                mp[h].merge(nums[i], f[i][h], Integer::max);
                if (g[h][0] != nums[i]) {
                    if (f[i][h] >= g[h][1]) {
                        g[h][2] = g[h][1];
                        g[h][1] = f[i][h];
                        g[h][0] = nums[i];
                    } else {
                        g[h][2] = Math.max(g[h][2], f[i][h]);
                    }
                } else {
                    g[h][1] = Math.max(g[h][1], f[i][h]);
                }
                ans = Math.max(ans, f[i][h]);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumLength(vector<int>& nums, int k) {
        int n = nums.size();
        vector<vector<int>> f(n, vector<int>(k + 1));
        vector<unordered_map<int, int>> mp(k + 1);
        vector<vector<int>> g(k + 1, vector<int>(3));
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int h = 0; h <= k; ++h) {
                f[i][h] = mp[h][nums[i]];
                if (h > 0) {
                    if (g[h - 1][0] != nums[i]) {
                        f[i][h] = max(f[i][h], g[h - 1][1]);
                    } else {
                        f[i][h] = max(f[i][h], g[h - 1][2]);
                    }
                }
                ++f[i][h];
                mp[h][nums[i]] = max(mp[h][nums[i]], f[i][h]);
                if (g[h][0] != nums[i]) {
                    if (f[i][h] >= g[h][1]) {
                        g[h][2] = g[h][1];
                        g[h][1] = f[i][h];
                        g[h][0] = nums[i];
                    } else {
                        g[h][2] = max(g[h][2], f[i][h]);
                    }
                } else {
                    g[h][1] = max(g[h][1], f[i][h]);
                }
                ans = max(ans, f[i][h]);
            }
        }

        return ans;
    }
};
```

#### Go

```go
func maximumLength(nums []int, k int) int {
	n := len(nums)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	mp := make([]map[int]int, k+1)
	for i := range mp {
		mp[i] = make(map[int]int)
	}
	g := make([][3]int, k+1)
	ans := 0

	for i := 0; i < n; i++ {
		for h := 0; h <= k; h++ {
			f[i][h] = mp[h][nums[i]]
			if h > 0 {
				if g[h-1][0] != nums[i] {
					if g[h-1][1] > f[i][h] {
						f[i][h] = g[h-1][1]
					}
				} else {
					if g[h-1][2] > f[i][h] {
						f[i][h] = g[h-1][2]
					}
				}
			}
			f[i][h]++
			if f[i][h] > mp[h][nums[i]] {
				mp[h][nums[i]] = f[i][h]
			}
			if g[h][0] != nums[i] {
				if f[i][h] >= g[h][1] {
					g[h][2] = g[h][1]
					g[h][1] = f[i][h]
					g[h][0] = nums[i]
				} else if f[i][h] > g[h][2] {
					g[h][2] = f[i][h]
				}
			} else {
				if f[i][h] > g[h][1] {
					g[h][1] = f[i][h]
				}
			}
			if f[i][h] > ans {
				ans = f[i][h]
			}
		}
	}

	return ans
}
```

#### TypeScript

```ts
function maximumLength(nums: number[], k: number): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array(k + 1).fill(0));
    const mp: Map<number, number>[] = Array.from({ length: k + 1 }, () => new Map());
    const g: number[][] = Array.from({ length: k + 1 }, () => Array(3).fill(0));
    let ans = 0;

    for (let i = 0; i < n; i++) {
        for (let h = 0; h <= k; h++) {
            f[i][h] = mp[h].get(nums[i]) || 0;
            if (h > 0) {
                if (g[h - 1][0] !== nums[i]) {
                    f[i][h] = Math.max(f[i][h], g[h - 1][1]);
                } else {
                    f[i][h] = Math.max(f[i][h], g[h - 1][2]);
                }
            }
            f[i][h]++;
            mp[h].set(nums[i], Math.max(mp[h].get(nums[i]) || 0, f[i][h]));
            if (g[h][0] !== nums[i]) {
                if (f[i][h] >= g[h][1]) {
                    g[h][2] = g[h][1];
                    g[h][1] = f[i][h];
                    g[h][0] = nums[i];
                } else {
                    g[h][2] = Math.max(g[h][2], f[i][h]);
                }
            } else {
                g[h][1] = Math.max(g[h][1], f[i][h]);
            }
            ans = Math.max(ans, f[i][h]);
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
