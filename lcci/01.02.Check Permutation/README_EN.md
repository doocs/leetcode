# [01.02. Check Permutation](https://leetcode.cn/problems/check-permutation-lcci)

[中文文档](/lcci/01.02.Check%20Permutation/README.md)

## Description

<p>Given two strings,write a method to decide if one is a permutation of the other.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>s1 = &quot;abc&quot;, s2 = &quot;bca&quot;

<strong>Output: </strong>true

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>s1 = &quot;abc&quot;, s2 = &quot;bad&quot;

<strong>Output: </strong>false

</pre>

<p><strong>Note:</strong></p>
<ol>
	<li><code>0 &lt;= len(s1) &lt;= 100 </code></li>
	<li><code>0 &lt;= len(s2) &lt;= 100</code></li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def CheckPermutation(self, s1: str, s2: str) -> bool:
        n1, n2 = len(s1), len(s2)
        if n1 != n2:
            return False
        counter = Counter()
        for i in range(n1):
            counter[s1[i]] += 1
            counter[s2[i]] -= 1
        return all(v == 0 for v in counter.values())
```

### **Java**

```java
class Solution {
    public boolean CheckPermutation(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        if (n1 != n2) {
            return false;
        }
        int[] counter = new int[128];
        for (int i = 0; i < n1; ++i) {
            ++counter[s1.charAt(i)];
            --counter[s2.charAt(i)];
        }
        for (int v : counter) {
            if (v != 0) {
                return false;
            }
        }
        return true;
    }
}
```

### **JavaScript**

```js
var CheckPermutation = function (s1, s2) {
    let n1 = s1.length,
        n2 = s2.length;
    if (n1 != n2) return false;
    let counter = {};
    for (let i = 0; i < n1; i++) {
        let cur1 = s1.charAt(i),
            cur2 = s2.charAt(i);
        counter[cur1] = (counter[cur1] || 0) + 1;
        counter[cur2] = (counter[cur2] || 0) - 1;
    }
    return Object.values(counter).every(v => v == 0);
};
```

### **Go**

```go
func CheckPermutation(s1 string, s2 string) bool {
	freq := make(map[rune]int)
	for _, r := range s1 {
		freq[r]++
	}
	for _, r := range s2 {
		if freq[r] == 0 {
			return false
		}
		freq[r]--
	}
	for _, v := range freq {
		if v != 0 {
			return false
		}
	}
	return true
}
```

### **C++**

```cpp
class Solution {
public:
    bool CheckPermutation(string s1, string s2) {
        int n1 = s1.size();
        int n2 = s2.size();
        if (n1 != n2) return 0;
        vector<int> counter(128);
        for (int i = 0; i < n1; ++i) {
            ++counter[s1[i]];
            --counter[s2[i]];
        }
        for (int v : counter)
            if (v) return 0;
        return 1;
    }
};
```

### **TypeScript**

```ts
function CheckPermutation(s1: string, s2: string): boolean {
    const n = s1.length;
    const m = s2.length;
    if (n !== m) {
        return false;
    }
    const map = new Map<string, number>();
    for (let i = 0; i < n; i++) {
        map.set(s1[i], (map.get(s1[i]) || 0) + 1);
        map.set(s2[i], (map.get(s2[i]) || 0) - 1);
    }
    for (const v of map.values()) {
        if (v !== 0) {
            return false;
        }
    }
    return true;
}
```

```ts
function CheckPermutation(s1: string, s2: string): boolean {
    if (s1.length !== s2.length) {
        return false;
    }
    return (
        s1
            .split('')
            .sort((a, b) => a.charCodeAt(0) - b.charCodeAt(0))
            .join('') ===
        s2
            .split('')
            .sort((a, b) => a.charCodeAt(0) - b.charCodeAt(0))
            .join('')
    );
}
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    pub fn check_permutation(s1: String, s2: String) -> bool {
        let n = s1.len();
        let m = s2.len();
        if n != m {
            return false;
        }
        let s1: Vec<char> = s1.chars().collect();
        let s2: Vec<char> = s2.chars().collect();
        let mut map = HashMap::new();
        for i in 0..n {
            map.insert(s1[i], map.get(&s1[i]).unwrap_or(&0) + 1);
            map.insert(s2[i], map.get(&s2[i]).unwrap_or(&0) - 1);
        }
        map.values().all(|i| *i == 0)
    }
}
```

```rust
impl Solution {
    pub fn check_permutation(s1: String, s2: String) -> bool {
        let mut v1: Vec<char> = s1.chars().collect();
        let mut v2: Vec<char> = s2.chars().collect();
        v1.sort();
        v2.sort();
        v1 == v2
    }
}
```

### **...**

```

```

<!-- tabs:end -->
