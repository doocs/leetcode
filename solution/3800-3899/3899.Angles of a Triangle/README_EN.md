---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3899.Angles%20of%20a%20Triangle/README_EN.md
rating: 1407
source: Weekly Contest 497 Q2
---

<!-- problem:start -->

# [3899. Angles of a Triangle](https://leetcode.com/problems/angles-of-a-triangle)

[中文文档](/solution/3800-3899/3899.Angles%20of%20a%20Triangle/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer array <code>sides</code> of length 3.</p>

<p>Determine if there exists a triangle with <strong>positive</strong> area whose three side lengths are given by the elements of <code>sides</code>.</p>

<p>If such a triangle exists, return an array of three floating-point numbers representing its internal angles (in <strong>degrees</strong>), <strong>sorted</strong> in <strong>non-decreasing</strong> order. Otherwise, return an empty array.</p>

<p>Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">sides = [3,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">[36.86990,53.13010,90.00000]</span></p>

<p><strong>Explanation:</strong></p>

<p>You can form a right-angled triangle with side lengths 3, 4, and 5. The internal angles of this triangle are approximately 36.869897646, 53.130102354, and 90 degrees respectively.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">sides = [2,4,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>You cannot form a triangle with positive area using side lengths 2, 4, and 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>sides.length == 3</code></li>
	<li><code>1 &lt;= sides[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Math

We first sort the array $\textit{sides}$ in non-decreasing order, and denote the three side lengths as $a$, $b$, and $c$, where $a \le b \le c$.

According to the triangle inequality, if $a + b \le c$, then these three sides cannot form a triangle with positive area, so we return an empty array directly.

Otherwise, the three sides can form a valid triangle. By the law of cosines, we have:

$$
\cos A = \frac{b^2 + c^2 - a^2}{2bc}
$$

$$
\cos B = \frac{a^2 + c^2 - b^2}{2ac}
$$

Therefore, we can compute angles $A$ and $B$ separately. Finally, using the fact that the sum of the internal angles of a triangle is $180^\circ$, we get:

$$
C = 180^\circ - A - B
$$

Finally, we return the three internal angles.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def internalAngles(self, sides: list[int]) -> list[float]:
        sides.sort()
        a, b, c = sides
        if a + b <= c:
            return []
        A = degrees(acos((b**2 + c**2 - a**2) / (2 * b * c)))
        B = degrees(acos((a**2 + c**2 - b**2) / (2 * a * c)))
        C = 180 - A - B
        return [A, B, C]
```

#### Java

```java
class Solution {
    public double[] internalAngles(int[] sides) {
        Arrays.sort(sides);
        int a = sides[0], b = sides[1], c = sides[2];
        if (a + b <= c) {
            return new double[0];
        }
        double A = Math.toDegrees(Math.acos((b * b + c * c - a * a) / (2.0 * b * c)));
        double B = Math.toDegrees(Math.acos((a * a + c * c - b * b) / (2.0 * a * c)));
        double C = 180.0 - A - B;
        return new double[] {A, B, C};
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<double> internalAngles(vector<int>& sides) {
        sort(sides.begin(), sides.end());
        int a = sides[0], b = sides[1], c = sides[2];
        if (a + b <= c) {
            return {};
        }
        double A = acos((1.0 * b * b + 1.0 * c * c - 1.0 * a * a) / (2.0 * b * c)) * 180.0 / acos(-1.0);
        double B = acos((1.0 * a * a + 1.0 * c * c - 1.0 * b * b) / (2.0 * a * c)) * 180.0 / acos(-1.0);
        double C = 180.0 - A - B;
        return {A, B, C};
    }
};
```

#### Go

```go
func internalAngles(sides []int) []float64 {
	sort.Ints(sides)
	a, b, c := sides[0], sides[1], sides[2]
	if a+b <= c {
		return []float64{}
	}
	A := math.Acos(float64(b*b+c*c-a*a)/float64(2*b*c)) * 180 / math.Pi
	B := math.Acos(float64(a*a+c*c-b*b)/float64(2*a*c)) * 180 / math.Pi
	C := 180 - A - B
	return []float64{A, B, C}
}
```

#### TypeScript

```ts
function internalAngles(sides: number[]): number[] {
    sides.sort((a, b) => a - b);
    const [a, b, c] = sides;
    if (a + b <= c) {
        return [];
    }
    const A = (Math.acos((b * b + c * c - a * a) / (2 * b * c)) * 180) / Math.PI;
    const B = (Math.acos((a * a + c * c - b * b) / (2 * a * c)) * 180) / Math.PI;
    const C = 180 - A - B;
    return [A, B, C];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
