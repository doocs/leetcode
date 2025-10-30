---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3719.Longest%20Balanced%20Subarray%20I/README_EN.md
rating: 1467
source: Weekly Contest 472 Q2
tags:
    - Segment Tree
    - Array
    - Hash Table
    - Divide and Conquer
    - Prefix Sum
---

<!-- problem:start -->

# [3719. Longest Balanced Subarray I](https://leetcode.com/problems/longest-balanced-subarray-i)

[中文文档](/solution/3700-3799/3719.Longest%20Balanced%20Subarray%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>A <strong><span data-keyword="subarray-nonempty">subarray</span></strong> is called <strong>balanced</strong> if the number of <strong>distinct even</strong> numbers in the subarray is equal to the number of <strong>distinct odd</strong> numbers.</p>

<p>Return the length of the <strong>longest</strong> balanced subarray.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,5,4,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The longest balanced subarray is <code>[2, 5, 4, 3]</code>.</li>
	<li>It has 2 distinct even numbers <code>[2, 4]</code> and 2 distinct odd numbers <code>[5, 3]</code>. Thus, the answer is 4.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,2,2,5,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The longest balanced subarray is <code>[3, 2, 2, 5, 4]</code>.</li>
	<li>It has 2 distinct even numbers <code>[2, 4]</code> and 2 distinct odd numbers <code>[3, 5]</code>. Thus, the answer is 5.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The longest balanced subarray is <code>[2, 3, 2]</code>.</li>
	<li>It has 1 distinct even number <code>[2]</code> and 1 distinct odd number <code>[3]</code>. Thus, the answer is 3.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1500</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Enumeration

We can enumerate the left endpoint $i$ of the subarray, and then enumerate the right endpoint $j$ from the left endpoint. During the enumeration process, we use a hash table $\textit{vis}$ to record the numbers that have appeared in the subarray, and use an array $\textit{cnt}$ of length $2$ to record the count of distinct even numbers and distinct odd numbers in the subarray respectively. When $\textit{cnt}[0] = \textit{cnt}[1]$, we update the answer $\textit{ans} = \max(\textit{ans}, j - i + 1)$.

The time complexity is $O(n^2)$ and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestBalanced(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0
        for i in range(n):
            cnt = [0, 0]
            vis = set()
            for j in range(i, n):
                if nums[j] not in vis:
                    cnt[nums[j] & 1] += 1
                    vis.add(nums[j])
                if cnt[0] == cnt[1]:
                    ans = max(ans, j - i + 1)
        return ans
```

#### Java

```java
class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            Set<Integer> vis = new HashSet<>();
            int[] cnt = new int[2];
            for (int j = i; j < n; ++j) {
                if (vis.add(nums[j])) {
                    ++cnt[nums[j] & 1];
                }
                if (cnt[0] == cnt[1]) {
                    ans = Math.max(ans, j - i + 1);
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
    int longestBalanced(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            unordered_set<int> vis;
            int cnt[2]{};
            for (int j = i; j < n; ++j) {
                if (!vis.contains(nums[j])) {
                    vis.insert(nums[j]);
                    ++cnt[nums[j] & 1];
                }
                if (cnt[0] == cnt[1]) {
                    ans = max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestBalanced(nums []int) (ans int) {
	n := len(nums)
	for i := 0; i < n; i++ {
		vis := map[int]bool{}
		cnt := [2]int{}
		for j := i; j < n; j++ {
			if !vis[nums[j]] {
				vis[nums[j]] = true
				cnt[nums[j]&1]++
			}
			if cnt[0] == cnt[1] {
				ans = max(ans, j-i+1)
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function longestBalanced(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        const vis = new Set<number>();
        const cnt: number[] = Array(2).fill(0);
        for (let j = i; j < n; ++j) {
            if (!vis.has(nums[j])) {
                vis.add(nums[j]);
                ++cnt[nums[j] & 1];
            }
            if (cnt[0] === cnt[1]) {
                ans = Math.max(ans, j - i + 1);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
