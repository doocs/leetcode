# [2186. Minimum Number of Steps to Make Two Strings Anagram II](https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram-ii)

[中文文档](/solution/2100-2199/2186.Minimum%20Number%20of%20Steps%20to%20Make%20Two%20Strings%20Anagram%20II/README.md)

## Description

<p>You are given two strings <code>s</code> and <code>t</code>. In one step, you can append <strong>any character</strong> to either <code>s</code> or <code>t</code>.</p>

<p>Return <em>the minimum number of steps to make </em><code>s</code><em> and </em><code>t</code><em> <strong>anagrams</strong> of each other.</em></p>

<p>An <strong>anagram</strong> of a string is a string that contains the same characters with a different (or the same) ordering.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;<strong><u>lee</u></strong>tco<u><strong>de</strong></u>&quot;, t = &quot;co<u><strong>a</strong></u>t<u><strong>s</strong></u>&quot;
<strong>Output:</strong> 7
<strong>Explanation:</strong> 
- In 2 steps, we can append the letters in &quot;as&quot; onto s = &quot;leetcode&quot;, forming s = &quot;leetcode<strong><u>as</u></strong>&quot;.
- In 5 steps, we can append the letters in &quot;leede&quot; onto t = &quot;coats&quot;, forming t = &quot;coats<u><strong>leede</strong></u>&quot;.
&quot;leetcodeas&quot; and &quot;coatsleede&quot; are now anagrams of each other.
We used a total of 2 + 5 = 7 steps.
It can be shown that there is no way to make them anagrams of each other with less than 7 steps.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;night&quot;, t = &quot;thing&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> The given strings are already anagrams of each other. Thus, we do not need any further steps.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>s</code> and <code>t</code> consist of lowercase English letters.</li>
</ul>

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
function minSteps(s: string, t: string): number {
    let count1 = new Array(128).fill(0);
    let count2 = new Array(128).fill(0);
    for (let char of s) {
        count1[char.charCodeAt(0)]++;
    }
    for (let char of t) {
        count2[char.charCodeAt(0)]++;
    }
    let ans = 0;
    for (let i = 0; i < 128; i++) {
        ans += Math.abs(count1[i] - count2[i]);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
