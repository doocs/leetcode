---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3269.Constructing%20Two%20Increasing%20Arrays/README_EN.md
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3269. Constructing Two Increasing Arrays 🔒](https://leetcode.com/problems/constructing-two-increasing-arrays)

[中文文档](/solution/3200-3299/3269.Constructing%20Two%20Increasing%20Arrays/README.md)

## Description

<!-- description:start -->

<p>Given 2 integer arrays <code>nums1</code> and <code>nums2</code> consisting only of 0 and 1, your task is to calculate the <strong>minimum</strong> possible <strong>largest</strong> number in arrays <code>nums1</code> and <code>nums2</code>, after doing the following.</p>

<p>Replace every 0 with an <em>even positive integer</em> and every 1 with an <em>odd positive integer</em>. After replacement, both arrays should be <strong>increasing</strong> and each integer should be used <strong>at most</strong> once.</p>

<p>Return the <em>minimum possible largest number</em> after applying the changes.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [], nums2 = [1,0,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>After replacing, <code>nums1 = []</code>, and <code>nums2 = [1, 2, 3, 5]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [0,1,0,1], nums2 = [1,0,0,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<p>One way to replace, having 9 as the largest element is <code>nums1 = [2, 3, 8, 9]</code>, and <code>nums2 = [1, 4, 6, 7]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [0,1,0,0,1], nums2 = [0,0,0,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">13</span></p>

<p><strong>Explanation:</strong></p>

<p>One way to replace, having 13 as the largest element is <code>nums1 = [2, 3, 4, 6, 7]</code>, and <code>nums2 = [8, 10, 12, 13]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums1.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums2.length &lt;= 1000</code></li>
	<li><code>nums1</code> and <code>nums2</code> consist only of 0 and 1.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j]$ to represent the minimum of the maximum values among the first $i$ elements of array $\textit{nums1}$ and the first $j$ elements of array $\textit{nums2}$. Initially, $f[i][j] = 0$, and the answer is $f[m][n]$, where $m$ and $n$ are the lengths of arrays $\textit{nums1}$ and $\textit{nums2}$, respectively.

If $j = 0$, then the value of $f[i][0]$ can only be derived from $f[i - 1][0]$, with the transition equation $f[i][0] = \textit{nxt}(f[i - 1][0], \textit{nums1}[i - 1])$, where $\textit{nxt}(x, y)$ represents the smallest integer greater than $x$ that has the same parity as $y$.

If $i = 0$, then the value of $f[0][j]$ can only be derived from $f[0][j - 1]$, with the transition equation $f[0][j] = \textit{nxt}(f[0][j - 1], \textit{nums2}[j - 1])$.

If $i > 0$ and $j > 0$, then the value of $f[i][j]$ can be derived from both $f[i - 1][j]$ and $f[i][j - 1]$, with the transition equation $f[i][j] = \min(\textit{nxt}(f[i - 1][j], \textit{nums1}[i - 1]), \textit{nxt}(f[i][j - 1], \textit{nums2}[j - 1]))$.

Finally, return $f[m][n]$.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the lengths of arrays $\textit{nums1}$ and $\textit{nums2}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minLargest(self, nums1: List[int], nums2: List[int]) -> int:
        def nxt(x: int, y: int) -> int:
            return x + 1 if (x & 1 ^ y) == 1 else x + 2

        m, n = len(nums1), len(nums2)
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for i, x in enumerate(nums1, 1):
            f[i][0] = nxt(f[i - 1][0], x)
        for j, y in enumerate(nums2, 1):
            f[0][j] = nxt(f[0][j - 1], y)
        for i, x in enumerate(nums1, 1):
            for j, y in enumerate(nums2, 1):
                f[i][j] = min(nxt(f[i - 1][j], x), nxt(f[i][j - 1], y))
        return f[m][n]
```

#### Java

```java
class Solution {
    public int minLargest(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] f = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            f[i][0] = nxt(f[i - 1][0], nums1[i - 1]);
        }
        for (int j = 1; j <= n; ++j) {
            f[0][j] = nxt(f[0][j - 1], nums2[j - 1]);
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int x = nxt(f[i - 1][j], nums1[i - 1]);
                int y = nxt(f[i][j - 1], nums2[j - 1]);
                f[i][j] = Math.min(x, y);
            }
        }
        return f[m][n];
    }

    private int nxt(int x, int y) {
        return (x & 1 ^ y) == 1 ? x + 1 : x + 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minLargest(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        int f[m + 1][n + 1];
        memset(f, 0, sizeof(f));
        auto nxt = [](int x, int y) -> int {
            return (x & 1 ^ y) == 1 ? x + 1 : x + 2;
        };
        for (int i = 1; i <= m; ++i) {
            f[i][0] = nxt(f[i - 1][0], nums1[i - 1]);
        }
        for (int j = 1; j <= n; ++j) {
            f[0][j] = nxt(f[0][j - 1], nums2[j - 1]);
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int x = nxt(f[i - 1][j], nums1[i - 1]);
                int y = nxt(f[i][j - 1], nums2[j - 1]);
                f[i][j] = min(x, y);
            }
        }
        return f[m][n];
    }
};
```

#### Go

```go
func minLargest(nums1 []int, nums2 []int) int {
	m, n := len(nums1), len(nums2)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	nxt := func(x, y int) int {
		if (x&1 ^ y) == 1 {
			return x + 1
		}
		return x + 2
	}
	for i := 1; i <= m; i++ {
		f[i][0] = nxt(f[i-1][0], nums1[i-1])
	}
	for j := 1; j <= n; j++ {
		f[0][j] = nxt(f[0][j-1], nums2[j-1])
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			x := nxt(f[i-1][j], nums1[i-1])
			y := nxt(f[i][j-1], nums2[j-1])
			f[i][j] = min(x, y)
		}
	}
	return f[m][n]
}
```

#### TypeScript

```ts
function minLargest(nums1: number[], nums2: number[]): number {
    const m = nums1.length;
    const n = nums2.length;
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    const nxt = (x: number, y: number): number => {
        return (x & 1) ^ y ? x + 1 : x + 2;
    };
    for (let i = 1; i <= m; ++i) {
        f[i][0] = nxt(f[i - 1][0], nums1[i - 1]);
    }
    for (let j = 1; j <= n; ++j) {
        f[0][j] = nxt(f[0][j - 1], nums2[j - 1]);
    }
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            f[i][j] = Math.min(nxt(f[i - 1][j], nums1[i - 1]), nxt(f[i][j - 1], nums2[j - 1]));
        }
    }
    return f[m][n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
