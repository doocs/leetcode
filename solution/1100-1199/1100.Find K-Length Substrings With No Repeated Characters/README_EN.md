# [1100. Find K-Length Substrings With No Repeated Characters](https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters)

[中文文档](/solution/1100-1199/1100.Find%20K-Length%20Substrings%20With%20No%20Repeated%20Characters/README.md)

## Description

<p>Given a string <code>s</code> and an integer <code>k</code>, return <em>the number of substrings in </em><code>s</code><em> of length </em><code>k</code><em> with no repeated characters</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;havefunonleetcode&quot;, k = 5
<strong>Output:</strong> 6
<strong>Explanation:</strong> There are 6 substrings they are: &#39;havef&#39;,&#39;avefu&#39;,&#39;vefun&#39;,&#39;efuno&#39;,&#39;etcod&#39;,&#39;tcode&#39;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;home&quot;, k = 5
<strong>Output:</strong> 0
<strong>Explanation:</strong> Notice k can be larger than the length of s. In this case, it is not possible to find any substring.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numKLenSubstrNoRepeats(self, s: str, k: int) -> int:
        n = len(s)
        if k > n or k > 26:
            return 0
        ans = j = 0
        cnt = Counter()
        for i, c in enumerate(s):
            cnt[c] += 1
            while cnt[c] > 1 or i - j + 1 > k:
                cnt[s[j]] -= 1
                j += 1
            ans += i - j + 1 == k
        return ans
```

### **Java**

```java
class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) {
        int n = s.length();
        if (k > n || k > 26) {
            return 0;
        }
        int[] cnt = new int[128];
        int ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            ++cnt[s.charAt(i)];
            while (cnt[s.charAt(i)] > 1 || i - j + 1 > k) {
                cnt[s.charAt(j++)]--;
            }
            ans += i - j + 1 == k ? 1 : 0;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numKLenSubstrNoRepeats(string s, int k) {
        int n = s.size();
        if (k > n || k > 26) {
            return 0;
        }
        int cnt[128]{};
        int ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            ++cnt[s[i]];
            while (cnt[s[i]] > 1 || i - j + 1 > k) {
                --cnt[s[j++]];
            }
            ans += i - j + 1 == k;
        }
        return ans;
    }
};
```

### **Go**

```go
func numKLenSubstrNoRepeats(s string, k int) (ans int) {
	if k > len(s) || k > 26 {
		return 0
	}
	cnt := [128]int{}
	for i, j := 0, 0; i < len(s); i++ {
		cnt[s[i]]++
		for cnt[s[i]] > 1 || i-j+1 > k {
			cnt[s[j]]--
			j++
		}
		if i-j+1 == k {
			ans++
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
