# [438. Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string)

[中文文档](/solution/0400-0499/0438.Find%20All%20Anagrams%20in%20a%20String/README.md)

## Description

<p>Given two strings <code>s</code> and <code>p</code>, return <em>an array of all the start indices of </em><code>p</code><em>&#39;s anagrams in </em><code>s</code>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cbaebabacd&quot;, p = &quot;abc&quot;
<strong>Output:</strong> [0,6]
<strong>Explanation:</strong>
The substring with start index = 0 is &quot;cba&quot;, which is an anagram of &quot;abc&quot;.
The substring with start index = 6 is &quot;bac&quot;, which is an anagram of &quot;abc&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abab&quot;, p = &quot;ab&quot;
<strong>Output:</strong> [0,1,2]
<strong>Explanation:</strong>
The substring with start index = 0 is &quot;ab&quot;, which is an anagram of &quot;ab&quot;.
The substring with start index = 1 is &quot;ba&quot;, which is an anagram of &quot;ab&quot;.
The substring with start index = 2 is &quot;ab&quot;, which is an anagram of &quot;ab&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, p.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s</code> and <code>p</code> consist of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        counter = Counter(p)
        ans = []
        left = right = 0
        t = Counter()
        while right < len(s):
            t[s[right]] += 1
            while t[s[right]] > counter[s[right]]:
                t[s[left]] -= 1
                left += 1
            if right - left + 1 == len(p):
                ans.append(left)
            right += 1
        return ans
```

### **Java**

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] counter = new int[26];
        for (char c : p.toCharArray()) {
            ++counter[c - 'a'];
        }
        List<Integer> ans = new ArrayList<>();
        int left = 0, right = 0;
        int[] t = new int[26];
        while (right < s.length()) {
            int i = s.charAt(right) - 'a';
            ++t[i];
            while (t[i] > counter[i]) {
                --t[s.charAt(left) - 'a'];
                ++left;
            }
            if (right - left + 1 == p.length()) {
                ans.add(left);
            }
            ++right;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> findAnagrams(string s, string p) {
        vector<int> counter(26);
        for (char c : p) ++counter[c - 'a'];
        vector<int> ans;
        int left = 0, right = 0;
        vector<int> t(26);
        while (right < s.size())
        {
            int i = s[right] - 'a';
            ++t[i];
            while (t[i] > counter[i])
            {
                --t[s[left] - 'a'];
                ++left;
            }
            if (right - left + 1 == p.size()) ans.push_back(left);
            ++right;
        }
        return ans;
    }
};
```

### **Go**

```go
func findAnagrams(s string, p string) []int {
	counter := make([]int, 26)
	for _, c := range p {
		counter[c-'a']++
	}
	var ans []int
	left, right := 0, 0
	t := make([]int, 26)
	for right < len(s) {
		i := s[right] - 'a'
		t[i]++
		for t[i] > counter[i] {
			t[s[left]-'a']--
			left++
		}
		if right-left+1 == len(p) {
			ans = append(ans, left)
		}
		right++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
