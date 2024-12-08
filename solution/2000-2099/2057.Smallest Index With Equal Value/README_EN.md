---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2057.Smallest%20Index%20With%20Equal%20Value/README_EN.md
rating: 1167
source: Weekly Contest 265 Q1
tags:
    - Array
---

<!-- problem:start -->

# [2057. Smallest Index With Equal Value](https://leetcode.com/problems/smallest-index-with-equal-value)

[中文文档](/solution/2000-2099/2057.Smallest%20Index%20With%20Equal%20Value/README.md)

## Description

<!-- description:start -->

<p>Given a <strong>0-indexed</strong> integer array <code>nums</code>, return <em>the <strong>smallest</strong> index </em><code>i</code><em> of </em><code>nums</code><em> such that </em><code>i mod 10 == nums[i]</code><em>, or </em><code>-1</code><em> if such index does not exist</em>.</p>

<p><code>x mod y</code> denotes the <strong>remainder</strong> when <code>x</code> is divided by <code>y</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,2]
<strong>Output:</strong> 0
<strong>Explanation:</strong> 
i=0: 0 mod 10 = 0 == nums[0].
i=1: 1 mod 10 = 1 == nums[1].
i=2: 2 mod 10 = 2 == nums[2].
All indices have i mod 10 == nums[i], so we return the smallest index 0.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,3,2,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
i=0: 0 mod 10 = 0 != nums[0].
i=1: 1 mod 10 = 1 != nums[1].
i=2: 2 mod 10 = 2 == nums[2].
i=3: 3 mod 10 = 3 != nums[3].
2 is the only index which has i mod 10 == nums[i].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6,7,8,9,0]
<strong>Output:</strong> -1
<strong>Explanation:</strong> No index satisfies i mod 10 == nums[i].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal

We directly traverse the array. For each index $i$, we check if it satisfies $i \bmod 10 = \textit{nums}[i]$. If it does, we return the current index $i$.

If we traverse the entire array and do not find a satisfying index, we return $-1$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestEqual(self, nums: List[int]) -> int:
        for i, x in enumerate(nums):
            if i % 10 == x:
                return i
        return -1
```

#### Java

```java
class Solution {
    public int smallestEqual(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if (i % 10 == nums[i]) {
                return i;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestEqual(vector<int>& nums) {
        for (int i = 0; i < nums.size(); ++i) {
            if (i % 10 == nums[i]) {
                return i;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func smallestEqual(nums []int) int {
	for i, x := range nums {
		if i%10 == x {
			return i
		}
	}
	return -1
}
```

#### TypeScript

```ts
function smallestEqual(nums: number[]): number {
    for (let i = 0; i < nums.length; ++i) {
        if (i % 10 === nums[i]) {
            return i;
        }
    }
    return -1;
}
```

#### Rust

```rust
impl Solution {
    pub fn smallest_equal(nums: Vec<i32>) -> i32 {
        for (i, &x) in nums.iter().enumerate() {
            if i % 10 == x as usize {
                return i as i32;
            }
        }
        -1
    }
}
```

#### Cangjie

```cj
class Solution {
    func smallestEqual(nums: Array<Int64>): Int64 {
        for (i in 0..nums.size) {
            if (i % 10 == nums[i]) {
                return i
            }
        }
        -1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
