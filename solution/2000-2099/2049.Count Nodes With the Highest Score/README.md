# [2049. 统计最高分的节点数目](https://leetcode.cn/problems/count-nodes-with-the-highest-score)

[English Version](/solution/2000-2099/2049.Count%20Nodes%20With%20the%20Highest%20Score/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵根节点为 <code>0</code> 的&nbsp;<strong>二叉树</strong>&nbsp;，它总共有 <code>n</code>&nbsp;个节点，节点编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;。同时给你一个下标从&nbsp;<strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>parents</code>&nbsp;表示这棵树，其中&nbsp;<code>parents[i]</code>&nbsp;是节点 <code>i</code>&nbsp;的父节点。由于节点 <code>0</code>&nbsp;是根，所以&nbsp;<code>parents[0] == -1</code>&nbsp;。</p>

<p>一个子树的 <strong>大小</strong>&nbsp;为这个子树内节点的数目。每个节点都有一个与之关联的&nbsp;<strong>分数</strong>&nbsp;。求出某个节点分数的方法是，将这个节点和与它相连的边全部 <strong>删除</strong>&nbsp;，剩余部分是若干个 <strong>非空</strong>&nbsp;子树，这个节点的 <strong>分数</strong>&nbsp;为所有这些子树 <strong>大小的乘积</strong>&nbsp;。</p>

<p>请你返回有 <strong>最高得分</strong>&nbsp;节点的 <strong>数目</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<p><img alt="example-1" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2049.Count%20Nodes%20With%20the%20Highest%20Score/images/example-1.png" style="width: 604px; height: 266px;"></p>

<pre><b>输入：</b>parents = [-1,2,0,2,0]
<b>输出：</b>3
<strong>解释：</strong>
- 节点 0 的分数为：3 * 1 = 3
- 节点 1 的分数为：4 = 4
- 节点 2 的分数为：1 * 1 * 2 = 2
- 节点 3 的分数为：4 = 4
- 节点 4 的分数为：4 = 4
最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="example-2" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2049.Count%20Nodes%20With%20the%20Highest%20Score/images/example-2.png" style="width: 95px; height: 143px;"></p>

<pre><b>输入：</b>parents = [-1,2,0]
<b>输出：</b>2
<strong>解释：</strong>
- 节点 0 的分数为：2 = 2
- 节点 1 的分数为：2 = 2
- 节点 2 的分数为：1 * 1 = 1
最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == parents.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>parents[0] == -1</code></li>
	<li>对于&nbsp;<code>i != 0</code>&nbsp;，有&nbsp;<code>0 &lt;= parents[i] &lt;= n - 1</code></li>
	<li><code>parents</code>&nbsp;表示一棵二叉树。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

第一步可以将 `parents` 数组转为相对好处理的邻接矩阵。

接下来，观察样例 1 中的 `Removed 2`，删除一个节点可能产生若干子树，或者整棵树除掉以该节点为根的子树后剩下的部分。

总结出规律后，递归处理即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countHighestScoreNodes(self, parents: List[int]) -> int:
        n, max_score, ans = len(parents), 0, 0
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parents[i]].append(i)

        def dfs(cur: int) -> int:
            nonlocal max_score, ans
            size, score = 1, 1
            for c in g[cur]:
                s = dfs(c)
                size += s
                score *= s
            if cur > 0:
                score *= n - size
            if score > max_score:
                max_score = score
                ans = 1
            elif score == max_score:
                ans += 1
            return size

        dfs(0)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    private int n;
    private long maxScore;
    private int ans;
    private List<List<Integer>> graph;

    public int countHighestScoreNodes(int[] parents) {
        n = parents.length;
        maxScore = 0;
        ans = 0;
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            graph.get(parents[i]).add(i);
        }
        dfs(0);
        return ans;
    }

    private int dfs(int cur) {
        int size = 1;
        long score = 1;
        for (int child : graph.get(cur)) {
            int s = dfs(child);
            size += s;
            score *= s;
        }
        if (cur > 0) {
            score *= n - size;
        }
        if (score > maxScore) {
            maxScore = score;
            ans = 1;
        } else if (score == maxScore) {
            ans++;
        }
        return size;
    }
}
```

### **TypeScript**

```ts
function countHighestScoreNodes(parents: number[]): number {
    const n = parents.length;
    let edge = Array.from({ length: n }, (v, i) => []);
    for (let i = 0; i < n; i++) {
        const parent = parents[i];
        if (parent != -1) {
            edge[parent].push(i);
        }
    }

    let ans = 0;
    let max = 0;
    function dfs(idx: number): number {
        let size = 1,
            score = 1;
        for (let i = 0; i < edge[idx].length; i++) {
            const child = edge[idx][i];
            let childSize = dfs(child);
            size += childSize;
            score *= childSize;
        }
        if (idx > 0) {
            score *= n - size;
        }
        if (score > max) {
            max = score;
            ans = 1;
        } else if (score == max) {
            ans++;
        }
        return size;
    }
    dfs(0);
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int ans;
    long long maxScore;
    int n;

    int countHighestScoreNodes(vector<int>& parents) {
        ans = 0;
        maxScore = 0;
        n = parents.size();
        unordered_map<int, vector<int>> g;
        for (int i = 1; i < n; ++i) g[parents[i]].push_back(i);
        dfs(0, g);
        return ans;
    }

    int dfs(int u, unordered_map<int, vector<int>>& g) {
        int size = 1;
        long long score = 1;
        for (int v : g[u]) {
            int t = dfs(v, g);
            size += t;
            score *= t;
        }
        if (u > 0) score *= (n - size);
        if (score > maxScore) {
            maxScore = score;
            ans = 1;
        } else if (score == maxScore)
            ++ans;
        return size;
    }
};
```

### **Go**

```go
func countHighestScoreNodes(parents []int) int {
	n := len(parents)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		p := parents[i]
		g[p] = append(g[p], i)
	}
	maxScore, ans := 0, 0
	var dfs func(int) int
	dfs = func(u int) int {
		size, score := 1, 1
		for _, v := range g[u] {
			t := dfs(v)
			size += t
			score *= t
		}
		if u > 0 {
			score *= n - size
		}
		if score > maxScore {
			maxScore, ans = score, 1
		} else if score == maxScore {
			ans++
		}
		return size
	}
	dfs(0)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
