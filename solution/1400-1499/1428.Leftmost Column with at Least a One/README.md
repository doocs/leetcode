# [1428. 至少有一个 1 的最左端列](https://leetcode.cn/problems/leftmost-column-with-at-least-a-one)

[English Version](/solution/1400-1499/1428.Leftmost%20Column%20with%20at%20Least%20a%20One/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><em>（这是一个<strong>交互题</strong>）</em></p>

<p>我们称只包含元素&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>&nbsp;的矩阵为二进制矩阵。矩阵中每个<strong>单独</strong>的行都按非递减顺序排序。</p>

<p>给定一个这样的二进制矩阵，返回至少包含一个&nbsp;<code>1</code>&nbsp;的最左端列的索引（从 0 开始）。如果这样的列不存在，返回&nbsp;<code>-1</code>。</p>

<p><strong>您不能直接访问该二进制矩阵。</strong>你只可以通过&nbsp;<code>BinaryMatrix</code>&nbsp;接口来访问。</p>

<ul>
	<li><code>BinaryMatrix.get(row, col)</code>&nbsp;返回位于索引&nbsp;<code>(row, col)</code>&nbsp;（从 0 开始）的元素。</li>
	<li><code>BinaryMatrix.dimensions()</code>&nbsp;返回含有 2 个元素的列表&nbsp;<code>[rows, cols]</code>，表示这是一个&nbsp;<code>rows *&nbsp;cols</code>的矩阵。</li>
</ul>

<p>如果提交的答案调用&nbsp;<code>BinaryMatrix.get</code>&nbsp;超过 <code>1000</code> 次，则该答案会被判定为<em>错误答案</em>。提交任何试图规避判定机制的答案将会被取消资格。</p>

<p>下列示例中，&nbsp;<code>mat</code>&nbsp;为给定的二进制矩阵。您不能直接访问该矩阵。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1428.Leftmost%20Column%20with%20at%20Least%20a%20One/images/untitled-diagram-5.jpg" style="height:81px; width:81px" /></strong></p>

<pre>
<strong>输入:</strong> mat = [[0,0],[1,1]]
<strong>输出:</strong> 0
</pre>

<p><strong>示例 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1428.Leftmost%20Column%20with%20at%20Least%20a%20One/images/untitled-diagram-4.jpg" style="height:81px; width:81px" /></strong></p>

<pre>
<strong>输入:</strong> mat = [[0,0],[0,1]]
<strong>输出:</strong> 1
</pre>

<p><strong>示例 3:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1428.Leftmost%20Column%20with%20at%20Least%20a%20One/images/untitled-diagram-3.jpg" style="height:81px; width:81px" /></strong></p>

<pre>
<strong>输入:</strong> mat = [[0,0],[0,0]]
<strong>输出:</strong> -1</pre>

<p><strong>示例 4:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1428.Leftmost%20Column%20with%20at%20Least%20a%20One/images/untitled-diagram-6.jpg" style="height:121px; width:161px" /></strong></p>

<pre>
<strong>输入:</strong> mat = [[0,0,0,1],[0,0,1,1],[0,1,1,1]]
<strong>输出:</strong> 1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>rows == mat.length</code></li>
	<li><code>cols == mat[i].length</code></li>
	<li><code>1 &lt;= rows, cols&nbsp;&lt;= 100</code></li>
	<li><code>mat[i][j]</code>&nbsp;只会是&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>。</li>
	<li><code>mat[i]</code>&nbsp;已按非递减顺序排序。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

我们先调用 `BinaryMatrix.dimensions()` 得到矩阵的行数 $m$ 和列数 $n$，然后对于每一行，我们使用二分查找来找到最左边的 $1$ 所在的列数 $j$，找出所有行中最小的满足 $j$ 的值即为答案。如果不存在这样的列，则返回 $-1$。

时间复杂度 $O(m \times \log n)$，其中 $m$ 和 $n$ 分别是矩阵的行数和列数。需要遍历每一行，每一行内使用二分查找，时间复杂度为 $O(\log n)$。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# """
# This is BinaryMatrix's API interface.
# You should not implement it, or speculate about its implementation
# """
# class BinaryMatrix(object):
#    def get(self, row: int, col: int) -> int:
#    def dimensions(self) -> list[]:


class Solution:
    def leftMostColumnWithOne(self, binaryMatrix: "BinaryMatrix") -> int:
        m, n = binaryMatrix.dimensions()
        ans = n
        for i in range(m):
            j = bisect_left(range(n), 1, key=lambda k: binaryMatrix.get(i, k))
            ans = min(ans, j)
        return -1 if ans >= n else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> e = binaryMatrix.dimensions();
        int m = e.get(0), n = e.get(1);
        int ans = n;
        for (int i = 0; i < m; ++i) {
            int l = 0, r = n;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (binaryMatrix.get(i, mid) == 1) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            ans = Math.min(ans, l);
        }
        return ans >= n ? -1 : ans;
    }
}
```

### **C++**

```cpp
/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * class BinaryMatrix {
 *   public:
 *     int get(int row, int col);
 *     vector<int> dimensions();
 * };
 */

