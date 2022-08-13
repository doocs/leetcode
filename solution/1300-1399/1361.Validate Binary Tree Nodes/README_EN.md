# [1361. Validate Binary Tree Nodes](https://leetcode.com/problems/validate-binary-tree-nodes)

[中文文档](/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/README.md)

## Description

<p>You have <code>n</code> binary tree nodes numbered from <code>0</code> to <code>n - 1</code> where node <code>i</code> has two children <code>leftChild[i]</code> and <code>rightChild[i]</code>, return <code>true</code> if and only if <strong>all</strong> the given nodes form <strong>exactly one</strong> valid binary tree.</p>

<p>If node <code>i</code> has no left child then <code>leftChild[i]</code> will equal <code>-1</code>, similarly for the right child.</p>

<p>Note that the nodes have no values and that we only use the node numbers in this problem.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/images/1503_ex1.png" style="width: 195px; height: 287px;" />
<pre>
<strong>Input:</strong> n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
<strong>Output:</strong> true
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/images/1503_ex2.png" style="width: 183px; height: 272px;" />
<pre>
<strong>Input:</strong> n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
<strong>Output:</strong> false
</pre>

<p><strong>Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1361.Validate%20Binary%20Tree%20Nodes/images/1503_ex3.png" style="width: 82px; height: 174px;" />
<pre>
<strong>Input:</strong> n = 2, leftChild = [1,0], rightChild = [-1,-1]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == leftChild.length == rightChild.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>-1 &lt;= leftChild[i], rightChild[i] &lt;= n - 1</code></li>
</ul>

## Solutions

Union find.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def validateBinaryTreeNodes(
        self, n: int, leftChild: List[int], rightChild: List[int]
    ) -> bool:
        p = list(range(n))
        vis = [False] * n

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(n):
            l, r = leftChild[i], rightChild[i]
            if l != -1:
                if vis[l] or find(i) == find(l):
                    return False
                p[find(i)] = find(l)
                vis[l] = True
                n -= 1
            if r != -1:
                if vis[r] or find(i) == find(r):
                    return False
                p[find(i)] = find(r)
                vis[r] = True
                n -= 1
        return n == 1
```

### **Java**

```java
class Solution {
    private int[] p;

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        p = new int[n];
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int i = 0, t = n; i < t; ++i) {
            int l = leftChild[i], r = rightChild[i];
            if (l != -1) {
                if (vis[l] || find(i) == find(l)) {
                    return false;
                }
                vis[l] = true;
                p[find(i)] = find(l);
                --n;
            }
            if (r != -1) {
                if (vis[r] || find(i) == find(r)) {
                    return false;
                }
                vis[r] = true;
                p[find(i)] = find(r);
                --n;
            }
        }
        return n == 1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    bool validateBinaryTreeNodes(int n, vector<int>& leftChild, vector<int>& rightChild) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        vector<bool> vis(n, false);
        for (int i = 0, t = n; i < t; ++i) {
            int l = leftChild[i], r = rightChild[i];
            if (l != -1) {
                if (vis[l] || find(i) == find(l)) return false;
                vis[l] = true;
                p[find(i)] = find(l);
                --n;
            }
            if (r != -1) {
                if (vis[r] || find(i) == find(r)) return false;
                vis[r] = true;
                p[find(i)] = find(r);
                --n;
            }
        }
        return n == 1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
var p []int

func validateBinaryTreeNodes(n int, leftChild []int, rightChild []int) bool {
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	vis := make([]bool, n)
	for i, t := 0, n; i < t; i++ {
		l, r := leftChild[i], rightChild[i]
		if l != -1 {
			if vis[l] || find(i) == find(l) {
				return false
			}
			vis[l] = true
			p[find(i)] = find(l)
			n--
		}
		if r != -1 {
			if vis[r] || find(i) == find(r) {
				return false
			}
			vis[r] = true
			p[find(i)] = find(r)
			n--
		}
	}
	return n == 1
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}
```

### **...**

```

```

<!-- tabs:end -->
