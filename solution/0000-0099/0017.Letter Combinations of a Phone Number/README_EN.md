# [17. Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number)

[中文文档](/solution/0000-0099/0017.Letter%20Combinations%20of%20a%20Phone%20Number/README.md)

## Description

<p>Given a string containing digits from <code>2-9</code> inclusive, return all possible letter combinations that the number could represent. Return the answer in <strong>any order</strong>.</p>

<p>A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.</p>

<p><img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0017.Letter%20Combinations%20of%20a%20Phone%20Number/images/200px-Telephone-keypad2.svg.png" style="width: 200px; height: 162px;" /></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> digits = &quot;23&quot;
<strong>Output:</strong> [&quot;ad&quot;,&quot;ae&quot;,&quot;af&quot;,&quot;bd&quot;,&quot;be&quot;,&quot;bf&quot;,&quot;cd&quot;,&quot;ce&quot;,&quot;cf&quot;]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> digits = &quot;&quot;
<strong>Output:</strong> []
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> digits = &quot;2&quot;
<strong>Output:</strong> [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= digits.length &lt;= 4</code></li>
	<li><code>digits[i]</code> is a digit in the range <code>[&#39;2&#39;, &#39;9&#39;]</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        n = len(digits)
        if n == 0:
            return []
        chars = ['abc', 'def', 'ghi', 'jkl', 'mno', 'pqrs', 'tuv', 'wxyz']
        strs = [chars[int(d) - 2] for d in digits]
        res = []
        for s in strs:
            if not res:
                res = list(s)
            else:
                cache = []
                for item in res:
                    for letter in s:
                        cache.append(item + letter)
                res = cache
        return res
```

### **Java**

```java
class Solution {
    public List<String> letterCombinations(String digits) {
        int n;
        if ((n = digits.length()) == 0) return Collections.emptyList();
        List<String> chars = Arrays.asList("abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz");

        List<String> strs = new ArrayList<>();
        for (char c : digits.toCharArray()) {
            strs.add(chars.get(c - '0' - 2));
        }
        List<String> res = new ArrayList<>();
        for (String str : strs) {
            if (res.size() == 0) {
                for (char c : str.toCharArray()) {
                    res.add(String.valueOf(c));
                }
            } else {
                List<String> cache = new ArrayList<>();
                for (String item : res) {
                    for (char c : str.toCharArray()) {
                        cache.add(item + String.valueOf(c));
                    }
                }
                res = cache;
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
