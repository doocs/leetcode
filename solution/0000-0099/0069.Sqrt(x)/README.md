# [69. x 的平方根 ](https://leetcode.cn/problems/sqrtx)

[English Version](/solution/0000-0099/0069.Sqrt%28x%29/README_EN.md)

<!-- tags:数学,二分查找 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个非负整数 <code>x</code> ，计算并返回&nbsp;<code>x</code>&nbsp;的 <strong>算术平方根</strong> 。</p>

<p>由于返回类型是整数，结果只保留 <strong>整数部分 </strong>，小数部分将被 <strong>舍去 。</strong></p>

<p><strong>注意：</strong>不允许使用任何内置指数函数和算符，例如 <code>pow(x, 0.5)</code> 或者 <code>x ** 0.5</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 4
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>x = 8
<strong>输出：</strong>2
<strong>解释：</strong>8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= x &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

### 方法一：二分查找

我们定义二分查找的左边界 $l = 0$，右边界 $r = x$，然后在 $[l, r]$ 范围内查找平方根。

在每一步查找中，我们找出中间值 $mid = (l + r + 1) / 2$，如果 $mid > x / mid$，说明平方根在 $[l, mid - 1]$ 范围内，我们令 $r = mid - 1$；否则说明平方根在 $[mid, r]$ 范围内，我们令 $l = mid$。

查找结束后，返回 $l$ 即可。

时间复杂度 $O(\log x)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def mySqrt(self, x: int) -> int:
        l, r = 0, x
        while l < r:
            mid = (l + r + 1) >> 1
            if mid > x // mid:
                r = mid - 1
            else:
                l = mid
        return l
```

```java
class Solution {
    public int mySqrt(int x) {
        int l = 0, r = x;
        while (l < r) {
            int mid = (l + r + 1) >>> 1;
            if (mid > x / mid) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }
}
```

```cpp
class Solution {
public:
    int mySqrt(int x) {
        int l = 0, r = x;
        while (l < r) {
            int mid = (l + r + 1ll) >> 1;
            if (mid > x / mid) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }
};
```

```go
func mySqrt(x int) int {
	return sort.Search(x+1, func(i int) bool { return i*i > x }) - 1
}
```

```rust
impl Solution {
    pub fn my_sqrt(x: i32) -> i32 {
        let mut l = 0;
        let mut r = x;

        while l < r {
            let mid = (l + r + 1) / 2;

            if mid > x / mid {
                r = mid - 1;
            } else {
                l = mid;
            }
        }

        l
    }
}
```

```js
/**
 * @param {number} x
 * @return {number}
 */
var mySqrt = function (x) {
    let [l, r] = [0, x];
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (mid > x / mid) {
            r = mid - 1;
        } else {
            l = mid;
        }
    }
    return l;
};
```

```cs
public class Solution {
    public int MySqrt(int x) {
        int l = 0, r = x;
        while (l < r) {
            int mid = (l + r + 1) >>> 1;
            if (mid > x / mid) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }
}
```

<!-- tabs:end -->

<!-- end -->
