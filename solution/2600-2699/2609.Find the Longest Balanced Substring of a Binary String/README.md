# [2609. 最长平衡子字符串](https://leetcode.cn/problems/find-the-longest-balanced-substring-of-a-binary-string)

[English Version](/solution/2600-2699/2609.Find%20the%20Longest%20Balanced%20Substring%20of%20a%20Binary%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个仅由 <code>0</code> 和 <code>1</code> 组成的二进制字符串 <code>s</code> 。<span style="">&nbsp;</span><span style="">&nbsp;</span></p>

<p>如果子字符串中 <strong>所有的<span style=""> </span></strong><code><span style="">0</span></code><strong><span style=""> </span>都在 </strong><code>1</code><strong> 之前</strong> 且其中 <code>0</code> 的数量等于 <code>1</code> 的数量，则认为 <code>s</code> 的这个子字符串是平衡子字符串。请注意，空子字符串也视作平衡子字符串。<span style="">&nbsp;</span></p>

<p>返回&nbsp;<span style=""> </span><code>s</code> 中最长的平衡子字符串长度。</p>

<p>子字符串是字符串中的一个连续字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "01000111"
<strong>输出：</strong>6
<strong>解释：</strong>最长的平衡子字符串是 "000111" ，长度为 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "00111"
<strong>输出：</strong>4
<strong>解释：</strong>最长的平衡子字符串是 "0011" ，长度为 <span style="">&nbsp;</span>4 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "111"
<strong>输出：</strong>0
<strong>解释：</strong>除了空子字符串之外不存在其他平衡子字符串，所以答案为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 50</code></li>
	<li><code>'0' &lt;= s[i] &lt;= '1'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findTheLongestBalancedSubstring(self, s: str) -> int:
        def check(i, j):
            cnt = 0
            for k in range(i, j + 1):
                if s[k] == '1':
                    cnt += 1
                elif cnt:
                    return False
            return cnt * 2 == (j - i + 1)

        n = len(s)
        ans = 0
        for i in range(n):
            for j in range(i + 1, n):
                if check(i, j):
                    ans = max(ans, j - i + 1)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findTheLongestBalancedSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (check(s, i, j)) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
    
    private boolean check(String s, int i, int j) {
        int cnt = 0;
        for (int k = i; k <= j; ++k) {
            if (s.charAt(k) == '1') {
                ++cnt;
            } else if (cnt > 0) {
                return false;
            }
        }
        return cnt * 2 == j - i + 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findTheLongestBalancedSubstring(string s) {
        int n = s.size();
        int ans = 0;
        auto check = [&](int i, int j) -> bool {
            int cnt = 0;
            for (int k = i; k <= j; ++k) {
                if (s[k] == '1') {
                    ++cnt;
                } else if (cnt) {
                    return false;
                }
            }
            return cnt * 2 == j - i + 1;
        };
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (check(i, j)) {
                    ans = max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findTheLongestBalancedSubstring(s string) (ans int) {
	n := len(s)
	check := func(i, j int) bool {
		cnt := 0
		for k := i; k <= j; k++ {
			if s[k] == '1' {
				cnt++
			} else if cnt > 0 {
				return false
			}
		}
		return cnt*2 == j-i+1
	}
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if check(i, j) {
				ans = max(ans, j-i+1)
			}
		}
	}
	return
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
