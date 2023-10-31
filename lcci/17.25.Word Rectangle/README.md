# [面试题 17.25. 单词矩阵](https://leetcode.cn/problems/word-rectangle-lcci)

[English Version](/lcci/17.25.Word%20Rectangle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一份单词的清单，设计一个算法，创建由字母组成的面积最大的矩形，其中每一行组成一个单词(自左向右)，每一列也组成一个单词(自上而下)。不要求这些单词在清单里连续出现，但要求所有行等长，所有列等高。</p>
<p>如果有多个面积最大的矩形，输出任意一个均可。一个单词可以重复使用。</p>
<p><strong>示例 1:</strong></p>
<pre><strong>输入:</strong> [&quot;this&quot;, &quot;real&quot;, &quot;hard&quot;, &quot;trh&quot;, &quot;hea&quot;, &quot;iar&quot;, &quot;sld&quot;]
<strong>输出:
</strong>[
&nbsp;  &quot;this&quot;,
&nbsp;  &quot;real&quot;,
&nbsp;  &quot;hard&quot;
]</pre>
<p><strong>示例 2:</strong></p>
<pre><strong>输入:</strong> [&quot;aa&quot;]
<strong>输出: </strong>[&quot;aa&quot;,&quot;aa&quot;]</pre>
<p><strong>说明：</strong></p>
<ul>
	<li><code>words.length &lt;= 1000</code></li>
	<li><code>words[i].length &lt;= 100</code></li>
	<li>数据保证单词足够随机</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：分组 + 回溯 + 字典树**

我们注意到，构建单词矩阵时所用的单词长度是相同的，因此，我们可以将单词按照长度分组，记录在哈希表 $d$ 中。对于每个长度，我们只需要考虑该长度的单词即可。

我们使用回溯的方法来构建单词矩阵。我们使用一个列表 $t$ 来记录当前已经构建好的单词矩阵，列表中的每个单词都具有相同的长度。我们从哈希表 $d$ 中取出长度为 $n$ 的单词列表，从中选择一个单词 $w$，加入到 $t$ 中。如果此时不是一个合法的单词矩阵，那么我们就不继续往下搜索，而是尝试选择另一个单词。如果是一个合法的单词矩阵，并且已经构建完成，那么我们更新最大面积以及答案矩阵；然后，我们递归地进行搜索，寻找下一个单词。最后，我们将 $w$ 从 $t$ 中移除，进入下一轮搜索。

在判断单词矩阵是否合法时，我们可以使用字典树来进行优化。我们将所有的单词加入到字典树中，然后对于每一列，我们检查其是否是一个单词。如果是一个单词，那么我们就检查下一列，否则我们就可以停止对该单词矩阵的搜索了。

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


class Solution:
    def maxRectangle(self, words: List[str]) -> List[str]:
        def check(mat):
            m, n = len(mat), len(mat[0])
            ans = 1
            for j in range(n):
                node = trie
                for i in range(m):
                    idx = ord(mat[i][j]) - ord("a")
                    if node.children[idx] is None:
                        return 0
                    node = node.children[idx]
                if not node.is_end:
                    ans = 2
            return ans

        def dfs(ws):
            nonlocal ans, max_s, max_l
            if len(ws[0]) * max_l <= max_s or len(t) >= max_l:
                return

            for w in ws:
                t.append(w)
                st = check(t)
                if st == 0:
                    t.pop()
                    continue
                if st == 1 and max_s < len(t) * len(t[0]):
                    ans = t[:]
                    max_s = len(t) * len(t[0])
                dfs(ws)
                t.pop()

        d = defaultdict(list)
        trie = Trie()
        max_l = 0
        for w in words:
            trie.insert(w)
            max_l = max(max_l, len(w))
            d[len(w)].append(w)

        max_s = 0
        ans = []
        for ws in d.values():
            t = []
            dfs(ws)
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
}

class Solution {
    private int maxL;
    private int maxS;
    private String[] ans;
    private Trie trie = new Trie();
    private List<String> t = new ArrayList<>();

    public String[] maxRectangle(String[] words) {
        Map<Integer, List<String>> d = new HashMap<>(100);
        for (String w : words) {
            maxL = Math.max(maxL, w.length());
            trie.insert(w);
            d.computeIfAbsent(w.length(), k -> new ArrayList<>()).add(w);
        }
        for (List<String> ws : d.values()) {
            t.clear();
            dfs(ws);
        }
        return ans;
    }

