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

深度优先搜索。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        n = len(nums)
        res = []
        path = [0] * n
        used = [False] * n

        def dfs(u):
            if u == n:
                res.append(path.copy())
                return
            for i in range(n):
                if not used[i]:
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
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int n = nums.length;
        boolean[] used = new boolean[n];
        dfs(0, n, nums, used, path, res);
        return res;
    }

    private void dfs(int u, int n, int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        if (u == n) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                dfs(u + 1, n, nums, used, path, res);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var permute = function(nums) {
    const n = nums.length;
    let res = [];
    let path = [];
    let used = new Array(n).fill(false);
    dfs(0, n, nums, used, path, res);
    return res;
};

function dfs(u, n, nums, used, path, res) {
    if (u == n) {
        res.push(path.slice());
        return;
    }
    for (let i = 0; i < n; ++i) {
        if (!used[i]) {
            path.push(nums[i]);
            used[i] = true;
            dfs(u + 1, n, nums, used, path, res);
            used[i] = false;
            path.pop();
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> permute(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> res;
        vector<int> path(n, 0);
        vector<bool> used(n, false);
        dfs(0, n, nums, used, path, res);
        return res;
    }

    void dfs(int u, int n, vector<int>& nums, vector<bool>& used, vector<int>& path, vector<vector<int>>& res) {
        if (u == n)
        {
            res.emplace_back(path);
            return;
        }
        for (int i = 0; i < n; ++i)
        {
            if (!used[i])
            {
                path[u] = nums[i];
                used[i] = true;
                dfs(u + 1, n, nums, used, path, res);
                used[i] = false;
            }
        }
    }
};
```

### **Go**

```go
func permute(nums []int) [][]int {
	n := len(nums)
	res := make([][]int, 0)
	path := make([]int, n)
	used := make([]bool, n)
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
		if !used[i] {
			path[u] = nums[i]
			used[i] = true
			dfs(u+1, n, nums, used, path, res)
			used[i] = false
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
