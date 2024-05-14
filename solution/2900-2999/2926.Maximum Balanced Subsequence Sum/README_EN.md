# [2926. Maximum Balanced Subsequence Sum](https://leetcode.com/problems/maximum-balanced-subsequence-sum)

[中文文档](/solution/2900-2999/2926.Maximum%20Balanced%20Subsequence%20Sum/README.md)

<!-- tags:Binary Indexed Tree,Segment Tree,Array,Binary Search,Dynamic Programming -->

<!-- difficulty:Hard -->

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>.</p>

<p>A <strong>subsequence</strong> of <code>nums</code> having length <code>k</code> and consisting of <strong>indices</strong> <code>i<sub>0</sub>&nbsp;&lt;&nbsp;i<sub>1</sub> &lt;&nbsp;... &lt; i<sub>k-1</sub></code> is <strong>balanced</strong> if the following holds:</p>

<ul>
	<li><code>nums[i<sub>j</sub>] - nums[i<sub>j-1</sub>] &gt;= i<sub>j</sub> - i<sub>j-1</sub></code>, for every <code>j</code> in the range <code>[1, k - 1]</code>.</li>
</ul>

<p>A <strong>subsequence</strong> of <code>nums</code> having length <code>1</code> is considered balanced.</p>

<p>Return <em>an integer denoting the <strong>maximum</strong> possible <strong>sum of elements</strong> in a <strong>balanced</strong> subsequence of </em><code>nums</code>.</p>

<p>A <strong>subsequence</strong> of an array is a new <strong>non-empty</strong> array that is formed from the original array by deleting some (<strong>possibly none</strong>) of the elements without disturbing the relative positions of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3,5,6]
<strong>Output:</strong> 14
<strong>Explanation:</strong> In this example, the subsequence [3,5,6] consisting of indices 0, 2, and 3 can be selected.
nums[2] - nums[0] &gt;= 2 - 0.
nums[3] - nums[2] &gt;= 3 - 2.
Hence, it is a balanced subsequence, and its sum is the maximum among the balanced subsequences of nums.
The subsequence consisting of indices 1, 2, and 3 is also valid.
It can be shown that it is not possible to get a balanced subsequence with a sum greater than 14.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,-1,-3,8]
<strong>Output:</strong> 13
<strong>Explanation:</strong> In this example, the subsequence [5,8] consisting of indices 0 and 3 can be selected.
nums[3] - nums[0] &gt;= 3 - 0.
Hence, it is a balanced subsequence, and its sum is the maximum among the balanced subsequences of nums.
It can be shown that it is not possible to get a balanced subsequence with a sum greater than 13.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,-1]
<strong>Output:</strong> -1
<strong>Explanation:</strong> In this example, the subsequence [-1] can be selected.
It is a balanced subsequence, and its sum is the maximum among the balanced subsequences of nums.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Dynamic Programming + Binary Indexed Tree

According to the problem description, we can transform the inequality $nums[i] - nums[j] \ge i - j$ into $nums[i] - i \ge nums[j] - j$. Therefore, we consider defining a new array $arr$, where $arr[i] = nums[i] - i$. A balanced subsequence satisfies that for any $j < i$, $arr[j] \le arr[i]$. The problem is transformed into selecting an increasing subsequence in $arr$ such that the corresponding sum in $nums$ is maximized.

Suppose $i$ is the index of the last element in the subsequence, then we consider the index $j$ of the second to last element in the subsequence. If $arr[j] \le arr[i]$, we can consider whether to add $j$ to the subsequence.

Therefore, we define $f[i]$ as the maximum sum of $nums$ when the index of the last element in the subsequence is $i$. The answer is $\max_{i=0}^{n-1} f[i]$.

The state transition equation is:

$$
f[i] = \max(\max_{j=0}^{i-1} f[j], 0) + nums[i]
$$

where $j$ satisfies $arr[j] \le arr[i]$.

We can use a Binary Indexed Tree to maintain the maximum value of the prefix, i.e., for each $arr[i]$, we maintain the maximum value of $f[i]$ in the prefix $arr[0..i]$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

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

<!-- end -->
