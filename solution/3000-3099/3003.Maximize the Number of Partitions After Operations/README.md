---
comments: true
difficulty: 困难
rating: 3039
source: 第 379 场周赛 Q4
tags:
    - 位运算
    - 字符串
    - 动态规划
    - 位掩码
---

<!-- problem:start -->

# [3003. 执行操作后的最大分割数量](https://leetcode.cn/problems/maximize-the-number-of-partitions-after-operations)

[English Version](/solution/3000-3099/3003.Maximize%20the%20Number%20of%20Partitions%20After%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串&nbsp;<code>s</code>&nbsp;和一个整数&nbsp;<code>k</code>。</p>

<p>首先，你最多可以更改 <code>s</code> 中的 <strong>一处</strong> 下标对应字符为另一个小写英文字母。</p>

<p>之后，执行以下分割操作，直到&nbsp;<code>s</code>&nbsp;变为&nbsp;<strong>空串</strong>：</p>

<ul>
	<li>选择&nbsp;<code>s</code>&nbsp;的最长&nbsp;<strong>前缀</strong>，该前缀最多包含&nbsp;<code>k</code>&nbsp;个&nbsp;<strong>不同&nbsp;</strong>字符。</li>
	<li>从&nbsp;<code>s</code> 中&nbsp;<strong>删除&nbsp;</strong>这个前缀，并将分割数量加一。如果有剩余字符，它们在&nbsp;<code>s</code>&nbsp;中保持原来的顺序。</li>
</ul>

<p>返回一个整数，表示在 <strong>最多</strong> 改变一处下标对应字符的情况下，经过操作后得到的最大分割数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "accca", k = 2</span></p>

<p><strong>输出：</strong><span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>最好的方式是把&nbsp;<code>s[2]</code>&nbsp;变为除了 a 和 c 之外的东西，比如&nbsp;b。然后它变成了&nbsp;<code>"acbca"</code>。</p>

<p>然后我们执行以下操作：</p>

<ol>
	<li>最多包含 2 个不同字符的最长前缀是 <code>"ac"</code>，我们删除它然后&nbsp;<code>s</code> 变为&nbsp;<code>"bca"</code>。</li>
	<li>现在最多包含 2 个不同字符的最长前缀是&nbsp;<code>"bc"</code>，所以我们删除它然后&nbsp;<code>s</code> 变为&nbsp;<code>"a"</code>。</li>
	<li>最后，我们删除&nbsp;<code>"a"</code>&nbsp;并且&nbsp;<code>s</code>&nbsp;变成空串，所以该过程结束。</li>
</ol>

<p>进行操作时，字符串被分成 3 个部分，所以答案是 3。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "aabaab", k = 3</span></p>

<p><strong>输出：</strong><span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>一开始&nbsp;<code>s</code>&nbsp;包含 2 个不同的字符，所以无论我们改变哪个，&nbsp;它最多包含 3 个不同字符，因此最多包含 3 个不同字符的最长前缀始终是所有字符，因此答案是 1。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">s = "xxyz", k = 1</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>最好的方式是将&nbsp;<code>s[0]</code>&nbsp;或&nbsp;<code>s[1]</code>&nbsp;变为&nbsp;<code>s</code>&nbsp;中字符以外的东西，例如将&nbsp;<code>s[0]</code>&nbsp;变为&nbsp;<code>w</code>。</p>

<p>然后&nbsp;<code>s</code>&nbsp;变为&nbsp;<code>"wxyz"</code>，包含 4 个不同的字符，所以当&nbsp;<code>k</code>&nbsp;为 1，它将分为 4 个部分。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
	<li><code>1 &lt;= k &lt;= 26</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

<!-- thinking:start -->

> **思考**
>
> $n \le 10^4$ 且最多改一个字符，按贪心规则分段。若对每个位置尝试修改再模拟分段，代价约为 $O(n^2 |\Sigma|)$，偏紧。
>
> 分段只取决于当前段已出现的字符集合是否超过 $k$。该集合可用 $26$ 位掩码表示，修改次数仅 $0$ 或 $1$，状态量约为 $O(n \cdot 2^{\min(k,26)})$ 量级，可记忆化。
>
> 为此定义 $\textit{dfs}(i, \textit{cur}, t)$：处理到下标 $i$、当前段掩码为 $\textit{cur}$、仍可修改 $t$ 次时的最大段数。加入 $s[i]$ 后若位数超过 $k$ 则新开一段；若仍有修改额度，再枚举改成哪一个字母。

<!-- thinking:end -->

我们设计一个函数 $\textit{dfs}(i, \textit{cur}, t)$ 表示当前处理到字符串 $s$ 的下标 $i$，当前前缀中已经包含的字符集合为 $\textit{cur}$，并且还可以修改 $t$ 次字符时，能够得到的最大分割数量。那么答案即为 $\textit{dfs}(0, 0, 1)$。

函数 $\textit{dfs}(i, \textit{cur}, t)$ 的执行逻辑如下：

1. 如果 $i \geq n$，说明已经处理完字符串 $s$，返回 1。
2. 计算当前字符 $s[i]$ 对应的位掩码 $v = 1 \ll (s[i] - 'a')$，并计算更新后的字符集合 $\textit{nxt} = \textit{cur} \mid v$。
3. 如果 $\textit{nxt}$ 中的位数超过 $k$，说明当前前缀已经包含超过 $k$ 个不同字符，我们需要进行一次分割，此时分割数量加 1，并递归调用 $\textit{dfs}(i + 1, v, t)$；否则，继续递归调用 $\textit{dfs}(i + 1, \textit{nxt}, t)$。
4. 如果 $t > 0$，说明我们还可以修改一次字符。我们尝试将当前字符 $s[i]$ 修改为任意一个小写字母（共 26 种选择），对于每个选择，计算更新后的字符集合 $\textit{nxt} = \textit{cur} \mid (1 \ll j)$，并根据是否超过 $k$ 个不同字符，选择相应的递归调用方式，更新最大分割数量。
5. 使用哈希表缓存已经计算过的状态，避免重复计算。

时间复杂度 $O(n \times |\Sigma| \times k)$，空间复杂度 $O(n \times |\Sigma| \times k)$。其中 $n$ 为字符串 $s$ 的长度，而 $|\Sigma|$ 为字符集大小。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxPartitionsAfterOperations(self, s: str, k: int) -> int:
        @cache
        def dfs(i: int, cur: int, t: int) -> int:
            if i >= n:
                return 1
            v = 1 << (ord(s[i]) - ord("a"))
            nxt = cur | v
            if nxt.bit_count() > k:
                ans = dfs(i + 1, v, t) + 1
            else:
                ans = dfs(i + 1, nxt, t)
            if t:
                for j in range(26):
                    nxt = cur | (1 << j)
                    if nxt.bit_count() > k:
                        ans = max(ans, dfs(i + 1, 1 << j, 0) + 1)
                    else:
                        ans = max(ans, dfs(i + 1, nxt, 0))
            return ans

        n = len(s)
        return dfs(0, 0, 1)
```

#### Java

```java
class Solution {
    private Map<List<Integer>, Integer> f = new HashMap<>();
    private String s;
    private int k;

    public int maxPartitionsAfterOperations(String s, int k) {
        this.s = s;
        this.k = k;
        return dfs(0, 0, 1);
    }

    private int dfs(int i, int cur, int t) {
        if (i >= s.length()) {
            return 1;
        }
        var key = List.of(i, cur, t);
        if (f.containsKey(key)) {
            return f.get(key);
        }
        int v = 1 << (s.charAt(i) - 'a');
        int nxt = cur | v;
        int ans = Integer.bitCount(nxt) > k ? dfs(i + 1, v, t) + 1 : dfs(i + 1, nxt, t);
        if (t > 0) {
            for (int j = 0; j < 26; ++j) {
                nxt = cur | (1 << j);
                if (Integer.bitCount(nxt) > k) {
                    ans = Math.max(ans, dfs(i + 1, 1 << j, 0) + 1);
                } else {
                    ans = Math.max(ans, dfs(i + 1, nxt, 0));
                }
            }
        }
        f.put(key, ans);
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxPartitionsAfterOperations(string s, int k) {
        int n = s.size();
        unordered_map<long long, int> f;
        auto dfs = [&](this auto&& dfs, int i, int cur, int t) -> int {
            if (i >= n) {
                return 1;
            }
            long long key = (long long) i << 32 | cur << 1 | t;
            if (f.count(key)) {
                return f[key];
            }
            int v = 1 << (s[i] - 'a');
            int nxt = cur | v;
            int ans = __builtin_popcount(nxt) > k ? dfs(i + 1, v, t) + 1 : dfs(i + 1, nxt, t);
            if (t) {
                for (int j = 0; j < 26; ++j) {
                    nxt = cur | (1 << j);
                    if (__builtin_popcount(nxt) > k) {
                        ans = max(ans, dfs(i + 1, 1 << j, 0) + 1);
                    } else {
                        ans = max(ans, dfs(i + 1, nxt, 0));
                    }
                }
            }
            return f[key] = ans;
        };
        return dfs(0, 0, 1);
    }
};
```

#### Go

```go
func maxPartitionsAfterOperations(s string, k int) int {
	n := len(s)
	type tuple struct{ i, cur, t int }
	f := map[tuple]int{}
	var dfs func(i, cur, t int) int
	dfs = func(i, cur, t int) int {
		if i >= n {
			return 1
		}
		key := tuple{i, cur, t}
		if v, ok := f[key]; ok {
			return v
		}
		v := 1 << (s[i] - 'a')
		nxt := cur | v
		var ans int
		if bits.OnesCount(uint(nxt)) > k {
			ans = dfs(i+1, v, t) + 1
		} else {
			ans = dfs(i+1, nxt, t)
		}
		if t > 0 {
			for j := 0; j < 26; j++ {
				nxt = cur | (1 << j)
				if bits.OnesCount(uint(nxt)) > k {
					ans = max(ans, dfs(i+1, 1<<j, 0)+1)
				} else {
					ans = max(ans, dfs(i+1, nxt, 0))
				}
			}
		}
		f[key] = ans
		return ans
	}
	return dfs(0, 0, 1)
}
```

#### TypeScript

```ts
function maxPartitionsAfterOperations(s: string, k: number): number {
    const n = s.length;
    const f: Map<bigint, number> = new Map();
    const dfs = (i: number, cur: number, t: number): number => {
        if (i >= n) {
            return 1;
        }
        const key = (BigInt(i) << 27n) | (BigInt(cur) << 1n) | BigInt(t);
        if (f.has(key)) {
            return f.get(key)!;
        }
        const v = 1 << (s.charCodeAt(i) - 97);
        let nxt = cur | v;
        let ans = 0;
        if (bitCount(nxt) > k) {
            ans = dfs(i + 1, v, t) + 1;
        } else {
            ans = dfs(i + 1, nxt, t);
        }
        if (t) {
            for (let j = 0; j < 26; ++j) {
                nxt = cur | (1 << j);
                if (bitCount(nxt) > k) {
                    ans = Math.max(ans, dfs(i + 1, 1 << j, 0) + 1);
                } else {
                    ans = Math.max(ans, dfs(i + 1, nxt, 0));
                }
            }
        }
        f.set(key, ans);
        return ans;
    };
    return dfs(0, 0, 1);
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

<!-- thinking:start -->

> **思考**
>
> $n \le 10^4$，最多改一个字符。对每个位置修改后再按最长前缀分段，大约是 $O(n^2 |\Sigma|)$，已经偏紧。
>
> 不修改时的转移总是先调用下一字符。从下标 $0$ 走到 $n$ 的链长度就是 $n$。$n=1500$ 时 Python 抛出 RecursionError，Java 与 Node 在 $n=8000$ 时栈溢出。
>
> 一段在字符种数即将超过 $k$ 时被切断，修改机会只有一次，所以每个下标上真正出现的掩码很少。每个状态又只依赖下一字符的答案。
>
> 因此先从左到右记下可达的 $(\textit{cur}, t)$，再从右往左填这些状态的最大段数。字符串走完时答案是 $1$。加入当前字符后若位数超过 $k$，就新开一段并加 $1$；还有修改额度时，再枚举把当前字符换成哪一个字母。

<!-- thinking:end -->

记状态 $(\textit{cur}, t)$ 表示当前段已经包含的字符掩码是 $\textit{cur}$，还可以修改 $t$ 次。答案是下标 $0$ 处 $(\textit{cur}, t) = (0, 1)$ 的最大段数。

先从左到右求出每个下标上可达的状态，起点只有 $(0, 1)$。设当前字符的位掩码为 $v = 1 \ll (s[i] - 'a')$。

- 令 $\textit{nxt} = \textit{cur} \mid v$。若 $\textit{nxt}$ 的位数超过 $k$，当前段在这里结束，下一状态的掩码换成 $v$，修改次数仍是 $t$；否则下一状态的掩码是 $\textit{nxt}$。
- 若 $t = 1$，再尝试把 $s[i]$ 改成任意小写字母。对字母 $j$，令 $\textit{nxt} = \textit{cur} \mid (1 \ll j)$。位数超过 $k$ 时，下一状态是掩码 $1 \ll j$、修改次数 $0$；否则下一状态是掩码 $\textit{nxt}$、修改次数 $0$。

再按 $i$ 从 $n - 1$ 降到 $0$，用同一组转移计算最大段数。$i = n$ 时字符串已经处理完，值为 $1$。切开当前段的那一支要在后继答案上再加 $1$。

时间复杂度 $O(n \times |\Sigma| \times k)$，空间复杂度 $O(n \times |\Sigma| \times k)$。其中 $n$ 为字符串 $s$ 的长度，而 $|\Sigma|$ 为字符集大小。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxPartitionsAfterOperations(self, s: str, k: int) -> int:
        n = len(s)
        masks = [1 << (ord(c) - ord("a")) for c in s]
        reach = [set() for _ in range(n + 1)]
        reach[0].add((0, 1))
        for i, v in enumerate(masks):
            for cur, t in reach[i]:
                nxt = cur | v
                if nxt.bit_count() > k:
                    reach[i + 1].add((v, t))
                else:
                    reach[i + 1].add((nxt, t))
                if t:
                    for j in range(26):
                        bit = 1 << j
                        nxt = cur | bit
                        if nxt.bit_count() > k:
                            reach[i + 1].add((bit, 0))
                        else:
                            reach[i + 1].add((nxt, 0))

        def get(i: int, cur: int, t: int) -> int:
            if i == n:
                return 1
            return f[i][(cur, t)]

        f = [dict() for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            v = masks[i]
            for cur, t in reach[i]:
                nxt = cur | v
                if nxt.bit_count() > k:
                    ans = get(i + 1, v, t) + 1
                else:
                    ans = get(i + 1, nxt, t)
                if t:
                    for j in range(26):
                        bit = 1 << j
                        nxt = cur | bit
                        if nxt.bit_count() > k:
                            ans = max(ans, get(i + 1, bit, 0) + 1)
                        else:
                            ans = max(ans, get(i + 1, nxt, 0))
                f[i][(cur, t)] = ans
        return f[0][(0, 1)]
```

#### Java

```java
class Solution {
    public int maxPartitionsAfterOperations(String s, int k) {
        int n = s.length();
        int[] masks = new int[n];
        for (int i = 0; i < n; ++i) {
            masks[i] = 1 << (s.charAt(i) - 'a');
        }
        List<Set<Integer>> reach = new ArrayList<>();
        List<Map<Integer, Integer>> f = new ArrayList<>();
        for (int i = 0; i <= n; ++i) {
            reach.add(new HashSet<>());
            f.add(new HashMap<>());
        }
        reach.get(0).add(1);
        for (int i = 0; i < n; ++i) {
            int v = masks[i];
            for (int key : reach.get(i)) {
                int cur = key >> 1, t = key & 1;
                add(reach.get(i + 1), cur, t, v, k);
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            int v = masks[i];
            for (int key : reach.get(i)) {
                int cur = key >> 1, t = key & 1;
                int nxt = cur | v;
                int ans = Integer.bitCount(nxt) > k ? value(f, n, i + 1, (v << 1) | t) + 1
                                                    : value(f, n, i + 1, (nxt << 1) | t);
                if (t == 1) {
                    for (int j = 0; j < 26; ++j) {
                        int bit = 1 << j;
                        nxt = cur | bit;
                        if (Integer.bitCount(nxt) > k) {
                            ans = Math.max(ans, value(f, n, i + 1, bit << 1) + 1);
                        } else {
                            ans = Math.max(ans, value(f, n, i + 1, nxt << 1));
                        }
                    }
                }
                f.get(i).put(key, ans);
            }
        }
        return f.get(0).get(1);
    }

    private void add(Set<Integer> reach, int cur, int t, int v, int k) {
        int nxt = cur | v;
        if (Integer.bitCount(nxt) > k) {
            reach.add((v << 1) | t);
        } else {
            reach.add((nxt << 1) | t);
        }
        if (t == 1) {
            for (int j = 0; j < 26; ++j) {
                int bit = 1 << j;
                nxt = cur | bit;
                if (Integer.bitCount(nxt) > k) {
                    reach.add(bit << 1);
                } else {
                    reach.add(nxt << 1);
                }
            }
        }
    }

    private int value(List<Map<Integer, Integer>> f, int n, int i, int key) {
        if (i == n) {
            return 1;
        }
        return f.get(i).get(key);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxPartitionsAfterOperations(string s, int k) {
        int n = s.size();
        vector<int> masks(n);
        for (int i = 0; i < n; ++i) {
            masks[i] = 1 << (s[i] - 'a');
        }
        vector<unordered_set<int>> reach(n + 1);
        vector<unordered_map<int, int>> f(n + 1);
        reach[0].insert(1);
        for (int i = 0; i < n; ++i) {
            int v = masks[i];
            for (int key : reach[i]) {
                int cur = key >> 1, t = key & 1;
                int nxt = cur | v;
                if (__builtin_popcount(nxt) > k) {
                    reach[i + 1].insert((v << 1) | t);
                } else {
                    reach[i + 1].insert((nxt << 1) | t);
                }
                if (t) {
                    for (int j = 0; j < 26; ++j) {
                        int bit = 1 << j;
                        nxt = cur | bit;
                        if (__builtin_popcount(nxt) > k) {
                            reach[i + 1].insert(bit << 1);
                        } else {
                            reach[i + 1].insert(nxt << 1);
                        }
                    }
                }
            }
        }
        auto get = [&](int i, int key) -> int {
            if (i == n) {
                return 1;
            }
            return f[i].at(key);
        };
        for (int i = n - 1; i >= 0; --i) {
            int v = masks[i];
            for (int key : reach[i]) {
                int cur = key >> 1, t = key & 1;
                int nxt = cur | v;
                int ans = __builtin_popcount(nxt) > k ? get(i + 1, (v << 1) | t) + 1
                                                      : get(i + 1, (nxt << 1) | t);
                if (t) {
                    for (int j = 0; j < 26; ++j) {
                        int bit = 1 << j;
                        nxt = cur | bit;
                        if (__builtin_popcount(nxt) > k) {
                            ans = max(ans, get(i + 1, bit << 1) + 1);
                        } else {
                            ans = max(ans, get(i + 1, nxt << 1));
                        }
                    }
                }
                f[i][key] = ans;
            }
        }
        return f[0].at(1);
    }
};
```

#### Go

```go
func maxPartitionsAfterOperations(s string, k int) int {
	n := len(s)
	masks := make([]int, n)
	for i := 0; i < n; i++ {
		masks[i] = 1 << (s[i] - 'a')
	}
	reach := make([]map[int]struct{}, n+1)
	f := make([]map[int]int, n+1)
	for i := 0; i <= n; i++ {
		reach[i] = map[int]struct{}{}
		f[i] = map[int]int{}
	}
	reach[0][1] = struct{}{}
	for i, v := range masks {
		for key := range reach[i] {
			cur, t := key>>1, key&1
			nxt := cur | v
			if bits.OnesCount(uint(nxt)) > k {
				reach[i+1][(v<<1)|t] = struct{}{}
			} else {
				reach[i+1][(nxt<<1)|t] = struct{}{}
			}
			if t == 1 {
				for j := 0; j < 26; j++ {
					bit := 1 << j
					nxt = cur | bit
					if bits.OnesCount(uint(nxt)) > k {
						reach[i+1][bit<<1] = struct{}{}
					} else {
						reach[i+1][nxt<<1] = struct{}{}
					}
				}
			}
		}
	}
	get := func(i, key int) int {
		if i == n {
			return 1
		}
		return f[i][key]
	}
	for i := n - 1; i >= 0; i-- {
		v := masks[i]
		for key := range reach[i] {
			cur, t := key>>1, key&1
			nxt := cur | v
			var ans int
			if bits.OnesCount(uint(nxt)) > k {
				ans = get(i+1, (v<<1)|t) + 1
			} else {
				ans = get(i+1, (nxt<<1)|t)
			}
			if t == 1 {
				for j := 0; j < 26; j++ {
					bit := 1 << j
					nxt = cur | bit
					if bits.OnesCount(uint(nxt)) > k {
						ans = max(ans, get(i+1, bit<<1)+1)
					} else {
						ans = max(ans, get(i+1, nxt<<1))
					}
				}
			}
			f[i][key] = ans
		}
	}
	return f[0][1]
}
```

#### TypeScript

```ts
function maxPartitionsAfterOperations(s: string, k: number): number {
    const n = s.length;
    const masks = new Array(n);
    for (let i = 0; i < n; ++i) {
        masks[i] = 1 << (s.charCodeAt(i) - 97);
    }
    const reach: Set<number>[] = Array.from({ length: n + 1 }, () => new Set());
    const f: Map<number, number>[] = Array.from({ length: n + 1 }, () => new Map());
    reach[0].add(1);
    for (let i = 0; i < n; ++i) {
        const v = masks[i];
        for (const key of reach[i]) {
            const cur = key >> 1;
            const t = key & 1;
            let nxt = cur | v;
            if (bitCount(nxt) > k) {
                reach[i + 1].add((v << 1) | t);
            } else {
                reach[i + 1].add((nxt << 1) | t);
            }
            if (t) {
                for (let j = 0; j < 26; ++j) {
                    const bit = 1 << j;
                    nxt = cur | bit;
                    if (bitCount(nxt) > k) {
                        reach[i + 1].add(bit << 1);
                    } else {
                        reach[i + 1].add(nxt << 1);
                    }
                }
            }
        }
    }
    const get = (i: number, key: number): number => {
        if (i === n) {
            return 1;
        }
        return f[i].get(key)!;
    };
    for (let i = n - 1; i >= 0; --i) {
        const v = masks[i];
        for (const key of reach[i]) {
            const cur = key >> 1;
            const t = key & 1;
            let nxt = cur | v;
            let ans = 0;
            if (bitCount(nxt) > k) {
                ans = get(i + 1, (v << 1) | t) + 1;
            } else {
                ans = get(i + 1, (nxt << 1) | t);
            }
            if (t) {
                for (let j = 0; j < 26; ++j) {
                    const bit = 1 << j;
                    nxt = cur | bit;
                    if (bitCount(nxt) > k) {
                        ans = Math.max(ans, get(i + 1, bit << 1) + 1);
                    } else {
                        ans = Math.max(ans, get(i + 1, nxt << 1));
                    }
                }
            }
            f[i].set(key, ans);
        }
    }
    return f[0].get(1)!;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
