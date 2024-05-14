# [1465. 切割后面积最大的蛋糕](https://leetcode.cn/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts)

[English Version](/solution/1400-1499/1465.Maximum%20Area%20of%20a%20Piece%20of%20Cake%20After%20Horizontal%20and%20Vertical%20Cuts/README_EN.md)

<!-- tags:贪心,数组,排序 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>矩形蛋糕的高度为 <code>h</code> 且宽度为 <code>w</code>，给你两个整数数组 <code>horizontalCuts</code> 和 <code>verticalCuts</code>，其中：</p>

<ul>
	<li>&nbsp;<code>horizontalCuts[i]</code> 是从矩形蛋糕顶部到第&nbsp; <code>i</code> 个水平切口的距离</li>
	<li><code>verticalCuts[j]</code> 是从矩形蛋糕的左侧到第 <code>j</code> 个竖直切口的距离</li>
</ul>

<p>请你按数组 <em><code>horizontalCuts</code> </em>和<em> <code>verticalCuts</code> </em>中提供的水平和竖直位置切割后，请你找出 <strong>面积最大</strong> 的那份蛋糕，并返回其 <strong>面积</strong> 。由于答案可能是一个很大的数字，因此需要将结果&nbsp;<strong>对</strong>&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code>&nbsp;<strong>取余</strong> 后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1465.Maximum%20Area%20of%20a%20Piece%20of%20Cake%20After%20Horizontal%20and%20Vertical%20Cuts/images/leetcode_max_area_2.png" /></p>

<pre>
<strong>输入：</strong>h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
<strong>输出：</strong>4 
<strong>解释：</strong>上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色的那份蛋糕面积最大。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1465.Maximum%20Area%20of%20a%20Piece%20of%20Cake%20After%20Horizontal%20and%20Vertical%20Cuts/images/leetcode_max_area_3.png" /></strong></p>

<pre>
<strong>输入：</strong>h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
<strong>输出：</strong>6
<strong>解释：</strong>上图所示的矩阵蛋糕中，红色线表示水平和竖直方向上的切口。切割蛋糕后，绿色和黄色的两份蛋糕面积最大。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
<strong>输出：</strong>9
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= h, w &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= horizontalCuts.length &lt;= min(h - 1, 10<sup>5</sup>)</code></li>
	<li><code>1 &lt;= verticalCuts.length &lt;= min(w - 1, 10<sup>5</sup>)</code></li>
	<li><code>1 &lt;= horizontalCuts[i] &lt; h</code></li>
	<li><code>1 &lt;= verticalCuts[i] &lt; w</code></li>
	<li>题目数据保证 <code>horizontalCuts</code> 中的所有元素各不相同</li>
	<li>题目数据保证 <code>verticalCuts</code>&nbsp;中的所有元素各不相同</li>
</ul>

## 解法

### 方法一：排序

我们先分别对 `horizontalCuts` 和 `verticalCuts` 排序，然后分别遍历两个数组，计算相邻两个元素的最大差值，分别记为 $x$ 和 $y$，最后返回 $x \times y$ 即可。

注意要考虑边界情况，即 `horizontalCuts` 和 `verticalCuts` 的首尾元素。

时间复杂度 $O(m\log m + n\log n)$，空间复杂度 $(\log m + \log n)$。其中 $m$ 和 $n$ 分别为 `horizontalCuts` 和 `verticalCuts` 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maxArea(
        self, h: int, w: int, horizontalCuts: List[int], verticalCuts: List[int]
    ) -> int:
        horizontalCuts.extend([0, h])
        verticalCuts.extend([0, w])
        horizontalCuts.sort()
        verticalCuts.sort()
        x = max(b - a for a, b in pairwise(horizontalCuts))
        y = max(b - a for a, b in pairwise(verticalCuts))
        return (x * y) % (10**9 + 7)
