# [621. Task Scheduler](https://leetcode.com/problems/task-scheduler)

[中文文档](/solution/0600-0699/0621.Task%20Scheduler/README.md)

## Description

<p>Given a characters array <code>tasks</code>, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.</p>

<p>However, there is a non-negative integer&nbsp;<code>n</code> that represents the cooldown period between&nbsp;two <b>same tasks</b>&nbsp;(the same letter in the array), that is that there must be at least <code>n</code> units of time between any two same tasks.</p>

<p>Return <em>the least number of units of times that the CPU will take to finish all the given tasks</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> tasks = [&quot;A&quot;,&quot;A&quot;,&quot;A&quot;,&quot;B&quot;,&quot;B&quot;,&quot;B&quot;], n = 2
<strong>Output:</strong> 8
<strong>Explanation:</strong> 
A -&gt; B -&gt; idle -&gt; A -&gt; B -&gt; idle -&gt; A -&gt; B
There is at least 2 units of time between any two same tasks.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> tasks = [&quot;A&quot;,&quot;A&quot;,&quot;A&quot;,&quot;B&quot;,&quot;B&quot;,&quot;B&quot;], n = 0
<strong>Output:</strong> 6
<strong>Explanation:</strong> On this case any permutation of size 6 would work since n = 0.
[&quot;A&quot;,&quot;A&quot;,&quot;A&quot;,&quot;B&quot;,&quot;B&quot;,&quot;B&quot;]
[&quot;A&quot;,&quot;B&quot;,&quot;A&quot;,&quot;B&quot;,&quot;A&quot;,&quot;B&quot;]
[&quot;B&quot;,&quot;B&quot;,&quot;B&quot;,&quot;A&quot;,&quot;A&quot;,&quot;A&quot;]
...
And so on.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> tasks = [&quot;A&quot;,&quot;A&quot;,&quot;A&quot;,&quot;A&quot;,&quot;A&quot;,&quot;A&quot;,&quot;B&quot;,&quot;C&quot;,&quot;D&quot;,&quot;E&quot;,&quot;F&quot;,&quot;G&quot;], n = 2
<strong>Output:</strong> 16
<strong>Explanation:</strong> 
One possible solution is
A -&gt; B -&gt; C -&gt; A -&gt; D -&gt; E -&gt; A -&gt; F -&gt; G -&gt; A -&gt; idle -&gt; idle -&gt; A -&gt; idle -&gt; idle -&gt; A
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= task.length &lt;= 10<sup>4</sup></code></li>
	<li><code>tasks[i]</code> is upper-case English letter.</li>
	<li>The integer <code>n</code> is in the range <code>[0, 100]</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def leastInterval(self, tasks: List[str], n: int) -> int:
        cnt = Counter(tasks)
        x = max(cnt.values())
        s = sum(v == x for v in cnt.values())
        return max(len(tasks), (x - 1) * (n + 1) + s)
```

### **Java**

```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] cnt = new int[26];
        int x = 0;
        for (char c : tasks) {
            c -= 'A';
            ++cnt[c];
            x = Math.max(x, cnt[c]);
        }
        int s = 0;
        for (int v : cnt) {
            if (v == x) {
                ++s;
            }
        }
        return Math.max(tasks.length, (x - 1) * (n + 1) + s);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int leastInterval(vector<char>& tasks, int n) {
        vector<int> cnt(26);
        int x = 0;
        for (char c : tasks) {
            c -= 'A';
            ++cnt[c];
            x = max(x, cnt[c]);
        }
        int s = 0;
        for (int v : cnt) {
            s += v == x;
        }
        return max((int) tasks.size(), (x - 1) * (n + 1) + s);
    }
};
```

### **Go**

```go
func leastInterval(tasks []byte, n int) int {
	cnt := make([]int, 26)
	x := 0
	for _, c := range tasks {
		c -= 'A'
		cnt[c]++
		x = max(x, cnt[c])
	}
	s := 0
	for _, v := range cnt {
		if v == x {
			s++
		}
	}
	return max(len(tasks), (x-1)*(n+1)+s)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **C#**

```cs
public class Solution {
    public int LeastInterval(char[] tasks, int n) {
        int[] cnt = new int[26];
        int x = 0;
        foreach (char c in tasks) {
            cnt[c - 'A']++;
            x = Math.Max(x, cnt[c - 'A']);
        }
        int s = 0;
        foreach (int v in cnt) {
            s = v == x ? s + 1 : s;
        }
        return Math.Max(tasks.Length, (x - 1) * (n + 1) + s);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
