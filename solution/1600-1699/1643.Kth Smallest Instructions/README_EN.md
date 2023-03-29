# [1643. Kth Smallest Instructions](https://leetcode.com/problems/kth-smallest-instructions)

[中文文档](/solution/1600-1699/1643.Kth%20Smallest%20Instructions/README.md)

## Description

<p>Bob is standing at cell <code>(0, 0)</code>, and he wants to reach <code>destination</code>: <code>(row, column)</code>. He can only travel <strong>right</strong> and <strong>down</strong>. You are going to help Bob by providing <strong>instructions</strong> for him to reach <code>destination</code>.</p>

<p>The <strong>instructions</strong> are represented as a string, where each character is either:</p>

<ul>
	<li><code>&#39;H&#39;</code>, meaning move horizontally (go <strong>right</strong>), or</li>
	<li><code>&#39;V&#39;</code>, meaning move vertically (go <strong>down</strong>).</li>
</ul>

<p>Multiple <strong>instructions</strong> will lead Bob to <code>destination</code>. For example, if <code>destination</code> is <code>(2, 3)</code>, both <code>&quot;HHHVV&quot;</code> and <code>&quot;HVHVH&quot;</code> are valid <strong>instructions</strong>.</p>

<p>However, Bob is very picky. Bob has a lucky number <code>k</code>, and he wants the <code>k<sup>th</sup></code> <strong>lexicographically smallest instructions</strong> that will lead him to <code>destination</code>. <code>k</code> is <strong>1-indexed</strong>.</p>

<p>Given an integer array <code>destination</code> and an integer <code>k</code>, return <em>the </em><code>k<sup>th</sup></code><em> <strong>lexicographically smallest instructions</strong> that will take Bob to </em><code>destination</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1643.Kth%20Smallest%20Instructions/images/ex1.png" style="width: 300px; height: 229px;" /></p>

<pre>
<strong>Input:</strong> destination = [2,3], k = 1
<strong>Output:</strong> &quot;HHHVV&quot;
<strong>Explanation:</strong> All the instructions that reach (2, 3) in lexicographic order are as follows:
[&quot;HHHVV&quot;, &quot;HHVHV&quot;, &quot;HHVVH&quot;, &quot;HVHHV&quot;, &quot;HVHVH&quot;, &quot;HVVHH&quot;, &quot;VHHHV&quot;, &quot;VHHVH&quot;, &quot;VHVHH&quot;, &quot;VVHHH&quot;].
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1643.Kth%20Smallest%20Instructions/images/ex2.png" style="width: 300px; height: 229px;" /></strong></p>

<pre>
<strong>Input:</strong> destination = [2,3], k = 2
<strong>Output:</strong> &quot;HHVHV&quot;
</pre>

<p><strong class="example">Example 3:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1643.Kth%20Smallest%20Instructions/images/ex3.png" style="width: 300px; height: 229px;" /></strong></p>

<pre>
<strong>Input:</strong> destination = [2,3], k = 3
<strong>Output:</strong> &quot;HHVVH&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>destination.length == 2</code></li>
	<li><code>1 &lt;= row, column &lt;= 15</code></li>
	<li><code>1 &lt;= k &lt;= nCr(row + column, row)</code>, where <code>nCr(a, b)</code> denotes <code>a</code> choose <code>b</code>​​​​​.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def kthSmallestPath(self, destination: List[int], k: int) -> str:
        v, h = destination
        ans = []
        for _ in range(h + v):
            if h == 0:
                ans.append("V")
            else:
                x = comb(h + v - 1, h - 1)
                if k > x:
                    ans.append("V")
                    v -= 1
                    k -= x
                else:
                    ans.append("H")
                    h -= 1
        return "".join(ans)
```

### **Java**

```java
class Solution {
    public String kthSmallestPath(int[] destination, int k) {
        int v = destination[0], h = destination[1];
        int n = v + h;
        int[][] c = new int[n + 1][h + 1];
        c[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= h; ++j) {
                c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = n; i > 0; --i) {
            if (h == 0) {
                ans.append('V');
            } else {
                int x = c[v + h - 1][h - 1];
                if (k > x) {
                    ans.append('V');
                    k -= x;
                    --v;
                } else {
                    ans.append('H');
                    --h;
                }
            }
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string kthSmallestPath(vector<int>& destination, int k) {
        int v = destination[0], h = destination[1];
        int n = v + h;
        int c[n + 1][h + 1];
        memset(c, 0, sizeof(c));
        c[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= h; ++j) {
                c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
            }
        }
        string ans;
        for (int i = 0; i < n; ++i) {
            if (h == 0) {
                ans.push_back('V');
            } else {
                int x = c[v + h - 1][h - 1];
                if (k > x) {
                    ans.push_back('V');
                    --v;
                    k -= x;
                } else {
                    ans.push_back('H');
                    --h;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func kthSmallestPath(destination []int, k int) string {
	v, h := destination[0], destination[1]
	n := v + h
	c := make([][]int, n+1)
	for i := range c {
		c[i] = make([]int, h+1)
		c[i][0] = 1
	}
	for i := 1; i <= n; i++ {
		for j := 1; j <= h; j++ {
			c[i][j] = c[i-1][j] + c[i-1][j-1]
		}
	}
	ans := []byte{}
	for i := 0; i < n; i++ {
		if h == 0 {
			ans = append(ans, 'V')
		} else {
			x := c[v+h-1][h-1]
			if k > x {
				ans = append(ans, 'V')
				k -= x
				v--
			} else {
				ans = append(ans, 'H')
				h--
			}
		}
	}
	return string(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