```

```java
class Solution {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        final int mod = (int) 1e9 + 7;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int m = horizontalCuts.length;
        int n = verticalCuts.length;
        long x = Math.max(horizontalCuts[0], h - horizontalCuts[m - 1]);
        long y = Math.max(verticalCuts[0], w - verticalCuts[n - 1]);
        for (int i = 1; i < m; ++i) {
            x = Math.max(x, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        for (int i = 1; i < n; ++i) {
            y = Math.max(y, verticalCuts[i] - verticalCuts[i - 1]);
        }
        return (int) ((x * y) % mod);
    }
}
```

```cpp
class Solution {
public:
    int maxArea(int h, int w, vector<int>& horizontalCuts, vector<int>& verticalCuts) {
        horizontalCuts.push_back(0);
        horizontalCuts.push_back(h);
        verticalCuts.push_back(0);
        verticalCuts.push_back(w);
        sort(horizontalCuts.begin(), horizontalCuts.end());
        sort(verticalCuts.begin(), verticalCuts.end());
        int x = 0, y = 0;
        for (int i = 1; i < horizontalCuts.size(); ++i) {
            x = max(x, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        for (int i = 1; i < verticalCuts.size(); ++i) {
            y = max(y, verticalCuts[i] - verticalCuts[i - 1]);
        }
        const int mod = 1e9 + 7;
        return (1ll * x * y) % mod;
    }
};
```

```go
func maxArea(h int, w int, horizontalCuts []int, verticalCuts []int) int {
	horizontalCuts = append(horizontalCuts, []int{0, h}...)
	verticalCuts = append(verticalCuts, []int{0, w}...)
	sort.Ints(horizontalCuts)
	sort.Ints(verticalCuts)
	x, y := 0, 0
	const mod int = 1e9 + 7
	for i := 1; i < len(horizontalCuts); i++ {
		x = max(x, horizontalCuts[i]-horizontalCuts[i-1])
	}
	for i := 1; i < len(verticalCuts); i++ {
		y = max(y, verticalCuts[i]-verticalCuts[i-1])
	}
	return (x * y) % mod
}
```

```ts
function maxArea(h: number, w: number, horizontalCuts: number[], verticalCuts: number[]): number {
    const mod = 1e9 + 7;
    horizontalCuts.push(0, h);
    verticalCuts.push(0, w);
    horizontalCuts.sort((a, b) => a - b);
    verticalCuts.sort((a, b) => a - b);
    let [x, y] = [0, 0];
    for (let i = 1; i < horizontalCuts.length; i++) {
        x = Math.max(x, horizontalCuts[i] - horizontalCuts[i - 1]);
    }
    for (let i = 1; i < verticalCuts.length; i++) {
        y = Math.max(y, verticalCuts[i] - verticalCuts[i - 1]);
    }
    return Number((BigInt(x) * BigInt(y)) % BigInt(mod));
}
```

```rust
impl Solution {
    pub fn max_area(
        h: i32,
        w: i32,
        mut horizontal_cuts: Vec<i32>,
        mut vertical_cuts: Vec<i32>
    ) -> i32 {
        const MOD: i64 = 1_000_000_007;

        horizontal_cuts.sort();
        vertical_cuts.sort();

        let m = horizontal_cuts.len();
        let n = vertical_cuts.len();

        let mut x = i64::max(
            horizontal_cuts[0] as i64,
            (h as i64) - (horizontal_cuts[m - 1] as i64)
        );
        let mut y = i64::max(vertical_cuts[0] as i64, (w as i64) - (vertical_cuts[n - 1] as i64));

        for i in 1..m {
            x = i64::max(x, (horizontal_cuts[i] as i64) - (horizontal_cuts[i - 1] as i64));
        }

        for i in 1..n {
            y = i64::max(y, (vertical_cuts[i] as i64) - (vertical_cuts[i - 1] as i64));
        }

        ((x * y) % MOD) as i32
    }
}
```

<!-- tabs:end -->

<!-- end -->
