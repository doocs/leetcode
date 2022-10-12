# [425. Word Squares](https://leetcode.com/problems/word-squares)

[中文文档](/solution/0400-0499/0425.Word%20Squares/README.md)

## Description

<p>Given an array of <strong>unique</strong> strings <code>words</code>, return <em>all the </em><strong><a href="https://en.wikipedia.org/wiki/Word_square" target="_blank">word squares</a></strong><em> you can build from </em><code>words</code>. The same word from <code>words</code> can be used <strong>multiple times</strong>. You can return the answer in <strong>any order</strong>.</p>

<p>A sequence of strings forms a valid <strong>word square</strong> if the <code>k<sup>th</sup></code> row and column read the same string, where <code>0 &lt;= k &lt; max(numRows, numColumns)</code>.</p>

<ul>
	<li>For example, the word sequence <code>[&quot;ball&quot;,&quot;area&quot;,&quot;lead&quot;,&quot;lady&quot;]</code> forms a word square because each word reads the same both horizontally and vertically.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;area&quot;,&quot;lead&quot;,&quot;wall&quot;,&quot;lady&quot;,&quot;ball&quot;]
<strong>Output:</strong> [[&quot;ball&quot;,&quot;area&quot;,&quot;lead&quot;,&quot;lady&quot;],[&quot;wall&quot;,&quot;area&quot;,&quot;lead&quot;,&quot;lady&quot;]]
<strong>Explanation:</strong>
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abat&quot;,&quot;baba&quot;,&quot;atan&quot;,&quot;atal&quot;]
<strong>Output:</strong> [[&quot;baba&quot;,&quot;abat&quot;,&quot;baba&quot;,&quot;atal&quot;],[&quot;baba&quot;,&quot;abat&quot;,&quot;baba&quot;,&quot;atan&quot;]]
<strong>Explanation:</strong>
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 4</code></li>
	<li>All <code>words[i]</code> have the same length.</li>
	<li><code>words[i]</code> consists of only lowercase English letters.</li>
	<li>All <code>words[i]</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.v = []

    def insert(self, w, i):
        node = self
        for c in w:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            node.v.append(i)

    def search(self, w):
        node = self
        for c in w:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                return []
            node = node.children[idx]
        return node.v


class Solution:
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        def dfs(t):
            if len(t) == len(words[0]):
                ans.append(t[:])
                return
            idx = len(t)
            pref = [v[idx] for v in t]
            indexes = trie.search(''.join(pref))
            for i in indexes:
                t.append(words[i])
                dfs(t)
                t.pop()

        trie = Trie()
        ans = []
        for i, w in enumerate(words):
            trie.insert(w, i)
        for w in words:
            dfs([w])
        return ans
```

### **Java**

```java
class Trie {
    Trie[] children = new Trie[26];
    List<Integer> v = new ArrayList<>();

    void insert(String word, int i) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
            node.v.add(i);
        }
    }

    List<Integer> search(String pref) {
        Trie node = this;
        for (char c : pref.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return Collections.emptyList();
            }
            node = node.children[c];
        }
        return node.v;
    }
}

class Solution {
    private Trie trie = new Trie();
    private String[] words;
    private List<List<String>> ans = new ArrayList<>();

    public List<List<String>> wordSquares(String[] words) {
        this.words = words;
        for (int i = 0; i < words.length; ++i) {
            trie.insert(words[i], i);
        }

        List<String> t = new ArrayList<>();
        for (String w : words) {
            t.add(w);
            dfs(t);
            t.remove(t.size() - 1);
        }
        return ans;
    }

    private void dfs(List<String> t) {
        if (t.size() == words[0].length()) {
            ans.add(new ArrayList<>(t));
            return;
        }
        int idx = t.size();
        StringBuilder pref = new StringBuilder();
        for (String x : t) {
            pref.append(x.charAt(idx));
        }
        List<Integer> indexes = trie.search(pref.toString());
        for (int i : indexes) {
            t.add(words[i]);
            dfs(t);
            t.remove(t.size() - 1);
        }
    }
}
```

### **Go**

```go
type Trie struct {
	children [26]*Trie
	v        []int
}

func newTrie() *Trie {
	return &Trie{}
}
func (this *Trie) insert(word string, i int) {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
		node.v = append(node.v, i)
	}
}
func (this *Trie) search(word string) []int {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			return []int{}
		}
		node = node.children[c]
	}
	return node.v
}

func wordSquares(words []string) [][]string {
	trie := newTrie()
	for i, w := range words {
		trie.insert(w, i)
	}
	ans := [][]string{}
	var dfs func([]string)
	dfs = func(t []string) {
		if len(t) == len(words[0]) {
			cp := make([]string, len(t))
			copy(cp, t)
			ans = append(ans, cp)
			return
		}
		idx := len(t)
		pref := []byte{}
		for _, v := range t {
			pref = append(pref, v[idx])
		}
		indexes := trie.search(string(pref))
		for _, i := range indexes {
			t = append(t, words[i])
			dfs(t)
			t = t[:len(t)-1]
		}
	}
	for _, w := range words {
		dfs([]string{w})
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
