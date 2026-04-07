---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0648.Replace%20Words/README_EN.md
tags:
    - Trie
    - Array
    - Hash Table
    - String
---

<!-- problem:start -->

# [648. Replace Words](https://leetcode.com/problems/replace-words)

[中文文档](/solution/0600-0699/0648.Replace%20Words/README.md)

## Description

<!-- description:start -->

<p>In English, we have a concept called <strong>root</strong>, which can be followed by some other word to form another longer word - let&#39;s call this word <strong>derivative</strong>. For example, when the <strong>root</strong> <code>&quot;help&quot;</code> is followed by the word <code>&quot;ful&quot;</code>, we can form a derivative <code>&quot;helpful&quot;</code>.</p>

<p>Given a <code>dictionary</code> consisting of many <strong>roots</strong> and a <code>sentence</code> consisting of words separated by spaces, replace all the derivatives in the sentence with the <strong>root</strong> forming it. If a derivative can be replaced by more than one <strong>root</strong>, replace it with the <strong>root</strong> that has <strong>the shortest length</strong>.</p>

<p>Return <em>the <code>sentence</code></em> after the replacement.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> dictionary = [&quot;cat&quot;,&quot;bat&quot;,&quot;rat&quot;], sentence = &quot;the cattle was rattled by the battery&quot;
<strong>Output:</strong> &quot;the cat was rat by the bat&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> dictionary = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;], sentence = &quot;aadsfasf absbs bbab cadsfafs&quot;
<strong>Output:</strong> &quot;a a b c&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= dictionary.length &lt;= 1000</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 100</code></li>
	<li><code>dictionary[i]</code> consists of only lower-case letters.</li>
	<li><code>1 &lt;= sentence.length &lt;= 10<sup>6</sup></code></li>
	<li><code>sentence</code> consists of only lower-case letters and spaces.</li>
	<li>The number of words in <code>sentence</code> is in the range <code>[1, 1000]</code></li>
	<li>The length of each word in <code>sentence</code> is in the range <code>[1, 1000]</code></li>
	<li>Every two consecutive words in <code>sentence</code> will be separated by exactly one space.</li>
	<li><code>sentence</code> does not have leading or trailing spaces.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Trie

We can use a trie to store all the roots in the dictionary. Define the trie node class $\text{Trie}$, which contains an array $\text{children}$ of length $26$ to store child nodes, and a boolean variable $\text{is\_end}$ to mark whether it is a complete root.

For each root, we insert it into the trie. For each word in the sentence, we search for its shortest root in the trie. If found, we replace the word; otherwise, we keep it unchanged.

The time complexity is $O(n \times |w| + L)$, and the space complexity is $O(n \times |w|)$, where $n$ and $|w|$ are the number of roots in the dictionary and the average length, respectively, and $L$ is the total length of words in the sentence.

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
