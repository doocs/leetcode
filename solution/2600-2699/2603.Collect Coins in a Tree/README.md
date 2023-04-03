# [2603. 收集树中金币](https://leetcode.cn/problems/collect-coins-in-a-tree)

[English Version](/solution/2600-2699/2603.Collect%20Coins%20in%20a%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>n</code>&nbsp;个节点的无向无根树，节点编号从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;。给你整数&nbsp;<code>n</code>&nbsp;和一个长度为 <code>n - 1</code>&nbsp;的二维整数数组 <code>edges</code>&nbsp;，其中&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示树中节点&nbsp;<code>a<sub>i</sub></code> 和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条边。再给你一个长度为 <code>n</code>&nbsp;的数组&nbsp;<code>coins</code>&nbsp;，其中&nbsp;<code>coins[i]</code> 可能为&nbsp;<code>0</code>&nbsp;也可能为&nbsp;<code>1</code>&nbsp;，<code>1</code>&nbsp;表示节点 <code>i</code>&nbsp;处有一个金币。</p>

<p>一开始，你需要选择树中任意一个节点出发。你可以执行下述操作任意次：</p>

<ul>
	<li>收集距离当前节点距离为 <code>2</code>&nbsp;以内的所有金币，或者</li>
	<li>移动到树中一个相邻节点。</li>
</ul>

<p>你需要收集树中所有的金币，并且回到出发节点，请你返回最少经过的边数。</p>

<p>如果你多次经过一条边，每一次经过都会给答案加一。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2603.Collect%20Coins%20in%20a%20Tree/images/graph-2.png" style="width: 522px; height: 522px;"></p>

<pre><b>输入：</b>coins = [1,0,0,0,0,1], edges = [[0,1],[1,2],[2,3],[3,4],[4,5]]
<b>输出：</b>2
<b>解释：</b>从节点 2 出发，收集节点 0 处的金币，移动到节点 3 ，收集节点 5 处的金币，然后移动回节点 2 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2603.Collect%20Coins%20in%20a%20Tree/images/graph-4.png" style="width: 522px; height: 522px;"></p>

<pre><b>输入：</b>coins = [0,0,0,1,1,0,0,1], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[5,6],[5,7]]
<b>输出：</b>2
<b>解释：</b>从节点 0 出发，收集节点 4 和 3 处的金币，移动到节点 2 处，收集节点 7 处的金币，移动回节点 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == coins.length</code></li>
	<li><code>1 &lt;= n &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= coins[i] &lt;= 1</code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>edges</code>&nbsp;表示一棵合法的树。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：拓扑排序**

我们先将 $edges$ 中的边转换成邻接表 $g$，其中 $g[i]$ 表示节点 $i$ 的所有邻接节点，用集合表示。

接下来我们遍历所有节点，找到 $coins[i]=0$ 且 $g[i]$ 中只有一个节点的节点（也即是金币为 $0$ 的叶子节点），将其加入队列 $q$ 中。

然后我们不断地从队列中取出节点，将其从邻接表中删除，然后判断其邻接节点是否满足 $coins[j]=0$ 且 $g[j]$ 中只有一个节点的条件，如果满足则将其加入队列 $q$ 中。循环，直至队列为空。

经过上述操作后，我们得到了一棵新的树，且树的叶子节点都是金币为 $1$ 的节点。

