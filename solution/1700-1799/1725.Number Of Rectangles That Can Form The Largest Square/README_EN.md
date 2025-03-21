---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1725.Number%20Of%20Rectangles%20That%20Can%20Form%20The%20Largest%20Square/README_EN.md
rating: 1229
source: Weekly Contest 224 Q1
tags:
    - Array
---

<!-- problem:start -->

# [1725. Number Of Rectangles That Can Form The Largest Square](https://leetcode.com/problems/number-of-rectangles-that-can-form-the-largest-square)

[中文文档](/solution/1700-1799/1725.Number%20Of%20Rectangles%20That%20Can%20Form%20The%20Largest%20Square/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>rectangles</code> where <code>rectangles[i] = [l<sub>i</sub>, w<sub>i</sub>]</code> represents the <code>i<sup>th</sup></code> rectangle of length <code>l<sub>i</sub></code> and width <code>w<sub>i</sub></code>.</p>

<p>You can cut the <code>i<sup>th</sup></code> rectangle to form a square with a side length of <code>k</code> if both <code>k &lt;= l<sub>i</sub></code> and <code>k &lt;= w<sub>i</sub></code>. For example, if you have a rectangle <code>[4,6]</code>, you can cut it to get a square with a side length of at most <code>4</code>.</p>

<p>Let <code>maxLen</code> be the side length of the <strong>largest</strong> square you can obtain from any of the given rectangles.</p>

<p>Return <em>the <strong>number</strong> of rectangles that can make a square with a side length of </em><code>maxLen</code>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> rectangles = [[5,8],[3,9],[5,12],[16,5]]

<strong>Output:</strong> 3

<strong>Explanation:</strong> The largest squares you can get from each rectangle are of lengths [5,3,5,5].

The largest possible square is of length 5, and you can get it out of 3 rectangles.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> rectangles = [[2,3],[3,7],[4,3],[3,7]]

<strong>Output:</strong> 3

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>1 &lt;= rectangles.length &lt;= 1000</code></li>

    <li><code>rectangles[i].length == 2</code></li>

    <li><code>1 &lt;= l<sub>i</sub>, w<sub>i</sub> &lt;= 10<sup>9</sup></code></li>

    <li><code>l<sub>i</sub> != w<sub>i</sub></code></li>

</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Single Pass

We define a variable $ans$ to record the count of squares with the current maximum side length, and another variable $mx$ to record the current maximum side length.

We traverse the array $rectangles$. For each rectangle $[l, w]$, we take $x = \min(l, w)$. If $mx < x$, it means we have found a larger side length, so we update $mx$ to $x$ and update $ans$ to $1$. If $mx = x$, it means we have found a side length equal to the current maximum side length, so we increase $ans$ by $1$.

Finally, we return $ans$.

The time complexity is $O(n)$, where $n$ is the length of the array $rectangles$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countGoodRectangles(self, rectangles: List[List[int]]) -> int:
        ans = mx = 0
        for l, w in rectangles:
            x = min(l, w)
            if mx < x:
                ans = 1
                mx = x
            elif mx == x:
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int countGoodRectangles(int[][] rectangles) {
        int ans = 0, mx = 0;
        for (var e : rectangles) {
            int x = Math.min(e[0], e[1]);
            if (mx < x) {
                mx = x;
                ans = 1;
            } else if (mx == x) {
                ++ans;
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
    int countGoodRectangles(vector<vector<int>>& rectangles) {
        int ans = 0, mx = 0;
        for (auto& e : rectangles) {
            int x = min(e[0], e[1]);
            if (mx < x) {
                mx = x;
                ans = 1;
            } else if (mx == x) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countGoodRectangles(rectangles [][]int) (ans int) {
	mx := 0
	for _, e := range rectangles {
		x := min(e[0], e[1])
		if mx < x {
			mx = x
			ans = 1
		} else if mx == x {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function countGoodRectangles(rectangles: number[][]): number {
    let [ans, mx] = [0, 0];
    for (const [l, w] of rectangles) {
        const x = Math.min(l, w);
        if (mx < x) {
            mx = x;
            ans = 1;
        } else if (mx === x) {
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
