# [497. 非重叠矩形中的随机点](https://leetcode.cn/problems/random-point-in-non-overlapping-rectangles)

[English Version](/solution/0400-0499/0497.Random%20Point%20in%20Non-overlapping%20Rectangles/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个由非重叠的轴对齐矩形的数组 <code>rects</code> ，其中 <code>rects[i] = [ai, bi, xi, yi]</code> 表示 <code>(ai, bi)</code> 是第 <code>i</code> 个矩形的左下角点，<code>(xi, yi)</code> 是第 <code>i</code> 个矩形的右上角点。设计一个算法来随机挑选一个被某一矩形覆盖的整数点。矩形周长上的点也算做是被矩形覆盖。所有满足要求的点必须等概率被返回。</p>

<p>在给定的矩形覆盖的空间内的任何整数点都有可能被返回。</p>

<p><strong>请注意&nbsp;</strong>，整数点是具有整数坐标的点。</p>

<p>实现&nbsp;<code>Solution</code>&nbsp;类:</p>

<ul>
	<li><code>Solution(int[][] rects)</code>&nbsp;用给定的矩形数组&nbsp;<code>rects</code> 初始化对象。</li>
	<li><code>int[] pick()</code>&nbsp;返回一个随机的整数点 <code>[u, v]</code> 在给定的矩形所覆盖的空间内。</li>
</ul>

<ol>
</ol>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0497.Random%20Point%20in%20Non-overlapping%20Rectangles/images/lc-pickrandomrec.jpg" style="height: 539px; width: 419px;" /></p>

<pre>
<strong>输入: 
</strong>["Solution", "pick", "pick", "pick", "pick", "pick"]
[[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
<strong>输出: 
</strong>[null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]

<strong>解释：</strong>
Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
solution.pick(); // 返回 [1, -2]
solution.pick(); // 返回 [1, -1]
solution.pick(); // 返回 [-1, -2]
solution.pick(); // 返回 [-2, -2]
solution.pick(); // 返回 [0, 0]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= rects.length &lt;= 100</code></li>
	<li><code>rects[i].length == 4</code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= a<sub>i</sub>&nbsp;&lt; x<sub>i</sub>&nbsp;&lt;= 10<sup>9</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= b<sub>i</sub>&nbsp;&lt; y<sub>i</sub>&nbsp;&lt;= 10<sup>9</sup></code></li>
	<li><code>x<sub>i</sub>&nbsp;- a<sub>i</sub>&nbsp;&lt;= 2000</code></li>
	<li><code>y<sub>i</sub>&nbsp;- b<sub>i</sub>&nbsp;&lt;= 2000</code></li>
	<li>所有的矩形不重叠。</li>
	<li><code>pick</code> 最多被调用&nbsp;<code>10<sup>4</sup></code>&nbsp;次。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 二分查找**

将矩形面积求前缀和 $s$，然后随机获取到一个面积 $v$，利用二分查找定位到是哪个矩形，然后继续随机获取该矩形的其中一个整数点坐标即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        return new int[] {rect[0] + random.nextInt(rect[2] - rect[0] + 1),
            rect[1] + random.nextInt(rect[3] - rect[1] + 1)};
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
