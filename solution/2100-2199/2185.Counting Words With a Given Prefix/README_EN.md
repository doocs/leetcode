# [2185. Counting Words With a Given Prefix](https://leetcode.com/problems/counting-words-with-a-given-prefix)

[中文文档](/solution/2100-2199/2185.Counting%20Words%20With%20a%20Given%20Prefix/README.md)

## Description

<p>You are given an array of strings <code>words</code> and a string <code>pref</code>.</p>

<p>Return <em>the number of strings in </em><code>words</code><em> that contain </em><code>pref</code><em> as a <strong>prefix</strong></em>.</p>

<p>A <strong>prefix</strong> of a string <code>s</code> is any leading contiguous substring of <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;pay&quot;,&quot;<strong><u>at</u></strong>tention&quot;,&quot;practice&quot;,&quot;<u><strong>at</strong></u>tend&quot;], <code>pref </code>= &quot;at&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The 2 strings that contain &quot;at&quot; as a prefix are: &quot;<u><strong>at</strong></u>tention&quot; and &quot;<u><strong>at</strong></u>tend&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;leetcode&quot;,&quot;win&quot;,&quot;loops&quot;,&quot;success&quot;], <code>pref </code>= &quot;code&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no strings that contain &quot;code&quot; as a prefix.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length, pref.length &lt;= 100</code></li>
	<li><code>words[i]</code> and <code>pref</code> consist of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def prefixCount(self, words: List[str], pref: str) -> int:
        return sum(w.startswith(pref) for w in words)
```

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.cnt = 0

    def insert(self, w):
        node = self
        for c in w:
            i = ord(c) - ord('a')
            if node.children[i] is None:
                node.children[i] = Trie()
            node = node.children[i]
            node.cnt += 1

    def search(self, pref):
        node = self
        for c in pref:
            i = ord(c) - ord('a')
            if node.children[i] is None:
                return 0
            node = node.children[i]
        return node.cnt


class Solution:
    def prefixCount(self, words: List[str], pref: str) -> int:
        tree = Trie()
        for w in words:
            tree.insert(w)
        return tree.search(pref)
```

### **Java**

```java
class Solution {
    public int prefixCount(String[] words, String pref) {
        int ans = 0;
        for (String w : words) {
            if (w.startsWith(pref)) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```java
class Trie {
    private Trie[] children = new Trie[26];
    private int cnt;

    public void insert(String w) {
        Trie node = this;
        for (int i = 0; i < w.length(); ++i) {
            int j = w.charAt(i) - 'a';
            if (node.children[j] == null) {
                node.children[j] = new Trie();
            }
            node = node.children[j];
            ++node.cnt;
        }
    }

    public int search(String pref) {
        Trie node = this;
        for (int i = 0; i < pref.length(); ++i) {
            int j = pref.charAt(i) - 'a';
            if (node.children[j] == null) {
                return 0;
            }
            node = node.children[j];
        }
        return node.cnt;
    }
}

class Solution {
    public int prefixCount(String[] words, String pref) {
        Trie tree = new Trie();
        for (String w : words) {
            tree.insert(w);
        }
        return tree.search(pref);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int prefixCount(vector<string>& words, string pref) {
        int ans = 0;
        for (auto& w : words) ans += w.find(pref) == 0;
        return ans;
    }
};
```

```cpp
class Trie {
public:
    Trie(): children(26), cnt(0) {}

    void insert(string w) {
        Trie* node = this;
        for (auto& c : w) {
            int i = c - 'a';
            if (!node->children[i]) {
                node->children[i] = new Trie();
            }
            node = node->children[i];
            ++node->cnt;
        }
    }

    int search(string pref) {
        Trie* node = this;
        for (auto& c : pref) {
            int i = c - 'a';
            if (!node->children[i]) {
                return 0;
            }
            node = node->children[i];
        }
        return node->cnt;
    }

private:
    vector<Trie*> children;
    int cnt;
};

class Solution {
public:
    int prefixCount(vector<string>& words, string pref) {
        Trie* tree = new Trie();
        for (auto& w : words) {
            tree->insert(w);
        }
        return tree->search(pref);
    }
};
```

### **Go**

```go
func prefixCount(words []string, pref string) (ans int) {
	for _, w := range words {
		if strings.HasPrefix(w, pref) {
			ans++
		}
	}
	return
}
```

```go
type Trie struct {
	children [26]*Trie
	cnt      int
}

func newTrie() *Trie {
	return &Trie{}
}

func (this *Trie) insert(w string) {
	node := this
	for _, c := range w {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
		node.cnt++
	}
}

func (this *Trie) search(pref string) int {
	node := this
	for _, c := range pref {
		c -= 'a'
		if node.children[c] == nil {
			return 0
		}
		node = node.children[c]
	}
	return node.cnt
}

func prefixCount(words []string, pref string) int {
	tree := newTrie()
	for _, w := range words {
		tree.insert(w)
	}
	return tree.search(pref)
}
```

### **TypeScript**

```ts
function prefixCount(words: string[], pref: string): number {
    return words.reduce((r, s) => (r += s.startsWith(pref) ? 1 : 0), 0);
}
```

### **Rust**

```rust
impl Solution {
    pub fn prefix_count(words: Vec<String>, pref: String) -> i32 {
        words.iter().filter(|s| s.starts_with(&pref)).count() as i32
    }
}
```

### **C**

```c
int prefixCount(char **words, int wordsSize, char *pref) {
    int ans = 0;
    int n = strlen(pref);
    for (int i = 0; i < wordsSize; i++) {
        if (strncmp(words[i], pref, n) == 0) {
            ans++;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
