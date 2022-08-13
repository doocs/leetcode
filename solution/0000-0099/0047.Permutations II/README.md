# [47. 全排列 II](https://leetcode.cn/problems/permutations-ii)

[English Version](/solution/0000-0099/0047.Permutations%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个可包含重复数字的序列 <code>nums</code> ，<em><strong>按任意顺序</strong></em> 返回所有不重复的全排列。</p>

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

### **C#**

```cs
using System.Collections.Generic;
using System.Linq;

public class Solution {
    public IList<IList<int>> PermuteUnique(int[] nums) {
        var results = new List<IList<int>>();
        var temp = new List<int>();
        var count = nums.GroupBy(n => n).ToDictionary(g => g.Key, g => g.Count());
        Search(count, temp, results);
        return results;
    }

    private void Search(Dictionary<int, int> count, IList<int> temp, IList<IList<int>> results)
    {
        if (!count.Any() && temp.Any())
        {
            results.Add(new List<int>(temp));
            return;
        }
        var keys = count.Keys.ToList();
        foreach (var key in keys)
        {
            temp.Add(key);
            --count[key];
            if (count[key] == 0) count.Remove(key);
            Search(count, temp, results);
            temp.RemoveAt(temp.Count - 1);
            if (count.ContainsKey(key))
            {
                ++count[key];
            }
            else
            {
                count[key] = 1;
            }
        }
    }
}
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

### **TypeScript**

```ts
function permuteUnique(nums: number[]): number[][] {
    const n = nums.length;
    const res: number[][] = [];
    const dfs = (i: number) => {
        if (i === n) {
            res.push([...nums]);
        }
        const set = new Set<number>();
        for (let j = i; j < n; j++) {
            if (set.has(nums[j])) {
                continue;
            }
            set.add(nums[j]);
            [nums[i], nums[j]] = [nums[j], nums[i]];
            dfs(i + 1);
            [nums[i], nums[j]] = [nums[j], nums[i]];
        }
    };
    dfs(0);
    return res;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    fn dfs(i: usize, nums: &mut Vec<i32>, res: &mut Vec<Vec<i32>>) {
        let n = nums.len();
        if i == n {
            res.push(nums.clone());
            return;
        }
        let mut set = HashSet::new();
        for j in i..n {
            if set.contains(&nums[j]) {
                continue;
            }
            set.insert(nums[j]);
            nums.swap(i, j);
            Self::dfs(i + 1, nums, res);
            nums.swap(i, j);
        }
    }

    pub fn permute_unique(mut nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut res = vec![];
        Self::dfs(0, &mut nums, &mut res);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
