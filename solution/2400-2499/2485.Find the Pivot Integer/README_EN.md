# [2485. Find the Pivot Integer](https://leetcode.com/problems/find-the-pivot-integer)

[中文文档](/solution/2400-2499/2485.Find%20the%20Pivot%20Integer/README.md)

## Description

<p>Given a positive integer <code>n</code>, find the <strong>pivot integer</strong> <code>x</code> such that:</p>

<ul>
	<li>The sum of all elements between <code>1</code> and <code>x</code> inclusively equals the sum of all elements between <code>x</code> and <code>n</code> inclusively.</li>
</ul>

<p>Return <em>the pivot integer </em><code>x</code>. If no such integer exists, return <code>-1</code>. It is guaranteed that there will be at most one pivot index for the given input.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 8
<strong>Output:</strong> 6
<strong>Explanation:</strong> 6 is the pivot integer since: 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> 1 is the pivot integer since: 1 = 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> -1
<strong>Explanation:</strong> It can be proved that no such integer exist.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## Solutions

### Solution 1: Enumeration

We can directly enumerate $x$ in the range of $[1,..n]$, and check whether the following equation holds. If it holds, then $x$ is the pivot integer, and we can directly return $x$.

$$
(1 + x) \times x = (x + n) \times (n - x + 1)
$$

The time complexity is $O(n)$, where $n$ is the given positive integer $n$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def pivotInteger(self, n: int) -> int:
        for x in range(1, n + 1):
            if (1 + x) * x == (x + n) * (n - x + 1):
                return x
        return -1
```

```java
class Solution {
    public int pivotInteger(int n) {
        for (int x = 1; x <= n; ++x) {
            if ((1 + x) * x == (x + n) * (n - x + 1)) {
                return x;
            }
        }
        return -1;
    }
}
```

```cpp
class Solution {
public:
    int pivotInteger(int n) {
        for (int x = 1; x <= n; ++x) {
            if ((1 + x) * x == (x + n) * (n - x + 1)) {
                return x;
            }
        }
        return -1;
    }
};
```

```go
func pivotInteger(n int) int {
	for x := 1; x <= n; x++ {
		if (1+x)*x == (x+n)*(n-x+1) {
			return x
		}
	}
	return -1
}
```

```ts
function pivotInteger(n: number): number {
    for (let x = 1; x <= n; ++x) {
        if ((1 + x) * x === (x + n) * (n - x + 1)) {
            return x;
        }
    }
    return -1;
}
```

```rust
impl Solution {
    pub fn pivot_integer(n: i32) -> i32 {
        let y = (n * (n + 1)) / 2;
        let x = (y as f64).sqrt() as i32;

        if x * x == y {
            return x;
        }

        -1
    }
}
```

```php
class Solution {
    /**
     * @param Integer $n
     * @return Integer
     */
    function pivotInteger($n) {
        $sum = ($n * ($n + 1)) / 2;
        $pre = 0;
        for ($i = 1; $i <= $n; $i++) {
            if ($pre + $i === $sum - $pre) {
                return $i;
            }
            $pre += $i;
        }
        return -1;
    }
}
```

<!-- tabs:end -->

### Solution 2: Mathematics

We can transform the above equation to get:

$$
n \times (n + 1) = 2 \times x^2
$$

That is:

$$
x = \sqrt{\frac{n \times (n + 1)}{2}}
$$

If $x$ is an integer, then $x$ is the pivot integer, otherwise there is no pivot integer.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def pivotInteger(self, n: int) -> int:
        y = n * (n + 1) // 2
        x = int(sqrt(y))
        return x if x * x == y else -1
```

```java
class Solution {
    public int pivotInteger(int n) {
        int y = n * (n + 1) / 2;
        int x = (int) Math.sqrt(y);
        return x * x == y ? x : -1;
    }
}
```

```cpp
class Solution {
public:
    int pivotInteger(int n) {
        int y = n * (n + 1) / 2;
        int x = sqrt(y);
        return x * x == y ? x : -1;
    }
};
```

```go
func pivotInteger(n int) int {
	y := n * (n + 1) / 2
	x := int(math.Sqrt(float64(y)))
	if x*x == y {
		return x
	}
	return -1
}
```

```ts
function pivotInteger(n: number): number {
    const y = Math.floor((n * (n + 1)) / 2);
    const x = Math.floor(Math.sqrt(y));
    return x * x === y ? x : -1;
}
```

<!-- tabs:end -->

<!-- end -->
