# [315. Count of Smaller Numbers After Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self)

[中文文档](/solution/0300-0399/0315.Count%20of%20Smaller%20Numbers%20After%20Self/README.md)

## Description

<p>You are given an integer array <code>nums</code> and you have to return a new <code>counts</code> array. The <code>counts</code> array has the property where <code>counts[i]</code> is the number of smaller elements to the right of <code>nums[i]</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,2,6,1]
<strong>Output:</strong> [2,1,1,0]
<strong>Explanation:</strong>
To the right of 5 there are <b>2</b> smaller elements (2 and 1).
To the right of 2 there is only <b>1</b> smaller element (1).
To the right of 6 there is <b>1</b> smaller element (1).
To the right of 1 there is <b>0</b> smaller element.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1]
<strong>Output:</strong> [0]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,-1]
<strong>Output:</strong> [0,0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
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
        while x > 0:
            s += self.c[x]
            x -= BinaryIndexedTree.lowbit(x)
        return s


class Solution:
    def countSmaller(self, nums: List[int]) -> List[int]:
        alls = sorted(set(nums))
        m = {v: i for i, v in enumerate(alls, 1)}
        tree = BinaryIndexedTree(len(m))
        ans = []
        for v in nums[::-1]:
            x = m[v]
            tree.update(x, 1)
            ans.append(tree.query(x - 1))
        return ans[::-1]
```

### **Java**

```java
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int v : nums) {
            s.add(v);
        }
        List<Integer> alls = new ArrayList<>(s);
        alls.sort(Comparator.comparingInt(a -> a));
        int n = alls.size();
        Map<Integer, Integer> m = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            m.put(alls.get(i), i + 1);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        LinkedList<Integer> ans = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            int x = m.get(nums[i]);
            tree.update(x, 1);
            ans.addFirst(tree.query(x - 1));
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
    vector<int> countSmaller(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        vector<int> alls(s.begin(), s.end());
        sort(alls.begin(), alls.end());
        unordered_map<int, int> m;
        int n = alls.size();
        for (int i = 0; i < n; ++i) m[alls[i]] = i + 1;
        BinaryIndexedTree* tree = new BinaryIndexedTree(n);
        vector<int> ans(nums.size());
        for (int i = nums.size() - 1; i >= 0; --i)
        {
            int x = m[nums[i]];
            tree->update(x, 1);
            ans[i] = tree->query(x - 1);
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

func countSmaller(nums []int) []int {
	s := make(map[int]bool)
	for _, v := range nums {
		s[v] = true
	}
	var alls []int
	for v := range s {
		alls = append(alls, v)
	}
	sort.Ints(alls)
	m := make(map[int]int)
	for i, v := range alls {
		m[v] = i + 1
	}
	ans := make([]int, len(nums))
	tree := newBinaryIndexedTree(len(alls))
	for i := len(nums) - 1; i >= 0; i-- {
		x := m[nums[i]]
		tree.update(x, 1)
		ans[i] = tree.query(x - 1)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
