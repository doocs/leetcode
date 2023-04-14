# [1756. Design Most Recently Used Queue](https://leetcode.com/problems/design-most-recently-used-queue)

[中文文档](/solution/1700-1799/1756.Design%20Most%20Recently%20Used%20Queue/README.md)

## Description

<p>Design a queue-like data structure that moves the most recently used element to the end of the queue.</p>

<p>Implement the <code>MRUQueue</code> class:</p>

<ul>
	<li><code>MRUQueue(int n)</code> constructs the <code>MRUQueue</code> with <code>n</code> elements: <code>[1,2,3,...,n]</code>.</li>
	<li><code>int fetch(int k)</code> moves the <code>k<sup>th</sup></code> element <strong>(1-indexed)</strong> to the end of the queue and returns it.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong>
[&quot;MRUQueue&quot;, &quot;fetch&quot;, &quot;fetch&quot;, &quot;fetch&quot;, &quot;fetch&quot;]
[[8], [3], [5], [2], [8]]
<strong>Output:</strong>
[null, 3, 6, 2, 2]

<strong>Explanation:</strong>
MRUQueue mRUQueue = new MRUQueue(8); // Initializes the queue to [1,2,3,4,5,6,7,8].
mRUQueue.fetch(3); // Moves the 3<sup>rd</sup> element (3) to the end of the queue to become [1,2,4,5,6,7,8,3] and returns it.
mRUQueue.fetch(5); // Moves the 5<sup>th</sup> element (6) to the end of the queue to become [1,2,4,5,7,8,3,6] and returns it.
mRUQueue.fetch(2); // Moves the 2<sup>nd</sup> element (2) to the end of the queue to become [1,4,5,7,8,3,6,2] and returns it.
mRUQueue.fetch(8); // The 8<sup>th</sup> element (2) is already at the end of the queue so just return it.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2000</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
	<li>At most <code>2000</code> calls will be made to <code>fetch</code>.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Finding an <code>O(n)</code> algorithm per <code>fetch</code> is a bit easy. Can you find an algorithm with a better complexity for each <code>fetch</code> call?

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class MRUQueue:

    def __init__(self, n: int):
        self.q = list(range(1, n + 1))

    def fetch(self, k: int) -> int:
        ans = self.q[k - 1]
        self.q[k - 1: k] = []
        self.q.append(ans)
        return ans

# Your MRUQueue object will be instantiated and called as such:
# obj = MRUQueue(n)
# param_1 = obj.fetch(k)
```

```python
class BinaryIndexedTree:
    def __init__(self, n: int):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, v: int):
        while x <= self.n:
            self.c[x] += v
            x += x & -x

    def query(self, x: int) -> int:
        s = 0
        while x:
            s += self.c[x]
            x -= x & -x
        return s


class MRUQueue:

    def __init__(self, n: int):
        self.q = list(range(n + 1))
        self.tree = BinaryIndexedTree(n + 2010)

    def fetch(self, k: int) -> int:
        l, r = 1, len(self.q)
        while l < r:
            mid = (l + r) >> 1
            if mid - self.tree.query(mid) >= k:
                r = mid
            else:
                l = mid + 1
        x = self.q[l]
        self.q.append(x)
        self.tree.update(l, 1)
        return x

# Your MRUQueue object will be instantiated and called as such:
# obj = MRUQueue(n)
# param_1 = obj.fetch(k)
```

### **Java**

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] += v;
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

class MRUQueue {
    private int n;
    private int[] q;
    private BinaryIndexedTree tree;

    public MRUQueue(int n) {
        this.n = n;
        q = new int[n + 2010];
        for (int i = 1; i <= n; ++i) {
            q[i] = i;
        }
        tree = new BinaryIndexedTree(n + 2010);
    }

    public int fetch(int k) {
        int l = 1, r = n;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (mid - tree.query(mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        int x = q[l];
        q[++n] = x;
        tree.update(l, 1);
        return x;
    }
}

/**
 * Your MRUQueue object will be instantiated and called as such:
 * MRUQueue obj = new MRUQueue(n);
 * int param_1 = obj.fetch(k);
 */
```

### **C++**

```cpp
class BinaryIndexedTree {
public:
    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }

private:
    int n;
    vector<int> c;
};

class MRUQueue {
public:
    MRUQueue(int n) {
        q.resize(n + 1);
        iota(q.begin() + 1, q.end(), 1);
        tree = new BinaryIndexedTree(n + 2010);
    }

    int fetch(int k) {
        int l = 1, r = q.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (mid - tree->query(mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        int x = q[l];
        q.push_back(x);
        tree->update(l, 1);
        return x;
    }

private:
    vector<int> q;
    BinaryIndexedTree* tree;
};

/**
 * Your MRUQueue object will be instantiated and called as such:
 * MRUQueue* obj = new MRUQueue(n);
 * int param_1 = obj->fetch(k);
 */
```

### **Go**

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) update(x, delta int) {
	for x <= this.n {
		this.c[x] += delta
		x += x & -x
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= x & -x
	}
	return s
}

type MRUQueue struct {
	q    []int
	tree *BinaryIndexedTree
}

func Constructor(n int) MRUQueue {
	q := make([]int, n+1)
	for i := 1; i <= n; i++ {
		q[i] = i
	}
	return MRUQueue{q, newBinaryIndexedTree(n + 2010)}
}

func (this *MRUQueue) Fetch(k int) int {
	l, r := 1, len(this.q)
	for l < r {
		mid := (l + r) >> 1
		if mid-this.tree.query(mid) >= k {
			r = mid
		} else {
			l = mid + 1
		}
	}
	x := this.q[l]
	this.q = append(this.q, x)
	this.tree.update(l, 1)
	return x
}

/**
 * Your MRUQueue object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Fetch(k);
 */
```

### **TypeScript**

```ts
class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = new Array(n + 1).fill(0);
    }

    public update(x: number, v: number): void {
        while (x <= this.n) {
            this.c[x] += v;
            x += x & -x;
        }
    }

    public query(x: number): number {
        let s = 0;
        while (x > 0) {
            s += this.c[x];
            x -= x & -x;
        }
        return s;
    }
}

class MRUQueue {
    private q: number[];
    private tree: BinaryIndexedTree;

    constructor(n: number) {
        this.q = new Array(n + 1);
        for (let i = 1; i <= n; ++i) {
            this.q[i] = i;
        }
        this.tree = new BinaryIndexedTree(n + 2010);
    }

    fetch(k: number): number {
        let l = 1;
        let r = this.q.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (mid - this.tree.query(mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        const x = this.q[l];
        this.q.push(x);
        this.tree.update(l, 1);
        return x;
    }
}

/**
 * Your MRUQueue object will be instantiated and called as such:
 * var obj = new MRUQueue(n)
 * var param_1 = obj.fetch(k)
 */
```

### **...**

```

```

<!-- tabs:end -->
