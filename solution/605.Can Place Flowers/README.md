## 种花问题
### 题目描述

假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。

给定一个花坛（表示为一个数组包含 0 和 1，其中 0 表示没种植花，1 表示种植了花），和一个数 `n` 。能否在不打破种植规则的情况下种入 `n` 朵花？能则返回 `True`，不能则返回 `False`。

**示例 1:**
```
输入: flowerbed = [1,0,0,0,1], n = 1
输出: True
```

**示例 2:**
```
输入: flowerbed = [1,0,0,0,1], n = 2
输出: False
```

**注意:**

- 数组内已种好的花不会违反种植规则。
- 输入的数组长度范围为 [1, 20000]。
- n 是非负整数，且不会超过输入数组的大小。

### 解法
遍历数组，若当前位置的元素为 0 并且左右元素也为 0，则该位置可以种花，计数器 +1。

注意，需要特殊处理数组首尾元素。

```java
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int cnt = 0;
        for (int i = 0; i < len; ++i) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == len - 1 || flowerbed[i + 1] == 0)) {
                ++cnt;
                flowerbed[i] = 1;
            }
        }
        return cnt >= n;
    }
}
```