## 数组中两个数的最大异或值
### 题目描述

给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 2<sup>31</sup> 。

找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,  j < n 。

**示例:**
```
输入: [3, 10, 5, 25, 2, 8]
输出: 28
解释: 最大的结果是 5 ^ 25 = 28.
```

### 解法
异或运算被称为不做进位的二进制加法运算, 且具有一个性质：如果 a ^ b = c 成立，那么a ^ c = b 与 b ^ c = a 均成立。

分析一下题目, 要在数组中找到两个数对他们进行异或运算后得到一个最大的异或值, 即这个异或值二进制表示非 0 最高位要尽可能的靠左同时剩余位尽可能为 1；

整体使用贪心原则, 依次假设整数从左至右第 i 为 1, 然后再使用一个 mask 与数组中所有数相与得到数据前 i 位的一个前缀集合, 再把之前一次 `i-1` 循环所得到的 max 加第 i 位；为 1 得到当前 i 循环中期望的 `pre-max`, 再与前缀集合中的所有数进行异或运算, 如果得到的值也同时在集合中, 表示假设成立, `max` 变为 `pre-max`, 否则直接 `i+1` 进行下一个循环, 直到 `i=0` 算法结束。

```java
class Solution {
    public int findMaximumXOR(int[] numbers) {
        int max = 0;
        int mask = 0;
        for (int i = 30; i >= 0; i--) {
            int current = 1 << i;
            // 期望的二进制前缀
            mask = mask ^ current;
            // 在当前前缀下, 数组内的前缀位数所有情况集合
            Set<Integer> set = new HashSet<>();
            for (int j = 0, k = numbers.length; j < k; j++) {
                set.add(mask & numbers[j]);
            }
            // 期望最终异或值的从右数第i位为1, 再根据异或运算的特性推算假设是否成立
            int flag = max | current;
            for (Integer prefix : set) {
                if (set.contains(prefix ^ flag)) {
                    max = flag;
                    break;
                }
            }
        }
        return max;
    }
}
```
