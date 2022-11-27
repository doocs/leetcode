# [2481. 分割圆的最少切割次数](https://leetcode.cn/problems/minimum-cuts-to-divide-a-circle)

[English Version](/solution/2400-2499/2481.Minimum%20Cuts%20to%20Divide%20a%20Circle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>圆内一个 <strong>有效切割</strong>&nbsp;，符合以下二者之一：</p>

<ul>
	<li>该切割是两个端点在圆上的线段，且该线段经过圆心。</li>
	<li>该切割是一端在圆心另一端在圆上的线段。</li>
</ul>

<p>一些有效和无效的切割如下图所示。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2481.Minimum%20Cuts%20to%20Divide%20a%20Circle/images/alldrawio.png" style="width: 450px; height: 174px;" /></p>

<p>给你一个整数&nbsp;<code>n</code>&nbsp;，请你返回将圆切割成相等的&nbsp;<code>n</code>&nbsp;等分的&nbsp;<strong>最少</strong>&nbsp;切割次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2481.Minimum%20Cuts%20to%20Divide%20a%20Circle/images/11drawio.png" style="width: 200px; height: 200px;" /></p>

<pre>
<b>输入：</b>n = 4
<b>输出：</b>2
<b>解释：</b>
上图展示了切割圆 2 次，得到四等分。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2481.Minimum%20Cuts%20to%20Divide%20a%20Circle/images/22drawio.png" style="width: 200px; height: 201px;" /></p>

<pre>
<b>输入：</b>n = 3
<b>输出：</b>3
<strong>解释：</strong>
最少需要切割 3 次，将圆切成三等分。
少于 3 次切割无法将圆切成大小相等面积相同的 3 等分。
同时可以观察到，第一次切割无法将圆切割开。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：分类讨论**

-   当 $n=1$ 时，不需要切割，返回 $0$；
-   当 $n$ 为奇数时，不存在共线的情况，返回 $n$；
-   当 $n$ 为偶数时，可以两两共线，返回 $\frac{n}{2}$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfCuts(self, n: int) -> int:
        return n if n > 1 and n % 2 else n >> 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numberOfCuts(int n) {
        return n > 1 && n % 2 == 1 ? n : n >> 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfCuts(int n) {
        return n > 1 && n % 2 == 1 ? n : n >> 1;
    }
};
```

### **Go**

```go
func numberOfCuts(n int) int {
	if n > 1 && n%2 == 1 {
		return n
	}
	return n >> 1
}
```

### **...**

```

```

<!-- tabs:end -->
