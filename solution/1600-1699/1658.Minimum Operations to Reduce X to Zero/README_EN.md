---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1658.Minimum%20Operations%20to%20Reduce%20X%20to%20Zero/README_EN.md
rating: 1817
source: Weekly Contest 215 Q3
tags:
    - Array
    - Hash Table
    - Binary Search
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [1658. Minimum Operations to Reduce X to Zero](https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero)

[中文文档](/solution/1600-1699/1658.Minimum%20Operations%20to%20Reduce%20X%20to%20Zero/README.md)

## Description

<p>You are given an integer array <code>nums</code> and an integer <code>x</code>. In one operation, you can either remove the leftmost or the rightmost element from the array <code>nums</code> and subtract its value from <code>x</code>. Note that this <strong>modifies</strong> the array for future operations.</p>

<p>Return <em>the <strong>minimum number</strong> of operations to reduce </em><code>x</code> <em>to <strong>exactly</strong></em> <code>0</code> <em>if it is possible</em><em>, otherwise, return </em><code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,4,2,3], x = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> The optimal solution is to remove the last two elements to reduce x to zero.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,6,7,8,9], x = 4
<strong>Output:</strong> -1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,20,1,1,3], x = 10
<strong>Output:</strong> 5
<strong>Explanation:</strong> The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= x &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Prefix Sum

According to the problem description, we need to remove elements from both ends of the array $nums$ so that the sum of the removed elements equals $x$, and the number of removed elements is minimized. We can transform the problem into: find the longest consecutive subarray in the array $nums$ such that the sum of the subarray $s = \sum_{i=0}^{n} nums[i] - x$. In this way, we can transform the problem into finding the length $mx$ of the longest consecutive subarray in the array $nums$ with a sum of $s$, and the answer is $n - mx$.

We initialize $mx = -1$, and then use a hash table $vis$ to store the prefix sum, where the key is the prefix sum and the value is the index corresponding to the prefix sum.

Traverse the array $nums$, for the current element $nums[i]$, calculate the prefix sum $t$, if $t$ is not in the hash table, add $t$ to the hash table; if $t - s$ is in the hash table, update $mx = \max(mx, i - vis[t - s])$.

Finally, if $mx = -1$, return $-1$, otherwise return $n - mx$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        s = sum(nums) - x
        vis = {0: -1}
        mx, t = -1, 0
        for i, v in enumerate(nums):
            t += v
            if t not in vis:
                vis[t] = i
            if t - s in vis:
                mx = max(mx, i - vis[t - s])
        return -1 if mx == -1 else len(nums) - mx
```

```java
class Solution {
    public int minOperations(int[] nums, int x) {
        int s = -x;
        for (int v : nums) {
            s += v;
        }
        Map<Integer, Integer> vis = new HashMap<>();
        vis.put(0, -1);
        int mx = -1, t = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            t += nums[i];
            vis.putIfAbsent(t, i);
            if (vis.containsKey(t - s)) {
                mx = Math.max(mx, i - vis.get(t - s));
            }
        }
        return mx == -1 ? -1 : n - mx;
    }
}
```

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        int s = accumulate(nums.begin(), nums.end(), 0) - x;
        unordered_map<int, int> vis = {{0, -1}};
        int mx = -1, t = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            t += nums[i];
            if (!vis.contains(t)) {
                vis[t] = i;
            }
            if (vis.contains(t - s)) {
                mx = max(mx, i - vis[t - s]);
            }
        }
        return mx == -1 ? -1 : n - mx;
    }
};
```

```go
func minOperations(nums []int, x int) int {
	s := -x
	for _, v := range nums {
		s += v
	}
	vis := map[int]int{0: -1}
	mx, t := -1, 0
	for i, v := range nums {
		t += v
		if _, ok := vis[t]; !ok {
			vis[t] = i
		}
		if j, ok := vis[t-s]; ok {
			mx = max(mx, i-j)
		}
	}
	if mx == -1 {
		return -1
	}
	return len(nums) - mx
}
```

```ts
function minOperations(nums: number[], x: number): number {
    const s = nums.reduce((acc, cur) => acc + cur, -x);
    const vis: Map<number, number> = new Map([[0, -1]]);
    let [mx, t] = [-1, 0];
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        t += nums[i];
        if (!vis.has(t)) {
            vis.set(t, i);
        }
        if (vis.has(t - s)) {
            mx = Math.max(mx, i - vis.get(t - s)!);
        }
    }
    return ~mx ? n - mx : -1;
}
```

