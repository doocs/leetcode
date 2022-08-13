# [159. 至多包含两个不同字符的最长子串](https://leetcode.cn/problems/longest-substring-with-at-most-two-distinct-characters)

[English Version](/solution/0100-0199/0159.Longest%20Substring%20with%20At%20Most%20Two%20Distinct%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你一个字符串 <code>s</code> ，请你找出&nbsp;<strong>至多&nbsp;</strong>包含 <strong>两个不同字符</strong> 的最长子串，并返回该子串的长度。

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

<!-- 这里可写通用的实现逻辑 -->

哈希表 + 双指针。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def lengthOfLongestSubstringTwoDistinct(self, s: str) -> int:
        mp = Counter()
        i = j = ans = 0
        for c in s:
            mp[c] += 1
            while len(mp) > 2:
                mp[s[i]] -= 1
                if mp[s[i]] == 0:
                    mp.pop(s[i])
                i += 1
            ans = max(ans, j - i + 1)
            j += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> mp = new HashMap<>();
        int i = 0, j = 0, ans = 0;
        for (char c : s.toCharArray()) {
            mp.put(c, mp.getOrDefault(c, 0) + 1);
            while (mp.size() > 2) {
                char t = s.charAt(i);
                mp.put(t, mp.get(t) - 1);
                if (mp.get(t) == 0) {
                    mp.remove(t);
                }
                ++i;
            }
            ans = Math.max(ans, j - i + 1);
            ++j;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int lengthOfLongestSubstringTwoDistinct(string s) {
        unordered_map<char, int> mp;
        int i = 0, j = 0, ans = 0;
        for (char& c : s) {
            ++mp[c];
            while (mp.size() > 2) {
                --mp[s[i]];
                if (mp[s[i]] == 0) mp.erase(s[i]);
                ++i;
            }
            ans = max(ans, j - i + 1);
            ++j;
        }
        return ans;
    }
};
```

### **Go**

```go
func lengthOfLongestSubstringTwoDistinct(s string) int {
	mp := make(map[byte]int)
	i, j, ans := 0, 0, 0
	for _, c := range s {
		mp[byte(c)]++
		for len(mp) > 2 {
			mp[s[i]]--
			if mp[s[i]] == 0 {
				delete(mp, s[i])
			}
			i++
		}
		ans = max(ans, j-i+1)
		j++
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
