---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2427.Number%20of%20Common%20Factors/README_EN.md
rating: 1172
source: Weekly Contest 313 Q1
tags:
    - Math
    - Enumeration
    - Number Theory
---

<!-- problem:start -->

# [2427. Number of Common Factors](https://leetcode.com/problems/number-of-common-factors)

[中文文档](/solution/2400-2499/2427.Number%20of%20Common%20Factors/README.md)

## Description

<p>Given two positive integers <code>a</code> and <code>b</code>, return <em>the number of <strong>common</strong> factors of </em><code>a</code><em> and </em><code>b</code>.</p>

<p>An integer <code>x</code> is a <strong>common factor</strong> of <code>a</code> and <code>b</code> if <code>x</code> divides both <code>a</code> and <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = 12, b = 6
<strong>Output:</strong> 4
<strong>Explanation:</strong> The common factors of 12 and 6 are 1, 2, 3, 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = 25, b = 30
<strong>Output:</strong> 2
<strong>Explanation:</strong> The common factors of 25 and 30 are 1, 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a, b &lt;= 1000</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can first calculate the greatest common divisor $g$ of $a$ and $b$, then enumerate each number in $[1,..g]$, check whether it is a factor of $g$, if it is, then increment the answer by one.

The time complexity is $O(\min(a, b))$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def commonFactors(self, a: int, b: int) -> int:
        g = gcd(a, b)
        return sum(g % x == 0 for x in range(1, g + 1))
```

```java
class Solution {
    public int commonFactors(int a, int b) {
        int g = gcd(a, b);
        int ans = 0;
        for (int x = 1; x <= g; ++x) {
            if (g % x == 0) {
                ++ans;
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

```cpp
class Solution {
public:
    int commonFactors(int a, int b) {
        int g = gcd(a, b);
        int ans = 0;
        for (int x = 1; x <= g; ++x) {
            ans += g % x == 0;
        }
        return ans;
    }
};
```

```go
func commonFactors(a int, b int) (ans int) {
	g := gcd(a, b)
	for x := 1; x <= g; x++ {
		if g%x == 0 {
			ans++
		}
	}
	return
}

func gcd(a int, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

```ts
function commonFactors(a: number, b: number): number {
    const g = gcd(a, b);
    let ans = 0;
    for (let x = 1; x <= g; ++x) {
        if (g % x === 0) {
            ++ans;
        }
    }
    return ans;
}

function gcd(a: number, b: number): number {
    return b === 0 ? a : gcd(b, a % b);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Optimized Enumeration

Similar to Solution 1, we can first calculate the greatest common divisor $g$ of $a$ and $b$, then enumerate all factors of the greatest common divisor $g$, and accumulate the answer.

The time complexity is $O(\sqrt{\min(a, b)})$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def commonFactors(self, a: int, b: int) -> int:
        g = gcd(a, b)
        ans, x = 0, 1
        while x * x <= g:
            if g % x == 0:
                ans += 1
                ans += x * x < g
            x += 1
        return ans
```

```java
class Solution {
    public int commonFactors(int a, int b) {
        int g = gcd(a, b);
        int ans = 0;
        for (int x = 1; x * x <= g; ++x) {
            if (g % x == 0) {
                ++ans;
                if (x * x < g) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

```cpp
class Solution {
public:
    int commonFactors(int a, int b) {
        int g = gcd(a, b);
        int ans = 0;
        for (int x = 1; x * x <= g; ++x) {
            if (g % x == 0) {
                ans++;
                ans += x * x < g;
            }
        }
        return ans;
    }
};
```

```go
func commonFactors(a int, b int) (ans int) {
	g := gcd(a, b)
	for x := 1; x*x <= g; x++ {
		if g%x == 0 {
			ans++
			if x*x < g {
				ans++
			}
		}
	}
	return
}

func gcd(a int, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

```ts
function commonFactors(a: number, b: number): number {
    const g = gcd(a, b);
    let ans = 0;
    for (let x = 1; x * x <= g; ++x) {
        if (g % x === 0) {
            ++ans;
            if (x * x < g) {
                ++ans;
            }
        }
    }
    return ans;
}

function gcd(a: number, b: number): number {
    return b === 0 ? a : gcd(b, a % b);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
