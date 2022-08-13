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
<p><strong>Example 1:</strong></p>
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
        self.mp = defaultdict(Counter)

    def add(self, point: List[int]) -> None:
        x, y = point
        self.mp[x][y] += 1

    def count(self, point: List[int]) -> int:
        x, y = point
        ans = 0
        if x not in self.mp:
            return ans
        xcnt = self.mp[x]

        for x1, counter in self.mp.items():
            if x1 != x:
                d = x1 - x
                ans += xcnt[y + d] * counter[y] * counter[y + d]
                ans += xcnt[y - d] * counter[y] * counter[y - d]
        return ans
```

### **Java**

```java
class DetectSquares {
    private Map<Integer, Map<Integer, Integer>> mp = new HashMap<>();

    public DetectSquares() {

    }

    public void add(int[] point) {
        int x = point[0], y = point[1];
        if (!mp.containsKey(x)) {
            mp.put(x, new HashMap<>());
        }
        mp.get(x).put(y, mp.get(x).getOrDefault(y, 0) + 1);
    }

    public int count(int[] point) {
        int x = point[0], y = point[1];
        int ans = 0;
        if (!mp.containsKey(x)) {
            return ans;
        }
        Map<Integer, Integer> xcnt = mp.get(x);
        for (Map.Entry<Integer, Map<Integer, Integer>> e : mp.entrySet()) {
            int x1 = e.getKey();
            Map<Integer, Integer> counter = e.getValue();
            if (x1 != x) {
                int d = x1 - x;
                ans += xcnt.getOrDefault(y + d, 0) * counter.getOrDefault(y, 0) * counter.getOrDefault(y + d, 0);
                ans += xcnt.getOrDefault(y - d, 0) * counter.getOrDefault(y, 0) * counter.getOrDefault(y - d, 0);
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
    unordered_map<int, unordered_map<int, int>> mp;

    DetectSquares() {
    }

    void add(vector<int> point) {
        int x = point[0], y = point[1];
        ++mp[x][y];
    }

    int count(vector<int> point) {
        int x = point[0], y = point[1];
        int ans = 0;
        if (!mp.count(x)) return ans;
        auto xcnt = mp[x];
        for (auto e : mp) {
            int x1 = e.first;
            auto counter = e.second;
            if (x1 != x) {
                int d = x1 - x;
                ans += xcnt[y + d] * counter[y] * counter[y + d];
                ans += xcnt[y - d] * counter[y] * counter[y - d];
            }
        }
        return ans;
    }
};

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares* obj = new DetectSquares();
 * obj->add(point);
 * int param_2 = obj->count(point);
 */
```

### **...**

```

```

<!-- tabs:end -->
