# [2237. Count Positions on Street With Required Brightness](https://leetcode.com/problems/count-positions-on-street-with-required-brightness)

[中文文档](/solution/2200-2299/2237.Count%20Positions%20on%20Street%20With%20Required%20Brightness/README.md)

## Description

<p>You are given an integer <code>n</code>. A perfectly straight street is represented by a number line ranging from <code>0</code> to <code>n - 1</code>. You are given a 2D integer array <code>lights</code> representing the street lamp(s) on the street. Each <code>lights[i] = [position<sub>i</sub>, range<sub>i</sub>]</code> indicates that there is a street lamp at position <code>position<sub>i</sub></code> that lights up the area from <code>[max(0, position<sub>i</sub> - range<sub>i</sub>), min(n - 1, position<sub>i</sub> + range<sub>i</sub>)]</code> (<strong>inclusive</strong>).</p>

<p>The <strong>brightness</strong> of a position <code>p</code> is defined as the number of street lamps that light up the position <code>p</code>. You are given a <strong>0-indexed</strong> integer array <code>requirement</code> of size <code>n</code> where <code>requirement[i]</code> is the minimum <strong>brightness</strong> of the <code>i<sup>th</sup></code> position on the street.</p>

<p>Return <em>the number of positions </em><code>i</code><em> on the street between </em><code>0</code><em> and </em><code>n - 1</code><em> that have a <strong>brightness</strong> </em><em>of <strong>at least</strong> </em><code>requirement[i]</code><em>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2237.Count%20Positions%20on%20Street%20With%20Required%20Brightness/images/screenshot-2022-04-11-at-22-24-43-diagramdrawio-diagramsnet.png" style="height: 150px; width: 579px;" />
<pre>
<strong>Input:</strong> n = 5, lights = [[0,1],[2,1],[3,2]], requirement = [0,2,1,4,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong>
- The first street lamp lights up the area from [max(0, 0 - 1), min(n - 1, 0 + 1)] = [0, 1] (inclusive).
- The second street lamp lights up the area from [max(0, 2 - 1), min(n - 1, 2 + 1)] = [1, 3] (inclusive).
- The third street lamp lights up the area from [max(0, 3 - 2), min(n - 1, 3 + 2)] = [1, 4] (inclusive).

-   Position 0 is covered by the first street lamp. It is covered by 1 street lamp which is greater than requirement[0].
-   Position 1 is covered by the first, second, and third street lamps. It is covered by 3 street lamps which is greater than requirement[1].
-   Position 2 is covered by the second and third street lamps. It is covered by 2 street lamps which is greater than requirement[2].
-   Position 3 is covered by the second and third street lamps. It is covered by 2 street lamps which is less than requirement[3].
-   Position 4 is covered by the third street lamp. It is covered by 1 street lamp which is equal to requirement[4].

Positions 0, 1, 2, and 4 meet the requirement so we return 4.

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1, lights = [[0,1]], requirement = [2]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
- The first street lamp lights up the area from [max(0, 0 - 1), min(n - 1, 0 + 1)] = [0, 0] (inclusive).
- Position 0 is covered by the first street lamp. It is covered by 1 street lamp which is less than requirement[0].
- We return 0 because no position meets their brightness requirement.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= lights.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= position<sub>i</sub> &lt; n</code></li>
	<li><code>0 &lt;= range<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>requirement.length == n</code></li>
	<li><code>0 &lt;= requirement[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def meetRequirement(
        self, n: int, lights: List[List[int]], requirement: List[int]
    ) -> int:
        d = [0] * 100010
        for p, r in lights:
            i, j = max(0, p - r), min(n - 1, p + r)
            d[i] += 1
            d[j + 1] -= 1
        return sum(s >= r for s, r in zip(accumulate(d), requirement))
```

### **Java**

```java
class Solution {
    public int meetRequirement(int n, int[][] lights, int[] requirement) {
        int[] d = new int[100010];
        for (int[] e : lights) {
            int i = Math.max(0, e[0] - e[1]);
            int j = Math.min(n - 1, e[0] + e[1]);
            ++d[i];
            --d[j + 1];
        }
        int s = 0;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            s += d[i];
            if (s >= requirement[i]) {
                ++ans;
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
    int meetRequirement(int n, vector<vector<int>>& lights, vector<int>& requirement) {
        vector<int> d(100010);
        for (auto& e : lights) {
            int i = max(0, e[0] - e[1]), j = min(n - 1, e[0] + e[1]);
            ++d[i];
            --d[j + 1];
        }
        int s = 0, ans = 0;
        for (int i = 0; i < n; ++i) {
            s += d[i];
            if (s >= requirement[i]) ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func meetRequirement(n int, lights [][]int, requirement []int) int {
	d := make([]int, 100010)
	for _, e := range lights {
		i, j := max(0, e[0]-e[1]), min(n-1, e[0]+e[1])
		d[i]++
		d[j+1]--
	}
	var s, ans int
	for i, r := range requirement {
		s += d[i]
		if s >= r {
			ans++
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

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
