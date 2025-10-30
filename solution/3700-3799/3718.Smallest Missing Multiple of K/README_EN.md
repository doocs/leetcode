---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3718.Smallest%20Missing%20Multiple%20of%20K/README_EN.md
rating: 1227
source: Weekly Contest 472 Q1
tags:
    - Array
    - Hash Table
---

<!-- problem:start -->

# [3718. Smallest Missing Multiple of K](https://leetcode.com/problems/smallest-missing-multiple-of-k)

[中文文档](/solution/3700-3799/3718.Smallest%20Missing%20Multiple%20of%20K/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return the <strong>smallest positive multiple</strong> of <code>k</code> that is <strong>missing</strong> from <code>nums</code>.</p>

<p>A <strong>multiple</strong> of <code>k</code> is any positive integer divisible by <code>k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [8,2,3,4,6], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>The multiples of <code>k = 2</code> are 2, 4, 6, 8, 10, 12... and the smallest multiple missing from <code>nums</code> is 10.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,7,10,15], k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The multiples of <code>k = 5</code> are 5, 10, 15, 20... and the smallest multiple missing from <code>nums</code> is 5.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Enumeration

We first use a hash table $\textit{s}$ to store the numbers that appear in the array $\textit{nums}$. Then, starting from the first positive multiple of $k$, which is $k \times 1$, we enumerate each positive multiple in sequence until we find the first multiple that does not appear in the hash table $\textit{s}$, which is the answer.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def missingMultiple(self, nums: List[int], k: int) -> int:
        s = set(nums)
        for i in count(1):
            x = k * i
            if x not in s:
                return x
```

#### Java

```java
class Solution {
    public int missingMultiple(int[] nums, int k) {
        boolean[] s = new boolean[101];
        for (int x : nums) {
            s[x] = true;
        }
        for (int i = 1;; ++i) {
            int x = k * i;
            if (x >= s.length || !s[x]) {
                return x;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int missingMultiple(vector<int>& nums, int k) {
        unordered_set<int> s;
        for (int x : nums) {
            s.insert(x);
        }
        for (int i = 1;; ++i) {
            int x = k * i;
            if (!s.contains(x)) {
                return x;
            }
        }
    }
};
```

#### Go

```go
func missingMultiple(nums []int, k int) int {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	for i := 1; ; i++ {
		if x := k * i; !s[x] {
			return x
		}
	}
}
```

#### TypeScript

```ts
function missingMultiple(nums: number[], k: number): number {
    const s = new Set<number>(nums);
    for (let i = 1; ; ++i) {
        const x = k * i;
        if (!s.has(x)) {
            return x;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
