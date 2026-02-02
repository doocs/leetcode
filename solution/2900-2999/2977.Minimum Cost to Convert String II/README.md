---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2977.Minimum%20Cost%20to%20Convert%20String%20II/README.md
rating: 2695
source: 第 377 场周赛 Q4
tags:
    - 图
    - 字典树
    - 数组
    - 字符串
    - 动态规划
    - 最短路
---

<!-- problem:start -->

# [2977. 转换字符串的最小成本 II](https://leetcode.cn/problems/minimum-cost-to-convert-string-ii)

[English Version](/solution/2900-2999/2977.Minimum%20Cost%20to%20Convert%20String%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个下标从 <strong>0</strong> 开始的字符串 <code>source</code> 和 <code>target</code> ，它们的长度均为 <code>n</code> 并且由 <strong>小写 </strong>英文字母组成。</p>

<p>另给你两个下标从 <strong>0</strong> 开始的字符串数组 <code>original</code> 和 <code>changed</code> ，以及一个整数数组 <code>cost</code> ，其中 <code>cost[i]</code> 代表将字符串 <code>original[i]</code> 更改为字符串 <code>changed[i]</code> 的成本。</p>

<p>你从字符串 <code>source</code> 开始。在一次操作中，<strong>如果 </strong>存在 <strong>任意</strong> 下标 <code>j</code> 满足 <code>cost[j] == z</code>&nbsp; 、<code>original[j] == x</code> 以及 <code>changed[j] == y</code> ，你就可以选择字符串中的 <strong>子串</strong> <code>x</code> 并以 <code>z</code> 的成本将其更改为 <code>y</code> 。 你可以执行 <strong>任意数量 </strong>的操作，但是任两次操作必须满足<strong> 以下两个 </strong>条件 <strong>之一</strong> ：</p>

<ul>
	<li>在两次操作中选择的子串分别是 <code>source[a..b]</code> 和 <code>source[c..d]</code> ，满足 <code>b &lt; c</code>&nbsp; <strong>或</strong> <code>d &lt; a</code> 。换句话说，两次操作中选择的下标<strong> 不相交 </strong>。</li>
	<li>在两次操作中选择的子串分别是 <code>source[a..b]</code> 和 <code>source[c..d]</code> ，满足 <code>a == c</code> <strong>且</strong> <code>b == d</code> 。换句话说，两次操作中选择的下标<strong> 相同 </strong>。</li>
</ul>

<p>返回将字符串 <code>source</code> 转换为字符串 <code>target</code> 所需的<strong> 最小 </strong>成本。如果不可能完成转换，则返回 <code>-1</code> 。</p>

<p><strong>注意</strong>，可能存在下标 <code>i</code> 、<code>j</code> 使得 <code>original[j] == original[i]</code> 且 <code>changed[j] == changed[i]</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>source = "abcd", target = "acbe", original = ["a","b","c","c","e","d"], changed = ["b","c","b","e","b","e"], cost = [2,5,5,1,2,20]
<strong>输出：</strong>28
<strong>解释：</strong>将 "abcd" 转换为 "acbe"，执行以下操作：
- 将子串 source[1..1] 从 "b" 改为 "c" ，成本为 5 。
- 将子串 source[2..2] 从 "c" 改为 "e" ，成本为 1 。
- 将子串 source[2..2] 从 "e" 改为 "b" ，成本为 2 。
- 将子串 source[3..3] 从 "d" 改为 "e" ，成本为 20 。
产生的总成本是 5 + 1 + 2 + 20 = 28 。 
可以证明这是可能的最小成本。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>source = "abcdefgh", target = "acdeeghh", original = ["bcd","fgh","thh"], changed = ["cde","thh","ghh"], cost = [1,3,5]
<strong>输出：</strong>9
<strong>解释：</strong>将 "abcdefgh" 转换为 "acdeeghh"，执行以下操作：
- 将子串 source[1..3] 从 "bcd" 改为 "cde" ，成本为 1 。
- 将子串 source[5..7] 从 "fgh" 改为 "thh" ，成本为 3 。可以执行此操作，因为下标 [5,7] 与第一次操作选中的下标不相交。
- 将子串 source[5..7] 从 "thh" 改为 "ghh" ，成本为 5 。可以执行此操作，因为下标 [5,7] 与第一次操作选中的下标不相交，且与第二次操作选中的下标相同。
产生的总成本是 1 + 3 + 5 = 9 。
可以证明这是可能的最小成本。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>source = "abcdefgh", target = "addddddd", original = ["bcd","defgh"], changed = ["ddd","ddddd"], cost = [100,1578]
<strong>输出：</strong>-1
<strong>解释：</strong>无法将 "abcdefgh" 转换为 "addddddd" 。
如果选择子串 source[1..3] 执行第一次操作，以将 "abcdefgh" 改为 "adddefgh" ，你无法选择子串 source[3..7] 执行第二次操作，因为两次操作有一个共用下标 3 。
如果选择子串 source[3..7] 执行第一次操作，以将 "abcdefgh" 改为 "abcddddd" ，你无法选择子串 source[1..3] 执行第二次操作，因为两次操作有一个共用下标 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= source.length == target.length &lt;= 1000</code></li>
	<li><code>source</code>、<code>target</code> 均由小写英文字母组成</li>
	<li><code>1 &lt;= cost.length == original.length == changed.length &lt;= 100</code></li>
	<li><code>1 &lt;= original[i].length == changed[i].length &lt;= source.length</code></li>
	<li><code>original[i]</code>、<code>changed[i]</code> 均由小写英文字母组成</li>
	<li><code>original[i] != changed[i]</code></li>
	<li><code>1 &lt;= cost[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：字典树 + Floyd 算法 + 记忆化搜索

根据题目描述，我们可以将每个字符串看作一个节点，每对字符串的转换成本看作一条有向边。那么我们先初始化一个 $26 \times 26$ 的二维数组 $g$，其中 $g[i][j]$ 表示字符串 $i$ 转换成字符串 $j$ 的最小成本。初始时 $g[i][j] = \infty$，如果 $i = j$，那么 $g[i][j] = 0$。在这里，我们可以借助字典树存储 `original` 和 `changed` 中的字符串以及对应的整数编号。

然后，我们使用 Floyd 算法计算出任意两个字符串之间的最小成本。

接下来，我们定义函数 $dfs(i)$ 表示将字符串 $source[i..]$ 转换为字符串 $target[i..]$ 所需的最小成本。那么答案就是 $dfs(0)$。

函数 $dfs(i)$ 的计算过程如下：

- 如果 $i \geq |source|$，说明不需要转换，返回 $0$。
- 否则，如果 $source[i] = target[i]$，那么可以直接跳过，我们直接递归计算 $dfs(i + 1)$。我们也可以在 $[i, |source|)$ 的范围内枚举下标 $j$，如果 $source[i..j]$ 和 $target[i..j]$ 都在字典树中，且其对应的整数编号 $x$ 和 $y$ 都大于等于 $0$，那么我们可以将 $dfs(j + 1)$ 和 $g[x][y]$ 相加，得到一种转换方案的成本，我们取所有方案中的最小值。

综上，我们可以得到：

$$
dfs(i) = \begin{cases}
0, & i \geq |source| \\
dfs(i + 1), & source[i] = target[i] \\
\min_{i \leq j < |source|} \{ dfs(j + 1) + g[x][y] \}, & \textit{otherwise}
\end{cases}
$$

其中 $x$ 和 $y$ 分别是 $source[i..j]$ 和 $target[i..j]$ 在字典树中对应的整数编号。

为了避免重复计算，我们可以使用记忆化搜索。

时间复杂度 $O(m^3 + n^2 + m \times n)$，空间复杂度 $O(m^2 + m \times n + n)$。其中 $m$ 和 $n$ 分别是数组 $original$ 和 $source$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Node:
    __slots__ = ["children", "v"]

    def __init__(self):
        self.children: List[Node | None] = [None] * 26
        self.v = -1


class Solution:
    def minimumCost(
        self,
        source: str,
        target: str,
        original: List[str],
        changed: List[str],
        cost: List[int],
    ) -> int:
        m = len(cost)
        g = [[inf] * (m << 1) for _ in range(m << 1)]
        for i in range(m << 1):
            g[i][i] = 0
        root = Node()
        idx = 0

        def insert(w: str) -> int:
            node = root
            for c in w:
                i = ord(c) - ord("a")
                if node.children[i] is None:
                    node.children[i] = Node()
                node = node.children[i]
            if node.v < 0:
                nonlocal idx
                node.v = idx
                idx += 1
            return node.v

        @cache
        def dfs(i: int) -> int:
            if i >= len(source):
                return 0
            res = dfs(i + 1) if source[i] == target[i] else inf
            p = q = root
            for j in range(i, len(source)):
                p = p.children[ord(source[j]) - ord("a")]
                q = q.children[ord(target[j]) - ord("a")]
                if p is None or q is None:
                    break
                if p.v < 0 or q.v < 0:
                    continue
                res = min(res, dfs(j + 1) + g[p.v][q.v])
            return res

        for x, y, z in zip(original, changed, cost):
            x = insert(x)
            y = insert(y)
            g[x][y] = min(g[x][y], z)
        for k in range(idx):
            for i in range(idx):
                if g[i][k] >= inf:
                    continue
                for j in range(idx):
                    # g[i][j] = min(g[i][j], g[i][k] + g[k][j])
                    if g[i][k] + g[k][j] < g[i][j]:
                        g[i][j] = g[i][k] + g[k][j]

        ans = dfs(0)
        return -1 if ans >= inf else ans
```

#### Java

```java
class Node {
    Node[] children = new Node[26];
    int v = -1;
}

class Solution {
    private final long inf = 1L << 60;
    private Node root = new Node();
    private int idx;

    private long[][] g;
    private char[] s;
    private char[] t;
    private Long[] f;

    public long minimumCost(
        String source, String target, String[] original, String[] changed, int[] cost) {
        int m = cost.length;
        g = new long[m << 1][m << 1];
        s = source.toCharArray();
        t = target.toCharArray();
        for (int i = 0; i < g.length; ++i) {
            Arrays.fill(g[i], inf);
            g[i][i] = 0;
        }
        for (int i = 0; i < m; ++i) {
            int x = insert(original[i]);
            int y = insert(changed[i]);
            g[x][y] = Math.min(g[x][y], cost[i]);
        }
        for (int k = 0; k < idx; ++k) {
            for (int i = 0; i < idx; ++i) {
                if (g[i][k] >= inf) {
                    continue;
                }
                for (int j = 0; j < idx; ++j) {
                    g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }
        f = new Long[s.length];
        long ans = dfs(0);
        return ans >= inf ? -1 : ans;
    }

    private int insert(String w) {
        Node node = root;
        for (char c : w.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null) {
                node.children[i] = new Node();
            }
            node = node.children[i];
        }
        if (node.v < 0) {
            node.v = idx++;
        }
        return node.v;
    }

    private long dfs(int i) {
        if (i >= s.length) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        long res = s[i] == t[i] ? dfs(i + 1) : inf;
        Node p = root, q = root;
        for (int j = i; j < s.length; ++j) {
            p = p.children[s[j] - 'a'];
            q = q.children[t[j] - 'a'];
            if (p == null || q == null) {
                break;
            }
            if (p.v < 0 || q.v < 0) {
                continue;
            }
            long t = g[p.v][q.v];
            if (t < inf) {
                res = Math.min(res, t + dfs(j + 1));
            }
        }
        return f[i] = res;
    }
}
```

#### C++

```cpp
class Node {
public:
    Node* children[26];
    int v = -1;
    Node() {
        fill(children, children + 26, nullptr);
    }
};

class Solution {
private:
    const long long inf = 1LL << 60;
    Node* root = new Node();
    int idx;

    vector<vector<long long>> g;
    string s;
    string t;
    vector<long long> f;

public:
    long long minimumCost(string source, string target, vector<string>& original, vector<string>& changed, vector<int>& cost) {
        int m = cost.size();
        g = vector<vector<long long>>(m << 1, vector<long long>(m << 1, inf));
        s = source;
        t = target;

        for (int i = 0; i < g.size(); ++i) {
            g[i][i] = 0;
        }

        for (int i = 0; i < m; ++i) {
            int x = insert(original[i]);
            int y = insert(changed[i]);
            g[x][y] = min(g[x][y], static_cast<long long>(cost[i]));
        }

        for (int k = 0; k < idx; ++k) {
            for (int i = 0; i < idx; ++i) {
                if (g[i][k] >= inf) {
                    continue;
                }
                for (int j = 0; j < idx; ++j) {
                    g[i][j] = min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }

        f = vector<long long>(s.length(), -1);
        long long ans = dfs(0);
        return ans >= inf ? -1 : ans;
    }

private:
    int insert(const string& w) {
        Node* node = root;
        for (char c : w) {
            int i = c - 'a';
            if (node->children[i] == nullptr) {
                node->children[i] = new Node();
            }
            node = node->children[i];
        }
        if (node->v < 0) {
            node->v = idx++;
        }
        return node->v;
    }

    long long dfs(int i) {
        if (i >= s.length()) {
            return 0;
        }
        if (f[i] != -1) {
            return f[i];
        }
        long long res = (s[i] == t[i]) ? dfs(i + 1) : inf;
        Node* p = root;
        Node* q = root;
        for (int j = i; j < s.length(); ++j) {
            p = p->children[s[j] - 'a'];
            q = q->children[t[j] - 'a'];
            if (p == nullptr || q == nullptr) {
                break;
            }
            if (p->v < 0 || q->v < 0) {
                continue;
            }
            long long temp = g[p->v][q->v];
            if (temp < inf) {
                res = min(res, temp + dfs(j + 1));
            }
        }
        return f[i] = res;
    }
};
```

#### Go

```go
type Node struct {
	children [26]*Node
	v        int
}

func newNode() *Node {
	return &Node{v: -1}
}

func minimumCost(source string, target string, original []string, changed []string, cost []int) int64 {
	inf := 1 << 60
	root := newNode()
	idx := 0
	m := len(cost)
	g := make([][]int, m<<1)
	for i := range g {
		g[i] = make([]int, m<<1)
		for j := range g[i] {
			g[i][j] = inf
		}
		g[i][i] = 0
	}
	insert := func(w string) int {
		node := root
		for _, c := range w {
			i := c - 'a'
			if node.children[i] == nil {
				node.children[i] = newNode()
			}
			node = node.children[i]
		}
		if node.v < 0 {
			node.v = idx
			idx++
		}
		return node.v
	}
	for i := range original {
		x := insert(original[i])
		y := insert(changed[i])
		g[x][y] = min(g[x][y], cost[i])
	}
	for k := 0; k < idx; k++ {
		for i := 0; i < idx; i++ {
			if g[i][k] >= inf {
				continue
			}
			for j := 0; j < idx; j++ {
				g[i][j] = min(g[i][j], g[i][k]+g[k][j])
			}
		}
	}
	n := len(source)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] >= 0 {
			return f[i]
		}
		f[i] = inf
		if source[i] == target[i] {
			f[i] = dfs(i + 1)
		}
		p, q := root, root
		for j := i; j < n; j++ {
			p = p.children[source[j]-'a']
			q = q.children[target[j]-'a']
			if p == nil || q == nil {
				break
			}
			if p.v < 0 || q.v < 0 {
				continue
			}
			f[i] = min(f[i], dfs(j+1)+g[p.v][q.v])
		}
		return f[i]
	}
	ans := dfs(0)
	if ans >= inf {
		ans = -1
	}
	return int64(ans)
}
```

#### TypeScript

```ts
class Node {
    children: (Node | null)[] = Array(26).fill(null);
    v: number = -1;
}

function minimumCost(
    source: string,
    target: string,
    original: string[],
    changed: string[],
    cost: number[],
): number {
    const m = cost.length;
    const n = source.length;
    const g: number[][] = Array.from({ length: m << 1 }, () => Array(m << 1).fill(Infinity));
    const root: Node = new Node();
    let idx: number = 0;
    const f: number[] = Array(n).fill(-1);
    const insert = (w: string): number => {
        let node: Node = root;
        for (const c of w) {
            const i: number = c.charCodeAt(0) - 'a'.charCodeAt(0);
            if (node.children[i] === null) {
                node.children[i] = new Node();
            }
            node = node.children[i] as Node;
        }
        if (node.v < 0) {
            node.v = idx++;
        }
        return node.v;
    };

    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        let res: number = source[i] === target[i] ? dfs(i + 1) : Infinity;
        let p: Node = root;
        let q: Node = root;
        for (let j = i; j < source.length; ++j) {
            p = p.children[source[j].charCodeAt(0) - 'a'.charCodeAt(0)] as Node;
            q = q.children[target[j].charCodeAt(0) - 'a'.charCodeAt(0)] as Node;
            if (p === null || q === null) {
                break;
            }
            if (p.v < 0 || q.v < 0) {
                continue;
            }
            const t: number = g[p.v][q.v];
            res = Math.min(res, t + dfs(j + 1));
        }
        return (f[i] = res);
    };

    for (let i = 0; i < m; ++i) {
        const x: number = insert(original[i]);
        const y: number = insert(changed[i]);
        g[x][y] = Math.min(g[x][y], cost[i]);
    }

    for (let k = 0; k < idx; ++k) {
        for (let i = 0; i < idx; ++i) {
            if (g[i][k] >= Infinity) {
                continue;
            }
            for (let j = 0; j < idx; ++j) {
                g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
            }
        }
    }
    const ans: number = dfs(0);
    return ans >= Infinity ? -1 : ans;
}
```

#### Rust

```rust
use std::cmp::min;

struct Node {
    children: [Option<Box<Node>>; 26],
    v: i32,
}

impl Node {
    fn new() -> Self {
        Self {
            children: std::array::from_fn(|_| None),
            v: -1,
        }
    }
}

impl Solution {
    pub fn minimum_cost(
        source: String,
        target: String,
        original: Vec<String>,
        changed: Vec<String>,
        cost: Vec<i32>,
    ) -> i64 {
        let inf: i64 = 1 << 60;
        let mut root = Box::new(Node::new());
        let mut idx: usize = 0;
        let m = cost.len();
        let n = m << 1;

        let mut g = vec![vec![inf; n]; n];
        for i in 0..n {
            g[i][i] = 0;
        }

        let mut insert = |w: &str, root: &mut Box<Node>, idx: &mut usize| -> usize {
            let mut node: &mut Box<Node> = root;
            for c in w.bytes() {
                let i = (c - b'a') as usize;
                if node.children[i].is_none() {
                    node.children[i] = Some(Box::new(Node::new()));
                }
                node = node.children[i].as_mut().unwrap();
            }
            if node.v < 0 {
                node.v = *idx as i32;
                *idx += 1;
            }
            node.v as usize
        };

        for i in 0..m {
            let x = insert(&original[i], &mut root, &mut idx);
            let y = insert(&changed[i], &mut root, &mut idx);
            g[x][y] = min(g[x][y], cost[i] as i64);
        }

        for k in 0..idx {
            for i in 0..idx {
                if g[i][k] >= inf {
                    continue;
                }
                for j in 0..idx {
                    let v = g[i][k] + g[k][j];
                    if v < g[i][j] {
                        g[i][j] = v;
                    }
                }
            }
        }

        let s = source.into_bytes();
        let t = target.into_bytes();
        let len = s.len();
        let mut f: Vec<Option<i64>> = vec![None; len];

        fn dfs(
            i: usize,
            s: &[u8],
            t: &[u8],
            root: &Box<Node>,
            g: &Vec<Vec<i64>>,
            f: &mut Vec<Option<i64>>,
            inf: i64,
        ) -> i64 {
            if i >= s.len() {
                return 0;
            }
            if let Some(v) = f[i] {
                return v;
            }
            let mut res = if s[i] == t[i] {
                dfs(i + 1, s, t, root, g, f, inf)
            } else {
                inf
            };
            let mut p: Option<&Box<Node>> = Some(root);
            let mut q: Option<&Box<Node>> = Some(root);
            for j in i..s.len() {
                p = p.and_then(|x| x.children[(s[j] - b'a') as usize].as_ref());
                q = q.and_then(|x| x.children[(t[j] - b'a') as usize].as_ref());
                if p.is_none() || q.is_none() {
                    break;
                }
                let pv = p.unwrap().v;
                let qv = q.unwrap().v;
                if pv < 0 || qv < 0 {
                    continue;
                }
                let c = g[pv as usize][qv as usize];
                if c < inf {
                    let v = c + dfs(j + 1, s, t, root, g, f, inf);
                    if v < res {
                        res = v;
                    }
                }
            }
            f[i] = Some(res);
            res
        }

        let ans = dfs(0, &s, &t, &root, &g, &mut f, inf);
        if ans >= inf { -1 } else { ans }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
