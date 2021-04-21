# [46. Permutations](https://leetcode.com/problems/permutations)

[中文文档](/solution/0000-0099/0046.Permutations/README.md)

## Description

<p>Given an array <code>nums</code> of distinct integers, return <em>all the possible permutations</em>. You can return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [0,1]
<strong>Output:</strong> [[0,1],[1,0]]
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> nums = [1]
<strong>Output:</strong> [[1]]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 6</code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
	<li>All the integers of <code>nums</code> are <strong>unique</strong>.</li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        if len(nums) <= 1:
            return [nums]
        res = []
        for i, num in enumerate(nums):
            n = nums[:i] + nums[i + 1:]
            for item in self.permute(n):
                res.append([num] + item)
        return res
```

### **Java**

Backtracking:

```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        dfs(nums, 0, res, path, used);
        return res;
    }

    private void dfs(int[] nums, int i, List<List<Integer>> res, List<Integer> path, boolean[] used) {
        if (i == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int j = 0; j < nums.length; ++j) {
            if (!used[j]) {
                path.add(nums[j]);
                used[j] = true;
                dfs(nums, i + 1, res, path, used);
                used[j] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
```

- Recursion:

```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permute(res, nums, 0);
        return res;
    }

    private void permute(List<List<Integer>> res, int[] nums, int start) {
        if (start == nums.length) {
            List<Integer> t = new ArrayList<>();
            for (int e : nums) {
                t.add(e);
            }
            res.add(t);
            return;
        }
        for (int i = start; i < nums.length; ++i) {
            swap(nums, i, start);
            permute(res, nums, start + 1);
            swap(nums, i, start);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
