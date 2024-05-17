---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0423.Reconstruct%20Original%20Digits%20from%20English/README.md
tags:
    - 哈希表
    - 数学
    - 字符串
---

<!-- problem:start -->

# [423. 从英文中重建数字](https://leetcode.cn/problems/reconstruct-original-digits-from-english)

[English Version](/solution/0400-0499/0423.Reconstruct%20Original%20Digits%20from%20English/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> ，其中包含字母顺序打乱的用英文单词表示的若干数字（<code>0-9</code>）。按 <strong>升序</strong> 返回原始的数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "owoztneoer"
<strong>输出：</strong>"012"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "fviefuro"
<strong>输出：</strong>"45"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 为 <code>["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"]</code> 这些字符之一</li>
	<li><code>s</code> 保证是一个符合题目要求的字符串</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def originalDigits(self, s: str) -> str:
        counter = Counter(s)
        cnt = [0] * 10

        cnt[0] = counter['z']
        cnt[2] = counter['w']
        cnt[4] = counter['u']
        cnt[6] = counter['x']
        cnt[8] = counter['g']

        cnt[3] = counter['h'] - cnt[8]
        cnt[5] = counter['f'] - cnt[4]
        cnt[7] = counter['s'] - cnt[6]

        cnt[1] = counter['o'] - cnt[0] - cnt[2] - cnt[4]
        cnt[9] = counter['i'] - cnt[5] - cnt[6] - cnt[8]

        return ''.join(cnt[i] * str(i) for i in range(10))
```

```java
class Solution {
    public String originalDigits(String s) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        int[] cnt = new int[10];
        cnt[0] = counter['z' - 'a'];
        cnt[2] = counter['w' - 'a'];
        cnt[4] = counter['u' - 'a'];
        cnt[6] = counter['x' - 'a'];
        cnt[8] = counter['g' - 'a'];

        cnt[3] = counter['h' - 'a'] - cnt[8];
        cnt[5] = counter['f' - 'a'] - cnt[4];
        cnt[7] = counter['s' - 'a'] - cnt[6];

        cnt[1] = counter['o' - 'a'] - cnt[0] - cnt[2] - cnt[4];
        cnt[9] = counter['i' - 'a'] - cnt[5] - cnt[6] - cnt[8];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < cnt[i]; ++j) {
                sb.append(i);
            }
        }
        return sb.toString();
    }
}
```

```cpp
class Solution {
public:
    string originalDigits(string s) {
        vector<int> counter(26);
        for (char c : s) ++counter[c - 'a'];
        vector<int> cnt(10);
        cnt[0] = counter['z' - 'a'];
        cnt[2] = counter['w' - 'a'];
        cnt[4] = counter['u' - 'a'];
        cnt[6] = counter['x' - 'a'];
        cnt[8] = counter['g' - 'a'];

        cnt[3] = counter['h' - 'a'] - cnt[8];
        cnt[5] = counter['f' - 'a'] - cnt[4];
        cnt[7] = counter['s' - 'a'] - cnt[6];

        cnt[1] = counter['o' - 'a'] - cnt[0] - cnt[2] - cnt[4];
        cnt[9] = counter['i' - 'a'] - cnt[5] - cnt[6] - cnt[8];

        string ans;
        for (int i = 0; i < 10; ++i)
            for (int j = 0; j < cnt[i]; ++j)
                ans += char(i + '0');
        return ans;
    }
};
```

```go
func originalDigits(s string) string {
	counter := make([]int, 26)
	for _, c := range s {
		counter[c-'a']++
	}
	cnt := make([]int, 10)
	cnt[0] = counter['z'-'a']
	cnt[2] = counter['w'-'a']
	cnt[4] = counter['u'-'a']
	cnt[6] = counter['x'-'a']
	cnt[8] = counter['g'-'a']

	cnt[3] = counter['h'-'a'] - cnt[8]
	cnt[5] = counter['f'-'a'] - cnt[4]
	cnt[7] = counter['s'-'a'] - cnt[6]

	cnt[1] = counter['o'-'a'] - cnt[0] - cnt[2] - cnt[4]
	cnt[9] = counter['i'-'a'] - cnt[5] - cnt[6] - cnt[8]

	ans := []byte{}
	for i, c := range cnt {
		ans = append(ans, bytes.Repeat([]byte{byte('0' + i)}, c)...)
	}
	return string(ans)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
