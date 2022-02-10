# [1100. Find K-Length Substrings With No Repeated Characters](https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters)

[中文文档](/solution/1100-1199/1100.Find%20K-Length%20Substrings%20With%20No%20Repeated%20Characters/README.md)

## Description

<p>Given a string <code>S</code>, return the number of substrings of length <code>K</code> with no repeated characters.</p>

<p>&nbsp;</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>S = <span id="example-input-1-1">&quot;havefunonleetcode&quot;</span>, K = <span id="example-input-1-2">5</span>

<strong>Output: </strong><span id="example-output-1">6</span>

<strong>Explanation: </strong>

There are 6 substrings they are : &#39;havef&#39;,&#39;avefu&#39;,&#39;vefun&#39;,&#39;efuno&#39;,&#39;etcod&#39;,&#39;tcode&#39;.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>S = <span id="example-input-2-1">&quot;home&quot;</span>, K = <span id="example-input-2-2">5</span>

<strong>Output: </strong><span id="example-output-2">0</span>

<strong>Explanation: </strong>

Notice K can be larger than the length of S. In this case is not possible to find any substring.

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= S.length &lt;= 10^4</code></li>
	<li>All characters of S are lowercase English letters.</li>
	<li><code>1 &lt;= K &lt;= 10^4</code></li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numKLenSubstrNoRepeats(self, s: str, k: int) -> int:
        ans = j = 0
        mp = {}
        for i, c in enumerate(s):
            if c in mp:
                j = max(j, mp[c] + 1)
            mp[c] = i
            if i - j + 1 >= k:
                ans += 1
        return ans
```

### **Java**

```java
class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) {
        int ans = 0;
        Map<Character, Integer> mp = new HashMap<>();
        for (int i = 0, j = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (mp.containsKey(c)) {
                j = Math.max(j, mp.get(c) + 1);
            }
            mp.put(c, i);
            if (i - j + 1 >= k) {
                ++ans;
            }
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
        int ans = 0;
        unordered_map<int, int> mp;
        for (int i = 0, j = 0; i < s.size(); ++i)
        {
            char c = s[i];
            if (mp.count(c)) j = max(j, mp[c] + 1);
            mp[c] = i;
            if (i - j + 1 >= k) ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func numKLenSubstrNoRepeats(s string, k int) int {
	mp := make(map[rune]int)
	ans, j := 0, 0
	for i, c := range s {
		if v, ok := mp[c]; ok {
			j = max(j, v+1)
		}
		mp[c] = i
		if i-j+1 >= k {
			ans++
		}
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
