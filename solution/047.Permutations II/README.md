## 全排列 II
### 题目描述

给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:
```
输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
```

### 解法
将数组的首元素依次与数组的每个元素交换(两元素不相等才进行交换)，对于每一轮交换，对后面的数组进行递归调用。当元素只剩下一个时，添加此时的数组到 list 中。

注意：第 i 个数字与第 j 个数字交换时，要求[i, j) 中没有与第 j 个数字相等的数。

解法①：

```java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        permute(list, nums, 0);
        return list;
    }
    
    private void permute(List<List<Integer>> list, int[] nums, int start) {
        int end = nums.length - 1;
        if (start == end) {
            List<Integer> tmp = new ArrayList<>();
            for (int val : nums) {
                tmp.add(val);
            }
            
            list.add(tmp);
            
        }
        
        for (int i = start; i <= end; ++i) {
            if (isSwap(nums, start, i)) {
                swap(nums, i, start);
                permute(list, nums, start + 1);
                swap(nums, i, start);
            }
            
        }
        
    }
    
    private boolean isSwap(int[] nums, int from, int to) {
        for (int i = from; i < to; ++i) {
            if (nums[i] == nums[to]) {
                // [from, to) 中出现与 第 to 个数相等的数，返回 false，不进行交换和全排列操作
                return false;
            }
        }
        return true;
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

解法②：

```java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        permute(list, nums, 0);
        return list;
    }
    
    private void permute(List<List<Integer>> list, int[] nums, int start) {
        int end = nums.length - 1;
        if (start == end) {
            List<Integer> tmp = new ArrayList<>();
            for (int val : nums) {
                tmp.add(val);
            }
            
            list.add(tmp);
            
        }
        
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= end; ++i) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        // 空间换取时间
        boolean[] flag = new boolean[max - min + 1];
        
        for (int i = start; i <= end; ++i) {
            int index = nums[i] - min;
            if (!flag[index]) {
                // 说明当前的数没在之前出现过
                swap(nums, i, start);
                permute(list, nums, start + 1);
                swap(nums, i, start);
                
                // 标志位设为 true
                flag[index] = true;
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