---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4020.Elevator%20Requests%20I/README_EN.md
---

<!-- problem:start -->

# [4020. Elevator Requests I](https://leetcode.com/problems/elevator-requests-i)

[中文文档](/solution/4000-4099/4020.Elevator%20Requests%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> denoting the number of floors in a building, where the floors are numbered from 0 to <code>n - 1</code>.</p>

<p>You are also given an integer array <code>requests</code>, where <code>requests</code> represents the sequence of floor requests.</p>

<p>An elevator starts at floor 0, and follows these rules:</p>

<ul>
	<li>The elevator moves one floor per second.</li>
	<li>The elevator serves requests in the given order.</li>
	<li>If the elevator is already on the requested floor, no movement is needed.</li>
	<li>After serving a request, the elevator immediately starts moving toward the next request.</li>
</ul>

<p>Return the <strong>total time</strong> (in seconds) required to serve all requests.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, requests = [2,1,4,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>requests[0] = 2</code>: Moving from floor 0 to floor 2 takes 2 seconds.</li>
	<li><code>requests[1] = 1</code>: Moving from floor 2 to floor 1 takes 1 second.</li>
	<li><code>requests[2] = 4</code>: Moving from floor 1 to floor 4 takes 3 seconds.</li>
	<li><code>requests[3] = 3</code>: Moving from floor 4 to floor 3 takes 1 second.</li>
</ul>

<p>The total time required is <code>2 + 1 + 3 + 1 = 7</code> seconds.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, requests = [2,0,0]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>requests[0] = 2</code>: Moving from floor 0 to floor 2 takes 2 seconds.</li>
	<li><code>requests[1] = 0</code>: Moving from floor 2 to floor 0 takes 2 seconds.</li>
	<li><code>requests[2] = 0</code>: No movement is needed.</li>
</ul>

<p>The total time required is <code>2 + 2 + 0 = 4</code> seconds.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= requests.length &lt;= 100</code></li>
	<li><code>0 &lt;= requests[i] &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

The elevator starts at floor $0$ and serves requests in the given order. The travel time between two consecutive requests is the absolute difference of their floor numbers. The first request goes from floor $0$ to $\textit{requests}[0]$, which takes $\textit{requests}[0]$ seconds. Then we add the absolute differences of adjacent requests.

The time complexity is $O(m)$, and the space complexity is $O(1)$, where $m$ is the number of requests.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def elevatorRequests(self, n: int, requests: list[int]) -> int:
        return requests[0] + sum(abs(x - y) for x, y in pairwise(requests))
```

#### Java

```java
class Solution {
    public int elevatorRequests(int n, int[] requests) {
        int ans = requests[0];
        for (int i = 1; i < requests.length; ++i) {
            ans += Math.abs(requests[i - 1] - requests[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int elevatorRequests(int n, vector<int>& requests) {
        int ans = requests[0];
        for (int i = 1; i < requests.size(); ++i) {
            ans += abs(requests[i - 1] - requests[i]);
        }
        return ans;
    }
};
```

#### Go

```go
func elevatorRequests(n int, requests []int) int {
	ans := requests[0]
	for i, x := range requests[1:] {
		ans += abs(x - requests[i])
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function elevatorRequests(n: number, requests: number[]): number {
    let ans: number = requests[0];
    for (let i = 1; i < requests.length; ++i) {
        ans += Math.abs(requests[i] - requests[i - 1]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