class Solution {
public:
    int leftMostColumnWithOne(BinaryMatrix &binaryMatrix) {
        auto e = binaryMatrix.dimensions();
        int m = e[0], n = e[1];
        int ans = n;
        for (int i = 0; i < m; ++i) {
            int l = 0, r = n;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (binaryMatrix.get(i, mid)) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            ans = min(ans, l);
        }
        return ans >= n ? -1 : ans;
    }
};
```

### **Go**

```go
/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * type BinaryMatrix struct {
 *     Get func(int, int) int
 *     Dimensions func() []int
 * }
 */

func leftMostColumnWithOne(binaryMatrix BinaryMatrix) int {
	e := binaryMatrix.Dimensions()
	m, n := e[0], e[1]
	ans := n
	for i := 0; i < m; i++ {
		l, r := 0, n
		for l < r {
			mid := (l + r) >> 1
			if binaryMatrix.Get(i, mid) == 1 {
				r = mid
			} else {
				l = mid + 1
			}
		}
		ans = min(ans, l)
	}
	if ans >= n {
		return -1
	}
	return ans
}
```

### **TypeScript**

```ts
/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * class BinaryMatrix {
 *      get(row: number, col: number): number {}
 *
 *      dimensions(): number[] {}
 * }
 */

function leftMostColumnWithOne(binaryMatrix: BinaryMatrix) {
    const [m, n] = binaryMatrix.dimensions();
    let ans = n;
    for (let i = 0; i < m; ++i) {
        let [l, r] = [0, n];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (binaryMatrix.get(i, mid) === 1) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        ans = Math.min(ans, l);
    }
    return ans >= n ? -1 : ans;
}
```

### **Rust**

```rust

/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 *  struct BinaryMatrix;
 *  impl BinaryMatrix {
 *     fn get(row: i32, col: i32) -> i32;
 *     fn dimensions() -> Vec<i32>;
 * };
 */

impl Solution {
    pub fn left_most_column_with_one(binaryMatrix: &BinaryMatrix) -> i32 {
        let e = binaryMatrix.dimensions();
        let m = e[0] as usize;
        let n = e[1] as usize;
        let mut ans = n;

        for i in 0..m {
            let (mut l, mut r) = (0, n);
            while l < r {
                let mid = (l + r) / 2;
                if binaryMatrix.get(i as i32, mid as i32) == 1 {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            ans = ans.min(l);
        }

        if ans >= n {
            -1
        } else {
            ans as i32
        }
    }
}
```

### **C#**

```cs
/**
 * // This is BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * class BinaryMatrix {
 *     public int Get(int row, int col) {}
 *     public IList<int> Dimensions() {}
 * }
 */

class Solution {
    public int LeftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        var e = binaryMatrix.Dimensions();
        int m = e[0], n = e[1];
        int ans = n;
        for (int i = 0; i < m; ++i) {
            int l = 0, r = n;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (binaryMatrix.Get(i, mid) == 1) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            ans = Math.Min(ans, l);
        }
        return ans >= n ? -1 : ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
