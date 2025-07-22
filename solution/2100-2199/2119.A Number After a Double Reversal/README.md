---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2119.A%20Number%20After%20a%20Double%20Reversal/README.md
rating: 1187
source: 第 273 场周赛 Q1
tags:
    - 数学
---

<!-- problem:start -->

# [2119. 反转两次的数字](https://leetcode.cn/problems/a-number-after-a-double-reversal)

[English Version](/solution/2100-2199/2119.A%20Number%20After%20a%20Double%20Reversal/README_EN.md)

## 题目描述

<!-- description:start -->

<p><strong>反转</strong> 一个整数意味着倒置它的所有位。</p>

<ul>
	<li>例如，反转 <code>2021</code> 得到 <code>1202</code> 。反转 <code>12300</code> 得到 <code>321</code> ，<strong>不保留前导零</strong> 。</li>
</ul>

<p>给你一个整数 <code>num</code> ，<strong>反转</strong> <code>num</code> 得到 <code>reversed1</code> ，<strong>接着反转</strong> <code>reversed1</code> 得到 <code>reversed2</code> 。如果 <code>reversed2</code> 等于 <code>num</code> ，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num = 526
<strong>输出：</strong>true
<strong>解释：</strong>反转 num 得到 625 ，接着反转 625 得到 526 ，等于 num 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num = 1800
<strong>输出：</strong>false
<strong>解释：</strong>反转 num 得到 81 ，接着反转 81 得到 18 ，不等于 num 。 </pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>num = 0
<strong>输出：</strong>true
<strong>解释：</strong>反转 num 得到 0 ，接着反转 0 得到 0 ，等于 num 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

如果数字是 $0$，或者数字的个位不是 $0$，那么反转两次后的数字一定和原数字相等。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isSameAfterReversals(self, num: int) -> bool:
        return num == 0 or num % 10 != 0
```

#### Java

```java
class Solution {
    public boolean isSameAfterReversals(int num) {
        return num == 0 || num % 10 != 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isSameAfterReversals(int num) {
        return num == 0 || num % 10 != 0;
    }
};
```

#### Go

```go
func isSameAfterReversals(num int) bool {
	return num == 0 || num%10 != 0
}
```

#### TypeScript

```ts
function isSameAfterReversals(num: number): boolean {
    return num === 0 || num % 10 !== 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_same_after_reversals(num: i32) -> bool {
        num == 0 || num % 10 != 0
    }
}
```

#### JavaScript

```js
/**
 * @param {number} num
 * @return {boolean}
 */
var isSameAfterReversals = function (num) {
    return num === 0 || num % 10 !== 0;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
