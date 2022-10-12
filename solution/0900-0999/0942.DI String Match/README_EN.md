# [942. DI String Match](https://leetcode.com/problems/di-string-match)

[中文文档](/solution/0900-0999/0942.DI%20String%20Match/README.md)

## Description

<p>A permutation <code>perm</code> of <code>n + 1</code> integers of all the integers in the range <code>[0, n]</code> can be represented as a string <code>s</code> of length <code>n</code> where:</p>

<ul>
	<li><code>s[i] == &#39;I&#39;</code> if <code>perm[i] &lt; perm[i + 1]</code>, and</li>
	<li><code>s[i] == &#39;D&#39;</code> if <code>perm[i] &gt; perm[i + 1]</code>.</li>
</ul>

<p>Given a string <code>s</code>, reconstruct the permutation <code>perm</code> and return it. If there are multiple valid permutations perm, return <strong>any of them</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s = "IDID"
<strong>Output:</strong> [0,4,1,3,2]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s = "III"
<strong>Output:</strong> [0,1,2,3]
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> s = "DDI"
<strong>Output:</strong> [3,2,0,1]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;I&#39;</code> or <code>&#39;D&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def diStringMatch(self, s: str) -> List[int]:
        n = len(s)
        low, high = 0, n
        ans = []
        for i in range(n):
            if s[i] == 'I':
                ans.append(low)
                low += 1
            else:
                ans.append(high)
                high -= 1
        ans.append(low)
        return ans
```

### **Java**

```java
class Solution {
    public int[] diStringMatch(String s) {
        int n = s.length();
        int low = 0, high = n;
        int[] ans = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'I') {
                ans[i] = low++;
            } else {
                ans[i] = high--;
            }
        }
        ans[n] = low;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> diStringMatch(string s) {
        int n = s.size();
        int low = 0, high = n;
        vector<int> ans(n + 1);
        for (int i = 0; i < n; ++i) {
            if (s[i] == 'I') {
                ans[i] = low++;
            } else {
                ans[i] = high--;
            }
        }
        ans[n] = low;
        return ans;
    }
};
```

### **Go**

```go
func diStringMatch(s string) []int {
	n := len(s)
	low, high := 0, n
	var ans []int
	for i := 0; i < n; i++ {
		if s[i] == 'I' {
			ans = append(ans, low)
			low++
		} else {
			ans = append(ans, high)
			high--
		}
	}
	ans = append(ans, low)
	return ans
}
```

### **TypeScript**

```ts
function diStringMatch(s: string): number[] {
    const n = s.length;
    const res = new Array(n + 1);
    let low = 0;
    let high = n;
    for (let i = 0; i < n; i++) {
        if (s[i] === 'I') {
            res[i] = low++;
        } else {
            res[i] = high--;
        }
    }
    res[n] = low;
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn di_string_match(s: String) -> Vec<i32> {
        let s = s.as_bytes();
        let n = s.len();
        let mut res = Vec::with_capacity(n + 1);
        let (mut low, mut high) = (-1, (n + 1) as i32);
        for i in 0..n {
            res.push(if s[i] == b'I' {
                low += 1;
                low
            } else {
                high -= 1;
                high
            });
        }
        res.push(low + 1);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
