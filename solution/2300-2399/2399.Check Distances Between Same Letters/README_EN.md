# [2399. Check Distances Between Same Letters](https://leetcode.com/problems/check-distances-between-same-letters)

[中文文档](/solution/2300-2399/2399.Check%20Distances%20Between%20Same%20Letters/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>s</code> consisting of only lowercase English letters, where each letter in <code>s</code> appears <strong>exactly</strong> <strong>twice</strong>. You are also given a <strong>0-indexed</strong> integer array <code>distance</code> of length <code>26</code>.</p>

<p>Each letter in the alphabet is numbered from <code>0</code> to <code>25</code> (i.e. <code>&#39;a&#39; -&gt; 0</code>, <code>&#39;b&#39; -&gt; 1</code>, <code>&#39;c&#39; -&gt; 2</code>, ... , <code>&#39;z&#39; -&gt; 25</code>).</p>

<p>In a <strong>well-spaced</strong> string, the number of letters between the two occurrences of the <code>i<sup>th</sup></code> letter is <code>distance[i]</code>. If the <code>i<sup>th</sup></code> letter does not appear in <code>s</code>, then <code>distance[i]</code> can be <strong>ignored</strong>.</p>

<p>Return <code>true</code><em> if </em><code>s</code><em> is a <strong>well-spaced</strong> string, otherwise return </em><code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abaccb&quot;, distance = [1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
<strong>Output:</strong> true
<strong>Explanation:</strong>
- &#39;a&#39; appears at indices 0 and 2 so it satisfies distance[0] = 1.
- &#39;b&#39; appears at indices 1 and 5 so it satisfies distance[1] = 3.
- &#39;c&#39; appears at indices 3 and 4 so it satisfies distance[2] = 0.
Note that distance[3] = 5, but since &#39;d&#39; does not appear in s, it can be ignored.
Return true because s is a well-spaced string.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aa&quot;, distance = [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
<strong>Output:</strong> false
<strong>Explanation:</strong>
- &#39;a&#39; appears at indices 0 and 1 so there are zero letters between them.
Because distance[0] = 1, s is not a well-spaced string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 52</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
	<li>Each letter appears in <code>s</code> exactly twice.</li>
	<li><code>distance.length == 26</code></li>
	<li><code>0 &lt;= distance[i] &lt;= 50</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def checkDistances(self, s: str, distance: List[int]) -> bool:
        d = defaultdict(int)
        for i, c in enumerate(s, 1):
            if d[c] and i - d[c] - 1 != distance[ord(c) - ord('a')]:
                return False
            d[c] = i
        return True
```

### **Java**

```java
class Solution {
    public boolean checkDistances(String s, int[] distance) {
        int[] d = new int[26];
        for (int i = 1, n = s.length(); i <= n; ++i) {
            int j = s.charAt(i - 1) - 'a';
            if (d[j] > 0 && i - d[j] - 1 != distance[j]) {
                return false;
            }
            d[j] = i;
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkDistances(string s, vector<int>& distance) {
        int d[26]{};
        for (int i = 1; i <= s.size(); ++i) {
            int j = s[i - 1] - 'a';
            if (d[j] && i - d[j] - 1 != distance[j]) {
                return false;
            }
            d[j] = i;
        }
        return true;
    }
};
```

### **Go**

```go
func checkDistances(s string, distance []int) bool {
	d := [26]int{}
	for i, c := range s {
		c -= 'a'
		if d[c] > 0 && i-d[c] != distance[c] {
			return false
		}
		d[c] = i + 1
	}
	return true
}
```

### **C**

```c
bool checkDistances(char *s, int *distance, int distanceSize) {
    int n = strlen(s);
    int d[26] = {0};
    for (int i = 0; i < n; i++) {
        int j = s[i] - 'a';
        if (d[j] > 0 && i - d[j] != distance[j]) {
            return false;
        }
        d[j] = i + 1;
    }
    return true;
}
```

### **TypeScript**

```ts
function checkDistances(s: string, distance: number[]): boolean {
    const n = s.length;
    const d: number[] = new Array(26).fill(0);
    for (let i = 1; i <= n; ++i) {
        const j = s.charCodeAt(i - 1) - 97;
        if (d[j] && i - d[j] - 1 !== distance[j]) {
            return false;
        }
        d[j] = i;
    }
    return true;
}
```

### **Rust**

```rust
impl Solution {
    pub fn check_distances(s: String, distance: Vec<i32>) -> bool {
        let n = s.len();
        let s = s.as_bytes();
        let mut d = [0; 26];
        for i in 0..n {
            let j = (s[i] - b'a') as usize;
            let i = i as i32;
            if d[j] > 0 && i - d[j] != distance[j] {
                return false;
            }
            d[j] = i + 1;
        }
        true
    }
}
```

### **...**

```


```

<!-- tabs:end -->
