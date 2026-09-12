---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0370.Range%20Addition/README.md
tags:
    - 数组
    - 前缀和
---

<!-- problem:start -->

# [370. 区间加法 🔒](https://leetcode.cn/problems/range-addition)

[English Version](/solution/0300-0399/0370.Range%20Addition/README_EN.md)

## 题目描述

<!-- description:start -->

<p>假设你有一个长度为&nbsp;<em><strong>n</strong></em>&nbsp;的数组，初始情况下所有的数字均为&nbsp;<strong>0</strong>，你将会被给出&nbsp;<em><strong>k</strong></em>​​​​​​<em>​</em> 个更新的操作。</p>

<p>其中，每个操作会被表示为一个三元组：<strong>[startIndex, endIndex, inc]</strong>，你需要将子数组&nbsp;<strong>A[startIndex ... endIndex]</strong>（包括 startIndex 和 endIndex）增加&nbsp;<strong>inc</strong>。</p>

<p>请你返回&nbsp;<strong><em>k</em></strong>&nbsp;次操作后的数组。</p>

<p><strong>示例:</strong></p>

<pre>
<strong>输入: </strong>length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
<strong>输出: </strong>[-2,0,3,5,3]
</pre>

<p><strong>解释:</strong></p>

<pre>
初始状态:
[0,0,0,0,0]

进行了操作 [1,3,2] 后的状态:
[0,2,2,2,0]

进行了操作 [2,4,3] 后的状态:
[0,2,5,5,3]

进行了操作 [0,2,-2] 后的状态:
[-2,0,3,5,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= updates.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= startIdx<sub>i</sub> &lt;= endIdx<sub>i</sub> &lt; length</code></li>
	<li><code>-1000 &lt;= inc<sub>i</sub> &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：差分数组

<!-- thinking:start -->

> **思考**
>
> 多次区间加，最后输出数组。每次修改区间为 $O(n)$，更新次数多则偏慢。差分把区间加变成两点修改。
>
> $d[l]\mathrel{+}=c$、$d[r+1]\mathrel{-}=c$，最后前缀和还原。每个更新 $O(1)$，总线性。

<!-- thinking:end -->

差分数组模板题。

我们定义 $d$ 为差分数组。给区间 $[l,..r]$ 中的每一个数加上 $c$，那么有 $d[l] += c$，并且 $d[r+1] -= c$。最后我们对差分数组求前缀和，即可得到原数组。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getModifiedArray(self, length: int, updates: List[List[int]]) -> List[int]:
        d = [0] * length
        for l, r, c in updates:
            d[l] += c
            if r + 1 < length:
                d[r + 1] -= c
        return list(accumulate(d))
```

#### Java

```java
class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] d = new int[length];
        for (var e : updates) {
            int l = e[0], r = e[1], c = e[2];
            d[l] += c;
            if (r + 1 < length) {
                d[r + 1] -= c;
            }
        }
        for (int i = 1; i < length; ++i) {
            d[i] += d[i - 1];
        }
        return d;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> getModifiedArray(int length, vector<vector<int>>& updates) {
        vector<int> d(length);
        for (const auto& e : updates) {
            int l = e[0], r = e[1], c = e[2];
            d[l] += c;
            if (r + 1 < length) {
                d[r + 1] -= c;
            }
        }
        for (int i = 1; i < length; ++i) {
            d[i] += d[i - 1];
        }
        return d;
    }
};
```

#### Go

```go
func getModifiedArray(length int, updates [][]int) []int {
	d := make([]int, length)
	for _, e := range updates {
		l, r, c := e[0], e[1], e[2]
		d[l] += c
		if r+1 < length {
			d[r+1] -= c
		}
	}
	for i := 1; i < length; i++ {
		d[i] += d[i-1]
	}
	return d
}
```

#### TypeScript

```ts
function getModifiedArray(length: number, updates: number[][]): number[] {
    const d: number[] = Array(length).fill(0);
    for (const [l, r, c] of updates) {
        d[l] += c;
        if (r + 1 < length) {
            d[r + 1] -= c;
        }
    }
    for (let i = 1; i < length; ++i) {
        d[i] += d[i - 1];
    }
    return d;
}
```

#### JavaScript

```js
/**
 * @param {number} length
 * @param {number[][]} updates
 * @return {number[]}
 */
