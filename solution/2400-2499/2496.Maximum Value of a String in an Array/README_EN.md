# [2496. Maximum Value of a String in an Array](https://leetcode.com/problems/maximum-value-of-a-string-in-an-array)

[中文文档](/solution/2400-2499/2496.Maximum%20Value%20of%20a%20String%20in%20an%20Array/README.md)

## Description

<p>The <strong>value</strong> of an alphanumeric string can be defined as:</p>

<ul>
	<li>The <strong>numeric</strong> representation of the string in base <code>10</code>, if it comprises of digits <strong>only</strong>.</li>
	<li>The <strong>length</strong> of the string, otherwise.</li>
</ul>

<p>Given an array <code>strs</code> of alphanumeric strings, return <em>the <strong>maximum value</strong> of any string in </em><code>strs</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;alic3&quot;,&quot;bob&quot;,&quot;3&quot;,&quot;4&quot;,&quot;00000&quot;]
<strong>Output:</strong> 5
<strong>Explanation:</strong> 
- &quot;alic3&quot; consists of both letters and digits, so its value is its length, i.e. 5.
- &quot;bob&quot; consists only of letters, so its value is also its length, i.e. 3.
- &quot;3&quot; consists only of digits, so its value is its numeric equivalent, i.e. 3.
- &quot;4&quot; also consists only of digits, so its value is 4.
- &quot;00000&quot; consists only of digits, so its value is 0.
Hence, the maximum value is 5, of &quot;alic3&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> strs = [&quot;1&quot;,&quot;01&quot;,&quot;001&quot;,&quot;0001&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 
Each string in the array has value 1. Hence, we return 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 100</code></li>
	<li><code>1 &lt;= strs[i].length &lt;= 9</code></li>
	<li><code>strs[i]</code> consists of only lowercase English letters and digits.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumValue(self, strs: List[str]) -> int:
        def f(s):
            return int(s) if all(c.isdigit() for c in s) else len(s)

        return max(f(s) for s in strs)
```

### **Java**

```java
class Solution {
    public int maximumValue(String[] strs) {
        int ans = 0;
        for (String s : strs) {
            ans = Math.max(ans, f(s));
        }
        return ans;
    }

    private int f(String s) {
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                return s.length();
            }
        }
        return Integer.parseInt(s);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumValue(vector<string>& strs) {
        auto f = [](string& s) {
            int n = s.size(), m = 0;
            for (char& c : s) {
                if (!isdigit(c)) return n;
                m = m * 10 + (c - '0');
            }
            return m;
        };
        int ans = 0;
        for (auto& s : strs) ans = max(ans, f(s));
        return ans;
    }
};
```

### **Go**

```go
func maximumValue(strs []string) (ans int) {
	f := func(s string) int {
		n, m := len(s), 0
		for _, c := range s {
			if c >= 'a' && c <= 'z' {
				return n
			}
			m = m*10 + int(c-'0')
		}
		return m
	}
	for _, s := range strs {
		if t := f(s); ans < t {
			ans = t
		}
	}
	return
}
```

### **TypeScript**

```ts
function maximumValue(strs: string[]): number {
    let ans = 0;
    for (const s of strs) {
        const num = Number(s);
        ans = Math.max(ans, Number.isNaN(num) ? s.length : num);
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn maximum_value(strs: Vec<String>) -> i32 {
        let mut ans = 0;
        for s in strs.iter() {
            let num = s.parse().unwrap_or(s.len());
            ans = ans.max(num);
        }
        ans as i32
    }
}
```

### **C**

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))

int parseInt(char *s) {
    int n = strlen(s);
    int res = 0;
    for (int i = 0; i < n; i++) {
        if (!isdigit(s[i])) {
            return n;
        }
        res = res * 10 + s[i] - '0';
    }
    return res;
}

int maximumValue(char **strs, int strsSize) {
    int ans = 0;
    for (int i = 0; i < strsSize; i++) {
        int num = parseInt(strs[i]);
        ans = max(ans, num);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
