# [2432. The Employee That Worked on the Longest Task](https://leetcode.com/problems/the-employee-that-worked-on-the-longest-task)

[中文文档](/solution/2400-2499/2432.The%20Employee%20That%20Worked%20on%20the%20Longest%20Task/README.md)

## Description

<p>There are <code>n</code> employees, each with a unique id from <code>0</code> to <code>n - 1</code>.</p>

<p>You are given a 2D integer array <code>logs</code> where <code>logs[i] = [id<sub>i</sub>, leaveTime<sub>i</sub>]</code> where:</p>

<ul>
	<li><code>id<sub>i</sub></code> is the id of the employee that worked on the <code>i<sup>th</sup></code> task, and</li>
	<li><code>leaveTime<sub>i</sub></code> is the time at which the employee finished the <code>i<sup>th</sup></code> task. All the values <code>leaveTime<sub>i</sub></code> are <strong>unique</strong>.</li>
</ul>

<p>Note that the <code>i<sup>th</sup></code> task starts the moment right after the <code>(i - 1)<sup>th</sup></code> task ends, and the <code>0<sup>th</sup></code> task starts at time <code>0</code>.</p>

<p>Return <em>the id of the employee that worked the task with the longest time.</em> If there is a tie between two or more employees, return<em> the <strong>smallest</strong> id among them</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 10, logs = [[0,3],[2,5],[0,9],[1,15]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
Task 0 started at 0 and ended at 3 with 3 units of times.
Task 1 started at 3 and ended at 5 with 2 units of times.
Task 2 started at 5 and ended at 9 with 4 units of times.
Task 3 started at 9 and ended at 15 with 6 units of times.
The task with the longest time is task 3 and the employee with id 1 is the one that worked on it, so we return 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 26, logs = [[1,1],[3,7],[2,12],[7,17]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
Task 0 started at 0 and ended at 1 with 1 unit of times.
Task 1 started at 1 and ended at 7 with 6 units of times.
Task 2 started at 7 and ended at 12 with 5 units of times.
Task 3 started at 12 and ended at 17 with 5 units of times.
The tasks with the longest time is task 1. The employees that worked on it is 3, so we return 3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 2, logs = [[0,10],[1,20]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> 
Task 0 started at 0 and ended at 10 with 10 units of times.
Task 1 started at 10 and ended at 20 with 10 units of times.
The tasks with the longest time are tasks 0 and 1. The employees that worked on them are 0 and 1, so we return the smallest id 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= logs.length &lt;= 500</code></li>
	<li><code>logs[i].length == 2</code></li>
	<li><code>0 &lt;= id<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= leaveTime<sub>i</sub> &lt;= 500</code></li>
	<li><code>id<sub>i</sub> != id<sub>i+1</sub></code></li>
	<li><code>leaveTime<sub>i</sub></code> are sorted in a strictly increasing order.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def hardestWorker(self, n: int, logs: List[List[int]]) -> int:
        last = 0
        ans = mx = 0
        for uid, t in logs:
            t -= last
            if mx < t or (mx == t and ans > uid):
                ans = uid
                mx = t
            last += t
        return ans
```

### **Java**

```java
class Solution {
    public int hardestWorker(int n, int[][] logs) {
        int ans = 0;
        int last = 0, mx = 0;
        for (int[] log : logs) {
            int uid = log[0], t = log[1];
            t -= last;
            if (mx < t || (mx == t && ans > uid)) {
                ans = uid;
                mx = t;
            }
            last += t;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int hardestWorker(int n, vector<vector<int>>& logs) {
        int ans = 0, mx = 0, last = 0;
        for (auto& log : logs) {
            int uid = log[0], t = log[1];
            t -= last;
            if (mx < t || (mx == t && ans > uid)) {
                mx = t;
                ans = uid;
            }
            last += t;
        }
        return ans;
    }
};
```

### **Go**

```go
func hardestWorker(n int, logs [][]int) (ans int) {
	var mx, last int
	for _, log := range logs {
		uid, t := log[0], log[1]
		t -= last
		if mx < t || (mx == t && uid < ans) {
			mx = t
			ans = uid
		}
		last += t
	}
	return
}
```

### **C**

```c
#define min(a,b) (((a) < (b)) ? (a) : (b))

int hardestWorker(int n, int **logs, int logsSize, int *logsColSize) {
    int res = 0;
    int max = 0;
    int pre = 0;
    for (int i = 0; i < logsSize; i++) {
        int t = logs[i][1] - pre;
        if (t > max || (t == max && res > logs[i][0])) {
            res = logs[i][0];
            max = t;
        }
        pre = logs[i][1];
    }
    return res;
}
```

### **TypeScript**

```ts
function hardestWorker(n: number, logs: number[][]): number {
    let [ans, mx, last] = [0, 0, 0];
    for (let [uid, t] of logs) {
        t -= last;
        if (mx < t || (mx == t && ans > uid)) {
            ans = uid;
            mx = t;
        }
        last += t;
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn hardest_worker(n: i32, logs: Vec<Vec<i32>>) -> i32 {
        let mut res = 0;
        let mut max = 0;
        let mut pre = 0;
        for log in logs.iter() {
            let t = log[1] - pre;
            if t > max || t == max && res > log[0] {
                res = log[0];
                max = t;
            }
            pre = log[1];
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
