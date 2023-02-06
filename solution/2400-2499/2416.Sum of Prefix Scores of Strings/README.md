# [2416. 字符串的前缀分数和](https://leetcode.cn/problems/sum-of-prefix-scores-of-strings)

[English Version](/solution/2400-2499/2416.Sum%20of%20Prefix%20Scores%20of%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的数组 <code>words</code> ，该数组由 <strong>非空</strong> 字符串组成。</p>

<p>定义字符串 <code>word</code> 的 <strong>分数</strong> 等于以 <code>word</code> 作为 <strong>前缀</strong> 的 <code>words[i]</code> 的数目。</p>

<ul>
	<li>例如，如果 <code>words = ["a", "ab", "abc", "cab"]</code> ，那么 <code>"ab"</code> 的分数是 <code>2</code> ，因为 <code>"ab"</code> 是 <code>"ab"</code> 和 <code>"abc"</code> 的一个前缀。</li>
</ul>

<p>返回一个长度为<em> </em><code>n</code> 的数组<em> </em><code>answer</code><em> </em>，其中<em> </em><code>answer[i]</code><em> </em>是<em>&nbsp;</em><code>words[i]</code> 的每个非空前缀的分数 <strong>总和</strong> <em>。</em></p>

<p><strong>注意：</strong>字符串视作它自身的一个前缀。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>words = ["abc","ab","bc","b"]
<strong>输出：</strong>[5,4,3,2]
<strong>解释：</strong>对应每个字符串的答案如下：
- "abc" 有 3 个前缀："a"、"ab" 和 "abc" 。
- 2 个字符串的前缀为 "a" ，2 个字符串的前缀为 "ab" ，1 个字符串的前缀为 "abc" 。
总计 answer[0] = 2 + 2 + 1 = 5 。
- "ab" 有 2 个前缀："a" 和 "ab" 。
- 2 个字符串的前缀为 "a" ，2 个字符串的前缀为 "ab" 。
总计 answer[1] = 2 + 2 = 4 。
- "bc" 有 2 个前缀："b" 和 "bc" 。
- 2 个字符串的前缀为 "b" ，1 个字符串的前缀为 "bc" 。 
总计 answer[2] = 2 + 1 = 3 。
- "b" 有 1 个前缀："b"。
- 2 个字符串的前缀为 "b" 。
总计 answer[3] = 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>words = ["abcd"]
<strong>输出：</strong>[4]
<strong>解释：</strong>
"abcd" 有 4 个前缀 "a"、"ab"、"abc" 和 "abcd"。
每个前缀的分数都是 1 ，总计 answer[0] = 1 + 1 + 1 + 1 = 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 1000</code></li>
	<li><code>words[i]</code> 由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀树**

用前缀树维护所有字符串的前缀以及每个前缀出现的次数。

然后遍历每个字符串，累加每个前缀的出现次数即可。

时间复杂度 $O(n\times m)$。其中 $n$, $m$ 分别为字符串数组 `words` 的长度和其中字符串的最大长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
