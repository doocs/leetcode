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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def dailyTemperatures(self, T: List[int]) -> List[int]:
        n = len(T)
        res = [0 for _ in range(n)]
        s = []
        for i in range(n):
            while s and T[s[-1]] < T[i]:
                j = s.pop()
                res[j] = i - j
            s.append(i)
        return res
```

### **Java**

```java
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] res = new int[n];
        Deque<Integer> s = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!s.isEmpty() && T[s.peek()] < T[i]) {
                int j = s.pop();
                res[j] = i - j;
            }
            s.push(i);
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
    vector<int> dailyTemperatures(vector<int>& T) {
        int n = T.size();
        vector<int> ans(n);
        stack<int> s;
        for(int i = 0; i < n; ++i) {
            while(!s.empty() && T[s.top()] < T[i]) {
                int pre = s.top();
                s.pop();
                ans[pre] = i - pre;
            }
            s.push(i);
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
