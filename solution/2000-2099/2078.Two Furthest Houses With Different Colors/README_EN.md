# [2078. Two Furthest Houses With Different Colors](https://leetcode.com/problems/two-furthest-houses-with-different-colors)

[中文文档](/solution/2000-2099/2078.Two%20Furthest%20Houses%20With%20Different%20Colors/README.md)

## Description

<p>There are <code>n</code> houses evenly lined up on the street, and each house is beautifully painted. You are given a <strong>0-indexed</strong> integer array <code>colors</code> of length <code>n</code>, where <code>colors[i]</code> represents the color of the <code>i<sup>th</sup></code> house.</p>

<p>Return <em>the <strong>maximum</strong> distance between <strong>two</strong> houses with <strong>different</strong> colors</em>.</p>

<p>The distance between the <code>i<sup>th</sup></code> and <code>j<sup>th</sup></code> houses is <code>abs(i - j)</code>, where <code>abs(x)</code> is the <strong>absolute value</strong> of <code>x</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2078.Two%20Furthest%20Houses%20With%20Different%20Colors/images/eg1.png" style="width: 610px; height: 84px;" />
<pre>
<strong>Input:</strong> colors = [<u><strong>1</strong></u>,1,1,<strong><u>6</u></strong>,1,1,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> In the above image, color 1 is blue, and color 6 is red.
The furthest two houses with different colors are house 0 and house 3.
House 0 has color 1, and house 3 has color 6. The distance between them is abs(0 - 3) = 3.
Note that houses 3 and 6 can also produce the optimal answer.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2078.Two%20Furthest%20Houses%20With%20Different%20Colors/images/eg2.png" style="width: 426px; height: 84px;" />
<pre>
<strong>Input:</strong> colors = [<u><strong>1</strong></u>,8,3,8,<u><strong>3</strong></u>]
<strong>Output:</strong> 4
<strong>Explanation:</strong> In the above image, color 1 is blue, color 8 is yellow, and color 3 is green.
The furthest two houses with different colors are house 0 and house 4.
House 0 has color 1, and house 4 has color 3. The distance between them is abs(0 - 4) = 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> colors = [<u><strong>0</strong></u>,<strong><u>1</u></strong>]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The furthest two houses with different colors are house 0 and house 1.
House 0 has color 0, and house 1 has color 1. The distance between them is abs(0 - 1) = 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n ==&nbsp;colors.length</code></li>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= colors[i] &lt;= 100</code></li>
	<li>Test data are generated such that <strong>at least</strong> two houses have different colors.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxDistance(self, colors: List[int]) -> int:
        ans, n = 0, len(colors)
        for i in range(n):
            for j in range(i + 1, n):
                if colors[i] != colors[j]:
                    ans = max(ans, abs(i - j))
        return ans
```

```python
class Solution:
    def maxDistance(self, colors: List[int]) -> int:
        n = len(colors)
        if colors[0] != colors[-1]:
            return n - 1
        i, j = 1, n - 2
        while colors[i] == colors[0]:
            i += 1
        while colors[j] == colors[0]:
            j -= 1
        return max(n - i - 1, j)
```

### **Java**

```java
class Solution {
    public int maxDistance(int[] colors) {
        int ans = 0, n = colors.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (colors[i] != colors[j]) {
                    ans = Math.max(ans, Math.abs(i - j));
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        if (colors[0] != colors[n - 1]) {
            return n - 1;
        }
        int i = 0, j = n - 1;
        while (colors[++i] == colors[0])
            ;
        while (colors[--j] == colors[0])
            ;
        return Math.max(n - i - 1, j);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxDistance(vector<int>& colors) {
        int ans = 0, n = colors.size();
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j < n; ++j)
                if (colors[i] != colors[j])
                    ans = max(ans, abs(i - j));
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maxDistance(vector<int>& colors) {
        int n = colors.size();
        if (colors[0] != colors[n - 1]) return n - 1;
        int i = 0, j = n;
        while (colors[++i] == colors[0]);
        while (colors[--j] == colors[0]);
        return max(n - i - 1, j);
    }
};
```

### **Go**

```go
func maxDistance(colors []int) int {
	ans, n := 0, len(colors)
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if colors[i] != colors[j] {
				ans = max(ans, abs(i-j))
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

func abs(x int) int {
	if x >= 0 {
		return x
	}
	return -x
}
```

```go
func maxDistance(colors []int) int {
	n := len(colors)
	if colors[0] != colors[n-1] {
		return n - 1
	}
	i, j := 1, n-2
	for colors[i] == colors[0] {
		i++
	}
	for colors[j] == colors[0] {
		j--
	}
	return max(n-i-1, j)
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
