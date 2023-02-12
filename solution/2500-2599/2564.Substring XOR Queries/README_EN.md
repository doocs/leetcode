# [2564. Substring XOR Queries](https://leetcode.com/problems/substring-xor-queries)

[中文文档](/solution/2500-2599/2564.Substring%20XOR%20Queries/README.md)

## Description

<p>You are given a <strong>binary string</strong> <code>s</code>, and a <strong>2D</strong> integer array <code>queries</code> where <code>queries[i] = [first<sub>i</sub>, second<sub>i</sub>]</code>.</p>

<p>For the <code>i<sup>th</sup></code> query, find the <strong>shortest substring</strong> of <code>s</code> whose <strong>decimal value</strong>, <code>val</code>, yields <code>second<sub>i</sub></code> when <strong>bitwise XORed</strong> with <code>first<sub>i</sub></code>. In other words, <code>val ^ first<sub>i</sub> == second<sub>i</sub></code>.</p>

<p>The answer to the <code>i<sup>th</sup></code> query is the endpoints (<strong>0-indexed</strong>) of the substring <code>[left<sub>i</sub>, right<sub>i</sub>]</code> or <code>[-1, -1]</code> if no such substring exists. If there are multiple answers, choose the one with the <strong>minimum</strong> <code>left<sub>i</sub></code>.</p>

<p><em>Return an array</em> <code>ans</code> <em>where</em> <code>ans[i] = [left<sub>i</sub>, right<sub>i</sub>]</code> <em>is the answer to the</em> <code>i<sup>th</sup></code> <em>query.</em></p>

<p>A <strong>substring</strong> is a contiguous non-empty sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;101101&quot;, queries = [[0,5],[1,2]]
<strong>Output:</strong> [[0,2],[2,3]]
<strong>Explanation:</strong> For the first query the substring in range <code>[0,2]</code> is <strong>&quot;101&quot;</strong> which has a decimal value of <strong><code>5</code></strong>, and <strong><code>5 ^ 0 = 5</code></strong>, hence the answer to the first query is <code>[0,2]</code>. In the second query, the substring in range <code>[2,3]</code> is <strong>&quot;11&quot;,</strong> and has a decimal value of <strong>3</strong>, and <strong>3<code> ^ 1 = 2</code></strong>.&nbsp;So, <code>[2,3]</code> is returned for the second query. 

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0101&quot;, queries = [[12,8]]
<strong>Output:</strong> [[-1,-1]]
<strong>Explanation:</strong> In this example there is no substring that answers the query, hence <code>[-1,-1] is returned</code>.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1&quot;, queries = [[4,5]]
<strong>Output:</strong> [[0,0]]
<strong>Explanation:</strong> For this example, the substring in range <code>[0,0]</code> has a decimal value of <strong><code>1</code></strong>, and <strong><code>1 ^ 4 = 5</code></strong>. So, the answer is <code>[0,0]</code>.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= first<sub>i</sub>, second<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def substringXorQueries(self, s: str, queries: List[List[int]]) -> List[List[int]]:
        d = {}
        n = len(s)
        for i in range(n):
            x = 0
            for j in range(32):
                if i + j >= n:
                    break
                x = x << 1 | int(s[i + j])
                if x not in d:
                    d[x] = [i, i + j]
                if x == 0:
                    break
        return [d.get(first ^ second, [-1, -1]) for first, second in queries]
```

### **Java**

```java
class Solution {
    public int[][] substringXorQueries(String s, int[][] queries) {
        Map<Integer, int[]> d = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int x = 0;
            for (int j = 0; j < 32 && i + j < n; ++j) {
                x = x << 1 | (s.charAt(i + j) - '0');
                d.putIfAbsent(x, new int[] {i, i + j});
                if (x == 0) {
                    break;
                }
            }
        }
        int m = queries.length;
        int[][] ans = new int[m][2];
        for (int i = 0; i < m; ++i) {
            int first = queries[i][0], second = queries[i][1];
            int val = first ^ second;
            ans[i] = d.getOrDefault(val, new int[] {-1, -1});
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> substringXorQueries(string s, vector<vector<int>>& queries) {
        unordered_map<int, vector<int>> d;
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            int x = 0;
            for (int j = 0; j < 32 && i + j < n; ++j) {
                x = x << 1 | (s[i + j] - '0');
                if (!d.count(x)) {
                    d[x] = {i, i + j};
                }
                if (x == 0) {
                    break;
                }
            }
        }
        vector<vector<int>> ans;
        for (auto& q : queries) {
            int first = q[0], second = q[1];
            int val = first ^ second;
            if (d.count(val)) {
                ans.emplace_back(d[val]);
            } else {
                ans.push_back({-1, -1});
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func substringXorQueries(s string, queries [][]int) (ans [][]int) {
	d := map[int][]int{}
	for i := range s {
		x := 0
		for j := 0; j < 32 && i+j < len(s); j++ {
			x = x<<1 | int(s[i+j]-'0')
			if _, ok := d[x]; !ok {
				d[x] = []int{i, i + j}
			}
			if x == 0 {
				break
			}
		}
	}
	for _, q := range queries {
		first, second := q[0], q[1]
		val := first ^ second
		if v, ok := d[val]; ok {
			ans = append(ans, v)
		} else {
			ans = append(ans, []int{-1, -1})
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
