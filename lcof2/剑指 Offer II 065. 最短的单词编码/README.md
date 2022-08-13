# [剑指 Offer II 065. 最短的单词编码](https://leetcode.cn/problems/iSwD2y)

## 题目描述

<!-- 这里写题目描述 -->

<p>单词数组&nbsp;<code>words</code> 的 <strong>有效编码</strong> 由任意助记字符串 <code>s</code> 和下标数组 <code>indices</code> 组成，且满足：</p>

<ul>
	<li><code>words.length == indices.length</code></li>
	<li>助记字符串 <code>s</code> 以 <code>&#39;#&#39;</code> 字符结尾</li>
	<li>对于每个下标 <code>indices[i]</code> ，<code>s</code> 的一个从 <code>indices[i]</code> 开始、到下一个 <code>&#39;#&#39;</code> 字符结束（但不包括 <code>&#39;#&#39;</code>）的 <strong>子字符串</strong> 恰好与 <code>words[i]</code> 相等</li>
</ul>

<p>给定一个单词数组&nbsp;<code>words</code> ，返回成功对 <code>words</code> 进行编码的最小助记字符串 <code>s</code> 的长度 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = [&quot;time&quot;, &quot;me&quot;, &quot;bell&quot;]
<strong>输出：</strong>10
<strong>解释：</strong>一组有效编码为 s = <code>&quot;time#bell#&quot; 和 indices = [0, 2, 5</code>] 。
words[0] = &quot;time&quot; ，s 开始于 indices[0] = 0 到下一个 &#39;#&#39; 结束的子字符串，如加粗部分所示 &quot;<strong>time</strong>#bell#&quot;
words[1] = &quot;me&quot; ，s 开始于 indices[1] = 2 到下一个 &#39;#&#39; 结束的子字符串，如加粗部分所示 &quot;ti<strong>me</strong>#bell#&quot;
words[2] = &quot;bell&quot; ，s 开始于 indices[2] = 5 到下一个 &#39;#&#39; 结束的子字符串，如加粗部分所示 &quot;time#<strong>bell</strong>#&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = [&quot;t&quot;]
<strong>输出：</strong>2
<strong>解释：</strong>一组有效编码为 s = &quot;t#&quot; 和 indices = [0] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 2000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 7</code></li>
	<li><code>words[i]</code> 仅由小写字母组成</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 820&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/short-encoding-of-words/">https://leetcode.cn/problems/short-encoding-of-words/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀树**

题目大意：充分利用重叠的后缀，使有效编码尽可能短。

判断当前单词是否是其他单词的后缀，若是，就不用写入助记字符串中，否则需要写入并且加上一个 # 后缀。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Trie:
    def __init__(self) -> None:
        self.children = [None] * 26


class Solution:
    def minimumLengthEncoding(self, words: List[str]) -> int:
        root = Trie()
        for w in words:
            cur = root
            for c in w[::-1]:
                idx = ord(c) - ord("a")
                if cur.children[idx] == None:
                    cur.children[idx] = Trie()
                cur = cur.children[idx]
        return self.dfs(root, 1)

    def dfs(self, cur: Trie, l: int) -> int:
        isLeaf, ans = True, 0
        for i in range(26):
            if cur.children[i] != None:
                isLeaf = False
                ans += self.dfs(cur.children[i], l + 1)
        if isLeaf:
            ans += l
        return ans
```

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26

    def insert(self, w):
        node = self
        pref = True
        for c in w:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                node.children[idx] = Trie()
                pref = False
            node = node.children[idx]
        return 0 if pref else len(w) + 1


class Solution:
    def minimumLengthEncoding(self, words: List[str]) -> int:
        words.sort(key=lambda x: -len(x))
        trie = Trie()
        return sum(trie.insert(w[::-1]) for w in words)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Trie {
    Trie[] children = new Trie[26];
}

class Solution {
    public int minimumLengthEncoding(String[] words) {
        Trie root = new Trie();
        for (String w : words) {
            Trie cur = root;
            for (int i = w.length() - 1; i >= 0; i--) {
                int idx = w.charAt(i) - 'a';
                if (cur.children[idx] == null) {
                    cur.children[idx] = new Trie();
                }
                cur = cur.children[idx];
            }
        }
        return dfs(root, 1);
    }

    private int dfs(Trie cur, int l) {
        boolean isLeaf = true;
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            if (cur.children[i] != null) {
                isLeaf = false;
                ans += dfs(cur.children[i], l + 1);
            }
        }
        if (isLeaf) {
            ans += l;
        }
        return ans;
    }
}
```

