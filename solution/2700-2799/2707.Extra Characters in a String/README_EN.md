# [2707. Extra Characters in a String](https://leetcode.com/problems/extra-characters-in-a-string)

[中文文档](/solution/2700-2799/2707.Extra%20Characters%20in%20a%20String/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>s</code> and a dictionary of words <code>dictionary</code>. You have to break <code>s</code> into one or more <strong>non-overlapping</strong> substrings such that each substring is present in <code>dictionary</code>. There may be some <strong>extra characters</strong> in <code>s</code> which are not present in any of the substrings.</p>

<p>Return <em>the <strong>minimum</strong> number of extra characters left over if you break up </em><code>s</code><em> optimally.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetscode&quot;, dictionary = [&quot;leet&quot;,&quot;code&quot;,&quot;leetcode&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can break s in two substrings: &quot;leet&quot; from index 0 to 3 and &quot;code&quot; from index 5 to 8. There is only 1 unused character (at index 4), so we return 1.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;sayhelloworld&quot;, dictionary = [&quot;hello&quot;,&quot;world&quot;]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can break s in two substrings: &quot;hello&quot; from index 3 to 7 and &quot;world&quot; from index 8 to 12. The characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence, we return 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 50</code></li>
	<li><code>1 &lt;= dictionary.length &lt;= 50</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 50</code></li>
	<li><code>dictionary[i]</code>&nbsp;and <code>s</code> consists of only lowercase English letters</li>
	<li><code>dictionary</code> contains distinct words</li>
</ul>

## Solutions

### Solution 1: Hash Table + Dynamic Programming

We can use a hash table $ss$ to record all words in the dictionary, which allows us to quickly determine whether a string is in the dictionary.

Next, we define $f[i]$ to represent the minimum number of extra characters in the first $i$ characters of string $s$, initially $f[0] = 0$.

When $i \ge 1$, the $i$th character $s[i - 1]$ can be an extra character, in which case $f[i] = f[i - 1] + 1$. If there exists an index $j \in [0, i - 1]$ such that $s[j..i)$ is in the hash table $ss$, then we can take $s[j..i)$ as a word, in which case $f[i] = f[j]$.

In summary, we can get the state transition equation:

$$
f[i] = \min \{ f[i - 1] + 1, \min_{j \in [0, i - 1]} f[j] \}
$$

where $i \ge 1$, and $j \in [0, i - 1]$ and $s[j..i)$ is in the hash table $ss$.

The final answer is $f[n]$.

The time complexity is $O(n^3 + L)$, and the space complexity is $O(n + L)$. Here, $n$ is the length of string $s$, and $L$ is the sum of the lengths of all words in the dictionary.

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

### Solution 2: Trie + Dynamic Programming

We can use a trie to optimize the time complexity of Solution 1.

Specifically, we first insert each word in the dictionary into the trie $root$ in reverse order, then we define $f[i]$ to represent the minimum number of extra characters in the first $i$ characters of string $s$, initially $f[0] = 0$.

When $i \ge 1$, the $i$th character $s[i - 1]$ can be an extra character, in which case $f[i] = f[i - 1] + 1$. We can also enumerate the index $j$ in reverse order in the range $[0..i-1]$, and determine whether $s[j..i)$ is in the trie $root$. If it exists, then we can take $s[j..i)$ as a word, in which case $f[i] = f[j]$.

The time complexity is $O(n^2 + L)$, and the space complexity is $O(n + L \times |\Sigma|)$. Here, $n$ is the length of string $s$, and $L$ is the sum of the lengths of all words in the dictionary. Additionally, $|\Sigma|$ is the size of the character set. In this problem, the character set is lowercase English letters, so $|\Sigma| = 26$.

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
