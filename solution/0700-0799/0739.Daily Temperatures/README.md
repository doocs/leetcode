# [739. 每日温度](https://leetcode-cn.com/problems/daily-temperatures)

[English Version](/solution/0700-0799/0739.Daily%20Temperatures/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>根据每日 <code>气温</code> 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用&nbsp;<code>0</code> 来代替。</p>

<p>例如，给定一个列表&nbsp;<code>temperatures = [73, 74, 75, 71, 69, 72, 76, 73]</code>，你的输出应该是&nbsp;<code>[1, 1, 4, 2, 1, 1, 0, 0]</code>。</p>

<p><strong>提示：</strong><code>气温</code> 列表长度的范围是&nbsp;<code>[1, 30000]</code>。每个气温的值的均为华氏度，都是在&nbsp;<code>[30, 100]</code>&nbsp;范围内的整数。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

栈实现，栈存放 T 中元素的的下标 i，结果用数组 res 存储。

遍历 T，遍历到 `T[i]` 时：

- 若栈不为空，并且栈顶元素小于 `T[i]` 时，弹出栈顶元素 j，并且 `res[j]` 赋值为 `i - j`。
- 然后将 i 压入栈中。

最后返回结果数组 res 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
