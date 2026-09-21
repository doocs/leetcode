---
comments: true
difficulty: 困难
rating: 2486
source: 第 247 场周赛 Q4
tags:
    - 树
    - 深度优先搜索
    - 图
    - 拓扑排序
    - 数组
    - 数学
    - 动态规划
    - 组合数学
---

<!-- problem:start -->

# [1916. 统计为蚁群构筑房间的不同顺序](https://leetcode.cn/problems/count-ways-to-build-rooms-in-an-ant-colony)

[English Version](/solution/1900-1999/1916.Count%20Ways%20to%20Build%20Rooms%20in%20an%20Ant%20Colony/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你是一只蚂蚁，负责为蚁群构筑 <code>n</code> 间编号从 <code>0</code> 到 <code>n-1</code> 的新房间。给你一个 <strong>下标从 0 开始</strong> 且长度为 <code>n</code> 的整数数组&nbsp;<code>prevRoom</code> 作为扩建计划。其中，<code>prevRoom[i]</code> 表示在构筑房间 <code>i</code> 之前，你必须先构筑房间 <code>prevRoom[i]</code> ，并且这两个房间必须 <strong>直接</strong> 相连。房间 <code>0</code> 已经构筑完成，所以 <code>prevRoom[0] = -1</code> 。扩建计划中还有一条硬性要求，在完成所有房间的构筑之后，从房间 <code>0</code> 可以访问到每个房间。</p>

<p>你一次只能构筑 <strong>一个</strong> 房间。你可以在 <strong>已经构筑好的</strong> 房间之间自由穿行，只要这些房间是 <strong>相连的</strong> 。如果房间&nbsp;<code>prevRoom[i]</code> 已经构筑完成，那么你就可以构筑房间 <code>i</code>。</p>

<p>返回你构筑所有房间的 <strong>不同顺序的数目</strong> 。由于答案可能很大，请返回对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1916.Count%20Ways%20to%20Build%20Rooms%20in%20an%20Ant%20Colony/images/d1.jpg" style="width: 200px; height: 212px;" />
<pre>
<strong>输入：</strong><code>prevRoom</code> = [-1,0,1]
<strong>输出：</strong>1
<strong>解释：</strong>仅有一种方案可以完成所有房间的构筑：0 → 1 → 2
</pre>

<p><strong>示例 2：</strong></p>
<strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1916.Count%20Ways%20to%20Build%20Rooms%20in%20an%20Ant%20Colony/images/d2.jpg" style="width: 200px; height: 239px;" /></strong>

<pre>
<strong>输入：</strong><code>prevRoom</code> = [-1,0,0,1,2]
<strong>输出：</strong>6
<strong>解释：
</strong>有 6 种不同顺序：
0 → 1 → 3 → 2 → 4
0 → 2 → 4 → 1 → 3
0 → 1 → 2 → 3 → 4
0 → 1 → 2 → 4 → 3
0 → 2 → 1 → 3 → 4
0 → 2 → 1 → 4 → 3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == prevRoom.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>prevRoom[0] == -1</code></li>
	<li>对于所有的&nbsp;<code>1 &lt;= i &lt; n</code>&nbsp;，都有&nbsp;<code>0 &lt;= prevRoom[i] &lt; n</code></li>
	<li>题目保证所有房间都构筑完成后，从房间 <code>0</code> 可以访问到每个房间</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 建造顺序必须尊重树形先序约束，合法序列是该有根树的拓扑序。枚举排列再检验在 $n\le 10^5$ 时不可行。
>
> 子树内部的建造顺序相互独立，合并两棵子树时只需在合并后的位置中为其中一棵选择下标，方案数为组合数。多子树则从左到右陆续合并。
>
> DFS 返回子树大小，对每个结点用 $\binom{s+t}{t}$ 把已合并大小 $s$ 与新子树大小 $t$ 相乘，并对模数取余。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def waysToBuildRooms(self, prevRoom: List[int]) -> int:
        modulo = 10**9 + 7
        ingoing = defaultdict(set)
        outgoing = defaultdict(set)

        for i in range(1, len(prevRoom)):
            ingoing[i].add(prevRoom[i])
            outgoing[prevRoom[i]].add(i)
        ans = [1]

        def recurse(i):
            if len(outgoing[i]) == 0:
                return 1

            nodes_in_tree = 0
            for v in outgoing[i]:
                cn = recurse(v)
                if nodes_in_tree != 0:
                    ans[0] *= comb(nodes_in_tree + cn, cn)
                    ans[0] %= modulo
                nodes_in_tree += cn
            return nodes_in_tree + 1

        recurse(0)
        return ans[0] % modulo
```

#### Java

```java
class Solution {
    private static final int MOD = 1_000_000_007;
    private List<Integer>[] g;
    private long[] fact;
    private long[] invFact;
    private long ans = 1;

    public int waysToBuildRooms(int[] prevRoom) {
        int n = prevRoom.length;
        g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            g[prevRoom[i]].add(i);
        }
        fact = new long[n + 1];
        invFact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; ++i) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[n] = qpow(fact[n], MOD - 2);
        for (int i = n; i > 0; --i) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }
        dfs(0);
        return (int) ans;
    }

    private int dfs(int u) {
        int merged = 0;
        for (int v : g[u]) {
            int cn = dfs(v);
            if (merged != 0) {
                ans = ans * comb(merged + cn, cn) % MOD;
            }
            merged += cn;
        }
        return merged + 1;
    }

    private long comb(int n, int k) {
        return fact[n] * invFact[k] % MOD * invFact[n - k] % MOD;
    }

    private long qpow(long a, long n) {
        long ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % MOD;
            }
            a = a * a % MOD;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int waysToBuildRooms(vector<int>& prevRoom) {
        int n = prevRoom.size();
        g.assign(n, {});
        for (int i = 1; i < n; ++i) {
            g[prevRoom[i]].push_back(i);
        }
        fact.assign(n + 1, 1);
        invFact.assign(n + 1, 1);
        for (int i = 1; i <= n; ++i) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[n] = qpow(fact[n], MOD - 2);
        for (int i = n; i > 0; --i) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }
        ans = 1;
        dfs(0);
        return ans;
    }

