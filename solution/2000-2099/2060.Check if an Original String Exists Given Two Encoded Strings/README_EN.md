# [2060. Check if an Original String Exists Given Two Encoded Strings](https://leetcode.com/problems/check-if-an-original-string-exists-given-two-encoded-strings)

[中文文档](/solution/2000-2099/2060.Check%20if%20an%20Original%20String%20Exists%20Given%20Two%20Encoded%20Strings/README.md)

## Description

<p>An original string, consisting of lowercase English letters, can be encoded by the following steps:</p>

<ul>
	<li>Arbitrarily <strong>split</strong> it into a <strong>sequence</strong> of some number of <strong>non-empty</strong> substrings.</li>
	<li>Arbitrarily choose some elements (possibly none) of the sequence, and <strong>replace</strong> each with <strong>its length</strong> (as a numeric string).</li>
	<li><strong>Concatenate</strong> the sequence as the encoded string.</li>
</ul>

<p>For example, <strong>one way</strong> to encode an original string <code>&quot;abcdefghijklmnop&quot;</code> might be:</p>

<ul>
	<li>Split it as a sequence: <code>[&quot;ab&quot;, &quot;cdefghijklmn&quot;, &quot;o&quot;, &quot;p&quot;]</code>.</li>
	<li>Choose the second and third elements to be replaced by their lengths, respectively. The sequence becomes <code>[&quot;ab&quot;, &quot;12&quot;, &quot;1&quot;, &quot;p&quot;]</code>.</li>
	<li>Concatenate the elements of the sequence to get the encoded string: <code>&quot;ab121p&quot;</code>.</li>
</ul>

<p>Given two encoded strings <code>s1</code> and <code>s2</code>, consisting of lowercase English letters and digits <code>1-9</code> (inclusive), return <code>true</code><em> if there exists an original string that could be encoded as <strong>both</strong> </em><code>s1</code><em> and </em><code>s2</code><em>. Otherwise, return </em><code>false</code>.</p>

<p><strong>Note</strong>: The test cases are generated such that the number of consecutive digits in <code>s1</code> and <code>s2</code> does not exceed <code>3</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;internationalization&quot;, s2 = &quot;i18n&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> It is possible that &quot;internationalization&quot; was the original string.
- &quot;internationalization&quot; 
  -&gt; Split:       [&quot;internationalization&quot;]
  -&gt; Do not replace any element
  -&gt; Concatenate:  &quot;internationalization&quot;, which is s1.
- &quot;internationalization&quot;
  -&gt; Split:       [&quot;i&quot;, &quot;nternationalizatio&quot;, &quot;n&quot;]
  -&gt; Replace:     [&quot;i&quot;, &quot;18&quot;,                 &quot;n&quot;]
  -&gt; Concatenate:  &quot;i18n&quot;, which is s2
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;l123e&quot;, s2 = &quot;44&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> It is possible that &quot;leetcode&quot; was the original string.
- &quot;leetcode&quot; 
  -&gt; Split:      [&quot;l&quot;, &quot;e&quot;, &quot;et&quot;, &quot;cod&quot;, &quot;e&quot;]
  -&gt; Replace:    [&quot;l&quot;, &quot;1&quot;, &quot;2&quot;,  &quot;3&quot;,   &quot;e&quot;]
  -&gt; Concatenate: &quot;l123e&quot;, which is s1.
- &quot;leetcode&quot; 
  -&gt; Split:      [&quot;leet&quot;, &quot;code&quot;]
  -&gt; Replace:    [&quot;4&quot;,    &quot;4&quot;]
  -&gt; Concatenate: &quot;44&quot;, which is s2.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;a5b&quot;, s2 = &quot;c5b&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible.
- The original string encoded as s1 must start with the letter &#39;a&#39;.
- The original string encoded as s2 must start with the letter &#39;c&#39;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 40</code></li>
	<li><code>s1</code> and <code>s2</code> consist of digits <code>1-9</code> (inclusive), and lowercase English letters only.</li>
	<li>The number of consecutive digits in <code>s1</code> and <code>s2</code> does not exceed <code>3</code>.</li>
</ul>

## Solutions

Dynamic Programming

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts
function possiblyEquals(s1: string, s2: string): boolean {
    const n = s1.length,
        m = s2.length;
    let dp: Array<Array<Set<number>>> = Array.from({ length: n + 1 }, v =>
        Array.from({ length: m + 1 }, w => new Set()),
    );
    dp[0][0].add(0);

    for (let i = 0; i <= n; i++) {
        for (let j = 0; j <= m; j++) {
            for (let delta of dp[i][j]) {
                // s1为数字
                let num = 0;
                if (delta <= 0) {
                    for (let p = i; i < Math.min(i + 3, n); p++) {
                        if (isDigit(s1[p])) {
                            num = num * 10 + Number(s1[p]);
                            dp[p + 1][j].add(delta + num);
                        } else {
                            break;
                        }
                    }
                }

                // s2为数字
                num = 0;
                if (delta >= 0) {
                    for (let q = j; q < Math.min(j + 3, m); q++) {
                        if (isDigit(s2[q])) {
                            num = num * 10 + Number(s2[q]);
                            dp[i][q + 1].add(delta - num);
                        } else {
                            break;
                        }
                    }
                }

                // 数字匹配s1为字母
                if (i < n && delta < 0 && !isDigit(s1[i])) {
                    dp[i + 1][j].add(delta + 1);
                }

                // 数字匹配s2为字母
                if (j < m && delta > 0 && !isDigit(s2[j])) {
                    dp[i][j + 1].add(delta - 1);
                }

                // 两个字母匹配
                if (i < n && j < m && delta == 0 && s1[i] == s2[j]) {
                    dp[i + 1][j + 1].add(0);
                }
            }
        }
    }
    return dp[n][m].has(0);
}

function isDigit(char: string): boolean {
    return /^\d{1}$/g.test(char);
}
```

### **...**

```

```

<!-- tabs:end -->
