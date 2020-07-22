# [面试题56 - II. 数组中数字出现的次数 II](https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/)

## 题目描述
在一个数组 `nums` 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。

**示例 1：**

```
输入：nums = [3,4,3,3]
输出：4
```

**示例 2：**

```
输入：nums = [9,1,7,9,7,9,7]
输出：1
```

**限制：**

- `1 <= nums.length <= 10000`
- `1 <= nums[i] < 2^31`

## 解法
<!-- tabs:start -->

### **Python3**
```python
class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        bits = [0 for _ in range(32)]
        for num in nums:
            for i in range(32):
                bits[i] += (num & 1)
                num >>= 1
        
        res = 0
        for i in range(32):
            if bits[i] % 3 == 1:
                res += pow(2, i)
        return res
```

### **Java**
```java
class Solution {
    public int singleNumber(int[] nums) {
        int[] bits = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; ++i) {
                bits[i] += (num & 1);
                num >>= 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            if (bits[i] % 3 == 1) {
                res += (int) Math.pow(2, i);
            }
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
var singleNumber = function(nums) {
    let a = 0
    let b = 0
    for(let num of nums) {
        a = (a ^ num) & ~b
        b = (b ^ num) & ~a
    }
    return a
};
```

### **...**
```

```

<!-- tabs:end -->