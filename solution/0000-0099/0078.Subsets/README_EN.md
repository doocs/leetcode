# [78. Subsets](https://leetcode.com/problems/subsets)

[中文文档](/solution/0000-0099/0078.Subsets/README.md)

## Description

<p>Given a set of <strong>distinct</strong> integers, <em>nums</em>, return all possible subsets (the power set).</p>

<p><strong>Note:</strong> The solution set must not contain duplicate subsets.</p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong> nums = [1,2,3]

<strong>Output:</strong>

[

  [3],

&nbsp; [1],

&nbsp; [2],

&nbsp; [1,2,3],

&nbsp; [1,3],

&nbsp; [2,3],

&nbsp; [1,2],

&nbsp; []

]</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        def dfs(nums, i, res, path):
            res.append(copy.deepcopy(path))
            while i < len(nums):
                path.append(nums[i])
                dfs(nums, i + 1, res, path)
                path.pop()
                i += 1
        res, path = [], []
        dfs(nums, 0, res, path)
        return res
```

### **Java**

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, res, path);
        return res;
    }

    private void dfs(int[] nums, int i, List<List<Integer>> res, List<Integer> path) {
        res.add(new ArrayList<>(path));
        while (i < nums.length) {
            path.add(nums[i]);
            dfs(nums, i + 1, res, path);
            path.remove(path.size() - 1);
            ++i;
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
