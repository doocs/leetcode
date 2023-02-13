# [1124. Longest Well-Performing Interval](https://leetcode.com/problems/longest-well-performing-interval)

[中文文档](/solution/1100-1199/1124.Longest%20Well-Performing%20Interval/README.md)

## Description

<p>We are given <code>hours</code>, a list of the number of hours worked per day for a given employee.</p>

<p>A day is considered to be a <em>tiring day</em> if and only if the number of hours worked is (strictly) greater than <code>8</code>.</p>

<p>A <em>well-performing interval</em> is an interval of days for which the number of tiring days is strictly larger than the number of non-tiring days.</p>

<p>Return the length of the longest well-performing interval.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> hours = [9,9,6,0,6,6,9]
<strong>Output:</strong> 3
<strong>Explanation: </strong>The longest well-performing interval is [9,9,6].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> hours = [6,6,6]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= hours.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= hours[i] &lt;= 16</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
