# [2031. Count Subarrays With More Ones Than Zeros 🔒](https://leetcode.com/problems/count-subarrays-with-more-ones-than-zeros)

[中文文档](/solution/2000-2099/2031.Count%20Subarrays%20With%20More%20Ones%20Than%20Zeros/README.md)

<!-- tags:Binary Indexed Tree,Segment Tree,Array,Binary Search,Divide and Conquer,Ordered Set,Merge Sort -->

<!-- difficulty:Medium -->

## Description

<p>You are given a binary array <code>nums</code> containing only the integers <code>0</code> and <code>1</code>. Return<em> the number of <strong>subarrays</strong> in nums that have <strong>more</strong> </em><code>1</code>&#39;<em>s than </em><code>0</code><em>&#39;s. Since the answer may be very large, return it <strong>modulo</strong> </em><code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>subarray</strong> is a contiguous sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,1,0,1]
<strong>Output:</strong> 9
<strong>Explanation:</strong>
The subarrays of size 1 that have more ones than zeros are: [1], [1], [1]
The subarrays of size 2 that have more ones than zeros are: [1,1]
The subarrays of size 3 that have more ones than zeros are: [0,1,1], [1,1,0], [1,0,1]
The subarrays of size 4 that have more ones than zeros are: [1,1,0,1]
The subarrays of size 5 that have more ones than zeros are: [0,1,1,0,1]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
No subarrays have more ones than zeros.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
The subarrays of size 1 that have more ones than zeros are: [1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1</code></li>
</ul>

## Solutions

### Solution 1: Prefix Sum + Binary Indexed Tree

The problem requires us to count the number of subarrays where the count of $1$ is greater than the count of $0$. If we treat $0$ in the array as $-1$, then the problem becomes counting the number of subarrays where the sum of elements is greater than $0$.

To calculate the sum of elements in a subarray, we can use the prefix sum. To count the number of subarrays where the sum of elements is greater than $0$, we can use a binary indexed tree to maintain the occurrence count of each prefix sum. Initially, the occurrence count of the prefix sum $0$ is $1$.

Next, we traverse the array $nums$, use variable $s$ to record the current prefix sum, and use variable $ans$ to record the answer. For each position $i$, we update the prefix sum $s$, then query the occurrence count of the prefix sum in the range $[0, s)$ in the binary indexed tree, add it to $ans$, and then update the occurrence count of $s$ in the binary indexed tree.

Finally, return $ans$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

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

### Solution 2

<!-- tabs:start -->

```python
from sortedcontainers import SortedList


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

<!-- end -->
