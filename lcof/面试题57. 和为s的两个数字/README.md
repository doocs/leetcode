# [面试题 57. 和为 s 的两个数字](https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/)

## 题目描述

<p>输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [2,7,11,15], target = 9
<strong>输出：</strong>[2,7] 或者 [7,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [10,26,30,31,47,60], target = 40
<strong>输出：</strong>[10,30] 或者 [30,10]
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i]&nbsp;&lt;= 10<sup>6</sup></code></li>
</ul>

**方法一：双指针**

我们用双指针 $l$ 和 $r$ 分别指向数组的左右两端，然后不断移动指针，直到找到一组和为 $target$ 的连续正整数序列。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        l, r = 0, len(nums) - 1
        while l < r:
            if nums[l] + nums[r] == target:
                return [nums[l], nums[r]]
            if nums[l] + nums[r] > target:
                r -= 1
            else:
                l += 1
```

### **Java**

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (true) {
            if (nums[l] + nums[r] == target) {
                return new int[] {nums[l], nums[r]};
            }
            if (nums[l] + nums[r] > target) {
                --r;
            } else {
                ++l;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        int l = 0, r = nums.size() - 1;
        while (1) {
            if (nums[l] + nums[r] == target) {
                return {nums[l], nums[r]};
            }
            if (nums[l] + nums[r] > target) {
                --r;
            } else {
                ++l;
            }
        }
    }
};
```

### **Go**

```go
func twoSum(nums []int, target int) []int {
	l, r := 0, len(nums)-1
	for {
		if nums[l]+nums[r] == target {
			return []int{nums[l], nums[r]}
		}
		if nums[l]+nums[r] > target {
			r--
		} else {
			l++
		}
	}
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (nums, target) {
    let l = 0;
    let r = nums.length - 1;
    while (1) {
        if (nums[l] + nums[r] == target) {
            return [nums[l], nums[r]];
        }
        if (nums[l] + nums[r] > target) {
            --r;
        } else {
            ++l;
        }
    }
};
```

### **TypeScript**

```ts
function twoSum(nums: number[], target: number): number[] {
    let l = 0;
    let r = nums.length - 1;
    while (nums[l] + nums[r] !== target) {
        if (nums[l] + nums[r] < target) {
            l++;
        } else {
            r--;
        }
    }
    return [nums[l], nums[r]];
}
```

### **Rust**

```rust
use std::cmp::Ordering;

impl Solution {
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let mut l = 0;
        let mut r = nums.len() - 1;
        loop {
            match target.cmp(&(nums[l] + nums[r])) {
                Ordering::Less => r -= 1,
                Ordering::Greater => l += 1,
                Ordering::Equal => break vec![nums[l], nums[r]],
            }
        }
    }
}
```

### **C#**

```cs
public class Solution {
    public int[] TwoSum(int[] nums, int target) {
        int l = 0, r = nums.Length - 1;
        while (true) {
            if (nums[l] + nums[r] == target) {
                return new int[] {nums[l], nums[r]};
            }
            if (nums[l] + nums[r] > target) {
                --r;
            } else {
                ++l;
            }
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
