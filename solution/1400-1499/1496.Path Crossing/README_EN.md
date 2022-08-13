# [1496. Path Crossing](https://leetcode.com/problems/path-crossing)

[中文文档](/solution/1400-1499/1496.Path%20Crossing/README.md)

## Description

<p>Given a string <code>path</code>, where <code>path[i] = &#39;N&#39;</code>, <code>&#39;S&#39;</code>, <code>&#39;E&#39;</code> or <code>&#39;W&#39;</code>, each representing moving one unit north, south, east, or west, respectively. You start at the origin <code>(0, 0)</code> on a 2D plane and walk on the path specified by <code>path</code>.</p>

<p>Return <code>true</code> <em>if the path crosses itself at any point, that is, if at any time you are on a location you have previously visited</em>. Return <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1496.Path%20Crossing/images/screen-shot-2020-06-10-at-123929-pm.png" style="width: 400px; height: 358px;" />
<pre>
<strong>Input:</strong> path = &quot;NES&quot;
<strong>Output:</strong> false 
<strong>Explanation:</strong> Notice that the path doesn&#39;t cross any point more than once.
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1496.Path%20Crossing/images/screen-shot-2020-06-10-at-123843-pm.png" style="width: 400px; height: 339px;" />
<pre>
<strong>Input:</strong> path = &quot;NESWW&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> Notice that the path visits the origin twice.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= path.length &lt;= 10<sup>4</sup></code></li>
	<li><code>path[i]</code> is either <code>&#39;N&#39;</code>, <code>&#39;S&#39;</code>, <code>&#39;E&#39;</code>, or <code>&#39;W&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isPathCrossing(self, path: str) -> bool:
        x = y = 0
        vis = {(x, y)}
        for c in path:
            if c == 'N':
                y += 1
            elif c == 'S':
                y -= 1
            elif c == 'E':
                x += 1
            else:
                x -= 1
            if (x, y) in vis:
                return True
            vis.add((x, y))
        return False
```

### **Java**

```java
class Solution {
    public boolean isPathCrossing(String path) {
        int x = 0;
        int y = 0;
        Set<Integer> vis = new HashSet<>();
        vis.add(0);
        for (char c : path.toCharArray()) {
            if (c == 'N') {
                ++y;
            } else if (c == 'S') {
                --y;
            } else if (c == 'E') {
                ++x;
            } else {
                --x;
            }
            int t = x * 20000 + y;
            if (vis.contains(t)) {
                return true;
            }
            vis.add(t);
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isPathCrossing(string path) {
        int x = 0, y = 0;
        unordered_set<int> vis {{0}};
        for (char c : path) {
            if (c == 'N')
                ++y;
            else if (c == 'S')
                --y;
            else if (c == 'E')
                ++x;
            else
                --x;
            int t = x * 20000 + y;
            if (vis.count(t)) return 1;
            vis.insert(t);
        }
        return 0;
    }
};
```

### **Go**

```go
func isPathCrossing(path string) bool {
	x, y := 0, 0
	vis := make(map[int]bool)
	vis[0] = true
	for _, c := range path {
		if c == 'N' {
			y++
		} else if c == 'S' {
			y--
		} else if c == 'E' {
			x++
		} else {
			x--
		}
		t := x*20000 + y
		if vis[t] {
			return true
		}
		vis[t] = true
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
