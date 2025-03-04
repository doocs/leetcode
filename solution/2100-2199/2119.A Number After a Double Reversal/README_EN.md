---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2119.A%20Number%20After%20a%20Double%20Reversal/README_EN.md
rating: 1187
source: Weekly Contest 273 Q1
tags:
    - Math
---

<!-- problem:start -->

# [2119. A Number After a Double Reversal](https://leetcode.com/problems/a-number-after-a-double-reversal)

[中文文档](/solution/2100-2199/2119.A%20Number%20After%20a%20Double%20Reversal/README.md)

## Description

<!-- description:start -->

<p><strong>Reversing</strong> an integer means to reverse all its digits.</p>

<ul>
	<li>For example, reversing <code>2021</code> gives <code>1202</code>. Reversing <code>12300</code> gives <code>321</code> as the <strong>leading zeros are not retained</strong>.</li>
</ul>

<p>Given an integer <code>num</code>, <strong>reverse</strong> <code>num</code> to get <code>reversed1</code>, <strong>then reverse</strong> <code>reversed1</code> to get <code>reversed2</code>. Return <code>true</code> <em>if</em> <code>reversed2</code> <em>equals</em> <code>num</code>. Otherwise return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 526
<strong>Output:</strong> true
<strong>Explanation:</strong> Reverse num to get 625, then reverse 625 to get 526, which equals num.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 1800
<strong>Output:</strong> false
<strong>Explanation:</strong> Reverse num to get 81, then reverse 81 to get 18, which does not equal num.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = 0
<strong>Output:</strong> true
<strong>Explanation:</strong> Reverse num to get 0, then reverse 0 to get 0, which equals num.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

If the number is $0$, or the last digit of the number is not $0$, then the number after reversing twice will be the same as the original number.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

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
