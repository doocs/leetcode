---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2481.Minimum%20Cuts%20to%20Divide%20a%20Circle/README.md
rating: 1246
source: 第 92 场双周赛 Q1
tags:
    - 几何
    - 数学
---

<!-- problem:start -->

# [2481. 分割圆的最少切割次数](https://leetcode.cn/problems/minimum-cuts-to-divide-a-circle)

[English Version](/solution/2400-2499/2481.Minimum%20Cuts%20to%20Divide%20a%20Circle/README_EN.md)

## 题目描述

<!-- description:start -->

<p>圆内一个 <strong>有效切割</strong>&nbsp;，符合以下二者之一：</p>

<ul>
	<li>该切割是两个端点在圆上的线段，且该线段经过圆心。</li>
	<li>该切割是一端在圆心另一端在圆上的线段。</li>
</ul>

<p>一些有效和无效的切割如下图所示。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2481.Minimum%20Cuts%20to%20Divide%20a%20Circle/images/alldrawio.png" style="width: 450px; height: 174px;" /></p>

<p>给你一个整数&nbsp;<code>n</code>&nbsp;，请你返回将圆切割成相等的&nbsp;<code>n</code>&nbsp;等分的&nbsp;<strong>最少</strong>&nbsp;切割次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2481.Minimum%20Cuts%20to%20Divide%20a%20Circle/images/11drawio.png" style="width: 200px; height: 200px;" /></p>

<pre>
<b>输入：</b>n = 4
<b>输出：</b>2
<b>解释：</b>
上图展示了切割圆 2 次，得到四等分。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2481.Minimum%20Cuts%20to%20Divide%20a%20Circle/images/22drawio.png" style="width: 200px; height: 201px;" /></p>

<pre>
<b>输入：</b>n = 3
<b>输出：</b>3
<strong>解释：</strong>
最少需要切割 3 次，将圆切成三等分。
少于 3 次切割无法将圆切成大小相等面积相同的 3 等分。
同时可以观察到，第一次切割无法将圆切割开。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分类讨论

- 当 $n=1$ 时，不需要切割，即切割次数为 $0$；
- 当 $n$ 为奇数时，不存在共线的情况，最少需要 $n$ 次切割；
- 当 $n$ 为偶数时，可以两两共线，最少需要 $\frac{n}{2}$ 次切割。

综上，可以得到：

$$
\textit{ans} = \begin{cases}
n, & n \gt 1 \textit{ 且 } n \textit{ 为奇数} \\
\frac{n}{2}, & n \textit{ 为其它} \\
\end{cases}
$$

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfCuts(self, n: int) -> int:
        return n if (n > 1 and n & 1) else n >> 1
```

#### Java

```java
class Solution {
    public int numberOfCuts(int n) {
        return n > 1 && n % 2 == 1 ? n : n >> 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfCuts(int n) {
        return n > 1 && n % 2 == 1 ? n : n >> 1;
    }
};
```

#### Go

```go
func numberOfCuts(n int) int {
	if n > 1 && n%2 == 1 {
		return n
	}
	return n >> 1
}
```

#### TypeScript

```ts
function numberOfCuts(n: number): number {
    return n > 1 && n & 1 ? n : n >> 1;
}
```

#### Rust

```rust
impl Solution {
    pub fn number_of_cuts(n: i32) -> i32 {
        if n > 1 && n % 2 == 1 {
            return n;
        }
        n >> 1
    }
}
```

#### C#

```cs
public class Solution {
    public int NumberOfCuts(int n) {
        return n > 1 && n % 2 == 1 ? n : n >> 1;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
