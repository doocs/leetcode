---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20083.%20%E6%B2%A1%E6%9C%89%E9%87%8D%E5%A4%8D%E5%85%83%E7%B4%A0%E9%9B%86%E5%90%88%E7%9A%84%E5%85%A8%E6%8E%92%E5%88%97/README.md
---

<!-- problem:start -->

# [剑指 Offer II 083. 没有重复元素集合的全排列](https://leetcode.cn/problems/VvJkup)

## 题目描述

<!-- description:start -->

<p>给定一个不含重复数字的整数数组 <code>nums</code> ，返回其 <strong>所有可能的全排列</strong> 。可以 <strong>按任意顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1]
<strong>输出：</strong>[[0,1],[1,0]]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1]
<strong>输出：</strong>[[1]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 6</code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
	<li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 46&nbsp;题相同：<a href="https://leetcode.cn/problems/permutations/">https://leetcode.cn/problems/permutations/</a>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### Python3

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

#### Java

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

    private void dfs(
        int u, int n, int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> res) {
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

#### C++

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
        if (u == n) {
            res.emplace_back(path);
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (!used[i]) {
                path[u] = nums[i];
                used[i] = true;
                dfs(u + 1, n, nums, used, path, res);
                used[i] = false;
            }
        }
    }
};
```

#### Go

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

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var permute = function (nums) {
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

#### C#

```cs
using System.Collections.Generic;
using System.Linq;

public class Solution {
    public IList<IList<int>> Permute(int[] nums) {
        var results = new List<IList<int>>();
        var temp = new List<int>();
        var visited = new bool[nums.Length];
        Search(nums, visited, temp, results);
        return results;
    }

    private void Search(int[] nums, bool[] visited, IList<int> temp, IList<IList<int>> results)
    {
        int count = 0;
        for (var i = 0; i < nums.Length; ++i)
        {
            if (visited[i]) continue;
            ++count;
            temp.Add(nums[i]);
            visited[i] = true;
            Search(nums, visited, temp, results);
            temp.RemoveAt(temp.Count - 1);
            visited[i] = false;
        }
        if (count == 0 && temp.Any())
        {
            results.Add(new List<int>(temp));
        }
    }
}
```

#### Swift

```swift
class Solution {
    func permute(_ nums: [Int]) -> [[Int]] {
        var res = [[Int]]()
        var path = [Int]()
        var used = [Bool](repeating: false, count: nums.count)
        dfs(0, nums.count, nums, &used, &path, &res)
        return res
    }

    private func dfs(
        _ u: Int, _ n: Int, _ nums: [Int], _ used: inout [Bool], _ path: inout [Int], _ res: inout [[Int]]
    ) {
        if u == n {
            res.append(path)
            return
        }
        for i in 0..<n {
            if !used[i] {
                path.append(nums[i])
                used[i] = true
                dfs(u + 1, n, nums, &used, &path, &res)
                used[i] = false
                path.removeLast()
            }
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
