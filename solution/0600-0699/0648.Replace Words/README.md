---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0648.Replace%20Words/README.md
tags:
    - 字典树
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [648. 单词替换](https://leetcode.cn/problems/replace-words)

[English Version](/solution/0600-0699/0648.Replace%20Words/README_EN.md)

## 题目描述

<!-- description:start -->

<p>在英语中，我们有一个叫做&nbsp;<strong>词根</strong>(root) 的概念，可以词根&nbsp;<strong>后面&nbsp;</strong>添加其他一些词组成另一个较长的单词——我们称这个词为 <strong>继承词</strong>&nbsp;(successor)。例如，词根&nbsp;<code>help</code>，跟随着 <strong>继承</strong>词&nbsp;<code>"ful"</code>，可以形成新的单词&nbsp;<code>"helpful"</code>。</p>

<p>现在，给定一个由许多&nbsp;<strong>词根&nbsp;</strong>组成的词典 <code>dictionary</code> 和一个用空格分隔单词形成的句子 <code>sentence</code>。你需要将句子中的所有&nbsp;<strong>继承词&nbsp;</strong>用&nbsp;<strong>词根&nbsp;</strong>替换掉。如果&nbsp;<strong>继承词&nbsp;</strong>有许多可以形成它的&nbsp;<strong>词根</strong>，则用&nbsp;<strong>最短&nbsp;</strong>的 <strong>词根</strong> 替换它。</p>

<p>你需要输出替换之后的句子。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
<strong>输出：</strong>"the cat was rat by the bat"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
<strong>输出：</strong>"a a b c"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= dictionary.length&nbsp;&lt;= 1000</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 100</code></li>
	<li><code>dictionary[i]</code>&nbsp;仅由小写字母组成。</li>
	<li><code>1 &lt;= sentence.length &lt;= 10<sup>6</sup></code></li>
	<li><code>sentence</code>&nbsp;仅由小写字母和空格组成。</li>
	<li><code>sentence</code> 中单词的总量在范围 <code>[1, 1000]</code> 内。</li>
	<li><code>sentence</code> 中每个单词的长度在范围 <code>[1, 1000]</code> 内。</li>
	<li><code>sentence</code> 中单词之间由一个空格隔开。</li>
	<li><code>sentence</code>&nbsp;没有前导或尾随空格。</li>
</ul>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀树

我们定义前缀树的节点数据结构如下：

-   `children`：子节点数组，长度为 $26$，每个元素为一个节点或 `None`
-   `ref`：如果当前节点是一个单词的结尾，则 `ref` 为该单词在 `dictionary` 中的索引，否则为 $-1$

我们首先将 `dictionary` 中的单词插入到前缀树中，然后遍历 `sentence` 中的每个单词，查找前缀树中是否存在该单词的前缀，如果存在，则将该单词替换为前缀。

时间复杂度为 $O(\sum_{w \in dictionary} |w| + |sentence|)$，空间复杂度为 $O(\sum_{w \in dictionary} |w|)$。其中 $|w|$ 表示单词 $w$ 的长度。

<!-- tabs:start -->

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

### 方法二

<!-- tabs:start -->

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
