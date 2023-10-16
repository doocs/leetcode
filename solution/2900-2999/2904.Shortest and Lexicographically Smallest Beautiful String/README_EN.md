# [2904. Shortest and Lexicographically Smallest Beautiful String](https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string)

[中文文档](/solution/2900-2999/2904.Shortest%20and%20Lexicographically%20Smallest%20Beautiful%20String/README.md)

## Description

<p>You are given a binary string <code>s</code> and a positive integer <code>k</code>.</p>

<p>A substring of <code>s</code> is <strong>beautiful</strong> if the number of <code>1</code>&#39;s in it is exactly <code>k</code>.</p>

<p>Let <code>len</code> be the length of the <strong>shortest</strong> beautiful substring.</p>

<p>Return <em>the lexicographically <strong>smallest</strong> beautiful substring of string </em><code>s</code><em> with length equal to </em><code>len</code>. If <code>s</code> doesn&#39;t contain a beautiful substring, return <em>an <strong>empty</strong> string</em>.</p>

<p>A string <code>a</code> is lexicographically <strong>larger</strong> than a string <code>b</code> (of the same length) if in the first position where <code>a</code> and <code>b</code> differ, <code>a</code> has a character strictly larger than the corresponding character in <code>b</code>.</p>

<ul>
	<li>For example, <code>&quot;abcd&quot;</code> is lexicographically larger than <code>&quot;abcc&quot;</code> because the first position they differ is at the fourth character, and <code>d</code> is greater than <code>c</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;100011001&quot;, k = 3
<strong>Output:</strong> &quot;11001&quot;
<strong>Explanation:</strong> There are 7 beautiful substrings in this example:
1. The substring &quot;<u>100011</u>001&quot;.
2. The substring &quot;<u>1000110</u>01&quot;.
3. The substring &quot;<u>10001100</u>1&quot;.
4. The substring &quot;1<u>00011001</u>&quot;.
5. The substring &quot;10<u>0011001</u>&quot;.
6. The substring &quot;100<u>011001</u>&quot;.
7. The substring &quot;1000<u>11001</u>&quot;.
The length of the shortest beautiful substring is 5.
The lexicographically smallest beautiful substring with length 5 is the substring &quot;11001&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1011&quot;, k = 2
<strong>Output:</strong> &quot;11&quot;
<strong>Explanation:</strong> There are 3 beautiful substrings in this example:
1. The substring &quot;<u>101</u>1&quot;.
2. The substring &quot;1<u>011</u>&quot;.
3. The substring &quot;10<u>11</u>&quot;.
The length of the shortest beautiful substring is 2.
The lexicographically smallest beautiful substring with length 2 is the substring &quot;11&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;000&quot;, k = 1
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> There are no beautiful substrings in this example.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
</ul>

## Solutions

**Solution 1: Enumeration**

We can enumerate all substrings $s[i: j]$, where $i \lt j$, and check if they are beautiful substrings. If so, we update the answer.

The time complexity is $O(n^3)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$.

**Solution 2: Two Pointers**

We can also use two pointers to maintain a sliding window, where pointer $i$ points to the left boundary of the window, and pointer $j$ points to the right boundary of the window. Initially, $i$ and $j$ both point to $0$. In addition, we use a variable $cnt$ to record the number of $1$s in the sliding window.

We first move pointer $j$ to the right, add $s[j]$ to the sliding window, and update $cnt$. If $cnt$ is greater than $k$, or if $i$ is less than $j$ and $s[i]$ is $0$, we move pointer $i$ to the right and update $cnt$.

When $cnt$ equals $k$, we have found a beautiful substring. We compare it with the current answer and update the answer if necessary.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def shortestBeautifulSubstring(self, s: str, k: int) -> str:
        n = len(s)
        ans = ""
        for i in range(n):
            for j in range(i + k, n + 1):
                t = s[i:j]
                if t.count("1") == k and (
                    not ans or j - i < len(ans) or (j - i == len(ans) and t < ans)
                ):
                    ans = t
        return ans
```

```python
class Solution:
    def shortestBeautifulSubstring(self, s: str, k: int) -> str:
        i = j = cnt = 0
        n = len(s)
        ans = ""
        while j < n:
            cnt += s[j] == "1"
            while cnt > k or (i < j and s[i] == "0"):
                cnt -= s[i] == "1"
                i += 1
            j += 1
            if cnt == k and (
                not ans or j - i < len(ans) or (j - i == len(ans) and s[i:j] < ans)
            ):
                ans = s[i:j]
        return ans
