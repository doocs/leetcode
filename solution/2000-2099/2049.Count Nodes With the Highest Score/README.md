# [2049. 统计最高分的节点数目](https://leetcode.cn/problems/count-nodes-with-the-highest-score)

[English Version](/solution/2000-2099/2049.Count%20Nodes%20With%20the%20Highest%20Score/README_EN.md)

<!-- tags:树,深度优先搜索,数组,二叉树 -->

<!-- difficulty:中等 -->

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

### 方法一：DFS

我们先根据给定的父节点数组 `parents` 构建图 $g$，其中 $g[i]$ 表示节点 $i$ 的所有子节点。定义变量 $ans$ 表示最高得分的节点数目，变量 $mx$ 表示最高得分。

然后，我们设计一个函数 $dfs(i, fa)$，它的作用是计算节点 $i$ 的分数，并返回以节点 $i$ 为根的子树的节点数目。

函数 $dfs(i, fa)$ 的计算过程如下：

我们首先初始化变量 $cnt = 1$，表示以节点 $i$ 为根的子树的节点数目；变量 $score = 1$，表示以节点 $i$ 初始分数。

接下来，我们遍历节点 $i$ 的所有子节点 $j$，如果 $j$ 不是节点 $i$ 的父节点 $fa$，那么我们递归调用 $dfs(j, i)$，并将返回值累乘到 $score$ 中，同时将返回值累加到 $cnt$ 中。

遍历完子节点后，如果 $n - cnt > 0$，那么我们将 $n - cnt$ 累乘到 $score$ 中。

然后，我们判断 $mx$ 是否小于 $score$，如果小于，那么我们将 $mx$ 更新为 $score$，并将 $ans$ 更新为 $1$；如果等于，那么我们将 $ans$ 更新为 $ans + 1$。

最后，我们返回 $cnt$。

最终，我们调用 $dfs(0, -1)$，并返回 $ans$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是节点数目。

<!-- tabs:start -->

```python
class Solution:
    def countHighestScoreNodes(self, parents: List[int]) -> int:
        def dfs(i: int, fa: int):
            cnt = score = 1
            for j in g[i]:
                if j != fa:
                    t = dfs(j, i)
                    score *= t
                    cnt += t
            if n - cnt:
                score *= n - cnt
            nonlocal ans, mx
            if mx < score:
                mx = score
                ans = 1
            elif mx == score:
                ans += 1
            return cnt

        n = len(parents)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parents[i]].append(i)
        ans = mx = 0
        dfs(0, -1)
        return ans
```

```java
class Solution {
    private List<Integer>[] g;
    private int ans;
    private long mx;
    private int n;

    public int countHighestScoreNodes(int[] parents) {
        n = parents.length;
        g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            g[parents[i]].add(i);
        }
        dfs(0, -1);
        return ans;
    }

    private int dfs(int i, int fa) {
        int cnt = 1;
        long score = 1;
        for (int j : g[i]) {
            if (j != fa) {
                int t = dfs(j, i);
                cnt += t;
                score *= t;
            }
        }
        if (n - cnt > 0) {
            score *= n - cnt;
        }
        if (mx < score) {
            mx = score;
            ans = 1;
        } else if (mx == score) {
            ++ans;
        }
        return cnt;
    }
}
```

```cpp
class Solution {
public:
    int countHighestScoreNodes(vector<int>& parents) {
        int n = parents.size();
        vector<int> g[n];
        for (int i = 1; i < n; ++i) {
            g[parents[i]].push_back(i);
        }
        int ans = 0;
        long long mx = 0;
        function<int(int, int)> dfs = [&](int i, int fa) {
            long long score = 1;
            int cnt = 1;
            for (int j : g[i]) {
                if (j != fa) {
                    int t = dfs(j, i);
                    cnt += t;
                    score *= t;
                }
            }
            if (n - cnt) {
                score *= n - cnt;
            }
            if (mx < score) {
                mx = score;
                ans = 1;
            } else if (mx == score) {
                ++ans;
            }
            return cnt;
        };
        dfs(0, -1);
        return ans;
    }
};
```

```go
func countHighestScoreNodes(parents []int) (ans int) {
	n := len(parents)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[parents[i]] = append(g[parents[i]], i)
	}
	mx := 0
	var dfs func(i, fa int) int
	dfs = func(i, fa int) int {
		cnt, score := 1, 1
		for _, j := range g[i] {
			if j != fa {
				t := dfs(j, i)
				cnt += t
				score *= t
			}
		}
		if n-cnt > 0 {
			score *= n - cnt
		}
		if mx < score {
			mx = score
			ans = 1
		} else if mx == score {
			ans++
		}
		return cnt
	}
	dfs(0, -1)
	return
}
```

```ts
function countHighestScoreNodes(parents: number[]): number {
    const n = parents.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 1; i < n; i++) {
        g[parents[i]].push(i);
    }
    let [ans, mx] = [0, 0];
    const dfs = (i: number, fa: number): number => {
        let [cnt, score] = [1, 1];
        for (const j of g[i]) {
            if (j !== fa) {
                const t = dfs(j, i);
                cnt += t;
                score *= t;
            }
        }
        if (n - cnt) {
            score *= n - cnt;
        }
        if (mx < score) {
            mx = score;
            ans = 1;
        } else if (mx === score) {
            ans++;
        }
        return cnt;
    };
    dfs(0, -1);
    return ans;
}
```

```cs
public class Solution {
    private List<int>[] g;
    private int ans;
    private long mx;
    private int n;

    public int CountHighestScoreNodes(int[] parents) {
        n = parents.Length;
        g = new List<int>[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new List<int>();
        }
        for (int i = 1; i < n; ++i) {
            g[parents[i]].Add(i);
        }

        Dfs(0, -1);
        return ans;
    }

    private int Dfs(int i, int fa) {
        int cnt = 1;
        long score = 1;

        foreach (int j in g[i]) {
            if (j != fa) {
                int t = Dfs(j, i);
                cnt += t;
                score *= t;
            }
        }

        if (n - cnt > 0) {
            score *= n - cnt;
        }

        if (mx < score) {
            mx = score;
            ans = 1;
        } else if (mx == score) {
            ++ans;
        }

        return cnt;
    }
}
```

<!-- tabs:end -->

<!-- end -->
