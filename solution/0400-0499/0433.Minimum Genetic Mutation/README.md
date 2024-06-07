---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0433.Minimum%20Genetic%20Mutation/README.md
tags:
    - 广度优先搜索
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [433. 最小基因变化](https://leetcode.cn/problems/minimum-genetic-mutation)

[English Version](/solution/0400-0499/0433.Minimum%20Genetic%20Mutation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 <code>'A'</code>、<code>'C'</code>、<code>'G'</code> 和 <code>'T'</code> 之一。</p>

<p>假设我们需要调查从基因序列&nbsp;<code>start</code> 变为 <code>end</code> 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。</p>

<ul>
	<li>例如，<code>"AACCGGTT" --&gt; "AACCGGTA"</code> 就是一次基因变化。</li>
</ul>

<p>另有一个基因库 <code>bank</code> 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。（变化后的基因必须位于基因库 <code>bank</code> 中）</p>

<p>给你两个基因序列 <code>start</code> 和 <code>end</code> ，以及一个基因库 <code>bank</code> ，请你找出并返回能够使&nbsp;<code>start</code> 变化为 <code>end</code> 所需的最少变化次数。如果无法完成此基因变化，返回 <code>-1</code> 。</p>

<p>注意：起始基因序列&nbsp;<code>start</code> 默认是有效的，但是它并不一定会出现在基因库中。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>start.length == 8</code></li>
	<li><code>end.length == 8</code></li>
	<li><code>0 &lt;= bank.length &lt;= 10</code></li>
	<li><code>bank[i].length == 8</code></li>
	<li><code>start</code>、<code>end</code> 和 <code>bank[i]</code> 仅由字符 <code>['A', 'C', 'G', 'T']</code> 组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

我们定义一个队列 `q`，用于存储当前基因序列以及变化的次数，定义一个集合 `vis`，用于存储已经访问过的基因序列。初始时，将起始基因序列 `start` 加入队列 `q`，并将其加入集合 `vis`。

然后，我们不断从队列 `q` 中取出一个基因序列，如果该基因序列等于目标基因序列，则返回当前的变化次数。否则，我们遍历基因库 `bank`，计算当前基因序列与基因库中的基因序列的差异值，如果差异值为 $1$，且基因库中的基因序列没有被访问过，则将其加入队列 `q`，并将其加入集合 `vis`。

如果队列 `q` 为空，说明无法完成基因变化，返回 $-1$。

时间复杂度 $O(C \times n \times m)$，空间复杂度 $O(n \times m)$。其中 $n$ 和 $m$ 分别表示基因序列的长度和基因库的长度，而 $C$ 表示基因序列的字符集大小，本题中 $C = 4$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMutation(self, startGene: str, endGene: str, bank: List[str]) -> int:
        q = deque([(startGene, 0)])
        vis = {startGene}
        while q:
            gene, depth = q.popleft()
            if gene == endGene:
                return depth
            for nxt in bank:
                diff = sum(a != b for a, b in zip(gene, nxt))
                if diff == 1 and nxt not in vis:
                    q.append((nxt, depth + 1))
                    vis.add(nxt)
        return -1
```

#### Java

```java
class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Deque<String> q = new ArrayDeque<>();
        q.offer(startGene);
        Set<String> vis = new HashSet<>();
        vis.add(startGene);
        int depth = 0;
        while (!q.isEmpty()) {
            for (int m = q.size(); m > 0; --m) {
                String gene = q.poll();
                if (gene.equals(endGene)) {
                    return depth;
                }
                for (String next : bank) {
                    int c = 2;
                    for (int k = 0; k < 8 && c > 0; ++k) {
                        if (gene.charAt(k) != next.charAt(k)) {
                            --c;
                        }
                    }
                    if (c > 0 && !vis.contains(next)) {
                        q.offer(next);
                        vis.add(next);
                    }
                }
            }
            ++depth;
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMutation(string startGene, string endGene, vector<string>& bank) {
        queue<pair<string, int>> q{{{startGene, 0}}};
        unordered_set<string> vis = {startGene};
        while (!q.empty()) {
            auto [gene, depth] = q.front();
            q.pop();
            if (gene == endGene) {
                return depth;
            }
            for (const string& next : bank) {
                int c = 2;
                for (int k = 0; k < 8 && c; ++k) {
                    c -= gene[k] != next[k];
                }
                if (c && !vis.contains(next)) {
                    vis.insert(next);
                    q.push({next, depth + 1});
                }
            }
        }
        return -1;
    }
};
```

#### Go

```go
func minMutation(startGene string, endGene string, bank []string) int {
	type pair struct {
		s     string
		depth int
	}
	q := []pair{pair{startGene, 0}}
	vis := map[string]bool{startGene: true}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		if p.s == endGene {
			return p.depth
		}
		for _, next := range bank {
			diff := 0
			for i := 0; i < len(startGene); i++ {
				if p.s[i] != next[i] {
					diff++
				}
			}
			if diff == 1 && !vis[next] {
				vis[next] = true
				q = append(q, pair{next, p.depth + 1})
			}
		}
	}
	return -1
}
```

#### TypeScript

```ts
function minMutation(startGene: string, endGene: string, bank: string[]): number {
    const q: [string, number][] = [[startGene, 0]];
    const vis = new Set<string>([startGene]);
    for (const [gene, depth] of q) {
        if (gene === endGene) {
            return depth;
        }
        for (const next of bank) {
            let c = 2;
            for (let k = 0; k < 8 && c > 0; ++k) {
                if (gene[k] !== next[k]) {
                    --c;
                }
            }
            if (c && !vis.has(next)) {
                q.push([next, depth + 1]);
                vis.add(next);
            }
        }
    }
    return -1;
}
```

#### Rust

```rust
use std::collections::{ HashSet, VecDeque };

impl Solution {
    pub fn min_mutation(start_gene: String, end_gene: String, bank: Vec<String>) -> i32 {
        let mut q = VecDeque::new();
        q.push_back((start_gene.clone(), 0));
        let mut vis = HashSet::new();
        vis.insert(start_gene);

        while let Some((gene, depth)) = q.pop_front() {
            if gene == end_gene {
                return depth;
            }
            for next in &bank {
                let mut c = 2;
                for k in 0..8 {
                    if gene.as_bytes()[k] != next.as_bytes()[k] {
                        c -= 1;
                    }
                    if c == 0 {
                        break;
                    }
                }
                if c > 0 && !vis.contains(next) {
                    vis.insert(next.clone());
                    q.push_back((next.clone(), depth + 1));
                }
            }
        }
        -1
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
