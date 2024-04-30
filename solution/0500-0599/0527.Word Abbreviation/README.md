# [527. 单词缩写 🔒](https://leetcode.cn/problems/word-abbreviation)

[English Version](/solution/0500-0599/0527.Word%20Abbreviation/README_EN.md)

<!-- tags:贪心,字典树,数组,字符串,排序 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组 <code>words</code> ，该数组由 <strong>互不相同</strong> 的若干字符串组成，请你找出并返回每个单词的 <strong>最小缩写</strong> 。</p>

<p>生成缩写的规则如下<strong>：</strong></p>

<ol>
	<li>初始缩写由起始字母+省略字母的数量+结尾字母组成。</li>
	<li>若存在冲突，亦即多于一个单词有同样的缩写，则使用更长的前缀代替首字母，直到从单词到缩写的映射唯一。换而言之，最终的缩写必须只能映射到一个单词。</li>
	<li>若缩写并不比原单词更短，则保留原样。</li>
</ol>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> words = ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
<strong>输出:</strong> ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["aa","aaa"]
<strong>输出：</strong>["aa","aaa"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 400</code></li>
	<li><code>2 &lt;= words[i].length &lt;= 400</code></li>
	<li><code>words[i]</code> 由小写英文字母组成</li>
	<li><code>words</code> 中的所有字符串 <strong>互不相同</strong></li>
</ul>

## 解法

### 方法一：分组字典树

我们注意到，如果两个单词的缩写相同，那么它们的首尾字母一定相同，并且它们的长度一定相同。因此，我们可以将所有的单词按照长度以及末尾字母进行分组，对于每组单词，我们使用字典树存储这组单词的信息。

字典树的每个节点结构如下：

-   `children`：长度为 $26$ 的数组，表示该节点的所有子节点。
-   `cnt`：表示经过该节点的单词数量。

对于每个单词，我们将其插入到字典树中，同时记录每个节点的 `cnt` 值。

在查询时，我们从根节点开始，对于当前的字母，如果其对应的子节点的 `cnt` 值为 $1$，那么我们就找到了唯一的缩写，我们返回当前前缀的长度即可。否则，我们继续向下遍历。遍历结束后，如果我们没有找到唯一的缩写，那么我们返回原单词的长度。在得到所有单词的前缀长度后，我们判断单词的缩写是否比原单词更短，如果是，那么我们将其加入答案中，否则我们将原单词加入答案中。

时间复杂度 $O(L)$，空间复杂度 $O(L)$，其中 $L$ 为所有单词的长度和。

<!-- tabs:start -->

```python
class Trie:
    __slots__ = ["children", "cnt"]

    def __init__(self):
        self.children = [None] * 26
        self.cnt = 0

    def insert(self, w: str):
        node = self
        for c in w:
            idx = ord(c) - ord("a")
            if not node.children[idx]:
                node.children[idx] = Trie()
            node = node.children[idx]
            node.cnt += 1

    def search(self, w: str) -> int:
        node = self
        cnt = 0
        for c in w:
            cnt += 1
            idx = ord(c) - ord("a")
            node = node.children[idx]
            if node.cnt == 1:
                return cnt
        return len(w)


class Solution:
    def wordsAbbreviation(self, words: List[str]) -> List[str]:
        tries = {}
        for w in words:
            m = len(w)
            if (m, w[-1]) not in tries:
                tries[(m, w[-1])] = Trie()
            tries[(m, w[-1])].insert(w)
        ans = []
        for w in words:
            cnt = tries[(len(w), w[-1])].search(w)
            ans.append(
                w if cnt + 2 >= len(w) else w[:cnt] + str(len(w) - cnt - 1) + w[-1]
            )
        return ans
```

```java
class Trie {
    private final Trie[] children = new Trie[26];
    private int cnt;

    public void insert(String w) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
            ++node.cnt;
        }
    }

    public int search(String w) {
        Trie node = this;
        int ans = 0;
        for (char c : w.toCharArray()) {
            ++ans;
            int idx = c - 'a';
            node = node.children[idx];
            if (node.cnt == 1) {
                return ans;
            }
        }
        return w.length();
    }
}

class Solution {
    public List<String> wordsAbbreviation(List<String> words) {
        Map<List<Integer>, Trie> tries = new HashMap<>();
        for (var w : words) {
            var key = List.of(w.length(), w.charAt(w.length() - 1) - 'a');
            tries.putIfAbsent(key, new Trie());
            tries.get(key).insert(w);
        }
        List<String> ans = new ArrayList<>();
        for (var w : words) {
            int m = w.length();
            var key = List.of(m, w.charAt(m - 1) - 'a');
            int cnt = tries.get(key).search(w);
            ans.add(cnt + 2 >= m ? w : w.substring(0, cnt) + (m - cnt - 1) + w.substring(m - 1));
        }
        return ans;
    }
}
```

```cpp
class Trie {
public:
    Trie()
        : cnt(0) {
        fill(children.begin(), children.end(), nullptr);
    }

    void insert(const string& w) {
        Trie* node = this;
        for (char c : w) {
            int idx = c - 'a';
            if (node->children[idx] == nullptr) {
                node->children[idx] = new Trie();
            }
            node = node->children[idx];
            ++node->cnt;
        }
    }

    int search(const string& w) {
        Trie* node = this;
        int ans = 0;
        for (char c : w) {
            ++ans;
            int idx = c - 'a';
            node = node->children[idx];
            if (node->cnt == 1) {
                return ans;
            }
        }
        return w.size();
    }

private:
    array<Trie*, 26> children;
    int cnt;
};

class Solution {
public:
    vector<string> wordsAbbreviation(vector<string>& words) {
        map<pair<int, int>, Trie*> tries;
        for (const auto& w : words) {
            pair<int, int> key = {static_cast<int>(w.size()), w.back() - 'a'};
            if (tries.find(key) == tries.end()) {
                tries[key] = new Trie();
            }
            tries[key]->insert(w);
        }

        vector<string> ans;
        for (const auto& w : words) {
            int m = w.size();
            pair<int, int> key = {m, w.back() - 'a'};
            int cnt = tries[key]->search(w);
            ans.push_back((cnt + 2 >= m) ? w : w.substr(0, cnt) + to_string(m - cnt - 1) + w.back());
        }

        return ans;
    }
};
```

```go
type Trie struct {
	children [26]*Trie
	cnt      int
}

func (t *Trie) insert(w string) {
	node := t
	for _, c := range w {
		idx := c - 'a'
		if node.children[idx] == nil {
			node.children[idx] = &Trie{}
		}
		node = node.children[idx]
		node.cnt++
	}
}

func (t *Trie) search(w string) int {
	node := t
	ans := 0
	for _, c := range w {
		ans++
		idx := c - 'a'
		node = node.children[idx]
		if node.cnt == 1 {
			return ans
		}
	}
	return len(w)
}

func wordsAbbreviation(words []string) (ans []string) {
	tries := make(map[[2]int]*Trie)
	for _, w := range words {
		key := [2]int{len(w), int(w[len(w)-1] - 'a')}
		_, exists := tries[key]
		if !exists {
			tries[key] = &Trie{}
		}
		tries[key].insert(w)
	}

	for _, w := range words {
		m := len(w)
		key := [2]int{m, int(w[m-1] - 'a')}
		cnt := tries[key].search(w)
		if cnt+2 >= m {
			ans = append(ans, w)
		} else {
			abbr := w[:cnt] + fmt.Sprintf("%d", m-cnt-1) + w[m-1:]
			ans = append(ans, abbr)
		}
	}
	return
}
```

```ts
class Trie {
    private children: Trie[] = Array(26);
    private cnt: number = 0;

    insert(w: string): void {
        let node: Trie = this;
        for (const c of w) {
            const idx: number = c.charCodeAt(0) - 'a'.charCodeAt(0);
            if (!node.children[idx]) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
            node.cnt++;
        }
    }

    search(w: string): number {
        let node: Trie = this;
        let ans: number = 0;
        for (const c of w) {
            ans++;
            const idx: number = c.charCodeAt(0) - 'a'.charCodeAt(0);
            node = node.children[idx];
            if (node.cnt === 1) {
                return ans;
            }
        }
        return w.length;
    }
}

function wordsAbbreviation(words: string[]): string[] {
    const tries: Map<string, Trie> = new Map();
    for (const w of words) {
        const key: string = `${w.length}-${w.charCodeAt(w.length - 1) - 'a'.charCodeAt(0)}`;
        if (!tries.get(key)) {
            tries.set(key, new Trie());
        }
        tries.get(key)!.insert(w);
    }

    const ans: string[] = [];
    for (const w of words) {
        const m: number = w.length;
        const key: string = `${m}-${w.charCodeAt(m - 1) - 'a'.charCodeAt(0)}`;
        const cnt: number = tries.get(key)!.search(w);
        ans.push(cnt + 2 >= m ? w : w.substring(0, cnt) + (m - cnt - 1) + w.substring(m - 1));
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
