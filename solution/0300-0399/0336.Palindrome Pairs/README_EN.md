---
comments: true
difficulty: Hard
tags:
    - Trie
    - Array
    - Hash Table
    - String
    - Hash Function
---

<!-- problem:start -->

# [336. Palindrome Pairs](https://leetcode.com/problems/palindrome-pairs)

[中文文档](/solution/0300-0399/0336.Palindrome%20Pairs/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> array of <strong>unique</strong> strings <code>words</code>.</p>

<p>A <strong>palindrome pair</strong> is a pair of integers <code>(i, j)</code> such that:</p>

<ul>
	<li><code>0 &lt;= i, j &lt; words.length</code>,</li>
	<li><code>i != j</code>, and</li>
	<li><code>words[i] + words[j]</code> (the concatenation of the two strings) is a <span data-keyword="palindrome-string">palindrome</span>.</li>
</ul>

<p>Return <em>an array of all the <strong>palindrome pairs</strong> of </em><code>words</code>.</p>

<p>You must write an algorithm with&nbsp;<code>O(sum of words[i].length)</code>&nbsp;runtime complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abcd&quot;,&quot;dcba&quot;,&quot;lls&quot;,&quot;s&quot;,&quot;sssll&quot;]
<strong>Output:</strong> [[0,1],[1,0],[3,2],[2,4]]
<strong>Explanation:</strong> The palindromes are [&quot;abcddcba&quot;,&quot;dcbaabcd&quot;,&quot;slls&quot;,&quot;llssssll&quot;]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;bat&quot;,&quot;tab&quot;,&quot;cat&quot;]
<strong>Output:</strong> [[0,1],[1,0]]
<strong>Explanation:</strong> The palindromes are [&quot;battab&quot;,&quot;tabbat&quot;]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;&quot;]
<strong>Output:</strong> [[0,1],[1,0]]
<strong>Explanation:</strong> The palindromes are [&quot;a&quot;,&quot;a&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 5000</code></li>
	<li><code>0 &lt;= words[i].length &lt;= 300</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Map

<!-- thinking:start -->

> **Thinking**
>
> Two words should concatenate to a palindrome. Testing every ordered pair is too slow. Split a word: if one side is already a palindrome, the reverse of the other side, if present, completes a pair.
>
> Index every word in a hash map. For each cut of $w$, a palindromic prefix looks up the reversed suffix, and a palindromic suffix looks up the reversed prefix. The empty cut is used on only one side. The code checks both sides at every cut.

<!-- thinking:end -->

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

### Solution 2

<!-- thinking:start -->

> **Thinking**
>
> Method 1 builds a reversed string for every cut. Inserting words into a trie lets us walk the other word backwards and read an index at a node, without allocating the reverse. When the remaining side is a palindrome we take the stored index; the matches are the same.

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
