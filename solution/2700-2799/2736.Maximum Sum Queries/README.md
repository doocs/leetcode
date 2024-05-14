# [2736. 最大和查询](https://leetcode.cn/problems/maximum-sum-queries)

[English Version](/solution/2700-2799/2736.Maximum%20Sum%20Queries/README_EN.md)

<!-- tags:栈,树状数组,线段树,数组,二分查找,排序,单调栈 -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个长度为 <code>n</code> 、下标从 <strong>0</strong> 开始的整数数组 <code>nums1</code> 和 <code>nums2</code> ，另给你一个下标从 <strong>1</strong> 开始的二维数组 <code>queries</code> ，其中 <code>queries[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 。</p>

<p>对于第 <code>i</code> 个查询，在所有满足 <code>nums1[j] &gt;= x<sub>i</sub></code> 且 <code>nums2[j] &gt;= y<sub>i</sub></code> 的下标 <code>j</code> <code>(0 &lt;= j &lt; n)</code> 中，找出 <code>nums1[j] + nums2[j]</code> 的 <strong>最大值</strong> ，如果不存在满足条件的 <code>j</code> 则返回 <strong>-1</strong> 。</p>

<p>返回数组<em> </em><code>answer</code><em> ，</em>其中<em> </em><code>answer[i]</code><em> </em>是第 <code>i</code> 个查询的答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums1 = [4,3,1,2], nums2 = [2,4,9,5], queries = [[4,1],[1,3],[2,5]]
<strong>输出：</strong>[6,10,7]
<strong>解释：</strong>
对于第 1 个查询：<code>x<sub>i</sub> = 4</code>&nbsp;且&nbsp;<code>y<sub>i</sub> = 1</code> ，可以选择下标&nbsp;<code>j = 0</code>&nbsp;，此时 <code>nums1[j] &gt;= 4</code>&nbsp;且&nbsp;<code>nums2[j] &gt;= 1</code> 。<code>nums1[j] + nums2[j]</code>&nbsp;等于 6 ，可以证明 6 是可以获得的最大值。
对于第 2 个查询：<code>x<sub>i</sub> = 1</code>&nbsp;且&nbsp;<code>y<sub>i</sub> = 3</code> ，可以选择下标&nbsp;<code>j = 2</code>&nbsp;，此时 <code>nums1[j] &gt;= 1</code>&nbsp;且&nbsp;<code>nums2[j] &gt;= 3</code> 。<code>nums1[j] + nums2[j]</code>&nbsp;等于 10 ，可以证明 10 是可以获得的最大值。
对于第 3 个查询：<code>x<sub>i</sub> = 2</code>&nbsp;且&nbsp;<code>y<sub>i</sub> = 5</code> ，可以选择下标&nbsp;<code>j = 3</code>&nbsp;，此时 <code>nums1[j] &gt;= 2</code>&nbsp;且&nbsp;<code>nums2[j] &gt;= 5</code> 。<code>nums1[j] + nums2[j]</code>&nbsp;等于 7 ，可以证明 7 是可以获得的最大值。
因此，我们返回&nbsp;<code>[6,10,7]</code> 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums1 = [3,2,5], nums2 = [2,3,4], queries = [[4,4],[3,2],[1,1]]
<strong>输出：</strong>[9,9,9]
<strong>解释：</strong>对于这个示例，我们可以选择下标&nbsp;<code>j = 2</code>&nbsp;，该下标可以满足每个查询的限制。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums1 = [2,1], nums2 = [2,3], queries = [[3,3]]
<strong>输出：</strong>[-1]
<strong>解释：</strong>示例中的查询 <code>x<sub>i</sub></code> = 3 且 <code>y<sub>i</sub></code> = 3 。对于每个下标 j ，都只满足 nums1[j] &lt; <code>x<sub>i</sub></code> 或者 nums2[j] &lt; <code>y<sub>i</sub></code> 。因此，不存在答案。 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums1.length == nums2.length</code>&nbsp;</li>
	<li><code>n ==&nbsp;nums1.length&nbsp;</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>9</sup>&nbsp;</code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length ==&nbsp;2</code></li>
	<li><code>x<sub>i</sub>&nbsp;== queries[i][1]</code></li>
	<li><code>y<sub>i</sub> == queries[i][2]</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：树状数组

本题属于二维偏序问题。

二维偏序是这样一类问题：给定若干个点对 $(a_1, b_1)$, $(a_2, b_2)$, $\cdots$, $(a_n, b_n)$，并定义某种偏序关系，现在给定点 $(a_i, b_i)$，求满足偏序关系的点对 $(a_j, b_j)$ 中的数量/最值。即：

$$
\left(a_{j}, b_{j}\right) \prec\left(a_{i}, b_{i}\right) \stackrel{\text { def }}{=} a_{j} \lesseqgtr a_{i} \text { and } b_{j} \lesseqgtr b_{i}
$$

二维偏序的一般解决方法是排序一维，用数据结构处理第二维（这种数据结构一般是树状数组）。

对于本题，我们可以创建一个数组 $nums$，其中 $nums[i]=(nums_1[i], nums_2[i])$，然后对 $nums$ 按照 $nums_1$ 从大到小的顺序排序，将查询 $queries$ 也按照 $x$ 从大到小的顺序排序。

接下来，遍历每个查询 $queries[i] = (x, y)$，对于当前查询，我们循环将 $nums$ 中所有大于等于 $x$ 的元素的 $nums_2$ 的值插入到树状数组中，树状数组维护的是离散化后的 $nums_2$ 的区间中 $nums_1 + nums_2$ 的最大值。那么我们只需要在树状数组中查询大于等于离散化后的 $y$ 区间对应的最大值即可。注意，由于树状数组维护的是前缀最大值，所以我们在实现上，可以将 $nums_2$ 反序插入到树状数组中。

时间复杂度 $O((n + m) \times \log n + m \times \log m)$，空间复杂度 $O(n + m)$。其中 $n$ 是数组 $nums$ 的长度，而 $m$ 是数组 $queries$ 的长度。

相似题目：

-   [2940. 找到 Alice 和 Bob 可以相遇的建筑](https://github.com/doocs/leetcode/blob/main/solution/2900-2999/2940.Find%20Building%20Where%20Alice%20and%20Bob%20Can%20Meet/README.md)

<!-- tabs:start -->

```python
class BinaryIndexedTree:
    __slots__ = ["n", "c"]

    def __init__(self, n: int):
        self.n = n
        self.c = [-1] * (n + 1)

    def update(self, x: int, v: int):
        while x <= self.n:
            self.c[x] = max(self.c[x], v)
            x += x & -x

    def query(self, x: int) -> int:
        mx = -1
        while x:
            mx = max(mx, self.c[x])
            x -= x & -x
        return mx


class Solution:
    def maximumSumQueries(
        self, nums1: List[int], nums2: List[int], queries: List[List[int]]
    ) -> List[int]:
        nums = sorted(zip(nums1, nums2), key=lambda x: -x[0])
        nums2.sort()
        n, m = len(nums1), len(queries)
        ans = [-1] * m
        j = 0
        tree = BinaryIndexedTree(n)
        for i in sorted(range(m), key=lambda i: -queries[i][0]):
            x, y = queries[i]
            while j < n and nums[j][0] >= x:
                k = n - bisect_left(nums2, nums[j][1])
                tree.update(k, nums[j][0] + nums[j][1])
                j += 1
            k = n - bisect_left(nums2, y)
            ans[i] = tree.query(k)
        return ans
```

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
        Arrays.fill(c, -1);
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] = Math.max(c[x], v);
            x += x & -x;
        }
    }

    public int query(int x) {
        int mx = -1;
        while (x > 0) {
            mx = Math.max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
}

class Solution {
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[][] nums = new int[n][0];
        for (int i = 0; i < n; ++i) {
            nums[i] = new int[] {nums1[i], nums2[i]};
        }
        Arrays.sort(nums, (a, b) -> b[0] - a[0]);
        Arrays.sort(nums2);
        int m = queries.length;
        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> queries[j][0] - queries[i][0]);
        int[] ans = new int[m];
        int j = 0;
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        for (int i : idx) {
            int x = queries[i][0], y = queries[i][1];
            for (; j < n && nums[j][0] >= x; ++j) {
                int k = n - Arrays.binarySearch(nums2, nums[j][1]);
                tree.update(k, nums[j][0] + nums[j][1]);
            }
            int p = Arrays.binarySearch(nums2, y);
            int k = p >= 0 ? n - p : n + p + 1;
            ans[i] = tree.query(k);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] q) {
        int n = nums1.length, m = q.length;
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            a[i][0] = nums1[i];
            a[i][1] = nums2[i];
        }
        int[][] b = new int[m][3];
        for (int i = 0; i < m; i++) {
            b[i][0] = q[i][0];
            b[i][1] = q[i][1];
            b[i][2] = i;
        }
        Arrays.sort(a, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(b, (o1, o2) -> o1[0] - o2[0]);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] res = new int[m];
        int max = -1;
        for (int i = m - 1, j = n - 1; i >= 0; i--) {
            int x = b[i][0], y = b[i][1], idx = b[i][2];
            while (j >= 0 && a[j][0] >= x) {
                if (max < a[j][1]) {
                    max = a[j][1];
                    Integer key = map.floorKey(a[j][1]);
                    while (key != null && map.get(key) <= a[j][0] + a[j][1]) {
                        map.remove(key);
                        key = map.floorKey(key);
                    }
                    map.put(max, a[j][0] + a[j][1]);
                }
                j--;
            }
            Integer key = map.ceilingKey(y);
            if (key == null)
                res[idx] = -1;
            else
                res[idx] = map.get(key);
        }
        return res;
    }
}
```

```cpp
class BinaryIndexedTree {
private:
    int n;
    vector<int> c;

public:
    BinaryIndexedTree(int n) {
        this->n = n;
        c.resize(n + 1, -1);
    }

