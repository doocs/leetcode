# [2213. Longest Substring of One Repeating Character](https://leetcode.com/problems/longest-substring-of-one-repeating-character)

[中文文档](/solution/2200-2299/2213.Longest%20Substring%20of%20One%20Repeating%20Character/README.md)

## Description

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

## Solutions

Segment Tree.

<!-- tabs:start -->

### **Python3**

```python
class Node:
    def __init__(self):
        self.l = 0
        self.r = 0
        self.lmx = 0
        self.rmx = 0
        self.mx = 0
        self.size = 0
        self.lc = None
        self.rc = None


N = 100010
tr = [Node() for _ in range(N << 2)]


class SegmentTree:
    def __init__(self, s):
        n = len(s)
        self.s = s
        self.build(1, 1, n)

    def build(self, u, l, r):
        tr[u].l = l
        tr[u].r = r
        if l == r:
            tr[u].lmx = tr[u].rmx = tr[u].mx = tr[u].size = 1
            tr[u].lc = tr[u].rc = self.s[l - 1]
            return
        mid = (l + r) >> 1
        self.build(u << 1, l, mid)
        self.build(u << 1 | 1, mid + 1, r)
        self.pushup(u)

    def modify(self, u, x, v):
        if tr[u].l == x and tr[u].r == x:
            tr[u].lc = tr[u].rc = v
            return
        mid = (tr[u].l + tr[u].r) >> 1
        if x <= mid:
            self.modify(u << 1, x, v)
        else:
            self.modify(u << 1 | 1, x, v)
        self.pushup(u)

    def query(self, u, l, r):
        if tr[u].l >= l and tr[u].r <= r:
            return tr[u]
        mid = (tr[u].l + tr[u].r) >> 1
        if r <= mid:
            return self.query(u << 1, l, r)
        if l > mid:
            return self.query(u << 1 | 1, l, r)
        left, right = self.query(u << 1, l, r), self.query(u << 1 | 1, l, r)
        ans = Node()
        self._pushup(ans, left, right)
        return ans

    def _pushup(self, root, left, right):
        root.lc, root.rc = left.lc, right.rc
        root.size = left.size + right.size

        root.mx = max(left.mx, right.mx)
        root.lmx, root.rmx = left.lmx, right.rmx

        if left.rc == right.lc:
            if left.lmx == left.size:
                root.lmx += right.lmx
            if right.rmx == right.size:
                root.rmx += left.rmx
            root.mx = max(root.mx, left.rmx + right.lmx)

    def pushup(self, u):
        self._pushup(tr[u], tr[u << 1], tr[u << 1 | 1])


class Solution:
    def longestRepeating(
        self, s: str, queryCharacters: str, queryIndices: List[int]
    ) -> List[int]:
        tree = SegmentTree(s)
        k = len(queryIndices)
        ans = []
        for i, c in enumerate(queryCharacters):
            x = queryIndices[i] + 1
            tree.modify(1, x, c)
            ans.append(tree.query(1, 1, len(s)).mx)
        return ans
```

### **Java**

