---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2213.Longest%20Substring%20of%20One%20Repeating%20Character/README.md
rating: 2628
source: 第 285 场周赛 Q4
tags:
    - 线段树
    - 数组
    - 字符串
    - 有序集合
---

<!-- problem:start -->

# [2213. 由单个字符重复的最长子字符串](https://leetcode.cn/problems/longest-substring-of-one-repeating-character)

[English Version](/solution/2200-2299/2213.Longest%20Substring%20of%20One%20Repeating%20Character/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串 <code>s</code> 。另给你一个下标从 <strong>0</strong> 开始、长度为 <code>k</code> 的字符串 <code>queryCharacters</code> ，一个下标从 <code>0</code> 开始、长度也是 <code>k</code> 的整数 <strong>下标</strong> 数组&nbsp;<code>queryIndices</code> ，这两个都用来描述 <code>k</code> 个查询。</p>

<p>第 <code>i</code> 个查询会将 <code>s</code> 中位于下标 <code>queryIndices[i]</code> 的字符更新为 <code>queryCharacters[i]</code> 。</p>

<p>返回一个长度为 <code>k</code> 的数组 <code>lengths</code> ，其中 <code>lengths[i]</code> 是在执行第 <code>i</code> 个查询 <strong>之后</strong> <code>s</code> 中仅由 <strong>单个字符重复</strong> 组成的 <strong>最长子字符串</strong> 的 <strong>长度</strong> <em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "babacc", queryCharacters = "bcb", queryIndices = [1,3,3]
<strong>输出：</strong>[3,3,4]
<strong>解释：</strong>
- 第 1 次查询更新后 s = "<em>b<strong>b</strong>b</em>acc" 。由单个字符重复组成的最长子字符串是 "bbb" ，长度为 3 。
- 第 2 次查询更新后 s = "bbb<em><strong>c</strong>cc</em>" 。由单个字符重复组成的最长子字符串是 "bbb" 或 "ccc"，长度为 3 。
- 第 3 次查询更新后 s = "<em>bbb<strong>b</strong></em>cc" 。由单个字符重复组成的最长子字符串是 "bbbb" ，长度为 4 。
因此，返回 [3,3,4] 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abyzz", queryCharacters = "aa", queryIndices = [2,1]
<strong>输出：</strong>[2,3]
<strong>解释：</strong>
- 第 1 次查询更新后 s = "ab<strong>a</strong><em>zz</em>" 。由单个字符重复组成的最长子字符串是 "zz" ，长度为 2 。
- 第 2 次查询更新后 s = "<em>a<strong>a</strong>a</em>zz" 。由单个字符重复组成的最长子字符串是 "aaa" ，长度为 3 。
因此，返回 [2,3] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写英文字母组成</li>
	<li><code>k == queryCharacters.length == queryIndices.length</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>queryCharacters</code> 由小写英文字母组成</li>
	<li><code>0 &lt;= queryIndices[i] &lt; s.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：线段树

线段树将整个区间分割为多个不连续的子区间，子区间的数量不超过 $\log(\textit{width})$。更新某个元素的值，只需要更新 $\log(\textit{width})$ 个区间，并且这些区间都包含在一个包含该元素的大区间内。区间修改时，需要使用**懒标记**保证效率。

- 线段树的每个节点代表一个区间；
- 线段树具有唯一的根节点，代表的区间是整个统计范围，如 $[1, n]$；
- 线段树的每个叶子节点代表一个长度为 $1$ 的元区间 $[x, x]$；
- 对于每个内部节点 $[l, r]$，它的左儿子是 $[l, mid]$，右儿子是 $[mid + 1, r]$, 其中 $mid = \frac{l + r}{2}$；

对于本题，线段树节点维护的信息有：

1. 前缀最长连续字符个数 $lmx$；
1. 后缀最长连续字符个数 $rmx$；
1. 区间最长连续字符个数 $mx$。
1. 区间左端点 $l$ 和右端点 $r$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n \times \log n)$。其中 $n$ 是字符串 $s$ 的长度。

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
