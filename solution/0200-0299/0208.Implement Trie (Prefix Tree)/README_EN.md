# [208. Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree)

[中文文档](/solution/0200-0299/0208.Implement%20Trie%20%28Prefix%20Tree%29/README.md)

<!-- tags:Design,Trie,Hash Table,String -->

## Description

<p>A <a href="https://en.wikipedia.org/wiki/Trie" target="_blank"><strong>trie</strong></a> (pronounced as &quot;try&quot;) or <strong>prefix tree</strong> is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.</p>

<p>Implement the Trie class:</p>

<ul>
	<li><code>Trie()</code> Initializes the trie object.</li>
	<li><code>void insert(String word)</code> Inserts the string <code>word</code> into the trie.</li>
	<li><code>boolean search(String word)</code> Returns <code>true</code> if the string <code>word</code> is in the trie (i.e., was inserted before), and <code>false</code> otherwise.</li>
	<li><code>boolean startsWith(String prefix)</code> Returns <code>true</code> if there is a previously inserted string <code>word</code> that has the prefix <code>prefix</code>, and <code>false</code> otherwise.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Trie&quot;, &quot;insert&quot;, &quot;search&quot;, &quot;search&quot;, &quot;startsWith&quot;, &quot;insert&quot;, &quot;search&quot;]
[[], [&quot;apple&quot;], [&quot;apple&quot;], [&quot;app&quot;], [&quot;app&quot;], [&quot;app&quot;], [&quot;app&quot;]]
<strong>Output</strong>
[null, null, true, false, true, null, true]

<strong>Explanation</strong>
Trie trie = new Trie();
trie.insert(&quot;apple&quot;);
trie.search(&quot;apple&quot;);   // return True
trie.search(&quot;app&quot;);     // return False
trie.startsWith(&quot;app&quot;); // return True
trie.insert(&quot;app&quot;);
trie.search(&quot;app&quot;);     // return True
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length, prefix.length &lt;= 2000</code></li>
	<li><code>word</code> and <code>prefix</code> consist only of lowercase English letters.</li>
	<li>At most <code>3 * 10<sup>4</sup></code> calls <strong>in total</strong> will be made to <code>insert</code>, <code>search</code>, and <code>startsWith</code>.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False

    def insert(self, word: str) -> None:
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True

    def search(self, word: str) -> bool:
        node = self._search_prefix(word)
        return node is not None and node.is_end

    def startsWith(self, prefix: str) -> bool:
        node = self._search_prefix(prefix)
        return node is not None

    def _search_prefix(self, prefix: str):
        node = self
        for c in prefix:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                return None
            node = node.children[idx]
        return node


# Your Trie object will be instantiated and called as such:
# obj = Trie()
# obj.insert(word)
# param_2 = obj.search(word)
# param_3 = obj.startsWith(prefix)
```

```java
class Trie {
    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
    }

    public void insert(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    private Trie searchPrefix(String s) {
        Trie node = this;
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                return null;
            }
            node = node.children[idx];
        }
        return node;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
```

```cpp
class Trie {
private:
    vector<Trie*> children;
    bool isEnd;

    Trie* searchPrefix(string s) {
        Trie* node = this;
        for (char c : s) {
            int idx = c - 'a';
            if (!node->children[idx]) return nullptr;
            node = node->children[idx];
        }
        return node;
    }

public:
    Trie()
        : children(26)
        , isEnd(false) {}

    void insert(string word) {
        Trie* node = this;
        for (char c : word) {
            int idx = c - 'a';
            if (!node->children[idx]) node->children[idx] = new Trie();
            node = node->children[idx];
        }
        node->isEnd = true;
    }

    bool search(string word) {
        Trie* node = searchPrefix(word);
        return node != nullptr && node->isEnd;
    }

    bool startsWith(string prefix) {
        Trie* node = searchPrefix(prefix);
        return node != nullptr;
    }
};

/**
 * Your Trie object will be instantiated and called as such:
 * Trie* obj = new Trie();
 * obj->insert(word);
 * bool param_2 = obj->search(word);
 * bool param_3 = obj->startsWith(prefix);
 */
```

```go
type Trie struct {
	children [26]*Trie
	isEnd    bool
}

func Constructor() Trie {
	return Trie{}
}

func (this *Trie) Insert(word string) {
	node := this
	for _, c := range word {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = &Trie{}
		}
		node = node.children[idx]
	}
	node.isEnd = true
}

func (this *Trie) Search(word string) bool {
	node := this.SearchPrefix(word)
	return node != nil && node.isEnd
}

func (this *Trie) StartsWith(prefix string) bool {
	node := this.SearchPrefix(prefix)
	return node != nil
}

func (this *Trie) SearchPrefix(s string) *Trie {
	node := this
	for _, c := range s {
		idx := c - 'a'
		if node.children[idx] == nil {
			return nil
		}
		node = node.children[idx]
	}
	return node
}

/**
 * Your Trie object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Insert(word);
 * param_2 := obj.Search(word);
 * param_3 := obj.StartsWith(prefix);
 */
```

```ts
class TrieNode {
    children;
    isEnd;
    constructor() {
        this.children = new Array(26);
        this.isEnd = false;
    }
}

class Trie {
    root;
    constructor() {
        this.root = new TrieNode();
    }

