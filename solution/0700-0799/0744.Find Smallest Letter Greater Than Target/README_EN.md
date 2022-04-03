# [744. Find Smallest Letter Greater Than Target](https://leetcode.com/problems/find-smallest-letter-greater-than-target)

[中文文档](/solution/0700-0799/0744.Find%20Smallest%20Letter%20Greater%20Than%20Target/README.md)

## Description

<p>Given a characters array <code>letters</code> that is sorted in <strong>non-decreasing</strong> order and a character <code>target</code>, return <em>the smallest character in the array that is larger than </em><code>target</code>.</p>

<p><strong>Note</strong> that the letters wrap around.</p>

<ul>
	<li>For example, if <code>target == &#39;z&#39;</code> and <code>letters == [&#39;a&#39;, &#39;b&#39;]</code>, the answer is <code>&#39;a&#39;</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> letters = [&quot;c&quot;,&quot;f&quot;,&quot;j&quot;], target = &quot;a&quot;
<strong>Output:</strong> &quot;c&quot;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> letters = [&quot;c&quot;,&quot;f&quot;,&quot;j&quot;], target = &quot;c&quot;
<strong>Output:</strong> &quot;f&quot;
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> letters = [&quot;c&quot;,&quot;f&quot;,&quot;j&quot;], target = &quot;d&quot;
<strong>Output:</strong> &quot;f&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= letters.length &lt;= 10<sup>4</sup></code></li>
	<li><code>letters[i]</code> is a lowercase English letter.</li>
	<li><code>letters</code> is sorted in <strong>non-decreasing</strong> order.</li>
	<li><code>letters</code> contains at least two different characters.</li>
	<li><code>target</code> is a lowercase English letter.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def nextGreatestLetter(self, letters: List[str], target: str) -> str:
        left, right = 0, len(letters)
        while left < right:
            mid = (left + right) >> 1
            if ord(letters[mid]) > ord(target):
                right = mid
            else:
                left = mid + 1
        return letters[left % len(letters)]
```

### **Java**

```java
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (letters[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return letters[left % letters.length];
    }
}
```

### **TypeScript**

```ts
function nextGreatestLetter(letters: string[], target: string): string {
    let left = 0,
        right = letters.length;
    let x = target.charCodeAt(0);
    while (left < right) {
        let mid = (left + right) >> 1;
        if (x < letters[mid].charCodeAt(0)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return letters[left % letters.length];
}
```

```ts
function nextGreatestLetter(letters: string[], target: string): string {
    const n = letters.length;
    let l = 0;
    let r = n;
    while (l < r) {
        const mid = (l + r) >>> 1;
        if (target < letters[mid]) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return letters[l % n];
}
```

### **C++**

```cpp
class Solution {
public:
    char nextGreatestLetter(vector<char>& letters, char target) {
        int left = 0, right = letters.size();
        while (left < right) {
            int mid = left + right >> 1;
            if (letters[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return letters[left % letters.size()];
    }
};
```

### **Go**

```go
func nextGreatestLetter(letters []byte, target byte) byte {
	left, right := 0, len(letters)
	for left < right {
		mid := (left + right) >> 1
		if letters[mid] > target {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return letters[left%len(letters)]
}
```

### **Rust**

```rust
impl Solution {
    pub fn next_greatest_letter(letters: Vec<char>, target: char) -> char {
        for c in letters.iter() {
            if c > &target {
                return *c;
            }
        }
        letters[0]
    }
}
```

```rust
impl Solution {
    pub fn next_greatest_letter(letters: Vec<char>, target: char) -> char {
        let n = letters.len();
        let mut l = 0;
        let mut r = n;
        while l < r {
            let mid = l + r >> 1;
            if letters[mid] <= target {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        letters[l % n]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
