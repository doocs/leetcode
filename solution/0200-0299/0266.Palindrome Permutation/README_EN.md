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
        Map<Character, Integer> counter = new HashMap<>();
        for (char c : s.toCharArray()) {
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }
        int oddCnt = 0;
        for (int e : counter.values()) {
            oddCnt += e % 2;
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
        unordered_map<char, int> counter;
        for (char c : s) ++counter[c];
        int oddCnt = 0;
        for (auto& it : counter) oddCnt += it.second % 2;
        return oddCnt < 2;
    }
};
```

### **Go**

```go
func canPermutePalindrome(s string) bool {
    counter := make(map[rune]int)
    for _, c := range s {
        counter[c]++
    }
    oddCnt := 0
    for _, e := range counter {
        oddCnt += e % 2
    }
    return oddCnt < 2
}
```

### **...**

```

```

<!-- tabs:end -->
