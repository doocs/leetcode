---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1881.Maximum%20Value%20after%20Insertion/README.md
rating: 1381
source: 第 243 场周赛 Q2
tags:
    - 贪心
    - 字符串
---

<!-- problem:start -->

# [1881. 插入后的最大值](https://leetcode.cn/problems/maximum-value-after-insertion)

[English Version](/solution/1800-1899/1881.Maximum%20Value%20after%20Insertion/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个非常大的整数 <code>n</code> 和一个整数数字 <code>x</code> ，大整数 <code>n</code> 用一个字符串表示。<code>n</code> 中每一位数字和数字 <code>x</code> 都处于闭区间 <code>[1, 9]</code> 中，且 <code>n</code> 可能表示一个 <strong>负数</strong> 。</p>

<p>你打算通过在 <code>n</code> 的十进制表示的任意位置插入 <code>x</code> 来 <strong>最大化</strong> <code>n</code> 的 <strong>数值</strong> ​​​​​​。但 <strong>不能</strong> 在负号的左边插入 <code>x</code> 。</p>

<ul>
	<li>例如，如果 <code>n = 73</code> 且 <code>x = 6</code> ，那么最佳方案是将 <code>6</code> 插入 <code>7</code> 和 <code>3</code> 之间，使 <code>n = 763</code> 。</li>
	<li>如果 <code>n = -55</code> 且 <code>x = 2</code> ，那么最佳方案是将 <code>2</code> 插在第一个 <code>5</code> 之前，使 <code>n = -255</code> 。</li>
</ul>

<p>返回插入操作后，用字符串表示的 <code>n</code> 的最大值。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = "99", x = 9
<strong>输出：</strong>"999"
<strong>解释：</strong>不管在哪里插入 9 ，结果都是相同的。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = "-13", x = 2
<strong>输出：</strong>"-123"
<strong>解释：</strong>向 n 中插入 x 可以得到 -213、-123 或者 -132 ，三者中最大的是 -123 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= x <= 9</code></li>
	<li><code>n</code>​​​ 中每一位的数字都在闭区间 <code>[1, 9]</code> 中。</li>
	<li><code>n</code> 代表一个有效的整数。</li>
	<li>当 <code>n</code> 表示负数时，将会以字符 <code>'-'</code> 开始。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

如果 $n$ 是负数，那么我们要找到第一个大于 $x$ 的位置，然后在这个位置插入 $x$；如果 $n$ 是正数，那么我们要找到第一个小于 $x$ 的位置，然后在这个位置插入 $x$。

时间复杂度 $O(m)$，其中 $m$ 为 $n$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxValue(self, n: str, x: int) -> str:
        i = 0
        if n[0] == "-":
            i += 1
            while i < len(n) and int(n[i]) <= x:
                i += 1
        else:
            while i < len(n) and int(n[i]) >= x:
                i += 1
        return n[:i] + str(x) + n[i:]
```

#### Java

```java
class Solution {
    public String maxValue(String n, int x) {
        int i = 0;
        if (n.charAt(0) == '-') {
            ++i;
            while (i < n.length() && n.charAt(i) - '0' <= x) {
                ++i;
            }
        } else {
            while (i < n.length() && n.charAt(i) - '0' >= x) {
                ++i;
            }
        }
        return n.substring(0, i) + x + n.substring(i);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string maxValue(string n, int x) {
        int i = 0;
        if (n[0] == '-') {
            ++i;
            while (i < n.size() && n[i] - '0' <= x) {
                ++i;
            }
        } else {
            while (i < n.size() && n[i] - '0' >= x) {
                ++i;
            }
        }
        n.insert(i, 1, x + '0');
        return n;
    }
};
```

#### Go

```go
func maxValue(n string, x int) string {
	i := 0
	y := byte('0' + x)
	if n[0] == '-' {
		i++
		for i < len(n) && n[i] <= y {
			i++
		}
	} else {
		for i < len(n) && n[i] >= y {
			i++
		}
	}
	return n[:i] + string(y) + n[i:]
}
```

#### TypeScript

```ts
function maxValue(n: string, x: number): string {
    let i = 0;
    if (n[0] === '-') {
        i++;
        while (i < n.length && +n[i] <= x) {
            i++;
        }
    } else {
        while (i < n.length && +n[i] >= x) {
            i++;
        }
    }
    return n.slice(0, i) + x + n.slice(i);
}
```

#### Rust

```rust
impl Solution {
    pub fn max_value(n: String, x: i32) -> String {
        let s = n.as_bytes();
        let mut i = 0;
        if n.starts_with('-') {
            i += 1;
            while i < s.len() && (s[i] - b'0') as i32 <= x {
                i += 1;
            }
        } else {
            while i < s.len() && (s[i] - b'0') as i32 >= x {
                i += 1;
            }
        }
        let mut ans = String::new();
        ans.push_str(&n[0..i]);
        ans.push_str(&x.to_string());
        ans.push_str(&n[i..]);
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {string} n
 * @param {number} x
 * @return {string}
 */
var maxValue = function (n, x) {
    let i = 0;
    if (n[0] === '-') {
        i++;
        while (i < n.length && +n[i] <= x) {
            i++;
        }
    } else {
        while (i < n.length && +n[i] >= x) {
            i++;
        }
    }
    return n.slice(0, i) + x + n.slice(i);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
