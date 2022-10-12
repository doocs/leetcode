# [1553. Minimum Number of Days to Eat N Oranges](https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges)

[中文文档](/solution/1500-1599/1553.Minimum%20Number%20of%20Days%20to%20Eat%20N%20Oranges/README.md)

## Description

<p>There are <code>n</code> oranges in the kitchen and you decided to eat some of these oranges every day as follows:</p>

<ul>
	<li>Eat one orange.</li>
	<li>If the number of remaining oranges <code>n</code> is divisible by <code>2</code> then you can eat <code>n / 2</code> oranges.</li>
	<li>If the number of remaining oranges <code>n</code> is divisible by <code>3</code> then you can eat <code>2 * (n / 3)</code> oranges.</li>
</ul>

<p>You can only choose one of the actions per day.</p>

<p>Given the integer <code>n</code>, return <em>the minimum number of days to eat</em> <code>n</code> <em>oranges</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 4
<strong>Explanation:</strong> You have 10 oranges.
Day 1: Eat 1 orange,  10 - 1 = 9.  
Day 2: Eat 6 oranges, 9 - 2*(9/3) = 9 - 6 = 3. (Since 9 is divisible by 3)
Day 3: Eat 2 oranges, 3 - 2*(3/3) = 3 - 2 = 1. 
Day 4: Eat the last orange  1 - 1  = 0.
You need at least 4 days to eat the 10 oranges.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 6
<strong>Output:</strong> 3
<strong>Explanation:</strong> You have 6 oranges.
Day 1: Eat 3 oranges, 6 - 6/2 = 6 - 3 = 3. (Since 6 is divisible by 2).
Day 2: Eat 2 oranges, 3 - 2*(3/3) = 3 - 2 = 1. (Since 3 is divisible by 3)
Day 3: Eat the last orange  1 - 1  = 0.
You need at least 3 days to eat the 6 oranges.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minDays(self, n: int) -> int:
        @cache
        def dfs(n):
            if n < 2:
                return n
            return 1 + min(n % 2 + dfs(n // 2), n % 3 + dfs(n // 3))

        return dfs(n)
```

### **Java**

```java
class Solution {
    private Map<Integer, Integer> f = new HashMap<>();

    public int minDays(int n) {
        return dfs(n);
    }

    private int dfs(int n) {
        if (n < 2) {
            return n;
        }
        if (f.containsKey(n)) {
            return f.get(n);
        }
        int res = 1 + Math.min(n % 2 + dfs(n / 2), n % 3 + dfs(n / 3));
        f.put(n, res);
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    unordered_map<int, int> f;

    int minDays(int n) {
        return dfs(n);
    }

    int dfs(int n) {
        if (n < 2) return n;
        if (f.count(n)) return f[n];
        int res = 1 + min(n % 2 + dfs(n / 2), n % 3 + dfs(n / 3));
        f[n] = res;
        return res;
    }
};
```

### **Go**

```go
func minDays(n int) int {
	f := map[int]int{0: 0, 1: 1}
	var dfs func(int) int
	dfs = func(n int) int {
		if v, ok := f[n]; ok {
			return v
		}
		res := 1 + min(n%2+dfs(n/2), n%3+dfs(n/3))
		f[n] = res
		return res
	}
	return dfs(n)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
