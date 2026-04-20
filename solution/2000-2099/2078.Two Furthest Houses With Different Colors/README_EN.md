---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2078.Two%20Furthest%20Houses%20With%20Different%20Colors/README_EN.md
rating: 1240
source: Weekly Contest 268 Q1
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [2078. Two Furthest Houses With Different Colors](https://leetcode.com/problems/two-furthest-houses-with-different-colors)

[中文文档](/solution/2000-2099/2078.Two%20Furthest%20Houses%20With%20Different%20Colors/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> houses evenly lined up on the street, and each house is beautifully painted. You are given a <strong>0-indexed</strong> integer array <code>colors</code> of length <code>n</code>, where <code>colors[i]</code> represents the color of the <code>i<sup>th</sup></code> house.</p>

<p>Return <em>the <strong>maximum</strong> distance between <strong>two</strong> houses with <strong>different</strong> colors</em>.</p>

<p>The distance between the <code>i<sup>th</sup></code> and <code>j<sup>th</sup></code> houses is <code>abs(i - j)</code>, where <code>abs(x)</code> is the <strong>absolute value</strong> of <code>x</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2078.Two%20Furthest%20Houses%20With%20Different%20Colors/images/eg1.png" style="width: 610px; height: 84px;" />
<pre>
<strong>Input:</strong> colors = [<u><strong>1</strong></u>,1,1,<strong><u>6</u></strong>,1,1,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> In the above image, color 1 is blue, and color 6 is red.
The furthest two houses with different colors are house 0 and house 3.
House 0 has color 1, and house 3 has color 6. The distance between them is abs(0 - 3) = 3.
Note that houses 3 and 6 can also produce the optimal answer.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2078.Two%20Furthest%20Houses%20With%20Different%20Colors/images/eg2.png" style="width: 426px; height: 84px;" />
<pre>
<strong>Input:</strong> colors = [<u><strong>1</strong></u>,8,3,8,<u><strong>3</strong></u>]
<strong>Output:</strong> 4
<strong>Explanation:</strong> In the above image, color 1 is blue, color 8 is yellow, and color 3 is green.
The furthest two houses with different colors are house 0 and house 4.
House 0 has color 1, and house 4 has color 3. The distance between them is abs(0 - 4) = 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> colors = [<u><strong>0</strong></u>,<strong><u>1</u></strong>]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The furthest two houses with different colors are house 0 and house 1.
House 0 has color 0, and house 1 has color 1. The distance between them is abs(0 - 1) = 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n ==&nbsp;colors.length</code></li>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= colors[i] &lt;= 100</code></li>
	<li>Test data are generated such that <strong>at least</strong> two houses have different colors.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

We can observe that if the first and last houses have different colors, the maximum distance is $n - 1$.

If the first and last houses have the same color, we can scan from the left to find the first house with a different color (let its index be $i$), and scan from the right to find the first house with a different color (let its index be $j$). The maximum distance is then $\max(n - i - 1, j)$.

The time complexity is $O(n)$, where $n$ is the number of houses. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDistance(self, colors: List[int]) -> int:
        n = len(colors)
        if colors[0] != colors[-1]:
            return n - 1
        i, j = 1, n - 2
        while colors[i] == colors[0]:
            i += 1
        while colors[j] == colors[0]:
            j -= 1
        return max(n - i - 1, j)
```

#### Java

```java
class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        if (colors[0] != colors[n - 1]) {
            return n - 1;
        }
        int i = 1, j = n - 2;
        while (colors[i] == colors[0]) {
            ++i;
        }
        while (colors[j] == colors[0]) {
            --j;
        }
        return Math.max(n - i - 1, j);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxDistance(vector<int>& colors) {
        int n = colors.size();
        if (colors[0] != colors[n - 1]) {
            return n - 1;
        }
        int i = 1, j = n - 2;
        while (colors[i] == colors[0]) {
            ++i;
        }
        while (colors[j] == colors[0]) {
            --j;
        }
        return max(n - i - 1, j);
    }
};
```

#### Go

```go
func maxDistance(colors []int) int {
	n := len(colors)
	if colors[0] != colors[n-1] {
		return n - 1
	}
	i, j := 1, n-2
	for colors[i] == colors[0] {
		i++
	}
	for colors[j] == colors[0] {
		j--
	}
	return max(n-i-1, j)
}
```

#### TypeScript

```ts
function maxDistance(colors: number[]): number {
    const n = colors.length;
    if (colors[0] !== colors[n - 1]) {
        return n - 1;
    }
    let [i, j] = [1, n - 2];
    while (colors[i] === colors[0]) {
        i++;
    }
    while (colors[j] === colors[0]) {
        j--;
    }
    return Math.max(n - i - 1, j);
};
```

#### Rust

```rust
impl Solution {
    pub fn max_distance(colors: Vec<i32>) -> i32 {
        let n = colors.len();
        if colors[0] != colors[n - 1] {
            return (n - 1) as i32;
        }
        let mut i = 1;
        while colors[i] == colors[0] {
            i += 1;
        }
        let mut j = n - 2;
        while colors[j] == colors[0] {
            j -= 1;
        }
        std::cmp::max((n - i - 1) as i32, j as i32)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
