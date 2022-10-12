# [2405. Optimal Partition of String](https://leetcode.com/problems/optimal-partition-of-string)

[中文文档](/solution/2400-2499/2405.Optimal%20Partition%20of%20String/README.md)

## Description

<p>Given a string <code>s</code>, partition the string into one or more <strong>substrings</strong> such that the characters in each substring are <strong>unique</strong>. That is, no letter appears in a single substring more than <strong>once</strong>.</p>

<p>Return <em>the <strong>minimum</strong> number of substrings in such a partition.</em></p>

<p>Note that each character should belong to exactly one substring in a partition.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abacaba&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong>
Two possible partitions are (&quot;a&quot;,&quot;ba&quot;,&quot;cab&quot;,&quot;a&quot;) and (&quot;ab&quot;,&quot;a&quot;,&quot;ca&quot;,&quot;ba&quot;).
It can be shown that 4 is the minimum number of substrings needed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ssssss&quot;
<strong>Output:</strong> 6
<strong>Explanation:
</strong>The only valid partition is (&quot;s&quot;,&quot;s&quot;,&quot;s&quot;,&quot;s&quot;,&quot;s&quot;,&quot;s&quot;).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only English lowercase letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def partitionString(self, s: str) -> int:
        ss = set()
        ans = 1
        for c in s:
            if c in ss:
                ans += 1
                ss = set()
            ss.add(c)
        return ans
```

```python
class Solution:
    def partitionString(self, s: str) -> int:
        ans, v = 1, 0
        for c in s:
            i = ord(c) - ord('a')
            if (v >> i) & 1:
                v = 0
                ans += 1
            v |= 1 << i
        return ans
```

### **Java**

```java
class Solution {
    public int partitionString(String s) {
        Set<Character> ss = new HashSet<>();
        int ans = 1;
        for (char c : s.toCharArray()) {
            if (ss.contains(c)) {
                ++ans;
                ss.clear();
            }
            ss.add(c);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int partitionString(String s) {
        int v = 0;
        int ans = 1;
        for (char c : s.toCharArray()) {
            int i = c - 'a';
            if (((v >> i) & 1) == 1) {
                v = 0;
                ++ans;
            }
            v |= 1 << i;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int partitionString(string s) {
        unordered_set<char> ss;
        int ans = 1;
        for (char c : s) {
            if (ss.count(c)) {
                ++ans;
                ss.clear();
            }
            ss.insert(c);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int partitionString(string s) {
        int ans = 1;
        int v = 0;
        for (char c : s) {
            int i = c - 'a';
            if ((v >> i) & 1) {
                v = 0;
                ++ans;
            }
            v |= 1 << i;
        }
        return ans;
    }
};
```

### **Go**

```go
func partitionString(s string) int {
	ss := map[rune]bool{}
	ans := 1
	for _, c := range s {
		if ss[c] {
			ans++
			ss = map[rune]bool{}
		}
		ss[c] = true
	}
	return ans
}
```

```go
func partitionString(s string) int {
	ans, v := 1, 0
	for _, c := range s {
		i := int(c - 'a')
		if v>>i&1 == 1 {
			v = 0
			ans++
		}
		v |= 1 << i
	}
	return ans
}
```

### **TypeScript**

```ts
function partitionString(s: string): number {
    const set = new Set();
    let res = 1;
    for (const c of s) {
        if (set.has(c)) {
            res++;
            set.clear();
        }
        set.add(c);
    }
    return res;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn partition_string(s: String) -> i32 {
        let mut set = HashSet::new();
        let mut res = 1;
        for c in s.as_bytes().iter() {
            if set.contains(c) {
                res += 1;
                set.clear();
            }
            set.insert(c);
        }
        res
    }
}
```

### **...**

```


```

<!-- tabs:end -->
