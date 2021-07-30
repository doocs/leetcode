# [495. 提莫攻击](https://leetcode-cn.com/problems/teemo-attacking)

[English Version](/solution/0400-0499/0495.Teemo%20Attacking/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在《英雄联盟》的世界中，有一个叫 &ldquo;提莫&rdquo; 的英雄，他的攻击可以让敌方英雄艾希（编者注：寒冰射手）进入中毒状态。现在，给出提莫对艾希的攻击时间序列和提莫攻击的中毒持续时间，你需要输出艾希的中毒状态总时长。</p>

<p>你可以认为提莫在给定的时间点进行攻击，并立即使艾希处于中毒状态。</p>

<p>&nbsp;</p>

<p><strong>示例1:</strong></p>

<pre><strong>输入:</strong> [1,4], 2
<strong>输出:</strong> 4
<strong>原因:</strong> 第 1 秒初，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持 2 秒钟，直到第 2 秒末结束。
第 4 秒初，提莫再次攻击艾希，使得艾希获得另外 2 秒中毒时间。
所以最终输出 4 秒。
</pre>

<p><strong>示例2:</strong></p>

<pre><strong>输入:</strong> [1,2], 2
<strong>输出:</strong> 3
<strong>原因:</strong> 第 1 秒初，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持 2 秒钟，直到第 2 秒末结束。
但是第 2 秒初，提莫再次攻击了已经处于中毒状态的艾希。
由于中毒状态不可叠加，提莫在第 2 秒初的这次攻击会在第 3 秒末结束。
所以最终输出 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>你可以假定时间序列数组的总长度不超过 10000。</li>
	<li>你可以假定提莫攻击时间序列中的数字和提莫攻击的中毒持续时间都是非负整数，并且不超过 10,000,000。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findPoisonedDuration(self, timeSeries: List[int], duration: int) -> int:
        n, res = len(timeSeries), duration
        for i in range(n - 1):
            res += min(duration, timeSeries[i + 1] - timeSeries[i])
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int n = timeSeries.length, res = duration;
        for (int i = 0; i < n - 1; ++i) {
            res += Math.min(duration, timeSeries[i + 1] - timeSeries[i]);
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findPoisonedDuration(vector<int>& timeSeries, int duration) {
        int n = timeSeries.size(), res = duration;
        for (int i = 0; i < n - 1; ++i) {
            res += min(duration, timeSeries[i + 1] - timeSeries[i]);
        }
        return res;
    }
};
```

### **Go**

```go
func findPoisonedDuration(timeSeries []int, duration int) int {
	n, res := len(timeSeries), duration
	for i := 0; i < n-1; i++ {
		res += min(duration, timeSeries[i+1]-timeSeries[i])
	}
	return res
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
