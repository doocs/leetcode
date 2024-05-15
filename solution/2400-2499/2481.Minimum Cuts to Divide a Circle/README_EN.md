---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2481.Minimum%20Cuts%20to%20Divide%20a%20Circle/README_EN.md
rating: 1246
tags:
    - Geometry
    - Math
---

# [2481. Minimum Cuts to Divide a Circle](https://leetcode.com/problems/minimum-cuts-to-divide-a-circle)

[中文文档](/solution/2400-2499/2481.Minimum%20Cuts%20to%20Divide%20a%20Circle/README.md)

## Description

<p>A <strong>valid cut</strong> in a circle can be:</p>

<ul>
	<li>A cut that is represented by a straight line that touches two points on the edge of the circle and passes through its center, or</li>
	<li>A cut that is represented by a straight line that touches one point on the edge of the circle and its center.</li>
</ul>

<p>Some valid and invalid cuts are shown in the figures below.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2481.Minimum%20Cuts%20to%20Divide%20a%20Circle/images/alldrawio.png" style="width: 450px; height: 174px;" />
<p>Given the integer <code>n</code>, return <em>the <strong>minimum</strong> number of cuts needed to divide a circle into </em><code>n</code><em> equal slices</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2481.Minimum%20Cuts%20to%20Divide%20a%20Circle/images/11drawio.png" style="width: 200px; height: 200px;" />
<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
The above figure shows how cutting the circle twice through the middle divides it into 4 equal slices.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2481.Minimum%20Cuts%20to%20Divide%20a%20Circle/images/22drawio.png" style="width: 200px; height: 201px;" />
<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong>
At least 3 cuts are needed to divide the circle into 3 equal slices. 
It can be shown that less than 3 cuts cannot result in 3 slices of equal size and shape.
Also note that the first cut will not divide the circle into distinct parts.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1: Case Discussion

-   When $n=1$, no cutting is needed, so the number of cuts is $0$;
-   When $n$ is odd, there is no collinear situation, and at least $n$ cuts are needed;
-   When $n$ is even, they can be collinear in pairs, and at least $\frac{n}{2}$ cuts are needed.

In summary, we can get:

$$
\text{ans} = \begin{cases}
n, & n \gt 1 \text{ and } n \text{ is odd} \\
\frac{n}{2}, & n \text{ is even} \\
\end{cases}
$$

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def numberOfCuts(self, n: int) -> int:
        return n if (n > 1 and n & 1) else n >> 1
```

```java
class Solution {
    public int numberOfCuts(int n) {
        return n > 1 && n % 2 == 1 ? n : n >> 1;
    }
}
```

```cpp
class Solution {
public:
    int numberOfCuts(int n) {
        return n > 1 && n % 2 == 1 ? n : n >> 1;
    }
};
```

```go
func numberOfCuts(n int) int {
	if n > 1 && n%2 == 1 {
		return n
	}
	return n >> 1
}
```

```ts
function numberOfCuts(n: number): number {
    return n > 1 && n & 1 ? n : n >> 1;
}
```

```rust
impl Solution {
    pub fn number_of_cuts(n: i32) -> i32 {
        if n > 1 && n % 2 == 1 {
            return n;
        }
        n >> 1
    }
}
```

```cs
public class Solution {
    public int NumberOfCuts(int n) {
        return n > 1 && n % 2 == 1 ? n : n >> 1;
    }
}
```

<!-- tabs:end -->

<!-- end -->
