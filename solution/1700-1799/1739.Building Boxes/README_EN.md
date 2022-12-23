# [1739. Building Boxes](https://leetcode.com/problems/building-boxes)

[中文文档](/solution/1700-1799/1739.Building%20Boxes/README.md)

## Description

<p>You have a cubic storeroom where the width, length, and height of the room are all equal to <code>n</code> units. You are asked to place <code>n</code> boxes in this room where each box is a cube of unit side length. There are however some rules to placing the boxes:</p>

<ul>
	<li>You can place the boxes anywhere on the floor.</li>
	<li>If box <code>x</code> is placed on top of the box <code>y</code>, then each side of the four vertical sides of the box <code>y</code> <strong>must</strong> either be adjacent to another box or to a wall.</li>
</ul>

<p>Given an integer <code>n</code>, return<em> the <strong>minimum</strong> possible number of boxes touching the floor.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1739.Building%20Boxes/images/3-boxes.png" style="width: 135px; height: 143px;" /></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> The figure above is for the placement of the three boxes.
These boxes are placed in the corner of the room, where the corner is on the left side.
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1739.Building%20Boxes/images/4-boxes.png" style="width: 135px; height: 179px;" /></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> 3
<strong>Explanation:</strong> The figure above is for the placement of the four boxes.
These boxes are placed in the corner of the room, where the corner is on the left side.
</pre>

<p><strong class="example">Example 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1739.Building%20Boxes/images/10-boxes.png" style="width: 271px; height: 257px;" /></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 6
<strong>Explanation:</strong> The figure above is for the placement of the ten boxes.
These boxes are placed in the corner of the room, where the corner is on the back side.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumBoxes(self, n: int) -> int:
        s, k = 0, 1
        while s + k * (k + 1) // 2 <= n:
            s += k * (k + 1) // 2
            k += 1
        k -= 1
        ans = k * (k + 1) // 2
        k = 1
        while s < n:
            ans += 1
            s += k
            k += 1
        return ans
```

### **Java**

```java
class Solution {
    public int minimumBoxes(int n) {
        int s = 0, k = 1;
        while (s + k * (k + 1) / 2 <= n) {
            s += k * (k + 1) / 2;
            ++k;
        }
        --k;
        int ans = k * (k + 1) / 2;
        k = 1;
        while (s < n) {
            ++ans;
            s += k;
            ++k;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumBoxes(int n) {
        int s = 0, k = 1;
        while (s + k * (k + 1) / 2 <= n) {
            s += k * (k + 1) / 2;
            ++k;
        }
        --k;
        int ans = k * (k + 1) / 2;
        k = 1;
        while (s < n) {
            ++ans;
            s += k;
            ++k;
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumBoxes(n int) int {
	s, k := 0, 1
	for s+k*(k+1)/2 <= n {
		s += k * (k + 1) / 2
		k++
	}
	k--
	ans := k * (k + 1) / 2
	k = 1
	for s < n {
		ans++
		s += k
		k++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