然后，我们在删除剩下的两层叶子节点，最终得到的是一棵所有节点都需要被访问的节点，我们只需要统计其边数，乘上 $2$，即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为节点数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def collectTheCoins(self, coins: List[int], edges: List[List[int]]) -> int:
        g = defaultdict(set)
        for a, b in edges:
            g[a].add(b)
            g[b].add(a)
        n = len(coins)
        q = deque(i for i in range(n) if len(g[i]) == 1 and coins[i] == 0)
        while q:
            i = q.popleft()
            for j in g[i]:
                g[j].remove(i)
                if coins[j] == 0 and len(g[j]) == 1:
                    q.append(j)
            g[i].clear()
        for k in range(2):
            q = [i for i in range(n) if len(g[i]) == 1]
            for i in q:
                for j in g[i]:
                    g[j].remove(i)
                g[i].clear()
        return sum(len(g[a]) > 0 and len(g[b]) > 0 for a, b in edges) * 2
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        Set<Integer>[] g = new Set[n];
        Arrays.setAll(g, k -> new HashSet<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (coins[i] == 0 && g[i].size() == 1) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int i = q.poll();
            for (int j : g[i]) {
                g[j].remove(i);
                if (coins[j] == 0 && g[j].size() == 1) {
                    q.offer(j);
                }
            }
            g[i].clear();
        }
        q.clear();
        for (int k = 0; k < 2; ++k) {
            for (int i = 0; i < n; ++i) {
                if (g[i].size() == 1) {
                    q.offer(i);
                }
            }
            for (int i : q) {
                for (int j : g[i]) {
                    g[j].remove(i);
                }
                g[i].clear();
            }
        }
        int ans = 0;
        for (var e : edges) {
            int a = e[0], b = e[1];
            if (g[a].size() > 0 && g[b].size() > 0) {
                ans += 2;
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
    int collectTheCoins(vector<int>& coins, vector<vector<int>>& edges) {
        int n = coins.size();
        unordered_set<int> g[n];
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].insert(b);
            g[b].insert(a);
        }
        queue<int> q;
        for (int i = 0; i < n; ++i) {
            if (coins[i] == 0 && g[i].size() == 1) {
                q.push(i);
            }
        }
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            for (int j : g[i]) {
                g[j].erase(i);
                if (coins[j] == 0 && g[j].size() == 1) {
                    q.push(j);
                }
            }
            g[i].clear();
        }
        for (int k = 0; k < 2; ++k) {
            vector<int> q;
            for (int i = 0; i < n; ++i) {
                if (g[i].size() == 1) {
                    q.push_back(i);
                }
            }
            for (int i : q) {
                for (int j : g[i]) {
                    g[j].erase(i);
                }
                g[i].clear();
            }
        }
        int ans = 0;
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            if (g[a].size() && g[b].size()) {
                ans += 2;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func collectTheCoins(coins []int, edges [][]int) int {
	n := len(coins)
	g := make([]map[int]bool, n)
	for i := range g {
		g[i] = map[int]bool{}
	}
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a][b] = true
		g[b][a] = true
	}
	q := []int{}
	for i, c := range coins {
		if c == 0 && len(g[i]) == 1 {
			q = append(q, i)
		}
	}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		for j := range g[i] {
			delete(g[j], i)
			if coins[j] == 0 && len(g[j]) == 1 {
				q = append(q, j)
			}
		}
		g[i] = map[int]bool{}
	}
	for k := 0; k < 2; k++ {
		q := []int{}
		for i := range coins {
			if len(g[i]) == 1 {
				q = append(q, i)
			}
		}
		for _, i := range q {
			for j := range g[i] {
				delete(g[j], i)
			}
			g[i] = map[int]bool{}
		}
	}
	ans := 0
	for _, e := range edges {
		a, b := e[0], e[1]
		if len(g[a]) > 0 && len(g[b]) > 0 {
			ans += 2
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function collectTheCoins(coins: number[], edges: number[][]): number {
    const n = coins.length;
    const g: Set<number>[] = new Array(n).fill(0).map(() => new Set<number>());
    for (const [a, b] of edges) {
        g[a].add(b);
        g[b].add(a);
    }
    let q: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (coins[i] === 0 && g[i].size === 1) {
            q.push(i);
        }
    }
    while (q.length) {
        const i = q.pop()!;
        for (const j of g[i]) {
            g[j].delete(i);
            if (coins[j] === 0 && g[j].size === 1) {
                q.push(j);
            }
        }
        g[i].clear();
    }
    q = [];
    for (let k = 0; k < 2; ++k) {
        for (let i = 0; i < n; ++i) {
            if (g[i].size === 1) {
                q.push(i);
            }
        }
        for (const i of q) {
            for (const j of g[i]) {
                g[j].delete(i);
            }
            g[i].clear();
        }
    }
    let ans = 0;
    for (const [a, b] of edges) {
        if (g[a].size > 0 && g[b].size > 0) {
            ans += 2;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
