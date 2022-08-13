# [715. Range 模块](https://leetcode.cn/problems/range-module)

[English Version](/solution/0700-0799/0715.Range%20Module/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Range模块是跟踪数字范围的模块。设计一个数据结构来跟踪表示为 <strong>半开区间</strong> 的范围并查询它们。</p>

<p><strong>半开区间</strong>&nbsp;<code>[left, right)</code>&nbsp;表示所有&nbsp;<code>left &lt;= x &lt; right</code>&nbsp;的实数 <code>x</code> 。</p>

<p>实现 <code>RangeModule</code> 类:</p>

<ul>
	<li><code>RangeModule()</code>&nbsp;初始化数据结构的对象。</li>
	<li><code>void addRange(int left, int right)</code> 添加 <strong>半开区间</strong>&nbsp;<code>[left, right)</code>，跟踪该区间中的每个实数。添加与当前跟踪的数字部分重叠的区间时，应当添加在区间&nbsp;<code>[left, right)</code>&nbsp;中尚未跟踪的任何数字到该区间中。</li>
	<li><code>boolean queryRange(int left, int right)</code>&nbsp;只有在当前正在跟踪区间&nbsp;<code>[left, right)</code>&nbsp;中的每一个实数时，才返回 <code>true</code>&nbsp;，否则返回 <code>false</code> 。</li>
	<li><code>void removeRange(int left, int right)</code>&nbsp;停止跟踪 <strong>半开区间</strong>&nbsp;<code>[left, right)</code>&nbsp;中当前正在跟踪的每个实数。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入</strong>
["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
[[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
<strong>输出</strong>
[null, null, null, true, false, true]

<strong>解释</strong>
RangeModule rangeModule = new RangeModule();
rangeModule.addRange(10, 20);
rangeModule.removeRange(14, 16);
rangeModule.queryRange(10, 14); 返回 true （区间 [10, 14) 中的每个数都正在被跟踪）
rangeModule.queryRange(13, 15); 返回 false（未跟踪区间 [13, 15) 中像 14, 14.03, 14.17 这样的数字）
rangeModule.queryRange(16, 17); 返回 true （尽管执行了删除操作，区间 [16, 17) 中的数字 16 仍然会被跟踪）
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= left &lt; right &lt;= 10<sup>9</sup></code></li>
	<li>在单个测试用例中，对&nbsp;<code>addRange</code>&nbsp;、&nbsp; <code>queryRange</code>&nbsp;和 <code>removeRange</code> 的调用总数不超过&nbsp;<code>10<sup>4</sup></code>&nbsp;次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：线段树**

线段树将整个区间分割为多个不连续的子区间，子区间的数量不超过 $log(width)$。更新某个元素的值，只需要更新 $log(width)$ 个区间，并且这些区间都包含在一个包含该元素的大区间内。区间修改时，需要使用**懒标记**保证效率。

-   线段树的每个节点代表一个区间；
-   线段树具有唯一的根节点，代表的区间是整个统计范围，如 $[1,N]$；
-   线段树的每个叶子节点代表一个长度为 $1$ 的元区间 $[x,x]$；
-   对于每个内部节点 $[l,r]$，它的左儿子是 $[l,mid]$，右儿子是 $[mid+1,r]$, 其中 $mid=⌊(l+r)/2⌋$ (即向下取整)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Node:
    def __init__(self):
        self.left = None
        self.right = None
        self.add = 0
        self.v = False


class SegmentTree:
    def __init__(self):
        self.root = Node()

    def modify(self, left, right, v, l=1, r=int(1e9), node=None):
        if node is None:
            node = self.root
        if l >= left and r <= right:
            if v == 1:
                node.add = 1
                node.v = True
            else:
                node.add = -1
                node.v = False
            return
        self.pushdown(node)
        mid = (l + r) >> 1
        if left <= mid:
            self.modify(left, right, v, l, mid, node.left)
        if right > mid:
            self.modify(left, right, v, mid + 1, r, node.right)
        self.pushup(node)

    def query(self, left, right, l=1, r=int(1e9), node=None):
        if node is None:
            node = self.root
        if l >= left and r <= right:
            return node.v
        self.pushdown(node)
        mid = (l + r) >> 1
        v = True
        if left <= mid:
            v = v and self.query(left, right, l, mid, node.left)
        if right > mid:
            v = v and self.query(left, right, mid + 1, r, node.right)
        return v

    def pushup(self, node):
        node.v = bool(node.left and node.left.v and node.right and node.right.v)

    def pushdown(self, node):
        if node.left is None:
            node.left = Node()
        if node.right is None:
            node.right = Node()
        if node.add:
            node.left.add = node.right.add = node.add
            node.left.v = node.add == 1
            node.right.v = node.add == 1
            node.add = 0


class RangeModule:
    def __init__(self):
        self.tree = SegmentTree()

    def addRange(self, left: int, right: int) -> None:
        self.tree.modify(left, right - 1, 1)

    def queryRange(self, left: int, right: int) -> bool:
        return self.tree.query(left, right - 1)

    def removeRange(self, left: int, right: int) -> None:
        self.tree.modify(left, right - 1, -1)


# Your RangeModule object will be instantiated and called as such:
# obj = RangeModule()
# obj.addRange(left,right)
# param_2 = obj.queryRange(left,right)
# obj.removeRange(left,right)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Node {
    Node left;
    Node right;
    int add;
    boolean v;
}

class SegmentTree {
    private Node root = new Node();

    public SegmentTree() {

    }

    public void modify(int left, int right, int v) {
        modify(left, right, v, 1, (int) 1e9, root);
    }

    public void modify(int left, int right, int v, int l, int r, Node node) {
        if (l >= left && r <= right) {
            node.v = v == 1;
            node.add = v;
            return;
        }
        pushdown(node);
        int mid = (l + r) >> 1;
        if (left <= mid) {
            modify(left, right, v, l, mid, node.left);
        }
        if (right > mid) {
            modify(left, right, v, mid + 1, r, node.right);
        }
        pushup(node);
    }

    public boolean query(int left, int right) {
        return query(left, right, 1, (int) 1e9, root);
    }

    public boolean query(int left, int right, int l, int r, Node node) {
        if (l >= left && r <= right) {
            return node.v;
        }
        pushdown(node);
        int mid = (l + r) >> 1;
        boolean v = true;
        if (left <= mid) {
            v = v && query(left, right, l, mid, node.left);
        }
        if (right > mid) {
            v = v && query(left, right, mid + 1, r, node.right);
        }
        return v;
    }

    public void pushup(Node node) {
        node.v = node.left != null && node.left.v && node.right != null && node.right.v;
    }

    public void pushdown(Node node) {
        if (node.left == null) {
            node.left = new Node();
        }
        if (node.right == null) {
            node.right = new Node();
        }
        if (node.add != 0) {
            node.left.add = node.add;
            node.right.add = node.add;
            node.left.v = node.add == 1;
            node.right.v = node.add == 1;
            node.add = 0;
        }
    }
}

class RangeModule {
    private SegmentTree tree = new SegmentTree();

    public RangeModule() {

    }

    public void addRange(int left, int right) {
        tree.modify(left, right - 1, 1);
    }

    public boolean queryRange(int left, int right) {
        return tree.query(left, right - 1);
    }

    public void removeRange(int left, int right) {
        tree.modify(left, right - 1, -1);
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
```

### **C++**

```cpp
template <class T>
class CachedObj {
public:
    void* operator new(size_t s) {
        if (!head) {
            T* a = new T[SIZE];
            for (size_t i = 0; i < SIZE; ++i)
                add(a + i);
        }
        T* p = head;
        head = head->CachedObj<T>::next;
        return p;
    }
    void operator delete(void* p, size_t) {
        if (p) add(static_cast<T*>(p));
    }
    virtual ~CachedObj() { }

protected:
    T* next;

private:
    static T* head;
    static const size_t SIZE;
    static void add(T* p) {
        p->CachedObj<T>::next = head;
        head = p;
    }
};
template <class T>
T* CachedObj<T>::head = 0;
template <class T>
const size_t CachedObj<T>::SIZE = 10000;
class Node : public CachedObj<Node> {
public:
    Node* left;
    Node* right;
    int add;
    bool v;
};

class SegmentTree {
private:
    Node* root;

public:
    SegmentTree() {
        root = new Node();
    }

    void modify(int left, int right, int v) {
        modify(left, right, v, 1, 1e9, root);
    }

    void modify(int left, int right, int v, int l, int r, Node* node) {
        if (l >= left && r <= right) {
            node->v = v == 1;
            node->add = v;
            return;
        }
        pushdown(node);
        int mid = (l + r) >> 1;
        if (left <= mid) modify(left, right, v, l, mid, node->left);
        if (right > mid) modify(left, right, v, mid + 1, r, node->right);
        pushup(node);
    }

    bool query(int left, int right) {
        return query(left, right, 1, 1e9, root);
    }

    bool query(int left, int right, int l, int r, Node* node) {
        if (l >= left && r <= right) return node->v;
        pushdown(node);
        int mid = (l + r) >> 1;
        bool v = true;
        if (left <= mid) v = v && query(left, right, l, mid, node->left);
        if (right > mid) v = v && query(left, right, mid + 1, r, node->right);
        return v;
    }

    void pushup(Node* node) {
        node->v = node->left && node->left->v && node->right && node->right->v;
    }

    void pushdown(Node* node) {
        if (!node->left) node->left = new Node();
        if (!node->right) node->right = new Node();
        if (node->add) {
            node->left->add = node->right->add = node->add;
            node->left->v = node->right->v = node->add == 1;
            node->add = 0;
        }
    }
};

class RangeModule {
public:
    SegmentTree* tree;

    RangeModule() {
        tree = new SegmentTree();
    }

    void addRange(int left, int right) {
        tree->modify(left, right - 1, 1);
    }

    bool queryRange(int left, int right) {
        return tree->query(left, right - 1);
    }

    void removeRange(int left, int right) {
        tree->modify(left, right - 1, -1);
    }
};

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule* obj = new RangeModule();
 * obj->addRange(left,right);
 * bool param_2 = obj->queryRange(left,right);
 * obj->removeRange(left,right);
 */
```

### **Go**

```go
const N int = 1e9

type node struct {
	lch   *node
	rch   *node
	added bool
	lazy  int
}

type segmentTree struct {
	root *node
}

func newSegmentTree() *segmentTree {
	return &segmentTree{
		root: new(node),
	}
}

func (t *segmentTree) update(n *node, l, r, i, j, x int) {
	if l >= i && r <= j {
		n.added = x == 1
		n.lazy = x
		return
	}
	t.pushdown(n)
	m := int(uint(l+r) >> 1)
	if i <= m {
		t.update(n.lch, l, m, i, j, x)
	}
	if j > m {
		t.update(n.rch, m+1, r, i, j, x)
	}
	t.pushup(n)
}

func (t *segmentTree) query(n *node, l, r, i, j int) bool {
	if l >= i && r <= j {
		return n.added
	}
	t.pushdown(n)
	v := true
	m := int(uint(l+r) >> 1)
	if i <= m {
		v = v && t.query(n.lch, l, m, i, j)
	}
	if j > m {
		v = v && t.query(n.rch, m+1, r, i, j)
	}
	return v
}

func (t *segmentTree) pushup(n *node) {
	n.added = n.lch.added && n.rch.added
}

func (t *segmentTree) pushdown(n *node) {
	if n.lch == nil {
		n.lch = new(node)
	}
	if n.rch == nil {
		n.rch = new(node)
	}
	if n.lazy != 0 {
		n.lch.added = n.lazy == 1
		n.rch.added = n.lazy == 1
		n.lch.lazy = n.lazy
		n.rch.lazy = n.lazy
		n.lazy = 0
	}
}

type RangeModule struct {
	t *segmentTree
}

func Constructor() RangeModule {
	return RangeModule{
		t: newSegmentTree(),
	}
}

func (this *RangeModule) AddRange(left int, right int) {
	this.t.update(this.t.root, 1, N, left, right-1, 1)
}

func (this *RangeModule) QueryRange(left int, right int) bool {
	return this.t.query(this.t.root, 1, N, left, right-1)
}

func (this *RangeModule) RemoveRange(left int, right int) {
	this.t.update(this.t.root, 1, N, left, right-1, -1)
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AddRange(left,right);
 * param_2 := obj.QueryRange(left,right);
 * obj.RemoveRange(left,right);
 */
```

### **...**

```

```

<!-- tabs:end -->
