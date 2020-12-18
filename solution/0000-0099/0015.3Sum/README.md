# [15. 三数之和](https://leetcode-cn.com/problems/3sum)

[English Version](/solution/0000-0099/0015.3Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给你一个包含 <em>n</em> 个整数的数组&nbsp;<code>nums</code>，判断&nbsp;<code>nums</code>&nbsp;中是否存在三个元素 <em>a，b，c ，</em>使得&nbsp;<em>a + b + c = </em>0 ？请你找出所有满足条件且不重复的三元组。</p>

<p><strong>注意：</strong>答案中不可以包含重复的三元组。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
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
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        if nums is None or len(nums) < 3:
            return []
        nums.sort()
        n = len(nums)
        res = []
        for i in range(n - 2):
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            p, q = i + 1, n - 1
            while p < q:
                if p > i + 1 and nums[p] == nums[p - 1]:
                    p += 1
                    continue
                if q < n - 1 and nums[q] == nums[q + 1]:
                    q -= 1
                    continue
                if nums[i] + nums[p] + nums[q] < 0:
                    p += 1
                elif nums[i] + nums[p] + nums[q] > 0:
                    q -= 1
                else:
                    res.append([nums[i], nums[p], nums[q]])
                    p += 1
                    q -= 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n;
        if (nums == null || (n = nums.length) < 3) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 2; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int p = i + 1, q = n - 1;
            while (p < q) {
                if (p > i + 1 && nums[p] == nums[p - 1]) {
                    ++p;
                    continue;
                }
                if (q < n - 1 && nums[q] == nums[q + 1]) {
                    --q;
                    continue;
                }
                if (nums[p] + nums[q] + nums[i] < 0) {
                    ++p;
                } else if (nums[p] + nums[q] + nums[i] > 0) {
                    --q;
                } else {
                    res.add(Arrays.asList(nums[p], nums[q], nums[i]));
                    ++p;
                    --q;
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
