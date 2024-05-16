---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0676.Implement%20Magic%20Dictionary/README.md
tags:
    - 深度优先搜索
    - 设计
    - 字典树
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [676. 实现一个魔法字典](https://leetcode.cn/problems/implement-magic-dictionary)

[English Version](/solution/0600-0699/0676.Implement%20Magic%20Dictionary/README_EN.md)

## 题目描述

<!-- description:start -->

<p>设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 <strong>互不相同</strong> 。 如果给出一个单词，请判定能否只将这个单词中<strong>一个</strong>字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。</p>

<p>实现 <code>MagicDictionary</code> 类：</p>

<ul>
	<li><code>MagicDictionary()</code> 初始化对象</li>
	<li><code>void buildDict(String[] dictionary)</code> 使用字符串数组 <code>dictionary</code> 设定该数据结构，<code>dictionary</code> 中的字符串互不相同</li>
	<li><code>bool search(String searchWord)</code> 给定一个字符串 <code>searchWord</code> ，判定能否只将字符串中<strong> 一个 </strong>字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 <code>true</code> ；否则，返回 <code>false</code> 。</li>
</ul>

<p> </p>

<div class="top-view__1vxA">
<div class="original__bRMd">
<div>
<p><strong>示例：</strong></p>

<pre>
<strong>输入</strong>
["MagicDictionary", "buildDict", "search", "search", "search", "search"]
[[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
<strong>输出</strong>
[null, null, false, true, false, false]

<strong>解释</strong>
MagicDictionary magicDictionary = new MagicDictionary();
magicDictionary.buildDict(["hello", "leetcode"]);
magicDictionary.search("hello"); // 返回 False
magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
magicDictionary.search("hell"); // 返回 False
magicDictionary.search("leetcoded"); // 返回 False
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= dictionary.length <= 100</code></li>
	<li><code>1 <= dictionary[i].length <= 100</code></li>
	<li><code>dictionary[i]</code> 仅由小写英文字母组成</li>
	<li><code>dictionary</code> 中的所有字符串 <strong>互不相同</strong></li>
	<li><code>1 <= searchWord.length <= 100</code></li>
	<li><code>searchWord</code> 仅由小写英文字母组成</li>
	<li><code>buildDict</code> 仅在 <code>search</code> 之前调用一次</li>
	<li>最多调用 <code>100</code> 次 <code>search</code></li>
</ul>
</div>
</div>
</div>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀树 + DFS

我们可以使用前缀树来存储字典中的所有单词，然后对于每个搜索的单词，我们使用深度优先搜索的方法，具体地，我们从前缀树的根节点开始，对于当前遍历到的字母，我们首先判断是否存在与其相同的子节点，如果存在，则继续向下遍历，否则我们需要判断是否还有剩余的修改次数，如果没有，则说明无法匹配，返回 false。如果有剩余的修改次数，我们可以尝试对当前的字母进行修改，然后继续向下遍历，如果当前的字母修改后对应的子节点存在，则说明可以匹配，否则说明无法匹配，返回 false。如果我们遍历到了单词的结尾，且修改次数恰好为 1，那么说明可以匹配，返回 true。

时间复杂度 $O(n \times l + q \times l \times |\Sigma|)$，空间复杂度 $O(n \times l)$，其中 $n$ 和 $l$ 分别是字典中的单词数量和单词的平均长度，而 $q$ 是搜索的单词数量。另外 $|\Sigma|$ 表示字符集的大小，这里字符集为小写英文字母，因此 $|\Sigma|=26$。

<!-- tabs:start -->

```python
class Trie:
    __slots__ = ["children", "is_end"]

    def __init__(self):
        self.children = {}
        self.is_end = False

    def insert(self, w: str) -> None:
        node = self
        for c in w:
            if c not in node.children:
                node.children[c] = Trie()
            node = node.children[c]
        node.is_end = True

    def search(self, w: str) -> bool:
        def dfs(i: int, node: Trie, diff: int) -> bool:
            if i == len(w):
                return diff == 1 and node.is_end
            if w[i] in node.children and dfs(i + 1, node.children[w[i]], diff):
                return True
            return diff == 0 and any(
                dfs(i + 1, node.children[c], 1) for c in node.children if c != w[i]
            )

        return dfs(0, self, 0)


class MagicDictionary:
    def __init__(self):
        self.trie = Trie()

    def buildDict(self, dictionary: List[str]) -> None:
        for w in dictionary:
            self.trie.insert(w)

    def search(self, searchWord: str) -> bool:
        return self.trie.search(searchWord)


# Your MagicDictionary object will be instantiated and called as such:
# obj = MagicDictionary()
# obj.buildDict(dictionary)
# param_2 = obj.search(searchWord)
```

```java
class Trie {
    private Trie[] children = new Trie[26];
    private boolean isEnd;

    public void insert(String w) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null) {
                node.children[i] = new Trie();
            }
            node = node.children[i];
        }
        node.isEnd = true;
    }

    public boolean search(String w) {
        return dfs(w, 0, this, 0);
    }

    private boolean dfs(String w, int i, Trie node, int diff) {
        if (i == w.length()) {
            return diff == 1 && node.isEnd;
        }
        int j = w.charAt(i) - 'a';
        if (node.children[j] != null) {
            if (dfs(w, i + 1, node.children[j], diff)) {
                return true;
            }
        }
        if (diff == 0) {
            for (int k = 0; k < 26; k++) {
                if (k != j && node.children[k] != null) {
                    if (dfs(w, i + 1, node.children[k], 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

class MagicDictionary {
    private Trie trie = new Trie();

    public MagicDictionary() {
    }

    public void buildDict(String[] dictionary) {
        for (String w : dictionary) {
            trie.insert(w);
        }
    }

    public boolean search(String searchWord) {
        return trie.search(searchWord);
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
```

```cpp
class Trie {
private:
    Trie* children[26];
    bool isEnd = false;

public:
    Trie() {
        fill(begin(children), end(children), nullptr);
    }

    void insert(const string& w) {
        Trie* node = this;
        for (char c : w) {
            int i = c - 'a';
            if (!node->children[i]) {
                node->children[i] = new Trie();
            }
            node = node->children[i];
        }
        node->isEnd = true;
    }

    bool search(const string& w) {
        function<bool(int, Trie*, int)> dfs = [&](int i, Trie* node, int diff) {
            if (i >= w.size()) {
                return diff == 1 && node->isEnd;
            }
            int j = w[i] - 'a';
            if (node->children[j] && dfs(i + 1, node->children[j], diff)) {
                return true;
            }
            if (diff == 0) {
                for (int k = 0; k < 26; ++k) {
                    if (k != j && node->children[k]) {
                        if (dfs(i + 1, node->children[k], 1)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        };
        return dfs(0, this, 0);
    }
};

class MagicDictionary {
public:
    MagicDictionary() {
        trie = new Trie();
    }

    void buildDict(vector<string> dictionary) {
        for (auto& w : dictionary) {
            trie->insert(w);
        }
    }

    bool search(string searchWord) {
        return trie->search(searchWord);
    }

private:
    Trie* trie;
};

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary* obj = new MagicDictionary();
 * obj->buildDict(dictionary);
 * bool param_2 = obj->search(searchWord);
 */
```

```go
type Trie struct {
	children [26]*Trie
	isEnd    bool
}

func NewTrie() *Trie {
	return &Trie{}
}

func (t *Trie) Insert(w string) {
	node := t
	for _, c := range w {
		i := c - 'a'
		if node.children[i] == nil {
			node.children[i] = NewTrie()
		}
		node = node.children[i]
	}
	node.isEnd = true
}

func (t *Trie) Search(w string) bool {
	var dfs func(int, *Trie, int) bool
	dfs = func(i int, node *Trie, diff int) bool {
		if i >= len(w) {
			return diff == 1 && node.isEnd
		}
		j := int(w[i] - 'a')
		if node.children[j] != nil && dfs(i+1, node.children[j], diff) {
			return true
		}
		if diff == 0 {
			for k := 0; k < 26; k++ {
				if k != j && node.children[k] != nil && dfs(i+1, node.children[k], 1) {
					return true
				}
			}
		}
		return false
	}
	return dfs(0, t, 0)
}

type MagicDictionary struct {
	trie *Trie
}

func Constructor() MagicDictionary {
	return MagicDictionary{trie: NewTrie()}
}

func (md *MagicDictionary) BuildDict(dictionary []string) {
	for _, w := range dictionary {
		md.trie.Insert(w)
	}
}

func (md *MagicDictionary) Search(searchWord string) bool {
	return md.trie.Search(searchWord)
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * obj := Constructor();
 * obj.BuildDict(dictionary);
 * param_2 := obj.Search(searchWord);
 */
```

```ts
class Trie {
    private children: Trie[] = Array(26).fill(null);
    private isEnd: boolean = false;

    constructor() {}

    insert(w: string): void {
        let node: Trie = this;
        for (const c of w) {
            const i: number = c.charCodeAt(0) - 'a'.charCodeAt(0);
            if (!node.children[i]) {
                node.children[i] = new Trie();
            }
            node = node.children[i];
        }
        node.isEnd = true;
    }

    search(w: string): boolean {
        const dfs = (i: number, node: Trie, diff: number): boolean => {
            if (i >= w.length) {
                return diff === 1 && node.isEnd;
            }
            const j: number = w.charCodeAt(i) - 'a'.charCodeAt(0);
            if (node.children[j] && dfs(i + 1, node.children[j], diff)) {
                return true;
            }
            if (diff === 0) {
                for (let k = 0; k < 26; k++) {
                    if (k !== j && node.children[k] && dfs(i + 1, node.children[k], 1)) {
                        return true;
                    }
                }
            }
            return false;
        };
        return dfs(0, this, 0);
    }
}

class MagicDictionary {
    private trie: Trie;

    constructor() {
        this.trie = new Trie();
    }

    buildDict(dictionary: string[]): void {
        for (const w of dictionary) {
            this.trie.insert(w);
        }
    }

    search(searchWord: string): boolean {
        return this.trie.search(searchWord);
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * var obj = new MagicDictionary()
 * obj.buildDict(dictionary)
 * var param_2 = obj.search(searchWord)
 */
```

```rust
use std::collections::HashMap;

#[derive(Clone)]
struct Trie {
    children: Vec<Option<Box<Trie>>>,
    is_end: bool,
}

impl Trie {
    fn new() -> Self {
        Trie {
            children: vec![None; 26],
            is_end: false,
        }
    }

    fn insert(&mut self, word: &str) {
        let mut node = self;
        for &ch in word.as_bytes() {
            let index = (ch - b'a') as usize;
            node = node.children[index].get_or_insert_with(|| Box::new(Trie::new()));
        }
        node.is_end = true;
    }

    fn search(&self, word: &str, diff: i32) -> bool {
        if word.is_empty() {
            return diff == 1 && self.is_end;
        }

        let index = (word.as_bytes()[0] - b'a') as usize;
        if let Some(child) = &self.children[index] {
            if child.search(&word[1..], diff) {
                return true;
            }
        }

        if diff == 0 {
            for (i, child) in self.children.iter().enumerate() {
                if i != index && child.is_some() {
                    if
                        child
                            .as_ref()
                            .unwrap()
                            .search(&word[1..], 1)
                    {
                        return true;
                    }
                }
            }
        }

        false
    }
}

struct MagicDictionary {
    trie: Trie,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MagicDictionary {
    fn new() -> Self {
        MagicDictionary { trie: Trie::new() }
    }

    fn build_dict(&mut self, dictionary: Vec<String>) {
        for word in dictionary {
            self.trie.insert(&word);
        }
    }

    fn search(&self, search_word: String) -> bool {
        self.trie.search(&search_word, 0)
    }
}/**
 * Your MagicDictionary object will be instantiated and called as such:
 * let obj = MagicDictionary::new();
 * obj.build_dict(dictionary);
 * let ret_2: bool = obj.search(searchWord);
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

```python
class Trie:
    __slots__ = ["children", "is_end"]

    def __init__(self):
        self.children: [Trie | None] = [None] * 26
        self.is_end = False

    def insert(self, w: str) -> None:
        node = self
        for c in w:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True

    def search(self, w: str) -> bool:
        def dfs(i: int, node: [Trie | None], diff: int) -> bool:
            if i == len(w):
                return diff == 1 and node.is_end
            j = ord(w[i]) - ord("a")
            if node.children[j] and dfs(i + 1, node.children[j], diff):
                return True
            return diff == 0 and any(
                node.children[k] and dfs(i + 1, node.children[k], 1)
                for k in range(26)
                if k != j
            )

        return dfs(0, self, 0)


class MagicDictionary:
    def __init__(self):
        self.trie = Trie()

    def buildDict(self, dictionary: List[str]) -> None:
        for w in dictionary:
            self.trie.insert(w)

    def search(self, searchWord: str) -> bool:
        return self.trie.search(searchWord)


# Your MagicDictionary object will be instantiated and called as such:
# obj = MagicDictionary()
# obj.buildDict(dictionary)
# param_2 = obj.search(searchWord)
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