    void update(int x, int v) {
        while (x <= n) {
            c[x] = max(c[x], v);
            x += x & -x;
        }
    }

    int query(int x) {
        int mx = -1;
        while (x > 0) {
            mx = max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
};

class Solution {
public:
    vector<int> maximumSumQueries(vector<int>& nums1, vector<int>& nums2, vector<vector<int>>& queries) {
        vector<pair<int, int>> nums;
        int n = nums1.size(), m = queries.size();
        for (int i = 0; i < n; ++i) {
            nums.emplace_back(-nums1[i], nums2[i]);
        }
        sort(nums.begin(), nums.end());
        sort(nums2.begin(), nums2.end());
        vector<int> idx(m);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) { return queries[j][0] < queries[i][0]; });
        vector<int> ans(m);
        int j = 0;
        BinaryIndexedTree tree(n);
        for (int i : idx) {
            int x = queries[i][0], y = queries[i][1];
            for (; j < n && -nums[j].first >= x; ++j) {
                int k = nums2.end() - lower_bound(nums2.begin(), nums2.end(), nums[j].second);
                tree.update(k, -nums[j].first + nums[j].second);
            }
            int k = nums2.end() - lower_bound(nums2.begin(), nums2.end(), y);
            ans[i] = tree.query(k);
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

func NewBinaryIndexedTree(n int) BinaryIndexedTree {
	c := make([]int, n+1)
	for i := range c {
		c[i] = -1
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
	mx := -1
	for x > 0 {
		mx = max(mx, bit.c[x])
		x -= x & -x
	}
	return mx
}

func maximumSumQueries(nums1 []int, nums2 []int, queries [][]int) []int {
	n, m := len(nums1), len(queries)
	nums := make([][2]int, n)
	for i := range nums {
		nums[i] = [2]int{nums1[i], nums2[i]}
	}
	sort.Slice(nums, func(i, j int) bool { return nums[j][0] < nums[i][0] })
	sort.Ints(nums2)
	idx := make([]int, m)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return queries[idx[j]][0] < queries[idx[i]][0] })
	tree := NewBinaryIndexedTree(n)
	ans := make([]int, m)
	j := 0
	for _, i := range idx {
		x, y := queries[i][0], queries[i][1]
		for ; j < n && nums[j][0] >= x; j++ {
			k := n - sort.SearchInts(nums2, nums[j][1])
			tree.update(k, nums[j][0]+nums[j][1])
		}
		k := n - sort.SearchInts(nums2, y)
		ans[i] = tree.query(k)
	}
	return ans
}
```

```ts
class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(-1);
    }

