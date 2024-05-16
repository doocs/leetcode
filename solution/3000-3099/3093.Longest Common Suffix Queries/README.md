---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3093.Longest%20Common%20Suffix%20Queries/README.md
rating: 2118
source: 第 390 场周赛 Q4
tags:
    - 字典树
    - 数组
    - 字符串
---

# [3093. 最长公共后缀查询](https://leetcode.cn/problems/longest-common-suffix-queries)

[English Version](/solution/3000-3099/3093.Longest%20Common%20Suffix%20Queries/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串数组&nbsp;<code>wordsContainer</code> 和&nbsp;<code>wordsQuery</code>&nbsp;。</p>

<p>对于每个&nbsp;<code>wordsQuery[i]</code>&nbsp;，你需要从&nbsp;<code>wordsContainer</code>&nbsp;中找到一个与&nbsp;<code>wordsQuery[i]</code>&nbsp;有&nbsp;<strong>最长公共后缀</strong>&nbsp;的字符串。如果 <code>wordsContainer</code>&nbsp;中有两个或者更多字符串有最长公共后缀，那么答案为长度<strong>&nbsp;最短</strong>&nbsp;的。如果有超过两个字符串有&nbsp;<strong>相同</strong>&nbsp;最短长度，那么答案为它们在&nbsp;<code>wordsContainer</code>&nbsp;中出现&nbsp;<strong>更早</strong>&nbsp;的一个。</p>

<p>请你返回一个整数数组<em>&nbsp;</em><code>ans</code>&nbsp;，其中<em>&nbsp;</em><code>ans[i]</code>是<em>&nbsp;</em><code>wordsContainer</code>中与&nbsp;<code>wordsQuery[i]</code>&nbsp;有&nbsp;<strong>最长公共后缀</strong>&nbsp;字符串的下标。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>wordsContainer = ["abcd","bcd","xbcd"], wordsQuery = ["cd","bcd","xyz"]</span></p>

<p><span class="example-io"><b>输出：</b>[1,1,1]</span></p>

<p><strong>解释：</strong></p>

<p>我们分别来看每一个&nbsp;<code>wordsQuery[i]</code>&nbsp;：</p>

<ul>
	<li>对于&nbsp;<code>wordsQuery[0] = "cd"</code>&nbsp;，<code>wordsContainer</code>&nbsp;中有最长公共后缀&nbsp;<code>"cd"</code>&nbsp;的字符串下标分别为&nbsp;0 ，1 和&nbsp;2 。这些字符串中，答案是下标为 1 的字符串，因为它的长度为 3 ，是最短的字符串。</li>
	<li>对于&nbsp;<code>wordsQuery[1] = "bcd"</code>&nbsp;，<code>wordsContainer</code>&nbsp;中有最长公共后缀&nbsp;<code>"bcd"</code>&nbsp;的字符串下标分别为 0 ，1 和 2 。这些字符串中，答案是下标为 1 的字符串，因为它的长度为 3 ，是最短的字符串。</li>
	<li>对于&nbsp;<code>wordsQuery[2] = "xyz"</code>&nbsp;，<code>wordsContainer</code>&nbsp;中没有字符串跟它有公共后缀，所以最长公共后缀为&nbsp;<code>""</code>&nbsp;，下标为&nbsp;0 ，1 和 2 的字符串都得到这一公共后缀。这些字符串中，&nbsp;答案是下标为 1 的字符串，因为它的长度为 3 ，是最短的字符串。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>wordsContainer = ["abcdefgh","poiuygh","ghghgh"], wordsQuery = ["gh","acbfgh","acbfegh"]</span></p>

<p><span class="example-io"><b>输出：</b>[2,0,2]</span></p>

<p><strong>解释：</strong></p>

<p>我们分别来看每一个&nbsp;<code>wordsQuery[i]</code>&nbsp;：</p>

<ul>
	<li>对于&nbsp;<code>wordsQuery[0] = "gh"</code>&nbsp;，<code>wordsContainer</code>&nbsp;中有最长公共后缀&nbsp;<code>"gh"</code>&nbsp;的字符串下标分别为 0 ，1 和 2 。这些字符串中，答案是下标为 2 的字符串，因为它的长度为 6 ，是最短的字符串。</li>
	<li>对于&nbsp;<code>wordsQuery[1] = "acbfgh"</code>&nbsp;，只有下标为 0 的字符串有最长公共后缀&nbsp;<code>"fgh"</code>&nbsp;。所以尽管下标为 2 的字符串是最短的字符串，但答案是 0 。</li>
	<li>对于&nbsp;<code>wordsQuery[2] = "acbfegh"</code>&nbsp;，<code>wordsContainer</code>&nbsp;中有最长公共后缀&nbsp;<code>"gh"</code>&nbsp;的字符串下标分别为 0 ，1 和 2 。这些字符串中，答案是下标为 2 的字符串，因为它的长度为 6 ，是最短的字符串。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= wordsContainer.length, wordsQuery.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= wordsContainer[i].length &lt;= 5 * 10<sup>3</sup></code></li>
	<li><code>1 &lt;= wordsQuery[i].length &lt;= 5 * 10<sup>3</sup></code></li>
	<li><code>wordsContainer[i]</code>&nbsp;只包含小写英文字母。</li>
	<li><code>wordsQuery[i]</code>&nbsp;只包含小写英文字母。</li>
	<li><code>wordsContainer[i].length</code>&nbsp;的和至多为&nbsp;<code>5 * 10<sup>5</sup></code>&nbsp;。</li>
	<li><code>wordsQuery[i].length</code>&nbsp;的和至多为&nbsp;<code>5 * 10<sup>5</sup></code>&nbsp;。</li>
</ul>

## 解法

### 方法一：字典树

题目需要我们找到最长公共后缀，我们可以考虑使用字典树。

我们定义字典树的节点结构如下：

-   `children`：一个长度为 26 的数组，用于存储子节点。
-   `length`：当前节点的最短字符串长度。
-   `idx`：当前节点的字符串下标。

我们遍历字符串数组 `wordsContainer`，将每个字符串倒序插入字典树中。在插入的过程中，我们更新每个节点的 `length` 和 `idx`。

接下来，我们遍历字符串数组 `wordsQuery`，对于每个字符串，我们从字典树中查找最长公共后缀的字符串下标，在寻找的过程中，如果遇到空节点，说明往后没有公共后缀了，我们可以直接返回当前节点的 `idx`。

时间复杂度 $(L_1 \times |\Sigma| + L_2)$，空间复杂度 $O(L_1 \times |\Sigma|)$，其中 $L_1$ 和 $L_2$ 分别是 `wordsContainer` 和 `wordsQuery` 的字符串长度之和；而 $\Sigma$ 是字符集大小，本题中 $\Sigma = 26$。

<!-- tabs:start -->

```python
class Trie:
    __slots__ = ("children", "length", "idx")

    def __init__(self):
        self.children = [None] * 26
        self.length = inf
        self.idx = inf

    def insert(self, w: str, i: int):
        node = self
        if node.length > len(w):
            node.length = len(w)
            node.idx = i
        for c in w[::-1]:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            if node.length > len(w):
                node.length = len(w)
                node.idx = i

    def query(self, w: str) -> int:
        node = self
        for c in w[::-1]:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                break
            node = node.children[idx]
        return node.idx


class Solution:
    def stringIndices(
        self, wordsContainer: List[str], wordsQuery: List[str]
    ) -> List[int]:
        trie = Trie()
        for i, w in enumerate(wordsContainer):
            trie.insert(w, i)
        return [trie.query(w) for w in wordsQuery]
```

```java
class Trie {
    private final int inf = 1 << 30;
    private Trie[] children = new Trie[26];
    private int length = inf;
    private int idx = inf;

    public void insert(String w, int i) {
        Trie node = this;
        if (node.length > w.length()) {
            node.length = w.length();
            node.idx = i;
        }
        for (int k = w.length() - 1; k >= 0; --k) {
            int idx = w.charAt(k) - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
            if (node.length > w.length()) {
                node.length = w.length();
                node.idx = i;
            }
        }
    }

    public int query(String w) {
        Trie node = this;
        for (int k = w.length() - 1; k >= 0; --k) {
            int idx = w.charAt(k) - 'a';
            if (node.children[idx] == null) {
                break;
            }
            node = node.children[idx];
        }
        return node.idx;
    }
}

class Solution {
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Trie trie = new Trie();
        for (int i = 0; i < wordsContainer.length; ++i) {
            trie.insert(wordsContainer[i], i);
        }
        int n = wordsQuery.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = trie.query(wordsQuery[i]);
        }
        return ans;
    }
}
```

```cpp
class Trie {
private:
    const int inf = 1 << 30;
    Trie* children[26];
    int length = inf;
    int idx = inf;

public:
    Trie() {
        for (int i = 0; i < 26; ++i) {
            children[i] = nullptr;
        }
    }

    void insert(string w, int i) {
        Trie* node = this;
        if (node->length > w.length()) {
            node->length = w.length();
            node->idx = i;
        }
        for (int k = w.length() - 1; k >= 0; --k) {
            int idx = w[k] - 'a';
            if (node->children[idx] == nullptr) {
                node->children[idx] = new Trie();
            }
            node = node->children[idx];
            if (node->length > w.length()) {
                node->length = w.length();
                node->idx = i;
            }
        }
    }

    int query(string w) {
        Trie* node = this;
        for (int k = w.length() - 1; k >= 0; --k) {
            int idx = w[k] - 'a';
            if (node->children[idx] == nullptr) {
                break;
            }
            node = node->children[idx];
        }
        return node->idx;
    }
};

class Solution {
public:
    vector<int> stringIndices(vector<string>& wordsContainer, vector<string>& wordsQuery) {
        Trie* trie = new Trie();
        for (int i = 0; i < wordsContainer.size(); ++i) {
            trie->insert(wordsContainer[i], i);
        }
        int n = wordsQuery.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = trie->query(wordsQuery[i]);
        }
        return ans;
    }
};
```

```go
const inf = 1 << 30

type Trie struct {
	children [26]*Trie
	length   int
	idx      int
}

func newTrie() *Trie {
	return &Trie{length: inf, idx: inf}
}

func (t *Trie) insert(w string, i int) {
	node := t
	if node.length > len(w) {
		node.length = len(w)
		node.idx = i
	}
	for k := len(w) - 1; k >= 0; k-- {
		idx := int(w[k] - 'a')
		if node.children[idx] == nil {
			node.children[idx] = newTrie()
		}
		node = node.children[idx]
		if node.length > len(w) {
			node.length = len(w)
			node.idx = i
		}
	}
}

func (t *Trie) query(w string) int {
	node := t
	for k := len(w) - 1; k >= 0; k-- {
		idx := int(w[k] - 'a')
		if node.children[idx] == nil {
			break
		}
		node = node.children[idx]
	}
	return node.idx
}

func stringIndices(wordsContainer []string, wordsQuery []string) (ans []int) {
	trie := newTrie()
	for i, w := range wordsContainer {
		trie.insert(w, i)
	}
	for _, w := range wordsQuery {
		ans = append(ans, trie.query(w))
	}
	return
}
```

```ts
class Trie {
    private children: Trie[] = new Array<Trie>(26);
    private length: number = Infinity;
    private idx: number = Infinity;

    public insert(w: string, i: number): void {
        let node: Trie = this;
        if (node.length > w.length) {
            node.length = w.length;
            node.idx = i;
        }
        for (let k: number = w.length - 1; k >= 0; --k) {
            let idx: number = w.charCodeAt(k) - 'a'.charCodeAt(0);
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
            if (node.length > w.length) {
                node.length = w.length;
                node.idx = i;
            }
        }
    }

    public query(w: string): number {
        let node: Trie = this;
        for (let k: number = w.length - 1; k >= 0; --k) {
            let idx: number = w.charCodeAt(k) - 'a'.charCodeAt(0);
            if (node.children[idx] == null) {
                break;
            }
            node = node.children[idx];
        }
        return node.idx;
    }
}

function stringIndices(wordsContainer: string[], wordsQuery: string[]): number[] {
    const trie: Trie = new Trie();
    for (let i: number = 0; i < wordsContainer.length; ++i) {
        trie.insert(wordsContainer[i], i);
    }
    const n: number = wordsQuery.length;
    const ans: number[] = new Array<number>(n);
    for (let i: number = 0; i < n; ++i) {
        ans[i] = trie.query(wordsQuery[i]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
