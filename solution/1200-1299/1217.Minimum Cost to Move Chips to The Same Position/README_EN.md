# [1217. Minimum Cost to Move Chips to The Same Position](https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position)

[中文文档](/solution/1200-1299/1217.Minimum%20Cost%20to%20Move%20Chips%20to%20The%20Same%20Position/README.md)

## Description

<p>We have <code>n</code> chips, where the position of the <code>i<sup>th</sup></code> chip is <code>position[i]</code>.</p>

<p>We need to move all the chips to <strong>the same position</strong>. In one step, we can change the position of the <code>i<sup>th</sup></code> chip from <code>position[i]</code> to:</p>

<ul>
	<li><code>position[i] + 2</code> or <code>position[i] - 2</code> with <code>cost = 0</code>.</li>
	<li><code>position[i] + 1</code> or <code>position[i] - 1</code> with <code>cost = 1</code>.</li>
</ul>

<p>Return <em>the minimum cost</em> needed to move all the chips to the same position.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1217.Minimum%20Cost%20to%20Move%20Chips%20to%20The%20Same%20Position/images/chips_e1.jpg" style="width: 750px; height: 217px;" />
<pre>
<strong>Input:</strong> position = [1,2,3]
<strong>Output:</strong> 1
<strong>Explanation:</strong> First step: Move the chip at position 3 to position 1 with cost = 0.
Second step: Move the chip at position 2 to position 1 with cost = 1.
Total cost is 1.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1217.Minimum%20Cost%20to%20Move%20Chips%20to%20The%20Same%20Position/images/chip_e2.jpg" style="width: 750px; height: 306px;" />
<pre>
<strong>Input:</strong> position = [2,2,2,3,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can move the two chips at position  3 to position 2. Each move has cost = 1. The total cost = 2.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> position = [1,1000000000]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= position.length &lt;= 100</code></li>
	<li><code>1 &lt;= position[i] &lt;= 10^9</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minCostToMoveChips(self, position: List[int]) -> int:
        a = sum(p % 2 for p in position)
        b = len(position) - a
        return min(a, b)
```

### **Java**

```java
class Solution {
    public int minCostToMoveChips(int[] position) {
        int a = 0;
        for (int p : position) {
            a += p % 2;
        }
        int b = position.length - a;
        return Math.min(a, b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minCostToMoveChips(vector<int>& position) {
        int a = 0;
        for (auto& p : position) a += p & 1;
        int b = position.size() - a;
        return min(a, b);
    }
};
```

### **Go**

```go
func minCostToMoveChips(position []int) int {
	a := 0
	for _, p := range position {
		a += p & 1
	}
	b := len(position) - a
	if a < b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {number[]} position
 * @return {number}
 */
var minCostToMoveChips = function (position) {
    let a = 0;
    for (let v of position) {
        a += v % 2;
    }
    let b = position.length - a;
    return Math.min(a, b);
};
```

### **...**

```

```

<!-- tabs:end -->
