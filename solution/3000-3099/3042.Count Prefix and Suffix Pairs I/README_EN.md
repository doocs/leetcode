# [3042. Count Prefix and Suffix Pairs I](https://leetcode.com/problems/count-prefix-and-suffix-pairs-i)

[中文文档](/solution/3000-3099/3042.Count%20Prefix%20and%20Suffix%20Pairs%20I/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string array <code>words</code>.</p>

<p>Let&#39;s define a <strong>boolean</strong> function <code>isPrefixAndSuffix</code> that takes two strings, <code>str1</code> and <code>str2</code>:</p>

<ul>
	<li><code>isPrefixAndSuffix(str1, str2)</code> returns <code>true</code> if <code>str1</code> is <strong>both</strong> a <span data-keyword="string-prefix">prefix</span> and a <span data-keyword="string-suffix">suffix</span> of <code>str2</code>, and <code>false</code> otherwise.</li>
</ul>

<p>For example, <code>isPrefixAndSuffix(&quot;aba&quot;, &quot;ababa&quot;)</code> is <code>true</code> because <code>&quot;aba&quot;</code> is a prefix of <code>&quot;ababa&quot;</code> and also a suffix, but <code>isPrefixAndSuffix(&quot;abc&quot;, &quot;abcd&quot;)</code> is <code>false</code>.</p>

<p>Return <em>an integer denoting the <strong>number</strong> of index pairs </em><code>(i, j)</code><em> such that </em><code>i &lt; j</code><em>, and </em><code>isPrefixAndSuffix(words[i], words[j])</code><em> is </em><code>true</code><em>.</em></p>

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
	<li><code>1 &lt;= words.length &lt;= 50</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>words[i]</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1: Enumeration

We can enumerate all index pairs $(i, j)$, where $i < j$, and then determine whether `words[i]` is a prefix or suffix of `words[j]`. If it is, we increment the count.

The time complexity is $O(n^2 \times m)$, where $n$ and $m$ are the length of `words` and the maximum length of the strings, respectively.

<!-- tabs:start -->

```python
class Solution:
    def countPrefixSuffixPairs(self, words: List[str]) -> int:
        ans = 0
        for i, s in enumerate(words):
            for t in words[i + 1 :]:
                ans += t.endswith(s) and t.startswith(s)
        return ans
```

```java
class Solution {
    public int countPrefixSuffixPairs(String[] words) {
        int ans = 0;
        int n = words.length;
        for (int i = 0; i < n; ++i) {
            String s = words[i];
            for (int j = i + 1; j < n; ++j) {
                String t = words[j];
                if (t.startsWith(s) && t.endsWith(s)) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countPrefixSuffixPairs(vector<string>& words) {
        int ans = 0;
        int n = words.size();
        for (int i = 0; i < n; ++i) {
            string s = words[i];
            for (int j = i + 1; j < n; ++j) {
                string t = words[j];
                if (t.find(s) == 0 && t.rfind(s) == t.length() - s.length()) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

```go
func countPrefixSuffixPairs(words []string) (ans int) {
	for i, s := range words {
		for _, t := range words[i+1:] {
			if strings.HasPrefix(t, s) && strings.HasSuffix(t, s) {
				ans++
			}
		}
	}
	return
}
```

```ts
function countPrefixSuffixPairs(words: string[]): number {
    let ans = 0;
    for (let i = 0; i < words.length; ++i) {
        const s = words[i];
        for (const t of words.slice(i + 1)) {
            if (t.startsWith(s) && t.endsWith(s)) {
                ++ans;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

### Solution 2

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
    public int countPrefixSuffixPairs(String[] words) {
        int ans = 0;
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
    int countPrefixSuffixPairs(vector<string>& words) {
        int ans = 0;
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

func countPrefixSuffixPairs(words []string) (ans int) {
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
			ans += node.cnt
		}
		node.cnt++
	}
	return
}
```

<!-- tabs:end -->

<!-- end -->
