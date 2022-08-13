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

前缀和 + 哈希表。前缀和 s 初始值为 0。遍历 hours 中每一项数据 h：

-   若 h 大于 8，则 s 加 1，否则减 1。
-   若当前 s 大于 0，说明从下标 0 到当前下标的这一段，满足「表现良好的时间段」，`ans = i + 1`。
-   若出现了一个新的 s，我们记录到哈希表 seen 中，`seen[s]` 表示 s 第一次出现的位置。

我们想要 s 大于 0，因此要找到 `s - 1` 第一次出现的位置。虽然 `s - x` 同样满足条件，但是它会出现得比 `s - 1` 要晚。因此最大长度是 `i - seen[s - 1]`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def longestWPI(self, hours: List[int]) -> int:
        ans = s = 0
        seen = {}
        for i, h in enumerate(hours):
            s += 1 if h > 8 else -1
            if s > 0:
                ans = i + 1
            else:
                if s not in seen:
                    seen[s] = i
                if s - 1 in seen:
                    ans = max(ans, i - seen[s - 1])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestWPI(int[] hours) {
        int s = 0, ans = 0;
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < hours.length; ++i) {
            s += hours[i] > 8 ? 1 : -1;
            if (s > 0) {
                ans = i + 1;
            } else {
                seen.putIfAbsent(s, i);
                if (seen.containsKey(s - 1)) {
                    ans = Math.max(ans, i - seen.get(s - 1));
                }
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
    int longestWPI(vector<int>& hours) {
        int s = 0, ans = 0;
        unordered_map<int, int> seen;
        for (int i = 0; i < hours.size(); ++i) {
            s += hours[i] > 8 ? 1 : -1;
            if (s > 0)
                ans = i + 1;
            else {
                if (!seen.count(s)) seen[s] = i;
                if (seen.count(s - 1)) ans = max(ans, i - seen[s - 1]);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestWPI(hours []int) int {
	s, ans := 0, 0
	seen := make(map[int]int)
	for i, h := range hours {
		if h > 8 {
			s += 1
		} else {
			s -= 1
		}
		if s > 0 {
			ans = i + 1
		} else {
			if _, ok := seen[s]; !ok {
				seen[s] = i
			}
			if j, ok := seen[s-1]; ok {
				ans = max(ans, i-j)
			}
		}
	}
	return ans
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
