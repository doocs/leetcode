---
comments: true
difficulty: 困难
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 拓扑排序
    - 数组
---

<!-- problem:start -->

# [3383. 施法所需最低符文数量 🔒](https://leetcode.cn/problems/minimum-runes-to-add-to-cast-spell)

[English Version](/solution/3300-3399/3383.Minimum%20Runes%20to%20Add%20to%20Cast%20Spell/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Alice 刚刚从巫师学校毕业，并且希望施展一个魔法咒语来庆祝。魔法咒语包含某些需要集中魔力的焦点，其中一些焦点含有作为咒语能量源的魔法水晶。焦点可以通过 <strong>有向符文</strong>&nbsp;进行连接，这些符文将魔力流从一个焦点传输到另一个焦点。</p>

<p>给定一个整数&nbsp;<code>n</code>&nbsp;表示焦点的数量，以及一个整数数组&nbsp;<code>crystals</code>，其中&nbsp;<code>crystals[i]</code>&nbsp;表示有魔法水晶的焦点。同时给定两个整数数组&nbsp;<code>flowFrom</code> 和&nbsp;<code>flowTo</code>，表示存在的 <strong>有向符文</strong>。第&nbsp;<code>i<sup>th</sup></code>&nbsp;个符文允许魔力流从焦点&nbsp;<code>flowFrom[i]</code>&nbsp;传输到焦点&nbsp;<code>flowTo[i]</code>。</p>

<p>你需要找到 Alice 必须添加到她的咒语中的定向符文数量，使得每个焦点要么：</p>

<ul>
	<li><strong>包含</strong>&nbsp;一个魔法水晶。</li>
	<li>从其它焦点&nbsp;<strong>接收</strong>&nbsp;魔力流。</li>
</ul>

<p>返回她所需要添加的 <strong>最少</strong>&nbsp;有向符文数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 6, crystals = [0], flowFrom = [0,1,2,3], flowTo = [1,2,3,0]</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><b>解释：</b></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3383.Minimum%20Runes%20to%20Add%20to%20Cast%20Spell/images/runesexample0.png" style="width: 250px; height: 252px;" /></p>

<p>添加两个有向符文：</p>

<ul>
	<li>从焦点 0 到焦点 4。</li>
	<li>从焦点 0 到焦点 5。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>n = 7, crystals = [3,5], flowFrom = [0,1,2,3,5], flowTo = [1,2,0,4,6]</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><b>解释：</b></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3383.Minimum%20Runes%20to%20Add%20to%20Cast%20Spell/images/runesexample1.png" style="width: 250px; height: 250px;" /></p>

<p>添加从焦点 4 到焦点 2 的有向符文。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= crystals.length &lt;= n</code></li>
	<li><code>0 &lt;= crystals[i] &lt;= n - 1</code></li>
	<li><code>1 &lt;= flowFrom.length == flowTo.length &lt;= min(2 * 10<sup>5</sup>, (n * (n - 1)) / 2)</code></li>
	<li><code>0 &lt;= flowFrom[i], flowTo[i] &lt;= n - 1</code></li>
	<li><code>flowFrom[i] != flowTo[i]</code></li>
	<li>所有的有向符文 <strong>互不相同</strong>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 有向图中晶体已点亮其可达点，问最少再加几条从「已亮」出发的边，使全图可达。$n \le 10^5$，先把已亮闭包 BFS 掉。
>
> 剩余点按 DFS 完成序倒序处理，相当于在缩点后的 DAG 上从汇点往回看源。
>
> 每个尚未被照亮的完成序节点需要一条新符文，再 BFS 扩大闭包。这样每条新边都连接一个尚未覆盖的弱连通源。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minRunesToAdd(
        self, n: int, crystals: List[int], flowFrom: List[int], flowTo: List[int]
    ) -> int:
        def bfs(q: Deque[int]):
            while q:
                a = q.popleft()
                for b in g[a]:
                    if vis[b] == 1:
                        continue
                    vis[b] = 1
                    q.append(b)

        def dfs(a: int):
            vis[a] = 2
            for b in g[a]:
                if vis[b] > 0:
                    continue
                dfs(b)
            seq.append(a)

        g = [[] for _ in range(n)]
        for a, b in zip(flowFrom, flowTo):
            g[a].append(b)

        q = deque(crystals)
        vis = [0] * n
        for x in crystals:
            vis[x] = 1
        bfs(q)

        seq = []
        for i in range(n):
            if vis[i] == 0:
                dfs(i)
        seq.reverse()
        ans = 0
        for i in seq:
            if vis[i] == 2:
                q = deque([i])
                vis[i] = 1
                bfs(q)
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    private int[] vis;
    private List<Integer>[] g;
    private List<Integer> seq = new ArrayList<>();

    public int minRunesToAdd(int n, int[] crystals, int[] flowFrom, int[] flowTo) {
        g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < flowFrom.length; ++i) {
            g[flowFrom[i]].add(flowTo[i]);
        }
        Deque<Integer> q = new ArrayDeque<>();
        vis = new int[n];
        for (int i : crystals) {
            vis[i] = 1;
            q.offer(i);
        }
        bfs(q);
        for (int i = 0; i < n; ++i) {
            if (vis[i] == 0) {
                dfs(i);
            }
        }
        int ans = 0;
        for (int i = seq.size() - 1; i >= 0; --i) {
            int a = seq.get(i);
            if (vis[a] == 2) {
                vis[a] = 1;
                q.clear();
                q.offer(a);
                bfs(q);
                ++ans;
            }
        }
        return ans;
    }

    private void bfs(Deque<Integer> q) {
        while (!q.isEmpty()) {
            int a = q.poll();
            for (int b : g[a]) {
                if (vis[b] == 1) {
                    continue;
                }
                vis[b] = 1;
                q.offer(b);
            }
        }
    }

    private void dfs(int a) {
        vis[a] = 2;
        for (int b : g[a]) {
            if (vis[b] > 0) {
                continue;
            }
            dfs(b);
        }
        seq.add(a);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> vis;
    vector<vector<int>> g;
    vector<int> seq;

    int minRunesToAdd(int n, vector<int>& crystals, vector<int>& flowFrom, vector<int>& flowTo) {
        g.resize(n);
        for (int i = 0; i < flowFrom.size(); ++i) {
            g[flowFrom[i]].push_back(flowTo[i]);
        }

        deque<int> q;
        vis.resize(n, 0);
        for (int i : crystals) {
            vis[i] = 1;
            q.push_back(i);
        }
        bfs(q);

        for (int i = 0; i < n; ++i) {
            if (vis[i] == 0) {
                dfs(i);
            }
        }

        int ans = 0;
        for (int i = seq.size() - 1; i >= 0; --i) {
            int a = seq[i];
            if (vis[a] == 2) {
                vis[a] = 1;
                q.clear();
                q.push_back(a);
                bfs(q);
                ++ans;
            }
        }
        return ans;
    }

private:
    void bfs(deque<int>& q) {
        while (!q.empty()) {
            int a = q.front();
            q.pop_front();
            for (int b : g[a]) {
                if (vis[b] == 1) {
                    continue;
                }
                vis[b] = 1;
                q.push_back(b);
            }
        }
    }

    void dfs(int a) {
        vis[a] = 2;
        for (int b : g[a]) {
            if (vis[b] > 0) {
                continue;
            }
            dfs(b);
        }
        seq.push_back(a);
    }
};
```

#### Go

```go
func minRunesToAdd(n int, crystals []int, flowFrom []int, flowTo []int) (ans int) {
	g := make([][]int, n)
	for i := 0; i < len(flowFrom); i++ {
		a, b := flowFrom[i], flowTo[i]
		g[a] = append(g[a], b)
	}

	vis := make([]int, n)
	for _, x := range crystals {
		vis[x] = 1
	}

	bfs := func(q []int) {
		for len(q) > 0 {
			a := q[0]
			q = q[1:]
			for _, b := range g[a] {
				if vis[b] == 1 {
					continue
				}
				vis[b] = 1
				q = append(q, b)
			}
		}
	}

	seq := []int{}
	var dfs func(a int)
	dfs = func(a int) {
		vis[a] = 2
		for _, b := range g[a] {
			if vis[b] > 0 {
				continue
			}
			dfs(b)
		}
		seq = append(seq, a)
	}

	q := crystals
	bfs(q)

	for i := 0; i < n; i++ {
		if vis[i] == 0 {
			dfs(i)
		}
	}

	for i, j := 0, len(seq)-1; i < j; i, j = i+1, j-1 {
		seq[i], seq[j] = seq[j], seq[i]
	}
	for _, i := range seq {
		if vis[i] == 2 {
			q = []int{i}
			vis[i] = 1
			bfs(q)
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function minRunesToAdd(
    n: number,
    crystals: number[],
    flowFrom: number[],
    flowTo: number[],
): number {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 0; i < flowFrom.length; i++) {
        const a = flowFrom[i],
            b = flowTo[i];
        g[a].push(b);
    }

    const vis: number[] = Array(n).fill(0);
    for (const x of crystals) {
        vis[x] = 1;
    }

    const bfs = (q: number[]) => {
        while (q.length > 0) {
            const a = q.shift()!;
            for (const b of g[a]) {
                if (vis[b] === 1) continue;
                vis[b] = 1;
                q.push(b);
            }
        }
    };

    const seq: number[] = [];
    const dfs = (a: number) => {
        vis[a] = 2;
        for (const b of g[a]) {
            if (vis[b] > 0) continue;
            dfs(b);
        }
        seq.push(a);
    };

    bfs(crystals);

    for (let i = 0; i < n; i++) {
        if (vis[i] === 0) {
            dfs(i);
        }
    }

    seq.reverse();

    let ans = 0;
    for (const i of seq) {
        if (vis[i] === 2) {
            bfs([i]);
            vis[i] = 1;
            ans++;
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
