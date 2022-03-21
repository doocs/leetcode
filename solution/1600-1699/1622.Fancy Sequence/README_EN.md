# [1622. Fancy Sequence](https://leetcode.com/problems/fancy-sequence)

[中文文档](/solution/1600-1699/1622.Fancy%20Sequence/README.md)

## Description

<p>Write an API that generates fancy sequences using the <code>append</code>, <code>addAll</code>, and <code>multAll</code> operations.</p>

<p>Implement the <code>Fancy</code> class:</p>

<ul>
	<li><code>Fancy()</code> Initializes the object with an empty sequence.</li>
	<li><code>void append(val)</code> Appends an integer <code>val</code> to the end of the sequence.</li>
	<li><code>void addAll(inc)</code> Increments all existing values in the sequence by an integer <code>inc</code>.</li>
	<li><code>void multAll(m)</code> Multiplies all existing values in the sequence by an integer <code>m</code>.</li>
	<li><code>int getIndex(idx)</code> Gets the current value at index <code>idx</code> (0-indexed) of the sequence <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>. If the index is greater or equal than the length of the sequence, return <code>-1</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Fancy&quot;, &quot;append&quot;, &quot;addAll&quot;, &quot;append&quot;, &quot;multAll&quot;, &quot;getIndex&quot;, &quot;addAll&quot;, &quot;append&quot;, &quot;multAll&quot;, &quot;getIndex&quot;, &quot;getIndex&quot;, &quot;getIndex&quot;]
[[], [2], [3], [7], [2], [0], [3], [10], [2], [0], [1], [2]]
<strong>Output</strong>
[null, null, null, null, null, 10, null, null, null, 26, 34, 20]

<strong>Explanation</strong>
Fancy fancy = new Fancy();
fancy.append(2);   // fancy sequence: [2]
fancy.addAll(3);   // fancy sequence: [2+3] -&gt; [5]
fancy.append(7);   // fancy sequence: [5, 7]
fancy.multAll(2);  // fancy sequence: [5*2, 7*2] -&gt; [10, 14]
fancy.getIndex(0); // return 10
fancy.addAll(3);   // fancy sequence: [10+3, 14+3] -&gt; [13, 17]
fancy.append(10);  // fancy sequence: [13, 17, 10]
fancy.multAll(2);  // fancy sequence: [13*2, 17*2, 10*2] -&gt; [26, 34, 20]
fancy.getIndex(0); // return 26
fancy.getIndex(1); // return 34
fancy.getIndex(2); // return 20
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= val, inc, m &lt;= 100</code></li>
	<li><code>0 &lt;= idx &lt;= 10<sup>5</sup></code></li>
	<li>At most <code>10<sup>5</sup></code> calls total will be made to <code>append</code>, <code>addAll</code>, <code>multAll</code>, and <code>getIndex</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

Segment Tree.

### **Python3**

