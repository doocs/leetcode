## 最短无序连续子数组
### 题目描述

给定一个整数数组，你需要寻找一个**连续的子数组**，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

你找到的子数组应是最短的，请输出它的长度。

**示例 1:**
```
输入: [2, 6, 4, 8, 10, 9, 15]
输出: 5
解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
```

说明 :

- 输入的数组长度范围在 [1, 10,000]。
- 输入的数组可能包含**重复**元素 ，所以**升序**的意思是<=。

### 解法
对数组排序，形成新数组，利用两个指针从头尾往中间遍历两数组，若对应位置的元素不同，则记录该下标，最后返回两个下标差。

```java
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = nums[i];
        }
        Arrays.sort(res);
        int p = 0;
        for (; p < n; ++p) {
            if (res[p] != nums[p]) {
                break;
            }
        }
        int q = n - 1;
        for (; q >= 0; --q) {
            if (res[q] != nums[q]) {
                break;
            }
        }
        return p == n ? 0 : q - p + 1 ;
        
    }
}
```