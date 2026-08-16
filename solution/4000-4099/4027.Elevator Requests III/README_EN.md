---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4027.Elevator%20Requests%20III/README_EN.md
---

<!-- problem:start -->

# [4027. Elevator Requests III](https://leetcode.com/problems/elevator-requests-iii)

[中文文档](/solution/4000-4099/4027.Elevator%20Requests%20III/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> denoting the number of floors in a building, where the floors are numbered from 0 to <code>n - 1</code>.</p>

<p>You are also given an integer <code>start</code> and a 2D integer array <code>requests</code>, where <code>requests[i] = [arrival<sub>i</sub>, floor<sub>i</sub>]</code> indicates that a request for <code>floor<sub>i</sub></code> is made at time <code>arrival<sub>i</sub></code>.</p>

<p>At time 0, the elevator is at floor <code>start</code>.</p>

<p>At each second, the elevator may move <strong>up</strong> by 1 floor, move <strong>down</strong> by 1 floor, or <strong>remain</strong> on its current floor.</p>

<p>A request can be fulfilled <strong>only</strong> at or after its arrival time; it is fulfilled <strong>instantly</strong> when the elevator is on its requested floor at any time from its arrival time onward.</p>

<p>Return the <strong>minimum</strong> time needed to fulfill all requests.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 9, start = 0, requests = [[0,8],[6,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Move from floor 0 (<code>start</code>) to floor 5 (<code>requests[1][1]</code>) in 5 seconds, reaching at time 5. Since <code>requests[1][0] = 6</code>, wait until time 6 to fulfill it.</li>
	<li>Move from floor 5 to floor 8 (<code>requests[0][1]</code>) in 3 seconds, fulfilling it at time 9.</li>
</ul>

<p>Thus, all requests are fulfilled by time 9.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 8, start = 5, requests = [[1,7],[7,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Move from floor 5 (<code>start</code>) to floor 7 (<code>requests[0][1]</code>) in 2 seconds, reaching at time 2. Since <code>requests[0][0] = 1</code> has already passed, floor 7 is fulfilled at time 2.</li>
	<li>Move from floor 7 to floor 3 (<code>requests[1][1]</code>) in 4 seconds, reaching at time 6. Since <code>requests[1][0] = 7</code>, wait until time 7.</li>
</ul>

<p>Thus, all requests are fulfilled by time 7.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 7, start = 3, requests = [[0,5],[0,1],[6,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Move from floor 3 (<code>start</code>) to floor 5 (<code>requests[0][1]</code>) in 2 seconds, fulfilling it at time 2.</li>
	<li>Move from floor 5 to floor 1 (<code>requests[1][1]</code>) in 4 seconds, fulfilling it at time 6.</li>
	<li>Move from floor 1 to floor 3 (<code>requests[2][1]</code>) in 2 seconds, reaching at time 8. Its request arrived at <code>requests[2][0] = 6</code>, so floor 3 is fulfilled at time 8.</li>
</ul>

<p>Thus, all requests are fulfilled by time 8.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= requests.length &lt;= 16</code></li>
	<li><code>requests[i] == [arrival<sub>i</sub>, floor<sub>i</sub>]</code></li>
	<li><code>0 &lt;= arrival<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= start, floor<sub>i</sub> &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: State Compression DP

The number of floors $n$ can be as large as $10^9$, but there are at most $m \le 16$ requests, so we only need to plan a path among at most $m$ target floors.

This is a traveling salesman problem with arrival-time constraints. Let $f[i][j]$ be the minimum time to fulfill the set of requests represented by bitmask $i$, with request $j$ fulfilled last.

For each state $i$ that contains request $j$, let $i_0 = i \oplus 2^j$:

- If $i_0 = 0$, we start from $\textit{start}$, and the time is $\max(|\textit{start} - \textit{floor}_j|, \textit{arrival}_j)$;
- Otherwise, we enumerate the previous request $j_0$, and the time is $\max(f[i_0][j_0] + |\textit{floor}_{j_0} - \textit{floor}_j|, \textit{arrival}_j)$.

The answer is the minimum of $f[2^m-1][j]$ over all $j$.

The time complexity is $O(m^2 \times 2^m)$, and the space complexity is $O(m \times 2^m)$, where $m$ is the number of requests.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def elevatorRequests(self, n: int, start: int, requests: list[list[int]]) -> int:
        m = len(requests)
        f = [[0] * m for _ in range(1 << m)]
        for i in range(1 << m):
            for j in range(m):
                if i >> j & 1:
                    f[i][j] = inf
                    i0 = i ^ (1 << j)
                    if i0 == 0:
                        d = abs(start - requests[j][1])
                        f[i][j] = min(f[i][j], max(d, requests[j][0]))
                    else:
                        for j0 in range(m):
                            if j0 != j and (i >> j0 & 1):
                                d = abs(requests[j0][1] - requests[j][1])
                                f[i][j] = min(
                                    f[i][j], max(f[i0][j0] + d, requests[j][0])
                                )
        return min(f[(1 << m) - 1][j] for j in range(m))
```

#### Java

```java
class Solution {
    public long elevatorRequests(int n, int start, int[][] requests) {
        int m = requests.length;
        long[][] f = new long[1 << m][m];

        for (int i = 0; i < (1 << m); i++) {
            for (int j = 0; j < m; j++) {
                if (((i >> j) & 1) == 1) {
                    f[i][j] = Long.MAX_VALUE;
                    int i0 = i ^ (1 << j);

                    if (i0 == 0) {
                        long d = Math.abs(start - requests[j][1]);
                        f[i][j] = Math.min(f[i][j], Math.max(d, requests[j][0]));
                    } else {
                        for (int j0 = 0; j0 < m; j0++) {
                            if (j0 != j && ((i >> j0) & 1) == 1) {
                                long d = Math.abs(requests[j0][1] - requests[j][1]);

                                f[i][j]
                                    = Math.min(f[i][j], Math.max(f[i0][j0] + d, requests[j][0]));
                            }
                        }
                    }
                }
            }
        }

        long ans = Long.MAX_VALUE;

        for (int j = 0; j < m; j++) {
            ans = Math.min(ans, f[(1 << m) - 1][j]);
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long elevatorRequests(int n, int start, vector<vector<int>>& requests) {
        int m = requests.size();

        vector<vector<long long>> f(1 << m, vector<long long>(m, 0));

        for (int i = 0; i < (1 << m); i++) {
            for (int j = 0; j < m; j++) {
                if ((i >> j) & 1) {
                    f[i][j] = LLONG_MAX;
                    int i0 = i ^ (1 << j);

                    if (i0 == 0) {
                        long long d = abs(start - requests[j][1]);

                        f[i][j] = min(
                            f[i][j],
                            max(d, (long long) requests[j][0]));
                    } else {
                        for (int j0 = 0; j0 < m; j0++) {
                            if (j0 != j && ((i >> j0) & 1)) {
                                long long d = abs(
                                    requests[j0][1] - requests[j][1]);

                                f[i][j] = min(
                                    f[i][j],
                                    max(
                                        f[i0][j0] + d,
                                        (long long) requests[j][0]));
                            }
                        }
                    }
                }
            }
        }

        long long ans = LLONG_MAX;
        for (int j = 0; j < m; j++) {
            ans = min(ans, f[(1 << m) - 1][j]);
        }

        return ans;
    }
};
```

#### Go

```go
func elevatorRequests(n int, start int, requests [][]int) int64 {
	m := len(requests)
	f := make([][]int64, 1<<m)

	for i := range f {
		f[i] = make([]int64, m)
	}

	const INF int64 = 1 << 60

	for i := 0; i < 1<<m; i++ {
		for j := 0; j < m; j++ {
			if (i>>j)&1 == 1 {
				f[i][j] = INF
				i0 := i ^ (1 << j)

				if i0 == 0 {
					d := int64(abs(start - requests[j][1]))
					f[i][j] = min(
						f[i][j],
						max(d, int64(requests[j][0])),
					)
				} else {
					for j0 := 0; j0 < m; j0++ {
						if j0 != j && (i>>j0)&1 == 1 {
							d := int64(abs(
								requests[j0][1] - requests[j][1],
							))

							f[i][j] = min(
								f[i][j],
								max(
									f[i0][j0]+d,
									int64(requests[j][0]),
								),
							)
						}
					}
				}
			}
		}
	}

	full := (1 << m) - 1
	ans := INF

	for j := 0; j < m; j++ {
		ans = min(ans, f[full][j])
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
function elevatorRequests(n: number, start: number, requests: number[][]): number {
    const m = requests.length;
    const f: number[][] = Array.from({ length: 1 << m }, () => Array(m).fill(0));

    for (let i = 0; i < 1 << m; i++) {
        for (let j = 0; j < m; j++) {
            if (((i >> j) & 1) === 1) {
                f[i][j] = Infinity;

                const i0 = i ^ (1 << j);

                if (i0 === 0) {
                    const d = Math.abs(start - requests[j][1]);

                    f[i][j] = Math.min(f[i][j], Math.max(d, requests[j][0]));
                } else {
                    for (let j0 = 0; j0 < m; j0++) {
                        if (j0 !== j && ((i >> j0) & 1) === 1) {
                            const d = Math.abs(requests[j0][1] - requests[j][1]);

                            f[i][j] = Math.min(f[i][j], Math.max(f[i0][j0] + d, requests[j][0]));
                        }
                    }
                }
            }
        }
    }

    const full = (1 << m) - 1;
    let ans = Infinity;

    for (let j = 0; j < m; j++) {
        ans = Math.min(ans, f[full][j]);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
