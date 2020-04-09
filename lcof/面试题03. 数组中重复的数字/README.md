# [面试题03. 数组中重复的数字](https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

## 题目描述
找出数组中重复的数字。


在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

**示例 1：**

```
输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3 
``` 

**限制：**

```
2 <= n <= 100000
```

## 解法
### Python3
```python
class Solution:
    def findRepeatNumber(self, nums: List[int]) -> int:
        for i, num in enumerate(nums):
            while i != num:
                if num == nums[num]:
                    return num
                nums[i], nums[num] = nums[num], nums[i]

```

### Java
```java
class Solution {
    public int findRepeatNumber(int[] nums) {
        for (int i = 0, len = nums.length; i < len; ++i) {
            while (i != nums[i]) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

### ...
```

```