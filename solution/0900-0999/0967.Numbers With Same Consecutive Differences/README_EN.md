# [967. Numbers With Same Consecutive Differences](https://leetcode.com/problems/numbers-with-same-consecutive-differences)

[中文文档](/solution/0900-0999/0967.Numbers%20With%20Same%20Consecutive%20Differences/README.md)

## Description

<p>Return all <strong>non-negative</strong> integers of length <code>n</code> such that the absolute difference between every two consecutive digits is <code>k</code>.</p>

<p>Note that <strong>every</strong> number in the answer <strong>must not</strong> have leading zeros. For example, <code>01</code> has one leading zero and is invalid.</p>

<p>You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 7
<strong>Output:</strong> [181,292,707,818,929]
<strong>Explanation:</strong> Note that 070 is not a valid number, because it has leading zeroes.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, k = 1
<strong>Output:</strong> [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 9</code></li>
	<li><code>0 &lt;= k &lt;= 9</code></li>
</ul>

## Solutions

DFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numsSameConsecDiff(self, n: int, k: int) -> List[int]:
        ans = []

        def dfs(n, k, t):
            if n == 0:
                ans.append(t)
                return
            last = t % 10
            if last + k <= 9:
                dfs(n - 1, k, t * 10 + last + k)
            if last - k >= 0 and k != 0:
                dfs(n - 1, k, t * 10 + last - k)

        for i in range(1, 10):
            dfs(n - 1, k, i)
        return ans
```

### **Java**

```java
class Solution {
    public int[] numsSameConsecDiff(int n, int k) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; ++i) {
            dfs(n - 1, k, i, res);
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    private void dfs(int n, int k, int t, List<Integer> res) {
        if (n == 0) {
            res.add(t);
            return;
        }
        int last = t % 10;
        if (last + k <= 9) {
            dfs(n - 1, k, t * 10 + last + k, res);
        }
        if (last - k >= 0 && k != 0) {
            dfs(n - 1, k, t * 10 + last - k, res);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> ans;

    vector<int> numsSameConsecDiff(int n, int k) {
        for (int i = 1; i < 10; ++i)
            dfs(n - 1, k, i);
        return ans;
    }

    void dfs(int n, int k, int t) {
        if (n == 0) {
            ans.push_back(t);
            return;
        }
        int last = t % 10;
        if (last + k <= 9) dfs(n - 1, k, t * 10 + last + k);
        if (last - k >= 0 && k != 0) dfs(n - 1, k, t * 10 + last - k);
    }
};
```

### **Go**

```go
func numsSameConsecDiff(n int, k int) []int {
	var ans []int
	var dfs func(n, k, t int)
	dfs = func(n, k, t int) {
		if n == 0 {
			ans = append(ans, t)
			return
		}
		last := t % 10
		if last+k <= 9 {
			dfs(n-1, k, t*10+last+k)
		}
		if last-k >= 0 && k != 0 {
			dfs(n-1, k, t*10+last-k)
		}
	}

	for i := 1; i < 10; i++ {
		dfs(n-1, k, i)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
