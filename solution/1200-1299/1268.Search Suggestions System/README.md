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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.v = []

    def insert(self, word, i):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            node.v.append(i)

    def search(self, word):
        res = [[] for _ in range(len(word))]
        node = self
        for i, c in enumerate(word):
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                break
            node = node.children[idx]
            res[i] = node.v[:3]
        return res


class Solution:
    def suggestedProducts(
        self, products: List[str], searchWord: str
    ) -> List[List[str]]:
        products.sort()
        trie = Trie()
        for i, w in enumerate(products):
            trie.insert(w, i)
        res = trie.search(searchWord)
        return [[products[j] for j in v] for v in res]
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
            if (node.v.size() < 3) {
                node.v.add(i);
            }
        }
    }

    List<List<Integer>> search(String word) {
        List<List<Integer>> res = new ArrayList<>();
        int n = word.length();
        for (int i = 0; i < n; ++i) {
            res.add(new ArrayList<>());
        }
        Trie node = this;
        for (int i = 0; i < n; ++i) {
            char c = word.charAt(i);
            c -= 'a';
            if (node.children[c] == null) {
                break;
            }
            node = node.children[c];
            res.set(i, node.v);
        }
        return res;
    }
}

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        Trie trie = new Trie();
        for (int i = 0; i < products.length; ++i) {
            trie.insert(products[i], i);
        }
        List<List<Integer>> res = trie.search(searchWord);
        List<List<String>> ans = new ArrayList<>();
        for (List<Integer> v : res) {
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
		if len(node.v) < 3 {
			node.v = append(node.v, i)
		}
	}
}

func (this *Trie) search(word string) [][]int {
	node := this
	n := len(word)
	res := make([][]int, n)
	for i, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			break
		}
		node = node.children[c]
		res[i] = node.v
	}
	return res
}

func suggestedProducts(products []string, searchWord string) [][]string {
	sort.Strings(products)
	trie := newTrie()
	for i, w := range products {
		trie.insert(w, i)
	}
	res := trie.search(searchWord)
	var ans [][]string
	for _, v := range res {
		t := []string{}
		for _, i := range v {
			t = append(t, products[i])
		}
		ans = append(ans, t)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
