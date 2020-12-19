# [18. 四数之和](https://leetcode-cn.com/problems/4sum)

[English Version](/solution/0000-0099/0018.4Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个包含&nbsp;<em>n</em> 个整数的数组&nbsp;<code>nums</code>&nbsp;和一个目标值&nbsp;<code>target</code>，判断&nbsp;<code>nums</code>&nbsp;中是否存在四个元素 <em>a，</em><em>b，c</em>&nbsp;和 <em>d</em>&nbsp;，使得&nbsp;<em>a</em> + <em>b</em> + <em>c</em> + <em>d</em>&nbsp;的值与&nbsp;<code>target</code>&nbsp;相等？找出所有满足条件且不重复的四元组。</p>

<p><strong>注意：</strong></p>

<p>答案中不可以包含重复的四元组。</p>

<p><strong>示例：</strong></p>

<pre>给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

满足要求的四元组集合为：
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“排序 + 双指针”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
        res = []
        if nums is None or len(nums) < 4:
            return res
        n = len(nums)
        nums.sort()
        for i in range(n - 3):
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            for j in range(i + 1, n - 2):
                if j > i + 1 and nums[j] == nums[j - 1]:
                    continue
                p, q = j + 1, n - 1
                while p < q:
                    if p > j + 1 and nums[p] == nums[p - 1]:
                        p += 1
                        continue
                    if q < n - 1 and nums[q] == nums[q + 1]:
                        q -= 1
                        continue
                    t = nums[i] + nums[j] + nums[p] + nums[q]
                    if t == target:
                        res.append([nums[i], nums[j], nums[p], nums[q]])
                        p += 1
                        q -= 1
                    elif t < target:
                        p += 1
                    else:
                        q -= 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n;
        if (nums == null || (n = (nums.length)) < 4) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 3; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 2; ++j) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int p = j + 1, q = n - 1;
                while (p < q) {
                    if (p > j + 1 && nums[p] == nums[p - 1]) {
                        ++p;
                        continue;
                    }
                    if (q < n - 1 && nums[q] == nums[q + 1]) {
                        --q;
                        continue;
                    }
                    int t = nums[i] + nums[j] + nums[p] + nums[q];
                    if (t == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[p], nums[q]));
                        ++p;
                        --q;
                    } else if (t < target) {
                        ++p;
                    } else {
                        --q;
                    }
                }
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
