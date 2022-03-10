# [739. 每日温度](https://leetcode-cn.com/problems/daily-temperatures)

[English Version](/solution/0700-0799/0739.Daily%20Temperatures/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组&nbsp;<code>temperatures</code>&nbsp;，表示每天的温度，返回一个数组&nbsp;<code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code>&nbsp;是指在第 <code>i</code> 天之后，<span style="font-size:10.5pt"><span style="font-family:Calibri"><span style="font-size:10.5000pt"><span style="font-family:宋体"><font face="宋体">才会有更高的温度</font></span></span></span></span>。如果气温在这之后都不会升高，请在该位置用&nbsp;<code>0</code> 来代替。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> <code>temperatures</code> = [73,74,75,71,69,72,76,73]
<strong>输出:</strong>&nbsp;[1,1,4,2,1,1,0,0]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> temperatures = [30,40,50,60]
<strong>输出:</strong>&nbsp;[1,1,1,0]
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> temperatures = [30,60,90]
<strong>输出: </strong>[1,1,0]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;temperatures.length &lt;= 10<sup>5</sup></code></li>
	<li><code>30 &lt;=&nbsp;temperatures[i]&nbsp;&lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

单调栈常见模型：找出每个数左/右边**离它最近的**且**比它大/小的数**。模板：

```python
stk = []
for i in range(n):
    while stk and check(stk[-1], i):
        stk.pop()
    stk.append(i)
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
