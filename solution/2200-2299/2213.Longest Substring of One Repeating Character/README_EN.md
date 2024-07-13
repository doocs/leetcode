---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2213.Longest%20Substring%20of%20One%20Repeating%20Character/README_EN.md
rating: 2628
source: Weekly Contest 285 Q4
tags:
    - Segment Tree
    - Array
    - String
    - Ordered Set
---

<!-- problem:start -->

# [2213. Longest Substring of One Repeating Character](https://leetcode.com/problems/longest-substring-of-one-repeating-character)

[中文文档](/solution/2200-2299/2213.Longest%20Substring%20of%20One%20Repeating%20Character/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> string <code>s</code>. You are also given a <strong>0-indexed</strong> string <code>queryCharacters</code> of length <code>k</code> and a <strong>0-indexed</strong> array of integer <strong>indices</strong> <code>queryIndices</code> of length <code>k</code>, both of which are used to describe <code>k</code> queries.</p>

<p>The <code>i<sup>th</sup></code> query updates the character in <code>s</code> at index <code>queryIndices[i]</code> to the character <code>queryCharacters[i]</code>.</p>

<p>Return <em>an array</em> <code>lengths</code> <em>of length </em><code>k</code><em> where</em> <code>lengths[i]</code> <em>is the <strong>length</strong> of the <strong>longest substring</strong> of </em><code>s</code><em> consisting of <strong>only one repeating</strong> character <strong>after</strong> the</em> <code>i<sup>th</sup></code> <em>query</em><em> is performed.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;babacc&quot;, queryCharacters = &quot;bcb&quot;, queryIndices = [1,3,3]
<strong>Output:</strong> [3,3,4]
<strong>Explanation:</strong> 
- 1<sup>st</sup> query updates s = &quot;<u>b<strong>b</strong>b</u>acc&quot;. The longest substring consisting of one repeating character is &quot;bbb&quot; with length 3.
- 2<sup>nd</sup> query updates s = &quot;bbb<u><strong>c</strong>cc</u>&quot;. 
  The longest substring consisting of one repeating character can be &quot;bbb&quot; or &quot;ccc&quot; with length 3.
- 3<sup>rd</sup> query updates s = &quot;<u>bbb<strong>b</strong></u>cc&quot;. The longest substring consisting of one repeating character is &quot;bbbb&quot; with length 4.
Thus, we return [3,3,4].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abyzz&quot;, queryCharacters = &quot;aa&quot;, queryIndices = [2,1]
<strong>Output:</strong> [2,3]
<strong>Explanation:</strong>
- 1<sup>st</sup> query updates s = &quot;ab<strong>a</strong><u>zz</u>&quot;. The longest substring consisting of one repeating character is &quot;zz&quot; with length 2.
- 2<sup>nd</sup> query updates s = &quot;<u>a<strong>a</strong>a</u>zz&quot;. The longest substring consisting of one repeating character is &quot;aaa&quot; with length 3.
Thus, we return [2,3].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>k == queryCharacters.length == queryIndices.length</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>queryCharacters</code> consists of lowercase English letters.</li>
	<li><code>0 &lt;= queryIndices[i] &lt; s.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Segment Tree

The segment tree divides the entire interval into multiple non-continuous sub-intervals, and the number of sub-intervals does not exceed $\log(\text{width})$. To update the value of an element, you only need to update $\log(\text{width})$ intervals, and these intervals are all contained in a large interval that contains the element. When modifying the interval, you need to use **lazy tags** to ensure efficiency.

-   Each node of the segment tree represents an interval;
-   The segment tree has a unique root node, which represents the entire statistical range, such as $[1, n]$;
-   Each leaf node of the segment tree represents an elementary interval of length $1$, $[x, x]$;
-   For each internal node $[l, r]$, its left child is $[l, mid]$, and the right child is $[mid + 1, r]$, where $mid = \frac{l + r}{2}$;

For this problem, the information maintained by the segment tree node includes:

1. The number of longest consecutive characters in the prefix, $lmx$;
2. The number of longest consecutive characters in the suffix, $rmx$;
3. The number of longest consecutive characters in the interval, $mx$.
4. The left endpoint $l$ and the right endpoint $r$ of the interval.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n \times \log n)$. Where $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
def max(a: int, b: int) -> int:
    return a if a > b else b


