---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3109.Find%20the%20Index%20of%20Permutation/README.md
tags:
    - 树状数组
    - 线段树
    - 数组
    - 二分查找
    - 分治
    - 有序集合
    - 归并排序
---

<!-- problem:start -->

# [3109. 查找排列的下标 🔒](https://leetcode.cn/problems/find-the-index-of-permutation)

[English Version](/solution/3100-3199/3109.Find%20the%20Index%20of%20Permutation/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个长度为&nbsp;<code>n</code>&nbsp;的数组&nbsp;<code>perm</code>，它是&nbsp;<code>[1, 2, ..., n]</code>&nbsp;的一个排列，将&nbsp;<code>[1, 2, ..., n]</code>&nbsp;所有的排列放在数组中，并以&nbsp;<span data-keyword="lexicographically-sorted-array">字典序</span>&nbsp;排序，返回这个数组中&nbsp;<code>perm</code>&nbsp;的下标。</p>

<p>由于答案可能非常大，返回值对&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code>&nbsp;<strong>取模</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>perm = [1,2]</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><strong>解释：</strong></p>

<p>按以下顺序只有 2 种排列：</p>

<p><code>[1,2]</code>, <code>[2,1]</code><br />
<br />
并且&nbsp;<code>[1,2]</code>&nbsp;在下标 0。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>perm = [3,1,2]</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>按以下顺序只有 6 种排列：</p>

<p><code>[1,2,3]</code>, <code>[1,3,2]</code>, <code>[2,1,3]</code>, <code>[2,3,1]</code>, <code>[3,1,2]</code>, <code>[3,2,1]</code><br />
<br />
并且&nbsp;<code>[3,1,2]</code>&nbsp;在下标 4。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == perm.length &lt;= 10<sup>5</sup></code></li>
	<li><code>perm</code>&nbsp;是&nbsp;<code>[1, 2, ..., n]</code>&nbsp;的一个排列。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：树状数组

根据题目要求，我们需要找出一共有多少个排列的字典序小于给定的排列。

我们考虑如何计算字典序小于给定排列的排列个数，一共有两种情况：

- 排列的第一个元素小于 $perm[0]$，一共有 $(perm[0] - 1) \times (n-1)!$ 种排列。
- 排列的第一个元素等于 $perm[0]$，我们需要继续考虑第二个元素，以此类推。
- 累加所有情况即可。

我们可以用树状数组维护遍历过的元素中，比当前元素小的元素个数，那么对于给定排列的第 $i$ 个元素，剩余的比它小的元素个数为 $perm[i] - 1 - tree.query(perm[i])$，排列种类数为 $(perm[i] - 1 - tree.query(perm[i])) \times (n-i-1)!$，累加到答案中。然后我们更新树状数组，将当前元素加入树状数组。继续遍历下一个元素，直到遍历完所有元素。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为排列的长度。

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
