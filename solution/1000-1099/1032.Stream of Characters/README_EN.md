# [1032. Stream of Characters](https://leetcode.com/problems/stream-of-characters)

[中文文档](/solution/1000-1099/1032.Stream%20of%20Characters/README.md)

## Description

<p>Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a given array of strings <code>words</code>.</p>

<p>For example, if <code>words = [&quot;abc&quot;, &quot;xyz&quot;]</code>&nbsp;and the stream added the four characters (one by one) <code>&#39;a&#39;</code>, <code>&#39;x&#39;</code>, <code>&#39;y&#39;</code>, and <code>&#39;z&#39;</code>, your algorithm should detect that the suffix <code>&quot;xyz&quot;</code> of the characters <code>&quot;axyz&quot;</code> matches <code>&quot;xyz&quot;</code> from <code>words</code>.</p>

<p>Implement the <code>StreamChecker</code> class:</p>

<ul>
	<li><code>StreamChecker(String[] words)</code> Initializes the object with the strings array <code>words</code>.</li>
	<li><code>boolean query(char letter)</code> Accepts a new character from the stream and returns <code>true</code> if any non-empty suffix from the stream forms a word that is in <code>words</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;StreamChecker&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;, &quot;query&quot;]
[[[&quot;cd&quot;, &quot;f&quot;, &quot;kl&quot;]], [&quot;a&quot;], [&quot;b&quot;], [&quot;c&quot;], [&quot;d&quot;], [&quot;e&quot;], [&quot;f&quot;], [&quot;g&quot;], [&quot;h&quot;], [&quot;i&quot;], [&quot;j&quot;], [&quot;k&quot;], [&quot;l&quot;]]
<strong>Output</strong>
[null, false, false, false, true, false, true, false, false, false, false, false, true]

<strong>Explanation</strong>
StreamChecker streamChecker = new StreamChecker([&quot;cd&quot;, &quot;f&quot;, &quot;kl&quot;]);
streamChecker.query(&quot;a&quot;); // return False
streamChecker.query(&quot;b&quot;); // return False
streamChecker.query(&quot;c&quot;); // return False
streamChecker.query(&quot;d&quot;); // return True, because &#39;cd&#39; is in the wordlist
streamChecker.query(&quot;e&quot;); // return False
streamChecker.query(&quot;f&quot;); // return True, because &#39;f&#39; is in the wordlist
streamChecker.query(&quot;g&quot;); // return False
streamChecker.query(&quot;h&quot;); // return False
streamChecker.query(&quot;i&quot;); // return False
streamChecker.query(&quot;j&quot;); // return False
streamChecker.query(&quot;k&quot;); // return False
streamChecker.query(&quot;l&quot;); // return True, because &#39;kl&#39; is in the wordlist
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 2000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 200</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
	<li><code>letter</code> is a lowercase English letter.</li>
	<li>At most <code>4 * 10<sup>4</sup></code> calls will be made to query.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False

    def insert(self, w: str):
        node = self
        for c in w[::-1]:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True

    def search(self, w: List[str]) -> bool:
        node = self
        for c in w[::-1]:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                return False
            node = node.children[idx]
            if node.is_end:
                return True
        return False


class StreamChecker:

    def __init__(self, words: List[str]):
        self.trie = Trie()
        self.cs = []
        self.limit = 201
        for w in words:
            self.trie.insert(w)

    def query(self, letter: str) -> bool:
        self.cs.append(letter)
        return self.trie.search(self.cs[-self.limit:])

# Your StreamChecker object will be instantiated and called as such:
# obj = StreamChecker(words)
# param_1 = obj.query(letter)
```

### **Java**

```java
class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd = false;

    public void insert(String w) {
        Trie node = this;
        for (int i = w.length() - 1; i >= 0; --i) {
            int idx = w.charAt(i) - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public boolean query(StringBuilder s) {
        Trie node = this;
        for (int i = s.length() - 1; i >= 0; --i) {
            int idx = s.charAt(i) - 'a';
            if (node.children[idx] == null) {
                return false;
            }
            node = node.children[idx];
            if (node.isEnd) {
                return true;
            }
        }
        return false;
    }
}

class StreamChecker {
    private StringBuilder sb = new StringBuilder();
    private Trie trie = new Trie();

    public StreamChecker(String[] words) {
        for (String w : words) {
            trie.insert(w);
        }
    }

    public boolean query(char letter) {
        sb.append(letter);
        return trie.query(sb);
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
```

### **C++**

```cpp
class Trie {
public:
    vector<Trie*> children;
    bool isEnd;

    Trie()
        : children(26)
        , isEnd(false) {}

    void insert(string& w) {
        Trie* node = this;
        reverse(w.begin(), w.end());
        for (char& c : w) {
            int idx = c - 'a';
            if (!node->children[idx]) {
                node->children[idx] = new Trie();
            }
            node = node->children[idx];
        }
        node->isEnd = true;
    }

    bool search(string& w) {
        Trie* node = this;
        for (int i = w.size() - 1; ~i; --i) {
            int idx = w[i] - 'a';
            if (!node->children[idx]) {
                return false;
            }
            node = node->children[idx];
            if (node->isEnd) {
                return true;
            }
        }
        return false;
    }
};

class StreamChecker {
public:
    Trie* trie = new Trie();
    string s;

    StreamChecker(vector<string>& words) {
        for (auto&& w : words) {
            trie->insert(w);
        }
    }

    bool query(char letter) {
        s += letter;
        return trie->search(s);
    }
};

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker* obj = new StreamChecker(words);
 * bool param_1 = obj->query(letter);
 */
```

### **Go**

```go
type Trie struct {
	children [26]*Trie
	isEnd    bool
}

func newTrie() Trie {
	return Trie{}
}

func (this *Trie) Insert(word string) {
	node := this
	for i := len(word) - 1; i >= 0; i-- {
		idx := word[i] - 'a'
		if node.children[idx] == nil {
			node.children[idx] = &Trie{}
		}
		node = node.children[idx]
	}
	node.isEnd = true
}

func (this *Trie) Search(word []byte) bool {
	node := this
	for i := len(word) - 1; i >= 0; i-- {
		idx := word[i] - 'a'
		if node.children[idx] == nil {
			return false
		}
		node = node.children[idx]
		if node.isEnd {
			return true
		}
	}
	return false
}

type StreamChecker struct {
	trie Trie
	s    []byte
}

func Constructor(words []string) StreamChecker {
	trie := newTrie()
	for _, w := range words {
		trie.Insert(w)
	}
	return StreamChecker{trie, []byte{}}
}

func (this *StreamChecker) Query(letter byte) bool {
	this.s = append(this.s, letter)
	return this.trie.Search(this.s)
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * obj := Constructor(words);
 * param_1 := obj.Query(letter);
 */
```

### **...**

```

```

<!-- tabs:end -->
