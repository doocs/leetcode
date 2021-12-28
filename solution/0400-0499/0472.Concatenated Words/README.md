# [472. 连接词](https://leetcode-cn.com/problems/concatenated-words)

[English Version](/solution/0400-0499/0472.Concatenated%20Words/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 <strong>不含重复 </strong>单词的字符串数组 <code>words</code> ，编写一个程序，返回 <code>words</code> 中的所有 <strong>连接词</strong> 。</p>

<p><strong>连接词</strong> 的定义为：一个字符串完全是由至少两个给定数组中的单词组成的。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
<strong>输出：</strong>["catsdogcats","dogcatsdog","ratcatdogcat"]
<strong>解释：</strong>"catsdogcats"由"cats", "dog" 和 "cats"组成; 
     "dogcatsdog"由"dog", "cats"和"dog"组成; 
     "ratcatdogcat"由"rat", "cat", "dog"和"cat"组成。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["cat","dog","catdog"]
<strong>输出：</strong>["catdog"]</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= words.length <= 10<sup>4</sup></code></li>
	<li><code>0 <= words[i].length <= 1000</code></li>
	<li><code>words[i]</code> 仅由小写字母组成</li>
	<li><code>0 <= sum(words[i].length) <= 6 * 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

前缀树（字典树） + DFS。

判断一个单词是不是连接词，需要判断这个单词是否完全由至少两个给定数组中的更短的非空单词（可以重复）组成。判断更短的单词是否在给定数组中，可以使用字典树实现。

首先将 words 按照字符串的长度递增的顺序排序，排序后可以确保当遍历到任意单词时，比该单词短的单词一定都已经遍历过，因此可以根据已经遍历过的全部单词判断当前单词是不是连接词。

在将 words 排序之后，遍历 words，跳过空字符串，对于每个非空单词，判断该单词是不是连接词，如果是连接词则将该单词加入结果数组，如果不是连接词则将该单词加入字典树。

判断一个单词是不是连接词的做法是在字典树中深度优先搜索。从该单词的第一个字符（即下标 0 处的字符）开始，在字典树中依次搜索每个字符对应的结点，可能有以下几种情况：

-   如果一个字符对应的结点是单词的结尾，则找到了一个更短的单词，从该字符的后一个字符开始搜索下一个更短的单词；
-   如果一个字符对应的结点在字典树中不存在，则当前的搜索结果失败，回到上一个单词的结尾继续搜索。

如果找到一个更短的单词且这个更短的单词的最后一个字符是当前单词的最后一个字符，则当前单词是连接词。由于数组 words 中没有重复的单词，因此在判断一个单词是不是连接词时，该单词一定没有加入字典树，由此可以确保判断连接词的条件成立。

说明：由于一个连接词由多个更短的非空单词组成，如果存在一个较长的连接词的组成部分之一是一个较短的连接词，则一定可以将这个较短的连接词换成多个更短的非空单词，因此**不需要将连接词加入字典树**。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
