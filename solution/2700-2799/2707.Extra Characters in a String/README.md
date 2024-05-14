# [2707. 字符串中的额外字符](https://leetcode.cn/problems/extra-characters-in-a-string)

[English Version](/solution/2700-2799/2707.Extra%20Characters%20in%20a%20String/README_EN.md)

<!-- tags:字典树,数组,哈希表,字符串,动态规划 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>s</code>&nbsp;和一个单词字典&nbsp;<code>dictionary</code>&nbsp;。你需要将&nbsp;<code>s</code>&nbsp;分割成若干个 <strong>互不重叠</strong>&nbsp;的子字符串，每个子字符串都在&nbsp;<code>dictionary</code>&nbsp;中出现过。<code>s</code>&nbsp;中可能会有一些&nbsp;<strong>额外的字符</strong>&nbsp;不在任何子字符串中。</p>

<p>请你采取最优策略分割 <code>s</code>&nbsp;，使剩下的字符 <strong>最少</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "leetscode", dictionary = ["leet","code","leetcode"]
<b>输出：</b>1
<b>解释：</b>将 s 分成两个子字符串：下标从 0 到 3 的 "leet" 和下标从 5 到 8 的 "code" 。只有 1 个字符没有使用（下标为 4），所以我们返回 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "sayhelloworld", dictionary = ["hello","world"]
<b>输出：</b>3
<b>解释：</b>将 s 分成两个子字符串：下标从 3 到 7 的 "hello" 和下标从 8 到 12 的 "world" 。下标为 0 ，1 和 2 的字符没有使用，所以我们返回 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 50</code></li>
	<li><code>1 &lt;= dictionary.length &lt;= 50</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 50</code></li>
	<li><code>dictionary[i]</code>&nbsp;和&nbsp;<code>s</code>&nbsp;只包含小写英文字母。</li>
	<li><code>dictionary</code>&nbsp;中的单词互不相同。</li>
</ul>

## 解法

### 方法一：哈希表 + 动态规划

我们可以用一个哈希表 $ss$ 记录字段中的所有单词，方便我们快速判断一个字符串是否在字典中。

接下来，我们定义 $f[i]$ 表示字符串 $s$ 的前 $i$ 个字符的最小额外字符数，初始时 $f[0] = 0$。

当 $i \ge 1$ 时，第 $i$ 个字符 $s[i - 1]$ 可以作为一个额外字符，此时 $f[i] = f[i - 1] + 1$，如果在 $j \in [0, i - 1]$ 中存在一个下标 $j$，使得 $s[j..i)$ 在哈希表 $ss$ 中，那么我们可以将 $s[j..i)$ 作为一个单词，此时 $f[i] = f[j]$。

综上，我们可以得到状态转移方程：

$$
f[i] = \min \{ f[i - 1] + 1, \min_{j \in [0, i - 1]} f[j] \}
$$

其中 $i \ge 1$，而 $j \in [0, i - 1]$ 且 $s[j..i)$ 在哈希表 $ss$ 中。

最终答案为 $f[n]$。

时间复杂度 $O(n^3 + L)$，空间复杂度 $O(n + L)$。其中 $n$ 是字符串 $s$ 的长度，而 $L$ 是字典中所有单词的长度之和。

<!-- tabs:start -->

```python
class Solution:
    def minExtraChar(self, s: str, dictionary: List[str]) -> int:
        ss = set(dictionary)
        n = len(s)
        f = [0] * (n + 1)
        for i in range(1, n + 1):
            f[i] = f[i - 1] + 1
            for j in range(i):
                if s[j:i] in ss and f[j] < f[i]:
                    f[i] = f[j]
        return f[n]
```

```java
class Solution {
    public int minExtraChar(String s, String[] dictionary) {
        Set<String> ss = new HashSet<>();
        for (String w : dictionary) {
            ss.add(w);
        }
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 0;
        for (int i = 1; i <= n; ++i) {
            f[i] = f[i - 1] + 1;
            for (int j = 0; j < i; ++j) {
                if (ss.contains(s.substring(j, i))) {
                    f[i] = Math.min(f[i], f[j]);
                }
            }
        }
        return f[n];
    }
}
```

