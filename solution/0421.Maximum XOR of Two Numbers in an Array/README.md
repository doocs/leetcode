## 数组中两个数的最大异或值
### 题目描述

给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。

找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,  j < n 。

示例:
```
输入: [3, 10, 5, 25, 2, 8]

输出: 28

解释: 最大的结果是 5 ^ 25 = 28.
```

### 解法
异或运算被称为不做进位的二进制加法运算, 且具有一个性质：如果 a ^ b = c 成立，那么a ^ c = b 与 b ^ c = a 均成立。 
再分析一下题目, 要在数组中找到两个数对他们进行异或运算后得到一个最大的异或值, 即这个异或值二进制表示非0最高位要尽可能的靠左同时剩余位尽可能为1; 
那两个数中必然有一个它的最高位1一定是数组中所有数转化为二进制后最高的非0位的位数;
同样我们可以找到除了第一位后的后续最优第二个; 我们使用贪心算法, 从高位一直往后退, 找到第一个出现非0位的数即找到第一个数, 然后再剩下的数里面找第二个数;

```java
class Solution {

    public int findMaximumXOR(int[] numbers) {
        Set<Integer> oneNubmer = new HashSet<>();
        Set<Integer> twoNubmer = new HashSet<>();
        // oneNumber not has number
        int[] oneNumberSet = null;
        int[] twoNumberSet = null;
        boolean flag = true;
        for (int i = 31; i > 0; i--) {
            int mask = 1 << (i - 1);
            for (int j = 0; j < numbers.length; j++) {
                if (flag) {
                    // deal oneNumber
                    if ((mask & numbers[j]) > 0) {
                        oneNubmer.add(numbers[j]);
                    }
                } else {
                    // deal twoNumber
                    if (!twoNubmer.contains(numbers[j])) {
                        for (int k = 0; k < oneNumberSet.length; k++) {
                            if ((mask & (numbers[j] ^ oneNumberSet[k])) > 0) {
                                twoNubmer.add(numbers[j]);
                            }
                        }
                    }
                }
            }
            if (flag && oneNubmer.size() > 0) {
                flag = false;
                oneNumberSet = oneNubmer.stream().mapToInt(Integer::valueOf).toArray();
            }
            if (twoNubmer.size() > 0) {
                twoNumberSet = twoNubmer.stream().mapToInt(Integer::valueOf).toArray();
                break;
            }
        }
        // 处理两种特殊的结果集
        if (oneNubmer.size() == 0) {
            return -1;
        } else {
            if (twoNubmer.size() == 0) {
                return oneNumberSet[0];
            }
        }
        // 获取最大异或结果(也可以得到是哪种组合)
        int count = 0;
        for (int x = 0; x < oneNumberSet.length; x++) {
            for (int y = 0; y < twoNumberSet.length; y++) {
                if ((oneNumberSet[x] ^ twoNumberSet[y]) > count) {
                    count = oneNumberSet[x] ^ twoNumberSet[y];
                }
            }
        }
        return count;
    }
}
```
