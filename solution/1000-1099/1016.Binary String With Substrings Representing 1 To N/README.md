# [1016. 子串能表示从 1 到 N 数字的二进制串](https://leetcode.cn/problems/binary-string-with-substrings-representing-1-to-n)

[English Version](/solution/1000-1099/1016.Binary%20String%20With%20Substrings%20Representing%201%20To%20N/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二进制字符串&nbsp;<code>s</code>&nbsp;和一个正整数&nbsp;<code>n</code>，如果对于&nbsp;<code>[1, n]</code>&nbsp;范围内的每个整数，<em>其二进制表示都是&nbsp;<code>s</code> 的 <strong>子字符串</strong> ，就返回 <code>true</code>，否则返回 <code>false</code>&nbsp;</em>。</p>

<p><strong>子字符串</strong>&nbsp;是字符串中连续的字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "0110", n = 3
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "0110", n = 4
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code>&nbsp;不是&nbsp;<code>'0'</code>&nbsp;就是&nbsp;<code>'1'</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：脑筋急转弯**

我们注意到，字符串 $s$ 的长度不超过 $1000$，因此，字符串 $s$ 能表示不超过 $100$ 个 $10$ 位的二进制整数，因此，我们可以粗略地估算，如果 $n \gt 1023$，那么 $s$ 肯定不能表示 $[1, n]$ 范围内的所有整数的二进制表示。

另外，对于一个整数 $x$，如果 $x$ 的二进制表示是 $s$ 的子串，那么 $\lfloor x / 2 \rfloor$ 的二进制表示也是 $s$ 的子串。因此，我们只需要判断 $[\lfloor n / 2 \rfloor + 1,.. n]$ 范围内的整数的二进制表示是否是 $s$ 的子串即可。

时间复杂度 $O(m^2)，空间复杂度 $O(m)$，其中 $m$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def queryString(self, s: str, n: int) -> bool:
        if n > 1023:
            return False
        return all(bin(i)[2:] in s for i in range(n, n // 2, -1))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean queryString(String s, int n) {
        for (int i = n; i > n / 2; i--) {
            if (!s.contains(Integer.toBinaryString(i))) {
                return false;
            }
        }
        return true;
    }
}
```

```java
class Solution {
    public boolean queryString(String s, int n) {
        for (int i = 1; i <= n; ++i) {
            if (!s.contains(Integer.toBinaryString(i))) {
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
    bool queryString(string s, int n) {
        for (int i = n; i > n / 2; --i) {
            string b = bitset<32>(i).to_string();
            b = b.substr(b.find_first_not_of('0'));
            if (s.find(b) == string::npos) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func queryString(s string, n int) bool {
	for i := n; i > n/2; i-- {
		if !strings.Contains(s, strconv.FormatInt(int64(i), 2)) {
			return false
		}
	}
	return true
}
```

### **TypeScript**

```ts
function queryString(s: string, n: number): boolean {
    for (let i = n; i > n / 2; --i) {
        if (s.indexOf(i.toString(2)) === -1) {
            return false;
        }
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
