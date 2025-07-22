---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0000-0099/0066.Plus%20One/README.md
tags:
    - 数组
    - 数学
---

<!-- problem:start -->

# [66. 加一](https://leetcode.cn/problems/plus-one)

[English Version](/solution/0000-0099/0066.Plus%20One/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个表示 <strong>大整数</strong> 的整数数组 <code>digits</code>，其中 <code>digits[i]</code> 是整数的第 <code>i</code> 位数字。这些数字按从左到右，从最高位到最低位排列。这个大整数不包含任何前导 <code>0</code>。</p>

<p>将大整数加 1，并返回结果的数字数组。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>digits = [1,2,3]
<strong>输出：</strong>[1,2,4]
<strong>解释：</strong>输入数组表示数字 123。
加 1 后得到 123 + 1 = 124。
因此，结果应该是 [1,2,4]。
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>digits = [4,3,2,1]
<strong>输出：</strong>[4,3,2,2]
<strong>解释：</strong>输入数组表示数字 4321。
加 1 后得到 4321 + 1 = 4322。
因此，结果应该是 [4,3,2,2]。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>digits = [9]
<strong>输出：</strong>[1,0]
<strong>解释：</strong>输入数组表示数字 9。
加 1 得到了 9 + 1 = 10。
因此，结果应该是 [1,0]。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= digits.length &lt;= 100</code></li>
	<li><code>0 &lt;= digits[i] &lt;= 9</code></li>
	<li><code>digits</code>&nbsp;不包含任何前导 <code>0</code>。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们从数组的最后一个元素开始遍历，将当前元素加一，然后对 $10$ 取模，如果取模后的结果不为 $0$，说明当前元素没有进位，直接返回数组即可。否则，当前元素为 $0$，需要进位，继续遍历前一个元素，重复上述操作。如果遍历完数组后，仍然没有返回，说明数组中所有元素都为 $0$，需要在数组的头部插入一个 $1$。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def plusOne(self, digits: List[int]) -> List[int]:
        n = len(digits)
        for i in range(n - 1, -1, -1):
            digits[i] += 1
            digits[i] %= 10
            if digits[i] != 0:
                return digits
        return [1] + digits
```

#### Java

```java
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; --i) {
            ++digits[i];
            digits[i] %= 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[n + 1];
        digits[0] = 1;
        return digits;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
        for (int i = digits.size() - 1; i >= 0; --i) {
            ++digits[i];
            digits[i] %= 10;
            if (digits[i] != 0) return digits;
        }
        digits.insert(digits.begin(), 1);
        return digits;
    }
};
```

#### Go

```go
func plusOne(digits []int) []int {
	n := len(digits)
	for i := n - 1; i >= 0; i-- {
		digits[i]++
		digits[i] %= 10
		if digits[i] != 0 {
			return digits
		}
	}
	return append([]int{1}, digits...)
}
```

#### TypeScript

```ts
function plusOne(digits: number[]): number[] {
    const n = digits.length;
    for (let i = n - 1; i >= 0; i--) {
        if (10 > ++digits[i]) {
            return digits;
        }
        digits[i] %= 10;
    }
    return [1, ...digits];
}
```

#### Rust

```rust
impl Solution {
    pub fn plus_one(mut digits: Vec<i32>) -> Vec<i32> {
        let n = digits.len();
        for i in (0..n).rev() {
            digits[i] += 1;
            if 10 > digits[i] {
                return digits;
            }
            digits[i] %= 10;
        }
        digits.insert(0, 1);
        digits
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} digits
 * @return {number[]}
 */
var plusOne = function (digits) {
    for (let i = digits.length - 1; i >= 0; --i) {
        ++digits[i];
        digits[i] %= 10;
        if (digits[i] != 0) {
            return digits;
        }
    }
    return [1, ...digits];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
