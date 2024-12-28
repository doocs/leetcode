---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3046.Split%20the%20Array/README_EN.md
rating: 1212
source: Weekly Contest 386 Q1
tags:
    - Array
    - Hash Table
    - Counting
---

<!-- problem:start -->

# [3046. Split the Array](https://leetcode.com/problems/split-the-array)

[中文文档](/solution/3000-3099/3046.Split%20the%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of <strong>even</strong> length. You have to split the array into two parts <code>nums1</code> and <code>nums2</code> such that:</p>

<ul>
	<li><code>nums1.length == nums2.length == nums.length / 2</code>.</li>
	<li><code>nums1</code> should contain <strong>distinct </strong>elements.</li>
	<li><code>nums2</code> should also contain <strong>distinct</strong> elements.</li>
</ul>

<p>Return <code>true</code><em> if it is possible to split the array, and </em><code>false</code> <em>otherwise</em><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2,2,3,4]
<strong>Output:</strong> true
<strong>Explanation:</strong> One of the possible ways to split nums is nums1 = [1,2,3] and nums2 = [1,2,4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1]
<strong>Output:</strong> false
<strong>Explanation:</strong> The only possible way to split nums is nums1 = [1,1] and nums2 = [1,1]. Both nums1 and nums2 do not contain distinct elements. Therefore, we return false.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>nums.length % 2 == 0 </code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

According to the problem, we need to divide the array into two parts, and the elements in each part are all distinct. Therefore, we can count the occurrence of each element in the array. If an element appears three or more times, it cannot satisfy the problem's requirements. Otherwise, we can divide the array into two parts.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPossibleToSplit(self, nums: List[int]) -> bool:
        return max(Counter(nums).values()) < 3
```

#### Java

```java
class Solution {
    public boolean isPossibleToSplit(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) {
            if (++cnt[x] >= 3) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPossibleToSplit(vector<int>& nums) {
        int cnt[101]{};
        for (int x : nums) {
            if (++cnt[x] >= 3) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isPossibleToSplit(nums []int) bool {
	cnt := [101]int{}
	for _, x := range nums {
		cnt[x]++
		if cnt[x] >= 3 {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function isPossibleToSplit(nums: number[]): boolean {
    const cnt: number[] = Array(101).fill(0);
    for (const x of nums) {
        if (++cnt[x] >= 3) {
            return false;
        }
    }
    return true;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn is_possible_to_split(nums: Vec<i32>) -> bool {
        let mut cnt = HashMap::new();
        for &x in &nums {
            *cnt.entry(x).or_insert(0) += 1;
        }
        *cnt.values().max().unwrap_or(&0) < 3
    }
}
```

#### C#

```cs
public class Solution {
    public bool IsPossibleToSplit(int[] nums) {
        int[] cnt = new int[101];
        foreach (int x in nums) {
            if (++cnt[x] >= 3) {
                return false;
            }
        }
        return true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
