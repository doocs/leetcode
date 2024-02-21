# [1858. 包含所有前缀的最长单词](https://leetcode.cn/problems/longest-word-with-all-prefixes)

[English Version](/solution/1800-1899/1858.Longest%20Word%20With%20All%20Prefixes/README_EN.md)

<!-- tags:深度优先搜索,字典树 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串数组 <code>words</code>，找出 <code>words</code> 中<strong>所有的前缀</strong>都在 <code>words</code> 中的<strong>最长</strong>字符串。</p>

<ul>
	<li>例如，令 <code>words = ["a", "app", "ap"]</code>。字符串 <code>"app"</code> 含前缀 <code>"ap"</code> 和 <code>"a"</code> ，都在 <code>words</code> 中。</li>
</ul>

<p>返回符合上述要求的字符串。如果存在多个（符合条件的）相同长度的字符串，返回字典序中最小的字符串，如果这样的字符串不存在，返回<em> </em><code>""</code>。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre><b>输入：</b> words = ["k","ki","kir","kira", "kiran"]
<b>输出：</b> "kiran"
<b>解释：</b> "kiran" 含前缀 "kira"、 "kir"、 "ki"、 和 "k"，这些前缀都出现在 words 中。
</pre>

<p><strong>示例 2:</strong></p>

<pre><b>输入：</b> words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
<b>输出： </b>"apple"
<b>解释：</b> "apple" 和 "apply" 都在 words 中含有各自的所有前缀。
然而，"apple" 在字典序中更小，所以我们返回之。
</pre>

<p><strong>示例 3:</strong></p>

<pre><b>输入：</b> words = ["abc", "bc", "ab", "qwe"]
<b>输出：</b> ""
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= sum(words[i].length) &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一：前缀树

我们定义一棵前缀树，前缀树每个节点有两个属性，一个是长度为 $26$ 的子节点数组 `children`，另一个是是否为单词结尾的标记 `isEnd`。

我们遍历 `words`，对于每个单词 `w`，我们从根节点开始遍历，如果当前节点的子节点数组中没有 `w` 的第一个字符，我们就创建一个新的节点，然后继续遍历 `w` 的下一个字符，直到遍历完 `w`，我们将当前节点的 `isEnd` 标记为 `true`。

接下来我们遍历 `words`，对于每个单词 `w`，我们从根节点开始遍历，如果当前节点的子节点数组的 `isEnd` 字段为 `false`，说明 `w` 的某个前缀不在 `words` 中，我们返回 `false`。否则继续遍历 `w` 的下一个字符，直到遍历完 `w`，我们返回 `true`。

时间复杂度 $O(\sum_{w \in words} |w|)$，空间复杂度 $O(\sum_{w \in words} |w|)$。

<!-- tabs:start -->

```python
class Trie:
    __slots__ = ["children", "is_end"]

    def __init__(self):
        self.children: List[Trie | None] = [None] * 26
        self.is_end: bool = False

    def insert(self, w: str) -> None:
        node = self
        for c in w:
            idx = ord(c) - ord("a")
            if not node.children[idx]:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.is_end = True

    def search(self, w: str) -> bool:
        node = self
        for c in w:
            idx = ord(c) - ord("a")
            node = node.children[idx]
            if not node.is_end:
                return False
        return True


class Solution:
    def longestWord(self, words: List[str]) -> str:
        trie = Trie()
        for w in words:
            trie.insert(w)
        ans = ""
        for w in words:
            if (len(w) > len(ans) or len(w) == len(ans) and w < ans) and trie.search(w):
                ans = w
        return ans
```

```java
class Trie {
    private Trie[] children = new Trie[26];
    private boolean isEnd;

    public Trie() {
    }

    public void insert(String w) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public boolean search(String w) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            int idx = c - 'a';
            node = node.children[idx];
            if (!node.isEnd) {
                return false;
            }
        }
        return true;
    }
}

class Solution {
    public String longestWord(String[] words) {
        Trie trie = new Trie();
        for (String w : words) {
            trie.insert(w);
        }
        String ans = "";
        for (String w : words) {
            if ((w.length() > ans.length() || (w.length() == ans.length() && w.compareTo(ans) < 0))
                && trie.search(w)) {
                ans = w;
            }
        }
        return ans;
    }
}
```

