---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1954.Minimum%20Garden%20Perimeter%20to%20Collect%20Enough%20Apples/README_EN.md
rating: 1758
source: Weekly Contest 252 Q3
tags:
    - Math
    - Binary Search
---

<!-- problem:start -->

# [1954. Minimum Garden Perimeter to Collect Enough Apples](https://leetcode.com/problems/minimum-garden-perimeter-to-collect-enough-apples)

[中文文档](/solution/1900-1999/1954.Minimum%20Garden%20Perimeter%20to%20Collect%20Enough%20Apples/README.md)

## Description

<!-- description:start -->

<p>In a garden represented as an infinite 2D grid, there is an apple tree planted at <strong>every</strong> integer coordinate. The apple tree planted at an integer coordinate <code>(i, j)</code> has <code>|i| + |j|</code> apples growing on it.</p>

<p>You will buy an axis-aligned <strong>square plot</strong> of land that is centered at <code>(0, 0)</code>.</p>

<p>Given an integer <code>neededApples</code>, return <em>the <strong>minimum perimeter</strong> of a plot such that <strong>at least</strong></em><strong> </strong><code>neededApples</code> <em>apples are <strong>inside or on</strong> the perimeter of that plot</em>.</p>

<p>The value of <code>|x|</code> is defined as:</p>

<ul>
	<li><code>x</code> if <code>x &gt;= 0</code></li>
	<li><code>-x</code> if <code>x &lt; 0</code></li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1954.Minimum%20Garden%20Perimeter%20to%20Collect%20Enough%20Apples/images/1527_example_1_2.png" style="width: 442px; height: 449px;" />
<pre>
<strong>Input:</strong> neededApples = 1
<strong>Output:</strong> 8
<strong>Explanation:</strong> A square plot of side length 1 does not contain any apples.
However, a square plot of side length 2 has 12 apples inside (as depicted in the image above).
The perimeter is 2 * 4 = 8.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> neededApples = 13
<strong>Output:</strong> 16
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> neededApples = 1000000000
<strong>Output:</strong> 5040
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= neededApples &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

###  Find the math formula for the number of apples inside a square with a side length L:

Let r be the radius, which is half the side length of the square area, there're apples planted along its perimeter:
- 4 corners, each corner is $|r| + |r| = 2|r|$, so $8|r|$
- middle top and middle bottom (when x axis = 0), each $|0| + |r| = |r|$, so $2|r|$
- middle left and middle right (when y axis = 0), each $|0| + |r| = |r|$, so $2|r|$
- between middle-top and top-right conner([1,r], [2,r], ..., [r-1,r]):

    $|r-1||r| + (1 + 2 + ... + r-1) = |r-1||r| + |r||r-1|/2$

    And we have 8 of them: middle-top to top-left, middle-left to top-left, ...
    
    So we have: $8(|r-1||r| + |r||r-1|/2)$


=> the total number of apples around the perimeter is:

$8|r| + 4|r| + 8|r-1||r| + 4|r||r-1|
= 12|r| + 8r^2 - 8|r| + 4r^2 - 4|r|
= 12r^2$

So the sum of apples inside a square with a side length L ($r = L/2$, perimeter = $L*4 = r*8$):
    
$$12\left( \sum_{k=1}^r k^2 \right) = 12\left(r(r+1)(2r+1)/6\right) = 2r(r+1)(2r+1)$$


<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumPerimeter(self, neededApples: int) -> int:
        x = 1
        while 2 * x * (x + 1) * (2 * x + 1) < neededApples:
            x += 1
        return x * 8
```

#### Java

```java
class Solution {
    public long minimumPerimeter(long neededApples) {
        long x = 1;
        while (2 * x * (x + 1) * (2 * x + 1) < neededApples) {
            ++x;
        }
        return 8 * x;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minimumPerimeter(long long neededApples) {
        long long x = 1;
        while (2 * x * (x + 1) * (2 * x + 1) < neededApples) {
            ++x;
        }
        return 8 * x;
    }
};
```

#### Go

```go
func minimumPerimeter(neededApples int64) int64 {
	var x int64 = 1
	for 2*x*(x+1)*(2*x+1) < neededApples {
		x++
	}
	return 8 * x
}
```

#### TypeScript

```ts
function minimumPerimeter(neededApples: number): number {
    let x = 1;
    while (2 * x * (x + 1) * (2 * x + 1) < neededApples) {
        ++x;
    }
    return 8 * x;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumPerimeter(self, neededApples: int) -> int:
        l, r = 1, 100000
        while l < r:
            mid = (l + r) >> 1
            if 2 * mid * (mid + 1) * (2 * mid + 1) >= neededApples:
                r = mid
            else:
                l = mid + 1
        return l * 8
```

#### Java

```java
class Solution {
    public long minimumPerimeter(long neededApples) {
        long l = 1, r = 100000;
        while (l < r) {
            long mid = (l + r) >> 1;
            if (2 * mid * (mid + 1) * (2 * mid + 1) >= neededApples) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l * 8;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minimumPerimeter(long long neededApples) {
        long long l = 1, r = 100000;
        while (l < r) {
            long mid = (l + r) >> 1;
            if (2 * mid * (mid + 1) * (2 * mid + 1) >= neededApples) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l * 8;
    }
};
```

#### Go

```go
func minimumPerimeter(neededApples int64) int64 {
	var l, r int64 = 1, 100000
	for l < r {
		mid := (l + r) >> 1
		if 2*mid*(mid+1)*(2*mid+1) >= neededApples {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return l * 8
}
```

#### TypeScript

```ts
function minimumPerimeter(neededApples: number): number {
    let l = 1;
    let r = 100000;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (2 * mid * (mid + 1) * (2 * mid + 1) >= neededApples) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return 8 * l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
