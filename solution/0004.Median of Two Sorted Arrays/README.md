## 两个排序数组的中位数
### 题目描述

给定两个大小为 m 和 n 的有序数组 **nums1** 和 **nums2** 。

请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。

你可以假设 **nums1** 和 **nums2** 不同时为空。

**示例 1:**
```
nums1 = [1, 3]
nums2 = [2]

中位数是 2.0
```

**示例 2:**
```
nums1 = [1, 2]
nums2 = [3, 4]

中位数是 (2 + 3)/2 = 2.5
```

### 解法
假设两数组长度分别为 len1, len2，分别将 num1, num2 切成左右两半。
举个栗子：

```
nums1: num1[0] num1[1] num1[2]......num1[i - 1] | num1[i] ...nums1[len1 - 2] nums1[len1 - 1]

nums2: nums2[0] nums2[1] nums2[2]......nums2[j - 1] | nums2[j] ...nums2[len2 - 2] nums2[len2 - 1]

```
num1 在[0, i - 1] 是左半部分，[i, len1 - 1] 是右半部分；
num2 在[0, j - 1] 是左半部分，[j, len2 - 1] 是右半部分。

若两个左半部分合起来的最大值 `<=` 右半部分合起来的最小值。那么中位数就可以直接拿到了。

- 若 nums1[i - 1] > nums2[j]，说明 num1 的左边有数据过大，应该放到右边，而这样会使左边总数少了，那么 num2 右边的一个给左边就平衡了。如下：
```
nums1: num1[0] num1[1] num1[2]......num1 | [i - 1] num1[i] ...nums1[len1 - 2] nums1[len1 - 1]

nums2: nums2[0] nums2[1] nums2[2]......nums2[j - 1] nums2[j] |  ...nums2[len2 - 2] nums2[len2 - 1]
```

- 若 nums2[j - 1] > nums1[i]，同理。
- 否则，计算中位数。


```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        if (len1 > len2) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
            int t = len1;
            len1 = len2;
            len2 = t;
        }

        int min = 0;
        int max = len1;

        int m = (len1 + len2 + 1) / 2;

        while (min <= max) {
            int i = (min + max) / 2;
            int j = m - i;

            if (i > min && nums1[i - 1] > nums2[j]) {
                --max;
            } else if (i < max && nums2[j - 1] > nums1[i]) {
                ++min;
            } else {

                int maxLeft = i == 0 ? nums2[j - 1] : j == 0 ? nums1[i - 1] : Math.max(nums1[i - 1], nums2[j - 1]);

                if (((len1 + len2) & 1) == 1) {
                    return maxLeft;
                }

                int minRight = i == len1 ? nums2[j] : j == len2 ? nums1[i] : Math.min(nums2[j], nums1[i]);

                return (maxLeft + minRight) / 2.0;

            }

        }

        return 0;
    }
}
```