private:
    static constexpr int MOD = 1e9 + 7;
    vector<vector<int>> g;
    vector<long long> fact, invFact;
    int ans;

    int dfs(int u) {
        int merged = 0;
        for (int v : g[u]) {
            int cn = dfs(v);
            if (merged) {
                ans = 1LL * ans * comb(merged + cn, cn) % MOD;
            }
            merged += cn;
        }
        return merged + 1;
    }

    long long comb(int n, int k) {
        return fact[n] * invFact[k] % MOD * invFact[n - k] % MOD;
    }

    long long qpow(long long a, long long n) {
        long long res = 1;
        for (; n; n >>= 1) {
            if (n & 1) {
                res = res * a % MOD;
            }
            a = a * a % MOD;
        }
        return res;
    }
};
```

#### Go

```go
func waysToBuildRooms(prevRoom []int) int {
	const mod = 1_000_000_007
	n := len(prevRoom)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[prevRoom[i]] = append(g[prevRoom[i]], i)
	}
	fact := make([]int, n+1)
	invFact := make([]int, n+1)
	fact[0] = 1
	for i := 1; i <= n; i++ {
		fact[i] = fact[i-1] * i % mod
	}
	qpow := func(a, n int) int {
		res := 1
		for ; n > 0; n >>= 1 {
			if n&1 == 1 {
				res = res * a % mod
			}
			a = a * a % mod
		}
		return res
	}
	invFact[n] = qpow(fact[n], mod-2)
	for i := n; i > 0; i-- {
		invFact[i-1] = invFact[i] * i % mod
	}
	comb := func(n, k int) int {
		return fact[n] * invFact[k] % mod * invFact[n-k] % mod
	}
	ans := 1
	var dfs func(int) int
	dfs = func(u int) int {
		merged := 0
		for _, v := range g[u] {
			cn := dfs(v)
			if merged != 0 {
				ans = ans * comb(merged+cn, cn) % mod
			}
			merged += cn
		}
		return merged + 1
	}
	dfs(0)
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
