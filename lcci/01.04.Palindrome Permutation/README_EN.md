# [01.04. Palindrome Permutation](https://leetcode-cn.com/problems/palindrome-permutation-lcci)

[中文文档](/lcci/01.04.Palindrome%20Permutation/README.md)

## Description

<p>Given a string, write a function to check if it is a permutation of a palin&shy; drome. A palindrome is a word or phrase that is the same forwards and backwards. A permutation is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.</p>

<p>&nbsp;</p>

<p><strong>Example1: </strong></p>

<pre>

<strong>Input: &quot;</strong>tactcoa&quot;

<strong>Output: </strong>true（permutations: &quot;tacocat&quot;、&quot;atcocta&quot;, etc.）

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        counter = Counter(s)
        cnt = 0
        for val in counter.values():
            if (val & 1) == 1:
                cnt += 1
            if cnt > 1:
                return False
        return True
```

### **Java**

```java
class Solution {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0, n = s.length(); i < n; ++i) {
            char c = s.charAt(i);
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }
        int cnt = 0;
        for (int val : counter.values()) {
            if ((val & 1) == 1) {
                ++cnt;
            }
            if (cnt > 1) {
                return false;
            }
        }
        return true;
    }
}
```

### **Go**

```go
func canPermutePalindrome(s string) bool {
	m := make(map[rune]bool)
	count := 0
	for _, r := range s {
		if m[r] {
			m[r] = false
			count--
		} else {
			m[r] = true
			count++
		}
	}
	return count <= 1
}
```

### **...**

```

```

<!-- tabs:end -->
