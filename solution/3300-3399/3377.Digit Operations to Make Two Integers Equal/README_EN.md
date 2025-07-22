---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3377.Digit%20Operations%20to%20Make%20Two%20Integers%20Equal/README_EN.md
rating: 2186
source: Biweekly Contest 145 Q3
tags:
    - Graph
    - Math
    - Number Theory
    - Shortest Path
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3377. Digit Operations to Make Two Integers Equal](https://leetcode.com/problems/digit-operations-to-make-two-integers-equal)

[中文文档](/solution/3300-3399/3377.Digit%20Operations%20to%20Make%20Two%20Integers%20Equal/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>n</code> and <code>m</code> that consist of the <strong>same</strong> number of digits.</p>

<p>You can perform the following operations <strong>any</strong> number of times:</p>

<ul>
	<li>Choose <strong>any</strong> digit from <code>n</code> that is not 9 and <strong>increase</strong> it by 1.</li>
	<li>Choose <strong>any</strong> digit from <code>n</code> that is not 0 and <strong>decrease</strong> it by 1.</li>
</ul>

<p>The integer <code>n</code> must not be a <span data-keyword="prime-number">prime</span> number at any point, including its original value and after each operation.</p>

<p>The cost of a transformation is the sum of <strong>all</strong> values that <code>n</code> takes throughout the operations performed.</p>

<p>Return the <strong>minimum</strong> cost to transform <code>n</code> into <code>m</code>. If it is impossible, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 10, m = 12</span></p>

<p><strong>Output:</strong> <span class="example-io">85</span></p>

<p><strong>Explanation:</strong></p>

<p>We perform the following operations:</p>

<ul>
	<li>Increase the first digit, now <code>n = <u><strong>2</strong></u>0</code>.</li>
	<li>Increase the second digit, now <code>n = 2<strong><u>1</u></strong></code>.</li>
	<li>Increase the second digit, now <code>n = 2<strong><u>2</u></strong></code>.</li>
	<li>Decrease the first digit, now <code>n = <strong><u>1</u></strong>2</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, m = 8</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>It is impossible to make <code>n</code> equal to <code>m</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 6, m = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong>&nbsp;</p>

<p>Since 2 is already a prime, we can&#39;t make <code>n</code> equal to <code>m</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, m &lt; 10<sup>4</sup></code></li>
	<li><code>n</code> and <code>m</code> consist of the same number of digits.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
import heapq

class Solution:
    def __init__(self):
        self.sieve = []

    def run_sieve(self):
        self.sieve = [True] * 100000
        self.sieve[0], self.sieve[1] = False, False
        for i in range(2, 100000):
            if self.sieve[i]:
                for j in range(2 * i, 100000, i):
                    self.sieve[j] = False

    def solve(self, n, m):
        pq = []
        heapq.heappush(pq, (n, n))
        visited = set()

        while pq:
            sum_, cur = heapq.heappop(pq)

            if cur in visited:
                continue
            visited.add(cur)

            if cur == m:
                return sum_

            s = list(str(cur))
            for i in range(len(s)):
                c = s[i]

                if s[i] < '9':
                    s[i] = chr(ord(s[i]) + 1)
                    next_ = int(''.join(s))
                    if not self.sieve[next_] and next_ not in visited:
                        heapq.heappush(pq, (sum_ + next_, next_))
                    s[i] = c

                if s[i] > '0' and not (i == 0 and s[i] == '1'):
                    s[i] = chr(ord(s[i]) - 1)
                    next_ = int(''.join(s))
                    if not self.sieve[next_] and next_ not in visited:
                        heapq.heappush(pq, (sum_ + next_, next_))
                    s[i] = c

        return -1

    def minOperations(self, n, m):
        self.run_sieve()
        if self.sieve[n] or self.sieve[m]:
            return -1
        return self.solve(n, m)
```

#### Java

```java
class Solution {
    private boolean[] sieve;

    private void runSieve() {
        sieve = new boolean[100000];
        Arrays.fill(sieve, true);
        sieve[0] = false;
        sieve[1] = false;
        for (int i = 2; i < 100000; i++) {
            if (sieve[i]) {
                for (int j = 2 * i; j < 100000; j += i) {
                    sieve[j] = false;
                }
            }
        }
    }

    private int solve(int n, int m) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[] {n, n});
        Set<Integer> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int sum = top[0], cur = top[1];

            if (visited.contains(cur)) {
                continue;
            }
            visited.add(cur);

            if (cur == m) {
                return sum;
            }

            char[] s = String.valueOf(cur).toCharArray();
            for (int i = 0; i < s.length; i++) {
                char c = s[i];

                if (s[i] < '9') {
                    s[i] = (char) (s[i] + 1);
                    int next = Integer.parseInt(new String(s));
                    if (!sieve[next] && !visited.contains(next)) {
                        pq.add(new int[] {sum + next, next});
                    }
                    s[i] = c;
                }

                if (s[i] > '0' && !(i == 0 && s[i] == '1')) {
                    s[i] = (char) (s[i] - 1);
                    int next = Integer.parseInt(new String(s));
                    if (!sieve[next] && !visited.contains(next)) {
                        pq.add(new int[] {sum + next, next});
                    }
                    s[i] = c;
                }
            }
        }

        return -1;
    }

    public int minOperations(int n, int m) {
        runSieve();
        if (sieve[n] || sieve[m]) {
            return -1;
        }
        return solve(n, m);
    }
}
```

#### C++

```cpp
class Solution {
private:
    vector<bool> sieve;
    void runSieve() {
        sieve.resize(100000, true);
        sieve[0] = false, sieve[1] = false;
        for (int i = 2; i < 1e5; ++i) {
            if (sieve[i]) {
                for (int j = 2 * i; j < 1e5; j += i) {
                    sieve[j] = false;
                }
            }
        }
    }
    int solve(int n, int m) {
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
        unordered_set<int> vis;
        pq.push({n, n});
        while (!pq.empty()) {
            int sum = pq.top().first, cur = pq.top().second;
            pq.pop();
            if (vis.find(cur) != vis.end()) continue;
            vis.insert(cur);
            if (cur == m) return sum;
            string s = to_string(cur);
            for (int i = 0; i < s.size(); ++i) {
                char c = s[i];
                if (s[i] < '9') {
                    s[i]++;
                    int next = stoi(s);
                    if (!sieve[next] && vis.find(next) == vis.end()) {
                        pq.push({sum + next, next});
                    }
                    s[i] = c;
                }
                if (s[i] > '0' && !(i == 0 && s[i] == '1')) {
                    s[i]--;
                    int next = stoi(s);
                    if (!sieve[next] && vis.find(next) == vis.end()) {
                        pq.push({sum + next, next});
                    }
                    s[i] = c;
                }
            }
        }
        return -1;
    }

public:
    int minOperations(int n, int m) {
        runSieve();
        if (sieve[n] || sieve[m]) return -1;
        return solve(n, m);
    }
};
```

#### Go

```go
package main

