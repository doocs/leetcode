# [831. Masking Personal Information](https://leetcode.com/problems/masking-personal-information)

[中文文档](/solution/0800-0899/0831.Masking%20Personal%20Information/README.md)

## Description

<p>You are given a personal information string <code>s</code>, representing either an <strong>email address</strong> or a <strong>phone number</strong>. Return <em>the <strong>masked</strong> personal information using the below rules</em>.</p>

<p><u><strong>Email address:</strong></u></p>

<p>An email address is:</p>

<ul>
	<li>A <strong>name</strong> consisting of uppercase and lowercase English letters, followed by</li>
	<li>The <code>&#39;@&#39;</code> symbol, followed by</li>
	<li>The <strong>domain</strong> consisting of uppercase and lowercase English letters with a dot <code>&#39;.&#39;</code> somewhere in the middle (not the first or last character).</li>
</ul>

<p>To mask an email:</p>

<ul>
	<li>The uppercase letters in the <strong>name</strong> and <strong>domain</strong> must be converted to lowercase letters.</li>
	<li>The middle letters of the <strong>name</strong> (i.e., all but the first and last letters) must be replaced by 5 asterisks <code>&quot;*****&quot;</code>.</li>
</ul>

<p><u><strong>Phone number:</strong></u></p>

<p>A phone number is formatted as follows:</p>

<ul>
	<li>The phone number contains 10-13 digits.</li>
	<li>The last 10 digits make up the <strong>local number</strong>.</li>
	<li>The remaining 0-3 digits, in the beginning, make up the <strong>country code</strong>.</li>
	<li><strong>Separation characters</strong> from the set <code>{&#39;+&#39;, &#39;-&#39;, &#39;(&#39;, &#39;)&#39;, &#39; &#39;}</code> separate the above digits in some way.</li>
</ul>

<p>To mask a phone number:</p>

<ul>
	<li>Remove all <strong>separation characters</strong>.</li>
	<li>The masked phone number should have the form:
	<ul>
		<li><code>&quot;***-***-XXXX&quot;</code> if the country code has 0 digits.</li>
		<li><code>&quot;+*-***-***-XXXX&quot;</code> if the country code has 1 digit.</li>
		<li><code>&quot;+**-***-***-XXXX&quot;</code> if the country code has 2 digits.</li>
		<li><code>&quot;+***-***-***-XXXX&quot;</code> if the country code has 3 digits.</li>
	</ul>
	</li>
	<li><code>&quot;XXXX&quot;</code> is the last 4 digits of the <strong>local number</strong>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;LeetCode@LeetCode.com&quot;
<strong>Output:</strong> &quot;l*****e@leetcode.com&quot;
<strong>Explanation:</strong> s is an email address.
The name and domain are converted to lowercase, and the middle of the name is replaced by 5 asterisks.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;AB@qq.com&quot;
<strong>Output:</strong> &quot;a*****b@qq.com&quot;
<strong>Explanation:</strong> s is an email address.
The name and domain are converted to lowercase, and the middle of the name is replaced by 5 asterisks.
Note that even though &quot;ab&quot; is 2 characters, it still must have 5 asterisks in the middle.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1(234)567-890&quot;
<strong>Output:</strong> &quot;***-***-7890&quot;
<strong>Explanation:</strong> s is a phone number.
There are 10 digits, so the local number is 10 digits and the country code is 0 digits.
Thus, the resulting masked number is &quot;***-***-7890&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>s</code> is either a <strong>valid</strong> email or a phone number.</li>
	<li>If <code>s</code> is an email:
	<ul>
		<li><code>8 &lt;= s.length &lt;= 40</code></li>
		<li><code>s</code> consists of uppercase and lowercase English letters and exactly one <code>&#39;@&#39;</code> symbol and <code>&#39;.&#39;</code> symbol.</li>
	</ul>
	</li>
	<li>If <code>s</code> is a phone number:
	<ul>
		<li><code>10 &lt;= s.length &lt;= 20</code></li>
		<li><code>s</code> consists of digits, spaces, and the symbols <code>&#39;(&#39;</code>, <code>&#39;)&#39;</code>, <code>&#39;-&#39;</code>, and <code>&#39;+&#39;</code>.</li>
	</ul>
	</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maskPII(self, s: str) -> str:
        if s[0].isalpha():
            s = s.lower()
            return s[0] + '*****' + s[s.find('@') - 1:]
        s = ''.join(c for c in s if c.isdigit())
        cnt = len(s) - 10
        suf = '***-***-' + s[-4:]
        return suf if cnt == 0 else f'+{"*" * cnt}-{suf}'
```

### **Java**

```java
class Solution {
    public String maskPII(String s) {
        if (Character.isLetter(s.charAt(0))) {
            s = s.toLowerCase();
            int i = s.indexOf('@');
            return s.substring(0, 1) + "*****" + s.substring(i - 1);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }
        s = sb.toString();
        int cnt = s.length() - 10;
        String suf = "***-***-" + s.substring(s.length() - 4);
        return cnt == 0 ? suf
                        : "+"
                + "*".repeat(cnt) + "-" + suf;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string maskPII(string s) {
        int i = s.find('@');
        if (i != -1) {
            string ans;
            ans += tolower(s[0]);
            ans += "*****";
            for (int j = i - 1; j < s.size(); ++j) {
                ans += tolower(s[j]);
            }
            return ans;
        }
        string t;
        for (char c : s) {
            if (isdigit(c)) {
                t += c;
            }
        }
        int cnt = t.size() - 10;
        string suf = "***-***-" + t.substr(t.size() - 4);
        return cnt == 0 ? suf : "+" + string(cnt, '*') + "-" + suf;
    }
};
```

### **Go**

```go
func maskPII(s string) string {
	i := strings.Index(s, "@")
	if i != -1 {
		s = strings.ToLower(s)
		return s[0:1] + "*****" + s[i-1:]
	}
	t := []rune{}
	for _, c := range s {
		if c >= '0' && c <= '9' {
			t = append(t, c)
		}
	}
	s = string(t)
	cnt := len(s) - 10
	suf := "***-***-" + s[len(s)-4:]
	if cnt == 0 {
		return suf
	}
	return "+" + strings.Repeat("*", cnt) + "-" + suf
}
```

### **TypeScript**

```ts
function maskPII(s: string): string {
    const i = s.indexOf('@');
    if (i !== -1) {
        let ans = s[0].toLowerCase() + '*****';
        for (let j = i - 1; j < s.length; ++j) {
            ans += s.charAt(j).toLowerCase();
        }
        return ans;
    }
    let t = '';
    for (const c of s) {
        if (/\d/.test(c)) {
            t += c;
        }
    }
    const cnt = t.length - 10;
    const suf = `***-***-${t.substring(t.length - 4)}`;
    return cnt === 0 ? suf : `+${'*'.repeat(cnt)}-${suf}`;
}
```

### **...**

```

```

<!-- tabs:end -->
