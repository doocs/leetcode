# [1915. Number of Wonderful Substrings](https://leetcode.com/problems/number-of-wonderful-substrings)

[中文文档](/solution/1900-1999/1915.Number%20of%20Wonderful%20Substrings/README.md)

## Description

<p>A <strong>wonderful</strong> string is a string where <strong>at most one</strong> letter appears an <strong>odd</strong> number of times.</p>

<ul>

    <li>For example, <code>&quot;ccjjc&quot;</code> and <code>&quot;abab&quot;</code> are wonderful, but <code>&quot;ab&quot;</code> is not.</li>

</ul>

<p>Given a string <code>word</code> that consists of the first ten lowercase English letters (<code>&#39;a&#39;</code> through <code>&#39;j&#39;</code>), return <em>the <strong>number of wonderful non-empty substrings</strong> in </em><code>word</code><em>. If the same substring appears multiple times in </em><code>word</code><em>, then count <strong>each occurrence</strong> separately.</em></p>

<p>A <strong>substring</strong> is a contiguous sequence of characters in a string.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> word = &quot;aba&quot;

<strong>Output:</strong> 4

<strong>Explanation:</strong> The four wonderful substrings are underlined below:

- &quot;<u><strong>a</strong></u>ba&quot; -&gt; &quot;a&quot;

- &quot;a<u><strong>b</strong></u>a&quot; -&gt; &quot;b&quot;

- &quot;ab<u><strong>a</strong></u>&quot; -&gt; &quot;a&quot;

- &quot;<u><strong>aba</strong></u>&quot; -&gt; &quot;aba&quot;

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> word = &quot;aabb&quot;

<strong>Output:</strong> 9

<strong>Explanation:</strong> The nine wonderful substrings are underlined below:

- &quot;<strong><u>a</u></strong>abb&quot; -&gt; &quot;a&quot;

- &quot;<u><strong>aa</strong></u>bb&quot; -&gt; &quot;aa&quot;

- &quot;<u><strong>aab</strong></u>b&quot; -&gt; &quot;aab&quot;

- &quot;<u><strong>aabb</strong></u>&quot; -&gt; &quot;aabb&quot;

- &quot;a<u><strong>a</strong></u>bb&quot; -&gt; &quot;a&quot;

- &quot;a<u><strong>abb</strong></u>&quot; -&gt; &quot;abb&quot;

- &quot;aa<u><strong>b</strong></u>b&quot; -&gt; &quot;b&quot;

- &quot;aa<u><strong>bb</strong></u>&quot; -&gt; &quot;bb&quot;

- &quot;aab<u><strong>b</strong></u>&quot; -&gt; &quot;b&quot;

</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>

<strong>Input:</strong> word = &quot;he&quot;

<strong>Output:</strong> 2

<strong>Explanation:</strong> The two wonderful substrings are underlined below:

- &quot;<b><u>h</u></b>e&quot; -&gt; &quot;h&quot;

- &quot;h<strong><u>e</u></strong>&quot; -&gt; &quot;e&quot;

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>1 &lt;= word.length &lt;= 10<sup>5</sup></code></li>

    <li><code>word</code> consists of lowercase English letters from <code>&#39;a&#39;</code>&nbsp;to <code>&#39;j&#39;</code>.</li>

</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def wonderfulSubstrings(self, word: str) -> int:
        counter = Counter({0: 1})
        state = 0
        ans = 0
        for c in word:
            state ^= 1 << (ord(c) - ord('a'))
            ans += counter[state]
            for i in range(10):
                ans += counter[state ^ (1 << i)]
            counter[state] += 1
        return ans
```

### **Java**

```java
class Solution {
    public long wonderfulSubstrings(String word) {
        int[] counter = new int[1 << 10];
        counter[0] = 1;
        int state = 0;
        long ans = 0;
        for (char c : word.toCharArray()) {
            state ^= (1 << (c - 'a'));
            ans += counter[state];
            for (int i = 0; i < 10; ++i) {
                ans += counter[state ^ (1 << i)];
            }
            ++counter[state];
        }
        return ans;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} word
 * @return {number}
 */
var wonderfulSubstrings = function (word) {
    let counter = new Array(1 << 10).fill(0);
    counter[0] = 1;
    let state = 0;
    let ans = 0;
    for (let c of word) {
        state ^= 1 << (c.charCodeAt(0) - 'a'.charCodeAt(0));
        ans += counter[state];
        for (let i = 0; i < 10; ++i) {
            ans += counter[state ^ (1 << i)];
        }
        ++counter[state];
    }
    return ans;
};
```

### **C++**

```cpp
class Solution {
public:
    long long wonderfulSubstrings(string word) {
        vector<int> counter(1024);
        counter[0] = 1;
        long long ans = 0;
        int state = 0;
        for (char c : word) {
            state ^= (1 << (c - 'a'));
            ans += counter[state];
            for (int i = 0; i < 10; ++i) ans += counter[state ^ (1 << i)];
            ++counter[state];
        }
        return ans;
    }
};
```

### **Go**

```go
func wonderfulSubstrings(word string) int64 {
	counter := make([]int, 1024)
	counter[0] = 1
	state := 0
	var ans int64
	for _, c := range word {
		state ^= (1 << (c - 'a'))
		ans += int64(counter[state])
		for i := 0; i < 10; i++ {
			ans += int64(counter[state^(1<<i)])
		}
		counter[state]++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
