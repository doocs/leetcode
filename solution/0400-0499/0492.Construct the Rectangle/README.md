# [492. 构造矩形](https://leetcode.cn/problems/construct-the-rectangle)

[English Version](/solution/0400-0499/0492.Construct%20the%20Rectangle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>作为一位web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。 所以，现给定一个具体的矩形页面面积，你的任务是设计一个长度为 L 和宽度为 W 且满足以下要求的矩形的页面。要求：</p>

<ol>
	<li>你设计的矩形页面必须等于给定的目标面积。</li>
	<li>宽度 <code>W</code>&nbsp;不应大于长度 <code>L</code> ，换言之，要求 <code>L &gt;= W </code>。</li>
	<li>长度 <code>L</code> 和宽度 <code>W</code>&nbsp;之间的差距应当尽可能小。</li>
</ol>

<p>返回一个&nbsp;<em>数组</em>&nbsp;<code>[L, W]</code>，其中 <em><code>L</code> 和 <code>W</code> 是你按照顺序设计的网页的长度和宽度</em>。<br />
&nbsp;</p>

<p><strong>示例1：</strong></p>

<pre>
<strong>输入:</strong> 4
<strong>输出:</strong> [2, 2]
<strong>解释:</strong> 目标面积是 4， 所有可能的构造方案有 [1,4], [2,2], [4,1]。
但是根据要求2，[1,4] 不符合要求; 根据要求3，[2,2] 比 [4,1] 更能符合要求. 所以输出长度 L 为 2， 宽度 W 为 2。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> area = 37
<strong>输出:</strong> [37,1]
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> area = 122122
<strong>输出:</strong> [427,286]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= area &lt;= 10<sup>7</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def constructRectangle(self, area: int) -> List[int]:
        w = int(sqrt(area))
        while area % w != 0:
            w -= 1
        return [area // w, w]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] constructRectangle(int area) {
        int w = (int) Math.sqrt(area);
        while (area % w != 0) {
            --w;
        }
        return new int[]{area / w, w};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> constructRectangle(int area) {
        int w = sqrt(1.0 * area);
        while (area % w != 0) --w;
        return {area / w, w};
    }
};
```

### **Go**

```go
func constructRectangle(area int) []int {
	w := int(math.Sqrt(float64(area)))
	for area%w != 0 {
		w--
	}
	return []int{area / w, w}
}
```

### **...**

```

```

<!-- tabs:end -->
