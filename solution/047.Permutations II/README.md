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
            
            // 已存在，则不添加
            if (!list.contains(tmp)) {
                list.add(tmp);
                return;
            }
            
        }
        
        for (int i = start; i <= end; ++i) {
            if (i == start) {
                permute(list, nums, start + 1);
            } else if (nums[i] != nums[start]) {
                // i 与 start 不相等，且对应的数组值也不相等，才进行交换
                swap(nums, i, start);
                permute(list, nums, start + 1);
                swap(nums, i, start);
            }
            
        }
        
        
    }
    
    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```