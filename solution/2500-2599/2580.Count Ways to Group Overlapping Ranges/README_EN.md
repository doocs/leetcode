# [2580. Count Ways to Group Overlapping Ranges](https://leetcode.com/problems/count-ways-to-group-overlapping-ranges)

[中文文档](/solution/2500-2599/2580.Count%20Ways%20to%20Group%20Overlapping%20Ranges/README.md)

## Description

<p>You are given a 2D integer array <code>ranges</code> where <code>ranges[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> denotes that all integers between <code>start<sub>i</sub></code> and <code>end<sub>i</sub></code> (both <strong>inclusive</strong>) are contained in the <code>i<sup>th</sup></code> range.</p>

<p>You are to split <code>ranges</code> into <strong>two</strong> (possibly empty) groups such that:</p>

<ul>
	<li>Each range belongs to exactly one group.</li>
	<li>Any two <strong>overlapping</strong> ranges must belong to the <strong>same</strong> group.</li>
</ul>

<p>Two ranges are said to be <strong>overlapping</strong>&nbsp;if there exists at least <strong>one</strong> integer that is present in both ranges.</p>

<ul>
	<li>For example, <code>[1, 3]</code> and <code>[2, 5]</code> are overlapping because <code>2</code> and <code>3</code> occur in both ranges.</li>
</ul>

<p>Return <em>the <strong>total number</strong> of ways to split</em> <code>ranges</code> <em>into two groups</em>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> ranges = [[6,10],[5,15]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
The two ranges are overlapping, so they must be in the same group.
Thus, there are two possible ways:
- Put both the ranges together in group 1.
- Put both the ranges together in group 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> ranges = [[1,3],[10,20],[2,5],[4,8]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> 
Ranges [1,3], and [2,5] are overlapping. So, they must be in the same group.
Again, ranges [2,5] and [4,8] are also overlapping. So, they must also be in the same group. 
Thus, there are four possible ways to group them:
- All the ranges in group 1.
- All the ranges in group 2.
- Ranges [1,3], [2,5], and [4,8] in group 1 and [10,20] in group 2.
- Ranges [1,3], [2,5], and [4,8] in group 2 and [10,20] in group 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= ranges.length &lt;= 10<sup>5</sup></code></li>
	<li><code>ranges[i].length == 2</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countWays(self, ranges: List[List[int]]) -> int:
        ranges.sort()
        cnt, mx = 0, -1
        for start, end in ranges:
            if start > mx:
                cnt += 1
            mx = max(mx, end)
        mod = 10**9 + 7
        return pow(2, cnt, mod)
```

```python
class Solution:
    def countWays(self, ranges: List[List[int]]) -> int:
        ranges.sort()
        mx = -1
        mod = 10**9 + 7
        ans = 1
        for start, end in ranges:
            if start > mx:
                ans = ans * 2 % mod
            mx = max(mx, end)
        return ans
```

### **Java**

```java
class Solution {
    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);
        int cnt = 0, mx = -1;
        for (int[] e : ranges) {
            if (e[0] > mx) {
                ++cnt;
            }
            mx = Math.max(mx, e[1]);
        }
        return qmi(2, cnt, (int) 1e9 + 7);
    }

    int qmi(long a, long k, int p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return (int) res;
    }
}
```

```java
class Solution {
    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);
        int mx = -1;
        int ans = 1;
        final int mod = (int) 1e9 + 7;
        for (int[] e : ranges) {
            if (e[0] > mx) {
                ans = ans * 2 % mod;
            }
            mx = Math.max(mx, e[1]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countWays(vector<vector<int>>& ranges) {
        sort(ranges.begin(), ranges.end());
        int cnt = 0, mx = -1;
        for (auto& e : ranges) {
            cnt += e[0] > mx;
            mx = max(mx, e[1]);
        }
        return qmi(2, cnt, 1e9 + 7);
    }

    int qmi(long a, long k, int p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }
};
```

```cpp
class Solution {
public:
    int countWays(vector<vector<int>>& ranges) {
        sort(ranges.begin(), ranges.end());
        int ans = 1, mx = -1;
        const int mod = 1e9 + 7;
        for (auto& e : ranges) {
            if (e[0] > mx) {
                ans = ans * 2 % mod;
            }
            mx = max(mx, e[1]);
        }
        return ans;
    }
};
```

### **Go**

```go
func countWays(ranges [][]int) int {
	sort.Slice(ranges, func(i, j int) bool { return ranges[i][0] < ranges[j][0] })
	cnt, mx := 0, -1
	for _, e := range ranges {
		if e[0] > mx {
			cnt++
		}
		if mx < e[1] {
			mx = e[1]
		}
	}
	return qmi(2, cnt, 1e9+7)
}

func qmi(a, k, p int) int {
	res := 1
	for k != 0 {
		if k&1 == 1 {
			res = res * a % p
		}
		k >>= 1
		a = a * a % p
	}
	return res
}
```

```go
func countWays(ranges [][]int) int {
	sort.Slice(ranges, func(i, j int) bool { return ranges[i][0] < ranges[j][0] })
	ans, mx := 1, -1
	const mod = 1e9 + 7
	for _, e := range ranges {
		if e[0] > mx {
			ans = ans * 2 % mod
		}
		if mx < e[1] {
			mx = e[1]
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function countWays(ranges: number[][]): number {
    ranges.sort((a, b) => a[0] - b[0]);
    let mx = -1;
    let ans = 1;
    const mod = 10 ** 9 + 7;
    for (const [start, end] of ranges) {
        if (start > mx) {
            ans = (ans * 2) % mod;
        }
        mx = Math.max(mx, end);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
