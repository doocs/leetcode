---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2519.Count%20the%20Number%20of%20K-Big%20Indices/README.md
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

# [2519. 统计 K-Big 索引的数量 🔒](https://leetcode.cn/problems/count-the-number-of-k-big-indices)

[English Version](/solution/2500-2599/2519.Count%20the%20Number%20of%20K-Big%20Indices/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个 <strong>下标从0开始</strong> 的整数数组 <code>nums</code> 和一个正整数 <code>k</code> 。</p>

<p>如果满足以下条件，我们称下标 <code>i</code> 为 <strong>k-big</strong> ：</p>

<ul>
	<li>存在至少 <code>k</code> 个不同的索引 <code>idx1</code> ，满足 <code>idx1 &lt; i</code> 且 <code>nums[idx1] &lt; nums[i]</code> 。</li>
	<li>存在至少 <code>k</code> 个不同的索引 <code>idx2</code> ，满足 <code>idx2 &gt; i</code> 且 <code>nums[idx2] &lt; nums[i]</code> 。</li>
</ul>

<p>返回 k-big 索引的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1 ：</strong></p>

<pre>
<b>输入：</b>nums = [2,3,6,5,2,3], k = 2
<b>输出：</b>2
<b>解释：</b>在nums中只有两个 2-big 的索引:
- i = 2 --&gt; 有两个有效的 idx1: 0 和 1。有三个有效的 idx2: 2、3 和 4。
- i = 3 --&gt; 有两个有效的 idx1: 0 和 1。有两个有效的 idx2: 3 和 4。</pre>

<p><strong class="example">示例 2 ：</strong></p>

<pre>
<b>输入：</b>nums = [1,1,1], k = 3
<b>输出：</b>0
<b>解释：</b>在 nums 中没有 3-big 的索引
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：树状数组

我们维护两个树状数组，一个记录当前位置左边小于当前位置的数的个数，另一个记录当前位置右边小于当前位置的数的个数。

遍历数组，对于当前位置，如果左边小于当前位置的数的个数大于等于 $k$，且右边小于当前位置的数的个数大于等于 $k$，则当前位置是 $k-big$，答案加一。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

#### Python3

```python
class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] += delta
            x += x & -x

    def query(self, x):
        s = 0
        while x:
            s += self.c[x]
            x -= x & -x
        return s


class Solution:
    def kBigIndices(self, nums: List[int], k: int) -> int:
        n = len(nums)
        tree1 = BinaryIndexedTree(n)
        tree2 = BinaryIndexedTree(n)
        for v in nums:
            tree2.update(v, 1)
        ans = 0
        for v in nums:
            tree2.update(v, -1)
            ans += tree1.query(v - 1) >= k and tree2.query(v - 1) >= k
            tree1.update(v, 1)
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
    public int kBigIndices(int[] nums, int k) {
        int n = nums.length;
        BinaryIndexedTree tree1 = new BinaryIndexedTree(n);
        BinaryIndexedTree tree2 = new BinaryIndexedTree(n);
        for (int v : nums) {
            tree2.update(v, 1);
        }
        int ans = 0;
        for (int v : nums) {
            tree2.update(v, -1);
            if (tree1.query(v - 1) >= k && tree2.query(v - 1) >= k) {
                ++ans;
            }
            tree1.update(v, 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class BinaryIndexedTree {
public:
    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
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
    int kBigIndices(vector<int>& nums, int k) {
        int n = nums.size();
        BinaryIndexedTree* tree1 = new BinaryIndexedTree(n);
        BinaryIndexedTree* tree2 = new BinaryIndexedTree(n);
        for (int& v : nums) {
            tree2->update(v, 1);
        }
        int ans = 0;
        for (int& v : nums) {
            tree2->update(v, -1);
            ans += tree1->query(v - 1) >= k && tree2->query(v - 1) >= k;
            tree1->update(v, 1);
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

func kBigIndices(nums []int, k int) (ans int) {
	n := len(nums)
	tree1 := newBinaryIndexedTree(n)
	tree2 := newBinaryIndexedTree(n)
	for _, v := range nums {
		tree2.update(v, 1)
	}
	for _, v := range nums {
		tree2.update(v, -1)
		if tree1.query(v-1) >= k && tree2.query(v-1) >= k {
			ans++
		}
		tree1.update(v, 1)
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

function kBigIndices(nums: number[], k: number): number {
    const n = Math.max(...nums);
    const tree1 = new BinaryIndexedTree(n);
    const tree2 = new BinaryIndexedTree(n);

    for (const v of nums) {
        tree2.update(v, 1);
    }

    let ans = 0;
    for (const v of nums) {
        tree2.update(v, -1);
        if (tree1.query(v - 1) >= k && tree2.query(v - 1) >= k) {
            ans++;
        }
        tree1.update(v, 1);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
