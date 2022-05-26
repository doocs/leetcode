# [17.04. Missing Number](https://leetcode.cn/problems/missing-number-lcci)

[中文文档](/lcci/17.04.Missing%20Number/README.md)

## Description

<p>An array&nbsp;contains all the integers from 0 to n, except for one number which is missing.&nbsp; Write code to find the missing integer. Can you do it in O(n) time?</p>

<p><strong>Note: </strong>This problem is slightly different from the original one the book.</p>

<p><strong>Example 1: </strong></p>

<pre>

<strong>Input: </strong>[3,0,1]

<strong>Output: </strong>2</pre>

<p>&nbsp;</p>

<p><strong>Example 2: </strong></p>

<pre>

<strong>Input: </strong>[9,6,4,2,3,5,7,0,1]

<strong>Output: </strong>8

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def missingNumber(self, nums: List[int]) -> int:
        res = 0
        for i, num in enumerate(nums):
            res = res ^ num ^ (i + 1)
        return res
```

### **Java**

```java
class Solution {
    public int missingNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            res = res ^ nums[i] ^ (i + 1);
        }
        return res;
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
    let res;
    for (let i = 0; i < nums.length; i++) {
        res = res ^ nums[i] ^ (i + 1);
    }
    return res;
};
```

### **C++**

```cpp
class Solution {
public:
    int missingNumber(vector<int>& nums) {
        int res = 0;
        for (int i = 0; i < nums.size(); ++i) {
            res = res ^ nums[i] ^ (i + 1);
        }
        return res;
    }
};
```

### **Rust**

```rust
impl Solution {
    pub fn missing_number(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let n = nums.len() as i32;
        for i in 0..n {
            if i != nums[i as usize] {
                return i;
            }
        }
        n
    }
}
```

```rust
impl Solution {
    pub fn missing_number(nums: Vec<i32>) -> i32 {
        let n = nums.len() as i32;
        let mut sum = 0;
        let mut max = 0;
        for num in nums {
            sum += num;
            max = max.max(num);
        }
        if max == n {
            ((1 + max) * max / 2) - sum
        } else {
            n
        }
    }
}
```

```rust
impl Solution {
    pub fn missing_number(nums: Vec<i32>) -> i32 {
        let mut res = 0;
        let n = nums.len();
        for i in 0..n {
            res ^= nums[i] ^ (i + 1) as i32;
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
