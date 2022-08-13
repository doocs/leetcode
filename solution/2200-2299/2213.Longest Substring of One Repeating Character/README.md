# [2213. 由单个字符重复的最长子字符串](https://leetcode.cn/problems/longest-substring-of-one-repeating-character)

[English Version](/solution/2200-2299/2213.Longest%20Substring%20of%20One%20Repeating%20Character/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：线段树**

线段树将整个区间分割为多个不连续的子区间，子区间的数量不超过 `log(width)`。更新某个元素的值，只需要更新 `log(width)` 个区间，并且这些区间都包含在一个包含该元素的大区间内。区间修改时，需要使用**懒标记**保证效率。

-   线段树的每个节点代表一个区间；
-   线段树具有唯一的根节点，代表的区间是整个统计范围，如 `[1, N]`；
-   线段树的每个叶子节点代表一个长度为 1 的元区间 `[x, x]`；
-   对于每个内部节点 `[l, r]`，它的左儿子是 `[l, mid]`，右儿子是 `[mid + 1, r]`, 其中 `mid = ⌊(l + r) / 2⌋` (即向下取整)。

对于本题，线段树节点维护的信息有：

1. 前缀最长连续字符个数 `lmx`；
1. 后缀最长连续字符个数 `rmx`；
1. 区间最长连续字符个数 `mx`；
1. 区间长度 `size`；
1. 区间首尾字符 `lc, rc`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
