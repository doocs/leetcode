---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3516.Find%20Closest%20Person/README_EN.md
rating: 1164
source: Weekly Contest 445 Q1
tags:
    - Math
---

<!-- problem:start -->

# [3516. Find Closest Person](https://leetcode.com/problems/find-closest-person)

[中文文档](/solution/3500-3599/3516.Find%20Closest%20Person/README.md)

## Description

<!-- description:start -->

<p data-end="116" data-start="0">You are given three integers <code data-end="33" data-start="30">x</code>, <code data-end="38" data-start="35">y</code>, and <code data-end="47" data-start="44">z</code>, representing the positions of three people on a number line:</p>

<ul data-end="252" data-start="118">
	<li data-end="154" data-start="118"><code data-end="123" data-start="120">x</code> is the position of Person 1.</li>
	<li data-end="191" data-start="155"><code data-end="160" data-start="157">y</code> is the position of Person 2.</li>
	<li data-end="252" data-start="192"><code data-end="197" data-start="194">z</code> is the position of Person 3, who does <strong>not</strong> move.</li>
</ul>

<p data-end="322" data-start="254">Both Person 1 and Person 2 move toward Person 3 at the <strong>same</strong> speed.</p>

<p data-end="372" data-start="324">Determine which person reaches Person 3 <strong>first</strong>:</p>

<ul data-end="505" data-start="374">
	<li data-end="415" data-start="374">Return 1 if Person 1 arrives first.</li>
	<li data-end="457" data-start="416">Return 2 if Person 2 arrives first.</li>
	<li data-end="505" data-start="458">Return 0 if both arrive at the <strong>same</strong> time.</li>
</ul>

<p data-end="537" data-is-last-node="" data-is-only-node="" data-start="507">Return the result accordingly.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">x = 2, y = 7, z = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul data-end="258" data-start="113">
	<li data-end="193" data-start="113">Person 1 is at position 2 and can reach Person 3 (at position 4) in 2 steps.</li>
	<li data-end="258" data-start="194">Person 2 is at position 7 and can reach Person 3 in 3 steps.</li>
</ul>

<p data-end="317" data-is-last-node="" data-is-only-node="" data-start="260">Since Person 1 reaches Person 3 first, the output is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">x = 2, y = 5, z = 6</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul data-end="245" data-start="92">
	<li data-end="174" data-start="92">Person 1 is at position 2 and can reach Person 3 (at position 6) in 4 steps.</li>
	<li data-end="245" data-start="175">Person 2 is at position 5 and can reach Person 3 in 1 step.</li>
</ul>

<p data-end="304" data-is-last-node="" data-is-only-node="" data-start="247">Since Person 2 reaches Person 3 first, the output is 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">x = 1, y = 5, z = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul data-end="245" data-start="92">
	<li data-end="174" data-start="92">Person 1 is at position 1 and can reach Person 3 (at position 3) in 2 steps.</li>
	<li data-end="245" data-start="175">Person 2 is at position 5 and can reach Person 3 in 2 steps.</li>
</ul>

<p data-end="304" data-is-last-node="" data-is-only-node="" data-start="247">Since both Person 1 and Person 2 reach Person 3 at the same time, the output is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= x, y, z &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

We calculate the distance $a$ between the 1st person and the 3rd person, and the distance $b$ between the 2nd person and the 3rd person.

-   If $a = b$, it means both people arrive at the same time, return $0$;
-   If $a \lt b$, it means the 1st person will arrive first, return $1$;
-   Otherwise, it means the 2nd person will arrive first, return $2$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findClosest(self, x: int, y: int, z: int) -> int:
        a = abs(x - z)
        b = abs(y - z)
        return 0 if a == b else (1 if a < b else 2)
```

#### Java

```java
class Solution {
    public int findClosest(int x, int y, int z) {
        int a = Math.abs(x - z);
        int b = Math.abs(y - z);
        return a == b ? 0 : (a < b ? 1 : 2);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findClosest(int x, int y, int z) {
        int a = abs(x - z);
        int b = abs(y - z);
        return a == b ? 0 : (a < b ? 1 : 2);
    }
};
```

#### Go

```go
func findClosest(x int, y int, z int) int {
	a, b := abs(x-z), abs(y-z)
	if a == b {
		return 0
	}
	if a < b {
		return 1
	}
	return 2
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function findClosest(x: number, y: number, z: number): number {
    const a = Math.abs(x - z);
    const b = Math.abs(y - z);
    return a === b ? 0 : a < b ? 1 : 2;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_closest(x: i32, y: i32, z: i32) -> i32 {
        let a = (x - z).abs();
        let b = (y - z).abs();
        if a == b {
            0
        } else if a < b {
            1
        } else {
            2
        }
    }
}
```

#### JavaScript

```js
/**
 * @param {number} x
 * @param {number} y
 * @param {number} z
 * @return {number}
 */
var findClosest = function (x, y, z) {
    const a = Math.abs(x - z);
    const b = Math.abs(y - z);
    return a === b ? 0 : a < b ? 1 : 2;
};
```

#### C#

```cs
public class Solution {
    public int FindClosest(int x, int y, int z) {
        int a = Math.Abs(x - z);
        int b = Math.Abs(y - z);
        return a == b ? 0 : (a < b ? 1 : 2);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
