# [1964. Find the Longest Valid Obstacle Course at Each Position](https://leetcode.com/problems/find-the-longest-valid-obstacle-course-at-each-position)

[中文文档](/solution/1900-1999/1964.Find%20the%20Longest%20Valid%20Obstacle%20Course%20at%20Each%20Position/README.md)

## Description

<p>You want to build some obstacle courses. You are given a <strong>0-indexed</strong> integer array <code>obstacles</code> of length <code>n</code>, where <code>obstacles[i]</code> describes the height of the <code>i<sup>th</sup></code> obstacle.</p>

<p>For every index <code>i</code> between <code>0</code> and <code>n - 1</code> (<strong>inclusive</strong>), find the length of the <strong>longest obstacle course</strong> in <code>obstacles</code> such that:</p>

<ul>
	<li>You choose any number of obstacles between <code>0</code> and <code>i</code> <strong>inclusive</strong>.</li>
	<li>You must include the <code>i<sup>th</sup></code> obstacle in the course.</li>
	<li>You must put the chosen obstacles in the <strong>same order</strong> as they appear in <code>obstacles</code>.</li>
	<li>Every obstacle (except the first) is <strong>taller</strong> than or the <strong>same height</strong> as the obstacle immediately before it.</li>
</ul>

<p>Return <em>an array</em> <code>ans</code> <em>of length</em> <code>n</code>, <em>where</em> <code>ans[i]</code> <em>is the length of the <strong>longest obstacle course</strong> for index</em> <code>i</code><em> as described above</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> obstacles = [1,2,3,2]
<strong>Output:</strong> [1,2,3,3]
<strong>Explanation:</strong> The longest valid obstacle course at each position is:
- i = 0: [<u>1</u>], [1] has length 1.
- i = 1: [<u>1</u>,<u>2</u>], [1,2] has length 2.
- i = 2: [<u>1</u>,<u>2</u>,<u>3</u>], [1,2,3] has length 3.
- i = 3: [<u>1</u>,<u>2</u>,3,<u>2</u>], [1,2,2] has length 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> obstacles = [2,2,1]
<strong>Output:</strong> [1,2,1]
<strong>Explanation: </strong>The longest valid obstacle course at each position is:
- i = 0: [<u>2</u>], [2] has length 1.
- i = 1: [<u>2</u>,<u>2</u>], [2,2] has length 2.
- i = 2: [2,2,<u>1</u>], [1] has length 1.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> obstacles = [3,1,5,6,4,2]
<strong>Output:</strong> [1,1,2,3,2,2]
<strong>Explanation:</strong> The longest valid obstacle course at each position is:
- i = 0: [<u>3</u>], [3] has length 1.
- i = 1: [3,<u>1</u>], [1] has length 1.
- i = 2: [<u>3</u>,1,<u>5</u>], [3,5] has length 2. [1,5] is also valid.
- i = 3: [<u>3</u>,1,<u>5</u>,<u>6</u>], [3,5,6] has length 3. [1,5,6] is also valid.
- i = 4: [<u>3</u>,1,5,6,<u>4</u>], [3,4] has length 2. [1,4] is also valid.
- i = 5: [3,<u>1</u>,5,6,4,<u>2</u>], [1,2] has length 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == obstacles.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= obstacles[i] &lt;= 10<sup>7</sup></code></li>
</ul>

## Solutions

Binary Indexed Tree.

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
        while x > 0:
            s = max(s, self.c[x])
            x -= BinaryIndexedTree.lowbit(x)
        return s


class Solution:
    def longestObstacleCourseAtEachPosition(self, obstacles: List[int]) -> List[int]:
        s = sorted(set(obstacles))
        m = {v: i for i, v in enumerate(s, 1)}
        tree = BinaryIndexedTree(len(m))
        ans = []
        for v in obstacles:
            x = m[v]
            ans.append(1 + tree.query(x))
            tree.update(x, ans[-1])
        return ans
```

### **Java**

```java
class Solution {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        TreeSet<Integer> ts = new TreeSet();
        for (int v : obstacles) {
            ts.add(v);
        }
        int idx = 1;
        Map<Integer, Integer> m = new HashMap<>();
        for (int v : ts) {
            m.put(v, idx++);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m.size());
        int n = obstacles.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int v = obstacles[i];
            int x = m.get(v);
            ans[i] = tree.query(x) + 1;
            tree.update(x, ans[i]);
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

    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) { }

    void update(int x, int val) {
        while (x <= n) {
            c[x] = max(c[x], val);
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
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
    vector<int> longestObstacleCourseAtEachPosition(vector<int>& obstacles) {
        set<int> s(obstacles.begin(), obstacles.end());
        int idx = 1;
        unordered_map<int, int> m;
        for (int v : s) m[v] = idx++;
        BinaryIndexedTree* tree = new BinaryIndexedTree(m.size());
        int n = obstacles.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            int v = obstacles[i];
            int x = m[v];
            ans[i] = 1 + tree->query(x);
            tree->update(x, ans[i]);
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

func longestObstacleCourseAtEachPosition(obstacles []int) []int {
	s := make(map[int]bool)
	for _, v := range obstacles {
		s[v] = true
	}
	var t []int
	for v, _ := range s {
		t = append(t, v)
	}
	sort.Ints(t)
	m := make(map[int]int)
	for i, v := range t {
		m[v] = i + 1
	}
	n := len(obstacles)
	ans := make([]int, n)
	tree := newBinaryIndexedTree(len(m))
	for i, v := range obstacles {
		x := m[v]
		ans[i] = 1 + tree.query(x)
		tree.update(x, ans[i])
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
