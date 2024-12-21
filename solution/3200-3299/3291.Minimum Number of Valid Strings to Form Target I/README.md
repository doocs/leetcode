---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3291.Minimum%20Number%20of%20Valid%20Strings%20to%20Form%20Target%20I/README.md
rating: 2081
source: 第 415 场周赛 Q3
tags:
    - 字典树
    - 线段树
    - 数组
    - 字符串
    - 二分查找
    - 动态规划
    - 字符串匹配
    - 哈希函数
    - 滚动哈希
---

<!-- problem:start -->

# [3291. 形成目标字符串需要的最少字符串数 I](https://leetcode.cn/problems/minimum-number-of-valid-strings-to-form-target-i)

[English Version](/solution/3200-3299/3291.Minimum%20Number%20of%20Valid%20Strings%20to%20Form%20Target%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串数组 <code>words</code> 和一个字符串 <code>target</code>。</p>

<p>如果字符串 <code>x</code> 是 <code>words</code> 中<strong> 任意 </strong>字符串的 <span data-keyword="string-prefix">前缀</span>，则认为 <code>x</code> 是一个 <strong>有效</strong> 字符串。</p>

<p>现计划通过 <strong>连接 </strong>有效字符串形成 <code>target</code> ，请你计算并返回需要连接的 <strong>最少 </strong>字符串数量。如果无法通过这种方式形成 <code>target</code>，则返回 <code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">words = ["abc","aaaaa","bcdef"], target = "aabcdabc"</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>target 字符串可以通过连接以下有效字符串形成：</p>

<ul>
	<li><code>words[1]</code> 的长度为 2 的前缀，即 <code>"aa"</code>。</li>
	<li><code>words[2]</code> 的长度为 3 的前缀，即 <code>"bcd"</code>。</li>
	<li><code>words[0]</code> 的长度为 3 的前缀，即 <code>"abc"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">words = ["abababab","ab"], target = "ababaababa"</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>target 字符串可以通过连接以下有效字符串形成：</p>

<ul>
	<li><code>words[0]</code> 的长度为 5 的前缀，即 <code>"ababa"</code>。</li>
	<li><code>words[0]</code> 的长度为 5 的前缀，即 <code>"ababa"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">words = ["abcdef"], target = "xyz"</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 5 * 10<sup>3</sup></code></li>
	<li>输入确保 <code>sum(words[i].length) &lt;= 10<sup>5</sup></code>。</li>
	<li><code>words[i]</code> 只包含小写英文字母。</li>
	<li><code>1 &lt;= target.length &lt;= 5 * 10<sup>3</sup></code></li>
	<li><code>target</code> 只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：字典树 + 记忆化搜索

我们可以使用字典树存储所有有效字符串，然后使用记忆化搜索计算答案。

我们设计一个函数 $\textit{dfs}(i)$，表示从字符串 $\textit{target}$ 的第 $i$ 个字符开始，需要连接的最少字符串数量。那么答案就是 $\textit{dfs}(0)$。

函数 $\textit{dfs}(i)$ 的计算方式如下：

-   如果 $i \geq n$，表示字符串 $\textit{target}$ 已经遍历完了，返回 $0$；
-   否则，我们可以从字典树中找到以 $\textit{target}[i]$ 开头的有效字符串，然后递归计算 $\textit{dfs}(i + \text{len}(w))$，其中 $w$ 是找到的有效字符串。我们取这些值中的最小值加 $1$ 作为 $\textit{dfs}(i)$ 的返回值。

为了避免重复计算，我们使用记忆化搜索。

时间复杂度 $O(n^2 + L)$，空间复杂度 $O(n + L)$。其中 $n$ 是字符串 $\textit{target}$ 的长度，而 $L$ 是所有有效字符串的总长度。

<!-- tabs:start -->

#### Python3

```python
def min(a: int, b: int) -> int:
    return a if a < b else b


class Trie:
    def __init__(self):
        self.children: List[Optional[Trie]] = [None] * 26

    def insert(self, w: str):
        node = self
        for i in map(lambda c: ord(c) - 97, w):
            if node.children[i] is None:
                node.children[i] = Trie()
            node = node.children[i]


class Solution:
    def minValidStrings(self, words: List[str], target: str) -> int:
        @cache
        def dfs(i: int) -> int:
            if i >= n:
                return 0
            node = trie
            ans = inf
            for j in range(i, n):
                k = ord(target[j]) - 97
                if node.children[k] is None:
                    break
                node = node.children[k]
                ans = min(ans, 1 + dfs(j + 1))
            return ans

        trie = Trie()
        for w in words:
            trie.insert(w)
        n = len(target)
        ans = dfs(0)
        return ans if ans < inf else -1
```

#### Java

```java
class Trie {
    Trie[] children = new Trie[26];

    void insert(String w) {
        Trie node = this;
        for (int i = 0; i < w.length(); ++i) {
            int j = w.charAt(i) - 'a';
            if (node.children[j] == null) {
                node.children[j] = new Trie();
            }
            node = node.children[j];
        }
    }
}

class Solution {
    private Integer[] f;
    private char[] s;
    private Trie trie;
    private final int inf = 1 << 30;

    public int minValidStrings(String[] words, String target) {
        trie = new Trie();
        for (String w : words) {
            trie.insert(w);
        }
        s = target.toCharArray();
        f = new Integer[s.length];
        int ans = dfs(0);
        return ans < inf ? ans : -1;
    }

    private int dfs(int i) {
        if (i >= s.length) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        Trie node = trie;
        f[i] = inf;
        for (int j = i; j < s.length; ++j) {
            int k = s[j] - 'a';
            if (node.children[k] == null) {
                break;
            }
            f[i] = Math.min(f[i], 1 + dfs(j + 1));
            node = node.children[k];
        }
        return f[i];
    }
}
```

#### C++

```cpp
class Trie {
public:
    Trie* children[26]{};

    void insert(string& word) {
        Trie* node = this;
        for (char& c : word) {
            int i = c - 'a';
            if (!node->children[i]) {
                node->children[i] = new Trie();
            }
            node = node->children[i];
        }
    }
};

class Solution {
public:
    int minValidStrings(vector<string>& words, string target) {
        int n = target.size();
        Trie* trie = new Trie();
        for (auto& w : words) {
            trie->insert(w);
        }
        const int inf = 1 << 30;
        int f[n];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i) -> int {
            if (i >= n) {
                return 0;
            }
            if (f[i] != -1) {
                return f[i];
            }
            f[i] = inf;
            Trie* node = trie;
            for (int j = i; j < n; ++j) {
                int k = target[j] - 'a';
                if (!node->children[k]) {
                    break;
                }
                node = node->children[k];
                f[i] = min(f[i], 1 + dfs(j + 1));
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
type Trie struct {
	children [26]*Trie
}

func (t *Trie) insert(word string) {
	node := t
	for _, c := range word {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = &Trie{}
		}
		node = node.children[idx]
	}
}

func minValidStrings(words []string, target string) int {
	n := len(target)
	trie := &Trie{}
	for _, w := range words {
		trie.insert(w)
	}
	const inf int = 1 << 30
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] != 0 {
			return f[i]
		}
		node := trie
		f[i] = inf
		for j := i; j < n; j++ {
			k := int(target[j] - 'a')
			if node.children[k] == nil {
				break
			}
			f[i] = min(f[i], 1+dfs(j+1))
			node = node.children[k]
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
class Trie {
    children: (Trie | null)[] = Array(26).fill(null);

    insert(word: string): void {
        let node: Trie = this;
        for (const c of word) {
            const i = c.charCodeAt(0) - 'a'.charCodeAt(0);
            if (!node.children[i]) {
                node.children[i] = new Trie();
            }
            node = node.children[i];
        }
    }
}

function minValidStrings(words: string[], target: string): number {
    const n = target.length;
    const trie = new Trie();
    for (const w of words) {
        trie.insert(w);
    }
    const inf = 1 << 30;
    const f = Array(n).fill(0);

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
            const k = target[j].charCodeAt(0) - 'a'.charCodeAt(0);
            if (!node?.children[k]) {
                break;
            }
            node = node.children[k];
            f[i] = Math.min(f[i], 1 + dfs(j + 1));
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
