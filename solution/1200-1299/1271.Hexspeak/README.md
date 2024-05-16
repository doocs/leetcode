---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1271.Hexspeak/README.md
rating: 1384
source: 第 14 场双周赛 Q1
tags:
    - 数学
    - 字符串
---

# [1271. 十六进制魔术数字 🔒](https://leetcode.cn/problems/hexspeak)

[English Version](/solution/1200-1299/1271.Hexspeak/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有一个十进制数字，请按照此规则将它变成「十六进制魔术数字」：首先将它变成字母大写的十六进制字符串，然后将所有的数字&nbsp;<code>0</code> 变成字母&nbsp;<code>O</code> ，将数字&nbsp;<code>1</code> &nbsp;变成字母&nbsp;<code>I</code> 。</p>

<p>如果一个数字在转换后只包含&nbsp;<code>{&quot;A&quot;, &quot;B&quot;, &quot;C&quot;, &quot;D&quot;, &quot;E&quot;, &quot;F&quot;, &quot;I&quot;, &quot;O&quot;}</code>&nbsp;，那么我们就认为这个转换是有效的。</p>

<p>给你一个字符串&nbsp;<code>num</code> ，它表示一个十进制数 <code>N</code>，如果它的十六进制魔术数字转换是有效的，请返回转换后的结果，否则返回&nbsp;<code>&quot;ERROR&quot;</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num = &quot;257&quot;
<strong>输出：</strong>&quot;IOI&quot;
<strong>解释：</strong>257 的十六进制表示是 101 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num = &quot;3&quot;
<strong>输出：</strong>&quot;ERROR&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= N &lt;= 10^12</code></li>
	<li>给定字符串不会有前导 0 。</li>
	<li>结果中的所有字母都应该是大写字母。</li>
</ul>

## 解法

### 方法一：模拟

将数字转换为十六进制字符串，然后遍历字符串，将数字 $0$ 转换为字母 $O$，将数字 $1$ 转换为字母 $I$，最后判断转换后的字符串是否合法。

时间复杂度 $O(\log n)$，其中 $n$ 为 $num$ 所表示的十进制数字的大小。

<!-- tabs:start -->

```python
class Solution:
    def toHexspeak(self, num: str) -> str:
        s = set('ABCDEFIO')
        t = hex(int(num))[2:].upper().replace('0', 'O').replace('1', 'I')
        return t if all(c in s for c in t) else 'ERROR'
```

```java
class Solution {
    private static final Set<Character> S = Set.of('A', 'B', 'C', 'D', 'E', 'F', 'I', 'O');

    public String toHexspeak(String num) {
        String t
            = Long.toHexString(Long.valueOf(num)).toUpperCase().replace("0", "O").replace("1", "I");
        for (char c : t.toCharArray()) {
            if (!S.contains(c)) {
                return "ERROR";
            }
        }
        return t;
    }
}
```

```cpp
class Solution {
public:
    string toHexspeak(string num) {
        stringstream ss;
        ss << hex << stol(num);
        string t = ss.str();
        for (int i = 0; i < t.size(); ++i) {
            if (t[i] >= '2' && t[i] <= '9') return "ERROR";
            if (t[i] == '0')
                t[i] = 'O';
            else if (t[i] == '1')
                t[i] = 'I';
            else
                t[i] = t[i] - 32;
        }
        return t;
    }
};
```

```go
func toHexspeak(num string) string {
	x, _ := strconv.Atoi(num)
	t := strings.ToUpper(fmt.Sprintf("%x", x))
	t = strings.ReplaceAll(t, "0", "O")
	t = strings.ReplaceAll(t, "1", "I")
	for _, c := range t {
		if c >= '2' && c <= '9' {
			return "ERROR"
		}
	}
	return t
}
```

<!-- tabs:end -->

<!-- end -->
