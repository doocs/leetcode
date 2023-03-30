# [1637. Widest Vertical Area Between Two Points Containing No Points](https://leetcode.com/problems/widest-vertical-area-between-two-points-containing-no-points)

[中文文档](/solution/1600-1699/1637.Widest%20Vertical%20Area%20Between%20Two%20Points%20Containing%20No%20Points/README.md)

## Description

<p>Given <code>n</code> <code>points</code> on a 2D plane where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>, Return<em>&nbsp;the <strong>widest vertical area</strong> between two points such that no points are inside the area.</em></p>

<p>A <strong>vertical area</strong> is an area of fixed-width extending infinitely along the y-axis (i.e., infinite height). The <strong>widest vertical area</strong> is the one with the maximum width.</p>

<p>Note that points <strong>on the edge</strong> of a vertical area <strong>are not</strong> considered included in the area.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1637.Widest%20Vertical%20Area%20Between%20Two%20Points%20Containing%20No%20Points/images/points3.png" style="width: 276px; height: 371px;" />​
<pre>
<strong>Input:</strong> points = [[8,7],[9,9],[7,4],[9,7]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> Both the red and the blue area are optimal.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> points = [[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == points.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub>&nbsp;&lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxWidthOfVerticalArea(self, points: List[List[int]]) -> int:
        points.sort()
        return max(b[0] - a[0] for a, b in pairwise(points))
```

```python
class Solution:
    def maxWidthOfVerticalArea(self, points: List[List[int]]) -> int:
        nums = [x for x, _ in points]
        n = len(nums)
        mi, mx = min(nums), max(nums)
        bucket_size = max(1, (mx - mi) // (n - 1))
        bucket_count = (mx - mi) // bucket_size + 1
        buckets = [[inf, -inf] for _ in range(bucket_count)]
        for x in nums:
            i = (x - mi) // bucket_size
            buckets[i][0] = min(buckets[i][0], x)
            buckets[i][1] = max(buckets[i][1], x)
        ans = 0
        prev = inf
        for curmin, curmax in buckets:
            if curmin > curmax:
                continue
            ans = max(ans, curmin - prev)
            prev = curmax
        return ans
```

### **Java**

```java
class Solution {
    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        int ans = 0;
        for (int i = 0; i < points.length - 1; ++i) {
            ans = Math.max(ans, points[i + 1][0] - points[i][0]);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maxWidthOfVerticalArea(int[][] points) {
        int n = points.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = points[i][0];
        }
        final int inf = 1 << 30;
        int mi = inf, mx = -inf;
        for (int v : nums) {
            mi = Math.min(mi, v);
            mx = Math.max(mx, v);
        }
        int bucketSize = Math.max(1, (mx - mi) / (n - 1));
        int bucketCount = (mx - mi) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][2];
        for (var bucket : buckets) {
            bucket[0] = inf;
            bucket[1] = -inf;
        }
        for (int v : nums) {
            int i = (v - mi) / bucketSize;
            buckets[i][0] = Math.min(buckets[i][0], v);
            buckets[i][1] = Math.max(buckets[i][1], v);
        }
        int prev = inf;
        int ans = 0;
        for (var bucket : buckets) {
            if (bucket[0] > bucket[1]) {
                continue;
            }
            ans = Math.max(ans, bucket[0] - prev);
            prev = bucket[1];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxWidthOfVerticalArea(vector<vector<int>>& points) {
        sort(points.begin(), points.end());
        int ans = 0;
        for (int i = 0; i < points.size() - 1; ++i) {
            ans = max(ans, points[i + 1][0] - points[i][0]);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maxWidthOfVerticalArea(vector<vector<int>>& points) {
        int n = points.size();
        vector<int> nums;
        for (auto& p : points) {
            nums.push_back(p[0]);
        }
        const int inf = 1 << 30;
        int mi = inf, mx = -inf;
        for (int v : nums) {
            mi = min(mi, v);
            mx = max(mx, v);
        }
        int bucketSize = max(1, (mx - mi) / (n - 1));
        int bucketCount = (mx - mi) / bucketSize + 1;
        vector<pair<int, int>> buckets(bucketCount, {inf, -inf});
        for (int v : nums) {
            int i = (v - mi) / bucketSize;
            buckets[i].first = min(buckets[i].first, v);
            buckets[i].second = max(buckets[i].second, v);
        }
        int ans = 0;
        int prev = inf;
        for (auto [curmin, curmax] : buckets) {
            if (curmin > curmax) continue;
            ans = max(ans, curmin - prev);
            prev = curmax;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxWidthOfVerticalArea(points [][]int) (ans int) {
	sort.Slice(points, func(i, j int) bool { return points[i][0] < points[j][0] })
	for i, p := range points[1:] {
		ans = max(ans, p[0]-points[i][0])
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func maxWidthOfVerticalArea(points [][]int) (ans int) {
	n := len(points)
	nums := make([]int, 0, n)
	for _, p := range points {
		nums = append(nums, p[0])
	}
	const inf = 1 << 30
	mi, mx := inf, -inf
	for _, v := range nums {
		mi = min(mi, v)
		mx = max(mx, v)
	}
	bucketSize := max(1, (mx-mi)/(n-1))
	bucketCount := (mx-mi)/bucketSize + 1
	buckets := make([][]int, bucketCount)
	for i := range buckets {
		buckets[i] = []int{inf, -inf}
	}
	for _, v := range nums {
		i := (v - mi) / bucketSize
		buckets[i][0] = min(buckets[i][0], v)
		buckets[i][1] = max(buckets[i][1], v)
	}
	prev := inf
	for _, bucket := range buckets {
		if bucket[0] > bucket[1] {
			continue
		}
		ans = max(ans, bucket[0]-prev)
		prev = bucket[1]
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function maxWidthOfVerticalArea(points: number[][]): number {
    points.sort((a, b) => a[0] - b[0]);
    let ans = 0;
    for (let i = 1; i < points.length; ++i) {
        ans = Math.max(ans, points[i][0] - points[i - 1][0]);
    }
    return ans;
}
```

```ts
function maxWidthOfVerticalArea(points: number[][]): number {
    const nums: number[] = points.map(point => point[0]);
    const inf = 1 << 30;
    const n = nums.length;
    let mi = inf;
    let mx = -inf;
    for (const x of nums) {
        mi = Math.min(mi, x);
        mx = Math.max(mx, x);
    }
    const bucketSize = Math.max(1, Math.floor((mx - mi) / (n - 1)));
    const bucketCount = Math.floor((mx - mi) / bucketSize) + 1;
    const buckets = new Array(bucketCount).fill(0).map(() => [inf, -inf]);
    for (const x of nums) {
        const i = Math.floor((x - mi) / bucketSize);
        buckets[i][0] = Math.min(buckets[i][0], x);
        buckets[i][1] = Math.max(buckets[i][1], x);
    }
    let prev = inf;
    let ans = 0;
    for (const [left, right] of buckets) {
        if (left > right) {
            continue;
        }
        ans = Math.max(ans, left - prev);
        prev = right;
    }
    return ans;
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} points
 * @return {number}
 */
var maxWidthOfVerticalArea = function (points) {
    points.sort((a, b) => a[0] - b[0]);
    let ans = 0;
    let px = points[0][0];
    for (const [x, _] of points) {
        ans = Math.max(ans, x - px);
        px = x;
    }
    return ans;
};
```

```js
/**
 * @param {number[][]} points
 * @return {number}
 */
var maxWidthOfVerticalArea = function (points) {
    const nums = points.map(point => point[0]);
    const inf = 1 << 30;
    const n = nums.length;
    let mi = inf;
    let mx = -inf;
    for (const x of nums) {
        mi = Math.min(mi, x);
        mx = Math.max(mx, x);
    }
    const bucketSize = Math.max(1, Math.floor((mx - mi) / (n - 1)));
    const bucketCount = Math.floor((mx - mi) / bucketSize) + 1;
    const buckets = new Array(bucketCount).fill(0).map(() => [inf, -inf]);
    for (const x of nums) {
        const i = Math.floor((x - mi) / bucketSize);
        buckets[i][0] = Math.min(buckets[i][0], x);
        buckets[i][1] = Math.max(buckets[i][1], x);
    }
    let prev = inf;
    let ans = 0;
    for (const [left, right] of buckets) {
        if (left > right) {
            continue;
        }
        ans = Math.max(ans, left - prev);
        prev = right;
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
