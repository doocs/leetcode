# [1888. Minimum Number of Flips to Make the Binary String Alternating](https://leetcode.com/problems/minimum-number-of-flips-to-make-the-binary-string-alternating)

[中文文档](/solution/1800-1899/1888.Minimum%20Number%20of%20Flips%20to%20Make%20the%20Binary%20String%20Alternating/README.md)

## Description

<p>You are given a binary string <code>s</code>. You are allowed to perform two types of operations on the string in any sequence:</p>

<ul>
	<li><strong>Type-1: Remove</strong> the character at the start of the string <code>s</code> and <strong>append</strong> it to the end of the string.</li>
	<li><strong>Type-2: Pick</strong> any character in <code>s</code> and <strong>flip</strong> its value, i.e., if its value is <code>&#39;0&#39;</code> it becomes <code>&#39;1&#39;</code> and vice-versa.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> number of <strong>type-2</strong> operations you need to perform</em> <em>such that </em><code>s</code> <em>becomes <strong>alternating</strong>.</em></p>

<p>The string is called <strong>alternating</strong> if no two adjacent characters are equal.</p>

<ul>
	<li>For example, the strings <code>&quot;010&quot;</code> and <code>&quot;1010&quot;</code> are alternating, while the string <code>&quot;0100&quot;</code> is not.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;111000&quot;
<strong>Output:</strong> 2
<strong>Explanation</strong>: Use the first operation two times to make s = &quot;100011&quot;.
Then, use the second operation on the third and sixth elements to make s = &quot;10<u>1</u>01<u>0</u>&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;010&quot;
<strong>Output:</strong> 0
<strong>Explanation</strong>: The string is already alternating.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1110&quot;
<strong>Output:</strong> 1
<strong>Explanation</strong>: Use the second operation on the second element to make s = &quot;1<u>0</u>10&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minFlips(self, s: str) -> int:
        n = len(s)
        target = '01'
        cnt = 0
        for i, c in enumerate(s):
            cnt += c != target[i & 1]
        res = min(cnt, n - cnt)
        for i in range(n):
            cnt -= s[i] != target[i & 1]
            cnt += s[i] != target[(i + n) & 1]
            res = min(res, cnt, n - cnt)
        return res
```

### **Java**

```java
class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String target = "01";
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            cnt += (s.charAt(i) == target.charAt(i & 1) ? 0 : 1);
        }
        int res = Math.min(cnt, n - cnt);
        for (int i = 0; i < n; ++i) {
            cnt -= (s.charAt(i) == target.charAt(i & 1) ? 0 : 1);
            cnt += (s.charAt(i) == target.charAt((i + n) & 1) ? 0 : 1);
            res = Math.min(res, Math.min(cnt, n - cnt));
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function minFlips(s: string): number {
    const n: number = s.length;
    const target: string[] = ['0', '1'];
    let count: number = 0;
    for (let i: number = 0; i < n; ++i) {
        count += s.charAt(i) == target[i & 1] ? 0 : 1;
    }
    let res = Math.min(count, n - count);
    for (let i: number = 0; i < n; ++i) {
        count -= s.charAt(i) == target[i & 1] ? 0 : 1;
        count += s.charAt(i) == target[(i + n) & 1] ? 0 : 1;
        res = Math.min(res, count, n - count);
    }
    return res;
}
```

### **...**

```

```

<!-- tabs:end -->
