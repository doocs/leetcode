---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1957.Delete%20Characters%20to%20Make%20Fancy%20String/README.md
rating: 1357
source: 第 58 场双周赛 Q1
tags:
    - 字符串
---

<!-- problem:start -->

# [1957. 删除字符使字符串变好](https://leetcode.cn/problems/delete-characters-to-make-fancy-string)

[English Version](/solution/1900-1999/1957.Delete%20Characters%20to%20Make%20Fancy%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个字符串如果没有 <strong>三个连续</strong>&nbsp;相同字符，那么它就是一个 <strong>好字符串</strong>&nbsp;。</p>

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，请你从 <code>s</code>&nbsp;删除&nbsp;<strong>最少</strong>&nbsp;的字符，使它变成一个 <strong>好字符串</strong> 。</p>

<p>请你返回删除后的字符串。题目数据保证答案总是 <strong>唯一的 </strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "le<strong>e</strong>etcode"
<b>输出：</b>"leetcode"
<strong>解释：</strong>
从第一组 'e' 里面删除一个 'e' ，得到 "leetcode" 。
没有连续三个相同字符，所以返回 "leetcode" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "<strong>a</strong>aab<strong>aa</strong>aa"
<b>输出：</b>"aabaa"
<strong>解释：</strong>
从第一组 'a' 里面删除一个 'a' ，得到 "aabaaaa" 。
从第二组 'a' 里面删除两个 'a' ，得到 "aabaa" 。
没有连续三个相同字符，所以返回 "aabaa" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>s = "aab"
<b>输出：</b>"aab"
<b>解释：</b>没有连续三个相同字符，所以返回 "aab" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def makeFancyString(self, s: str) -> str:
        ans = []
        for c in s:
            if len(ans) > 1 and ans[-1] == ans[-2] == c:
                continue
            ans.append(c)
        return ''.join(ans)
```

```java
class Solution {
    public String makeFancyString(String s) {
        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray()) {
            int n = ans.length();
            if (n > 1 && ans.charAt(n - 1) == c && ans.charAt(n - 2) == c) {
                continue;
            }
            ans.append(c);
        }
        return ans.toString();
    }
}
```

```cpp
class Solution {
public:
    string makeFancyString(string s) {
        string ans = "";
        for (char& c : s) {
            int n = ans.size();
            if (n > 1 && ans[n - 1] == c && ans[n - 2] == c) continue;
            ans.push_back(c);
        }
        return ans;
    }
};
```

```go
func makeFancyString(s string) string {
	ans := []rune{}
	for _, c := range s {
		n := len(ans)
		if n > 1 && ans[n-1] == c && ans[n-2] == c {
			continue
		}
		ans = append(ans, c)
	}
	return string(ans)
}
```

```php
class Solution {
    /**
     * @param String $s
     * @return String
     */
    function makeFancyString($s) {
        $rs = '';
        for ($i = 0; $i < strlen($s); $i++) {
            if ($s[$i] == $s[$i + 1] && $s[$i] == $s[$i + 2]) {
                continue;
            } else {
                $rs .= $s[$i];
            }
        }
        return $rs;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
