# [1756. 设计最近使用（MRU）队列](https://leetcode.cn/problems/design-most-recently-used-queue)

[English Version](/solution/1700-1799/1756.Design%20Most%20Recently%20Used%20Queue/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一种类似队列的数据结构，该数据结构将最近使用的元素移到队列尾部。</p>

<p>实现 <code>MRUQueue</code> 类：</p>

<ul>
	<li><code>MRUQueue(int n)</code>  使用 <code>n</code> 个元素： <code>[1,2,3,...,n]</code> 构造 <code>MRUQueue</code> 。</li>
	<li><code>fetch(int k)</code> 将第 <code>k</code> 个元素<strong>（从 1 开始索引）</strong>移到队尾，并返回该元素。</li>
</ul>

<p> </p>

<p><b>示例 1：</b></p>

<pre>
<strong>输入：</strong>
["MRUQueue", "fetch", "fetch", "fetch", "fetch"]
[[8], [3], [5], [2], [8]]
<strong>输出：</strong>
[null, 3, 6, 2, 2]

<strong>解释：</strong>
MRUQueue mRUQueue = new MRUQueue(8); // 初始化队列为 [1,2,3,4,5,6,7,8]。
mRUQueue.fetch(3); // 将第 3 个元素 (3) 移到队尾，使队列变为 [1,2,4,5,6,7,8,3] 并返回该元素。
mRUQueue.fetch(5); // 将第 5 个元素 (6) 移到队尾，使队列变为 [1,2,4,5,7,8,3,6] 并返回该元素。
mRUQueue.fetch(2); // 将第 2 个元素 (2) 移到队尾，使队列变为 [1,4,5,7,8,3,6,2] 并返回该元素。
mRUQueue.fetch(8); // 第 8 个元素 (2) 已经在队列尾部了，所以直接返回该元素即可。
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 <= n <= 2000</code></li>
	<li><code>1 <= k <= n</code></li>
	<li>最多调用 <code>2000</code> 次 <code>fetch</code></li>
</ul>

<p> </p>
<b>进阶：</b>找到每次 <code>fetch</code> 的复杂度为 <code>O(n)</code> 的算法比较简单。你可以找到每次 <code>fetch</code> 的复杂度更佳的算法吗？

## 解法

<!-- 这里可写通用的实现逻辑 -->

树状数组维护前缀和，二分法查找第 k 个数。

树状数组，也称作“二叉索引树”（Binary Indexed Tree）或 Fenwick 树。 它可以高效地实现如下两个操作：

1. **单点更新** `update(x, delta)`： 把序列 x 位置的数加上一个值 delta；
1. **前缀和查询** `query(x)`：查询序列 `[1,...x]` 区间的区间和，即位置 x 的前缀和。

这两个操作的时间复杂度均为 `O(log n)`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
