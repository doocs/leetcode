# [2697. Lexicographically Smallest Palindrome](https://leetcode.com/problems/lexicographically-smallest-palindrome)

[中文文档](/solution/2600-2699/2697.Lexicographically%20Smallest%20Palindrome/README.md)

## Description

<p>You are given a string <code node="[object Object]">s</code> consisting of <strong>lowercase English letters</strong>, and you are allowed to perform operations on it. In one operation, you can <strong>replace</strong> a character in <code node="[object Object]">s</code> with another lowercase English letter.</p>

<p>Your task is to make <code node="[object Object]">s</code> a <strong>palindrome</strong> with the <strong>minimum</strong> <strong>number</strong> <strong>of operations</strong> possible. If there are <strong>multiple palindromes</strong> that can be <meta charset="utf-8" />made using the <strong>minimum</strong> number of operations, <meta charset="utf-8" />make the <strong>lexicographically smallest</strong> one.</p>

<p>A string <code>a</code> is lexicographically smaller than a string <code>b</code> (of the same length) if in the first position where <code>a</code> and <code>b</code> differ, string <code>a</code> has a letter that appears earlier in the alphabet than the corresponding letter in <code>b</code>.</p>

<p>Return <em>the resulting palindrome string.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;egcfe&quot;
<strong>Output:</strong> &quot;efcfe&quot;
<strong>Explanation:</strong> The minimum number of operations to make &quot;egcfe&quot; a palindrome is 1, and the lexicographically smallest palindrome string we can get by modifying one character is &quot;efcfe&quot;, by changing &#39;g&#39;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;
<strong>Output:</strong> &quot;abba&quot;
<strong>Explanation:</strong> The minimum number of operations to make &quot;abcd&quot; a palindrome is 2, and the lexicographically smallest palindrome string we can get by modifying two characters is &quot;abba&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;seven&quot;
<strong>Output:</strong> &quot;neven&quot;
<strong>Explanation:</strong> The minimum number of operations to make &quot;seven&quot; a palindrome is 1, and the lexicographically smallest palindrome string we can get by modifying one character is &quot;neven&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code>&nbsp;consists of only lowercase English letters<b>.</b></li>
</ul>

## Solutions

**Solution 1: Greedy + Two Pointers**

We use two pointers $i$ and $j$ to point to the beginning and end of the string, initially $i = 0$, $j = n - 1$.

Next, each time we greedily modify $s[i]$ and $s[j]$ to their smaller value to make them equal. Then we move $i$ one step forward and $j$ one step backward, and continue this process until $i \ge j$. At this point, we have obtained the smallest palindrome string.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def makeSmallestPalindrome(self, s: str) -> str:
        cs = list(s)
        i, j = 0, len(s) - 1
        while i < j:
            cs[i] = cs[j] = min(cs[i], cs[j])
            i, j = i + 1, j - 1
        return "".join(cs)
```

### **Java**

```java
class Solution {
    public String makeSmallestPalindrome(String s) {
        char[] cs = s.toCharArray();
        for (int i = 0, j = cs.length - 1; i < j; ++i, --j) {
            cs[i] = cs[j] = (char) Math.min(cs[i], cs[j]);
        }
        return new String(cs);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string makeSmallestPalindrome(string s) {
        for (int i = 0, j = s.size() - 1; i < j; ++i, --j) {
            s[i] = s[j] = min(s[i], s[j]);
        }
        return s;
    }
};
```

### **Go**

```go
func makeSmallestPalindrome(s string) string {
	cs := []byte(s)
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		cs[i] = min(cs[i], cs[j])
		cs[j] = cs[i]
	}
	return string(cs)
}
```

### **TypeScript**

```ts
function makeSmallestPalindrome(s: string): string {
    const cs = s.split('');
    for (let i = 0, j = s.length - 1; i < j; ++i, --j) {
        cs[i] = cs[j] = s[i] < s[j] ? s[i] : s[j];
    }
    return cs.join('');
}
```

### **Rust**

```rust
impl Solution {
    pub fn make_smallest_palindrome(s: String) -> String {
        let mut cs: Vec<char> = s.chars().collect();
        let n = cs.len();
        for i in 0..n / 2 {
            let j = n - 1 - i;
            cs[i] = std::cmp::min(cs[i], cs[j]);
            cs[j] = cs[i];
        }
        cs.into_iter().collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
