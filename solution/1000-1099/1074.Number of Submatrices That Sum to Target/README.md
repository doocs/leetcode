# [1074. 元素和为目标值的子矩阵数量](https://leetcode.cn/problems/number-of-submatrices-that-sum-to-target)

[English Version](/solution/1000-1099/1074.Number%20of%20Submatrices%20That%20Sum%20to%20Target/README_EN.md)

<!-- tags:数组,哈希表,矩阵,前缀和 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给出矩阵&nbsp;<code>matrix</code>&nbsp;和目标值&nbsp;<code>target</code>，返回元素总和等于目标值的非空子矩阵的数量。</p>

<p>子矩阵&nbsp;<code>x1, y1, x2, y2</code>&nbsp;是满足 <code>x1 &lt;= x &lt;= x2</code>&nbsp;且&nbsp;<code>y1 &lt;= y &lt;= y2</code>&nbsp;的所有单元&nbsp;<code>matrix[x][y]</code>&nbsp;的集合。</p>

<p>如果&nbsp;<code>(x1, y1, x2, y2)</code> 和&nbsp;<code>(x1', y1', x2', y2')</code>&nbsp;两个子矩阵中部分坐标不同（如：<code>x1 != x1'</code>），那么这两个子矩阵也不同。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1074.Number%20of%20Submatrices%20That%20Sum%20to%20Target/images/mate1.jpg" style="width: 242px; height: 242px;" /></p>

<pre>
<strong>输入：</strong>matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
<strong>输出：</strong>4
<strong>解释：</strong>四个只含 0 的 1x1 子矩阵。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[1,-1],[-1,1]], target = 0
<strong>输出：</strong>5
<strong>解释：</strong>两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[904]], target = 0
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong><strong>提示：</strong></strong></p>

<ul>
	<li><code>1 &lt;= matrix.length &lt;= 100</code></li>
	<li><code>1 &lt;= matrix[0].length &lt;= 100</code></li>
	<li><code>-1000 &lt;= matrix[i][j] &lt;= 1000</code></li>
	<li><code>-10^8 &lt;= target &lt;= 10^8</code></li>
</ul>

## 解法

### 方法一：枚举上下边界 + 前缀和 + 哈希表

我们可以枚举矩阵的上下边界 $i$ 和 $j$，每次算出当前上下边界内每列的元素和，记为数组 $col$，然后问题就转换为如何在数组 $col$ 中寻找和为目标值 $target$ 的子数组个数。我们累加这些子数组的个数，就是题目要求的答案。

那么题目就变成了：给定一个数组 $nums$ 和目标值 $target$，计算有多少个子数组的和为 $target$，我们可以通过函数 $f(nums, target)$ 来求解。

函数 $f(nums, target)$ 的计算方法如下：

-   定义一个哈希表 $d$，用来记录出现过的前缀和以及其出现次数，初始时 $d[0] = 1$；
-   初始化变量 $s = 0, cnt = 0$，其中 $s$ 表示前缀和，而 $cnt$ 表示和为 $target$ 的子数组个数；
-   从左到右遍历数组 $nums$，对于当前遍历到的元素 $x$，更新前缀和 $s = s + x$，如果 $d[s - target]$ 的值存在，那么更新 $cnt = cnt + d[s - target]$，即子数组个数增加 $d[s - target]$。然后更新哈希表中元素 $d[s]$ 的值，即 $d[s] = d[s] + 1$；继续遍历下一个元素；
-   遍历结束之后，返回子数组个数 $cnt$。

时间复杂度 $O(m^2 \times n)$，空间复杂度 $O(n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def numSubmatrixSumTarget(self, matrix: List[List[int]], target: int) -> int:
        def f(nums: List[int]) -> int:
            d = defaultdict(int)
            d[0] = 1
            cnt = s = 0
            for x in nums:
                s += x
                cnt += d[s - target]
                d[s] += 1
            return cnt

        m, n = len(matrix), len(matrix[0])
        ans = 0
        for i in range(m):
            col = [0] * n
            for j in range(i, m):
                for k in range(n):
                    col[k] += matrix[j][k]
                ans += f(col)
        return ans
```

```java
class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            int[] col = new int[n];
            for (int j = i; j < m; ++j) {
                for (int k = 0; k < n; ++k) {
                    col[k] += matrix[j][k];
                }
                ans += f(col, target);
            }
        }
        return ans;
    }

    private int f(int[] nums, int target) {
        Map<Integer, Integer> d = new HashMap<>();
        d.put(0, 1);
        int s = 0, cnt = 0;
        for (int x : nums) {
            s += x;
            cnt += d.getOrDefault(s - target, 0);
            d.merge(s, 1, Integer::sum);
        }
        return cnt;
    }
}
```

```cpp
class Solution {
public:
    int numSubmatrixSumTarget(vector<vector<int>>& matrix, int target) {
        int m = matrix.size(), n = matrix[0].size();
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            vector<int> col(n);
            for (int j = i; j < m; ++j) {
                for (int k = 0; k < n; ++k) {
                    col[k] += matrix[j][k];
                }
                ans += f(col, target);
            }
        }
        return ans;
    }

    int f(vector<int>& nums, int target) {
        unordered_map<int, int> d{{0, 1}};
        int cnt = 0, s = 0;
        for (int& x : nums) {
            s += x;
            if (d.count(s - target)) {
                cnt += d[s - target];
            }
            ++d[s];
        }
        return cnt;
    }
};
```

```go
func numSubmatrixSumTarget(matrix [][]int, target int) (ans int) {
	m, n := len(matrix), len(matrix[0])
	for i := 0; i < m; i++ {
		col := make([]int, n)
		for j := i; j < m; j++ {
			for k := 0; k < n; k++ {
				col[k] += matrix[j][k]
			}
			ans += f(col, target)
		}
	}
	return
}

func f(nums []int, target int) (cnt int) {
	d := map[int]int{0: 1}
	s := 0
	for _, x := range nums {
		s += x
		if v, ok := d[s-target]; ok {
			cnt += v
		}
		d[s]++
	}
	return
}
```

```ts
function numSubmatrixSumTarget(matrix: number[][], target: number): number {
    const m = matrix.length;
    const n = matrix[0].length;
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        const col: number[] = new Array(n).fill(0);
        for (let j = i; j < m; ++j) {
            for (let k = 0; k < n; ++k) {
                col[k] += matrix[j][k];
            }
            ans += f(col, target);
        }
    }
    return ans;
}

function f(nums: number[], target: number): number {
    const d: Map<number, number> = new Map();
    d.set(0, 1);
    let cnt = 0;
    let s = 0;
    for (const x of nums) {
        s += x;
        if (d.has(s - target)) {
            cnt += d.get(s - target)!;
        }
        d.set(s, (d.get(s) || 0) + 1);
    }
    return cnt;
}
```

<!-- tabs:end -->

<!-- end -->
