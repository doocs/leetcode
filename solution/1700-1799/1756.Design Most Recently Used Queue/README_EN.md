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
<p><strong>Example 1:</strong></p>

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
class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    @staticmethod
    def lowbit(x):
        return x & -x

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] += delta
            x += BinaryIndexedTree.lowbit(x)

    def query(self, x):
        s = 0
        while x > 0:
            s += self.c[x]
            x -= BinaryIndexedTree.lowbit(x)
        return s


class MRUQueue:
    def __init__(self, n: int):
        self.data = list(range(n + 1))
        self.tree = BinaryIndexedTree(n + 2010)

    def fetch(self, k: int) -> int:
        left, right = 1, len(self.data)
        while left < right:
            mid = (left + right) >> 1
            if mid - self.tree.query(mid) >= k:
                right = mid
            else:
                left = mid + 1
        self.data.append(self.data[left])
        self.tree.update(left, 1)
        return self.data[left]


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
        c = new int[n + 1];
    }

    public void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    public static int lowbit(int x) {
        return x & -x;
    }
}

class MRUQueue {
    private int n;
    private int[] data;
    private BinaryIndexedTree tree;

    public MRUQueue(int n) {
        this.n = n;
        data = new int[n + 2010];
        for (int i = 1; i <= n; ++i) {
            data[i] = i;
        }
        tree = new BinaryIndexedTree(n + 2010);
    }

    public int fetch(int k) {
        int left = 1;
        int right = n++;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (mid - tree.query(mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        data[n] = data[left];
        tree.update(left, 1);
        return data[left];
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
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) { }

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    int lowbit(int x) {
        return x & -x;
    }
};

class MRUQueue {
public:
    int n;
    vector<int> data;
    BinaryIndexedTree* tree;

    MRUQueue(int n) {
        this->n = n;
        data.resize(n + 1);
        for (int i = 1; i <= n; ++i) data[i] = i;
        tree = new BinaryIndexedTree(n + 2010);
    }

    int fetch(int k) {
        int left = 1, right = data.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (mid - tree->query(mid) >= k)
                right = mid;
            else
                left = mid + 1;
        }
        data.push_back(data[left]);
        tree->update(left, 1);
        return data[left];
    }
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

func (this *BinaryIndexedTree) lowbit(x int) int {
	return x & -x
}

func (this *BinaryIndexedTree) update(x, delta int) {
	for x <= this.n {
		this.c[x] += delta
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= this.lowbit(x)
	}
	return s
}

type MRUQueue struct {
	data []int
	tree *BinaryIndexedTree
}

func Constructor(n int) MRUQueue {
	data := make([]int, n+1)
	for i := range data {
		data[i] = i
	}
	return MRUQueue{data, newBinaryIndexedTree(n + 2010)}
}

func (this *MRUQueue) Fetch(k int) int {
	left, right := 1, len(this.data)
	for left < right {
		mid := (left + right) >> 1
		if mid-this.tree.query(mid) >= k {
			right = mid
		} else {
			left = mid + 1
		}
	}
	this.data = append(this.data, this.data[left])
	this.tree.update(left, 1)
	return this.data[left]
}

/**
 * Your MRUQueue object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Fetch(k);
 */
```

### **...**

```

```

<!-- tabs:end -->
