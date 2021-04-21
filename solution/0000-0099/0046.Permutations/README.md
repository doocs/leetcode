# [46. 全排列](https://leetcode-cn.com/problems/permutations)

[English Version](/solution/0000-0099/0046.Permutations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个<strong> 没有重复</strong> 数字的序列，返回其所有可能的全排列。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> [1,2,3]
<strong>输出:</strong>
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]</pre>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

回溯法：

```python
class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        def dfs(nums, i, res, path, used):
            if i == len(nums):
                res.append(copy.deepcopy(path))
                return
            for j in range(len(nums)):
                if not used[j]:
                    path.append(nums[j])
                    used[j] = True
                    dfs(nums, i + 1, res, path, used)
                    used[j] = False
                    path.pop()

        res, path = [], []
        used = [False] * len(nums)
        dfs(nums, 0, res, path, used)
        return res
```

切分数组：

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

回溯法：

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

- 递归：

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
