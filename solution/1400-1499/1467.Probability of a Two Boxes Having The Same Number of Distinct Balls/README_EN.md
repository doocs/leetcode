# [1467. Probability of a Two Boxes Having The Same Number of Distinct Balls](https://leetcode.com/problems/probability-of-a-two-boxes-having-the-same-number-of-distinct-balls)

[中文文档](/solution/1400-1499/1467.Probability%20of%20a%20Two%20Boxes%20Having%20The%20Same%20Number%20of%20Distinct%20Balls/README.md)

<!-- tags:Array,Math,Dynamic Programming,Backtracking,Combinatorics,Probability and Statistics -->

## Description

<p>Given <code>2n</code> balls of <code>k</code> distinct colors. You will be given an integer array <code>balls</code> of size <code>k</code> where <code>balls[i]</code> is the number of balls of color <code>i</code>.</p>

<p>All the balls will be <strong>shuffled uniformly at random</strong>, then we will distribute the first <code>n</code> balls to the first box and the remaining <code>n</code> balls to the other box (Please read the explanation of the second example carefully).</p>

<p>Please note that the two boxes are considered different. For example, if we have two balls of colors <code>a</code> and <code>b</code>, and two boxes <code>[]</code> and <code>()</code>, then the distribution <code>[a] (b)</code> is considered different than the distribution <code>[b] (a) </code>(Please read the explanation of the first example carefully).</p>

<p>Return<em> the probability</em> that the two boxes have the same number of distinct balls. Answers within <code>10<sup>-5</sup></code> of the actual value will be accepted as correct.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> balls = [1,1]
<strong>Output:</strong> 1.00000
<strong>Explanation:</strong> Only 2 ways to divide the balls equally:
- A ball of color 1 to box 1 and a ball of color 2 to box 2
- A ball of color 2 to box 1 and a ball of color 1 to box 2
In both ways, the number of distinct colors in each box is equal. The probability is 2/2 = 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> balls = [2,1,1]
<strong>Output:</strong> 0.66667
<strong>Explanation:</strong> We have the set of balls [1, 1, 2, 3]
This set of balls will be shuffled randomly and we may have one of the 12 distinct shuffles with equal probability (i.e. 1/12):
[1,1 / 2,3], [1,1 / 3,2], [1,2 / 1,3], [1,2 / 3,1], [1,3 / 1,2], [1,3 / 2,1], [2,1 / 1,3], [2,1 / 3,1], [2,3 / 1,1], [3,1 / 1,2], [3,1 / 2,1], [3,2 / 1,1]
After that, we add the first two balls to the first box and the second two balls to the second box.
We can see that 8 of these 12 possible random distributions have the same number of distinct colors of balls in each box.
Probability is 8/12 = 0.66667
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> balls = [1,2,1,2]
<strong>Output:</strong> 0.60000
<strong>Explanation:</strong> The set of balls is [1, 2, 2, 3, 4, 4]. It is hard to display all the 180 possible random shuffles of this set but it is easy to check that 108 of them will have the same number of distinct colors in each box.
Probability = 108 / 180 = 0.6
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= balls.length &lt;= 8</code></li>
	<li><code>1 &lt;= balls[i] &lt;= 6</code></li>
	<li><code>sum(balls)</code> is even.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def getProbability(self, balls: List[int]) -> float:
        @cache
        def dfs(i: int, j: int, diff: int) -> float:
            if i >= k:
                return 1 if j == 0 and diff == 0 else 0
            if j < 0:
                return 0
            ans = 0
            for x in range(balls[i] + 1):
                y = 1 if x == balls[i] else (-1 if x == 0 else 0)
                ans += dfs(i + 1, j - x, diff + y) * comb(balls[i], x)
            return ans

        n = sum(balls) >> 1
        k = len(balls)
        return dfs(0, n, 0) / comb(n << 1, n)
```

```java
class Solution {
    private int n;
    private long[][] c;
    private int[] balls;
    private Map<List<Integer>, Long> f = new HashMap<>();

