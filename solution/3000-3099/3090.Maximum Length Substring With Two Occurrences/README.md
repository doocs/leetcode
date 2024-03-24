# [3090. 每个字符最多出现两次的最长子字符串](https://leetcode.cn/problems/maximum-length-substring-with-two-occurrences)

[English Version](/solution/3000-3099/3090.Maximum%20Length%20Substring%20With%20Two%20Occurrences/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，请找出满足每个字符最多出现两次的最长子字符串，并返回该<span data-keyword="substring">子字符串</span>的<strong> 最大 </strong>长度。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "bcbbbcba"</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>以下子字符串长度为 4，并且每个字符最多出现两次：<code>"bcbb<u>bcba</u>"</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "aaaa"</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>以下子字符串长度为 2，并且每个字符最多出现两次：<code>"<u>aa</u>aa"</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul><!-- 字符串 s 的长度在 2 到 100 之间 -->
	<li><code>2 &lt;= s.length &lt;= 100</code></li>
	<!-- 字符串 s 仅包含小写英文字母 -->
	<li><code>s</code> 仅由小写英文字母组成。</li>
</ul>

## 解法

### 方法一：双指针

我们用两个指针 $i$ 和 $j$ 来维护一个滑动窗口，用一个数组 $cnt$ 来记录窗口中每个字符的出现次数。

每一次，我们将指针 $j$ 对应的字符 $c$ 加入窗口，然后判断 $cnt[c]$ 是否大于 $2$，如果大于 $2$，则将指针 $i$ 循环右移，直到 $cnt[c]$ 小于等于 $2$。此时，我们更新答案 $ans = \max(ans, j - i + 1)$。

最终，我们返回答案 $ans$。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(|\Sigma|)$，其中 $\Sigma$ 为字符集，本题中 $\Sigma = 26$。

<!-- tabs:start -->

```python
class Solution:
    def maximumLengthSubstring(self, s: str) -> int:
        cnt = Counter()
        ans = i = 0
        for j, c in enumerate(s):
            cnt[c] += 1
            while cnt[c] > 2:
                cnt[s[i]] -= 1
                i += 1
            ans = max(ans, j - i + 1)
        return ans
```

```java
class Solution {
    public int maximumLengthSubstring(String s) {
        int[] cnt = new int[26];
        int ans = 0;
        for (int i = 0, j = 0; j < s.length(); ++j) {
            int idx = s.charAt(j) - 'a';
            ++cnt[idx];
            while (cnt[idx] > 2) {
                --cnt[s.charAt(i++) - 'a'];
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumLengthSubstring(string s) {
        int cnt[26]{};
        int ans = 0;
        for (int i = 0, j = 0; j < s.length(); ++j) {
            int idx = s[j] - 'a';
            ++cnt[idx];
            while (cnt[idx] > 2) {
                --cnt[s[i++] - 'a'];
            }
            ans = max(ans, j - i + 1);
        }
        return ans;
    }
};
```

```go
func maximumLengthSubstring(s string) (ans int) {
	cnt := [26]int{}
	i := 0
	for j, c := range s {
		idx := c - 'a'
		cnt[idx]++
		for cnt[idx] > 2 {
			cnt[s[i]-'a']--
			i++
		}
		ans = max(ans, j-i+1)
	}
	return
}
```

```ts
function maximumLengthSubstring(s: string): number {
    let ans = 0;
    const cnt: number[] = Array(26).fill(0);
    for (let i = 0, j = 0; j < s.length; ++j) {
        const idx = s[j].charCodeAt(0) - 'a'.charCodeAt(0);
        ++cnt[idx];
        while (cnt[idx] > 2) {
            --cnt[s[i++].charCodeAt(0) - 'a'.charCodeAt(0)];
        }
        ans = Math.max(ans, j - i + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
