# [2380. Time Needed to Rearrange a Binary String](https://leetcode.com/problems/time-needed-to-rearrange-a-binary-string)

[中文文档](/solution/2300-2399/2380.Time%20Needed%20to%20Rearrange%20a%20Binary%20String/README.md)

## Description

<p>You are given a binary string <code>s</code>. In one second, <strong>all</strong> occurrences of <code>&quot;01&quot;</code> are <strong>simultaneously</strong> replaced with <code>&quot;10&quot;</code>. This process <strong>repeats</strong> until no occurrences of <code>&quot;01&quot;</code> exist.</p>

<p>Return<em> the number of seconds needed to complete this process.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;0110101&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> 
After one second, s becomes &quot;1011010&quot;.
After another second, s becomes &quot;1101100&quot;.
After the third second, s becomes &quot;1110100&quot;.
After the fourth second, s becomes &quot;1111000&quot;.
No occurrence of &quot;01&quot; exists any longer, and the process needed 4 seconds to complete,
so we return 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;11100&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong>
No occurrence of &quot;01&quot; exists in s, and the processes needed 0 seconds to complete,
so we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<p>Can you solve this problem in O(n) time complexity?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def secondsToRemoveOccurrences(self, s: str) -> int:
        ans = 0
        while s.count('01'):
            s = s.replace('01', '10')
            ans += 1
        return ans
```

```python
class Solution:
    def secondsToRemoveOccurrences(self, s: str) -> int:
        ans = cnt = 0
        for c in s:
            if c == '0':
                cnt += 1
            elif cnt:
                ans = max(ans + 1, cnt)
        return ans
```

### **Java**

```java
class Solution {
    public int secondsToRemoveOccurrences(String s) {
        char[] cs = s.toCharArray();
        boolean find = true;
        int ans = 0;
        while (find) {
            find = false;
            for (int i = 0; i < cs.length - 1; ++i) {
                if (cs[i] == '0' && cs[i + 1] == '1') {
                    char t = cs[i];
                    cs[i] = cs[i + 1];
                    cs[i + 1] = t;
                    ++i;
                    find = true;
                }
            }
            if (find) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int secondsToRemoveOccurrences(String s) {
        int ans = 0, cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                ++cnt;
            } else if (cnt > 0) {
                ans = Math.max(ans + 1, cnt);
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
    int secondsToRemoveOccurrences(string s) {
        bool find = true;
        int ans = 0;
        while (find) {
            find = false;
            for (int i = 0; i < s.size() - 1; ++i) {
                if (s[i] == '0' && s[i + 1] == '1') {
                    swap(s[i], s[i + 1]);
                    ++i;
                    find = true;
                }
            }
            if (find) {
                ++ans;
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int secondsToRemoveOccurrences(string s) {
        int ans = 0, cnt = 0;
        for (char c : s) {
            if (c == '0') {
                ++cnt;
            } else if (cnt) {
                ans = max(ans + 1, cnt);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func secondsToRemoveOccurrences(s string) int {
	cs := []byte(s)
	ans := 0
	find := true
	for find {
		find = false
		for i := 0; i < len(cs)-1; i++ {
			if cs[i] == '0' && cs[i+1] == '1' {
				cs[i], cs[i+1] = cs[i+1], cs[i]
				i++
				find = true
			}
		}
		if find {
			ans++
		}
	}
	return ans
}
```

```go
func secondsToRemoveOccurrences(s string) int {
	ans, cnt := 0, 0
	for _, c := range s {
		if c == '0' {
			cnt++
		} else if cnt > 0 {
			ans = max(ans+1, cnt)
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
