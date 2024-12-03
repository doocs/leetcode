---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3370.Smallest%20Number%20With%20All%20Set%20Bits/README.md
tags:
    - 位运算
    - 数学
---

<!-- problem:start -->

# [3370. 仅含置位位的最小整数](https://leetcode.cn/problems/smallest-number-with-all-set-bits)

[English Version](/solution/3300-3399/3370.Smallest%20Number%20With%20All%20Set%20Bits/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个正整数 <code>n</code>。</p>

<p>返回&nbsp;<strong>大于等于</strong> <code>n</code>&nbsp;且二进制表示仅包含&nbsp;<strong>置位&nbsp;</strong>位的&nbsp;<strong>最小&nbsp;</strong>整数 <code>x</code>&nbsp;。</p>

<p><strong>置位&nbsp;</strong>位指的是二进制表示中值为 <code>1</code> 的位。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 5</span></p>

<p><strong>输出：</strong> <span class="example-io">7</span></p>

<p><strong>解释：</strong></p>

<p>7 的二进制表示是 <code>"111"</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 10</span></p>

<p><strong>输出：</strong> <span class="example-io">15</span></p>

<p><strong>解释：</strong></p>

<p>15 的二进制表示是 <code>"1111"</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 3</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>3 的二进制表示是 <code>"11"</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

我们从 $x = 1$ 开始，不断将 $x$ 左移，直到 $x - 1 \geq n$，此时 $x - 1$ 就是我们要找的答案。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestNumber(self, n: int) -> int:
        x = 1
        while x - 1 < n:
            x <<= 1
        return x - 1
```

#### Java

```java
class Solution {
    public int smallestNumber(int n) {
        int x = 1;
        while (x - 1 < n) {
            x <<= 1;
        }
        return x - 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestNumber(int n) {
        int x = 1;
        while (x - 1 < n) {
            x <<= 1;
        }
        return x - 1;
    }
};
```

#### Go

```go
func smallestNumber(n int) int {
	x := 1
	for x-1 < n {
		x <<= 1
	}
	return x - 1
}
```

#### TypeScript

```ts
function smallestNumber(n: number): number {
    let x = 1;
    while (x - 1 < n) {
        x <<= 1;
    }
    return x - 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
