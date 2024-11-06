---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2534.Time%20Taken%20to%20Cross%20the%20Door/README_EN.md
tags:
    - Queue
    - Array
    - Simulation
---

<!-- problem:start -->

# [2534. Time Taken to Cross the Door 🔒](https://leetcode.com/problems/time-taken-to-cross-the-door)

[中文文档](/solution/2500-2599/2534.Time%20Taken%20to%20Cross%20the%20Door/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> persons numbered from <code>0</code> to <code>n - 1</code> and a door. Each person can enter or exit through the door once, taking one second.</p>

<p>You are given a <strong>non-decreasing</strong> integer array <code>arrival</code> of size <code>n</code>, where <code>arrival[i]</code> is the arrival time of the <code>i<sup>th</sup></code> person at the door. You are also given an array <code>state</code> of size <code>n</code>, where <code>state[i]</code> is <code>0</code> if person <code>i</code> wants to enter through the door or <code>1</code> if they want to exit through the door.</p>

<p>If two or more persons want to use the door at the <strong>same</strong> time, they follow the following rules:</p>

<ul>
	<li>If the door was <strong>not</strong> used in the previous second, then the person who wants to <strong>exit</strong> goes first.</li>
	<li>If the door was used in the previous second for <strong>entering</strong>, the person who wants to enter goes first.</li>
	<li>If the door was used in the previous second for <strong>exiting</strong>, the person who wants to <strong>exit</strong> goes first.</li>
	<li>If multiple persons want to go in the same direction, the person with the <strong>smallest</strong> index goes first.</li>
</ul>

<p>Return <em>an array </em><code>answer</code><em> of size </em><code>n</code><em> where </em><code>answer[i]</code><em> is the second at which the </em><code>i<sup>th</sup></code><em> person crosses the door</em>.</p>

<p><strong>Note</strong> that:</p>

<ul>
	<li>Only one person can cross the door at each second.</li>
	<li>A person may arrive at the door and wait without entering or exiting to follow the mentioned rules.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> arrival = [0,1,1,2,4], state = [0,1,0,0,1]
<strong>Output:</strong> [0,3,1,2,4]
<strong>Explanation:</strong> At each second we have the following:
- At t = 0: Person 0 is the only one who wants to enter, so they just enter through the door.
- At t = 1: Person 1 wants to exit, and person 2 wants to enter. Since the door was used the previous second for entering, person 2 enters.
- At t = 2: Person 1 still wants to exit, and person 3 wants to enter. Since the door was used the previous second for entering, person 3 enters.
- At t = 3: Person 1 is the only one who wants to exit, so they just exit through the door.
- At t = 4: Person 4 is the only one who wants to exit, so they just exit through the door.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> arrival = [0,0,0], state = [1,0,1]
<strong>Output:</strong> [0,2,1]
<strong>Explanation:</strong> At each second we have the following:
- At t = 0: Person 1 wants to enter while persons 0 and 2 want to exit. Since the door was not used in the previous second, the persons who want to exit get to go first. Since person 0 has a smaller index, they exit first.
- At t = 1: Person 1 wants to enter, and person 2 wants to exit. Since the door was used in the previous second for exiting, person 2 exits.
- At t = 2: Person 1 is the only one who wants to enter, so they just enter through the door.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == arrival.length == state.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= arrival[i] &lt;= n</code></li>
	<li><code>arrival</code> is sorted in <strong>non-decreasing</strong> order.</li>
	<li><code>state[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Queue + Simulation

We define two queues, where $q[0]$ stores the indices of people who want to enter, and $q[1]$ stores the indices of people who want to exit.

We maintain a variable $t$ to represent the current time, and a variable $st$ to represent the current state of the door. When $st = 1$, it means the door is not in use or someone exited in the previous second. When $st = 0$, it means someone entered in the previous second. Initially, $t = 0$ and $st = 1$.

We traverse the array $\textit{arrival}$. For each person, if the current time $t$ is less than or equal to the time the person arrives at the door $\textit{arrival}[i]$, we add the person's index to the corresponding queue $q[\text{state}[i]]$.

Then we check if both queues $q[0]$ and $q[1]$ are not empty. If both are not empty, we dequeue the front element from the queue $q[st]$ and assign the current time $t$ to the person's passing time. If only one queue is not empty, we update the value of $st$ based on which queue is not empty, then dequeue the front element from that queue and assign the current time $t$ to the person's passing time. If both queues are empty, we update the value of $st$ to $1$, indicating the door is not in use.

Next, we increment the time $t$ by $1$ and continue traversing the array $\textit{arrival}$ until all people have passed through the door.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ represents the length of the array $\textit{arrival}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def timeTaken(self, arrival: List[int], state: List[int]) -> List[int]:
        q = [deque(), deque()]
        n = len(arrival)
        t = i = 0
        st = 1
        ans = [0] * n
        while i < n or q[0] or q[1]:
            while i < n and arrival[i] <= t:
                q[state[i]].append(i)
                i += 1
            if q[0] and q[1]:
                ans[q[st].popleft()] = t
            elif q[0] or q[1]:
                st = 0 if q[0] else 1
                ans[q[st].popleft()] = t
            else:
                st = 1
            t += 1
        return ans
```

#### Java

```java
class Solution {
    public int[] timeTaken(int[] arrival, int[] state) {
        Deque<Integer>[] q = new Deque[2];
        Arrays.setAll(q, i -> new ArrayDeque<>());
        int n = arrival.length;
        int t = 0, i = 0, st = 1;
        int[] ans = new int[n];
        while (i < n || !q[0].isEmpty() || !q[1].isEmpty()) {
            while (i < n && arrival[i] <= t) {
                q[state[i]].add(i++);
            }
            if (!q[0].isEmpty() && !q[1].isEmpty()) {
                ans[q[st].poll()] = t;
            } else if (!q[0].isEmpty() || !q[1].isEmpty()) {
                st = q[0].isEmpty() ? 1 : 0;
                ans[q[st].poll()] = t;
            } else {
                st = 1;
            }
            ++t;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> timeTaken(vector<int>& arrival, vector<int>& state) {
        int n = arrival.size();
        queue<int> q[2];
        int t = 0, i = 0, st = 1;
        vector<int> ans(n);

        while (i < n || !q[0].empty() || !q[1].empty()) {
            while (i < n && arrival[i] <= t) {
                q[state[i]].push(i++);
            }

            if (!q[0].empty() && !q[1].empty()) {
                ans[q[st].front()] = t;
                q[st].pop();
            } else if (!q[0].empty() || !q[1].empty()) {
                st = q[0].empty() ? 1 : 0;
                ans[q[st].front()] = t;
                q[st].pop();
            } else {
                st = 1;
            }

            ++t;
        }

        return ans;
    }
};
```

#### Go

```go
func timeTaken(arrival []int, state []int) []int {
	n := len(arrival)
	q := [2][]int{}
	t, i, st := 0, 0, 1
	ans := make([]int, n)

	for i < n || len(q[0]) > 0 || len(q[1]) > 0 {
		for i < n && arrival[i] <= t {
			q[state[i]] = append(q[state[i]], i)
			i++
		}

		if len(q[0]) > 0 && len(q[1]) > 0 {
			ans[q[st][0]] = t
			q[st] = q[st][1:]
		} else if len(q[0]) > 0 || len(q[1]) > 0 {
			if len(q[0]) == 0 {
				st = 1
			} else {
				st = 0
			}
			ans[q[st][0]] = t
			q[st] = q[st][1:]
		} else {
			st = 1
		}

		t++
	}

	return ans
}
```

#### TypeScript

```ts
function timeTaken(arrival: number[], state: number[]): number[] {
    const n = arrival.length;
    const q: number[][] = [[], []];
    let [t, i, st] = [0, 0, 1];
    const ans: number[] = Array(n).fill(0);

    while (i < n || q[0].length || q[1].length) {
        while (i < n && arrival[i] <= t) {
            q[state[i]].push(i++);
        }

        if (q[0].length && q[1].length) {
            ans[q[st][0]] = t;
            q[st].shift();
        } else if (q[0].length || q[1].length) {
            st = q[0].length ? 0 : 1;
            ans[q[st][0]] = t;
            q[st].shift();
        } else {
            st = 1;
        }

        t++;
    }

    return ans;
}
```

#### Rust

```rust
use std::collections::VecDeque;

impl Solution {
    pub fn time_taken(arrival: Vec<i32>, state: Vec<i32>) -> Vec<i32> {
        let n = arrival.len();
        let mut q = vec![VecDeque::new(), VecDeque::new()];
        let mut t = 0;
        let mut i = 0;
        let mut st = 1;
        let mut ans = vec![-1; n];

        while i < n || !q[0].is_empty() || !q[1].is_empty() {
            while i < n && arrival[i] <= t {
                q[state[i] as usize].push_back(i);
                i += 1;
            }

            if !q[0].is_empty() && !q[1].is_empty() {
                ans[*q[st].front().unwrap()] = t;
                q[st].pop_front();
            } else if !q[0].is_empty() || !q[1].is_empty() {
                st = if q[0].is_empty() { 1 } else { 0 };
                ans[*q[st].front().unwrap()] = t;
                q[st].pop_front();
            } else {
                st = 1;
            }

            t += 1;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
