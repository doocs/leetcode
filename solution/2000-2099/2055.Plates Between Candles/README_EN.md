# [2055. Plates Between Candles](https://leetcode.com/problems/plates-between-candles)

[中文文档](/solution/2000-2099/2055.Plates%20Between%20Candles/README.md)

## Description

<p>There is a long table with a line of plates and candles arranged on top of it. You are given a <strong>0-indexed</strong> string <code>s</code> consisting of characters <code>&#39;*&#39;</code> and <code>&#39;|&#39;</code> only, where a <code>&#39;*&#39;</code> represents a <strong>plate</strong> and a <code>&#39;|&#39;</code> represents a <strong>candle</strong>.</p>

<p>You are also given a <strong>0-indexed</strong> 2D integer array <code>queries</code> where <code>queries[i] = [left<sub>i</sub>, right<sub>i</sub>]</code> denotes the <strong>substring</strong> <code>s[left<sub>i</sub>...right<sub>i</sub>]</code> (<strong>inclusive</strong>). For each query, you need to find the <strong>number</strong> of plates <strong>between candles</strong> that are <strong>in the substring</strong>. A plate is considered <strong>between candles</strong> if there is at least one candle to its left <strong>and</strong> at least one candle to its right <strong>in the substring</strong>.</p>

<ul>
	<li>For example, <code>s = &quot;||**||**|*&quot;</code>, and a query <code>[3, 8]</code> denotes the substring <code>&quot;*||<strong><u>**</u></strong>|&quot;</code>. The number of plates between candles in this substring is <code>2</code>, as each of the two plates has at least one candle <strong>in the substring</strong> to its left <strong>and</strong> right.</li>
</ul>

<p>Return <em>an integer array</em> <code>answer</code> <em>where</em> <code>answer[i]</code> <em>is the answer to the</em> <code>i<sup>th</sup></code> <em>query</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="ex-1" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2055.Plates%20Between%20Candles/images/ex-1.png" style="width: 400px; height: 134px;" />
<pre>
<strong>Input:</strong> s = &quot;**|**|***|&quot;, queries = [[2,5],[5,9]]
<strong>Output:</strong> [2,3]
<strong>Explanation:</strong>
- queries[0] has two plates between candles.
- queries[1] has three plates between candles.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="ex-2" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2055.Plates%20Between%20Candles/images/ex-2.png" style="width: 600px; height: 193px;" />
<pre>
<strong>Input:</strong> s = &quot;***|**|*****|**||**|*&quot;, queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
<strong>Output:</strong> [9,0,0,0,0]
<strong>Explanation:</strong>
- queries[0] has nine plates between candles.
- The other queries have zero plates between candles.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of <code>&#39;*&#39;</code> and <code>&#39;|&#39;</code> characters.</li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= left<sub>i</sub> &lt;= right<sub>i</sub> &lt; s.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def platesBetweenCandles(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        presum = [0] * (n + 1)
        for i, c in enumerate(s):
            presum[i + 1] = presum[i] + (c == '*')

        left, right = [0] * n, [0] * n
        l = r = -1
        for i, c in enumerate(s):
            if c == '|':
                l = i
            left[i] = l
        for i in range(n - 1, -1, -1):
            if s[i] == '|':
                r = i
            right[i] = r

        ans = [0] * len(queries)
        for k, (l, r) in enumerate(queries):
            i, j = right[l], left[r]
            if i >= 0 and j >= 0 and i < j:
                ans[k] = presum[j] - presum[i + 1]
        return ans
```

### **Java**

```java
class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + (s.charAt(i) == '*' ? 1 : 0);
        }
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0, l = -1; i < n; ++i) {
            if (s.charAt(i) == '|') {
                l = i;
            }
            left[i] = l;
        }
        for (int i = n - 1, r = -1; i >= 0; --i) {
            if (s.charAt(i) == '|') {
                r = i;
            }
            right[i] = r;
        }
        int[] ans = new int[queries.length];
        for (int k = 0; k < queries.length; ++k) {
            int i = right[queries[k][0]];
            int j = left[queries[k][1]];
            if (i >= 0 && j >= 0 && i < j) {
                ans[k] = presum[j] - presum[i + 1];
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
    vector<int> platesBetweenCandles(string s, vector<vector<int>>& queries) {
        int n = s.size();
        vector<int> presum(n + 1);
        for (int i = 0; i < n; ++i) presum[i + 1] = presum[i] + (s[i] == '*');
        vector<int> left(n);
        vector<int> right(n);
        for (int i = 0, l = -1; i < n; ++i) {
            if (s[i] == '|') l = i;
            left[i] = l;
        }
        for (int i = n - 1, r = -1; i >= 0; --i) {
            if (s[i] == '|') r = i;
            right[i] = r;
        }
        vector<int> ans(queries.size());
        for (int k = 0; k < queries.size(); ++k) {
            int i = right[queries[k][0]];
            int j = left[queries[k][1]];
            if (i >= 0 && j >= 0 && i < j) ans[k] = presum[j] - presum[i + 1];
        }
        return ans;
    }
};
```

### **Go**

```go
func platesBetweenCandles(s string, queries [][]int) []int {
	n := len(s)
	presum := make([]int, n+1)
	for i := range s {
		if s[i] == '*' {
			presum[i+1] = 1
		}
		presum[i+1] += presum[i]
	}
	left, right := make([]int, n), make([]int, n)
	for i, l := 0, -1; i < n; i++ {
		if s[i] == '|' {
			l = i
		}
		left[i] = l
	}
	for i, r := n-1, -1; i >= 0; i-- {
		if s[i] == '|' {
			r = i
		}
		right[i] = r
	}
	ans := make([]int, len(queries))
	for k, q := range queries {
		i, j := right[q[0]], left[q[1]]
		if i >= 0 && j >= 0 && i < j {
			ans[k] = presum[j] - presum[i+1]
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
