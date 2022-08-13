# [306. 累加数](https://leetcode.cn/problems/additive-number)

[English Version](/solution/0300-0399/0306.Additive%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>累加数</strong> 是一个字符串，组成它的数字可以形成累加序列。</p>

<p>一个有效的 <strong>累加序列</strong> 必须<strong> 至少 </strong>包含 3 个数。除了最开始的两个数以外，序列中的每个后续数字必须是它之前两个数字之和。</p>

<p>给你一个只包含数字&nbsp;<code>'0'-'9'</code>&nbsp;的字符串，编写一个算法来判断给定输入是否是 <strong>累加数</strong> 。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p><strong>说明：</strong>累加序列里的数，除数字 0 之外，<strong>不会</strong> 以 0 开头，所以不会出现&nbsp;<code>1, 2, 03</code> 或者&nbsp;<code>1, 02, 3</code>&nbsp;的情况。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong><code>"112358"</code>
<strong>输出：</strong>true 
<strong>解释：</strong>累加序列为: <code>1, 1, 2, 3, 5, 8 </code>。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入<code>：</code></strong><code>"199100199"</code>
<strong>输出：</strong>true 
<strong>解释：</strong>累加序列为: <code>1, 99, 100, 199。</code>1 + 99 = 100, 99 + 100 = 199</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 35</code></li>
	<li><code>num</code> 仅由数字（<code>0</code> - <code>9</code>）组成</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你计划如何处理由过大的整数输入导致的溢出?</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

DFS + 剪枝。

Python 大整数相加不会有溢出问题。由于 num 字符串长度最大为 35，因此对于其他语言，可以通过控制整数长度防止溢出。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isAdditiveNumber(self, num: str) -> bool:
        def dfs(a, b, num):
            if not num:
                return True
            if a + b > 0 and num[0] == '0':
                return False
            for i in range(1, len(num) + 1):
                if a + b == int(num[:i]):
                    if dfs(b, a + b, num[i:]):
                        return True
            return False

        n = len(num)
        for i in range(1, n - 1):
            for j in range(i + 1, n):
                if i > 1 and num[0] == '0':
                    break
                if j - i > 1 and num[i] == '0':
                    continue
                if dfs(int(num[:i]), int(num[i:j]), num[j:]):
                    return True
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i < Math.min(n - 1, 19); ++i) {
            for (int j = i + 1; j < Math.min(n, i + 19); ++j) {
                if (i > 1 && num.charAt(0) == '0') {
                    break;
                }
                if (j - i > 1 && num.charAt(i) == '0') {
                    continue;
                }
                long a = Long.parseLong(num.substring(0, i));
                long b = Long.parseLong(num.substring(i, j));
                if (dfs(a, b, num.substring(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(long a, long b, String num) {
        if ("".equals(num)) {
            return true;
        }
        if (a + b > 0 && num.charAt(0) == '0') {
            return false;
        }
        for (int i = 1; i < Math.min(num.length() + 1, 19); ++i) {
            if (a + b == Long.parseLong(num.substring(0, i))) {
                if (dfs(b, a + b, num.substring(i))) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isAdditiveNumber(string num) {
        int n = num.size();
        for (int i = 1; i < min(n - 1, 19); ++i) {
            for (int j = i + 1; j < min(n, i + 19); ++j) {
                if (i > 1 && num[0] == '0') break;
                if (j - i > 1 && num[i] == '0') continue;
                auto a = stoll(num.substr(0, i));
                auto b = stoll(num.substr(i, j - i));
                if (dfs(a, b, num.substr(j, n - j))) return true;
            }
        }
        return false;
    }

    bool dfs(long long a, long long b, string num) {
        if (num == "") return true;
        if (a + b > 0 && num[0] == '0') return false;
        for (int i = 1; i < min((int)num.size() + 1, 19); ++i)
            if (a + b == stoll(num.substr(0, i)))
                if (dfs(b, a + b, num.substr(i, num.size() - i)))
                    return true;
        return false;
    }
};
```

### **Go**

```go
func isAdditiveNumber(num string) bool {
	n := len(num)
	var dfs func(a, b int64, num string) bool
	dfs = func(a, b int64, num string) bool {
		if num == "" {
			return true
		}
		if a+b > 0 && num[0] == '0' {
			return false
		}
		for i := 1; i < min(len(num)+1, 19); i++ {
			c, _ := strconv.ParseInt(num[:i], 10, 64)
			if a+b == c {
				if dfs(b, c, num[i:]) {
					return true
				}
			}
		}
		return false
	}
	for i := 1; i < min(n-1, 19); i++ {
		for j := i + 1; j < min(n, i+19); j++ {
			if i > 1 && num[0] == '0' {
				break
			}
			if j-i > 1 && num[i] == '0' {
				continue
			}
			a, _ := strconv.ParseInt(num[:i], 10, 64)
			b, _ := strconv.ParseInt(num[i:j], 10, 64)
			if dfs(a, b, num[j:]) {
				return true
			}
		}
	}
	return false
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
