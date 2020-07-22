# [面试题39. 数组中出现次数超过一半的数字](https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/)

## 题目描述
数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

**示例 1:**

```
输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
输出: 2
```

**限制：**

- `1 <= 数组长度 <= 50000`

## 解法
摩尔投票法。时间复杂度 O(n)，空间复杂度 O(1)。

<!-- tabs:start -->

### **Python3**
```python
class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        cnt = major = 0
        for num in nums:
            if cnt == 0:
                major = num
                cnt += 1
            else:
                cnt += (1 if major == num else -1)
        return major
```

### **Java**
```java
class Solution {
    public int majorityElement(int[] nums) {
        int major = 0, cnt = 0;
        for (int num : nums) {
            if (cnt == 0) {
                major = num;
                ++cnt;
            } else {
                cnt += (num == major ? 1 : -1);
            }
        }
        return major;
    }
}
```

### **JavaScript**
```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var majorityElement = function(nums) {
    let cnt = 0
    let mode = -1
    for(let num of nums) {
        if(!cnt) {
            mode = num
            cnt++
        } else {
            if(mode === num) cnt++
            else cnt--
        }
    }
    return mode
    // return nums.sort((a,b)=>a-b)[~~(nums.length/2)]
};
```

### **...**
```

```

<!-- tabs:end -->