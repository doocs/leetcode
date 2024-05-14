# [3093. Longest Common Suffix Queries](https://leetcode.com/problems/longest-common-suffix-queries)

[中文文档](/solution/3000-3099/3093.Longest%20Common%20Suffix%20Queries/README.md)

<!-- tags:Trie,Array,String -->

<!-- difficulty:Hard -->

## Description

<p>You are given two arrays of strings <code>wordsContainer</code> and <code>wordsQuery</code>.</p>

<p>For each <code>wordsQuery[i]</code>, you need to find a string from <code>wordsContainer</code> that has the <strong>longest common suffix</strong> with <code>wordsQuery[i]</code>. If there are two or more strings in <code>wordsContainer</code> that share the longest common suffix, find the string that is the <strong>smallest</strong> in length. If there are two or more such strings that have the <strong>same</strong> smallest length, find the one that occurred <strong>earlier</strong> in <code>wordsContainer</code>.</p>

<p>Return <em>an array of integers </em><code>ans</code><em>, where </em><code>ans[i]</code><em> is the index of the string in </em><code>wordsContainer</code><em> that has the <strong>longest common suffix</strong> with </em><code>wordsQuery[i]</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">wordsContainer = [&quot;abcd&quot;,&quot;bcd&quot;,&quot;xbcd&quot;], wordsQuery = [&quot;cd&quot;,&quot;bcd&quot;,&quot;xyz&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>Let&#39;s look at each <code>wordsQuery[i]</code> separately:</p>

<ul>
	<li>For <code>wordsQuery[0] = &quot;cd&quot;</code>, strings from <code>wordsContainer</code> that share the longest common suffix <code>&quot;cd&quot;</code> are at indices 0, 1, and 2. Among these, the answer is the string at index 1 because it has the shortest length of 3.</li>
	<li>For <code>wordsQuery[1] = &quot;bcd&quot;</code>, strings from <code>wordsContainer</code> that share the longest common suffix <code>&quot;bcd&quot;</code> are at indices 0, 1, and 2. Among these, the answer is the string at index 1 because it has the shortest length of 3.</li>
	<li>For <code>wordsQuery[2] = &quot;xyz&quot;</code>, there is no string from <code>wordsContainer</code> that shares a common suffix. Hence the longest common suffix is <code>&quot;&quot;</code>, that is shared with strings at index 0, 1, and 2. Among these, the answer is the string at index 1 because it has the shortest length of 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">wordsContainer = [&quot;abcdefgh&quot;,&quot;poiuygh&quot;,&quot;ghghgh&quot;], wordsQuery = [&quot;gh&quot;,&quot;acbfgh&quot;,&quot;acbfegh&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,0,2]</span></p>

<p><strong>Explanation:</strong></p>

<p>Let&#39;s look at each <code>wordsQuery[i]</code> separately:</p>

<ul>
	<li>For <code>wordsQuery[0] = &quot;gh&quot;</code>, strings from <code>wordsContainer</code> that share the longest common suffix <code>&quot;gh&quot;</code> are at indices 0, 1, and 2. Among these, the answer is the string at index 2 because it has the shortest length of 6.</li>
	<li>For <code>wordsQuery[1] = &quot;acbfgh&quot;</code>, only the string at index 0 shares the longest common suffix <code>&quot;fgh&quot;</code>. Hence it is the answer, even though the string at index 2 is shorter.</li>
	<li>For <code>wordsQuery[2] = &quot;acbfegh&quot;</code>, strings from <code>wordsContainer</code> that share the longest common suffix <code>&quot;gh&quot;</code> are at indices 0, 1, and 2. Among these, the answer is the string at index 2 because it has the shortest length of 6.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= wordsContainer.length, wordsQuery.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= wordsContainer[i].length &lt;= 5 * 10<sup>3</sup></code></li>
	<li><code>1 &lt;= wordsQuery[i].length &lt;= 5 * 10<sup>3</sup></code></li>
	<li><code>wordsContainer[i]</code> consists only of lowercase English letters.</li>
	<li><code>wordsQuery[i]</code> consists only of lowercase English letters.</li>
	<li>Sum of <code>wordsContainer[i].length</code> is at most <code>5 * 10<sup>5</sup></code>.</li>
	<li>Sum of <code>wordsQuery[i].length</code> is at most <code>5 * 10<sup>5</sup></code>.</li>
</ul>

## Solutions

### Solution 1: Trie

The problem requires us to find the longest common suffix, so we can consider using a Trie.

We define the structure of the Trie node as follows:

-   `children`: An array of length 26, used to store child nodes.
-   `length`: The length of the shortest string at the current node.
-   `idx`: The index of the string at the current node.

We traverse the string array `wordsContainer`, and insert each string in reverse order into the Trie. During the insertion process, we update the `length` and `idx` of each node.

Next, we traverse the string array `wordsQuery`. For each string, we search for the index of the longest common suffix string from the Trie. During the search process, if we encounter a null node, it means that there is no common suffix afterwards, and we can directly return the `idx` of the current node.

The time complexity is $(L_1 \times |\Sigma| + L_2)$, and the space complexity is $O(L_1 \times |\Sigma|)$. Here, $L_1$ and $L_2$ are the sum of the lengths of the strings in `wordsContainer` and `wordsQuery` respectively; and $\Sigma$ is the size of the character set, in this problem $\Sigma = 26$.

<!-- tabs:start -->

