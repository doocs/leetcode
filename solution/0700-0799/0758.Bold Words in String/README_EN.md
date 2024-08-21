---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0758.Bold%20Words%20in%20String/README_EN.md
tags:
    - Trie
    - Array
    - Hash Table
    - String
    - String Matching
---

<!-- problem:start -->

# [758. Bold Words in String 🔒](https://leetcode.com/problems/bold-words-in-string)

[中文文档](/solution/0700-0799/0758.Bold%20Words%20in%20String/README.md)

## Description

<!-- description:start -->

<p>Given an array of keywords <code>words</code> and a string <code>s</code>, make all appearances of all keywords <code>words[i]</code> in <code>s</code> bold. Any letters between <code>&lt;b&gt;</code> and <code>&lt;/b&gt;</code> tags become bold.</p>

<p>Return <code>s</code> <em>after adding the bold tags</em>. The returned string should use the least number of tags possible, and the tags should form a valid combination.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;ab&quot;,&quot;bc&quot;], s = &quot;aabcd&quot;
<strong>Output:</strong> &quot;a&lt;b&gt;abc&lt;/b&gt;d&quot;
<strong>Explanation:</strong> Note that returning <code>&quot;a&lt;b&gt;a&lt;b&gt;b&lt;/b&gt;c&lt;/b&gt;d&quot;</code> would use more tags, so it is incorrect.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;ab&quot;,&quot;cb&quot;], s = &quot;aabcd&quot;
<strong>Output:</strong> &quot;a&lt;b&gt;ab&lt;/b&gt;cd&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>0 &lt;= words.length &lt;= 50</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>s</code> and <code>words[i]</code> consist of lowercase English letters.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Note:</strong> This question is the same as <a href="https://leetcode.com/problems/add-bold-tag-in-string/description/" target="_blank">616. Add Bold Tag in String</a>.</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Trie:
    def __init__(self):
        self.children = [None] * 128
        self.is_end = False

    def insert(self, word):
        node = self
        for c in word:
            idx = ord(c)
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True


class Solution:
    def boldWords(self, words: List[str], s: str) -> str:
        trie = Trie()
        for w in words:
            trie.insert(w)
        n = len(s)
        pairs = []
        for i in range(n):
            node = trie
            for j in range(i, n):
                idx = ord(s[j])
                if node.children[idx] is None:
                    break
                node = node.children[idx]
                if node.is_end:
                    pairs.append([i, j])
        if not pairs:
            return s
        st, ed = pairs[0]
        t = []
        for a, b in pairs[1:]:
            if ed + 1 < a:
                t.append([st, ed])
                st, ed = a, b
            else:
                ed = max(ed, b)
        t.append([st, ed])

        ans = []
        i = j = 0
        while i < n:
            if j == len(t):
                ans.append(s[i:])
                break
            st, ed = t[j]
            if i < st:
                ans.append(s[i:st])
            ans.append('<b>')
            ans.append(s[st : ed + 1])
            ans.append('</b>')
            j += 1
            i = ed + 1

        return ''.join(ans)
```

#### Java

```java
class Trie {
    Trie[] children = new Trie[128];
    boolean isEnd;

    public void insert(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.isEnd = true;
    }
}

class Solution {
    public String boldWords(String[] words, String s) {
        Trie trie = new Trie();
        for (String w : words) {
            trie.insert(w);
        }
        List<int[]> pairs = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            Trie node = trie;
            for (int j = i; j < n; ++j) {
                int idx = s.charAt(j);
                if (node.children[idx] == null) {
                    break;
                }
                node = node.children[idx];
                if (node.isEnd) {
                    pairs.add(new int[] {i, j});
                }
            }
        }
        if (pairs.isEmpty()) {
            return s;
        }
        List<int[]> t = new ArrayList<>();
        int st = pairs.get(0)[0], ed = pairs.get(0)[1];
        for (int j = 1; j < pairs.size(); ++j) {
            int a = pairs.get(j)[0], b = pairs.get(j)[1];
            if (ed + 1 < a) {
                t.add(new int[] {st, ed});
                st = a;
                ed = b;
            } else {
                ed = Math.max(ed, b);
            }
        }
        t.add(new int[] {st, ed});
        int i = 0, j = 0;
        StringBuilder ans = new StringBuilder();
        while (i < n) {
            if (j == t.size()) {
                ans.append(s.substring(i));
                break;
            }
            st = t.get(j)[0];
            ed = t.get(j)[1];
            if (i < st) {
                ans.append(s.substring(i, st));
            }
            ++j;
            ans.append("<b>");
            ans.append(s.substring(st, ed + 1));
            ans.append("</b>");
            i = ed + 1;
        }
        return ans.toString();
    }
}
```

#### C++

```cpp
class Trie {
public:
    vector<Trie*> children;
    bool isEnd;

