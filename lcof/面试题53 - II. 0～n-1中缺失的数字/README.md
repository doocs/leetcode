# [面试题 53 - II. 0 ～ n-1 中缺失的数字](https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/)

## 题目描述

<p>一个长度为 n-1 的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> [0,1,3]
<strong>输出:</strong> 2
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> [0,1,2,3,4,5,6,7,9]
<strong>输出:</strong> 8</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>1 &lt;= 数组长度 &lt;= 10000</code></p>

## 解法

二分法。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def missingNumber(self, nums: List[int]) -> int:
        l, r = 0, len(nums) - 1
        if r == 0 or nums[0] == 1:
            return nums[0] ^ 1
        if nums[r] == r:
            return r + 1
        while r - l > 1:
            m = (l + r) >> 1
            if nums[m] == m:
                l = m
            else:
                r = m
        return nums[r] - 1
```

### **Java**

```java
class Solution {
    public int missingNumber(int[] nums) {
        int l = 0, r = nums.length - 1;
        if (r == 0 || nums[0] == 1) {
            return nums[0] ^ 1;
        }
        if (nums[r] == r) {
            return r + 1;
        }
        while (r - l > 1) {
            int m = (l + r) >>> 1;
            if (nums[m] == m) {
                l = m;
            } else {
                r = m;
            }
        }
        return nums[r] - 1;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var missingNumber = function (nums) {
    if (!nums || !nums.length) return 0;
    let left = 0;
    let right = nums.length - 1;
    while (left < right) {
        let mid = left + ~~((right - left) / 2);
        if (nums[mid] !== mid) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return nums[left] === left ? nums.length : left;
};
```

### **C++**

```cpp
class Solution {
public:
    int missingNumber(vector<int>& nums) {
        int left = 0, right = nums.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
};
```

### **Rust**

```rust
impl Solution {
    pub fn missing_number(nums: Vec<i32>) -> i32 {
        let n = nums.len() as i32;
        let mut sum = (1 + n) * n / 2;
        for num in nums.iter() {
            sum -= num;
        }
        sum
    }
}
```

```rust
impl Solution {
    pub fn missing_number(nums: Vec<i32>) -> i32 {
        let mut prev = 0;
        for &num in nums.iter() {
            if prev != num {
                return prev;
            }
            prev += 1;
        }
        prev
    }
}
```

### **C#**

```cs
public class Solution {
    public int MissingNumber(int[] nums) {
        int l = 0, r = nums.Length - 1;
        if (r == 0 || nums[0] == 1) {
            return nums[0] ^ 1;
        }
        if (nums[r] == r) {
            return r + 1;
        }
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] == mid) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
