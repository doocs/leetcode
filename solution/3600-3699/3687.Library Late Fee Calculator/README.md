---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3687.Library%20Late%20Fee%20Calculator/README.md
tags:
    - 数组
    - 模拟
---

<!-- problem:start -->

# [3687. 图书馆逾期罚款计算器 🔒](https://leetcode.cn/problems/library-late-fee-calculator)

[English Version](/solution/3600-3699/3687.Library%20Late%20Fee%20Calculator/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>daysLate</code>，其中&nbsp;<code>daysLate[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;书的归还日期晚了几天。</p>

<p>罚款计算如下：</p>

<ul>
	<li>如果&nbsp;<code>daysLate[i] == 1</code>，罚款为 1。</li>
	<li>如果&nbsp;<code>2 &lt;= daysLate[i] &lt;= 5</code>，罚款为&nbsp;<code>2 * daysLate[i]</code>。</li>
	<li>如果&nbsp;<code>daysLate[i] &gt; 5</code>，罚款为&nbsp;<code>3 * daysLate[i]</code>。</li>
</ul>

<p>返回所有书的总罚款。</p>

<p>&nbsp;</p>

<p><strong class="example">示例&nbsp;1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>daysLate = [5,1,7]</span></p>

<p><span class="example-io"><b>输入：</b>32</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>daysLate[0] = 5</code>：罚款是&nbsp;<code>2 * daysLate[0] = 2 * 5 = 10</code>。</li>
	<li><code>daysLate[1] = 1</code>：罚款是&nbsp;<code>1</code>。</li>
	<li><code>daysLate[2] = 7</code>：罚款是&nbsp;<code>3 * daysLate[2] = 3 * 7 = 21</code>。</li>
	<li>因此，总罚款为&nbsp;<code>10 + 1 + 21 = 32</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>daysLate = [1,1]</span></p>

<p><span class="example-io"><b>输出：</b>2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>daysLate[0] = 1</code>：罚款为&nbsp;<code>1</code>。</li>
	<li><code>daysLate[1] = 1</code>：罚款为&nbsp;<code>1</code>。</li>
	<li>因此，总罚款为&nbsp;<code>1 + 1 = 2</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= daysLate.length &lt;= 100</code></li>
	<li><code>1 &lt;= daysLate[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

<!-- thinking:start -->

> **思考**
>
> 罚款按逾期天数分段：$1$ 天为 $1$，$2\ldots 5$ 天为 $2x$，更长为 $3x$。各项独立，求和即可。
>
> 函数 $f$ 实现三段，对 $\textit{daysLate}$ 映射累加。$n\le 100$，无需额外结构。

<!-- thinking:end -->


我们定义一个函数 $\text{f}(x)$ 来计算每本书的罚款：

$$
\text{f}(x) = \begin{cases}
1 & x = 1 \\
2x & 2 \leq x \leq 5 \\
3x & x > 5
\end{cases}
$$

然后我们对数组 $\textit{daysLate}$ 中的每个元素 $x$ 计算 $\text{f}(x)$ 并累加得到总罚款。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{daysLate}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lateFee(self, daysLate: List[int]) -> int:
        def f(x: int) -> int:
            if x == 1:
                return 1
            if x > 5:
                return 3 * x
            return 2 * x

        return sum(f(x) for x in daysLate)
```

#### Java

```java
class Solution {
    public int lateFee(int[] daysLate) {
        IntUnaryOperator f = x -> {
            if (x == 1) {
                return 1;
            } else if (x > 5) {
                return 3 * x;
            } else {
                return 2 * x;
            }
        };

        int ans = 0;
        for (int x : daysLate) {
            ans += f.applyAsInt(x);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int lateFee(vector<int>& daysLate) {
        auto f = [](int x) {
            if (x == 1) {
                return 1;
            } else if (x > 5) {
                return 3 * x;
            } else {
                return 2 * x;
            }
        };

        int ans = 0;
        for (int x : daysLate) {
            ans += f(x);
        }
        return ans;
    }
};
```

#### Go

```go
func lateFee(daysLate []int) (ans int) {
	f := func(x int) int {
		if x == 1 {
			return 1
		} else if x > 5 {
			return 3 * x
		}
		return 2 * x
	}
	for _, x := range daysLate {
		ans += f(x)
	}
	return
}
```

#### TypeScript

```ts
function lateFee(daysLate: number[]): number {
    const f = (x: number): number => {
        if (x === 1) {
            return 1;
        } else if (x > 5) {
            return 3 * x;
        }
        return 2 * x;
    };
    return daysLate.reduce((acc, days) => acc + f(days), 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
