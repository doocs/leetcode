# [159. Longest Substring with At Most Two Distinct Characters](https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters)

[中文文档](/solution/0100-0199/0159.Longest%20Substring%20with%20At%20Most%20Two%20Distinct%20Characters/README.md)

## Description

<p>Given a string <code>s</code>, return <em>the length of the longest substring that contains at most <strong>two distinct characters</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;eceba&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The substring is &quot;ece&quot; which its length is 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ccaabbb&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The substring is &quot;aabbb&quot; which its length is 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
