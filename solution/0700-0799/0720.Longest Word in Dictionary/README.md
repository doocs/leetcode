---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0720.Longest%20Word%20in%20Dictionary/README.md
tags:
    - 字典树
    - 数组
    - 哈希表
    - 字符串
    - 排序
---

<!-- problem:start -->

# [720. 词典中最长的单词](https://leetcode.cn/problems/longest-word-in-dictionary)

[English Version](/solution/0700-0799/0720.Longest%20Word%20in%20Dictionary/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给出一个字符串数组&nbsp;<code>words</code> 组成的一本英语词典。返回&nbsp;<code>words</code> 中最长的一个单词，该单词是由&nbsp;<code>words</code>&nbsp;词典中其他单词逐步添加一个字母组成。</p>

<p>若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。</p>

<p>请注意，单词应该从左到右构建，每个额外的字符都添加到前一个单词的结尾。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["w","wo","wor","worl", "world"]
<strong>输出：</strong>"world"
<strong>解释：</strong> 单词"world"可由"w", "wo", "wor", 和 "worl"逐步添加一个字母组成。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
<strong>输出：</strong>"apple"
<strong>解释：</strong>"apply" 和 "apple" 都能由词典中的单词组成。但是 "apple" 的字典序小于 "apply" 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 30</code></li>
	<li>所有输入的字符串&nbsp;<code>words[i]</code>&nbsp;都只包含小写字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：字典树

我们可以使用字典树来存储所有的单词，然后遍历所有的单词，判断当前单词是否可以由字典树中的其他单词逐步添加一个字母组成，找出满足条件的最长的，且字典序最小的单词。

时间复杂度 $O(L)$，空间复杂度 $O(L)$，其中 $L$ 是所有单词的长度之和。

<!-- tabs:start -->

#### Python3

```python
class Trie:
    def __init__(self):
        self.children: List[Optional[Trie]] = [None] * 26
        self.is_end = False

    def insert(self, w: str):
        node = self
        for c in w:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True

    def search(self, w: str) -> bool:
        node = self
        for c in w:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                return False
            node = node.children[idx]
            if not node.is_end:
                return False
        return True


class Solution:
    def longestWord(self, words: List[str]) -> str:
        trie = Trie()
        for w in words:
            trie.insert(w)
        ans = ""
        for w in words:
            if trie.search(w) and (
                len(ans) < len(w) or (len(ans) == len(w) and ans > w)
            ):
                ans = w
        return ans
```

#### Java

```java
class Trie {
    private Trie[] children = new Trie[26];
    private boolean isEnd = false;

    public void insert(String w) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public boolean search(String w) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null || !node.children[idx].isEnd) {
                return false;
            }
            node = node.children[idx];
        }
        return true;
    }
}

class Solution {
    public String longestWord(String[] words) {
        Trie trie = new Trie();
        for (String w : words) {
            trie.insert(w);
        }
        String ans = "";
        for (String w : words) {
            if (trie.search(w)
                && (ans.length() < w.length()
                    || (ans.length() == w.length() && w.compareTo(ans) < 0))) {
                ans = w;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Trie {
public:
    Trie* children[26] = {nullptr};
    bool isEnd = false;

    void insert(const string& w) {
        Trie* node = this;
        for (char c : w) {
            int idx = c - 'a';
            if (node->children[idx] == nullptr) {
                node->children[idx] = new Trie();
            }
            node = node->children[idx];
        }
        node->isEnd = true;
    }

    bool search(const string& w) {
        Trie* node = this;
        for (char c : w) {
            int idx = c - 'a';
            if (node->children[idx] == nullptr || !node->children[idx]->isEnd) {
                return false;
            }
            node = node->children[idx];
        }
        return true;
    }
};

class Solution {
public:
    string longestWord(vector<string>& words) {
        Trie trie;
        for (const string& w : words) {
            trie.insert(w);
        }

        string ans = "";
        for (const string& w : words) {
            if (trie.search(w) && (ans.length() < w.length() || (ans.length() == w.length() && w < ans))) {
                ans = w;
            }
        }
        return ans;
    }
};
```

#### Go

```go
type Trie struct {
	children [26]*Trie
	isEnd    bool
}

func (t *Trie) insert(w string) {
	node := t
	for i := 0; i < len(w); i++ {
		idx := w[i] - 'a'
		if node.children[idx] == nil {
			node.children[idx] = &Trie{}
		}
		node = node.children[idx]
	}
	node.isEnd = true
}

func (t *Trie) search(w string) bool {
	node := t
	for i := 0; i < len(w); i++ {
		idx := w[i] - 'a'
		if node.children[idx] == nil || !node.children[idx].isEnd {
			return false
		}
		node = node.children[idx]
	}
	return true
}

func longestWord(words []string) string {
	trie := &Trie{}
	for _, w := range words {
		trie.insert(w)
	}

	ans := ""
	for _, w := range words {
		if trie.search(w) && (len(ans) < len(w) || (len(ans) == len(w) && w < ans)) {
			ans = w
		}
	}
	return ans
}
```

#### TypeScript

```ts
class Trie {
    children: (Trie | null)[] = new Array(26).fill(null);
    isEnd: boolean = false;

    insert(w: string): void {
        let node: Trie = this;
        for (let i = 0; i < w.length; i++) {
            const idx: number = w.charCodeAt(i) - 'a'.charCodeAt(0);
            if (node.children[idx] === null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx]!;
        }
        node.isEnd = true;
    }

    search(w: string): boolean {
        let node: Trie = this;
        for (let i = 0; i < w.length; i++) {
            const idx: number = w.charCodeAt(i) - 'a'.charCodeAt(0);
            if (node.children[idx] === null || !node.children[idx]!.isEnd) {
                return false;
            }
            node = node.children[idx]!;
        }
        return true;
    }
}

function longestWord(words: string[]): string {
    const trie = new Trie();
    for (const w of words) {
        trie.insert(w);
    }

    let ans = '';
    for (const w of words) {
        if (trie.search(w) && (ans.length < w.length || (ans.length === w.length && w < ans))) {
            ans = w;
        }
    }
    return ans;
}
```

#### Rust

```rust
struct Trie {
    children: [Option<Box<Trie>>; 26],
    is_end: bool,
}

impl Trie {
    fn new() -> Self {
        Trie {
            children: Default::default(),
            is_end: false,
        }
    }

    fn insert(&mut self, w: &str) {
        let mut node = self;
        for c in w.chars() {
            let idx = (c as usize) - ('a' as usize);
            if node.children[idx].is_none() {
                node.children[idx] = Some(Box::new(Trie::new()));
            }
            node = node.children[idx].as_mut().unwrap();
        }
        node.is_end = true;
    }

    fn search(&self, w: &str) -> bool {
        let mut node = self;
        for c in w.chars() {
            let idx = (c as usize) - ('a' as usize);
            if node.children[idx].is_none() || !node.children[idx].as_ref().unwrap().is_end {
                return false;
            }
            node = node.children[idx].as_ref().unwrap();
        }
        true
    }
}

impl Solution {
    pub fn longest_word(words: Vec<String>) -> String {
        let mut trie = Trie::new();
        for w in &words {
            trie.insert(w);
        }

        let mut ans = String::new();
        for w in words {
            if trie.search(&w) && (ans.len() < w.len() || (ans.len() == w.len() && w < ans)) {
                ans = w;
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {string[]} words
 * @return {string}
 */
var longestWord = function (words) {
    const trie = new Trie();
    for (const w of words) {
        trie.insert(w);
    }

    let ans = '';
    for (const w of words) {
        if (trie.search(w) && (ans.length < w.length || (ans.length === w.length && w < ans))) {
            ans = w;
        }
    }
    return ans;
};

class Trie {
    constructor() {
        this.children = Array(26).fill(null);
        this.isEnd = false;
    }

    insert(w) {
        let node = this;
        for (let i = 0; i < w.length; i++) {
            const idx = w.charCodeAt(i) - 'a'.charCodeAt(0);
            if (node.children[idx] === null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    search(w) {
        let node = this;
        for (let i = 0; i < w.length; i++) {
            const idx = w.charCodeAt(i) - 'a'.charCodeAt(0);
            if (node.children[idx] === null || !node.children[idx].isEnd) {
                return false;
            }
            node = node.children[idx];
        }
        return true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
