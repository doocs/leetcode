---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0119.Pascal%27s%20Triangle%20II/README.md
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [119. 杨辉三角 II](https://leetcode.cn/problems/pascals-triangle-ii)

[English Version](/solution/0100-0199/0119.Pascal%27s%20Triangle%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个非负索引 <code>rowIndex</code>，返回「杨辉三角」的第 <code>rowIndex</code><em> </em>行。</p>

<p><small>在「杨辉三角」中，每个数是它左上方和右上方的数的和。</small></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0119.Pascal%27s%20Triangle%20II/images/1626927345-DZmfxB-PascalTriangleAnimated2.gif" /></p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> rowIndex = 3
<strong>输出:</strong> [1,3,3,1]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> rowIndex = 0
<strong>输出:</strong> [1]
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> rowIndex = 1
<strong>输出:</strong> [1,1]
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>0 <= rowIndex <= 33</code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong></p>

<p>你可以优化你的算法到 <code><em>O</em>(<i>rowIndex</i>)</code> 空间复杂度吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递推

我们创建一个长度为 $rowIndex + 1$ 的数组 $f$，初始时所有元素均为 $1$。

接下来，我们从第 $2$ 行开始，从后往前计算当前行的第 $j$ 个元素的值 $f[j] = f[j] + f[j - 1]$，其中 $j \in [1, i - 1]$。

最后返回 $f$ 即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是给定的行数。

<!-- tabs:start -->

```python
class Solution:
    def getRow(self, rowIndex: int) -> List[int]:
        f = [1] * (rowIndex + 1)
        for i in range(2, rowIndex + 1):
            for j in range(i - 1, 0, -1):
                f[j] += f[j - 1]
        return f
```

```java
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> f = new ArrayList<>();
        for (int i = 0; i < rowIndex + 1; ++i) {
            f.add(1);
        }
        for (int i = 2; i < rowIndex + 1; ++i) {
            for (int j = i - 1; j > 0; --j) {
                f.set(j, f.get(j) + f.get(j - 1));
            }
        }
        return f;
    }
}
```

```cpp
class Solution {
public:
    vector<int> getRow(int rowIndex) {
        vector<int> f(rowIndex + 1, 1);
        for (int i = 2; i < rowIndex + 1; ++i) {
            for (int j = i - 1; j; --j) {
                f[j] += f[j - 1];
            }
        }
        return f;
    }
};
```

```go
func getRow(rowIndex int) []int {
	f := make([]int, rowIndex+1)
	for i := range f {
		f[i] = 1
	}
	for i := 2; i < rowIndex+1; i++ {
		for j := i - 1; j > 0; j-- {
			f[j] += f[j-1]
		}
	}
	return f
}
```

```ts
function getRow(rowIndex: number): number[] {
    const f: number[] = Array(rowIndex + 1).fill(1);
    for (let i = 2; i < rowIndex + 1; ++i) {
        for (let j = i - 1; j; --j) {
            f[j] += f[j - 1];
        }
    }
    return f;
}
```

```rust
impl Solution {
    pub fn get_row(row_index: i32) -> Vec<i32> {
        let n = (row_index + 1) as usize;
        let mut f = vec![1; n];
        for i in 2..n {
            for j in (1..i).rev() {
                f[j] += f[j - 1];
            }
        }
        f
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
