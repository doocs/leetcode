# [159. 至多包含两个不同字符的最长子串 🔒](https://leetcode.cn/problems/longest-substring-with-at-most-two-distinct-characters)

[English Version](/solution/0100-0199/0159.Longest%20Substring%20with%20At%20Most%20Two%20Distinct%20Characters/README_EN.md)

<!-- tags:哈希表,字符串,滑动窗口 -->

## 题目描述

<!-- 这里写题目描述 -->

给你一个字符串 <code>s</code> ，请你找出&nbsp;<strong>至多&nbsp;</strong>包含 <strong>两个不同字符</strong> 的最长<span data-keyword="substring">子串</span>，并返回该子串的长度。

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "eceba"
<strong>输出：</strong>3
<strong>解释：</strong>满足题目要求的子串是 "ece" ，长度为 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "ccaabbb"
<strong>输出：</strong>5
<strong>解释：</strong>满足题目要求的子串是 "aabbb" ，长度为 5 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由英文字母组成</li>
</ul>

## 解法

### 方法一：哈希表 + 滑动窗口

我们维护一个哈希表 `cnt` 记录当前滑动窗口中各个字符出现的次数，如果哈希表中的键值对个数超过 $2$，则说明当前滑动窗口中包含了超过 $2$ 个不同的字符，此时需要移动左指针 `j`，直到哈希表中的键值对个数不超过 $2$ 为止，然后更新窗口的最大长度。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def lengthOfLongestSubstringTwoDistinct(self, s: str) -> int:
        cnt = Counter()
        ans = j = 0
        for i, c in enumerate(s):
            cnt[c] += 1
            while len(cnt) > 2:
                cnt[s[j]] -= 1
                if cnt[s[j]] == 0:
                    cnt.pop(s[j])
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

```java
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> cnt = new HashMap<>();
        int n = s.length();
        int ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            char c = s.charAt(i);
            cnt.put(c, cnt.getOrDefault(c, 0) + 1);
            while (cnt.size() > 2) {
                char t = s.charAt(j++);
                cnt.put(t, cnt.get(t) - 1);
                if (cnt.get(t) == 0) {
                    cnt.remove(t);
                }
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int lengthOfLongestSubstringTwoDistinct(string s) {
        unordered_map<char, int> cnt;
        int n = s.size();
        int ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            cnt[s[i]]++;
            while (cnt.size() > 2) {
                cnt[s[j]]--;
                if (cnt[s[j]] == 0) {
                    cnt.erase(s[j]);
                }
                ++j;
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

```go
func lengthOfLongestSubstringTwoDistinct(s string) (ans int) {
	cnt := map[byte]int{}
	j := 0
	for i := range s {
		cnt[s[i]]++
		for len(cnt) > 2 {
			cnt[s[j]]--
			if cnt[s[j]] == 0 {
				delete(cnt, s[j])
			}
			j++
		}
		ans = max(ans, i-j+1)
	}
	return
}
```

<!-- tabs:end -->

<!-- end -->
