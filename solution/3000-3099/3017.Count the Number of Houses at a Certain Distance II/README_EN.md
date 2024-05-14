# [3017. Count the Number of Houses at a Certain Distance II](https://leetcode.com/problems/count-the-number-of-houses-at-a-certain-distance-ii)

[中文文档](/solution/3000-3099/3017.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20II/README.md)

<!-- tags:Graph,Prefix Sum -->

<!-- difficulty:Hard -->

## Description

<p>You are given three <strong>positive</strong> integers <code>n</code>, <code>x</code>, and <code>y</code>.</p>

<p>In a city, there exist houses numbered <code>1</code> to <code>n</code> connected by <code>n</code> streets. There is a street connecting the house numbered <code>i</code> with the house numbered <code>i + 1</code> for all <code>1 &lt;= i &lt;= n - 1</code> . An additional street connects the house numbered <code>x</code> with the house numbered <code>y</code>.</p>

<p>For each <code>k</code>, such that <code>1 &lt;= k &lt;= n</code>, you need to find the number of <strong>pairs of houses</strong> <code>(house<sub>1</sub>, house<sub>2</sub>)</code> such that the <strong>minimum</strong> number of streets that need to be traveled to reach <code>house<sub>2</sub></code> from <code>house<sub>1</sub></code> is <code>k</code>.</p>

<p>Return <em>a <strong>1-indexed</strong> array </em><code>result</code><em> of length </em><code>n</code><em> where </em><code>result[k]</code><em> represents the <strong>total</strong> number of pairs of houses such that the <strong>minimum</strong> streets required to reach one house from the other is </em><code>k</code>.</p>

<p><strong>Note</strong> that <code>x</code> and <code>y</code> can be <strong>equal</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3017.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20II/images/example2.png" style="width: 474px; height: 197px;" />
<pre>
<strong>Input:</strong> n = 3, x = 1, y = 3
<strong>Output:</strong> [6,0,0]
<strong>Explanation:</strong> Let&#39;s look at each pair of houses:
- For the pair (1, 2), we can go from house 1 to house 2 directly.
- For the pair (2, 1), we can go from house 2 to house 1 directly.
- For the pair (1, 3), we can go from house 1 to house 3 directly.
- For the pair (3, 1), we can go from house 3 to house 1 directly.
- For the pair (2, 3), we can go from house 2 to house 3 directly.
- For the pair (3, 2), we can go from house 3 to house 2 directly.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3017.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20II/images/example3.png" style="width: 668px; height: 174px;" />
<pre>
<strong>Input:</strong> n = 5, x = 2, y = 4
<strong>Output:</strong> [10,8,2,0,0]
<strong>Explanation:</strong> For each distance k the pairs are:
- For k == 1, the pairs are (1, 2), (2, 1), (2, 3), (3, 2), (2, 4), (4, 2), (3, 4), (4, 3), (4, 5), and (5, 4).
- For k == 2, the pairs are (1, 3), (3, 1), (1, 4), (4, 1), (2, 5), (5, 2), (3, 5), and (5, 3).
- For k == 3, the pairs are (1, 5), and (5, 1).
- For k == 4 and k == 5, there are no pairs.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3017.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20II/images/example5.png" style="width: 544px; height: 130px;" />
<pre>
<strong>Input:</strong> n = 4, x = 1, y = 1
<strong>Output:</strong> [6,4,2,0]
<strong>Explanation:</strong> For each distance k the pairs are:
- For k == 1, the pairs are (1, 2), (2, 1), (2, 3), (3, 2), (3, 4), and (4, 3).
- For k == 2, the pairs are (1, 3), (3, 1), (2, 4), and (4, 2).
- For k == 3, the pairs are (1, 4), and (4, 1).
- For k == 4, there are no pairs.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= x, y &lt;= n</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def countOfPairs(self, n: int, x: int, y: int) -> List[int]:
        if abs(x - y) <= 1:
            return [2 * x for x in reversed(range(n))]
        cycle_len = abs(x - y) + 1
        n2 = n - cycle_len + 2
        res = [2 * x for x in reversed(range(n2))]
        while len(res) < n:
            res.append(0)
        res2 = [cycle_len * 2] * (cycle_len >> 1)
        if not cycle_len & 1:
            res2[-1] = cycle_len
        res2[0] -= 2
        for i in range(len(res2)):
            res[i] += res2[i]
        if x > y:
            x, y = y, x
        tail1 = x - 1
        tail2 = n - y
        for tail in (tail1, tail2):
            if not tail:
                continue
            i_mx = tail + (cycle_len >> 1)
            val_mx = 4 * min((cycle_len - 3) >> 1, tail)
            i_mx2 = i_mx - (1 - (cycle_len & 1))
            res3 = [val_mx] * i_mx
            res3[0] = 0
            res3[1] = 0
            if not cycle_len & 1:
                res3[-1] = 0
            for i, j in enumerate(range(4, val_mx, 4)):
                res3[i + 2] = j
                res3[i_mx2 - i - 1] = j
            for i in range(1, tail + 1):
                res3[i] += 2
            if not cycle_len & 1:
                mn = cycle_len >> 1
                for i in range(mn, mn + tail):
                    res3[i] += 2
            for i in range(len(res3)):
                res[i] += res3[i]
        return res
