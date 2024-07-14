---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3109.Find%20the%20Index%20of%20Permutation/README_EN.md
tags:
    - Binary Indexed Tree
    - Segment Tree
    - Array
    - Binary Search
    - Divide and Conquer
    - Ordered Set
    - Merge Sort
---

<!-- problem:start -->

# [3109. Find the Index of Permutation 🔒](https://leetcode.com/problems/find-the-index-of-permutation)

[中文文档](/solution/3100-3199/3109.Find%20the%20Index%20of%20Permutation/README.md)

## Description

<!-- description:start -->

<p>Given an array <code>perm</code> of length <code>n</code> which is a permutation of <code>[1, 2, ..., n]</code>, return the index of <code>perm</code> in the <span data-keyword="lexicographically-sorted-array">lexicographically sorted</span> array of all of the permutations of <code>[1, 2, ..., n]</code>.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup>&nbsp;+ 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">perm = [1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There are only two permutations in the following order:</p>

<p><code>[1,2]</code>, <code>[2,1]</code><br />
<br />
And <code>[1,2]</code> is at index 0.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">perm = [3,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>There are only six permutations in the following order:</p>

<p><code>[1,2,3]</code>, <code>[1,3,2]</code>, <code>[2,1,3]</code>, <code>[2,3,1]</code>, <code>[3,1,2]</code>, <code>[3,2,1]</code><br />
<br />
And <code>[3,1,2]</code> is at index 4.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == perm.length &lt;= 10<sup>5</sup></code></li>
	<li><code>perm</code> is a permutation of <code>[1, 2, ..., n]</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Indexed Tree

According to the problem requirements, we need to find out how many permutations are lexicographically smaller than the given permutation.

We consider how to calculate the number of permutations that are lexicographically smaller than the given permutation. There are two situations:

-   The first element of the permutation is less than $perm[0]$, there are $(perm[0] - 1) \times (n-1)!$ permutations.
-   The first element of the permutation is equal to $perm[0]$, we need to continue to consider the second element, and so on.
-   The sum of all situations is the answer.

We can use a binary indexed tree to maintain the number of elements that are smaller than the current element in the traversed elements. For the $i$-th element of the given permutation, the number of remaining elements that are smaller than it is $perm[i] - 1 - tree.query(perm[i])$, and the number of permutation types is $(perm[i] - 1 - tree.query(perm[i])) \times (n-i-1)!$, which is added to the answer. Then we update the binary indexed tree and add the current element to the binary indexed tree. Continue to traverse the next element until all elements are traversed.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the length of the permutation.

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
    def getPermutationIndex(self, perm: List[int]) -> int:
        mod = 10**9 + 7
        ans, n = 0, len(perm)
        tree = BinaryIndexedTree(n + 1)
        f = [1] * n
        for i in range(1, n):
            f[i] = f[i - 1] * i % mod
        for i, x in enumerate(perm):
            cnt = x - 1 - tree.query(x)
            ans += cnt * f[n - i - 1] % mod
            tree.update(x, 1)
        return ans % mod
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
    public int getPermutationIndex(int[] perm) {
        final int mod = (int) 1e9 + 7;
        long ans = 0;
        int n = perm.length;
        BinaryIndexedTree tree = new BinaryIndexedTree(n + 1);
        long[] f = new long[n];
        f[0] = 1;
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] * i % mod;
        }
        for (int i = 0; i < n; ++i) {
            int cnt = perm[i] - 1 - tree.query(perm[i]);
            ans = (ans + cnt * f[n - i - 1] % mod) % mod;
            tree.update(perm[i], 1);
        }
        return (int) ans;
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
    int getPermutationIndex(vector<int>& perm) {
        const int mod = 1e9 + 7;
        using ll = long long;
        ll ans = 0;
        int n = perm.size();
        BinaryIndexedTree tree(n + 1);
        ll f[n];
        f[0] = 1;
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] * i % mod;
        }
        for (int i = 0; i < n; ++i) {
            int cnt = perm[i] - 1 - tree.query(perm[i]);
            ans += cnt * f[n - i - 1] % mod;
            tree.update(perm[i], 1);
        }
        return ans % mod;
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

func getPermutationIndex(perm []int) (ans int) {
	const mod int = 1e9 + 7
	n := len(perm)
	tree := NewBinaryIndexedTree(n + 1)
	f := make([]int, n)
	f[0] = 1
	for i := 1; i < n; i++ {
		f[i] = f[i-1] * i % mod
	}
	for i, x := range perm {
		cnt := x - 1 - tree.query(x)
		ans += cnt * f[n-1-i] % mod
		tree.update(x, 1)
	}
	return ans % mod
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

function getPermutationIndex(perm: number[]): number {
    const mod = 1e9 + 7;
    const n = perm.length;
    const tree = new BinaryIndexedTree(n + 1);
    let ans = 0;
    const f: number[] = Array(n).fill(1);
    for (let i = 1; i < n; ++i) {
        f[i] = (f[i - 1] * i) % mod;
    }
    for (let i = 0; i < n; ++i) {
        const cnt = perm[i] - 1 - tree.query(perm[i]);
        ans = (ans + cnt * f[n - i - 1]) % mod;
        tree.update(perm[i], 1);
    }
    return ans % mod;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