    Trie() {
        children.resize(128);
        isEnd = false;
    }

    void insert(string word) {
        Trie* node = this;
        for (char c : word) {
            if (!node->children[c]) node->children[c] = new Trie();
            node = node->children[c];
        }
        node->isEnd = true;
    }
};

class Solution {
public:
    string boldWords(vector<string>& words, string s) {
        Trie* trie = new Trie();
        for (string w : words) trie->insert(w);
        int n = s.size();
        vector<pair<int, int>> pairs;
        for (int i = 0; i < n; ++i) {
            Trie* node = trie;
            for (int j = i; j < n; ++j) {
                int idx = s[j];
                if (!node->children[idx]) break;
                node = node->children[idx];
                if (node->isEnd) pairs.push_back({i, j});
            }
        }
        if (pairs.empty()) return s;
        vector<pair<int, int>> t;
        int st = pairs[0].first, ed = pairs[0].second;
        for (int i = 1; i < pairs.size(); ++i) {
            int a = pairs[i].first, b = pairs[i].second;
            if (ed + 1 < a) {
                t.push_back({st, ed});
                st = a, ed = b;
            } else
                ed = max(ed, b);
        }
        t.push_back({st, ed});
        string ans = "";
        int i = 0, j = 0;
        while (i < n) {
            if (j == t.size()) {
                ans += s.substr(i);
                break;
            }
            st = t[j].first, ed = t[j].second;
            if (i < st) ans += s.substr(i, st - i);
            ans += "<b>";
            ans += s.substr(st, ed - st + 1);
            ans += "</b>";
            i = ed + 1;
            ++j;
        }
        return ans;
    }
};
```

#### Go

```go
type Trie struct {
	children [128]*Trie
	isEnd    bool
}

func newTrie() *Trie {
	return &Trie{}
}

func (this *Trie) insert(word string) {
	node := this
	for _, c := range word {
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
	}
	node.isEnd = true
}

func boldWords(words []string, s string) string {
	trie := newTrie()
	for _, w := range words {
		trie.insert(w)
	}
	n := len(s)
	var pairs [][]int
	for i := range s {
		node := trie
		for j := i; j < n; j++ {
			if node.children[s[j]] == nil {
				break
			}
			node = node.children[s[j]]
			if node.isEnd {
				pairs = append(pairs, []int{i, j})
			}
		}
	}
	if len(pairs) == 0 {
		return s
	}
	var t [][]int
	st, ed := pairs[0][0], pairs[0][1]
	for i := 1; i < len(pairs); i++ {
		a, b := pairs[i][0], pairs[i][1]
		if ed+1 < a {
			t = append(t, []int{st, ed})
			st, ed = a, b
		} else {
			ed = max(ed, b)
		}
	}
	t = append(t, []int{st, ed})
	var ans strings.Builder
	i, j := 0, 0
	for i < n {
		if j == len(t) {
			ans.WriteString(s[i:])
			break
		}
		st, ed = t[j][0], t[j][1]
		if i < st {
			ans.WriteString(s[i:st])
		}
		ans.WriteString("<b>")
		ans.WriteString(s[st : ed+1])
		ans.WriteString("</b>")
		i = ed + 1
		j++
	}
	return ans.String()
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
