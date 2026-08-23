---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4025.Minimize%20the%20Maximum%20Waiting%20Time%20at%20Synchronized%20Traffic%20Lights/README_EN.md
rating: 1456
source: Weekly Contest 515 Q2
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [4025. Minimize the Maximum Waiting Time at Synchronized Traffic Lights](https://leetcode.com/problems/minimize-the-maximum-waiting-time-at-synchronized-traffic-lights)

[中文文档](/solution/4000-4099/4025.Minimize%20the%20Maximum%20Waiting%20Time%20at%20Synchronized%20Traffic%20Lights/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>period</code> and an integer array <code>lights</code>, where <code>lights[i]</code> is the duration, in seconds, of the green phase of the <code>i<sup>th</sup></code> traffic light.</p>

<p>At time 0, every traffic light starts at the beginning of its green phase. Their cycles are synchronized: every traffic light starts a new cycle at the same time, and every cycle lasts <strong>exactly</strong> <code>period</code> seconds. Therefore, the red phase of the <code>i<sup>th</sup></code> traffic light lasts for <code>period - lights[i]</code> seconds.</p>

<p>You are also given an integer array <code>arrivalTime</code>, where <code>arrivalTime[j]</code> is the arrival time, in seconds, of the <code>j<sup>th</sup></code> car.</p>

<p>Each car must be assigned to <strong>exactly</strong> one traffic light. Multiple cars may be assigned to the same traffic light. Any number of cars may cross the same traffic light simultaneously while it is green. Cars do not block or delay one another.</p>

<p>For a car <code>j</code> assigned to the <code>i<sup>th</sup></code> traffic light, let <code>r = arrivalTime[j] % period</code>. If <code>r &lt; lights[i]</code>, its waiting time is 0. Otherwise, its waiting time is <code>period - r</code>.</p>

<p>The <strong>penalty</strong> of an assignment is the <strong>maximum</strong> waiting time among all cars.</p>

<p>Return an integer denoting the <strong>minimum possible penalty</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">period = 8, lights = [2,3], arrivalTime = [2,5,8,11]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal solution is:</p>

<ul>
	<li>Assign <code>arrivalTime[0]</code> to the traffic light with <code>lights[1] = 3</code>. Here, <code>r = 2 % 8 = 2</code>. Since <code>2 &lt; 3</code>, the waiting time is 0.</li>
	<li>Assign <code>arrivalTime[1]</code> to the traffic light with <code>lights[0] = 2</code>. Here, <code>r = 5 % 8 = 5</code>. Since <code>5 &gt;= 2</code>, the waiting time is <code>8 - 5 = 3</code>.</li>
	<li>Assign <code>arrivalTime[2]</code> to the traffic light with <code>lights[0] = 2</code>. Here, <code>r = 8 % 8 = 0</code>. Since <code>0 &lt; 2</code>, the waiting time is 0.</li>
	<li>Assign <code>arrivalTime[3]</code> to the traffic light with <code>lights[0] = 2</code>. Here, <code>r = 11 % 8 = 3</code>. Since <code>3 &gt;= 2</code>, the waiting time is <code>8 - 3 = 5</code>.</li>
</ul>

<p>The penalty of this assignment is 5, which is the minimum possible. Other optimal assignments may exist.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">period = 10, lights = [3,6,8], arrivalTime = [4,9,15]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal solution is:</p>

<ul>
	<li>Assign <code>arrivalTime[0]</code> to the traffic light with <code>lights[2] = 8</code>. Here, <code>r = 4 % 10 = 4</code>. Since <code>4 &lt; 8</code>, the waiting time is 0.</li>
	<li>Assign <code>arrivalTime[1]</code> to the traffic light with <code>lights[2] = 8</code>. Here, <code>r = 9 % 10 = 9</code>. Since <code>9 &gt;= 8</code>, the waiting time is <code>10 - 9 = 1</code>.</li>
	<li>Assign <code>arrivalTime[2]</code> to the traffic light with <code>lights[2] = 8</code>. Here, <code>r = 15 % 10 = 5</code>. Since <code>5 &lt; 8</code>, the waiting time is 0.</li>
</ul>

<p>The penalty of this assignment is 1, which is the minimum possible.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">period = 5, lights = [2], arrivalTime = [2,3,4,5,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal solution is:</p>

<ul>
	<li>Assign <code>arrivalTime[0]</code> to the traffic light with <code>lights[0] = 2</code>. Here, <code>r = 2 % 5 = 2</code>. Since <code>2 &gt;= 2</code>, the waiting time is <code>5 - 2 = 3</code>.</li>
	<li>Assign <code>arrivalTime[1]</code> to the traffic light with <code>lights[0] = 2</code>. Here, <code>r = 3 % 5 = 3</code>. Since <code>3 &gt;= 2</code>, the waiting time is <code>5 - 3 = 2</code>.</li>
	<li>Assign <code>arrivalTime[2]</code> to the traffic light with <code>lights[0] = 2</code>. Here, <code>r = 4 % 5 = 4</code>. Since <code>4 &gt;= 2</code>, the waiting time is <code>5 - 4 = 1</code>.</li>
	<li>Assign <code>arrivalTime[3]</code> to the traffic light with <code>lights[0] = 2</code>. Here, <code>r = 5 % 5 = 0</code>. Since <code>0 &lt; 2</code>, the waiting time is 0.</li>
	<li>Assign <code>arrivalTime[4]</code> to the traffic light with <code>lights[0] = 2</code>. Here, <code>r = 6 % 5 = 1</code>. Since <code>1 &lt; 2</code>, the waiting time is 0.</li>
</ul>

<p>The penalty of this assignment is 3, which is the minimum possible.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= period &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= lights.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= lights[i] &lt;= period - 1</code></li>
	<li><code>1 &lt;= arrivalTime.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arrivalTime[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

Let $\textit{mx} = \max(\textit{lights})$ be the longest green duration. For car $j$, let $r = \textit{arrivalTime}[j] \bmod \textit{period}$.

- If $r < \textit{mx}$, we can assign the car to the light with the longest green phase, and the waiting time is $0$.
- If $r \ge \textit{mx}$, then $r \ge \textit{lights}[i]$ for every light, so the waiting time is $\textit{period} - r$ regardless of the assignment.

Therefore, the penalty is the maximum of $\textit{period} - r$ over all cars with $r \ge \textit{mx}$. If every car can pass during a green light, the answer is $0$.

The time complexity is $O(n + m)$, and the space complexity is $O(1)$, where $n$ and $m$ are the lengths of $\textit{lights}$ and $\textit{arrivalTime}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minPenalty(self, period: int, lights: list[int], arrivalTime: list[int]) -> int:
        mx = max(lights)
        ans = 0
        for x in arrivalTime:
            r = x % period
            if r >= mx:
                ans = max(ans, period - r)
        return ans
```

#### Java

```java
class Solution {
    public int minPenalty(int period, int[] lights, int[] arrivalTime) {
        int mx = 0;
        for (int x : lights) {
            mx = Math.max(mx, x);
        }

        int ans = 0;

        for (int x : arrivalTime) {
            int r = x % period;

            if (r >= mx) {
                ans = Math.max(ans, period - r);
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
    int minPenalty(int period, vector<int>& lights, vector<int>& arrivalTime) {
        int mx = ranges::max(lights);

        int ans = 0;

        for (int x : arrivalTime) {
            int r = x % period;

            if (r >= mx) {
                ans = max(ans, period - r);
            }
        }

        return ans;
    }
};
```

#### Go

```go
func minPenalty(period int, lights []int, arrivalTime []int) int {
	mx := slices.Max(lights)
	ans := 0

	for _, x := range arrivalTime {
		r := x % period

		if r >= mx {
			ans = max(ans, period-r)
		}
	}

	return ans
}
```

#### TypeScript

```ts
function minPenalty(period: number, lights: number[], arrivalTime: number[]): number {
    const mx = Math.max(...lights);

    let ans = 0;

    for (const x of arrivalTime) {
        const r = x % period;

        if (r >= mx) {
            ans = Math.max(ans, period - r);
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
