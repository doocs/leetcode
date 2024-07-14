---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0319.Bulb%20Switcher/README.md
tags:
    - 脑筋急转弯
    - 数学
---

<!-- problem:start -->

# [319. 灯泡开关](https://leetcode.cn/problems/bulb-switcher)

[English Version](/solution/0300-0399/0319.Bulb%20Switcher/README_EN.md)

## 题目描述

<!-- description:start -->

<p>初始时有&nbsp;<code>n</code><em> </em>个灯泡处于关闭状态。第一轮，你将会打开所有灯泡。接下来的第二轮，你将会每两个灯泡关闭第二个。</p>

<p>第三轮，你每三个灯泡就切换第三个灯泡的开关（即，打开变关闭，关闭变打开）。第 <code>i</code> 轮，你每 <code>i</code> 个灯泡就切换第 <code>i</code> 个灯泡的开关。直到第 <code>n</code> 轮，你只需要切换最后一个灯泡的开关。</p>

<p>找出并返回 <code>n</code><em>&nbsp;</em>轮后有多少个亮着的灯泡。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0319.Bulb%20Switcher/images/bulb.jpg" style="width: 421px; height: 321px;" /></p>

<pre>
<strong>输入：</strong>n =<strong> </strong>3
<strong>输出：</strong>1 
<strong>解释：</strong>
初始时, 灯泡状态 <strong>[关闭, 关闭, 关闭]</strong>.
第一轮后, 灯泡状态 <strong>[开启, 开启, 开启]</strong>.
第二轮后, 灯泡状态 <strong>[开启, 关闭, 开启]</strong>.
第三轮后, 灯泡状态 <strong>[开启, 关闭, 关闭]</strong>. 

你应该返回 1，因为只有一个灯泡还亮着。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 0
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

我们不妨将 $n$ 个灯泡编号为 $1, 2, 3, \cdots, n$，那么对于第 $i$ 个灯泡，它会在第 $d$ 轮被操作，当且仅当 $d$ 是 $i$ 的因子。

对于一个数 $i$，它的因子个数是有限的，且因子个数为奇数时，最后的状态是开启的，否则是关闭的。

因此，我们只需要找到 $1$ 到 $n$ 中因子个数为奇数的数的个数即可。

对于一个数 $i$，如果它有因子 $d$，那么它一定有因子 $i/d$，因此因子个数为奇数的数一定是平方数。

举个例子，数字 $12$ 的因子有 $1, 2, 3, 4, 6, 12$，因子个数为 $6$，是偶数；而对于数字 $16$ 这个平方数，因子有 $1, 2, 4, 8, 16$，因子个数为 $5$，是奇数。

因此，我们只需要找到 $1$ 到 $n$ 中有多少个平方数即可，即 $\lfloor \sqrt{n} \rfloor$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def bulbSwitch(self, n: int) -> int:
        return int(sqrt(n))
```

#### Java

```java
class Solution {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int bulbSwitch(int n) {
        return (int) sqrt(n);
    }
};
```

#### Go

```go
func bulbSwitch(n int) int {
	return int(math.Sqrt(float64(n)))
}
```

#### TypeScript

```ts
function bulbSwitch(n: number): number {
    return Math.floor(Math.sqrt(n));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