var getModifiedArray = function (length, updates) {
    const d = Array(length).fill(0);
    for (const [l, r, c] of updates) {
        d[l] += c;
        if (r + 1 < length) {
            d[r + 1] -= c;
        }
    }
    for (let i = 1; i < length; ++i) {
        d[i] += d[i - 1];
    }
    return d;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：树状数组 + 差分思想

<!-- thinking:start -->

> **思考**
>
> 差分数组已是线性。若改用树状数组，区间加仍写成两点更新，查询前缀即原值。渐近多一个 $\log n$，便于扩展为在线查询。

<!-- thinking:end -->

时间复杂度 $O(n\times \log n)$。

树状数组，也称作“二叉索引树”（Binary Indexed Tree）或 Fenwick 树。 它可以高效地实现如下两个操作：

1. **单点更新** `update(x, delta)`： 把序列 $x$ 位置的数加上一个值 $delta$；
1. **前缀和查询** `query(x)`：查询序列 $[1,...x]$ 区间的区间和，即位置 $x$ 的前缀和。

这两个操作的时间复杂度均为 $O(\log n)$。

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


class Solution:
    def getModifiedArray(self, length: int, updates: List[List[int]]) -> List[int]:
        tree = BinaryIndexedTree(length)
        for l, r, c in updates:
            tree.update(l + 1, c)
            tree.update(r + 2, -c)
        return [tree.query(i + 1) for i in range(length)]
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

class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        var tree = new BinaryIndexedTree(length);
        for (var e : updates) {
            int l = e[0], r = e[1], c = e[2];
            tree.update(l + 1, c);
            tree.update(r + 2, -c);
        }
        int[] ans = new int[length];
        for (int i = 0; i < length; ++i) {
            ans[i] = tree.query(i + 1);
        }
        return ans;
    }
}
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

class Solution {
public:
    vector<int> getModifiedArray(int length, vector<vector<int>>& updates) {
        BinaryIndexedTree* tree = new BinaryIndexedTree(length);
        for (const auto& e : updates) {
            int l = e[0], r = e[1], c = e[2];
            tree->update(l + 1, c);
            tree->update(r + 2, -c);
        }
        vector<int> ans;
        for (int i = 0; i < length; ++i) {
            ans.push_back(tree->query(i + 1));
        }
        return ans;
    }
};
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

func getModifiedArray(length int, updates [][]int) (ans []int) {
	bit := NewBinaryIndexedTree(length)
	for _, e := range updates {
		l, r, c := e[0], e[1], e[2]
		bit.update(l+1, c)
		bit.update(r+2, -c)
	}
	for i := 1; i <= length; i++ {
		ans = append(ans, bit.query(i))
	}
	return
}
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

function getModifiedArray(length: number, updates: number[][]): number[] {
    const bit = new BinaryIndexedTree(length);
    for (const [l, r, c] of updates) {
        bit.update(l + 1, c);
        bit.update(r + 2, -c);
    }
    return Array.from({ length }, (_, i) => bit.query(i + 1));
}
```

#### JavaScript

```js
/**
 * @param {number} length
 * @param {number[][]} updates
 * @return {number[]}
 */
var getModifiedArray = function (length, updates) {
    class BinaryIndexedTree {
        constructor(n) {
            this.n = n;
            this.c = Array(n + 1).fill(0);
        }

        update(x, delta) {
            while (x <= this.n) {
                this.c[x] += delta;
                x += x & -x;
            }
        }

        query(x) {
            let s = 0;
            while (x > 0) {
                s += this.c[x];
                x -= x & -x;
            }
            return s;
        }
    }

    const bit = new BinaryIndexedTree(length);
    for (const [l, r, c] of updates) {
        bit.update(l + 1, c);
        bit.update(r + 2, -c);
    }
    return Array.from({ length }, (_, i) => bit.query(i + 1));
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
