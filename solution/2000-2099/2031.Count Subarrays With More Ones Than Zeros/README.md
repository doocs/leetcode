---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2031.Count%20Subarrays%20With%20More%20Ones%20Than%20Zeros/README.md
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

# [2031. 1 比 0 多的子数组个数 🔒](https://leetcode.cn/problems/count-subarrays-with-more-ones-than-zeros)

[English Version](/solution/2000-2099/2031.Count%20Subarrays%20With%20More%20Ones%20Than%20Zeros/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个只包含 <code>0</code> 和 <code>1</code> 的数组 <code>nums</code>，请返回 <code>1</code> 的数量 <strong>大于 </strong><code>0</code> 的数量的子数组的个数。由于答案可能很大，请返回答案对&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code>&nbsp;<strong>取余</strong>&nbsp;的结果。</p>

<p>一个 <strong>子数组</strong> 指的是原数组中连续的一个子序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> nums = [0,1,1,0,1]
<strong>输出:</strong> 9
<strong>解释:</strong>
长度为 1 的、1 的数量大于 0 的数量的子数组有: [1], [1], [1]
长度为 2 的、1 的数量大于 0 的数量的子数组有: [1,1]
长度为 3 的、1 的数量大于 0 的数量的子数组有: [0,1,1], [1,1,0], [1,0,1]
长度为 4 的、1 的数量大于 0 的数量的子数组有: [1,1,0,1]
长度为 5 的、1 的数量大于 0 的数量的子数组有: [0,1,1,0,1]
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> nums = [0]
<strong>输出:</strong> 0
<strong>解释:</strong>
没有子数组的 1 的数量大于 0 的数量。
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入:</strong> nums = [1]
<strong>输出:</strong> 1
<strong>解释:</strong>
长度为 1 的、1 的数量大于 0 的数量的子数组有: [1]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和 + 树状数组

题目需要我们统计所有子数组中 $1$ 的数量大于 $0$ 的数量的子数组的个数，如果我们将数组中的元素 $0$ 看作 $-1$，那么题目就变成了统计所有子数组中元素和大于 $0$ 的子数组的个数。

求子数组的元素和，可以使用前缀和来实现。为了统计所有子数组中元素和大于 $0$ 的子数组的个数，我们可以用树状数组维护每个前缀和出现的次数。初始时前缀和为 $0$ 的次数为 $1$。

接下来，我们遍历数组 $nums$，用变量 $s$ 记录当前的前缀和，用变量 $ans$ 记录答案。对于每个位置 $i$，更新前缀和 $s$，然后我们在树状数组中查询 $[0, s)$ 范围内的前缀和出现的次数，将其加到 $ans$ 中，然后在树状数组中更新 $s$ 出现的次数。

最后返回 $ans$ 即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class BinaryIndexedTree:
    __slots__ = ["n", "c"]

    def __init__(self, n: int):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x: int, v: int):
        while x <= self.n:
            self.c[x] += v
            x += x & -x

    def query(self, x: int) -> int:
        s = 0
        while x:
            s += self.c[x]
            x -= x & -x
        return s


class Solution:
    def subarraysWithMoreZerosThanOnes(self, nums: List[int]) -> int:
        n = len(nums)
        base = n + 1
        tree = BinaryIndexedTree(n + base)
        tree.update(base, 1)
        mod = 10**9 + 7
        ans = s = 0
        for x in nums:
            s += x or -1
            ans += tree.query(s - 1 + base)
            ans %= mod
            tree.update(s + base, 1)
        return ans
```

#### Java

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int v) {
        for (; x <= n; x += x & -x) {
            c[x] += v;
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
    public int subarraysWithMoreZerosThanOnes(int[] nums) {
        int n = nums.length;
        int base = n + 1;
        BinaryIndexedTree tree = new BinaryIndexedTree(n + base);
        tree.update(base, 1);
        final int mod = (int) 1e9 + 7;
        int ans = 0, s = 0;
        for (int x : nums) {
            s += x == 0 ? -1 : 1;
            ans += tree.query(s - 1 + base);
            ans %= mod;
            tree.update(s + base, 1);
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
        , c(n + 1, 0) {}

    void update(int x, int v) {
        for (; x <= n; x += x & -x) {
            c[x] += v;
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
    int subarraysWithMoreZerosThanOnes(vector<int>& nums) {
        int n = nums.size();
        int base = n + 1;
        BinaryIndexedTree tree(n + base);
        tree.update(base, 1);
        const int mod = 1e9 + 7;
        int ans = 0, s = 0;
        for (int x : nums) {
            s += (x == 0) ? -1 : 1;
            ans += tree.query(s - 1 + base);
            ans %= mod;
            tree.update(s + base, 1);
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

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	return &BinaryIndexedTree{n: n, c: make([]int, n+1)}
}

func (bit *BinaryIndexedTree) update(x, v int) {
	for ; x <= bit.n; x += x & -x {
		bit.c[x] += v
	}
}

func (bit *BinaryIndexedTree) query(x int) (s int) {
	for ; x > 0; x -= x & -x {
		s += bit.c[x]
	}
	return
}

func subarraysWithMoreZerosThanOnes(nums []int) (ans int) {
	n := len(nums)
	base := n + 1
	tree := newBinaryIndexedTree(n + base)
	tree.update(base, 1)
	const mod = int(1e9) + 7
	s := 0
	for _, x := range nums {
		if x == 0 {
			s--
		} else {
			s++
		}
		ans += tree.query(s - 1 + base)
		ans %= mod
		tree.update(s+base, 1)
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

    update(x: number, v: number): void {
        for (; x <= this.n; x += x & -x) {
            this.c[x] += v;
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

function subarraysWithMoreZerosThanOnes(nums: number[]): number {
    const n: number = nums.length;
    const base: number = n + 1;
    const tree: BinaryIndexedTree = new BinaryIndexedTree(n + base);
    tree.update(base, 1);
    const mod: number = 1e9 + 7;
    let ans: number = 0;
    let s: number = 0;
    for (const x of nums) {
        s += x || -1;
        ans += tree.query(s - 1 + base);
        ans %= mod;
        tree.update(s + base, 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def subarraysWithMoreZerosThanOnes(self, nums: List[int]) -> int:
        sl = SortedList([0])
        mod = 10**9 + 7
        ans = s = 0
        for x in nums:
            s += x or -1
            ans += sl.bisect_left(s)
            ans %= mod
            sl.add(s)
        return ans
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
