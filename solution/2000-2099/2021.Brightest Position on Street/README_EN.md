# [2021. Brightest Position on Street](https://leetcode.com/problems/brightest-position-on-street)

[中文文档](/solution/2000-2099/2021.Brightest%20Position%20on%20Street/README.md)

## Description

<p>A perfectly straight street is represented by a number line. The street has street lamp(s) on it and is represented by a 2D integer array <code>lights</code>. Each <code>lights[i] = [position<sub>i</sub>, range<sub>i</sub>]</code> indicates that there is a street lamp at position <code>position<sub>i</sub></code> that lights up the area from <code>[position<sub>i</sub> - range<sub>i</sub>, position<sub>i</sub> + range<sub>i</sub>]</code> (<strong>inclusive</strong>).</p>

<p>The <strong>brightness</strong> of a position <code>p</code> is defined as the number of street lamp that light up the position <code>p</code>.</p>

<p>Given <code>lights</code>, return <em>the <strong>brightest</strong> position on the</em><em> street. If there are multiple brightest positions, return the <strong>smallest</strong> one.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2021.Brightest%20Position%20on%20Street/images/image-20210928155140-1.png" style="width: 700px; height: 165px;" />
<pre>
<strong>Input:</strong> lights = [[-3,2],[1,2],[3,3]]
<strong>Output:</strong> -1
<strong>Explanation:</strong>
The first street lamp lights up the area from [(-3) - 2, (-3) + 2] = [-5, -1].
The second street lamp lights up the area from [1 - 2, 1 + 2] = [-1, 3].
The third street lamp lights up the area from [3 - 3, 3 + 3] = [0, 6].

Position -1 has a brightness of 2, illuminated by the first and second street light.
Positions 0, 1, 2, and 3 have a brightness of 2, illuminated by the second and third street light.
Out of all these positions, -1 is the smallest, so return it.

</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> lights = [[1,0],[0,1]]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
The first street lamp lights up the area from [1 - 0, 1 + 0] = [1, 1].
The second street lamp lights up the area from [0 - 1, 0 + 1] = [-1, 1].

Position 1 has a brightness of 2, illuminated by the first and second street light.
Return 1 because it is the brightest position on the street.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> lights = [[1,2]]
<strong>Output:</strong> -1
<strong>Explanation:</strong>
The first street lamp lights up the area from [1 - 2, 1 + 2] = [-1, 3].

Positions -1, 0, 1, 2, and 3 have a brightness of 1, illuminated by the first street light.
Out of all these positions, -1 is the smallest, so return it.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= lights.length &lt;= 10<sup>5</sup></code></li>
	<li><code>lights[i].length == 2</code></li>
	<li><code>-10<sup>8</sup> &lt;= position<sub>i</sub> &lt;= 10<sup>8</sup></code></li>
	<li><code>0 &lt;= range<sub>i</sub> &lt;= 10<sup>8</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def brightestPosition(self, lights: List[List[int]]) -> int:
        d = defaultdict(int)
        for p, r in lights:
            d[p - r] += 1
            d[p + r + 1] -= 1
        s = mx = ans = 0
        for k in sorted(d):
            s += d[k]
            if s > mx:
                mx = s
                ans = k
        return ans
```

### **Java**

```java
class Solution {
    public int brightestPosition(int[][] lights) {
        TreeMap<Integer, Integer> d = new TreeMap<>();
        for (int[] e : lights) {
            int l = e[0] - e[1], r = e[0] + e[1];
            d.put(l, d.getOrDefault(l, 0) + 1);
            d.put(r + 1, d.getOrDefault(r + 1, 0) - 1);
        }
        int s = 0, mx = 0, ans = 0;
        for (Map.Entry<Integer, Integer> e : d.entrySet()) {
            s += e.getValue();
            if (s > mx) {
                mx = s;
                ans = e.getKey();
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int brightestPosition(vector<vector<int>>& lights) {
        map<int, int> d;
        for (auto& e : lights) {
            int l = e[0] - e[1], r = e[0] + e[1];
            ++d[l];
            --d[r + 1];
        }
        int s = 0, mx = 0, ans = 0;
        for (auto& e : d) {
            s += e.second;
            if (s > mx) {
                mx = s;
                ans = e.first;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func brightestPosition(lights [][]int) int {
	d := make(map[int]int)
	for _, e := range lights {
		l, r := e[0]-e[1], e[0]+e[1]
		d[l] += 1
		d[r+1] -= 1
	}

	var keys []int
	for k := range d {
		keys = append(keys, k)
	}
	sort.Ints(keys)

	s, mx, ans := 0, 0, 0
	for _, k := range keys {
		s += d[k]
		if s > mx {
			mx = s
			ans = k
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
