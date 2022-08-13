# [1944. Number of Visible People in a Queue](https://leetcode.com/problems/number-of-visible-people-in-a-queue)

[中文文档](/solution/1900-1999/1944.Number%20of%20Visible%20People%20in%20a%20Queue/README.md)

## Description

<p>There are <code>n</code> people standing in a queue, and they numbered from <code>0</code> to <code>n - 1</code> in <strong>left to right</strong> order. You are given an array <code>heights</code> of <strong>distinct</strong> integers where <code>heights[i]</code> represents the height of the <code>i<sup>th</sup></code> person.</p>

<p>A person can <strong>see</strong> another person to their right in the queue if everybody in between is <strong>shorter</strong> than both of them. More formally, the <code>i<sup>th</sup></code> person can see the <code>j<sup>th</sup></code> person if <code>i &lt; j</code> and <code>min(heights[i], heights[j]) &gt; max(heights[i+1], heights[i+2], ..., heights[j-1])</code>.</p>

<p>Return <em>an array </em><code>answer</code><em> of length </em><code>n</code><em> where </em><code>answer[i]</code><em> is the <strong>number of people</strong> the </em><code>i<sup>th</sup></code><em> person can <strong>see</strong> to their right in the queue</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1944.Number%20of%20Visible%20People%20in%20a%20Queue/images/queue-plane.jpg" style="width: 600px; height: 247px;" /></p>

<pre>
<strong>Input:</strong> heights = [10,6,8,5,11,9]
<strong>Output:</strong> [3,1,2,1,1,0]
<strong>Explanation:</strong>
Person 0 can see person 1, 2, and 4.
Person 1 can see person 2.
Person 2 can see person 3 and 4.
Person 3 can see person 4.
Person 4 can see person 5.
Person 5 can see no one since nobody is to the right of them.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> heights = [5,1,2,3,10]
<strong>Output:</strong> [4,1,1,1,0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == heights.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= heights[i] &lt;= 10<sup>5</sup></code></li>
	<li>All the values of <code>heights</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

Monotonic stack.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canSeePersonsCount(self, heights: List[int]) -> List[int]:
        n = len(heights)
        ans = [0] * n
        stack = list()

        for i in range(n - 1, -1, -1):
            while stack:
                ans[i] += 1
                if heights[i] > stack[-1]:
                    stack.pop()
                else:
                    break
            stack.append(heights[i])

        return ans
```

### **Java**

```java

```

### **C++**

```cpp
class Solution {
public:
    vector<int> canSeePersonsCount(vector<int>& heights) {
        int n = heights.size();
        vector<int> ans(n);
        stack<int> stk;
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.empty()) {
                ans[i]++;
                if (heights[i] <= stk.top()) break;
                stk.pop();
            }
            stk.push(heights[i]);
        }
        return ans;
    }
};
```

<!-- tabs:end -->
