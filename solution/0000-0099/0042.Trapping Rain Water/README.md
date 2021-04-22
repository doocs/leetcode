# [42. 接雨水](https://leetcode-cn.com/problems/trapping-rain-water)

[English Version](/solution/0000-0099/0042.Trapping%20Rain%20Water/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定 <em>n</em> 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><img src="/solution/0000-0099/0042.Trapping Rain Water/images/rainwatertrap.png" style="height: 161px; width: 412px;" /></p>

<pre>
<strong>输入：</strong>height = [0,1,0,2,1,0,1,3,2,1,2,1]
<strong>输出：</strong>6
<strong>解释：</strong>上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>height = [4,2,0,3,2,5]
<strong>输出：</strong>9
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == height.length</code></li>
	<li><code>0 <= n <= 3 * 10<sup>4</sup></code></li>
	<li><code>0 <= height[i] <= 10<sup>5</sup></code></li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划法。

对于下标 i，水能达到的最大高度等于下标 i 左右两侧的最大高度的最小值，再减去 `height[i]` 就能得到当前柱子所能存的水量。

同[面试题 17.21. 直方图的水量](/lcci/17.21.Volume%20of%20Histogram/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def trap(self, height: List[int]) -> int:
        n = len(height)
        if n < 3:
            return 0

        left_max = [height[0]] * n
        for i in range(1, n):
            left_max[i] = max(left_max[i - 1], height[i])

        right_max = [height[n - 1]] * n
        for i in range(n - 2, -1, -1):
            right_max[i] = max(right_max[i + 1], height[i])

        res = 0
        for i in range(n):
            res += min(left_max[i], right_max[i]) - height[i]
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int trap(int[] height) {
        int n;
        if ((n = height.length) < 3) return 0;

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
