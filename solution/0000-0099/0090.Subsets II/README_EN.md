# [90. Subsets II](https://leetcode.com/problems/subsets-ii)

[中文文档](/solution/0000-0099/0090.Subsets%20II/README.md)

## Description

<p>Given a collection of integers that might contain duplicates, <strong><em>nums</em></strong>, return all possible subsets (the power set).</p>

<p><strong>Note:</strong> The solution set must not contain duplicate subsets.</p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong> [1,2,2]

<strong>Output:</strong>

[

  [2],

  [1],

  [1,2,2],

  [2,2],

  [1,2],

  []

]

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

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
