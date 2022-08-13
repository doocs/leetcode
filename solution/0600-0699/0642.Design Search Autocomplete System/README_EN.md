# [642. Design Search Autocomplete System](https://leetcode.com/problems/design-search-autocomplete-system)

[中文文档](/solution/0600-0699/0642.Design%20Search%20Autocomplete%20System/README.md)

## Description

<p>Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character <code>&#39;#&#39;</code>).</p>

<p>You are given a string array <code>sentences</code> and an integer array <code>times</code> both of length <code>n</code> where <code>sentences[i]</code> is a previously typed sentence and <code>times[i]</code> is the corresponding number of times the sentence was typed. For each input character except <code>&#39;#&#39;</code>, return the top <code>3</code> historical hot sentences that have the same prefix as the part of the sentence already typed.</p>

<p>Here are the specific rules:</p>

<ul>
	<li>The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.</li>
	<li>The returned top <code>3</code> hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same hot degree, use ASCII-code order (smaller one appears first).</li>
	<li>If less than <code>3</code> hot sentences exist, return as many as you can.</li>
	<li>When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.</li>
</ul>

<p>Implement the <code>AutocompleteSystem</code> class:</p>

<ul>
	<li><code>AutocompleteSystem(String[] sentences, int[] times)</code> Initializes the object with the <code>sentences</code> and <code>times</code> arrays.</li>
	<li><code>List&lt;String&gt; input(char c)</code> This indicates that the user typed the character <code>c</code>.
	<ul>
		<li>Returns an empty array <code>[]</code> if <code>c == &#39;#&#39;</code> and stores the inputted sentence in the system.</li>
		<li>Returns the top <code>3</code> historical hot sentences that have the same prefix as the part of the sentence already typed. If there are fewer than <code>3</code> matches, return them all.</li>
	</ul>
	</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;AutocompleteSystem&quot;, &quot;input&quot;, &quot;input&quot;, &quot;input&quot;, &quot;input&quot;]
[[[&quot;i love you&quot;, &quot;island&quot;, &quot;iroman&quot;, &quot;i love leetcode&quot;], [5, 3, 2, 2]], [&quot;i&quot;], [&quot; &quot;], [&quot;a&quot;], [&quot;#&quot;]]
<strong>Output</strong>
[null, [&quot;i love you&quot;, &quot;island&quot;, &quot;i love leetcode&quot;], [&quot;i love you&quot;, &quot;i love leetcode&quot;], [], []]

<strong>Explanation</strong>
AutocompleteSystem obj = new AutocompleteSystem([&quot;i love you&quot;, &quot;island&quot;, &quot;iroman&quot;, &quot;i love leetcode&quot;], [5, 3, 2, 2]);
obj.input(&quot;i&quot;); // return [&quot;i love you&quot;, &quot;island&quot;, &quot;i love leetcode&quot;]. There are four sentences that have prefix &quot;i&quot;. Among them, &quot;ironman&quot; and &quot;i love leetcode&quot; have same hot degree. Since &#39; &#39; has ASCII code 32 and &#39;r&#39; has ASCII code 114, &quot;i love leetcode&quot; should be in front of &quot;ironman&quot;. Also we only need to output top 3 hot sentences, so &quot;ironman&quot; will be ignored.
obj.input(&quot; &quot;); // return [&quot;i love you&quot;, &quot;i love leetcode&quot;]. There are only two sentences that have prefix &quot;i &quot;.
obj.input(&quot;a&quot;); // return []. There are no sentences that have prefix &quot;i a&quot;.
obj.input(&quot;#&quot;); // return []. The user finished the input, the sentence &quot;i a&quot; should be saved as a historical sentence in system. And the following input will be counted as a new search.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == sentences.length</code></li>
	<li><code>n == times.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= sentences[i].length &lt;= 100</code></li>
	<li><code>1 &lt;= times[i] &lt;= 50</code></li>
	<li><code>c</code> is a lowercase English letter, a hash <code>&#39;#&#39;</code>, or space <code>&#39; &#39;</code>.</li>
	<li>Each tested sentence will be a sequence of characters <code>c</code> that end with the character <code>&#39;#&#39;</code>.</li>
	<li>Each tested sentence will have a length in the range <code>[1, 200]</code>.</li>
	<li>The words in each input sentence are separated by single spaces.</li>
	<li>At most <code>5000</code> calls will be made to <code>input</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Trie:
    def __init__(self):
        self.children = [None] * 27
        self.v = 0
        self.w = ''

    def insert(self, w, t):
        node = self
        for c in w:
            idx = 26 if c == ' ' else ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.v += t
        node.w = w

    def search(self, pref):
        node = self
        for c in pref:
            idx = 26 if c == ' ' else ord(c) - ord('a')
            if node.children[idx] is None:
                return None
            node = node.children[idx]
        return node


class AutocompleteSystem:
    def __init__(self, sentences: List[str], times: List[int]):
        self.trie = Trie()
        for a, b in zip(sentences, times):
            self.trie.insert(a, b)
        self.t = []

    def input(self, c: str) -> List[str]:
        def dfs(node):
            if node is None:
                return
            if node.v:
                res.append((node.v, node.w))
            for nxt in node.children:
                dfs(nxt)

        if c == '#':
            s = ''.join(self.t)
            self.trie.insert(s, 1)
            self.t = []
            return []

        res = []
        self.t.append(c)
        node = self.trie.search(''.join(self.t))
        if node is None:
            return res
        dfs(node)
        res.sort(key=lambda x: (-x[0], x[1]))
        return [v[1] for v in res[:3]]


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)
```

### **Java**

```java
class Trie {
    Trie[] children = new Trie[27];
    int v;
    String w = "";

    void insert(String w, int t) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            int idx = c == ' ' ? 26 : c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.v += t;
        node.w = w;
    }

    Trie search(String pref) {
        Trie node = this;
        for (char c : pref.toCharArray()) {
            int idx = c == ' ' ? 26 : c - 'a';
            if (node.children[idx] == null) {
                return null;
            }
            node = node.children[idx];
        }
        return node;
    }
}

class AutocompleteSystem {
    private Trie trie = new Trie();
    private StringBuilder t = new StringBuilder();

    public AutocompleteSystem(String[] sentences, int[] times) {
        int i = 0;
        for (String s : sentences) {
            trie.insert(s, times[i++]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            trie.insert(t.toString(), 1);
            t = new StringBuilder();
            return res;
        }
        t.append(c);
        Trie node = trie.search(t.toString());
        if (node == null) {
            return res;
        }
        PriorityQueue<Trie> q = new PriorityQueue<>((a, b) -> a.v == b.v ? b.w.compareTo(a.w) : a.v - b.v);
        dfs(node, q);
        while (!q.isEmpty()) {
            res.add(0, q.poll().w);
        }
        return res;
    }

    private void dfs(Trie node, PriorityQueue q) {
        if (node == null) {
            return;
        }
        if (node.v > 0) {
            q.offer(node);
            if (q.size() > 3) {
                q.poll();
            }
        }
        for (Trie nxt : node.children) {
            dfs(nxt, q);
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
```

### **...**

```

```

<!-- tabs:end -->
