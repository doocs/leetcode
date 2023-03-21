# [1326. Minimum Number of Taps to Open to Water a Garden](https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden)

[中文文档](/solution/1300-1399/1326.Minimum%20Number%20of%20Taps%20to%20Open%20to%20Water%20a%20Garden/README.md)

## Description

<p>There is a one-dimensional garden on the x-axis. The garden starts at the point <code>0</code> and ends at the point <code>n</code>. (i.e The length of the garden is <code>n</code>).</p>

<p>There are <code>n + 1</code> taps located at points <code>[0, 1, ..., n]</code> in the garden.</p>

<p>Given an integer <code>n</code> and an integer array <code>ranges</code> of length <code>n + 1</code> where <code>ranges[i]</code> (0-indexed) means the <code>i-th</code> tap can water the area <code>[i - ranges[i], i + ranges[i]]</code> if it was open.</p>

<p>Return <em>the minimum number of taps</em> that should be open to water the whole garden, If the garden cannot be watered return <strong>-1</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1326.Minimum%20Number%20of%20Taps%20to%20Open%20to%20Water%20a%20Garden/images/1685_example_1.png" style="width: 525px; height: 255px;" />
<pre>
<strong>Input:</strong> n = 5, ranges = [3,4,1,1,0,0]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The tap at point 0 can cover the interval [-3,3]
The tap at point 1 can cover the interval [-3,5]
The tap at point 2 can cover the interval [1,3]
The tap at point 3 can cover the interval [2,4]
The tap at point 4 can cover the interval [4,4]
The tap at point 5 can cover the interval [5,5]
Opening Only the second tap will water the whole garden [0,5]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, ranges = [0,0,0,0]
<strong>Output:</strong> -1
<strong>Explanation:</strong> Even if you activate all the four taps you cannot water the whole garden.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>ranges.length == n + 1</code></li>
	<li><code>0 &lt;= ranges[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minTaps(self, n: int, ranges: List[int]) -> int:
        last = [0] * (n + 1)
        for i, x in enumerate(ranges):
            l, r = max(0, i - x), i + x
            last[l] = max(last[l], r)

        ans = mx = pre = 0
        for i in range(n):
            mx = max(mx, last[i])
            if mx <= i:
                return -1
            if pre == i:
                ans += 1
                pre = mx
        return ans
```

### **Java**

```java
class Solution {
    public int minTaps(int n, int[] ranges) {
        int[] last = new int[n + 1];
        for (int i = 0; i < n + 1; ++i) {
            int l = Math.max(0, i - ranges[i]), r = i + ranges[i];
            last[l] = Math.max(last[l], r);
        }
        int ans = 0, mx = 0, pre = 0;
        for (int i = 0; i < n; ++i) {
            mx = Math.max(mx, last[i]);
            if (mx <= i) {
                return -1;
            }
            if (pre == i) {
                ++ans;
                pre = mx;
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
    int minTaps(int n, vector<int>& ranges) {
        vector<int> last(n + 1);
        for (int i = 0; i < n + 1; ++i) {
            int l = max(0, i - ranges[i]), r = i + ranges[i];
            last[l] = max(last[l], r);
        }
        int ans = 0, mx = 0, pre = 0;
        for (int i = 0; i < n; ++i) {
            mx = max(mx, last[i]);
            if (mx <= i) {
                return -1;
            }
            if (pre == i) {
                ++ans;
                pre = mx;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minTaps(n int, ranges []int) (ans int) {
	last := make([]int, n+1)
	for i, x := range ranges {
		l, r := max(0, i-x), i+x
		last[l] = max(last[l], r)
	}
	var pre, mx int
	for i, j := range last[:n] {
		mx = max(mx, j)
		if mx <= i {
			return -1
		}
		if pre == i {
			ans++
			pre = mx
		}
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

### **TypeScript**

```ts
function minTaps(n: number, ranges: number[]): number {
    const last = new Array(n + 1).fill(0);
    for (let i = 0; i < n + 1; ++i) {
        const l = Math.max(0, i - ranges[i]);
        const r = i + ranges[i];
        last[l] = Math.max(last[l], r);
    }
    let ans = 0;
    let mx = 0;
    let pre = 0;
    for (let i = 0; i < n; ++i) {
        mx = Math.max(mx, last[i]);
        if (mx <= i) {
            return -1;
        }
        if (pre == i) {
            ++ans;
            pre = mx;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
