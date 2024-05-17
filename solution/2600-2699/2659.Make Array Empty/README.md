---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2659.Make%20Array%20Empty/README.md
rating: 2281
source: 第 103 场双周赛 Q4
tags:
    - 贪心
    - 树状数组
    - 线段树
    - 数组
    - 二分查找
    - 有序集合
    - 排序
---

<!-- problem:start -->

# [2659. 将数组清空](https://leetcode.cn/problems/make-array-empty)

[English Version](/solution/2600-2699/2659.Make%20Array%20Empty/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个包含若干 <strong>互不相同</strong>&nbsp;整数的数组&nbsp;<code>nums</code>&nbsp;，你需要执行以下操作 <strong>直到</strong><strong>数组为空</strong>&nbsp;：</p>

<ul>
	<li>如果数组中第一个元素是当前数组中的 <strong>最小值</strong>&nbsp;，则删除它。</li>
	<li>否则，将第一个元素移动到数组的 <strong>末尾</strong>&nbsp;。</li>
</ul>

<p>请你返回需要多少个操作使<em>&nbsp;</em><code>nums</code><em>&nbsp;</em>为空。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [3,4,-1]
<b>输出：</b>5
</pre>

<table style="border: 2px solid black; border-collapse: collapse;">
	<thead>
		<tr>
			<th style="border: 2px solid black; padding: 5px;">Operation</th>
			<th style="border: 2px solid black; padding: 5px;">Array</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 2px solid black; padding: 5px;">1</td>
			<td style="border: 2px solid black; padding: 5px;">[4, -1, 3]</td>
		</tr>
		<tr>
			<td style="border: 2px solid black; padding: 5px;">2</td>
			<td style="border: 2px solid black; padding: 5px;">[-1, 3, 4]</td>
		</tr>
		<tr>
			<td style="border: 2px solid black; padding: 5px;">3</td>
			<td style="border: 2px solid black; padding: 5px;">[3, 4]</td>
		</tr>
		<tr>
			<td style="border: 2px solid black; padding: 5px;">4</td>
			<td style="border: 2px solid black; padding: 5px;">[4]</td>
		</tr>
		<tr>
			<td style="border: 2px solid black; padding: 5px;">5</td>
			<td style="border: 2px solid black; padding: 5px;">[]</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,4,3]
<b>输出：</b>5
</pre>

<table style="border: 2px solid black; border-collapse: collapse;">
	<thead>
		<tr>
			<th style="border: 2px solid black; padding: 5px;">Operation</th>
			<th style="border: 2px solid black; padding: 5px;">Array</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 2px solid black; padding: 5px;">1</td>
			<td style="border: 2px solid black; padding: 5px;">[2, 4, 3]</td>
		</tr>
		<tr>
			<td style="border: 2px solid black; padding: 5px;">2</td>
			<td style="border: 2px solid black; padding: 5px;">[4, 3]</td>
		</tr>
		<tr>
			<td style="border: 2px solid black; padding: 5px;">3</td>
			<td style="border: 2px solid black; padding: 5px;">[3, 4]</td>
		</tr>
		<tr>
			<td style="border: 2px solid black; padding: 5px;">4</td>
			<td style="border: 2px solid black; padding: 5px;">[4]</td>
		</tr>
		<tr>
			<td style="border: 2px solid black; padding: 5px;">5</td>
			<td style="border: 2px solid black; padding: 5px;">[]</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3]
<b>输出：</b>3
</pre>

<table style="border: 2px solid black; border-collapse: collapse;">
	<thead>
		<tr>
			<th style="border: 2px solid black; padding: 5px;">Operation</th>
			<th style="border: 2px solid black; padding: 5px;">Array</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="border: 2px solid black; padding: 5px;">1</td>
			<td style="border: 2px solid black; padding: 5px;">[2, 3]</td>
		</tr>
		<tr>
			<td style="border: 2px solid black; padding: 5px;">2</td>
			<td style="border: 2px solid black; padding: 5px;">[3]</td>
		</tr>
		<tr>
			<td style="border: 2px solid black; padding: 5px;">3</td>
			<td style="border: 2px solid black; padding: 5px;">[]</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9&nbsp;</sup>&lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums</code>&nbsp;中的元素 <strong>互不相同</strong>&nbsp;。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 排序 + 树状数组

我们先用哈希表 $pos$ 记录数组 $nums$ 中每个元素的位置，接下来对数组 $nums$ 进行排序，那么数组最小值的下标为 $pos[nums[0]]$，移动到数组的第一个位置并且删除，需要 $pos[nums[0]] + 1$ 次操作，因此初始答案为 $ans = pos[nums[0]] + 1$。

