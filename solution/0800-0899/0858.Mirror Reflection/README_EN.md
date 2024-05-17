---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0858.Mirror%20Reflection/README_EN.md
tags:
    - Geometry
    - Math
    - Number Theory
---

<!-- problem:start -->

# [858. Mirror Reflection](https://leetcode.com/problems/mirror-reflection)

[中文文档](/solution/0800-0899/0858.Mirror%20Reflection/README.md)

## Description

<!-- description:start -->

<p>There is a special square room with mirrors on each of the four walls. Except for the southwest corner, there are receptors on each of the remaining corners, numbered <code>0</code>, <code>1</code>, and <code>2</code>.</p>

<p>The square room has walls of length <code>p</code>&nbsp;and a laser ray from the southwest corner first meets the east wall at a distance <code>q</code> from the <code>0<sup>th</sup></code> receptor.</p>

<p>Given the two integers <code>p</code> and <code>q</code>, return <em>the number of the receptor that the ray meets first</em>.</p>

<p>The test cases are guaranteed so that the ray will meet a receptor eventually.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0858.Mirror%20Reflection/images/reflection.png" style="width: 218px; height: 217px;" />
<pre>
<strong>Input:</strong> p = 2, q = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> The ray meets receptor 2 the first time it gets reflected back to the left wall.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> p = 3, q = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= q &lt;= p &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def mirrorReflection(self, p: int, q: int) -> int:
        g = gcd(p, q)
        p = (p // g) % 2
        q = (q // g) % 2
        if p == 1 and q == 1:
            return 1
        return 0 if p == 1 else 2
```

```java
class Solution {
    public int mirrorReflection(int p, int q) {
        int g = gcd(p, q);
        p = (p / g) % 2;
        q = (q / g) % 2;
        if (p == 1 && q == 1) {
            return 1;
        }
        return p == 1 ? 0 : 2;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

```cpp
class Solution {
public:
    int mirrorReflection(int p, int q) {
        int g = __gcd(p, q);
        p = (p / g) % 2;
        q = (q / g) % 2;
        if (p == 1 && q == 1) {
            return 1;
        }
        return p == 1 ? 0 : 2;
    }
};
```

```go
func mirrorReflection(p int, q int) int {
	g := gcd(p, q)
	p = (p / g) % 2
	q = (q / g) % 2
	if p == 1 && q == 1 {
		return 1
	}
	if p == 1 {
		return 0
	}
	return 2
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

```ts
function mirrorReflection(p: number, q: number): number {
    const g = gcd(p, q);
    p = Math.floor(p / g) % 2;
    q = Math.floor(q / g) % 2;
    if (p === 1 && q === 1) {
        return 1;
    }
    return p === 1 ? 0 : 2;
}

function gcd(a: number, b: number): number {
    return b === 0 ? a : gcd(b, a % b);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
