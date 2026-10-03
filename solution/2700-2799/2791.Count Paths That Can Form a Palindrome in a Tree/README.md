---
comments: true
difficulty: 困难
rating: 2677
source: 第 355 场周赛 Q4
tags:
    - 位运算
    - 树
    - 深度优先搜索
    - 哈希表
---

<!-- problem:start -->

# [2791. 树中可以形成回文的路径数](https://leetcode.cn/problems/count-paths-that-can-form-a-palindrome-in-a-tree)

[English Version](/solution/2700-2799/2791.Count%20Paths%20That%20Can%20Form%20a%20Palindrome%20in%20a%20Tree/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一棵 <strong>树</strong>（即，一个连通、无向且无环的图），<strong>根</strong> 节点为 <code>0</code> ，由编号从 <code>0</code> 到 <code>n - 1</code> 的 <code>n</code> 个节点组成。这棵树用一个长度为 <code>n</code> 、下标从 <strong>0</strong> 开始的数组 <code>parent</code> 表示，其中 <code>parent[i]</code> 为节点 <code>i</code> 的父节点，由于节点 <code>0</code> 为根节点，所以 <code>parent[0] == -1</code> 。</p>

<p>另给你一个长度为 <code>n</code> 的字符串 <code>s</code> ，其中 <code>s[i]</code> 是分配给 <code>i</code> 和 <code>parent[i]</code> 之间的边的字符。<code>s[0]</code> 可以忽略。</p>

<p>找出满足 <code>u &lt; v</code> ，且从 <code>u</code> 到 <code>v</code> 的路径上分配的字符可以 <strong>重新排列</strong> 形成 <strong>回文</strong> 的所有节点对&nbsp;<code>(u, v)</code> ，并返回节点对的数目。</p>

<p>如果一个字符串正着读和反着读都相同，那么这个字符串就是一个 <strong>回文</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2791.Count%20Paths%20That%20Can%20Form%20a%20Palindrome%20in%20a%20Tree/images/treedrawio-8drawio.png" style="width: 281px; height: 181px;" /></p>

<pre>
<strong>输入：</strong>parent = [-1,0,0,1,1,2], s = "acaabc"
<strong>输出：</strong>8
<strong>解释：</strong>符合题目要求的节点对分别是：
- (0,1)、(0,2)、(1,3)、(1,4) 和 (2,5) ，路径上只有一个字符，满足回文定义。
- (2,3)，路径上字符形成的字符串是 "aca" ，满足回文定义。
- (1,5)，路径上字符形成的字符串是 "cac" ，满足回文定义。
- (3,5)，路径上字符形成的字符串是 "acac" ，可以重排形成回文 "acca" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>parent = [-1,0,0,0,0], s = "aaaaa"
<strong>输出：</strong>10
<strong>解释：</strong>任何满足 u &lt; v 的节点对 (u,v) 都符合题目要求。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == parent.length == s.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li>对于所有 <code>i &gt;= 1</code> ，<code>0 &lt;= parent[i] &lt;= n - 1</code> 均成立</li>
	<li><code>parent[0] == -1</code></li>
	<li><code>parent</code> 表示一棵有效的树</li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 树上简单路径对应的字符重排后若能成回文，则计入答案。路径条数为平方，边权是字母，直接枚举路径不可行。
>
> 根到点的字母奇偶压成 $20$ 位掩码，路径异或为两端到根的异或。重排回文要求异或至多一位为 $1$。DFS 时用计数器查询已出现的相同掩码以及只差一位的掩码，再把当前掩码入库。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPalindromePaths(self, parent: List[int], s: str) -> int:
        def dfs(i: int, xor: int):
            nonlocal ans
            for j, v in g[i]:
                x = xor ^ v
                ans += cnt[x]
                for k in range(26):
                    ans += cnt[x ^ (1 << k)]
                cnt[x] += 1
                dfs(j, x)

        n = len(parent)
        g = defaultdict(list)
        for i in range(1, n):
            p = parent[i]
            g[p].append((i, 1 << (ord(s[i]) - ord('a'))))
        ans = 0
        cnt = Counter({0: 1})
        dfs(0, 0)
        return ans
```

#### Java

```java
class Solution {
    private List<int[]>[] g;
    private Map<Integer, Integer> cnt = new HashMap<>();
    private long ans;

    public long countPalindromePaths(List<Integer> parent, String s) {
        int n = parent.size();
        g = new List[n];
        cnt.put(0, 1);
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            int p = parent.get(i);
            g[p].add(new int[] {i, 1 << (s.charAt(i) - 'a')});
        }
        dfs(0, 0);
        return ans;
    }

    private void dfs(int i, int xor) {
        for (int[] e : g[i]) {
            int j = e[0], v = e[1];
            int x = xor ^ v;
            ans += cnt.getOrDefault(x, 0);
            for (int k = 0; k < 26; ++k) {
                ans += cnt.getOrDefault(x ^ (1 << k), 0);
            }
            cnt.merge(x, 1, Integer::sum);
            dfs(j, x);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countPalindromePaths(vector<int>& parent, string s) {
        int n = parent.size();
        vector<vector<pair<int, int>>> g(n);
        unordered_map<int, int> cnt;
        cnt[0] = 1;
        for (int i = 1; i < n; ++i) {
            int p = parent[i];
            g[p].emplace_back(i, 1 << (s[i] - 'a'));
        }
        long long ans = 0;
        function<void(int, int)> dfs = [&](int i, int xo) {
            for (auto [j, v] : g[i]) {
                int x = xo ^ v;
                ans += cnt[x];
                for (int k = 0; k < 26; ++k) {
                    ans += cnt[x ^ (1 << k)];
                }
                ++cnt[x];
                dfs(j, x);
            }
        };
        dfs(0, 0);
        return ans;
    }
};
```

#### Go

```go
func countPalindromePaths(parent []int, s string) (ans int64) {
	type pair struct{ i, v int }
	n := len(parent)
	g := make([][]pair, n)
	for i := 1; i < n; i++ {
		p := parent[i]
		g[p] = append(g[p], pair{i, 1 << (s[i] - 'a')})
	}
	cnt := map[int]int{0: 1}
	var dfs func(i, xor int)
	dfs = func(i, xor int) {
		for _, e := range g[i] {
			x := xor ^ e.v
			ans += int64(cnt[x])
			for k := 0; k < 26; k++ {
				ans += int64(cnt[x^(1<<k)])
			}
			cnt[x]++
			dfs(e.i, x)
		}
	}
	dfs(0, 0)
	return
}
```

#### TypeScript

```ts
function countPalindromePaths(parent: number[], s: string): number {
    const n = parent.length;
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (let i = 1; i < n; ++i) {
        g[parent[i]].push([i, 1 << (s.charCodeAt(i) - 97)]);
    }
    const cnt: Map<number, number> = new Map();
    cnt.set(0, 1);
    let ans = 0;
    const dfs = (i: number, xor: number): void => {
        for (const [j, v] of g[i]) {
            const x = xor ^ v;
            ans += cnt.get(x) || 0;
            for (let k = 0; k < 26; ++k) {
                ans += cnt.get(x ^ (1 << k)) || 0;
            }
            cnt.set(x, (cnt.get(x) || 0) + 1);
            dfs(j, x);
        }
    };
    dfs(0, 0);
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：显式栈

<!-- thinking:start -->

> **思考**
>
> 路径上的字符重排后能成回文，当且仅当至多一种字母出现奇数次。$n \le 10^5$，枚举点对是 $O(n^2)$；从根递归进入每个孩子，链上的调用深度就是 $n$，会超出递归栈。
>
> 根到点的字母奇偶可以压成掩码，两点间路径的奇偶就是两端掩码的异或，回文要求这个异或至多一位为 $1$。先把根的掩码 $0$ 放进计数器。
>
> 显式栈做先序。弹出节点后，对每个孩子用父掩码异或边权得到子掩码，累加计数器里相同掩码以及只差一位的掩码，再把子掩码入库并入栈。每一对节点只在后访问的一端计数一次，所以兄弟的处理顺序不影响答案。

<!-- thinking:end -->

用显式栈从根做先序遍历。根的掩码 $0$ 预先计入哈希表。访问每条边时，用父节点掩码异或该边字母得到子节点掩码，把与已访问节点形成回文的路径数累加进答案，再把子节点掩码入库。

时间复杂度 $O(n \times |\Sigma|)$，空间复杂度 $O(n)$。其中 $|\Sigma|$ 是字母表大小。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countPalindromePaths(self, parent: List[int], s: str) -> int:
        n = len(parent)
        g = [[] for _ in range(n)]
        for i in range(1, n):
            g[parent[i]].append((i, 1 << (ord(s[i]) - ord('a'))))
        ans = 0
        cnt = Counter({0: 1})
        stk = [(0, 0)]
        while stk:
            i, xor = stk.pop()
            for j, v in g[i]:
                x = xor ^ v
                ans += cnt[x]
                for k in range(26):
                    ans += cnt[x ^ (1 << k)]
                cnt[x] += 1
                stk.append((j, x))
        return ans
```

#### Java

```java
class Solution {
    public long countPalindromePaths(List<Integer> parent, String s) {
        int n = parent.size();
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            int p = parent.get(i);
            g[p].add(new int[] {i, 1 << (s.charAt(i) - 'a')});
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        long ans = 0;
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], xor = cur[1];
            for (int[] e : g[i]) {
                int j = e[0], v = e[1];
                int x = xor ^ v;
                ans += cnt.getOrDefault(x, 0);
                for (int k = 0; k < 26; ++k) {
                    ans += cnt.getOrDefault(x ^ (1 << k), 0);
                }
                cnt.merge(x, 1, Integer::sum);
                stk.push(new int[] {j, x});
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countPalindromePaths(vector<int>& parent, string s) {
        int n = parent.size();
        vector<vector<pair<int, int>>> g(n);
        for (int i = 1; i < n; ++i) {
            g[parent[i]].emplace_back(i, 1 << (s[i] - 'a'));
        }
        unordered_map<int, int> cnt;
        cnt[0] = 1;
        long long ans = 0;
        vector<pair<int, int>> stk{{0, 0}};
        while (!stk.empty()) {
            auto [i, xo] = stk.back();
            stk.pop_back();
            for (auto [j, v] : g[i]) {
                int x = xo ^ v;
                ans += cnt[x];
                for (int k = 0; k < 26; ++k) {
                    ans += cnt[x ^ (1 << k)];
                }
                ++cnt[x];
                stk.emplace_back(j, x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countPalindromePaths(parent []int, s string) (ans int64) {
	type pair struct{ i, v int }
	n := len(parent)
	g := make([][]pair, n)
	for i := 1; i < n; i++ {
		p := parent[i]
		g[p] = append(g[p], pair{i, 1 << (s[i] - 'a')})
	}
	cnt := map[int]int{0: 1}
	stk := []pair{{0, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		for _, e := range g[cur.i] {
			x := cur.v ^ e.v
			ans += int64(cnt[x])
			for k := 0; k < 26; k++ {
				ans += int64(cnt[x^(1<<k)])
			}
			cnt[x]++
			stk = append(stk, pair{e.i, x})
		}
	}
	return
}
```

#### TypeScript

```ts
function countPalindromePaths(parent: number[], s: string): number {
    const n = parent.length;
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (let i = 1; i < n; ++i) {
        g[parent[i]].push([i, 1 << (s.charCodeAt(i) - 97)]);
    }
    const cnt: Map<number, number> = new Map();
    cnt.set(0, 1);
    let ans = 0;
    const stk: [number, number][] = [[0, 0]];
    while (stk.length) {
        const [i, xor] = stk.pop()!;
        for (const [j, v] of g[i]) {
            const x = xor ^ v;
            ans += cnt.get(x) || 0;
            for (let k = 0; k < 26; ++k) {
                ans += cnt.get(x ^ (1 << k)) || 0;
            }
            cnt.set(x, (cnt.get(x) || 0) + 1);
            stk.push([j, x]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
