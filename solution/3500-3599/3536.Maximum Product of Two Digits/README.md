---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3536.Maximum%20Product%20of%20Two%20Digits/README.md
tags:
    - 数学
    - 排序
---

<!-- problem:start -->

# [3536. 两个数字的最大乘积](https://leetcode.cn/problems/maximum-product-of-two-digits)

[English Version](/solution/3500-3599/3536.Maximum%20Product%20of%20Two%20Digits/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个正整数 <code>n</code>。</p>

<p>返回 <strong>任意两位数字&nbsp;</strong>相乘所得的&nbsp;<strong>最大&nbsp;</strong>乘积。</p>

<p><strong>注意：</strong>如果某个数字在 <code>n</code> 中出现多次，你可以多次使用该数字。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 31</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>n</code> 的数字是 <code>[3, 1]</code>。</li>
	<li>任意两位数字相乘的结果为：<code>3 * 1 = 3</code>。</li>
	<li>最大乘积为 3。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 22</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>n</code> 的数字是 <code>[2, 2]</code>。</li>
	<li>任意两位数字相乘的结果为：<code>2 * 2 = 4</code>。</li>
	<li>最大乘积为 4。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 124</span></p>

<p><strong>输出：</strong> <span class="example-io">8</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>n</code> 的数字是 <code>[1, 2, 4]</code>。</li>
	<li>任意两位数字相乘的结果为：<code>1 * 2 = 2</code>, <code>1 * 4 = 4</code>, <code>2 * 4 = 8</code>。</li>
	<li>最大乘积为 8。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>10 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：找到最大和次大数字

我们用两个变量 $a$ 和 $b$ 来记录当前最大的数字和次大的数字。我们遍历 $n$ 的每一位数字，如果当前数字大于 $a$，则将 $b$ 赋值为 $a$，然后将 $a$ 赋值为当前数字；否则，如果当前数字大于 $b$，则将 $b$ 赋值为当前数字。最后返回 $a \times b$ 即可。

时间复杂度 $O(\log n)$，其中 $n$ 是输入数字的大小。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProduct(self, n: int) -> int:
        a = b = 0
        while n:
            n, x = divmod(n, 10)
            if a < x:
                a, b = x, a
            elif b < x:
                b = x
        return a * b
```

#### Java

```java
class Solution {
    public int maxProduct(int n) {
        int a = 0, b = 0;
        for (; n > 0; n /= 10) {
            int x = n % 10;
            if (a < x) {
                b = a;
                a = x;
            } else if (b < x) {
                b = x;
            }
        }
        return a * b;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxProduct(int n) {
        int a = 0, b = 0;
        for (; n; n /= 10) {
            int x = n % 10;
            if (a < x) {
                b = a;
                a = x;
            } else if (b < x) {
                b = x;
            }
        }
        return a * b;
    }
};
```

#### Go

```go
func maxProduct(n int) int {
	a, b := 0, 0
	for ; n > 0; n /= 10 {
		x := n % 10
		if a < x {
			b, a = a, x
		} else if b < x {
			b = x
		}
	}
	return a * b
}
```

#### TypeScript

```ts
function maxProduct(n: number): number {
    let [a, b] = [0, 0];
    for (; n; n = Math.floor(n / 10)) {
        const x = n % 10;
        if (a < x) {
            [a, b] = [x, a];
        } else if (b < x) {
            b = x;
        }
    }
    return a * b;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
