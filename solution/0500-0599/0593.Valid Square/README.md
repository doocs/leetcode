# [593. 有效的正方形](https://leetcode.cn/problems/valid-square)

[English Version](/solution/0500-0599/0593.Valid%20Square/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定2D空间中四个点的坐标&nbsp;<code>p1</code>,&nbsp;<code>p2</code>,&nbsp;<code>p3</code>&nbsp;和&nbsp;<code>p4</code>，如果这四个点构成一个正方形，则返回 <code>true</code> 。</p>

<p>点的坐标&nbsp;<code>p<sub>i</sub></code> 表示为 <code>[xi, yi]</code> 。 <code>输入没有任何顺序</code> 。</p>

<p>一个 <strong>有效的正方形</strong> 有四条等边和四个等角(90度角)。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
<strong>输出:</strong> True
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong>p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
<b>输出：</b>false
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<b>输入：</b>p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
<b>输出：</b>true
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>p1.length == p2.length == p3.length == p4.length == 2</code></li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= x<sub>i</sub>, y<sub>i</sub>&nbsp;&lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

若任选三个点，都能构成等腰直角三角形，说明是有效的正方形。

时间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def validSquare(
        self, p1: List[int], p2: List[int], p3: List[int], p4: List[int]
    ) -> bool:
        def check(a, b, c):
            (x1, y1), (x2, y2), (x3, y3) = a, b, c
            d1 = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)
            d2 = (x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3)
            d3 = (x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3)
            return any(
                [
                    d1 == d2 and d1 + d2 == d3 and d1,
                    d2 == d3 and d2 + d3 == d1 and d2,
                    d1 == d3 and d1 + d3 == d2 and d1,
                ]
            )

        return (
            check(p1, p2, p3)
            and check(p2, p3, p4)
            and check(p1, p3, p4)
            and check(p1, p2, p4)
        )
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        return check(p1, p2, p3) && check(p1, p3, p4) && check(p1, p2, p4) && check(p2, p3, p4);
    }

    private boolean check(int[] a, int[] b, int[] c) {
        int x1 = a[0], y1 = a[1];
        int x2 = b[0], y2 = b[1];
        int x3 = c[0], y3 = c[1];
        int d1 = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        int d2 = (x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3);
        int d3 = (x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3);
        if (d1 == d2 && d1 + d2 == d3 && d1 > 0) {
            return true;
        }
        if (d1 == d3 && d1 + d3 == d2 && d1 > 0) {
            return true;
        }
        if (d2 == d3 && d2 + d3 == d1 && d2 > 0) {
            return true;
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool validSquare(vector<int>& p1, vector<int>& p2, vector<int>& p3, vector<int>& p4) {
        return check(p1, p2, p3) && check(p1, p3, p4) && check(p1, p2, p4) && check(p2, p3, p4);
    }

    bool check(vector<int>& a, vector<int>& b, vector<int>& c) {
        int x1 = a[0], y1 = a[1];
        int x2 = b[0], y2 = b[1];
        int x3 = c[0], y3 = c[1];
        int d1 = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        int d2 = (x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3);
        int d3 = (x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3);
        if (d1 == d2 && d1 + d2 == d3 && d1 > 0) return true;
        if (d1 == d3 && d1 + d3 == d2 && d1 > 0) return true;
        if (d2 == d3 && d2 + d3 == d1 && d2 > 0) return true;
        return false;
    }
};
```

### **Go**

```go
func validSquare(p1 []int, p2 []int, p3 []int, p4 []int) bool {
	check := func(a, b, c []int) bool {
		x1, y1 := a[0], a[1]
		x2, y2 := b[0], b[1]
		x3, y3 := c[0], c[1]
		d1 := (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)
		d2 := (x1-x3)*(x1-x3) + (y1-y3)*(y1-y3)
		d3 := (x2-x3)*(x2-x3) + (y2-y3)*(y2-y3)
		if d1 == d2 && d1+d2 == d3 && d1 > 0 {
			return true
		}
		if d1 == d3 && d1+d3 == d2 && d1 > 0 {
			return true
		}
		if d2 == d3 && d2+d3 == d1 && d2 > 0 {
			return true
		}
		return false
	}
	return check(p1, p2, p3) && check(p1, p3, p4) && check(p1, p2, p4) && check(p2, p3, p4)
}
```

### **...**

```

```

<!-- tabs:end -->
