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

**Solution 1: Binary Indexed Tree**

We can use a Binary Indexed Tree to maintain the prefix sum of the bulbs. Every time we turn on a bulb, we update the corresponding position in the Binary Indexed Tree. Then we check if the $k$ bulbs to the left or right of the current bulb are all turned off and the $(k+1)$-th bulb is already turned on. If either of these conditions is met, we return the current day.

The time complexity is $O(n \times \log n)$ and the space complexity is $O(n)$, where $n$ is the number of bulbs.

<!-- tabs:start -->

### **Python3**

```python
class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] += delta
            x += x & -x

    def query(self, x):
        s = 0
        while x:
            s += self.c[x]
            x -= x & -x
        return s


class Solution:
    def kEmptySlots(self, bulbs: List[int], k: int) -> int:
        n = len(bulbs)
        tree = BinaryIndexedTree(n)
        vis = [False] * (n + 1)
        for i, x in enumerate(bulbs, 1):
            tree.update(x, 1)
            vis[x] = True
            y = x - k - 1
            if y > 0 and vis[y] and tree.query(x - 1) - tree.query(y) == 0:
                return i
            y = x + k + 1
            if y <= n and vis[y] and tree.query(y - 1) - tree.query(x) == 0:
                return i
        return -1
```

### **Java**

```java
class Solution {
    public int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        boolean[] vis = new boolean[n + 1];
        for (int i = 1; i <= n; ++i) {
            int x = bulbs[i - 1];
            tree.update(x, 1);
            vis[x] = true;
            int y = x - k - 1;
            if (y > 0 && vis[y] && tree.query(x - 1) - tree.query(y) == 0) {
                return i;
            }
            y = x + k + 1;
            if (y <= n && vis[y] && tree.query(y - 1) - tree.query(x) == 0) {
                return i;
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
        this.c = new int[n + 1];
    }

    public void update(int x, int delta) {
        for (; x <= n; x += x & -x) {
            c[x] += delta;
        }
    }

    public int query(int x) {
        int s = 0;
        for (; x > 0; x -= x & -x) {
            s += c[x];
        }
        return s;
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
        , c(_n + 1) {}

    void update(int x, int delta) {
        for (; x <= n; x += x & -x) {
            c[x] += delta;
        }
    }

    int query(int x) {
        int s = 0;
        for (; x; x -= x & -x) {
            s += c[x];
        }
        return s;
    }
};

class Solution {
public:
    int kEmptySlots(vector<int>& bulbs, int k) {
        int n = bulbs.size();
        BinaryIndexedTree* tree = new BinaryIndexedTree(n);
        bool vis[n + 1];
        memset(vis, false, sizeof(vis));
        for (int i = 1; i <= n; ++i) {
            int x = bulbs[i - 1];
            tree->update(x, 1);
            vis[x] = true;
            int y = x - k - 1;
            if (y > 0 && vis[y] && tree->query(x - 1) - tree->query(y) == 0) {
                return i;
            }
            y = x + k + 1;
            if (y <= n && vis[y] && tree->query(y - 1) - tree->query(x) == 0) {
                return i;
            }
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

func (this *BinaryIndexedTree) update(x, delta int) {
	for ; x <= this.n; x += x & -x {
		this.c[x] += delta
	}
}

func (this *BinaryIndexedTree) query(x int) (s int) {
	for ; x > 0; x -= x & -x {
		s += this.c[x]
	}
	return
}

func kEmptySlots(bulbs []int, k int) int {
	n := len(bulbs)
	tree := newBinaryIndexedTree(n)
	vis := make([]bool, n+1)
	for i, x := range bulbs {
		tree.update(x, 1)
		vis[x] = true
		i++
		y := x - k - 1
		if y > 0 && vis[y] && tree.query(x-1)-tree.query(y) == 0 {
			return i
		}
		y = x + k + 1
		if y <= n && vis[y] && tree.query(y-1)-tree.query(x) == 0 {
			return i
		}
	}
	return -1
}
```

### **TypeScript**

```ts
class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    public update(x: number, delta: number) {
        for (; x <= this.n; x += x & -x) {
            this.c[x] += delta;
        }
    }

    public query(x: number): number {
        let s = 0;
        for (; x > 0; x -= x & -x) {
            s += this.c[x];
        }
        return s;
    }
}

function kEmptySlots(bulbs: number[], k: number): number {
    const n = bulbs.length;
    const tree = new BinaryIndexedTree(n);
    const vis: boolean[] = Array(n + 1).fill(false);
    for (let i = 1; i <= n; ++i) {
        const x = bulbs[i - 1];
        tree.update(x, 1);
        vis[x] = true;
        let y = x - k - 1;
        if (y > 0 && vis[y] && tree.query(x - 1) - tree.query(y) === 0) {
            return i;
        }
        y = x + k + 1;
        if (y <= n && vis[y] && tree.query(y - 1) - tree.query(x) === 0) {
            return i;
        }
    }
    return -1;
}
```

### **...**

```

```

<!-- tabs:end -->
