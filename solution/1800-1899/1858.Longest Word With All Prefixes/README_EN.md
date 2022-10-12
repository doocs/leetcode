# [1858. Longest Word With All Prefixes](https://leetcode.com/problems/longest-word-with-all-prefixes)

[中文文档](/solution/1800-1899/1858.Longest%20Word%20With%20All%20Prefixes/README.md)

## Description

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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False

    def insert(self, w):
        node = self
        for c in w:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True

    def search(self, w):
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
            if ans and (len(ans) > len(w) or (len(ans) == len(w) and ans < w)):
                continue
            if trie.search(w):
                ans = w
        return ans
```

### **Java**

```java
class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd;

    void insert(String w) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.isEnd = true;
    }

    boolean search(String w) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            c -= 'a';
            node = node.children[c];
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
            if (!"".equals(ans)
                && (ans.length() > w.length()
                    || (ans.length() == w.length() && ans.compareTo(w) < 0))) {
                continue;
            }
            if (trie.search(w)) {
                ans = w;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Trie {
private:
    vector<Trie*> children;
    bool isEnd;

public:
    Trie()
        : children(26)
        , isEnd(false) { }

    void insert(string word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            if (!node->children[c]) node->children[c] = new Trie();
            node = node->children[c];
        }
        node->isEnd = true;
    }

    bool search(string word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            node = node->children[c];
            if (!node->isEnd) return false;
        }
        return true;
    }
};

class Solution {
public:
    string longestWord(vector<string>& words) {
        Trie* trie = new Trie();
        for (auto w : words) trie->insert(w);
        string ans = "";
        for (auto w : words) {
            if (ans != "" && (ans.size() > w.size() || (ans.size() == w.size() && ans < w))) continue;
            if (trie->search(w)) ans = w;
        }
        return ans;
    }
};
```

### **Go**

```go
type Trie struct {
	children [26]*Trie
	isEnd    bool
}

func newTrie() *Trie {
	return &Trie{}
}
func (this *Trie) insert(word string) {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
	}
	node.isEnd = true
}
func (this *Trie) search(word string) bool {
	node := this
	for _, c := range word {
		c -= 'a'
		node = node.children[c]
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
		if ans != "" && (len(ans) > len(w) || (len(ans) == len(w) && ans < w)) {
			continue
		}
		if trie.search(w) {
			ans = w
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
