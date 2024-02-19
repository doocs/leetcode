# [3039. 进行操作使字符串为空](https://leetcode.cn/problems/apply-operations-to-make-string-empty)

[English Version](/solution/3000-3099/3039.Apply%20Operations%20to%20Make%20String%20Empty/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;。</p>

<p>请你进行以下操作直到 <code>s</code>&nbsp;为 <strong>空</strong>&nbsp;：</p>

<ul>
	<li>每次操作 <strong>依次</strong> 遍历 <code>'a'</code> 到 <code>'z'</code>，如果当前字符出现在 <code>s</code> 中，那么删除出现位置&nbsp;<strong>最早</strong>&nbsp;的该字符。</li>
</ul>

<p>请你返回进行 <strong>最后</strong>&nbsp;一次操作 <strong>之前</strong>&nbsp;的字符串<em>&nbsp;</em><code>s</code><em>&nbsp;</em>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>s = "aabcbbca"
<b>输出：</b>"ba"
<b>解释：</b>我们进行以下操作：
- 删除 s = "<em><strong>a</strong></em>a<em><strong>bc</strong></em>bbca" 中加粗加斜字符，得到字符串 s = "abbca" 。
- 删除 s = "<em><strong>ab</strong></em>b<em><strong>c</strong></em>a" 中加粗加斜字符，得到字符串 s = "ba" 。
- 删除 s = "<em><strong>ba</strong></em>" 中加粗加斜字符，得到字符串 s = "" 。
进行最后一次操作之前的字符串为 "ba" 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>s = "abcd"
<b>输出：</b>"abcd"
<b>解释：</b>我们进行以下操作：
- 删除 s = "<em><strong>abcd</strong></em>" 中加粗加斜字符，得到字符串 s = "" 。
进行最后一次操作之前的字符串为 "abcd" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def lastNonEmptyString(self, s: str) -> str:
        cnt = Counter(s)
        mx = cnt.most_common(1)[0][1]
        last = {c: i for i, c in enumerate(s)}
        return "".join(c for i, c in enumerate(s) if cnt[c] == mx and last[c] == i)
```

```java
class Solution {
    public String lastNonEmptyString(String s) {
        int[] cnt = new int[26];
        int[] last = new int[26];
        int n = s.length();
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int c = s.charAt(i) - 'a';
            mx = Math.max(mx, ++cnt[c]);
            last[c] = i;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            int c = s.charAt(i) - 'a';
            if (cnt[c] == mx && last[c] == i) {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }
}
```

```cpp
class Solution {
public:
    string lastNonEmptyString(string s) {
        int cnt[26]{};
        int last[26]{};
        int n = s.size();
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int c = s[i] - 'a';
            mx = max(mx, ++cnt[c]);
            last[c] = i;
        }
        string ans;
        for (int i = 0; i < n; ++i) {
            int c = s[i] - 'a';
            if (cnt[c] == mx && last[c] == i) {
                ans.push_back(s[i]);
            }
        }
        return ans;
    }
};
```

```go
func lastNonEmptyString(s string) string {
	cnt := [26]int{}
	last := [26]int{}
	mx := 0
	for i, c := range s {
		c -= 'a'
		cnt[c]++
		last[c] = i
		mx = max(mx, cnt[c])
	}
	ans := []rune{}
	for i, c := range s {
		if cnt[c-'a'] == mx && last[c-'a'] == i {
			ans = append(ans, c)
		}
	}
	return string(ans)
}
```

```ts
function lastNonEmptyString(s: string): string {
    const cnt: number[] = Array(26).fill(0);
    const last: number[] = Array(26).fill(0);
    const n = s.length;
    let mx = 0;
    for (let i = 0; i < n; ++i) {
        const c = s.charCodeAt(i) - 97;
        mx = Math.max(mx, ++cnt[c]);
        last[c] = i;
    }
    const ans: string[] = [];
    for (let i = 0; i < n; ++i) {
        const c = s.charCodeAt(i) - 97;
        if (cnt[c] === mx && last[c] === i) {
            ans.push(String.fromCharCode(c + 97));
        }
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- end -->