class Node:
    __slots__ = "l", "r", "lmx", "rmx", "mx"

    def __init__(self, l: int, r: int):
        self.l = l
        self.r = r
        self.lmx = self.rmx = self.mx = 1


class SegmentTree:
    __slots__ = "s", "tr"

    def __init__(self, s: str):
        self.s = list(s)
        n = len(s)
        self.tr: List[Node | None] = [None] * (n * 4)
        self.build(1, 1, n)

    def build(self, u: int, l: int, r: int):
        self.tr[u] = Node(l, r)
        if l == r:
            return
        mid = (l + r) // 2
        self.build(u << 1, l, mid)
        self.build(u << 1 | 1, mid + 1, r)
        self.pushup(u)

    def query(self, u: int, l: int, r: int) -> int:
        if self.tr[u].l >= l and self.tr[u].r <= r:
            return self.tr[u].mx
        mid = (self.tr[u].l + self.tr[u].r) // 2
        ans = 0
        if r <= mid:
            ans = self.query(u << 1, l, r)
        if l > mid:
            ans = max(ans, self.query(u << 1 | 1, l, r))
        return ans

    def modify(self, u: int, x: int, v: str):
        if self.tr[u].l == self.tr[u].r:
            self.s[x - 1] = v
            return
        mid = (self.tr[u].l + self.tr[u].r) // 2
        if x <= mid:
            self.modify(u << 1, x, v)
        else:
            self.modify(u << 1 | 1, x, v)
        self.pushup(u)

    def pushup(self, u: int):
        root, left, right = self.tr[u], self.tr[u << 1], self.tr[u << 1 | 1]
        root.lmx = left.lmx
        root.rmx = right.rmx
        root.mx = max(left.mx, right.mx)
        a, b = left.r - left.l + 1, right.r - right.l + 1
        if self.s[left.r - 1] == self.s[right.l - 1]:
            if left.lmx == a:
                root.lmx += right.lmx
            if right.rmx == b:
                root.rmx += left.rmx
            root.mx = max(root.mx, left.rmx + right.lmx)


class Solution:
    def longestRepeating(
        self, s: str, queryCharacters: str, queryIndices: List[int]
    ) -> List[int]:
        tree = SegmentTree(s)
        ans = []
        for x, v in zip(queryIndices, queryCharacters):
            tree.modify(1, x + 1, v)
            ans.append(tree.query(1, 1, len(s)))
        return ans
```

#### Java

```java
class Node {
    int l, r;
    int lmx, rmx, mx;

    Node(int l, int r) {
        this.l = l;
        this.r = r;
        lmx = rmx = mx = 1;
    }
}

class SegmentTree {
    private char[] s;
    private Node[] tr;

    public SegmentTree(char[] s) {
        int n = s.length;
        this.s = s;
        tr = new Node[n << 2];
        build(1, 1, n);
    }

