---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1858.Longest%20Word%20With%20All%20Prefixes/README_EN.md
tags:
    - Depth-First Search
    - Trie
---

<!-- problem:start -->

# [1858. Longest Word With All Prefixes 🔒](https://leetcode.com/problems/longest-word-with-all-prefixes)

[中文文档](/solution/1800-1899/1858.Longest%20Word%20With%20All%20Prefixes/README.md)

## Description

<!-- description:start -->

<p>Given an array of strings <code>words</code>, find the <strong>longest</strong> string in <code>words</code> such that <strong>every prefix</strong> of it is also in <code>words</code>.</p>

<ul>

    <li>For example, let <code>words = [&quot;a&quot;, &quot;app&quot;, &quot;ap&quot;]</code>. The string <code>&quot;app&quot;</code> has prefixes <code>&quot;ap&quot;</code> and <code>&quot;a&quot;</code>, all of which are in <code>words</code>.</li>

</ul>

<p>Return <em>the string described above. If there is more than one string with the same length, return the <strong>lexicographically smallest</strong> one, and if no string exists, return </em><code>&quot;&quot;</code>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> words = [&quot;k&quot;,&quot;ki&quot;,&quot;kir&quot;,&quot;kira&quot;, &quot;kiran&quot;]

<strong>Output:</strong> &quot;kiran&quot;

<strong>Explanation:</strong> &quot;kiran&quot; has prefixes &quot;kira&quot;, &quot;kir&quot;, &quot;ki&quot;, and &quot;k&quot;, and all of them appear in words.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> words = [&quot;a&quot;, &quot;banana&quot;, &quot;app&quot;, &quot;appl&quot;, &quot;ap&quot;, &quot;apply&quot;, &quot;apple&quot;]

<strong>Output:</strong> &quot;apple&quot;

<strong>Explanation:</strong> Both &quot;apple&quot; and &quot;apply&quot; have all their prefixes in words.

However, &quot;apple&quot; is lexicographically smaller, so we return that.

</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>

<strong>Input:</strong> words = [&quot;abc&quot;, &quot;bc&quot;, &quot;ab&quot;, &quot;qwe&quot;]

<strong>Output:</strong> &quot;&quot;

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code></li>

    <li><code>1 &lt;= words[i].length &lt;= 10<sup>5</sup></code></li>

    <li><code>1 &lt;= sum(words[i].length) &lt;= 10<sup>5</sup></code></li>

</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Trie

We define a trie, each node of the trie has two attributes, one is a `children` array of length $26$, and the other is a `isEnd` flag indicating whether it is the end of a word.

We traverse `words`, for each word `w`, we start traversing from the root node. If the current node's `children` array does not contain the first character of `w`, we create a new node, then continue to traverse the next character of `w`, until we finish traversing `w`, we mark the `isEnd` of the current node as `true`.

Next, we traverse `words`, for each word `w`, we start traversing from the root node. If the `isEnd` field of the current node's `children` array is `false`, it means that some prefix of `w` is not in `words`, we return `false`. Otherwise, we continue to traverse the next character of `w`, until we finish traversing `w`, we return `true`.

The time complexity is $O(\sum_{w \in words} |w|)$, and the space complexity is $O(\sum_{w \in words} |w|)$.

<!-- tabs:start -->

#### Python3

```python
class Trie:
    __slots__ = ["children", "is_end"]

    def __init__(self):
        self.children: List[Trie | None] = [None] * 26
        self.is_end: bool = False

    def insert(self, w: str) -> None:
        node = self
        for c in w:
            idx = ord(c) - ord("a")
            if not node.children[idx]:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True

    def search(self, w: str) -> bool:
        node = self
        for c in w:
            idx = ord(c) - ord("a")
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
            if (len(w) > len(ans) or len(w) == len(ans) and w < ans) and trie.search(w):
                ans = w
        return ans
```

#### Java

```java
class Trie {
    private Trie[] children = new Trie[26];
    private boolean isEnd;

    public Trie() {
    }

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
            node = node.children[idx];
            if (!node.isEnd) {
                return false;
            }
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
            if ((w.length() > ans.length() || (w.length() == ans.length() && w.compareTo(ans) < 0))
                && trie.search(w)) {
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
            int idx = c - 'a';
            if (!node->children[idx]) {
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
            node = node->children[idx];
            if (!node->isEnd) {
                return false;
            }
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
            if ((w.size() > ans.size() || (w.size() == ans.size() && w < ans)) && trie.search(w)) {
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

func newTrie() *Trie {
	return &Trie{}
}

func (t *Trie) insert(w string) {
	node := t
	for _, c := range w {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = newTrie()
		}
		node = node.children[idx]
	}
	node.isEnd = true
}

func (t *Trie) search(w string) bool {
	node := t
	for _, c := range w {
		idx := c - 'a'
		node = node.children[idx]
		if !node.isEnd {
			return false
		}
	}
	return true
}

func longestWord(words []string) string {
	trie := newTrie()
	for _, w := range words {
		trie.insert(w)
	}
	ans := ""
	for _, w := range words {
		if (len(w) > len(ans) || (len(w) == len(ans) && w < ans)) && trie.search(w) {
			ans = w
		}
	}
	return ans
}
```

#### TypeScript

```ts
class Trie {
    private children: (Trie | null)[] = Array(26).fill(null);
    private isEnd: boolean = false;

    insert(w: string): void {
        let node: Trie = this;
        for (const c of w) {
            const idx: number = c.charCodeAt(0) - 'a'.charCodeAt(0);
            if (!node.children[idx]) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx] as Trie;
        }
        node.isEnd = true;
    }

    search(w: string): boolean {
        let node: Trie = this;
        for (const c of w) {
            const idx: number = c.charCodeAt(0) - 'a'.charCodeAt(0);
            node = node.children[idx] as Trie;
            if (!node.isEnd) {
                return false;
            }
        }
        return true;
    }
}

function longestWord(words: string[]): string {
    const trie: Trie = new Trie();
    for (const w of words) {
        trie.insert(w);
    }
    let ans: string = '';
    for (const w of words) {
        if ((w.length > ans.length || (w.length === ans.length && w < ans)) && trie.search(w)) {
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
            node = node.children[idx].get_or_insert_with(|| Box::new(Trie::new()));
        }
        node.is_end = true;
    }

    fn search(&self, w: &str) -> bool {
        let mut node = self;
        for c in w.chars() {
            let idx = (c as usize) - ('a' as usize);
            if let Some(next_node) = &node.children[idx] {
                node = next_node.as_ref();
                if !node.is_end {
                    return false;
                }
            }
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
        for w in &words {
            if (w.len() > ans.len() || (w.len() == ans.len() && w < &ans)) && trie.search(w) {
                ans = w.clone();
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Trie {
    private Trie[] children = new Trie[26];
    private bool isEnd;

    public Trie() { }

    public void Insert(string w) {
        Trie node = this;
        foreach (char c in w.ToCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public bool Search(string w) {
        Trie node = this;
        foreach (char c in w.ToCharArray()) {
            int idx = c - 'a';
            node = node.children[idx];
            if (!node.isEnd) {
                return false;
            }
        }
        return true;
    }
}

public class Solution {
    public string LongestWord(string[] words) {
        Trie trie = new Trie();
        foreach (string w in words) {
            trie.Insert(w);
        }

        string ans = "";
        foreach (string w in words) {
            if ((w.Length > ans.Length || (w.Length == ans.Length && string.Compare(w, ans) < 0)) && trie.Search(w)) {
                ans = w;
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
