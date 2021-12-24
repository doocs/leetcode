# [面试题 56 - I. 数组中数字出现的次数](https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/)

## 题目描述

一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是 O(n)，空间复杂度是 O(1)。

**示例 1：**

```
输入：nums = [4,1,4,6]
输出：[1,6] 或 [6,1]
```

**示例 2：**

```
输入：nums = [1,2,10,4,1,4,3,3]
输出：[2,10] 或 [10,2]
```

**限制：**

- `2 <= nums <= 10000`

## 解法

异或运算求解。

首先明确，两个相同的数异或之后的结果为 0。对该数组所有元素进行异或运算，结果就是**两个只出现一次的数字异或的结果**，即 `eor = a ^ b`

找出这个结果 eor 中最后一个二进制位为 1 而其余位为 0 的数，即 `eor & (~eor + 1)`，之后遍历数组所有元素，二进制位为 0 的元素异或到 a。

遍历结束后 `b = eor ^ a`，返回结果即可。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def singleNumbers(self, nums: List[int]) -> List[int]:
        eor = 0
        for num in nums:
            eor ^= num
        # 找出最右边的 1
        diff = eor & (~eor + 1)
        a = 0
        for num in nums:
            if (num & diff) == 0:
                a ^= num
        b = eor ^ a
        return [a, b]
```

### **Java**

```java
class Solution {
    public int[] singleNumbers(int[] nums) {
        int eor = 0;
        for (int num : nums) {
            eor ^= num;
        }
        // # 找出最右边的 1
        int diff = eor & (~eor + 1);
        int a = 0;
        for (int num : nums) {
            if ((num & diff) == 0) {
                a ^= num;
            }
        }
        int b = eor ^ a;
        return new int[]{a, b};
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var singleNumbers = function (nums) {
    let eor = 0;
    for (let num of nums) {
        eor ^= num;
    }
    const diff = eor & (~eor + 1);
    let a = 0;
    for (let num of nums) {
        if ((num & diff) == 0) {
            a ^= num;
        }
    }
    let b = eor ^ a;
    return [a, b];
};
```

### **...**

```

```

<!-- tabs:end -->
