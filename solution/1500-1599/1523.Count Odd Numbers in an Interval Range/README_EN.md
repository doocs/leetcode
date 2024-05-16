---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1523.Count%20Odd%20Numbers%20in%20an%20Interval%20Range/README_EN.md
rating: 1209
source: Biweekly Contest 31 Q1
tags:
    - Math
---

<!-- problem:start -->

# [1523. Count Odd Numbers in an Interval Range](https://leetcode.com/problems/count-odd-numbers-in-an-interval-range)

[中文文档](/solution/1500-1599/1523.Count%20Odd%20Numbers%20in%20an%20Interval%20Range/README.md)

## Description

<p>Given two non-negative integers <code>low</code> and <code><font face="monospace">high</font></code>. Return the <em>count of odd numbers between </em><code>low</code><em> and </em><code><font face="monospace">high</font></code><em>&nbsp;(inclusive)</em>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> low = 3, high = 7

<strong>Output:</strong> 3

<b>Explanation: </b>The odd numbers between 3 and 7 are [3,5,7].</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> low = 8, high = 10

<strong>Output:</strong> 1

<b>Explanation: </b>The odd numbers between 8 and 10 are [9].</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>0 &lt;= low &lt;= high&nbsp;&lt;= 10^9</code></li>

</ul>

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def countOdds(self, low: int, high: int) -> int:
        return ((high + 1) >> 1) - (low >> 1)
```

```java
class Solution {
    public int countOdds(int low, int high) {
        return ((high + 1) >> 1) - (low >> 1);
    }
}
```

```cpp
class Solution {
public:
    int countOdds(int low, int high) {
        return (high + 1 >> 1) - (low >> 1);
    }
};
```

```go
func countOdds(low int, high int) int {
	return ((high + 1) >> 1) - (low >> 1)
}
```

```ts
function countOdds(low: number, high: number): number {
    return ((high + 1) >> 1) - (low >> 1);
}
```

```rust
impl Solution {
    pub fn count_odds(low: i32, high: i32) -> i32 {
        ((high + 1) >> 1) - (low >> 1)
    }
}
```

```php
class Solution {
    /**
     * @param Integer $low
     * @param Integer $high
     * @return Integer
     */
    function countOdds($low, $high) {
        return ($high + 1 >> 1) - ($low >> 1);
    }
}
```

```c
int countOdds(int low, int high) {
    return ((high + 1) >> 1) - (low >> 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
