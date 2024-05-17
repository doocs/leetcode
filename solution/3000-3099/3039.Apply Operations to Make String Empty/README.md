---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3039.Apply%20Operations%20to%20Make%20String%20Empty/README.md
rating: 1423
source: 第 124 场双周赛 Q2
tags:
    - 数组
    - 哈希表
    - 计数
    - 排序
---

<!-- problem:start -->

# [3039. 进行操作使字符串为空](https://leetcode.cn/problems/apply-operations-to-make-string-empty)

[English Version](/solution/3000-3099/3039.Apply%20Operations%20to%20Make%20String%20Empty/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;。</p>

<p>请你进行以下操作直到 <code>s</code>&nbsp;为 <strong>空</strong>&nbsp;：</p>

<ul>
	<li>每次操作 <strong>依次</strong> 遍历 <code>'a'</code> 到 <code>'z'</code>，如果当前字符出现在 <code>s</code> 中，那么删除出现位置&nbsp;<strong>最早</strong>&nbsp;的该字符（如果存在的话）。</li>
</ul>

<p>例如，最初 <code>s = "aabcbbca"</code>。我们执行下述操作：</p>

<ul>
	<li>移除下划线的字符&nbsp; <code>s = "<u><strong>a</strong></u>a<u><strong>bc</strong></u>bbca"</code>。结果字符串为 <code>s = "abbca"</code>。</li>
	<li>移除下划线的字符&nbsp; <code>s = "<u><strong>ab</strong></u>b<u><strong>c</strong></u>a"</code>。结果字符串为 <code>s = "ba"</code>。</li>
	<li>移除下划线的字符&nbsp; <code>s = "<u><strong>ba</strong></u>"</code>。结果字符串为 <code>s = ""</code>。</li>
</ul>

<p>请你返回进行 <strong>最后</strong>&nbsp;一次操作 <strong>之前</strong>&nbsp;的字符串<em>&nbsp;</em><code>s</code><em>&nbsp;</em>。在上面的例子中，答案是&nbsp;<code>"ba"</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>s = "aabcbbca"
<b>输出：</b>"ba"
<b>解释：</b>已经在题目描述中解释。
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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表或数组

我们用一个哈希表或数组 $cnt$ 记录字符串 $s$ 中每个字符的出现次数，用一个哈希表或数组 $last$ 记录字符串 $s$ 中每个字符最后一次出现的位置。字符串 $s$ 中出现次数最多的字符的出现次数记为 $mx$。

然后我们遍历字符串 $s$，如果当前字符的出现次数等于 $mx$ 且当前字符所在位置等于该字符最后一次出现的位置，那么我们将当前字符加入答案中。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(|\Sigma|)$，其中 $n$ 是字符串 $s$ 的长度，而 $\Sigma$ 是字符集，本题中 $\Sigma$ 为小写英文字母。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lastNonEmptyString(self, s: str) -> str:
        cnt = Counter(s)
        mx = cnt.most_common(1)[0][1]
        last = {c: i for i, c in enumerate(s)}
        return "".join(c for i, c in enumerate(s) if cnt[c] == mx and last[c] == i)
```

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

<!-- solution:end -->

<!-- problem:end -->
