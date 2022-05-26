# [1014. 最佳观光组合](https://leetcode.cn/problems/best-sightseeing-pair)

[English Version](/solution/1000-1099/1014.Best%20Sightseeing%20Pair/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数数组 <code>values</code>，其中 <code>values[i]</code> 表示第 <code>i</code> 个观光景点的评分，并且两个景点 <code>i</code> 和 <code>j</code> 之间的 <strong>距离</strong> 为 <code>j - i</code>。</p>

<p>一对景点（<code>i < j</code>）组成的观光组合的得分为 <code>values[i] + values[j] + i - j</code> ，也就是景点的评分之和<strong> 减去 </strong>它们两者之间的距离。</p>

<p>返回一对观光景点能取得的最高分。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>values = [8,1,5,2,6]
<strong>输出：</strong>11
<strong>解释：</strong>i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>values = [1,2]
<strong>输出：</strong>2
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= values.length <= 5 * 10<sup>4</sup></code></li>
	<li><code>1 <= values[i] <= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxScoreSightseeingPair(self, values: List[int]) -> int:
        res, mx = 0, values[0]
        for i in range(1, len(values)):
            res = max(res, values[i] - i + mx)
            mx = max(mx, values[i] + i)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int res = 0, mx = values[0];
        for (int i = 1; i < values.length; ++i) {
            res = Math.max(res, values[i] - i + mx);
            mx = Math.max(mx, values[i] + i);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxScoreSightseeingPair(vector<int>& values) {
        int res = 0, mx = values[0];
        for (int i = 1; i < values.size(); ++i) {
            res = max(res, values[i] - i + mx);
            mx = max(mx, values[i] + i);
        }
        return res;
    }
};
```

### **Go**

```go
func maxScoreSightseeingPair(values []int) int {
	res, mx := 0, values[0]
	for i := 1; i < len(values); i++ {
		res = max(res, values[i]-i+mx)
		mx = max(mx, values[i]+i)
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