接下来，我们遍历排序后的数组 $nums$，相邻两个元素 $a$ 和 $b$ 的下标分别为 $i=pos[a]$, $j=pos[b]$。那么将第二个元素 $b$ 移动到数组第一个位置并且删除所需要的操作数，等于两个下标的间隔，减去两个下标之间此前删除的下标个数，累加操作数到答案中。我们可以用树状数组或者有序列表维护已删除的下标，这样就可以在 $O(\log n)$ 的时间内求出两个下标之间已删除的下标个数。注意，如果 $i \gt j$，那么需要额外增加 $n - k$ 次操作，其中 $k$ 是当前遍历到的位置。

遍历结束后，返回操作数 $ans$ 即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
from sortedcontainers import SortedList


class Solution:
    def countOperationsToEmptyArray(self, nums: List[int]) -> int:
        pos = {x: i for i, x in enumerate(nums)}
        nums.sort()
        sl = SortedList()
        ans = pos[nums[0]] + 1
        n = len(nums)
        for k, (a, b) in enumerate(pairwise(nums)):
            i, j = pos[a], pos[b]
            d = j - i - sl.bisect(j) + sl.bisect(i)
            ans += d + (n - k) * int(i > j)
            sl.add(i)
        return ans
```

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
    public long countOperationsToEmptyArray(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            pos.put(nums[i], i);
        }
        Arrays.sort(nums);
        long ans = pos.get(nums[0]) + 1;
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        for (int k = 0; k < n - 1; ++k) {
            int i = pos.get(nums[k]), j = pos.get(nums[k + 1]);
            long d = j - i - (tree.query(j + 1) - tree.query(i + 1));
            ans += d + (n - k) * (i > j ? 1 : 0);
            tree.update(i + 1, 1);
        }
        return ans;
    }
}
```

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
    long long countOperationsToEmptyArray(vector<int>& nums) {
        unordered_map<int, int> pos;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            pos[nums[i]] = i;
        }
        sort(nums.begin(), nums.end());
        BinaryIndexedTree tree(n);
        long long ans = pos[nums[0]] + 1;
        for (int k = 0; k < n - 1; ++k) {
            int i = pos[nums[k]], j = pos[nums[k + 1]];
            long long d = j - i - (tree.query(j + 1) - tree.query(i + 1));
            ans += d + (n - k) * int(i > j);
            tree.update(i + 1, 1);
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

func countOperationsToEmptyArray(nums []int) int64 {
	n := len(nums)
	pos := map[int]int{}
	for i, x := range nums {
		pos[x] = i
	}
	sort.Ints(nums)
	tree := newBinaryIndexedTree(n)
	ans := pos[nums[0]] + 1
	for k := 0; k < n-1; k++ {
		i, j := pos[nums[k]], pos[nums[k+1]]
		d := j - i - (tree.query(j+1) - tree.query(i+1))
		if i > j {
			d += n - k
		}
		ans += d
		tree.update(i+1, 1)
	}
	return int64(ans)
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

    public update(x: number, v: number): void {
        while (x <= this.n) {
            this.c[x] += v;
            x += x & -x;
        }
    }

    public query(x: number): number {
        let s = 0;
        while (x > 0) {
            s += this.c[x];
            x -= x & -x;
        }
        return s;
    }
}

function countOperationsToEmptyArray(nums: number[]): number {
    const pos: Map<number, number> = new Map();
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        pos.set(nums[i], i);
    }
    nums.sort((a, b) => a - b);
    const tree = new BinaryIndexedTree(n);
    let ans = pos.get(nums[0])! + 1;
    for (let k = 0; k < n - 1; ++k) {
        const i = pos.get(nums[k])!;
        const j = pos.get(nums[k + 1])!;
        let d = j - i - (tree.query(j + 1) - tree.query(i + 1));
        if (i > j) {
            d += n - k;
        }
        ans += d;
        tree.update(i + 1, 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

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
    def countOperationsToEmptyArray(self, nums: List[int]) -> int:
        pos = {x: i for i, x in enumerate(nums)}
        nums.sort()
        ans = pos[nums[0]] + 1
        n = len(nums)
        tree = BinaryIndexedTree(n)
        for k, (a, b) in enumerate(pairwise(nums)):
            i, j = pos[a], pos[b]
            d = j - i - tree.query(j + 1) + tree.query(i + 1)
            ans += d + (n - k) * int(i > j)
            tree.update(i + 1, 1)
        return ans
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
