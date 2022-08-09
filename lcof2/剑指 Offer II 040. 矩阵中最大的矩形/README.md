# [剑指 Offer II 040. 矩阵中最大的矩形](https://leetcode.cn/problems/PLYXKQ)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个由&nbsp;<code>0</code> 和 <code>1</code>&nbsp;组成的矩阵 <code>matrix</code>&nbsp;，找出只包含 <code>1</code> 的最大矩形，并返回其面积。</p>

<p><strong>注意：</strong>此题 <code>matrix</code>&nbsp;输入格式为一维 <code>01</code> 字符串数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20040.%20%E7%9F%A9%E9%98%B5%E4%B8%AD%E6%9C%80%E5%A4%A7%E7%9A%84%E7%9F%A9%E5%BD%A2/images/maximal.jpg" style="width: 402px; height: 322px;" /></p>

<pre>
<strong>输入：</strong>matrix = ["10100","10111","11111","10010"]
<strong>输出：</strong>6
<strong>解释：</strong>最大矩形如上图所示。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>matrix = []
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>matrix = ["0"]
<strong>输出：</strong>0
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>matrix = ["1"]
<strong>输出：</strong>1
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>matrix = ["00"]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>rows == matrix.length</code></li>
	<li><code>cols == matrix[0].length</code></li>
	<li><code>0 &lt;= row, cols &lt;= 200</code></li>
	<li><code>matrix[i][j]</code> 为 <code>'0'</code> 或 <code>'1'</code></li>
</ul>

<p>&nbsp;</p>

<p>注意：本题与主站 85 题相同（输入参数格式不同）：&nbsp;<a href="https://leetcode.cn/problems/maximal-rectangle/">https://leetcode.cn/problems/maximal-rectangle/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：单调栈**

-   首先在柱状图中求最大矩形面积可以通过单调栈，维护每一列的左边第一个比它小的位置 $L$，和右边第一个比它小的位置 $R$，就能得到以这一列为高的最大矩形面积为 $(R-L-1)*h$。
-   考虑每一行作为底边的柱状图中，能够得到的最大的矩形面积。再对每一行的最大面积取 $max$ 就是最终的答案。
-   柱状图中每一列的高可以通过类似前缀和的方式去维护。
-   假设矩阵大小为 $n*m$，那么时间复杂为 $O(nm)$，空间复杂度为 $O(m)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **C++**

```cpp
class Solution {
public:
    int h[210];
    int l[210], r[210];
    int maximalRectangle(vector<string>& matrix) {
        int n = matrix.size();
        if (n == 0) return 0;
        int m = matrix[0].size();
        int ans = 0;
        stack<int> st;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                h[j] = (matrix[i][j] == '1' ? h[j] + 1 : 0);
                while (st.size() && h[j] <= h[st.top()])
                {
                    ans = max(ans, (j - l[st.top()] - 1) * h[st.top()]);
                    st.pop();
                }
                if (st.size()) l[j] = st.top();
                else l[j] = -1;
                st.push(j);
            }
            while (st.size())
            {
                ans = max(ans, (m - 1 - l[st.top()]) * h[st.top()]);
                st.pop();
            }
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
