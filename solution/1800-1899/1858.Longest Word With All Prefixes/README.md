# [1858. 包含所有前缀的最长单词](https://leetcode.cn/problems/longest-word-with-all-prefixes)

[English Version](/solution/1800-1899/1858.Longest%20Word%20With%20All%20Prefixes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串数组 <code>words</code>，找出 <code>words</code> 中<strong>所有的前缀</strong>都在 <code>words</code> 中的<strong>最长</strong>字符串。</p>

<ul>
	<li>例如，令 <code>words = ["a", "app", "ap"]</code>。字符串 <code>"app"</code> 含前缀 <code>"ap"</code> 和 <code>"a"</code> ，都在 <code>words</code> 中。</li>
</ul>

<p>返回符合上述要求的字符串。如果存在多个（符合条件的）相同长度的字符串，返回字典序中最小的字符串，如果这样的字符串不存在，返回<em> </em><code>""</code>。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre><b>输入：</b> words = ["k","ki","kir","kira", "kiran"]
<b>输出：</b> "kiran"
<b>解释：</b> "kiran" 含前缀 "kira"、 "kir"、 "ki"、 和 "k"，这些前缀都出现在 words 中。
</pre>

<p><strong>示例 2:</strong></p>

<pre><b>输入：</b> words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
<b>输出： </b>"apple"
<b>解释：</b> "apple" 和 "apply" 都在 words 中含有各自的所有前缀。
然而，"apple" 在字典序中更小，所以我们返回之。
</pre>

<p><strong>示例 3:</strong></p>

<pre><b>输入：</b> words = ["abc", "bc", "ab", "qwe"]
<b>输出：</b> ""
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= sum(words[i].length) &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀树**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
            if (!"".equals(ans) && (ans.length() > w.length() || (ans.length() == w.length() && ans.compareTo(w) < 0))) {
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
