# [497. Random Point in Non-overlapping Rectangles](https://leetcode.com/problems/random-point-in-non-overlapping-rectangles)

[中文文档](/solution/0400-0499/0497.Random%20Point%20in%20Non-overlapping%20Rectangles/README.md)

## Description

<p>You are given an array of non-overlapping axis-aligned rectangles <code>rects</code> where <code>rects[i] = [a<sub>i</sub>, b<sub>i</sub>, x<sub>i</sub>, y<sub>i</sub>]</code> indicates that <code>(a<sub>i</sub>, b<sub>i</sub>)</code> is the bottom-left corner point of the <code>i<sup>th</sup></code> rectangle and <code>(x<sub>i</sub>, y<sub>i</sub>)</code> is the top-right corner point of the <code>i<sup>th</sup></code> rectangle. Design an algorithm to pick a random integer point inside the space covered by one of the given rectangles. A point on the perimeter of a rectangle is included in the space covered by the rectangle.</p>

<p>Any integer point inside the space covered by one of the given rectangles should be equally likely to be returned.</p>

<p><strong>Note</strong> that an integer point is a point that has integer coordinates.</p>

<p>Implement the <code>Solution</code> class:</p>

<ul>
	<li><code>Solution(int[][] rects)</code> Initializes the object with the given rectangles <code>rects</code>.</li>
	<li><code>int[] pick()</code> Returns a random integer point <code>[u, v]</code> inside the space covered by one of the given rectangles.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0497.Random%20Point%20in%20Non-overlapping%20Rectangles/images/lc-pickrandomrec.jpg" style="width: 419px; height: 539px;" />
<pre>
<strong>Input</strong>
[&quot;Solution&quot;, &quot;pick&quot;, &quot;pick&quot;, &quot;pick&quot;, &quot;pick&quot;, &quot;pick&quot;]
[[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
<strong>Output</strong>
[null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]

<strong>Explanation</strong>
Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
solution.pick(); // return [1, -2]
solution.pick(); // return [1, -1]
solution.pick(); // return [-1, -2]
solution.pick(); // return [-2, -2]
solution.pick(); // return [0, 0]

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= rects.length &lt;= 100</code></li>
	<li><code>rects[i].length == 4</code></li>
	<li><code>-10<sup>9</sup> &lt;= a<sub>i</sub> &lt; x<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= b<sub>i</sub> &lt; y<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>x<sub>i</sub> - a<sub>i</sub> &lt;= 2000</code></li>
	<li><code>y<sub>i</sub> - b<sub>i</sub> &lt;= 2000</code></li>
	<li>All the rectangles do not overlap.</li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>pick</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def __init__(self, rects: List[List[int]]):
        self.rects = rects
        self.s = [0] * len(rects)
        for i, (x1, y1, x2, y2) in enumerate(rects):
            self.s[i] = self.s[i - 1] + (x2 - x1 + 1) * (y2 - y1 + 1)

    def pick(self) -> List[int]:
        v = random.randint(1, self.s[-1])
        idx = bisect_left(self.s, v)
        x1, y1, x2, y2 = self.rects[idx]
        return [random.randint(x1, x2), random.randint(y1, y2)]


# Your Solution object will be instantiated and called as such:
# obj = Solution(rects)
# param_1 = obj.pick()
```

### **Java**

```java
class Solution {
    private int[] s;
    private int[][] rects;
    private Random random = new Random();

    public Solution(int[][] rects) {
        int n = rects.length;
        s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
        }
        this.rects = rects;
    }

    public int[] pick() {
        int n = rects.length;
        int v = 1 + random.nextInt(s[n]);
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (s[mid] >= v) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        int[] rect = rects[left - 1];
        return new int[]{rect[0] + random.nextInt(rect[2] - rect[0] + 1), rect[1] + random.nextInt(rect[3] - rect[1] + 1)};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */
```

### **C++**

```cpp
class Solution {
public:
    vector<int> s;
    vector<vector<int>> rects;

    Solution(vector<vector<int>>& rects) {
        int n = rects.size();
        s.resize(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
        this->rects = rects;
        srand(time(nullptr));
    }

    vector<int> pick() {
        int n = rects.size();
        int v = 1 + rand() % s[n];
        int idx = lower_bound(s.begin(), s.end(), v) - s.begin();
        auto& rect = rects[idx - 1];
        int x = rect[0] + rand() % (rect[2] - rect[0] + 1);
        int y = rect[1] + rand() % (rect[3] - rect[1] + 1);
        return {x, y};
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(rects);
 * vector<int> param_1 = obj->pick();
 */
```

### **Go**

```go
type Solution struct {
	s     []int
	rects [][]int
}

func Constructor(rects [][]int) Solution {
	n := len(rects)
	s := make([]int, n+1)
	for i, v := range rects {
		s[i+1] = s[i] + (v[2]-v[0]+1)*(v[3]-v[1]+1)
	}
	return Solution{s, rects}
}

func (this *Solution) Pick() []int {
	n := len(this.rects)
	v := 1 + rand.Intn(this.s[len(this.s)-1])
	left, right := 0, n
	for left < right {
		mid := (left + right) >> 1
		if this.s[mid] >= v {
			right = mid
		} else {
			left = mid + 1
		}
	}
	rect := this.rects[left-1]
	x, y := rect[0]+rand.Intn(rect[2]-rect[0]+1), rect[1]+rand.Intn(rect[3]-rect[1]+1)
	return []int{x, y}
}
```

### **...**

```

```

<!-- tabs:end -->
