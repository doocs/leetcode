# [425. 单词方块](https://leetcode.cn/problems/word-squares)

[English Version](/solution/0400-0499/0425.Word%20Squares/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个单词集合&nbsp;<code>words</code> <strong>（没有重复）</strong>，找出并返回其中所有的 <a href="https://en.wikipedia.org/wiki/Word_square">单词方块</a><strong>&nbsp;</strong>。&nbsp;<code>words</code>&nbsp;中的同一个单词可以被 <strong>多次</strong> 使用。你可以按 <strong>任意顺序</strong> 返回答案。</p>

<p>一个单词序列形成了一个有效的 <strong>单词方块</strong> 的意思是指从第 <code>k</code> 行和第 <code>k</code> 列 &nbsp;<code>0 &lt;= k &lt; max(numRows, numColumns)</code> 来看都是相同的字符串。</p>

<ul>
	<li>例如，单词序列&nbsp;<code>["ball","area","lead","lady"]</code>&nbsp;形成了一个单词方块，因为每个单词从水平方向看和从竖直方向看都是相同的。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["area","lead","wall","lady","ball"]
<strong>输出:</strong> [["ball","area","lead","lady"],
["wall","area","lead","lady"]]
<strong>解释：</strong>
输出包含两个单词方块，输出的顺序不重要，只需要保证每个单词方块内的单词顺序正确即可。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["abat","baba","atan","atal"]
<strong>输出：</strong>[["baba","abat","baba","atal"],
["baba","abat","baba","atan"]]
<strong>解释：</strong>
输出包含两个单词方块，输出的顺序不重要，只需要保证每个单词方块内的单词顺序正确即可。 
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 4</code></li>
	<li><code>words[i]</code>&nbsp;长度相同</li>
	<li><code>words[i]</code>&nbsp;只由小写英文字母组成</li>
	<li><code>words[i]</code>&nbsp;都 <strong>各不相同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀树 + DFS**

根据已添加单词确定下一个单词的前缀，继续进行搜索。

比如已经添加了两个单词 $ball$ 和 $area$，要添加下一个单词，我们首先要获取下一个单词的前缀，第一个字母是第一个单词的第三个位置 $l$，第二个字母是第二个单词的第三个位置 $e$，这样就构成了前缀 $le$。然后找出所有前缀为 $le$ 的单词，作为下一个单词。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
