---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1131.Maximum%20of%20Absolute%20Value%20Expression/README.md
rating: 2059
source: 第 146 场周赛 Q4
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [1131. 绝对值表达式的最大值](https://leetcode.cn/problems/maximum-of-absolute-value-expression)

[English Version](/solution/1100-1199/1131.Maximum%20of%20Absolute%20Value%20Expression/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个长度相等的整数数组，返回下面表达式的最大值：</p>

<p><code>|arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|</code></p>

<p>其中下标 <code>i</code>，<code>j</code> 满足&nbsp;<code>0 &lt;= i, j &lt; arr1.length</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
<strong>输出：</strong>13
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
<strong>输出：</strong>20</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= arr1.length == arr2.length &lt;= 40000</code></li>
	<li><code>-10^6 &lt;= arr1[i], arr2[i] &lt;= 10^6</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学 + 枚举

我们不妨令 $x_i = arr1[i]$, $y_i = arr2[i]$，由于 $i$ 和 $j$ 的大小关系不影响表达式的值，我们不妨假设 $i \ge j$，那么表达式可以变为：

$$
| x_i - x_j | + | y_i - y_j | + i - j = \max \begin{cases} (x_i + y_i) - (x_j + y_j) \\ (x_i - y_i) - (x_j - y_j) \\ (-x_i + y_i) - (-x_j + y_j) \\ (-x_i - y_i) - (-x_j - y_j) \end{cases} + i - j\\
= \max \begin{cases} (x_i + y_i + i) - (x_j + y_j + j) \\ (x_i - y_i + i) - (x_j - y_j + j) \\ (-x_i + y_i + i) - (-x_j + y_j + j) \\ (-x_i - y_i + i) - (-x_j - y_j + j) \end{cases}
$$

因此，我们只要求出 $a \times x_i + b \times y_i + i$ 的最大值 $mx$，以及最小值 $mi$，其中 $a, b \in \{-1, 1\}$。那么答案就是所有 $mx - mi$ 中的最大值。

时间复杂度 $O(n)$，其中 $n$ 是数组长度。空间复杂度 $O(1)$。

相似题目：

- [1330. 翻转子数组得到最大的数组值](https://github.com/doocs/leetcode/blob/main/solution/1300-1399/1330.Reverse%20Subarray%20To%20Maximize%20Array%20Value/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxAbsValExpr(self, arr1: List[int], arr2: List[int]) -> int:
        dirs = (1, -1, -1, 1, 1)
        ans = -inf
        for a, b in pairwise(dirs):
            mx, mi = -inf, inf
            for i, (x, y) in enumerate(zip(arr1, arr2)):
                mx = max(mx, a * x + b * y + i)
                mi = min(mi, a * x + b * y + i)
                ans = max(ans, mx - mi)
        return ans
```

#### Java

```java
class Solution {
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int[] dirs = {1, -1, -1, 1, 1};
        final int inf = 1 << 30;
        int ans = -inf;
        int n = arr1.length;
        for (int k = 0; k < 4; ++k) {
            int a = dirs[k], b = dirs[k + 1];
            int mx = -inf, mi = inf;
            for (int i = 0; i < n; ++i) {
                mx = Math.max(mx, a * arr1[i] + b * arr2[i] + i);
                mi = Math.min(mi, a * arr1[i] + b * arr2[i] + i);
                ans = Math.max(ans, mx - mi);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxAbsValExpr(vector<int>& arr1, vector<int>& arr2) {
        int dirs[5] = {1, -1, -1, 1, 1};
        const int inf = 1 << 30;
        int ans = -inf;
        int n = arr1.size();
        for (int k = 0; k < 4; ++k) {
            int a = dirs[k], b = dirs[k + 1];
            int mx = -inf, mi = inf;
            for (int i = 0; i < n; ++i) {
                mx = max(mx, a * arr1[i] + b * arr2[i] + i);
                mi = min(mi, a * arr1[i] + b * arr2[i] + i);
                ans = max(ans, mx - mi);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxAbsValExpr(arr1 []int, arr2 []int) int {
	dirs := [5]int{1, -1, -1, 1, 1}
	const inf = 1 << 30
	ans := -inf
	for k := 0; k < 4; k++ {
		a, b := dirs[k], dirs[k+1]
		mx, mi := -inf, inf
		for i, x := range arr1 {
			y := arr2[i]
			mx = max(mx, a*x+b*y+i)
			mi = min(mi, a*x+b*y+i)
			ans = max(ans, mx-mi)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxAbsValExpr(arr1: number[], arr2: number[]): number {
    const dirs = [1, -1, -1, 1, 1];
    const inf = 1 << 30;
    let ans = -inf;
    for (let k = 0; k < 4; ++k) {
        const [a, b] = [dirs[k], dirs[k + 1]];
        let mx = -inf;
        let mi = inf;
        for (let i = 0; i < arr1.length; ++i) {
            const [x, y] = [arr1[i], arr2[i]];
            mx = Math.max(mx, a * x + b * y + i);
            mi = Math.min(mi, a * x + b * y + i);
            ans = Math.max(ans, mx - mi);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
