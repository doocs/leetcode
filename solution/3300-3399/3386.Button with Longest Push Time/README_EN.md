---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3386.Button%20with%20Longest%20Push%20Time/README_EN.md
tags:
    - Array
---

<!-- problem:start -->

# [3386. Button with Longest Push Time](https://leetcode.com/problems/button-with-longest-push-time)

[中文文档](/solution/3300-3399/3386.Button%20with%20Longest%20Push%20Time/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D array <code>events</code> which represents a sequence of events where a child pushes a series of buttons on a keyboard.</p>

<p>Each <code>events[i] = [index<sub>i</sub>, time<sub>i</sub>]</code> indicates that the button at index <code>index<sub>i</sub></code> was pressed at time <code>time<sub>i</sub></code>.</p>

<ul>
	<li>The array is <strong>sorted</strong> in increasing order of <code>time</code>.</li>
	<li>The time taken to press a button is the difference in time between consecutive button presses. The time for the first button is simply the time at which it was pressed.</li>
</ul>

<p>Return the <code>index</code> of the button that took the <strong>longest</strong> time to push. If multiple buttons have the same longest time, return the button with the <strong>smallest</strong> <code>index</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">events = [[1,2],[2,5],[3,9],[1,15]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Button with index 1 is pressed at time 2.</li>
	<li>Button with index 2 is pressed at time 5, so it took <code>5 - 2 = 3</code> units of time.</li>
	<li>Button with index 3 is pressed at time 9, so it took <code>9 - 5 = 4</code> units of time.</li>
	<li>Button with index 1 is pressed again at time 15, so it took <code>15 - 9 = 6</code> units of time.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">events = [[10,5],[1,7]]</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Button with index 10 is pressed at time 5.</li>
	<li>Button with index 1 is pressed at time 7, so it took <code>7 - 5 = 2</code> units of time.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= events.length &lt;= 1000</code></li>
	<li><code>events[i] == [index<sub>i</sub>, time<sub>i</sub>]</code></li>
	<li><code>1 &lt;= index<sub>i</sub>, time<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li>The input is generated such that <code>events</code> is sorted in increasing order of <code>time<sub>i</sub></code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Single Pass

We define two variables $\textit{ans}$ and $t$, representing the index of the button with the longest press time and the press time, respectively.

Next, we start traversing the array $\textit{events}$ from index $k = 1$. For each $k$, we calculate the press time of the current button $d = t2 - t1$, where $t2$ is the press time of the current button and $t1$ is the press time of the previous button. If $d > t$ or $d = t$ and the index $i$ of the current button is less than $\textit{ans}$, we update $\textit{ans} = i$ and $t = d$.

Finally, we return $\textit{ans}$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{events}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def buttonWithLongestTime(self, events: List[List[int]]) -> int:
        ans, t = events[0]
        for (_, t1), (i, t2) in pairwise(events):
            d = t2 - t1
            if d > t or (d == t and i < ans):
                ans, t = i, d
        return ans
```

#### Java

```java
class Solution {
    public int buttonWithLongestTime(int[][] events) {
        int ans = events[0][0], t = events[0][1];
        for (int k = 1; k < events.length; ++k) {
            int i = events[k][0], t2 = events[k][1], t1 = events[k - 1][1];
            int d = t2 - t1;
            if (d > t || (d == t && ans > i)) {
                ans = i;
                t = d;
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
    int buttonWithLongestTime(vector<vector<int>>& events) {
        int ans = events[0][0], t = events[0][1];
        for (int k = 1; k < events.size(); ++k) {
            int i = events[k][0], t2 = events[k][1], t1 = events[k - 1][1];
            int d = t2 - t1;
            if (d > t || (d == t && ans > i)) {
                ans = i;
                t = d;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func buttonWithLongestTime(events [][]int) int {
	ans, t := events[0][0], events[0][1]
	for k, e := range events[1:] {
		i, t2, t1 := e[0], e[1], events[k][1]
		d := t2 - t1
		if d > t || (d == t && i < ans) {
			ans, t = i, d
		}
	}
	return ans
}
```

#### TypeScript

```ts
function buttonWithLongestTime(events: number[][]): number {
    let [ans, t] = events[0];
    for (let k = 1; k < events.length; ++k) {
        const [i, t2] = events[k];
        const d = t2 - events[k - 1][1];
        if (d > t || (d === t && i < ans)) {
            ans = i;
            t = d;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
