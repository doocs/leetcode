---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/17.06.Number%20Of%202s%20In%20Range/README_EN.md
---

<!-- problem:start -->

# [17.06. Number Of 2s In Range](https://leetcode.cn/problems/number-of-2s-in-range-lcci)

[中文文档](/lcci/17.06.Number%20Of%202s%20In%20Range/README.md)

## Description

<!-- description:start -->

<p>Write a method to count the number of 2s that appear in all the numbers between 0&nbsp;and n (inclusive).</p>
<p><strong>Example:</strong></p>
<pre>

<strong>Input: </strong>25

<strong>Output: </strong>9

<strong>Explanation: </strong>(2, 12, 20, 21, 22, 23, 24, 25)(Note that 22 counts for two 2s.)</pre>

<p>Note:</p>
<ul>
	<li><code>n &lt;= 10^9</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Digit DP

This problem is essentially about finding the number of occurrences of the digit $2$ in the given interval $[l,..r]$. The count is related to the number of digits and the digit at each position. We can use the idea of Digit DP to solve this problem. In Digit DP, the size of the number has little impact on the complexity.

For the interval $[l,..r]$, we usually transform it into $[1,..r]$ and then subtract $[1,..l - 1]$, i.e.,

$$
ans = \sum_{i=1}^{r} ans_i -  \sum_{i=1}^{l-1} ans_i
$$

However, for this problem, we only need to find the value in the interval $[1,..r]$.

Here, we use memoization to implement Digit DP. We start from the top and search down to the bottom to get the number of schemes, then return the answer layer by layer and accumulate it, finally getting the final answer from the starting point of the search.

The basic steps are as follows:

1. Convert the number $n$ into an int array $a$, where $a[1]$ is the least significant digit, and $a[len]$ is the most significant digit.
2. Design the function $dfs()$ based on the problem information. For this problem, we define $dfs(pos, cnt, limit)$, and the answer is $dfs(len, 0, true)$.

Where:

-   `pos` represents the number of digits, starting from the least significant digit or the first digit, usually depending on the digit construction property of the problem. For this problem, we choose to start from the most significant digit, so the initial value of `pos` is `len`.
-   `cnt` represents the number of $2$s in the current number.
-   `limit` represents the restriction on the digits that can be filled. If there is no restriction, you can choose $[0,1,..9]$, otherwise, you can only choose $[0,..a[pos]]$. If `limit` is `true` and the maximum value has been reached, then the next `limit` is also `true`. If `limit` is `true` but the maximum value has not been reached, or if `limit` is `false`, then the next `limit` is `false`.

For details on the implementation of the function, please refer to the code below.

The time complexity is $O(\log n)$.

Similar problems:

-   [233. Number of Digit One](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0233.Number%20of%20Digit%20One/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOf2sInRange(self, n: int) -> int:
        @cache
        def dfs(pos, cnt, limit):
            if pos <= 0:
                return cnt
            up = a[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                ans += dfs(pos - 1, cnt + (i == 2), limit and i == up)
            return ans

        a = [0] * 12
        l = 0
        while n:
            l += 1
            a[l] = n % 10
            n //= 10
        return dfs(l, 0, True)
```

#### Java

```java
class Solution {
    private int[] a = new int[12];
    private int[][] dp = new int[12][12];

    public int numberOf2sInRange(int n) {
        int len = 0;
        while (n > 0) {
            a[++len] = n % 10;
            n /= 10;
        }
        for (var e : dp) {
            Arrays.fill(e, -1);
        }
        return dfs(len, 0, true);
    }

    private int dfs(int pos, int cnt, boolean limit) {
        if (pos <= 0) {
            return cnt;
        }
        if (!limit && dp[pos][cnt] != -1) {
            return dp[pos][cnt];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            ans += dfs(pos - 1, cnt + (i == 2 ? 1 : 0), limit && i == up);
        }
        if (!limit) {
            dp[pos][cnt] = ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int a[12];
    int dp[12][12];

    int numberOf2sInRange(int n) {
        int len = 0;
        while (n) {
            a[++len] = n % 10;
            n /= 10;
        }
        memset(dp, -1, sizeof dp);
        return dfs(len, 0, true);
    }

    int dfs(int pos, int cnt, bool limit) {
        if (pos <= 0) {
            return cnt;
        }
        if (!limit && dp[pos][cnt] != -1) {
            return dp[pos][cnt];
        }
        int ans = 0;
        int up = limit ? a[pos] : 9;
        for (int i = 0; i <= up; ++i) {
            ans += dfs(pos - 1, cnt + (i == 2), limit && i == up);
        }
        if (!limit) {
            dp[pos][cnt] = ans;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOf2sInRange(n int) int {
	a := make([]int, 12)
	dp := make([][]int, 12)
	for i := range dp {
		dp[i] = make([]int, 12)
		for j := range dp[i] {
			dp[i][j] = -1
		}
	}
	l := 0
	for n > 0 {
		l++
		a[l] = n % 10
		n /= 10
	}
	var dfs func(int, int, bool) int
	dfs = func(pos, cnt int, limit bool) int {
		if pos <= 0 {
			return cnt
		}
		if !limit && dp[pos][cnt] != -1 {
			return dp[pos][cnt]
		}
		up := 9
		if limit {
			up = a[pos]
		}
		ans := 0
		for i := 0; i <= up; i++ {
			t := cnt
			if i == 2 {
				t++
			}
			ans += dfs(pos-1, t, limit && i == up)
		}
		if !limit {
			dp[pos][cnt] = ans
		}
		return ans
	}
	return dfs(l, 0, true)
}
```

#### Swift

```swift
class Solution {
    private var a = [Int](repeating: 0, count: 12)
    private var dp = [[Int]](repeating: [Int](repeating: -1, count: 12), count: 12)

    func numberOf2sInRange(_ n: Int) -> Int {
        var n = n
        var len = 0
        while n > 0 {
            len += 1
            a[len] = n % 10
            n /= 10
        }
        for i in 0..<12 {
            dp[i] = [Int](repeating: -1, count: 12)
        }
        return dfs(len, 0, true)
    }

    private func dfs(_ pos: Int, _ cnt: Int, _ limit: Bool) -> Int {
        if pos <= 0 {
            return cnt
        }
        if !limit && dp[pos][cnt] != -1 {
            return dp[pos][cnt]
        }
        let up = limit ? a[pos] : 9
        var ans = 0
        for i in 0...up {
            ans += dfs(pos - 1, cnt + (i == 2 ? 1 : 0), limit && i == up)
        }
        if !limit {
            dp[pos][cnt] = ans
        }
        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