    public void build(int u, int l, int r) {
        tr[u] = new Node(l, r);
        if (l == r) {
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    public void modify(int u, int x, char v) {
        if (tr[u].l == x && tr[u].r == x) {
            s[x - 1] = v;
            return;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (x <= mid) {
            modify(u << 1, x, v);
        } else {
            modify(u << 1 | 1, x, v);
        }
        pushup(u);
    }

    public int query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u].mx;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        int ans = 0;
        if (r <= mid) {
            ans = query(u << 1, l, r);
        }
        if (l > mid) {
            ans = Math.max(ans, query(u << 1 | 1, l, r));
        }
        return ans;
    }

    private void pushup(int u) {
        Node root = tr[u];
        Node left = tr[u << 1], right = tr[u << 1 | 1];
        root.mx = Math.max(left.mx, right.mx);
        root.lmx = left.lmx;
        root.rmx = right.rmx;
        int a = left.r - left.l + 1;
        int b = right.r - right.l + 1;
        if (s[left.r - 1] == s[right.l - 1]) {
            if (left.lmx == a) {
                root.lmx += right.lmx;
            }
            if (right.rmx == b) {
                root.rmx += left.rmx;
            }
            root.mx = Math.max(root.mx, left.rmx + right.lmx);
        }
    }
}

class Solution {
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        SegmentTree tree = new SegmentTree(s.toCharArray());
        int k = queryIndices.length;
        int[] ans = new int[k];
        int n = s.length();
        for (int i = 0; i < k; ++i) {
            int x = queryIndices[i] + 1;
            char v = queryCharacters.charAt(i);
            tree.modify(1, x, v);
            ans[i] = tree.query(1, 1, n);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Node {
public:
    int l, r;
    int lmx, rmx, mx;

    Node(int l, int r)
        : l(l)
        , r(r)
        , lmx(1)
        , rmx(1)
        , mx(1) {}
};

class SegmentTree {
private:
    string s;
    vector<Node*> tr;

    void build(int u, int l, int r) {
        tr[u] = new Node(l, r);
        if (l == r) {
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    void pushup(int u) {
        Node* root = tr[u];
        Node* left = tr[u << 1];
        Node* right = tr[u << 1 | 1];
        root->mx = max(left->mx, right->mx);
        root->lmx = left->lmx;
        root->rmx = right->rmx;
        int a = left->r - left->l + 1;
        int b = right->r - right->l + 1;
        if (s[left->r - 1] == s[right->l - 1]) {
            if (left->lmx == a) {
                root->lmx += right->lmx;
            }
            if (right->rmx == b) {
                root->rmx += left->rmx;
            }
            root->mx = max(root->mx, left->rmx + right->lmx);
        }
    }

public:
    SegmentTree(const string& s)
        : s(s) {
        int n = s.size();
        tr.resize(n * 4);
        build(1, 1, n);
    }

    void modify(int u, int x, char v) {
        if (tr[u]->l == x && tr[u]->r == x) {
            s[x - 1] = v;
            return;
        }
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (x <= mid) {
            modify(u << 1, x, v);
        } else {
            modify(u << 1 | 1, x, v);
        }
        pushup(u);
    }

    int query(int u, int l, int r) {
        if (tr[u]->l >= l && tr[u]->r <= r) {
            return tr[u]->mx;
        }
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        int ans = 0;
        if (r <= mid) {
            ans = query(u << 1, l, r);
        } else if (l > mid) {
            ans = max(ans, query(u << 1 | 1, l, r));
        }
        return ans;
    }
};

class Solution {
public:
    vector<int> longestRepeating(string s, string queryCharacters, vector<int>& queryIndices) {
        SegmentTree tree(s);
        int k = queryIndices.size();
        vector<int> ans(k);
        int n = s.size();
        for (int i = 0; i < k; ++i) {
            int x = queryIndices[i] + 1;
            char v = queryCharacters[i];
            tree.modify(1, x, v);
            ans[i] = tree.query(1, 1, n);
        }
        return ans;
    }
};
```

#### Go

```go
type Node struct {
	l, r         int
	lmx, rmx, mx int
}

type SegmentTree struct {
	s  []byte
	tr []*Node
}

func NewNode(l, r int) *Node {
	return &Node{l: l, r: r, lmx: 1, rmx: 1, mx: 1}
}

func NewSegmentTree(s string) *SegmentTree {
	n := len(s)
	tree := &SegmentTree{s: []byte(s), tr: make([]*Node, n<<2)}
	tree.build(1, 1, n)
	return tree
}

func (tree *SegmentTree) build(u, l, r int) {
	tree.tr[u] = NewNode(l, r)
	if l == r {
		return
	}
	mid := (l + r) >> 1
	tree.build(u<<1, l, mid)
	tree.build(u<<1|1, mid+1, r)
	tree.pushup(u)
}

func (tree *SegmentTree) modify(u, x int, v byte) {
	if tree.tr[u].l == x && tree.tr[u].r == x {
		tree.s[x-1] = v
		return
	}
	mid := (tree.tr[u].l + tree.tr[u].r) >> 1
	if x <= mid {
		tree.modify(u<<1, x, v)
	} else {
		tree.modify(u<<1|1, x, v)
	}
	tree.pushup(u)
}

func (tree *SegmentTree) query(u, l, r int) int {
	if tree.tr[u].l >= l && tree.tr[u].r <= r {
		return tree.tr[u].mx
	}
	mid := (tree.tr[u].l + tree.tr[u].r) >> 1
	ans := 0
	if r <= mid {
		ans = tree.query(u<<1, l, r)
	} else if l > mid {
		ans = max(ans, tree.query(u<<1|1, l, r))
	} else {
		ans = max(tree.query(u<<1, l, r), tree.query(u<<1|1, l, r))
	}
	return ans
}

func (tree *SegmentTree) pushup(u int) {
	root := tree.tr[u]
	left := tree.tr[u<<1]
	right := tree.tr[u<<1|1]
	root.mx = max(left.mx, right.mx)
	root.lmx = left.lmx
	root.rmx = right.rmx
	a := left.r - left.l + 1
	b := right.r - right.l + 1
	if tree.s[left.r-1] == tree.s[right.l-1] {
		if left.lmx == a {
			root.lmx += right.lmx
		}
		if right.rmx == b {
			root.rmx += left.rmx
		}
		root.mx = max(root.mx, left.rmx+right.lmx)
	}
}

func longestRepeating(s string, queryCharacters string, queryIndices []int) (ans []int) {
	tree := NewSegmentTree(s)
	n := len(s)
	for i, v := range queryCharacters {
		x := queryIndices[i] + 1
		tree.modify(1, x, byte(v))
		ans = append(ans, tree.query(1, 1, n))
	}
	return
}
```

#### TypeScript

```ts
class Node {
    l: number;
    r: number;
    lmx: number;
    rmx: number;
    mx: number;

    constructor(l: number, r: number) {
        this.l = l;
        this.r = r;
        this.lmx = 1;
        this.rmx = 1;
        this.mx = 1;
    }
}

class SegmentTree {
    private s: string[];
    private tr: Node[];

    constructor(s: string) {
        this.s = s.split('');
        this.tr = Array(s.length * 4)
            .fill(null)
            .map(() => new Node(0, 0));
        this.build(1, 1, s.length);
    }

    private build(u: number, l: number, r: number): void {
        this.tr[u] = new Node(l, r);
        if (l === r) {
            return;
        }
        const mid = (l + r) >> 1;
        this.build(u << 1, l, mid);
        this.build((u << 1) | 1, mid + 1, r);
        this.pushup(u);
    }

    public modify(u: number, x: number, v: string): void {
        if (this.tr[u].l === x && this.tr[u].r === x) {
            this.s[x - 1] = v;
            return;
        }
        const mid = (this.tr[u].l + this.tr[u].r) >> 1;
        if (x <= mid) {
            this.modify(u << 1, x, v);
        } else {
            this.modify((u << 1) | 1, x, v);
        }
        this.pushup(u);
    }

    public query(u: number, l: number, r: number): number {
        if (this.tr[u].l >= l && this.tr[u].r <= r) {
            return this.tr[u].mx;
        }
        const mid = (this.tr[u].l + this.tr[u].r) >> 1;
        let ans = 0;
        if (r <= mid) {
            ans = this.query(u << 1, l, r);
        } else if (l > mid) {
            ans = Math.max(ans, this.query((u << 1) | 1, l, r));
        } else {
            ans = Math.max(this.query(u << 1, l, r), this.query((u << 1) | 1, l, r));
        }
        return ans;
    }

    private pushup(u: number): void {
        const root = this.tr[u];
        const left = this.tr[u << 1];
        const right = this.tr[(u << 1) | 1];
        root.mx = Math.max(left.mx, right.mx);
        root.lmx = left.lmx;
        root.rmx = right.rmx;
        const a = left.r - left.l + 1;
        const b = right.r - right.l + 1;
        if (this.s[left.r - 1] === this.s[right.l - 1]) {
            if (left.lmx === a) {
                root.lmx += right.lmx;
            }
            if (right.rmx === b) {
                root.rmx += left.rmx;
            }
            root.mx = Math.max(root.mx, left.rmx + right.lmx);
        }
    }
}

function longestRepeating(s: string, queryCharacters: string, queryIndices: number[]): number[] {
    const tree = new SegmentTree(s);
    const k = queryIndices.length;
    const ans: number[] = new Array(k);
    const n = s.length;
    for (let i = 0; i < k; ++i) {
        const x = queryIndices[i] + 1;
        const v = queryCharacters[i];
        tree.modify(1, x, v);
        ans[i] = tree.query(1, 1, n);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
