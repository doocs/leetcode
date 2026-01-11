---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3783.Mirror%20Distance%20of%20an%20Integer/README.md
rating: 1170
source: 第 481 场周赛 Q1
tags:
    - 数学
---

<!-- problem:start -->

# [3783. 整数的镜像距离](https://leetcode.cn/problems/mirror-distance-of-an-integer)

[English Version](/solution/3700-3799/3783.Mirror%20Distance%20of%20an%20Integer/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>。</p>

<p>定义它的&nbsp;<strong>镜像距离</strong>&nbsp;为：<code>abs(n - reverse(n))</code>​​​​​​​，其中 <code>reverse(n)</code> 表示将 <code>n</code> 的数字反转后形成的整数。</p>

<p>返回表示 <code>n</code> 的镜像距离的整数。</p>

<p>其中，<code>abs(x)</code> 表示 <code>x</code> 的绝对值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 25</span></p>

<p><strong>输出：</strong> <span class="example-io">27</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>reverse(25) = 52</code>。</li>
	<li>因此，答案为 <code>abs(25 - 52) = 27</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 10</span></p>

<p><strong>输出：</strong> <span class="example-io">9</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>reverse(10) = 01</code>，即 1。</li>
	<li>因此，答案为 <code>abs(10 - 1) = 9</code>。</li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 7</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>reverse(7) = 7</code>。</li>
	<li>因此，答案为 <code>abs(7 - 7) = 0</code>。</li>
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

我们定义一个函数 $\text{reverse}(x)$ 来反转整数 $x$ 的数字。具体地，我们初始化一个变量 $y$ 为 $0$，然后不断地将 $x$ 的最后一位数字添加到 $y$ 的末尾，并将 $x$ 去掉最后一位，直到 $x$ 变为 $0$。最终 $y$ 即为反转后的整数。

接下来，我们计算整数 $n$ 的镜像距离，即 $\text{abs}(n - \text{reverse}(n))$，并返回结果。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$，其中 $n$ 是输入整数的大小。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def mirrorDistance(self, n: int) -> int:
        return abs(n - int(str(n)[::-1]))
```

#### Java

```java
class Solution {
    public int mirrorDistance(int n) {
        return Math.abs(n - reverse(n));
    }

    private int reverse(int x) {
        int y = 0;
        for (; x > 0; x /= 10) {
            y = y * 10 + x % 10;
        }
        return y;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int mirrorDistance(int n) {
        auto reverse = [](int x) -> int {
            int y = 0;
            for (; x; x /= 10) {
                y = y * 10 + x % 10;
            }
            return y;
        };
        return abs(n - reverse(n));
    }
};
```

#### Go

```go
func mirrorDistance(n int) int {
	reverse := func(x int) int {
		y := 0
		for ; x > 0; x /= 10 {
			y = y*10 + x%10
		}
		return y
	}
	return abs(n - reverse(n))
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function mirrorDistance(n: number): number {
    const reverse = (x: number): number => {
        let y = 0;
        for (; x > 0; x = Math.floor(x / 10)) {
            y = y * 10 + (x % 10);
        }
        return y;
    };
    return Math.abs(n - reverse(n));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