    public double getProbability(int[] balls) {
        int mx = 0;
        for (int x : balls) {
            n += x;
            mx = Math.max(mx, x);
        }
        n >>= 1;
        this.balls = balls;
        int m = Math.max(mx, n << 1);
        c = new long[m + 1][m + 1];
        for (int i = 0; i <= m; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
        return dfs(0, n, 0) * 1.0 / c[n << 1][n];
    }

    private long dfs(int i, int j, int diff) {
        if (i >= balls.length) {
            return j == 0 && diff == 0 ? 1 : 0;
        }
        if (j < 0) {
            return 0;
        }
        List<Integer> key = List.of(i, j, diff);
        if (f.containsKey(key)) {
            return f.get(key);
        }
        long ans = 0;
        for (int x = 0; x <= balls[i]; ++x) {
            int y = x == balls[i] ? 1 : (x == 0 ? -1 : 0);
            ans += dfs(i + 1, j - x, diff + y) * c[balls[i]][x];
        }
        f.put(key, ans);
        return ans;
    }
}
```

```cpp
class Solution {
public:
    double getProbability(vector<int>& balls) {
        int n = accumulate(balls.begin(), balls.end(), 0) / 2;
        int mx = *max_element(balls.begin(), balls.end());
        int m = max(mx, n << 1);
        long long c[m + 1][m + 1];
        memset(c, 0, sizeof(c));
        for (int i = 0; i <= m; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
        int k = balls.size();
        long long f[k][n + 1][k << 1 | 1];
        memset(f, -1, sizeof(f));
        function<long long(int, int, int)> dfs = [&](int i, int j, int diff) -> long long {
            if (i >= k) {
                return j == 0 && diff == k ? 1 : 0;
            }
            if (j < 0) {
                return 0;
            }
            if (f[i][j][diff] != -1) {
                return f[i][j][diff];
            }
            long long ans = 0;
            for (int x = 0; x <= balls[i]; ++x) {
                int y = x == balls[i] ? 1 : (x == 0 ? -1 : 0);
                ans += dfs(i + 1, j - x, diff + y) * c[balls[i]][x];
            }
            return f[i][j][diff] = ans;
        };
        return dfs(0, n, k) * 1.0 / c[n << 1][n];
    }
};
```

```go
func getProbability(balls []int) float64 {
	n, mx := 0, 0
	for _, x := range balls {
		n += x
		mx = max(mx, x)
	}
	n >>= 1
	m := max(mx, n<<1)
	c := make([][]int, m+1)
	for i := range c {
		c[i] = make([]int, m+1)
	}
	for i := 0; i <= m; i++ {
		c[i][0] = 1
		for j := 1; j <= i; j++ {
			c[i][j] = c[i-1][j-1] + c[i-1][j]
		}
	}
	k := len(balls)
	f := make([][][]int, k)
	for i := range f {
		f[i] = make([][]int, n+1)
		for j := range f[i] {
			f[i][j] = make([]int, k<<1|1)
			for h := range f[i][j] {
				f[i][j][h] = -1
			}
		}
	}
	var dfs func(int, int, int) int
	dfs = func(i, j, diff int) int {
		if i >= k {
			if j == 0 && diff == k {
				return 1
			}
			return 0
		}
		if j < 0 {
			return 0
		}
		if f[i][j][diff] != -1 {
			return f[i][j][diff]
		}
		ans := 0
		for x := 0; x <= balls[i]; x++ {
			y := 1
			if x != balls[i] {
				if x == 0 {
					y = -1
				} else {
					y = 0
				}
			}
			ans += dfs(i+1, j-x, diff+y) * c[balls[i]][x]
		}
		f[i][j][diff] = ans
		return ans
	}
	return float64(dfs(0, n, k)) / float64(c[n<<1][n])
}
```

```ts
function getProbability(balls: number[]): number {
    const n = balls.reduce((a, b) => a + b, 0) >> 1;
    const mx = Math.max(...balls);
    const m = Math.max(mx, n << 1);
    const c: number[][] = Array(m + 1)
        .fill(0)
        .map(() => Array(m + 1).fill(0));
    for (let i = 0; i <= m; ++i) {
        c[i][0] = 1;
        for (let j = 1; j <= i; ++j) {
            c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
        }
    }
    const k = balls.length;
    const f: number[][][] = Array(k)
        .fill(0)
        .map(() =>
            Array(n + 1)
                .fill(0)
                .map(() => Array((k << 1) | 1).fill(-1)),
        );
    const dfs = (i: number, j: number, diff: number): number => {
        if (i >= k) {
            return j === 0 && diff === k ? 1 : 0;
        }
        if (j < 0) {
            return 0;
        }
        if (f[i][j][diff] !== -1) {
            return f[i][j][diff];
        }
        let ans = 0;
        for (let x = 0; x <= balls[i]; ++x) {
            const y = x === balls[i] ? 1 : x === 0 ? -1 : 0;
            ans += dfs(i + 1, j - x, diff + y) * c[balls[i]][x];
        }
        return (f[i][j][diff] = ans);
    };
    return dfs(0, n, k) / c[n << 1][n];
}
```

<!-- tabs:end -->

<!-- end -->
