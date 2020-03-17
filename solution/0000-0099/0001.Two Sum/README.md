# [1. 两数之和](https://leetcode-cn.com/problems/two-sum/)

## 题目描述
<!-- 这里写题目描述 -->

给定一个整数数组 `nums` 和一个目标值 `target`，请你在该数组中找出和为目标值的那**两个**整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

**示例:**

```
给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
```

## 解法
<!-- 这里可写通用的实现逻辑 -->
利用 HashMap 记录数组元素值和对应的下标，对于一个数 nums[i]，判断 `target - nums[i]` 是否存在 HashMap 中，存在的话，返回两个下标组成的数组。注意，已存在的元素下标在前，当前元素下标在后。

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
```

### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### ...
```

```
