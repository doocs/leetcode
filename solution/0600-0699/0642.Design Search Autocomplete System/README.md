# [642. 设计搜索自动补全系统](https://leetcode.cn/problems/design-search-autocomplete-system)

[English Version](/solution/0600-0699/0642.Design%20Search%20Autocomplete%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>为搜索引擎设计一个搜索自动补全系统。用户会输入一条语句（最少包含一个字母，以特殊字符 <code>'#'</code> 结尾）。</p>

<p>给定一个字符串数组&nbsp;<code>sentences</code>&nbsp;和一个整数数组&nbsp;<code>times</code>&nbsp;，长度都为&nbsp;<code>n</code>&nbsp;，其中&nbsp;<code>sentences[i]</code>&nbsp;是之前输入的句子，&nbsp;<code>times[i]</code>&nbsp;是该句子输入的相应次数。对于除 <code>‘#’</code>&nbsp;以外的每个输入字符，返回前 <code>3</code> 个历史热门句子，这些句子的前缀与已经输入的句子的部分相同。</p>

<p>下面是详细规则：</p>

<ul>
	<li>一条句子的热度定义为历史上用户输入这个句子的总次数。</li>
	<li>返回前 <code>3</code> 的句子需要按照热度从高到低排序（第一个是最热门的）。如果有多条热度相同的句子，请按照 ASCII 码的顺序输出（ASCII 码越小排名越前）。</li>
	<li>如果满足条件的句子个数少于 <code>3</code>&nbsp;，将它们全部输出。</li>
	<li>如果输入了特殊字符，意味着句子结束了，请返回一个空集合。</li>
</ul>

<p>实现&nbsp;<code>AutocompleteSystem</code>&nbsp;类：</p>

<ul>
	<li><code>AutocompleteSystem(String[] sentences, int[] times):</code>&nbsp;使用数组<code>sentences</code>&nbsp;和&nbsp;<code>times</code>&nbsp;对对象进行初始化。</li>
	<li><code>List&lt;String&gt; input(char c)</code>&nbsp;表示用户输入了字符&nbsp;<code>c</code>&nbsp;。
	<ul>
		<li>如果&nbsp;<code>c == '#'</code>&nbsp;，则返回空数组&nbsp;<code>[]</code> ，并将输入的语句存储在系统中。</li>
		<li>返回前 <code>3</code> 个历史热门句子，这些句子的前缀与已经输入的句子的部分相同。如果少于 <code>3</code> 个匹配项，则全部返回。</li>
	</ul>
	</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入</strong>
["AutocompleteSystem", "input", "input", "input", "input"]
[[["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]], ["i"], [" "], ["a"], ["#"]]
<b>输出</b>
[null, ["i love you", "island", "i love leetcode"], ["i love you", "i love leetcode"], [], []]

<strong>解释</strong>
AutocompleteSystem obj = new AutocompleteSystem(["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]);
obj.input("i"); // return ["i love you", "island", "i love leetcode"]. There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
obj.input(" "); // return ["i love you", "i love leetcode"]. There are only two sentences that have prefix "i ".
obj.input("a"); // return []. There are no sentences that have prefix "i a".
obj.input("#"); // return []. The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == sentences.length</code></li>
	<li><code>n == times.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= sentences[i].length &lt;= 100</code></li>
	<li><code>1 &lt;= times[i] &lt;= 50</code></li>
	<li><code>c</code>&nbsp;是小写英文字母，&nbsp;<code>'#'</code>, 或空格&nbsp;<code>' '</code></li>
	<li>每个被测试的句子将是一个以字符 <code>'#'</code>&nbsp;结尾的字符 <code>c</code> 序列。</li>
	<li>每个被测试的句子的长度范围为 <code>[1,200]</code>&nbsp;</li>
	<li>每个输入句子中的单词用单个空格隔开。</li>
	<li><code>input</code>&nbsp;最多被调用 <code>5000</code> 次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀树 + 排序/优先队列**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