```python

MOD = int(1e9 + 7)


class SegmentTree:
    def __init__(self, l, r):
        self.l = l
        self.r = r
        self.v = 0
        self.left = None
        self.right = None
        self.add = 0
        self.mul = 1

    @property
    def mid(self):
        return (self.l + self.r) >> 1

    @property
    def _left(self):
        if self.left is None:
            self.left = SegmentTree(self.l, self.mid)
        return self.left

    @property
    def _right(self):
        if self.right is None:
            self.right = SegmentTree(self.mid + 1, self.r)
        return self.right

    def pushup(self):
        self.v = self._left.v + self._right.v
        self.v %= MOD

    def modify_add(self, l, r, inc):
        if self.l >= l and self.r <= r:
            self.v = (self.v + (self.r - self.l + 1) * inc) % MOD
            self.add += inc
            return
        self.pushdown()
        if l <= self.mid:
            self._left.modify_add(l, r, inc)
        if r > self.mid:
            self._right.modify_add(l, r, inc)
        self.pushup()

    def modify_mul(self, l, r, m):
        if self.l >= l and self.r <= r:
            self.v = (self.v * m) % MOD
            self.add = (self.add * m) % MOD
            self.mul = (self.mul * m) % MOD
            return
        self.pushdown()
        if l <= self.mid:
            self._left.modify_mul(l, r, m)
        if r > self.mid:
            self._right.modify_mul(l, r, m)
        self.pushup()

    def query(self, l, r):
        if self.l >= l and self.r <= r:
            return self.v
        self.pushdown()
        v = 0
        if l <= self.mid:
            v += self._left.query(l, r)
        if r > self.mid:
            v += self._right.query(l, r)
        return v % MOD

    def pushdown(self):
        left, right = self._left, self._right
        if self.add or self.mul != 1:
            left.v = (left.v * self.mul +
                      (left.r - left.l + 1) * self.add) % MOD
            right.v = (right.v * self.mul +
                       (right.r - right.l + 1) * self.add) % MOD
            left.add = (left.add * self.mul + self.add) % MOD
            right.add = (right.add * self.mul + self.add) % MOD
            left.mul = (left.mul * self.mul) % MOD
            right.mul = (right.mul * self.mul) % MOD
            self.add = 0
            self.mul = 1


class Fancy:

    def __init__(self):
        self.n = 0
        self.tree = SegmentTree(0, 10**5 + 1)

    def append(self, val: int) -> None:
        self.n += 1
        self.tree.modify_add(self.n, self.n, val)

    def addAll(self, inc: int) -> None:
        self.tree.modify_add(1, self.n, inc)

    def multAll(self, m: int) -> None:
        self.tree.modify_mul(1, self.n, m)

    def getIndex(self, idx: int) -> int:
        return -1 if idx >= self.n else self.tree.query(idx + 1, idx + 1)


# Your Fancy object will be instantiated and called as such:
# obj = Fancy()
# obj.append(val)
# obj.addAll(inc)
# obj.multAll(m)
# param_4 = obj.getIndex(idx)
```

### **Java**

```java
class Fancy {
    private int n;
    private SegmentTree tree;

    public Fancy() {
        tree = new SegmentTree(1, (int) 1e5 + 1);
    }
    
    public void append(int val) {
        ++n;
        tree.modifyAdd(n, n, val);
    }
    
    public void addAll(int inc) {
        tree.modifyAdd(1, n, inc);
    }
    
    public void multAll(int m) {
        tree.modifyMul(1, n, m);
    }
    
    public int getIndex(int idx) {
        return idx >= n ? -1 : tree.query(idx + 1, idx + 1);
    }
}

class SegmentTree {
    private int l;
    private int r;
    private SegmentTree left;
    private SegmentTree right;
    private long v;
    private long add;
    private long mul = 1;
    private static final int MOD = (int) 1e9 + 7;

    public SegmentTree(int l, int r) {
        this.l = l;
        this.r = r;
    }

    public void pushup() {
        v = (left().v + right().v) % MOD;
    }

    public int mid() {
        return (l + r) >> 1;
    }

    public SegmentTree left() {
        if (left == null) {
            left = new SegmentTree(l, mid());
        }
        return left;
    }

    public SegmentTree right() {
        if (right == null) {
            right = new SegmentTree(mid() + 1, r);
        }
        return right;
    }

    public void modifyAdd(int l, int r, int inc) {
        if (l > r) {
            return;
        }
        if (this.l >= l && this.r <= r) {
            v = (v + (this.r - this.l + 1) * inc) % MOD;
            add = (add + inc) % MOD;
            return;
        }
        pushdown();
        int mid = mid();
        if (l <= mid) {
            left().modifyAdd(l, r, inc);
        }
        if (r > mid) {
            right().modifyAdd(l, r, inc);
        }
        pushup();
    }

    public void modifyMul(int l, int r, int m) {
        if (l > r) {
            return;
        }
        if (this.l >= l && this.r <= r) {
            v = (v * m) % MOD;
            add = (add * m) % MOD;
            mul = (mul * m) % MOD;
            return;
        }
        pushdown();
        int mid = mid();
        if (l <= mid) {
            left().modifyMul(l, r, m);
        }
        if (r > mid) {
            right().modifyMul(l, r, m);
        }
        pushup();
    }

    public int query(int l, int r) {
        if (l > r) {
            return 0;
        }
        if (this.l >= l && this.r <= r) {
            return (int) v;
        }
        pushdown();
        int mid = mid();
        long v = 0;
        if (l <= mid) {
            v += left().query(l, r);
        }
        if (r > mid) {
            v += right().query(l, r);
        }
        return (int) v % MOD;
    }

    public void pushdown() {
        SegmentTree left = left(), right = right();
        if (add != 0 || mul != 1) {
            left.v = (left.v * mul + (left.r - left.l + 1) * add) % MOD;
            right.v = (right.v * mul + (right.r - right.l + 1) * add) % MOD;
            left.add = (left.add * mul + add) % MOD;
            right.add = (right.add * mul + add) % MOD;
            left.mul = (left.mul * mul) % MOD;
            right.mul = (right.mul * mul) % MOD;
            add = 0;
            mul = 1;
        }
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */
```

