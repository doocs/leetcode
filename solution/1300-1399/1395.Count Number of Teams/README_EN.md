# [1395. Count Number of Teams](https://leetcode.com/problems/count-number-of-teams)

[中文文档](/solution/1300-1399/1395.Count%20Number%20of%20Teams/README.md)

## Description

<p>There are <code>n</code> soldiers standing in a line. Each soldier is assigned a <strong>unique</strong> <code>rating</code> value.</p>

<p>You have to form a team of 3 soldiers amongst them under the following rules:</p>

<ul>
	<li>Choose 3 soldiers with index (<code>i</code>, <code>j</code>, <code>k</code>) with rating (<code>rating[i]</code>, <code>rating[j]</code>, <code>rating[k]</code>).</li>
	<li>A team is valid if: (<code>rating[i] &lt; rating[j] &lt; rating[k]</code>) or (<code>rating[i] &gt; rating[j] &gt; rating[k]</code>) where (<code>0 &lt;= i &lt; j &lt; k &lt; n</code>).</li>
</ul>

<p>Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> rating = [2,5,3,4,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1). 
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> rating = [2,1,3]
<strong>Output:</strong> 0
<strong>Explanation:</strong> We can&#39;t form any team given the conditions.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> rating = [1,2,3,4]
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == rating.length</code></li>
	<li><code>3 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= rating[i] &lt;= 10<sup>5</sup></code></li>
	<li>All the integers in <code>rating</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numTeams(self, rating: List[int]) -> int:
        n, ans = len(rating), 0
        for j in range(1, n - 1):
            ia = ib = ka = kb = 0
            for i in range(j):
                if rating[i] < rating[j]:
                    ia += 1
                elif rating[i] > rating[j]:
                    ib += 1
            for k in range(j + 1, n):
                if rating[j] < rating[k]:
                    ka += 1
                elif rating[j] > rating[k]:
                    kb += 1
            ans += ia * ka + ib * kb
        return ans
```

### **Java**

```java
class Solution {
    public int numTeams(int[] rating) {
        int n = rating.length;
        int ans = 0;
        for (int j = 1; j < n - 1; ++j) {
            int ia = 0;
            int ib = 0;
            int ka = 0;
            int kb = 0;
            for (int i = 0; i < j; ++i) {
                if (rating[i] < rating[j]) {
                    ++ia;
                } else if (rating[i] > rating[j]) {
                    ++ib;
                }
            }
            for (int k = j + 1; k < n; ++k) {
                if (rating[j] < rating[k]) {
                    ++ka;
                } else if (rating[j] > rating[k]) {
                    ++kb;
                }
            }
            ans += ia * ka + ib * kb;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numTeams(vector<int>& rating) {
        int n = rating.size(), ans = 0;
        for (int j = 1; j < n - 1; ++j) {
            int ia = 0, ib = 0, ka = 0, kb = 0;
            for (int i = 0; i < j; ++i) {
                if (rating[i] < rating[j])
                    ++ia;
                else if (rating[i] > rating[j])
                    ++ib;
            }
            for (int k = j + 1; k < n; ++k) {
                if (rating[j] < rating[k])
                    ++ka;
                else if (rating[j] > rating[k])
                    ++kb;
            }
            ans += ia * ka + ib * kb;
        }
        return ans;
    }
};
```

### **Go**

```go
func numTeams(rating []int) int {
	n, ans := len(rating), 0
	for j := 1; j < n-1; j++ {
		ia, ib, ka, kb := 0, 0, 0, 0
		for i := 0; i < j; i++ {
			if rating[i] < rating[j] {
				ia++
			} else if rating[i] > rating[j] {
				ib++
			}
		}
		for k := j + 1; k < n; k++ {
			if rating[j] < rating[k] {
				ka++
			} else if rating[j] > rating[k] {
				kb++
			}
		}
		ans += ia*ka + ib*kb
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
