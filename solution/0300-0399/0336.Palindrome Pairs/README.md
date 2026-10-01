---
comments: true
difficulty: 困难
tags:
    - 字典树
    - 数组
    - 哈希表
    - 字符串
    - 哈希函数
---

<!-- problem:start -->

# [336. 回文对](https://leetcode.cn/problems/palindrome-pairs)

[English Version](/solution/0300-0399/0336.Palindrome%20Pairs/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个由唯一字符串构成的 <strong>0 索引&nbsp;</strong>数组 <code>words</code>&nbsp;。</p>

<p><strong>回文对</strong> 是一对整数 <code>(i, j)</code> ，满足以下条件：</p>

<ul>
	<li><code>0 &lt;= i, j &lt; words.length</code>，</li>
	<li><code>i != j</code> ，并且</li>
	<li><code>words[i] + words[j]</code>（两个字符串的连接）是一个<span data-keyword="palindrome-string">回文串</span>。</li>
</ul>

<p>返回一个数组，它包含&nbsp;<code>words</code> 中所有满足 <strong>回文对</strong> 条件的字符串。</p>

<p>你必须设计一个时间复杂度为 <code>O(sum of words[i].length)</code> 的算法。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["abcd","dcba","lls","s","sssll"]
<strong>输出：</strong>[[0,1],[1,0],[3,2],[2,4]] 
<strong>解释：</strong>可拼接成的回文串为 <code>["dcbaabcd","abcddcba","slls","llssssll"]</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["bat","tab","cat"]
<strong>输出：</strong>[[0,1],[1,0]] 
<strong>解释：</strong>可拼接成的回文串为 <code>["battab","tabbat"]</code></pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = ["a",""]
<strong>输出：</strong>[[0,1],[1,0]]
</pre>

&nbsp;

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 5000</code></li>
	<li><code>0 &lt;= words[i].length &lt;= 300</code></li>
	<li><code>words[i]</code> 由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

<!-- thinking:start -->

> **思考**
>
> 两词拼接为回文。若枚举所有有序对再检测，词数与长度乘积过大。把一词切开，若一侧已是回文，则另一侧的逆串若在词表中即可配对。
>
> 哈希表记下每个词的下标。对 $w$ 的每个切点，前缀为回文则找后缀逆，后缀为回文则找前缀逆；切点 $j=0$ 只计一侧以免空串重复。代码按切点同时检查两侧。

<!-- thinking:end -->

用哈希表记下每个单词的下标。枚举单词 $w$ 的切分位置 $j$，记前缀 $a=w[:j]$、后缀 $b=w[j:]$。

- 若后缀 $b$ 是回文，且前缀 $a$ 的逆序串在哈希表中，则该逆序串接在 $w$ 左侧后构成回文。
- 若 $j>0$ 且前缀 $a$ 是回文，且后缀 $b$ 的逆序串在哈希表中，则该逆序串接在 $w$ 右侧后构成回文。$j=0$ 时前缀为空，不再从这一侧配对，避免空串被重复统计。

时间复杂度 $O(\sum |w_i|^2)$，空间复杂度 $O(\sum |w_i|)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def palindromePairs(self, words: List[str]) -> List[List[int]]:
        d = {w: i for i, w in enumerate(words)}
        ans = []
        for i, w in enumerate(words):
            for j in range(len(w) + 1):
                a, b = w[:j], w[j:]
                ra, rb = a[::-1], b[::-1]
                if ra in d and d[ra] != i and b == rb:
                    ans.append([i, d[ra]])
                if j and rb in d and d[rb] != i and a == ra:
                    ans.append([d[rb], i])
        return ans
```

#### Java

```java
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> d = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            d.put(words[i], i);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            String w = words[i];
            int m = w.length();
            for (int j = 0; j <= m; ++j) {
                String a = w.substring(0, j);
                String b = w.substring(j);
                String ra = new StringBuilder(a).reverse().toString();
                String rb = new StringBuilder(b).reverse().toString();
                if (d.containsKey(ra) && d.get(ra) != i && b.equals(rb)) {
                    ans.add(Arrays.asList(i, d.get(ra)));
                }
                if (j > 0 && d.containsKey(rb) && d.get(rb) != i && a.equals(ra)) {
                    ans.add(Arrays.asList(d.get(rb), i));
                }
            }
        }
        return ans;
    }
}
```

#### Go

```go
func palindromePairs(words []string) [][]int {
	d := map[string]int{}
	for i, w := range words {
		d[w] = i
	}
	var ans [][]int
	for i, w := range words {
		for j := 0; j <= len(w); j++ {
			a, b := w[:j], w[j:]
			ra, rb := reverse(a), reverse(b)
			if k, ok := d[ra]; ok && k != i && b == rb {
				ans = append(ans, []int{i, k})
			}
			if j > 0 {
				if k, ok := d[rb]; ok && k != i && a == ra {
					ans = append(ans, []int{k, i})
				}
			}
		}
	}
	return ans
}

