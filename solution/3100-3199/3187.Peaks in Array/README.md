---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3187.Peaks%20in%20Array/README.md
tags:
    - 树状数组
    - 线段树
    - 数组
---

<!-- problem:start -->

# [3187. 数组中的峰值](https://leetcode.cn/problems/peaks-in-array)

[English Version](/solution/3100-3199/3187.Peaks%20in%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>数组 <code>arr</code>&nbsp;中&nbsp;<strong>大于</strong>&nbsp;前面和后面相邻元素的元素被称为 <strong>峰值</strong>&nbsp;元素。</p>

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个二维整数数组&nbsp;<code>queries</code>&nbsp;。</p>

<p>你需要处理以下两种类型的操作：</p>

<ul>
	<li><code>queries[i] = [1, l<sub>i</sub>, r<sub>i</sub>]</code>&nbsp;，求出子数组&nbsp;<code>nums[l<sub>i</sub>..r<sub>i</sub>]</code>&nbsp;中&nbsp;<strong>峰值</strong>&nbsp;元素的数目。<!-- notionvc: 73b20b7c-e1ab-4dac-86d0-13761094a9ae --></li>
	<li><code>queries[i] = [2, index<sub>i</sub>, val<sub>i</sub>]</code>&nbsp;，将&nbsp;<code>nums[index<sub>i</sub>]</code>&nbsp;变为&nbsp;<code><font face="monospace">val<sub>i</sub></font></code><font face="monospace">&nbsp;。</font></li>
</ul>

<p>请你返回一个数组&nbsp;<code>answer</code>&nbsp;，它依次包含每一个第一种操作的答案。<!-- notionvc: a9ccef22-4061-4b5a-b4cc-a2b2a0e12f30 --></p>

<p><strong>注意：</strong></p>

<ul>
	<li>子数组中 <strong>第一个</strong>&nbsp;和 <strong>最后一个</strong>&nbsp;元素都 <strong>不是</strong>&nbsp;峰值元素。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [3,1,4,2,5], queries = [[2,3,4],[1,0,4]]</span></p>

<p><span class="example-io"><b>输出：</b>[0]</span></p>

<p><strong>解释：</strong></p>

<p>第一个操作：我们将&nbsp;<code>nums[3]</code>&nbsp;变为&nbsp;4 ，<code>nums</code>&nbsp;变为&nbsp;<code>[3,1,4,4,5]</code>&nbsp;。</p>

<p>第二个操作：<code>[3,1,4,4,5]</code>&nbsp;中峰值元素的数目为 0 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [4,1,4,2,1,5], queries = [[2,2,4],[1,0,2],[1,0,4]]</span></p>

<p><span class="example-io"><b>输出：</b>[0,1]</span></p>

<p><strong>解释：</strong></p>

<p>第一个操作：<code>nums[2]</code>&nbsp;变为 4 ，它已经是 4 了，所以保持不变。</p>

<p>第二个操作：<code>[4,1,4]</code>&nbsp;中峰值元素的数目为 0 。</p>

<p>第三个操作：第二个 4 是&nbsp;<code>[4,1,4,2,1]</code>&nbsp;中的峰值元素。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i][0] == 1</code>&nbsp;或者&nbsp;<code>queries[i][0] == 2</code></li>
	<li>对于所有的 <code>i</code>&nbsp;，都有：
	<ul>
		<li><code>queries[i][0] == 1</code>&nbsp;：<code>0 &lt;= queries[i][1] &lt;= queries[i][2] &lt;= nums.length - 1</code></li>
		<li><code>queries[i][0] == 2</code> ：<code>0 &lt;= queries[i][1] &lt;= nums.length - 1</code>, <code>1 &lt;= queries[i][2] &lt;= 10<sup>5</sup></code></li>
	</ul>
	</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：树状数组

根据题目描述，对于 $0 < i < n - 1$，如果满足 $nums[i - 1] < nums[i]$ 且 $nums[i] > nums[i + 1]$，我们可以将 $nums[i]$ 视为 $1$，否则视为 $0$。这样，对于操作 $1$，即查询子数组 $nums[l..r]$ 中的峰值元素个数，相当于查询区间 $[l + 1, r - 1]$ 中 $1$ 的个数。我们可以使用树状数组来维护区间 $[1, n - 1]$ 中 $1$ 的个数。

而对于操作 $1$，即把 $nums[idx]$ 更新为 $val$，只会影响到 $idx - 1$, $idx$, $idx + 1$ 这三个位置的值，因此我们只需要更新这三个位置即可。具体地，我们可以先把这三个位置的峰值元素去掉，然后更新 $nums[idx]$ 的值，最后再把这三个位置的峰值元素加回来。

