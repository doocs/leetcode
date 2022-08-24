# [1461. 检查一个字符串是否包含所有长度为 K 的二进制子串](https://leetcode.cn/problems/check-if-a-string-contains-all-binary-codes-of-size-k)

[English Version](/solution/1400-1499/1461.Check%20If%20a%20String%20Contains%20All%20Binary%20Codes%20of%20Size%20K/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二进制字符串&nbsp;<code>s</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。如果所有长度为 <code>k</code>&nbsp;的二进制字符串都是 <code>s</code>&nbsp;的子串，请返回 <code>true</code> ，否则请返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "00110110", k = 2
<strong>输出：</strong>true
<strong>解释：</strong>长度为 2 的二进制串包括 "00"，"01"，"10" 和 "11"。它们分别是 s 中下标为 0，1，3，2 开始的长度为 2 的子串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "0110", k = 1
<strong>输出：</strong>true
<strong>解释：</strong>长度为 1 的二进制串包括 "0" 和 "1"，显然它们都是 s 的子串。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "0110", k = 2
<strong>输出：</strong>false
<strong>解释：</strong>长度为 2 的二进制串 "00" 没有出现在 s 中。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 不是<code>'0'</code> 就是 <code>'1'</code></li>
	<li><code>1 &lt;= k &lt;= 20</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

遍历字符串 $s$，用一个哈希表存储所有长度为 $k$ 的不同子串。只需要判断子串数能否达到 $2^k$ 即可。

时间复杂度 $O(n \times k)$，其中 $n$ 是字符串 $s$ 的长度，$k$ 是子串长度。

**方法二：滑动窗口**

方法一中，我们存储了所有长度为 $k$ 的不同子串，子串的处理需要 $O(k)$ 的时间，我们可以改用滑动窗口，每次添加最新字符时，删除窗口最左边的字符。此过程中用一个整型数字 $num$ 来存放子串。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def hasAllCodes(self, s: str, k: int) -> bool:
        ss = {s[i: i + k] for i in range(len(s) - k + 1)}
        return len(ss) == 1 << k
```

```python
class Solution:
    def hasAllCodes(self, s: str, k: int) -> bool:
        if len(s) - k + 1 < (1 << k):
            return False
        vis = [False] * (1 << k)
        num = int(s[:k], 2)
        vis[num] = True
        for i in range(k, len(s)):
            a = (ord(s[i - k]) - ord('0')) << (k - 1)
            b = ord(s[i]) - ord('0')
            num = ((num - a) << 1) + b
            vis[num] = True
        return all(v for v in vis)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean hasAllCodes(String s, int k) {
        Set<String> ss = new HashSet<>();
        for (int i = 0; i < s.length() - k + 1; ++i) {
            ss.add(s.substring(i, i + k));
        }
        return ss.size() == 1 << k;
    }
}
```

```java
class Solution {
    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        if (n - k + 1 < (1 << k)) {
            return false;
        }
        boolean[] vis = new boolean[1 << k];
        int num = Integer.parseInt(s.substring(0, k), 2);
        vis[num] = true;
        for (int i = k; i < n; ++i) {
            int a = (s.charAt(i - k) - '0') << (k - 1);
            int b = s.charAt(i) - '0';
            num = (num - a) << 1 | b;
            vis[num] = true;
        }
        for (boolean v : vis) {
            if (!v) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool hasAllCodes(string s, int k) {
        unordered_set<string> ss;
        for (int i = 0; i + k <= s.size(); ++i) {
            ss.insert(move(s.substr(i, k)));
        }
        return ss.size() == 1 << k;
    }
};
```

```cpp
class Solution {
public:
    bool hasAllCodes(string s, int k) {
        int n = s.size();
        if (n - k + 1 < (1 << k)) return false;
        vector<bool> vis(1 << k);
        int num = stoi(s.substr(0, k), nullptr, 2);
        vis[num] = true;
        for (int i = k; i < n; ++i) {
            int a = (s[i - k] - '0') << (k - 1);
            int b = s[i] - '0';
            num = (num - a) << 1 | b;
            vis[num] = true;
        }
        for (bool v : vis) if (!v) return false;
        return true;
    }
};
```

### **Go**

```go
func hasAllCodes(s string, k int) bool {
	ss := map[string]bool{}
	for i := 0; i+k <= len(s); i++ {
		ss[s[i:i+k]] = true
	}
	return len(ss) == 1<<k
}
```

```go
func hasAllCodes(s string, k int) bool {
	n := len(s)
	if n-k+1 < (1 << k) {
		return false
	}
	vis := make([]bool, 1<<k)
	num := 0
	for i := 0; i < k; i++ {
		num = num<<1 | int(s[i]-'0')
	}
	vis[num] = true
	for i := k; i < n; i++ {
		a := int(s[i-k]-'0') << (k - 1)
		num = (num-a)<<1 | int(s[i]-'0')
		vis[num] = true
	}
	for _, v := range vis {
		if !v {
			return false
		}
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