```python
class Trie:
    __slots__ = ("children", "length", "idx")

    def __init__(self):
        self.children = [None] * 26
        self.length = inf
        self.idx = inf

    def insert(self, w: str, i: int):
        node = self
        if node.length > len(w):
            node.length = len(w)
            node.idx = i
        for c in w[::-1]:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                node.children[idx] = Trie()
            node = node.children[idx]
            if node.length > len(w):
                node.length = len(w)
                node.idx = i

    def query(self, w: str) -> int:
        node = self
        for c in w[::-1]:
            idx = ord(c) - ord("a")
            if node.children[idx] is None:
                break
            node = node.children[idx]
        return node.idx


class Solution:
    def stringIndices(
        self, wordsContainer: List[str], wordsQuery: List[str]
    ) -> List[int]:
        trie = Trie()
        for i, w in enumerate(wordsContainer):
            trie.insert(w, i)
        return [trie.query(w) for w in wordsQuery]
```

```java
class Trie {
    private final int inf = 1 << 30;
    private Trie[] children = new Trie[26];
    private int length = inf;
    private int idx = inf;

    public void insert(String w, int i) {
        Trie node = this;
        if (node.length > w.length()) {
            node.length = w.length();
            node.idx = i;
        }
        for (int k = w.length() - 1; k >= 0; --k) {
            int idx = w.charAt(k) - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
            if (node.length > w.length()) {
                node.length = w.length();
                node.idx = i;
            }
        }
    }

    public int query(String w) {
        Trie node = this;
        for (int k = w.length() - 1; k >= 0; --k) {
            int idx = w.charAt(k) - 'a';
            if (node.children[idx] == null) {
                break;
            }
            node = node.children[idx];
        }
        return node.idx;
    }
}

class Solution {
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Trie trie = new Trie();
        for (int i = 0; i < wordsContainer.length; ++i) {
            trie.insert(wordsContainer[i], i);
        }
        int n = wordsQuery.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = trie.query(wordsQuery[i]);
        }
        return ans;
    }
}
```

```cpp
class Trie {
private:
    const int inf = 1 << 30;
    Trie* children[26];
    int length = inf;
    int idx = inf;

public:
    Trie() {
        for (int i = 0; i < 26; ++i) {
            children[i] = nullptr;
        }
    }

    void insert(string w, int i) {
        Trie* node = this;
        if (node->length > w.length()) {
            node->length = w.length();
            node->idx = i;
        }
        for (int k = w.length() - 1; k >= 0; --k) {
            int idx = w[k] - 'a';
            if (node->children[idx] == nullptr) {
                node->children[idx] = new Trie();
            }
            node = node->children[idx];
            if (node->length > w.length()) {
                node->length = w.length();
                node->idx = i;
            }
        }
    }

    int query(string w) {
        Trie* node = this;
        for (int k = w.length() - 1; k >= 0; --k) {
            int idx = w[k] - 'a';
            if (node->children[idx] == nullptr) {
                break;
            }
            node = node->children[idx];
        }
        return node->idx;
    }
};

class Solution {
public:
    vector<int> stringIndices(vector<string>& wordsContainer, vector<string>& wordsQuery) {
        Trie* trie = new Trie();
        for (int i = 0; i < wordsContainer.size(); ++i) {
            trie->insert(wordsContainer[i], i);
        }
        int n = wordsQuery.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = trie->query(wordsQuery[i]);
        }
        return ans;
    }
};
```

```go
const inf = 1 << 30

type Trie struct {
	children [26]*Trie
	length   int
	idx      int
}

func newTrie() *Trie {
	return &Trie{length: inf, idx: inf}
}

func (t *Trie) insert(w string, i int) {
	node := t
	if node.length > len(w) {
		node.length = len(w)
		node.idx = i
	}
	for k := len(w) - 1; k >= 0; k-- {
		idx := int(w[k] - 'a')
		if node.children[idx] == nil {
			node.children[idx] = newTrie()
		}
		node = node.children[idx]
		if node.length > len(w) {
			node.length = len(w)
			node.idx = i
		}
	}
}

func (t *Trie) query(w string) int {
	node := t
	for k := len(w) - 1; k >= 0; k-- {
		idx := int(w[k] - 'a')
		if node.children[idx] == nil {
			break
		}
		node = node.children[idx]
	}
	return node.idx
}

func stringIndices(wordsContainer []string, wordsQuery []string) (ans []int) {
	trie := newTrie()
	for i, w := range wordsContainer {
		trie.insert(w, i)
	}
	for _, w := range wordsQuery {
		ans = append(ans, trie.query(w))
	}
	return
}
```

```ts
class Trie {
    private children: Trie[] = new Array<Trie>(26);
    private length: number = Infinity;
    private idx: number = Infinity;

    public insert(w: string, i: number): void {
        let node: Trie = this;
        if (node.length > w.length) {
            node.length = w.length;
            node.idx = i;
        }
        for (let k: number = w.length - 1; k >= 0; --k) {
            let idx: number = w.charCodeAt(k) - 'a'.charCodeAt(0);
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
            if (node.length > w.length) {
                node.length = w.length;
                node.idx = i;
            }
        }
    }

    public query(w: string): number {
        let node: Trie = this;
        for (let k: number = w.length - 1; k >= 0; --k) {
            let idx: number = w.charCodeAt(k) - 'a'.charCodeAt(0);
            if (node.children[idx] == null) {
                break;
            }
            node = node.children[idx];
        }
        return node.idx;
    }
}

function stringIndices(wordsContainer: string[], wordsQuery: string[]): number[] {
    const trie: Trie = new Trie();
    for (let i: number = 0; i < wordsContainer.length; ++i) {
        trie.insert(wordsContainer[i], i);
    }
    const n: number = wordsQuery.length;
    const ans: number[] = new Array<number>(n);
    for (let i: number = 0; i < n; ++i) {
        ans[i] = trie.query(wordsQuery[i]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
