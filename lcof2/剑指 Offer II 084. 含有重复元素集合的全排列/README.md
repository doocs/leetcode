# [剑指 Offer II 084. 含有重复元素集合的全排列](https://leetcode.cn/problems/7p8L0Z)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个可包含重复数字的整数集合&nbsp;<code>nums</code> ，<strong>按任意顺序</strong> 返回它所有不重复的全排列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,2]
<strong>输出：</strong>
[[1,1,2],
 [1,2,1],
 [2,1,1]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 8</code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 47&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/permutations-ii/">https://leetcode.cn/problems/permutations-ii/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

排序 + 深度优先搜索。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        n = len(nums)
        res = []
        path = [0] * n
        used = [False] * n
        nums.sort()

        def dfs(u):
            if u == n:
                res.append(path.copy())
                return
            for i in range(n):
                if used[i] or (i > 0 and nums[i] == nums[i - 1] and not used[i - 1]):
                    continue
                path[u] = nums[i]
                used[i] = True
                dfs(u + 1)
                used[i] = False

        dfs(0)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int n = nums.length;
        boolean[] used = new boolean[n];
        Arrays.sort(nums);
        dfs(0, n, nums, used, path, res);
        return res;
    }

    private void dfs(int u, int n, int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        if (u == n) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            dfs(u + 1, n, nums, used, path, res);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> permuteUnique(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> res;
        vector<int> path(n, 0);
        vector<bool> used(n, false);
        sort(nums.begin(), nums.end());
        dfs(0, n, nums, used, path, res);
        return res;
    }

    void dfs(int u, int n, vector<int>& nums, vector<bool>& used, vector<int>& path, vector<vector<int>>& res) {
        if (u == n) {
            res.emplace_back(path);
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue;
            path[u] = nums[i];
            used[i] = true;
            dfs(u + 1, n, nums, used, path, res);
            used[i] = false;
        }
    }
};
```

### **Go**

```go
func permuteUnique(nums []int) [][]int {
	n := len(nums)
	res := make([][]int, 0)
	path := make([]int, n)
	used := make([]bool, n)
	sort.Ints(nums)
	dfs(0, n, nums, used, path, &res)
	return res
}

func dfs(u, n int, nums []int, used []bool, path []int, res *[][]int) {
	if u == n {
		t := make([]int, n)
		copy(t, path)
		*res = append(*res, t)
		return
	}
	for i := 0; i < n; i++ {
		if used[i] || (i > 0 && nums[i] == nums[i-1] && !used[i-1]) {
			continue
		}
		path[u] = nums[i]
		used[i] = true
		dfs(u+1, n, nums, used, path, res)
		used[i] = false
	}
}
```

### **...**

```

```

<!-- tabs:end -->
