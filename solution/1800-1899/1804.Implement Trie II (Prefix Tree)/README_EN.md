# [1804. Implement Trie II (Prefix Tree)](https://leetcode.com/problems/implement-trie-ii-prefix-tree)

[中文文档](/solution/1800-1899/1804.Implement%20Trie%20II%20%28Prefix%20Tree%29/README.md)

## Description

<p>A <a href="https://en.wikipedia.org/wiki/Trie" target="_blank"><strong>trie</strong></a> (pronounced as &quot;try&quot;) or <strong>prefix tree</strong> is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.</p>

<p>Implement the Trie class:</p>

<ul>
	<li><code>Trie()</code> Initializes the trie object.</li>
	<li><code>void insert(String word)</code> Inserts the string <code>word</code> into the trie.</li>
	<li><code>int countWordsEqualTo(String word)</code> Returns the number of instances of the string <code>word</code> in the trie.</li>
	<li><code>int countWordsStartingWith(String prefix)</code> Returns the number of strings in the trie that have the string <code>prefix</code> as a prefix.</li>
	<li><code>void erase(String word)</code> Erases the string <code>word</code> from the trie.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Trie&quot;, &quot;insert&quot;, &quot;insert&quot;, &quot;countWordsEqualTo&quot;, &quot;countWordsStartingWith&quot;, &quot;erase&quot;, &quot;countWordsEqualTo&quot;, &quot;countWordsStartingWith&quot;, &quot;erase&quot;, &quot;countWordsStartingWith&quot;]
[[], [&quot;apple&quot;], [&quot;apple&quot;], [&quot;apple&quot;], [&quot;app&quot;], [&quot;apple&quot;], [&quot;apple&quot;], [&quot;app&quot;], [&quot;apple&quot;], [&quot;app&quot;]]
<strong>Output</strong>
[null, null, null, 2, 2, null, 1, 1, null, 0]

<strong>Explanation</strong>
Trie trie = new Trie();
trie.insert(&quot;apple&quot;);               // Inserts &quot;apple&quot;.
trie.insert(&quot;apple&quot;);               // Inserts another &quot;apple&quot;.
trie.countWordsEqualTo(&quot;apple&quot;);    // There are two instances of &quot;apple&quot; so return 2.
trie.countWordsStartingWith(&quot;app&quot;); // &quot;app&quot; is a prefix of &quot;apple&quot; so return 2.
trie.erase(&quot;apple&quot;);                // Erases one &quot;apple&quot;.
trie.countWordsEqualTo(&quot;apple&quot;);    // Now there is only one instance of &quot;apple&quot; so return 1.
trie.countWordsStartingWith(&quot;app&quot;); // return 1
trie.erase(&quot;apple&quot;);                // Erases &quot;apple&quot;. Now the trie is empty.
trie.countWordsStartingWith(&quot;app&quot;); // return 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length, prefix.length &lt;= 2000</code></li>
	<li><code>word</code> and <code>prefix</code> consist only of lowercase English letters.</li>
	<li>At most <code>3 * 10<sup>4</sup></code> calls <strong>in total</strong> will be made to <code>insert</code>, <code>countWordsEqualTo</code>, <code>countWordsStartingWith</code>, and <code>erase</code>.</li>
	<li>It is guaranteed that for any function call to <code>erase</code>, the string <code>word</code> will exist in the trie.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.v = self.pv = 0

    def insert(self, word: str) -> None:
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            node.pv += 1
        node.v += 1

    def countWordsEqualTo(self, word: str) -> int:
        node = self.search(word)
        return 0 if node is None else node.v

    def countWordsStartingWith(self, prefix: str) -> int:
        node = self.search(prefix)
        return 0 if node is None else node.pv

    def erase(self, word: str) -> None:
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            node = node.children[idx]
            node.pv -= 1
        node.v -= 1

    def search(self, word):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                return None
            node = node.children[idx]
        return node


# Your Trie object will be instantiated and called as such:
# obj = Trie()
# obj.insert(word)
# param_2 = obj.countWordsEqualTo(word)
# param_3 = obj.countWordsStartingWith(prefix)
# obj.erase(word)
```

### **Java**

```java
class Trie {
    private Trie[] children = new Trie[26];
    private int v;
    private int pv;

    public Trie() {
    }

    public void insert(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
            ++node.pv;
        }
        ++node.v;
    }

    public int countWordsEqualTo(String word) {
        Trie node = search(word);
        return node == null ? 0 : node.v;
    }

    public int countWordsStartingWith(String prefix) {
        Trie node = search(prefix);
        return node == null ? 0 : node.pv;
    }

    public void erase(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            node = node.children[c];
            --node.pv;
        }
        --node.v;
    }

    private Trie search(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return null;
            }
            node = node.children[c];
        }
        return node;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * int param_2 = obj.countWordsEqualTo(word);
 * int param_3 = obj.countWordsStartingWith(prefix);
 * obj.erase(word);
 */
```

### **C++**

```cpp
class Trie {
public:
    Trie()
        : children(26)
        , v(0)
        , pv(0) {
    }

    void insert(string word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            if (!node->children[c]) {
                node->children[c] = new Trie();
            }
            node = node->children[c];
            ++node->pv;
        }
        ++node->v;
    }

    int countWordsEqualTo(string word) {
        Trie* node = search(word);
        return node ? node->v : 0;
    }

    int countWordsStartingWith(string prefix) {
        Trie* node = search(prefix);
        return node ? node->pv : 0;
    }

    void erase(string word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            node = node->children[c];
            --node->pv;
        }
        --node->v;
    }

private:
    vector<Trie*> children;
    int v, pv;

    Trie* search(string& word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            if (!node->children[c]) {
                return nullptr;
            }
            node = node->children[c];
        }
        return node;
    }
};

/**
 * Your Trie object will be instantiated and called as such:
 * Trie* obj = new Trie();
 * obj->insert(word);
 * int param_2 = obj->countWordsEqualTo(word);
 * int param_3 = obj->countWordsStartingWith(prefix);
 * obj->erase(word);
 */
```

### **Go**

```go
type Trie struct {
	children [26]*Trie
	v        int
	pv       int
}

func Constructor() (_ Trie) { return }

func (this *Trie) Insert(word string) {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = &Trie{}
		}
		node = node.children[c]
		node.pv++
	}
	node.v++
}

func (this *Trie) CountWordsEqualTo(word string) int {
	node := this.search(word)
	if node == nil {
		return 0
	}
	return node.v
}

func (this *Trie) CountWordsStartingWith(prefix string) int {
	node := this.search(prefix)
	if node == nil {
		return 0
	}
	return node.pv
}

func (this *Trie) Erase(word string) {
	node := this
	for _, c := range word {
		c -= 'a'
		node = node.children[c]
		node.pv--
	}
	node.v--
}

func (this *Trie) search(word string) *Trie {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			return nil
		}
		node = node.children[c]
	}
	return node
}

/**
 * Your Trie object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Insert(word);
 * param_2 := obj.CountWordsEqualTo(word);
 * param_3 := obj.CountWordsStartingWith(prefix);
 * obj.Erase(word);
 */
```

### **...**

```

```

<!-- tabs:end -->
