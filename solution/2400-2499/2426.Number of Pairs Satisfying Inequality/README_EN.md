# [2426. Number of Pairs Satisfying Inequality](https://leetcode.com/problems/number-of-pairs-satisfying-inequality)

[中文文档](/solution/2400-2499/2426.Number%20of%20Pairs%20Satisfying%20Inequality/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code>, each of size <code>n</code>, and an integer <code>diff</code>. Find the number of <strong>pairs</strong> <code>(i, j)</code> such that:</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt;= n - 1</code> <strong>and</strong></li>
	<li><code>nums1[i] - nums1[j] &lt;= nums2[i] - nums2[j] + diff</code>.</li>
</ul>

<p>Return<em> the <strong>number of pairs</strong> that satisfy the conditions.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [3,2,5], nums2 = [2,2,1], diff = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong>
There are 3 pairs that satisfy the conditions:
1. i = 0, j = 1: 3 - 2 &lt;= 2 - 2 + 1. Since i &lt; j and 1 &lt;= 1, this pair satisfies the conditions.
2. i = 0, j = 2: 3 - 5 &lt;= 2 - 1 + 1. Since i &lt; j and -2 &lt;= 2, this pair satisfies the conditions.
3. i = 1, j = 2: 2 - 5 &lt;= 2 - 1 + 1. Since i &lt; j and -3 &lt;= 2, this pair satisfies the conditions.
Therefore, we return 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [3,-1], nums2 = [-2,2], diff = -1
<strong>Output:</strong> 0
<strong>Explanation:</strong>
Since there does not exist any pair that satisfies the conditions, we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= diff &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    @staticmethod
    def lowbit(x):
        return x & -x

    def update(self, x, delta):
        while x <= self.n:
            self.c[x] += delta
            x += BinaryIndexedTree.lowbit(x)

    def query(self, x):
        s = 0
        while x:
            s += self.c[x]
            x -= BinaryIndexedTree.lowbit(x)
        return s


class Solution:
    def numberOfPairs(self, nums1: List[int], nums2: List[int], diff: int) -> int:
        tree = BinaryIndexedTree(10**5)
        ans = 0
        for a, b in zip(nums1, nums2):
            v = a - b
            ans += tree.query(v + diff + 40000)
            tree.update(v + 40000, 1)
        return ans
```

### **Java**

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public static final int lowbit(int x) {
        return x & -x;
    }

    public void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }
}

class Solution {
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        BinaryIndexedTree tree = new BinaryIndexedTree(100000);
        long ans = 0;
        for (int i = 0; i < nums1.length; ++i) {
            int v = nums1[i] - nums2[i];
            ans += tree.query(v + diff + 40000);
            tree.update(v + 40000, 1);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) { }

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    int lowbit(int x) {
        return x & -x;
    }
};

class Solution {
public:
    long long numberOfPairs(vector<int>& nums1, vector<int>& nums2, int diff) {
        BinaryIndexedTree* tree = new BinaryIndexedTree(1e5);
        long long ans = 0;
        for (int i = 0; i < nums1.size(); ++i) {
            int v = nums1[i] - nums2[i];
            ans += tree->query(v + diff + 40000);
            tree->update(v + 40000, 1);
        }
        return ans;
    }
};
```

### **Go**

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) lowbit(x int) int {
	return x & -x
}

func (this *BinaryIndexedTree) update(x, delta int) {
	for x <= this.n {
		this.c[x] += delta
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		s += this.c[x]
		x -= this.lowbit(x)
	}
	return s
}

func numberOfPairs(nums1 []int, nums2 []int, diff int) int64 {
	tree := newBinaryIndexedTree(100000)
	ans := 0
	for i := range nums1 {
		v := nums1[i] - nums2[i]
		ans += tree.query(v + diff + 40000)
		tree.update(v+40000, 1)
	}
	return int64(ans)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
