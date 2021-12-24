# [1647. Minimum Deletions to Make Character Frequencies Unique](https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique)

[中文文档](/solution/1600-1699/1647.Minimum%20Deletions%20to%20Make%20Character%20Frequencies%20Unique/README.md)

## Description

<p>A string <code>s</code> is called <strong>good</strong> if there are no two different characters in <code>s</code> that have the same <strong>frequency</strong>.</p>

<p>Given a string <code>s</code>, return<em> the <strong>minimum</strong> number of characters you need to delete to make </em><code>s</code><em> <strong>good</strong>.</em></p>

<p>The <strong>frequency</strong> of a character in a string is the number of times it appears in the string. For example, in the string <code>&quot;aab&quot;</code>, the <strong>frequency</strong> of <code>&#39;a&#39;</code> is <code>2</code>, while the <strong>frequency</strong> of <code>&#39;b&#39;</code> is <code>1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aab&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> <code>s</code> is already good.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaabbbcc&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can delete two &#39;b&#39;s resulting in the good string &quot;aaabcc&quot;.
Another way it to delete one &#39;b&#39; and one &#39;c&#39; resulting in the good string &quot;aaabbc&quot;.</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;ceabaacb&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can delete both &#39;c&#39;s resulting in the good string &quot;eabaab&quot;.
Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;contains only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minDeletions(self, s: str) -> int:
        counter = Counter(s)
        vals = [v for v in counter.values()]
        vals.sort(reverse=True)
        ans = 0
        for i in range(1, len(vals)):
            while vals[i] >= vals[i - 1] and vals[i] > 0:
                vals[i] -= 1
                ans += 1
        return ans
```

### **Java**

```java
class Solution {
    public int minDeletions(String s) {
        int[] counter = new int[26];
        for (char c : s.toCharArray()) {
            ++counter[c - 'a'];
        }
        Arrays.sort(counter);
        int ans = 0;
        for (int i = 24; i >= 0; --i) {
            while (counter[i] >= counter[i + 1] && counter[i] > 0) {
                --counter[i];
                ++ans;
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function minDeletions(s: string): number {
    let map = {};
    for (let c of s) {
        map[c] = (map[c] || 0) + 1;
    }
    let ans = 0;
    let vals: number[] = Object.values(map);
    vals.sort((a, b) => a - b);
    for (let i = 1; i < vals.length; ++i) {
        while (vals[i] > 0 && i != vals.indexOf(vals[i])) {
            --vals[i];
            ++ans;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
