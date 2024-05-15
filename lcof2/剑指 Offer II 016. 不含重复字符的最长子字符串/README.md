---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20016.%20%E4%B8%8D%E5%90%AB%E9%87%8D%E5%A4%8D%E5%AD%97%E7%AC%A6%E7%9A%84%E6%9C%80%E9%95%BF%E5%AD%90%E5%AD%97%E7%AC%A6%E4%B8%B2/README.md
---

# [剑指 Offer II 016. 不含重复字符的最长子字符串](https://leetcode.cn/problems/wtcaE1)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串 <code>s</code> ，请你找出其中不含有重复字符的&nbsp;<strong>最长连续子字符串&nbsp;</strong>的长度。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入: </strong>s = &quot;abcabcbb&quot;
<strong>输出: </strong>3
<strong>解释:</strong> 因为无重复字符的最长子字符串是 <code>&quot;abc&quot;，所以其</code>长度为 3。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>s = &quot;bbbbb&quot;
<strong>输出: </strong>1
<strong>解释: </strong>因为无重复字符的最长子字符串是 <code>&quot;b&quot;</code>，所以其长度为 1。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入: </strong>s = &quot;pwwkew&quot;
<strong>输出: </strong>3
<strong>解释: </strong>因为无重复字符的最长子串是&nbsp;<code>&quot;wke&quot;</code>，所以其长度为 3。
&nbsp;    请注意，你的答案必须是 <strong>子串 </strong>的长度，<code>&quot;pwke&quot;</code>&nbsp;是一个<em>子序列，</em>不是子串。
</pre>

<p><strong>示例 4:</strong></p>

<pre>
<strong>输入: </strong>s = &quot;&quot;
<strong>输出: </strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code>&nbsp;由英文字母、数字、符号和空格组成</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 3&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/">https://leetcode.cn/problems/longest-substring-without-repeating-characters/</a></p>

## 解法

### 方法一：双指针 + 哈希表

我们用两个指针 $j$ 和 $i$ 维护一个不包含重复字符的子串，其中 $j$ 为子串的左边界，$i$ 为子串的右边界，用一个哈希表或数组 $ss$ 记录窗口中所有出现过的字符。

接下来，我们遍历字符串 $s$，对于当前遍历到的字符 $s[i]$，如果 $s[i]$ 在 $[j, i)$ 范围内有与 $s[i]$ 相同的字符，我们就不断地向右移动指针 $j$，直到 $ss[s[i]]$ 为 `false`，此时 $[j,i)$ 中没有任何与 $s[i]$ 相同的字符，我们就找到了以字符 $s[i]$ 为结尾的最长子串。对于每个 $i$，我们都更新最长子串的长度，最终返回答案。

时间复杂度 $O(n)$，空间复杂度 $O(|\Sigma|)$，其中 $n$ 为字符串 $s$ 的长度，而 $\Sigma$ 表示字符集，本题中字符集为所有 ASCII 码在 $[0, 128)$ 内的字符，即 $|\Sigma|=128$。

<!-- tabs:start -->

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        ss = set()
        ans = j = 0
        for i, c in enumerate(s):
            while c in ss:
                ss.remove(s[j])
                j += 1
            ans = max(ans, i - j + 1)
            ss.add(c)
        return ans
```

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        boolean[] ss = new boolean[128];
        int ans = 0, j = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            while (ss[c]) {
                ss[s.charAt(j++)] = false;
            }
            ans = Math.max(ans, i - j + 1);
            ss[c] = true;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        bool ss[128] = {false};
        int n = s.size();
        int ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            while (ss[s[i]]) {
                ss[s[j++]] = false;
            }
            ss[s[i]] = true;
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

```go
func lengthOfLongestSubstring(s string) (ans int) {
	ss := make([]bool, 128)
	j := 0
	for i, c := range s {
		for ss[c] {
			ss[s[j]] = false
			j++
		}
		ss[c] = true
		ans = max(ans, i-j+1)
	}
	return
}
```

```ts
function lengthOfLongestSubstring(s: string): number {
    let ans = 0;
    const vis = new Set<string>();
    for (let i = 0, j = 0; i < s.length; ++i) {
        while (vis.has(s[i])) {
            vis.delete(s[j++]);
        }
        vis.add(s[i]);
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```ts
function lengthOfLongestSubstring(s: string): number {
    let ans = 0;
    const n = s.length;
    const ss: boolean[] = new Array(128).fill(false);
    for (let i = 0, j = 0; i < n; ++i) {
        while (ss[s[i]]) {
            ss[s[j++]] = false;
        }
        ss[s[i]] = true;
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
