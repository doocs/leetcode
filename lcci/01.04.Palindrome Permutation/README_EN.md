# [01.04. Palindrome Permutation](https://leetcode.cn/problems/palindrome-permutation-lcci)

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
        return sum(1 for v in counter.values() if v % 2 == 1) <= 1
```

### **Java**

```java
class Solution {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        for (char c : s.toCharArray()) {
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }
        int cnt = 0;
        for (int v : counter.values()) {
            cnt += v % 2;
        }
        return cnt < 2;
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

### **C++**

```cpp
class Solution {
public:
    bool canPermutePalindrome(string s) {
        unordered_map<char, int> counter;
        for (char c : s) ++counter[c];
        int cnt = 0;
        for (auto& [k, v] : counter) cnt += v % 2;
        return cnt < 2;
    }
};
```

### **TypeScript**

```ts
function canPermutePalindrome(s: string): boolean {
    const set = new Set<string>();
    for (const c of s) {
        if (set.has(c)) {
            set.delete(c);
        } else {
            set.add(c);
        }
    }
    return set.size <= 1;
}
```

### **Rust**

```rust
use std::collections::HashSet;

impl Solution {
    pub fn can_permute_palindrome(s: String) -> bool {
        let mut set = HashSet::new();
        for c in s.chars() {
            if set.contains(&c) {
                set.remove(&c);
            } else {
                set.insert(c);
            }
        }
        set.len() <= 1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
