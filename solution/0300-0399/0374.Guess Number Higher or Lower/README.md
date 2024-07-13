---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0374.Guess%20Number%20Higher%20or%20Lower/README.md
tags:
    - 二分查找
    - 交互
---

<!-- problem:start -->

# [374. 猜数字大小](https://leetcode.cn/problems/guess-number-higher-or-lower)

[English Version](/solution/0300-0399/0374.Guess%20Number%20Higher%20or%20Lower/README_EN.md)

## 题目描述

<!-- description:start -->

<p>我们正在玩猜数字游戏。猜数字游戏的规则如下：</p>

<p>我会从&nbsp;<code>1</code>&nbsp;到&nbsp;<code>n</code> 随机选择一个数字。 请你猜选出的是哪个数字。</p>

<p>如果你猜错了，我会告诉你，我选出的数字比你猜测的数字大了还是小了。</p>

<p>你可以通过调用一个预先定义好的接口 <code>int guess(int num)</code> 来获取猜测结果，返回值一共有三种可能的情况：</p>

<ul>
	<li><code>-1</code>：你猜的数字比我选出的数字大 （即&nbsp;<code>num &gt; pick</code>）。</li>
	<li><code>1</code>：你猜的数字比我选出的数字小&nbsp;（即&nbsp;<code>num &lt;&nbsp;pick</code>）。</li>
	<li><code>0</code>：你猜的数字与我选出的数字相等。（即&nbsp;<code>num&nbsp;== pick</code>）。</li>
</ul>

<p>返回我选出的数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 10, pick = 6
<strong>输出：</strong>6
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1, pick = 1
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 2, pick = 1
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>1 &lt;= pick &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们在区间 $[1,..n]$ 进行二分查找，找到第一个满足 `guess(x) <= 0` 的数，即为答案。

时间复杂度 $O(\log n)$。其中 $n$ 为题目给定的上限。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
# The guess API is already defined for you.
# @param num, your guess
# @return -1 if num is higher than the picked number
#          1 if num is lower than the picked number
#          otherwise return 0
# def guess(num: int) -> int:


class Solution:
    def guessNumber(self, n: int) -> int:
        return bisect.bisect(range(1, n + 1), 0, key=lambda x: -guess(x))
```

#### Java

```java
/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (guess(mid) <= 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

#### C++

```cpp
/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * int guess(int num);
 */

class Solution {
public:
    int guessNumber(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (guess(mid) <= 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
```

#### Go

```go
/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * func guess(num int) int;
 */

func guessNumber(n int) int {
	return sort.Search(n, func(i int) bool {
		i++
		return guess(i) <= 0
	}) + 1
}
```

#### TypeScript

```ts
/**
 * Forward declaration of guess API.
 * @param {number} num   your guess
 * @return 	            -1 if num is lower than the guess number
 *			             1 if num is higher than the guess number
 *                       otherwise return 0
 * var guess = function(num) {}
 */

function guessNumber(n: number): number {
    let l = 1;
    let r = n;
    while (l < r) {
        const mid = (l + r) >>> 1;
        if (guess(mid) <= 0) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
```

#### Rust

```rust
/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * unsafe fn guess(num: i32) -> i32 {}
 */

impl Solution {
    unsafe fn guessNumber(n: i32) -> i32 {
        let mut l = 1;
        let mut r = n;
        loop {
            let mid = l + (r - l) / 2;
            match guess(mid) {
                -1 => {
                    r = mid - 1;
                }
                1 => {
                    l = mid + 1;
                }
                _ => {
                    break mid;
                }
            }
        }
    }
}
```

#### C#

```cs
/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution : GuessGame {
    public int GuessNumber(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (guess(mid) <= 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
