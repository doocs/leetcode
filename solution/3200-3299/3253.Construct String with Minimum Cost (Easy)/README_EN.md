---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3253.Construct%20String%20with%20Minimum%20Cost%20%28Easy%29/README_EN.md
---

<!-- problem:start -->

# [3253. Construct String with Minimum Cost (Easy) 🔒](https://leetcode.com/problems/construct-string-with-minimum-cost-easy)

[中文文档](/solution/3200-3299/3253.Construct%20String%20with%20Minimum%20Cost%20%28Easy%29/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>target</code>, an array of strings <code>words</code>, and an integer array <code>costs</code>, both arrays of the same length.</p>

<p>Imagine an empty string <code>s</code>.</p>

<p>You can perform the following operation any number of times (including <strong>zero</strong>):</p>

<ul>
	<li>Choose an index <code>i</code> in the range <code>[0, words.length - 1]</code>.</li>
	<li>Append <code>words[i]</code> to <code>s</code>.</li>
	<li>The cost of operation is <code>costs[i]</code>.</li>
</ul>

<p>Return the <strong>minimum</strong> cost to make <code>s</code> equal to <code>target</code>. If it&#39;s not possible, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">target = &quot;abcdef&quot;, words = [&quot;abdef&quot;,&quot;abc&quot;,&quot;d&quot;,&quot;def&quot;,&quot;ef&quot;], costs = [100,1,1,10,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>The minimum cost can be achieved by performing the following operations:</p>

<ul>
	<li>Select index 1 and append <code>&quot;abc&quot;</code> to <code>s</code> at a cost of 1, resulting in <code>s = &quot;abc&quot;</code>.</li>
	<li>Select index 2 and append <code>&quot;d&quot;</code> to <code>s</code> at a cost of 1, resulting in <code>s = &quot;abcd&quot;</code>.</li>
	<li>Select index 4 and append <code>&quot;ef&quot;</code> to <code>s</code> at a cost of 5, resulting in <code>s = &quot;abcdef&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">target = &quot;aaaa&quot;, words = [&quot;z&quot;,&quot;zz&quot;,&quot;zzz&quot;], costs = [1,10,100]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>It is impossible to make <code>s</code> equal to <code>target</code>, so we return -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 2000</code></li>
	<li><code>1 &lt;= words.length == costs.length &lt;= 50</code></li>
	<li><code>1 &lt;= words[i].length &lt;= target.length</code></li>
	<li><code>target</code> and <code>words[i]</code> consist only of lowercase English letters.</li>
	<li><code>1 &lt;= costs[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Trie + Memoized Search

We first create a Trie $\textit{trie}$, where each node in the Trie contains an array $\textit{children}$ of length $26$, and each element in the array is a pointer to the next node. Each node in the Trie also contains a $\textit{cost}$ variable, which represents the minimum cost from the root node to the current node.

We traverse the $\textit{words}$ array, inserting each word into the Trie while updating the $\textit{cost}$ variable for each node.

Next, we define a memoized search function $\textit{dfs}(i)$, which represents the minimum cost to construct the string starting from $\textit{target}[i]$. The answer is $\textit{dfs}(0)$.

The calculation process of the function $\textit{dfs}(i)$ is as follows:

-   If $i \geq \textit{len}(\textit{target})$, it means the entire string has been constructed, so return $0$.
-   Otherwise, we start from the root node of the $\textit{trie}$ and traverse all suffixes starting from $\textit{target}[i]$, finding the minimum cost, which is the $\textit{cost}$ variable in the $\textit{trie}$, plus the result of $\textit{dfs}(j+1)$, where $j$ is the ending position of the suffix starting from $\textit{target}[i]$.

Finally, if $\textit{dfs}(0) < \textit{inf}$, return $\textit{dfs}(0)$; otherwise, return $-1$.

The time complexity is $O(n^2 + L)$, and the space complexity is $O(n + L)$. Here, $n$ is the length of $\textit{target}$, and $L$ is the sum of the lengths of all words in the $\textit{words}$ array.

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
