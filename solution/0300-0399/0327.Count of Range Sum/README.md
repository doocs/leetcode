---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0327.Count%20of%20Range%20Sum/README.md
tags:
    - 树状数组
    - 线段树
    - 数组
    - 二分查找
    - 分治
    - 有序集合
    - 归并排序
---

# [327. 区间和的个数](https://leetcode.cn/problems/count-of-range-sum)

[English Version](/solution/0300-0399/0327.Count%20of%20Range%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 以及两个整数 <code>lower</code> 和 <code>upper</code> 。求数组中，值位于范围 <code>[lower, upper]</code> （包含 <code>lower</code> 和 <code>upper</code>）之内的 <strong>区间和的个数</strong> 。</p>

<p><strong>区间和</strong> <code>S(i, j)</code> 表示在 <code>nums</code> 中，位置从 <code>i</code> 到 <code>j</code> 的元素之和，包含 <code>i</code> 和 <code>j</code> (<code>i</code> ≤ <code>j</code>)。</p>

<p> </p>
<strong>示例 1：</strong>

<pre>
<strong>输入：</strong>nums = [-2,5,-1], lower = -2, upper = 2
<strong>输出：</strong>3
<strong>解释：</strong>存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0], lower = 0, upper = 0
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</code></li>
	<li><code>-10<sup>5</sup> <= lower <= upper <= 10<sup>5</sup></code></li>
	<li>题目数据保证答案是一个 <strong>32 位</strong> 的整数</li>
</ul>

## 解法

### 方法一：前缀和 + 树状数组

题目要求区间和，因此我们可以先求出前缀和数组 $s$，其中 $s[i]$ 表示 $nums$ 中前 $i$ 个元素的和。那么对于任意的 $i \lt j$，$s[j+1] - s[i]$ 就是 $nums$ 中下标在 $[i, j]$ 的元素之和。

而 $lower \leq s[j+1] - s[i] \leq upper$，可以转换为 $s[j+1] - upper \leq s[i] \leq s[j+1] - lower$，也就是说，对于当前前缀和 $s[j+1]$，我们需要统计 $s$ 中有多少个下标 $i$ 满足 $s[j+1] - upper \leq s[i] \leq s[j+1] - lower$。

我们可以用树状数组来维护每个前缀和出现的次数，这样对于每个前缀和 $s[j+1]$，我们只需要查询树状数组中有多少个前缀和 $s[i]$ 满足 $s[j+1] - upper \leq s[i] \leq s[j+1] - lower$ 即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

```python
class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x, v):
        while x <= self.n:
            self.c[x] += v
            x += x & -x

    def query(self, x):
        s = 0
        while x > 0:
            s += self.c[x]
            x -= x & -x
        return s


class Solution:
    def countRangeSum(self, nums: List[int], lower: int, upper: int) -> int:
        s = list(accumulate(nums, initial=0))
        arr = sorted(set(v for x in s for v in (x, x - lower, x - upper)))
        tree = BinaryIndexedTree(len(arr))
        ans = 0
        for x in s:
            l = bisect_left(arr, x - upper) + 1
            r = bisect_left(arr, x - lower) + 1
            ans += tree.query(r) - tree.query(l - 1)
            tree.update(bisect_left(arr, x) + 1, 1)
        return ans
```

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] += v;
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x != 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        long[] arr = new long[n * 3 + 3];
        for (int i = 0, j = 0; i <= n; ++i, j += 3) {
            arr[j] = s[i];
            arr[j + 1] = s[i] - lower;
            arr[j + 2] = s[i] - upper;
        }
        Arrays.sort(arr);
        int m = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (i == 0 || arr[i] != arr[i - 1]) {
                arr[m++] = arr[i];
            }
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m);
        int ans = 0;
        for (long x : s) {
            int l = search(arr, m, x - upper);
            int r = search(arr, m, x - lower);
            ans += tree.query(r) - tree.query(l - 1);
            tree.update(search(arr, m, x), 1);
        }
        return ans;
    }

    private int search(long[] nums, int r, long x) {
        int l = 0;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l + 1;
    }
}
```

```cpp
class BinaryIndexedTree {
public:
    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int v) {
        while (x <= n) {
            c[x] += v;
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }

private:
    int n;
    vector<int> c;
};

class Solution {
public:
    int countRangeSum(vector<int>& nums, int lower, int upper) {
        using ll = long long;
        int n = nums.size();
        ll s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        ll arr[(n + 1) * 3];
        for (int i = 0, j = 0; i <= n; ++i, j += 3) {
            arr[j] = s[i];
            arr[j + 1] = s[i] - lower;
            arr[j + 2] = s[i] - upper;
        }
        sort(arr, arr + (n + 1) * 3);
        int m = unique(arr, arr + (n + 1) * 3) - arr;
        BinaryIndexedTree tree(m);
        int ans = 0;
        for (int i = 0; i <= n; ++i) {
            int l = lower_bound(arr, arr + m, s[i] - upper) - arr + 1;
            int r = lower_bound(arr, arr + m, s[i] - lower) - arr + 1;
            ans += tree.query(r) - tree.query(l - 1);
            tree.update(lower_bound(arr, arr + m, s[i]) - arr + 1, 1);
        }
        return ans;
    }
};
```

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
	for x <= this.n {
		this.c[x] += delta
		x += x & -x
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= x & -x
	}
	return s
}

func countRangeSum(nums []int, lower int, upper int) (ans int) {
	n := len(nums)
	s := make([]int, n+1)
	for i, x := range nums {
		s[i+1] = s[i] + x
	}
	arr := make([]int, (n+1)*3)
	for i, j := 0, 0; i <= n; i, j = i+1, j+3 {
		arr[j] = s[i]
		arr[j+1] = s[i] - lower
		arr[j+2] = s[i] - upper
	}
	sort.Ints(arr)
	m := 0
	for i := range arr {
		if i == 0 || arr[i] != arr[i-1] {
			arr[m] = arr[i]
			m++
		}
	}
	arr = arr[:m]
	tree := newBinaryIndexedTree(m)
	for _, x := range s {
		l := sort.SearchInts(arr, x-upper) + 1
		r := sort.SearchInts(arr, x-lower) + 1
		ans += tree.query(r) - tree.query(l-1)
		tree.update(sort.SearchInts(arr, x)+1, 1)
	}
	return
}
```

```ts
class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    update(x: number, v: number) {
        while (x <= this.n) {
            this.c[x] += v;
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

function countRangeSum(nums: number[], lower: number, upper: number): number {
    const n = nums.length;
    const s = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    let arr: number[] = Array((n + 1) * 3);
    for (let i = 0, j = 0; i <= n; ++i, j += 3) {
        arr[j] = s[i];
        arr[j + 1] = s[i] - lower;
        arr[j + 2] = s[i] - upper;
    }
    arr.sort((a, b) => a - b);
    let m = 0;
    for (let i = 0; i < arr.length; ++i) {
        if (i === 0 || arr[i] !== arr[i - 1]) {
            arr[m++] = arr[i];
        }
    }
    arr = arr.slice(0, m);
    const tree = new BinaryIndexedTree(m);
    let ans = 0;
    for (const x of s) {
        const l = search(arr, m, x - upper);
        const r = search(arr, m, x - lower);
        ans += tree.query(r) - tree.query(l - 1);
        tree.update(search(arr, m, x), 1);
    }
    return ans;
}

function search(nums: number[], r: number, x: number): number {
    let l = 0;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] >= x) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l + 1;
}
```

<!-- tabs:end -->

<!-- end -->
