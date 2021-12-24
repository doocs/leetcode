# [547. Number of Provinces](https://leetcode.com/problems/number-of-provinces)

[中文文档](/solution/0500-0599/0547.Number%20of%20Provinces/README.md)

## Description

<p>There are <code>n</code> cities. Some of them are connected, while some are not. If city <code>a</code> is connected directly with city <code>b</code>, and city <code>b</code> is connected directly with city <code>c</code>, then city <code>a</code> is connected indirectly with city <code>c</code>.</p>

<p>A <strong>province</strong> is a group of directly or indirectly connected cities and no other cities outside of the group.</p>

<p>You are given an <code>n x n</code> matrix <code>isConnected</code> where <code>isConnected[i][j] = 1</code> if the <code>i<sup>th</sup></code> city and the <code>j<sup>th</sup></code> city are directly connected, and <code>isConnected[i][j] = 0</code> otherwise.</p>

<p>Return <em>the total number of <strong>provinces</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0547.Number%20of%20Provinces/images/graph1.jpg" style="width: 222px; height: 142px;" />
<pre>
<strong>Input:</strong> isConnected = [[1,1,0],[1,1,0],[0,0,1]]
<strong>Output:</strong> 2
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0547.Number%20of%20Provinces/images/graph2.jpg" style="width: 222px; height: 142px;" />
<pre>
<strong>Input:</strong> isConnected = [[1,0,0],[0,1,0],[0,0,1]]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>n == isConnected.length</code></li>
	<li><code>n == isConnected[i].length</code></li>
	<li><code>isConnected[i][j]</code> is <code>1</code> or <code>0</code>.</li>
	<li><code>isConnected[i][i] == 1</code></li>
	<li><code>isConnected[i][j] == isConnected[j][i]</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findCircleNum(self, isConnected: List[List[int]]) -> int:
        def dfs(i):
            for j in range(n):
                if not visited[j] and isConnected[i][j] == 1:
                    visited[j] = True
                    dfs(j)

        n = len(isConnected)
        visited = [False] * n
        num = 0
        for i in range(n):
            if not visited[i]:
                dfs(i)
                num += 1
        return num
```

```python
class Solution:
    def findCircleNum(self, isConnected: List[List[int]]) -> int:
        n = size = len(isConnected)
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in range(n):
            for j in range(i + 1, n):
                if isConnected[i][j] == 1 and find(i) != find(j):
                    p[find(i)] = find(j)
                    size -= 1
        return size
```

### **Java**

```java
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int num = 0;
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                dfs(isConnected, visited, i, n);
                ++num;
            }
        }
        return num;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int i, int n) {
        for (int j = 0; j < n; ++j) {
            if (!visited[j] && isConnected[i][j] == 1) {
                visited[j] = true;
                dfs(isConnected, visited, j, n);
            }
        }
    }
}
```

```java
class Solution {
    private int[] p;

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        int size =  n;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (isConnected[i][j] == 1 && find(i) != find(j)) {
                    p[find(i)] = find(j);
                    --size;
                }
            }
        }
        return size;
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

    int findCircleNum(vector<vector<int>>& isConnected) {
        int n = isConnected.size();
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        int size = n;
        for (int i = 0; i < n; ++i)
        {
            for (int j = i + 1; j < n; ++j)
            {
                if (isConnected[i][j] && find(i) != find(j))
                {
                    p[find(i)] = find(j);
                    --size;
                }
            }
        }
        return size;
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

func findCircleNum(isConnected [][]int) int {
	n := len(isConnected)
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	size := n
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if isConnected[i][j] == 1 && find(i) != find(j) {
				p[find(i)] = find(j)
				size--
			}
		}
	}
	return size
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
