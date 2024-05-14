# [3045. Count Prefix and Suffix Pairs II](https://leetcode.com/problems/count-prefix-and-suffix-pairs-ii)

[中文文档](/solution/3000-3099/3045.Count%20Prefix%20and%20Suffix%20Pairs%20II/README.md)

<!-- tags:Trie,Array,String,String Matching,Hash Function,Rolling Hash -->

<!-- difficulty:Hard -->

## Description

<p>You are given a <strong>0-indexed</strong> string array <code>words</code>.</p>

<p>Let&#39;s define a <strong>boolean</strong> function <code>isPrefixAndSuffix</code> that takes two strings, <code>str1</code> and <code>str2</code>:</p>

<ul>
	<li><code>isPrefixAndSuffix(str1, str2)</code> returns <code>true</code> if <code>str1</code> is <strong>both</strong> a <span data-keyword="string-prefix">prefix</span> and a <span data-keyword="string-suffix">suffix</span> of <code>str2</code>, and <code>false</code> otherwise.</li>
</ul>

<p>For example, <code>isPrefixAndSuffix(&quot;aba&quot;, &quot;ababa&quot;)</code> is <code>true</code> because <code>&quot;aba&quot;</code> is a prefix of <code>&quot;ababa&quot;</code> and also a suffix, but <code>isPrefixAndSuffix(&quot;abc&quot;, &quot;abcd&quot;)</code> is <code>false</code>.</p>

<p>Return <em>an integer denoting the <strong>number</strong> of index pairs </em><code>(i<em>, </em>j)</code><em> such that </em><code>i &lt; j</code><em>, and </em><code>isPrefixAndSuffix(words[i], words[j])</code><em> is </em><code>true</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;aba&quot;,&quot;ababa&quot;,&quot;aa&quot;]
<strong>Output:</strong> 4
<strong>Explanation:</strong> In this example, the counted index pairs are:
i = 0 and j = 1 because isPrefixAndSuffix(&quot;a&quot;, &quot;aba&quot;) is true.
i = 0 and j = 2 because isPrefixAndSuffix(&quot;a&quot;, &quot;ababa&quot;) is true.
i = 0 and j = 3 because isPrefixAndSuffix(&quot;a&quot;, &quot;aa&quot;) is true.
i = 1 and j = 2 because isPrefixAndSuffix(&quot;aba&quot;, &quot;ababa&quot;) is true.
Therefore, the answer is 4.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;pa&quot;,&quot;papa&quot;,&quot;ma&quot;,&quot;mama&quot;]
<strong>Output:</strong> 2
<strong>Explanation:</strong> In this example, the counted index pairs are:
i = 0 and j = 1 because isPrefixAndSuffix(&quot;pa&quot;, &quot;papa&quot;) is true.
i = 2 and j = 3 because isPrefixAndSuffix(&quot;ma&quot;, &quot;mama&quot;) is true.
Therefore, the answer is 2.  </pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abab&quot;,&quot;ab&quot;]
<strong>Output:</strong> 0
<strong>Explanation: </strong>In this example, the only valid index pair is i = 0 and j = 1, and isPrefixAndSuffix(&quot;abab&quot;, &quot;ab&quot;) is false.
Therefore, the answer is 0.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10<sup>5</sup></code></li>
	<li><code>words[i]</code> consists only of lowercase English letters.</li>
	<li>The sum of the lengths of all <code>words[i]</code> does not exceed <code>5 * 10<sup>5</sup></code>.</li>
</ul>

## Solutions

### Solution 1: Trie

We can treat each string $s$ in the string array as a list of character pairs, where each character pair $(s[i], s[m - i - 1])$ represents the $i$th character pair of the prefix and suffix of string $s$.

We can use a trie to store all the character pairs, and then for each string $s$, we search for all the character pairs $(s[i], s[m - i - 1])$ in the trie, and add their counts to the answer.

The time complexity is $O(n \times m)$, and the space complexity is $O(n \times m)$. Here, $n$ and $m$ are the lengths of `words` and the maximum length of the strings, respectively.

<!-- tabs:start -->

```python
class Node:
    __slots__ = ["children", "cnt"]

    def __init__(self):
        self.children = {}
        self.cnt = 0


class Solution:
    def countPrefixSuffixPairs(self, words: List[str]) -> int:
        ans = 0
        trie = Node()
        for s in words:
            node = trie
            for p in zip(s, reversed(s)):
                if p not in node.children:
                    node.children[p] = Node()
                node = node.children[p]
                ans += node.cnt
            node.cnt += 1
        return ans
```

```java
class Node {
    Map<Integer, Node> children = new HashMap<>();
    int cnt;
}

class Solution {
    public long countPrefixSuffixPairs(String[] words) {
        long ans = 0;
        Node trie = new Node();
        for (String s : words) {
            Node node = trie;
            int m = s.length();
            for (int i = 0; i < m; ++i) {
                int p = s.charAt(i) * 32 + s.charAt(m - i - 1);
                node.children.putIfAbsent(p, new Node());
                node = node.children.get(p);
                ans += node.cnt;
            }
            ++node.cnt;
        }
        return ans;
    }
}
```

```cpp
class Node {
public:
    unordered_map<int, Node*> children;
    int cnt;

    Node()
        : cnt(0) {}
};

class Solution {
public:
    long long countPrefixSuffixPairs(vector<string>& words) {
        long long ans = 0;
        Node* trie = new Node();
        for (const string& s : words) {
            Node* node = trie;
            int m = s.length();
            for (int i = 0; i < m; ++i) {
                int p = s[i] * 32 + s[m - i - 1];
                if (node->children.find(p) == node->children.end()) {
                    node->children[p] = new Node();
                }
                node = node->children[p];
                ans += node->cnt;
            }
            ++node->cnt;
        }
        return ans;
    }
};
```

```go
type Node struct {
	children map[int]*Node
	cnt      int
}

func countPrefixSuffixPairs(words []string) (ans int64) {
	trie := &Node{children: make(map[int]*Node)}
	for _, s := range words {
		node := trie
		m := len(s)
		for i := 0; i < m; i++ {
			p := int(s[i])*32 + int(s[m-i-1])
			if _, ok := node.children[p]; !ok {
				node.children[p] = &Node{children: make(map[int]*Node)}
			}
			node = node.children[p]
			ans += int64(node.cnt)
		}
		node.cnt++
	}
	return
}
```

```ts
class Node {
    children: Map<number, Node> = new Map<number, Node>();
    cnt: number = 0;
}

function countPrefixSuffixPairs(words: string[]): number {
    let ans: number = 0;
    const trie: Node = new Node();
    for (const s of words) {
        let node: Node = trie;
        const m: number = s.length;
        for (let i: number = 0; i < m; ++i) {
            const p: number = s.charCodeAt(i) * 32 + s.charCodeAt(m - i - 1);
            if (!node.children.has(p)) {
                node.children.set(p, new Node());
            }
            node = node.children.get(p)!;
            ans += node.cnt;
        }
        ++node.cnt;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
