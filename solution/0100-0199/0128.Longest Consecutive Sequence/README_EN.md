---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0128.Longest%20Consecutive%20Sequence/README_EN.md
tags:
    - Union Find
    - Array
    - Hash Table
---

<!-- problem:start -->

# [128. Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence)

[中文文档](/solution/0100-0199/0128.Longest%20Consecutive%20Sequence/README.md)

## Description

<!-- description:start -->

<p>Given an unsorted array of integers <code>nums</code>, return <em>the length of the longest consecutive elements sequence.</em></p>

<p>You must write an algorithm that runs in&nbsp;<code>O(n)</code>&nbsp;time.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [100,4,200,1,3,2]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest consecutive elements sequence is <code>[1, 2, 3, 4]</code>. Therefore its length is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,3,7,2,5,8,4,6,0,1]
<strong>Output:</strong> 9
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We can use a hash table $\textit{s}$ to store all the elements in the array, a variable $\textit{ans}$ to record the length of the longest consecutive sequence, and a hash table $\textit{d}$ to record the length of the consecutive sequence each element $x$ belongs to.

Next, we iterate through each element $x$ in the array, using a temporary variable $y$ to record the maximum value of the current consecutive sequence, initially $y = x$. Then, we continuously try to match $y+1, y+2, y+3, \dots$ until we can no longer match. During this process, we remove the matched elements from the hash table $\textit{s}$. The length of the consecutive sequence that the current element $x$ belongs to is $d[x] = d[y] + y - x$, and then we update the answer $\textit{ans} = \max(\textit{ans}, d[x])$.

After the iteration, we return the answer $\textit{ans}$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        s = set(nums)
        ans = 0
        d = defaultdict(int)
        for x in nums:
            y = x
            while y in s:
                s.remove(y)
                y += 1
            d[x] = d[y] + y - x
            ans = max(ans, d[x])
        return ans
```

#### Java

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            s.add(x);
        }
        int ans = 0;
        Map<Integer, Integer> d = new HashMap<>();
        for (int x : nums) {
            int y = x;
            while (s.contains(y)) {
                s.remove(y++);
            }
            d.put(x, d.getOrDefault(y, 0) + y - x);
            ans = Math.max(ans, d.get(x));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        int ans = 0;
        unordered_map<int, int> d;
        for (int x : nums) {
            int y = x;
            while (s.contains(y)) {
                s.erase(y++);
            }
            d[x] = (d.contains(y) ? d[y] : 0) + y - x;
            ans = max(ans, d[x]);
        }
        return ans;
    }
};
```

#### Go

```go
func longestConsecutive(nums []int) (ans int) {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	d := map[int]int{}
	for _, x := range nums {
		y := x
		for s[y] {
			delete(s, y)
			y++
		}
		d[x] = d[y] + y - x
		ans = max(ans, d[x])
	}
	return
}
```

#### TypeScript

```ts
function longestConsecutive(nums: number[]): number {
    const s = new Set(nums);
    let ans = 0;
    const d = new Map<number, number>();
    for (const x of nums) {
        let y = x;
        while (s.has(y)) {
            s.delete(y++);
        }
        d.set(x, (d.get(y) || 0) + (y - x));
        ans = Math.max(ans, d.get(x)!);
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::{HashMap, HashSet};

impl Solution {
    pub fn longest_consecutive(nums: Vec<i32>) -> i32 {
        let mut s: HashSet<i32> = nums.iter().cloned().collect();
        let mut ans = 0;
        let mut d: HashMap<i32, i32> = HashMap::new();
        for &x in &nums {
            let mut y = x;
            while s.contains(&y) {
                s.remove(&y);
                y += 1;
            }
            let length = d.get(&(y)).unwrap_or(&0) + y - x;
            d.insert(x, length);
            ans = ans.max(length);
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var longestConsecutive = function (nums) {
    const s = new Set(nums);
    let ans = 0;
    const d = new Map();
    for (const x of nums) {
        let y = x;
        while (s.has(y)) {
            s.delete(y++);
        }
        d.set(x, (d.get(y) || 0) + (y - x));
        ans = Math.max(ans, d.get(x));
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Hash Table (Optimization)

Similar to Solution 1, we use a hash table $\textit{s}$ to store all the elements in the array and a variable $\textit{ans}$ to record the length of the longest consecutive sequence. However, we no longer use a hash table $\textit{d}$ to record the length of the consecutive sequence each element $x$ belongs to. During the iteration, we skip elements where $x-1$ is also in the hash table $\textit{s}$. If $x-1$ is in the hash table $\textit{s}$, then $x$ is definitely not the start of a consecutive sequence, so we can directly skip $x$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        s = set(nums)
        ans = 0
        for x in s:
            if x - 1 not in s:
                y = x + 1
                while y in s:
                    y += 1
                ans = max(ans, y - x)
        return ans
```

#### Java

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            s.add(x);
        }
        int ans = 0;
        for (int x : s) {
            if (!s.contains(x - 1)) {
                int y = x + 1;
                while (s.contains(y)) {
                    ++y;
                }
                ans = Math.max(ans, y - x);
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
    int longestConsecutive(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        int ans = 0;
        for (int x : s) {
            if (!s.contains(x - 1)) {
                int y = x + 1;
                while (s.contains(y)) {
                    y++;
                }
                ans = max(ans, y - x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestConsecutive(nums []int) (ans int) {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	for x, _ := range s {
		if !s[x-1] {
			y := x + 1
			for s[y] {
				y++
			}
			ans = max(ans, y-x)
		}
	}
	return
}
```

#### TypeScript

```ts
function longestConsecutive(nums: number[]): number {
    const s = new Set<number>(nums);
    let ans = 0;
    for (const x of s) {
        if (!s.has(x - 1)) {
            let y = x + 1;
            while (s.has(y)) {
                y++;
            }
            ans = Math.max(ans, y - x);
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::HashSet;

impl Solution {
    pub fn longest_consecutive(nums: Vec<i32>) -> i32 {
        let s: HashSet<i32> = nums.iter().cloned().collect();
        let mut ans = 0;
        for &x in &s {
            if !s.contains(&(x - 1)) {
                let mut y = x + 1;
                while s.contains(&y) {
                    y += 1;
                }
                ans = ans.max(y - x);
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var longestConsecutive = function (nums) {
    const s = new Set(nums);
    let ans = 0;
    for (const x of nums) {
        if (!s.has(x - 1)) {
            let y = x + 1;
            while (s.has(y)) {
                y++;
            }
            ans = Math.max(ans, y - x);
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
