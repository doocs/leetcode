---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3391.Design%20a%203D%20Binary%20Matrix%20with%20Efficient%20Layer%20Tracking/README_EN.md
---

<!-- problem:start -->

# [3391. Design a 3D Binary Matrix with Efficient Layer Tracking 🔒](https://leetcode.com/problems/design-a-3d-binary-matrix-with-efficient-layer-tracking)

[中文文档](/solution/3300-3399/3391.Design%20a%203D%20Binary%20Matrix%20with%20Efficient%20Layer%20Tracking/README.md)

## Description

<!-- description:start -->

None

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
from sortedcontainers import SortedList


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
