---
comment: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/01.06.Compress%20String/README_EN.md
---

# [01.06. Compress String](https://leetcode.cn/problems/compress-string-lcci)

[中文文档](/lcci/01.06.Compress%20String/README.md)

## Description

<p>Implement a method to perform basic string compression using the counts of repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the &quot;compressed&quot; string would not become smaller than the original string, your method should return the original string. You can assume the string has only uppercase and lowercase letters (a - z).</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>&quot;aabcccccaaa&quot;

<strong>Output: </strong>&quot;a2b1c5a3&quot;

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>&quot;abbccd&quot;

<strong>Output: </strong>&quot;abbccd&quot;

<strong>Explanation: </strong>

The compressed string is &quot;a1b2c2d1&quot;, which is longer than the original string.

</pre>

<p><strong>Note:</strong></p>

-   `0 <= S.length <= 50000`

## Solutions

### Solution 1: Two Pointers

We can use two pointers to find the start and end positions of each consecutive character, calculate the length of the consecutive characters, and then append the character and length to the string $t$.

Finally, we compare the lengths of $t$ and $S$. If the length of $t$ is less than $S$, we return $t$, otherwise we return $S$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string.

<!-- tabs:start -->

```python
class Solution:
    def compressString(self, S: str) -> str:
        t = "".join(a + str(len(list(b))) for a, b in groupby(S))
        return min(S, t, key=len)
```

```python
class Solution:
    def compressString(self, S: str) -> str:
        t = []
        i, n = 0, len(S)
        while i < n:
            j = i + 1
            while j < n and S[j] == S[i]:
                j += 1
            t.append(S[i] + str(j - i))
            i = j
        return min(S, "".join(t), key=len)
```

```java
class Solution {
    public String compressString(String S) {
        int n = S.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && S.charAt(j) == S.charAt(i)) {
                ++j;
            }
            sb.append(S.charAt(i)).append(j - i);
            i = j;
        }
        String t = sb.toString();
        return t.length() < n ? t : S;
    }
}
```

```cpp
class Solution {
public:
    string compressString(string S) {
        int n = S.size();
        string t;
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && S[j] == S[i]) {
                ++j;
            }
            t += S[i];
            t += to_string(j - i);
            i = j;
        }
        return t.size() < n ? t : S;
    }
};
```

```go
func compressString(S string) string {
	n := len(S)
	sb := strings.Builder{}
	for i := 0; i < n; {
		j := i + 1
		for j < n && S[j] == S[i] {
			j++
		}
		sb.WriteByte(S[i])
		sb.WriteString(strconv.Itoa(j - i))
		i = j
	}
	if t := sb.String(); len(t) < n {
		return t
	}
	return S
}
```

```rust
impl Solution {
    pub fn compress_string(s: String) -> String {
        let mut cs: Vec<char> = s.chars().collect();
        let mut t = Vec::new();
        let mut i = 0;
        let n = s.len();
        while i < n {
            let mut j = i + 1;
            while j < n && cs[j] == cs[i] {
                j += 1;
            }
            t.push(cs[i]);
            t.extend((j - i).to_string().chars());
            i = j;
        }

        let t = t.into_iter().collect::<String>();
        if s.len() <= t.len() {
            s
        } else {
            t
        }
    }
}
```

```js
/**
 * @param {string} S
 * @return {string}
 */
var compressString = function (S) {
    const n = S.length;
    const t = [];
    for (let i = 0; i < n; ) {
        let j = i + 1;
        while (j < n && S.charAt(j) === S.charAt(i)) {
            ++j;
        }
        t.push(S.charAt(i), j - i);
        i = j;
    }
    return t.length < n ? t.join('') : S;
};
```

```swift
class Solution {
    func compressString(_ S: String) -> String {
        let n = S.count
        var compressed = ""
        var i = 0

        while i < n {
            var j = i
            let currentChar = S[S.index(S.startIndex, offsetBy: i)]
            while j < n && S[S.index(S.startIndex, offsetBy: j)] == currentChar {
                j += 1
            }
            compressed += "\(currentChar)\(j - i)"
            i = j
        }

        return compressed.count < n ? compressed : S
    }
}
```

<!-- tabs:end -->

<!-- end -->
