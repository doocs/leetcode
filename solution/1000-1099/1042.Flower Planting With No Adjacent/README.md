# [1042. 不邻接植花](https://leetcode.cn/problems/flower-planting-with-no-adjacent)

[English Version](/solution/1000-1099/1042.Flower%20Planting%20With%20No%20Adjacent/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code> 个花园，按从&nbsp;<code>1</code>&nbsp;到 <code>n</code> 标记。另有数组 <code>paths</code> ，其中 <code>paths[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;描述了花园&nbsp;<code>x<sub>i</sub></code> 到花园&nbsp;<code>y<sub>i</sub></code> 的双向路径。在每个花园中，你打算种下四种花之一。</p>

<p>另外，所有花园 <strong>最多</strong> 有 <strong>3</strong> 条路径可以进入或离开.</p>

<p>你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。</p>

<p><em>以数组形式返回 <strong>任一</strong> 可行的方案作为答案&nbsp;<code>answer</code>，其中&nbsp;<code>answer[i]</code>&nbsp;为在第&nbsp;<code>(i+1)</code>&nbsp;个花园中种植的花的种类。花的种类用 &nbsp;1、2、3、4 表示。保证存在答案。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3, paths = [[1,2],[2,3],[3,1]]
<strong>输出：</strong>[1,2,3]
<strong>解释：</strong>
花园 1 和 2 花的种类不同。
花园 2 和 3 花的种类不同。
花园 3 和 1 花的种类不同。
因此，[1,2,3] 是一个满足题意的答案。其他满足题意的答案有 [1,2,4]、[1,4,2] 和 [3,2,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 4, paths = [[1,2],[3,4]]
<strong>输出：</strong>[1,2,1,2]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
<strong>输出：</strong>[1,2,3,4]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= paths.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>paths[i].length == 2</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= n</code></li>
	<li><code>x<sub>i</sub> != y<sub>i</sub></code></li>
	<li>每个花园 <strong>最多</strong> 有 <strong>3</strong> 条路径可以进入或离开</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def gardenNoAdj(self, n: int, paths: List[List[int]]) -> List[int]:
        g = defaultdict(list)
        for x, y in paths:
            x, y = x - 1, y - 1
            g[x].append(y)
            g[y].append(x)
        ans = [0] * n
        for u in range(n):
            colors = set(ans[v] for v in g[u])
            for c in range(1, 5):
                if c not in colors:
                    ans[u] = c
                    break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] gardenNoAdj(int n, int[][] paths) {
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] p : paths) {
            int x = p[0] - 1, y = p[1] - 1;
            g[x].add(y);
            g[y].add(x);
        }
        int[] ans = new int[n];
        for (int u = 0; u < n; ++u) {
            Set<Integer> colors = new HashSet<>();
            for (int v : g[u]) {
                colors.add(ans[v]);
            }
            for (int c = 1; c < 5; ++c) {
                if (!colors.contains(c)) {
                    ans[u] = c;
                    break;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> gardenNoAdj(int n, vector<vector<int>>& paths) {
        vector<vector<int>> g(n);
        for (auto& p : paths) {
            int x = p[0] - 1, y = p[1] - 1;
            g[x].push_back(y);
            g[y].push_back(x);
        }
        vector<int> ans(n);
        for (int u = 0; u < n; ++u) {
            unordered_set<int> colors;
            for (int v : g[u]) colors.insert(ans[v]);
            for (int c = 1; c < 5; ++c) {
                if (!colors.count(c)) {
                    ans[u] = c;
                    break;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func gardenNoAdj(n int, paths [][]int) []int {
	g := make([][]int, n)
	for _, p := range paths {
		x, y := p[0]-1, p[1]-1
		g[x] = append(g[x], y)
		g[y] = append(g[y], x)
	}
	ans := make([]int, n)
	for u := 0; u < n; u++ {
		colors := make(map[int]bool)
		for _, v := range g[u] {
			colors[ans[v]] = true
		}
		for c := 1; c < 5; c++ {
			if !colors[c] {
				ans[u] = c
				break
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
