---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0648.Replace%20Words/README.md
tags:
    - 字典树
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [648. 单词替换](https://leetcode.cn/problems/replace-words)

[English Version](/solution/0600-0699/0648.Replace%20Words/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在英语中，我们有一个叫做&nbsp;<strong>词根</strong>(root) 的概念，可以词根&nbsp;<strong>后面&nbsp;</strong>添加其他一些词组成另一个较长的单词——我们称这个词为 <strong>衍生词</strong>&nbsp;(<strong>derivative</strong>)。例如，词根&nbsp;<code>help</code>，跟随着 <strong>继承</strong>词&nbsp;<code>"ful"</code>，可以形成新的单词&nbsp;<code>"helpful"</code>。</p>

<p>现在，给定一个由许多&nbsp;<strong>词根&nbsp;</strong>组成的词典 <code>dictionary</code> 和一个用空格分隔单词形成的句子 <code>sentence</code>。你需要将句子中的所有&nbsp;<strong>衍生词&nbsp;</strong>用&nbsp;<strong>词根&nbsp;</strong>替换掉。如果&nbsp;<strong>衍生词&nbsp;</strong>有许多可以形成它的&nbsp;<strong>词根</strong>，则用&nbsp;<strong>最短&nbsp;</strong>的 <strong>词根</strong> 替换它。</p>

<p>你需要输出替换之后的句子。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
<strong>输出：</strong>"the cat was rat by the bat"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
<strong>输出：</strong>"a a b c"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= dictionary.length&nbsp;&lt;= 1000</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 100</code></li>
	<li><code>dictionary[i]</code>&nbsp;仅由小写字母组成。</li>
	<li><code>1 &lt;= sentence.length &lt;= 10<sup>6</sup></code></li>
	<li><code>sentence</code>&nbsp;仅由小写字母和空格组成。</li>
	<li><code>sentence</code> 中单词的总量在范围 <code>[1, 1000]</code> 内。</li>
	<li><code>sentence</code> 中每个单词的长度在范围 <code>[1, 1000]</code> 内。</li>
	<li><code>sentence</code> 中单词之间由一个空格隔开。</li>
	<li><code>sentence</code>&nbsp;没有前导或尾随空格。</li>
</ul>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀树

我们可以使用前缀树来存储词典中的所有词根。定义前缀树节点类 $\text{Trie}$，其中包含一个长度为 $26$ 的数组 $\text{children}$ 来存储子节点，以及一个布尔变量 $\text{is\_end}$ 来标记是否为一个完整的词根。

对于每个词根，我们将其插入前缀树中。对于句子中的每个单词，我们在前缀树中搜索其最短的词根，如果找到了，则替换该单词，否则保持不变。

时间复杂度 $O(n \times |w| + L)$，空间复杂度 $O(n \times |w|)$，其中 $n$ 和 $|w|$ 分别是词典中词根的数量和平均长度，而 $L$ 是句子中单词的总长度。

<!-- tabs:start -->

#### Python3

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False

    def insert(self, w: str) -> None:
        node = self
        for c in w:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True

    def search(self, w: str) -> str:
        node = self
        for i, c in enumerate(w, 1):
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                return w
            node = node.children[idx]
            if node.is_end:
                return w[:i]
        return w


class Solution:
    def replaceWords(self, dictionary: List[str], sentence: str) -> str:
        trie = Trie()
        for w in dictionary:
            trie.insert(w)
        return " ".join(trie.search(w) for w in sentence.split())
```

#### Java

```java
class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd = false;

    void insert(String w) {
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

    String search(String w) {
        Trie node = this;
        for (int i = 0; i < w.length(); i++) {
            int idx = w.charAt(i) - 'a';
            if (node.children[idx] == null) {
                return w;
            }
            node = node.children[idx];
            if (node.isEnd) {
                return w.substring(0, i + 1);
            }
        }
        return w;
    }
}

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (String w : dictionary) {
            trie.insert(w);
        }

        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = trie.search(words[i]);
        }
        return String.join(" ", words);
    }
}
```

#### C++

```cpp
class Trie {
public:
    Trie* children[26]{};
    bool isEnd = false;

