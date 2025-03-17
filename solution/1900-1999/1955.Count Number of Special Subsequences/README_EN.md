---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1955.Count%20Number%20of%20Special%20Subsequences/README_EN.md
rating: 2125
source: Weekly Contest 252 Q4
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [1955. Count Number of Special Subsequences](https://leetcode.com/problems/count-number-of-special-subsequences)

[中文文档](/solution/1900-1999/1955.Count%20Number%20of%20Special%20Subsequences/README.md)

## Description

<!-- description:start -->

<p>A sequence is <strong>special</strong> if it consists of a <strong>positive</strong> number of <code>0</code>s, followed by a <strong>positive</strong> number of <code>1</code>s, then a <strong>positive</strong> number of <code>2</code>s.</p>

<ul>
	<li>For example, <code>[0,1,2]</code> and <code>[0,0,1,1,1,2]</code> are special.</li>
	<li>In contrast, <code>[2,1,0]</code>, <code>[1]</code>, and <code>[0,1,2,0]</code> are not special.</li>
</ul>

<p>Given an array <code>nums</code> (consisting of <strong>only</strong> integers <code>0</code>, <code>1</code>, and <code>2</code>), return<em> the <strong>number of different subsequences</strong> that are special</em>. Since the answer may be very large, <strong>return it modulo </strong><code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>subsequence</strong> of an array is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements. Two subsequences are <strong>different</strong> if the <strong>set of indices</strong> chosen are different.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,2,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The special subsequences are bolded [<strong><u>0</u></strong>,<strong><u>1</u></strong>,<strong><u>2</u></strong>,2], [<strong><u>0</u></strong>,<strong><u>1</u></strong>,2,<strong><u>2</u></strong>], and [<strong><u>0</u></strong>,<strong><u>1</u></strong>,<strong><u>2</u></strong>,<strong><u>2</u></strong>].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,0,0]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no special subsequences in [2,2,0,0].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,2,0,1,2]
<strong>Output:</strong> 7
<strong>Explanation:</strong> The special subsequences are bolded:
- [<strong><u>0</u></strong>,<strong><u>1</u></strong>,<strong><u>2</u></strong>,0,1,2]
- [<strong><u>0</u></strong>,<strong><u>1</u></strong>,2,0,1,<strong><u>2</u></strong>]
- [<strong><u>0</u></strong>,<strong><u>1</u></strong>,<strong><u>2</u></strong>,0,1,<strong><u>2</u></strong>]
- [<strong><u>0</u></strong>,<strong><u>1</u></strong>,2,0,<strong><u>1</u></strong>,<strong><u>2</u></strong>]
- [<strong><u>0</u></strong>,1,2,<strong><u>0</u></strong>,<strong><u>1</u></strong>,<strong><u>2</u></strong>]
- [<strong><u>0</u></strong>,1,2,0,<strong><u>1</u></strong>,<strong><u>2</u></strong>]
- [0,1,2,<strong><u>0</u></strong>,<strong><u>1</u></strong>,<strong><u>2</u></strong>]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 2</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j]$ to represent the number of special subsequences ending with $j$ among the first $i+1$ elements. Initially, $f[i][j]=0$, and if $nums[0]=0$, then $f[0][0]=1$.

For $i \gt 0$, we consider the value of $nums[i]$:

If $nums[i] = 0$: If we do not choose $nums[i]$, then $f[i][0] = f[i-1][0]$; if we choose $nums[i]$, then $f[i][0]=f[i-1][0]+1$, because we can add a $0$ to the end of any special subsequence ending with $0$ to get a new special subsequence, or we can use $nums[i]$ alone as a special subsequence. Therefore, $f[i][0] = 2 \times f[i - 1][0] + 1$. The rest of $f[i][j]$ is equal to $f[i-1][j]$.

If $nums[i] = 1$: If we do not choose $nums[i]$, then $f[i][1] = f[i-1][1]$; if we choose $nums[i]$, then $f[i][1]=f[i-1][1]+f[i-1][0]$, because we can add a $1$ to the end of any special subsequence ending with $0$ or $1$ to get a new special subsequence. Therefore, $f[i][1] = f[i-1][0] + 2 \times f[i - 1][1]$. The rest of $f[i][j]$ is equal to $f[i-1][j]$.

If $nums[i] = 2$: If we do not choose $nums[i]$, then $f[i][2] = f[i-1][2]$; if we choose $nums[i]$, then $f[i][2]=f[i-1][2]+f[i-1][1]$, because we can add a $2$ to the end of any special subsequence ending with $1$ or $2$ to get a new special subsequence. Therefore, $f[i][2] = f[i-1][1] + 2 \times f[i - 1][2]$. The rest of $f[i][j]$ is equal to $f[i-1][j]$.

