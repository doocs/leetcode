---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3894.Traffic%20Signal%20Color/README_EN.md
rating: 1222
source: Biweekly Contest 180 Q1
---

<!-- problem:start -->

# [3894. Traffic Signal Color](https://leetcode.com/problems/traffic-signal-color)

[中文文档](/solution/3800-3899/3894.Traffic%20Signal%20Color/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>timer</code> representing the remaining time (in seconds) on a traffic signal.</p>

<p>The signal follows these rules:</p>

<ul>
	<li>If <code>timer == 0</code>, the signal is <code>&quot;Green&quot;</code></li>
	<li>If <code>timer == 30</code>, the signal is <code>&quot;Orange&quot;</code></li>
	<li>If <code>30 &lt; timer &lt;= 90</code>, the signal is <code>&quot;Red&quot;</code></li>
</ul>

<p>Return the current state of the signal. If none of the above conditions are met, return <code>&quot;Invalid&quot;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">timer = 60</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;Red&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Since <code>timer = 60</code>, and <code>30 &lt; timer &lt;= 90</code>, the answer is <code>&quot;Red&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">timer = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;Invalid&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Since <code>timer = 5</code>, it does not satisfy any of the given conditions, the answer is <code>&quot;Invalid&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= timer &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We determine the answer according to the conditions described in the problem and return the corresponding string.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def trafficSignal(self, timer: int) -> str:
        if timer == 0:
            return "Green"
        if timer == 30:
            return "Orange"
        if 30 < timer <= 90:
            return "Red"
        return "Invalid"
```

#### Java

```java
class Solution {
    public String trafficSignal(int timer) {
        if (timer == 0) {
            return "Green";
        }
        if (timer == 30) {
            return "Orange";
        }
        if (timer > 30 && timer <= 90) {
            return "Red";
        }
        return "Invalid";
    }
}
```

#### C++

```cpp
class Solution {
public:
    string trafficSignal(int timer) {
        if (timer == 0) {
            return "Green";
        }
        if (timer == 30) {
            return "Orange";
        }
        if (timer > 30 && timer <= 90) {
            return "Red";
        }
        return "Invalid";
    }
};
```

#### Go

```go
func trafficSignal(timer int) string {
	switch {
	case timer == 0:
		return "Green"
	case timer == 30:
		return "Orange"
	case timer > 30 && timer <= 90:
		return "Red"
	default:
		return "Invalid"
	}
}
```

#### TypeScript

```ts
function trafficSignal(timer: number): string {
    if (timer === 0) {
        return 'Green';
    }
    if (timer === 30) {
        return 'Orange';
    }
    if (timer > 30 && timer <= 90) {
        return 'Red';
    }
    return 'Invalid';
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