```

### **Java**

```java
class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String ans = "";
        for (int i = 0; i < n; ++i) {
            for (int j = i + k; j <= n; ++j) {
                String t = s.substring(i, j);
                int cnt = 0;
                for (char c : t.toCharArray()) {
                    cnt += c - '0';
                }
                if (cnt == k && ("".equals(ans) || j - i < ans.length() || (j - i == ans.length() && t.compareTo(ans) < 0))) {
                    ans = t;
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int i = 0, j = 0, cnt = 0;
        int n = s.length();
        String ans = "";
        while (j < n) {
            cnt += s.charAt(j) - '0';
            while (cnt > k || (i < j && s.charAt(i) == '0')) {
                cnt -= s.charAt(i) - '0';
                ++i;
            }
            ++j;
            String t = s.substring(i, j);
            if (cnt == k
                && ("".equals(ans) || j - i < ans.length()
                    || (j - i == ans.length() && t.compareTo(ans) < 0))) {
                ans = t;
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
    string shortestBeautifulSubstring(string s, int k) {
        int n = s.size();
        string ans = "";
        for (int i = 0; i < n; ++i) {
            for (int j = i + k; j <= n; ++j) {
                string t = s.substr(i, j - i);
                int cnt = count(t.begin(), t.end(), '1');
                if (cnt == k && (ans == "" || j - i < ans.size() || (j - i == ans.size() && t < ans))) {
                    ans = t;
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    string shortestBeautifulSubstring(string s, int k) {
        int i = 0, j = 0, cnt = 0;
        int n = s.size();
        string ans = "";
        while (j < n) {
            cnt += s[j] == '1';
            while (cnt > k || (i < j && s[i] == '0')) {
                cnt -= s[i++] == '1';
            }
            ++j;
            string t = s.substr(i, j - i);
            if (cnt == k && (ans == "" || j - i < ans.size() || (j - i == ans.size() && t < ans))) {
                ans = t;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func shortestBeautifulSubstring(s string, k int) (ans string) {
	n := len(s)
	for i := 0; i < n; i++ {
		for j := i + k; j <= n; j++ {
			t := s[i:j]
			cnt := 0
			for _, c := range t {
				if c == '1' {
					cnt++
				}
			}
			if cnt == k && (ans == "" || j-i < len(ans) || (j-i == len(ans) && t < ans)) {
				ans = t
			}
		}
	}
	return
}
```

```go
func shortestBeautifulSubstring(s string, k int) (ans string) {
	i, j, cnt := 0, 0, 0
	n := len(s)
	for j < n {
		cnt += int(s[j] - '0')
		for cnt > k || (i < j && s[i] == '0') {
			cnt -= int(s[i] - '0')
			i++
		}
		j++
		t := s[i:j]
		if cnt == k && (ans == "" || j-i < len(ans) || (j-i == len(ans) && t < ans)) {
			ans = t
		}
	}
	return
}
```

### **TypeScript**

```ts
function shortestBeautifulSubstring(s: string, k: number): string {
    const n = s.length;
    let ans: string = '';
    for (let i = 0; i < n; ++i) {
        for (let j = i + k; j <= n; ++j) {
            const t = s.slice(i, j);
            const cnt = t.split('').filter(c => c === '1').length;
            if (
                cnt === k &&
                (ans === '' || j - i < ans.length || (j - i === ans.length && t < ans))
            ) {
                ans = t;
            }
        }
    }
    return ans;
}
```

```ts
function shortestBeautifulSubstring(s: string, k: number): string {
    let [i, j, cnt] = [0, 0, 0];
    const n = s.length;
    let ans: string = '';
    while (j < n) {
        cnt += s[j] === '1' ? 1 : 0;
        while (cnt > k || (i < j && s[i] === '0')) {
            cnt -= s[i++] === '1' ? 1 : 0;
        }
        ++j;
        const t = s.slice(i, j);
        if (cnt === k && (ans === '' || j - i < ans.length || (j - i === ans.length && t < ans))) {
            ans = t;
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn shortest_beautiful_substring(s: String, k: i32) -> String {
        let n = s.len();
        let mut ans = String::new();

        for i in 0..n {
            for j in (i + k as usize)..=n {
                let t = &s[i..j];
                if t.matches('1').count() as i32 == k &&
                    (ans.is_empty() || j - i < ans.len() || (j - i == ans.len() && t < &ans)) {
                    ans = t.to_string();
                }
            }
        }
        ans
    }
}
```

```rust
impl Solution {
    pub fn shortest_beautiful_substring(s: String, k: i32) -> String {
        let s_chars: Vec<char> = s.chars().collect();
        let mut i = 0;
        let mut j = 0;
        let mut cnt = 0;
        let mut ans = String::new();
        let n = s.len();

        while j < n {
            if s_chars[j] == '1' {
                cnt += 1;
            }

            while cnt > k || (i < j && s_chars[i] == '0') {
                if s_chars[i] == '1' {
                    cnt -= 1;
                }
                i += 1;
            }

            j += 1;

            if cnt == k && (ans.is_empty() || j - i < ans.len() || (j - i == ans.len() && &s[i..j] < &ans)) {
                ans = s_chars[i..j].iter().collect();
            }
        }

        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
