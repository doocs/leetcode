---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1344.Angle%20Between%20Hands%20of%20a%20Clock/README_EN.md
rating: 1324
source: Biweekly Contest 19 Q3
tags:
    - Math
---

<!-- problem:start -->

# [1344. Angle Between Hands of a Clock](https://leetcode.com/problems/angle-between-hands-of-a-clock)

[中文文档](/solution/1300-1399/1344.Angle%20Between%20Hands%20of%20a%20Clock/README.md)

## Description

<!-- description:start -->

<p>Given two numbers, <code>hour</code> and <code>minutes</code>, return <em>the smaller angle (in degrees) formed between the </em><code>hour</code><em> and the </em><code>minute</code><em> hand</em>.</p>

<p>Answers within <code>10<sup>-5</sup></code> of the actual value will be accepted as correct.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1344.Angle%20Between%20Hands%20of%20a%20Clock/images/sample_1_1673.png" style="width: 300px; height: 296px;" />
<pre>
<strong>Input:</strong> hour = 12, minutes = 30
<strong>Output:</strong> 165
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1344.Angle%20Between%20Hands%20of%20a%20Clock/images/sample_2_1673.png" style="width: 300px; height: 301px;" />
<pre>
<strong>Input:</strong> hour = 3, minutes = 30
<strong>Output:</strong> 75
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1344.Angle%20Between%20Hands%20of%20a%20Clock/images/sample_3_1673.png" style="width: 300px; height: 301px;" />
<pre>
<strong>Input:</strong> hour = 3, minutes = 15
<strong>Output:</strong> 7.5
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= hour &lt;= 12</code></li>
	<li><code>0 &lt;= minutes &lt;= 59</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def angleClock(self, hour: int, minutes: int) -> float:
        h = 30 * hour + 0.5 * minutes
        m = 6 * minutes
        diff = abs(h - m)
        return min(diff, 360 - diff)
```

#### Java

```java
class Solution {
    public double angleClock(int hour, int minutes) {
        double h = 30 * hour + 0.5 * minutes;
        double m = 6 * minutes;
        double diff = Math.abs(h - m);
        return Math.min(diff, 360 - diff);
    }
}
```

#### C++

```cpp
class Solution {
public:
    double angleClock(int hour, int minutes) {
        double h = 30 * hour + 0.5 * minutes;
        double m = 6 * minutes;
        double diff = abs(h - m);
        return min(diff, 360 - diff);
    }
};
```

#### Go

```go
func angleClock(hour int, minutes int) float64 {
	h := 30*float64(hour) + 0.5*float64(minutes)
	m := 6 * float64(minutes)
	diff := math.Abs(h - m)
	return math.Min(diff, 360-diff)
}
```

#### TypeScript

```ts
function angleClock(hour: number, minutes: number): number {
    const h = 30 * hour + 0.5 * minutes;
    const m = 6 * minutes;
    const diff = Math.abs(h - m);
    return Math.min(diff, 360 - diff);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
