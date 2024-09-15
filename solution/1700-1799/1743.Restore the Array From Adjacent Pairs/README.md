---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1743.Restore%20the%20Array%20From%20Adjacent%20Pairs/README.md
rating: 1579
source: 第 226 场周赛 Q2
tags:
    - 深度优先搜索
    - 数组
    - 哈希表
---

<!-- problem:start -->

# [1743. 从相邻元素对还原数组](https://leetcode.cn/problems/restore-the-array-from-adjacent-pairs)

[English Version](/solution/1700-1799/1743.Restore%20the%20Array%20From%20Adjacent%20Pairs/README_EN.md)

## 题目描述

<!-- description:start -->

<p>存在一个由 <code>n</code> 个不同元素组成的整数数组 <code>nums</code> ，但你已经记不清具体内容。好在你还记得 <code>nums</code> 中的每一对相邻元素。</p>

<p>给你一个二维整数数组 <code>adjacentPairs</code> ，大小为 <code>n - 1</code> ，其中每个 <code>adjacentPairs[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示元素 <code>u<sub>i</sub></code> 和 <code>v<sub>i</sub></code> 在 <code>nums</code> 中相邻。</p>

<p>题目数据保证所有由元素 <code>nums[i]</code> 和 <code>nums[i+1]</code> 组成的相邻元素对都存在于 <code>adjacentPairs</code> 中，存在形式可能是 <code>[nums[i], nums[i+1]]</code> ，也可能是 <code>[nums[i+1], nums[i]]</code> 。这些相邻元素对可以 <strong>按任意顺序</strong> 出现。</p>

<p>返回 <strong>原始数组</strong><em> </em><code>nums</code><em> </em>。如果存在多种解答，返回 <strong>其中任意一个</strong> 即可。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>adjacentPairs = [[2,1],[3,4],[3,2]]
<strong>输出：</strong>[1,2,3,4]
<strong>解释：</strong>数组的所有相邻元素对都在 adjacentPairs 中。
特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>adjacentPairs = [[4,-2],[1,4],[-3,1]]
<strong>输出：</strong>[-2,4,1,-3]
<strong>解释：</strong>数组中可能存在负数。
另一种解答是 [-3,1,4,-2] ，也会被视作正确答案。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>adjacentPairs = [[100000,-100000]]
<strong>输出：</strong>[100000,-100000]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums.length == n</code></li>
	<li><code>adjacentPairs.length == n - 1</code></li>
	<li><code>adjacentPairs[i].length == 2</code></li>
	<li><code>2 <= n <= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> <= nums[i], u<sub>i</sub>, v<sub>i</sub> <= 10<sup>5</sup></code></li>
	<li>题目数据保证存在一些以 <code>adjacentPairs</code> 作为元素对的数组 <code>nums</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

从度为一的点开始遍历图，可以用 DFS，也可以直接遍历。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def restoreArray(self, adjacentPairs: List[List[int]]) -> List[int]:
        g = defaultdict(list)
        for a, b in adjacentPairs:
            g[a].append(b)
            g[b].append(a)
        n = len(adjacentPairs) + 1
        ans = [0] * n
        for i, v in g.items():
            if len(v) == 1:
                ans[0] = i
                ans[1] = v[0]
                break
        for i in range(2, n):
            v = g[ans[i - 1]]
            ans[i] = v[0] if v[1] == ans[i - 2] else v[1]
        return ans
```

#### Java

```java
class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length + 1;
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int[] e : adjacentPairs) {
            int a = e[0], b = e[1];
            g.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            g.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }
        int[] ans = new int[n];
        for (Map.Entry<Integer, List<Integer>> entry : g.entrySet()) {
            if (entry.getValue().size() == 1) {
                ans[0] = entry.getKey();
                ans[1] = entry.getValue().get(0);
                break;
            }
        }
        for (int i = 2; i < n; ++i) {
            List<Integer> v = g.get(ans[i - 1]);
            ans[i] = v.get(1) == ans[i - 2] ? v.get(0) : v.get(1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> restoreArray(vector<vector<int>>& adjacentPairs) {
        int n = adjacentPairs.size() + 1;
        unordered_map<int, vector<int>> g;
        for (auto& e : adjacentPairs) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        vector<int> ans(n);
        for (auto& [k, v] : g) {
            if (v.size() == 1) {
                ans[0] = k;
                ans[1] = v[0];
                break;
            }
        }
        for (int i = 2; i < n; ++i) {
            auto v = g[ans[i - 1]];
            ans[i] = v[0] == ans[i - 2] ? v[1] : v[0];
        }
        return ans;
    }
};
```

#### Go

```go
func restoreArray(adjacentPairs [][]int) []int {
	n := len(adjacentPairs) + 1
	g := map[int][]int{}
	for _, e := range adjacentPairs {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	ans := make([]int, n)
	for k, v := range g {
		if len(v) == 1 {
			ans[0] = k
			ans[1] = v[0]
			break
		}
	}
	for i := 2; i < n; i++ {
		v := g[ans[i-1]]
		ans[i] = v[0]
		if v[0] == ans[i-2] {
			ans[i] = v[1]
		}
	}
	return ans
}
```

#### C#

```cs
public class Solution {
    public int[] RestoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.Length + 1;
        Dictionary<int, List<int>> g = new Dictionary<int, List<int>>();

        foreach (int[] e in adjacentPairs) {
            int a = e[0], b = e[1];
            if (!g.ContainsKey(a)) {
                g[a] = new List<int>();
            }
            if (!g.ContainsKey(b)) {
                g[b] = new List<int>();
            }
            g[a].Add(b);
            g[b].Add(a);
        }

        int[] ans = new int[n];

        foreach (var entry in g) {
            if (entry.Value.Count == 1) {
                ans[0] = entry.Key;
                ans[1] = entry.Value[0];
                break;
            }
        }

        for (int i = 2; i < n; ++i) {
            List<int> v = g[ans[i - 1]];
            ans[i] = v[1] == ans[i - 2] ? v[0] : v[1];
        }

        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def restoreArray(self, adjacentPairs: List[List[int]]) -> List[int]:
        def dfs(i, fa):
            ans.append(i)
            for j in g[i]:
                if j != fa:
                    dfs(j, i)

        g = defaultdict(list)
        for a, b in adjacentPairs:
            g[a].append(b)
            g[b].append(a)
        i = next(i for i, v in g.items() if len(v) == 1)
        ans = []
        dfs(i, 1e6)
        return ans
```

#### Java

```java
class Solution {
    private Map<Integer, List<Integer>> g = new HashMap<>();
    private int[] ans;

    public int[] restoreArray(int[][] adjacentPairs) {
        for (var e : adjacentPairs) {
            int a = e[0], b = e[1];
            g.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            g.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }
        int n = adjacentPairs.length + 1;
        ans = new int[n];
        for (var e : g.entrySet()) {
            if (e.getValue().size() == 1) {
                dfs(e.getKey(), 1000000, 0);
                break;
            }
        }
        return ans;
    }

    private void dfs(int i, int fa, int k) {
        ans[k++] = i;
        for (int j : g.get(i)) {
            if (j != fa) {
                dfs(j, i, k);
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> restoreArray(vector<vector<int>>& adjacentPairs) {
        unordered_map<int, vector<int>> g;
        for (auto& e : adjacentPairs) {
            int a = e[0], b = e[1];
            g[a].emplace_back(b);
            g[b].emplace_back(a);
        }
        int n = adjacentPairs.size() + 1;
        vector<int> ans;
        function<void(int, int)> dfs = [&](int i, int fa) {
            ans.emplace_back(i);
            for (int& j : g[i]) {
                if (j != fa) {
                    dfs(j, i);
                }
            }
        };
        for (auto& [i, v] : g) {
            if (v.size() == 1) {
                dfs(i, 1e6);
                break;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func restoreArray(adjacentPairs [][]int) []int {
	g := map[int][]int{}
	for _, e := range adjacentPairs {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	ans := []int{}
	var dfs func(i, fa int)
	dfs = func(i, fa int) {
		ans = append(ans, i)
		for _, j := range g[i] {
			if j != fa {
				dfs(j, i)
			}
		}
	}
	for i, v := range g {
		if len(v) == 1 {
			dfs(i, 1000000)
			break
		}
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
