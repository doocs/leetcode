# [2579. Count Total Number of Colored Cells](https://leetcode.com/problems/count-total-number-of-colored-cells)

[中文文档](/solution/2500-2599/2579.Count%20Total%20Number%20of%20Colored%20Cells/README.md)

## Description

<p>There exists an infinitely large two-dimensional grid of uncolored unit cells. You are given a positive integer <code>n</code>, indicating that you must do the following routine for <code>n</code> minutes:</p>

<ul>
	<li>At the first minute, color <strong>any</strong> arbitrary unit cell blue.</li>
	<li>Every minute thereafter, color blue <strong>every</strong> uncolored cell that touches a blue cell.</li>
</ul>

<p>Below is a pictorial representation of the state of the grid after minutes 1, 2, and 3.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2579.Count%20Total%20Number%20of%20Colored%20Cells/images/example-copy-2.png" style="width: 500px; height: 279px;" />
<p>Return <em>the number of <strong>colored cells</strong> at the end of </em><code>n</code> <em>minutes</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> After 1 minute, there is only 1 blue cell, so we return 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 5
<strong>Explanation:</strong> After 2 minutes, there are 4 colored cells on the boundary and 1 in the center, so we return 5. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

**Solution 1: Mathematics**

We find that after the $n$th minute, there are a total of $2 \times n - 1$ columns in the grid, and the numbers on each column are respectively $1, 3, 5, \cdots, 2 \times n - 1, 2 \times n - 3, \cdots, 3, 1$. The left and right parts are both arithmetic progressions, and the sum can be obtained by $2 \times n \times (n - 1) + 1$.

Time complexity $O(1)$, space complexity $O(1)$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def coloredCells(self, n: int) -> int:
        return 2 * n * (n - 1) + 1
```

### **Java**

```java
class Solution {
    public long coloredCells(int n) {
        return 2L * n * (n - 1) + 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long coloredCells(int n) {
        return 2LL * n * (n - 1) + 1;
    }
};
```

### **Go**

```go
func coloredCells(n int) int64 {
	return int64(2*n*(n-1) + 1)
}
```

### **TypeScript**

```ts
function coloredCells(n: number): number {
    return 2 * n * (n - 1) + 1;
}
```

### **...**

```

```

<!-- tabs:end -->
