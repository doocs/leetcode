---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3376.Minimum%20Time%20to%20Break%20Locks%20I/README_EN.md
rating: 1793
source: Biweekly Contest 145 Q2
tags:
    - Bit Manipulation
    - Array
    - Dynamic Programming
    - Backtracking
    - Bitmask
---

<!-- problem:start -->

# [3376. Minimum Time to Break Locks I](https://leetcode.com/problems/minimum-time-to-break-locks-i)

[中文文档](/solution/3300-3399/3376.Minimum%20Time%20to%20Break%20Locks%20I/README.md)

## Description

<!-- description:start -->

<p>Bob is stuck in a dungeon and must break <code>n</code> locks, each requiring some amount of <strong>energy</strong> to break. The required energy for each lock is stored in an array called <code>strength</code> where <code>strength[i]</code> indicates the energy needed to break the <code>i<sup>th</sup></code> lock.</p>

<p>To break a lock, Bob uses a sword with the following characteristics:</p>

<ul>
	<li>The initial energy of the sword is 0.</li>
	<li>The initial factor <code><font face="monospace">x</font></code> by which the energy of the sword increases is 1.</li>
	<li>Every minute, the energy of the sword increases by the current factor <code>x</code>.</li>
	<li>To break the <code>i<sup>th</sup></code> lock, the energy of the sword must reach <strong>at least</strong> <code>strength[i]</code>.</li>
	<li>After breaking a lock, the energy of the sword resets to 0, and the factor <code>x</code> increases by a given value <code>k</code>.</li>
</ul>

<p>Your task is to determine the <strong>minimum</strong> time in minutes required for Bob to break all <code>n</code> locks and escape the dungeon.</p>

<p>Return the <strong>minimum </strong>time required for Bob to break all <code>n</code> locks.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">strength = [3,4,1], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Time</th>
			<th style="border: 1px solid black;">Energy</th>
			<th style="border: 1px solid black;">x</th>
			<th style="border: 1px solid black;">Action</th>
			<th style="border: 1px solid black;">Updated x</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Nothing</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Break 3<sup>rd</sup> Lock</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">Nothing</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">Break 2<sup>nd</sup> Lock</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">Break 1<sup>st</sup> Lock</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
	</tbody>
</table>

<p>The locks cannot be broken in less than 4 minutes; thus, the answer is 4.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">strength = [2,5,4], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Time</th>
			<th style="border: 1px solid black;">Energy</th>
			<th style="border: 1px solid black;">x</th>
			<th style="border: 1px solid black;">Action</th>
			<th style="border: 1px solid black;">Updated x</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Nothing</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Nothing</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">Break 1<sup>st</sup> Lock</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">Nothing</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">Break 2<sup>n</sup><sup>d</sup> Lock</td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">Break 3<sup>r</sup><sup>d</sup> Lock</td>
			<td style="border: 1px solid black;">7</td>
		</tr>
	</tbody>
</table>

<p>The locks cannot be broken in less than 5 minutes; thus, the answer is 5.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == strength.length</code></li>
	<li><code>1 &lt;= n &lt;= 8</code></li>
	<li><code>1 &lt;= K &lt;= 10</code></li>
	<li><code>1 &lt;= strength[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findMinimumTime(self, strength: List[int], K: int) -> int:
        @cache
        def dfs(i: int) -> int:
            if i == (1 << len(strength)) - 1:
                return 0
            cnt = i.bit_count()
            x = 1 + cnt * K
            ans = inf
            for j, s in enumerate(strength):
                if i >> j & 1 ^ 1:
                    ans = min(ans, dfs(i | 1 << j) + (s + x - 1) // x)
            return ans

        return dfs(0)
```

#### Java

```java
class Solution {
    private List<Integer> strength;
    private Integer[] f;
    private int k;
    private int n;

    public int findMinimumTime(List<Integer> strength, int K) {
        n = strength.size();
        f = new Integer[1 << n];
        k = K;
        this.strength = strength;
        return dfs(0);
    }

    private int dfs(int i) {
        if (i == (1 << n) - 1) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int cnt = Integer.bitCount(i);
        int x = 1 + cnt * k;
        f[i] = 1 << 30;
        for (int j = 0; j < n; ++j) {
            if ((i >> j & 1) == 0) {
                f[i] = Math.min(f[i], dfs(i | 1 << j) + (strength.get(j) + x - 1) / x);
            }
        }
        return f[i];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findMinimumTime(vector<int>& strength, int K) {
        int n = strength.size();
        int f[1 << n];
        memset(f, -1, sizeof(f));
        int k = K;
        auto dfs = [&](this auto&& dfs, int i) -> int {
            if (i == (1 << n) - 1) {
                return 0;
            }
            if (f[i] != -1) {
                return f[i];
            }
            int cnt = __builtin_popcount(i);
            int x = 1 + k * cnt;
            f[i] = INT_MAX;
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1 ^ 1) {
                    f[i] = min(f[i], dfs(i | 1 << j) + (strength[j] + x - 1) / x);
                }
            }
            return f[i];
        };
        return dfs(0);
    }
};
```

#### Go

```go
func findMinimumTime(strength []int, K int) int {
	n := len(strength)
	f := make([]int, 1<<n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i == 1<<n-1 {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		x := 1 + K*bits.OnesCount(uint(i))
		f[i] = 1 << 30
		for j, s := range strength {
			if i>>j&1 == 0 {
				f[i] = min(f[i], dfs(i|1<<j)+(s+x-1)/x)
			}
		}
		return f[i]
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function findMinimumTime(strength: number[], K: number): number {
    const n = strength.length;
    const f: number[] = Array(1 << n).fill(-1);
    const dfs = (i: number): number => {
        if (i === (1 << n) - 1) {
            return 0;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        f[i] = Infinity;
        const x = 1 + K * bitCount(i);
        for (let j = 0; j < n; ++j) {
            if (((i >> j) & 1) == 0) {
                f[i] = Math.min(f[i], dfs(i | (1 << j)) + Math.ceil(strength[j] / x));
            }
        }
        return f[i];
    };
    return dfs(0);
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