    private void dfs(List<String> ws) {
        if (ws.get(0).length() * maxL <= maxS || t.size() >= maxL) {
            return;
        }
        for (String w : ws) {
            t.add(w);
            int st = check(t);
            if (st == 0) {
                t.remove(t.size() - 1);
                continue;
            }
            if (st == 1 && maxS < t.size() * t.get(0).length()) {
                maxS = t.size() * t.get(0).length();
                ans = t.toArray(new String[0]);
            }
            dfs(ws);
            t.remove(t.size() - 1);
        }
    }

    private int check(List<String> mat) {
        int m = mat.size(), n = mat.get(0).length();
        int ans = 1;
        for (int j = 0; j < n; ++j) {
            Trie node = trie;
            for (int i = 0; i < m; ++i) {
                int idx = mat.get(i).charAt(j) - 'a';
                if (node.children[idx] == null) {
                    return 0;
                }
                node = node.children[idx];
            }
            if (!node.isEnd) {
                ans = 2;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Trie {
public:
    vector<Trie*> children;
    bool is_end;

    Trie() {
        children = vector<Trie*>(26, nullptr);
        is_end = false;
    }

    void insert(const string& word) {
        Trie* cur = this;
        for (char c : word) {
            c -= 'a';
            if (cur->children[c] == nullptr) {
                cur->children[c] = new Trie;
            }
            cur = cur->children[c];
        }
        cur->is_end = true;
    }
};

class Solution {
public:
    vector<string> maxRectangle(vector<string>& words) {
        unordered_map<int, vector<string>> d;
        int maxL = 0, maxS = 0;
        vector<string> ans;
        vector<string> t;
        Trie* trie = new Trie();
        for (auto& w : words) {
            maxL = max(maxL, (int) w.size());
            d[w.size()].emplace_back(w);
            trie->insert(w);
        }
        auto check = [&](vector<string>& mat) {
            int m = mat.size(), n = mat[0].size();
            int ans = 1;
            for (int j = 0; j < n; ++j) {
                Trie* node = trie;
                for (int i = 0; i < m; ++i) {
                    int idx = mat[i][j] - 'a';
                    if (!node->children[idx]) {
                        return 0;
                    }
                    node = node->children[idx];
                }
                if (!node->is_end) {
                    ans = 2;
                }
            }
            return ans;
        };

        function<void(vector<string>&)> dfs = [&](vector<string>& ws) {
            if (ws[0].size() * maxL <= maxS || t.size() >= maxL) {
                return;
            }
            for (auto& w : ws) {
                t.emplace_back(w);
                int st = check(t);
                if (st == 0) {
                    t.pop_back();
                    continue;
                }
                if (st == 1 && maxS < t.size() * t[0].size()) {
                    maxS = t.size() * t[0].size();
                    ans = t;
                }
                dfs(ws);
                t.pop_back();
            }
        };
        for (auto& [_, ws] : d) {
            t.clear();
            dfs(ws);
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

func maxRectangle(words []string) (ans []string) {
	trie := newTrie()
	d := map[int][]string{}
	t := []string{}
	var maxL, maxS int
	for _, w := range words {
		maxL = max(maxL, len(w))
		d[len(w)] = append(d[len(w)], w)
		trie.insert(w)
	}
	check := func(mat []string) int {
		m, n := len(mat), len(mat[0])
		ans := 1
		for j := 0; j < n; j++ {
			node := trie
			for i := 0; i < m; i++ {
				idx := mat[i][j] - 'a'
				if node.children[idx] == nil {
					return 0
				}
				node = node.children[idx]
			}
			if !node.isEnd {
				ans = 2
			}
		}
		return ans
	}
	var dfs func([]string)
	dfs = func(ws []string) {
		if len(ws[0])*maxL <= maxS || len(t) >= maxL {
			return
		}
		for _, w := range ws {
			t = append(t, w)
			st := check(t)
			if st == 0 {
				t = t[:len(t)-1]
				continue
			}
			if st == 1 && maxS < len(t)*len(t[0]) {
				maxS = len(t) * len(t[0])
				ans = append([]string{}, t...)
			}
			dfs(ws)
			t = t[:len(t)-1]
		}
	}
	for _, ws := range d {
		dfs(ws)
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