```

```java
class Solution {
    public long[] countOfPairs(int n, int x, int y) {
        --x;
        --y;
        if (x > y) {
        int temp = x;
        x = y;
        y = temp;
        }
        long[] diff = new long[n];
        for (int i = 0; i < n; ++i) {
        diff[0] += 1 + 1;
        ++diff[Math.min(Math.abs(i - x), Math.abs(i - y) + 1)];
        ++diff[Math.min(Math.abs(i - y), Math.abs(i - x) + 1)];
        --diff[Math.min(Math.abs(i - 0), Math.abs(i - y) + 1 + Math.abs(x - 0))];
        --diff[Math.min(Math.abs(i - (n - 1)),
                        Math.abs(i - x) + 1 + Math.abs(y - (n - 1)))];
        --diff[Math.max(x - i, 0) + Math.max(i - y, 0) + ((y - x) + 0) / 2];
        --diff[Math.max(x - i, 0) + Math.max(i - y, 0) + ((y - x) + 1) / 2];
        }
        for (int i = 0; i + 1 < n; ++i) {
        diff[i + 1] += diff[i];
        }
        return diff;
    }
}
```

```cpp
class Solution {
public:
  vector<long long> countOfPairs(int n, int x, int y) {
    --x, --y;
    if (x > y) {
      swap(x, y);
    }
    vector<long long> diff(n);
    for (int i = 0; i < n; ++i) {
      diff[0] += 1 + 1;
      ++diff[min(abs(i - x), abs(i - y) + 1)];
      ++diff[min(abs(i - y), abs(i - x) + 1)];
      --diff[min(abs(i - 0), abs(i - y) + 1 + abs(x - 0))];
      --diff[min(abs(i - (n - 1)), abs(i - x) + 1 + abs(y - (n - 1)))];
      --diff[max(x - i, 0) + max(i - y, 0) + ((y - x) + 0) / 2];
      --diff[max(x - i, 0) + max(i - y, 0) + ((y - x) + 1) / 2];
    }
    for (int i = 0; i + 1 < n; ++i) {
      diff[i + 1] += diff[i];
    }
    return diff;
  }
};
```

```go
func countOfPairs(n int, x int, y int) []int64 {
	if x > y {
		x, y = y, x
	}
	A := make([]int64, n)
	for i := 1; i <= n; i++ {
		A[0] += 2
		A[min(int64(i-1), int64(math.Abs(float64(i-y)))+int64(x))] -= 1
		A[min(int64(n-i), int64(math.Abs(float64(i-x)))+1+int64(n-y))] -= 1
		A[min(int64(math.Abs(float64(i-x))), int64(math.Abs(float64(y-i)))+1)] += 1
		A[min(int64(math.Abs(float64(i-x)))+1, int64(math.Abs(float64(y-i))))] += 1
		r := max(int64(x-i), 0) + max(int64(i-y), 0)
		A[r+int64((y-x+0)/2)] -= 1
		A[r+int64((y-x+1)/2)] -= 1
	}
	for i := 1; i < n; i++ {
		A[i] += A[i-1]
	}

	return A
}
```

<!-- tabs:end -->

<!-- end -->
