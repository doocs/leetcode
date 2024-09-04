---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3274.Check%20if%20Two%20Chessboard%20Squares%20Have%20the%20Same%20Color/README_EN.md
tags:
    - Math
    - String
---

<!-- problem:start -->

# [3274. Check if Two Chessboard Squares Have the Same Color](https://leetcode.com/problems/check-if-two-chessboard-squares-have-the-same-color)

[中文文档](/solution/3200-3299/3274.Check%20if%20Two%20Chessboard%20Squares%20Have%20the%20Same%20Color/README.md)

## Description

<!-- description:start -->

<p>You are given two strings, <code>coordinate1</code> and <code>coordinate2</code>, representing the coordinates of a square on an <code>8 x 8</code> chessboard.</p>

<p>Below is the chessboard for reference.</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3200-3299/3274.Check%20if%20Two%20Chessboard%20Squares%20Have%20the%20Same%20Color/images/screenshot-2021-02-20-at-22159-pm.png" style="width: 400px; height: 396px;" /></p>

<p>Return <code>true</code> if these two squares have the same color and <code>false</code> otherwise.</p>

<p>The coordinate will always represent a valid chessboard square. The coordinate will always have the letter first (indicating its column), and the number second (indicating its row).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">coordinate1 = &quot;a1&quot;, coordinate2 = &quot;c3&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>Both squares are black.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">coordinate1 = &quot;a1&quot;, coordinate2 = &quot;h3&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>Square <code>&quot;a1&quot;</code> is black and <code>&quot;h3&quot;</code> is white.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>coordinate1.length == coordinate2.length == 2</code></li>
	<li><code>&#39;a&#39; &lt;= coordinate1[0], coordinate2[0] &lt;= &#39;h&#39;</code></li>
	<li><code>&#39;1&#39; &lt;= coordinate1[1], coordinate2[1] &lt;= &#39;8&#39;</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

We calculate the differences in the x-coordinates and y-coordinates of the two points. If the sum of these differences is even, then the colors of the squares at these two coordinates are the same; otherwise, they are different.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def checkTwoChessboards(self, coordinate1: str, coordinate2: str) -> bool:
        x = ord(coordinate1[0]) - ord(coordinate2[0])
        y = int(coordinate1[1]) - int(coordinate2[1])
        return (x + y) % 2 == 0
```

#### Java

```java
class Solution {
    public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        int x = coordinate1.charAt(0) - coordinate2.charAt(0);
        int y = coordinate1.charAt(1) - coordinate2.charAt(1);
        return (x + y) % 2 == 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool checkTwoChessboards(string coordinate1, string coordinate2) {
        int x = coordinate1[0] - coordinate2[0];
        int y = coordinate1[1] - coordinate2[1];
        return (x + y) % 2 == 0;
    }
};
```

#### Go

```go
func checkTwoChessboards(coordinate1 string, coordinate2 string) bool {
	x := coordinate1[0] - coordinate2[0]
	y := coordinate1[1] - coordinate2[1]
	return (x+y)%2 == 0
}
```

#### TypeScript

```ts
function checkTwoChessboards(coordinate1: string, coordinate2: string): boolean {
    const x = coordinate1.charCodeAt(0) - coordinate2.charCodeAt(0);
    const y = coordinate1.charCodeAt(1) - coordinate2.charCodeAt(1);
    return (x + y) % 2 === 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
