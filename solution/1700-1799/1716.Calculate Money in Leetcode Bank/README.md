---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1716.Calculate%20Money%20in%20Leetcode%20Bank/README.md
rating: 1294
source: 第 43 场双周赛 Q1
tags:
    - 数学
---

<!-- problem:start -->

# [1716. 计算力扣银行的钱](https://leetcode.cn/problems/calculate-money-in-leetcode-bank)

[English Version](/solution/1700-1799/1716.Calculate%20Money%20in%20Leetcode%20Bank/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Hercy 想要为购买第一辆车存钱。他 <strong>每天</strong> 都往力扣银行里存钱。</p>

<p>最开始，他在周一的时候存入 <code>1</code> 块钱。从周二到周日，他每天都比前一天多存入 <code>1</code> 块钱。在接下来每一个周一，他都会比 <strong>前一个周一</strong> 多存入 <code>1</code> 块钱。<span style=""> </span></p>

<p>给你 <code>n</code> ，请你返回在第 <code>n</code> 天结束的时候他在力扣银行总共存了多少块钱。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>n = 4
<b>输出：</b>10
<b>解释：</b>第 4 天后，总额为 1 + 2 + 3 + 4 = 10 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>n = 10
<b>输出：</b>37
<b>解释：</b>第 10 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37 。注意到第二个星期一，Hercy 存入 2 块钱。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>n = 20
<b>输出：</b>96
<b>解释：</b>第 20 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

根据题目描述，每周的存款情况如下：

```
第 1 周：1, 2, 3, 4, 5, 6, 7
第 2 周：2, 3, 4, 5, 6, 7, 8
第 3 周：3, 4, 5, 6, 7, 8, 9
...
第 k 周：k, k+1, k+2, k+3, k+4, k+5, k+6
```

存款天数为 $n$，那么完整的周数为 $k = \lfloor n / 7 \rfloor$，剩余天数为 $b = n \mod 7$。

完整的 $k$ 周的存款总额，可以通过等差数列求和公式计算：

$$
S_1 = \frac{k}{2} \times (28 + 28 + 7 \times (k - 1))
$$

剩余的 $b$ 天的存款总额，同样可以通过等差数列求和公式计算：

$$
S_2 = \frac{b}{2} \times (k + 1 + k + 1 + b - 1)
$$

最终的总存款金额为 $S = S_1 + S_2$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def totalMoney(self, n: int) -> int:
        k, b = divmod(n, 7)
        s1 = (28 + 28 + 7 * (k - 1)) * k // 2
        s2 = (k + 1 + k + 1 + b - 1) * b // 2
        return s1 + s2
```

#### Java

```java
class Solution {
    public int totalMoney(int n) {
        int k = n / 7, b = n % 7;
        int s1 = (28 + 28 + 7 * (k - 1)) * k / 2;
        int s2 = (k + 1 + k + 1 + b - 1) * b / 2;
        return s1 + s2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int totalMoney(int n) {
        int k = n / 7, b = n % 7;
        int s1 = (28 + 28 + 7 * (k - 1)) * k / 2;
        int s2 = (k + 1 + k + 1 + b - 1) * b / 2;
        return s1 + s2;
    }
};
```

#### Go

```go
func totalMoney(n int) int {
	k, b := n/7, n%7
	s1 := (28 + 28 + 7*(k-1)) * k / 2
	s2 := (k + 1 + k + 1 + b - 1) * b / 2
	return s1 + s2
}
```

#### TypeScript

```ts
function totalMoney(n: number): number {
    const k = (n / 7) | 0;
    const b = n % 7;
    const s1 = (((28 + 28 + 7 * (k - 1)) * k) / 2) | 0;
    const s2 = (((k + 1 + k + 1 + b - 1) * b) / 2) | 0;
    return s1 + s2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
