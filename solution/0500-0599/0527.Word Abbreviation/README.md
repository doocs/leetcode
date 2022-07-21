# [527. 单词缩写](https://leetcode.cn/problems/word-abbreviation)

[English Version](/solution/0500-0599/0527.Word%20Abbreviation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组 <code>words</code> ，该数组由 <strong>互不相同</strong> 的若干字符串组成，请你找出并返回每个单词的 <strong>最小缩写</strong> 。</p>

<p>生成缩写的规则如下<strong>：</strong></p>

<ol>
	<li>初始缩写由起始字母+省略字母的数量+结尾字母组成。</li>
	<li>若存在冲突，亦即多于一个单词有同样的缩写，则使用更长的前缀代替首字母，直到从单词到缩写的映射唯一。换而言之，最终的缩写必须只能映射到一个单词。</li>
	<li>若缩写并不比原单词更短，则保留原样。</li>
</ol>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> words = ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
<strong>输出:</strong> ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["aa","aaa"]
<strong>输出：</strong>["aa","aaa"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 400</code></li>
	<li><code>2 &lt;= words[i].length &lt;= 400</code></li>
	<li><code>words[i]</code> 由小写英文字母组成</li>
	<li><code>words</code> 中的所有字符串 <strong>互不相同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀树**

将 $words$ 按照长度分组，构造对应长度的前缀树。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.v = defaultdict(int)

    def insert(self, w):
        node = self
        for c in w:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            node.v[(w[-1], len(w))] += 1

    def search(self, w):
        node = self
        res = []
        for c in w[:-1]:
            idx = ord(c) - ord('a')
            node = node.children[idx]
            res.append(c)
            if node.v[(w[-1], len(w))] == 1:
                break
        n = len(w) - len(res) - 1
        if n:
            res.append(str(n))
        res.append(w[-1])
        t = ''.join(res)
        return t if len(t) < len(w) else w


class Solution:
    def wordsAbbreviation(self, words: List[str]) -> List[str]:
        trie = Trie()
        for w in words:
            trie.insert(w)
        return [trie.search(w) for w in words]
```

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.v = Counter()

    def insert(self, w):
        node = self
        for c in w:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            node.v[w[-1]] += 1

    def search(self, w):
        node = self
        res = []
        for c in w[:-1]:
            idx = ord(c) - ord('a')
            node = node.children[idx]
            res.append(c)
            if node.v[w[-1]] == 1:
                break
        n = len(w) - len(res) - 1
        if n:
            res.append(str(n))
        res.append(w[-1])
        t = ''.join(res)
        return t if len(t) < len(w) else w

class Solution:
    def wordsAbbreviation(self, words: List[str]) -> List[str]:
        trees = {}
        for w in words:
            if len(w) not in trees:
                trees[len(w)] = Trie()
        for w in words:
            trees[len(w)].insert(w)
        return [trees[len(w)].search(w) for w in words]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Trie {
    Trie[] children = new Trie[26];
    int[] v = new int[26];

    void insert(String w) {
        Trie node = this;
        int t = w.charAt(w.length() - 1) - 'a';
        for (char c : w.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
            node.v[t]++;
        }
    }

    String search(String w) {
        Trie node = this;
        StringBuilder res = new StringBuilder();
        int t = w.charAt(w.length() - 1) - 'a';
        for (int i = 0; i < w.length() - 1; ++i) {
            char c = w.charAt(i);
            node = node.children[c - 'a'];
            res.append(c);
            if (node.v[t] == 1) {
                break;
            }
        }
        int n = w.length() - res.length() - 1;
        if (n > 0) {
            res.append(n);
        }
        res.append(w.charAt(w.length() - 1));
        return res.length() < w.length() ? res.toString() : w;
    }
}

class Solution {
    public List<String> wordsAbbreviation(List<String> words) {
        Map<Integer, Trie> trees = new HashMap<>();
        for (String w : words) {
            if (!trees.containsKey(w.length())) {
                trees.put(w.length(), new Trie());
            }
        }
        for (String w : words) {
            trees.get(w.length()).insert(w);
        }
        List<String> ans = new ArrayList<>();
        for (String w : words) {
            ans.add(trees.get(w.length()).search(w));
        }
        return ans;
    }
}
```

### **Go**

```go
type Trie struct {
	children [26]*Trie
	v        [26]int
}

func newTrie() *Trie {
	return &Trie{}
}
func (this *Trie) insert(w string) {
	node := this
	t := w[len(w)-1] - 'a'
	for _, c := range w {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
		node.v[t]++
	}
}
func (this *Trie) search(w string) string {
	node := this
	t := w[len(w)-1] - 'a'
	res := &strings.Builder{}
	for _, c := range w[:len(w)-1] {
		res.WriteRune(c)
		c -= 'a'
		node = node.children[c]
		if node.v[t] == 1 {
			break
		}
	}
	n := len(w) - res.Len() - 1
	if n > 0 {
		res.WriteString(strconv.Itoa(n))
	}
	res.WriteByte(w[len(w)-1])
	if res.Len() < len(w) {
		return res.String()
	}
	return w
}

func wordsAbbreviation(words []string) []string {
	trees := map[int]*Trie{}
	for _, w := range words {
		if _, ok := trees[len(w)]; !ok {
			trees[len(w)] = newTrie()
		}
	}
	for _, w := range words {
		trees[len(w)].insert(w)
	}
	ans := []string{}
	for _, w := range words {
		ans = append(ans, trees[len(w)].search(w))
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
