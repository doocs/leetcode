---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1909.Remove%20One%20Element%20to%20Make%20the%20Array%20Strictly%20Increasing/README_EN.md
rating: 1461
source: Biweekly Contest 55 Q1
tags:
    - Array
---

<!-- problem:start -->

# [1909. Remove One Element to Make the Array Strictly Increasing](https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing)

[中文文档](/solution/1900-1999/1909.Remove%20One%20Element%20to%20Make%20the%20Array%20Strictly%20Increasing/README.md)

## Description

<!-- description:start -->

<p>Given a <strong>0-indexed</strong> integer array <code>nums</code>, return <code>true</code> <em>if it can be made <strong>strictly increasing</strong> after removing <strong>exactly one</strong> element, or </em><code>false</code><em> otherwise. If the array is already strictly increasing, return </em><code>true</code>.</p>

<p>The array <code>nums</code> is <strong>strictly increasing</strong> if <code>nums[i - 1] &lt; nums[i]</code> for each index <code>(1 &lt;= i &lt; nums.length).</code></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,<u>10</u>,5,7]
<strong>Output:</strong> true
<strong>Explanation:</strong> By removing 10 at index 2 from nums, it becomes [1,2,5,7].
[1,2,5,7] is strictly increasing, so return true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,1,2]
<strong>Output:</strong> false
<strong>Explanation:</strong>
[3,1,2] is the result of removing the element at index 0.
[2,1,2] is the result of removing the element at index 1.
[2,3,2] is the result of removing the element at index 2.
[2,3,1] is the result of removing the element at index 3.
No resulting array is strictly increasing, so return false.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1]
<strong>Output:</strong> false
<strong>Explanation:</strong> The result of removing any element is [1,1].
[1,1] is not strictly increasing, so return false.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal

We can traverse the array to find the first position $i$ where $\textit{nums}[i] < \textit{nums}[i+1]$ is not satisfied. Then, we check if the array is strictly increasing after removing either $i$ or $i+1$. If it is, we return $\textit{true}$; otherwise, we return $\textit{false}$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canBeIncreasing(self, nums: List[int]) -> bool:
        def check(k: int) -> bool:
            pre = -inf
            for i, x in enumerate(nums):
                if i == k:
                    continue
                if pre >= x:
                    return False
                pre = x
            return True

        i = 0
        while i + 1 < len(nums) and nums[i] < nums[i + 1]:
            i += 1
        return check(i) or check(i + 1)
```

#### Java

```java
class Solution {
    public boolean canBeIncreasing(int[] nums) {
        int i = 0;
        while (i + 1 < nums.length && nums[i] < nums[i + 1]) {
            ++i;
        }
        return check(nums, i) || check(nums, i + 1);
    }

    private boolean check(int[] nums, int k) {
        int pre = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i == k) {
                continue;
            }
            if (pre >= nums[i]) {
                return false;
            }
            pre = nums[i];
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canBeIncreasing(vector<int>& nums) {
        int n = nums.size();
        auto check = [&](int k) -> bool {
            int pre = 0;
            for (int i = 0; i < n; ++i) {
                if (i == k) {
                    continue;
                }
                if (pre >= nums[i]) {
                    return false;
                }
                pre = nums[i];
            }
            return true;
        };
        int i = 0;
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            ++i;
        }
        return check(i) || check(i + 1);
    }
};
```

#### Go

```go
func canBeIncreasing(nums []int) bool {
	check := func(k int) bool {
		pre := 0
		for i, x := range nums {
			if i == k {
				continue
			}
			if pre >= x {
				return false
			}
			pre = x
		}
		return true
	}
	i := 0
	for i+1 < len(nums) && nums[i] < nums[i+1] {
		i++
	}
	return check(i) || check(i+1)
}
```

#### TypeScript

```ts
function canBeIncreasing(nums: number[]): boolean {
    const n = nums.length;
    const check = (k: number): boolean => {
        let pre = 0;
        for (let i = 0; i < n; ++i) {
            if (i === k) {
                continue;
            }
            if (pre >= nums[i]) {
                return false;
            }
            pre = nums[i];
        }
        return true;
    };
    let i = 0;
    while (i + 1 < n && nums[i] < nums[i + 1]) {
        ++i;
    }
    return check(i) || check(i + 1);
}
```

#### Rust

```rust
impl Solution {
    pub fn can_be_increasing(nums: Vec<i32>) -> bool {
        let check = |k: usize| -> bool {
            let mut pre = 0;
            for (i, &x) in nums.iter().enumerate() {
                if i == k {
                    continue;
                }
                if pre >= x {
                    return false;
                }
                pre = x;
            }
            true
        };

        let mut i = 0;
        while i + 1 < nums.len() && nums[i] < nums[i + 1] {
            i += 1;
        }
        check(i) || check(i + 1)
    }
}
```

#### C#

```cs
public class Solution {
    public bool CanBeIncreasing(int[] nums) {
        int n = nums.Length;
        bool check(int k) {
            int pre = 0;
            for (int i = 0; i < n; ++i) {
                if (i == k) {
                    continue;
                }
                if (pre >= nums[i]) {
                    return false;
                }
                pre = nums[i];
            }
            return true;
        }
        int i = 0;
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            ++i;
        }
        return check(i) || check(i + 1);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