```cpp
class Solution {
public:
    int minExtraChar(string s, vector<string>& dictionary) {
        unordered_set<string> ss(dictionary.begin(), dictionary.end());
        int n = s.size();
        int f[n + 1];
        f[0] = 0;
        for (int i = 1; i <= n; ++i) {
            f[i] = f[i - 1] + 1;
            for (int j = 0; j < i; ++j) {
                if (ss.count(s.substr(j, i - j))) {
                    f[i] = min(f[i], f[j]);
                }
            }
        }
        return f[n];
    }
};
```

```go
func minExtraChar(s string, dictionary []string) int {
	ss := map[string]bool{}
	for _, w := range dictionary {
		ss[w] = true
	}
	n := len(s)
	f := make([]int, n+1)
	for i := 1; i <= n; i++ {
		f[i] = f[i-1] + 1
		for j := 0; j < i; j++ {
			if ss[s[j:i]] && f[j] < f[i] {
				f[i] = f[j]
			}
		}
	}
	return f[n]
}
```

```ts
function minExtraChar(s: string, dictionary: string[]): number {
    const ss = new Set(dictionary);
    const n = s.length;
    const f = new Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        f[i] = f[i - 1] + 1;
        for (let j = 0; j < i; ++j) {
            if (ss.has(s.substring(j, i))) {
                f[i] = Math.min(f[i], f[j]);
            }
        }
    }
    return f[n];
}
```

```rust
use std::collections::HashSet;

impl Solution {
    pub fn min_extra_char(s: String, dictionary: Vec<String>) -> i32 {
        let ss: HashSet<String> = dictionary.into_iter().collect();
        let n = s.len();
        let mut f = vec![0; n + 1];
        for i in 1..=n {
            f[i] = f[i - 1] + 1;
            for j in 0..i {
                if ss.contains(&s[j..i]) {
                    f[i] = f[i].min(f[j]);
                }
            }
        }
        f[n]
    }
}
```

<!-- tabs:end -->

### 方法二：字典树 + 动态规划

我们可以借助字典树来优化方法一的时间复杂度。

具体地，我们首先将字典中的每个单词逆序插入到字典树 $root$ 中，然后我们定义 $f[i]$ 表示字符串 $s$ 的前 $i$ 个字符的最小额外字符数，初始时 $f[0] = 0$。

当 $i \ge 1$ 时，第 $i$ 个字符 $s[i - 1]$ 可以作为一个额外字符，此时 $f[i] = f[i - 1] + 1$；我们也可以在 $[0..i-1]$ 的范围内逆序枚举下标 $j$，判断 $s[j..i)$ 是否在字典树 $root$ 中，如果存在，那么我们可以将 $s[j..i)$ 作为一个单词，此时 $f[i] = f[j]$。

时间复杂度 $O(n^2 + L)$，空间复杂度 $O(n + L \times |\Sigma|)$。其中 $n$ 是字符串 $s$ 的长度，而 $L$ 是字典中所有单词的长度之和，另外 $|\Sigma|$ 是字符集的大小，本题中字符集为小写英文字母，因此 $|\Sigma| = 26$。

<!-- tabs:start -->

```python
class Node:
    __slots__ = ['children', 'is_end']

    def __init__(self):
        self.children: List[Node | None] = [None] * 26
        self.is_end = False


class Solution:
    def minExtraChar(self, s: str, dictionary: List[str]) -> int:
        root = Node()
        for w in dictionary:
            node = root
            for c in w[::-1]:
                i = ord(c) - ord('a')
                if node.children[i] is None:
                    node.children[i] = Node()
                node = node.children[i]
            node.is_end = True

        n = len(s)
        f = [0] * (n + 1)
        for i in range(1, n + 1):
            f[i] = f[i - 1] + 1
            node = root
            for j in range(i - 1, -1, -1):
                node = node.children[ord(s[j]) - ord('a')]
                if node is None:
                    break
                if node.is_end and f[j] < f[i]:
                    f[i] = f[j]
        return f[n]
```

