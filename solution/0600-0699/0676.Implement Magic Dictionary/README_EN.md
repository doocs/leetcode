---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0676.Implement%20Magic%20Dictionary/README_EN.md
tags:
    - Depth-First Search
    - Design
    - Trie
    - Hash Table
    - String
---

<!-- problem:start -->

# [676. Implement Magic Dictionary](https://leetcode.com/problems/implement-magic-dictionary)

[中文文档](/solution/0600-0699/0676.Implement%20Magic%20Dictionary/README.md)

## Description

<!-- description:start -->

<p>Design a data structure that is initialized with a list of <strong>different</strong> words. Provided a string, you should determine if you can change exactly one character in this string to match any word in the data structure.</p>

<p>Implement the&nbsp;<code>MagicDictionary</code>&nbsp;class:</p>

<ul>
	<li><code>MagicDictionary()</code>&nbsp;Initializes the object.</li>
	<li><code>void buildDict(String[]&nbsp;dictionary)</code>&nbsp;Sets the data structure&nbsp;with an array of distinct strings <code>dictionary</code>.</li>
	<li><code>bool search(String searchWord)</code> Returns <code>true</code> if you can change <strong>exactly one character</strong> in <code>searchWord</code> to match any string in the data structure, otherwise returns <code>false</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MagicDictionary&quot;, &quot;buildDict&quot;, &quot;search&quot;, &quot;search&quot;, &quot;search&quot;, &quot;search&quot;]
[[], [[&quot;hello&quot;, &quot;leetcode&quot;]], [&quot;hello&quot;], [&quot;hhllo&quot;], [&quot;hell&quot;], [&quot;leetcoded&quot;]]
<strong>Output</strong>
[null, null, false, true, false, false]

<strong>Explanation</strong>
MagicDictionary magicDictionary = new MagicDictionary();
magicDictionary.buildDict([&quot;hello&quot;, &quot;leetcode&quot;]);
magicDictionary.search(&quot;hello&quot;); // return False
magicDictionary.search(&quot;hhllo&quot;); // We can change the second &#39;h&#39; to &#39;e&#39; to match &quot;hello&quot; so we return True
magicDictionary.search(&quot;hell&quot;); // return False
magicDictionary.search(&quot;leetcoded&quot;); // return False
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;dictionary.length &lt;= 100</code></li>
	<li><code>1 &lt;=&nbsp;dictionary[i].length &lt;= 100</code></li>
	<li><code>dictionary[i]</code> consists of only lower-case English letters.</li>
	<li>All the strings in&nbsp;<code>dictionary</code>&nbsp;are <strong>distinct</strong>.</li>
	<li><code>1 &lt;=&nbsp;searchWord.length &lt;= 100</code></li>
	<li><code>searchWord</code>&nbsp;consists of only lower-case English letters.</li>
	<li><code>buildDict</code>&nbsp;will be called only once before <code>search</code>.</li>
	<li>At most <code>100</code> calls will be made to <code>search</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Trie + DFS

We can use a trie to store all the words in the dictionary. For each word we search, we use depth-first search. Specifically, we start from the root of the trie. For the current letter we are traversing, we first check whether there is a child node that is the same as it. If there is, we continue to traverse downwards. Otherwise, we need to check whether there are remaining modification times. If not, it means that it cannot be matched, so we return false. If there are remaining modification times, we can try to modify the current letter and continue to traverse downwards. If the child node corresponding to the modified letter exists, it means that it can be matched, otherwise it means that it cannot be matched, so we return false. If we traverse to the end of the word and the number of modifications is exactly 1, it means that it can be matched, so we return true.

The time complexity is $O(n \times l + q \times l \times |\Sigma|)$, and the space complexity is $O(n \times l)$, where $n$ and $l$ are the number of words in the dictionary and the average length of the words, respectively, and $q$ is the number of words searched. In addition, $|\Sigma|$ represents the size of the character set. Here, the character set is lowercase English letters, so $|\Sigma|=26$.

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

### Solution 2

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
