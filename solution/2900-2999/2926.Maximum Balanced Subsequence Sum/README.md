---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2926.Maximum%20Balanced%20Subsequence%20Sum/README.md
rating: 2448
source: 第 370 场周赛 Q4
tags:
    - 树状数组
    - 线段树
    - 数组
    - 二分查找
    - 动态规划
---

<!-- problem:start -->

# [2926. 平衡子序列的最大和](https://leetcode.cn/problems/maximum-balanced-subsequence-sum)

[English Version](/solution/2900-2999/2926.Maximum%20Balanced%20Subsequence%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p><code>nums</code>&nbsp;一个长度为 <code>k</code>&nbsp;的 <strong>子序列</strong>&nbsp;指的是选出 <code>k</code>&nbsp;个 <strong>下标</strong>&nbsp;<code>i<sub>0</sub>&nbsp;&lt;&nbsp;i<sub>1</sub> &lt;&nbsp;... &lt; i<sub>k-1</sub></code>&nbsp;，如果这个子序列满足以下条件，我们说它是 <strong>平衡的</strong>&nbsp;：</p>

<ul>
	<li>对于范围&nbsp;<code>[1, k - 1]</code>&nbsp;内的所有&nbsp;<code>j</code>&nbsp;，<code>nums[i<sub>j</sub>] - nums[i<sub>j-1</sub>] &gt;= i<sub>j</sub> - i<sub>j-1</sub></code>&nbsp;都成立。</li>
</ul>

<p><code>nums</code>&nbsp;长度为 <code>1</code>&nbsp;的 <strong>子序列</strong>&nbsp;是平衡的。</p>

<p>请你返回一个整数，表示 <code>nums</code>&nbsp;<strong>平衡</strong>&nbsp;子序列里面的 <strong>最大元素和</strong>&nbsp;。</p>

<p>一个数组的 <strong>子序列</strong>&nbsp;指的是从原数组中删除一些元素（<strong>也可能一个元素也不删除</strong>）后，剩余元素保持相对顺序得到的 <strong>非空</strong>&nbsp;新数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [3,3,5,6]
<b>输出：</b>14
<b>解释：</b>这个例子中，选择子序列 [3,5,6] ，下标为 0 ，2 和 3 的元素被选中。
nums[2] - nums[0] &gt;= 2 - 0 。
nums[3] - nums[2] &gt;= 3 - 2 。
所以，这是一个平衡子序列，且它的和是所有平衡子序列里最大的。
包含下标 1 ，2 和 3 的子序列也是一个平衡的子序列。
最大平衡子序列和为 14 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [5,-1,-3,8]
<b>输出：</b>13
<b>解释：</b>这个例子中，选择子序列 [5,8] ，下标为 0 和 3 的元素被选中。
nums[3] - nums[0] &gt;= 3 - 0 。
所以，这是一个平衡子序列，且它的和是所有平衡子序列里最大的。
最大平衡子序列和为 13 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [-2,-1]
<b>输出：</b>-1
<b>解释：</b>这个例子中，选择子序列 [-1] 。
这是一个平衡子序列，而且它的和是 nums 所有平衡子序列里最大的。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划 + 树状数组

根据题目描述，我们可以将不等式 $nums[i] - nums[j] \ge i - j$ 转化为 $nums[i] - i \ge nums[j] - j$，因此，我们考虑定义一个新数组 $arr$，其中 $arr[i] = nums[i] - i$，那么平衡子序列满足对于任意 $j \lt i$，都有 $arr[j] \le arr[i]$。即题目转换为求在 $arr$ 中选出一个递增子序列，使得对应的 $nums$ 的和最大。

假设 $i$ 是子序列中最后一个元素的下标，那么我们考虑子序列倒数第二个元素的下标 $j$，如果 $arr[j] \le arr[i]$，我们可以考虑是否要将 $j$ 加入到子序列中。

因此，我们定义 $f[i]$ 表示子序列最后一个元素的下标为 $i$ 时，对应的 $nums$ 的最大和，那么答案为 $\max_{i=0}^{n-1} f[i]$。

状态转移方程为：

$$
f[i] = \max(\max_{j=0}^{i-1} f[j], 0) + nums[i]
$$

其中 $j$ 满足 $arr[j] \le arr[i]$。

我们可以使用树状数组来维护前缀的最大值，即对于每个 $arr[i]$，我们维护前缀 $arr[0..i]$ 中 $f[i]$ 的最大值。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class BinaryIndexedTree:
    def __init__(self, n: int):
        self.n = n
        self.c = [-inf] * (n + 1)

    def update(self, x: int, v: int):
        while x <= self.n:
            self.c[x] = max(self.c[x], v)
            x += x & -x

    def query(self, x: int) -> int:
        mx = -inf
        while x:
            mx = max(mx, self.c[x])
            x -= x & -x
        return mx


class Solution:
    def maxBalancedSubsequenceSum(self, nums: List[int]) -> int:
        arr = [x - i for i, x in enumerate(nums)]
        s = sorted(set(arr))
        tree = BinaryIndexedTree(len(s))
        for i, x in enumerate(nums):
            j = bisect_left(s, x - i) + 1
            v = max(tree.query(j), 0) + x
            tree.update(j, v)
        return tree.query(len(s))
```

```java
class BinaryIndexedTree {
    private int n;
    private long[] c;
    private final long inf = 1L << 60;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new long[n + 1];
        Arrays.fill(c, -inf);
    }

    public void update(int x, long v) {
        while (x <= n) {
            c[x] = Math.max(c[x], v);
            x += x & -x;
        }
    }

    public long query(int x) {
        long mx = -inf;
        while (x > 0) {
            mx = Math.max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
}

class Solution {
    public long maxBalancedSubsequenceSum(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = nums[i] - i;
        }
        Arrays.sort(arr);
        int m = 0;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || arr[i] != arr[i - 1]) {
                arr[m++] = arr[i];
            }
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m);
        for (int i = 0; i < n; ++i) {
            int j = search(arr, nums[i] - i, m) + 1;
            long v = Math.max(tree.query(j), 0) + nums[i];
            tree.update(j, v);
        }
        return tree.query(m);
    }

    private int search(int[] nums, int x, int r) {
        int l = 0;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

```cpp
class BinaryIndexedTree {
private:
    int n;
    vector<long long> c;
    const long long inf = 1e18;

public:
    BinaryIndexedTree(int n) {
        this->n = n;
        c.resize(n + 1, -inf);
    }

    void update(int x, long long v) {
        while (x <= n) {
            c[x] = max(c[x], v);
            x += x & -x;
        }
    }

    long long query(int x) {
        long long mx = -inf;
        while (x > 0) {
            mx = max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
};

class Solution {
public:
    long long maxBalancedSubsequenceSum(vector<int>& nums) {
        int n = nums.size();
        vector<int> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = nums[i] - i;
        }
        sort(arr.begin(), arr.end());
        arr.erase(unique(arr.begin(), arr.end()), arr.end());
        int m = arr.size();
        BinaryIndexedTree tree(m);
        for (int i = 0; i < n; ++i) {
            int j = lower_bound(arr.begin(), arr.end(), nums[i] - i) - arr.begin() + 1;
            long long v = max(tree.query(j), 0LL) + nums[i];
            tree.update(j, v);
        }
        return tree.query(m);
    }
};
```

```go
const inf int = 1e18

type BinaryIndexedTree struct {
	n int
	c []int
}

func NewBinaryIndexedTree(n int) BinaryIndexedTree {
	c := make([]int, n+1)
	for i := range c {
		c[i] = -inf
	}
	return BinaryIndexedTree{n: n, c: c}
}

func (bit *BinaryIndexedTree) update(x, v int) {
	for x <= bit.n {
		bit.c[x] = max(bit.c[x], v)
		x += x & -x
	}
}

func (bit *BinaryIndexedTree) query(x int) int {
	mx := -inf
	for x > 0 {
		mx = max(mx, bit.c[x])
		x -= x & -x
	}
	return mx
}

func maxBalancedSubsequenceSum(nums []int) int64 {
	n := len(nums)
	arr := make([]int, n)
	for i, x := range nums {
		arr[i] = x - i
	}
	sort.Ints(arr)
	m := 0
	for i, x := range arr {
		if i == 0 || x != arr[i-1] {
			arr[m] = x
			m++
		}
	}
	arr = arr[:m]
	tree := NewBinaryIndexedTree(m)
	for i, x := range nums {
		j := sort.SearchInts(arr, x-i) + 1
		v := max(tree.query(j), 0) + x
		tree.update(j, v)
	}
	return int64(tree.query(m))
}
```

```ts
class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(-Infinity);
    }

    update(x: number, v: number): void {
        while (x <= this.n) {
            this.c[x] = Math.max(this.c[x], v);
            x += x & -x;
        }
    }

    query(x: number): number {
        let mx = -Infinity;
        while (x > 0) {
            mx = Math.max(mx, this.c[x]);
            x -= x & -x;
        }
        return mx;
    }
}

function maxBalancedSubsequenceSum(nums: number[]): number {
    const n = nums.length;
    const arr = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        arr[i] = nums[i] - i;
    }
    arr.sort((a, b) => a - b);
    let m = 0;
    for (let i = 0; i < n; ++i) {
        if (i === 0 || arr[i] !== arr[i - 1]) {
            arr[m++] = arr[i];
        }
    }
    arr.length = m;
    const tree = new BinaryIndexedTree(m);
    const search = (nums: number[], x: number): number => {
        let [l, r] = [0, nums.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let i = 0; i < n; ++i) {
        const j = search(arr, nums[i] - i) + 1;
        const v = Math.max(tree.query(j), 0) + nums[i];
        tree.update(j, v);
    }
    return tree.query(m);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
