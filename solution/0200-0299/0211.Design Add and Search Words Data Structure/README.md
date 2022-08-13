# [211. 添加与搜索单词 - 数据结构设计](https://leetcode.cn/problems/design-add-and-search-words-data-structure)

[English Version](/solution/0200-0299/0211.Design%20Add%20and%20Search%20Words%20Data%20Structure/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。</p>

<p>实现词典类 <code>WordDictionary</code> ：</p>

<ul>
	<li><code>WordDictionary()</code> 初始化词典对象</li>
	<li><code>void addWord(word)</code> 将 <code>word</code> 添加到数据结构中，之后可以对它进行匹配</li>
	<li><code>bool search(word)</code> 如果数据结构中存在字符串与&nbsp;<code>word</code> 匹配，则返回 <code>true</code> ；否则，返回&nbsp; <code>false</code> 。<code>word</code> 中可能包含一些 <code>'.'</code> ，每个&nbsp;<code>.</code> 都可以表示任何一个字母。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
<strong>输出：</strong>
[null,null,null,null,false,true,true,true]

<strong>解释：</strong>
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // 返回 False
wordDictionary.search("bad"); // 返回 True
wordDictionary.search(".ad"); // 返回 True
wordDictionary.search("b.."); // 返回 True
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 25</code></li>
	<li><code>addWord</code> 中的 <code>word</code> 由小写英文字母组成</li>
	<li><code>search</code> 中的 <code>word</code> 由 '.' 或小写英文字母组成</li>
	<li>最多调用 <code>10<sup>4</sup></code> 次 <code>addWord</code> 和 <code>search</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“前缀树”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False


class WordDictionary:
    def __init__(self):
        self.trie = Trie()

    def addWord(self, word: str) -> None:
        node = self.trie
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True

    def search(self, word: str) -> bool:
        def search(word, node):
            for i in range(len(word)):
                c = word[i]
                idx = ord(c) - ord('a')
                if c != '.' and node.children[idx] is None:
                    return False
                if c == '.':
                    for child in node.children:
                        if child is not None and search(word[i + 1 :], child):
                            return True
                    return False
                node = node.children[idx]
            return node.is_end

        return search(word, self.trie)


# Your WordDictionary object will be instantiated and called as such:
# obj = WordDictionary()
# obj.addWord(word)
# param_2 = obj.search(word)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd;
}

class WordDictionary {
    private Trie trie;

    /** Initialize your data structure here. */
    public WordDictionary() {
        trie = new Trie();
    }

    public void addWord(String word) {
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

    public boolean search(String word) {
        return search(word, trie);
    }

    private boolean search(String word, Trie node) {
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            int idx = c - 'a';
            if (c != '.' && node.children[idx] == null) {
                return false;
            }
            if (c == '.') {
                for (Trie child : node.children) {
                    if (child != null && search(word.substring(i + 1), child)) {
                        return true;
                    }
                }
                return false;
            }
            node = node.children[idx];
        }
        return node.isEnd;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
```

### **Go**

```go
type WordDictionary struct {
	root *trie
}

func Constructor() WordDictionary {
	return WordDictionary{new(trie)}
}

func (this *WordDictionary) AddWord(word string) {
	this.root.insert(word)
}

func (this *WordDictionary) Search(word string) bool {
	n := len(word)

	var dfs func(int, *trie) bool
	dfs = func(i int, cur *trie) bool {
		if i == n {
			return cur.isEnd
		}
		c := word[i]
		if c != '.' {
			child := cur.children[c-'a']
			if child != nil && dfs(i+1, child) {
				return true
			}
		} else {
			for _, child := range cur.children {
				if child != nil && dfs(i+1, child) {
					return true
				}
			}
		}
		return false
	}

	return dfs(0, this.root)
}

type trie struct {
	children [26]*trie
	isEnd    bool
}

func (t *trie) insert(word string) {
	cur := t
	for _, c := range word {
		c -= 'a'
		if cur.children[c] == nil {
			cur.children[c] = new(trie)
		}
		cur = cur.children[c]
	}
	cur.isEnd = true
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AddWord(word);
 * param_2 := obj.Search(word);
 */
```

### **C++**

```cpp
class trie {
public:
    vector<trie*> children;
    bool is_end;

    trie() {
        children = vector<trie*>(26, nullptr);
        is_end = false;
    }

    void insert(const string& word) {
        trie* cur = this;
        for (char c : word) {
            c -= 'a';
            if (cur->children[c] == nullptr) {
                cur->children[c] = new trie;
            }
            cur = cur->children[c];
        }
        cur->is_end = true;
    }
};

class WordDictionary {
private:
    trie* root;

public:
    WordDictionary()
        : root(new trie) { }

    void addWord(string word) {
        root->insert(word);
    }

    bool search(string word) {
        return dfs(word, 0, root);
    }

private:
    bool dfs(const string& word, int i, trie* cur) {
        if (i == word.size()) {
            return cur->is_end;
        }
        char c = word[i];
        if (c != '.') {
            trie* child = cur->children[c - 'a'];
            if (child != nullptr && dfs(word, i + 1, child)) {
                return true;
            }
        } else {
            for (trie* child : cur->children) {
                if (child != nullptr && dfs(word, i + 1, child)) {
                    return true;
                }
            }
        }
        return false;
    }
};

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary* obj = new WordDictionary();
 * obj->addWord(word);
 * bool param_2 = obj->search(word);
 */
```

### **...**

```

```

<!-- tabs:end -->
