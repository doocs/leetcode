# [1268. Search Suggestions System](https://leetcode.com/problems/search-suggestions-system)

[中文文档](/solution/1200-1299/1268.Search%20Suggestions%20System/README.md)

## Description

<p>You are given an array of strings <code>products</code> and a string <code>searchWord</code>.</p>

<p>Design a system that suggests at most three product names from <code>products</code> after each character of <code>searchWord</code> is typed. Suggested products should have common prefix with <code>searchWord</code>. If there are more than three products with a common prefix return the three lexicographically minimums products.</p>

<p>Return <em>a list of lists of the suggested products after each character of </em><code>searchWord</code><em> is typed</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> products = [&quot;mobile&quot;,&quot;mouse&quot;,&quot;moneypot&quot;,&quot;monitor&quot;,&quot;mousepad&quot;], searchWord = &quot;mouse&quot;
<strong>Output:</strong> [
[&quot;mobile&quot;,&quot;moneypot&quot;,&quot;monitor&quot;],
[&quot;mobile&quot;,&quot;moneypot&quot;,&quot;monitor&quot;],
[&quot;mouse&quot;,&quot;mousepad&quot;],
[&quot;mouse&quot;,&quot;mousepad&quot;],
[&quot;mouse&quot;,&quot;mousepad&quot;]
]
<strong>Explanation:</strong> products sorted lexicographically = [&quot;mobile&quot;,&quot;moneypot&quot;,&quot;monitor&quot;,&quot;mouse&quot;,&quot;mousepad&quot;]
After typing m and mo all products match and we show user [&quot;mobile&quot;,&quot;moneypot&quot;,&quot;monitor&quot;]
After typing mou, mous and mouse the system suggests [&quot;mouse&quot;,&quot;mousepad&quot;]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> products = [&quot;havana&quot;], searchWord = &quot;havana&quot;
<strong>Output:</strong> [[&quot;havana&quot;],[&quot;havana&quot;],[&quot;havana&quot;],[&quot;havana&quot;],[&quot;havana&quot;],[&quot;havana&quot;]]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> products = [&quot;bags&quot;,&quot;baggage&quot;,&quot;banner&quot;,&quot;box&quot;,&quot;cloths&quot;], searchWord = &quot;bags&quot;
<strong>Output:</strong> [[&quot;baggage&quot;,&quot;bags&quot;,&quot;banner&quot;],[&quot;baggage&quot;,&quot;bags&quot;,&quot;banner&quot;],[&quot;baggage&quot;,&quot;bags&quot;],[&quot;bags&quot;]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= products.length &lt;= 1000</code></li>
	<li><code>1 &lt;= products[i].length &lt;= 3000</code></li>
	<li><code>1 &lt;= sum(products[i].length) &lt;= 2 * 10<sup>4</sup></code></li>
	<li>All the strings of <code>products</code> are <strong>unique</strong>.</li>
	<li><code>products[i]</code> consists of lowercase English letters.</li>
	<li><code>1 &lt;= searchWord.length &lt;= 1000</code></li>
	<li><code>searchWord</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
