---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3253.Construct%20String%20with%20Minimum%20Cost%20%28Easy%29/README.md
---

<!-- problem:start -->

# [3253. 最小代价构造字符串（简单） 🔒](https://leetcode.cn/problems/construct-string-with-minimum-cost-easy)

[English Version](/solution/3200-3299/3253.Construct%20String%20with%20Minimum%20Cost%20%28Easy%29/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>target</code>、一个字符串数组 <code>words</code> 以及一个整数数组 <code>costs</code>，这两个数组长度相同。</p>

<p>设想一个空字符串 <code>s</code>。</p>

<p>你可以执行以下操作任意次数（包括&nbsp;<strong>零&nbsp;</strong>次）：</p>

<ul>
	<li>选择一个在范围&nbsp; <code>[0, words.length - 1]</code> 的索引 <code>i</code>。</li>
	<li>将 <code>words[i]</code> 追加到 <code>s</code>。</li>
	<li>该操作的成本是 <code>costs[i]</code>。</li>
</ul>

<p>返回使 <code>s</code> 等于 <code>target</code> 的 <strong>最小</strong> 成本。如果不可能，返回 <code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong>输入：</strong> target = "abcdef", words = ["abdef","abc","d","def","ef"], costs = [100,1,1,10,5]</p>

<p><strong>输出：</strong> 7</p>

<p><strong>解释：</strong></p>

<ul>
	<li>选择索引 1 并以成本 1 将 <code>"abc"</code> 追加到 <code>s</code>，得到 <code>s = "abc"</code>。</li>
	<li>选择索引 2 并以成本 1 将 <code>"d"</code> 追加到 <code>s</code>，得到 <code>s = "abcd"</code>。</li>
	<li>选择索引 4 并以成本 5 将 <code>"ef"</code> 追加到 <code>s</code>，得到 <code>s = "abcdef"</code>。</li>
</ul>

<p><strong>示例 2：</strong></p>

<p><strong>输入：</strong> target = "aaaa", words = ["z","zz","zzz"], costs = [1,10,100]</p>

<p><strong>输出：</strong> -1</p>

<p><strong>解释：</strong></p>

<p>无法使 <code>s</code> 等于 <code>target</code>，因此返回 -1。</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 2000</code></li>
	<li><code>1 &lt;= words.length == costs.length &lt;= 50</code></li>
	<li><code>1 &lt;= words[i].length &lt;= target.length</code></li>
	<li><code>target</code> 和 <code>words[i]</code> 仅由小写英文字母组成。</li>
	<li><code>1 &lt;= costs[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：字典树 + 记忆化搜索

<!-- thinking:start -->

> **思考**
>
> $\textit{target}$ 长 $2000$、单词至多 $50$ 个，朴素对每个位置枚举每个词做匹配可以过，但重复前缀会被反复比较。把单词放进字典树后，从 $i$ 一次走下去即可得到所有匹配终点。
>
> $\textit{dfs}(i)$ 为拼出后缀 $\textit{target}[i:]$ 的最小代价，沿字典树走到带代价的节点就加上 $\textit{dfs}(j+1)$。记忆化后状态 $n$ 个，每状态沿树走 $O(n)$。

<!-- thinking:end -->

我们首先创建一个字典树 $\textit{trie}$，字典树的每个节点包含一个长度为 $26$ 的数组 $\textit{children}$，数组中的每个元素都是一个指向下一个节点的指针。字典树的每个节点还包含一个 $\textit{cost}$ 变量，表示从根节点到当前节点的最小花费。

我们遍历 $\textit{words}$ 数组，将每个单词插入到字典树中，同时更新每个节点的 $\textit{cost}$ 变量。

接下来，我们定义一个记忆化搜索函数 $\textit{dfs}(i)$，表示从 $\textit{target}[i]$ 开始构造字符串的最小花费。那么答案就是 $\textit{dfs}(0)$。

函数 $\textit{dfs}(i)$ 的计算过程如下：

- 如果 $i \geq \textit{len}(\textit{target})$，表示已经构造完整个字符串，返回 $0$。
- 否则，我们从 $\textit{trie}$ 的根节点开始，遍历 $\textit{target}[i]$ 开始的所有后缀，找到最小花费，即 $\textit{trie}$ 中的 $\textit{cost}$ 变量，加上 $\textit{dfs}(j+1)$ 的结果，其中 $j$ 是 $\textit{target}[i]$ 开始的后缀的结束位置。

最后，如果 $\textit{dfs}(0) < \textit{inf}$，返回 $\textit{dfs}(0)$，否则返回 $-1$。

时间复杂度 $O(n^2 + L)$，空间复杂度 $O(n + L)$。其中 $n$ 是 $\textit{target}$ 的长度，而 $L$ 是 $\textit{words}$ 数组中所有单词的长度之和。

<!-- tabs:start -->

#### Python3

```python
class Trie:
    def __init__(self):
        self.children: List[Optional[Trie]] = [None] * 26
        self.cost = inf

    def insert(self, word: str, cost: int):
        node = self
        for c in word:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.cost = min(node.cost, cost)


class Solution:
    def minimumCost(self, target: str, words: List[str], costs: List[int]) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= len(target):
                return 0
            ans = inf
            node = trie
            for j in range(i, len(target)):
                idx = ord(target[j]) - ord("a")
                if node.children[idx] is None:
                    return ans
                node = node.children[idx]
                ans = min(ans, node.cost + dfs(j + 1))
            return ans

        trie = Trie()
        for word, cost in zip(words, costs):
            trie.insert(word, cost)
        ans = dfs(0)
        return ans if ans < inf else -1
```

#### Java

```java
class Trie {
    public final int inf = 1 << 29;
    public Trie[] children = new Trie[26];
    public int cost = inf;

    public void insert(String word, int cost) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.cost = Math.min(node.cost, cost);
    }
}

class Solution {
    private Trie trie = new Trie();
    private char[] target;
    private Integer[] f;

    public int minimumCost(String target, String[] words, int[] costs) {
        for (int i = 0; i < words.length; ++i) {
            trie.insert(words[i], costs[i]);
        }
        this.target = target.toCharArray();
        f = new Integer[target.length()];
        int ans = dfs(0);
        return ans < trie.inf ? ans : -1;
    }

    private int dfs(int i) {
        if (i >= target.length) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        f[i] = trie.inf;
        Trie node = trie;
        for (int j = i; j < target.length; ++j) {
            int idx = target[j] - 'a';
            if (node.children[idx] == null) {
                return f[i];
            }
            node = node.children[idx];
            f[i] = Math.min(f[i], node.cost + dfs(j + 1));
        }
        return f[i];
    }
}
```

#### C++

```cpp
const int inf = 1 << 29;

class Trie {
public:
    Trie* children[26]{};
    int cost = inf;

    void insert(string& word, int cost) {
        Trie* node = this;
        for (char c : word) {
            int idx = c - 'a';
            if (!node->children[idx]) {
                node->children[idx] = new Trie();
            }
            node = node->children[idx];
        }
        node->cost = min(node->cost, cost);
    }
};

class Solution {
public:
    int minimumCost(string target, vector<string>& words, vector<int>& costs) {
        Trie* trie = new Trie();
        for (int i = 0; i < words.size(); ++i) {
            trie->insert(words[i], costs[i]);
        }
        int n = target.length();
        int f[n];
        memset(f, 0, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i) -> int {
            if (i >= n) {
                return 0;
            }
            if (f[i]) {
                return f[i];
            }
            f[i] = inf;
            Trie* node = trie;
            for (int j = i; j < n; ++j) {
                int idx = target[j] - 'a';
                if (!node->children[idx]) {
                    return f[i];
                }
                node = node->children[idx];
                f[i] = min(f[i], node->cost + dfs(j + 1));
            }
            return f[i];
        };
        int ans = dfs(0);
        return ans < inf ? ans : -1;
    }
};
```

#### Go

```go
const inf = 1 << 29

type Trie struct {
	children [26]*Trie
	cost     int
}

func NewTrie() *Trie {
	return &Trie{cost: inf}
}

func (t *Trie) insert(word string, cost int) {
	node := t
	for _, c := range word {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = NewTrie()
		}
		node = node.children[idx]
	}
	node.cost = min(node.cost, cost)
}

func minimumCost(target string, words []string, costs []int) int {
	trie := NewTrie()
	for i, word := range words {
		trie.insert(word, costs[i])
	}

	n := len(target)
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] != 0 {
			return f[i]
		}
		f[i] = inf
		node := trie
		for j := i; j < n; j++ {
			idx := target[j] - 'a'
			if node.children[idx] == nil {
				return f[i]
			}
			node = node.children[idx]
			f[i] = min(f[i], node.cost+dfs(j+1))
		}
		return f[i]
	}
	if ans := dfs(0); ans < inf {
		return ans
	}
	return -1
}
```

#### TypeScript

```ts
const inf = 1 << 29;

class Trie {
    children: (Trie | null)[];
    cost: number;

    constructor() {
        this.children = Array(26).fill(null);
        this.cost = inf;
    }

    insert(word: string, cost: number): void {
        let node: Trie = this;
        for (const c of word) {
            const idx = c.charCodeAt(0) - 97;
            if (!node.children[idx]) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx]!;
        }
        node.cost = Math.min(node.cost, cost);
    }
}

function minimumCost(target: string, words: string[], costs: number[]): number {
    const trie = new Trie();
    for (let i = 0; i < words.length; ++i) {
        trie.insert(words[i], costs[i]);
    }

    const n = target.length;
    const f: number[] = Array(n).fill(0);
    const dfs = (i: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i]) {
            return f[i];
        }
        f[i] = inf;
        let node: Trie | null = trie;
        for (let j = i; j < n; ++j) {
            const idx = target.charCodeAt(j) - 97;
            if (!node?.children[idx]) {
                return f[i];
            }
            node = node.children[idx];
            f[i] = Math.min(f[i], node!.cost + dfs(j + 1));
        }
        return f[i];
    };

    const ans = dfs(0);
    return ans < inf ? ans : -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
