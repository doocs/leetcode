# [2262. Total Appeal of A String](https://leetcode.com/problems/total-appeal-of-a-string)

[中文文档](/solution/2200-2299/2262.Total%20Appeal%20of%20A%20String/README.md)

## Description

<p>The <b>appeal</b> of a string is the number of <strong>distinct</strong> characters found in the string.</p>

<ul>
	<li>For example, the appeal of <code>&quot;abbca&quot;</code> is <code>3</code> because it has <code>3</code> distinct characters: <code>&#39;a&#39;</code>, <code>&#39;b&#39;</code>, and <code>&#39;c&#39;</code>.</li>
</ul>

<p>Given a string <code>s</code>, return <em>the <strong>total appeal of all of its <strong>substrings</strong>.</strong></em></p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abbca&quot;
<strong>Output:</strong> 28
<strong>Explanation:</strong> The following are the substrings of &quot;abbca&quot;:
- Substrings of length 1: &quot;a&quot;, &quot;b&quot;, &quot;b&quot;, &quot;c&quot;, &quot;a&quot; have an appeal of 1, 1, 1, 1, and 1 respectively. The sum is 5.
- Substrings of length 2: &quot;ab&quot;, &quot;bb&quot;, &quot;bc&quot;, &quot;ca&quot; have an appeal of 2, 1, 2, and 2 respectively. The sum is 7.
- Substrings of length 3: &quot;abb&quot;, &quot;bbc&quot;, &quot;bca&quot; have an appeal of 2, 2, and 3 respectively. The sum is 7.
- Substrings of length 4: &quot;abbc&quot;, &quot;bbca&quot; have an appeal of 3 and 3 respectively. The sum is 6.
- Substrings of length 5: &quot;abbca&quot; has an appeal of 3. The sum is 3.
The total sum is 5 + 7 + 7 + 6 + 3 = 28.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;code&quot;
<strong>Output:</strong> 20
<strong>Explanation:</strong> The following are the substrings of &quot;code&quot;:
- Substrings of length 1: &quot;c&quot;, &quot;o&quot;, &quot;d&quot;, &quot;e&quot; have an appeal of 1, 1, 1, and 1 respectively. The sum is 4.
- Substrings of length 2: &quot;co&quot;, &quot;od&quot;, &quot;de&quot; have an appeal of 2, 2, and 2 respectively. The sum is 6.
- Substrings of length 3: &quot;cod&quot;, &quot;ode&quot; have an appeal of 3 and 3 respectively. The sum is 6.
- Substrings of length 4: &quot;code&quot; has an appeal of 4. The sum is 4.
The total sum is 4 + 6 + 6 + 4 = 20.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def appealSum(self, s: str) -> int:
        ans = t = 0
        pos = [-1] * 26
        for i, c in enumerate(s):
            c = ord(c) - ord('a')
            t += i - pos[c]
            ans += t
            pos[c] = i
        return ans
```

### **Java**

```java
class Solution {
    public long appealSum(String s) {
        long ans = 0;
        long t = 0;
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        for (int i = 0; i < s.length(); ++i) {
            int c = s.charAt(i) - 'a';
            t += i - pos[c];
            ans += t;
            pos[c] = i;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long appealSum(string s) {
        long long ans = 0, t = 0;
        vector<int> pos(26, -1);
        for (int i = 0; i < s.size(); ++i) {
            int c = s[i] - 'a';
            t += i - pos[c];
            ans += t;
            pos[c] = i;
        }
        return ans;
    }
};
```

### **Go**

```go
func appealSum(s string) int64 {
	var ans, t int64
	pos := make([]int, 26)
	for i := range pos {
		pos[i] = -1
	}
	for i, c := range s {
		c -= 'a'
		t += int64(i - pos[c])
		ans += t
		pos[c] = i
	}
	return ans
}
```

### **TypeScript**

```ts
function appealSum(s: string): number {
    const n = s.length;
    let dp = new Array(n + 1).fill(0);
    const hashMap = new Map();
    for (let i = 0; i < n; i++) {
        const c = s.charAt(i);
        dp[i + 1] = dp[i] + i + 1 - (hashMap.get(c) || 0);
        hashMap.set(c, i + 1);
    }
    return dp.reduce((a, c) => a + c, 0);
}
```

### **...**

```

```

<!-- tabs:end -->
