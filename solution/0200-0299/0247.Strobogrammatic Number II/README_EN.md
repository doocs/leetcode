# [247. Strobogrammatic Number II 🔒](https://leetcode.com/problems/strobogrammatic-number-ii)

[中文文档](/solution/0200-0299/0247.Strobogrammatic%20Number%20II/README.md)

<!-- tags:Recursion,Array,String -->

## Description

<p>Given an integer <code>n</code>, return all the <strong>strobogrammatic numbers</strong> that are of length <code>n</code>. You may return the answer in <strong>any order</strong>.</p>

<p>A <strong>strobogrammatic number</strong> is a number that looks the same when rotated <code>180</code> degrees (looked at upside down).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> n = 2
<strong>Output:</strong> ["11","69","88","96"]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> n = 1
<strong>Output:</strong> ["0","1","8"]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 14</code></li>
</ul>

## Solutions

### Solution 1: Recursion

If the length is $1$, then the strobogrammatic numbers are only $0, 1, 8$; if the length is $2$, then the strobogrammatic numbers are only $11, 69, 88, 96$.

We design a recursive function $dfs(u)$, which returns the strobogrammatic numbers of length $u$. The answer is $dfs(n)$.

If $u$ is $0$, return a list containing an empty string, i.e., `[""]`; if $u$ is $1$, return the list `["0", "1", "8"]`.

If $u$ is greater than $1$, we traverse all the strobogrammatic numbers of length $u - 2$. For each strobogrammatic number $v$, we add $1, 8, 6, 9$ to both sides of it, and we can get the strobogrammatic numbers of length `u`.

Note that if $u \neq n$, we can also add $0$ to both sides of the strobogrammatic number.

Finally, we return all the strobogrammatic numbers of length $n$.

The time complexity is $O(2^{n+2})$.

Similar problems:

-   [248. Strobogrammatic Number III 🔒](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0248.Strobogrammatic%20Number%20III/README_EN.md)

<!-- tabs:start -->

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
                for l, r in ('11', '88', '69', '96'):
                    ans.append(l + v + r)
                if u != n:
                    ans.append('0' + v + '0')
            return ans

        return dfs(n)
```

```java
class Solution {
    private static final int[][] PAIRS = {{1, 1}, {8, 8}, {6, 9}, {9, 6}};
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
        for (String v : dfs(u - 2)) {
            for (var p : PAIRS) {
                ans.add(p[0] + v + p[1]);
            }
            if (u != n) {
                ans.add(0 + v + 0);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    const vector<pair<char, char>> pairs = {{'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};

    vector<string> findStrobogrammatic(int n) {
        function<vector<string>(int)> dfs = [&](int u) {
            if (u == 0) return vector<string>{""};
            if (u == 1) return vector<string>{"0", "1", "8"};
            vector<string> ans;
            for (auto& v : dfs(u - 2)) {
                for (auto& [l, r] : pairs) ans.push_back(l + v + r);
                if (u != n) ans.push_back('0' + v + '0');
            }
            return ans;
        };
        return dfs(n);
    }
};
```

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

<!-- tabs:end -->

<!-- end -->
