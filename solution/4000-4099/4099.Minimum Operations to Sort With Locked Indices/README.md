---
comments: true
difficulty: 中等
rating: 1828
source: 第 526 场周赛 Q3
---

<!-- problem:start -->

# [4099. 将锁定数组排序的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-sort-with-locked-indices)

[English Version](/solution/4000-4099/4099.Minimum%20Operations%20to%20Sort%20With%20Locked%20Indices/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code>，其中 <code>nums</code> 是范围 <code>[1, n]</code> 内整数的一个&nbsp;<strong><span data-keyword="permutation-array">排列</span></strong>。</p>

<p>同时给你一个长度为 <code>n</code> 的&nbsp;<strong><span data-keyword="binary-array">二进制</span>&nbsp;</strong>数组 <code>locked</code> 和一个整数 <code>k</code>。</p>

<ul>
	<li><code>locked[i] = 1</code> 表示下标 <code>i</code> 已锁定。</li>
	<li><code>locked[i] = 0</code> 表示下标 <code>i</code> 已解锁。</li>
</ul>

<p>你可以按&nbsp;<strong>任意顺序&nbsp;</strong>执行以下操作：</p>

<ul>
	<li><strong>交换：</strong>选择一个下标 <code>i</code>，需满足下标 <code>i</code> 和 <code>i + 1</code> 当前都处于解锁状态，然后交换 <code>nums[i]</code> 和 <code>nums[i + 1]</code>。</li>
	<li><strong>解锁：</strong>选择一个下标 <code>i</code>，满足 <code>locked[i] = 1</code>，并通过将 <code>locked[i] = 0</code> 来&nbsp;<strong>永久&nbsp;</strong>解锁它。</li>
</ul>

<p>你可以执行任意次数的交换操作，但&nbsp;<strong>最多&nbsp;</strong>只能执行 <code>k</code> 次解锁操作。</p>

<p>返回将 <code>nums</code>&nbsp;排序为&nbsp;<strong><span data-keyword="strictly-increasing-array">严格递增</span>&nbsp;</strong>顺序所需的&nbsp;<strong>最小总操作次数&nbsp;</strong>。如果不可能，则返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,1,3,4], locked = [0,1,0,1], k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>解锁下标 1。数组 <code>locked</code> 变为 <code>[0,0,0,1]</code>。</li>
	<li>交换 <code>nums[0]</code> 和 <code>nums[1]</code>。数组 <code>nums</code> 变为 <code>[1,2,3,4]</code>。</li>
</ul>

<p>总操作次数为 <code>1 + 1 = 2</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,1,2], locked = [1,0,1], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>解锁下标 0 和 2。此时所有下标都已解锁。</li>
	<li>交换下标 0 和 1。数组 <code>nums</code> 变为 <code>[1,3,2]</code>。</li>
	<li>交换下标 1 和 2。数组 <code>nums</code> 变为 <code>[1,2,3]</code>。</li>
</ul>

<p>总操作次数为 <code>2 + 2 = 4</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [2,3,1], locked = [1,0,0], k = 0</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>下标 0 被锁定，因此它的值无法被移动。由于 <code>k = 0</code>，无法解锁任何下标。因此，无法对数组进行排序。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length == locked.length &lt;= 2000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= n</code></li>
	<li><code>0 &lt;= locked[i] &lt;= 1</code></li>
	<li><code>nums</code> 是范围 <code>[1, n]</code> 内整数的一个排列。</li>
	<li><code>0 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：树状数组 + 贪心

<!-- thinking:start -->

> **思考**
>
> 只允许交换相邻元素来将排列还原为递增顺序，最少交换次数始终等于排列的逆序对数；而要统计全部逆序对，如果双重循环枚举需要 $O(n^2)$，进阶要求提示我们应当做到 $O(n \log n)$。
>
> 解锁操作并不会减少所需的相邻交换次数，它只决定哪些下标可以参与相邻交换。由于 $\textit{nums}$ 是 $1 \sim n$ 的排列，每当某个前缀 $[0..i]$ 的最大值恰好等于 $i + 1$ 时，前缀与后缀中的元素便不再需要跨过分界点 $i$ 进行交换，数组由此被切分为若干个极小的独立区间 $[j..i]$。若区间长度为 $1$，唯一的元素已在目标位置上，无需参与交换；若区间长度大于 $1$，区间无法再细分，即对于任意 $p \in [j, i - 1]$，前缀 $[j..p]$ 与后缀 $[p + 1..i]$ 之间必定有元素需要跨过分界 $(p, p + 1)$，因此整个区间 $[j..i]$ 内的所有下标都必须处于解锁状态。
>
> 据此，我们先倒序遍历 $\textit{nums}$，用树状数组在 $O(n \log n)$ 时间内统计逆序对总数 $\textit{inv}$；随后正序遍历并维护前缀最大值 $mx$，每当 $mx = i + 1$ 就结算当前极小区间，若 $i > j$ 则将该区间内 $\textit{locked}$ 的和累加到所需解锁次数 $\textit{cnt}$ 中。

<!-- thinking:end -->

我们可以将总操作次数拆分为**最少交换次数**和**最少解锁次数**两部分分别计算：

