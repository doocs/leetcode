---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0936.Stamping%20The%20Sequence/README.md
tags:
    - 栈
    - 贪心
    - 队列
    - 字符串
---

<!-- problem:start -->

# [936. 戳印序列](https://leetcode.cn/problems/stamping-the-sequence)

[English Version](/solution/0900-0999/0936.Stamping%20The%20Sequence/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你想要用<strong>小写字母</strong>组成一个目标字符串&nbsp;<code>target</code>。&nbsp;</p>

<p>开始的时候，序列由&nbsp;<code>target.length</code>&nbsp;个&nbsp;<code>&#39;?&#39;</code>&nbsp;记号组成。而你有一个小写字母印章&nbsp;<code>stamp</code>。</p>

<p>在每个回合，你可以将印章放在序列上，并将序列中的每个字母替换为印章上的相应字母。你最多可以进行&nbsp;<code>10 * target.length</code>&nbsp; 个回合。</p>

<p>举个例子，如果初始序列为 &quot;?????&quot;，而你的印章 <code>stamp</code>&nbsp;是&nbsp;<code>&quot;abc&quot;</code>，那么在第一回合，你可以得到&nbsp;&quot;abc??&quot;、&quot;?abc?&quot;、&quot;??abc&quot;。（请注意，印章必须完全包含在序列的边界内才能盖下去。）</p>

<p>如果可以印出序列，那么返回一个数组，该数组由每个回合中被印下的最左边字母的索引组成。如果不能印出序列，就返回一个空数组。</p>

<p>例如，如果序列是 &quot;ababc&quot;，印章是 <code>&quot;abc&quot;</code>，那么我们就可以返回与操作&nbsp;&quot;?????&quot; -&gt; &quot;abc??&quot; -&gt; &quot;ababc&quot; 相对应的答案 <code>[0, 2]</code>；</p>

<p>另外，如果可以印出序列，那么需要保证可以在 <code>10 * target.length</code>&nbsp;个回合内完成。任何超过此数字的答案将不被接受。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>stamp = &quot;abc&quot;, target = &quot;ababc&quot;
<strong>输出：</strong>[0,2]
（[1,0,2] 以及其他一些可能的结果也将作为答案被接受）
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>stamp = &quot;abca&quot;, target = &quot;aabcaca&quot;
<strong>输出：</strong>[3,0,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= stamp.length &lt;= target.length &lt;= 1000</code></li>
	<li><code>stamp</code> 和&nbsp;<code>target</code>&nbsp;只包含小写字母。</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：逆向思维 + 拓扑排序

如果我们正向地对序列进行操作，那么处理起来会比较麻烦，因为后续的操作会把前面的操作覆盖掉。我们不妨考虑逆向地对序列进行操作，即从目标字符串 $target$ 开始，考虑将 $target$ 变成 $?????$ 的过程。

我们不妨记字母印章的长度为 $m$，目标字符串的长度为 $n$。如果我们拿着字母印章在目标字符串上操作，那么一共有 $n-m+1$ 个开始位置可以放置字母印章。我们可以枚举这 $n-m+1$ 个开始位置，利用类似拓扑排序的方法，逆向地进行操作。

首先，我们明确，每个开始位置都对应着一个长度为 $m$ 的窗口。

接下来，我们定义以下数据结构或变量，其中：

- 入度数组 $indeg$，其中 $indeg[i]$ 表示第 $i$ 个窗口中有多少位置的字符与字母印章中的字符不同，初始时，$indeg[i]=m$。若 $indeg[i]=0$，说明第 $i$ 个窗口中的字符都与字母印章中的字符相同，那么我们就可以在第 $i$ 个窗口中放置字母印章。
- 邻接表 $g$，其中 $g[i]$ 表示目标字符串 $target$ 的第 $i$ 个位置上，所有与字母印章存在不同字符的窗口的集合。
- 队列 $q$，用于存储所有入度为 $0$ 的窗口的编号。
- 数组 $vis$，用于标记目标字符串 $target$ 的每个位置是否已经被覆盖。
- 数组 $ans$，用于存储答案。

接下来，我们进行拓扑排序。在拓扑排序的每一步中，我们取出队首的窗口编号 $i$，并将 $i$ 放入答案数组 $ans$ 中。然后，我们枚举字母印章中的每个位置 $j$，如果第 $i$ 个窗口中的第 $j$ 个位置未被覆盖，那么我们就将其覆盖，并将 $indeg$ 数组中所有与第 $i$ 个窗口中的第 $j$ 个位置相同的窗口的入度减少 $1$。如果某个窗口的入度变为 $0$，那么我们就将其放入队列 $q$ 中等待下一次处理。

在拓扑排序结束后，如果目标字符串 $target$ 的每个位置都被覆盖，那么答案数组 $ans$ 中存储的就是我们要求的答案。否则，目标字符串 $target$ 无法被覆盖，我们就返回一个空数组。

时间复杂度 $O(n \times (n - m + 1))$，空间复杂度 $O(n \times (n - m + 1))$。其中 $n$ 和 $m$ 分别是目标字符串 $target$ 和字母印章的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def movesToStamp(self, stamp: str, target: str) -> List[int]:
        m, n = len(stamp), len(target)
        indeg = [m] * (n - m + 1)
        q = deque()
        g = [[] for _ in range(n)]
        for i in range(n - m + 1):
            for j, c in enumerate(stamp):
                if target[i + j] == c:
                    indeg[i] -= 1
                    if indeg[i] == 0:
                        q.append(i)
                else:
                    g[i + j].append(i)
        ans = []
        vis = [False] * n
        while q:
            i = q.popleft()
            ans.append(i)
            for j in range(m):
                if not vis[i + j]:
                    vis[i + j] = True
                    for k in g[i + j]:
                        indeg[k] -= 1
                        if indeg[k] == 0:
                            q.append(k)
        return ans[::-1] if all(vis) else []
```

#### Java

```java
class Solution {
    public int[] movesToStamp(String stamp, String target) {
        int m = stamp.length(), n = target.length();
        int[] indeg = new int[n - m + 1];
        Arrays.fill(indeg, m);
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n - m + 1; ++i) {
            for (int j = 0; j < m; ++j) {
                if (target.charAt(i + j) == stamp.charAt(j)) {
                    if (--indeg[i] == 0) {
                        q.offer(i);
                    }
                } else {
                    g[i + j].add(i);
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[n];
        while (!q.isEmpty()) {
            int i = q.poll();
            ans.add(i);
            for (int j = 0; j < m; ++j) {
                if (!vis[i + j]) {
                    vis[i + j] = true;
                    for (int k : g[i + j]) {
                        if (--indeg[k] == 0) {
                            q.offer(k);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                return new int[0];
            }
        }
        Collections.reverse(ans);
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> movesToStamp(string stamp, string target) {
        int m = stamp.size(), n = target.size();
        vector<int> indeg(n - m + 1, m);
        vector<int> g[n];
        queue<int> q;
        for (int i = 0; i < n - m + 1; ++i) {
            for (int j = 0; j < m; ++j) {
                if (target[i + j] == stamp[j]) {
                    if (--indeg[i] == 0) {
                        q.push(i);
                    }
                } else {
                    g[i + j].push_back(i);
                }
            }
        }
        vector<int> ans;
        vector<bool> vis(n);
        while (q.size()) {
            int i = q.front();
            q.pop();
            ans.push_back(i);
            for (int j = 0; j < m; ++j) {
                if (!vis[i + j]) {
                    vis[i + j] = true;
                    for (int k : g[i + j]) {
                        if (--indeg[k] == 0) {
                            q.push(k);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            if (!vis[i]) {
                return {};
            }
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

#### Go

```go
func movesToStamp(stamp string, target string) (ans []int) {
	m, n := len(stamp), len(target)
	indeg := make([]int, n-m+1)
	for i := range indeg {
		indeg[i] = m
	}
	g := make([][]int, n)
	q := []int{}
	for i := 0; i < n-m+1; i++ {
		for j := range stamp {
			if target[i+j] == stamp[j] {
				indeg[i]--
				if indeg[i] == 0 {
					q = append(q, i)
				}
			} else {
				g[i+j] = append(g[i+j], i)
			}
		}
	}
	vis := make([]bool, n)
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		ans = append(ans, i)
		for j := range stamp {
			if !vis[i+j] {
				vis[i+j] = true
				for _, k := range g[i+j] {
					indeg[k]--
					if indeg[k] == 0 {
						q = append(q, k)
					}
				}
			}
		}
	}
	for _, v := range vis {
		if !v {
			return []int{}
		}
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return
}
```

#### TypeScript

```ts
function movesToStamp(stamp: string, target: string): number[] {
    const m: number = stamp.length;
    const n: number = target.length;
    const indeg: number[] = Array(n - m + 1).fill(m);
    const g: number[][] = Array.from({ length: n }, () => []);
    const q: number[] = [];
    for (let i = 0; i < n - m + 1; ++i) {
        for (let j = 0; j < m; ++j) {
            if (target[i + j] === stamp[j]) {
                if (--indeg[i] === 0) {
                    q.push(i);
                }
            } else {
                g[i + j].push(i);
            }
        }
    }

    const ans: number[] = [];
    const vis: boolean[] = Array(n).fill(false);
    while (q.length) {
        const i: number = q.shift()!;
        ans.push(i);
        for (let j = 0; j < m; ++j) {
            if (!vis[i + j]) {
                vis[i + j] = true;
                for (const k of g[i + j]) {
                    if (--indeg[k] === 0) {
                        q.push(k);
                    }
                }
            }
        }
    }
    if (!vis.every(v => v)) {
        return [];
    }
    ans.reverse();
    return ans;
}
```

#### Rust

```rust
use std::collections::VecDeque;

impl Solution {
    pub fn moves_to_stamp(stamp: String, target: String) -> Vec<i32> {
        let m = stamp.len();
        let n = target.len();

        let mut indeg: Vec<usize> = vec![m; n - m + 1];
        let mut g: Vec<Vec<usize>> = vec![Vec::new(); n];
        let mut q: VecDeque<usize> = VecDeque::new();

        for i in 0..n - m + 1 {
            for j in 0..m {
                if target.chars().nth(i + j).unwrap() == stamp.chars().nth(j).unwrap() {
                    indeg[i] -= 1;
                    if indeg[i] == 0 {
                        q.push_back(i);
                    }
                } else {
                    g[i + j].push(i);
                }
            }
        }

        let mut ans: Vec<i32> = Vec::new();
        let mut vis: Vec<bool> = vec![false; n];

        while let Some(i) = q.pop_front() {
            ans.push(i as i32);

            for j in 0..m {
                if !vis[i + j] {
                    vis[i + j] = true;

                    for &k in g[i + j].iter() {
                        indeg[k] -= 1;
                        if indeg[k] == 0 {
                            q.push_back(k);
                        }
                    }
                }
            }
        }

        if vis.iter().all(|&v| v) {
            ans.reverse();
            ans
        } else {
            Vec::new()
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
