# [剑指 Offer II 039. 直方图最大矩形面积](https://leetcode.cn/problems/0ynMMM)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定非负整数数组 <code>heights</code>&nbsp;，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 <code>1</code> 。</p>

<p>求在该柱状图中，能够勾勒出来的矩形的最大面积。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20039.%20%E7%9B%B4%E6%96%B9%E5%9B%BE%E6%9C%80%E5%A4%A7%E7%9F%A9%E5%BD%A2%E9%9D%A2%E7%A7%AF/images/histogram.jpg" /></p>

<pre>
<strong>输入：</strong>heights = [2,1,5,6,2,3]
<strong>输出：</strong>10
<strong>解释：</strong>最大的矩形为图中红色区域，面积为 10
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20039.%20%E7%9B%B4%E6%96%B9%E5%9B%BE%E6%9C%80%E5%A4%A7%E7%9F%A9%E5%BD%A2%E9%9D%A2%E7%A7%AF/images/histogram-1.jpg" /></p>

<pre>
<strong>输入：</strong> heights = [2,4]
<b>输出：</b> 4</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= heights.length &lt;=10<sup>5</sup></code></li>
	<li><code>0 &lt;= heights[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 84&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/largest-rectangle-in-histogram/">https://leetcode.cn/problems/largest-rectangle-in-histogram/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

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

我们遍历每个柱体，若当前的柱体高度大于等于栈顶柱体的高度，就直接将当前柱体入栈，否则若当前的柱体高度小于栈顶柱体的高度，说明当前栈顶柱体找到了右边的第一个小于自身的柱体，那么就可以将栈顶柱体出栈来计算以其为高的矩形的面积了。

```cpp
class Solution {
public:
    int largestRectangleArea(vector<int>& heights) {
        int maxarea = 0;
        stack<int> s;
        heights.insert(heights.begin(), 0);
        heights.push_back(0);


        for (int i = 0; i < heights.size(); i++) {
            while (!s.empty() && heights[i] < heights[s.top()]) {
                int h = heights[s.top()];
                s.pop();
                maxarea = max(maxarea, h * (i - s.top() - 1));
            }


            s.push(i);
        }


        return maxarea;
    }
};
```

### **...**

```


```

<!-- tabs:end -->
