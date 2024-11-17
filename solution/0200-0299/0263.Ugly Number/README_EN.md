---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0263.Ugly%20Number/README_EN.md
tags:
    - Math
---

<!-- problem:start -->

# [263. Ugly Number](https://leetcode.com/problems/ugly-number)

[中文文档](/solution/0200-0299/0263.Ugly%20Number/README.md)

## Description

<!-- description:start -->

<p>An <strong>ugly number</strong> is a <em>positive</em> integer which does not have a prime factor other than 2, 3, and 5.</p>

<p>Given an integer <code>n</code>, return <code>true</code> <em>if</em> <code>n</code> <em>is an <strong>ugly number</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 6
<strong>Output:</strong> true
<strong>Explanation:</strong> 6 = 2 &times; 3
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> true
<strong>Explanation:</strong> 1 has no prime factors.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 14
<strong>Output:</strong> false
<strong>Explanation:</strong> 14 is not ugly since it includes the prime factor 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isUgly(self, n: int) -> bool:
        if n < 1:
            return False
        for x in [2, 3, 5]:
            while n % x == 0:
                n //= x
        return n == 1
```

#### Java

```java
class Solution {
    public boolean isUgly(int n) {
        if (n < 1) return false;
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isUgly(int n) {
        if (n < 1) return false;
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }
};
```

#### Go

```go
func isUgly(n int) bool {
	if n < 1 {
		return false
	}
	for _, x := range []int{2, 3, 5} {
		for n%x == 0 {
			n /= x
		}
	}
	return n == 1
}
```

#### JavaScript

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isUgly = function (n) {
    if (n < 1) return false;
    while (n % 2 === 0) {
        n /= 2;
    }
    while (n % 3 === 0) {
        n /= 3;
    }
    while (n % 5 === 0) {
        n /= 5;
    }
    return n === 1;
};
```

#### PHP

```php
class Solution {
    /**
     * @param Integer $n
     * @return Boolean
     */
    function isUgly($n) {
        while ($n) {
            if ($n % 2 == 0) {
                $n = $n / 2;
            } elseif ($n % 3 == 0) {
                $n = $n / 3;
            } elseif ($n % 5 == 0) {
                $n = $n / 5;
            } else {
                break;
            }
        }
        return $n == 1;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
