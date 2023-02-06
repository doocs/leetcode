# [2416. Sum of Prefix Scores of Strings](https://leetcode.com/problems/sum-of-prefix-scores-of-strings)

[中文文档](/solution/2400-2499/2416.Sum%20of%20Prefix%20Scores%20of%20Strings/README.md)

## Description

<p>You are given an array <code>words</code> of size <code>n</code> consisting of <strong>non-empty</strong> strings.</p>

<p>We define the <strong>score</strong> of a string <code>word</code> as the <strong>number</strong> of strings <code>words[i]</code> such that <code>word</code> is a <strong>prefix</strong> of <code>words[i]</code>.</p>

<ul>
	<li>For example, if <code>words = [&quot;a&quot;, &quot;ab&quot;, &quot;abc&quot;, &quot;cab&quot;]</code>, then the score of <code>&quot;ab&quot;</code> is <code>2</code>, since <code>&quot;ab&quot;</code> is a prefix of both <code>&quot;ab&quot;</code> and <code>&quot;abc&quot;</code>.</li>
</ul>

<p>Return <em>an array </em><code>answer</code><em> of size </em><code>n</code><em> where </em><code>answer[i]</code><em> is the <strong>sum</strong> of scores of every <strong>non-empty</strong> prefix of </em><code>words[i]</code>.</p>

<p><strong>Note</strong> that a string is considered as a prefix of itself.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abc&quot;,&quot;ab&quot;,&quot;bc&quot;,&quot;b&quot;]
<strong>Output:</strong> [5,4,3,2]
<strong>Explanation:</strong> The answer for each string is the following:
- &quot;abc&quot; has 3 prefixes: &quot;a&quot;, &quot;ab&quot;, and &quot;abc&quot;.
- There are 2 strings with the prefix &quot;a&quot;, 2 strings with the prefix &quot;ab&quot;, and 1 string with the prefix &quot;abc&quot;.
The total is answer[0] = 2 + 2 + 1 = 5.
- &quot;ab&quot; has 2 prefixes: &quot;a&quot; and &quot;ab&quot;.
- There are 2 strings with the prefix &quot;a&quot;, and 2 strings with the prefix &quot;ab&quot;.
The total is answer[1] = 2 + 2 = 4.
- &quot;bc&quot; has 2 prefixes: &quot;b&quot; and &quot;bc&quot;.
- There are 2 strings with the prefix &quot;b&quot;, and 1 string with the prefix &quot;bc&quot;.
The total is answer[2] = 2 + 1 = 3.
- &quot;b&quot; has 1 prefix: &quot;b&quot;.
- There are 2 strings with the prefix &quot;b&quot;.
The total is answer[3] = 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abcd&quot;]
<strong>Output:</strong> [4]
<strong>Explanation:</strong>
&quot;abcd&quot; has 4 prefixes: &quot;a&quot;, &quot;ab&quot;, &quot;abc&quot;, and &quot;abcd&quot;.
Each prefix has a score of one, so the total is answer[0] = 1 + 1 + 1 + 1 = 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 1000</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.cnt = 0

    def insert(self, w):
        node = self
        for c in w:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            node.cnt += 1

    def search(self, w):
        node = self
        ans = 0
        for c in w:
            idx = ord(c) - ord('a')
            if node.children[idx] is None:
                return ans
            node = node.children[idx]
            ans += node.cnt
        return ans


class Solution:
    def sumPrefixScores(self, words: List[str]) -> List[int]:
        trie = Trie()
        for w in words:
            trie.insert(w)
        return [trie.search(w) for w in words]
```

### **Java**

```java
class Trie {
    private Trie[] children = new Trie[26];
    private int cnt;

    public void insert(String w) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
            ++node.cnt;
        }
    }

    public int search(String w) {
        Trie node = this;
        int ans = 0;
        for (char c : w.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return ans;
            }
            node = node.children[c];
            ans += node.cnt;
        }
        return ans;
    }
}

class Solution {
    public int[] sumPrefixScores(String[] words) {
        Trie trie = new Trie();
        for (String w : words) {
            trie.insert(w);
        }
        int[] ans = new int[words.length];
        for (int i = 0; i < words.length; ++i) {
            ans[i] = trie.search(words[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Trie {
private:
    vector<Trie*> children;
    int cnt;

public:
    Trie()
        : children(26)
        , cnt(0) { }

    void insert(string& w) {
        Trie* node = this;
        for (char c : w) {
            int idx = c - 'a';
            if (!node->children[idx]) node->children[idx] = new Trie();
            node = node->children[idx];
            ++node->cnt;
        }
    }

    int search(string& w) {
        Trie* node = this;
        int ans = 0;
        for (char c : w) {
            int idx = c - 'a';
            if (!node->children[idx]) return ans;
            node = node->children[idx];
            ans += node->cnt;
        }
        return ans;
    }
};

class Solution {
public:
    vector<int> sumPrefixScores(vector<string>& words) {
        Trie* trie = new Trie();
        for (auto& w : words) {
            trie->insert(w);
        }
        vector<int> ans;
        for (auto& w : words) {
            ans.push_back(trie->search(w));
        }
        return ans;
    }
};
```

### **Go**

```go
type Trie struct {
	children [26]*Trie
	cnt      int
}

func newTrie() *Trie {
	return &Trie{}
}
func (this *Trie) insert(w string) {
	node := this
	for _, c := range w {
		c -= 'a'
		if node.children[c] == nil {
			node.children[c] = newTrie()
		}
		node = node.children[c]
		node.cnt++
	}
}

func (this *Trie) search(word string) int {
	node := this
	ans := 0
	for _, c := range word {
		c -= 'a'
		if node.children[c] == nil {
			return ans
		}
		node = node.children[c]
		ans += node.cnt
	}
	return ans
}

func sumPrefixScores(words []string) []int {
	trie := newTrie()
	for _, w := range words {
		trie.insert(w)
	}
	ans := make([]int, len(words))
	for i, w := range words {
		ans[i] = trie.search(w)
	}
	return ans
}
```

### **TypeScript**

```ts
function sumPrefixScores(words: string[]): number[] {
    const map = new Map();

    for (const word of words) {
        const n = word.length;
        for (let i = 1; i <= n; i++) {
            const s = word.slice(0, i);
            map.set(s, (map.get(s) ?? 0) + 1);
        }
    }

    return words.map(word => {
        const n = word.length;
        let count = 0;
        for (let i = 1; i <= n; i++) {
            const s = word.slice(0, i);
            count += map.get(s);
        }
        return count;
    });
}
```

```ts
class Trie {
    children: Array<any>;
    cnt: number;

    constructor() {
        this.children = Array(26);
        this.cnt = 0;
    }

    insert(w: string): void {
        let node = this;
        for (const c of w) {
            const idx = c.charCodeAt(0) - 'a'.charCodeAt(0);
            if (!node.children[idx]) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
            node.cnt++;
        }
    }

    search(w: string): number {
        let node = this;
        let ans = 0;
        for (const c of w) {
            const idx = c.charCodeAt(0) - 'a'.charCodeAt(0);
            if (!node.children[idx]) {
                return ans;
            }
            node = node.children[idx];
            ans += node.cnt;
        }
        return ans;
    }
}

function sumPrefixScores(words: string[]): number[] {
    const trie = new Trie();
    for (const w of words) {
        trie.insert(w);
    }
    let ans = [];
    for (const w of words) {
        ans.push(trie.search(w));
    }
    return ans;
}
```

### **...**

```


```

<!-- tabs:end -->
