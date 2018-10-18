## 四数之和
### 题目描述

给定一个包含 n 个整数的数组 `nums` 和一个目标值 `target`，判断 `nums` 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 `target` 相等？找出所有满足条件且不重复的四元组。

**注意：**

答案中不可以包含重复的四元组。

**示例：**
```
给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

满足要求的四元组集合为：
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
```


### 解法

#### 解法一
1. 将数组排序；
2. 先假设确定一个数 nums[i] 将 4Sum 问题转换为 3Sum 问题；
3. 再假设确定一个数将 3Sum 问题转换为 2Sum 问题；
4. 对排序数组，用首尾指针向中间靠拢的思路寻找满足 target 的 nums[l] 和 nums[k]

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> re = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return re;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {

            // 当 nums[i] 对应的最小组合都大于 target 时，后面大于 nums[i] 的组合必然也大于 target，
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            // 当 nums[i] 对应的最大组合都小于 target 时， nums[i] 的其他组合必然也小于 target
            if (nums[i] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                continue;
            }

            int firstNum = nums[i];
            for (int j = i + 1; j < nums.length - 2; j++) {

                // nums[j] 过大时，与 nums[i] 过大同理
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                // nums[j] 过小时，与 nums[i] 过小同理
                if (nums[i] + nums[j] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                    continue;
                }

                int twoSum = target - nums[i] - nums[j];
                int l = j + 1;
                int k = nums.length - 1;
                while (l < k) {
                    int tempSum = nums[l] + nums[k];
                    if (tempSum == twoSum) {
                        ArrayList<Integer> oneGroup = new ArrayList<>(4);
                        oneGroup.add(nums[i]);
                        oneGroup.add(nums[j]);
                        oneGroup.add(nums[l++]);
                        oneGroup.add(nums[k--]);
                        re.add(oneGroup);
                        while (l < nums.length && l < k && nums[l] == oneGroup.get(2) && nums[k] == oneGroup.get(3)) {
                            l++;
                            k--;
                        }
                    } else if (tempSum < twoSum) {
                        l++;
                    } else {
                        k--;
                    }
                }
                // 跳过重复项
                while ((j < nums.length - 2) && (twoSum + nums[i] + nums[j + 1] == target)) {
                    j++;
                }
            }
            // 跳过重复项
            while (i < nums.length - 3 && nums[i + 1] == firstNum) {
                i++;
            }
        }
        return re;
    }
}
```

#### 解法二
对数组进行排序，利用指针 `i`, `j` 固定前两个数，`p`, `q` 指向剩余数组的首尾，判断四数和是否为 `target`：
- 若是，添加到 `list` 中。此时 右移 `p` 直到 `nums[p] != nums[p - 1]`（为了去重）。同样，`q` 左移，进行去重。
- 若四数和大于 `target`，`q` 指针左移；否则 `p` 指针右移。
- 对于外面的两层 `for` 循环，同样需要进行去重操作。

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> list = new ArrayList<>();
        int p = 0;
        int q = 0;
        for (int i = 0; i < n - 3; ++i) {
            for (int j = i + 1; j < n - 2; ++j) {
                p = j + 1;
                q = n - 1;
                while (p < q) {
                    int val = nums[i] + nums[j] + nums[p] + nums[q];
                    if (val == target) {
                        list.add(Arrays.asList(nums[i], nums[j], nums[p], nums[q]));
                        // p 指针右移，直到 nums[p] 与 nums[p - 1] 不等
                        ++p;
                        while (p < q && nums[p] == nums[p - 1]) {
                            ++p;
                        }
                        --q;
                        while (p < q && nums[q] == nums[q + 1]) {
                            --q;
                        }
                    } else if (val > target) {
                        --q;
                    } else {
                        q = val > target ? q - 1 : q;
                        p = val < target ? p + 1 : p;
                    }
                }
                
                // j < n - 3：保证 j 不会溢出
                while (j < n - 3 && nums[j] == nums[j + 1]) {
                    ++j;
                }
            }
            
            // i < n - 4：保证 i 不会溢出
            while (i < n - 4 && nums[i] == nums[i + 1]) {
                ++i;
            }
        }
        return list;
    }
}
```