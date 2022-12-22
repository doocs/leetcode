# [335. Self Crossing](https://leetcode.com/problems/self-crossing)

[中文文档](/solution/0300-0399/0335.Self%20Crossing/README.md)

## Description

<p>You are given an array of integers <code>distance</code>.</p>

<p>You start at the point <code>(0, 0)</code> on an <strong>X-Y plane,</strong> and you move <code>distance[0]</code> meters to the north, then <code>distance[1]</code> meters to the west, <code>distance[2]</code> meters to the south, <code>distance[3]</code> meters to the east, and so on. In other words, after each move, your direction changes counter-clockwise.</p>

<p>Return <code>true</code> <em>if your path crosses itself or </em><code>false</code><em> if it does not</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0335.Self%20Crossing/images/11.jpg" style="width: 400px; height: 413px;" />
<pre>
<strong>Input:</strong> distance = [2,1,1,2]
<strong>Output:</strong> true
<strong>Explanation:</strong> The path crosses itself at the point (0, 1).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0335.Self%20Crossing/images/22.jpg" style="width: 400px; height: 413px;" />
<pre>
<strong>Input:</strong> distance = [1,2,3,4]
<strong>Output:</strong> false
<strong>Explanation:</strong> The path does not cross itself at any point.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0335.Self%20Crossing/images/33.jpg" style="width: 400px; height: 413px;" />
<pre>
<strong>Input:</strong> distance = [1,1,1,2,1]
<strong>Output:</strong> true
<strong>Explanation:</strong> The path crosses itself at the point (0, 0).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;=&nbsp;distance.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;=&nbsp;distance[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

```bash
                i-2
    case 1 : i-1┌─┐
                └─┼─>i
                 i-3

                   i-2
    case 2 : i-1 ┌────┐
                 └─══>┘i-3
                 i  i-4

    case 3 :    i-4
               ┌──┐
               │i<┼─┐
            i-3│ i-5│i-1
               └────┘
                i-2
```

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isSelfCrossing(self, distance: List[int]) -> bool:
        d = distance
        for i in range(3, len(d)):
            if d[i] >= d[i - 2] and d[i - 1] <= d[i - 3]:
                return True
            if i >= 4 and d[i - 1] == d[i - 3] and d[i] + d[i - 4] >= d[i - 2]:
                return True
            if (
                i >= 5
                and d[i - 2] >= d[i - 4]
                and d[i - 1] <= d[i - 3]
                and d[i] >= d[i - 2] - d[i - 4]
                and d[i - 1] + d[i - 5] >= d[i - 3]
            ):
                return True
        return False
```

### **Java**

```java
class Solution {
    public boolean isSelfCrossing(int[] distance) {
        int[] d = distance;
        for (int i = 3; i < d.length; ++i) {
            if (d[i] >= d[i - 2] && d[i - 1] <= d[i - 3]) {
                return true;
            }
            if (i >= 4 && d[i - 1] == d[i - 3] && d[i] + d[i - 4] >= d[i - 2]) {
                return true;
            }
            if (i >= 5 && d[i - 2] >= d[i - 4] && d[i - 1] <= d[i - 3]
                && d[i] >= d[i - 2] - d[i - 4] && d[i - 1] + d[i - 5] >= d[i - 3]) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isSelfCrossing(vector<int>& distance) {
        vector<int> d = distance;
        for (int i = 3; i < d.size(); ++i) {
            if (d[i] >= d[i - 2] && d[i - 1] <= d[i - 3]) return true;
            if (i >= 4 && d[i - 1] == d[i - 3] && d[i] + d[i - 4] >= d[i - 2]) return true;
            if (i >= 5 && d[i - 2] >= d[i - 4] && d[i - 1] <= d[i - 3] && d[i] >= d[i - 2] - d[i - 4] && d[i - 1] + d[i - 5] >= d[i - 3]) return true;
        }
        return false;
    }
};
```

### **Go**

```go
func isSelfCrossing(distance []int) bool {
	d := distance
	for i := 3; i < len(d); i++ {
		if d[i] >= d[i-2] && d[i-1] <= d[i-3] {
			return true
		}
		if i >= 4 && d[i-1] == d[i-3] && d[i]+d[i-4] >= d[i-2] {
			return true
		}
		if i >= 5 && d[i-2] >= d[i-4] && d[i-1] <= d[i-3] && d[i] >= d[i-2]-d[i-4] && d[i-1]+d[i-5] >= d[i-3] {
			return true
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
