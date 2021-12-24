# [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring)

[中文文档](/solution/0000-0099/0076.Minimum%20Window%20Substring/README.md)

## Description

<p>Given two strings <code>s</code> and <code>t</code>, return <em>the minimum window in <code>s</code> which will contain all the characters in <code>t</code></em>. If there is no such window in <code>s</code> that covers all characters in <code>t</code>, return <em>the empty string <code>&quot;&quot;</code></em>.</p>

<p><strong>Note</strong> that If there is such a window, it is&nbsp;guaranteed that there will always be only one unique minimum window in <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> s = "ADOBECODEBANC", t = "ABC"
<strong>Output:</strong> "BANC"
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> s = "a", t = "a"
<strong>Output:</strong> "a"
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> and <code>t</code> consist of English letters.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you find an algorithm that runs in <code>O(n)</code> time?

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts
function minWindow(s: string, t: string): string {
    let n1 = s.length,
        n2 = t.length;
    if (n1 < n2) return "";
    let need = new Array(128).fill(0);
    let window = new Array(128).fill(0);
    for (let i = 0; i < n2; ++i) {
        ++need[t.charCodeAt(i)];
    }

    let left = 0,
        right = 0;
    let res = "";
    let count = 0;
    let min = n1 + 1;
    while (right < n1) {
        let cur = s.charCodeAt(right);
        ++window[cur];
        if (need[cur] > 0 && need[cur] >= window[cur]) {
            ++count;
        }
        while (count == n2) {
            cur = s.charCodeAt(left);
            if (need[cur] > 0 && need[cur] >= window[cur]) {
                --count;
            }
            if (right - left + 1 < min) {
                min = right - left + 1;
                res = s.slice(left, right + 1);
            }
            --window[cur];
            ++left;
        }
        ++right;
    }
    return res;
}
```

### **...**

```

```

<!-- tabs:end -->
