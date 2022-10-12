# [902. Numbers At Most N Given Digit Set](https://leetcode.com/problems/numbers-at-most-n-given-digit-set)

[中文文档](/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README.md)

## Description

<p>Given an array of <code>digits</code> which is sorted in <strong>non-decreasing</strong> order. You can write numbers using each <code>digits[i]</code> as many times as we want. For example, if <code>digits = [&#39;1&#39;,&#39;3&#39;,&#39;5&#39;]</code>, we may write numbers such as <code>&#39;13&#39;</code>, <code>&#39;551&#39;</code>, and <code>&#39;1351315&#39;</code>.</p>

<p>Return <em>the number of positive integers that can be generated </em>that are less than or equal to a given integer <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> digits = [&quot;1&quot;,&quot;3&quot;,&quot;5&quot;,&quot;7&quot;], n = 100
<strong>Output:</strong> 20
<strong>Explanation: </strong>
The 20 numbers that can be written are:
1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> digits = [&quot;1&quot;,&quot;4&quot;,&quot;9&quot;], n = 1000000000
<strong>Output:</strong> 29523
<strong>Explanation: </strong>
We can write 3 one digit numbers, 9 two digit numbers, 27 three digit numbers,
81 four digit numbers, 243 five digit numbers, 729 six digit numbers,
2187 seven digit numbers, 6561 eight digit numbers, and 19683 nine digit numbers.
In total, this is 29523 integers that can be written using the digits array.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> digits = [&quot;7&quot;], n = 8
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= digits.length &lt;= 9</code></li>
	<li><code>digits[i].length == 1</code></li>
	<li><code>digits[i]</code> is a digit from&nbsp;<code>&#39;1&#39;</code>&nbsp;to <code>&#39;9&#39;</code>.</li>
	<li>All the values in&nbsp;<code>digits</code> are <strong>unique</strong>.</li>
	<li><code>digits</code> is sorted in&nbsp;<strong>non-decreasing</strong> order.</li>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def atMostNGivenDigitSet(self, digits: List[str], n: int) -> int:
        @cache
        def dfs(pos, lead, limit):
            if pos <= 0:
                return lead == False
            up = a[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                if i == 0 and lead:
                    ans += dfs(pos - 1, lead, limit and i == up)
                elif i in s:
                    ans += dfs(pos - 1, False, limit and i == up)
            return ans

        l = 0
        a = [0] * 12
        s = {int(d) for d in digits}
        while n:
            l += 1
            a[l] = n % 10
            n //= 10
        return dfs(l, True, True)
```

### **Java**

```java
class Solution {
    private int[] a = new int[12];
    private int[][] dp = new int[12][2];
    private Set<Integer> s = new HashSet<>();

    public int atMostNGivenDigitSet(String[] digits, int n) {
        for (var e : dp) {
            Arrays.fill(e, -1);
        }
        for (String d : digits) {
            s.add(Integer.parseInt(d));
        }
        int len = 0;
        while (n > 0) {
            a[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, 1, true);
    }

    private int dfs(int pos, int lead, boolean limit) {
        if (pos <= 0) {
            return lead ^ 1;
        }
        if (!limit && lead != 1 && dp[pos][lead] != -1) {
            return dp[pos][lead];
        }
        int ans = 0;
        int up = limit ? a[pos] : 9;
        for (int i = 0; i <= up; ++i) {
            if (i == 0 && lead == 1) {
                ans += dfs(pos - 1, lead, limit && i == up);
            } else if (s.contains(i)) {
                ans += dfs(pos - 1, 0, limit && i == up);
            }
        }
        if (!limit && lead == 0) {
            dp[pos][lead] = ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int a[12];
    int dp[12][2];
    unordered_set<int> s;

    int atMostNGivenDigitSet(vector<string>& digits, int n) {
        memset(dp, -1, sizeof dp);
        for (auto& d : digits) {
            s.insert(stoi(d));
        }
        int len = 0;
        while (n) {
            a[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, 1, true);
    }

    int dfs(int pos, int lead, bool limit) {
        if (pos <= 0) {
            return lead ^ 1;
        }
        if (!limit && !lead && dp[pos][lead] != -1) {
            return dp[pos][lead];
        }
        int ans = 0;
        int up = limit ? a[pos] : 9;
        for (int i = 0; i <= up; ++i) {
            if (i == 0 && lead) {
                ans += dfs(pos - 1, lead, limit && i == up);
            } else if (s.count(i)) {
                ans += dfs(pos - 1, 0, limit && i == up);
            }
        }
        if (!limit && !lead) {
            dp[pos][lead] = ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func atMostNGivenDigitSet(digits []string, n int) int {
	s := map[int]bool{}
	for _, d := range digits {
		i, _ := strconv.Atoi(d)
		s[i] = true
	}
	a := make([]int, 12)
	dp := make([][2]int, 12)
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
	dfs = func(pos, lead int, limit bool) int {
		if pos <= 0 {
			return lead ^ 1
		}
		if !limit && lead == 0 && dp[pos][lead] != -1 {
			return dp[pos][lead]
		}
		up := 9
		if limit {
			up = a[pos]
		}
		ans := 0
		for i := 0; i <= up; i++ {
			if i == 0 && lead == 1 {
				ans += dfs(pos-1, lead, limit && i == up)
			} else if s[i] {
				ans += dfs(pos-1, 0, limit && i == up)
			}
		}
		if !limit {
			dp[pos][lead] = ans
		}
		return ans
	}
	return dfs(l, 1, true)
}
```

### **...**

```

```

<!-- tabs:end -->
