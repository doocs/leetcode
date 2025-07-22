---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2145.Count%20the%20Hidden%20Sequences/README.md
rating: 1614
source: 第 70 场双周赛 Q2
tags:
    - 数组
    - 前缀和
---

<!-- problem:start -->

# [2145. 统计隐藏数组数目](https://leetcode.cn/problems/count-the-hidden-sequences)

[English Version](/solution/2100-2199/2145.Count%20the%20Hidden%20Sequences/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始且长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>differences</code>&nbsp;，它表示一个长度为&nbsp;<code>n + 1</code>&nbsp;的&nbsp;<strong>隐藏</strong>&nbsp;数组&nbsp;<strong>相邻</strong>&nbsp;元素之间的&nbsp;<strong>差值</strong>&nbsp;。更正式的表述为：我们将隐藏数组记作&nbsp;<code>hidden</code>&nbsp;，那么&nbsp;<code>differences[i] = hidden[i + 1] - hidden[i]</code>&nbsp;。</p>

<p>同时给你两个整数&nbsp;<code>lower</code> 和&nbsp;<code>upper</code>&nbsp;，它们表示隐藏数组中所有数字的值都在 <strong>闭</strong>&nbsp;区间&nbsp;<code>[lower, upper]</code>&nbsp;之间。</p>

<ul>
	<li>比方说，<code>differences = [1, -3, 4]</code>&nbsp;，<code>lower = 1</code>&nbsp;，<code>upper = 6</code>&nbsp;，那么隐藏数组是一个长度为 <code>4</code>&nbsp;且所有值都在&nbsp;<code>1</code>&nbsp;和&nbsp;<code>6</code>&nbsp;（包含两者）之间的数组。

    <ul>
    	<li><code>[3, 4, 1, 5]</code> 和&nbsp;<code>[4, 5, 2, 6]</code>&nbsp;都是符合要求的隐藏数组。</li>
    	<li><code>[5, 6, 3, 7]</code>&nbsp;不符合要求，因为它包含大于 <code>6</code>&nbsp;的元素。</li>
    	<li><code>[1, 2, 3, 4]</code>&nbsp;不符合要求，因为相邻元素的差值不符合给定数据。</li>
    </ul>
    </li>

</ul>

<p>请你返回 <strong>符合</strong>&nbsp;要求的隐藏数组的数目。如果没有符合要求的隐藏数组，请返回 <code>0</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>differences = [1,-3,4], lower = 1, upper = 6
<b>输出：</b>2
<b>解释：</b>符合要求的隐藏数组为：
- [3, 4, 1, 5]
- [4, 5, 2, 6]
所以返回 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>differences = [3,-4,5,1,-2], lower = -4, upper = 5
<b>输出：</b>4
<b>解释：</b>符合要求的隐藏数组为：
- [-3, 0, -4, 1, 2, 0]
- [-2, 1, -3, 2, 3, 1]
- [-1, 2, -2, 3, 4, 2]
- [0, 3, -1, 4, 5, 3]
所以返回 4 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>differences = [4,-7,2], lower = 3, upper = 6
<b>输出：</b>0
<b>解释：</b>没有符合要求的隐藏数组，所以返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == differences.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= differences[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= lower &lt;= upper &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和

由于数组 $\textit{differences}$ 已经确定，那么数组 $\textit{hidden}$ 的元素最大值与最小值之差也是固定的，我们只要确保差值不超过 $\textit{upper} - \textit{lower}$ 即可。

我们不妨假设数组 $\textit{hidden}$ 的第一个元素为 $0$，那么 $\textit{hidden}[i] = \textit{hidden}[i - 1] + \textit{differences}[i - 1]$，其中 $1 \leq i \leq n$。记数组 $\textit{hidden}$ 的最大值为 $mx$，最小值为 $mi$，如果 $mx - mi \leq \textit{upper} - \textit{lower}$，那么我们就可以构造出一个合法的 $\textit{hidden}$ 数组，可以构造的个数为 $\textit{upper} - \textit{lower} - (mx - mi) + 1$。否则，无法构造出合法的 $\textit{hidden}$ 数组，返回 $0$。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{differences}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfArrays(self, differences: List[int], lower: int, upper: int) -> int:
        x = mi = mx = 0
        for d in differences:
            x += d
            mi = min(mi, x)
            mx = max(mx, x)
        return max(upper - lower - (mx - mi) + 1, 0)
```

#### Java

```java
class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long x = 0, mi = 0, mx = 0;
        for (int d : differences) {
            x += d;
            mi = Math.min(mi, x);
            mx = Math.max(mx, x);
        }
        return (int) Math.max(upper - lower - (mx - mi) + 1, 0);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfArrays(vector<int>& differences, int lower, int upper) {
        long long x = 0, mi = 0, mx = 0;
        for (int d : differences) {
            x += d;
            mi = min(mi, x);
            mx = max(mx, x);
        }
        return max(upper - lower - (mx - mi) + 1, 0LL);
    }
};
```

#### Go

```go
func numberOfArrays(differences []int, lower int, upper int) int {
	x, mi, mx := 0, 0, 0
	for _, d := range differences {
		x += d
		mi = min(mi, x)
		mx = max(mx, x)
	}
	return max(0, upper-lower-(mx-mi)+1)
}
```

#### TypeScript

```ts
function numberOfArrays(differences: number[], lower: number, upper: number): number {
    let [x, mi, mx] = [0, 0, 0];
    for (const d of differences) {
        x += d;
        mi = Math.min(mi, x);
        mx = Math.max(mx, x);
    }
    return Math.max(0, upper - lower - (mx - mi) + 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
