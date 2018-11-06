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

解法①：

将数组的首元素依次与数组的每个元素交换(两元素不相等才进行交换)，对于每一轮交换，对后面的数组进行递归调用。当元素只剩下一个时，添加此时的数组到 list 中。

注意：第 i 个数字与第 j 个数字交换时，要求[i, j) 中没有与第 j 个数字相等的数。

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

利用空间换取时间，减少 n^2 复杂度。这里的空间，可以采用数组，或者 HashMap。

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

解法➂：

这是非递归的解法。思路就是：

- 先保存升序数组；
- 找到下一个比该数组大的数组，保存；
- 直到数组呈降序。

如何找到下一个数组呢？从后往前，找到第一个升序状态的位置，记为 i-1，在[i, length - 1] 中找到比 nums[i - 1] 大的，且差值最小的元素（如果有多个差值最小且相同的元素，取后者），进行交换。将后面的数组序列升序排列，保存。然后恢复 i，继续循环。

```java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // 先排序，并添加至 list 中
        Arrays.sort(nums);
        addList(list, nums);
        permute(list, nums);
        return list;
    }
    
    private void permute(List<List<Integer>> list, int[] nums) {
        int n = nums.length;
        for (int i = n - 1; i > 0; --i) {
            if (nums[i - 1] < nums[i]) {
                // 在数组右边找到比 nums[i - 1] 大的，但差值最小的数的下标
                // 若有多个最小差值，取后者
                int index = findMinIndex(nums, i, nums[i - 1]);
                swap(nums, i - 1, index);
                sortArr(nums, i);
                addList(list, nums);
                i = n;
            }
        }
        
    }
    
    private void sortArr(int[] nums, int start) {
        int n = nums.length - 1;
        while (start < n) {
            swap(nums, start, n);
            ++start;
            --n;
        }
    }
    
    private int findMinIndex(int[] nums, int start, int val) {
        int index = start;
        int distance = nums[start] - val;
        for (int i = start; i < nums.length; ++i) {
            if (nums[i] > val && (nums[i] - val <= distance)) {
                distance = nums[i] - val;
                index = i;
            }
        }
        return index;
    }
    
    private void addList(List<List<Integer>> list, int[] nums) {
        List<Integer> tmpList = new ArrayList<>();
        for (int val : nums) {
            tmpList.add(val);
        }
        list.add(tmpList);
        
    }
    
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```