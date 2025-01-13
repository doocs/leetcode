---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3383.Minimum%20Runes%20to%20Add%20to%20Cast%20Spell/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Graph
    - Topological Sort
    - Array
---

<!-- problem:start -->

# [3383. Minimum Runes to Add to Cast Spell 🔒](https://leetcode.com/problems/minimum-runes-to-add-to-cast-spell)

[中文文档](/solution/3300-3399/3383.Minimum%20Runes%20to%20Add%20to%20Cast%20Spell/README.md)

## Description

<!-- description:start -->

<p>Alice has just graduated from wizard school, and wishes to cast a magic spell to celebrate. The magic spell contains certain <strong>focus points</strong> where magic needs to be concentrated, and some of these focus points contain <strong>magic crystals</strong> which serve as the spell&#39;s energy source. Focus points can be linked through <strong>directed runes</strong>, which channel magic flow from one focus point to another.</p>

<p>You are given a integer <code>n</code> denoting the <em>number</em> of focus points and an array of integers <code>crystals</code> where <code>crystals[i]</code> indicates a focus point which holds a magic crystal. You are also given two integer arrays <code>flowFrom</code> and <code>flowTo</code>, which represent the existing <strong>directed runes</strong>. The <code>i<sup>th</sup></code> rune allows magic to freely flow from focus point <code>flowFrom[i]</code> to focus point <code>flowTo[i]</code>.</p>

<p>You need to find the number of directed runes Alice must add to her spell, such that <em>each</em> focus point either:</p>

<ul>
	<li><strong>Contains</strong> a magic crystal.</li>
	<li><strong>Receives</strong> magic flow <em>from</em> another focus point.</li>
</ul>

<p>Return the <strong>minimum</strong> number of directed runes that she should add.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 6, crystals = [0], flowFrom = [0,1,2,3], flowTo = [1,2,3,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3383.Minimum%20Runes%20to%20Add%20to%20Cast%20Spell/images/runesexample0.png" style="width: 250px; height: 252px;" /></p>

<p>Add two directed runes:</p>

<ul>
	<li>From focus point&nbsp;0 to focus point&nbsp;4.</li>
	<li>From focus point 0 to focus point 5.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 7, crystals = [3,5], flowFrom = [0,1,2,3,5], flowTo = [1,2,0,4,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3300-3399/3383.Minimum%20Runes%20to%20Add%20to%20Cast%20Spell/images/runesexample1.png" style="width: 250px; height: 250px;" /></p>

<p>Add a directed rune from focus point 4 to focus point 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= crystals.length &lt;= n</code></li>
	<li><code>0 &lt;= crystals[i] &lt;= n - 1</code></li>
	<li><code>1 &lt;= flowFrom.length == flowTo.length &lt;= min(2 * 10<sup>5</sup>, (n * (n - 1)) / 2)</code></li>
	<li><code>0 &lt;= flowFrom[i], flowTo[i] &lt;= n - 1</code></li>
	<li><code>flowFrom[i] != flowTo[i]</code></li>
	<li>All pre-existing directed runes are <strong>distinct</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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