### **C++**

```cpp
const int MOD = 1e9 + 7;

class SegmentTree {
private:
    int l;
    int r;
    SegmentTree* left;
    SegmentTree* right;
    long long v;
    long long add;
    long long mul;
    
public:
    SegmentTree(int l, int r) {
        this->l = l;
        this->r = r;
        this->left = nullptr;
        this->right = nullptr;
        this->v = 0;
        this->add = 0;
        this->mul = 1;
    }

    void modifyAdd(int l, int r, int inc) {
        if (l > r) return;
        if (this->l >= l && this->r <= r)
        {
            v = (v + (this->r - this->l + 1) * inc) % MOD;
            add = (add + inc) % MOD;
            return;
        }
        pushdown();
        int mid = _mid();
        if (l <= mid) _left()->modifyAdd(l, r, inc);
        if (r > mid) _right()->modifyAdd(l, r, inc);
        pushup();
    }

    void modifyMul(int l, int r, int m) {
        if (l > r) return;
        if (this->l >= l && this->r <= r)
        {
            v = (v * m) % MOD;
            add = (add * m) % MOD;
            mul = (mul * m) % MOD;
            return;
        }
        pushdown();
        int mid = _mid();
        if (l <= mid) _left()->modifyMul(l, r, m);
        if (r > mid) _right()->modifyMul(l, r, m);
        pushup();
    }

    int query(int l, int r) {
        if (l > r) return 0;
        if (this->l >= l && this->r <= r) return v;
        pushdown();
        int mid = _mid();
        int v = 0;
        if (l <= mid) v = (v +  _left()->query(l, r)) % MOD;
        if (r > mid) v = (v + _right()->query(l, r)) % MOD;
        return v;
    }

    int _mid() {
        return (l + r) >> 1;
    }

    SegmentTree* _left() {
        if (!left) left = new SegmentTree(l, _mid());
        return left;
    }

    SegmentTree* _right() {
        if (!right) right = new SegmentTree(_mid() + 1, r);
        return right;
    }

    void pushup() {
        v = (_left()->v + _right()->v) % MOD;
    }

    void pushdown() {
        if (add != 0 || mul != 1)
        {
            _left()->v = (_left()->v * mul + (_left()->r - _left()->l + 1) * add) % MOD;
            _right()->v = (_right()->v * mul + (_right()->r - _right()->l + 1) * add) % MOD;
            _left()->add = (_left()->add * mul + add) % MOD;
            _right()->add = (_right()->add * mul + add) % MOD;
            _left()->mul = (_left()->mul * mul) % MOD;
            _right()->mul = (_right()->mul * mul) % MOD;
            add = 0;
            mul = 1;
        }
    }
};

class Fancy {
public:
    int n;
    SegmentTree* tree;

    Fancy() {
        this-> n = 0;
        tree = new SegmentTree(1, 1e5 + 1);
    }
    
    void append(int val) {
        ++this->n;
        tree->modifyAdd(this->n, this->n, val);
    }
    
    void addAll(int inc) {
        tree->modifyAdd(1, this->n, inc);
    }
    
    void multAll(int m) {
        tree->modifyMul(1, this->n, m);
    }
    
    int getIndex(int idx) {
        return idx >= this-> n ? -1 : tree->query(idx + 1, idx + 1);
    }
};

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy* obj = new Fancy();
 * obj->append(val);
 * obj->addAll(inc);
 * obj->multAll(m);
 * int param_4 = obj->getIndex(idx);
 */
```

### **...**

```

```

<!-- tabs:end -->
