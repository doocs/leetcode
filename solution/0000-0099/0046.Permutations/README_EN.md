# [46. Permutations](https://leetcode.com/problems/permutations)

[中文文档](/solution/0000-0099/0046.Permutations/README.md)

## Description

<p>Given a collection of <strong>distinct</strong> integers, return all possible permutations.</p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong> [1,2,3]

<strong>Output:</strong>

[

  [1,2,3],

  [1,3,2],

  [2,1,3],

  [2,3,1],

  [3,1,2],

  [3,2,1]

]

</pre>

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
