# [616. 给字符串添加加粗标签 🔒](https://leetcode.cn/problems/add-bold-tag-in-string)

[English Version](/solution/0600-0699/0616.Add%20Bold%20Tag%20in%20String/README_EN.md)

<!-- tags:字典树,数组,哈希表,字符串,字符串匹配 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定字符串 <code>s</code> 和字符串数组 <code>words</code>。</p>

<p>对于 <code>s</code> 内部的子字符串，若其存在于 <code>words</code> 数组中， 则通过添加闭合的粗体标签<meta charset="UTF-8" />&nbsp;<code>&lt;b&gt;</code>&nbsp;和&nbsp;<code>&lt;/b&gt;</code>&nbsp;进行加粗标记。</p>

<ul>
	<li>如果两个这样的子字符串重叠，你应该仅使用一对闭合的粗体标签将它们包围起来。</li>
	<li>如果被粗体标签包围的两个子字符串是连续的，你应该将它们合并。</li>
</ul>

<p>返回添加加粗标签后的字符串 <code>s</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> s = "abcxyz123", words = ["abc","123"]
<strong>输出：</strong>"&lt;b&gt;abc&lt;/b&gt;xyz&lt;b&gt;123&lt;/b&gt;"
<strong>解释：</strong>两个单词字符串是 s 的子字符串，如下所示: "abcxyz123"。
我们在每个子字符串之前添加&lt;b&gt;，在每个子字符串之后添加&lt;/b&gt;。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aaabbb", words = ["aa","b"]
<strong>输出：</strong>"&lt;b&gt;aaabbb&lt;/b&gt;"
<strong>解释：</strong>
"aa"作为子字符串出现了两次: "<u>aa</u>abbb" 和 "a<u>aa</u>bbb"。
"b"作为子字符串出现了三次: "aaa<u>b</u>bb"、"aaab<u>b</u>b" 和 "aaabb<u>b</u>"。
我们在每个子字符串之前添加&lt;b&gt;，在每个子字符串之后添加&lt;/b&gt;: "&lt;b&gt;a&lt;b&gt;a&lt;/b&gt;a&lt;/b&gt;&lt;b&gt;b&lt;/b&gt;&lt;b&gt;b&lt;/b&gt;&lt;b&gt;b&lt;/b&gt;"。
由于前两个&lt;b&gt;重叠，把它们合并得到: "&lt;b&gt;aaa&lt;/b&gt;&lt;b&gt;b&lt;/b&gt;&lt;b&gt;b&lt;/b&gt;&lt;b&gt;b&lt;/b&gt;"。
由于现在这四个&lt;b&gt;是连续的，把它们合并得到: "&lt;b&gt;aaabbb&lt;/b&gt;"。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>0 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 1000</code></li>
	<li><code>s</code> 和 <code>words[i]</code> 由英文字母和数字组成</li>
	<li><code>words</code> 中的所有值 <strong>互不相同</strong></li>
</ul>

<p>&nbsp;</p>

<p><strong>注：</strong>此题与「758 - 字符串中的加粗单词」相同 - <a href="https://leetcode.cn/problems/bold-words-in-string">https://leetcode.cn/problems/bold-words-in-string</a></p>

<p>&nbsp;</p>

## 解法

### 方法一：前缀树 + 区间合并

相似题目：

-   [1065. 字符串的索引对](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1065.Index%20Pairs%20of%20a%20String/README.md)
-   [758. 字符串中的加粗单词](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0758.Bold%20Words%20in%20String/README.md)

<!-- tabs:start -->

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
    def addBoldTag(self, s: str, words: List[str]) -> str:
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
    public String addBoldTag(String s, String[] words) {
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
    string addBoldTag(string s, vector<string>& words) {
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

func addBoldTag(s string, words []string) string {
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

<!-- end -->
