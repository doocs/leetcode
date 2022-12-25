# [2516. Take K of Each Character From Left and Right](https://leetcode.com/problems/take-k-of-each-character-from-left-and-right)

[中文文档](/solution/2500-2599/2516.Take%20K%20of%20Each%20Character%20From%20Left%20and%20Right/README.md)

## Description

<p>You are given a string <code>s</code> consisting of the characters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code> and a non-negative integer <code>k</code>. Each minute, you may take either the <strong>leftmost</strong> character of <code>s</code>, or the <strong>rightmost</strong> character of <code>s</code>.</p>

<p>Return<em> the <strong>minimum</strong> number of minutes needed for you to take <strong>at least</strong> </em><code>k</code><em> of each character, or return </em><code>-1</code><em> if it is not possible to take </em><code>k</code><em> of each character.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabaaaacaabc&quot;, k = 2
<strong>Output:</strong> 8
<strong>Explanation:</strong> 
Take three characters from the left of s. You now have two &#39;a&#39; characters, and one &#39;b&#39; character.
Take five characters from the right of s. You now have four &#39;a&#39; characters, two &#39;b&#39; characters, and two &#39;c&#39; characters.
A total of 3 + 5 = 8 minutes is needed.
It can be proven that 8 is the minimum number of minutes needed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a&quot;, k = 1
<strong>Output:</strong> -1
<strong>Explanation:</strong> It is not possible to take one &#39;b&#39; or &#39;c&#39; so return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only the letters <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code>.</li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def takeCharacters(self, s: str, k: int) -> int:
        cnt = Counter(s)
        if any(cnt[c] < k for c in "abc"):
            return -1
        ans = j = 0
        for i, c in enumerate(s):
            cnt[c] -= 1
            while cnt[c] < k:
                cnt[s[j]] += 1
                j += 1
            ans = max(ans, i - j + 1)
        return len(s) - ans
```

### **Java**

```java
class Solution {
    public int takeCharacters(String s, int k) {
        int[] cnt = new int[3];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        if (cnt[0] < k || cnt[1] < k || cnt[2] < k) {
            return -1;
        }
        int ans = 0, j = 0;
        for (int i = 0; i < n; ++i) {
            int c = s.charAt(i) - 'a';
            --cnt[c];
            while (cnt[c] < k) {
                ++cnt[s.charAt(j++) - 'a'];
            }
            ans = Math.max(ans, i - j + 1);
        }
        return n - ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int takeCharacters(string s, int k) {
        int cnt[3] = {0};
        for (char& c : s) ++cnt[c - 'a'];
        if (cnt[0] < k || cnt[1] < k || cnt[2] < k) return -1;
        int ans = 0, j = 0;
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            int c = s[i] - 'a';
            --cnt[c];
            while (cnt[c] < k) {
                ++cnt[s[j++] - 'a'];
            }
            ans = max(ans, i - j + 1);
        }
        return n - ans;
    }
};
```

### **Go**

```go
func takeCharacters(s string, k int) int {
	cnt := [3]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	if cnt[0] < k || cnt[1] < k || cnt[2] < k {
		return -1
	}
	ans, j := 0, 0
	for i, c := range s {
		c -= 'a'
		cnt[c]--
		for cnt[c] < k {
			cnt[s[j]-'a']++
			j++
		}
		ans = max(ans, i-j+1)
	}
	return len(s) - ans
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
