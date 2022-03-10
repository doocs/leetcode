# [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures)

[中文文档](/solution/0700-0799/0739.Daily%20Temperatures/README.md)

## Description

<p>Given an array of integers <code>temperatures</code> represents the daily temperatures, return <em>an array</em> <code>answer</code> <em>such that</em> <code>answer[i]</code> <em>is the number of days you have to wait after the</em> <code>i<sup>th</sup></code> <em>day to get a warmer temperature</em>. If there is no future day for which this is possible, keep <code>answer[i] == 0</code> instead.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> temperatures = [73,74,75,71,69,72,76,73]
<strong>Output:</strong> [1,1,4,2,1,1,0,0]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> temperatures = [30,40,50,60]
<strong>Output:</strong> [1,1,1,0]
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> temperatures = [30,60,90]
<strong>Output:</strong> [1,1,0]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;temperatures.length &lt;= 10<sup>5</sup></code></li>
	<li><code>30 &lt;=&nbsp;temperatures[i] &lt;= 100</code></li>
</ul>

## Solutions

Easy solution with stack.

Everytime a higher temperature is found, we update answer of the peak one in the stack.

If the day with higher temperature is not found, we leave the ans to be the default `0`.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        res = [0] * len(temperatures)
        stk = []
        for i, t in enumerate(temperatures):
            while stk and temperatures[stk[-1]] < t:
                j = stk.pop()
                res[j] = i - j
            stk.append(i)
        return res
```

### **Java**

```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!stk.isEmpty() && temperatures[stk.peek()] < temperatures[i]) {
                int j = stk.pop();
                res[j] = i - j;
            }
            stk.push(i);
        }
        return res;
    }
}
```

### **C++**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```cpp
class Solution {
public:
    vector<int> dailyTemperatures(vector<int> &temperatures) {
        int n = temperatures.size();
        vector<int> res(n);
        stack<int> stk;
        for (int i = 0; i < n; ++i)
        {
            while (!stk.empty() && temperatures[stk.top()] < temperatures[i])
            {
                res[stk.top()] = i - stk.top();
                stk.pop();
            }
            stk.push(i);
        }
        return res;
    }
};
```

### **Go**

```go
func dailyTemperatures(temperatures []int) []int {
	res := make([]int, len(temperatures))
	var stk []int
	for i, t := range temperatures {
		for len(stk) > 0 && temperatures[stk[len(stk)-1]] < t {
			j := stk[len(stk)-1]
			res[j] = i - j
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, i)
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
