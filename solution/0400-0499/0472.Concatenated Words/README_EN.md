# [472. Concatenated Words](https://leetcode.com/problems/concatenated-words)

[中文文档](/solution/0400-0499/0472.Concatenated%20Words/README.md)

## Description

<p>Given an array of strings <code>words</code> (<strong>without duplicates</strong>), return <em>all the <strong>concatenated words</strong> in the given list of</em> <code>words</code>.</p>

<p>A <strong>concatenated word</strong> is defined as a string that is comprised entirely of at least two shorter words in the given array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;cat&quot;,&quot;cats&quot;,&quot;catsdogcats&quot;,&quot;dog&quot;,&quot;dogcatsdog&quot;,&quot;hippopotamuses&quot;,&quot;rat&quot;,&quot;ratcatdogcat&quot;]
<strong>Output:</strong> [&quot;catsdogcats&quot;,&quot;dogcatsdog&quot;,&quot;ratcatdogcat&quot;]
<strong>Explanation:</strong> &quot;catsdogcats&quot; can be concatenated by &quot;cats&quot;, &quot;dog&quot; and &quot;cats&quot;; 
&quot;dogcatsdog&quot; can be concatenated by &quot;dog&quot;, &quot;cats&quot; and &quot;dog&quot;; 
&quot;ratcatdogcat&quot; can be concatenated by &quot;rat&quot;, &quot;cat&quot;, &quot;dog&quot; and &quot;cat&quot;.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;cat&quot;,&quot;dog&quot;,&quot;catdog&quot;]
<strong>Output:</strong> [&quot;catdog&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= words[i].length &lt;= 1000</code></li>
	<li><code>words[i]</code> consists of only lowercase English letters.</li>
	<li><code>0 &lt;= sum(words[i].length) &lt;= 6 * 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False


class Solution:
    def findAllConcatenatedWordsInADict(self, words: List[str]) -> List[str]:
        trie = Trie()
        words.sort(key=lambda x: len(x))
        ans = []

        def insert(word):
            node = trie
            for c in word:
                idx = ord(c) - ord('a')
                if node.children[idx] is None:
                    node.children[idx] = Trie()
                node = node.children[idx]
            node.is_end = True

        def dfs(word):
            node = trie
            if not word:
                return True
            for i, c in enumerate(word):
                idx = ord(c) - ord('a')
                node = node.children[idx]
                if node is None:
                    return False
                if node.is_end:
                    if dfs(word[i + 1:]):
                        return True
            return False

        for word in words:
            if not word:
                continue
            if dfs(word):
                ans.append(word)
            else:
                insert(word)
        return ans
```

### **Java**

```java
class Solution {
    private Trie trie;

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        List<String> ans = new ArrayList<>();
        trie = new Trie();
        for (String word : words) {
            if ("".equals(word)) {
                continue;
            }
            if (dfs(word, 0)) {
                ans.add(word);
            } else {
                insert(word);
            }
        }
        return ans;
    }

    private boolean dfs(String word, int u) {
        if (word.length() == u) {
            return true;
        }
        Trie node = trie;
        for (int i = u; i < word.length(); ++i) {
            int idx = word.charAt(i) - 'a';
            node = node.children[idx];
            if (node == null) {
                return false;
            }
            if (node.isEnd) {
                if (dfs(word, i + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void insert(String word) {
        Trie node = trie;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }
}

class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd;
}
```

### **C++**

```cpp
class Trie {
public:
    vector<Trie*> children;
    bool isEnd;
    Trie(): children(26), isEnd(false) {}
};

class Solution {
public:
    Trie* trie;

    vector<string> findAllConcatenatedWordsInADict(vector<string>& words) {
        sort(words.begin(), words.end(), [&](const string & a, const string & b){
            return a.size() < b.size();
        });
        vector<string> ans;
        trie = new Trie();
        for (auto& word : words)
        {
            if (word.size() == 0) continue;
            if (dfs(word, 0)) ans.push_back(word);
            else insert(word);
        }
        return ans;
    }

    bool dfs(string word, int u) {
        Trie* node = trie;
        if (u == word.size()) return true;
        for (int i = u; i < word.size(); ++i)
        {
            int idx = word[i] - 'a';
            node = node->children[idx];
            if (!node) return false;
            if (node->isEnd && dfs(word, i + 1)) return true;
        }
        return false;
    }

    void insert(string word) {
        Trie* node = trie;
        for (char c : word)
        {
            int idx = c - 'a';
            if (!node->children[idx]) node->children[idx] = new Trie();
            node = node->children[idx];
        }
        node->isEnd = true;
    }
};
```

### **Go**

```go
type trie struct {
	children [26]*trie
	isEnd    bool
}

func (root *trie) insert(word string) {
	node := root
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = &trie{}
		}
		node = node.children[c]
	}
	node.isEnd = true
}

func (root *trie) dfs(word string) bool {
	if word == "" {
		return true
	}
	node := root
	for i, c := range word {
		node = node.children[c-'a']
		if node == nil {
			return false
		}
		if node.isEnd && root.dfs(word[i+1:]) {
			return true
		}
	}
	return false
}

func findAllConcatenatedWordsInADict(words []string) (ans []string) {
	sort.Slice(words, func(i, j int) bool { return len(words[i]) < len(words[j]) })
	root := &trie{}
	for _, word := range words {
		if word == "" {
			continue
		}
		if root.dfs(word) {
			ans = append(ans, word)
		} else {
			root.insert(word)
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
