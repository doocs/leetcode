# [696. 计数二进制子串](https://leetcode.cn/problems/count-binary-substrings)

[English Version](/solution/0600-0699/0696.Count%20Binary%20Substrings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串&nbsp;<code>s</code>，统计并返回具有相同数量 <code>0</code> 和 <code>1</code> 的非空（连续）子字符串的数量，并且这些子字符串中的所有 <code>0</code> 和所有 <code>1</code> 都是成组连续的。</p>

<p>重复出现（不同位置）的子串也要统计它们出现的次数。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "00110011"
<strong>输出：</strong>6
<strong>解释：</strong>6 个子串满足具有相同数量的连续 1 和 0 ："0011"、"01"、"1100"、"10"、"0011" 和 "01" 。
注意，一些重复出现的子串（不同位置）要统计它们出现的次数。
另外，"00110011" 不是有效的子串，因为所有的 0（还有 1 ）没有组合在一起。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "10101"
<strong>输出：</strong>4
<strong>解释：</strong>有 4 个子串："10"、"01"、"10"、"01" ，具有相同数量的连续 1 和 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 为 <code>'0'</code> 或 <code>'1'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countBinarySubstrings(self, s: str) -> int:
        i, n = 0, len(s)
        t = []
        while i < n:
            cnt = 1
            while i + 1 < n and s[i + 1] == s[i]:
                cnt += 1
                i += 1
            t.append(cnt)
            i += 1
        ans = 0
        for i in range(1, len(t)):
            ans += min(t[i - 1], t[i])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countBinarySubstrings(String s) {
        int i = 0, n = s.length();
        List<Integer> t = new ArrayList<>();
        while (i < n) {
            int cnt = 1;
            while (i + 1 < n && s.charAt(i + 1) == s.charAt(i)) {
                ++i;
                ++cnt;
            }
            t.add(cnt);
            ++i;
        }
        int ans = 0;
        for (i = 1; i < t.size(); ++i) {
            ans += Math.min(t.get(i - 1), t.get(i));
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countBinarySubstrings(string s) {
        int i = 0, n = s.size();
        vector<int> t;
        while (i < n) {
            int cnt = 1;
            while (i + 1 < n && s[i + 1] == s[i]) {
                ++cnt;
                ++i;
            }
            t.push_back(cnt);
            ++i;
        }
        int ans = 0;
        for (i = 1; i < t.size(); ++i) ans += min(t[i - 1], t[i]);
        return ans;
    }
};
```

### **Go**

```go
func countBinarySubstrings(s string) int {
	i, n := 0, len(s)
	var t []int
	for i < n {
		cnt := 1
		for i+1 < n && s[i+1] == s[i] {
			i++
			cnt++
		}
		t = append(t, cnt)
		i++
	}
	ans := 0
	for i := 1; i < len(t); i++ {
		ans += min(t[i-1], t[i])
	}
	return ans
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