func reverse(s string) string {
	b := []byte(s)
	for i, j := 0, len(b)-1; i < j; i, j = i+1, j-1 {
		b[i], b[j] = b[j], b[i]
	}
	return string(b)
}
```

#### C#

```cs
public class Solution {
    public IList<IList<int>> PalindromePairs(string[] words) {
        var results = new List<IList<int>>();
        var reverseDict = words.Select((w, i) => new {Word = w, Index = i}).ToDictionary(w => new string(w.Word.Reverse().ToArray()), w => w.Index);

        for (var i = 0; i < words.Length; ++i) {
            var word = words[i];
            for (var j = 0; j <= word.Length; ++j) {
                if (j > 0 && IsPalindrome(word, 0, j - 1)) {
                    var suffix = word.Substring(j);
                    int pairIndex;
                    if (reverseDict.TryGetValue(suffix, out pairIndex) && i != pairIndex) {
                        results.Add(new [] { pairIndex, i});
                    }
                }
                if (IsPalindrome(word, j, word.Length - 1)) {
                    var prefix = word.Substring(0, j);
                    int pairIndex;
                    if (reverseDict.TryGetValue(prefix, out pairIndex) && i != pairIndex) {
                        results.Add(new [] { i, pairIndex});
                    }
                }
            }
        }

        return results;
    }

    private bool IsPalindrome(string word, int startIndex, int endIndex) {
        var i = startIndex;
        var j = endIndex;
        while (i < j) {
            if (word[i] != word[j]) return false;
            ++i;
            --j;
        }
        return true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：前缀树

<!-- thinking:start -->

> **思考**
>
> 方法一对每个切点在哈希表中查完整逆串。把词插入前缀树后，可沿另一词反向走字符，在结点处读取下标，避免每次构造整段逆串。后缀（前缀）为回文时在对应深度取树中词号，语义与方法一相同。

<!-- thinking:end -->

<!-- tabs:start -->

#### Java

```java
class Trie {
    Trie[] children = new Trie[26];
    Integer v;

    void insert(String w, int i) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.v = i;
    }

    Integer search(String w, int i, int j) {
        Trie node = this;
        for (int k = j; k >= i; --k) {
            int idx = w.charAt(k) - 'a';
            if (node.children[idx] == null) {
                return null;
            }
            node = node.children[idx];
        }
        return node.v;
    }
}

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Trie trie = new Trie();
        int n = words.length;
        for (int i = 0; i < n; ++i) {
            trie.insert(words[i], i);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            String w = words[i];
            int m = w.length();
            for (int j = 0; j <= m; ++j) {
                if (isPalindrome(w, j, m - 1)) {
                    Integer k = trie.search(w, 0, j - 1);
                    if (k != null && k != i) {
                        ans.add(Arrays.asList(i, k));
                    }
                }
                if (j != 0 && isPalindrome(w, 0, j - 1)) {
                    Integer k = trie.search(w, j, m - 1);
                    if (k != null && k != i) {
                        ans.add(Arrays.asList(k, i));
                    }
                }
            }
        }
        return ans;
    }

    // TLE
    // private boolean isPalindrome(String w, int i, int j) {
    //     for (; i < j; ++i, --j) {
    //         if (w.charAt(i) != w.charAt(j)) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    private boolean isPalindrome(String w, int start, int end) {
        int i = start, j = end;
        for (; i < j; ++i, --j) {
            if (w.charAt(i) != w.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
