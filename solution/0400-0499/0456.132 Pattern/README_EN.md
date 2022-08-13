# [456. 132 Pattern](https://leetcode.com/problems/132-pattern)

[中文文档](/solution/0400-0499/0456.132%20Pattern/README.md)

## Description

<p>Given an array of <code>n</code> integers <code>nums</code>, a <strong>132 pattern</strong> is a subsequence of three integers <code>nums[i]</code>, <code>nums[j]</code> and <code>nums[k]</code> such that <code>i &lt; j &lt; k</code> and <code>nums[i] &lt; nums[k] &lt; nums[j]</code>.</p>

<p>Return <code>true</code><em> if there is a <strong>132 pattern</strong> in </em><code>nums</code><em>, otherwise, return </em><code>false</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no 132 pattern in the sequence.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,4,2]
<strong>Output:</strong> true
<strong>Explanation:</strong> There is a 132 pattern in the sequence: [1, 4, 2].
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,3,2,0]
<strong>Output:</strong> true
<strong>Explanation:</strong> There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def find132pattern(self, nums: List[int]) -> bool:
        ak = -inf
        stack = []
        for num in nums[::-1]:
            if num < ak:
                return True
            while stack and num > stack[-1]:
                ak = stack.pop()
            stack.append(num)
        return False
```

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
    def find132pattern(self, nums: List[int]) -> bool:
        s = sorted(set(nums))
        m = {v: i for i, v in enumerate(s, 1)}
        n = len(m)
        tree = BinaryIndexedTree(n)
        for v in nums:
            tree.update(m[v], 1)
        mi = nums[0]
        for v in nums:
            tree.update(m[v], -1)
            if tree.query(m[v] - 1) - tree.query(m[mi]) > 0:
                return True
            mi = min(mi, v)
        return False
```

### **Java**

```java
class Solution {
    public boolean find132pattern(int[] nums) {
        int ak = Integer.MIN_VALUE;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            if (nums[i] < ak) {
                return true;
            }
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                ak = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }
}
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

class Solution {
    public boolean find132pattern(int[] nums) {
        TreeSet<Integer> ts = new TreeSet();
        for (int v : nums) {
            ts.add(v);
        }
        int idx = 1;
        Map<Integer, Integer> m = new HashMap<>();
        for (int v : ts) {
            m.put(v, idx++);
        }
        int n = m.size();
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        for (int v : nums) {
            tree.update(m.get(v), 1);
        }
        int mi = nums[0];
        for (int v : nums) {
            int x = m.get(v);
            tree.update(x, -1);
            if (tree.query(x - 1) - tree.query(m.get(mi)) > 0) {
                return true;
            }
            mi = Math.min(mi, v);
        }
        return false;
    }
}
```

### **TypeScript**

```ts
function find132pattern(nums: number[]): boolean {
    const n = nums.length;
    if (n < 3) {
        return false;
    }
    let last = -Infinity;
    const stack = [];
    for (let i = n - 1; i >= 0; i--) {
        const num = nums[i];
        if (num < last) {
            return true;
        }
        while (stack[stack.length - 1] < num) {
            last = Math.max(last, stack.pop());
        }
        stack.push(num);
    }
    return false;
}
```

### **Rust**

```rust
impl Solution {
    pub fn find132pattern(nums: Vec<i32>) -> bool {
        let n = nums.len();
        if n < 3 {
            return false;
        }
        let mut last = i32::MIN;
        let mut stack = vec![];
        for i in (0..n).rev() {
            if nums[i] < last {
                return true;
            }
            while !stack.is_empty() && stack.last().unwrap() < &nums[i] {
                last = stack.pop().unwrap();
            }
            stack.push(nums[i])
        }
        false
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
    bool find132pattern(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        vector<int> alls(s.begin(), s.end());
        sort(alls.begin(), alls.end());
        unordered_map<int, int> m;
        int n = alls.size();
        for (int i = 0; i < n; ++i) m[alls[i]] = i + 1;
        BinaryIndexedTree* tree = new BinaryIndexedTree(n);
        for (int v : nums) tree->update(m[v], 1);
        int mi = nums[0];
        for (int v : nums) {
            tree->update(m[v], -1);
            if (tree->query(m[v] - 1) - tree->query(m[mi]) > 0) return true;
            mi = min(mi, v);
        }
        return false;
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

func find132pattern(nums []int) bool {
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
	tree := newBinaryIndexedTree(len(m))
	for _, v := range nums {
		tree.update(m[v], 1)
	}
	mi := nums[0]
	for _, v := range nums {
		tree.update(m[v], -1)
		if tree.query(m[v]-1)-tree.query(m[mi]) > 0 {
			return true
		}
		if v < mi {
			mi = v
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
