# [567. Permutation in String](https://leetcode.com/problems/permutation-in-string)

[中文文档](/solution/0500-0599/0567.Permutation%20in%20String/README.md)

## Description

<p>Given two strings <code>s1</code> and <code>s2</code>, return <code>true</code><em> if </em><code>s2</code><em> contains a permutation of </em><code>s1</code><em>, or </em><code>false</code><em> otherwise</em>.</p>

<p>In other words, return <code>true</code> if one of <code>s1</code>&#39;s permutations is the substring of <code>s2</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;ab&quot;, s2 = &quot;eidbaooo&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> s2 contains one permutation of s1 (&quot;ba&quot;).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;ab&quot;, s2 = &quot;eidboaoo&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s1</code> and <code>s2</code> consist of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        need, window = {}, {}
        validate, left, right = 0, 0, 0
        for c in s1:
            window[c] = 0
            if c in need:
                need[c] += 1
            else:
                need[c] = 1
        # sliding window
        for right in range(len(s2)):
            c = s2[right]
            if c in need:
                window[c] += 1
                if window[c] == need[c]:
                    validate += 1
            while right - left + 1 >= len(s1):
                if validate == len(need):
                    return True
                d = s2[left]
                left += 1
                if d in need:
                    if window[d] == need[d]:
                        validate -= 1
                    window[d] -= 1
        return False
```

### **Java**

```java

```

### **TypeScript**

```ts
function checkInclusion(s1: string, s2: string): boolean {
    if (s1.length > s2.length) {
        return false;
    }

    const n = s1.length;
    const m = s2.length;

    const toCode = (s: string) => s.charCodeAt(0) - 97;
    const isMatch = () => {
        for (let i = 0; i < 26; i++) {
            if (arr1[i] !== arr2[i]) {
                return false;
            }
        }
        return true;
    };

    const arr1 = new Array(26).fill(0);
    for (const s of s1) {
        const index = toCode(s);
        arr1[index]++;
    }

    const arr2 = new Array(26).fill(0);
    for (let i = 0; i < n; i++) {
        const index = toCode(s2[i]);
        arr2[index]++;
    }

    for (let l = 0, r = n; r < m; l++, r++) {
        if (isMatch()) {
            return true;
        }

        const i = toCode(s2[l]);
        const j = toCode(s2[r]);
        arr2[i]--;
        arr2[j]++;
    }
    return isMatch();
}
```

### **Rust**

```rust
use std::collections::HashMap;


impl Solution {
    fn is_match(m1: &HashMap<char, i32>, m2: &HashMap<char, i32>) -> bool {
        for (k, v) in m1.iter() {
            if m2.get(k).unwrap_or(&0) != v {
                return false;
            }
        }
        true
    }
    pub fn check_inclusion(s1: String, s2: String) -> bool {
        if s1.len() > s2.len() {
            return false;
        }
        let mut m1 = HashMap::new();
        let mut m2 = HashMap::new();
        for c in s1.chars() {
            m1.insert(c, m1.get(&c).unwrap_or(&0) + 1);
        }
        let cs: Vec<char> = s2.chars().collect();
        let mut i = 0;
        while i < s1.len() {
            m2.insert(cs[i], m2.get(&cs[i]).unwrap_or(&0) + 1);
            i += 1;
        }
        if Self::is_match(&m1, &m2) {
            return true;
        }
        let mut j = 0;
        while i < cs.len() {
            m2.insert(cs[j], m2.get(&cs[j]).unwrap_or(&1) - 1);
            m2.insert(cs[i], m2.get(&cs[i]).unwrap_or(&0) + 1);
            j += 1;
            i += 1;
            if Self::is_match(&m1, &m2) {
                return true;
            }
        }
        false
    }
}
```

### **Go**

```go
func checkInclusion(s1 string, s2 string) bool {
	need, window := make(map[byte]int), make(map[byte]int)
	validate, left, right := 0, 0, 0
	for i := range s1 {
		need[s1[i]] += 1
	}
	for ; right < len(s2); right++ {
		c := s2[right]
		window[c] += 1
		if need[c] == window[c] {
			validate++
		}
		// shrink window
		for right-left+1 >= len(s1) {
			if validate == len(need) {
				return true
			}
			d := s2[left]
			if need[d] == window[d] {
				validate--
			}
			window[d] -= 1
			left++
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
