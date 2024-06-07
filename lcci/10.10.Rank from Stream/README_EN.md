---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/10.10.Rank%20from%20Stream/README_EN.md
---

<!-- problem:start -->

# [10.10. Rank from Stream](https://leetcode.cn/problems/rank-from-stream-lcci)

[中文文档](/lcci/10.10.Rank%20from%20Stream/README.md)

## Description

<!-- description:start -->

<p>Imagine you are reading in a stream of integers. Periodically, you wish to be able to look up the rank of a number <code>x</code> (the number of values less than or equal to <code>x</code>). lmplement the data structures and algorithms to support these operations. That is, implement the method <code>track (int x)</code>, which is called when each number is generated, and the method <code>getRankOfNumber(int x)</code>, which returns the number of values less than or equal to <code>x</code>.</p>

<p><b>Note:&nbsp;</b>This problem is slightly different from the original one in the book.</p>

<p><strong>Example:</strong></p>

<pre>

<strong>Input:</strong>

[&quot;StreamRank&quot;, &quot;getRankOfNumber&quot;, &quot;track&quot;, &quot;getRankOfNumber&quot;]

[[], [1], [0], [0]]

<strong>Output:

</strong>[null,0,null,1]

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>x &lt;= 50000</code></li>
	<li>The number of calls of both&nbsp;<code>track</code>&nbsp;and&nbsp;<code>getRankOfNumber</code>&nbsp;methods are less than or equal to 2000.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Indexed Tree

We can use a Binary Indexed Tree (also known as a Fenwick Tree) to maintain the count of numbers that are less than or equal to the current number among the added numbers.

We create a Binary Indexed Tree with a length of $50010$. For the `track` method, we increment the current number and add it to the Binary Indexed Tree. For the `getRankOfNumber` method, we directly query the count of numbers that are less than or equal to $x + 1$ in the Binary Indexed Tree.

In terms of time complexity, both the update and query operations of the Binary Indexed Tree have a time complexity of $O(\log n)$, where $n$ is the length of the Binary Indexed Tree.

<!-- tabs:start -->

#### Python3

```python
class BinaryIndexedTree:
    __slots__ = "n", "c"

    def __init__(self, n: int):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, delta: int) -> None:
        while x <= self.n:
            self.c[x] += delta
            x += x & -x

    def query(self, x: int) -> int:
        s = 0
        while x:
            s += self.c[x]
            x -= x & -x
        return s


class StreamRank:

    def __init__(self):
        self.tree = BinaryIndexedTree(50010)

    def track(self, x: int) -> None:
        self.tree.update(x + 1, 1)

    def getRankOfNumber(self, x: int) -> int:
        return self.tree.query(x + 1)


# Your StreamRank object will be instantiated and called as such:
# obj = StreamRank()
# obj.track(x)
# param_2 = obj.getRankOfNumber(x)
```

#### Java

```java
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

class StreamRank {
    private BinaryIndexedTree tree = new BinaryIndexedTree(50010);

    public StreamRank() {

    }

    public void track(int x) {
        tree.update(x + 1, 1);
    }

    public int getRankOfNumber(int x) {
        return tree.query(x + 1);
    }
}

/**
 * Your StreamRank object will be instantiated and called as such:
 * StreamRank obj = new StreamRank();
 * obj.track(x);
 * int param_2 = obj.getRankOfNumber(x);
 */
```

#### C++

```cpp
class BinaryIndexedTree {
private:
    int n;
    vector<int> c;

public:
    BinaryIndexedTree(int n)
        : n(n)
        , c(n + 1) {}

    void update(int x, int delta) {
        for (; x <= n; x += x & -x) {
            c[x] += delta;
        }
    }

    int query(int x) {
        int s = 0;
        for (; x > 0; x -= x & -x) {
            s += c[x];
        }
        return s;
    }
};

class StreamRank {
public:
    StreamRank() {
    }

    void track(int x) {
        tree->update(x + 1, 1);
    }

    int getRankOfNumber(int x) {
        return tree->query(x + 1);
    }

private:
    BinaryIndexedTree* tree = new BinaryIndexedTree(50010);
};

/**
 * Your StreamRank object will be instantiated and called as such:
 * StreamRank* obj = new StreamRank();
 * obj->track(x);
 * int param_2 = obj->getRankOfNumber(x);
 */
```

#### Go

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) *BinaryIndexedTree {
	return &BinaryIndexedTree{n: n, c: make([]int, n+1)}
}

func (bit *BinaryIndexedTree) update(x, delta int) {
	for ; x <= bit.n; x += x & -x {
		bit.c[x] += delta
	}
}

func (bit *BinaryIndexedTree) query(x int) int {
	s := 0
	for ; x > 0; x -= x & -x {
		s += bit.c[x]
	}
	return s
}

type StreamRank struct {
	tree *BinaryIndexedTree
}

func Constructor() StreamRank {
	return StreamRank{NewBinaryIndexedTree(50010)}
}

func (this *StreamRank) Track(x int) {
	this.tree.update(x+1, 1)
}

func (this *StreamRank) GetRankOfNumber(x int) int {
	return this.tree.query(x + 1)
}

/**
 * Your StreamRank object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Track(x);
 * param_2 := obj.GetRankOfNumber(x);
 */
```

#### TypeScript

```ts
class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    update(x: number, delta: number): void {
        for (; x <= this.n; x += x & -x) {
            this.c[x] += delta;
        }
    }

    query(x: number): number {
        let s = 0;
        for (; x > 0; x -= x & -x) {
            s += this.c[x];
        }
        return s;
    }
}

class StreamRank {
    private tree: BinaryIndexedTree = new BinaryIndexedTree(50010);

    constructor() {}

    track(x: number): void {
        this.tree.update(x + 1, 1);
    }

    getRankOfNumber(x: number): number {
        return this.tree.query(x + 1);
    }
}

/**
 * Your StreamRank object will be instantiated and called as such:
 * var obj = new StreamRank()
 * obj.track(x)
 * var param_2 = obj.getRankOfNumber(x)
 */
```

#### Swift

```swift
class BinaryIndexedTree {
    private var n: Int
    private var c: [Int]

    init(_ n: Int) {
        self.n = n
        self.c = Array(repeating: 0, count: n + 1)
    }

    func update(_ x: Int, _ delta: Int) {
        var idx = x
        while idx <= n {
            c[idx] += delta
            idx += (idx & -idx)
        }
    }

    func query(_ x: Int) -> Int {
        var sum = 0
        var idx = x
        while idx > 0 {
            sum += c[idx]
            idx -= (idx & -idx)
        }
        return sum
    }
}

class StreamRank {
    private var tree: BinaryIndexedTree

    init() {
        tree = BinaryIndexedTree(50010)
    }

    func track(_ x: Int) {
        tree.update(x + 1, 1)
    }

    func getRankOfNumber(_ x: Int) -> Int {
        return tree.query(x + 1)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
