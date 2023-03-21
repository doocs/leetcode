# [1268. 搜索推荐系统](https://leetcode.cn/problems/search-suggestions-system)

[English Version](/solution/1200-1299/1268.Search%20Suggestions%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个产品数组&nbsp;<code>products</code>&nbsp;和一个字符串&nbsp;<code>searchWord</code>&nbsp;，<code>products</code>&nbsp; 数组中每个产品都是一个字符串。</p>

<p>请你设计一个推荐系统，在依次输入单词&nbsp;<code>searchWord</code> 的每一个字母后，推荐&nbsp;<code>products</code> 数组中前缀与&nbsp;<code>searchWord</code> 相同的最多三个产品。如果前缀相同的可推荐产品超过三个，请按字典序返回最小的三个。</p>

<p>请你以二维列表的形式，返回在输入&nbsp;<code>searchWord</code>&nbsp;每个字母后相应的推荐产品的列表。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>products = [&quot;mobile&quot;,&quot;mouse&quot;,&quot;moneypot&quot;,&quot;monitor&quot;,&quot;mousepad&quot;], searchWord = &quot;mouse&quot;
<strong>输出：</strong>[
[&quot;mobile&quot;,&quot;moneypot&quot;,&quot;monitor&quot;],
[&quot;mobile&quot;,&quot;moneypot&quot;,&quot;monitor&quot;],
[&quot;mouse&quot;,&quot;mousepad&quot;],
[&quot;mouse&quot;,&quot;mousepad&quot;],
[&quot;mouse&quot;,&quot;mousepad&quot;]
]
<strong>解释：</strong>按字典序排序后的产品列表是 [&quot;mobile&quot;,&quot;moneypot&quot;,&quot;monitor&quot;,&quot;mouse&quot;,&quot;mousepad&quot;]
输入 m 和 mo，由于所有产品的前缀都相同，所以系统返回字典序最小的三个产品 [&quot;mobile&quot;,&quot;moneypot&quot;,&quot;monitor&quot;]
输入 mou， mous 和 mouse 后系统都返回 [&quot;mouse&quot;,&quot;mousepad&quot;]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>products = [&quot;havana&quot;], searchWord = &quot;havana&quot;
<strong>输出：</strong>[[&quot;havana&quot;],[&quot;havana&quot;],[&quot;havana&quot;],[&quot;havana&quot;],[&quot;havana&quot;],[&quot;havana&quot;]]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>products = [&quot;bags&quot;,&quot;baggage&quot;,&quot;banner&quot;,&quot;box&quot;,&quot;cloths&quot;], searchWord = &quot;bags&quot;
<strong>输出：</strong>[[&quot;baggage&quot;,&quot;bags&quot;,&quot;banner&quot;],[&quot;baggage&quot;,&quot;bags&quot;,&quot;banner&quot;],[&quot;baggage&quot;,&quot;bags&quot;],[&quot;bags&quot;]]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>products = [&quot;havana&quot;], searchWord = &quot;tatiana&quot;
<strong>输出：</strong>[[],[],[],[],[],[],[]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= products.length &lt;= 1000</code></li>
	<li><code>1 &lt;= &Sigma; products[i].length &lt;= 2 * 10^4</code></li>
	<li><code>products[i]</code>&nbsp;中所有的字符都是小写英文字母。</li>
	<li><code>1 &lt;= searchWord.length &lt;= 1000</code></li>
	<li><code>searchWord</code>&nbsp;中所有字符都是小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 前缀树**

题目要求在输入 `searchWord` 的每一个字母后，推荐 `products` 数组中前缀与 `searchWord` 相同的最多三个产品。如果前缀相同的可推荐产品超过三个，按字典序返回最小的三个。

找前缀相同的产品，可以使用前缀树；而要返回字典序最小的三个产品，我们可以先对 `products` 数组进行排序，然后将排序后的数组下标存入前缀树中。

前缀树的每个节点维护以下信息：

-   `children`：这是一个长度为 $26$ 的数组，用于存储当前节点的子节点，`children[i]` 表示当前节点的子节点中字符为 `i + 'a'` 的节点。
-   `v`：这是一个数组，用于存储当前节点的子节点中的字符在 `products` 数组中的下标，最多存储三个下标。

搜索时，我们从前缀树的根节点开始，找到每一个前缀对应的下标数组，将其存入结果数组中。最后只需要将每个下标数组中的下标对应到 `products` 数组中即可。

时间复杂度 $O(L \times \log n + m)$，空间复杂度 $O(L)$。其中 $L$ 是 `products` 数组所有字符串的长度之和，而 $n$ 和 $m$ 分别是 `products` 数组的长度和 `searchWord` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Trie:
    def __init__(self):
        self.children: List[Union[Trie, None]] = [None] * 26
        self.v: List[int] = []

    def insert(self, w, i):
        node = self
        for c in w:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            if len(node.v) < 3:
                node.v.append(i)

    def search(self, w):
        node = self
        ans = [[] for _ in range(len(w))]
        for i, c in enumerate(w):
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                break
            node = node.children[idx]
            ans[i] = node.v
        return ans


class Solution:
    def suggestedProducts(
        self, products: List[str], searchWord: str
    ) -> List[List[str]]:
        products.sort()
        trie = Trie()
        for i, w in enumerate(products):
            trie.insert(w, i)
        return [[products[i] for i in v] for v in trie.search(searchWord)]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Trie {
    Trie[] children = new Trie[26];
    List<Integer> v = new ArrayList<>();

    public void insert(String w, int i) {
        Trie node = this;
        for (int j = 0; j < w.length(); ++j) {
            int idx = w.charAt(j) - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
            if (node.v.size() < 3) {
                node.v.add(i);
            }
        }
    }

    public List<Integer>[] search(String w) {
        Trie node = this;
        int n = w.length();
        List<Integer>[] ans = new List[n];
        Arrays.setAll(ans, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            int idx = w.charAt(i) - 'a';
            if (node.children[idx] == null) {
                break;
            }
            node = node.children[idx];
            ans[i] = node.v;
        }
        return ans;
    }
}

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        Trie trie = new Trie();
        for (int i = 0; i < products.length; ++i) {
            trie.insert(products[i], i);
        }
        List<List<String>> ans = new ArrayList<>();
        for (var v : trie.search(searchWord)) {
            List<String> t = new ArrayList<>();
            for (int i : v) {
                t.add(products[i]);
            }
            ans.add(t);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Trie {
public:
    void insert(string& w, int i) {
        Trie* node = this;
        for (int j = 0; j < w.size(); ++j) {
            int idx = w[j] - 'a';
            if (!node->children[idx]) {
                node->children[idx] = new Trie();
            }
            node = node->children[idx];
            if (node->v.size() < 3) {
                node->v.push_back(i);
            }
        }
    }

    vector<vector<int>> search(string& w) {
        Trie* node = this;
        int n = w.size();
        vector<vector<int>> ans(n);
        for (int i = 0; i < w.size(); ++i) {
            int idx = w[i] - 'a';
            if (!node->children[idx]) {
                break;
            }
            node = node->children[idx];
            ans[i] = move(node->v);
        }
        return ans;
    }

private:
    vector<Trie*> children = vector<Trie*>(26);
    vector<int> v;
};

class Solution {
public:
    vector<vector<string>> suggestedProducts(vector<string>& products, string searchWord) {
        sort(products.begin(), products.end());
        Trie* trie = new Trie();
        for (int i = 0; i < products.size(); ++i) {
            trie->insert(products[i], i);
        }
        vector<vector<string>> ans;
        for (auto& v : trie->search(searchWord)) {
            vector<string> t;
            for (int i : v) {
                t.push_back(products[i]);
            }
            ans.push_back(move(t));
        }
        return ans;
    }
};
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
func (this *Trie) insert(w string, i int) {
	node := this
	for _, c := range w {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
		if len(node.v) < 3 {
			node.v = append(node.v, i)
		}
	}
}

func (this *Trie) search(w string) [][]int {
	node := this
	n := len(w)
	ans := make([][]int, n)
	for i, c := range w {
		c -= 'a'
		if node.children[c] == nil {
			break
		}
		node = node.children[c]
		ans[i] = node.v
	}
	return ans
}

func suggestedProducts(products []string, searchWord string) (ans [][]string) {
	sort.Strings(products)
	trie := newTrie()
	for i, w := range products {
		trie.insert(w, i)
	}
	for _, v := range trie.search(searchWord) {
		t := []string{}
		for _, i := range v {
			t = append(t, products[i])
		}
		ans = append(ans, t)
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
