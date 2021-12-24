# [345. Reverse Vowels of a String](https://leetcode.com/problems/reverse-vowels-of-a-string)

[中文文档](/solution/0300-0399/0345.Reverse%20Vowels%20of%20a%20String/README.md)

## Description

<p>Given a string <code>s</code>, reverse only all the vowels in the string and return it.</p>

<p>The vowels are <code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, and <code>&#39;u&#39;</code>, and they can appear in both cases.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "hello"
<strong>Output:</strong> "holle"
</pre><p><strong>Example 2:</strong></p>
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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def reverseVowels(self, s: str) -> str:
        vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'}
        i, j = 0, len(s) - 1
        chars = list(s)
        while i < j:
            if chars[i] not in vowels:
                i += 1
                continue
            if chars[j] not in vowels:
                j -= 1
                continue
            chars[i], chars[j] = chars[j], chars[i]
            i += 1
            j -= 1
        return ''.join(chars)
```

### **Java**

```java
class Solution {
    public String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int i = 0, j = s.length() - 1;
        char[] chars = s.toCharArray();
        while (i < j) {
            if (!vowels.contains(chars[i])) {
                ++i;
                continue;
            }
            if (!vowels.contains(chars[j])) {
                --j;
                continue;
            }
            char t = chars[i];
            chars[i] = chars[j];
            chars[j] = t;
            ++i;
            --j;
        }
        return new String(chars);
    }
}
```

### **Go**

```go
func reverseVowels(s string) string {
	left, right := 0, len(s)-1
	a := []byte(s)
	for left < right {
		for left < right && !isVowel(a[left]) {
			left++
		}
		for left < right && !isVowel(a[right]) {
			right--
		}
		if left != right && isVowel(a[left]) && isVowel(a[right]) {
			a[left], a[right] = a[right], a[left]
			left++
			right--
		}
	}
	return string(a)
}

func isVowel(b byte) bool {
	return b == 'a' || b == 'e' || b == 'i' || b == 'o' || b == 'u' ||
		b == 'A' || b == 'E' || b == 'I' || b == 'O' || b == 'U'
}
```

### **...**

```

```

<!-- tabs:end -->
