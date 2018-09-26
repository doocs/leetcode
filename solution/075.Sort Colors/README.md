## 颜色分类
### 题目描述

给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

注意:
不能使用代码库中的排序函数来解决这道题。

示例:
```
输入: [2,0,2,1,1,0]
输出: [0,0,1,1,2,2]
```

进阶：

- 一个直观的解决方案是使用计数排序的两趟扫描算法。
首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
- 你能想出一个仅使用常数空间的一趟扫描算法吗？

### 解法
指针 p 指示左侧等于 0 区域的最后一个元素，q 指示右侧等于 2 的第一个元素。p, q 初始分别指向 -1, nums.length。
cur 从 0 开始遍历：

- 若 nums[cur] == 0，则与 p 的下一个位置的元素互换，p+1， cur+1；
- 若 nums[cur] == 1，++cur；
- 若 nums[cur] == 2，则与 q 的前一个位置的元素互换，q-1，cur不变。

```java
class Solution {
    public void sortColors(int[] nums) {
        int p = -1;
        int q = nums.length;
        int cur = 0;
        while (cur < q) {
            if (nums[cur] == 0) {
                swap(nums, cur++, ++p);
            } else if (nums[cur] == 1) {
                ++cur;
            } else {
                swap(nums, --q, cur);
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```