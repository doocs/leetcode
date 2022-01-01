# [497. 非重叠矩形中的随机点](https://leetcode-cn.com/problems/random-point-in-non-overlapping-rectangles)

[English Version](/solution/0400-0499/0497.Random%20Point%20in%20Non-overlapping%20Rectangles/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非重叠轴对齐矩形的列表 <code>rects</code>，写一个函数 <code>pick</code> 随机均匀地选取矩形覆盖的空间中的整数点。</p>

<p>提示：</p>

<ol>
	<li><strong>整数点</strong>是具有整数坐标的点。</li>
	<li>矩形周边上的点包含在矩形覆盖的空间中。</li>
	<li>第 <code>i</code> 个矩形 <code>rects [i] = [x1，y1，x2，y2]</code>，其中&nbsp;<code>[x1，y1]</code> 是左下角的整数坐标，<code>[x2，y2]</code> 是右上角的整数坐标。</li>
	<li>每个矩形的长度和宽度不超过 2000。</li>
	<li><code>1 &lt;= rects.length&nbsp;&lt;= 100</code></li>
	<li><code>pick</code> 以整数坐标数组&nbsp;<code>[p_x, p_y]</code>&nbsp;的形式返回一个点。</li>
	<li><code>pick</code> 最多被调用10000次。</li>
</ol>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: 
</strong>[&quot;Solution&quot;,&quot;pick&quot;,&quot;pick&quot;,&quot;pick&quot;]
[[[[1,1,5,5]]],[],[],[]]
<strong>输出: 
</strong>[null,[4,1],[4,1],[3,3]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入: 
</strong>[&quot;Solution&quot;,&quot;pick&quot;,&quot;pick&quot;,&quot;pick&quot;,&quot;pick&quot;,&quot;pick&quot;]
[[[[-2,-2,-1,-1],[1,0,3,0]]],[],[],[],[],[]]
<strong>输出: 
</strong>[null,[-1,-2],[2,0],[-2,-1],[3,0],[-2,-2]]</pre>

<p>&nbsp;</p>

<p><strong>输入语法的说明：</strong></p>

<p>输入是两个列表：调用的子例程及其参数。<code>Solution</code> 的构造函数有一个参数，即矩形数组 <code>rects</code>。<code>pick</code> 没有参数。参数总是用列表包装的，即使没有也是如此。</p>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

前缀和 + 二分查找。

将矩形面积求前缀和 s，然后随机获取到一个面积 v，利用二分查找定位到是哪个矩形，然后继续随机获取该矩形的其中一个整数点坐标即可。

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
        left, right = 0, len(self.s) - 1
        while left < right:
            mid = (left + right) >> 1
            if self.s[mid] >= v:
                right = mid
            else:
                left = mid + 1
        x1, y1, x2, y2 = self.rects[left]
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
        int left = 0, right = n;
        while (left < right)
        {
            int mid = (left + right) >> 1;
            if (s[mid] >= v) right = mid;
            else left = mid + 1;
        }
        auto& rect = rects[left - 1];
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
