# [527. Word Abbreviation](https://leetcode.com/problems/word-abbreviation)

[中文文档](/solution/0500-0599/0527.Word%20Abbreviation/README.md)

## Description

<p>Given an array of <strong>distinct</strong> strings <code>words</code>, return <em>the minimal possible <strong>abbreviations</strong> for every word</em>.</p>

<p>The following are the rules for a string abbreviation:</p>

<ol>
	<li>The <strong>initial</strong> abbreviation for each word is: the first character, then the number of characters in between, followed by the last character.</li>
	<li>If more than one word shares the <strong>same</strong> abbreviation, then perform the following operation:
	<ul>
		<li><strong>Increase</strong> the prefix (characters in the first part) of each of their abbreviations by <code>1</code>.
		<ul>
			<li>For example, say you start with the words <code>[&quot;abcdef&quot;,&quot;abndef&quot;]</code> both initially abbreviated as <code>&quot;a4f&quot;</code>. Then, a sequence of operations would be <code>[&quot;a4f&quot;,&quot;a4f&quot;]</code> -&gt; <code>[&quot;ab3f&quot;,&quot;ab3f&quot;]</code> -&gt; <code>[&quot;abc2f&quot;,&quot;abn2f&quot;]</code>.</li>
		</ul>
		</li>
		<li>This operation is repeated until every abbreviation is <strong>unique</strong>.</li>
	</ul>
	</li>
	<li>At the end, if an abbreviation did not make a word shorter, then keep it as the original word.</li>
</ol>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> words = ["like","god","internal","me","internet","interval","intension","face","intrusion"]
<strong>Output:</strong> ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> words = ["aa","aaa"]
<strong>Output:</strong> ["aa","aaa"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 400</code></li>
	<li><code>2 &lt;= words[i].length &lt;= 400</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
	<li>All the strings of <code>words</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
