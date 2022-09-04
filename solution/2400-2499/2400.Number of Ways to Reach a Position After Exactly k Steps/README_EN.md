# [2400. Number of Ways to Reach a Position After Exactly k Steps](https://leetcode.com/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps)

[中文文档](/solution/2400-2499/2400.Number%20of%20Ways%20to%20Reach%20a%20Position%20After%20Exactly%20k%20Steps/README.md)

## Description

<p>You are given two <strong>positive</strong> integers <code>startPos</code> and <code>endPos</code>. Initially, you are standing at position <code>startPos</code> on an <strong>infinite</strong> number line. With one step, you can move either one position to the left, or one position to the right.</p>

<p>Given a positive integer <code>k</code>, return <em>the number of <strong>different</strong> ways to reach the position </em><code>endPos</code><em> starting from </em><code>startPos</code><em>, such that you perform <strong>exactly</strong> </em><code>k</code><em> steps</em>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>Two ways are considered different if the order of the steps made is not exactly the same.</p>

<p><strong>Note</strong> that the number line includes negative integers.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> startPos = 1, endPos = 2, k = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can reach position 2 from 1 in exactly 3 steps in three ways:
- 1 -&gt; 2 -&gt; 3 -&gt; 2.
- 1 -&gt; 2 -&gt; 1 -&gt; 2.
- 1 -&gt; 0 -&gt; 1 -&gt; 2.
It can be proven that no other way is possible, so we return 3.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> startPos = 2, endPos = 5, k = 10
<strong>Output:</strong> 0
<strong>Explanation:</strong> It is impossible to reach position 5 from position 2 in exactly 10 steps.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= startPos, endPos, k &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfWays(self, startPos: int, endPos: int, k: int) -> int:
        @cache
        def dfs(d, k):
            if k < 0 or abs(d) > k:
                return 0
            if k == 0:
                return d == 0
            res = dfs(d - 1, k - 1) + dfs(d + 1, k - 1)
            return res % (10**9 + 7)

        return dfs(abs(startPos - endPos), k)
```

### **Java**

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;
    private int[][] f = new int[3010][3010];
    private int j;

    public int numberOfWays(int startPos, int endPos, int k) {
        startPos += 1000;
        endPos += 1000;
        for (var e : f) {
            Arrays.fill(e, -1);
        }
        j = endPos;
        return dfs(startPos, k);
    }

    private int dfs(int i, int k) {
        if (Math.abs(i - j) > k) {
            return 0;
        }
        if (f[i][k] != -1) {
            return f[i][k];
        }
        if (k == 0) {
            return i == j ? 1 : 0;
        }
        int res = dfs(i + 1, k - 1) + dfs(i - 1, k - 1);
        res %= MOD;
        f[i][k] = res;
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    unordered_map<int, int> f;
    int mod = 1e9 + 7;
    int j;

    int numberOfWays(int startPos, int endPos, int k) {
        j = endPos;
        return dfs(startPos, k);
    }

    int dfs(int i, int k) {
        if (f.count(i * 10000 + k)) return f[i * 10000 + k];
        if (abs(i - j) > k) return 0;
        if (k == 0) return i == j;
        int res = dfs(i - 1, k - 1) + dfs(i + 1, k - 1);
        res %= mod;
        f[i * 10000 + k] = res;
        return res;
    }
};
```

### **Go**

```go
func numberOfWays(startPos int, endPos int, k int) int {
	f := map[int]int{}
	var dfs func(i, k int) int
	dfs = func(i, k int) int {
		if abs(i-endPos) > k {
			return 0
		}
		if k == 0 {
			if i == endPos {
				return 1
			}
			return 0
		}
		if v, ok := f[i*10000+k]; ok {
			return v
		}
		res := dfs(i+1, k-1) + dfs(i-1, k-1)
		res %= 1e9 + 7
		f[i*10000+k] = res
		return res
	}
	return dfs(startPos, k)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
