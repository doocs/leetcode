## 寻找旋转排序数组中的最小值
### 题目描述

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

请找出其中最小的元素。

你可以假设数组中不存在重复元素。

示例 1:
```
输入: [3,4,5,1,2]
输出: 1
```

示例 2:
```
输入: [4,5,6,7,0,1,2]
输出: 0   
```

### 解法
利用两个指针 p,q 指示数组的首尾两端，若 nums[p] <= nums[q]，说明数组呈递增趋势，返回 nums[p]；否则判断 nums[p] 与 nums[mid] 的大小，从而决定新的数组首尾两端，循环二分查找。

```java
class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        
        int p = 0;
        int q = n - 1;
        
        
        int mid = p + ((q - p) >> 1);
        
        while (p < q) {
            if (nums[p] <= nums[q]) {
                break;
            }
            
            if (nums[p] > nums[mid]) {
                q = mid;
            } else {
                p = mid + 1;
            }
            
            mid = p + ((q - p) >> 1);
        }
        
        return nums[p];
        
    }
}
```