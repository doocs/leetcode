---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2745.Construct%20the%20Longest%20New%20String/README_EN.md
rating: 1607
source: Biweekly Contest 107 Q2
tags:
    - Greedy
    - Brainteaser
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [2745. Construct the Longest New String](https://leetcode.com/problems/construct-the-longest-new-string)

[中文文档](/solution/2700-2799/2745.Construct%20the%20Longest%20New%20String/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code>x</code>, <code>y</code>, and <code>z</code>.</p>

<p>You have <code>x</code> strings equal to <code>&quot;AA&quot;</code>, <code>y</code> strings equal to <code>&quot;BB&quot;</code>, and <code>z</code> strings equal to <code>&quot;AB&quot;</code>. You want to choose some (possibly all or none) of these strings and concatenate them in some order to form a new string. This new string must not contain <code>&quot;AAA&quot;</code> or <code>&quot;BBB&quot;</code> as a substring.</p>

<p>Return <em>the maximum possible length of the new string</em>.</p>

<p>A <b>substring</b> is a contiguous <strong>non-empty</strong> sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> x = 2, y = 5, z = 1
<strong>Output:</strong> 12
<strong>Explanation: </strong>We can concatenate the strings &quot;BB&quot;, &quot;AA&quot;, &quot;BB&quot;, &quot;AA&quot;, &quot;BB&quot;, and &quot;AB&quot; in that order. Then, our new string is &quot;BBAABBAABBAB&quot;. 
That string has length 12, and we can show that it is impossible to construct a string of longer length.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> x = 3, y = 2, z = 2
<strong>Output:</strong> 14
<strong>Explanation:</strong> We can concatenate the strings &quot;AB&quot;, &quot;AB&quot;, &quot;AA&quot;, &quot;BB&quot;, &quot;AA&quot;, &quot;BB&quot;, and &quot;AA&quot; in that order. Then, our new string is &quot;ABABAABBAABBAA&quot;. 
That string has length 14, and we can show that it is impossible to construct a string of longer length.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= x, y, z &lt;= 50</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Case Discussion

We observe that the string 'AA' can only be followed by 'BB', and the string 'AB' can be placed at the beginning or end of the string. Therefore:

-   If $x < y$, we can first alternately place 'BBAABBAA..BB', placing a total of $x$ 'AA' and $x+1$ 'BB', then place the remaining $z$ 'AB', with a total length of $(x \times 2 + z + 1) \times 2$;
-   If $x > y$, we can first alternately place 'AABBAABB..AA', placing a total of $y$ 'BB' and $y+1$ 'AA', then place the remaining $z$ 'AB', with a total length of $(y \times 2 + z + 1) \times 2$;
-   If $x = y$, we only need to alternately place 'AABB', placing a total of $x$ 'AA' and $y$ 'BB', then place the remaining $z$ 'AB', with a total length of $(x + y + z) \times 2$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestString(self, x: int, y: int, z: int) -> int:
        if x < y:
            return (x * 2 + z + 1) * 2
        if x > y:
            return (y * 2 + z + 1) * 2
        return (x + y + z) * 2
```

#### Java

```java
class Solution {
    public int longestString(int x, int y, int z) {
        if (x < y) {
            return (x * 2 + z + 1) * 2;
        }
        if (x > y) {
            return (y * 2 + z + 1) * 2;
        }
        return (x + y + z) * 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestString(int x, int y, int z) {
        if (x < y) {
            return (x * 2 + z + 1) * 2;
        }
        if (x > y) {
            return (y * 2 + z + 1) * 2;
        }
        return (x + y + z) * 2;
    }
};
```

#### Go

```go
func longestString(x int, y int, z int) int {
	if x < y {
		return (x*2 + z + 1) * 2
	}
	if x > y {
		return (y*2 + z + 1) * 2
	}
	return (x + y + z) * 2
}
```

#### TypeScript

```ts
function longestString(x: number, y: number, z: number): number {
    if (x < y) {
        return (x * 2 + z + 1) * 2;
    }
    if (x > y) {
        return (y * 2 + z + 1) * 2;
    }
    return (x + y + z) * 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
