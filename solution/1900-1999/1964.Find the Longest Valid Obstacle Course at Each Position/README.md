# [1964. 找出到每个位置为止最长的有效障碍赛跑路线](https://leetcode.cn/problems/find-the-longest-valid-obstacle-course-at-each-position)

[English Version](/solution/1900-1999/1964.Find%20the%20Longest%20Valid%20Obstacle%20Course%20at%20Each%20Position/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你打算构建一些障碍赛跑路线。给你一个 <strong>下标从 0 开始</strong> 的整数数组 <code>obstacles</code> ，数组长度为 <code>n</code> ，其中 <code>obstacles[i]</code> 表示第 <code>i</code> 个障碍的高度。</p>

<p>对于每个介于 <code>0</code> 和 <code>n - 1</code> 之间（包含 <code>0</code> 和 <code>n - 1</code>）的下标&nbsp; <code>i</code> ，在满足下述条件的前提下，请你找出&nbsp;<code>obstacles</code> 能构成的最长障碍路线的长度：</p>

<ul>
	<li>你可以选择下标介于 <code>0</code> 到 <code>i</code> 之间（包含 <code>0</code> 和 <code>i</code>）的任意个障碍。</li>
	<li>在这条路线中，必须包含第 <code>i</code> 个障碍。</li>
	<li>你必须按障碍在&nbsp;<code>obstacles</code>&nbsp;中的<strong> </strong><strong>出现顺序</strong> 布置这些障碍。</li>
	<li>除第一个障碍外，路线中每个障碍的高度都必须和前一个障碍 <strong>相同</strong> 或者 <strong>更高</strong> 。</li>
</ul>

<p>返回长度为 <code>n</code> 的答案数组 <code>ans</code> ，其中 <code>ans[i]</code> 是上面所述的下标 <code>i</code> 对应的最长障碍赛跑路线的长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>obstacles = [1,2,3,2]
<strong>输出：</strong>[1,2,3,3]
<strong>解释：</strong>每个位置的最长有效障碍路线是：
- i = 0: [<em><strong>1</strong></em>], [1] 长度为 1
- i = 1: [<em><strong>1</strong></em>,<em><strong>2</strong></em>], [1,2] 长度为 2
- i = 2: [<em><strong>1</strong></em>,<em><strong>2</strong></em>,<em><strong>3</strong></em>], [1,2,3] 长度为 3
- i = 3: [<em><strong>1</strong></em>,<em><strong>2</strong></em>,3,<em><strong>2</strong></em>], [1,2,2] 长度为 3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>obstacles = [2,2,1]
<strong>输出：</strong>[1,2,1]
<strong>解释：</strong>每个位置的最长有效障碍路线是：
- i = 0: [<em><strong>2</strong></em>], [2] 长度为 1
- i = 1: [<em><strong>2</strong></em>,<em><strong>2</strong></em>], [2,2] 长度为 2
- i = 2: [2,2,<em><strong>1</strong></em>], [1] 长度为 1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>obstacles = [3,1,5,6,4,2]
<strong>输出：</strong>[1,1,2,3,2,2]
<strong>解释：</strong>每个位置的最长有效障碍路线是：
- i = 0: [<em><strong>3</strong></em>], [3] 长度为 1
- i = 1: [3,<em><strong>1</strong></em>], [1] 长度为 1
- i = 2: [<em><strong>3</strong></em>,1,<em><strong>5</strong></em>], [3,5] 长度为 2, [1,5] 也是有效的障碍赛跑路线
- i = 3: [<em><strong>3</strong></em>,1,<em><strong>5</strong></em>,<em><strong>6</strong></em>], [3,5,6] 长度为 3, [1,5,6] 也是有效的障碍赛跑路线
- i = 4: [<em><strong>3</strong></em>,1,5,6,<em><strong>4</strong></em>], [3,4] 长度为 2, [1,4] 也是有效的障碍赛跑路线
- i = 5: [3,<em><strong>1</strong></em>,5,6,4,<em><strong>2</strong></em>], [1,2] 长度为 2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == obstacles.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= obstacles[i] &lt;= 10<sup>7</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：树状数组**

树状数组，也称作“二叉索引树”（Binary Indexed Tree）或 Fenwick 树。 它可以高效地实现如下两个操作：

1. **单点更新** `update(x, delta)`： 把序列 x 位置的数加上一个值 delta；
1. **前缀和查询** `query(x)`：查询序列 `[1,...x]` 区间的区间和，即位置 x 的前缀和。

这两个操作的时间复杂度均为 $O(\log n)$。当数的范围比较大时，需要进行离散化，即先进行去重并排序，然后对每个数字进行编号。

本题我们使用树状数组 `tree[x]` 来维护以 x 结尾的最长上升子序列的长度。

```python
def update(x, val):
    while x <= n:
        c[x] = max(c[x], val)
        x += lowbit(x)


def query(x):
    s = 0
    while x > 0:
        s = max(s, c[x])
        x -= lowbit(x)
    return s
```

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
