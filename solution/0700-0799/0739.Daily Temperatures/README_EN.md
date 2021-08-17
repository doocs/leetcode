# [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures)

[中文文档](/solution/0700-0799/0739.Daily%20Temperatures/README.md)

## Description

<p>

Given a list of daily temperatures <code>T</code>, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature.  If there is no future day for which this is possible, put <code>0</code> instead.

</p><p>

For example, given the list of temperatures <code>T = [73, 74, 75, 71, 69, 72, 76, 73]</code>, your output should be <code>[1, 1, 4, 2, 1, 1, 0, 0]</code>.

</p>



<p><b>Note:</b>

The length of <code>temperatures</code> will be in the range <code>[1, 30000]</code>.

Each temperature will be an integer in the range <code>[30, 100]</code>.

</p>

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
