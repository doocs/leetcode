# [1713. Minimum Operations to Make a Subsequence](https://leetcode.com/problems/minimum-operations-to-make-a-subsequence)

[中文文档](/solution/1700-1799/1713.Minimum%20Operations%20to%20Make%20a%20Subsequence/README.md)

## Description

<p>You are given an array <code>target</code> that consists of <strong>distinct</strong> integers and another integer array <code>arr</code> that <strong>can</strong> have duplicates.</p>

<p>In one operation, you can insert any integer at any position in <code>arr</code>. For example, if <code>arr = [1,4,1,2]</code>, you can add <code>3</code> in the middle and make it <code>[1,4,<u>3</u>,1,2]</code>. Note that you can insert the integer at the very beginning or end of the array.</p>

<p>Return <em>the <strong>minimum</strong> number of operations needed to make </em><code>target</code><em> a <strong>subsequence</strong> of </em><code>arr</code><em>.</em></p>

<p>A <strong>subsequence</strong> of an array is a new array generated from the original array by deleting some elements (possibly none) without changing the remaining elements&#39; relative order. For example, <code>[2,7,4]</code> is a subsequence of <code>[4,<u>2</u>,3,<u>7</u>,2,1,<u>4</u>]</code> (the underlined elements), while <code>[2,4,2]</code> is not.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = [5,1,3], <code>arr</code> = [9,4,2,3,4]
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can add 5 and 1 in such a way that makes <code>arr</code> = [<u>5</u>,9,4,<u>1</u>,2,3,4], then target will be a subsequence of <code>arr</code>.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = [6,4,8,1,3,2], <code>arr</code> = [4,7,6,2,3,8,6,1]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target.length, arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= target[i], arr[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>target</code> contains no duplicates.</li>
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

    def update(self, x, val):
        while x <= self.n:
            self.c[x] = max(self.c[x], val)
            x += BinaryIndexedTree.lowbit(x)

    def query(self, x):
        s = 0
        while x:
            s = max(s, self.c[x])
            x -= BinaryIndexedTree.lowbit(x)
        return s


class Solution:
    def minOperations(self, target: List[int], arr: List[int]) -> int:
        d = {v: i for i, v in enumerate(target)}
        nums = [d[v] for v in arr if v in d]
        return len(target) - self.lengthOfLIS(nums)

    def lengthOfLIS(self, nums):
        s = sorted(set(nums))
        m = {v: i for i, v in enumerate(s, 1)}
        tree = BinaryIndexedTree(len(m))
        ans = 0
        for v in nums:
            x = m[v]
            t = tree.query(x - 1) + 1
            ans = max(ans, t)
            tree.update(x, t)
        return ans
```

### **Java**

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public static int lowbit(int x) {
        return x & -x;
    }

    public void update(int x, int val) {
        while (x <= n) {
            c[x] = Math.max(c[x], val);
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s = Math.max(s, c[x]);
            x -= lowbit(x);
        }
        return s;
    }
}

class Solution {
    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0; i < target.length; ++i) {
            d.put(target[i], i);
        }
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < arr.length; ++i) {
            if (d.containsKey(arr[i])) {
                nums.add(d.get(arr[i]));
            }
        }
        return target.length - lengthOfLIS(nums);
    }

    private int lengthOfLIS(List<Integer> nums) {
        TreeSet<Integer> ts = new TreeSet();
        for (int v : nums) {
            ts.add(v);
        }
        int idx = 1;
        Map<Integer, Integer> d = new HashMap<>();
        for (int v : ts) {
            d.put(v, idx++);
        }
        int ans = 0;
        BinaryIndexedTree tree = new BinaryIndexedTree(nums.size());
        for (int v : nums) {
            int x = d.get(v);
            int t = tree.query(x - 1) + 1;
            ans = Math.max(ans, t);
            tree.update(x, t);
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

    BinaryIndexedTree(int _n): n(_n), c(_n + 1){}

    void update(int x, int val) {
        while (x <= n)
        {
            c[x] = max(c[x], val);
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0)
        {
            s = max(s, c[x]);
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
    int minOperations(vector<int>& target, vector<int>& arr) {
        unordered_map<int, int> d;
        for (int i = 0; i < target.size(); ++i) d[target[i]] = i;
        vector<int> nums;
        for (int i = 0; i < arr.size(); ++i) {
            if (d.count(arr[i])) {
                nums.push_back(d[arr[i]]);
            }
        }
        return target.size() - lengthOfLIS(nums);
    }

    int lengthOfLIS(vector<int>& nums) {
        set<int> s(nums.begin(), nums.end());
        int idx = 1;
        unordered_map<int, int> d;
        for (int v : s) d[v] = idx++;
        BinaryIndexedTree* tree = new BinaryIndexedTree(d.size());
        int ans = 0;
        for (int v : nums) {
            int x = d[v];
            int t = tree->query(x - 1) + 1;
            ans = max(ans, t);
            tree->update(x, t);
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

func (this *BinaryIndexedTree) update(x, val int) {
	for x <= this.n {
		if this.c[x] < val {
			this.c[x] = val
		}
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		if s < this.c[x] {
			s = this.c[x]
		}
		x -= this.lowbit(x)
	}
	return s
}

func minOperations(target []int, arr []int) int {
	d := map[int]int{}
	for i, v := range target {
		d[v] = i
	}
	nums := []int{}
	for _, v := range arr {
		if i, ok := d[v]; ok {
			nums = append(nums, i)
		}
	}
	return len(target) - lengthOfLIS(nums)
}

func lengthOfLIS(nums []int) int {
	s := map[int]bool{}
	for _, v := range nums {
		s[v] = true
	}
	t := []int{}
	for v := range s {
		t = append(t, v)
	}
	sort.Ints(t)
	d := map[int]int{}
	for i, v := range t {
		d[v] = i + 1
	}
	tree := newBinaryIndexedTree(len(d))
	ans := 0
	for _, v := range nums {
		x := d[v]
		t := tree.query(x-1) + 1
		ans = max(ans, t)
		tree.update(x, t)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
