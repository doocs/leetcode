# [面试题61. 扑克牌中的顺子](https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof/)

## 题目描述
<!-- 这里写题目描述 -->
从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。

**示例 1:**

```
输入: [1,2,3,4,5]
输出: True
```

**示例 2:**

```
输入: [0,0,1,2,5]
输出: True
```

**限制：**

- 数组长度为 5 
- 数组的数取值为 `[0, 13]`
## 解法
<!-- 这里可写通用的实现逻辑 -->
用数组 t 记录是否存在重复的数，存在则直接返回 false。

遍历数组，忽略大小王(0)，求出数组的最大、最小值。若差值超过 4，则无法构成顺子，例如：`5,6,(0),8,10`。

### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isStraight(self, nums: List[int]) -> bool:
        t = [False for _ in range(14)]
        max_val, min_val = 0, 14
        for num in nums:
            if num == 0:
                continue
            if t[num]:
                return False
            t[num] = True
            max_val = max(max_val, num)
            min_val = min(min_val, num)
        return max_val - min_val <= 4


```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isStraight(int[] nums) {
        boolean[] t = new boolean[14];
        int maxVal = Integer.MIN_VALUE, minVal = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            if (t[num]) {
                return false;
            }
            t[num] = true;
            maxVal = Math.max(maxVal, num);
            minVal = Math.min(minVal, num);
        }
        return maxVal - minVal <= 4;
    }
}
```

### ...
```

```
