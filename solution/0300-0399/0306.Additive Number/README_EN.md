# [306. Additive Number](https://leetcode.com/problems/additive-number)

[中文文档](/solution/0300-0399/0306.Additive%20Number/README.md)

## Description

<p>An <strong>additive number</strong> is a string whose digits can form an <strong>additive sequence</strong>.</p>

<p>A valid <strong>additive sequence</strong> should contain <strong>at least</strong> three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.</p>

<p>Given a string containing only digits, return <code>true</code> if it is an <strong>additive number</strong> or <code>false</code> otherwise.</p>

<p><strong>Note:</strong> Numbers in the additive sequence <strong>cannot</strong> have leading zeros, so sequence <code>1, 2, 03</code> or <code>1, 02, 3</code> is invalid.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> &quot;112358&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> 
The digits can form an additive sequence: 1, 1, 2, 3, 5, 8. 
1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> &quot;199100199&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> 
The additive sequence is: 1, 99, 100, 199.&nbsp;
1 + 99 = 100, 99 + 100 = 199
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 35</code></li>
	<li><code>num</code> consists only of digits.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> How would you handle overflow for very large input integers?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

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
