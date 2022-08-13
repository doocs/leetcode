# [面试题 17.15. 最长单词](https://leetcode.cn/problems/longest-word-lcci)

[English Version](/lcci/17.15.Longest%20Word/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一组单词<code>words</code>，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong> ["cat","banana","dog","nana","walk","walker","dogwalker"]
<strong>输出：</strong> "dogwalker"
<strong>解释：</strong> "dogwalker"可由"dog"和"walker"组成。
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>0 <= len(words) <= 100</code></li>
<li><code>1 <= len(words[i]) <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀树 + DFS**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

    def search(self, word):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                return False
            node = node.children[idx]
        return node.is_end


class Solution:
    def longestWord(self, words: List[str]) -> str:
        def cmp(a, b):
            if len(a) != len(b):
                return len(a) - len(b)
            return -1 if a > b else 1

        def dfs(w):
            return not w or any(
                trie.search(w[:i]) and dfs(w[i:]) for i in range(1, len(w) + 1)
            )

        words.sort(key=cmp_to_key(cmp))
        trie = Trie()
        ans = ""
        for w in words:
            if dfs(w):
                ans = w
            trie.insert(w)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd;

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

    boolean search(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return false;
            }
            node = node.children[c];
        }
        return node.isEnd;
    }
}

class Solution {
    private Trie trie = new Trie();

    public String longestWord(String[] words) {
        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            }
            return b.compareTo(a);
        });
        String ans = "";
        for (String w : words) {
            if (dfs(w)) {
                ans = w;
            }
            trie.insert(w);
        }
        return ans;
    }

    private boolean dfs(String w) {
        if ("".equals(w)) {
            return true;
        }
        for (int i = 1; i <= w.length(); ++i) {
            if (trie.search(w.substring(0, i)) && dfs(w.substring(i))) {
                return true;
            }
        }
        return false;
    }
}
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
		if node.children[c] == nil {
			return false
		}
		node = node.children[c]
	}
	return node.isEnd
}

func longestWord(words []string) string {
	sort.Slice(words, func(i, j int) bool {
		a, b := words[i], words[j]
		if len(a) != len(b) {
			return len(a) < len(b)
		}
		return a > b
	})
	trie := newTrie()
	var dfs func(string) bool
	dfs = func(w string) bool {
		if len(w) == 0 {
			return true
		}
		for i := 1; i <= len(w); i++ {
			if trie.search(w[:i]) && dfs(w[i:]) {
				return true
			}
		}
		return false
	}
	ans := ""
	for _, w := range words {
		if dfs(w) {
			ans = w
		}
		trie.insert(w)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
