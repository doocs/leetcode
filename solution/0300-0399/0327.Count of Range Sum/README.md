# [327. 区间和的个数](https://leetcode-cn.com/problems/count-of-range-sum)

[English Version](/solution/0300-0399/0327.Count%20of%20Range%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组 <code>nums</code> 。区间和 <code>S(i, j)</code> 表示在 <code>nums</code> 中，位置从 <code>i</code> 到 <code>j</code> 的元素之和，包含 <code>i</code> 和 <code>j</code> (<code>i</code> ≤ <code>j</code>)。</p>

<p>请你以下标 <code>i</code> （<code>0 <= i <= nums.length</code> ）为起点，元素个数逐次递增，计算子数组内的元素和。</p>

<p>当元素和落在范围 <code>[lower, upper]</code> （包含 <code>lower</code> 和 <code>upper</code>）之内时，记录子数组当前最末元素下标 <code>j</code> ，记作 <strong>有效</strong> 区间和 <code>S(i, j)</code> 。</p>

<p>求数组中，值位于范围 <code>[lower, upper]</code> （包含 <code>lower</code> 和 <code>upper</code>）之内的 <strong>有效</strong> 区间和的个数。</p>

<p> </p>

<p><strong>注意：</strong><br />
最直观的算法复杂度是 <em>O</em>(<em>n</em><sup>2</sup>) ，请在此基础上优化你的算法。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong><em>nums</em> = <code>[-2,5,-1]</code>, <em>lower</em> = <code>-2</code>, <em>upper</em> = <code>2</code>,
<strong>输出：</strong>3 
<strong>解释：</strong>
下标 i = 0 时，子数组 [-2]、[-2,5]、[-2,5,-1]，对应元素和分别为 -2、3、2 ；其中 -2 和 2 落在范围 [lower = -2, upper = 2] 之间，因此记录有效区间和 S(0,0)，S(0,2) 。
下标 i = 1 时，子数组 [5]、[5,-1] ，元素和 5、4 ；没有满足题意的有效区间和。
下标 i = 2 时，子数组 [-1] ，元素和 -1 ；记录有效区间和 S(2,2) 。
故，共有 3 个有效区间和。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= nums.length <= 10^4</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

树状数组。

树状数组，也称作“二叉索引树”（Binary Indexed Tree）或 Fenwick 树。 它可以高效地实现如下两个操作：

1. **单点更新** `update(x, delta)`： 把序列 x 位置的数加上一个值 delta；
1. **前缀和查询** `query(x)`：查询序列 `[1,...x]` 区间的区间和，即位置 x 的前缀和。

这两个操作的时间复杂度均为 `O(log n)`。

本题中，对于每个下标 j，以 j 为右端点的下标对的数量，就等于 `preSum[1..j]` 中的所有整数，出现在区间 `[preSum[j] - upper, preSum[j] - lower]` 的次数。我们可以用树状数组，从左到右扫描前缀和数组，每遇到一个前缀和 s，就在树状数组中查询区间 `[preSum[j] - upper, preSum[j] - lower]` 内的整数的数量，随后将 s 更新至树状数组。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
