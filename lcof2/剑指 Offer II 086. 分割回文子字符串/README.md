# [剑指 Offer II 086. 分割回文子字符串](https://leetcode.cn/problems/M99OJA)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串 <code>s</code> ，请将 <code>s</code> 分割成一些子串，使每个子串都是 <strong>回文串</strong> ，返回 s 所有可能的分割方案。</p>

<p><meta charset="UTF-8" /><strong>回文串</strong>&nbsp;是正着读和反着读都一样的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s =<strong> </strong>&quot;google&quot;
<strong>输出：</strong>[[&quot;g&quot;,&quot;o&quot;,&quot;o&quot;,&quot;g&quot;,&quot;l&quot;,&quot;e&quot;],[&quot;g&quot;,&quot;oo&quot;,&quot;g&quot;,&quot;l&quot;,&quot;e&quot;],[&quot;goog&quot;,&quot;l&quot;,&quot;e&quot;]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;aab&quot;
<strong>输出：</strong>[[&quot;a&quot;,&quot;a&quot;,&quot;b&quot;],[&quot;aa&quot;,&quot;b&quot;]]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;a&quot;
<strong>输出：</strong>[[&quot;a&quot;]<span style="font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 14px; background-color: rgb(255, 255, 255);">&nbsp;</span></pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 16</code></li>
	<li><code>s </code>仅由小写英文字母组成</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 131&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/palindrome-partitioning/">https://leetcode.cn/problems/palindrome-partitioning/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def partition(self, s: str) -> List[List[str]]:
        ans = []
        n = len(s)
        dp = [[True] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                dp[i][j] = s[i] == s[j] and dp[i + 1][j - 1]

        def dfs(s, i, t):
            nonlocal n
            if i == n:
                ans.append(t.copy())
                return
            for j in range(i, n):
                if dp[i][j]:
                    t.append(s[i : j + 1])
                    dfs(s, j + 1, t)
                    t.pop(-1)

        dfs(s, 0, [])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private boolean[][] dp;
    private List<List<String>> ans;
    private int n;

    public String[][] partition(String s) {
        ans = new ArrayList<>();
        n = s.length();
        dp = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], true);
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
            }
        }
        dfs(s, 0, new ArrayList<>());
        String [][] res = new String [ans.size()][];
        for (int i = 0; i < ans.size(); ++i) {
            res[i] = ans.get(i).toArray(new String[0]);
        }
        return res;
    }

    private void dfs(String s, int i, List<String> t) {
        if (i == n) {
            ans.add(new ArrayList<>(t));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (dp[i][j]) {
                t.add(s.substring(i, j + 1));
                dfs(s, j + 1, t);
                t.remove(t.size() - 1);
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<bool>> dp;
    vector<vector<string>> ans;
    int n;

    vector<vector<string>> partition(string s) {
        n = s.size();
        dp.assign(n, vector<bool>(n, true));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                dp[i][j] = s[i] == s[j] && dp[i + 1][j - 1];
            }
        }
        vector<string> t;
        dfs(s, 0, t);
        return ans;
    }

    void dfs(string& s, int i, vector<string> t) {
        if (i == n) {
            ans.push_back(t);
            return;
        }
        for (int j = i; j < n; ++j) {
            if (dp[i][j]) {
                t.push_back(s.substr(i, j - i + 1));
                dfs(s, j + 1, t);
                t.pop_back();
            }
        }
    }
};
```

### **Go**

```go
func partition(s string) [][]string {
	n := len(s)
	dp := make([][]bool, n)
	var ans [][]string
	for i := 0; i < n; i++ {
		dp[i] = make([]bool, n)
		for j := 0; j < n; j++ {
			dp[i][j] = true
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			dp[i][j] = s[i] == s[j] && dp[i+1][j-1]
		}
	}

	var dfs func(s string, i int, t []string)
	dfs = func(s string, i int, t []string) {
		if i == n {
			ans = append(ans, append([]string(nil), t...))
			return
		}
		for j := i; j < n; j++ {
			if dp[i][j] {
				t = append(t, s[i:j+1])
				dfs(s, j+1, t)
				t = t[:len(t)-1]
			}
		}
	}

	var t []string
	dfs(s, 0, t)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