时间复杂度 $O((n + q) \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 和 $q$ 分别是数组 `nums` 的长度和查询数组 `queries` 的长度。

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
    def countOfPeaks(self, nums: List[int], queries: List[List[int]]) -> List[int]:
        def update(i: int, val: int):
            if i <= 0 or i >= n - 1:
                return
            if nums[i - 1] < nums[i] and nums[i] > nums[i + 1]:
                tree.update(i, val)

        n = len(nums)
        tree = BinaryIndexedTree(n - 1)
        for i in range(1, n - 1):
            update(i, 1)
        ans = []
        for q in queries:
            if q[0] == 1:
                l, r = q[1] + 1, q[2] - 1
                ans.append(0 if l > r else tree.query(r) - tree.query(l - 1))
            else:
                idx, val = q[1:]
                for i in range(idx - 1, idx + 2):
                    update(i, -1)
                nums[idx] = val
                for i in range(idx - 1, idx + 2):
                    update(i, 1)
        return ans
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
    private BinaryIndexedTree tree;
    private int[] nums;

    public List<Integer> countOfPeaks(int[] nums, int[][] queries) {
        int n = nums.length;
        this.nums = nums;
        tree = new BinaryIndexedTree(n - 1);
        for (int i = 1; i < n - 1; ++i) {
            update(i, 1);
        }
        List<Integer> ans = new ArrayList<>();
        for (var q : queries) {
            if (q[0] == 1) {
                int l = q[1] + 1, r = q[2] - 1;
                ans.add(l > r ? 0 : tree.query(r) - tree.query(l - 1));
            } else {
                int idx = q[1], val = q[2];
                for (int i = idx - 1; i <= idx + 1; ++i) {
                    update(i, -1);
                }
                nums[idx] = val;
                for (int i = idx - 1; i <= idx + 1; ++i) {
                    update(i, 1);
                }
            }
        }
        return ans;
    }

    private void update(int i, int val) {
        if (i <= 0 || i >= nums.length - 1) {
            return;
        }
        if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
            tree.update(i, val);
        }
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
    vector<int> countOfPeaks(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        BinaryIndexedTree tree(n - 1);
        auto update = [&](int i, int val) {
            if (i <= 0 || i >= n - 1) {
                return;
            }
            if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
                tree.update(i, val);
            }
        };
        for (int i = 1; i < n - 1; ++i) {
            update(i, 1);
        }
        vector<int> ans;
        for (auto& q : queries) {
            if (q[0] == 1) {
                int l = q[1] + 1, r = q[2] - 1;
                ans.push_back(l > r ? 0 : tree.query(r) - tree.query(l - 1));
            } else {
                int idx = q[1], val = q[2];
                for (int i = idx - 1; i <= idx + 1; ++i) {
                    update(i, -1);
                }
                nums[idx] = val;
                for (int i = idx - 1; i <= idx + 1; ++i) {
                    update(i, 1);
                }
            }
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

func countOfPeaks(nums []int, queries [][]int) (ans []int) {
	n := len(nums)
	tree := NewBinaryIndexedTree(n - 1)
	update := func(i, val int) {
		if i <= 0 || i >= n-1 {
			return
		}
		if nums[i-1] < nums[i] && nums[i] > nums[i+1] {
			tree.update(i, val)
		}
	}
	for i := 1; i < n-1; i++ {
		update(i, 1)
	}
	for _, q := range queries {
		if q[0] == 1 {
			l, r := q[1]+1, q[2]-1
			t := 0
			if l <= r {
				t = tree.query(r) - tree.query(l-1)
			}
			ans = append(ans, t)
		} else {
			idx, val := q[1], q[2]
			for i := idx - 1; i <= idx+1; i++ {
				update(i, -1)
			}
			nums[idx] = val
			for i := idx - 1; i <= idx+1; i++ {
				update(i, 1)
			}
		}
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

function countOfPeaks(nums: number[], queries: number[][]): number[] {
    const n = nums.length;
    const tree = new BinaryIndexedTree(n - 1);
    const update = (i: number, val: number): void => {
        if (i <= 0 || i >= n - 1) {
            return;
        }
        if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
            tree.update(i, val);
        }
    };
    for (let i = 1; i < n - 1; ++i) {
        update(i, 1);
    }
    const ans: number[] = [];
    for (const q of queries) {
        if (q[0] === 1) {
            const [l, r] = [q[1] + 1, q[2] - 1];
            ans.push(l > r ? 0 : tree.query(r) - tree.query(l - 1));
        } else {
            const [idx, val] = [q[1], q[2]];
            for (let i = idx - 1; i <= idx + 1; ++i) {
                update(i, -1);
            }
            nums[idx] = val;
            for (let i = idx - 1; i <= idx + 1; ++i) {
                update(i, 1);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
