# [1124. 表现良好的最长时间段](https://leetcode.cn/problems/longest-well-performing-interval)

[English Version](/solution/1100-1199/1124.Longest%20Well-Performing%20Interval/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一份工作时间表&nbsp;<code>hours</code>，上面记录着某一位员工每天的工作小时数。</p>

<p>我们认为当员工一天中的工作小时数大于&nbsp;<code>8</code> 小时的时候，那么这一天就是「<strong>劳累的一天</strong>」。</p>

<p>所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格<strong> 大于</strong>「不劳累的天数」。</p>

<p>请你返回「表现良好时间段」的最大长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>hours = [9,9,6,0,6,6,9]
<strong>输出：</strong>3
<strong>解释：</strong>最长的表现良好时间段是 [9,9,6]。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>hours = [6,6,6]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= hours.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= hours[i] &lt;= 16</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 哈希表**

我们可以利用前缀和的思想，维护一个变量 $s$，表示从下标 $0$ 到当前下标的这一段，「劳累的天数」与「不劳累的天数」的差值。如果 $s$ 大于 $0$，说明从下标 $0$ 到当前下标的这一段，满足「表现良好的时间段」。另外，用哈希表 $pos$ 记录每个 $s$ 第一次出现的下标。

接下来，我们遍历数组 `hours`，对于每个下标 $i$：

-   如果 $hours[i] \gt 8$，我们就让 $s$ 加 $1$，否则减 $1$。
-   如果 $s$ 大于 $0$，说明从下标 $0$ 到当前下标的这一段，满足「表现良好的时间段」，我们更新结果 $ans = i + 1$。否则，如果 $s - 1$ 在哈希表 $pos$ 中，记 $j = pos[s - 1]$，说明从下标 $j + 1$ 到当前下标 $i$ 的这一段，满足「表现良好的时间段」，我们更新结果 $ans = max(ans, i - j)$。
-   然后，如果 $s$ 不在哈希表 $pos$ 中，我们就记录 $pos[s] = i$。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `hours` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestWPI(self, hours: List[int]) -> int:
        ans = s = 0
        pos = {}
        for i, x in enumerate(hours):
            s += 1 if x > 8 else -1
            if s > 0:
                ans = i + 1
            elif s - 1 in pos:
                ans = max(ans, i - pos[s - 1])
            if s not in pos:
                pos[s] = i
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestWPI(int[] hours) {
        int ans = 0, s = 0;
        Map<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < hours.length; ++i) {
            s += hours[i] > 8 ? 1 : -1;
            if (s > 0) {
                ans = i + 1;
            } else if (pos.containsKey(s - 1)) {
                ans = Math.max(ans, i - pos.get(s - 1));
            }
            pos.putIfAbsent(s, i);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestWPI(vector<int>& hours) {
        int ans = 0, s = 0;
        unordered_map<int, int> pos;
        for (int i = 0; i < hours.size(); ++i) {
            s += hours[i] > 8 ? 1 : -1;
            if (s > 0) {
                ans = i + 1;
            } else if (pos.count(s - 1)) {
                ans = max(ans, i - pos[s - 1]);
            }
            if (!pos.count(s)) {
                pos[s] = i;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestWPI(hours []int) (ans int) {
	s := 0
	pos := map[int]int{}
	for i, x := range hours {
		if x > 8 {
			s++
		} else {
			s--
		}
		if s > 0 {
			ans = i + 1
		} else if j, ok := pos[s-1]; ok {
			ans = max(ans, i-j)
		}
		if _, ok := pos[s]; !ok {
			pos[s] = i
		}
	}
	return
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