    insert(word: string): void {
        let head = this.root;
        for (let char of word) {
            let index = char.charCodeAt(0) - 97;
            if (!head.children[index]) {
                head.children[index] = new TrieNode();
            }
            head = head.children[index];
        }
        head.isEnd = true;
    }

    search(word: string): boolean {
        let head = this.searchPrefix(word);
        return head != null && head.isEnd;
    }

    startsWith(prefix: string): boolean {
        return this.searchPrefix(prefix) != null;
    }

    private searchPrefix(prefix: string) {
        let head = this.root;
        for (let char of prefix) {
            let index = char.charCodeAt(0) - 97;
            if (!head.children[index]) return null;
            head = head.children[index];
        }
        return head;
    }
}
```

```rust
use std::{ rc::Rc, cell::RefCell, collections::HashMap };

struct TrieNode {
    pub val: Option<char>,
    pub flag: bool,
    pub child: HashMap<char, Rc<RefCell<TrieNode>>>,
}

impl TrieNode {
    fn new() -> Self {
        Self {
            val: None,
            flag: false,
            child: HashMap::new(),
        }
    }

    fn new_with_val(val: char) -> Self {
        Self {
            val: Some(val),
            flag: false,
            child: HashMap::new(),
        }
    }
}

struct Trie {
    root: Rc<RefCell<TrieNode>>,
}

/// Your Trie object will be instantiated and called as such:
/// let obj = Trie::new();
/// obj.insert(word);
/// let ret_2: bool = obj.search(word);
/// let ret_3: bool = obj.starts_with(prefix);
impl Trie {
    fn new() -> Self {
        Self {
            root: Rc::new(RefCell::new(TrieNode::new())),
        }
    }

    fn insert(&self, word: String) {
        let char_vec: Vec<char> = word.chars().collect();
        // Get the clone of current root node
        let mut root = Rc::clone(&self.root);
        for c in &char_vec {
            if !root.borrow().child.contains_key(c) {
                // We need to manually create the entry
                root.borrow_mut().child.insert(*c, Rc::new(RefCell::new(TrieNode::new())));
            }
            // Get the child node
            let root_clone = Rc::clone(root.borrow().child.get(c).unwrap());
            root = root_clone;
        }
        {
            root.borrow_mut().flag = true;
        }
    }

    fn search(&self, word: String) -> bool {
        let char_vec: Vec<char> = word.chars().collect();
        // Get the clone of current root node
        let mut root = Rc::clone(&self.root);
        for c in &char_vec {
            if !root.borrow().child.contains_key(c) {
                return false;
            }
            // Get the child node
            let root_clone = Rc::clone(root.borrow().child.get(c).unwrap());
            root = root_clone;
        }
        let flag = root.borrow().flag;
        flag
    }

    fn starts_with(&self, prefix: String) -> bool {
        let char_vec: Vec<char> = prefix.chars().collect();
        // Get the clone of current root node
        let mut root = Rc::clone(&self.root);
        for c in &char_vec {
            if !root.borrow().child.contains_key(c) {
                return false;
            }
            // Get the child node
            let root_clone = Rc::clone(root.borrow().child.get(c).unwrap());
            root = root_clone;
        }
        true
    }
}
```

```js
/**
 * Initialize your data structure here.
 */
var Trie = function () {
    this.children = {};
};

/**
 * Inserts a word into the trie.
 * @param {string} word
 * @return {void}
 */
Trie.prototype.insert = function (word) {
    let node = this.children;
    for (let char of word) {
        if (!node[char]) {
            node[char] = {};
        }
        node = node[char];
    }
    node.isEnd = true;
};

/**
 * Returns if the word is in the trie.
 * @param {string} word
 * @return {boolean}
 */
Trie.prototype.search = function (word) {
    let node = this.searchPrefix(word);
    return node != undefined && node.isEnd != undefined;
};

Trie.prototype.searchPrefix = function (prefix) {
    let node = this.children;
    for (let char of prefix) {
        if (!node[char]) return false;
        node = node[char];
    }
    return node;
};

/**
 * Returns if there is any word in the trie that starts with the given prefix.
 * @param {string} prefix
 * @return {boolean}
 */
Trie.prototype.startsWith = function (prefix) {
    return this.searchPrefix(prefix);
};

/**
 * Your Trie object will be instantiated and called as such:
 * var obj = new Trie()
 * obj.insert(word)
 * var param_2 = obj.search(word)
 * var param_3 = obj.startsWith(prefix)
 */
```

```cs
public class Trie {
    bool isEnd;
    Trie[] children = new Trie[26];

    public Trie() {

    }

    public void Insert(string word) {
        Trie node = this;
        foreach (var c in word)
        {
            var idx = c - 'a';
            node.children[idx] ??= new Trie();
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public bool Search(string word) {
        Trie node = SearchPrefix(word);
        return node != null && node.isEnd;
    }

    public bool StartsWith(string prefix) {
        Trie node = SearchPrefix(prefix);
        return node != null;
    }

    private Trie SearchPrefix(string s) {
        Trie node = this;
        foreach (var c in s)
        {
            var idx = c - 'a';
            if (node.children[idx] == null)
            {
                return null;
            }
            node = node.children[idx];
        }
        return node;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.Insert(word);
 * bool param_2 = obj.Search(word);
 * bool param_3 = obj.StartsWith(prefix);
 */
```

<!-- tabs:end -->

<!-- end -->
