# [683. K Empty Slots](https://leetcode.com/problems/k-empty-slots)

[中文文档](/solution/0600-0699/0683.K%20Empty%20Slots/README.md)

## Description

<p>You have <code>n</code> bulbs in a row numbered from <code>1</code> to <code>n</code>. Initially, all the bulbs are turned off. We turn on <strong>exactly one</strong> bulb every day until all bulbs are on after <code>n</code> days.</p>

<p>You are given an array <code>bulbs</code>&nbsp;of length <code>n</code>&nbsp;where <code>bulbs[i] = x</code> means that on the <code>(i+1)<sup>th</sup></code> day, we will turn on the bulb at position <code>x</code>&nbsp;where&nbsp;<code>i</code>&nbsp;is&nbsp;<strong>0-indexed</strong>&nbsp;and&nbsp;<code>x</code>&nbsp;is&nbsp;<strong>1-indexed.</strong></p>

<p>Given an integer <code>k</code>, return&nbsp;<em>the <strong>minimum day number</strong> such that there exists two <strong>turned on</strong> bulbs that have <strong>exactly</strong>&nbsp;<code>k</code> bulbs between them that are <strong>all turned off</strong>. If there isn&#39;t such day, return <code>-1</code>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> bulbs = [1,3,2], k = 1
<strong>Output:</strong> 2
<b>Explanation:</b>
On the first day: bulbs[0] = 1, first bulb is turned on: [1,0,0]
On the second day: bulbs[1] = 3, third bulb is turned on: [1,0,1]
On the third day: bulbs[2] = 2, second bulb is turned on: [1,1,1]
We return 2 because on the second day, there were two on bulbs with one off bulb between them.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> bulbs = [1,2,3], k = 1
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == bulbs.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= bulbs[i] &lt;= n</code></li>
	<li><code>bulbs</code>&nbsp;is a permutation of numbers from&nbsp;<code>1</code>&nbsp;to&nbsp;<code>n</code>.</li>
	<li><code>0 &lt;= k &lt;= 2 * 10<sup>4</sup></code></li>
</ul>

## Solutions

Binary Indexed Tree.

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


class Solution:
    def kEmptySlots(self, bulbs: List[int], k: int) -> int:
        n = len(bulbs)
        tree = BinaryIndexedTree(n)
        for i, x in enumerate(bulbs, 1):
            tree.update(x, 1)
            case1 = (
                x - k - 1 > 0
                and tree.query(x - k - 1) - tree.query(x - k - 2) == 1
                and tree.query(x - 1) - tree.query(x - k - 1) == 0
            )
            case2 = (
                x + k + 1 <= n
                and tree.query(x + k + 1) - tree.query(x + k) == 1
                and tree.query(x + k) - tree.query(x) == 0
            )
            if case1 or case2:
                return i
        return -1
```

### **Java**

```java
class Solution {
    public int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        for (int i = 0; i < n; ++i) {
            int x = bulbs[i];
            tree.update(x, 1);
            boolean case1 = x - k - 1 > 0 && tree.query(x - k - 1) - tree.query(x - k - 2) == 1
                && tree.query(x - 1) - tree.query(x - k - 1) == 0;
            boolean case2 = x + k + 1 <= n && tree.query(x + k + 1) - tree.query(x + k) == 1
                && tree.query(x + k) - tree.query(x) == 0;
            if (case1 || case2) {
                return i + 1;
            }
        }
        return -1;
    }
}

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

class Solution {
public:
    int kEmptySlots(vector<int>& bulbs, int k) {
        int n = bulbs.size();
        BinaryIndexedTree* tree = new BinaryIndexedTree(n);
        for (int i = 0; i < n; ++i) {
            int x = bulbs[i];
            tree->update(x, 1);
            bool case1 = x - k - 1 > 0 && tree->query(x - k - 1) - tree->query(x - k - 2) == 1 && tree->query(x - 1) - tree->query(x - k - 1) == 0;
            bool case2 = x + k + 1 <= n && tree->query(x + k + 1) - tree->query(x + k) == 1 && tree->query(x + k) - tree->query(x) == 0;
            if (case1 || case2) return i + 1;
        }
        return -1;
    }
};
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

func kEmptySlots(bulbs []int, k int) int {
	n := len(bulbs)
	tree := newBinaryIndexedTree(n)
	for i, x := range bulbs {
		tree.update(x, 1)
		case1 := x-k-1 > 0 && tree.query(x-k-1)-tree.query(x-k-2) == 1 && tree.query(x-1)-tree.query(x-k-1) == 0
		case2 := x+k+1 <= n && tree.query(x+k+1)-tree.query(x+k) == 1 && tree.query(x+k)-tree.query(x) == 0
		if case1 || case2 {
			return i + 1
		}
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
