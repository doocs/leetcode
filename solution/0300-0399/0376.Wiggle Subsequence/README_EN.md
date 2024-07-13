---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0376.Wiggle%20Subsequence/README_EN.md
tags:
    - Greedy
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [376. Wiggle Subsequence](https://leetcode.com/problems/wiggle-subsequence)

[中文文档](/solution/0300-0399/0376.Wiggle%20Subsequence/README.md)

## Description

<!-- description:start -->

<p>A <strong>wiggle sequence</strong> is a sequence where the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with one element and a sequence with two non-equal elements are trivially wiggle sequences.</p>

<ul>
	<li>For example, <code>[1, 7, 4, 9, 2, 5]</code> is a <strong>wiggle sequence</strong> because the differences <code>(6, -3, 5, -7, 3)</code> alternate between positive and negative.</li>
	<li>In contrast, <code>[1, 4, 7, 2, 5]</code> and <code>[1, 7, 4, 5, 5]</code> are not wiggle sequences. The first is not because its first two differences are positive, and the second is not because its last difference is zero.</li>
</ul>

<p>A <strong>subsequence</strong> is obtained by deleting some elements (possibly zero) from the original sequence, leaving the remaining elements in their original order.</p>

<p>Given an integer array <code>nums</code>, return <em>the length of the longest <strong>wiggle subsequence</strong> of </em><code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,7,4,9,2,5]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The entire sequence is a wiggle sequence with differences (6, -3, 5, -7, 3).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,17,5,10,13,15,10,5,16,8]
<strong>Output:</strong> 7
<strong>Explanation:</strong> There are several subsequences that achieve this length.
One is [1, 17, 10, 13, 10, 16, 8] with differences (16, -7, 3, -3, 6, -8).
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6,7,8,9]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve this in <code>O(n)</code> time?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i]$ as the length of the wiggle sequence ending at the $i$th element with an upward trend, and $g[i]$ as the length of the wiggle sequence ending at the $i$th element with a downward trend. Initially, $f[0] = g[0] = 1$ because when there is only one element, the length of the wiggle sequence is $1$. Initialize the answer as $1$.

For $f[i]$, where $i \geq 1$, we enumerate $j$ in the range $[0, i)$, if $nums[j] < nums[i]$, it means that $i$ can be appended after $j$ to form an upward wiggle sequence, then $f[i] = \max(f[i], g[j] + 1)$; if $nums[j] > nums[i]$, it means that $i$ can be appended after $j$ to form a downward wiggle sequence, then $g[i] = \max(g[i], f[j] + 1)$. Then we update the answer to $\max(f[i], g[i])$.

Finally, we return the answer.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def wiggleMaxLength(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 1
        f = [1] * n
        g = [1] * n
        for i in range(1, n):
            for j in range(i):
                if nums[j] < nums[i]:
                    f[i] = max(f[i], g[j] + 1)
                elif nums[j] > nums[i]:
                    g[i] = max(g[i], f[j] + 1)
            ans = max(ans, f[i], g[i])
        return ans
```

#### Java

```java
class Solution {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        int ans = 1;
        int[] f = new int[n];
        int[] g = new int[n];
        f[0] = 1;
        g[0] = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    f[i] = Math.max(f[i], g[j] + 1);
                } else if (nums[j] > nums[i]) {
                    g[i] = Math.max(g[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, Math.max(f[i], g[i]));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int wiggleMaxLength(vector<int>& nums) {
        int n = nums.size();
        int ans = 1;
        vector<int> f(n, 1);
        vector<int> g(n, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    f[i] = max(f[i], g[j] + 1);
                } else if (nums[j] > nums[i]) {
                    g[i] = max(g[i], f[j] + 1);
                }
            }
            ans = max({ans, f[i], g[i]});
        }
        return ans;
    }
};
```

#### Go

```go
func wiggleMaxLength(nums []int) int {
	n := len(nums)
	f := make([]int, n)
	g := make([]int, n)
	f[0], g[0] = 1, 1
	ans := 1
	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			if nums[j] < nums[i] {
				f[i] = max(f[i], g[j]+1)
			} else if nums[j] > nums[i] {
				g[i] = max(g[i], f[j]+1)
			}
		}
		ans = max(ans, max(f[i], g[i]))
	}
	return ans
}
```

#### TypeScript

```ts
function wiggleMaxLength(nums: number[]): number {
    const n = nums.length;
    const f: number[] = Array(n).fill(1);
    const g: number[] = Array(n).fill(1);
    let ans = 1;
    for (let i = 1; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (nums[i] > nums[j]) {
                f[i] = Math.max(f[i], g[j] + 1);
            } else if (nums[i] < nums[j]) {
                g[i] = Math.max(g[i], f[j] + 1);
            }
        }
        ans = Math.max(ans, f[i], g[i]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
