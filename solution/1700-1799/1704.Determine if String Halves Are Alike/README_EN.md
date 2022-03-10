# [1704. Determine if String Halves Are Alike](https://leetcode.com/problems/determine-if-string-halves-are-alike)

[中文文档](/solution/1700-1799/1704.Determine%20if%20String%20Halves%20Are%20Alike/README.md)

## Description

<p>You are given a string <code>s</code> of even length. Split this string into two halves of equal lengths, and let <code>a</code> be the first half and <code>b</code> be the second half.</p>

<p>Two strings are <strong>alike</strong> if they have the same number of vowels (<code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, <code>&#39;u&#39;</code>, <code>&#39;A&#39;</code>, <code>&#39;E&#39;</code>, <code>&#39;I&#39;</code>, <code>&#39;O&#39;</code>, <code>&#39;U&#39;</code>). Notice that <code>s</code> contains uppercase and lowercase letters.</p>

<p>Return <code>true</code><em> if </em><code>a</code><em> and </em><code>b</code><em> are <strong>alike</strong></em>. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;book&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> a = &quot;b<u>o</u>&quot; and b = &quot;<u>o</u>k&quot;. a has 1 vowel and b has 1 vowel. Therefore, they are alike.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;textbook&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> a = &quot;t<u>e</u>xt&quot; and b = &quot;b<u>oo</u>k&quot;. a has 1 vowel whereas b has 2. Therefore, they are not alike.
Notice that the vowel o is counted twice.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s.length</code> is even.</li>
	<li><code>s</code> consists of <strong>uppercase and lowercase</strong> letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def halvesAreAlike(self, s: str) -> bool:
        half = len(s) >> 1
        vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'}
        s1 = sum(1 for c in s[:half] if c in vowels)
        s2 = sum(1 for c in s[half:] if c in vowels)
        return s1 == s2
```

### **Java**

```java
class Solution {
    public boolean halvesAreAlike(String s) {
        int half = s.length() >> 1;
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int s1 = 0, s2 = 0;
        for (int i = 0; i < half; ++i) {
            if (vowels.contains(s.charAt(i))) {
                ++s1;
            }
            if (vowels.contains(s.charAt(half + i))) {
                ++s2;
            }
        }
        return s1 == s2;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
