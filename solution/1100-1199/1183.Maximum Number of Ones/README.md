# [1183. 矩阵中 1 的最大数量 🔒](https://leetcode.cn/problems/maximum-number-of-ones)

[English Version](/solution/1100-1199/1183.Maximum%20Number%20of%20Ones/README_EN.md)

<!-- tags:贪心,堆（优先队列） -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>现在有一个尺寸为 <code>width * height</code>&nbsp;的矩阵&nbsp;<code>M</code>，矩阵中的每个单元格的值不是&nbsp;<code>0</code>&nbsp;就是&nbsp;<code>1</code>。</p>

<p>而且矩阵 <code>M</code> 中每个大小为&nbsp;<code>sideLength * sideLength</code>&nbsp;的 <strong>正方形</strong> 子阵中，<code>1</code> 的数量不得超过&nbsp;<code>maxOnes</code>。</p>

<p>请你设计一个算法，计算矩阵中最多可以有多少个 <code>1</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>width = 3, height = 3, sideLength = 2, maxOnes = 1
<strong>输出：</strong>4
<strong>解释：</strong>
题目要求：在一个 3*3 的矩阵中，每一个 2*2 的子阵中的 1 的数目不超过 1 个。
最好的解决方案中，矩阵 M 里最多可以有 4 个 1，如下所示：
[1,0,1]
[0,0,0]
[1,0,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>width = 3, height = 3, sideLength = 2, maxOnes = 2
<strong>输出：</strong>6
<strong>解释：</strong>
[1,0,1]
[1,0,1]
[1,0,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= width, height &lt;= 100</code></li>
	<li><code>1 &lt;= sideLength &lt;= width, height</code></li>
	<li><code>0 &lt;= maxOnes &lt;= sideLength * sideLength</code></li>
</ul>

## 解法

### 方法一：统计等效位置

为了方便说明，我们不妨令 $x = sideLength$。

考虑一个 $x\times x$ 的正方形，我们需要在正方形里面取最多 $maxOnes$ 个点，将其置为 1。注意到当坐标 $(i, j)$ 处的点被选取后，所有坐标为 $(i\pm k_1 \times x, j\pm k_2 \times x)$ 的点都可以等效地置为 1。因此，我们算出坐标 $(i, j)$ 在矩阵中的等效位置的数量，取数量最多的前 $maxOnes$ 个即可。

时间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def maximumNumberOfOnes(
        self, width: int, height: int, sideLength: int, maxOnes: int
    ) -> int:
        x = sideLength
        cnt = [0] * (x * x)
        for i in range(width):
            for j in range(height):
                k = (i % x) * x + (j % x)
                cnt[k] += 1
        cnt.sort(reverse=True)
        return sum(cnt[:maxOnes])
```

```java
class Solution {
    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        int x = sideLength;
        int[] cnt = new int[x * x];
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                int k = (i % x) * x + (j % x);
                ++cnt[k];
            }
        }
        Arrays.sort(cnt);
        int ans = 0;
        for (int i = 0; i < maxOnes; ++i) {
            ans += cnt[cnt.length - i - 1];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        int x = sideLength;
        vector<int> cnt(x * x);
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                int k = (i % x) * x + (j % x);
                ++cnt[k];
            }
        }
        sort(cnt.rbegin(), cnt.rend());
        int ans = 0;
        for (int i = 0; i < maxOnes; ++i) {
            ans += cnt[i];
        }
        return ans;
    }
};
```

```go
func maximumNumberOfOnes(width int, height int, sideLength int, maxOnes int) int {
	x := sideLength
	cnt := make([]int, x*x)
	for i := 0; i < width; i++ {
		for j := 0; j < height; j++ {
			k := (i%x)*x + (j % x)
			cnt[k]++
		}
	}
	sort.Ints(cnt)
	ans := 0
	for i := range cnt[:maxOnes] {
		ans += cnt[len(cnt)-i-1]
	}
	return ans
}
```

```js
/**
 * @param {number} width
 * @param {number} height
 * @param {number} sideLength
 * @param {number} maxOnes
 * @return {number}
 */
var maximumNumberOfOnes = function (width, height, sideLength, maxOnes) {
    const x = sideLength;
    const cnt = new Array(x * x).fill(0);
    for (let i = 0; i < width; ++i) {
        for (let j = 0; j < height; ++j) {
            const k = (i % x) * x + (j % x);
            ++cnt[k];
        }
    }
    cnt.sort((a, b) => b - a);
    return cnt.slice(0, maxOnes).reduce((a, b) => a + b, 0);
};
```

<!-- tabs:end -->

<!-- end -->
