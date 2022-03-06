# [327. Count of Range Sum](https://leetcode.com/problems/count-of-range-sum)

[中文文档](/solution/0300-0399/0327.Count%20of%20Range%20Sum/README.md)

## Description

<p>Given an integer array <code>nums</code> and two integers <code>lower</code> and <code>upper</code>, return <em>the number of range sums that lie in</em> <code>[lower, upper]</code> <em>inclusive</em>.</p>

<p>Range sum <code>S(i, j)</code> is defined as the sum of the elements in <code>nums</code> between indices <code>i</code> and <code>j</code> inclusive, where <code>i &lt;= j</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,5,-1], lower = -2, upper = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> The three ranges are: [0,0], [2,2], and [0,2] and their respective sums are: -2, -1, 2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0], lower = 0, upper = 0
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>-3 * 10<sup>4</sup> &lt;= lower &lt;= upper &lt;= 3 * 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> A naive algorithm of <code>O(n<sup>2</sup>)</code> is trivial, Could you do better than that?

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
        while x > 0:
            s += self.c[x]
            x -= BinaryIndexedTree.lowbit(x)
        return s


class Solution:
    def countRangeSum(self, nums: List[int], lower: int, upper: int) -> int:
        presum = [0]
        for v in nums:
            presum.append(presum[-1] + v)
        alls = set()
        for s in presum:
            alls.add(s)
            alls.add(s - lower)
            alls.add(s - upper)
        alls = sorted(alls)
        m = {v: i for i, v in enumerate(alls, 1)}
        tree = BinaryIndexedTree(len(m))
        ans = 0
        for s in presum:
            i, j = m[s - upper], m[s - lower]
            ans += tree.query(j) - tree.query(i - 1)
            tree.update(m[s], 1)
        return ans
```

### **Java**

```java
class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        TreeSet<Long> ts = new TreeSet<>();
        for (long s : preSum) {
            ts.add(s);
            ts.add(s - upper);
            ts.add(s - lower);
        }
        Map<Long, Integer> m = new HashMap<>();
        int idx = 1;
        for (long s : ts) {
            m.put(s, idx++);
        }
        int ans = 0;
        BinaryIndexedTree tree = new BinaryIndexedTree(m.size());
        for (long s : preSum) {
            int i = m.get(s - upper);
            int j = m.get(s - lower);
            ans += tree.query(j) - tree.query(i - 1);
            tree.update(m.get(s), 1);
        }
        return ans;
    }
}

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

    public static int lowbit(int x) {
        return x & -x;
    }
}
```

### **C++**

```cpp
class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n): n(_n), c(_n + 1){}

    void update(int x, int delta) {
        while (x <= n)
        {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0)
        {
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
    int countRangeSum(vector<int>& nums, int lower, int upper) {
        int n = nums.size();
        vector<long long> preSum(n + 1);
        for (int i = 0; i < n; ++i) preSum[i + 1] = preSum[i] + nums[i];
        set<long long> alls;
        for (auto& s : preSum)
        {
            alls.insert(s);
            alls.insert(s - upper);
            alls.insert(s - lower);
        }
        unordered_map<long long, int> m;
        int idx = 1;
        for (auto& v : alls) m[v] = idx++;
        BinaryIndexedTree* tree = new BinaryIndexedTree(m.size());
        int ans = 0;
        for (auto& s : preSum)
        {
            int i = m[s - upper], j = m[s - lower];
            ans += tree->query(j) - tree->query(i - 1);
            tree->update(m[s], 1);
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

func countRangeSum(nums []int, lower int, upper int) int {
	n := len(nums)
	presum := make([]int, n+1)
	for i, v := range nums {
		presum[i+1] = presum[i] + v
	}
	alls := make(map[int]bool)
	for _, s := range presum {
		alls[s] = true
		alls[s-upper] = true
		alls[s-lower] = true
	}
	var t []int
	for s, _ := range alls {
		t = append(t, s)
	}
	sort.Ints(t)
	m := make(map[int]int)
	for i, v := range t {
		m[v] = i + 1
	}
	ans := 0
	tree := newBinaryIndexedTree(len(alls))
	for _, s := range presum {
		i, j := m[s-upper], m[s-lower]
		ans += tree.query(j) - tree.query(i-1)
		tree.update(m[s], 1)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
