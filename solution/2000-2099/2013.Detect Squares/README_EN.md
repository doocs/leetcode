# [2013. Detect Squares](https://leetcode.com/problems/detect-squares)

[中文文档](/solution/2000-2099/2013.Detect%20Squares/README.md)

## Description

<p>You are given a stream of points on the X-Y plane. Design an algorithm that:</p>

<ul>
	<li><strong>Adds</strong> new points from the stream into a data structure. <strong>Duplicate</strong> points are allowed and should be treated as different points.</li>
	<li>Given a query point, <strong>counts</strong> the number of ways to choose three points from the data structure such that the three points and the query point form an <strong>axis-aligned square</strong> with <strong>positive area</strong>.</li>
</ul>

<p>An <strong>axis-aligned square</strong> is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis.</p>

<p>Implement the <code>DetectSquares</code> class:</p>

<ul>
	<li><code>DetectSquares()</code> Initializes the object with an empty data structure.</li>
	<li><code>void add(int[] point)</code> Adds a new point <code>point = [x, y]</code> to the data structure.</li>
	<li><code>int count(int[] point)</code> Counts the number of ways to form <strong>axis-aligned squares</strong> with point <code>point = [x, y]</code> as described above.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2013.Detect%20Squares/images/image.png" style="width: 869px; height: 504px;" />
<pre>
<strong>Input</strong>
[&quot;DetectSquares&quot;, &quot;add&quot;, &quot;add&quot;, &quot;add&quot;, &quot;count&quot;, &quot;count&quot;, &quot;add&quot;, &quot;count&quot;]
[[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
<strong>Output</strong>
[null, null, null, null, 1, 0, null, 2]

<strong>Explanation</strong>
DetectSquares detectSquares = new DetectSquares();
detectSquares.add([3, 10]);
detectSquares.add([11, 2]);
detectSquares.add([3, 2]);
detectSquares.count([11, 10]); // return 1. You can choose:
// - The first, second, and third points
detectSquares.count([14, 8]); // return 0. The query point cannot form a square with any points in the data structure.
detectSquares.add([11, 2]); // Adding duplicate points is allowed.
detectSquares.count([11, 10]); // return 2. You can choose:
// - The first, second, and third points
// - The first, third, and fourth points

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>point.length == 2</code></li>
	<li><code>0 &lt;= x, y &lt;= 1000</code></li>
	<li>At most <code>3000</code> calls <strong>in total</strong> will be made to <code>add</code> and <code>count</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class DetectSquares:
    def __init__(self):
        self.cnt = defaultdict(Counter)

    def add(self, point: List[int]) -> None:
        x, y = point
        self.cnt[x][y] += 1

    def count(self, point: List[int]) -> int:
        x1, y1 = point
        if x1 not in self.cnt:
            return 0
        ans = 0
        for x2 in self.cnt.keys():
            if x2 != x1:
                d = x2 - x1
                ans += self.cnt[x2][y1] * self.cnt[x1][y1 + d] * self.cnt[x2][y1 + d]
                ans += self.cnt[x2][y1] * self.cnt[x1][y1 - d] * self.cnt[x2][y1 - d]
        return ans


# Your DetectSquares object will be instantiated and called as such:
# obj = DetectSquares()
# obj.add(point)
# param_2 = obj.count(point)
```

### **Java**

```java
class DetectSquares {
    private Map<Integer, Map<Integer, Integer>> cnt = new HashMap<>();

    public DetectSquares() {
    }

    public void add(int[] point) {
        int x = point[0], y = point[1];
        cnt.computeIfAbsent(x, k -> new HashMap<>()).merge(y, 1, Integer::sum);
    }

    public int count(int[] point) {
        int x1 = point[0], y1 = point[1];
        if (!cnt.containsKey(x1)) {
            return 0;
        }
        int ans = 0;
        for (var e : cnt.entrySet()) {
            int x2 = e.getKey();
            if (x2 != x1) {
                int d = x2 - x1;
                var cnt1 = cnt.get(x1);
                var cnt2 = e.getValue();
                ans += cnt2.getOrDefault(y1, 0) * cnt1.getOrDefault(y1 + d, 0)
                    * cnt2.getOrDefault(y1 + d, 0);
                ans += cnt2.getOrDefault(y1, 0) * cnt1.getOrDefault(y1 - d, 0)
                    * cnt2.getOrDefault(y1 - d, 0);
            }
        }
        return ans;
    }
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */
```

### **C++**

```cpp
class DetectSquares {
public:
    DetectSquares() {

    }

    void add(vector<int> point) {
        int x = point[0], y = point[1];
        ++cnt[x][y];
    }

    int count(vector<int> point) {
        int x1 = point[0], y1 = point[1];
        if (!cnt.count(x1)) {
            return 0;
        }
        int ans = 0;
        for (auto& [x2, cnt2] : cnt) {
            if (x2 != x1) {
                int d = x2 - x1;
                auto& cnt1 = cnt[x1];
                ans += cnt2[y1] * cnt1[y1 + d] * cnt2[y1 + d];
                ans += cnt2[y1] * cnt1[y1 - d] * cnt2[y1 - d];
            }
        }
        return ans;
    }

private:
    unordered_map<int, unordered_map<int, int>> cnt;
};

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares* obj = new DetectSquares();
 * obj->add(point);
 * int param_2 = obj->count(point);
 */
```

### **Go**

```go
type DetectSquares struct {
	cnt map[int]map[int]int
}

func Constructor() DetectSquares {
	return DetectSquares{map[int]map[int]int{}}
}

func (this *DetectSquares) Add(point []int) {
	x, y := point[0], point[1]
	if _, ok := this.cnt[x]; !ok {
		this.cnt[x] = map[int]int{}
	}
	this.cnt[x][y]++
}

func (this *DetectSquares) Count(point []int) (ans int) {
	x1, y1 := point[0], point[1]
	if cnt1, ok := this.cnt[x1]; ok {
		for x2, cnt2 := range this.cnt {
			if x2 != x1 {
				d := x2 - x1
				ans += cnt2[y1] * cnt1[y1+d] * cnt2[y1+d]
				ans += cnt2[y1] * cnt1[y1-d] * cnt2[y1-d]
			}
		}
	}
	return
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Add(point);
 * param_2 := obj.Count(point);
 */
```

### **...**

```

```

<!-- tabs:end -->
