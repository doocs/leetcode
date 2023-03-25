# [1585. Check If String Is Transformable With Substring Sort Operations](https://leetcode.com/problems/check-if-string-is-transformable-with-substring-sort-operations)

[中文文档](/solution/1500-1599/1585.Check%20If%20String%20Is%20Transformable%20With%20Substring%20Sort%20Operations/README.md)

## Description

<p>Given two strings <code>s</code> and <code>t</code>, transform string <code>s</code> into string <code>t</code> using the following operation any number of times:</p>

<ul>
	<li>Choose a <strong>non-empty</strong> substring in <code>s</code> and sort it in place so the characters are in <strong>ascending order</strong>.

    <ul>
    	<li>For example, applying the operation on the underlined substring in <code>&quot;1<u>4234</u>&quot;</code> results in <code>&quot;1<u>2344</u>&quot;</code>.</li>
    </ul>
    </li>

</ul>

<p>Return <code>true</code> if <em>it is possible to transform <code>s</code> into <code>t</code></em>. Otherwise, return <code>false</code>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;84532&quot;, t = &quot;34852&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> You can transform s into t using the following sort operations:
&quot;84<u>53</u>2&quot; (from index 2 to 3) -&gt; &quot;84<u>35</u>2&quot;
&quot;<u>843</u>52&quot; (from index 0 to 2) -&gt; &quot;<u>348</u>52&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;34521&quot;, t = &quot;23415&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> You can transform s into t using the following sort operations:
&quot;<u>3452</u>1&quot; -&gt; &quot;<u>2345</u>1&quot;
&quot;234<u>51</u>&quot; -&gt; &quot;234<u>15</u>&quot;
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;12345&quot;, t = &quot;12435&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>s.length == t.length</code></li>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> and <code>t</code> consist of only digits.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def isTransformable(self, s: str, t: str) -> bool:
        pos = defaultdict(deque)
        for i, c in enumerate(s):
            pos[int(c)].append(i)
        for c in t:
            x = int(c)
            if not pos[x] or any(pos[i] and pos[i][0] < pos[x][0] for i in range(x)):
                return False
            pos[x].popleft()
        return True
```

### **Java**

```java
class Solution {
    public boolean isTransformable(String s, String t) {
        Deque<Integer>[] pos = new Deque[10];
        Arrays.setAll(pos, k -> new ArrayDeque<>());
        for (int i = 0; i < s.length(); ++i) {
            pos[s.charAt(i) - '0'].offer(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            int x = t.charAt(i) - '0';
            if (pos[x].isEmpty()) {
                return false;
            }
            for (int j = 0; j < x; ++j) {
                if (!pos[j].isEmpty() && pos[j].peek() < pos[x].peek()) {
                    return false;
                }
            }
            pos[x].poll();
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isTransformable(string s, string t) {
        queue<int> pos[10];
        for (int i = 0; i < s.size(); ++i) {
            pos[s[i] - '0'].push(i);
        }
        for (char& c : t) {
            int x = c - '0';
            if (pos[x].empty()) {
                return false;
            }
            for (int j = 0; j < x; ++j) {
                if (!pos[j].empty() && pos[j].front() < pos[x].front()) {
                    return false;
                }
            }
            pos[x].pop();
        }
        return true;
    }
};
```

### **Go**

```go
func isTransformable(s string, t string) bool {
	pos := [10][]int{}
	for i, c := range s {
		pos[c-'0'] = append(pos[c-'0'], i)
	}
	for _, c := range t {
		x := int(c - '0')
		if len(pos[x]) == 0 {
			return false
		}
		for j := 0; j < x; j++ {
			if len(pos[j]) > 0 && pos[j][0] < pos[x][0] {
				return false
			}
		}
		pos[x] = pos[x][1:]
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
