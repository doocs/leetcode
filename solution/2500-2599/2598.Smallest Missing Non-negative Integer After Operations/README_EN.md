---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2598.Smallest%20Missing%20Non-negative%20Integer%20After%20Operations/README_EN.md
rating: 1845
source: Weekly Contest 337 Q4
tags:
    - Greedy
    - Array
    - Hash Table
    - Math
---

<!-- problem:start -->

# [2598. Smallest Missing Non-negative Integer After Operations](https://leetcode.com/problems/smallest-missing-non-negative-integer-after-operations)

[中文文档](/solution/2500-2599/2598.Smallest%20Missing%20Non-negative%20Integer%20After%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> and an integer <code>value</code>.</p>

<p>In one operation, you can add or subtract <code>value</code> from any element of <code>nums</code>.</p>

<ul>
	<li>For example, if <code>nums = [1,2,3]</code> and <code>value = 2</code>, you can choose to subtract <code>value</code> from <code>nums[0]</code> to make <code>nums = [-1,2,3]</code>.</li>
</ul>

<p>The MEX (minimum excluded) of an array is the smallest missing <strong>non-negative</strong> integer in it.</p>

<ul>
	<li>For example, the MEX of <code>[-1,2,3]</code> is <code>0</code> while the MEX of <code>[1,0,3]</code> is <code>2</code>.</li>
</ul>

<p>Return <em>the maximum MEX of </em><code>nums</code><em> after applying the mentioned operation <strong>any number of times</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-10,7,13,6,8], value = 5
<strong>Output:</strong> 4
<strong>Explanation:</strong> One can achieve this result by applying the following operations:
- Add value to nums[1] twice to make nums = [1,<strong><u>0</u></strong>,7,13,6,8]
- Subtract value from nums[2] once to make nums = [1,0,<strong><u>2</u></strong>,13,6,8]
- Subtract value from nums[3] twice to make nums = [1,0,2,<strong><u>3</u></strong>,6,8]
The MEX of nums is 4. It can be shown that 4 is the maximum MEX we can achieve.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-10,7,13,6,8], value = 7
<strong>Output:</strong> 2
<strong>Explanation:</strong> One can achieve this result by applying the following operation:
- subtract value from nums[2] once to make nums = [1,-10,<u><strong>0</strong></u>,13,6,8]
The MEX of nums is 2. It can be shown that 2 is the maximum MEX we can achieve.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, value &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We use a hash table $\textit{cnt}$ to count the number of remainders when each number in the array is modulo $\textit{value}$.

Then we traverse starting from $0$. For the current number $i$ being traversed, if $\textit{cnt}[i \bmod \textit{value}]$ is $0$, it means there is no number in the array whose remainder when modulo $\textit{value}$ is $i$, so $i$ is the MEX of the array, and we can return it directly. Otherwise, we decrement $\textit{cnt}[i \bmod \textit{value}]$ by $1$ and continue traversing.

The time complexity is $O(n)$ and the space complexity is $O(\textit{value})$, where $n$ is the length of array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findSmallestInteger(self, nums: List[int], value: int) -> int:
        cnt = Counter(x % value for x in nums)
        for i in range(len(nums) + 1):
            if cnt[i % value] == 0:
                return i
            cnt[i % value] -= 1
```

#### Java

```java
class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        int[] cnt = new int[value];
        for (int x : nums) {
            ++cnt[(x % value + value) % value];
        }
        for (int i = 0;; ++i) {
            if (cnt[i % value]-- == 0) {
                return i;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findSmallestInteger(vector<int>& nums, int value) {
        int cnt[value];
        memset(cnt, 0, sizeof(cnt));
        for (int x : nums) {
            ++cnt[(x % value + value) % value];
        }
        for (int i = 0;; ++i) {
            if (cnt[i % value]-- == 0) {
                return i;
            }
        }
    }
};
```

#### Go

```go
func findSmallestInteger(nums []int, value int) int {
	cnt := make([]int, value)
	for _, x := range nums {
		cnt[(x%value+value)%value]++
	}
	for i := 0; ; i++ {
		if cnt[i%value] == 0 {
			return i
		}
		cnt[i%value]--
	}
}
```

#### TypeScript

```ts
function findSmallestInteger(nums: number[], value: number): number {
    const cnt: number[] = new Array(value).fill(0);
    for (const x of nums) {
        ++cnt[((x % value) + value) % value];
    }
    for (let i = 0; ; ++i) {
        if (cnt[i % value]-- === 0) {
            return i;
        }
    }
}
```

#### Rust

```rust
impl Solution {
    pub fn find_smallest_integer(nums: Vec<i32>, value: i32) -> i32 {
        let mut cnt = vec![0; value as usize];
        for &x in &nums {
            let idx = ((x % value + value) % value) as usize;
            cnt[idx] += 1;
        }

        let mut i = 0;
        loop {
            let idx = (i % value) as usize;
            if cnt[idx] == 0 {
                return i;
            }
            cnt[idx] -= 1;
            i += 1;
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} value
 * @return {number}
 */
var findSmallestInteger = function (nums, value) {
    const cnt = Array(value).fill(0);
    for (const x of nums) {
        ++cnt[((x % value) + value) % value];
    }
    for (let i = 0; ; ++i) {
        if (cnt[i % value]-- === 0) {
            return i;
        }
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
