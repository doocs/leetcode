# [2021. 街上最亮的位置](https://leetcode.cn/problems/brightest-position-on-street)

[English Version](/solution/2000-2099/2021.Brightest%20Position%20on%20Street/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一条街上有很多的路灯，路灯的坐标由数组&nbsp;<code>lights&nbsp;</code>的形式给出。&nbsp;每个&nbsp;<code>lights[i] = [position<sub>i</sub>, range<sub>i</sub>]</code>&nbsp;代表坐标为&nbsp;<code>position<sub>i</sub></code>&nbsp;的路灯照亮的范围为&nbsp;<code>[position<sub>i</sub> - range<sub>i</sub>, position<sub>i</sub> + range<sub>i</sub>]</code>&nbsp;<strong>（包括顶点）。</strong></p>

<p>位置&nbsp;<code>p</code>&nbsp;的亮度由能够照到&nbsp;<code>p</code>的路灯的数量来决定的。</p>

<p>给出&nbsp;<code>lights</code>, 返回<strong>最亮</strong>的位置&nbsp;。如果有很多，返回坐标最小的。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2021.Brightest%20Position%20on%20Street/images/image-20210928155140-1.png" style="width: 700px; height: 165px;">
<pre><strong>输入:</strong> lights = [[-3,2],[1,2],[3,3]]
<strong>输出:</strong> -1
<strong>解释:</strong>
第一个路灯照亮的范围是[(-3) - 2, (-3) + 2] = [-5, -1].
第二个路灯照亮的范围是 [1 - 2, 1 + 2] = [-1, 3].
第三个路灯照亮的范围是 [3 - 3, 3 + 3] = [0, 6].

坐标-1 被第一个和第二个路灯照亮，亮度为 2
坐标 0，1，2 都被第二个和第三个路灯照亮，亮度为 2.
对于以上坐标，-1 最小，所以返回-1</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> lights = [[1,0],[0,1]]
<strong>输出:</strong> 1
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入:</strong> lights = [[1,2]]
<strong>输出:</strong> -1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= lights.length &lt;= 10<sup>5</sup></code></li>
	<li><code>lights[i].length == 2</code></li>
	<li><code>-10<sup>8</sup> &lt;= position<sub>i</sub> &lt;= 10<sup>8</sup></code></li>
	<li><code>0 &lt;= range<sub>i</sub> &lt;= 10<sup>8</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

差分数组 + 排序。

如果用数组实现，空间分配过大。因此可以使用哈希表 + 排序，或者直接使用 TreeMap。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
