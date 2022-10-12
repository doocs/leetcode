# [636. Exclusive Time of Functions](https://leetcode.com/problems/exclusive-time-of-functions)

[中文文档](/solution/0600-0699/0636.Exclusive%20Time%20of%20Functions/README.md)

## Description

<p>On a <strong>single-threaded</strong> CPU, we execute a program containing <code>n</code> functions. Each function has a unique ID between <code>0</code> and <code>n-1</code>.</p>

<p>Function calls are <strong>stored in a <a href="https://en.wikipedia.org/wiki/Call_stack">call stack</a></strong>: when a function call starts, its ID is pushed onto the stack, and when a function call ends, its ID is popped off the stack. The function whose ID is at the top of the stack is <strong>the current function being executed</strong>. Each time a function starts or ends, we write a log with the ID, whether it started or ended, and the timestamp.</p>

<p>You are given a list <code>logs</code>, where <code>logs[i]</code> represents the <code>i<sup>th</sup></code> log message formatted as a string <code>&quot;{function_id}:{&quot;start&quot; | &quot;end&quot;}:{timestamp}&quot;</code>. For example, <code>&quot;0:start:3&quot;</code> means a function call with function ID <code>0</code> <strong>started at the beginning</strong> of timestamp <code>3</code>, and <code>&quot;1:end:2&quot;</code> means a function call with function ID <code>1</code> <strong>ended at the end</strong> of timestamp <code>2</code>. Note that a function can be called <b>multiple times, possibly recursively</b>.</p>

<p>A function&#39;s <strong>exclusive time</strong> is the sum of execution times for all function calls in the program. For example, if a function is called twice, one call executing for <code>2</code> time units and another call executing for <code>1</code> time unit, the <strong>exclusive time</strong> is <code>2 + 1 = 3</code>.</p>

<p>Return <em>the <strong>exclusive time</strong> of each function in an array, where the value at the </em><code>i<sup>th</sup></code><em> index represents the exclusive time for the function with ID </em><code>i</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0636.Exclusive%20Time%20of%20Functions/images/diag1b.png" style="width: 550px; height: 239px;" />
<pre>
<strong>Input:</strong> n = 2, logs = [&quot;0:start:0&quot;,&quot;1:start:2&quot;,&quot;1:end:5&quot;,&quot;0:end:6&quot;]
<strong>Output:</strong> [3,4]
<strong>Explanation:</strong>
Function 0 starts at the beginning of time 0, then it executes 2 for units of time and reaches the end of time 1.
Function 1 starts at the beginning of time 2, executes for 4 units of time, and ends at the end of time 5.
Function 0 resumes execution at the beginning of time 6 and executes for 1 unit of time.
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1, logs = [&quot;0:start:0&quot;,&quot;0:start:2&quot;,&quot;0:end:5&quot;,&quot;0:start:6&quot;,&quot;0:end:6&quot;,&quot;0:end:7&quot;]
<strong>Output:</strong> [8]
<strong>Explanation:</strong>
Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls itself.
Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
Function 0 (initial call) resumes execution then immediately calls itself again.
Function 0 (2nd recursive call) starts at the beginning of time 6 and executes for 1 unit of time.
Function 0 (initial call) resumes execution at the beginning of time 7 and executes for 1 unit of time.
So function 0 spends 2 + 4 + 1 + 1 = 8 units of total time executing.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 2, logs = [&quot;0:start:0&quot;,&quot;0:start:2&quot;,&quot;0:end:5&quot;,&quot;1:start:6&quot;,&quot;1:end:6&quot;,&quot;0:end:7&quot;]
<strong>Output:</strong> [7,1]
<strong>Explanation:</strong>
Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls itself.
Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
Function 0 (initial call) resumes execution then immediately calls function 1.
Function 1 starts at the beginning of time 6, executes 1 unit of time, and ends at the end of time 6.
Function 0 resumes execution at the beginning of time 6 and executes for 2 units of time.
So function 0 spends 2 + 4 + 1 = 7 units of total time executing, and function 1 spends 1 unit of total time executing.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= logs.length &lt;= 500</code></li>
	<li><code>0 &lt;= function_id &lt; n</code></li>
	<li><code>0 &lt;= timestamp &lt;= 10<sup>9</sup></code></li>
	<li>No two start events will happen at the same timestamp.</li>
	<li>No two end events will happen at the same timestamp.</li>
	<li>Each function has an <code>&quot;end&quot;</code> log for each <code>&quot;start&quot;</code> log.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def exclusiveTime(self, n: int, logs: List[str]) -> List[int]:
        ans = [0] * n
        stk = []
        curr = -1
        for log in logs:
            t = log.split(':')
            fid = int(t[0])
            ts = int(t[2])
            if t[1] == 'start':
                if stk:
                    ans[stk[-1]] += ts - curr
                stk.append(fid)
                curr = ts
            else:
                fid = stk.pop()
                ans[fid] += ts - curr + 1
                curr = ts + 1
        return ans
```

### **Java**

```java
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        Deque<Integer> stk = new ArrayDeque<>();
        int curr = -1;
        for (String log : logs) {
            String[] t = log.split(":");
            int fid = Integer.parseInt(t[0]);
            int ts = Integer.parseInt(t[2]);
            if ("start".equals(t[1])) {
                if (!stk.isEmpty()) {
                    ans[stk.peek()] += ts - curr;
                }
                stk.push(fid);
                curr = ts;
            } else {
                fid = stk.pop();
                ans[fid] += ts - curr + 1;
                curr = ts + 1;
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function exclusiveTime(n: number, logs: string[]): number[] {
    const res = new Array(n).fill(0);
    const stack: [number, number][] = [];

    for (const log of logs) {
        const t = log.split(':');
        const [id, state, time] = [Number(t[0]), t[1], Number(t[2])];

        if (state === 'start') {
            if (stack.length !== 0) {
                const pre = stack[stack.length - 1];
                res[pre[0]] += time - pre[1];
            }
            stack.push([id, time]);
        } else {
            const pre = stack.pop();
            res[pre[0]] += time - pre[1] + 1;
            if (stack.length !== 0) {
                stack[stack.length - 1][1] = time + 1;
            }
        }
    }

    return res;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> exclusiveTime(int n, vector<string>& logs) {
        vector<int> ans(n);
        stack<int> stk;
        int curr = -1;
        for (auto& log : logs) {
            char type[10];
            int fid, ts;
            sscanf(log.c_str(), "%d:%[^:]:%d", &fid, type, &ts);
            if (type[0] == 's') {
                if (!stk.empty()) ans[stk.top()] += ts - curr;
                curr = ts;
                stk.push(fid);
            } else {
                fid = stk.top();
                stk.pop();
                ans[fid] += ts - curr + 1;
                curr = ts + 1;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func exclusiveTime(n int, logs []string) []int {
	ans := make([]int, n)
	stk := []int{}
	curr := 1
	for _, log := range logs {
		t := strings.Split(log, ":")
		fid, _ := strconv.Atoi(t[0])
		ts, _ := strconv.Atoi(t[2])
		if t[1][0] == 's' {
			if len(stk) > 0 {
				ans[stk[len(stk)-1]] += ts - curr
			}
			stk = append(stk, fid)
			curr = ts
		} else {
			fid := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			ans[fid] += ts - curr + 1
			curr = ts + 1
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
