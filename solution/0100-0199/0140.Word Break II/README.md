---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0140.Word%20Break%20II/README.md
tags:
    - 字典树
    - 记忆化搜索
    - 数组
    - 哈希表
    - 字符串
    - 动态规划
    - 回溯
---

# [140. 单词拆分 II](https://leetcode.cn/problems/word-break-ii)

[English Version](/solution/0100-0199/0140.Word%20Break%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串 <code>s</code> 和一个字符串字典<meta charset="UTF-8" />&nbsp;<code>wordDict</code>&nbsp;，在字符串<meta charset="UTF-8" />&nbsp;<code>s</code>&nbsp;中增加空格来构建一个句子，使得句子中所有的单词都在词典中。<strong>以任意顺序</strong> 返回所有这些可能的句子。</p>

<p><strong>注意：</strong>词典中的同一个单词可能在分段中被重复使用多次。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入:</strong>s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
<strong>输出:</strong>["cats and dog","cat sand dog"]
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入:</strong>s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
<strong>输出:</strong>["pine apple pen apple","pineapple pen apple","pine applepen apple"]
<strong>解释:</strong> 注意你可以重复使用字典中的单词。
</pre>

<p><strong class="example">示例&nbsp;3：</strong></p>

<pre>
<strong>输入:</strong>s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
<strong>输出:</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 20</code></li>
	<li><code>1 &lt;= wordDict.length &lt;= 1000</code></li>
	<li><code>1 &lt;= wordDict[i].length &lt;= 10</code></li>
	<li><code>s</code>&nbsp;和&nbsp;<code>wordDict[i]</code>&nbsp;仅有小写英文字母组成</li>
	<li><code>wordDict</code>&nbsp;中所有字符串都 <strong>不同</strong></li>
</ul>

## 解法

### 方法一：前缀树 + DFS

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

    def search(self, word):
        node = self
        for c in word:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                return False
            node = node.children[idx]
        return node.is_end


class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> List[str]:
        def dfs(s):
            if not s:
                return [[]]
            res = []
            for i in range(1, len(s) + 1):
                if trie.search(s[:i]):
                    for v in dfs(s[i:]):
                        res.append([s[:i]] + v)
            return res

        trie = Trie()
        for w in wordDict:
            trie.insert(w)
        ans = dfs(s)
        return [' '.join(v) for v in ans]
```

```java
class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd;

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

    boolean search(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return false;
            }
            node = node.children[c];
        }
        return node.isEnd;
    }
}

class Solution {
    private Trie trie = new Trie();

    public List<String> wordBreak(String s, List<String> wordDict) {
        for (String w : wordDict) {
            trie.insert(w);
        }
        List<List<String>> res = dfs(s);
        return res.stream().map(e -> String.join(" ", e)).collect(Collectors.toList());
    }

    private List<List<String>> dfs(String s) {
        List<List<String>> res = new ArrayList<>();
        if ("".equals(s)) {
            res.add(new ArrayList<>());
            return res;
        }
        for (int i = 1; i <= s.length(); ++i) {
            if (trie.search(s.substring(0, i))) {
                for (List<String> v : dfs(s.substring(i))) {
                    v.add(0, s.substring(0, i));
                    res.add(v);
                }
            }
        }
        return res;
    }
}
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
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
	}
	node.isEnd = true
}
func (this *Trie) search(word string) bool {
	node := this
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			return false
		}
		node = node.children[c]
	}
	return node.isEnd
}

func wordBreak(s string, wordDict []string) []string {
	trie := newTrie()
	for _, w := range wordDict {
		trie.insert(w)
	}
	var dfs func(string) [][]string
	dfs = func(s string) [][]string {
		res := [][]string{}
		if len(s) == 0 {
			res = append(res, []string{})
			return res
		}
		for i := 1; i <= len(s); i++ {
			if trie.search(s[:i]) {
				for _, v := range dfs(s[i:]) {
					v = append([]string{s[:i]}, v...)
					res = append(res, v)
				}
			}
		}
		return res
	}
	res := dfs(s)
	ans := []string{}
	for _, v := range res {
		ans = append(ans, strings.Join(v, " "))
	}
	return ans
}
```

```cs
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

class Node
{
    public int Index1 { get; set; }
    public int Index2 { get; set; }
}

public class Solution {
    public IList<string> WordBreak(string s, IList<string> wordDict) {
        var paths = new List<Tuple<int, string>>[s.Length + 1];
        paths[s.Length] = new List<Tuple<int, string>> { Tuple.Create(-1, (string)null) };
        var wordDictGroup = wordDict.GroupBy(word => word.Length);
        for (var i = s.Length - 1; i >= 0; --i)
        {
            paths[i] = new List<Tuple<int, string>>();
            foreach (var wordGroup in wordDictGroup)
            {
                var wordLength = wordGroup.Key;
                if (i + wordLength <= s.Length && paths[i + wordLength].Count > 0)
                {
                    foreach (var word in wordGroup)
                    {
                        if (s.Substring(i, wordLength) == word)
                        {
                            paths[i].Add(Tuple.Create(i + wordLength, word));
                        }
                    }
                }
            }
        }

        return GenerateResults(paths);
    }

    private IList<string> GenerateResults(List<Tuple<int, string>>[] paths)
    {
        var results = new List<string>();
        var sb = new StringBuilder();
        var stack = new Stack<Node>();
        stack.Push(new Node());
        while (stack.Count > 0)
        {
            var node = stack.Peek();
            if (node.Index1 == paths.Length - 1 || node.Index2 == paths[node.Index1].Count)
            {
                if (node.Index1 == paths.Length - 1)
                {
                    results.Add(sb.ToString());
                }
                stack.Pop();
                if (stack.Count > 0)
                {
                    var parent = stack.Peek();
                    var length = paths[parent.Index1][parent.Index2 - 1].Item2.Length;
                    if (length < sb.Length) ++length;
                    sb.Remove(sb.Length - length, length);
                }
            }
            else
            {
                var newNode = new Node { Index1 = paths[node.Index1][node.Index2].Item1, Index2 = 0 };
                if (sb.Length != 0)
                {
                    sb.Append(' ');
                }
                sb.Append(paths[node.Index1][node.Index2].Item2);
                stack.Push(newNode);
                ++node.Index2;
            }
        }
        return results;
    }
}
```

<!-- tabs:end -->

<!-- end -->