    void insert(const string& w) {
        Trie* node = this;
        for (char c : w) {
            int idx = c - 'a';
            if (!node->children[idx]) {
                node->children[idx] = new Trie();
            }
            node = node->children[idx];
        }
        node->isEnd = true;
    }

    string search(const string& w) {
        Trie* node = this;
        for (int i = 0; i < w.size(); ++i) {
            int idx = w[i] - 'a';
            if (!node->children[idx]) {
                return w;
            }
            node = node->children[idx];
            if (node->isEnd) {
                return w.substr(0, i + 1);
            }
        }
        return w;
    }
};

class Solution {
public:
    string replaceWords(vector<string>& dictionary, string sentence) {
        Trie trie;
        for (auto& w : dictionary) {
            trie.insert(w);
        }

        stringstream ss(sentence);
        string word, res;
        while (ss >> word) {
            if (!res.empty()) res += " ";
            res += trie.search(word);
        }
        return res;
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
	for _, c := range w {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = &Trie{}
		}
		node = node.children[idx]
	}
	node.isEnd = true
}

func (t *Trie) search(w string) string {
	node := t
	for i, c := range w {
		idx := c - 'a'
		if node.children[idx] == nil {
			return w
		}
		node = node.children[idx]
		if node.isEnd {
			return w[:i+1]
		}
	}
	return w
}

func replaceWords(dictionary []string, sentence string) string {
	trie := &Trie{}
	for _, w := range dictionary {
		trie.insert(w)
	}

	words := strings.Split(sentence, " ")
	for i, w := range words {
		words[i] = trie.search(w)
	}
	return strings.Join(words, " ")
}
```

#### TypeScript

```ts
class Trie {
    children: Array<Trie | null>;
    isEnd: boolean;

    constructor() {
        this.children = new Array(26).fill(null);
        this.isEnd = false;
    }

    insert(w: string): void {
        let node: Trie = this;
        for (const c of w) {
            const idx = c.charCodeAt(0) - 97;
            if (!node.children[idx]) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx]!;
        }
        node.isEnd = true;
    }

    search(w: string): string {
        let node: Trie = this;
        for (let i = 0; i < w.length; i++) {
            const idx = w.charCodeAt(i) - 97;
            if (!node.children[idx]) {
                return w;
            }
            node = node.children[idx]!;
            if (node.isEnd) {
                return w.slice(0, i + 1);
            }
        }
        return w;
    }
}

function replaceWords(dictionary: string[], sentence: string): string {
    const trie = new Trie();
    for (const w of dictionary) {
        trie.insert(w);
    }

    return sentence
        .split(' ')
        .map(w => trie.search(w))
        .join(' ');
}
```

#### Rust

```rust
struct Trie {
    children: Vec<Option<Box<Trie>>>,
    is_end: bool,
}

impl Trie {
    fn new() -> Self {
        Self {
            children: (0..26).map(|_| None).collect(),
            is_end: false,
        }
    }

    fn insert(&mut self, w: String) {
        let mut node = self;
        for c in w.chars() {
            let idx = (c as u8 - b'a') as usize;
            node = node.children[idx].get_or_insert(Box::new(Trie::new()));
        }
        node.is_end = true;
    }

    fn search(&self, w: &str) -> String {
        let mut node = self;
        for (i, c) in w.chars().enumerate() {
            let idx = (c as u8 - b'a') as usize;
            if node.children[idx].is_none() {
                return w.to_string();
            }
            node = node.children[idx].as_ref().unwrap();
            if node.is_end {
                return w[..i + 1].to_string();
            }
        }
        w.to_string()
    }
}

impl Solution {
    pub fn replace_words(dictionary: Vec<String>, sentence: String) -> String {
        let mut trie = Trie::new();
        for w in dictionary {
            trie.insert(w);
        }

        sentence
            .split_whitespace()
            .map(|w| trie.search(w))
            .collect::<Vec<_>>()
            .join(" ")
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
