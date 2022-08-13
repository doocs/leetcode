# [247. Strobogrammatic Number II](https://leetcode.com/problems/strobogrammatic-number-ii)

[中文文档](/solution/0200-0299/0247.Strobogrammatic%20Number%20II/README.md)

## Description

<p>Given an integer <code>n</code>, return all the <strong>strobogrammatic numbers</strong> that are of length <code>n</code>. You may return the answer in <strong>any order</strong>.</p>

<p>A <strong>strobogrammatic number</strong> is a number that looks the same when rotated <code>180</code> degrees (looked at upside down).</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> n = 2
<strong>Output:</strong> ["11","69","88","96"]
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> n = 1
<strong>Output:</strong> ["0","1","8"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 14</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findStrobogrammatic(self, n: int) -> List[str]:
        def dfs(u):
            if u == 0:
                return ['']
            if u == 1:
                return ['0', '1', '8']
            ans = []
            for v in dfs(u - 2):
                for l, r in [['1', '1'], ['8', '8'], ['6', '9'], ['9', '6']]:
                    ans.append(l + v + r)
                if u != n:
                    ans.append('0' + v + '0')
            return ans

        return dfs(n)
```

### **Java**

```java
class Solution {
    private int n;

    public List<String> findStrobogrammatic(int n) {
        this.n = n;
        return dfs(n);
    }

    private List<String> dfs(int u) {
        if (u == 0) {
            return Collections.singletonList("");
        }
        if (u == 1) {
            return Arrays.asList("0", "1", "8");
        }
        List<String> ans = new ArrayList<>();
        int[][] pairs = new int[][]{{1, 1}, {8, 8}, {6, 9}, {9, 6}};
        for (String v : dfs(u - 2)) {
            for (int[] p : pairs) {
                ans.add(p[0] + v + p[1]);
            }
            if (u != n) {
                ans.add("0" + v + "0");
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
    int n;
    vector<string> findStrobogrammatic(int n) {
        this->n = n;
        return dfs(n);
    }

    vector<string> dfs(int u) {
        if (u == 0) return {""};
        if (u == 1) return {"0", "1", "8"};
        vector<string> ans;
        vector<vector<char>> pairs = {{'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};
        for (string v : dfs(u - 2)) {
            for (auto& p : pairs) ans.push_back({p[0] + v + p[1]});
            if (u != n) ans.push_back('0' + v + '0');
        }
        return ans;
    }
};
```

### **Go**

```go
func findStrobogrammatic(n int) []string {
	var dfs func(int) []string
	dfs = func(u int) []string {
		if u == 0 {
			return []string{""}
		}
		if u == 1 {
			return []string{"0", "1", "8"}
		}
		var ans []string
		pairs := [][]string{{"1", "1"}, {"8", "8"}, {"6", "9"}, {"9", "6"}}
		for _, v := range dfs(u - 2) {
			for _, p := range pairs {
				ans = append(ans, p[0]+v+p[1])
			}
			if u != n {
				ans = append(ans, "0"+v+"0")
			}
		}
		return ans
	}
	return dfs(n)
}
```

### **...**

```

```

<!-- tabs:end -->