In summary, we can get the following state transition equations:

$$
\begin{aligned}
f[i][0] &= 2 \times f[i - 1][0] + 1, \quad nums[i] = 0 \\
f[i][1] &= f[i-1][0] + 2 \times f[i - 1][1], \quad nums[i] = 1 \\
f[i][2] &= f[i-1][1] + 2 \times f[i - 1][2], \quad nums[i] = 2 \\
f[i][j] &= f[i-1][j], \quad nums[i] \neq j
\end{aligned}
$$

The final answer is $f[n-1][2]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $nums$.

Similar code found with 1 license type

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSpecialSubsequences(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        n = len(nums)
        f = [[0] * 3 for _ in range(n)]
        f[0][0] = nums[0] == 0
        for i in range(1, n):
            if nums[i] == 0:
                f[i][0] = (2 * f[i - 1][0] + 1) % mod
                f[i][1] = f[i - 1][1]
                f[i][2] = f[i - 1][2]
            elif nums[i] == 1:
                f[i][0] = f[i - 1][0]
                f[i][1] = (f[i - 1][0] + 2 * f[i - 1][1]) % mod
                f[i][2] = f[i - 1][2]
            else:
                f[i][0] = f[i - 1][0]
                f[i][1] = f[i - 1][1]
                f[i][2] = (f[i - 1][1] + 2 * f[i - 1][2]) % mod
        return f[n - 1][2]
```

#### Java

```java
class Solution {
    public int countSpecialSubsequences(int[] nums) {
        final int mod = (int) 1e9 + 7;
        int n = nums.length;
        int[][] f = new int[n][3];
        f[0][0] = nums[0] == 0 ? 1 : 0;
        for (int i = 1; i < n; ++i) {
            if (nums[i] == 0) {
                f[i][0] = (2 * f[i - 1][0] % mod + 1) % mod;
                f[i][1] = f[i - 1][1];
                f[i][2] = f[i - 1][2];
            } else if (nums[i] == 1) {
                f[i][0] = f[i - 1][0];
                f[i][1] = (f[i - 1][0] + 2 * f[i - 1][1] % mod) % mod;
                f[i][2] = f[i - 1][2];
            } else {
                f[i][0] = f[i - 1][0];
                f[i][1] = f[i - 1][1];
                f[i][2] = (f[i - 1][1] + 2 * f[i - 1][2] % mod) % mod;
            }
        }
        return f[n - 1][2];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countSpecialSubsequences(vector<int>& nums) {
        const int mod = 1e9 + 7;
        int n = nums.size();
        int f[n][3];
        memset(f, 0, sizeof(f));
        f[0][0] = nums[0] == 0;
        for (int i = 1; i < n; ++i) {
            if (nums[i] == 0) {
                f[i][0] = (2 * f[i - 1][0] % mod + 1) % mod;
                f[i][1] = f[i - 1][1];
                f[i][2] = f[i - 1][2];
            } else if (nums[i] == 1) {
                f[i][0] = f[i - 1][0];
                f[i][1] = (f[i - 1][0] + 2 * f[i - 1][1] % mod) % mod;
                f[i][2] = f[i - 1][2];
            } else {
                f[i][0] = f[i - 1][0];
                f[i][1] = f[i - 1][1];
                f[i][2] = (f[i - 1][1] + 2 * f[i - 1][2] % mod) % mod;
            }
        }
        return f[n - 1][2];
    }
};
```

#### Go

```go
func countSpecialSubsequences(nums []int) int {
	const mod = 1e9 + 7
	n := len(nums)
	f := make([][3]int, n)
	if nums[0] == 0 {
		f[0][0] = 1
	}
	for i := 1; i < n; i++ {
		if nums[i] == 0 {
			f[i][0] = (2*f[i-1][0] + 1) % mod
			f[i][1] = f[i-1][1]
			f[i][2] = f[i-1][2]
		} else if nums[i] == 1 {
			f[i][0] = f[i-1][0]
			f[i][1] = (f[i-1][0] + 2*f[i-1][1]) % mod
			f[i][2] = f[i-1][2]
		} else {
			f[i][0] = f[i-1][0]
			f[i][1] = f[i-1][1]
			f[i][2] = (f[i-1][1] + 2*f[i-1][2]) % mod
		}
	}
	return f[n-1][2]
}
```

#### TypeScript

```ts
function countSpecialSubsequences(nums: number[]): number {
    const mod = 1e9 + 7;
    const n = nums.length;
    const f: number[][] = Array(n)
        .fill(0)
        .map(() => Array(3).fill(0));
    f[0][0] = nums[0] === 0 ? 1 : 0;
    for (let i = 1; i < n; ++i) {
        if (nums[i] === 0) {
            f[i][0] = (((2 * f[i - 1][0]) % mod) + 1) % mod;
            f[i][1] = f[i - 1][1];
            f[i][2] = f[i - 1][2];
        } else if (nums[i] === 1) {
            f[i][0] = f[i - 1][0];
            f[i][1] = (f[i - 1][0] + ((2 * f[i - 1][1]) % mod)) % mod;
            f[i][2] = f[i - 1][2];
        } else {
            f[i][0] = f[i - 1][0];
            f[i][1] = f[i - 1][1];
            f[i][2] = (f[i - 1][1] + ((2 * f[i - 1][2]) % mod)) % mod;
        }
    }
    return f[n - 1][2];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming (Space Optimization)

We notice that in the above state transition equations, the value of $f[i][j]$ is only related to $f[i-1][j]$. Therefore, we can remove the first dimension and optimize the space complexity to $O(1)$.

We can use an array $f$ of length 3 to represent the number of special subsequences ending with 0, 1, and 2, respectively. For each element in the array, we update the array $f$ according to the value of the current element.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSpecialSubsequences(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        n = len(nums)
        f = [0] * 3
        f[0] = nums[0] == 0
        for i in range(1, n):
            if nums[i] == 0:
                f[0] = (2 * f[0] + 1) % mod
            elif nums[i] == 1:
                f[1] = (f[0] + 2 * f[1]) % mod
            else:
                f[2] = (f[1] + 2 * f[2]) % mod
        return f[2]
```

#### Java

```java
class Solution {
    public int countSpecialSubsequences(int[] nums) {
        final int mod = (int) 1e9 + 7;
        int n = nums.length;
        int[] f = new int[3];
        f[0] = nums[0] == 0 ? 1 : 0;
        for (int i = 1; i < n; ++i) {
            if (nums[i] == 0) {
                f[0] = (2 * f[0] % mod + 1) % mod;
            } else if (nums[i] == 1) {
                f[1] = (f[0] + 2 * f[1] % mod) % mod;
            } else {
                f[2] = (f[1] + 2 * f[2] % mod) % mod;
            }
        }
        return f[2];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countSpecialSubsequences(vector<int>& nums) {
        const int mod = 1e9 + 7;
        int n = nums.size();
        int f[3]{0};
        f[0] = nums[0] == 0;
        for (int i = 1; i < n; ++i) {
            if (nums[i] == 0) {
                f[0] = (2 * f[0] % mod + 1) % mod;
            } else if (nums[i] == 1) {
                f[1] = (f[0] + 2 * f[1] % mod) % mod;
            } else {
                f[2] = (f[1] + 2 * f[2] % mod) % mod;
            }
        }
        return f[2];
    }
};
```

#### Go

```go
func countSpecialSubsequences(nums []int) int {
	const mod = 1e9 + 7
	n := len(nums)
	f := [3]int{}
	if nums[0] == 0 {
		f[0] = 1
	}
	for i := 1; i < n; i++ {
		if nums[i] == 0 {
			f[0] = (2*f[0] + 1) % mod
		} else if nums[i] == 1 {
			f[1] = (f[0] + 2*f[1]) % mod
		} else {
			f[2] = (f[1] + 2*f[2]) % mod
		}
	}
	return f[2]
}
```

#### TypeScript

```ts
function countSpecialSubsequences(nums: number[]): number {
    const mod = 1e9 + 7;
    const n = nums.length;
    const f: number[] = [0, 0, 0];
    f[0] = nums[0] === 0 ? 1 : 0;
    for (let i = 1; i < n; ++i) {
        if (nums[i] === 0) {
            f[0] = (((2 * f[0]) % mod) + 1) % mod;
            f[1] = f[1];
            f[2] = f[2];
        } else if (nums[i] === 1) {
            f[0] = f[0];
            f[1] = (f[0] + ((2 * f[1]) % mod)) % mod;
            f[2] = f[2];
        } else {
            f[0] = f[0];
            f[1] = f[1];
            f[2] = (f[1] + ((2 * f[2]) % mod)) % mod;
        }
    }
    return f[2];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
