# [820. Short Encoding of Words](https://leetcode.com/problems/short-encoding-of-words)

[中文文档](/solution/0800-0899/0820.Short%20Encoding%20of%20Words/README.md)

<!-- tags:Trie,Array,Hash Table,String -->

<!-- difficulty:Medium -->

## Description

<p>A <strong>valid encoding</strong> of an array of <code>words</code> is any reference string <code>s</code> and array of indices <code>indices</code> such that:</p>

<ul>
	<li><code>words.length == indices.length</code></li>
	<li>The reference string <code>s</code> ends with the <code>&#39;#&#39;</code> character.</li>
	<li>For each index <code>indices[i]</code>, the <strong>substring</strong> of <code>s</code> starting from <code>indices[i]</code> and up to (but not including) the next <code>&#39;#&#39;</code> character is equal to <code>words[i]</code>.</li>
</ul>

<p>Given an array of <code>words</code>, return <em>the <strong>length of the shortest reference string</strong> </em><code>s</code><em> possible of any <strong>valid encoding</strong> of </em><code>words</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;time&quot;, &quot;me&quot;, &quot;bell&quot;]
<strong>Output:</strong> 10
<strong>Explanation:</strong> A valid encoding would be s = <code>&quot;time#bell#&quot; and indices = [0, 2, 5</code>].
words[0] = &quot;time&quot;, the substring of s starting from indices[0] = 0 to the next &#39;#&#39; is underlined in &quot;<u>time</u>#bell#&quot;
words[1] = &quot;me&quot;, the substring of s starting from indices[1] = 2 to the next &#39;#&#39; is underlined in &quot;ti<u>me</u>#bell#&quot;
words[2] = &quot;bell&quot;, the substring of s starting from indices[2] = 5 to the next &#39;#&#39; is underlined in &quot;time#<u>bell</u>#&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;t&quot;]
<strong>Output:</strong> 2
<strong>Explanation:</strong> A valid encoding would be s = &quot;t#&quot; and indices = [0].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 2000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 7</code></li>
	<li><code>words[i]</code> consists of only lowercase letters.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

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

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

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

```cpp
class Trie {
public:
    vector<Trie*> children;
    Trie()
        : children(26) {}

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
        sort(words.begin(), words.end(), [](string& a, string& b) { return a.size() > b.size(); });
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

<!-- tabs:end -->

<!-- end -->
