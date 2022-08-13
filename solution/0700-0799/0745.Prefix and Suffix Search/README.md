# [745. 前缀和后缀搜索](https://leetcode.cn/problems/prefix-and-suffix-search)

[English Version](/solution/0700-0799/0745.Prefix%20and%20Suffix%20Search/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。</p>

<p>实现 <code>WordFilter</code> 类：</p>

<ul>
	<li><code>WordFilter(string[] words)</code> 使用词典中的单词 <code>words</code> 初始化对象。</li>
	<li><code>f(string pref, string suff)</code> 返回词典中具有前缀&nbsp;<code>prefix</code>&nbsp;和后缀 <code>suff</code>&nbsp;的单词的下标。如果存在不止一个满足要求的下标，返回其中 <strong>最大的下标</strong> 。如果不存在这样的单词，返回 <code>-1</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入</strong>
["WordFilter", "f"]
[[["apple"]], ["a", "e"]]
<strong>输出</strong>
[null, 0]
<strong>解释</strong>
WordFilter wordFilter = new WordFilter(["apple"]);
wordFilter.f("a", "e"); // 返回 0 ，因为下标为 0 的单词：前缀 prefix = "a" 且 后缀 suff = "e" 。
</pre>

&nbsp;

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 7</code></li>
	<li><code>1 &lt;= pref.length, suff.length &lt;= 7</code></li>
	<li><code>words[i]</code>、<code>pref</code> 和 <code>suff</code> 仅由小写英文字母组成</li>
	<li>最多对函数 <code>f</code> 执行 <code>10<sup>4</sup></code> 次调用</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：暴力哈希**

遍历 $words$ 的每个单词 $w$，将 $w$ 的所有前缀、后缀对存放到哈希表中。

**方法二：双前缀树**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class WordFilter:
    def __init__(self, words: List[str]):
        self.d = {}
        for k, w in enumerate(words):
            n = len(w)
            for i in range(n + 1):
                a = w[:i]
                for j in range(n + 1):
                    b = w[j:]
                    self.d[(a, b)] = k

    def f(self, pref: str, suff: str) -> int:
        return self.d.get((pref, suff), -1)


# Your WordFilter object will be instantiated and called as such:
# obj = WordFilter(words)
# param_1 = obj.f(pref,suff)
```

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.indexes = []

    def insert(self, word, i):
        node = self
        for c in word:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            node.indexes.append(i)

    def search(self, pref):
        node = self
        for c in pref:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                return []
            node = node.children[idx]
        return node.indexes


class WordFilter:

    def __init__(self, words: List[str]):
        self.p = Trie()
        self.s = Trie()
        for i, w in enumerate(words):
            self.p.insert(w, i)
            self.s.insert(w[::-1], i)

    def f(self, pref: str, suff: str) -> int:
        a = self.p.search(pref)
        b = self.s.search(suff[::-1])
        if not a or not b:
            return -1
        i, j = len(a) - 1, len(b) - 1
        while ~i and ~j:
            if a[i] == b[j]:
                return a[i]
            if a[i] > b[j]:
                i -= 1
            else:
                j -= 1
        return -1


# Your WordFilter object will be instantiated and called as such:
# obj = WordFilter(words)
# param_1 = obj.f(pref,suff)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class WordFilter {
    private Map<String, Integer> d = new HashMap<>();

    public WordFilter(String[] words) {
        for (int k = 0; k < words.length; ++k) {
            String w = words[k];
            int n = w.length();
            for (int i = 0; i <= n; ++i) {
                String a = w.substring(0, i);
                for (int j = 0; j <= n; ++j) {
                    String b = w.substring(j);
                    d.put(a + "." + b, k);
                }
            }
        }
    }

    public int f(String pref, String suff) {
        return d.getOrDefault(pref + "." + suff, -1);
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(pref,suff);
 */
```

```java
class Trie {
    Trie[] children = new Trie[26];
    List<Integer> indexes = new ArrayList<>();

    void insert(String word, int i) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
            node.indexes.add(i);
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
        return node.indexes;
    }
}

class WordFilter {
    private Trie p = new Trie();
    private Trie s = new Trie();

    public WordFilter(String[] words) {
        for (int i = 0; i < words.length; ++i) {
            String w = words[i];
            p.insert(w, i);
            s.insert(new StringBuilder(w).reverse().toString(), i);
        }
    }

    public int f(String pref, String suff) {
        suff = new StringBuilder(suff).reverse().toString();
        List<Integer> a = p.search(pref);
        List<Integer> b = s.search(suff);
        if (a.isEmpty() || b.isEmpty()) {
            return -1;
        }
        int i = a.size() - 1, j = b.size() - 1;
        while (i >= 0 && j >= 0) {
            int c = a.get(i), d = b.get(j);
            if (c == d) {
                return c;
            }
            if (c > d) {
                --i;
            } else {
                --j;
            }
        }
        return -1;
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(pref,suff);
 */
```

### **C++**

```cpp
class WordFilter {
public:
    unordered_map<string, int> d;

    WordFilter(vector<string>& words) {
        for (int k = 0; k < words.size(); ++k) {
            string w = words[k];
            int n = w.size();
            for (int i = 0; i <= n; ++i) {
                string a = w.substr(0, i);
                for (int j = 0; j <= n; ++j) {
                    string b = w.substr(j, n - j);
                    d[a + "." + b] = k;
                }
            }
        }
    }

    int f(string pref, string suff) {
        string key = pref + "." + suff;
        if (d.count(key)) return d[key];
        return -1;
    }
};

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter* obj = new WordFilter(words);
 * int param_1 = obj->f(pref,suff);
 */
```

### **Go**

```go
type WordFilter struct {
	d map[string]int
}

func Constructor(words []string) WordFilter {
	d := map[string]int{}
	for k, w := range words {
		n := len(w)
		for i := 0; i <= n; i++ {
			a := w[:i]
			for j := 0; j <= n; j++ {
				b := w[j:]
				d[a+"."+b] = k
			}
		}
	}
	return WordFilter{d}
}

func (this *WordFilter) F(pref string, suff string) int {
	if v, ok := this.d[pref+"."+suff]; ok {
		return v
	}
	return -1
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * obj := Constructor(words);
 * param_1 := obj.F(pref,suff);
 */
```

```go
type Trie struct {
	children [26]*Trie
	indexes  []int
}

func newTrie() *Trie {
	return &Trie{indexes: []int{}}
}
func (this *Trie) insert(word string, i int) {
	node := this
	for _, c := range word {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = newTrie()
		}
		node = node.children[idx]
		node.indexes = append(node.indexes, i)
	}
}

func (this *Trie) search(pref string) []int {
	node := this
	for _, c := range pref {
		idx := c - 'a'
		if node.children[idx] == nil {
			return []int{}
		}
		node = node.children[idx]
	}
	return node.indexes
}

type WordFilter struct {
	p *Trie
	s *Trie
}

func Constructor(words []string) WordFilter {
	p := newTrie()
	s := newTrie()
	for i, w := range words {
		p.insert(w, i)
		s.insert(reverse(w), i)
	}
	return WordFilter{p, s}
}

func (this *WordFilter) F(pref string, suff string) int {
	a := this.p.search(pref)
	b := this.s.search(reverse(suff))
	if len(a) == 0 || len(b) == 0 {
		return -1
	}
	i, j := len(a)-1, len(b)-1
	for i >= 0 && j >= 0 {
		if a[i] == b[j] {
			return a[i]
		}
		if a[i] > b[j] {
			i--
		} else {
			j--
		}
	}
	return -1
}

func reverse(w string) string {
	ww := []byte(w)
	for i, j := 0, len(w)-1; i < j; i++ {
		ww[i], ww[j] = ww[j], ww[i]
		j--
	}
	return string(ww)
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * obj := Constructor(words);
 * param_1 := obj.F(pref,suff);
 */
```

### **...**

```

```

<!-- tabs:end -->
