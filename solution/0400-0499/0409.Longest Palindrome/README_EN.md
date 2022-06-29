# [409. Longest Palindrome](https://leetcode.com/problems/longest-palindrome)

[中文文档](/solution/0400-0499/0409.Longest%20Palindrome/README.md)

## Description

<p>Given a string <code>s</code> which consists of lowercase or uppercase letters, return <em>the length of the <strong>longest palindrome</strong></em>&nbsp;that can be built with those letters.</p>

<p>Letters are <strong>case sensitive</strong>, for example,&nbsp;<code>&quot;Aa&quot;</code> is not considered a palindrome here.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abccccdd&quot;
<strong>Output:</strong> 7
<strong>Explanation:</strong> One longest palindrome that can be built is &quot;dccaccd&quot;, whose length is 7.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> The longest palindrome that can be built is &quot;a&quot;, whose length is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code> consists of lowercase <strong>and/or</strong> uppercase English&nbsp;letters only.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestPalindrome(self, s: str) -> int:
        n = len(s)
        counter = Counter(s)
        odd_cnt = sum(e % 2 for e in counter.values())
        return n if odd_cnt == 0 else n - odd_cnt + 1
```

### **Java**

```java
class Solution {
    public int longestPalindrome(String s) {
        int[] counter = new int[128];
        for (char c : s.toCharArray()) {
            ++counter[c];
        }
        int oddCnt = 0;
        for (int e : counter) {
            oddCnt += (e % 2);
        }
        int n = s.length();
        return oddCnt == 0 ? n : n - oddCnt + 1;
    }
}
```

### **TypeScript**

```ts
function longestPalindrome(s: string): number {
    let n = s.length;
    let ans = 0;
    let record = new Array(128).fill(0);
    for (let i = 0; i < n; i++) {
        record[s.charCodeAt(i)]++;
    }
    for (let i = 65; i < 128; i++) {
        let count = record[i];
        ans += count % 2 == 0 ? count : count - 1;
    }
    return ans < s.length ? ans + 1 : ans;
}
```

```ts
function longestPalindrome(s: string): number {
    const map = new Map();
    for (const c of s) {
        map.set(c, (map.get(c) ?? 0) + 1);
    }
    let hasOdd = false;
    let res = 0;
    for (const v of map.values()) {
        res += v;
        if (v & 1) {
            hasOdd = true;
            res--;
        }
    }
    return res + (hasOdd ? 1 : 0);
}
```

### **C++**

```cpp
class Solution {
public:
    int longestPalindrome(string s) {
        vector<int> counter(128);
        for (char c : s) ++counter[c];
        int oddCnt = 0;
        for (int e : counter) oddCnt += e % 2;
        int n = s.size();
        return oddCnt == 0 ? n : n - oddCnt + 1;
    }
};
```

### **Go**

```go
func longestPalindrome(s string) int {
	counter := make([]int, 128)
	for _, c := range s {
		counter[c]++
	}
	oddCnt := 0
	for _, e := range counter {
		oddCnt += e % 2
	}
	n := len(s)
	if oddCnt == 0 {
		return n
	}
	return n - oddCnt + 1
}
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    pub fn longest_palindrome(s: String) -> i32 {
        let mut map: HashMap<char, i32> = HashMap::new();
        for c in s.chars() {
            map.insert(c, map.get(&c).unwrap_or(&0) + 1);
        }
        let mut has_odd = false;
        let mut res = 0;
        for v in map.values() {
            res += v;
            if v % 2 == 1 {
                has_odd = true;
                res -= 1;
            }
        }
        res + if has_odd { 1 } else { 0 }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
