## 下一个排列
### 题目描述

实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须原地修改，只允许使用额外常数空间。

以下是一些例子，输入位于左侧列，其相应输出位于右侧列。

`1,2,3` → `1,3,2`

`3,2,1` → `1,2,3`

`1,1,5` → `1,5,1`

### 解法
从后往前，找到第一个升序状态的位置，记为 i-1，在[i, length - 1] 中找到比 nums[i - 1] 大的，且差值最小的元素（如果有多个差值最小且相同的元素，取后者），进行交换。将后面的数组序列升序排列，保存。然后恢复 i，继续循环。


```java
class Solution {
    public void nextPermutation(int[] nums) {
        boolean flag = false;
        for (int i = nums.length - 2; i >= 0; --i) {
            if (nums[i] < nums[i + 1]) {
                int index = findMinIndex(nums, i, nums[i]);
                swap(nums, i, index);
                reverse(nums, i + 1);
                flag = true;
                break;
            }
        }
        if (!flag) {
            Arrays.sort(nums);
        }
    }
    
    private void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
    
    /**
     * 找出从start开始的比val大的最小元素的下标，如果有多个，选择后者 
     *
     * @param name
     * @param start
     * @param val
     * @return index
     */
    private int findMinIndex(int[] nums, int start, int val) {
        int end = nums.length - 1;
        int i = start;
        for (; i < end; ++i) {
            if (nums[i + 1] <= val) {
                break;
            }
        }
        return i;
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```