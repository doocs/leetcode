# [1578. 使绳子变成彩色的最短时间](https://leetcode.cn/problems/minimum-time-to-make-rope-colorful)

[English Version](/solution/1500-1599/1578.Minimum%20Time%20to%20Make%20Rope%20Colorful/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 把 <code>n</code> 个气球排列在一根绳子上。给你一个下标从 <strong>0</strong> 开始的字符串 <code>colors</code> ，其中 <code>colors[i]</code> 是第 <code>i</code> 个气球的颜色。</p>

<p>Alice 想要把绳子装扮成 <strong>彩色</strong> ，且她不希望两个连续的气球涂着相同的颜色，所以她喊来 Bob 帮忙。Bob 可以从绳子上移除一些气球使绳子变成 <strong>彩色</strong> 。给你一个下标从 <strong>0</strong> 开始的整数数组 <code>neededTime</code> ，其中 <code>neededTime[i]</code> 是 Bob 从绳子上移除第 <code>i</code> 个气球需要的时间（以秒为单位）。</p>

<p>返回 Bob 使绳子变成 <strong>彩色</strong> 需要的 <strong>最少时间</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1578.Minimum%20Time%20to%20Make%20Rope%20Colorful/images/ballon1.jpg" style="width: 404px; height: 243px;" />
<pre>
<strong>输入：</strong>colors = "abaac", neededTime = [1,2,3,4,5]
<strong>输出：</strong>3
<strong>解释：</strong>在上图中，'a' 是蓝色，'b' 是红色且 'c' 是绿色。
Bob 可以移除下标 2 的蓝色气球。这将花费 3 秒。
移除后，不存在两个连续的气球涂着相同的颜色。总时间 = 3 。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1578.Minimum%20Time%20to%20Make%20Rope%20Colorful/images/balloon2.jpg" style="width: 244px; height: 243px;" />
<pre>
<strong>输入：</strong>colors = "abc", neededTime = [1,2,3]
<strong>输出：</strong>0
<strong>解释：</strong>绳子已经是彩色的，Bob 不需要从绳子上移除任何气球。
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1578.Minimum%20Time%20to%20Make%20Rope%20Colorful/images/balloon3.jpg" style="width: 404px; height: 243px;" />
<pre>
<strong>输入：</strong>colors = "aabaa", neededTime = [1,2,3,4,1]
<strong>输出：</strong>2
<strong>解释：</strong>Bob 会移除下标 0 和下标 4 处的气球。这两个气球各需要 1 秒来移除。
移除后，不存在两个连续的气球涂着相同的颜色。总时间 = 1 + 1 = 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == colors.length == neededTime.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= neededTime[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>colors</code> 仅由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针 + 贪心**

我们可以用双指针指向当前连续相同颜色的气球的首尾，然后计算出当前连续相同颜色的气球的总时间 $s$，以及最大的时间 $mx$。如果当前连续相同颜色的气球个数大于 $1$，那么我们可以贪心地选择保留时间最大的气球，然后移除其它相同颜色的气球，耗时 $s - mx$，累加到答案中。接下来继续遍历，直到遍历完所有气球。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为气球的个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minCost(self, colors: str, neededTime: List[int]) -> int:
        ans = i = 0
        n = len(colors)
        while i < n:
            j = i
            s = mx = 0
            while j < n and colors[j] == colors[i]:
                s += neededTime[j]
                if mx < neededTime[j]:
                    mx = neededTime[j]
                j += 1
            if j - i > 1:
                ans += s - mx
            i = j
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minCost(String colors, int[] neededTime) {
        int ans = 0;
        int n = neededTime.length;
        for (int i = 0, j = 0; i < n; i = j) {
            j = i;
            int s = 0, mx = 0;
            while (j < n && colors.charAt(j) == colors.charAt(i)) {
                s += neededTime[j];
                mx = Math.max(mx, neededTime[j]);
                ++j;
            }
            if (j - i > 1) {
                ans += s - mx;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minCost(string colors, vector<int>& neededTime) {
        int ans = 0;
        int n = colors.size();
        for (int i = 0, j = 0; i < n; i = j) {
            j = i;
            int s = 0, mx = 0;
            while (j < n && colors[j] == colors[i]) {
                s += neededTime[j];
                mx = max(mx, neededTime[j]);
                ++j;
            }
            if (j - i > 1) {
                ans += s - mx;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minCost(colors string, neededTime []int) (ans int) {
	n := len(colors)
	for i, j := 0, 0; i < n; i = j {
		j = i
		s, mx := 0, 0
		for j < n && colors[j] == colors[i] {
			s += neededTime[j]
			if mx < neededTime[j] {
				mx = neededTime[j]
			}
			j++
		}
		if j-i > 1 {
			ans += s - mx
		}
	}
	return
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