```java
class Node {
    int l;
    int r;
    int size;
    int lmx;
    int rmx;
    int mx;
    char lc;
    char rc;
}

class SegmentTree {
    private String s;
    private Node[] tr;

    public SegmentTree(String s) {
        int n = s.length();
        this.s = s;
        tr = new Node[n << 2];
        for (int i = 0; i < tr.length; ++i) {
            tr[i] = new Node();
        }
        build(1, 1, n);
    }

    public void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l == r) {
            tr[u].lmx = 1;
            tr[u].rmx = 1;
            tr[u].mx = 1;
            tr[u].size = 1;
            tr[u].lc = s.charAt(l - 1);
            tr[u].rc = s.charAt(l - 1);
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    void modify(int u, int x, char v) {
        if (tr[u].l == x && tr[u].r == x) {
            tr[u].lc = v;
            tr[u].rc = v;
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

    Node query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u];
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (r <= mid) {
            return query(u << 1, l, r);
        }
        if (l > mid) {
            return query(u << 1 | 1, l, r);
        }
        Node ans = new Node();
        Node left = query(u << 1, l, r);
        Node right = query(u << 1 | 1, l, r);
        pushup(ans, left, right);
        return ans;
    }

    void pushup(Node root, Node left, Node right) {
        root.lc = left.lc;
        root.rc = right.rc;
        root.size = left.size + right.size;

        root.mx = Math.max(left.mx, right.mx);
        root.lmx = left.lmx;
        root.rmx = right.rmx;

        if (left.rc == right.lc) {
            if (left.lmx == left.size) {
                root.lmx += right.lmx;
            }
            if (right.rmx == right.size) {
                root.rmx += left.rmx;
            }
            root.mx = Math.max(root.mx, left.rmx + right.lmx);
        }
    }

    void pushup(int u) {
        pushup(tr[u], tr[u << 1], tr[u << 1 | 1]);
    }
}

class Solution {
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        SegmentTree tree = new SegmentTree(s);
        int k = queryCharacters.length();
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            int x = queryIndices[i] + 1;
            char c = queryCharacters.charAt(i);
            tree.modify(1, x, c);
            ans[i] = tree.query(1, 1, s.length()).mx;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Node {
public:
    int l, r, size, lmx, rmx, mx;
    char lc, rc;
};

class SegmentTree {
private:
    string s;
    vector<Node*> tr;

public:
    SegmentTree(string& s) {
        this->s = s;
        int n = s.size();
        tr.resize(n << 2);
        for (int i = 0; i < tr.size(); ++i) tr[i] = new Node();
        build(1, 1, n);
    }

    void build(int u, int l, int r) {
        tr[u]->l = l;
        tr[u]->r = r;
        if (l == r) {
            tr[u]->lmx = tr[u]->rmx = tr[u]->mx = tr[u]->size = 1;
            tr[u]->lc = tr[u]->rc = s[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    void modify(int u, int x, char v) {
        if (tr[u]->l == x && tr[u]->r == x) {
            tr[u]->lc = tr[u]->rc = v;
            return;
        }
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (x <= mid)
            modify(u << 1, x, v);
        else
            modify(u << 1 | 1, x, v);
        pushup(u);
    }

    Node* query(int u, int l, int r) {
        if (tr[u]->l >= l && tr[u]->r <= r) return tr[u];
        int mid = (tr[u]->l + tr[u]->r) >> 1;
        if (r <= mid) return query(u << 1, l, r);
        if (l > mid) query(u << 1 | 1, l, r);
        Node* ans = new Node();
        Node* left = query(u << 1, l, r);
        Node* right = query(u << 1 | 1, l, r);
        pushup(ans, left, right);
        return ans;
    }

    void pushup(Node* root, Node* left, Node* right) {
        root->lc = left->lc;
        root->rc = right->rc;
        root->size = left->size + right->size;

        root->mx = max(left->mx, right->mx);
        root->lmx = left->lmx;
        root->rmx = right->rmx;

        if (left->rc == right->lc) {
            if (left->lmx == left->size) root->lmx += right->lmx;
            if (right->rmx == right->size) root->rmx += left->rmx;
            root->mx = max(root->mx, left->rmx + right->lmx);
        }
    }

    void pushup(int u) {
        pushup(tr[u], tr[u << 1], tr[u << 1 | 1]);
    }
};

class Solution {
public:
    vector<int> longestRepeating(string s, string queryCharacters, vector<int>& queryIndices) {
        SegmentTree* tree = new SegmentTree(s);
        int k = queryCharacters.size();
        vector<int> ans(k);
        for (int i = 0; i < k; ++i) {
            int x = queryIndices[i] + 1;
            tree->modify(1, x, queryCharacters[i]);
            ans[i] = tree->query(1, 1, s.size())->mx;
        }
        return ans;
    }
};
```

### **Go**

```go
type segmentTree struct {
	str []byte
	mx  []int
	lmx []int
	rmx []int
}

func newSegmentTree(s string) *segmentTree {
	n := len(s)
	t := &segmentTree{
		str: []byte(s),
		mx:  make([]int, n<<2),
		lmx: make([]int, n<<2),
		rmx: make([]int, n<<2),
	}
	t.build(0, 0, n-1)
	return t
}

func (t *segmentTree) build(x, l, r int) {
	if l == r {
		t.lmx[x] = 1
		t.rmx[x] = 1
		t.mx[x] = 1
		return
	}
	m := int(uint(l+r) >> 1)
	t.build(x*2+1, l, m)
	t.build(x*2+2, m+1, r)
	t.pushup(x, l, m, r)
}

func (t *segmentTree) pushup(x, l, m, r int) {
	lch, rch := x*2+1, x*2+2
	t.lmx[x] = t.lmx[lch]
	t.rmx[x] = t.rmx[rch]
	t.mx[x] = max(t.mx[lch], t.mx[rch])
	// can be merged
	if t.str[m] == t.str[m+1] {
		if t.lmx[lch] == m-l+1 {
			t.lmx[x] += t.lmx[rch]
		}
		if t.rmx[rch] == r-m {
			t.rmx[x] += t.rmx[lch]
		}
		t.mx[x] = max(t.mx[x], t.rmx[lch]+t.lmx[rch])
	}
}

func (t *segmentTree) update(x, l, r, pos int, val byte) {
	if l == r {
		t.str[pos] = val
		return
	}
	m := int(uint(l+r) >> 1)
	if pos <= m {
		t.update(x*2+1, l, m, pos, val)
	} else {
		t.update(x*2+2, m+1, r, pos, val)
	}
	t.pushup(x, l, m, r)
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}

func longestRepeating(s string, queryCharacters string, queryIndices []int) []int {
	ans := make([]int, len(queryCharacters))
	t := newSegmentTree(s)
	n := len(s)
	for i, c := range queryCharacters {
		t.update(0, 0, n-1, queryIndices[i], byte(c))
		ans[i] = t.mx[0]
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
