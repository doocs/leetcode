# [2736. Maximum Sum Queries](https://leetcode.com/problems/maximum-sum-queries)

[中文文档](/solution/2700-2799/2736.Maximum%20Sum%20Queries/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code>, each of length <code>n</code>, and a <strong>1-indexed 2D array</strong> <code>queries</code> where <code>queries[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>.</p>

<p>For the <code>i<sup>th</sup></code> query, find the <strong>maximum value</strong> of <code>nums1[j] + nums2[j]</code> among all indices <code>j</code> <code>(0 &lt;= j &lt; n)</code>, where <code>nums1[j] &gt;= x<sub>i</sub></code> and <code>nums2[j] &gt;= y<sub>i</sub></code>, or <strong>-1</strong> if there is no <code>j</code> satisfying the constraints.</p>

<p>Return <em>an array </em><code>answer</code><em> where </em><code>answer[i]</code><em> is the answer to the </em><code>i<sup>th</sup></code><em> query.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [4,3,1,2], nums2 = [2,4,9,5], queries = [[4,1],[1,3],[2,5]]
<strong>Output:</strong> [6,10,7]
<strong>Explanation:</strong> 
For the 1st query <code node="[object Object]">x<sub>i</sub> = 4</code>&nbsp;and&nbsp;<code node="[object Object]">y<sub>i</sub> = 1</code>, we can select index&nbsp;<code node="[object Object]">j = 0</code>&nbsp;since&nbsp;<code node="[object Object]">nums1[j] &gt;= 4</code>&nbsp;and&nbsp;<code node="[object Object]">nums2[j] &gt;= 1</code>. The sum&nbsp;<code node="[object Object]">nums1[j] + nums2[j]</code>&nbsp;is 6, and we can show that 6 is the maximum we can obtain.

For the 2nd query <code node="[object Object]">x<sub>i</sub> = 1</code>&nbsp;and&nbsp;<code node="[object Object]">y<sub>i</sub> = 3</code>, we can select index&nbsp;<code node="[object Object]">j = 2</code>&nbsp;since&nbsp;<code node="[object Object]">nums1[j] &gt;= 1</code>&nbsp;and&nbsp;<code node="[object Object]">nums2[j] &gt;= 3</code>. The sum&nbsp;<code node="[object Object]">nums1[j] + nums2[j]</code>&nbsp;is 10, and we can show that 10 is the maximum we can obtain. 

For the 3rd query <code node="[object Object]">x<sub>i</sub> = 2</code>&nbsp;and&nbsp;<code node="[object Object]">y<sub>i</sub> = 5</code>, we can select index&nbsp;<code node="[object Object]">j = 3</code>&nbsp;since&nbsp;<code node="[object Object]">nums1[j] &gt;= 2</code>&nbsp;and&nbsp;<code node="[object Object]">nums2[j] &gt;= 5</code>. The sum&nbsp;<code node="[object Object]">nums1[j] + nums2[j]</code>&nbsp;is 7, and we can show that 7 is the maximum we can obtain.

Therefore, we return&nbsp;<code node="[object Object]">[6,10,7]</code>.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [3,2,5], nums2 = [2,3,4], queries = [[4,4],[3,2],[1,1]]
<strong>Output:</strong> [9,9,9]
<strong>Explanation:</strong> For this example, we can use index&nbsp;<code node="[object Object]">j = 2</code>&nbsp;for all the queries since it satisfies the constraints for each query.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [2,1], nums2 = [2,3], queries = [[3,3]]
<strong>Output:</strong> [-1]
<strong>Explanation:</strong> There is one query in this example with <code node="[object Object]">x<sub>i</sub></code> = 3 and <code node="[object Object]">y<sub>i</sub></code> = 3. For every index, j, either nums1[j] &lt; <code node="[object Object]">x<sub>i</sub></code> or nums2[j] &lt; <code node="[object Object]">y<sub>i</sub></code>. Hence, there is no solution. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

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

## Solutions

### Solution 1: Binary Indexed Tree

This problem belongs to the category of two-dimensional partial order problems.

A two-dimensional partial order problem is defined as follows: given several pairs of points $(a_1, b_1)$, $(a_2, b_2)$, ..., $(a_n, b_n)$, and a defined partial order relation, now given a point $(a_i, b_i)$, we need to find the number/maximum value of point pairs $(a_j, b_j)$ that satisfy the partial order relation. That is:

$$
\left(a_{j}, b_{j}\right) \prec\left(a_{i}, b_{i}\right) \stackrel{\text { def }}{=} a_{j} \lesseqgtr a_{i} \text { and } b_{j} \lesseqgtr b_{i}
$$

The general solution to two-dimensional partial order problems is to sort one dimension and use a data structure to handle the second dimension (this data structure is generally a binary indexed tree).

For this problem, we can create an array $nums$, where $nums[i]=(nums_1[i], nums_2[i])$, and then sort $nums$ in descending order according to $nums_1$. We also sort the queries $queries$ in descending order according to $x$.

Next, we iterate through each query $queries[i] = (x, y)$. For the current query, we loop to insert the value of $nums_2$ for all elements in $nums$ that are greater than or equal to $x$ into the binary indexed tree. The binary indexed tree maintains the maximum value of $nums_1 + nums_2$ in the discretized $nums_2$ interval. Therefore, we only need to query the maximum value corresponding to the interval greater than or equal to the discretized $y$ in the binary indexed tree. Note that since the binary indexed tree maintains the prefix maximum value, we can insert $nums_2$ in reverse order into the binary indexed tree in the implementation.

The time complexity is $O((n + m) \times \log n + m \times \log m)$, and the space complexity is $O(n + m)$. Here, $n$ is the length of the array $nums$, and $m$ is the length of the array $queries$.

Similar problems:

-   [2940. Find Building Where Alice and Bob Can Meet](/solution/2900-2999/2940.Find%20Building%20Where%20Alice%20and%20Bob%20Can%20Meet/README_EN.md)

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

### Solution 2

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
