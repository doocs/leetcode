# [266. Palindrome Permutation](https://leetcode.com/problems/palindrome-permutation)

[中文文档](/solution/0200-0299/0266.Palindrome%20Permutation/README.md)

## Description

<p>Given a string <code>s</code>, return <code>true</code> if a permutation of the string could form a palindrome.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;code&quot;
<strong>Output:</strong> false
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aab&quot;
<strong>Output:</strong> true
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;carerac&quot;
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5000</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        counter = Counter(s)
        return sum(e % 2 for e in counter.values()) < 2
```

### **Java**

```java
class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        int oddCnt = 0;
        for (int cnt : counter) {
            oddCnt += cnt % 2;
        }
        return oddCnt < 2;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canPermutePalindrome(string s) {
        vector<int> counter(26);
        for (auto& c : s) ++counter[c - 'a'];
        int oddCnt = 0;
        for (int& cnt : counter) oddCnt += cnt % 2;
        return oddCnt < 2;
    }
};
```

### **Go**

```go
func canPermutePalindrome(s string) bool {
	counter := make([]int, 26)
	for i := range s {
		counter[s[i]-'a']++
	}
	oddCnt := 0
	for _, cnt := range counter {
		oddCnt += cnt % 2
	}
	return oddCnt < 2
}
```

### **...**

```

```

<!-- tabs:end -->
