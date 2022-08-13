# [283. Move Zeroes](https://leetcode.com/problems/move-zeroes)

[中文文档](/solution/0200-0299/0283.Move%20Zeroes/README.md)

## Description

<p>Given an integer array <code>nums</code>, move all <code>0</code>&#39;s to the end of it while maintaining the relative order of the non-zero elements.</p>

<p><strong>Note</strong> that you must do this in-place without making a copy of the array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [0,1,0,3,12]
<strong>Output:</strong> [1,3,12,0,0]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [0]
<strong>Output:</strong> [0]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you minimize the total number of operations done?

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def moveZeroes(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        left, n = 0, len(nums)
        for right in range(n):
            if nums[right] != 0:
                nums[left], nums[right] = nums[right], nums[left]
                left += 1
```

### **Java**

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int left = 0, n = nums.length;
        for (int right = 0; right < n; ++right) {
            if (nums[right] != 0) {
                int t = nums[left];
                nums[left] = nums[right];
                nums[right] = t;
                ++left;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        int left = 0, n = nums.size();
        for (int right = 0; right < n; ++right) {
            if (nums[right] != 0) {
                swap(nums[left], nums[right]);
                ++left;
            }
        }
    }
};
```

### **Go**

```go
func moveZeroes(nums []int) {
	n := len(nums)
	left := 0
	for right := 0; right < n; right++ {
		if nums[right] != 0 {
			nums[left], nums[right] = nums[right], nums[left]
			left++
		}
	}
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var moveZeroes = function (nums) {
    let left = 0,
        n = nums.length;
    for (let right = 0; right < n; ++right) {
        if (nums[right]) {
            [nums[left], nums[right]] = [nums[right], nums[left]];
            ++left;
        }
    }
};
```

```js
/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var moveZeroes = function (nums) {
    let left = 0;
    let right = left;
    while (left < nums.length) {
        if (nums[left] != 0) {
            left++;
        } else {
            right = left + 1;
            while (right < nums.length) {
                if (nums[right] == 0) {
                    right++;
                } else {
                    let tem = nums[left];
                    nums[left] = nums[right];
                    nums[right] = tem;
                    break;
                }
            }
            left++;
        }
    }
};
```

### **Rust**

```rust
impl Solution {
    pub fn move_zeroes(nums: &mut Vec<i32>) {
        let mut i = 0;
        for j in 0..nums.len() {
            if nums[j] != 0 {
                if i != j {
                    nums[i] = nums[j];
                    nums[j] = 0;
                }
                i += 1;
            }
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
