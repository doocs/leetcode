---
comments: true
difficulty: Medium
tags:
    - Trie
    - Memoization
    - Array
    - Hash Table
    - String
    - Dynamic Programming
    - Brute-Force Search
---

<!-- problem:start -->

# [139. Word Break](https://leetcode.com/problems/word-break)

[中文文档](/solution/0100-0199/0139.Word%20Break/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một chuỗi <code>s</code> và một từ điển các chuỗi <code>wordDict</code>, hãy trả về <code>true</code> nếu <code>s</code> có thể được phân tách thành một dãy gồm một hoặc nhiều từ trong từ điển, ngăn cách nhau bằng dấu cách.</p>

<p><strong>Lưu ý</strong> rằng cùng một từ trong từ điển có thể được sử dụng lại nhiều lần trong phép phân tách.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;leetcode&quot;, wordDict = [&quot;leet&quot;,&quot;code&quot;]
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong> Trả về true vì &quot;leetcode&quot; có thể được phân tách thành &quot;leet code&quot;.
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;applepenapple&quot;, wordDict = [&quot;apple&quot;,&quot;pen&quot;]
<strong>Đầu ra:</strong> true
<strong>Giải thích:</strong> Trả về true vì &quot;applepenapple&quot; có thể được phân tách thành &quot;apple pen apple&quot;.
Lưu ý rằng bạn được phép sử dụng lại một từ trong từ điển.
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> s = &quot;catsandog&quot;, wordDict = [&quot;cats&quot;,&quot;dog&quot;,&quot;sand&quot;,&quot;and&quot;,&quot;cat&quot;]
<strong>Đầu ra:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 300</code></li>
	<li><code>1 &lt;= wordDict.length &lt;= 1000</code></li>
	<li><code>1 &lt;= wordDict[i].length &lt;= 20</code></li>
	<li><code>s</code> và <code>wordDict[i]</code> chỉ gồm các chữ cái tiếng Anh viết thường.</li>
	<li>Tất cả các chuỗi trong <code>wordDict</code> là <strong>duy nhất</strong>.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> $s$ có thể được phân tách thành các từ trong từ điển không? Vì $|s|\le 300$, quay lui sẽ lặp lại các hậu tố giống nhau. $f[i]$ biểu thị việc tiền tố $s[0..i)$ có thể được tách hay không: thử một điểm cắt $j$ sao cho $f[j]$ là true và $s[j..i)$ nằm trong từ điển. Một hash set giúp kiểm tra phần tử có thuộc tập với độ phức tạp kỳ vọng là $O(1)$.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        words = set(wordDict)
        n = len(s)
        f = [True] + [False] * n
        for i in range(1, n + 1):
            f[i] = any(f[j] and s[j:i] in words for j in range(i))
        return f[n]
```

#### Java

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (f[j] && words.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool wordBreak(string s, vector<string>& wordDict) {
        unordered_set<string> words(wordDict.begin(), wordDict.end());
        int n = s.size();
        bool f[n + 1];
        memset(f, false, sizeof(f));
        f[0] = true;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (f[j] && words.count(s.substr(j, i - j))) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }
};
```

#### Go

```go
func wordBreak(s string, wordDict []string) bool {
	words := map[string]bool{}
	for _, w := range wordDict {
		words[w] = true
	}
	n := len(s)
	f := make([]bool, n+1)
	f[0] = true
	for i := 1; i <= n; i++ {
		for j := 0; j < i; j++ {
			if f[j] && words[s[j:i]] {
				f[i] = true
				break
			}
		}
	}
	return f[n]
}
```

#### TypeScript

```ts
function wordBreak(s: string, wordDict: string[]): boolean {
    const words = new Set(wordDict);
    const n = s.length;
    const f: boolean[] = new Array(n + 1).fill(false);
    f[0] = true;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (f[j] && words.has(s.substring(j, i))) {
                f[i] = true;
                break;
            }
        }
    }
    return f[n];
}
```

#### Rust

```rust
impl Solution {
    pub fn word_break(s: String, word_dict: Vec<String>) -> bool {
        let words: std::collections::HashSet<String> = word_dict.into_iter().collect();
        let mut f = vec![false; s.len() + 1];
        f[0] = true;
        for i in 1..=s.len() {
            for j in 0..i {
                f[i] |= f[j] && words.contains(&s[j..i]);
            }
        }
        f[s.len()]
    }
}
```

#### C#

```cs
public class Solution {
    public bool WordBreak(string s, IList<string> wordDict) {
        var words = new HashSet<string>(wordDict);
        int n = s.Length;
        var f = new bool[n + 1];
        f[0] = true;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (f[j] && words.Contains(s.Substring(j, i - j))) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 cắt một chuỗi con rồi băm nó ở mỗi điểm cắt, nên mỗi lần kiểm tra tốn $O(n)$. Trie cho phép chúng ta đi từ chỉ số $i$ chỉ qua các cạnh đã tồn tại, nhờ đó loại bỏ các lần cắt vô ích và đưa thời gian về $O(n^2)$.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Trie:
    def __init__(self):
        self.children: List[Trie | None] = [None] * 26
        self.isEnd = False

    def insert(self, w: str):
        node = self
        for c in w:
            idx = ord(c) - ord('a')
            if not node.children[idx]:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.isEnd = True


class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        trie = Trie()
        for w in wordDict:
            trie.insert(w)
        n = len(s)
        f = [False] * (n + 1)
        f[n] = True
        for i in range(n - 1, -1, -1):
            node = trie
            for j in range(i, n):
                idx = ord(s[j]) - ord('a')
                if not node.children[idx]:
                    break
                node = node.children[idx]
                if node.isEnd and f[j + 1]:
                    f[i] = True
                    break
        return f[0]
```

#### Java

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Trie trie = new Trie();
        for (String w : wordDict) {
            trie.insert(w);
        }
        int n = s.length();
        boolean[] f = new boolean[n + 1];
        f[n] = true;
        for (int i = n - 1; i >= 0; --i) {
            Trie node = trie;
            for (int j = i; j < n; ++j) {
                int k = s.charAt(j) - 'a';
                if (node.children[k] == null) {
                    break;
                }
                node = node.children[k];
                if (node.isEnd && f[j + 1]) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[0];
    }
}

class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd = false;

    public void insert(String w) {
        Trie node = this;
        for (int i = 0; i < w.length(); ++i) {
            int j = w.charAt(i) - 'a';
            if (node.children[j] == null) {
                node.children[j] = new Trie();
            }
            node = node.children[j];
        }
        node.isEnd = true;
    }
}
```

#### C++

```cpp
class Trie {
public:
    vector<Trie*> children;
    bool isEnd;
    Trie()
        : children(26)
        , isEnd(false) {}

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
    bool wordBreak(string s, vector<string>& wordDict) {
        Trie trie;
        for (auto& w : wordDict) {
            trie.insert(w);
        }
        int n = s.size();
        vector<bool> f(n + 1);
        f[n] = true;
        for (int i = n - 1; ~i; --i) {
            Trie* node = &trie;
            for (int j = i; j < n; ++j) {
                int k = s[j] - 'a';
                if (!node->children[k]) {
                    break;
                }
                node = node->children[k];
                if (node->isEnd && f[j + 1]) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[0];
    }
};
```

#### Go

```go
type trie struct {
	children [26]*trie
	isEnd    bool
}

func newTrie() *trie {
	return &trie{}
}

func (t *trie) insert(w string) {
	node := t
	for _, c := range w {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
	}
	node.isEnd = true
}

func wordBreak(s string, wordDict []string) bool {
	trie := newTrie()
	for _, w := range wordDict {
		trie.insert(w)
	}
	n := len(s)
	f := make([]bool, n+1)
	f[n] = true
	for i := n - 1; i >= 0; i-- {
		node := trie
		for j := i; j < n; j++ {
			k := s[j] - 'a'
			if node.children[k] == nil {
				break
			}
			node = node.children[k]
			if node.isEnd && f[j+1] {
				f[i] = true
				break
			}
		}
	}
	return f[0]
}
```

#### TypeScript

```ts
function wordBreak(s: string, wordDict: string[]): boolean {
    const trie = new Trie();
    for (const w of wordDict) {
        trie.insert(w);
    }
    const n = s.length;
    const f: boolean[] = new Array(n + 1).fill(false);
    f[n] = true;
    for (let i = n - 1; i >= 0; --i) {
        let node: Trie = trie;
        for (let j = i; j < n; ++j) {
            const k = s.charCodeAt(j) - 97;
            if (!node.children[k]) {
                break;
            }
            node = node.children[k];
            if (node.isEnd && f[j + 1]) {
                f[i] = true;
                break;
            }
        }
    }
    return f[0];
}

class Trie {
    children: Trie[];
    isEnd: boolean;

    constructor() {
        this.children = new Array(26);
        this.isEnd = false;
    }

    insert(w: string): void {
        let node: Trie = this;
        for (const c of w) {
            const i = c.charCodeAt(0) - 97;
            if (!node.children[i]) {
                node.children[i] = new Trie();
            }
            node = node.children[i];
        }
        node.isEnd = true;
    }
}
```

#### C#

```cs
public class Solution {
    public bool WordBreak(string s, IList<string> wordDict) {
        Trie trie = new Trie();
        foreach (string w in wordDict) {
            trie.Insert(w);
        }
        int n = s.Length;
        bool[] f = new bool[n + 1];
        f[n] = true;
        for (int i = n - 1; i >= 0; --i) {
            Trie node = trie;
            for (int j = i; j < n; ++j) {
                int k = s[j] - 'a';
                if (node.Children[k] == null) {
                    break;
                }
                node = node.Children[k];
                if (node.IsEnd && f[j + 1]) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[0];
    }
}

class Trie {
    public Trie[] Children { get; set; }
    public bool IsEnd { get; set; }

    public Trie() {
        Children = new Trie[26];
        IsEnd = false;
    }

    public void Insert(string word) {
        Trie node = this;
        foreach (char c in word) {
            int i = c - 'a';
            if (node.Children[i] == null) {
                node.Children[i] = new Trie();
            }
            node = node.Children[i];
        }
        node.IsEnd = true;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
