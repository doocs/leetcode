# [面试题 17.17. 多次搜索](https://leetcode.cn/problems/multi-search-lcci)

[English Version](/lcci/17.17.Multi%20Search/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个较长字符串<code>big</code>和一个包含较短字符串的数组<code>smalls</code>，设计一个方法，根据<code>smalls</code>中的每一个较短字符串，对<code>big</code>进行搜索。输出<code>smalls</code>中的字符串在<code>big</code>里出现的所有位置<code>positions</code>，其中<code>positions[i]</code>为<code>smalls[i]</code>出现的所有位置。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>
big = &quot;mississippi&quot;
smalls = [&quot;is&quot;,&quot;ppi&quot;,&quot;hi&quot;,&quot;sis&quot;,&quot;i&quot;,&quot;ssippi&quot;]
<strong>输出：</strong> [[1,4],[8],[],[3],[1,4,7,10],[5]]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= len(big) &lt;= 1000</code></li>
	<li><code>0 &lt;= len(smalls[i]) &lt;= 1000</code></li>
	<li><code>smalls</code>的总字符数不会超过 100000。</li>
	<li>你可以认为<code>smalls</code>中没有重复字符串。</li>
	<li>所有出现的字符均为英文小写字母。</li>
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
        self.idx = -1
        self.children = [None] * 26

    def insert(self, word, i):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.idx = i

    def search(self, word):
        res = []
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                return res
            node = node.children[idx]
            if node.idx != -1:
                res.append(node.idx)
        return res


class Solution:
    def multiSearch(self, big: str, smalls: List[str]) -> List[List[int]]:
        tree = Trie()
        for i, s in enumerate(smalls):
            tree.insert(s, i)
        n = len(smalls)
        ans = [[] for _ in range(n)]
        for i in range(len(big)):
            s = big[i:]
            for idx in tree.search(s):
                ans[idx].append(i)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] multiSearch(String big, String[] smalls) {
        Trie tree = new Trie();
        int n = smalls.length;
        for (int i = 0; i < n; ++i) {
            tree.insert(smalls[i], i);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            res.add(new ArrayList<>());
        }
        for (int i = 0; i < big.length(); ++i) {
            String s = big.substring(i);
            List<Integer> t = tree.search(s);
            for (int idx : t) {
                res.get(idx).add(i);
            }
        }
        int[][] ans = new int[n][];
        for (int i = 0; i < n; ++i) {
            ans[i] = res.get(i).stream().mapToInt(Integer::intValue).toArray();
        }
        return ans;
    }
}

class Trie {
    private int idx;
    private Trie[] children;

    public Trie() {
        idx = -1;
        children = new Trie[26];
    }

    public void insert(String word, int i) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.idx = i;
    }

    public List<Integer> search(String word) {
        Trie node = this;
        List<Integer> res = new ArrayList<>();
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return res;
            }
            node = node.children[c];
            if (node.idx != -1) {
                res.add(node.idx);
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Trie {
private:
    vector<Trie*> children;
    int idx;

public:
    Trie()
        : children(26)
        , idx(-1) { }

    void insert(string word, int i) {
        Trie* node = this;
        for (char c : word) {
            int idx = c - 'a';
            if (!node->children[idx]) node->children[idx] = new Trie();
            node = node->children[idx];
        }
        node->idx = i;
    }

    vector<int> search(string word) {
        Trie* node = this;
        vector<int> res;
        for (char c : word) {
            int idx = c - 'a';
            if (!node->children[idx]) return res;
            node = node->children[idx];
            if (node->idx != -1) res.push_back(node->idx);
        }
        return res;
    }
};

class Solution {
public:
    vector<vector<int>> multiSearch(string big, vector<string>& smalls) {
        Trie* tree = new Trie();
        int n = smalls.size();
        for (int i = 0; i < n; ++i) tree->insert(smalls[i], i);
        vector<vector<int>> ans(n);
        for (int i = 0, m = big.size(); i < m; ++i) {
            string s = big.substr(i, m - i);
            vector<int> t = tree->search(s);
            for (int& idx : t) ans[idx].push_back(i);
        }
        return ans;
    }
};
```

### **Go**

```go
type Trie struct {
	children [26]*Trie
	idx      int
}

func newTrie() *Trie {
	return &Trie{idx: -1}
}

func (this *Trie) insert(word string, idx int) {
	node := this
	for _, c := range word {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = newTrie()
		}
		node = node.children[idx]
	}
	node.idx = idx
}

func (this *Trie) search(word string) []int {
	node := this
	var res []int
	for _, c := range word {
		idx := c - 'a'
		if node.children[idx] == nil {
			return res
		}
		node = node.children[idx]
		if node.idx != -1 {
			res = append(res, node.idx)
		}
	}
	return res
}

func multiSearch(big string, smalls []string) [][]int {
	tree := newTrie()
	for i, s := range smalls {
		tree.insert(s, i)
	}
	n := len(smalls)
	ans := make([][]int, n)
	for i := range big {
		s := big[i:]
		t := tree.search(s)
		for _, idx := range t {
			ans[idx] = append(ans[idx], i)
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