import (
	"container/heap"
	"strconv"
)

type MinHeap [][]int

func (h MinHeap) Len() int            { return len(h) }
func (h MinHeap) Less(i, j int) bool { return h[i][0] < h[j][0] }
func (h MinHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *MinHeap) Push(x interface{}) {
	*h = append(*h, x.([]int))
}
func (h *MinHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

var sieve []bool

func runSieve() {
	sieve = make([]bool, 100000)
	for i := range sieve {
		sieve[i] = true
	}
	sieve[0], sieve[1] = false, false
	for i := 2; i < 100000; i++ {
		if sieve[i] {
			for j := 2 * i; j < 100000; j += i {
				sieve[j] = false
			}
		}
	}
}

func solve(n int, m int) int {
	pq := &MinHeap{}
	heap.Init(pq)
	heap.Push(pq, []int{n, n})
	visited := make(map[int]bool)

	for pq.Len() > 0 {
		top := heap.Pop(pq).([]int)
		sum, cur := top[0], top[1]

		if visited[cur] {
			continue
		}
		visited[cur] = true

		if cur == m {
			return sum
		}

		s := []rune(strconv.Itoa(cur))
		for i := 0; i < len(s); i++ {
			c := s[i]

			if s[i] < '9' {
				s[i]++
				next, _ := strconv.Atoi(string(s))
				if !sieve[next] && !visited[next] {
					heap.Push(pq, []int{sum + next, next})
				}
				s[i] = c
			}

			if s[i] > '0' && !(i == 0 && s[i] == '1') {
				s[i]--
				next, _ := strconv.Atoi(string(s))
				if !sieve[next] && !visited[next] {
					heap.Push(pq, []int{sum + next, next})
				}
				s[i] = c
			}
		}
	}

	return -1
}

func minOperations(n int, m int) int {
	runSieve()
	if sieve[n] || sieve[m] {
		return -1
	}
	return solve(n, m)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
