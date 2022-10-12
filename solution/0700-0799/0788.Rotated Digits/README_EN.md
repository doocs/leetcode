# [788. Rotated Digits](https://leetcode.com/problems/rotated-digits)

[中文文档](/solution/0700-0799/0788.Rotated%20Digits/README.md)

## Description

<p>An integer <code>x</code> is a <strong>good</strong> if after rotating each digit individually by 180 degrees, we get a valid number that is different from <code>x</code>. Each digit must be rotated - we cannot choose to leave it alone.</p>

<p>A number is valid if each digit remains a digit after rotation. For example:</p>

<ul>
	<li><code>0</code>, <code>1</code>, and <code>8</code> rotate to themselves,</li>
	<li><code>2</code> and <code>5</code> rotate to each other (in this case they are rotated in a different direction, in other words, <code>2</code> or <code>5</code> gets mirrored),</li>
	<li><code>6</code> and <code>9</code> rotate to each other, and</li>
	<li>the rest of the numbers do not rotate to any other number and become invalid.</li>
</ul>

<p>Given an integer <code>n</code>, return <em>the number of <strong>good</strong> integers in the range </em><code>[1, n]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def rotatedDigits(self, n: int) -> int:
        def check(x):
            y, t = 0, x
            k = 1
            while t:
                v = t % 10
                if d[v] == -1:
                    return False
                y = d[v] * k + y
                k *= 10
                t //= 10
            return x != y

        d = [0, 1, 5, -1, -1, 2, 9, -1, 8, 6]
        return sum(check(i) for i in range(1, n + 1))
```

```python
class Solution:
    def rotatedDigits(self, n: int) -> int:
        @cache
        def dfs(pos, ok, limit):
            if pos <= 0:
                return ok
            up = a[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                if i in (0, 1, 8):
                    ans += dfs(pos - 1, ok, limit and i == up)
                if i in (2, 5, 6, 9):
                    ans += dfs(pos - 1, 1, limit and i == up)
            return ans

        a = [0] * 6
        l = 1
        while n:
            a[l] = n % 10
            n //= 10
            l += 1
        return dfs(l, 0, True)
```

### **Java**

```java
class Solution {
    private int[] d = new int[] {0, 1, 5, -1, -1, 2, 9, -1, 8, 6};

    public int rotatedDigits(int n) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            if (check(i)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(int x) {
        int y = 0, t = x;
        int k = 1;
        while (t > 0) {
            int v = t % 10;
            if (d[v] == -1) {
                return false;
            }
            y = d[v] * k + y;
            k *= 10;
            t /= 10;
        }
        return x != y;
    }
}
```

```java
class Solution {
    private int[] a = new int[6];
    private int[][] dp = new int[6][2];

    public int rotatedDigits(int n) {
        int len = 0;
        for (var e : dp) {
            Arrays.fill(e, -1);
        }
        while (n > 0) {
            a[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, 0, true);
    }

    private int dfs(int pos, int ok, boolean limit) {
        if (pos <= 0) {
            return ok;
        }
        if (!limit && dp[pos][ok] != -1) {
            return dp[pos][ok];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if (i == 0 || i == 1 || i == 8) {
                ans += dfs(pos - 1, ok, limit && i == up);
            }
            if (i == 2 || i == 5 || i == 6 || i == 9) {
                ans += dfs(pos - 1, 1, limit && i == up);
            }
        }
        if (!limit) {
            dp[pos][ok] = ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const vector<int> d = {0, 1, 5, -1, -1, 2, 9, -1, 8, 6};

    int rotatedDigits(int n) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans += check(i);
        }
        return ans;
    }

    bool check(int x) {
        int y = 0, t = x;
        int k = 1;
        while (t) {
            int v = t % 10;
            if (d[v] == -1) {
                return false;
            }
            y = d[v] * k + y;
            k *= 10;
            t /= 10;
        }
        return x != y;
    }
};
```

```cpp
class Solution {
public:
    int a[6];
    int dp[6][2];

    int rotatedDigits(int n) {
        memset(dp, -1, sizeof dp);
        int len = 0;
        while (n) {
            a[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, 0, true);
    }

    int dfs(int pos, int ok, bool limit) {
        if (pos <= 0) {
            return ok;
        }
        if (!limit && dp[pos][ok] != -1) {
            return dp[pos][ok];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if (i == 0 || i == 1 || i == 8) {
                ans += dfs(pos - 1, ok, limit && i == up);
            }
            if (i == 2 || i == 5 || i == 6 || i == 9) {
                ans += dfs(pos - 1, 1, limit && i == up);
            }
        }
        if (!limit) {
            dp[pos][ok] = ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func rotatedDigits(n int) int {
	d := []int{0, 1, 5, -1, -1, 2, 9, -1, 8, 6}
	check := func(x int) bool {
		y, t := 0, x
		k := 1
		for ; t > 0; t /= 10 {
			v := t % 10
			if d[v] == -1 {
				return false
			}
			y = d[v]*k + y
			k *= 10
		}
		return x != y
	}
	ans := 0
	for i := 1; i <= n; i++ {
		if check(i) {
			ans++
		}
	}
	return ans
}
```

```go
func rotatedDigits(n int) int {
	a := make([]int, 6)
	dp := make([][2]int, 6)
	for i := range a {
		dp[i] = [2]int{-1, -1}
	}
	l := 0
	for n > 0 {
		l++
		a[l] = n % 10
		n /= 10
	}

	var dfs func(int, int, bool) int
	dfs = func(pos, ok int, limit bool) int {
		if pos <= 0 {
			return ok
		}
		if !limit && dp[pos][ok] != -1 {
			return dp[pos][ok]
		}
		up := 9
		if limit {
			up = a[pos]
		}
		ans := 0
		for i := 0; i <= up; i++ {
			if i == 0 || i == 1 || i == 8 {
				ans += dfs(pos-1, ok, limit && i == up)
			}
			if i == 2 || i == 5 || i == 6 || i == 9 {
				ans += dfs(pos-1, 1, limit && i == up)
			}
		}
		if !limit {
			dp[pos][ok] = ans
		}
		return ans
	}

	return dfs(l, 0, true)
}
```

### **...**

```

```

<!-- tabs:end -->
