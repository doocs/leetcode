---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1370.Increasing%20Decreasing%20String/README.md
rating: 1369
source: 第 21 场双周赛 Q1
tags:
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [1370. 上升下降字符串](https://leetcode.cn/problems/increasing-decreasing-string)

[English Version](/solution/1300-1399/1370.Increasing%20Decreasing%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，请你根据下面的算法重新构造字符串：</p>

<ol>
	<li>从 <code>s</code>&nbsp;中选出 <strong>最小</strong>&nbsp;的字符，将它 <strong>接在</strong>&nbsp;结果字符串的后面。</li>
	<li>从 <code>s</code>&nbsp;剩余字符中选出&nbsp;<strong>最小</strong>&nbsp;的字符，且该字符比上一个添加的字符大，将它 <strong>接在</strong>&nbsp;结果字符串后面。</li>
	<li>重复步骤 2 ，直到你没法从 <code>s</code>&nbsp;中选择字符。</li>
	<li>从 <code>s</code>&nbsp;中选出 <strong>最大</strong>&nbsp;的字符，将它 <strong>接在</strong>&nbsp;结果字符串的后面。</li>
	<li>从 <code>s</code>&nbsp;剩余字符中选出&nbsp;<strong>最大</strong>&nbsp;的字符，且该字符比上一个添加的字符小，将它 <strong>接在</strong>&nbsp;结果字符串后面。</li>
	<li>重复步骤 5&nbsp;，直到你没法从 <code>s</code>&nbsp;中选择字符。</li>
	<li>重复步骤 1 到 6 ，直到 <code>s</code>&nbsp;中所有字符都已经被选过。</li>
</ol>

<p>在任何一步中，如果最小或者最大字符不止一个&nbsp;，你可以选择其中任意一个，并将其添加到结果字符串。</p>

<p>请你返回将&nbsp;<code>s</code>&nbsp;中字符重新排序后的 <strong>结果字符串</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;aaaabbbbcccc&quot;
<strong>输出：</strong>&quot;abccbaabccba&quot;
<strong>解释：</strong>第一轮的步骤 1，2，3 后，结果字符串为 result = &quot;abc&quot;
第一轮的步骤 4，5，6 后，结果字符串为 result = &quot;abccba&quot;
第一轮结束，现在 s = &quot;aabbcc&quot; ，我们再次回到步骤 1
第二轮的步骤 1，2，3 后，结果字符串为 result = &quot;abccbaabc&quot;
第二轮的步骤 4，5，6 后，结果字符串为 result = &quot;abccbaabccba&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;rat&quot;
<strong>输出：</strong>&quot;art&quot;
<strong>解释：</strong>单词 &quot;rat&quot; 在上述算法重排序以后变成 &quot;art&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;leetcode&quot;
<strong>输出：</strong>&quot;cdelotee&quot;
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s = &quot;ggggggg&quot;
<strong>输出：</strong>&quot;ggggggg&quot;
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>s = &quot;spo&quot;
<strong>输出：</strong>&quot;ops&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 模拟

我们先用一个哈希表或者一个长度为 $26$ 的数组 $cnt$ 统计字符串 $s$ 中每个字符出现的次数。

然后，我们枚举字母 $[a,...,z]$，对于当前枚举到的字母 $c$，如果 $cnt[c] \gt 0$，我们就将字母 $c$ 接在答案字符串的末尾，并将 $cnt[c]$ 减一。我们重复这一步骤，直到 $cnt[c]=0$。随后我们逆序枚举字母 $[z,...,a]$，执行类似的操作。如果答案字符串的长度等于 $s$ 的长度，那么我们就完成了所有的拼接操作。

时间复杂度 $O(n \times |\Sigma|)$，空间复杂度 $O(|\Sigma|)$。其中 $n$ 是字符串 $s$ 的长度，而 $\Sigma$ 是字符集，本题中字符集为所有小写字母，因此 $|\Sigma|=26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortString(self, s: str) -> str:
        cnt = Counter(s)
        cs = ascii_lowercase + ascii_lowercase[::-1]
        ans = []
        while len(ans) < len(s):
            for c in cs:
                if cnt[c]:
                    ans.append(c)
                    cnt[c] -= 1
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String sortString(String s) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            cnt[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < n) {
            for (int i = 0; i < 26; ++i) {
                if (cnt[i] > 0) {
                    sb.append((char) ('a' + i));
                    --cnt[i];
                }
            }
            for (int i = 25; i >= 0; --i) {
                if (cnt[i] > 0) {
                    sb.append((char) ('a' + i));
                    --cnt[i];
                }
            }
        }
        return sb.toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string sortString(string s) {
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        string ans;
        while (ans.size() < s.size()) {
            for (int i = 0; i < 26; ++i) {
                if (cnt[i]) {
                    ans += i + 'a';
                    --cnt[i];
                }
            }
            for (int i = 25; i >= 0; --i) {
                if (cnt[i]) {
                    ans += i + 'a';
                    --cnt[i];
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func sortString(s string) string {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	n := len(s)
	ans := make([]byte, 0, n)
	for len(ans) < n {
		for i := 0; i < 26; i++ {
			if cnt[i] > 0 {
				ans = append(ans, byte(i)+'a')
				cnt[i]--
			}
		}
		for i := 25; i >= 0; i-- {
			if cnt[i] > 0 {
				ans = append(ans, byte(i)+'a')
				cnt[i]--
			}
		}
	}
	return string(ans)
}
```

#### TypeScript

```ts
function sortString(s: string): string {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    const ans: string[] = [];
    while (ans.length < s.length) {
        for (let i = 0; i < 26; ++i) {
            if (cnt[i]) {
                ans.push(String.fromCharCode(i + 'a'.charCodeAt(0)));
                --cnt[i];
            }
        }
        for (let i = 25; i >= 0; --i) {
            if (cnt[i]) {
                ans.push(String.fromCharCode(i + 'a'.charCodeAt(0)));
                --cnt[i];
            }
        }
    }
    return ans.join('');
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @return {string}
 */
var sortString = function (s) {
    const cnt = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    const ans = [];
    while (ans.length < s.length) {
        for (let i = 0; i < 26; ++i) {
            if (cnt[i]) {
                ans.push(String.fromCharCode(i + 'a'.charCodeAt(0)));
                --cnt[i];
            }
        }
        for (let i = 25; i >= 0; --i) {
            if (cnt[i]) {
                ans.push(String.fromCharCode(i + 'a'.charCodeAt(0)));
                --cnt[i];
            }
        }
    }
    return ans.join('');
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
