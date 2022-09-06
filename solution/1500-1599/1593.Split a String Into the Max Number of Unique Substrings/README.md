# [1593. 拆分字符串使唯一子字符串的数目最大](https://leetcode.cn/problems/split-a-string-into-the-max-number-of-unique-substrings)

[English Version](/solution/1500-1599/1593.Split%20a%20String%20Into%20the%20Max%20Number%20of%20Unique%20Substrings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，请你拆分该字符串，并返回拆分后唯一子字符串的最大数目。</p>

<p>字符串 <code>s</code> 拆分后可以得到若干 <strong>非空子字符串</strong> ，这些子字符串连接后应当能够还原为原字符串。但是拆分出来的每个子字符串都必须是 <strong>唯一的</strong> 。</p>

<p>注意：<strong>子字符串</strong> 是字符串中的一个连续字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;ababccc&quot;
<strong>输出：</strong>5
<strong>解释：</strong>一种最大拆分方法为 [&#39;a&#39;, &#39;b&#39;, &#39;ab&#39;, &#39;c&#39;, &#39;cc&#39;] 。像 [&#39;a&#39;, &#39;b&#39;, &#39;a&#39;, &#39;b&#39;, &#39;c&#39;, &#39;cc&#39;] 这样拆分不满足题目要求，因为其中的 &#39;a&#39; 和 &#39;b&#39; 都出现了不止一次。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;aba&quot;
<strong>输出：</strong>2
<strong>解释：</strong>一种最大拆分方法为 [&#39;a&#39;, &#39;ba&#39;] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;aa&quot;
<strong>输出：</strong>1
<strong>解释：</strong>无法进一步拆分字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>
	<p><code>1 &lt;= s.length&nbsp;&lt;= 16</code></p>
	</li>
	<li>
	<p><code>s</code> 仅包含小写英文字母</p>
	</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

经典 DFS 回溯问题。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxUniqueSplit(self, s: str) -> int:
        def dfs(i, t):
            if i >= len(s):
                nonlocal ans
                ans = max(ans, t)
                return
            for j in range(i + 1, len(s) + 1):
                if s[i: j] not in vis:
                    vis.add(s[i: j])
                    dfs(j, t + 1)
                    vis.remove(s[i: j])

        vis = set()
        ans = 1
        dfs(0, 0)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Set<String> vis = new HashSet<>();
    private int ans = 1;
    private String s;

    public int maxUniqueSplit(String s) {
        this.s = s;
        dfs(0, 0);
        return ans;
    }

    private void dfs(int i, int t) {
        if (i >= s.length()) {
            ans = Math.max(ans, t);
            return;
        }
        for (int j = i + 1; j <= s.length(); ++j) {
            String x = s.substring(i, j);
            if (vis.add(x)) {
                dfs(j, t + 1);
                vis.remove(x);
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    unordered_set<string> vis;
    string s;
    int ans = 1;

    int maxUniqueSplit(string s) {
        this->s = s;
        dfs(0, 0);
        return ans;
    }

    void dfs(int i, int t) {
        if (i >= s.size()) {
            ans = max(ans, t);
            return;
        }
        for (int j = i + 1; j <= s.size(); ++j) {
            string x = s.substr(i, j - i);
            if (!vis.count(x)) {
                vis.insert(x);
                dfs(j, t + 1);
                vis.erase(x);
            }
        }
    }
};
```

### **Go**

```go
func maxUniqueSplit(s string) int {
	ans := 1
	vis := map[string]bool{}

	var dfs func(i, t int)
	dfs = func(i, t int) {
		if i >= len(s) {
			ans = max(ans, t)
			return
		}
		for j := i + 1; j <= len(s); j++ {
			x := s[i:j]
			if !vis[x] {
				vis[x] = true
				dfs(j, t+1)
				vis[x] = false
			}
		}
	}
	dfs(0, 0)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
