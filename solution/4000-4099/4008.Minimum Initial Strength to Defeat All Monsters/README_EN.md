---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4008.Minimum%20Initial%20Strength%20to%20Defeat%20All%20Monsters/README_EN.md
rating: 1776
source: Biweekly Contest 188 Q3
---

<!-- problem:start -->

# [4008. Minimum Initial Strength to Defeat All Monsters](https://leetcode.com/problems/minimum-initial-strength-to-defeat-all-monsters)

[中文文档](/solution/4000-4099/4008.Minimum%20Initial%20Strength%20to%20Defeat%20All%20Monsters/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>monsters</code>, where <code>monsters[i]</code> represents the strength of the <code>i<sup>th</sup></code> monster.</p>

<p>You are also given a 2D integer array <code>boosts</code>, where <code>boosts[i] = [l<sub>i</sub>, r<sub>i</sub>, v<sub>i</sub>]</code> indicates that <code>v<sub>i</sub></code> is added to your <strong>temporary bonus</strong> while fighting any monster whose index lies in <code>[l<sub>i</sub>, r<sub>i</sub>]</code>. Boost ranges may overlap, and the values of all applicable boosts are added together.</p>

<p>You start with a <strong>non-negative</strong> initial strength and fight the monsters from left to right.</p>

<p>For each monster at index <code>i</code>:</p>

<ul>
	<li>Let <code>bonus</code> be the <strong>sum</strong> of the values of all boosts that apply to monster <code>i</code>.</li>
	<li>You can defeat the monster only if your current strength plus <code>bonus</code> is <strong>at least</strong> <code>monsters[i]</code>.</li>
	<li>After defeating the monster, only your current strength decreases by <code>monsters[i]</code>. If it becomes <strong>negative</strong>, it is set to 0.</li>
</ul>

<p>Return the <strong>minimum</strong> initial strength required to defeat all monsters.</p>

<p>Note: The temporary bonus is used only to determine whether the current monster can be defeated. It does not otherwise change your current strength.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">monsters = [5,10,15], boosts = [[1,1,10]]</span></p>

<p><strong>Output:</strong> <span class="example-io">30</span></p>

<p><strong>Explanation:</strong></p>

<p>Let&#39;s start with an initial strength of 30.</p>

<ul>
	<li><code>monsters[0] = 5</code>: At index 0, the bonus is 0. Since <code>30 + 0 &gt;= 5</code>, this monster can be defeated. The strength becomes <code>30 - 5 = 25</code>.</li>
	<li><code>monsters[1] = 10</code>: At index 1, the bonus is 10. Since <code>25 + 10 &gt;= 10</code>, this monster can be defeated. The strength becomes <code>25 - 10 = 15</code>.</li>
	<li><code>monsters[2] = 15</code>: At index 2, the bonus is 0. Since <code>15 + 0 &gt;= 15</code>, this monster can be defeated. The strength becomes <code>15 - 15 = 0</code>.</li>
</ul>

<p>Thus, the minimum initial strength required is 30.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">monsters = [5,10,15], boosts = [[1,2,10],[1,2,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>Let&#39;s start with an initial strength of 5.</p>

<ul>
	<li><code>monsters[0] = 5</code>: The bonus is 0. Since <code>5 + 0 &gt;= 5</code>, the monster can be defeated. The strength becomes <code>5 - 5 = 0</code>.</li>
	<li><code>monsters[1] = 10</code>: The two overlapping boosts provide <code>bonus = 10 + 5 = 15</code>. Since <code>0 + 15 &gt;= 10</code>, the monster can be defeated. The strength remains 0.</li>
	<li><code>monsters[2] = 15</code>: The two overlapping boosts again provide <code>bonus = 15</code>. Since <code>0 + 15 &gt;= 15</code>, the monster can be defeated. The strength remains 0.</li>
</ul>

<p>Thus, the minimum initial strength required is 5.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= monsters.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= monsters[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= boosts.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>boosts[i] == [l<sub>i</sub>, r<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt;= r<sub>i</sub> &lt; monsters.length</code></li>
	<li><code>1 &lt;= v<sub>i</sub> &lt;= 10<sup>9</sup></code>​​​​​​​</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Difference Array + Binary Search

Each boost adds a value to an entire index range $[l, r]$, so we first apply all boosts using a difference array $d$. The $\textit{bonus}$ when fighting the $i$-th monster is then the prefix sum $\sum_{j=0}^{i} d[j]$.

Next, we binary search the initial strength $v$. For a given $v$, we simulate the fights from left to right: maintain the current $\textit{bonus}$ (the prefix sum of the difference array); if $v + \textit{bonus} < \textit{monsters}[i]$, the monster cannot be defeated and $v$ is infeasible; otherwise, we defeat it, decrease $v$ by $\textit{monsters}[i]$, and reset $v$ to $0$ if it becomes negative. If all monsters can be defeated, $v$ is feasible.

A larger initial strength never makes it harder to defeat all monsters, so feasibility is monotonic in $v$, and we can binary search the minimum feasible initial strength. The upper bound of the search is set to $10^{15}$ (the total strength of all monsters is at most $5 \times 10^4 \times 10^9 = 5 \times 10^{13}$).

The time complexity is $O((n + m) \times \log M)$, and the space complexity is $O(n)$, where $n$ is the number of monsters, $m$ is the number of boosts, and $M = 10^{15}$ is the upper bound of the binary search.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minInitialStrength(self, monsters: list[int], boosts: list[list[int]]) -> int:
        def check(v: int) -> bool:
            bonus = 0
            for a, b in zip(monsters, d):
                bonus += b
                if v + bonus < a:
                    return False
                v -= a
                v = max(v, 0)
            return True

        n = len(monsters)
        d = [0] * (n + 1)
        for l, r, v in boosts:
            d[l] += v
            d[r + 1] -= v

        l, r = 0, 10**15
        while l < r:
            mid = (l + r) >> 1
            if check(mid):
                r = mid
            else:
                l = mid + 1
        return l
```

#### Java

```java
class Solution {
    private int[] monsters;
    private long[] d;

    public long minInitialStrength(int[] monsters, int[][] boosts) {
        this.monsters = monsters;
        int n = monsters.length;
        d = new long[n + 1];
        for (int[] b : boosts) {
            d[b[0]] += b[2];
            d[b[1] + 1] -= b[2];
        }

        long left = 0, right = (long) 1e15;
        while (left < right) {
            long mid = (left + right) >>> 1;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(long v) {
        long bonus = 0;
        for (int i = 0; i < monsters.length; i++) {
            bonus += d[i];
            if (v + bonus < monsters[i]) {
                return false;
            }
            v -= monsters[i];
            if (v < 0) {
                v = 0;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minInitialStrength(vector<int>& monsters, vector<vector<int>>& boosts) {
        int n = monsters.size();
        vector<long long> d(n + 1);
        for (auto& b : boosts) {
            d[b[0]] += b[2];
            d[b[1] + 1] -= b[2];
        }

        auto check = [&](long long v) -> bool {
            long long bonus = 0;
            for (int i = 0; i < n; i++) {
                bonus += d[i];
                if (v + bonus < monsters[i]) {
                    return false;
                }
                v -= monsters[i];
                if (v < 0) {
                    v = 0;
                }
            }
            return true;
        };

        long long left = 0, right = 1000000000000000LL;
        while (left < right) {
            long long mid = (left + right) / 2;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
```

#### Go

```go
func minInitialStrength(monsters []int, boosts [][]int) int64 {
	n := len(monsters)
	d := make([]int64, n+1)
	for _, b := range boosts {
		d[b[0]] += int64(b[2])
		d[b[1]+1] -= int64(b[2])
	}

	check := func(v int64) bool {
		var bonus int64
		for i, a := range monsters {
			bonus += d[i]
			if v+bonus < int64(a) {
				return false
			}
			v -= int64(a)
			if v < 0 {
				v = 0
			}
		}
		return true
	}

	var left, right int64 = 0, 1000000000000000
	for left < right {
		mid := (left + right) / 2
		if check(mid) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}
```

#### TypeScript

```ts
function minInitialStrength(monsters: number[], boosts: number[][]): number {
    const n = monsters.length;
    const d = new Array<number>(n + 1).fill(0);

    for (const [l, r, v] of boosts) {
        d[l] += v;
        d[r + 1] -= v;
    }

    const check = (v: number): boolean => {
        let bonus = 0;
        for (let i = 0; i < n; i++) {
            bonus += d[i];
            if (v + bonus < monsters[i]) {
                return false;
            }
            v -= monsters[i];
            if (v < 0) {
                v = 0;
            }
        }
        return true;
    };

    let left = 0;
    let right = 1e15;
    while (left < right) {
        const mid = Math.floor((left + right) / 2);
        if (check(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
