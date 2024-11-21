---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0370.Range%20Addition/README_EN.md
tags:
    - Array
    - Prefix Sum
---

<!-- problem:start -->

# [370. Range Addition 🔒](https://leetcode.com/problems/range-addition)

[中文文档](/solution/0300-0399/0370.Range%20Addition/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>length</code> and an array <code>updates</code> where <code>updates[i] = [startIdx<sub>i</sub>, endIdx<sub>i</sub>, inc<sub>i</sub>]</code>.</p>

<p>You have an array <code>arr</code> of length <code>length</code> with all zeros, and you have some operation to apply on <code>arr</code>. In the <code>i<sup>th</sup></code> operation, you should increment all the elements <code>arr[startIdx<sub>i</sub>], arr[startIdx<sub>i</sub> + 1], ..., arr[endIdx<sub>i</sub>]</code> by <code>inc<sub>i</sub></code>.</p>

<p>Return <code>arr</code> <em>after applying all the</em> <code>updates</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0370.Range%20Addition/images/rangeadd-grid.jpg" style="width: 413px; height: 573px;" />
<pre>
<strong>Input:</strong> length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
<strong>Output:</strong> [-2,0,3,5,3]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> length = 10, updates = [[2,4,6],[5,6,8],[1,9,-4]]
<strong>Output:</strong> [0,-4,2,2,2,4,4,-4,-4,-4]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= updates.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= startIdx<sub>i</sub> &lt;= endIdx<sub>i</sub> &lt; length</code></li>
	<li><code>-1000 &lt;= inc<sub>i</sub> &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Difference Array

This is a template problem for difference arrays.

We define $d$ as the difference array. To add $c$ to each number in the interval $[l,..r]$, we set $d[l] += c$ and $d[r+1] -= c$. Finally, we compute the prefix sum of the difference array to obtain the original array.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array.

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

### Solution 2: Binary Indexed Tree + Difference Array

The time complexity is $O(n \times \log n)$.

A Binary Indexed Tree (BIT), also known as a Fenwick Tree, can efficiently perform the following two operations:

1. **Point Update** `update(x, delta)`: Add a value $delta$ to the number at position $x$ in the sequence.
2. **Prefix Sum Query** `query(x)`: Query the sum of the interval $[1, ... , x]$ in the sequence, i.e., the prefix sum up to position $x$.

The time complexity for both operations is $O(\log n)$.

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

l

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
