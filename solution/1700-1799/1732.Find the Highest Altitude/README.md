# [1732. 找到最高海拔](https://leetcode.cn/problems/find-the-highest-altitude)

[English Version](/solution/1700-1799/1732.Find%20the%20Highest%20Altitude/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个自行车手打算进行一场公路骑行，这条路线总共由 <code>n + 1</code> 个不同海拔的点组成。自行车手从海拔为 <code>0</code> 的点 <code>0</code> 开始骑行。</p>

<p>给你一个长度为 <code>n</code> 的整数数组 <code>gain</code> ，其中 <code>gain[i]</code> 是点 <code>i</code> 和点 <code>i + 1</code> 的 <strong>净海拔高度差</strong>（<code>0 <= i < n</code>）。请你返回 <strong>最高点的海拔</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>gain = [-5,1,5,0,-7]
<b>输出：</b>1
<b>解释：</b>海拔高度依次为 [0,-5,-4,1,1,-6] 。最高海拔为 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>gain = [-4,-3,-2,-1,4,3,2]
<b>输出：</b>0
<b>解释：</b>海拔高度依次为 [0,-4,-7,-9,-10,-6,-3,-1] 。最高海拔为 0 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == gain.length</code></li>
	<li><code>1 <= n <= 100</code></li>
	<li><code>-100 <= gain[i] <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

求前 N 项和的最大值即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def largestAltitude(self, gain: List[int]) -> int:
        res = t = 0
        for h in gain:
            t += h
            res = max(res, t)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int largestAltitude(int[] gain) {
        int res = 0;
        int t = 0;
        for (int h : gain) {
            t += h;
            res = Math.max(res, t);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int largestAltitude(vector<int>& gain) {
        int res = 0, t = 0;
        for (int h : gain) {
            t += h;
            res = max(res, t);
        }
        return res;
    }
};
```

### **Go**

```go
func largestAltitude(gain []int) int {
	res, t := 0, 0
	for _, h := range gain {
		t += h
		res = max(res, t)
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
