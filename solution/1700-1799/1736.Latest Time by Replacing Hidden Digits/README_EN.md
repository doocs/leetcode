# [1736. Latest Time by Replacing Hidden Digits](https://leetcode.com/problems/latest-time-by-replacing-hidden-digits)

[中文文档](/solution/1700-1799/1736.Latest%20Time%20by%20Replacing%20Hidden%20Digits/README.md)

## Description

<p>You are given a string <code>time</code> in the form of <code> hh:mm</code>, where some of the digits in the string are hidden (represented by <code>?</code>).</p>

<p>The valid times are those inclusively between <code>00:00</code> and <code>23:59</code>.</p>

<p>Return <em>the latest valid time you can get from</em> <code>time</code><em> by replacing the hidden</em> <em>digits</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> time = &quot;2?:?0&quot;
<strong>Output:</strong> &quot;23:50&quot;
<strong>Explanation:</strong> The latest hour beginning with the digit &#39;2&#39; is 23 and the latest minute ending with the digit &#39;0&#39; is 50.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> time = &quot;0?:3?&quot;
<strong>Output:</strong> &quot;09:39&quot;
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> time = &quot;1?:22&quot;
<strong>Output:</strong> &quot;19:22&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>time</code> is in the format <code>hh:mm</code>.</li>
	<li>It is guaranteed that you can produce a valid time from the given string.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumTime(self, time: str) -> str:
        t = list(time)
        if t[0] == '?':
            t[0] = '1' if '4' <= t[1] <= '9' else '2'
        if t[1] == '?':
            t[1] = '3' if t[0] == '2' else '9'
        if t[3] == '?':
            t[3] = '5'
        if t[4] == '?':
            t[4] = '9'
        return ''.join(t)
```

### **Java**

```java
class Solution {
    public String maximumTime(String time) {
        char[] t = time.toCharArray();
        if (t[0] == '?') {
            t[0] = t[1] >= '4' && t[1] <= '9' ? '1' : '2';
        }
        if (t[1] == '?') {
            t[1] = t[0] == '2' ? '3' : '9';
        }
        if (t[3] == '?') {
            t[3] = '5';
        }
        if (t[4] == '?') {
            t[4] = '9';
        }
        return new String(t);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string maximumTime(string time) {
        if (time[0] == '?') {
            time[0] = (time[1] >= '4' && time[1] <= '9') ? '1' : '2';
        }
        if (time[1] == '?') {
            time[1] = (time[0] == '2') ? '3' : '9';
        }
        if (time[3] == '?') {
            time[3] = '5';
        }
        if (time[4] == '?') {
            time[4] = '9';
        }
        return time;
    }
};
```

### **Go**

```go
func maximumTime(time string) string {
	t := []byte(time)
	if t[0] == '?' {
		if t[1] >= '4' && t[1] <= '9' {
			t[0] = '1'
		} else {
			t[0] = '2'
		}
	}
	if t[1] == '?' {
		if t[0] == '2' {
			t[1] = '3'
		} else {
			t[1] = '9'
		}
	}
	if t[3] == '?' {
		t[3] = '5'
	}
	if t[4] == '?' {
		t[4] = '9'
	}
	return string(t)
}
```

### **JavaScript**

```js
/**
 * @param {string} time
 * @return {string}
 */
var maximumTime = function (time) {
    const t = Array.from(time);
    if (t[0] === '?') {
        t[0] = t[1] >= '4' && t[1] <= '9' ? '1' : '2';
    }
    if (t[1] === '?') {
        t[1] = t[0] == '2' ? '3' : '9';
    }
    if (t[3] === '?') {
        t[3] = '5';
    }
    if (t[4] === '?') {
        t[4] = '9';
    }
    return t.join('');
};
```

### **...**

```

```

<!-- tabs:end -->
