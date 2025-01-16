---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3391.Design%20a%203D%20Binary%20Matrix%20with%20Efficient%20Layer%20Tracking/README_EN.md
tags:
    - Design
    - Array
    - Hash Table
    - Matrix
    - Ordered Set
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3391. Design a 3D Binary Matrix with Efficient Layer Tracking 🔒](https://leetcode.com/problems/design-a-3d-binary-matrix-with-efficient-layer-tracking)

[中文文档](/solution/3300-3399/3391.Design%20a%203D%20Binary%20Matrix%20with%20Efficient%20Layer%20Tracking/README.md)

## Description

<!-- description:start -->

<p>You are given a <code>n x n x n</code> <strong>binary</strong> 3D array <code>matrix</code>.</p>

<p>Implement the <code>Matrix3D</code> class:</p>

<ul>
	<li><code>Matrix3D(int n)</code> Initializes the object with the 3D binary array <code>matrix</code>, where <strong>all</strong> elements are initially set to 0.</li>
	<li><code>void setCell(int x, int y, int z)</code> Sets the value at <code>matrix[x][y][z]</code> to 1.</li>
	<li><code>void unsetCell(int x, int y, int z)</code> Sets the value at <code>matrix[x][y][z]</code> to 0.</li>
	<li><code>int largestMatrix()</code> Returns the index <code>x</code> where <code>matrix[x]</code> contains the most number of 1&#39;s. If there are multiple such indices, return the <strong>largest</strong> <code>x</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;Matrix3D&quot;, &quot;setCell&quot;, &quot;largestMatrix&quot;, &quot;setCell&quot;, &quot;largestMatrix&quot;, &quot;setCell&quot;, &quot;largestMatrix&quot;]<br />
[[3], [0, 0, 0], [], [1, 1, 2], [], [0, 0, 1], []]</span></p>

<p><strong>Output:</strong><br />
<span class="example-io">[null, null, 0, null, 1, null, 0] </span></p>

<p><strong>Explanation</strong></p>
Matrix3D matrix3D = new Matrix3D(3); // Initializes a <code>3 x 3 x 3</code> 3D array <code>matrix</code>, filled with all 0&#39;s.<br />
matrix3D.setCell(0, 0, 0); // Sets <code>matrix[0][0][0]</code> to 1.<br />
matrix3D.largestMatrix(); // Returns 0. <code>matrix[0]</code> has the most number of 1&#39;s.<br />
matrix3D.setCell(1, 1, 2); // Sets <code>matrix[1][1][2]</code> to 1.<br />
matrix3D.largestMatrix(); // Returns 1. <code>matrix[0]</code> and <code>matrix[1]</code> tie with the most number of 1&#39;s, but index 1 is bigger.<br />
matrix3D.setCell(0, 0, 1); // Sets <code>matrix[0][0][1]</code> to 1.<br />
matrix3D.largestMatrix(); // Returns 0. <code>matrix[0]</code> has the most number of 1&#39;s.</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;Matrix3D&quot;, &quot;setCell&quot;, &quot;largestMatrix&quot;, &quot;unsetCell&quot;, &quot;largestMatrix&quot;]<br />
[[4], [2, 1, 1], [], [2, 1, 1], []]</span></p>

<p><strong>Output:</strong><br />
<span class="example-io">[null, null, 2, null, 3] </span></p>

<p><strong>Explanation</strong></p>
Matrix3D matrix3D = new Matrix3D(4); // Initializes a <code>4 x 4 x 4</code> 3D array <code>matrix</code>, filled with all 0&#39;s.<br />
matrix3D.setCell(2, 1, 1); // Sets <code>matrix[2][1][1]</code> to 1.<br />
matrix3D.largestMatrix(); // Returns 2. <code>matrix[2]</code> has the most number of 1&#39;s.<br />
matrix3D.unsetCell(2, 1, 1); // Sets <code>matrix[2][1][1]</code> to 0.<br />
matrix3D.largestMatrix(); // Returns 3. All indices from 0 to 3 tie with the same number of 1&#39;s, but index 3 is the biggest.</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= x, y, z &lt; n</code></li>
	<li>At most <code>10<sup>5</sup></code> calls are made in total to <code>setCell</code> and <code>unsetCell</code>.</li>
	<li>At most <code>10<sup>4</sup></code> calls are made to <code>largestMatrix</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting + Ordered Set

We use a three-dimensional array $\textit{g}$ to represent the matrix, where $\textit{g}[x][y][z]$ represents the value at coordinate $(x, y, z)$ in the matrix. We use an array $\textit{cnt}$ of length $n$ to record the number of 1s in each layer. We use an ordered set $\textit{sl}$ to maintain the number of 1s and the layer number for each layer. The elements in $\textit{sl}$ are $(\textit{cnt}[x], x)$, so $\textit{sl}$ can be sorted in descending order by the number of 1s, and in descending order by layer number if the number of 1s is the same.

When calling the `setCell` method, we first check if $(x, y, z)$ has already been set to 1. If it has, we return directly. Otherwise, we set $\textit{g}[x][y][z]$ to 1, remove $(\textit{cnt}[x], x)$ from $\textit{sl}$, increment $\textit{cnt}[x]$ by 1, and add $(\textit{cnt}[x], x)$ to $\textit{sl}$.

When calling the `unsetCell` method, we first check if $(x, y, z)$ has already been set to 0. If it has, we return directly. Otherwise, we set $\textit{g}[x][y][z]$ to 0, remove $(\textit{cnt}[x], x)$ from $\textit{sl}$, decrement $\textit{cnt}[x]$ by 1, and if $\textit{cnt}[x]$ is greater than 0, add $(\textit{cnt}[x], x)$ to $\textit{sl}$.

When calling the `largestMatrix` method, we return the second value of the first element in $\textit{sl}$. If $\textit{sl}$ is empty, we return $n - 1$.

In terms of time complexity, the `setCell` and `unsetCell` methods both have a time complexity of $O(\log n)$, and the `largestMatrix` method has a time complexity of $O(1)$. The space complexity is $O(n^3)$.

<!-- tabs:start -->

#### Python3

```python
class matrix3D:

    def __init__(self, n: int):
        self.g = [[[0] * n for _ in range(n)] for _ in range(n)]
        self.cnt = [0] * n
        self.sl = SortedList(key=lambda x: (-x[0], -x[1]))

    def setCell(self, x: int, y: int, z: int) -> None:
        if self.g[x][y][z]:
            return
        self.g[x][y][z] = 1
        self.sl.discard((self.cnt[x], x))
        self.cnt[x] += 1
        self.sl.add((self.cnt[x], x))

    def unsetCell(self, x: int, y: int, z: int) -> None:
        if self.g[x][y][z] == 0:
            return
        self.g[x][y][z] = 0
        self.sl.discard((self.cnt[x], x))
        self.cnt[x] -= 1
        if self.cnt[x]:
            self.sl.add((self.cnt[x], x))

    def largestMatrix(self) -> int:
        return self.sl[0][1] if self.sl else len(self.g) - 1


# Your matrix3D object will be instantiated and called as such:
# obj = matrix3D(n)
# obj.setCell(x,y,z)
# obj.unsetCell(x,y,z)
# param_3 = obj.largestMatrix()
```

#### Java

```java
class matrix3D {
    private final int[][][] g;
    private final int[] cnt;
    private final TreeSet<int[]> sl
        = new TreeSet<>((a, b) -> a[0] == b[0] ? b[1] - a[1] : b[0] - a[0]);

    public matrix3D(int n) {
        g = new int[n][n][n];
        cnt = new int[n];
    }

    public void setCell(int x, int y, int z) {
        if (g[x][y][z] == 1) {
            return;
        }
        g[x][y][z] = 1;
        sl.remove(new int[] {cnt[x], x});
        cnt[x]++;
        sl.add(new int[] {cnt[x], x});
    }

    public void unsetCell(int x, int y, int z) {
        if (g[x][y][z] == 0) {
            return;
        }
        g[x][y][z] = 0;
        sl.remove(new int[] {cnt[x], x});
        cnt[x]--;
        if (cnt[x] > 0) {
            sl.add(new int[] {cnt[x], x});
        }
    }

    public int largestMatrix() {
        return sl.isEmpty() ? g.length - 1 : sl.first()[1];
    }
}

/**
 * Your matrix3D object will be instantiated and called as such:
 * matrix3D obj = new matrix3D(n);
 * obj.setCell(x,y,z);
 * obj.unsetCell(x,y,z);
 * int param_3 = obj.largestMatrix();
 */
```

#### C++

```cpp
class matrix3D {
private:
    vector<vector<vector<int>>> g;
    vector<int> cnt;
    set<pair<int, int>> sl;

public:
    matrix3D(int n) {
        g.resize(n, vector<vector<int>>(n, vector<int>(n, 0)));
        cnt.resize(n, 0);
    }

    void setCell(int x, int y, int z) {
        if (g[x][y][z] == 1) {
            return;
        }
        g[x][y][z] = 1;
        sl.erase({-cnt[x], -x});
        cnt[x]++;
        sl.insert({-cnt[x], -x});
    }

    void unsetCell(int x, int y, int z) {
        if (g[x][y][z] == 0) {
            return;
        }
        g[x][y][z] = 0;
        sl.erase({-cnt[x], -x});
        cnt[x]--;
        if (cnt[x]) {
            sl.insert({-cnt[x], -x});
        }
    }

    int largestMatrix() {
        return sl.empty() ? g.size() - 1 : -sl.begin()->second;
    }
};

/**
 * Your matrix3D object will be instantiated and called as such:
 * matrix3D* obj = new matrix3D(n);
 * obj->setCell(x,y,z);
 * obj->unsetCell(x,y,z);
 * int param_3 = obj->largestMatrix();
 */
```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
