# [1616. Split Two Strings to Make Palindrome](https://leetcode.com/problems/split-two-strings-to-make-palindrome)

[中文文档](/solution/1600-1699/1616.Split%20Two%20Strings%20to%20Make%20Palindrome/README.md)

## Description

<p>You are given two strings <code>a</code> and <code>b</code> of the same length. Choose an index and split both strings <strong>at the same index</strong>, splitting <code>a</code> into two strings: <code>a<sub>prefix</sub></code> and <code>a<sub>suffix</sub></code> where <code>a = a<sub>prefix</sub> + a<sub>suffix</sub></code>, and splitting <code>b</code> into two strings: <code>b<sub>prefix</sub></code> and <code>b<sub>suffix</sub></code> where <code>b = b<sub>prefix</sub> + b<sub>suffix</sub></code>. Check if <code>a<sub>prefix</sub> + b<sub>suffix</sub></code> or <code>b<sub>prefix</sub> + a<sub>suffix</sub></code> forms a palindrome.</p>

<p>When you split a string <code>s</code> into <code>s<sub>prefix</sub></code> and <code>s<sub>suffix</sub></code>, either <code>s<sub>suffix</sub></code> or <code>s<sub>prefix</sub></code> is allowed to be empty. For example, if <code>s = &quot;abc&quot;</code>, then <code>&quot;&quot; + &quot;abc&quot;</code>, <code>&quot;a&quot; + &quot;bc&quot;</code>, <code>&quot;ab&quot; + &quot;c&quot;</code> , and <code>&quot;abc&quot; + &quot;&quot;</code> are valid splits.</p>

<p>Return <code>true</code><em> if it is possible to form</em><em> a palindrome string, otherwise return </em><code>false</code>.</p>

<p><strong>Notice</strong> that&nbsp;<code>x + y</code> denotes the concatenation of strings <code>x</code> and <code>y</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;x&quot;, b = &quot;y&quot;
<strong>Output:</strong> true
<strong>Explaination:</strong> If either a or b are palindromes the answer is true since you can split in the following way:
a<sub>prefix</sub> = &quot;&quot;, a<sub>suffix</sub> = &quot;x&quot;
b<sub>prefix</sub> = &quot;&quot;, b<sub>suffix</sub> = &quot;y&quot;
Then, a<sub>prefix</sub> + b<sub>suffix</sub> = &quot;&quot; + &quot;y&quot; = &quot;y&quot;, which is a palindrome.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;xbdef&quot;, b = &quot;xecab&quot;
<strong>Output:</strong> false
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;ulacfd&quot;, b = &quot;jizalu&quot;
<strong>Output:</strong> true
<strong>Explaination:</strong> Split them at index 3:
a<sub>prefix</sub> = &quot;ula&quot;, a<sub>suffix</sub> = &quot;cfd&quot;
b<sub>prefix</sub> = &quot;jiz&quot;, b<sub>suffix</sub> = &quot;alu&quot;
Then, a<sub>prefix</sub> + b<sub>suffix</sub> = &quot;ula&quot; + &quot;alu&quot; = &quot;ulaalu&quot;, which is a palindrome.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 10<sup>5</sup></code></li>
	<li><code>a.length == b.length</code></li>
	<li><code>a</code> and <code>b</code> consist of lowercase English letters</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def checkPalindromeFormation(self, a: str, b: str) -> bool:
        def check1(a: str, b: str) -> bool:
            i, j = 0, len(b) - 1
            while i < j and a[i] == b[j]:
                i, j = i + 1, j - 1
            return i >= j or check2(a, i, j) or check2(b, i, j)

        def check2(a: str, i: int, j: int) -> bool:
            return a[i: j + 1] == a[i: j + 1][::-1]

        return check1(a, b) or check1(b, a)
```

### **Java**

```java
class Solution {
    public boolean checkPalindromeFormation(String a, String b) {
        return check1(a, b) || check1(b, a);
    }

    private boolean check1(String a, String b) {
        int i = 0;
        int j = b.length() - 1;
        while (i < j && a.charAt(i) == b.charAt(j)) {
            i++;
            j--;
        }
        return i >= j || check2(a, i, j) || check2(b, i, j);
    }

    private boolean check2(String a, int i, int j) {
        while (i < j && a.charAt(i) == a.charAt(j)) {
            i++;
            j--;
        }
        return i >= j;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool checkPalindromeFormation(string a, string b) {
        return check1(a, b) || check1(b, a);
    }

private:
    bool check1(string &a, string &b) {
        int i = 0, j = b.size() - 1;
        while (i < j && a[i] == b[j]) {
            ++i;
            --j;
        }
        return i >= j || check2(a, i, j) || check2(b, i, j);
    }

    bool check2(string &a, int i, int j) {
        while (i <= j && a[i] == a[j]) {
            ++i;
            --j;
        }
        return i >= j;
    }
};
```

### **Go**

```go
func checkPalindromeFormation(a string, b string) bool {
	return check1(a, b) || check1(b, a)
}

func check1(a, b string) bool {
	i, j := 0, len(b)-1
	for i < j && a[i] == b[j] {
		i++
		j--
	}
	return i >= j || check2(a, i, j) || check2(b, i, j)
}

func check2(a string, i, j int) bool {
	for i < j && a[i] == a[j] {
		i++
		j--
	}
	return i >= j
}
```

### **Rust**

```rust
impl Solution {
    pub fn check_palindrome_formation(a: String, b: String) -> bool {
        fn check1(a: &[u8], b: &[u8]) -> bool {
            let (mut i, mut j) = (0, b.len() - 1);
            while i < j && a[i] == b[j] {
                i += 1;
                j -= 1;
            }
            if i >= j {
                return true;
            }
            check2(a, i, j) || check2(b, i, j)
        }

        fn check2(a: &[u8], mut i: usize, mut j: usize) -> bool {
            while i < j && a[i] == a[j] {
                i += 1;
                j -= 1;
            }
            i >= j
        }

        let a = a.as_bytes();
        let b = b.as_bytes();
        check1(a, b) || check1(b, a)
    }
}
```

### **TypeScript**

```ts
function checkPalindromeFormation(a: string, b: string): boolean {
    const check1 = (a: string, b: string) => {
        let i = 0;
        let j = b.length - 1;
        while (i < j && a.charAt(i) === b.charAt(j)) {
            i++;
            j--;
        }
        return i >= j || check2(a, i, j) || check2(b, i, j);
    };

    const check2 = (a: string, i: number, j: number) => {
        while (i < j && a.charAt(i) === a.charAt(j)) {
            i++;
            j--;
        }
        return i >= j;
    };
    return check1(a, b) || check1(b, a);
}
```

### **...**

```

```

<!-- tabs:end -->
