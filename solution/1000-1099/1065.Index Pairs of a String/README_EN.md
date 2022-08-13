# [1065. Index Pairs of a String](https://leetcode.com/problems/index-pairs-of-a-string)

[中文文档](/solution/1000-1099/1065.Index%20Pairs%20of%20a%20String/README.md)

## Description

<p>Given a string <code>text</code> and an array of strings <code>words</code>, return <em>an array of all index pairs </em><code>[i, j]</code><em> so that the substring </em><code>text[i...j]</code><em> is in <code>words</code></em>.</p>

<p>Return the pairs <code>[i, j]</code> in sorted order (i.e., sort them by their first coordinate, and in case of ties sort them by their second coordinate).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;thestoryofleetcodeandme&quot;, words = [&quot;story&quot;,&quot;fleet&quot;,&quot;leetcode&quot;]
<strong>Output:</strong> [[3,7],[9,13],[10,17]]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> text = &quot;ababa&quot;, words = [&quot;aba&quot;,&quot;ab&quot;]
<strong>Output:</strong> [[0,1],[0,2],[2,3],[2,4]]
<strong>Explanation:</strong> Notice that matches can overlap, see &quot;aba&quot; is found in [0,2] and [2,4].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 100</code></li>
	<li><code>1 &lt;= words.length &lt;= 20</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 50</code></li>
	<li><code>text</code> and <code>words[i]</code> consist of lowercase English letters.</li>
	<li>All the strings of <code>words</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def indexPairs(self, text: str, words: List[str]) -> List[List[int]]:
        words = set(words)
        n = len(text)
        return [
            [i, j] for i in range(n) for j in range(i, n) if text[i : j + 1] in words
        ]
```

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False

    def insert(self, word):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True


class Solution:
    def indexPairs(self, text: str, words: List[str]) -> List[List[int]]:
        trie = Trie()
        for w in words:
            trie.insert(w)
        n = len(text)
        ans = []
        for i in range(n):
            node = trie
            for j in range(i, n):
                idx = ord(text[j]) - ord('a')
                if node.children[idx] is None:
                    break
                node = node.children[idx]
                if node.is_end:
                    ans.append([i, j])
        return ans
```

### **Java**

```java
class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd = false;

    void insert(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.isEnd = true;
    }
}

class Solution {
    public int[][] indexPairs(String text, String[] words) {
        Trie trie = new Trie();
        for (String w : words) {
            trie.insert(w);
        }
        int n = text.length();
        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            Trie node = trie;
            for (int j = i; j < n; ++j) {
                int idx = text.charAt(j) - 'a';
                if (node.children[idx] == null) {
                    break;
                }
                node = node.children[idx];
                if (node.isEnd) {
                    ans.add(new int[]{i, j});
                }
            }
        }
        return ans.toArray(new int[ans.size()][2]);
    }
}
```

### **C++**

```cpp
class Trie {
public:
    vector<Trie*> children;
    bool isEnd = false;

    Trie() {
        children.resize(26);
    }

    void insert(string word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            if (!node->children[c]) node->children[c] = new Trie();
            node = node->children[c];
        }
        node->isEnd = true;
    }
};

class Solution {
public:
    vector<vector<int>> indexPairs(string text, vector<string>& words) {
        Trie* trie = new Trie();
        for (auto w : words) trie->insert(w);
        int n = text.size();
        vector<vector<int>> ans;
        for (int i = 0; i < n; ++i) {
            Trie* node = trie;
            for (int j = i; j < n; ++j) {
                int idx = text[j] - 'a';
                if (!node->children[idx]) break;
                node = node->children[idx];
                if (node->isEnd) ans.push_back({i, j});
            }
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
		idx := int(c - 'a')
		if node.children[idx] == nil {
			node.children[idx] = newTrie()
		}
		node = node.children[idx]
	}
	node.isEnd = true
}

func indexPairs(text string, words []string) [][]int {
	trie := newTrie()
	for _, w := range words {
		trie.insert(w)
	}
	n := len(text)
	var ans [][]int
	for i := range text {
		node := trie
		for j := i; j < n; j++ {
			idx := int(text[j] - 'a')
			if node.children[idx] == nil {
				break
			}
			node = node.children[idx]
			if node.isEnd {
				ans = append(ans, []int{i, j})
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
