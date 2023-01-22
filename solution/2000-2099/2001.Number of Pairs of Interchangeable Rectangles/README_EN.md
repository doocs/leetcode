# [2001. Number of Pairs of Interchangeable Rectangles](https://leetcode.com/problems/number-of-pairs-of-interchangeable-rectangles)

[中文文档](/solution/2000-2099/2001.Number%20of%20Pairs%20of%20Interchangeable%20Rectangles/README.md)

## Description

<p>You are given <code>n</code> rectangles represented by a <strong>0-indexed</strong> 2D integer array <code>rectangles</code>, where <code>rectangles[i] = [width<sub>i</sub>, height<sub>i</sub>]</code> denotes the width and height of the <code>i<sup>th</sup></code> rectangle.</p>

<p>Two rectangles <code>i</code> and <code>j</code> (<code>i &lt; j</code>) are considered <strong>interchangeable</strong> if they have the <strong>same</strong> width-to-height ratio. More formally, two rectangles are <strong>interchangeable</strong> if <code>width<sub>i</sub>/height<sub>i</sub> == width<sub>j</sub>/height<sub>j</sub></code> (using decimal division, not integer division).</p>

<p>Return <em>the <strong>number</strong> of pairs of <strong>interchangeable</strong> rectangles in </em><code>rectangles</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> rectangles = [[4,8],[3,6],[10,20],[15,30]]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The following are the interchangeable pairs of rectangles by index (0-indexed):
- Rectangle 0 with rectangle 1: 4/8 == 3/6.
- Rectangle 0 with rectangle 2: 4/8 == 10/20.
- Rectangle 0 with rectangle 3: 4/8 == 15/30.
- Rectangle 1 with rectangle 2: 3/6 == 10/20.
- Rectangle 1 with rectangle 3: 3/6 == 15/30.
- Rectangle 2 with rectangle 3: 10/20 == 15/30.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> rectangles = [[4,5],[7,8]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no interchangeable pairs of rectangles.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == rectangles.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>rectangles[i].length == 2</code></li>
	<li><code>1 &lt;= width<sub>i</sub>, height<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def interchangeableRectangles(self, rectangles: List[List[int]]) -> int:
        ans = 0
        cnt = Counter()
        for w, h in rectangles:
            g = gcd(w, h)
            w, h = w // g, h // g
            ans += cnt[(w, h)]
            cnt[(w, h)] += 1
        return ans
```

### **Java**

```java
class Solution {
    public long interchangeableRectangles(int[][] rectangles) {
        long ans = 0;
        int n = rectangles.length + 1;
        Map<Long, Integer> cnt = new HashMap<>();
        for (var e : rectangles) {
            int w = e[0], h = e[1];
            int g = gcd(w, h);
            w /= g;
            h /= g;
            long x = (long) w * n + h;
            ans += cnt.getOrDefault(x, 0);
            cnt.merge(x, 1, Integer::sum);
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long interchangeableRectangles(vector<vector<int>>& rectangles) {
        long long ans = 0;
        int n = rectangles.size();
        unordered_map<long long, int> cnt;
        for (auto& e : rectangles) {
            int w = e[0], h = e[1];
            int g = gcd(w, h);
            w /= g;
            h /= g;
            long long x = 1ll * w * (n + 1) + h;
            ans += cnt[x];
            cnt[x]++;
        }
        return ans;
    }
};
```

### **Go**

```go
func interchangeableRectangles(rectangles [][]int) int64 {
	ans := 0
	n := len(rectangles)
	cnt := map[int]int{}
	for _, e := range rectangles {
		w, h := e[0], e[1]
		g := gcd(w, h)
		w, h = w/g, h/g
		x := w*(n+1) + h
		ans += cnt[x]
		cnt[x]++
	}
	return int64(ans)
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} rectangles
 * @return {number}
 */
var interchangeableRectangles = function (rectangles) {
    const cnt = new Map();
    let ans = 0;
    for (let [w, h] of rectangles) {
        const g = gcd(w, h);
        w = Math.floor(w / g);
        h = Math.floor(h / g);
        const x = w * (rectangles.length + 1) + h;
        ans += cnt.get(x) | 0;
        cnt.set(x, (cnt.get(x) | 0) + 1);
    }
    return ans;
};

function gcd(a, b) {
    if (b == 0) {
        return a;
    }
    return gcd(b, a % b);
}
```

### **...**

```

```

<!-- tabs:end -->