1. **最少交换次数**：每次操作只能交换相邻的两个元素，将数组还原为严格递增顺序所需的最少相邻交换次数等于数组 $\textit{nums}$ 的**逆序对**数量 $\textit{inv}$。我们可以倒序遍历数组 $\textit{nums}$，利用树状数组统计每个元素右侧比它小的元素个数，从而在 $O(n \log n)$ 时间内求出 $\textit{inv}$。
2. **最少解锁次数**：由于 $\textit{nums}$ 是 $[1, n]$ 的一个排列，当我们从左到右遍历数组并维护前缀最大值 $mx$ 时，一旦在下标 $i$ 处满足 $mx = i + 1$，说明前缀 $\textit{nums}[0..i]$ 恰好包含了 $[1, i + 1]$ 中的所有整数。这意味着前缀 $[0..i]$ 与后缀 $[i + 1..n - 1]$ 之间没有任何元素需要跨界交换。因此，整个数组可以被划分为若干个极小的独立区间 $[j..i]$：
    - 若 $i = j$（区间长度为 $1$），则 $\textit{nums}[i] = i + 1$ 已经在正确的位置上，无需与任何元素交换，因此下标 $i$ 即使被锁定也无需解锁。
    - 若 $i > j$（区间长度大于 $1$），由于该区间是极小的，对于任意 $p \in [j, i - 1]$，前缀 $[0..p]$ 的最大值都严格大于 $p + 1$，必然有元素需要在 $p$ 和 $p + 1$ 之间发生交换。因此，区间 $[j..i]$ 内的所有下标都必须参与交换，其中所有满足 $\textit{locked}[p] = 1$ 的下标都必须被解锁。

遍历结束后，如果累计需要解锁的下标数量 $\textit{cnt} > k$，则无法完成排序，返回 $-1$；否则返回 $\textit{cnt} + \textit{inv}$。

时间复杂度 $O(n \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

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
    def minOperations(self, nums: list[int], locked: list[int], k: int) -> int:
        n = len(nums)
        bit = BinaryIndexedTree(n)
        inv = 0
        for x in reversed(nums):
            inv += bit.query(x - 1)
            bit.update(x, 1)

        cnt = mx = j = 0
        for i, x in enumerate(nums):
            mx = max(mx, x)
            if mx == i + 1:
                if i > j:
                    cnt += sum(locked[j : i + 1])
                j = i + 1

        return -1 if cnt > k else cnt + inv
```

#### Java

```java
class BinaryIndexedTree {
    private final int n;
    private final int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public int minOperations(int[] nums, int[] locked, int k) {
        int n = nums.length;
        BinaryIndexedTree bit = new BinaryIndexedTree(n);
        int inv = 0;
        for (int i = n - 1; i >= 0; --i) {
            inv += bit.query(nums[i] - 1);
            bit.update(nums[i], 1);
        }

        int cnt = 0, mx = 0, j = 0;
        for (int i = 0; i < n; ++i) {
            mx = Math.max(mx, nums[i]);
            if (mx == i + 1) {
                if (i > j) {
                    for (int p = j; p <= i; ++p) {
                        cnt += locked[p];
                    }
                }
                j = i + 1;
            }
        }

        return cnt > k ? -1 : cnt + inv;
    }
}
```

#### C++

```cpp
class BinaryIndexedTree {
    int n;
    vector<int> c;

public:
    BinaryIndexedTree(int n)
        : n(n)
        , c(n + 1) {}

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
};

class Solution {
public:
    int minOperations(vector<int>& nums, vector<int>& locked, int k) {
        int n = nums.size();
        BinaryIndexedTree bit(n);
        int inv = 0;
        for (int i = n - 1; i >= 0; --i) {
            inv += bit.query(nums[i] - 1);
            bit.update(nums[i], 1);
        }

        int cnt = 0, mx = 0, j = 0;
        for (int i = 0; i < n; ++i) {
            mx = max(mx, nums[i]);
            if (mx == i + 1) {
                if (i > j) {
                    for (int p = j; p <= i; ++p) {
                        cnt += locked[p];
                    }
                }
                j = i + 1;
            }
        }

        return cnt > k ? -1 : cnt + inv;
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
	return &BinaryIndexedTree{
		n: n,
		c: make([]int, n+1),
	}
}

func (bit *BinaryIndexedTree) update(x int, delta int) {
	for x <= bit.n {
		bit.c[x] += delta
		x += x & -x
	}
}

func (bit *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += bit.c[x]
		x -= x & -x
	}
	return s
}

func minOperations(nums []int, locked []int, k int) int {
	n := len(nums)
	bit := NewBinaryIndexedTree(n)
	inv := 0
	for i := n - 1; i >= 0; i-- {
		inv += bit.query(nums[i] - 1)
		bit.update(nums[i], 1)
	}

	cnt, mx, j := 0, 0, 0
	for i, x := range nums {
		mx = max(mx, x)
		if mx == i+1 {
			if i > j {
				for p := j; p <= i; p++ {
					cnt += locked[p]
				}
			}
			j = i + 1
		}
	}

	if cnt > k {
		return -1
	}
	return cnt + inv
}
```

#### TypeScript

```ts
class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = new Array(n + 1).fill(0);
    }

    update(x: number, delta: number): void {
        while (x <= this.n) {
            this.c[x] += delta;
            x += x & -x;
        }
    }

    query(x: number): number {
        let s = 0;
        while (x > 0) {
            s += this.c[x];
            x -= x & -x;
        }
        return s;
    }
}

function minOperations(nums: number[], locked: number[], k: number): number {
    const n = nums.length;
    const bit = new BinaryIndexedTree(n);
    let inv = 0;
    for (let i = n - 1; i >= 0; --i) {
        inv += bit.query(nums[i] - 1);
        bit.update(nums[i], 1);
    }

    let [cnt, mx, j] = [0, 0, 0];
    for (let i = 0; i < n; ++i) {
        mx = Math.max(mx, nums[i]);
        if (mx === i + 1) {
            if (i > j) {
                for (let p = j; p <= i; ++p) {
                    cnt += locked[p];
                }
            }
            j = i + 1;
        }
    }

    return cnt > k ? -1 : cnt + inv;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
