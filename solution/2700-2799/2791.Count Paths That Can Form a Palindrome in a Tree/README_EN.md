---
comments: true
difficulty: Hard
rating: 2677
source: Weekly Contest 355 Q4
tags:
    - Bit Manipulation
    - Tree
    - Depth-First Search
    - Hash Table
---

<!-- problem:start -->

# [2791. Count Paths That Can Form a Palindrome in a Tree](https://leetcode.com/problems/count-paths-that-can-form-a-palindrome-in-a-tree)

[中文文档](/solution/2700-2799/2791.Count%20Paths%20That%20Can%20Form%20a%20Palindrome%20in%20a%20Tree/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>tree</strong> (i.e. a connected, undirected graph that has no cycles) <strong>rooted</strong> at node <code>0</code> consisting of <code>n</code> nodes numbered from <code>0</code> to <code>n - 1</code>. The tree is represented by a <strong>0-indexed</strong> array <code>parent</code> of size <code>n</code>, where <code>parent[i]</code> is the parent of node <code>i</code>. Since node <code>0</code> is the root, <code>parent[0] == -1</code>.</p>

<p>You are also given a string <code>s</code> of length <code>n</code>, where <code>s[i]</code> is the character assigned to the edge between <code>i</code> and <code>parent[i]</code>. <code>s[0]</code> can be ignored.</p>

<p>Return <em>the number of pairs of nodes </em><code>(u, v)</code><em> such that </em><code>u &lt; v</code><em> and the characters assigned to edges on the path from </em><code>u</code><em> to </em><code>v</code><em> can be <strong>rearranged</strong> to form a <strong>palindrome</strong></em>.</p>

<p>A string is a <strong>palindrome</strong> when it reads the same backwards as forwards.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2700-2799/2791.Count%20Paths%20That%20Can%20Form%20a%20Palindrome%20in%20a%20Tree/images/treedrawio-8drawio.png" style="width: 281px; height: 181px;" /></p>

<pre>
<strong>Input:</strong> parent = [-1,0,0,1,1,2], s = &quot;acaabc&quot;
<strong>Output:</strong> 8
<strong>Explanation:</strong> The valid pairs are:
- All the pairs (0,1), (0,2), (1,3), (1,4) and (2,5) result in one character which is always a palindrome.
- The pair (2,3) result in the string &quot;aca&quot; which is a palindrome.
- The pair (1,5) result in the string &quot;cac&quot; which is a palindrome.
- The pair (3,5) result in the string &quot;acac&quot; which can be rearranged into the palindrome &quot;acca&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> parent = [-1,0,0,0,0], s = &quot;aaaaa&quot;
<strong>Output:</strong> 10
<strong>Explanation:</strong> Any pair of nodes (u,v) where u &lt; v is valid.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == parent.length == s.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= parent[i] &lt;= n - 1</code> for all <code>i &gt;= 1</code></li>
	<li><code>parent[0] == -1</code></li>
	<li><code>parent</code> represents a valid tree.</li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> Count simple paths whose characters can be rearranged into a palindrome. There are quadratically many paths and the labels are letters, so enumeration is impossible.
>
> XOR-pack the parity of letters from the root to a node in a $20$-bit mask; a path is the XOR of its ends. A palindrome permutation allows at most one bit set. During a DFS, a counter looks up the same mask and the twenty masks that differ by one bit, then records the current mask.

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

### Solution 2: Explicit Stack

<!-- thinking:start -->

> **Thinking**
>
> A path can be rearranged into a palindrome exactly when at most one letter has an odd count. With $n \le 10^5$, enumerating pairs is $O(n^2)$, and recursing into each child from the root is too deep: a chain makes the call depth $n$.
>
> The parity of letters from the root to a node packs into a bitmask, and the parity on the path between two nodes is the XOR of those masks. A palindrome allows at most one bit set. The root mask $0$ is placed in the counter first.
>
> An explicit stack runs a preorder. After a node is popped, each child mask is the parent mask XOR the edge letter. We add the counts of the same mask and of the masks that differ by one bit, then store the child mask and push the child. Each pair is counted only at the node visited later, so the order of siblings does not change the answer.

<!-- thinking:end -->

An explicit stack walks the tree in preorder from the root. The root mask $0$ is stored in the hash map first. For each edge, the child mask is the parent mask XOR the edge letter. Paths that form a palindrome with an already visited node are added to the answer, and the child mask is then recorded.

The time complexity is $O(n \times |\Sigma|)$ and the space complexity is $O(n)$, where $|\Sigma|$ is the alphabet size.

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