```rust
use std::collections::HashMap;

impl Solution {
    pub fn min_operations(nums: Vec<i32>, x: i32) -> i32 {
        let s = nums.iter().sum::<i32>() - x;
        let mut vis: HashMap<i32, i32> = HashMap::new();
        vis.insert(0, -1);
        let mut mx = -1;
        let mut t = 0;
        for (i, v) in nums.iter().enumerate() {
            t += v;
            if !vis.contains_key(&t) {
                vis.insert(t, i as i32);
            }
            if let Some(&j) = vis.get(&(t - s)) {
                mx = mx.max((i as i32) - j);
            }
        }
        if mx == -1 {
            -1
        } else {
            (nums.len() as i32) - mx
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Two Pointers

Based on the analysis of Solution 1, we need to find the length $mx$ of the longest consecutive subarray in the array $nums$ with a sum of $s$. Since all elements in the array $nums$ are positive integers, the prefix sum of the array will only increase monotonically, so we can use two pointers to solve this problem.

We initialize pointer $j = 0$, prefix sum $t = 0$, and the length of the longest consecutive subarray $mx = -1$.

Traverse the array $nums$, for the current element $nums[i]$, calculate the prefix sum $t += nums[i]$. If $t > s$, then move the pointer $j$ until $t \leq s$. If $t = s$, then update $mx = \max(mx, i - j + 1)$.

Finally, if $mx = -1$, return $-1$, otherwise return $n - mx$.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        s = sum(nums) - x
        j = t = 0
        mx = -1
        for i, x in enumerate(nums):
            t += x
            while j <= i and t > s:
                t -= nums[j]
                j += 1
            if t == s:
                mx = max(mx, i - j + 1)
        return -1 if mx == -1 else len(nums) - mx
```

```java
class Solution {
    public int minOperations(int[] nums, int x) {
        int s = -x;
        for (int v : nums) {
            s += v;
        }
        int mx = -1, t = 0;
        int n = nums.length;
        for (int i = 0, j = 0; i < n; ++i) {
            t += nums[i];
            while (j <= i && t > s) {
                t -= nums[j++];
            }
            if (t == s) {
                mx = Math.max(mx, i - j + 1);
            }
        }
        return mx == -1 ? -1 : n - mx;
    }
}
```

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        int s = accumulate(nums.begin(), nums.end(), 0) - x;
        int mx = -1, t = 0;
        int n = nums.size();
        for (int i = 0, j = 0; i < n; ++i) {
            t += nums[i];
            while (j <= i && t > s) {
                t -= nums[j++];
            }
            if (t == s) {
                mx = max(mx, i - j + 1);
            }
        }
        return mx == -1 ? -1 : n - mx;
    }
};
```

```go
func minOperations(nums []int, x int) int {
	s := -x
	for _, v := range nums {
		s += v
	}
	mx, t, j := -1, 0, 0
	for i, v := range nums {
		t += v
		for ; j <= i && t > s; j++ {
			t -= nums[j]
		}
		if t == s {
			mx = max(mx, i-j+1)
		}
	}
	if mx == -1 {
		return -1
	}
	return len(nums) - mx
}
```

```ts
function minOperations(nums: number[], x: number): number {
    const s = nums.reduce((acc, cur) => acc + cur, -x);
    let [mx, t] = [-1, 0];
    const n = nums.length;
    for (let i = 0, j = 0; i < n; ++i) {
        t += nums[i];
        while (t > s) {
            t -= nums[j++];
        }
        if (t === s) {
            mx = Math.max(mx, i - j + 1);
        }
    }
    return ~mx ? n - mx : -1;
}
```

```rust
impl Solution {
    pub fn min_operations(nums: Vec<i32>, x: i32) -> i32 {
        let s: i32 = nums.iter().sum::<i32>() - x;
        let mut j: usize = 0;
        let mut t: i32 = 0;
        let mut mx: i32 = -1;
        for (i, &v) in nums.iter().enumerate() {
            t += v;
            while j <= i && t > s {
                t -= nums[j];
                j += 1;
            }
            if t == s {
                mx = mx.max((i - j + 1) as i32);
            }
        }
        if mx == -1 {
            -1
        } else {
            (nums.len() as i32) - mx
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
