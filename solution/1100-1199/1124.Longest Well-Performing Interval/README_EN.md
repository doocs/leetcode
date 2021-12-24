# [1124. Longest Well-Performing Interval](https://leetcode.com/problems/longest-well-performing-interval)

[中文文档](/solution/1100-1199/1124.Longest%20Well-Performing%20Interval/README.md)

## Description

<p>We are given <code>hours</code>, a list of the number of hours&nbsp;worked per day for a given employee.</p>

<p>A day is considered to be a <em>tiring day</em> if and only if the number of hours worked is (strictly) greater than <code>8</code>.</p>

<p>A <em>well-performing interval</em> is an interval of days for which the number of tiring days is strictly larger than the number of non-tiring days.</p>

<p>Return the length of the longest well-performing interval.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> hours = [9,9,6,0,6,6,9]
<strong>Output:</strong> 3
<strong>Explanation: </strong>The longest well-performing interval is [9,9,6].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= hours.length &lt;= 10000</code></li>
	<li><code>0 &lt;= hours[i] &lt;= 16</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestWPI(self, hours: List[int]) -> int:
        pre_sum, res = 0, 0
        mp = {}
        for i in range(len(hours)):
            temp = 1 if hours[i] > 8 else -1
            pre_sum += temp
            if pre_sum > 0:
                res = i + 1
            else:
                if pre_sum not in mp:
                    mp[pre_sum] = i
                if (pre_sum - 1) in mp:
                    res = max(res, i - mp[pre_sum - 1])
        return res
```

### **Java**

```java
class Solution {
    public int longestWPI(int[] hours) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int s = 0;
        for (int i = 0; i < hours.length; ++i) {
            s += hours[i] > 8 ? 1 : -1;
            if (s > 0) {
                res = i + 1;
            } else {
                int b = map.getOrDefault(s - 1, -1);
                if (b != -1) {
                    res = Math.max(res, i - b);
                }
            }
            map.putIfAbsent(s, i);
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
