# [1649. Create Sorted Array through Instructions](https://leetcode.com/problems/create-sorted-array-through-instructions)

[中文文档](/solution/1600-1699/1649.Create%20Sorted%20Array%20through%20Instructions/README.md)

## Description

<p>Given an integer array <code>instructions</code>, you are asked to create a sorted array from the elements in <code>instructions</code>. You start with an empty container <code>nums</code>. For each element from <strong>left to right</strong> in <code>instructions</code>, insert it into <code>nums</code>. The <strong>cost</strong> of each insertion is the <b>minimum</b> of the following:</p>

<ul>
	<li>The number of elements currently in <code>nums</code> that are <strong>strictly less than</strong> <code>instructions[i]</code>.</li>
	<li>The number of elements currently in <code>nums</code> that are <strong>strictly greater than</strong> <code>instructions[i]</code>.</li>
</ul>

<p>For example, if inserting element <code>3</code> into <code>nums = [1,2,3,5]</code>, the <strong>cost</strong> of insertion is <code>min(2, 1)</code> (elements <code>1</code> and <code>2</code> are less than <code>3</code>, element <code>5</code> is greater than <code>3</code>) and <code>nums</code> will become <code>[1,2,3,3,5]</code>.</p>

<p>Return <em>the <strong>total cost</strong> to insert all elements from </em><code>instructions</code><em> into </em><code>nums</code>. Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code></p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> instructions = [1,5,6,2]

<strong>Output:</strong> 1

<strong>Explanation:</strong> Begin with nums = [].

Insert 1 with cost min(0, 0) = 0, now nums = [1].

Insert 5 with cost min(1, 0) = 0, now nums = [1,5].

Insert 6 with cost min(2, 0) = 0, now nums = [1,5,6].

Insert 2 with cost min(1, 2) = 1, now nums = [1,2,5,6].

The total cost is 0 + 0 + 0 + 1 = 1.</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> instructions = [1,2,3,6,5,4]

<strong>Output:</strong> 3

<strong>Explanation:</strong> Begin with nums = [].

Insert 1 with cost min(0, 0) = 0, now nums = [1].

Insert 2 with cost min(1, 0) = 0, now nums = [1,2].

Insert 3 with cost min(2, 0) = 0, now nums = [1,2,3].

Insert 6 with cost min(3, 0) = 0, now nums = [1,2,3,6].

Insert 5 with cost min(3, 1) = 1, now nums = [1,2,3,5,6].

Insert 4 with cost min(3, 2) = 2, now nums = [1,2,3,4,5,6].

The total cost is 0 + 0 + 0 + 0 + 1 + 2 = 3.

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> instructions = [1,3,3,3,2,4,2,1,2]

<strong>Output:</strong> 4

<strong>Explanation:</strong> Begin with nums = [].

Insert 1 with cost min(0, 0) = 0, now nums = [1].

Insert 3 with cost min(1, 0) = 0, now nums = [1,3].

Insert 3 with cost min(1, 0) = 0, now nums = [1,3,3].

Insert 3 with cost min(1, 0) = 0, now nums = [1,3,3,3].

Insert 2 with cost min(1, 3) = 1, now nums = [1,2,3,3,3].

Insert 4 with cost min(5, 0) = 0, now nums = [1,2,3,3,3,4].

​​​​​​​Insert 2 with cost min(1, 4) = 1, now nums = [1,2,2,3,3,3,4].

​​​​​​​Insert 1 with cost min(0, 6) = 0, now nums = [1,1,2,2,3,3,3,4].

​​​​​​​Insert 2 with cost min(2, 4) = 2, now nums = [1,1,2,2,2,3,3,3,4].

The total cost is 0 + 0 + 0 + 0 + 1 + 0 + 1 + 0 + 2 = 4.

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= instructions.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= instructions[i] &lt;= 10<sup>5</sup></code></li>
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
    def createSortedArray(self, instructions: List[int]) -> int:
        n = max(instructions)
        tree = BinaryIndexedTree(n)
        ans = 0
        for num in instructions:
            a = tree.query(num - 1)
            b = tree.query(n) - tree.query(num)
            ans += min(a, b)
            tree.update(num, 1)
        return ans % int((1e9 + 7))
```

### **Java**

```java
class Solution {
    public int createSortedArray(int[] instructions) {
        int n = 100010;
        int mod = (int) 1e9 + 7;
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        int ans = 0;
        for (int num : instructions) {
            int a = tree.query(num - 1);
            int b = tree.query(n) - tree.query(num);
            ans += Math.min(a, b);
            ans %= mod;
            tree.update(num, 1);
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
    int createSortedArray(vector<int>& instructions) {
        int n = 100010;
        int mod = 1e9 + 7;
        BinaryIndexedTree* tree = new BinaryIndexedTree(n);
        int ans = 0;
        for (int num : instructions)
        {
            int a = tree->query(num - 1);
            int b = tree->query(n) - tree->query(num);
            ans += min(a, b);
            ans %= mod;
            tree->update(num, 1);
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

func createSortedArray(instructions []int) int {
	n := 100010
	mod := int(1e9 + 7)
	tree := newBinaryIndexedTree(n)
	ans := 0
	for _, num := range instructions {
		a, b := tree.query(num-1), tree.query(n)-tree.query(num)
		ans += min(a, b)
		ans %= mod
		tree.update(num, 1)
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
