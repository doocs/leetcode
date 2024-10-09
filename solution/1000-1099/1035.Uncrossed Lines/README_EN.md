---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1035.Uncrossed%20Lines/README_EN.md
rating: 1805
source: Weekly Contest 134 Q3
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [1035. Uncrossed Lines](https://leetcode.com/problems/uncrossed-lines)

[中文文档](/solution/1000-1099/1035.Uncrossed%20Lines/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>nums1</code> and <code>nums2</code>. We write the integers of <code>nums1</code> and <code>nums2</code> (in the order they are given) on two separate horizontal lines.</p>

<p>We may draw connecting lines: a straight line connecting two numbers <code>nums1[i]</code> and <code>nums2[j]</code> such that:</p>

<ul>
	<li><code>nums1[i] == nums2[j]</code>, and</li>
	<li>the line we draw does not intersect any other connecting (non-horizontal) line.</li>
</ul>

<p>Note that a connecting line cannot intersect even at the endpoints (i.e., each number can only belong to one connecting line).</p>

<p>Return <em>the maximum number of connecting lines we can draw in this way</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1035.Uncrossed%20Lines/images/142.png" style="width: 400px; height: 286px;" />
<pre>
<strong>Input:</strong> nums1 = [1,4,2], nums2 = [1,2,4]
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can draw 2 uncrossed lines as in the diagram.
We cannot draw 3 uncrossed lines, because the line from nums1[1] = 4 to nums2[2] = 4 will intersect the line from nums1[2]=2 to nums2[1]=2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
<strong>Output:</strong> 3
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 500</code></li>
	<li><code>1 &lt;= nums1[i], nums2[j] &lt;= 2000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j]$ to represent the maximum number of connections between the first $i$ numbers of $\textit{nums1}$ and the first $j$ numbers of $\textit{nums2}$. Initially, $f[i][j] = 0$, and the answer is $f[m][n]$.

When $\textit{nums1}[i-1] = \textit{nums2}[j-1]$, we can add a connection based on the first $i-1$ numbers of $\textit{nums1}$ and the first $j-1$ numbers of $\textit{nums2}$. In this case, $f[i][j] = f[i-1][j-1] + 1$.

When $\textit{nums1}[i-1] \neq \textit{nums2}[j-1]$, we either solve based on the first $i-1$ numbers of $\textit{nums1}$ and the first $j$ numbers of $\textit{nums2}$, or solve based on the first $i$ numbers of $\textit{nums1}$ and the first $j-1$ numbers of $\textit{nums2}$, taking the maximum of the two. That is, $f[i][j] = \max(f[i-1][j], f[i][j-1])$.

Finally, return $f[m][n]$.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the lengths of $\textit{nums1}$ and $\textit{nums2}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxUncrossedLines(self, nums1: List[int], nums2: List[int]) -> int:
        m, n = len(nums1), len(nums2)
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for i, x in enumerate(nums1, 1):
            for j, y in enumerate(nums2, 1):
                if x == y:
                    f[i][j] = f[i - 1][j - 1] + 1
                else:
                    f[i][j] = max(f[i - 1][j], f[i][j - 1])
        return f[m][n]
```

#### Java

```java
class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] f = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }
        return f[m][n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxUncrossedLines(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        int f[m + 1][n + 1];
        memset(f, 0, sizeof(f));
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }
        return f[m][n];
    }
};
```

#### Go

```go
func maxUncrossedLines(nums1 []int, nums2 []int) int {
	m, n := len(nums1), len(nums2)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if nums1[i-1] == nums2[j-1] {
				f[i][j] = f[i-1][j-1] + 1
			} else {
				f[i][j] = max(f[i-1][j], f[i][j-1])
			}
		}
	}
	return f[m][n]
}
```

#### TypeScript

```ts
function maxUncrossedLines(nums1: number[], nums2: number[]): number {
    const m = nums1.length;
    const n = nums2.length;
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            if (nums1[i - 1] === nums2[j - 1]) {
                f[i][j] = f[i - 1][j - 1] + 1;
            } else {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
            }
        }
    }
    return f[m][n];
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
var maxUncrossedLines = function (nums1, nums2) {
    const m = nums1.length;
    const n = nums2.length;
    const f = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            if (nums1[i - 1] === nums2[j - 1]) {
                f[i][j] = f[i - 1][j - 1] + 1;
            } else {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
            }
        }
    }
    return f[m][n];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
