# [1065. 字符串的索引对 🔒](https://leetcode.cn/problems/index-pairs-of-a-string)

[English Version](/solution/1000-1099/1065.Index%20Pairs%20of%20a%20String/README_EN.md)

<!-- tags:字典树,数组,字符串,排序 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给出&nbsp;<strong>字符串 </strong><code>text</code> 和&nbsp;<strong>字符串列表</strong> <code>words</code>, 返回所有的索引对 <code>[i, j]</code> 使得在索引对范围内的子字符串 <code>text[i]...text[j]</code>（包括&nbsp;<code>i</code>&nbsp;和&nbsp;<code>j</code>）属于字符串列表 <code>words</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入: </strong>text = &quot;thestoryofleetcodeandme&quot;, words = [&quot;story&quot;,&quot;fleet&quot;,&quot;leetcode&quot;]
<strong>输出: </strong>[[3,7],[9,13],[10,17]]
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入: </strong>text = &quot;ababa&quot;, words = [&quot;aba&quot;,&quot;ab&quot;]
<strong>输出: </strong>[[0,1],[0,2],[2,3],[2,4]]
<strong>解释: 
</strong>注意，返回的配对可以有交叉，比如，&quot;aba&quot; 既在 [0,2] 中也在 [2,4] 中
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ol>
	<li>所有字符串都只包含小写字母。</li>
	<li>保证 <code>words</code> 中的字符串无重复。</li>
	<li><code>1 &lt;= text.length &lt;= 100</code></li>
	<li><code>1 &lt;= words.length &lt;= 20</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 50</code></li>
	<li>按序返回索引对 <code>[i,j]</code>（即，按照索引对的第一个索引进行排序，当第一个索引对相同时按照第二个索引对排序）。</li>
</ol>

## 解法

### 方法一：暴力枚举

<!-- tabs:start -->

```python
class Solution:
    def indexPairs(self, text: str, words: List[str]) -> List[List[int]]:
        words = set(words)
        n = len(text)
        return [
            [i, j] for i in range(n) for j in range(i, n) if text[i : j + 1] in words
        ]
```

```java
class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd = false;

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
    public int[][] indexPairs(String text, String[] words) {
        Trie trie = new Trie();
        for (String w : words) {
            trie.insert(w);
        }
        int n = text.length();
        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            Trie node = trie;
            for (int j = i; j < n; ++j) {
                int idx = text.charAt(j) - 'a';
                if (node.children[idx] == null) {
                    break;
                }
                node = node.children[idx];
                if (node.isEnd) {
                    ans.add(new int[] {i, j});
                }
            }
        }
        return ans.toArray(new int[ans.size()][2]);
    }
}
```

```cpp
class Trie {
public:
    vector<Trie*> children;
    bool isEnd = false;

    Trie() {
        children.resize(26);
    }

    void insert(string word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            if (!node->children[c]) node->children[c] = new Trie();
            node = node->children[c];
        }
        node->isEnd = true;
    }
};

class Solution {
public:
    vector<vector<int>> indexPairs(string text, vector<string>& words) {
        Trie* trie = new Trie();
        for (auto w : words) trie->insert(w);
        int n = text.size();
        vector<vector<int>> ans;
        for (int i = 0; i < n; ++i) {
            Trie* node = trie;
            for (int j = i; j < n; ++j) {
                int idx = text[j] - 'a';
                if (!node->children[idx]) break;
                node = node->children[idx];
                if (node->isEnd) ans.push_back({i, j});
            }
        }
        return ans;
    }
};
```

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
		idx := int(c - 'a')
		if node.children[idx] == nil {
			node.children[idx] = newTrie()
		}
		node = node.children[idx]
	}
	node.isEnd = true
}

func indexPairs(text string, words []string) [][]int {
	trie := newTrie()
	for _, w := range words {
		trie.insert(w)
	}
	n := len(text)
	var ans [][]int
	for i := range text {
		node := trie
		for j := i; j < n; j++ {
			idx := int(text[j] - 'a')
			if node.children[idx] == nil {
				break
			}
			node = node.children[idx]
			if node.isEnd {
				ans = append(ans, []int{i, j})
			}
		}
	}
	return ans
}
```

<!-- tabs:end -->

### 方法二：前缀树

相似题目：

-   [616. 给字符串添加加粗标签](https://github.com/doocs/leetcode/blob/main/solution/0600-0699/0616.Add%20Bold%20Tag%20in%20String/README.md)
-   [758. 字符串中的加粗单词](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0758.Bold%20Words%20in%20String/README.md)

<!-- tabs:start -->

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.is_end = False

    def insert(self, word):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True


class Solution:
    def indexPairs(self, text: str, words: List[str]) -> List[List[int]]:
        trie = Trie()
        for w in words:
            trie.insert(w)
        n = len(text)
        ans = []
        for i in range(n):
            node = trie
            for j in range(i, n):
                idx = ord(text[j]) - ord('a')
                if node.children[idx] is None:
                    break
                node = node.children[idx]
                if node.is_end:
                    ans.append([i, j])
        return ans
```

<!-- tabs:end -->

<!-- end -->
