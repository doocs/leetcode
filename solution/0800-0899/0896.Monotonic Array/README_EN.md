---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0896.Monotonic%20Array/README_EN.md
tags:
    - Array
---

# [896. Monotonic Array](https://leetcode.com/problems/monotonic-array)

[中文文档](/solution/0800-0899/0896.Monotonic%20Array/README.md)

## Description

<p>An array is <strong>monotonic</strong> if it is either monotone increasing or monotone decreasing.</p>

<p>An array <code>nums</code> is monotone increasing if for all <code>i &lt;= j</code>, <code>nums[i] &lt;= nums[j]</code>. An array <code>nums</code> is monotone decreasing if for all <code>i &lt;= j</code>, <code>nums[i] &gt;= nums[j]</code>.</p>

<p>Given an integer array <code>nums</code>, return <code>true</code><em> if the given array is monotonic, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2,3]
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,5,4,4]
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,2]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1: Single Traversal

We traverse the array, and if an increasing or decreasing situation occurs, we record it. We then check whether both increasing and decreasing situations have occurred. If both have occurred, it means that the array is not monotonic, and we return `false`.

Otherwise, if we reach the end of the traversal, it means that the array is monotonic, and we return `true`.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def isMonotonic(self, nums: List[int]) -> bool:
        asc = all(a <= b for a, b in pairwise(nums))
        desc = all(a >= b for a, b in pairwise(nums))
        return asc or desc
```

```java
class Solution {
    public boolean isMonotonic(int[] nums) {
        boolean asc = false, desc = false;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i - 1] < nums[i]) {
                asc = true;
            } else if (nums[i - 1] > nums[i]) {
                desc = true;
            }
            if (asc && desc) {
                return false;
            }
        }
        return true;
    }
}
```

```cpp
class Solution {
public:
    bool isMonotonic(vector<int>& nums) {
        bool asc = false, desc = false;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i - 1] < nums[i]) {
                asc = true;
            } else if (nums[i - 1] > nums[i]) {
                desc = true;
            }
            if (asc && desc) {
                return false;
            }
        }
        return true;
    }
};
```

```go
func isMonotonic(nums []int) bool {
	asc, desc := false, false
	for i, x := range nums[1:] {
		if nums[i] < x {
			asc = true
		} else if nums[i] > x {
			desc = true
		}
		if asc && desc {
			return false
		}
	}
	return true
}
```

```ts
function isMonotonic(nums: number[]): boolean {
    let [asc, desc] = [false, false];
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i - 1] < nums[i]) {
            asc = true;
        } else if (nums[i - 1] > nums[i]) {
            desc = true;
        }
        if (asc && desc) {
            return false;
        }
    }
    return true;
}
```

```rust
impl Solution {
    pub fn is_monotonic(nums: Vec<i32>) -> bool {
        let mut asc = false;
        let mut desc = false;
        for i in 1..nums.len() {
            if nums[i - 1] < nums[i] {
                asc = true;
            } else if nums[i - 1] > nums[i] {
                desc = true;
            }
            if asc && desc {
                return false;
            }
        }
        true
    }
}
```

```js
/**
 * @param {number[]} nums
 * @return {boolean}
 */
var isMonotonic = function (nums) {
    let [asc, desc] = [false, false];
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i - 1] < nums[i]) {
            asc = true;
        } else if (nums[i - 1] > nums[i]) {
            desc = true;
        }
        if (asc && desc) {
            return false;
        }
    }
    return true;
};
```

<!-- tabs:end -->

<!-- end -->
