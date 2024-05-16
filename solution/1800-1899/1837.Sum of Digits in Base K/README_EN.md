---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1837.Sum%20of%20Digits%20in%20Base%20K/README_EN.md
rating: 1282
source: Weekly Contest 238 Q1
tags:
    - Math
---

<!-- problem:start -->

# [1837. Sum of Digits in Base K](https://leetcode.com/problems/sum-of-digits-in-base-k)

[中文文档](/solution/1800-1899/1837.Sum%20of%20Digits%20in%20Base%20K/README.md)

## Description

<p>Given an integer <code>n</code> (in base <code>10</code>) and a base <code>k</code>, return <em>the <strong>sum</strong> of the digits of </em><code>n</code><em> <strong>after</strong> converting </em><code>n</code><em> from base </em><code>10</code><em> to base </em><code>k</code>.</p>

<p>After converting, each digit should be interpreted as a base <code>10</code> number, and the sum should be returned in base <code>10</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 34, k = 6
<strong>Output:</strong> 9
<strong>Explanation: </strong>34 (base 10) expressed in base 6 is 54. 5 + 4 = 9.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 10, k = 10
<strong>Output:</strong> 1
<strong>Explanation: </strong>n is already in base 10. 1 + 0 = 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>2 &lt;= k &lt;= 10</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

We divide $n$ by $k$ and take the remainder until it is $0$. The sum of the remainders gives the result.

The time complexity is $O(\log_{k}n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def sumBase(self, n: int, k: int) -> int:
        ans = 0
        while n:
            ans += n % k
            n //= k
        return ans
```

```java
class Solution {
    public int sumBase(int n, int k) {
        int ans = 0;
        while (n != 0) {
            ans += n % k;
            n /= k;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int sumBase(int n, int k) {
        int ans = 0;
        while (n) {
            ans += n % k;
            n /= k;
        }
        return ans;
    }
};
```

```go
func sumBase(n int, k int) (ans int) {
	for n > 0 {
		ans += n % k
		n /= k
	}
	return
}
```

```ts
function sumBase(n: number, k: number): number {
    let ans = 0;
    while (n) {
        ans += n % k;
        n = Math.floor(n / k);
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn sum_base(mut n: i32, k: i32) -> i32 {
        let mut ans = 0;
        while n != 0 {
            ans += n % k;
            n /= k;
        }
        ans
    }
}
```

```js
/**
 * @param {number} n
 * @param {number} k
 * @return {number}
 */
var sumBase = function (n, k) {
    let ans = 0;
    while (n) {
        ans += n % k;
        n = Math.floor(n / k);
    }
    return ans;
};
```

```c
int sumBase(int n, int k) {
    int ans = 0;
    while (n) {
        ans += n % k;
        n /= k;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
