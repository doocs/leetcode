# [90. Subsets II](https://leetcode.com/problems/subsets-ii)

[中文文档](/solution/0000-0099/0090.Subsets%20II/README.md)

## Description

<p>Given an integer array <code>nums</code> that may contain duplicates, return <em>all possible subsets (the power set)</em>.</p>

<p>The solution set <strong>must not</strong> contain duplicate subsets. Return the solution in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,2]
<strong>Output:</strong> [[],[1],[1,2],[1,2,2],[2],[2,2]]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [0]
<strong>Output:</strong> [[],[0]]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10</code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
</ul>

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
