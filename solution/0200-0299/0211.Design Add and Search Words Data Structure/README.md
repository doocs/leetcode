# [211. 添加与搜索单词 - 数据结构设计](https://leetcode-cn.com/problems/design-add-and-search-words-data-structure)

[English Version](/solution/0200-0299/0211.Design%20Add%20and%20Search%20Words%20Data%20Structure/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。</p>

<p>实现词典类 <code>WordDictionary</code> ：</p>

<ul>
	<li><code>WordDictionary()</code> 初始化词典对象</li>
	<li><code>void addWord(word)</code> 将 <code>word</code> 添加到数据结构中，之后可以对它进行匹配</li>
	<li><code>bool search(word)</code> 如果数据结构中存在字符串与 <code>word</code> 匹配，则返回 <code>true</code> ；否则，返回  <code>false</code> 。<code>word</code> 中可能包含一些 <code>'.'</code> ，每个 <code>.</code> 都可以表示任何一个字母。</li>
</ul>

<p> </p>

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
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= word.length <= 500</code></li>
	<li><code>addWord</code> 中的 <code>word</code> 由小写英文字母组成</li>
	<li><code>search</code> 中的 <code>word</code> 由 '.' 或小写英文字母组成</li>
	<li>最多调用 <code>50000</code> 次 <code>addWord</code> 和 <code>search</code></li>
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
        """
        Initialize your data structure here.
        """
        self.trie = Trie()

    def addWord(self, word: str) -> None:
        node = self.trie
        for c in word:
            index = ord(c) - ord('a')
            if node.children[index] is None:
                node.children[index] = Trie()
            node = node.children[index]
        node.is_end = True

    def search(self, word: str) -> bool:
        return self._search(word, self.trie)

    def _search(self, word: str, node: Trie) -> bool:
        for i in range(len(word)):
            c = word[i]
            index = ord(c) - ord('a')
            if c != '.' and node.children[index] is None:
                return False
            if c == '.':
                for j in range(26):
                    if node.children[j] is not None and self._search(word[i + 1:], node.children[j]):
                        return True
                return False
            node = node.children[index]
        return node.is_end

# Your WordDictionary object will be instantiated and called as such:
# obj = WordDictionary()
# obj.addWord(word)
# param_2 = obj.search(word)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class WordDictionary {
    class Trie {
        Trie[] children;
        boolean isEnd;
        Trie() {
            children = new Trie[26];
            isEnd = false;
        }
    }

    private Trie trie;

    /** Initialize your data structure here. */
    public WordDictionary() {
        trie = new Trie();
    }

    public void addWord(String word) {
        Trie node = trie;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        return search(word, trie);
    }

    private boolean search(String word, Trie node) {
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (c != '.' && node.children[index] == null) {
                return false;
            }
            if (c == '.') {
                for (int j = 0; j < 26; ++j) {
                    if (node.children[j] != null && search(word.substring(i + 1), node.children[j])) {
                        return true;
                    }
                }
                return false;
            }
            node = node.children[index];
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

### **...**

```

```

<!-- tabs:end -->
