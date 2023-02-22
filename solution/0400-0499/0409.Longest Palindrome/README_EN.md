# [409. Longest Palindrome](https://leetcode.com/problems/longest-palindrome)

[中文文档](/solution/0400-0499/0409.Longest%20Palindrome/README.md)

## Description

<p>Given a string <code>s</code> which consists of lowercase or uppercase letters, return <em>the length of the <strong>longest palindrome</strong></em>&nbsp;that can be built with those letters.</p>

<p>Letters are <strong>case sensitive</strong>, for example,&nbsp;<code>&quot;Aa&quot;</code> is not considered a palindrome here.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abccccdd&quot;
<strong>Output:</strong> 7
<strong>Explanation:</strong> One longest palindrome that can be built is &quot;dccaccd&quot;, whose length is 7.
</pre>

<p><strong class="example">Example 2:</strong></p>

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
        cnt = Counter(s)
        ans = 0
        for v in cnt.values():
            ans += v - (v & 1)
            ans += (ans & 1 ^ 1) and (v & 1)
        return ans
```

### **Java**

```java
class Solution {
    public int longestPalindrome(String s) {
        int[] cnt = new int[128];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i)];
        }
        int ans = 0;
        for (int v : cnt) {
            ans += v - (v & 1);
            if (ans % 2 == 0 && v % 2 == 1) {
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
    int longestPalindrome(string s) {
        int cnt[128]{};
        for (char& c : s) {
            ++cnt[c];
        }
        int ans = 0;
        for (int v : cnt) {
            ans += v - (v & 1);
            if (ans % 2 == 0 && v % 2 == 1) {
                ++ans;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func longestPalindrome(s string) (ans int) {
	cnt := [128]int{}
	for _, c := range s {
		cnt[c]++
	}
	for _, v := range cnt {
		ans += v - (v & 1)
		if ans&1 == 0 && v&1 == 1 {
			ans++
		}
	}
	return
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
