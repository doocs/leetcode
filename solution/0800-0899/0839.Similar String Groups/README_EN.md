---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0839.Similar%20String%20Groups/README_EN.md
tags:
    - Depth-First Search
    - Breadth-First Search
    - Union Find
    - Array
    - Hash Table
    - String
---

<!-- problem:start -->

# [839. Similar String Groups](https://leetcode.com/problems/similar-string-groups)

[中文文档](/solution/0800-0899/0839.Similar%20String%20Groups/README.md)

## Description

<!-- description:start -->

<p>Two strings, <code>X</code> and <code>Y</code>, are considered similar if either they are identical or we can make them equivalent by swapping at most two letters (in distinct positions) within the string <code>X</code>.</p>

<p>For example, <code>&quot;tars&quot;</code>&nbsp;and <code>&quot;rats&quot;</code>&nbsp;are similar (swapping at positions <code>0</code> and <code>2</code>), and <code>&quot;rats&quot;</code> and <code>&quot;arts&quot;</code> are similar, but <code>&quot;star&quot;</code> is not similar to <code>&quot;tars&quot;</code>, <code>&quot;rats&quot;</code>, or <code>&quot;arts&quot;</code>.</p>

<p>Together, these form two connected groups by similarity: <code>{&quot;tars&quot;, &quot;rats&quot;, &quot;arts&quot;}</code> and <code>{&quot;star&quot;}</code>.&nbsp; Notice that <code>&quot;tars&quot;</code> and <code>&quot;arts&quot;</code> are in the same group even though they are not similar.&nbsp; Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.</p>

<p>We are given a list <code>strs</code> of strings where every string in <code>strs</code> is an anagram of every other string in <code>strs</code>. How many groups are there?</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;tars&quot;,&quot;rats&quot;,&quot;arts&quot;,&quot;star&quot;]
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;omv&quot;,&quot;ovm&quot;]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 300</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 300</code></li>
	<li><code>strs[i]</code> consists of lowercase letters only.</li>
	<li>All words in <code>strs</code> have the same length and are anagrams of each other.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Union-Find

We can enumerate any two strings $s$ and $t$ in the list of strings. Since $s$ and $t$ are anagrams, if the number of differing characters at corresponding positions between $s$ and $t$ does not exceed $2$, then $s$ and $t$ are similar. We can use the union-find data structure to merge $s$ and $t$. If the merge is successful, the number of similar string groups decreases by $1$.

The final number of similar string groups is the number of connected components in the union-find structure.

Time complexity is $O(n^2 \times (m + \alpha(n)))$, and space complexity is $O(n)$. Here, $n$ and $m$ are the length of the list of strings and the length of the strings, respectively, and $\alpha(n)$ is the inverse Ackermann function, which can be considered a very small constant.

<!-- tabs:start -->

#### Python3

```python
class UnionFind:
    def __init__(self, n):
        self.p = list(range(n))
        self.size = [1] * n

    def find(self, x):
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]

    def union(self, a, b):
        pa, pb = self.find(a), self.find(b)
        if pa == pb:
            return False
        if self.size[pa] > self.size[pb]:
            self.p[pb] = pa
            self.size[pa] += self.size[pb]
        else:
            self.p[pa] = pb
            self.size[pb] += self.size[pa]
        return True


class Solution:
    def numSimilarGroups(self, strs: List[str]) -> int:
        n, m = len(strs), len(strs[0])
        uf = UnionFind(n)
        for i, s in enumerate(strs):
            for j, t in enumerate(strs[:i]):
                if sum(s[k] != t[k] for k in range(m)) <= 2 and uf.union(i, j):
                    n -= 1
        return n
```

#### Java

```java
class UnionFind {
    private final int[] p;
    private final int[] size;

    public UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }
}

class Solution {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length, m = strs[0].length();
        UnionFind uf = new UnionFind(n);
        int cnt = n;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int diff = 0;
                for (int k = 0; k < m; ++k) {
                    if (strs[i].charAt(k) != strs[j].charAt(k)) {
                        ++diff;
                    }
                }
                if (diff <= 2 && uf.union(i, j)) {
                    --cnt;
                }
            }
        }
        return cnt;
    }
}
```

#### C++

```cpp
class UnionFind {
public:
    UnionFind(int n) {
        p = vector<int>(n);
        size = vector<int>(n, 1);
        iota(p.begin(), p.end(), 0);
    }

    bool unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

private:
    vector<int> p, size;
};

class Solution {
public:
    int numSimilarGroups(vector<string>& strs) {
        int n = strs.size(), m = strs[0].size();
        int cnt = n;
        UnionFind uf(n);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int diff = 0;
                for (int k = 0; k < m; ++k) {
                    diff += strs[i][k] != strs[j][k];
                }
                if (diff <= 2 && uf.unite(i, j)) {
                    --cnt;
                }
            }
        }
        return cnt;
    }
};
```

#### Go

```go
type unionFind struct {
	p, size []int
}

func newUnionFind(n int) *unionFind {
	p := make([]int, n)
	size := make([]int, n)
	for i := range p {
		p[i] = i
		size[i] = 1
	}
	return &unionFind{p, size}
}

func (uf *unionFind) find(x int) int {
	if uf.p[x] != x {
		uf.p[x] = uf.find(uf.p[x])
	}
	return uf.p[x]
}

func (uf *unionFind) union(a, b int) bool {
	pa, pb := uf.find(a), uf.find(b)
	if pa == pb {
		return false
	}
	if uf.size[pa] > uf.size[pb] {
		uf.p[pb] = pa
		uf.size[pa] += uf.size[pb]
	} else {
		uf.p[pa] = pb
		uf.size[pb] += uf.size[pa]
	}
	return true
}

func numSimilarGroups(strs []string) int {
	n := len(strs)
	uf := newUnionFind(n)
	for i, s := range strs {
		for j, t := range strs[:i] {
			diff := 0
			for k := range s {
				if s[k] != t[k] {
					diff++
				}
			}
			if diff <= 2 && uf.union(i, j) {
				n--
			}
		}
	}
	return n
}
```

#### TypeScript

```ts
class UnionFind {
    private p: number[];
    private size: number[];

    constructor(n: number) {
        this.p = Array.from({ length: n }, (_, i) => i);
        this.size = Array(n).fill(1);
    }

    union(a: number, b: number): boolean {
        const pa = this.find(a);
        const pb = this.find(b);
        if (pa === pb) {
            return false;
        }
        if (this.size[pa] > this.size[pb]) {
            this.p[pb] = pa;
            this.size[pa] += this.size[pb];
        } else {
            this.p[pa] = pb;
            this.size[pb] += this.size[pa];
        }
        return true;
    }

    find(x: number): number {
        if (this.p[x] !== x) {
            this.p[x] = this.find(this.p[x]);
        }
        return this.p[x];
    }
}

function numSimilarGroups(strs: string[]): number {
    const n = strs.length;
    const m = strs[0].length;
    const uf = new UnionFind(n);
    let cnt = n;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            let diff = 0;
            for (let k = 0; k < m; ++k) {
                if (strs[i][k] !== strs[j][k]) {
                    diff++;
                }
            }
            if (diff <= 2 && uf.union(i, j)) {
                cnt--;
            }
        }
    }
    return cnt;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
