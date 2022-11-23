# [1743. Restore the Array From Adjacent Pairs](https://leetcode.com/problems/restore-the-array-from-adjacent-pairs)

[中文文档](/solution/1700-1799/1743.Restore%20the%20Array%20From%20Adjacent%20Pairs/README.md)

## Description

<p>There is an integer array <code>nums</code> that consists of <code>n</code> <strong>unique </strong>elements, but you have forgotten it. However, you do remember every pair of adjacent elements in <code>nums</code>.</p>

<p>You are given a 2D integer array <code>adjacentPairs</code> of size <code>n - 1</code> where each <code>adjacentPairs[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> indicates that the elements <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> are adjacent in <code>nums</code>.</p>

<p>It is guaranteed that every adjacent pair of elements <code>nums[i]</code> and <code>nums[i+1]</code> will exist in <code>adjacentPairs</code>, either as <code>[nums[i], nums[i+1]]</code> or <code>[nums[i+1], nums[i]]</code>. The pairs can appear <strong>in any order</strong>.</p>

<p>Return <em>the original array </em><code>nums</code><em>. If there are multiple solutions, return <strong>any of them</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> adjacentPairs = [[2,1],[3,4],[3,2]]
<strong>Output:</strong> [1,2,3,4]
<strong>Explanation:</strong> This array has all its adjacent pairs in adjacentPairs.
Notice that adjacentPairs[i] may not be in left-to-right order.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> adjacentPairs = [[4,-2],[1,4],[-3,1]]
<strong>Output:</strong> [-2,4,1,-3]
<strong>Explanation:</strong> There can be negative numbers.
Another solution is [-3,1,4,-2], which would also be accepted.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> adjacentPairs = [[100000,-100000]]
<strong>Output:</strong> [100000,-100000]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums.length == n</code></li>
	<li><code>adjacentPairs.length == n - 1</code></li>
	<li><code>adjacentPairs[i].length == 2</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i], u<sub>i</sub>, v<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li>There exists some <code>nums</code> that has <code>adjacentPairs</code> as its pairs.</li>
</ul>

## Solutions

Traverse the graph from the point where the degree is one.

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
