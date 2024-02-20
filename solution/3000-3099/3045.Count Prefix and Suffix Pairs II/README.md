# [3045. 统计前后缀下标对 II](https://leetcode.cn/problems/count-prefix-and-suffix-pairs-ii)

[English Version](/solution/3000-3099/3045.Count%20Prefix%20and%20Suffix%20Pairs%20II/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串数组 <code>words</code> 。</p>

<p>定义一个 <strong>布尔 </strong>函数 <code>isPrefixAndSuffix</code> ，它接受两个字符串参数 <code>str1</code> 和 <code>str2</code> ：</p>

<ul>
	<li>当 <code>str1</code> 同时是 <code>str2</code> 的前缀（<span data-keyword="string-prefix">prefix</span>）和后缀（<span data-keyword="string-suffix">suffix</span>）时，<code>isPrefixAndSuffix(str1, str2)</code> 返回 <code>true</code>，否则返回 <code>false</code>。</li>
</ul>

<p>例如，<code>isPrefixAndSuffix("aba", "ababa")</code> 返回 <code>true</code>，因为 <code>"aba"</code> 既是 <code>"ababa"</code> 的前缀，也是 <code>"ababa"</code> 的后缀，但是 <code>isPrefixAndSuffix("abc", "abcd")</code> 返回<code> false</code>。</p>

<p>以整数形式，返回满足 <code>i &lt; j</code> 且 <code>isPrefixAndSuffix(words[i], words[j])</code> 为 <code>true</code> 的下标对 <code>(i, j)</code> 的<strong> 数量 </strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["a","aba","ababa","aa"]
<strong>输出：</strong>4
<strong>解释：</strong>在本示例中，计数的下标对包括：
i = 0 且 j = 1 ，因为 isPrefixAndSuffix("a", "aba") 为 true 。
i = 0 且 j = 2 ，因为 isPrefixAndSuffix("a", "ababa") 为 true 。
i = 0 且 j = 3 ，因为 isPrefixAndSuffix("a", "aa") 为 true 。
i = 1 且 j = 2 ，因为 isPrefixAndSuffix("aba", "ababa") 为 true 。
因此，答案是 4 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["pa","papa","ma","mama"]
<strong>输出：</strong>2
<strong>解释：</strong>在本示例中，计数的下标对包括：
i = 0 且 j = 1 ，因为 isPrefixAndSuffix("pa", "papa") 为 true 。
i = 2 且 j = 3 ，因为 isPrefixAndSuffix("ma", "mama") 为 true 。
因此，答案是 2 。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = ["abab","ab"]
<strong>输出：</strong>0
<strong>解释：</strong>在本示例中，唯一有效的下标对是 i = 0 且 j = 1 ，但是 isPrefixAndSuffix("abab", "ab") 为 false 。
因此，答案是 0 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10<sup>5</sup></code></li>
	<li><code>words[i]</code> 仅由小写英文字母组成。</li>
	<li>所有 <code>words[i]</code> 的长度之和不超过 <code>5 * 10<sup>5</sup></code> 。</li>
</ul>

## 解法

### 方法一

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
