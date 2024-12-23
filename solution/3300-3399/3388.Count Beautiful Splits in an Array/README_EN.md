---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3388.Count%20Beautiful%20Splits%20in%20an%20Array/README_EN.md
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3388. Count Beautiful Splits in an Array](https://leetcode.com/problems/count-beautiful-splits-in-an-array)

[中文文档](/solution/3300-3399/3388.Count%20Beautiful%20Splits%20in%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code>.</p>

<p>A split of an array <code>nums</code> is <strong>beautiful</strong> if:</p>

<ol>
	<li>The array <code>nums</code> is split into three <span data-keyword="subarray-nonempty">subarrays</span>: <code>nums1</code>, <code>nums2</code>, and <code>nums3</code>, such that <code>nums</code> can be formed by concatenating <code>nums1</code>, <code>nums2</code>, and <code>nums3</code> in that order.</li>
	<li>The subarray <code>nums1</code> is a <span data-keyword="array-prefix">prefix</span> of <code>nums2</code> <strong>OR</strong> <code>nums2</code> is a <span data-keyword="array-prefix">prefix</span> of <code>nums3</code>.</li>
</ol>

<p>Return the <strong>number of ways</strong> you can make this split.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The beautiful splits are:</p>

<ol>
	<li>A split with <code>nums1 = [1]</code>, <code>nums2 = [1,2]</code>, <code>nums3 = [1]</code>.</li>
	<li>A split with <code>nums1 = [1]</code>, <code>nums2 = [1]</code>, <code>nums3 = [2,1]</code>.</li>
</ol>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There are 0 beautiful splits.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code><font face="monospace">0 &lt;= nums[i] &lt;= 50</font></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: LCP + Enumeration

We can preprocess $\text{LCP}[i][j]$ to represent the length of the longest common prefix of $\textit{nums}[i:]$ and $\textit{nums}[j:]$. Initially, $\text{LCP}[i][j] = 0$.

Next, we enumerate $i$ and $j$ in reverse order. For each pair of $i$ and $j$, if $\textit{nums}[i] = \textit{nums}[j]$, then we can get $\text{LCP}[i][j] = \text{LCP}[i + 1][j + 1] + 1$.

Finally, we enumerate the ending position $i$ of the first subarray (excluding position $i$) and the ending position $j$ of the second subarray (excluding position $j$). The length of the first subarray is $i$, the length of the second subarray is $j - i$, and the length of the third subarray is $n - j$. If $i \leq j - i$ and $\text{LCP}[0][i] \geq i$, or $j - i \leq n - j$ and $\text{LCP}[i][j] \geq j - i$, then this split is beautiful, and we increment the answer by one.

After enumerating, the answer is the number of beautiful splits.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def beautifulSplits(self, nums: List[int]) -> int:
        n = len(nums)
        lcp = [[0] * (n + 1) for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for j in range(n - 1, i - 1, -1):
                if nums[i] == nums[j]:
                    lcp[i][j] = lcp[i + 1][j + 1] + 1
        ans = 0
        for i in range(1, n - 1):
            for j in range(i + 1, n):
                a = i <= j - i and lcp[0][i] >= i
                b = j - i <= n - j and lcp[i][j] >= j - i
                ans += int(a or b)
        return ans
```

#### Java

```java
class Solution {
    public int beautifulSplits(int[] nums) {
        int n = nums.length;
        int[][] lcp = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[i] == nums[j]) {
                    lcp[i][j] = lcp[i + 1][j + 1] + 1;
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                boolean a = (i <= j - i) && (lcp[0][i] >= i);
                boolean b = (j - i <= n - j) && (lcp[i][j] >= j - i);
                if (a || b) {
                    ans++;
                }
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
    int beautifulSplits(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> lcp(n + 1, vector<int>(n + 1, 0));

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[i] == nums[j]) {
                    lcp[i][j] = lcp[i + 1][j + 1] + 1;
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                bool a = (i <= j - i) && (lcp[0][i] >= i);
                bool b = (j - i <= n - j) && (lcp[i][j] >= j - i);
                if (a || b) {
                    ans++;
                }
            }
        }

        return ans;
    }
};
```

#### Go

```go
func beautifulSplits(nums []int) (ans int) {
    n := len(nums)
    lcp := make([][]int, n+1)
    for i := range lcp {
        lcp[i] = make([]int, n+1)
    }

    for i := n - 1; i >= 0; i-- {
        for j := n - 1; j > i; j-- {
            if nums[i] == nums[j] {
                lcp[i][j] = lcp[i+1][j+1] + 1
            }
        }
    }

    for i := 1; i < n-1; i++ {
        for j := i + 1; j < n; j++ {
            a := i <= j-i && lcp[0][i] >= i
            b := j-i <= n-j && lcp[i][j] >= j-i
            if a || b {
                ans++
            }
        }
    }

    return
}
```

#### TypeScript

```ts
function beautifulSplits(nums: number[]): number {
    const n = nums.length;
    const lcp: number[][] = Array.from({ length: n + 1 }, () => Array(n + 1).fill(0));

    for (let i = n - 1; i >= 0; i--) {
        for (let j = n - 1; j > i; j--) {
            if (nums[i] === nums[j]) {
                lcp[i][j] = lcp[i + 1][j + 1] + 1;
            }
        }
    }

    let ans = 0;
    for (let i = 1; i < n - 1; i++) {
        for (let j = i + 1; j < n; j++) {
            const a = i <= j - i && lcp[0][i] >= i;
            const b = j - i <= n - j && lcp[i][j] >= j - i;
            if (a || b) {
                ans++;
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
