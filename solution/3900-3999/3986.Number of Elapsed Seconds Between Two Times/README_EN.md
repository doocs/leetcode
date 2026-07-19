---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3986.Number%20of%20Elapsed%20Seconds%20Between%20Two%20Times/README_EN.md
rating: 1205
source: Weekly Contest 510 Q1
---

<!-- problem:start -->

# [3986. Number of Elapsed Seconds Between Two Times](https://leetcode.com/problems/number-of-elapsed-seconds-between-two-times)

[中文文档](/solution/3900-3999/3986.Number%20of%20Elapsed%20Seconds%20Between%20Two%20Times/README.md)

## Description

<!-- description:start -->

<p>You are given two valid times <code>startTime</code> and <code>endTime</code>, each represented as a string in the format <code>&quot;HH:MM:SS&quot;</code>.</p>

<p>Return the number of seconds that have elapsed from <code>startTime</code> to <code>endTime</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">startTime = &quot;01:00:00&quot;, endTime = &quot;01:00:25&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">25</span></p>

<p><strong>Explanation:</strong></p>
<code>endTime</code> is 25 seconds ahead of <code>startTime</code>.</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">startTime = &quot;12:34:56&quot;, endTime = &quot;13:00:00&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1504</span></p>

<p><strong>Explanation:</strong></p>

<p><code>endTime</code> is 25 minutes and 4 seconds ahead of <code>startTime</code>, which equals 1504 seconds.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>startTime.length == 8</code></li>
	<li><code>endTime.length == 8</code></li>
	<li><code>startTime</code> and <code>endTime</code> are valid times in the format <code>&quot;HH:MM:SS&quot;</code></li>
	<li><code>00 &lt;= HH &lt;= 23</code></li>
	<li><code>00 &lt;= MM &lt;= 59</code></li>
	<li><code>00 &lt;= SS &lt;= 59</code></li>
	<li><code>endTime</code> is not earlier than <code>startTime</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

Convert each time string into the number of seconds elapsed since $00$:$00$:$00$, i.e. $HH \times 3600 + MM \times 60 + SS$, then return the difference between the two values.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def secondsBetweenTimes(self, startTime: str, endTime: str) -> int:
        def f(s: str) -> int:
            return int(s[:2]) * 3600 + int(s[3:5]) * 60 + int(s[6:])

        return f(endTime) - f(startTime)
```

#### Java

```java
class Solution {
    public int secondsBetweenTimes(String startTime, String endTime) {
        return f(endTime) - f(startTime);
    }

    private int f(String s) {
        return Integer.parseInt(s.substring(0, 2)) * 3600 + Integer.parseInt(s.substring(3, 5)) * 60
            + Integer.parseInt(s.substring(6));
    }
}
```

#### C++

```cpp
class Solution {
public:
    int secondsBetweenTimes(string startTime, string endTime) {
        return f(endTime) - f(startTime);
    }

private:
    int f(const string& s) {
        return stoi(s.substr(0, 2)) * 3600
            + stoi(s.substr(3, 2)) * 60
            + stoi(s.substr(6));
    }
};
```

#### Go

```go
func secondsBetweenTimes(startTime string, endTime string) int {
	return f(endTime) - f(startTime)
}

func f(s string) int {
	h, _ := strconv.Atoi(s[:2])
	m, _ := strconv.Atoi(s[3:5])
	sec, _ := strconv.Atoi(s[6:])
	return h*3600 + m*60 + sec
}
```

#### TypeScript

```ts
function secondsBetweenTimes(startTime: string, endTime: string): number {
    return f(endTime) - f(startTime);
}

function f(s: string): number {
    return parseInt(s.slice(0, 2)) * 3600 + parseInt(s.slice(3, 5)) * 60 + parseInt(s.slice(6));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
