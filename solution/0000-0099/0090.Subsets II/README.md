# [90. 子集 II](https://leetcode-cn.com/problems/subsets-ii)

[English Version](/solution/0000-0099/0090.Subsets%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个可能包含重复元素的整数数组 <em><strong>nums</strong></em>，返回该数组所有可能的子集（幂集）。</p>

<p><strong>说明：</strong>解集不能包含重复的子集。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> [1,2,2]
<strong>输出:</strong>
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        def dfs(nums, i, res, path):
            res.append(copy.deepcopy(path))
            for j in range(i, len(nums)):
                if j != i and nums[j] == nums[j - 1]:
                    continue
                path.append(nums[j])
                dfs(nums, j + 1, res, path)
                path.pop()
        res, path = [], []
        nums.sort()
        dfs(nums, 0, res, path)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, res, path);
        return res;
    }

    private void dfs(int[] nums, int i, List<List<Integer>> res, List<Integer> path) {
        res.add(new ArrayList<>(path));
        for (int j = i; j < nums.length; ++j) {
            if (j != i && nums[j] == nums[j - 1]) {
                continue;
            }
            path.add(nums[j]);
            dfs(nums, i + 1, res, path);
            path.remove(path.size() - 1);
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
