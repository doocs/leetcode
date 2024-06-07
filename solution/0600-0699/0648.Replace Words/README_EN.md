---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0648.Replace%20Words/README_EN.md
tags:
    - Trie
    - Array
    - Hash Table
    - String
---

<!-- problem:start -->

# [648. Replace Words](https://leetcode.com/problems/replace-words)

[中文文档](/solution/0600-0699/0648.Replace%20Words/README.md)

## Description

<!-- description:start -->

<p>In English, we have a concept called <strong>root</strong>, which can be followed by some other word to form another longer word - let&#39;s call this word <strong>derivative</strong>. For example, when the <strong>root</strong> <code>&quot;help&quot;</code> is followed by the word <code>&quot;ful&quot;</code>, we can form a derivative <code>&quot;helpful&quot;</code>.</p>

<p>Given a <code>dictionary</code> consisting of many <strong>roots</strong> and a <code>sentence</code> consisting of words separated by spaces, replace all the derivatives in the sentence with the <strong>root</strong> forming it. If a derivative can be replaced by more than one <strong>root</strong>, replace it with the <strong>root</strong> that has <strong>the shortest length</strong>.</p>

<p>Return <em>the <code>sentence</code></em> after the replacement.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> dictionary = [&quot;cat&quot;,&quot;bat&quot;,&quot;rat&quot;], sentence = &quot;the cattle was rattled by the battery&quot;
<strong>Output:</strong> &quot;the cat was rat by the bat&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> dictionary = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;], sentence = &quot;aadsfasf absbs bbab cadsfafs&quot;
<strong>Output:</strong> &quot;a a b c&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= dictionary.length &lt;= 1000</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 100</code></li>
	<li><code>dictionary[i]</code> consists of only lower-case letters.</li>
	<li><code>1 &lt;= sentence.length &lt;= 10<sup>6</sup></code></li>
	<li><code>sentence</code> consists of only lower-case letters and spaces.</li>
	<li>The number of words in <code>sentence</code> is in the range <code>[1, 1000]</code></li>
	<li>The length of each word in <code>sentence</code> is in the range <code>[1, 1000]</code></li>
	<li>Every two consecutive words in <code>sentence</code> will be separated by exactly one space.</li>
	<li><code>sentence</code> does not have leading or trailing spaces.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Trie:
    def __init__(self):
        self.children: List[Trie | None] = [None] * 26
        self.ref: int = -1

    def insert(self, w: str, i: int):
        node = self
        for c in w:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
        node.ref = i

    def search(self, w: str) -> int:
        node = self
        for c in w:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                return -1
            node = node.children[idx]
            if node.ref != -1:
                return node.ref
        return -1


class Solution:
    def replaceWords(self, dictionary: List[str], sentence: str) -> str:
        trie = Trie()
        for i, w in enumerate(dictionary):
            trie.insert(w, i)
        ans = []
        for w in sentence.split():
            idx = trie.search(w)
            ans.append(dictionary[idx] if idx != -1 else w)
        return " ".join(ans)
```

#### Java

```java
class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Set<String> s = new HashSet<>(dictionary);
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            for (int j = 1; j <= word.length(); ++j) {
                String t = word.substring(0, j);
                if (s.contains(t)) {
                    words[i] = t;
                    break;
                }
            }
        }
        return String.join(" ", words);
    }
}
```

#### C++

```cpp
class Trie {
private:
    Trie* children[26];
    int ref;

public:
    Trie()
        : ref(-1) {
        memset(children, 0, sizeof(children));
    }

    void insert(const string& w, int i) {
        Trie* node = this;
        for (auto& c : w) {
            int idx = c - 'a';
            if (!node->children[idx]) {
                node->children[idx] = new Trie();
            }
            node = node->children[idx];
        }
        node->ref = i;
    }

    int search(const string& w) {
        Trie* node = this;
        for (auto& c : w) {
            int idx = c - 'a';
            if (!node->children[idx]) {
                return -1;
            }
            node = node->children[idx];
            if (node->ref != -1) {
                return node->ref;
            }
        }
        return -1;
    }
};

class Solution {
public:
    string replaceWords(vector<string>& dictionary, string sentence) {
        Trie* trie = new Trie();
        for (int i = 0; i < dictionary.size(); ++i) {
            trie->insert(dictionary[i], i);
        }
        stringstream ss(sentence);
        string w;
        string ans;
        while (ss >> w) {
            int idx = trie->search(w);
            ans += (idx == -1 ? w : dictionary[idx]) + " ";
        }
        ans.pop_back();
        return ans;
    }
};
```

#### Go

```go
type Trie struct {
	children [26]*Trie
	ref      int
}

func newTrie() *Trie {
	return &Trie{ref: -1}
}

func (this *Trie) insert(w string, i int) {
	node := this
	for _, c := range w {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = newTrie()
		}
		node = node.children[idx]
	}
	node.ref = i
}

func (this *Trie) search(w string) int {
	node := this
	for _, c := range w {
		idx := c - 'a'
		if node.children[idx] == nil {
			return -1
		}
		node = node.children[idx]
		if node.ref != -1 {
			return node.ref
		}
	}
	return -1
}

func replaceWords(dictionary []string, sentence string) string {
	trie := newTrie()
	for i, w := range dictionary {
		trie.insert(w, i)
	}
	ans := strings.Builder{}
	for _, w := range strings.Split(sentence, " ") {
		if idx := trie.search(w); idx != -1 {
			ans.WriteString(dictionary[idx])
		} else {
			ans.WriteString(w)
		}
		ans.WriteByte(' ')
	}
	return ans.String()[:ans.Len()-1]
}
```

#### TypeScript

```ts
class Trie {
    private children: Trie[];
    private ref: number;

    constructor() {
        this.children = new Array<Trie>(26);
        this.ref = -1;
    }

    public insert(w: string, i: number) {
        let node: Trie = this;
        for (const c of w) {
            const idx = c.charCodeAt(0) - 97;
            if (!node.children[idx]) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.ref = i;
    }

    public search(w: string): number {
        let node: Trie = this;
        for (const c of w) {
            const idx = c.charCodeAt(0) - 97;
            if (!node.children[idx]) {
                return -1;
            }
            node = node.children[idx];
            if (node.ref !== -1) {
                return node.ref;
            }
        }
        return -1;
    }
}

function replaceWords(dictionary: string[], sentence: string): string {
    const trie = new Trie();
    for (let i = 0; i < dictionary.length; i++) {
        trie.insert(dictionary[i], i);
    }
    return sentence
        .split(' ')
        .map(w => {
            const idx = trie.search(w);
            return idx !== -1 ? dictionary[idx] : w;
        })
        .join(' ');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### Java

```java
class Trie {
    private Trie[] children = new Trie[26];
    private int ref = -1;

    public void insert(String w, int i) {
        Trie node = this;
        for (int j = 0; j < w.length(); ++j) {
            int idx = w.charAt(j) - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.ref = i;
    }

    public int search(String w) {
        Trie node = this;
        for (int j = 0; j < w.length(); ++j) {
            int idx = w.charAt(j) - 'a';
            if (node.children[idx] == null) {
                return -1;
            }
            node = node.children[idx];
            if (node.ref != -1) {
                return node.ref;
            }
        }
        return -1;
    }
}

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (int i = 0; i < dictionary.size(); ++i) {
            trie.insert(dictionary.get(i), i);
        }
        List<String> ans = new ArrayList<>();
        for (String w : sentence.split("\\s")) {
            int idx = trie.search(w);
            ans.add(idx == -1 ? w : dictionary.get(idx));
        }
        return String.join(" ", ans);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
