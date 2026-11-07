---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4099. Minimum Operations to Sort With Locked Indices](https://leetcode.com/problems/minimum-operations-to-sort-with-locked-indices)

[中文文档](/solution/4000-4099/4099.Minimum%20Operations%20to%20Sort%20With%20Locked%20Indices/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>, where <code>nums</code> is a <strong><span data-keyword="permutation-array">permutation</span></strong> of the integers in the range <code>[1, n]</code>.</p>

<p>You are also given a <span data-keyword="binary-array"><strong>binary</strong> array</span> <code>locked</code> of length <code>n</code> and an integer <code>k</code>.</p>

<ul>
	<li><code>locked[i] = 1</code> means index <code>i</code> is locked.</li>
	<li><code>locked[i] = 0</code> means index <code>i</code> is unlocked.</li>
</ul>

<p>You may perform the following operations in <strong>any order</strong>:</p>

<ul>
	<li><strong>Swap:</strong> Choose an index <code>i</code> such that both indices <code>i</code> and <code>i + 1</code> are currently unlocked, and swap <code>nums[i]</code> and <code>nums[i + 1]</code>.</li>
	<li><strong>Unlock:</strong> Choose an index <code>i</code> such that <code>locked[i] = 1</code> and <strong>permanently</strong> unlock it by setting <code>locked[i] = 0</code>.</li>
</ul>

<p>You may perform any number of swap operations but <strong>at most</strong> <code>k</code> unlock operations.</p>

<p>Return the <strong>minimum total number of operations</strong> required to sort <code>nums</code> in <strong><span data-keyword="strictly-increasing-array">strictly increasing</span></strong> order. If it is impossible, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,3,4], locked = [0,1,0,1], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Unlock index 1. The array <code>locked</code> becomes <code>[0,0,0,1]</code>.</li>
	<li>Swap <code>nums[0]</code> and <code>nums[1]</code>. The array <code>nums</code> becomes <code>[1,2,3,4]</code>.</li>
</ul>

<p>The total number of operations is <code>1 + 1 = 2</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,2], locked = [1,0,1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Unlock indices 0 and 2. All indices are now unlocked.</li>
	<li>Swap indices 0 and 1. The array <code>nums</code> becomes <code>[1,3,2]</code>.</li>
	<li>Swap indices 1 and 2. The array <code>nums</code> becomes <code>[1,2,3]</code>.</li>
</ul>

<p>The total number of operations is <code>2 + 2 = 4</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,3,1], locked = [1,0,0], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>Index 0 is locked, so its value cannot be moved. Since <code>k = 0</code>, no indices can be unlocked. Therefore, sorting the array is impossible.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length == locked.length &lt;= 2000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= n</code></li>
	<li><code>0 &lt;= locked[i] &lt;= 1</code></li>
	<li><code>nums</code> is a permutation of the integers in the range <code>[1, n]</code>.</li>
	<li><code>0 &lt;= k &lt;= n</code></li>
</ul>

<p>&nbsp;</p>
Can you solve this in <code>O(nlog(n))</code>?

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Indexed Tree + Greedy

<!-- thinking:start -->

> **Thinking**
>
> Sorting a permutation using only adjacent swaps always requires a minimum number of swaps equal to its inversion count; while a nested loop counts inversions in $O(n^2)$, the follow-up asks for $O(n \log n)$.
>
> Unlocking indices does not change the number of required swaps—it only determines which indices can participate in swaps. Because $\textit{nums}$ is a permutation of $1 \dots n$, whenever the maximum of a prefix $[0..i]$ equals $i + 1$, no element needs to cross index $i$, partitioning the array into minimal independent segments $[j..i]$. A segment of length $1$ already has its sole element in place and needs no swaps; a segment of length greater than $1$ cannot be split further, meaning every boundary $(p, p + 1)$ for $p \in [j, i - 1]$ must be crossed by some swap, so every index in $[j..i]$ must be unlocked.
>
> Thus, we first scan $\textit{nums}$ from right to left with a Binary Indexed Tree to count total inversions $\textit{inv}$ in $O(n \log n)$ time, and then scan from left to right tracking the prefix maximum $mx$: whenever $mx = i + 1$, we close the current minimal segment $[j..i]$ and, if $i > j$, add the sum of $\textit{locked}[j..i]$ to the required unlock count $\textit{cnt}$.

<!-- thinking:end -->

We can decompose the total number of operations into the **minimum number of swaps** and the **minimum number of unlocks**, and compute them separately:

1. **Minimum number of swaps**: Since each swap only exchanges two adjacent elements, the minimum number of adjacent swaps needed to sort $\textit{nums}$ in strictly increasing order equals the number of **inversions** $\textit{inv}$ in $\textit{nums}$. We can traverse $\textit{nums}$ from right to left and use a Binary Indexed Tree to count how many elements to the right of each element are smaller than it, finding $\textit{inv}$ in $O(n \log n)$ time.
2. **Minimum number of unlocks**: Since $\textit{nums}$ is a permutation of $[1, n]$, as we scan the array from left to right while maintaining the prefix maximum $mx$, whenever $mx = i + 1$ at index $i$, the prefix $\textit{nums}[0..i]$ contains all integers in $[1, i + 1]$. This means no element needs to be swapped across the boundary between $[0..i]$ and $[i + 1..n - 1]$. Consequently, the array can be partitioned into minimal independent segments $[j..i]$:
    - If $i = j$ (segment length is $1$), then $\textit{nums}[i] = i + 1$ is already in its final position and does not need to participate in any swap, so index $i$ does not need to be unlocked even if $\textit{locked}[i] = 1$.
    - If $i > j$ (segment length is greater than $1$), because the segment is minimal, for every $p \in [j, i - 1]$ the maximum of $\textit{nums}[0..p]$ is strictly greater than $p + 1$, so at least one swap must occur between $p$ and $p + 1$. Therefore, all indices in $[j..i]$ must participate in swaps, and every locked index in this segment must be unlocked.

After the scan, if the total number of required unlocks $\textit{cnt} > k$, sorting is impossible and we return $-1$; otherwise, we return $\textit{cnt} + \textit{inv}$.

The time complexity is $O(n \log n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

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
