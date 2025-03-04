---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2021.Brightest%20Position%20on%20Street/README_EN.md
tags:
    - Array
    - Ordered Set
    - Prefix Sum
    - Sorting
---

<!-- problem:start -->

# [2021. Brightest Position on Street 🔒](https://leetcode.com/problems/brightest-position-on-street)

[中文文档](/solution/2000-2099/2021.Brightest%20Position%20on%20Street/README.md)

## Description

<!-- description:start -->

<p>A perfectly straight street is represented by a number line. The street has street lamp(s) on it and is represented by a 2D integer array <code>lights</code>. Each <code>lights[i] = [position<sub>i</sub>, range<sub>i</sub>]</code> indicates that there is a street lamp at position <code>position<sub>i</sub></code> that lights up the area from <code>[position<sub>i</sub> - range<sub>i</sub>, position<sub>i</sub> + range<sub>i</sub>]</code> (<strong>inclusive</strong>).</p>

<p>The <strong>brightness</strong> of a position <code>p</code> is defined as the number of street lamp that light up the position <code>p</code>.</p>

<p>Given <code>lights</code>, return <em>the <strong>brightest</strong> position on the</em><em> street. If there are multiple brightest positions, return the <strong>smallest</strong> one.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
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

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> lights = [[1,0],[0,1]]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
The first street lamp lights up the area from [1 - 0, 1 + 0] = [1, 1].
The second street lamp lights up the area from [0 - 1, 0 + 1] = [-1, 1].

Position 1 has a brightness of 2, illuminated by the first and second street light.
Return 1 because it is the brightest position on the street.
</pre>

<p><strong class="example">Example 3:</strong></p>

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Difference Array + Hash Table + Sorting

We can consider the range illuminated by each street light as an interval, with the left endpoint $l = position_i - range_i$ and the right endpoint $r = position_i + range_i$. We can use the idea of a difference array. For each interval $[l, r]$, we add $1$ to the value at position $l$ and subtract $1$ from the value at position $r + 1$. We use a hash table to maintain the change value at each position.

Then we traverse each position in ascending order, calculate the brightness $s$ at the current position. If the previous maximum brightness $mx < s$, then update the maximum brightness $mx = s$ and record the current position $ans = i$.

Finally, return $ans$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of `lights`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def brightestPosition(self, lights: List[List[int]]) -> int:
        d = defaultdict(int)
        for i, j in lights:
            l, r = i - j, i + j
            d[l] += 1
            d[r + 1] -= 1
        ans = s = mx = 0
        for k in sorted(d):
            s += d[k]
            if mx < s:
                mx = s
                ans = k
        return ans
```

#### Java

```java
class Solution {
    public int brightestPosition(int[][] lights) {
        TreeMap<Integer, Integer> d = new TreeMap<>();
        for (var x : lights) {
            int l = x[0] - x[1], r = x[0] + x[1];
            d.merge(l, 1, Integer::sum);
            d.merge(r + 1, -1, Integer::sum);
        }
        int ans = 0, s = 0, mx = 0;
        for (var x : d.entrySet()) {
            int v = x.getValue();
            s += v;
            if (mx < s) {
                mx = s;
                ans = x.getKey();
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int brightestPosition(vector<vector<int>>& lights) {
        map<int, int> d;
        for (auto& x : lights) {
            int l = x[0] - x[1], r = x[0] + x[1];
            ++d[l];
            --d[r + 1];
        }
        int ans = 0, s = 0, mx = 0;
        for (auto& [i, v] : d) {
            s += v;
            if (mx < s) {
                mx = s;
                ans = i;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func brightestPosition(lights [][]int) (ans int) {
	d := map[int]int{}
	for _, x := range lights {
		l, r := x[0]-x[1], x[0]+x[1]
		d[l]++
		d[r+1]--
	}
	keys := make([]int, 0, len(d))
	for i := range d {
		keys = append(keys, i)
	}
	sort.Ints(keys)
	mx, s := 0, 0
	for _, i := range keys {
		s += d[i]
		if mx < s {
			mx = s
			ans = i
		}
	}
	return
}
```

#### JavaScript

```js
/**
 * @param {number[][]} lights
 * @return {number}
 */
var brightestPosition = function (lights) {
    const d = new Map();
    for (const [i, j] of lights) {
        const l = i - j;
        const r = i + j;
        d.set(l, (d.get(l) ?? 0) + 1);
        d.set(r + 1, (d.get(r + 1) ?? 0) - 1);
    }
    const keys = [];
    for (const k of d.keys()) {
        keys.push(k);
    }
    keys.sort((a, b) => a - b);
    let ans = 0;
    let s = 0;
    let mx = 0;
    for (const i of keys) {
        s += d.get(i);
        if (mx < s) {
            mx = s;
            ans = i;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