```cpp
class Trie {
private:
    Trie* children[26];
    bool isEnd = false;

public:
    Trie() {
        fill(begin(children), end(children), nullptr);
    }

    void insert(const string& w) {
        Trie* node = this;
        for (char c : w) {
            int idx = c - 'a';
            if (!node->children[idx]) {
                node->children[idx] = new Trie();
            }
            node = node->children[idx];
        }
        node->isEnd = true;
    }

    bool search(const string& w) {
        Trie* node = this;
        for (char c : w) {
            int idx = c - 'a';
            node = node->children[idx];
            if (!node->isEnd) {
                return false;
            }
        }
        return true;
    }
};

class Solution {
public:
    string longestWord(vector<string>& words) {
        Trie trie;
        for (const string& w : words) {
            trie.insert(w);
        }
        string ans = "";
        for (const string& w : words) {
            if ((w.size() > ans.size() || (w.size() == ans.size() && w < ans)) && trie.search(w)) {
                ans = w;
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

func (t *Trie) insert(w string) {
	node := t
	for _, c := range w {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = newTrie()
		}
		node = node.children[idx]
	}
	node.isEnd = true
}

func (t *Trie) search(w string) bool {
	node := t
	for _, c := range w {
		idx := c - 'a'
		node = node.children[idx]
		if !node.isEnd {
			return false
		}
	}
	return true
}

func longestWord(words []string) string {
	trie := newTrie()
	for _, w := range words {
		trie.insert(w)
	}
	ans := ""
	for _, w := range words {
		if (len(w) > len(ans) || (len(w) == len(ans) && w < ans)) && trie.search(w) {
			ans = w
		}
	}
	return ans
}
```

```ts
class Trie {
    private children: (Trie | null)[] = Array(26).fill(null);
    private isEnd: boolean = false;

    insert(w: string): void {
        let node: Trie = this;
        for (const c of w) {
            const idx: number = c.charCodeAt(0) - 'a'.charCodeAt(0);
            if (!node.children[idx]) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx] as Trie;
        }
        node.isEnd = true;
    }

    search(w: string): boolean {
        let node: Trie = this;
        for (const c of w) {
            const idx: number = c.charCodeAt(0) - 'a'.charCodeAt(0);
            node = node.children[idx] as Trie;
            if (!node.isEnd) {
                return false;
            }
        }
        return true;
    }
}

function longestWord(words: string[]): string {
    const trie: Trie = new Trie();
    for (const w of words) {
        trie.insert(w);
    }
    let ans: string = '';
    for (const w of words) {
        if ((w.length > ans.length || (w.length === ans.length && w < ans)) && trie.search(w)) {
            ans = w;
        }
    }
    return ans;
}
```

```rust
struct Trie {
    children: [Option<Box<Trie>>; 26],
    is_end: bool,
}

impl Trie {
    fn new() -> Self {
        Trie {
            children: Default::default(),
            is_end: false,
        }
    }

    fn insert(&mut self, w: &str) {
        let mut node = self;
        for c in w.chars() {
            let idx = (c as usize) - ('a' as usize);
            node = node.children[idx].get_or_insert_with(|| Box::new(Trie::new()));
        }
        node.is_end = true;
    }

    fn search(&self, w: &str) -> bool {
        let mut node = self;
        for c in w.chars() {
            let idx = (c as usize) - ('a' as usize);
            if let Some(next_node) = &node.children[idx] {
                node = next_node.as_ref();
                if !node.is_end {
                    return false;
                }
            }
        }
        true
    }
}

impl Solution {
    pub fn longest_word(words: Vec<String>) -> String {
        let mut trie = Trie::new();
        for w in &words {
            trie.insert(w);
        }
        let mut ans = String::new();
        for w in &words {
            if (w.len() > ans.len() || (w.len() == ans.len() && w < &ans)) && trie.search(w) {
                ans = w.clone();
            }
        }
        ans
    }
}
```

```cs
public class Trie {
    private Trie[] children = new Trie[26];
    private bool isEnd;

    public Trie() { }

    public void Insert(string w) {
        Trie node = this;
        foreach (char c in w.ToCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public bool Search(string w) {
        Trie node = this;
        foreach (char c in w.ToCharArray()) {
            int idx = c - 'a';
            node = node.children[idx];
            if (!node.isEnd) {
                return false;
            }
        }
        return true;
    }
}

public class Solution {
    public string LongestWord(string[] words) {
        Trie trie = new Trie();
        foreach (string w in words) {
            trie.Insert(w);
        }

        string ans = "";
        foreach (string w in words) {
            if ((w.Length > ans.Length || (w.Length == ans.Length && string.Compare(w, ans) < 0)) && trie.Search(w)) {
                ans = w;
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
