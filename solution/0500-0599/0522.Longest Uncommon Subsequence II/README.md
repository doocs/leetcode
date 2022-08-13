# [522. 最长特殊序列 II](https://leetcode.cn/problems/longest-uncommon-subsequence-ii)

[English Version](/solution/0500-0599/0522.Longest%20Uncommon%20Subsequence%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定字符串列表&nbsp;<code>strs</code> ，返回其中 <strong>最长的特殊序列</strong>&nbsp;的长度。如果最长特殊序列不存在，返回 <code>-1</code> 。</p>

<p><strong>特殊序列</strong> 定义如下：该序列为某字符串 <strong>独有的子序列（即不能是其他字符串的子序列）</strong>。</p>

<p>&nbsp;<code>s</code>&nbsp;的&nbsp;<strong>子序列</strong>可以通过删去字符串&nbsp;<code>s</code>&nbsp;中的某些字符实现。</p>

<ul>
	<li>例如，<code>"abc"</code>&nbsp;是 <code>"aebdc"</code>&nbsp;的子序列，因为您可以删除<code>"a<u>e</u>b<u>d</u>c"</code>中的下划线字符来得到 <code>"abc"</code>&nbsp;。<code>"aebdc"</code>的子序列还包括<code>"aebdc"</code>、 <code>"aeb"</code>&nbsp;和 <font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size: 12.6px; background-color: rgb(249, 242, 244);">""</span></font>&nbsp;(空字符串)。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> strs = ["aba","cdc","eae"]
<strong>输出:</strong> 3
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> strs = ["aaa","aaa","aa"]
<strong>输出:</strong> -1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>2 &lt;= strs.length &lt;= 50</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 10</code></li>
	<li><code>strs[i]</code>&nbsp;只包含小写英文字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：判断子序列**

判断是否独有，只需要取字符串 $s$ 本身，与其他字符串比较即可。题目可以转化为：获取**非其他字符串子序列**的字符串的最大长度。若不存在，返回 -1。

其中，$check(a,b)$ 用于判断字符串 $b$ 是否为字符串 $a$ 的子序列。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findLUSlength(self, strs: List[str]) -> int:
        def check(a, b):
            i = j = 0
            while i < len(a) and j < len(b):
                if a[i] == b[j]:
                    j += 1
                i += 1
            return j == len(b)

        n = len(strs)
        ans = -1

        for i in range(n):
            j = 0
            while j < n:
                if i == j or not check(strs[j], strs[i]):
                    j += 1
                else:
                    break
            if j == n:
                ans = max(ans, len(strs[i]))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findLUSlength(String[] strs) {
        int ans = -1;
        for (int i = 0, j = 0, n = strs.length; i < n; ++i) {
            for (j = 0; j < n; ++j) {
                if (i == j) {
                    continue;
                }
                if (check(strs[j], strs[i])) {
                    break;
                }
            }
            if (j == n) {
                ans = Math.max(ans, strs[i].length());
            }
        }
        return ans;
    }

    private boolean check(String a, String b) {
        int j = 0;
        for (int i = 0; i < a.length() && j < b.length(); ++i) {
            if (a.charAt(i) == b.charAt(j)) {
                ++j;
            }
        }
        return j == b.length();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findLUSlength(vector<string>& strs) {
        int ans = -1;
        for (int i = 0, j = 0, n = strs.size(); i < n; ++i) {
            for (j = 0; j < n; ++j) {
                if (i == j) continue;
                if (check(strs[j], strs[i])) break;
            }
            if (j == n) ans = max(ans, (int)strs[i].size());
        }
        return ans;
    }

    bool check(string a, string b) {
        int j = 0;
        for (int i = 0; i < a.size() && j < b.size(); ++i)
            if (a[i] == b[j]) ++j;
        return j == b.size();
    }
};
```

### **Go**

```go
func findLUSlength(strs []string) int {
	check := func(a, b string) bool {
		j := 0
		for i := 0; i < len(a) && j < len(b); i++ {
			if a[i] == b[j] {
				j++
			}
		}
		return j == len(b)
	}

	ans := -1
	for i, j, n := 0, 0, len(strs); i < n; i++ {
		for j = 0; j < n; j++ {
			if i == j {
				continue
			}
			if check(strs[j], strs[i]) {
				break
			}
		}
		if j == n && ans < len(strs[i]) {
			ans = len(strs[i])
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
