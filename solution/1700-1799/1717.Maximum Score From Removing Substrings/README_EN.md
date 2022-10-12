# [1717. Maximum Score From Removing Substrings](https://leetcode.com/problems/maximum-score-from-removing-substrings)

[中文文档](/solution/1700-1799/1717.Maximum%20Score%20From%20Removing%20Substrings/README.md)

## Description

<p>You are given a string <code>s</code> and two integers <code>x</code> and <code>y</code>. You can perform two types of operations any number of times.</p>

<ul>
	<li>Remove substring <code>&quot;ab&quot;</code> and gain <code>x</code> points.

    <ul>
    	<li>For example, when removing <code>&quot;ab&quot;</code> from <code>&quot;c<u>ab</u>xbae&quot;</code> it becomes <code>&quot;cxbae&quot;</code>.</li>
    </ul>
    </li>
    <li>Remove substring <code>&quot;ba&quot;</code> and gain <code>y</code> points.
    <ul>
    	<li>For example, when removing <code>&quot;ba&quot;</code> from <code>&quot;cabx<u>ba</u>e&quot;</code> it becomes <code>&quot;cabxe&quot;</code>.</li>
    </ul>
    </li>

</ul>

<p>Return <em>the maximum points you can gain after applying the above operations on</em> <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cdbcbbaaabab&quot;, x = 4, y = 5
<strong>Output:</strong> 19
<strong>Explanation:</strong>
- Remove the &quot;ba&quot; underlined in &quot;cdbcbbaaa<u>ba</u>b&quot;. Now, s = &quot;cdbcbbaaab&quot; and 5 points are added to the score.
- Remove the &quot;ab&quot; underlined in &quot;cdbcbbaa<u>ab</u>&quot;. Now, s = &quot;cdbcbbaa&quot; and 4 points are added to the score.
- Remove the &quot;ba&quot; underlined in &quot;cdbcb<u>ba</u>a&quot;. Now, s = &quot;cdbcba&quot; and 5 points are added to the score.
- Remove the &quot;ba&quot; underlined in &quot;cdbc<u>ba</u>&quot;. Now, s = &quot;cdbc&quot; and 5 points are added to the score.
Total score = 5 + 4 + 5 + 5 = 19.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabbaaxybbaabb&quot;, x = 5, y = 4
<strong>Output:</strong> 20
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= x, y &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumGain(self, s: str, x: int, y: int) -> int:
        if x < y:
            return self.maximumGain(s[::-1], y, x)
        ans = 0
        stk1, stk2 = [], []
        for c in s:
            if c != 'b':
                stk1.append(c)
            else:
                if stk1 and stk1[-1] == 'a':
                    stk1.pop()
                    ans += x
                else:
                    stk1.append(c)
        while stk1:
            c = stk1.pop()
            if c != 'b':
                stk2.append(c)
            else:
                if stk2 and stk2[-1] == 'a':
                    stk2.pop()
                    ans += y
                else:
                    stk2.append(c)
        return ans
```

### **Java**

```java
class Solution {
    public int maximumGain(String s, int x, int y) {
        if (x < y) {
            return maximumGain(new StringBuilder(s).reverse().toString(), y, x);
        }
        int ans = 0;
        Deque<Character> stk1 = new ArrayDeque<>();
        Deque<Character> stk2 = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c != 'b') {
                stk1.push(c);
            } else {
                if (!stk1.isEmpty() && stk1.peek() == 'a') {
                    stk1.pop();
                    ans += x;
                } else {
                    stk1.push(c);
                }
            }
        }
        while (!stk1.isEmpty()) {
            char c = stk1.pop();
            if (c != 'b') {
                stk2.push(c);
            } else {
                if (!stk2.isEmpty() && stk2.peek() == 'a') {
                    stk2.pop();
                    ans += y;
                } else {
                    stk2.push(c);
                }
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
    int maximumGain(string s, int x, int y) {
        if (x < y) {
            reverse(s.begin(), s.end());
            return maximumGain(s, y, x);
        }
        int ans = 0;
        stack<char> stk1;
        stack<char> stk2;
        for (char c : s) {
            if (c != 'b')
                stk1.push(c);
            else {
                if (!stk1.empty() && stk1.top() == 'a') {
                    stk1.pop();
                    ans += x;
                } else
                    stk1.push(c);
            }
        }
        while (!stk1.empty()) {
            char c = stk1.top();
            stk1.pop();
            if (c != 'b')
                stk2.push(c);
            else {
                if (!stk2.empty() && stk2.top() == 'a') {
                    stk2.pop();
                    ans += y;
                } else
                    stk2.push(c);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumGain(s string, x int, y int) int {
	if x < y {
		t := []rune(s)
		for i, j := 0, len(t)-1; i < j; i, j = i+1, j-1 {
			t[i], t[j] = t[j], t[i]
		}
		return maximumGain(string(t), y, x)
	}
	ans := 0
	var stk1 []byte
	var stk2 []byte
	for i := range s {
		if s[i] != 'b' {
			stk1 = append(stk1, s[i])
		} else {
			if len(stk1) > 0 && stk1[len(stk1)-1] == 'a' {
				stk1 = stk1[0 : len(stk1)-1]
				ans += x
			} else {
				stk1 = append(stk1, s[i])
			}
		}
	}
	for _, c := range stk1 {
		if c != 'a' {
			stk2 = append(stk2, c)
		} else {
			if len(stk2) > 0 && stk2[len(stk2)-1] == 'b' {
				stk2 = stk2[0 : len(stk2)-1]
				ans += y
			} else {
				stk2 = append(stk2, c)
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
