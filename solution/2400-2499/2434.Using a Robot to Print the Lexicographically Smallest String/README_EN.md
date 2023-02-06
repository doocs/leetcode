# [2434. Using a Robot to Print the Lexicographically Smallest String](https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string)

[中文文档](/solution/2400-2499/2434.Using%20a%20Robot%20to%20Print%20the%20Lexicographically%20Smallest%20String/README.md)

## Description

<p>You are given a string <code>s</code> and a robot that currently holds an empty string <code>t</code>. Apply one of the following operations until <code>s</code> and <code>t</code> <strong>are both empty</strong>:</p>

<ul>
	<li>Remove the <strong>first</strong> character of a string <code>s</code> and give it to the robot. The robot will append this character to the string <code>t</code>.</li>
	<li>Remove the <strong>last</strong> character of a string <code>t</code> and give it to the robot. The robot will write this character on paper.</li>
</ul>

<p>Return <em>the lexicographically smallest string that can be written on the paper.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;zza&quot;
<strong>Output:</strong> &quot;azz&quot;
<strong>Explanation:</strong> Let p denote the written string.
Initially p=&quot;&quot;, s=&quot;zza&quot;, t=&quot;&quot;.
Perform first operation three times p=&quot;&quot;, s=&quot;&quot;, t=&quot;zza&quot;.
Perform second operation three times p=&quot;azz&quot;, s=&quot;&quot;, t=&quot;&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bac&quot;
<strong>Output:</strong> &quot;abc&quot;
<strong>Explanation:</strong> Let p denote the written string.
Perform first operation twice p=&quot;&quot;, s=&quot;c&quot;, t=&quot;ba&quot;. 
Perform second operation twice p=&quot;ab&quot;, s=&quot;c&quot;, t=&quot;&quot;. 
Perform first operation p=&quot;ab&quot;, s=&quot;&quot;, t=&quot;c&quot;. 
Perform second operation p=&quot;abc&quot;, s=&quot;&quot;, t=&quot;&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bdda&quot;
<strong>Output:</strong> &quot;addb&quot;
<strong>Explanation:</strong> Let p denote the written string.
Initially p=&quot;&quot;, s=&quot;bdda&quot;, t=&quot;&quot;.
Perform first operation four times p=&quot;&quot;, s=&quot;&quot;, t=&quot;bdda&quot;.
Perform second operation four times p=&quot;addb&quot;, s=&quot;&quot;, t=&quot;&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only English lowercase letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def robotWithString(self, s: str) -> str:
        cnt = Counter(s)
        ans = []
        stk = []
        mi = 'a'
        for c in s:
            cnt[c] -= 1
            while mi < 'z' and cnt[mi] == 0:
                mi = chr(ord(mi) + 1)
            stk.append(c)
            while stk and stk[-1] <= mi:
                ans.append(stk.pop())
        return ''.join(ans)
```

```python
class Solution:
    def robotWithString(self, s: str) -> str:
        n = len(s)
        right = [chr(ord('z') + 1)] * (n + 1)
        for i in range(n - 1, -1, -1):
            right[i] = min(s[i], right[i + 1])
        ans = []
        stk = []
        for i, c in enumerate(s):
            stk.append(c)
            while stk and stk[-1] <= right[i + 1]:
                ans.append(stk.pop())
        return ''.join(ans)
```

### **Java**

```java
class Solution {
    public String robotWithString(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        StringBuilder ans = new StringBuilder();
        Deque<Character> stk = new ArrayDeque<>();
        char mi = 'a';
        for (char c : s.toCharArray()) {
            --cnt[c - 'a'];
            while (mi < 'z' && cnt[mi - 'a'] == 0) {
                ++mi;
            }
            stk.push(c);
            while (!stk.isEmpty() && stk.peek() <= mi) {
                ans.append(stk.pop());
            }
        }
        return ans.toString();
    }
}
```

```java
class Solution {
    public String robotWithString(String s) {
        int n = s.length();
        int[] right = new int[n];
        right[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; --i) {
            right[i] = s.charAt(i) < s.charAt(right[i + 1]) ? i : right[i + 1];
        }
        StringBuilder ans = new StringBuilder();
        Deque<Character> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            stk.push(s.charAt(i));
            while (
                !stk.isEmpty() && (stk.peek() <= (i > n - 2 ? 'z' + 1 : s.charAt(right[i + 1])))) {
                ans.append(stk.pop());
            }
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string robotWithString(string s) {
        int cnt[26] = {0};
        for (char& c : s) ++cnt[c - 'a'];
        char mi = 'a';
        string stk;
        string ans;
        for (char& c : s) {
            --cnt[c - 'a'];
            while (mi < 'z' && cnt[mi - 'a'] == 0) ++mi;
            stk += c;
            while (!stk.empty() && stk.back() <= mi) {
                ans += stk.back();
                stk.pop_back();
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    string robotWithString(string s) {
        int n = s.size();
        vector<int> right(n, n - 1);
        for (int i = n - 2; i >= 0; --i) {
            right[i] = s[i] < s[right[i + 1]] ? i : right[i + 1];
        }
        string ans;
        string stk;
        for (int i = 0; i < n; ++i) {
            stk += s[i];
            while (!stk.empty() && (stk.back() <= (i > n - 2 ? 'z' + 1 : s[right[i + 1]]))) {
                ans += stk.back();
                stk.pop_back();
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func robotWithString(s string) string {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	mi := byte('a')
	stk := []byte{}
	ans := []byte{}
	for i := range s {
		cnt[s[i]-'a']--
		for mi < 'z' && cnt[mi-'a'] == 0 {
			mi++
		}
		stk = append(stk, s[i])
		for len(stk) > 0 && stk[len(stk)-1] <= mi {
			ans = append(ans, stk[len(stk)-1])
			stk = stk[:len(stk)-1]
		}
	}
	return string(ans)
}
```

### **TypeScript**

```ts
function robotWithString(s: string): string {
    let cnt = new Array(128).fill(0);
    for (let c of s) cnt[c.charCodeAt(0)] += 1;
    let min_index = 'a'.charCodeAt(0);
    let ans = [];
    let stack = [];
    for (let c of s) {
        cnt[c.charCodeAt(0)] -= 1;
        while (min_index <= 'z'.charCodeAt(0) && cnt[min_index] == 0) {
            min_index += 1;
        }
        stack.push(c);
        while (
            stack.length > 0 &&
            stack[stack.length - 1].charCodeAt(0) <= min_index
        ) {
            ans.push(stack.pop());
        }
    }
    return ans.join('');
}
```

### **...**

```

```

<!-- tabs:end -->
