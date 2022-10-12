# [1629. Slowest Key](https://leetcode.com/problems/slowest-key)

[中文文档](/solution/1600-1699/1629.Slowest%20Key/README.md)

## Description

<p>A newly designed keypad was tested, where a tester pressed a sequence of <code>n</code> keys, one at a time.</p>

<p>You are given a string <code>keysPressed</code> of length <code>n</code>, where <code>keysPressed[i]</code> was the <code>i<sup>th</sup></code> key pressed in the testing sequence, and a sorted list <code>releaseTimes</code>, where <code>releaseTimes[i]</code> was the time the <code>i<sup>th</sup></code> key was released. Both arrays are <strong>0-indexed</strong>. The <code>0<sup>th</sup></code> key was pressed at the time <code>0</code>,&nbsp;and every subsequent key was pressed at the <strong>exact</strong> time the previous key was released.</p>

<p>The tester wants to know the key of the keypress that had the <strong>longest duration</strong>. The <code>i<sup>th</sup></code><sup> </sup>keypress had a <strong>duration</strong> of <code>releaseTimes[i] - releaseTimes[i - 1]</code>, and the <code>0<sup>th</sup></code> keypress had a duration of <code>releaseTimes[0]</code>.</p>

<p>Note that the same key could have been pressed multiple times during the test, and these multiple presses of the same key <strong>may not</strong> have had the same <strong>duration</strong>.</p>

<p><em>Return the key of the keypress that had the <strong>longest duration</strong>. If there are multiple such keypresses, return the lexicographically largest key of the keypresses.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> releaseTimes = [9,29,49,50], keysPressed = &quot;cbcd&quot;
<strong>Output:</strong> &quot;c&quot;
<strong>Explanation:</strong> The keypresses were as follows:
Keypress for &#39;c&#39; had a duration of 9 (pressed at time 0 and released at time 9).
Keypress for &#39;b&#39; had a duration of 29 - 9 = 20 (pressed at time 9 right after the release of the previous character and released at time 29).
Keypress for &#39;c&#39; had a duration of 49 - 29 = 20 (pressed at time 29 right after the release of the previous character and released at time 49).
Keypress for &#39;d&#39; had a duration of 50 - 49 = 1 (pressed at time 49 right after the release of the previous character and released at time 50).
The longest of these was the keypress for &#39;b&#39; and the second keypress for &#39;c&#39;, both with duration 20.
&#39;c&#39; is lexicographically larger than &#39;b&#39;, so the answer is &#39;c&#39;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> releaseTimes = [12,23,36,46,62], keysPressed = &quot;spuda&quot;
<strong>Output:</strong> &quot;a&quot;
<strong>Explanation:</strong> The keypresses were as follows:
Keypress for &#39;s&#39; had a duration of 12.
Keypress for &#39;p&#39; had a duration of 23 - 12 = 11.
Keypress for &#39;u&#39; had a duration of 36 - 23 = 13.
Keypress for &#39;d&#39; had a duration of 46 - 36 = 10.
Keypress for &#39;a&#39; had a duration of 62 - 46 = 16.
The longest of these was the keypress for &#39;a&#39; with duration 16.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>releaseTimes.length == n</code></li>
	<li><code>keysPressed.length == n</code></li>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= releaseTimes[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>releaseTimes[i] &lt; releaseTimes[i+1]</code></li>
	<li><code>keysPressed</code> contains only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def slowestKey(self, releaseTimes: List[int], keysPressed: str) -> str:
        ans = keysPressed[0]
        mx = releaseTimes[0]
        for i in range(1, len(keysPressed)):
            d = releaseTimes[i] - releaseTimes[i - 1]
            if d > mx or (d == mx and ord(keysPressed[i]) > ord(ans)):
                mx = d
                ans = keysPressed[i]
        return ans
```

### **Java**

```java
class Solution {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        char ans = keysPressed.charAt(0);
        int mx = releaseTimes[0];
        for (int i = 1; i < releaseTimes.length; ++i) {
            int d = releaseTimes[i] - releaseTimes[i - 1];
            if (d > mx || (d == mx && keysPressed.charAt(i) > ans)) {
                mx = d;
                ans = keysPressed.charAt(i);
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    char slowestKey(vector<int>& releaseTimes, string keysPressed) {
        char ans = keysPressed[0];
        int mx = releaseTimes[0];
        for (int i = 1, n = releaseTimes.size(); i < n; ++i) {
            int d = releaseTimes[i] - releaseTimes[i - 1];
            if (d > mx || (d == mx && keysPressed[i] > ans)) {
                mx = d;
                ans = keysPressed[i];
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func slowestKey(releaseTimes []int, keysPressed string) byte {
	ans := keysPressed[0]
	mx := releaseTimes[0]
	for i := 1; i < len(releaseTimes); i++ {
		d := releaseTimes[i] - releaseTimes[i-1]
		if d > mx || (d == mx && keysPressed[i] > ans) {
			mx = d
			ans = keysPressed[i]
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