```java
class Trie {
    Trie[] children = new Trie[26];

    int insert(String w) {
        Trie node = this;
        boolean pref = true;
        for (int i = w.length() - 1; i >= 0; --i) {
            int idx = w.charAt(i) - 'a';
            if (node.children[idx] == null) {
                pref = false;
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        return pref ? 0 : w.length() + 1;
    }
}

class Solution {
    public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, (a, b) -> b.length() - a.length());
        int ans = 0;
        Trie trie = new Trie();
        for (String w : words) {
            ans += trie.insert(w);
        }
        return ans;
    }
}
```

### **Go**

```go
type trie struct {
	children [26]*trie
}

func minimumLengthEncoding(words []string) int {
	root := new(trie)
	for _, w := range words {
		cur := root
		for i := len(w) - 1; i >= 0; i-- {
			if cur.children[w[i]-'a'] == nil {
				cur.children[w[i]-'a'] = new(trie)
			}
			cur = cur.children[w[i]-'a']
		}
	}
	return dfs(root, 1)
}

func dfs(cur *trie, l int) int {
	isLeaf, ans := true, 0
	for i := 0; i < 26; i++ {
		if cur.children[i] != nil {
			isLeaf = false
			ans += dfs(cur.children[i], l+1)
		}
	}
	if isLeaf {
		ans += l
	}
	return ans
}
```

```go
type Trie struct {
	children [26]*Trie
}

func newTrie() *Trie {
	return &Trie{}
}

func (this *Trie) insert(w string) int {
	node := this
	pref := true
	for i := len(w) - 1; i >= 0; i-- {
		idx := w[i] - 'a'
		if node.children[idx] == nil {
			pref = false
			node.children[idx] = newTrie()
		}
		node = node.children[idx]
	}
	if pref {
		return 0
	}
	return len(w) + 1
}

func minimumLengthEncoding(words []string) int {
	sort.Slice(words, func(i, j int) bool { return len(words[i]) > len(words[j]) })
	trie := newTrie()
	ans := 0
	for _, w := range words {
		ans += trie.insert(w)
	}
	return ans
}
```

### **C++**

```cpp
struct Trie {
    Trie* children[26] = {nullptr};
};

class Solution {
public:
    int minimumLengthEncoding(vector<string>& words) {
        auto root = new Trie();
        for (auto& w : words) {
            auto cur = root;
            for (int i = w.size() - 1; i >= 0; --i) {
                if (cur->children[w[i] - 'a'] == nullptr) {
                    cur->children[w[i] - 'a'] = new Trie();
                }
                cur = cur->children[w[i] - 'a'];
            }
        }
        return dfs(root, 1);
    }

private:
    int dfs(Trie* cur, int l) {
        bool isLeaf = true;
        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            if (cur->children[i] != nullptr) {
                isLeaf = false;
                ans += dfs(cur->children[i], l + 1);
            }
        }
        if (isLeaf) {
            ans += l;
        }
        return ans;
    }
};
```

```cpp
class Trie {
public:
    vector<Trie*> children;
    Trie() : children(26) {}

    int insert(string w) {
        Trie* node = this;
        bool pref = true;
        for (char c : w) {
            c -= 'a';
            if (!node->children[c]) {
                pref = false;
                node->children[c] = new Trie();
            }
            node = node->children[c];
        }
        return pref ? 0 : w.size() + 1;
    }
};

class Solution {
public:
    int minimumLengthEncoding(vector<string>& words) {
        sort(words.begin(), words.end(), [](string &a, string &b) {return a.size() > b.size();});
        Trie* trie = new Trie();
        int ans = 0;
        for (auto& w : words) {
            reverse(w.begin(), w.end());
            ans += trie->insert(w);
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
