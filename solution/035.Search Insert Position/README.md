## 搜索位置描述
### 题目描述


给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

你可以假设数组中无重复元素。

**示例 1:**
```
输入: [1,3,5,6], 5
输出: 2
```

**示例 2:**
```
输入: [1,3,5,6], 2
输出: 1
```

**示例 3:**
```
输入: [1,3,5,6], 7
输出: 4
```

**示例 4:**
```
输入: [1,3,5,6], 0
输出: 0
```

### 解法
首先判断传入的数组为 0,1 这样的长度。

因为是一个给定的排序数组，在循环时就可以判断是否存在的同时判断大小，有相同的则直接返回索引，
不存在则判断大小，只要相较于当前索引的元素较小，则可以认为该目标数在数组中无对应元素，直接返回索引即可。

除此之外还可用二分法做解。


```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            if(nums[0] < target) {
                return 1;
            } else {
                return 0;
            }
        }
        for(int i = 0;i < nums.length;i++) {
            if(nums[i] == target) {
                return i;
            } else {
                int s = Math.min(nums[i],target);
                if(s == target) {
                    return i;
                }
            }
        }
        return nums.length;
    }
}
```

- 二分法
```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
```

#### CPP

思路1：

1. 先调函数查找是否存在target元素
2. 若存在，用二分法进行查找，或者顺序遍历
3. 若不存在，则顺序遍历插入

时间复杂度O(log2(n))~O(n)

思路2：
 1. 直接顺序遍历---需要点取巧，下标比nums长度小，nums[p]元素要比targat小

 时间复杂度O(n)

```CPP
class Solution {
public:
    int searchInsert(vector<int>& nums, int target) {
        int len = nums.size();
        if(len == 0){
            nums.push_back(target);
            return 0;
        }

        auto iter = find(nums.begin(),nums.end(),target);
        
        if(iter != nums.end()){
           return binarySearch(nums,0,len - 1,target);
        }
        else{
            int slow = 0;
            int fast = 1;
            
            if(nums[0] >= target)return 0;
            if(nums[len-1] <= target)return len;
            
            while(fast < len){
                if(nums[slow] <= target && nums[fast] > target){
                    nums.insert(nums.begin() + fast,target);
                    return fast;
                }
                else{
                    slow++;
                    fast++;
                }
            }
            return fast;
        } 
    }
    
    
    int binarySearch(vector<int> &nums,int left,int right,int target){
        if(nums[left] == target)return left;
        if(nums[right] == target)return right;
        
        int mid = (left + right) / 2;
        
        if(nums[mid] > target)return binarySearch(nums,left+1,mid,target);
        else if(nums[mid] < target)return binarySearch(nums,mid,right-1,target);
        else return mid;
    }
    
};

-------------------------
class Solution {
public:
    int searchInsert(vector<int>& nums, int target) {
        if (nums.size() == 0) {
            nums.push_back(target);
            return 0;
        }
        unsigned int i = 0;
        
        while(i<nums.size()&&nums[i]<target)i++;

        nums.insert(nums.begin() + i, target);
        return i;
    }
};

```
