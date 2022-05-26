# [370. Range Addition](https://leetcode.com/problems/range-addition)

[中文文档](/solution/0300-0399/0370.Range%20Addition/README.md)

## Description

<p>You are given an integer <code>length</code> and an array <code>updates</code> where <code>updates[i] = [startIdx<sub>i</sub>, endIdx<sub>i</sub>, inc<sub>i</sub>]</code>.</p>

<p>You have an array <code>arr</code> of length <code>length</code> with all zeros, and you have some operation to apply on <code>arr</code>. In the <code>i<sup>th</sup></code> operation, you should increment all the elements <code>arr[startIdx<sub>i</sub>], arr[startIdx<sub>i</sub> + 1], ..., arr[endIdx<sub>i</sub>]</code> by <code>inc<sub>i</sub></code>.</p>

<p>Return <code>arr</code> <em>after applying all the</em> <code>updates</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0370.Range%20Addition/images/rangeadd-grid.jpg" style="width: 413px; height: 573px;" />
<pre>
<strong>Input:</strong> length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
<strong>Output:</strong> [-2,0,3,5,3]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> length = 10, updates = [[2,4,6],[5,6,8],[1,9,-4]]
<strong>Output:</strong> [0,-4,2,2,2,4,4,-4,-4,-4]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= updates.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= startIdx<sub>i</sub> &lt;= endIdx<sub>i</sub> &lt; length</code></li>
	<li><code>-1000 &lt;= inc<sub>i</sub> &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getModifiedArray(self, length: int, updates: List[List[int]]) -> List[int]:
        delta = [0] * length
        for start, end, inc in updates:
            delta[start] += inc
            if end + 1 < length:
                delta[end + 1] -= inc
        return list(accumulate(delta))
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
    def getModifiedArray(self, length: int, updates: List[List[int]]) -> List[int]:
        tree = BinaryIndexedTree(length)
        for start, end, inc in updates:
            tree.update(start + 1, inc)
            tree.update(end + 2, -inc)
        return [tree.query(i + 1) for i in range(length)]
```

### **Java**

```java
class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] delta = new int[length];
        for (int[] e : updates) {
            delta[e[0]] += e[2];
            if (e[1] + 1 < length) {
                delta[e[1] + 1] -= e[2];
            }
        }
        for (int i = 1; i < length; ++i) {
            delta[i] += delta[i - 1];
        }
        return delta;
    }
}
```

```java
class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        BinaryIndexedTree tree = new BinaryIndexedTree(length);
        for (int[] e : updates) {
            int start = e[0], end = e[1], inc = e[2];
            tree.update(start + 1, inc);
            tree.update(end + 2, -inc);
        }
        int[] ans = new int[length];
        for (int i = 0; i < length; ++i) {
            ans[i] = tree.query(i + 1);
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
class Solution {
public:
    vector<int> getModifiedArray(int length, vector<vector<int>>& updates) {
        vector<int> delta(length);
        for (auto e : updates) {
            delta[e[0]] += e[2];
            if (e[1] + 1 < length) delta[e[1] + 1] -= e[2];
        }
        for (int i = 1; i < length; ++i) delta[i] += delta[i - 1];
        return delta;
    }
};
```

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
    vector<int> getModifiedArray(int length, vector<vector<int>>& updates) {
        BinaryIndexedTree* tree = new BinaryIndexedTree(length);
        for (auto& e : updates)
        {
            int start = e[0], end = e[1], inc = e[2];
            tree->update(start + 1, inc);
            tree->update(end + 2, -inc);
        }
        vector<int> ans;
        for (int i = 0; i < length; ++i) ans.push_back(tree->query(i + 1));
        return ans;
    }
};
```

### **Go**

```go
func getModifiedArray(length int, updates [][]int) []int {
	delta := make([]int, length)
	for _, e := range updates {
		delta[e[0]] += e[2]
		if e[1]+1 < length {
			delta[e[1]+1] -= e[2]
		}
	}
	for i := 1; i < length; i++ {
		delta[i] += delta[i-1]
	}
	return delta
}
```

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

func getModifiedArray(length int, updates [][]int) []int {
	tree := newBinaryIndexedTree(length)
	for _, e := range updates {
		start, end, inc := e[0], e[1], e[2]
		tree.update(start+1, inc)
		tree.update(end+2, -inc)
	}
	ans := make([]int, length)
	for i := range ans {
		ans[i] = tree.query(i + 1)
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number} length
 * @param {number[][]} updates
 * @return {number[]}
 */
var getModifiedArray = function (length, updates) {
    let delta = new Array(length).fill(0);
    for (let [start, end, inc] of updates) {
        delta[start] += inc;
        if (end + 1 < length) {
            delta[end + 1] -= inc;
        }
    }
    for (let i = 1; i < length; ++i) {
        delta[i] += delta[i - 1];
    }
    return delta;
};
```

### **...**

```

```

<!-- tabs:end -->
