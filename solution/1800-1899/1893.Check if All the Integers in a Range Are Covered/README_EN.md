# [1893. Check if All the Integers in a Range Are Covered](https://leetcode.com/problems/check-if-all-the-integers-in-a-range-are-covered)

[中文文档](/solution/1800-1899/1893.Check%20if%20All%20the%20Integers%20in%20a%20Range%20Are%20Covered/README.md)

## Description

<p>You are given a 2D integer array <code>ranges</code> and two integers <code>left</code> and <code>right</code>. Each <code>ranges[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> represents an <strong>inclusive</strong> interval between <code>start<sub>i</sub></code> and <code>end<sub>i</sub></code>.</p>

<p>Return <code>true</code> <em>if each integer in the inclusive range</em> <code>[left, right]</code> <em>is covered by <strong>at least one</strong> interval in</em> <code>ranges</code>. Return <code>false</code> <em>otherwise</em>.</p>

<p>An integer <code>x</code> is covered by an interval <code>ranges[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> if <code>start<sub>i</sub> &lt;= x &lt;= end<sub>i</sub></code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
<strong>Output:</strong> true
<strong>Explanation:</strong> Every integer between 2 and 5 is covered:
- 2 is covered by the first range.
- 3 and 4 are covered by the second range.
- 5 is covered by the third range.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> ranges = [[1,10],[10,20]], left = 21, right = 21
<strong>Output:</strong> false
<strong>Explanation:</strong> 21 is not covered by any range.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= ranges.length &lt;= 50</code></li>
	<li><code>1 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 50</code></li>
	<li><code>1 &lt;= left &lt;= right &lt;= 50</code></li>
</ul>

## Solutions

Interval update using difference array.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isCovered(self, ranges: List[List[int]], left: int, right: int) -> bool:
        diff = [0] * 52
        for l, r in ranges:
            diff[l] += 1
            diff[r + 1] -= 1
        cur = 0
        for i, df in enumerate(diff):
            cur += df
            if left <= i <= right and cur == 0:
                return False
        return True
```

### **Java**

```java
class Solution {
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] diff = new int[52];
        for (int[] range : ranges) {
            diff[range[0]]++;
            diff[range[1] + 1]--;
        }
        int cur = 0;
        for (int i = 0; i < 52; i++) {
            cur += diff[i];
            if (left <= i && i <= right && cur == 0) {
                return false;
            }
        }
        return true;
    }
}
```

### **TypeScript**

```ts
function isCovered(ranges: number[][], left: number, right: number): boolean {
    let diff = new Array(52).fill(0);
    for (let [start, end] of ranges) {
        ++diff[start];
        --diff[end + 1];
    }
    let cur = 0;
    for (let i = 1; i <= 50; i++) {
        cur += diff[i];
        if (i >= left && i <= right && cur <= 0) {
            return false;
        }
    }
    return true;
}
```

### **Go**

```go
func isCovered(ranges [][]int, left int, right int) bool {
	diff := make([]int, 52)
	for _, rg := range ranges {
		diff[rg[0]]++
		diff[rg[1]+1]--
	}
	cur := 0
	for i, df := range diff {
		cur += df
		if i >= left && i <= right && cur == 0 {
			return false
		}
	}
	return true
}
```

### **C++**

```cpp
class Solution {
public:
    bool isCovered(vector<vector<int>>& ranges, int left, int right) {
        vector<int> d(52);
        for (auto& e : ranges) {
            ++d[e[0]];
            --d[e[1] + 1];
        }
        int s = 0;
        for (int i = 0; i < d.size(); ++i) {
            s += d[i];
            if (left <= i && i <= right && s == 0) return false;
        }
        return true;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
