# [159. 至多包含两个不同字符的最长子串](https://leetcode-cn.com/problems/longest-substring-with-at-most-two-distinct-characters)

[English Version](/solution/0100-0199/0159.Longest%20Substring%20with%20At%20Most%20Two%20Distinct%20Characters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串<strong><em> s</em></strong> ，找出&nbsp;<strong>至多&nbsp;</strong>包含两个不同字符的最长子串 <strong><em>t</em> </strong>，并返回该子串的长度。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> &quot;eceba&quot;
<strong>输出: </strong>3
<strong>解释: <em>t</em></strong> 是 &quot;ece&quot;，长度为3。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> &quot;ccaabbb&quot;
<strong>输出: </strong>5
<strong>解释: <em>t</em></strong><em> </em>是 &quot;aabbb&quot;，长度为5。
</pre>

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
        for (char& c : s)
        {
            ++mp[c];
            while (mp.size() > 2)
            {
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