```java
class Node {
    Node[] children = new Node[26];
    boolean isEnd;
}

class Solution {
    public int minExtraChar(String s, String[] dictionary) {
        Node root = new Node();
        for (String w : dictionary) {
            Node node = root;
            for (int k = w.length() - 1; k >= 0; --k) {
                int i = w.charAt(k) - 'a';
                if (node.children[i] == null) {
                    node.children[i] = new Node();
                }
                node = node.children[i];
            }
            node.isEnd = true;
        }
        int n = s.length();
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            f[i] = f[i - 1] + 1;
            Node node = root;
            for (int j = i - 1; j >= 0; --j) {
                node = node.children[s.charAt(j) - 'a'];
                if (node == null) {
                    break;
                }
                if (node.isEnd && f[j] < f[i]) {
                    f[i] = f[j];
                }
            }
        }
        return f[n];
    }
}
```

```cpp
class Node {
public:
    Node* children[26];
    bool isEnd = false;
    Node() {
        fill(children, children + 26, nullptr);
    }
};

class Solution {
public:
    int minExtraChar(string s, vector<string>& dictionary) {
        Node* root = new Node();
        for (const string& w : dictionary) {
            Node* node = root;
            for (int k = w.length() - 1; k >= 0; --k) {
                int i = w[k] - 'a';
                if (node->children[i] == nullptr) {
                    node->children[i] = new Node();
                }
                node = node->children[i];
            }
            node->isEnd = true;
        }

        int n = s.size();
        int f[n + 1];
        f[0] = 0;
        for (int i = 1; i <= n; ++i) {
            f[i] = f[i - 1] + 1;
            Node* node = root;
            for (int j = i - 1; ~j; --j) {
                node = node->children[s[j] - 'a'];
                if (node == nullptr) {
                    break;
                }
                if (node->isEnd && f[j] < f[i]) {
                    f[i] = f[j];
                }
            }
        }
        return f[n];
    }
};
```

```go
type Node struct {
	children [26]*Node
	isEnd    bool
}

func minExtraChar(s string, dictionary []string) int {
	root := &Node{}
	for _, w := range dictionary {
		node := root
		for k := len(w) - 1; k >= 0; k-- {
			i := int(w[k] - 'a')
			if node.children[i] == nil {
				node.children[i] = &Node{}
			}
			node = node.children[i]
		}
		node.isEnd = true
	}

	n := len(s)
	f := make([]int, n+1)
	for i := 1; i <= n; i++ {
		f[i] = f[i-1] + 1
		node := root
		for j := i - 1; j >= 0; j-- {
			node = node.children[int(s[j]-'a')]
			if node == nil {
				break
			}
			if node.isEnd && f[j] < f[i] {
				f[i] = f[j]
			}
		}
	}
	return f[n]
}
```

```ts
class Node {
    children: (Node | null)[] = Array(26).fill(null);
    isEnd: boolean = false;
}

function minExtraChar(s: string, dictionary: string[]): number {
    const root = new Node();
    for (const w of dictionary) {
        let node = root;
        for (let k = w.length - 1; ~k; --k) {
            const i = w.charCodeAt(k) - 'a'.charCodeAt(0);
            if (node.children[i] === null) {
                node.children[i] = new Node();
            }
            node = node.children[i] as Node;
        }
        node.isEnd = true;
    }

    const n = s.length;
    const f: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        f[i] = f[i - 1] + 1;
        let node = root;
        for (let j = i - 1; ~j; --j) {
            node = (node.children[s.charCodeAt(j) - 'a'.charCodeAt(0)] as Node) || null;
            if (node === null) {
                break;
            }
            if (node.isEnd && f[j] < f[i]) {
                f[i] = f[j];
            }
        }
    }

    return f[n];
}
```

<!-- tabs:end -->

<!-- end -->
