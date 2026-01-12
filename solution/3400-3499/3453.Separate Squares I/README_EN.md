---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3453.Separate%20Squares%20I/README_EN.md
rating: 1735
source: Biweekly Contest 150 Q2
tags:
    - Array
    - Binary Search
---

<!-- problem:start -->

# [3453. Separate Squares I](https://leetcode.com/problems/separate-squares-i)

[中文文档](/solution/3400-3499/3453.Separate%20Squares%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>squares</code>. Each <code>squares[i] = [x<sub>i</sub>, y<sub>i</sub>, l<sub>i</sub>]</code> represents the coordinates of the bottom-left point and the side length of a square parallel to the x-axis.</p>

<p>Find the <strong>minimum</strong> y-coordinate value of a horizontal line such that the total area of the squares above the line <em>equals</em> the total area of the squares below the line.</p>

<p>Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p><strong>Note</strong>: Squares <strong>may</strong> overlap. Overlapping areas should be counted <strong>multiple times</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">squares = [[0,0,1],[2,2,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1.00000</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3453.Separate%20Squares%20I/images/4062example1drawio.png" style="width: 378px; height: 352px;" /></p>

<p>Any horizontal line between <code>y = 1</code> and <code>y = 2</code> will have 1 square unit above it and 1 square unit below it. The lowest option is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">squares = [[0,0,2],[1,1,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1.16667</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3453.Separate%20Squares%20I/images/4062example2drawio.png" style="width: 378px; height: 352px;" /></p>

<p>The areas are:</p>

<ul>
	<li>Below the line: <code>7/6 * 2 (Red) + 1/6 (Blue) = 15/6 = 2.5</code>.</li>
	<li>Above the line: <code>5/6 * 2 (Red) + 5/6 (Blue) = 15/6 = 2.5</code>.</li>
</ul>

<p>Since the areas above and below the line are equal, the output is <code>7/6 = 1.16667</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= squares.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>squares[i] = [x<sub>i</sub>, y<sub>i</sub>, l<sub>i</sub>]</code></li>
	<li><code>squares[i].length == 3</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= l<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li>The total area of all the squares will not exceed <code>10<sup>12</sup></code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

According to the problem, we need to find a horizontal line such that the total area of squares above the line equals the total area of squares below the line. Since as the $y$ coordinate increases, the area below the line increases and the area above the line decreases, we can use binary search to find the $y$ coordinate of this horizontal line.

We define the left boundary of the binary search as $l = 0$, and the right boundary as $r = \max(y_i + l_i)$, which is the highest point of all squares. Then we calculate the midpoint $mid = (l + r) / 2$ and calculate the area below this horizontal line. If this area is greater than or equal to half of the total area, it means we need to move the right boundary $r$ downward; otherwise, we move the left boundary $l$ upward. We repeat this process until the difference between the left and right boundaries is less than a very small value (e.g., $10^{-5}$).

The time complexity is $O(n \log(MU))$, where $n$ is the number of squares, $M = 10^5$, and $U = \max(y_i + l_i)$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def separateSquares(self, squares: List[List[int]]) -> float:
        def check(y1: float) -> bool:
            t = 0
            for _, y, l in squares:
                if y < y1:
                    t += l * min(y1 - y, l)
            return t >= s / 2

        s = sum(a[2] * a[2] for a in squares)
        l, r = 0, max(a[1] + a[2] for a in squares)
        eps = 1e-5
        while r - l > eps:
            mid = (l + r) / 2
            if check(mid):
                r = mid
            else:
                l = mid
        return r
```

#### Java

```java
class Solution {
    private int[][] squares;
    private double s;

    private boolean check(double y1) {
        double t = 0.0;
        for (int[] a : squares) {
            int y = a[1];
            int l = a[2];
            if (y < y1) {
                t += (double) l * Math.min(y1 - y, l);
            }
        }
        return t >= s / 2.0;
    }

    public double separateSquares(int[][] squares) {
        this.squares = squares;
        s = 0.0;
        double l = 0.0;
        double r = 0.0;
        for (int[] a : squares) {
            s += (double) a[2] * a[2];
            r = Math.max(r, a[1] + a[2]);
        }

        double eps = 1e-5;
        while (r - l > eps) {
            double mid = (l + r) / 2.0;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>>* squares;
    double s;

    bool check(double y1) {
        double t = 0.0;
        for (const auto& a : *squares) {
            int y = a[1];
            int l = a[2];
            if (y < y1) {
                t += (double) l * min(y1 - y, (double) l);
            }
        }
        return t >= s / 2.0;
    }

    double separateSquares(vector<vector<int>>& squares) {
        this->squares = &squares;
        s = 0.0;
        double l = 0.0;
        double r = 0.0;
        for (const auto& a : squares) {
            s += (double) a[2] * a[2];
            r = max(r, (double) a[1] + a[2]);
        }
        const double eps = 1e-5;
        while (r - l > eps) {
            double mid = (l + r) / 2.0;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }
};
```

#### Go

```go
func separateSquares(squares [][]int) float64 {
	s := 0.0
	check := func(y1 float64) bool {
		t := 0.0
		for _, a := range squares {
			y := a[1]
			l := a[2]
			if float64(y) < y1 {
				h := min(float64(l), y1-float64(y))
				t += float64(l) * h
			}
		}
		return t >= s/2.0
	}
	l, r := 0.0, 0.0
	for _, a := range squares {
		s += float64(a[2] * a[2])
		r = max(r, float64(a[1]+a[2]))
	}

	const eps = 1e-5
	for r-l > eps {
		mid := (l + r) / 2.0
		if check(mid) {
			r = mid
		} else {
			l = mid
		}
	}
	return r
}
```

#### TypeScript

```ts
function separateSquares(squares: number[][]): number {
    const check = (y1: number): boolean => {
        let t = 0;
        for (const [_, y, l] of squares) {
            if (y < y1) {
                t += l * Math.min(y1 - y, l);
            }
        }
        return t >= s / 2;
    };

    let s = 0;
    let l = 0;
    let r = 0;
    for (const a of squares) {
        s += a[2] * a[2];
        r = Math.max(r, a[1] + a[2]);
    }

    const eps = 1e-5;
    while (r - l > eps) {
        const mid = (l + r) / 2;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid;
        }
    }
    return r;
}
```

#### Rust

```rust
impl Solution {
    pub fn separate_squares(squares: Vec<Vec<i32>>) -> f64 {
        let mut s: f64 = 0.0;

        let mut l: f64 = 0.0;
        let mut r: f64 = 0.0;

        for a in squares.iter() {
            let len = a[2] as f64;
            s += len * len;
            r = r.max((a[1] + a[2]) as f64);
        }

        let check = |y1: f64| -> bool {
            let mut t: f64 = 0.0;
            for a in squares.iter() {
                let y = a[1] as f64;
                let l = a[2] as f64;
                if y < y1 {
                    let h = l.min(y1 - y);
                    t += l * h;
                }
            }
            t >= s / 2.0
        };

        const EPS: f64 = 1e-5;
        while r - l > EPS {
            let mid = (l + r) / 2.0;
            if check(mid) {
                r = mid;
            } else {
                l = mid;
            }
        }
        r
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
