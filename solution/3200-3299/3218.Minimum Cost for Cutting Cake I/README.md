---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3218.Minimum%20Cost%20for%20Cutting%20Cake%20I/README.md
---

<!-- problem:start -->

# [3218. 切蛋糕的最小总开销 I](https://leetcode.cn/problems/minimum-cost-for-cutting-cake-i)

[English Version](/solution/3200-3299/3218.Minimum%20Cost%20for%20Cutting%20Cake%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有一个&nbsp;<code>m x n</code>&nbsp;大小的矩形蛋糕，需要切成&nbsp;<code>1 x 1</code>&nbsp;的小块。</p>

<p>给你整数&nbsp;<code>m</code>&nbsp;，<code>n</code>&nbsp;和两个数组：</p>

<ul>
	<li><code>horizontalCut</code> 的大小为&nbsp;<code>m - 1</code>&nbsp;，其中&nbsp;<code>horizontalCut[i]</code>&nbsp;表示沿着水平线 <code>i</code>&nbsp;切蛋糕的开销。</li>
	<li><code>verticalCut</code> 的大小为&nbsp;<code>n - 1</code>&nbsp;，其中&nbsp;<code>verticalCut[j]</code>&nbsp;表示沿着垂直线&nbsp;<code>j</code>&nbsp;切蛋糕的开销。</li>
</ul>

<p>一次操作中，你可以选择任意不是&nbsp;<code>1 x 1</code>&nbsp;大小的矩形蛋糕并执行以下操作之一：</p>

<ol>
	<li>沿着水平线&nbsp;<code>i</code>&nbsp;切开蛋糕，开销为&nbsp;<code>horizontalCut[i]</code>&nbsp;。</li>
	<li>沿着垂直线&nbsp;<code>j</code>&nbsp;切开蛋糕，开销为&nbsp;<code>verticalCut[j]</code>&nbsp;。</li>
</ol>

<p>每次操作后，这块蛋糕都被切成两个独立的小蛋糕。</p>

<p>每次操作的开销都为最开始对应切割线的开销，并且不会改变。</p>

<p>请你返回将蛋糕全部切成&nbsp;<code>1 x 1</code>&nbsp;的蛋糕块的&nbsp;<strong>最小</strong>&nbsp;总开销。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>m = 3, n = 2, horizontalCut = [1,3], verticalCut = [5]</span></p>

<p><span class="example-io"><b>输出：</b>13</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3218.Minimum%20Cost%20for%20Cutting%20Cake%20I/images/ezgifcom-animated-gif-maker-1.gif" style="width: 280px; height: 320px;" /></p>

<ul>
	<li>沿着垂直线 0 切开蛋糕，开销为 5 。</li>
	<li>沿着水平线 0 切开&nbsp;<code>3 x 1</code>&nbsp;的蛋糕块，开销为 1 。</li>
	<li>沿着水平线 0 切开 <code>3 x 1</code>&nbsp;的蛋糕块，开销为 1 。</li>
	<li>沿着水平线 1 切开 <code>2 x 1</code>&nbsp;的蛋糕块，开销为 3 。</li>
	<li>沿着水平线 1 切开 <code>2 x 1</code>&nbsp;的蛋糕块，开销为 3 。</li>
</ul>

<p>总开销为&nbsp;<code>5 + 1 + 1 + 3 + 3 = 13</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>m = 2, n = 2, horizontalCut = [7], verticalCut = [4]</span></p>

<p><span class="example-io"><b>输出：</b>15</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>沿着水平线 0 切开蛋糕，开销为 7 。</li>
	<li>沿着垂直线 0 切开&nbsp;<code>1 x 2</code>&nbsp;的蛋糕块，开销为 4 。</li>
	<li>沿着垂直线 0 切开&nbsp;<code>1 x 2</code>&nbsp;的蛋糕块，开销为 4 。</li>
</ul>

<p>总开销为&nbsp;<code>7 + 4 + 4 = 15</code>&nbsp;。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 20</code></li>
	<li><code>horizontalCut.length == m - 1</code></li>
	<li><code>verticalCut.length == n - 1</code></li>
	<li><code>1 &lt;= horizontalCut[i], verticalCut[i] &lt;= 10<sup>3</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 双指针

对于一个位置，越早切，所需要切的次数越少，因此，显然是开销越大的位置越早切。

所以，我们可以对数组 $\textit{horizontalCut}$ 和 $\textit{verticalCut}$ 按照从大到小的顺序排序，然后使用两个指针 $i$ 和 $j$ 分别指向 $\textit{horizontalCut}$ 和 $\textit{verticalCut}$ 的开销，每次选择开销较大的位置进行切割，同时更新对应的行数和列数。

每次在水平方向上切割时，如果此前列数为 $v$，那么此次的开销为 $\textit{horizontalCut}[i] \times v$，然后行数 $h$ 加一；同理，每次在垂直方向上切割时，如果此前行数为 $h$，那么此次的开销为 $\textit{verticalCut}[j] \times h$，然后列数 $v$ 加一。

最后，当 $i$ 和 $j$ 都到达末尾时，返回总开销即可。

时间复杂度 $O(m \times \log m + n \times \log n)$，空间复杂度 $O(\log m + \log n)$。其中 $m$ 和 $n$ 分别为 $\textit{horizontalCut}$ 和 $\textit{verticalCut}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumCost(
        self, m: int, n: int, horizontalCut: List[int], verticalCut: List[int]
    ) -> int:
        horizontalCut.sort(reverse=True)
        verticalCut.sort(reverse=True)
        ans = i = j = 0
        h = v = 1
        while i < m - 1 or j < n - 1:
            if j == n - 1 or (i < m - 1 and horizontalCut[i] > verticalCut[j]):
                ans += horizontalCut[i] * v
                h, i = h + 1, i + 1
            else:
                ans += verticalCut[j] * h
                v, j = v + 1, j + 1
        return ans
```

#### Java

```java
class Solution {
    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);
        int ans = 0;
        int i = m - 2, j = n - 2;
        int h = 1, v = 1;
        while (i >= 0 || j >= 0) {
            if (j < 0 || (i >= 0 && horizontalCut[i] > verticalCut[j])) {
                ans += horizontalCut[i--] * v;
                ++h;
            } else {
                ans += verticalCut[j--] * h;
                ++v;
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
    int minimumCost(int m, int n, vector<int>& horizontalCut, vector<int>& verticalCut) {
        sort(horizontalCut.rbegin(), horizontalCut.rend());
        sort(verticalCut.rbegin(), verticalCut.rend());
        int ans = 0;
        int i = 0, j = 0;
        int h = 1, v = 1;
        while (i < m - 1 || j < n - 1) {
            if (j == n - 1 || (i < m - 1 && horizontalCut[i] > verticalCut[j])) {
                ans += horizontalCut[i++] * v;
                h++;
            } else {
                ans += verticalCut[j++] * h;
                v++;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minimumCost(m int, n int, horizontalCut []int, verticalCut []int) (ans int) {
	sort.Sort(sort.Reverse(sort.IntSlice(horizontalCut)))
	sort.Sort(sort.Reverse(sort.IntSlice(verticalCut)))
	i, j := 0, 0
	h, v := 1, 1
	for i < m-1 || j < n-1 {
		if j == n-1 || (i < m-1 && horizontalCut[i] > verticalCut[j]) {
			ans += horizontalCut[i] * v
			h++
			i++
		} else {
			ans += verticalCut[j] * h
			v++
			j++
		}
	}
	return
}
```

#### TypeScript

```ts
function minimumCost(m: number, n: number, horizontalCut: number[], verticalCut: number[]): number {
    horizontalCut.sort((a, b) => b - a);
    verticalCut.sort((a, b) => b - a);
    let ans = 0;
    let [i, j] = [0, 0];
    let [h, v] = [1, 1];
    while (i < m - 1 || j < n - 1) {
        if (j === n - 1 || (i < m - 1 && horizontalCut[i] > verticalCut[j])) {
            ans += horizontalCut[i] * v;
            h++;
            i++;
        } else {
            ans += verticalCut[j] * h;
            v++;
            j++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
