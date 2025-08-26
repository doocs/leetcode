---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3000.Maximum%20Area%20of%20Longest%20Diagonal%20Rectangle/README_EN.md
rating: 1249
source: Weekly Contest 379 Q1
tags:
    - Array
---

<!-- problem:start -->

# [3000. Maximum Area of Longest Diagonal Rectangle](https://leetcode.com/problems/maximum-area-of-longest-diagonal-rectangle)

[中文文档](/solution/3000-3099/3000.Maximum%20Area%20of%20Longest%20Diagonal%20Rectangle/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D <strong>0-indexed </strong>integer array <code>dimensions</code>.</p>

<p>For all indices <code>i</code>, <code>0 &lt;= i &lt; dimensions.length</code>, <code>dimensions[i][0]</code> represents the length and <code>dimensions[i][1]</code> represents the width of the rectangle<span style="font-size: 13.3333px;"> <code>i</code></span>.</p>

<p>Return <em>the <strong>area</strong> of the rectangle having the <strong>longest</strong> diagonal. If there are multiple rectangles with the longest diagonal, return the area of the rectangle having the <strong>maximum</strong> area.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> dimensions = [[9,3],[8,6]]
<strong>Output:</strong> 48
<strong>Explanation:</strong> 
For index = 0, length = 9 and width = 3. Diagonal length = sqrt(9 * 9 + 3 * 3) = sqrt(90) &asymp;<!-- notionvc: 882cf44c-3b17-428e-9c65-9940810216f1 --> 9.487.
For index = 1, length = 8 and width = 6. Diagonal length = sqrt(8 * 8 + 6 * 6) = sqrt(100) = 10.
So, the rectangle at index 1 has a greater diagonal length therefore we return area = 8 * 6 = 48.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> dimensions = [[3,4],[4,3]]
<strong>Output:</strong> 12
<strong>Explanation:</strong> Length of diagonal is the same for both which is 5, so maximum area = 12.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= dimensions.length &lt;= 100</code></li>
	<li><code><font face="monospace">dimensions[i].length == 2</font></code></li>
	<li><code><font face="monospace">1 &lt;= dimensions[i][0], dimensions[i][1] &lt;= 100</font></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

According to the Pythagorean theorem, the square of the diagonal of a rectangle is $l^2 + w^2$, where $l$ and $w$ are the length and width of the rectangle, respectively.

We can iterate through all the rectangles, calculate the square of their diagonal lengths, and keep track of the maximum diagonal length and the corresponding area.

After the iteration, we return the recorded maximum area.

The time complexity is $O(n)$, where $n$ is the number of rectangles. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def areaOfMaxDiagonal(self, dimensions: List[List[int]]) -> int:
        ans = mx = 0
        for l, w in dimensions:
            t = l**2 + w**2
            if mx < t:
                mx = t
                ans = l * w
            elif mx == t:
                ans = max(ans, l * w)
        return ans
```

#### Java

```java
class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int ans = 0, mx = 0;
        for (var d : dimensions) {
            int l = d[0], w = d[1];
            int t = l * l + w * w;
            if (mx < t) {
                mx = t;
                ans = l * w;
            } else if (mx == t) {
                ans = Math.max(ans, l * w);
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int areaOfMaxDiagonal(vector<vector<int>>& dimensions) {
        int ans = 0, mx = 0;
        for (auto& d : dimensions) {
            int l = d[0], w = d[1];
            int t = l * l + w * w;
            if (mx < t) {
                mx = t;
                ans = l * w;
            } else if (mx == t) {
                ans = max(ans, l * w);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func areaOfMaxDiagonal(dimensions [][]int) (ans int) {
	mx := 0
	for _, d := range dimensions {
		l, w := d[0], d[1]
		t := l*l + w*w
		if mx < t {
			mx = t
			ans = l * w
		} else if mx == t {
			ans = max(ans, l*w)
		}
	}
	return
}
```

#### TypeScript

```ts
function areaOfMaxDiagonal(dimensions: number[][]): number {
    let [ans, mx] = [0, 0];
    for (const [l, w] of dimensions) {
        const t = l * l + w * w;
        if (mx < t) {
            mx = t;
            ans = l * w;
        } else if (mx === t) {
            ans = Math.max(ans, l * w);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn area_of_max_diagonal(dimensions: Vec<Vec<i32>>) -> i32 {
        let mut ans = 0;
        let mut mx = 0;
        for d in dimensions {
            let l = d[0];
            let w = d[1];
            let t = l * l + w * w;
            if mx < t {
                mx = t;
                ans = l * w;
            } else if mx == t {
                ans = ans.max(l * w);
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public int AreaOfMaxDiagonal(int[][] dimensions) {
        int ans = 0, mx = 0;
        foreach (var d in dimensions) {
            int l = d[0], w = d[1];
            int t = l * l + w * w;
            if (mx < t) {
                mx = t;
                ans = l * w;
            } else if (mx == t) {
                ans = Math.Max(ans, l * w);
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