    update(x: number, v: number): void {
        while (x <= this.n) {
            this.c[x] = Math.max(this.c[x], v);
            x += x & -x;
        }
    }

    query(x: number): number {
        let mx = -1;
        while (x > 0) {
            mx = Math.max(mx, this.c[x]);
            x -= x & -x;
        }
        return mx;
    }
}

function maximumSumQueries(nums1: number[], nums2: number[], queries: number[][]): number[] {
    const n = nums1.length;
    const m = queries.length;
    const nums: [number, number][] = [];
    for (let i = 0; i < n; ++i) {
        nums.push([nums1[i], nums2[i]]);
    }
    nums.sort((a, b) => b[0] - a[0]);
    nums2.sort((a, b) => a - b);
    const idx: number[] = Array(m)
        .fill(0)
        .map((_, i) => i);
    idx.sort((i, j) => queries[j][0] - queries[i][0]);
    const ans: number[] = Array(m).fill(0);
    let j = 0;
    const search = (x: number) => {
        let [l, r] = [0, n];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums2[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const tree = new BinaryIndexedTree(n);
    for (const i of idx) {
        const [x, y] = queries[i];
        for (; j < n && nums[j][0] >= x; ++j) {
            const k = n - search(nums[j][1]);
            tree.update(k, nums[j][0] + nums[j][1]);
        }
        const k = n - search(y);
        ans[i] = tree.query(k);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
