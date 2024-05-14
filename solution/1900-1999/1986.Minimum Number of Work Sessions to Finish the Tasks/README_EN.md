---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1986.Minimum%20Number%20of%20Work%20Sessions%20to%20Finish%20the%20Tasks/README_EN.md
rating: 1995
tags:
    - Bit Manipulation
    - Array
    - Dynamic Programming
    - Backtracking
    - Bitmask
---

# [1986. Minimum Number of Work Sessions to Finish the Tasks](https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks)

[中文文档](/solution/1900-1999/1986.Minimum%20Number%20of%20Work%20Sessions%20to%20Finish%20the%20Tasks/README.md)

## Description

<p>There are <code>n</code> tasks assigned to you. The task times are represented as an integer array <code>tasks</code> of length <code>n</code>, where the <code>i<sup>th</sup></code> task takes <code>tasks[i]</code> hours to finish. A <strong>work session</strong> is when you work for <strong>at most</strong> <code>sessionTime</code> consecutive hours and then take a break.</p>

<p>You should finish the given tasks in a way that satisfies the following conditions:</p>

<ul>
	<li>If you start a task in a work session, you must complete it in the <strong>same</strong> work session.</li>
	<li>You can start a new task <strong>immediately</strong> after finishing the previous one.</li>
	<li>You may complete the tasks in <strong>any order</strong>.</li>
</ul>

<p>Given <code>tasks</code> and <code>sessionTime</code>, return <em>the <strong>minimum</strong> number of <strong>work sessions</strong> needed to finish all the tasks following the conditions above.</em></p>

<p>The tests are generated such that <code>sessionTime</code> is <strong>greater</strong> than or <strong>equal</strong> to the <strong>maximum</strong> element in <code>tasks[i]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> tasks = [1,2,3], sessionTime = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can finish the tasks in two work sessions.
- First work session: finish the first and the second tasks in 1 + 2 = 3 hours.
- Second work session: finish the third task in 3 hours.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> tasks = [3,1,3,1,1], sessionTime = 8
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can finish the tasks in two work sessions.
- First work session: finish all the tasks except the last one in 3 + 1 + 3 + 1 = 8 hours.
- Second work session: finish the last task in 1 hour.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> tasks = [1,2,3,4,5], sessionTime = 15
<strong>Output:</strong> 1
<strong>Explanation:</strong> You can finish all the tasks in one work session.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == tasks.length</code></li>
	<li><code>1 &lt;= n &lt;= 14</code></li>
	<li><code>1 &lt;= tasks[i] &lt;= 10</code></li>
	<li><code>max(tasks[i]) &lt;= sessionTime &lt;= 15</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def minSessions(self, tasks: List[int], sessionTime: int) -> int:
        n = len(tasks)
        ok = [False] * (1 << n)
        for i in range(1, 1 << n):
            t = sum(tasks[j] for j in range(n) if i >> j & 1)
            ok[i] = t <= sessionTime
        f = [inf] * (1 << n)
        f[0] = 0
        for i in range(1, 1 << n):
            j = i
            while j:
                if ok[j]:
                    f[i] = min(f[i], f[i ^ j] + 1)
                j = (j - 1) & i
        return f[-1]
```

```java
class Solution {
    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length;
        boolean[] ok = new boolean[1 << n];
        for (int i = 1; i < 1 << n; ++i) {
            int t = 0;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    t += tasks[j];
                }
            }
            ok[i] = t <= sessionTime;
        }
        int[] f = new int[1 << n];
        Arrays.fill(f, 1 << 30);
        f[0] = 0;
        for (int i = 1; i < 1 << n; ++i) {
            for (int j = i; j > 0; j = (j - 1) & i) {
                if (ok[j]) {
                    f[i] = Math.min(f[i], f[i ^ j] + 1);
                }
            }
        }
        return f[(1 << n) - 1];
    }
}
```

```cpp
class Solution {
public:
    int minSessions(vector<int>& tasks, int sessionTime) {
        int n = tasks.size();
        bool ok[1 << n];
        memset(ok, false, sizeof(ok));
        for (int i = 1; i < 1 << n; ++i) {
            int t = 0;
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    t += tasks[j];
                }
            }
            ok[i] = t <= sessionTime;
        }
        int f[1 << n];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int i = 1; i < 1 << n; ++i) {
            for (int j = i; j; j = (j - 1) & i) {
                if (ok[j]) {
                    f[i] = min(f[i], f[i ^ j] + 1);
                }
            }
        }
        return f[(1 << n) - 1];
    }
};
```

```go
func minSessions(tasks []int, sessionTime int) int {
	n := len(tasks)
	ok := make([]bool, 1<<n)
	f := make([]int, 1<<n)
	for i := 1; i < 1<<n; i++ {
		t := 0
		f[i] = 1 << 30
		for j, x := range tasks {
			if i>>j&1 == 1 {
				t += x
			}
		}
		ok[i] = t <= sessionTime
	}
	for i := 1; i < 1<<n; i++ {
		for j := i; j > 0; j = (j - 1) & i {
			if ok[j] {
				f[i] = min(f[i], f[i^j]+1)
			}
		}
	}
	return f[1<<n-1]
}
```

```ts
function minSessions(tasks: number[], sessionTime: number): number {
    const n = tasks.length;
    const ok: boolean[] = new Array(1 << n).fill(false);
    for (let i = 1; i < 1 << n; ++i) {
        let t = 0;
        for (let j = 0; j < n; ++j) {
            if (((i >> j) & 1) === 1) {
                t += tasks[j];
            }
        }
        ok[i] = t <= sessionTime;
    }

    const f: number[] = new Array(1 << n).fill(1 << 30);
    f[0] = 0;
    for (let i = 1; i < 1 << n; ++i) {
        for (let j = i; j > 0; j = (j - 1) & i) {
            if (ok[j]) {
                f[i] = Math.min(f[i], f[i ^ j] + 1);
            }
        }
    }
    return f[(1 << n) - 1];
}
```

<!-- tabs:end -->

<!-- end -->
