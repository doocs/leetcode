# [345. Reverse Vowels of a String](https://leetcode.com/problems/reverse-vowels-of-a-string)

[中文文档](/solution/0300-0399/0345.Reverse%20Vowels%20of%20a%20String/README.md)

<!-- tags:Two Pointers,String -->

<!-- difficulty:Easy -->

## Description

<p>Given a string <code>s</code>, reverse only all the vowels in the string and return it.</p>

<p>The vowels are <code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, and <code>&#39;u&#39;</code>, and they can appear in both lower and upper cases, more than once.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s = "hello"
<strong>Output:</strong> "holle"
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s = "leetcode"
<strong>Output:</strong> "leotcede"
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>s</code> consist of <strong>printable ASCII</strong> characters.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def reverseVowels(self, s: str) -> str:
        vowels = "aeiouAEIOU"
        i, j = 0, len(s) - 1
        cs = list(s)
        while i < j:
            while i < j and cs[i] not in vowels:
                i += 1
            while i < j and cs[j] not in vowels:
                j -= 1
            if i < j:
                cs[i], cs[j] = cs[j], cs[i]
                i, j = i + 1, j - 1
        return "".join(cs)
```

```java
class Solution {
    public String reverseVowels(String s) {
        boolean[] vowels = new boolean[128];
        for (char c : "aeiouAEIOU".toCharArray()) {
            vowels[c] = true;
        }
        char[] cs = s.toCharArray();
        int i = 0, j = cs.length - 1;
        while (i < j) {
            while (i < j && !vowels[cs[i]]) {
                ++i;
            }
            while (i < j && !vowels[cs[j]]) {
                --j;
            }
            if (i < j) {
                char t = cs[i];
                cs[i] = cs[j];
                cs[j] = t;
                ++i;
                --j;
            }
        }
        return String.valueOf(cs);
    }
}
```

```cpp
class Solution {
public:
    string reverseVowels(string s) {
        bool vowels[128];
        memset(vowels, false, sizeof(vowels));
        for (char c : "aeiouAEIOU") {
            vowels[c] = true;
        }
        int i = 0, j = s.size() - 1;
        while (i < j) {
            while (i < j && !vowels[s[i]]) {
                ++i;
            }
            while (i < j && !vowels[s[j]]) {
                --j;
            }
            if (i < j) {
                swap(s[i++], s[j--]);
            }
        }
        return s;
    }
};
```

```go
func reverseVowels(s string) string {
	vowels := [128]bool{}
	for _, c := range "aeiouAEIOU" {
		vowels[c] = true
	}
	cs := []byte(s)
	i, j := 0, len(cs)-1
	for i < j {
		for i < j && !vowels[cs[i]] {
			i++
		}
		for i < j && !vowels[cs[j]] {
			j--
		}
		if i < j {
			cs[i], cs[j] = cs[j], cs[i]
			i, j = i+1, j-1
		}
	}
	return string(cs)
}
```

```ts
function reverseVowels(s: string): string {
    const vowels = new Set(['a', 'e', 'i', 'o', 'u']);
    const cs = s.split('');
    for (let i = 0, j = cs.length - 1; i < j; ++i, --j) {
        while (i < j && !vowels.has(cs[i].toLowerCase())) {
            ++i;
        }
        while (i < j && !vowels.has(cs[j].toLowerCase())) {
            --j;
        }
        [cs[i], cs[j]] = [cs[j], cs[i]];
    }
    return cs.join('');
}
```

```rust
impl Solution {
    pub fn reverse_vowels(s: String) -> String {
        let vowel = String::from("aeiouAEIOU");
        let mut data: Vec<char> = s.chars().collect();
        let (mut i, mut j) = (0, s.len() - 1);
        while i < j {
            while i < j && !vowel.contains(data[i]) {
                i += 1;
            }
            while i < j && !vowel.contains(data[j]) {
                j -= 1;
            }
            if i < j {
                data.swap(i, j);
                i += 1;
                j -= 1;
            }
        }
        data.iter().collect()
    }
}
```

<!-- tabs:end -->

<!-- end -->
