---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4110. Find the Maximum Length of Valid Subsequence III 🔒](https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-iii)

[中文文档](/solution/4100-4199/4110.Find%20the%20Maximum%20Length%20of%20Valid%20Subsequence%20III/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and a <strong>positive</strong> integer <code>k</code>.</p>

<p>A <strong><span data-keyword="subsequence-array">subsequence</span></strong> of <code>nums</code> is <strong>valid</strong> if the sum of every pair of adjacent elements in the subsequence is divisible by <code>k</code>.</p>

<p>A subsequence of length 1 is always valid.</p>

<p>Return the length of the <strong>longest valid subsequence</strong> of <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Pick the subsequence <code>[1, 2, 4, 5]</code>.</li>
	<li>The adjacent sums are <code>1 + 2 = 3</code>, <code>2 + 4 = 6</code>, and <code>4 + 5 = 9</code>, all divisible by 3.</li>
	<li>No valid subsequence longer than 4 exists.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,10,15], k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Pick the whole array <code>[5, 10, 15]</code>.</li>
	<li>The adjacent sums are <code>5 + 10 = 15</code> and <code>10 + 15 = 25</code>, both divisible by 5.</li>
	<li>Every element is a multiple of 5, so any two elements can be adjacent in a valid subsequence, and the answer is 3.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,5,7,9], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Pick the whole array <code>[1, 3, 5, 7, 9]</code>.</li>
	<li>The adjacent sums are <code>1 + 3 = 4</code>, <code>3 + 5 = 8</code>, <code>5 + 7 = 12</code>, and <code>7 + 9 = 16</code>, all divisible by 4.</li>
	<li>The remainders alternate between 1 and 3, which add to 4, so the answer is 5.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> Comparing the current element with every earlier candidate takes $O(n^2)$ time. $n$ is at most $10^5$, so that enumeration does not fit.
>
> Adjacent values sum to a multiple of $k$ exactly when their remainders $x$ and $y$ satisfy $y=(k-x)\bmod k$. The predecessor remainder is fixed by the current one, so a valid subsequence can only alternate between $x$ and $y$. When $x=y$, every chosen element has the same remainder.
>
> For one remainder it is enough to keep the rightmost index. A later element can attach only to the current rightmost index of the complementary remainder, and that chain is at least as long as any earlier index of the same remainder.
>
> We therefore scan the array from left to right. A hash map $\textit{last}$ stores the latest index of each remainder, and $f[i]$ is the longest valid subsequence ending at index $i$. On remainder $x$, if $y$ has already appeared, update $f[i]$ with $f[\textit{last}[y]]+1$, then set $\textit{last}[x]$ to $i$.

<!-- thinking:end -->

Let $f[i]$ be the length of the longest valid subsequence ending at $\textit{nums}[i]$, and initialize every $f[i]$ to $1$. The hash map $\textit{last}$ stores the latest index of each remainder.

Scan the array from left to right. At index $i$, set $x=\textit{nums}[i]\bmod k$ and $y=(k-x)\bmod k$. If remainder $y$ is already in $\textit{last}$, let $j$ be that index and update $f[i]$ to $\max(f[i], f[j]+1)$. Then set $\textit{last}[x]=i$.

The answer is the maximum value of $f[i]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumLength(self, nums: list[int], k: int) -> int:
        f = [1] * len(nums)
        last = {}
        ans = 1
        for i, x in enumerate(nums):
            x %= k
            y = (k - x) % k
            if y in last:
                j = last[y]
                f[i] = max(f[i], f[j] + 1)
                ans = max(ans, f[i])
            last[x] = i
        return ans
```

#### Java

```java
class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        Map<Integer, Integer> last = new HashMap<>();
        int ans = 1;
        for (int i = 0; i < n; i++) {
            int x = nums[i] % k;
            int y = (k - x) % k;
            if (last.containsKey(y)) {
                int j = last.get(y);
                f[i] = Math.max(f[i], f[j] + 1);
                ans = Math.max(ans, f[i]);
            }
            last.put(x, i);
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
        vector<int> f(n, 1);
        unordered_map<int, int> last;
        int ans = 1;
        for (int i = 0; i < n; i++) {
            int x = nums[i] % k;
            int y = (k - x) % k;
            if (last.count(y)) {
                int j = last[y];
                f[i] = max(f[i], f[j] + 1);
                ans = max(ans, f[i]);
            }
            last[x] = i;
        }
        return ans;
    }
};
```

#### Go

```go
func maximumLength(nums []int, k int) int {
	n := len(nums)
	f := make([]int, n)
	for i := range f {
		f[i] = 1
	}
	last := map[int]int{}
	ans := 1
	for i, v := range nums {
		x := v % k
		y := (k - x) % k
		if j, ok := last[y]; ok {
			f[i] = max(f[i], f[j]+1)
			ans = max(ans, f[i])
		}
		last[x] = i
	}
	return ans
}
```

#### TypeScript

```ts
function maximumLength(nums: number[], k: number): number {
    const n = nums.length;
    const f: number[] = new Array(n).fill(1);
    const last = new Map<number, number>();
    let ans = 1;
    for (let i = 0; i < n; i++) {
        const x = nums[i] % k;
        const y = (k - x) % k;
        if (last.has(y)) {
            const j = last.get(y)!;
            f[i] = Math.max(f[i], f[j] + 1);
            ans = Math.max(ans, f[i]);
        }
        last.set(x, i);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
