## 移除元素
### 题目描述

给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

示例 1:

给定 nums = [3,2,2,3], val = 3,

函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。

你不需要考虑数组中超出新长度后面的元素。
示例 2:

给定 nums = [0,1,2,2,3,0,4,2], val = 2,

函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。

注意这五个元素可为任意顺序。

你不需要考虑数组中超出新长度后面的元素。

### 解法
1. 维护 i 和 end 两个指针，end 指向数组尾部，i 从左向右遍历数组, 
2. 若 nums[i] == val, 则把数组尾部的值 nums[end] 拷贝至 i 的位置，然后将 end 指针向左移动；否则，i 向右移动，继续遍历数组。
3. 这样当两个 i 与 end 相遇时，end 左边的所以 val 元素都被 end 右边的非 val 元素替换。

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int end = nums.length - 1;
        int i = 0;
        while(i <= end) {
            if(nums[i] == val) {
                nums[i] = nums[end];
                end--;
            }
            else {
                i++;
            }
        }
        return end + 1;
        
    }
}
```

#### CPP

```CPP
class Solution {
public:
    int removeElement(vector<int>& nums, int val) {
        int len = nums.size();
        if(len < 1)return 0;
        auto iter = find(nums.begin(),nums.end(),val);
        while(iter != nums.end())
        {
            nums.erase(iter);
            iter = find(nums.begin(),nums.end(),val);
        }
        len = nums.size();
        
        return len;
    }
};
--------------------------------------------------
class Solution {
public:
    int removeElement(vector<int>& nums, int val) {
        int len = nums.size();
        if(len < 1)return 0;
        
        int i = 0;
        while(i < len)
        {
            if(nums[i] == val){
                nums[i] = nums[len - 1];
                len--;
            }    
            else i++;
        }
        
        return len;
    }
};
```