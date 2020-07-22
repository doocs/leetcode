# [面试题57. 和为s的两个数字](https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof/)

## 题目描述
输入一个递增排序的数组和一个数字 s，在数组中查找两个数，使得它们的和正好是 s。如果有多对数字的和等于 s，则输出任意一对即可。

**示例 1：**

```
输入：nums = [2,7,11,15], target = 9
输出：[2,7] 或者 [7,2]
```

**示例 2：**

```
输入：nums = [10,26,30,31,47,60], target = 40
输出：[10,30] 或者 [30,10]
```

**限制：**

- `1 <= nums.length <= 10^5`
- `1 <= nums[i] <= 10^6`

## 解法
<!-- tabs:start -->

### **Python3**
```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        p, q = 0, len(nums) - 1
        while p < q:
            s = nums[p] + nums[q]
            if s == target:
                return [nums[p], nums[q]]
            if s < target: 
                p += 1
            else: 
                q -= 1

```

### **Java**
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int p = 0, q = nums.length - 1;
        while (p < q) {
            int s = nums[p] + nums[q];
            if (s == target) {
                return new int[] {nums[p], nums[q]};
            }
            if (s < target) {
                ++p;
            } else {
                --q;
            }
        }
        return new int[]{0};
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
var twoSum = function(nums, target) {
    let left = 0
    let right = nums.length - 1
    while(left < right) {
        let sum = nums[left] + nums[right]
        if(sum === target) {
            return [nums[left],nums[right]]
        } else if(sum > target) {
            right--
        } else {
            left++
        }
    }
    
};
```

### **...**
```

```

<!-- tabs:end -->