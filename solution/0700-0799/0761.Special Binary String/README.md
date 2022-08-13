# [761. 特殊的二进制序列](https://leetcode.cn/problems/special-binary-string)

[English Version](/solution/0700-0799/0761.Special%20Binary%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>特殊的二进制序列是具有以下两个性质的二进制序列：</p>

<ul>
	<li>0 的数量与 1 的数量相等。</li>
	<li>二进制序列的每一个前缀码中 1 的数量要大于等于 0 的数量。</li>
</ul>

<p>给定一个特殊的二进制序列&nbsp;<code>S</code>，以字符串形式表示。定义一个<em>操作 </em>为首先选择&nbsp;<code>S</code>&nbsp;的两个连续且非空的特殊的子串，然后将它们交换。（两个子串为连续的当且仅当第一个子串的最后一个字符恰好为第二个子串的第一个字符的前一个字符。)</p>

<p>在任意次数的操作之后，交换后的字符串按照字典序排列的最大的结果是什么？</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> S = &quot;11011000&quot;
<strong>输出:</strong> &quot;11100100&quot;
<strong>解释:</strong>
将子串 &quot;10&quot; （在S[1]出现） 和 &quot;1100&quot; （在S[3]出现）进行交换。
这是在进行若干次操作后按字典序排列最大的结果。
</pre>

<p><strong>说明:</strong></p>

<ol>
	<li><code>S</code>&nbsp;的长度不超过&nbsp;<code>50</code>。</li>
	<li><code>S</code>&nbsp;保证为一个满足上述定义的<em>特殊 </em>的二进制序列。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归 + 排序**

我们可以把特殊的二进制序列看作“有效的括号”，$1$ 代表左括号，$0$ 代表右括号。例如，"11011000" 可以看作："(()(()))"。

交换两个连续非空的特殊子串，相当于交换两个相邻的有效括号，我们可以使用递归来解决这个问题。

我们将字符串 $s$ 中的每个“有效的括号”都看作一部分，递归处理，最后进行排序，得到最终答案。

时间复杂度 $O(n^2)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def makeLargestSpecial(self, s: str) -> str:
        if s == '':
            return ''
        ans = []
        cnt = 0
        i = j = 0
        while i < len(s):
            cnt += 1 if s[i] == '1' else -1
            if cnt == 0:
                ans.append('1' + self.makeLargestSpecial(s[j + 1 : i]) + '0')
                j = i + 1
            i += 1
        ans.sort(reverse=True)
        return ''.join(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String makeLargestSpecial(String s) {
        if ("".equals(s)) {
            return "";
        }
        List<String> ans = new ArrayList<>();
        int cnt = 0;
        for (int i = 0, j = 0; i < s.length(); ++i) {
            cnt += s.charAt(i) == '1' ? 1 : -1;
            if (cnt == 0) {
                String t = "1" + makeLargestSpecial(s.substring(j + 1, i)) + "0";
                ans.add(t);
                j = i + 1;
            }
        }
        ans.sort(Comparator.reverseOrder());
        return String.join("", ans);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string makeLargestSpecial(string s) {
        if (s == "") return s;
        vector<string> ans;
        int cnt = 0;
        for (int i = 0, j = 0; i < s.size(); ++i) {
            cnt += s[i] == '1' ? 1 : -1;
            if (cnt == 0) {
                ans.push_back("1" + makeLargestSpecial(s.substr(j + 1, i - j - 1)) + "0");
                j = i + 1;
            }
        }
        sort(ans.begin(), ans.end(), greater<string> {});
        return accumulate(ans.begin(), ans.end(), ""s);
    }
};
```

### **Go**

```go
func makeLargestSpecial(s string) string {
	if s == "" {
		return ""
	}
	ans := sort.StringSlice{}
	cnt := 0
	for i, j := 0, 0; i < len(s); i++ {
		if s[i] == '1' {
			cnt++
		} else {
			cnt--
		}
		if cnt == 0 {
			ans = append(ans, "1"+makeLargestSpecial(s[j+1:i])+"0")
			j = i + 1
		}
	}
	sort.Sort(sort.Reverse(ans))
	return strings.Join(ans, "")
}
```

### **...**

```

```

<!-- tabs:end -->
