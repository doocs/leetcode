# [面试题 17.04. 消失的数字](https://leetcode-cn.com/problems/missing-number-lcci)

[English Version](/lcci/17.04.Missing%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>数组<code>nums</code>包含从<code>0</code>到<code>n</code>的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？</p>

<p><strong>注意：</strong>本题相对书上原题稍作改动</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[3,0,1]
<strong>输出：</strong>2</pre>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[9,6,4,2,3,5,7,0,1]
<strong>输出：</strong>8
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

利用异或的特性，`res = res ^ x ^ x`。对同一个值异或两次，结果等于它本身。最后异或的结果，就是只出现一次的数字，即数组中缺失的整数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def missingNumber(self, nums: List[int]) -> int:
        res = 0
        for i, num in enumerate(nums):
            res = res ^ num ^ (i + 1)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
var missingNumber = function(nums) {
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

### **...**

```

```

<!-- tabs:end -->
