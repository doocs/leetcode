# [2840. 判断通过操作能否让字符串相等 II](https://leetcode.cn/problems/check-if-strings-can-be-made-equal-with-operations-ii)

[English Version](/solution/2800-2899/2840.Check%20if%20Strings%20Can%20be%20Made%20Equal%20With%20Operations%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code>&nbsp;，两个字符串长度都为&nbsp;<code>n</code>&nbsp;，且只包含&nbsp;<strong>小写&nbsp;</strong>英文字母。</p>

<p>你可以对两个字符串中的 <strong>任意一个</strong>&nbsp;执行以下操作 <strong>任意</strong>&nbsp;次：</p>

<ul>
	<li>选择两个下标&nbsp;<code>i</code> 和&nbsp;<code>j</code>&nbsp;，满足 <code>i &lt; j</code>&nbsp;且 <code>j - i</code>&nbsp;是 <strong>偶数</strong>，然后 <strong>交换</strong> 这个字符串中两个下标对应的字符。</li>
</ul>

<p>&nbsp;</p>

<p>如果你可以让字符串<em>&nbsp;</em><code>s1</code><em> </em>和<em>&nbsp;</em><code>s2</code>&nbsp;相等，那么返回 <code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>s1 = "abcdba", s2 = "cabdab"
<b>输出：</b>true
<b>解释：</b>我们可以对 s1 执行以下操作：
- 选择下标 i = 0 ，j = 2 ，得到字符串 s1 = "cbadba" 。
- 选择下标 i = 2 ，j = 4 ，得到字符串 s1 = "cbbdaa" 。
- 选择下标 i = 1 ，j = 5 ，得到字符串 s1 = "cabdab" = s2 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>s1 = "abe", s2 = "bea"
<b>输出：</b>false
<b>解释：</b>无法让两个字符串相等。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == s1.length == s2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>s1</code> 和&nbsp;<code>s2</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def checkStrings(self, s1: str, s2: str) -> bool:
        return sorted(s1[::2]) == sorted(s2[::2]) and sorted(s1[1::2]) == sorted(
            s2[1::2]
        )
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkStrings(String s1, String s2) {
        int[][] cnt = new int[2][26];
        for (int i = 0; i < s1.length(); ++i) {
            ++cnt[i & 1][s1.charAt(i) - 'a'];
            --cnt[i & 1][s2.charAt(i) - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (cnt[0][i] != 0 || cnt[1][i] != 0) {
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
    bool checkStrings(string s1, string s2) {
        vector<vector<int>> cnt(2, vector<int>(26, 0));
        for (int i = 0; i < s1.size(); ++i) {
            ++cnt[i & 1][s1[i] - 'a'];
            --cnt[i & 1][s2[i] - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (cnt[0][i] || cnt[1][i]) {
                return false;
            }
        }
        return true;
    }
};
```

### **Go**

```go
func checkStrings(s1 string, s2 string) bool {
	cnt := [2][26]int{}
	for i := 0; i < len(s1); i++ {
		cnt[i&1][s1[i]-'a']++
		cnt[i&1][s2[i]-'a']--
	}
	for i := 0; i < 26; i++ {
		if cnt[0][i] != 0 || cnt[1][i] != 0 {
			return false
		}
	}
	return true
}
```

### **TypeScript**

```ts
function checkStrings(s1: string, s2: string): boolean {
    const cnt: number[][] = Array.from({ length: 2 }, () => Array.from({ length: 26 }, () => 0));
    for (let i = 0; i < s1.length; ++i) {
        ++cnt[i & 1][s1.charCodeAt(i) - 97];
        --cnt[i & 1][s2.charCodeAt(i) - 97];
    }
    for (let i = 0; i < 26; ++i) {
        if (cnt[0][i] || cnt[1][i]) {
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
