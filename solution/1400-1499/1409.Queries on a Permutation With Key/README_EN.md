---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1409.Queries%20on%20a%20Permutation%20With%20Key/README_EN.md
rating: 1334
source: Weekly Contest 184 Q2
tags:
    - Binary Indexed Tree
    - Array
    - Simulation
---

<!-- problem:start -->

# [1409. Queries on a Permutation With Key](https://leetcode.com/problems/queries-on-a-permutation-with-key)

[中文文档](/solution/1400-1499/1409.Queries%20on%20a%20Permutation%20With%20Key/README.md)

## Description

<!-- description:start -->

<p>Given the array <code>queries</code> of positive integers between <code>1</code> and <code>m</code>, you have to process all <code>queries[i]</code> (from <code>i=0</code> to <code>i=queries.length-1</code>) according to the following rules:</p>

<ul>
	<li>In the beginning, you have the permutation <code>P=[1,2,3,...,m]</code>.</li>
	<li>For the current <code>i</code>, find the position of <code>queries[i]</code> in the permutation <code>P</code> (<strong>indexing from 0</strong>) and then move this at the beginning of the permutation <code>P</code>. Notice that the position of <code>queries[i]</code> in <code>P</code> is the result for <code>queries[i]</code>.</li>
</ul>

<p>Return an array containing the result for the given <code>queries</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> queries = [3,1,2,1], m = 5
<strong>Output:</strong> [2,1,2,1] 
<strong>Explanation:</strong> The queries are processed as follow: 
For i=0: queries[i]=3, P=[1,2,3,4,5], position of 3 in P is <strong>2</strong>, then we move 3 to the beginning of P resulting in P=[3,1,2,4,5]. 
For i=1: queries[i]=1, P=[3,1,2,4,5], position of 1 in P is <strong>1</strong>, then we move 1 to the beginning of P resulting in P=[1,3,2,4,5]. 
For i=2: queries[i]=2, P=[1,3,2,4,5], position of 2 in P is <strong>2</strong>, then we move 2 to the beginning of P resulting in P=[2,1,3,4,5]. 
For i=3: queries[i]=1, P=[2,1,3,4,5], position of 1 in P is <strong>1</strong>, then we move 1 to the beginning of P resulting in P=[1,2,3,4,5]. 
Therefore, the array containing the result is [2,1,2,1].  
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> queries = [4,1,2,2], m = 4
<strong>Output:</strong> [3,1,2,0]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> queries = [7,5,5,8,3], m = 8
<strong>Output:</strong> [6,5,0,7,5]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m &lt;= 10^3</code></li>
	<li><code>1 &lt;= queries.length &lt;= m</code></li>
	<li><code>1 &lt;= queries[i] &lt;= m</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

The problem's data scale is not large, so we can directly simulate it.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def processQueries(self, queries: List[int], m: int) -> List[int]:
        p = list(range(1, m + 1))
        ans = []
        for v in queries:
            j = p.index(v)
            ans.append(j)
            p.pop(j)
            p.insert(0, v)
        return ans
```

#### Java

```java
class Solution {
    public int[] processQueries(int[] queries, int m) {
        List<Integer> p = new LinkedList<>();
        for (int i = 1; i <= m; ++i) {
            p.add(i);
        }
        int[] ans = new int[queries.length];
        int i = 0;
        for (int v : queries) {
            int j = p.indexOf(v);
            ans[i++] = j;
            p.remove(j);
            p.add(0, v);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> processQueries(vector<int>& queries, int m) {
        vector<int> p(m);
        iota(p.begin(), p.end(), 1);
        vector<int> ans;
        for (int v : queries) {
            int j = 0;
            for (int i = 0; i < m; ++i) {
                if (p[i] == v) {
                    j = i;
                    break;
                }
            }
            ans.push_back(j);
            p.erase(p.begin() + j);
            p.insert(p.begin(), v);
        }
        return ans;
    }
};
```

#### Go

```go
func processQueries(queries []int, m int) []int {
	p := make([]int, m)
	for i := range p {
		p[i] = i + 1
	}
	ans := []int{}
	for _, v := range queries {
		j := 0
		for i := range p {
			if p[i] == v {
				j = i
				break
			}
		}
		ans = append(ans, j)
		p = append(p[:j], p[j+1:]...)
		p = append([]int{v}, p...)
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Binary Indexed Tree

The Binary Indexed Tree (BIT), also known as the Fenwick Tree, efficiently supports the following two operations:

1. **Point Update** `update(x, delta)`: Adds a value `delta` to the element at position `x` in the sequence.
2. **Prefix Sum Query** `query(x)`: Queries the sum of the sequence over the interval `[1,...,x]`, i.e., the prefix sum at position `x`.

Both operations have a time complexity of $O(\log n)$.

The fundamental functionality of the Binary Indexed Tree is to count the number of elements smaller than a given element `x`. This comparison is abstract and can refer to size, coordinate, mass, etc.

For example, given the array `a[5] = {2, 5, 3, 4, 1}`, the task is to compute `b[i] = the number of elements to the left of position i that are less than or equal to a[i]`. For this example, `b[5] = {0, 1, 1, 2, 0}`.

The solution is to traverse the array, first calculating `query(a[i])` for each position, and then updating the Binary Indexed Tree with `update(a[i], 1)`. When the range of numbers is large, discretization is necessary, which involves removing duplicates, sorting, and then assigning an index to each number.

<!-- tabs:start -->

#### Python3

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
    def processQueries(self, queries: List[int], m: int) -> List[int]:
        n = len(queries)
        pos = [0] * (m + 1)
        tree = BinaryIndexedTree(m + n)
        for i in range(1, m + 1):
            pos[i] = n + i
            tree.update(n + i, 1)

        ans = []
        for i, v in enumerate(queries):
            j = pos[v]
            tree.update(j, -1)
            ans.append(tree.query(j))
            pos[v] = n - i
            tree.update(n - i, 1)
        return ans
```

#### Java

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
    public int[] processQueries(int[] queries, int m) {
        int n = queries.length;
        BinaryIndexedTree tree = new BinaryIndexedTree(m + n);
        int[] pos = new int[m + 1];
        for (int i = 1; i <= m; ++i) {
            pos[i] = n + i;
            tree.update(n + i, 1);
        }
        int[] ans = new int[n];
        int k = 0;
        for (int i = 0; i < n; ++i) {
            int v = queries[i];
            int j = pos[v];
            tree.update(j, -1);
            ans[k++] = tree.query(j);
            pos[v] = n - i;
            tree.update(n - i, 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

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
    vector<int> processQueries(vector<int>& queries, int m) {
        int n = queries.size();
        vector<int> pos(m + 1);
        BinaryIndexedTree* tree = new BinaryIndexedTree(m + n);
        for (int i = 1; i <= m; ++i) {
            pos[i] = n + i;
            tree->update(n + i, 1);
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            int v = queries[i];
            int j = pos[v];
            tree->update(j, -1);
            ans.push_back(tree->query(j));
            pos[v] = n - i;
            tree->update(n - i, 1);
        }
        return ans;
    }
};
```

#### Go

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

func processQueries(queries []int, m int) []int {
	n := len(queries)
	pos := make([]int, m+1)
	tree := newBinaryIndexedTree(m + n)
	for i := 1; i <= m; i++ {
		pos[i] = n + i
		tree.update(n+i, 1)
	}
	ans := []int{}
	for i, v := range queries {
		j := pos[v]
		tree.update(j, -1)
		ans = append(ans, tree.query(j))
		pos[v] = n - i
		tree.update(n-i, 1)
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
