# [1974. Minimum Time to Type Word Using Special Typewriter](https://leetcode.com/problems/minimum-time-to-type-word-using-special-typewriter)

[中文文档](/solution/1900-1999/1974.Minimum%20Time%20to%20Type%20Word%20Using%20Special%20Typewriter/README.md)

## Description

<p>There is a special typewriter with lowercase English letters <code>&#39;a&#39;</code> to <code>&#39;z&#39;</code> arranged in a <strong>circle</strong> with a <strong>pointer</strong>. A character can <strong>only</strong> be typed if the pointer is pointing to that character. The pointer is <strong>initially</strong> pointing to the character <code>&#39;a&#39;</code>.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1974.Minimum%20Time%20to%20Type%20Word%20Using%20Special%20Typewriter/images/chart.jpg" style="width: 530px; height: 410px;" />
<p>Each second, you may perform one of the following operations:</p>

<ul>
	<li>Move the pointer one character <strong>counterclockwise</strong> or <strong>clockwise</strong>.</li>
	<li>Type the character the pointer is <strong>currently</strong> on.</li>
</ul>

<p>Given a string <code>word</code>, return the<strong> minimum</strong> number of seconds to type out the characters in <code>word</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;abc&quot;
<strong>Output:</strong> 5
<strong>Explanation: 
</strong>The characters are printed as follows:
- Type the character &#39;a&#39; in 1 second since the pointer is initially on &#39;a&#39;.
- Move the pointer clockwise to &#39;b&#39; in 1 second.
- Type the character &#39;b&#39; in 1 second.
- Move the pointer clockwise to &#39;c&#39; in 1 second.
- Type the character &#39;c&#39; in 1 second.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;bza&quot;
<strong>Output:</strong> 7
<strong>Explanation:
</strong>The characters are printed as follows:
- Move the pointer clockwise to &#39;b&#39; in 1 second.
- Type the character &#39;b&#39; in 1 second.
- Move the pointer counterclockwise to &#39;z&#39; in 2 seconds.
- Type the character &#39;z&#39; in 1 second.
- Move the pointer clockwise to &#39;a&#39; in 1 second.
- Type the character &#39;a&#39; in 1 second.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;zjpc&quot;
<strong>Output:</strong> 34
<strong>Explanation:</strong>
The characters are printed as follows:
- Move the pointer counterclockwise to &#39;z&#39; in 1 second.
- Type the character &#39;z&#39; in 1 second.
- Move the pointer clockwise to &#39;j&#39; in 10 seconds.
- Type the character &#39;j&#39; in 1 second.
- Move the pointer clockwise to &#39;p&#39; in 6 seconds.
- Type the character &#39;p&#39; in 1 second.
- Move the pointer counterclockwise to &#39;c&#39; in 13 seconds.
- Type the character &#39;c&#39; in 1 second.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 100</code></li>
	<li><code>word</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minTimeToType(self, word: str) -> int:
        ans = prev = 0
        for c in word:
            curr = ord(c) - ord('a')
            t = abs(prev - curr)
            t = min(t, 26 - t)
            ans += t + 1
            prev = curr
        return ans
```

### **Java**

```java
class Solution {
    public int minTimeToType(String word) {
        int ans = 0;
        int prev = 0;
        for (char c : word.toCharArray()) {
            int curr = c - 'a';
            int t = Math.abs(prev - curr);
            t = Math.min(t, 26 - t);
            ans += t + 1;
            prev = curr;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minTimeToType(string word) {
        int ans = 0;
        int prev = 0;
        for (char& c : word) {
            int curr = c - 'a';
            int t = abs(prev - curr);
            t = min(t, 26 - t);
            ans += t + 1;
            prev = curr;
        }
        return ans;
    }
};
```

### **Go**

```go
func minTimeToType(word string) int {
	ans, prev := 0, 0
	for _, c := range word {
		curr := int(c - 'a')
		t := abs(prev - curr)
		t = min(t, 26-t)
		ans += t + 1
		prev = curr
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
