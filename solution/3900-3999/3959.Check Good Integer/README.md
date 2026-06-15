---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3959.Check%20Good%20Integer/README.md
---

<!-- problem:start -->

# [3959. 判定好整数](https://leetcode.cn/problems/check-good-integer)

[English Version](/solution/3900-3999/3959.Check%20Good%20Integer/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数 <code>n</code>。</p>

<p>令 <code>digitSum</code> 表示 <code>n</code> 的各位数字之和，令 <code>squareSum</code> 表示 <code>n</code> 的各位数字平方之和。</p>

<p>如果一个整数满足 <code>squareSum - digitSum &gt;= 50</code>，则称它是&nbsp;<strong>好整数&nbsp;</strong>。</p>

<p>如果 <code>n</code> 是好整数，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 1000</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>1000 的数字为 1、0、0 和 0。</li>
	<li><code>digitSum</code> 为 <code>1 + 0 + 0 + 0 = 1</code>。</li>
	<li><code>squareSum</code> 为 <code>1<sup>2</sup> + 0<sup>2</sup> + 0<sup>2</sup> + 0<sup>2</sup> = 1</code>。</li>
	<li><code>squareSum - digitSum</code> 为 <code>1 - 1 = 0</code>。由于 0 小于 50，因此输出 <code>false</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 19</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>19 的数字为 1 和 9。</li>
	<li><code>digitSum</code> 为 <code>1 + 9 = 10</code>。</li>
	<li><code>squareSum</code> 为 <code>1<sup>2</sup> + 9<sup>2</sup> = 1 + 81 = 82</code>。</li>
	<li><code>squareSum - digitSum</code> 为 <code>82 - 10 = 72</code>。由于 72 大于等于 50，因此输出 <code>true</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们用一个变量 $s$ 来记录 $n$ 的各位数字的平方和减去各位数字之和的结果，如果 $s$ 大于等于 50，则返回 $\textit{true}$，否则返回 $\textit{false}$。

时间复杂度 $O(\log n)$，其中 $\log n$ 是数字 $n$ 的位数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkGoodInteger(self, n: int) -> bool:
        s = 0
        while n:
            n, x = divmod(n, 10)
            s += x * (x - 1)
        return s >= 50
```

#### Java

```java
class Solution {
    public boolean checkGoodInteger(int n) {
        int s = 0;
        for (; n > 0; n /= 10) {
            int x = n % 10;
            s += x * (x - 1);
        }
        return s >= 50;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkGoodInteger(int n) {
        int s = 0;
        for (; n > 0; n /= 10) {
            int x = n % 10;
            s += x * (x - 1);
        }
        return s >= 50;
    }
};
```

#### Go

```go
func checkGoodInteger(n int) bool {
    s := 0
    for ; n > 0; n /= 10 {
        x := n % 10
        s += x * (x - 1)
    }
    return s >= 50
}
```

#### TypeScript

```ts
function checkGoodInteger(n: number): boolean {
    let s: number = 0;
    for (; n; n = Math.floor(n / 10)) {
        const x = n % 10;
        s += x * (x - 1);
    }
    return s >= 50;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
