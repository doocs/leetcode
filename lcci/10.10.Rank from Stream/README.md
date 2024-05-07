# [面试题 10.10. 数字流的秩](https://leetcode.cn/problems/rank-from-stream-lcci)

[English Version](/lcci/10.10.Rank%20from%20Stream/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>假设你正在读取一串整数。每隔一段时间，你希望能找出数字 x 的秩(小于或等于 x 的值的个数)。请实现数据结构和算法来支持这些操作，也就是说：</p>

<p>实现 <code>track(int x)</code>&nbsp;方法，每读入一个数字都会调用该方法；</p>

<p>实现 <code>getRankOfNumber(int x)</code> 方法，返回小于或等于 x 的值的个数。</p>

<p><strong>注意：</strong>本题相对原题稍作改动</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong>
[&quot;StreamRank&quot;, &quot;getRankOfNumber&quot;, &quot;track&quot;, &quot;getRankOfNumber&quot;]
[[], [1], [0], [0]]
<strong>输出:
</strong>[null,0,null,1]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>x &lt;= 50000</code></li>
	<li><code>track</code>&nbsp;和&nbsp;<code>getRankOfNumber</code> 方法的调用次数均不超过 2000 次</li>
</ul>

## 解法

### 方法一：树状数组

我们可以使用树状数组维护添加过的数字中，有多少个数字小于等于当前数字。

我们创建一个长度为 $50010$ 的树状数组，对于 `track` 方法，我们将当前数字加一，添加到树状数组中，对于 `getRankOfNumber` 方法，我们直接查询树状数组中小于等于 $x + 1$ 的数字个数。

时间复杂度方面，树状数组的更新和查询操作的时间复杂度均为 $O(\log n)$，其中 $n$ 为树状数组的长度。

<!-- tabs:start -->

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

<!-- end -->
