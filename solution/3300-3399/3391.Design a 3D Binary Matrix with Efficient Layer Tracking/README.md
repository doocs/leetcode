---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3391.Design%20a%203D%20Binary%20Matrix%20with%20Efficient%20Layer%20Tracking/README.md
tags:
    - 设计
    - 数组
    - 哈希表
    - 矩阵
    - 有序集合
    - 堆（优先队列）
---

<!-- problem:start -->

# [3391. 设计一个高效的层跟踪三维二进制矩阵 🔒](https://leetcode.cn/problems/design-a-3d-binary-matrix-with-efficient-layer-tracking)

[English Version](/solution/3300-3399/3391.Design%20a%203D%20Binary%20Matrix%20with%20Efficient%20Layer%20Tracking/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个&nbsp;<code>n x n x n</code>&nbsp;的 <strong>二进制&nbsp;</strong>三维数组&nbsp;<code>matrix</code>。</p>

<p>实现&nbsp;<code>Matrix3D</code>&nbsp;类：</p>

<ul>
	<li><code>Matrix3D(int n)</code>&nbsp;用三维二进制数组&nbsp;<code>matrix</code>&nbsp;初始化对象，其中 <strong>所有</strong>&nbsp;元素都初始化为 0。</li>
	<li><code>void setCell(int x, int y, int z)</code>&nbsp;将 <code>matrix[x][y][z]</code>&nbsp;的值设为 1。</li>
	<li><code>void unsetCell(int x, int y, int z)</code>&nbsp;将 <code>matrix[x][y][z]</code>&nbsp;的值设为 0。</li>
	<li><code>int largestMatrix()</code>&nbsp;返回包含最多 1 的 <code>matrix[x]</code>&nbsp;的下标&nbsp;<code>x</code>。如果这样的对应值有多个，返回&nbsp;<strong>最大的</strong>&nbsp;<code>x</code>。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><br />
<span class="example-io">["Matrix3D", "setCell", "largestMatrix", "setCell", "largestMatrix", "setCell", "largestMatrix"]<br />
[[3], [0, 0, 0], [], [1, 1, 2], [], [0, 0, 1], []]</span></p>

<p><strong>输出：</strong><br />
<span class="example-io">[null, null, 0, null, 1, null, 0] </span></p>

<p><strong>解释：</strong></p>
Matrix3D matrix3D = new Matrix3D(3); // 初始化一个&nbsp;<code>3 x 3 x 3</code>&nbsp;的三维数组&nbsp;<code>matrix</code>，用全 0 填充。<br />
matrix3D.setCell(0, 0, 0); // 将&nbsp;<code>matrix[0][0][0]</code> 设为 1。<br />
matrix3D.largestMatrix(); // 返回 0。<code>matrix[0]</code>&nbsp;1 的数量最多。<br />
matrix3D.setCell(1, 1, 2); // 将 <code>matrix[1][1][2]</code> 设为 1。<br />
matrix3D.largestMatrix(); // 返回 1。<code>matrix[0]</code> 和&nbsp;<code>matrix[1]</code>&nbsp;1 的数量一样多，但下标 1 更大。<br />
matrix3D.setCell(0, 0, 1); // 将 <code>matrix[0][0][1]</code> 设为 1。<br />
matrix3D.largestMatrix(); // 返回 0。<code>matrix[0]</code>&nbsp;1 的数量最多。</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><br />
<span class="example-io">["Matrix3D", "setCell", "largestMatrix", "unsetCell", "largestMatrix"]<br />
[[4], [2, 1, 1], [], [2, 1, 1], []]</span></p>

<p><strong>输出：</strong><br />
<span class="example-io">[null, null, 2, null, 3] </span></p>

<p><strong>解释：</strong></p>
Matrix3D matrix3D = new matrix3D(4); // 初始化一个&nbsp;<code>4 x 4 x 4</code>&nbsp;的三维数组&nbsp;<code>matrix</code>，用全 0 填充。<br />
matrix3D.setCell(2, 1, 1); // 将&nbsp;<code>matrix[2][1][1]</code> 设为 1。<br />
matrix3D.largestMatrix(); // 返回 2。<code>matrix[2]</code>&nbsp;1 的数量最多。<br />
matrix3D.unsetCell(2, 1, 1); // 将 <code>matrix[2][1][1]</code> 设为 0。<br />
matrix3D.largestMatrix(); // 返回 3。0 到 3 的对应值都有相同数量的 1，但下标 3 最大。</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= x, y, z &lt; n</code></li>
	<li>最多总共调用&nbsp;<code>10<sup>5</sup></code>&nbsp;次&nbsp;<code>setCell</code> 和&nbsp;<code>unsetCell</code>。</li>
	<li>最多调用&nbsp;<code>10<sup>4</sup></code> 次&nbsp;<code>largestMatrix</code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 有序集合

我们使用一个三维数组 $\textit{g}$ 来表示矩阵，其中 $\textit{g}[x][y][z]$ 表示矩阵中坐标 $(x, y, z)$ 的值，用一个长度为 $n$ 的数组 $\textit{cnt}$ 来记录每一层的 $1$ 的个数，用一个有序集合 $\textit{sl}$ 来维护每一层的 $1$ 的个数和层数，其中 $\textit{sl}$ 中的元素是 $(\textit{cnt}[x], x)$，这样 $\textit{sl}$ 就能按照 $1$ 的个数降序排序，如果 $1$ 的个数相同，则按照层数降序排序。

调用 `setCell` 方法时，我们先判断 $(x, y, z)$ 是否已经被设置为 $1$，如果是则直接返回，否则将 $\textit{g}[x][y][z]$ 设置为 $1$，然后将 $(\textit{cnt}[x], x)$ 从 $\textit{sl}$ 中删除，将 $\textit{cnt}[x]$ 加一，再将 $(\textit{cnt}[x], x)$ 加入 $\textit{sl}$。

调用 `unsetCell` 方法时，我们先判断 $(x, y, z)$ 是否已经被设置为 $0$，如果是则直接返回，否则将 $\textit{g}[x][y][z]$ 设置为 $0$，然后将 $(\textit{cnt}[x], x)$ 从 $\textit{sl}$ 中删除，将 $\textit{cnt}[x]$ 减一，如果 $\textit{cnt}[x]$ 大于 $0$，则将 $(\textit{cnt}[x], x)$ 加入 $\textit{sl}$。

调用 `largestMatrix` 方法时，我们返回 $\textit{sl}$ 中第一个元素的第二个值，如果 $\textit{sl}$ 为空，则返回 $n - 1$。

时间复杂度方面，`setCell` 和 `unsetCell` 方法的时间复杂度均为 $O(\log n)$，`largestMatrix` 方法的时间复杂度为 $O(1)$。空间复杂度 $O(n^3)$。

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
