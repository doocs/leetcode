---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1124.Longest%20Well-Performing%20Interval/README_EN.md
rating: 1908
source: Weekly Contest 145 Q3
tags:
    - Stack
    - Array
    - Hash Table
    - Prefix Sum
    - Monotonic Stack
---

<!-- problem:start -->

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

<!-- solution:start -->

### Solution 1: Prefix Sum + Hash Table

We can use the idea of prefix sum, maintaining a variable $s$, which represents the difference between the number of "tiring days" and "non-tiring days" from index $0$ to the current index. If $s$ is greater than $0$, it means that the segment from index $0$ to the current index is a "well-performing time period". In addition, we use a hash table $pos$ to record the first occurrence index of each $s$.

Next, we traverse the `hours` array, for each index $i$:

-   If $hours[i] > 8$, we increment $s$ by $1$, otherwise we decrement $s$ by $1$.
-   If $s > 0$, it means that the segment from index $0$ to the current index $i$ is a "well-performing time period", we update the result $ans = i + 1$. Otherwise, if $s - 1$ is in the hash table $pos$, let $j = pos[s - 1]$, it means that the segment from index $j + 1$ to the current index $i$ is a "well-performing time period", we update the result $ans = \max(ans, i - j)$.
-   Then, if $s$ is not in the hash table $pos$, we record $pos[s] = i$.

After the traversal, return the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the `hours` array.

<!-- tabs:start -->

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
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
