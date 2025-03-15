---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0532.K-diff%20Pairs%20in%20an%20Array/README_EN.md
tags:
    - Array
    - Hash Table
    - Two Pointers
    - Binary Search
    - Sorting
---

<!-- problem:start -->

# [532. K-diff Pairs in an Array](https://leetcode.com/problems/k-diff-pairs-in-an-array)

[中文文档](/solution/0500-0599/0532.K-diff%20Pairs%20in%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>Given an array of integers <code>nums</code> and an integer <code>k</code>, return <em>the number of <b>unique</b> k-diff pairs in the array</em>.</p>

<p>A <strong>k-diff</strong> pair is an integer pair <code>(nums[i], nums[j])</code>, where the following are true:</p>

<ul>
	<li><code>0 &lt;= i, j &lt; nums.length</code></li>
	<li><code>i != j</code></li>
	<li><code>|nums[i] - nums[j]| == k</code></li>
</ul>

<p><strong>Notice</strong> that <code>|val|</code> denotes the absolute value of <code>val</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,4,1,5], k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of <strong>unique</strong> pairs.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5], k = 1
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,1,5,4], k = 0
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is one 0-diff pair in the array, (1, 1).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>7</sup> &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>7</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

Since $k$ is a fixed value, we can use a hash table $\textit{ans}$ to record the smaller value of the pairs, which allows us to determine the larger value. Finally, we return the size of $\textit{ans}$ as the answer.

We traverse the array $\textit{nums}$. For the current number $x$, we use a hash table $\textit{vis}$ to record all the numbers that have been traversed. If $x-k$ is in $\textit{vis}$, we add $x-k$ to $\textit{ans}$. If $x+k$ is in $\textit{vis}$, we add $x$ to $\textit{ans}$. Then, we add $x$ to $\textit{vis}$. Continue traversing the array $\textit{nums}$ until the end.

Finally, we return the size of $\textit{ans}$ as the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findPairs(self, nums: List[int], k: int) -> int:
        ans = set()
        vis = set()
        for x in nums:
            if x - k in vis:
                ans.add(x - k)
            if x + k in vis:
                ans.add(x)
            vis.add(x)
        return len(ans)
```

#### Java

```java
class Solution {
    public int findPairs(int[] nums, int k) {
        Set<Integer> ans = new HashSet<>();
        Set<Integer> vis = new HashSet<>();
        for (int x : nums) {
            if (vis.contains(x - k)) {
                ans.add(x - k);
            }
            if (vis.contains(x + k)) {
                ans.add(x);
            }
            vis.add(x);
        }
        return ans.size();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findPairs(vector<int>& nums, int k) {
        unordered_set<int> ans, vis;
        for (int x : nums) {
            if (vis.count(x - k)) {
                ans.insert(x - k);
            }
            if (vis.count(x + k)) {
                ans.insert(x);
            }
            vis.insert(x);
        }
        return ans.size();
    }
};
```

#### Go

```go
func findPairs(nums []int, k int) int {
	ans := make(map[int]struct{})
	vis := make(map[int]struct{})

	for _, x := range nums {
		if _, ok := vis[x-k]; ok {
			ans[x-k] = struct{}{}
		}
		if _, ok := vis[x+k]; ok {
			ans[x] = struct{}{}
		}
		vis[x] = struct{}{}
	}
	return len(ans)
}
```

#### TypeScript

```ts
function findPairs(nums: number[], k: number): number {
    const ans = new Set<number>();
    const vis = new Set<number>();
    for (const x of nums) {
        if (vis.has(x - k)) {
            ans.add(x - k);
        }
        if (vis.has(x + k)) {
            ans.add(x);
        }
        vis.add(x);
    }
    return ans.size;
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn find_pairs(nums: Vec<i32>, k: i32) -> i32 {
        let mut ans = HashSet::new();
        let mut vis = HashSet::new();

        for &x in &nums {
            if vis.contains(&(x - k)) {
                ans.insert(x - k);
            }
            if vis.contains(&(x + k)) {
                ans.insert(x);
            }
            vis.insert(x);
        }
        ans.len() as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
