## 搜索旋转排序数组
### 题目描述

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 O(log n) 级别。

```
示例 1:
输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4
```

```
示例 2:
输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1
```


----------------------
### 思路：
因为是排序数组，而且要求是log2(n)时间搜索，所以优先选择用二分搜索法，但是这道题的难点就是不知道原数组的旋转位置在哪里，无法从中间值对比过程中直接进行二分搜索

我们还是用题目中给的例子来分析，对于数组[0 1 2 4 5 6 7] 共有下列七种旋转方法：

0　　1　　2　　 4　　5　　6　　7

7　　0　　1　　 **2　　4　　5　　6**

6　　7　　0　　 **1　　2　　4　　5**

5　　6　　7　　 **0　　1　　2　　4**

**4　　5　　6　　7**　　0　　1　　2

**2　　4　　5　　6**　　7　　0　　1

**1　　2　　4　　5**　　6　　7　　0

二分搜索法的关键点在于，在与中间值进行对比后，大于则在右半部分搜索，小于则在左半部分搜索，反复进行达到收敛。

我们观察上面加粗的部分，如果：
中间值比最右值小，则右半部有序递增
中间值比最右值大，则左半部有序递增

通过这个特点，对该数组进行二分搜索

```C++
class Solution {
public:
    int search(vector<int>& nums, int target) {
        int len = nums.size();
        int left = 0;
        int right = len - 1;
        int mid;
        while(left <= right) {
            mid = (left + right) / 2;
            if(nums[mid] == target)return mid;
            
            if(nums[mid] < nums[right]) {
                if(nums[right] >= target && nums[mid] < target)left = mid + 1;
                else right = mid - 1;
            }
            else {
                if(nums[left] <= target && nums[mid] > target)right = mid - 1;
                else left = mid + 1;
            }
        }
        return -1;    
    }
};

```
-------------------------
我发现其实这种暴力枚举，时间差不了多少，各位赶时间就直接暴力吧！！！

```C++
class Solution {
public:
    int search(vector<int>& nums, int target) {
        int len = nums.size();
        for(int i = 0;i<len;i++) {
            if(target == nums[i])return i;
        }
        
        return -1;
    }
};
```