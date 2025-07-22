---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2904.Shortest%20and%20Lexicographically%20Smallest%20Beautiful%20String/README.md
rating: 1483
source: 第 367 场周赛 Q2
tags:
    - 字符串
    - 滑动窗口
---

<!-- problem:start -->

# [2904. 最短且字典序最小的美丽子字符串](https://leetcode.cn/problems/shortest-and-lexicographically-smallest-beautiful-string)

[English Version](/solution/2900-2999/2904.Shortest%20and%20Lexicographically%20Smallest%20Beautiful%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二进制字符串 <code>s</code> 和一个正整数 <code>k</code> 。</p>

<p>如果 <code>s</code> 的某个子字符串中 <code>1</code> 的个数恰好等于 <code>k</code> ，则称这个子字符串是一个 <strong>美丽子字符串</strong> 。</p>

<p>令 <code>len</code> 等于 <strong>最短</strong> 美丽子字符串的长度。</p>

<p>返回长度等于 <code>len</code> 且字典序 <strong>最小</strong> 的美丽子字符串。如果 <code>s</code> 中不含美丽子字符串，则返回一个 <strong>空</strong> 字符串。</p>

<p>对于相同长度的两个字符串 <code>a</code> 和 <code>b</code> ，如果在 <code>a</code> 和 <code>b</code> 出现不同的第一个位置上，<code>a</code> 中该位置上的字符严格大于 <code>b</code> 中的对应字符，则认为字符串 <code>a</code> 字典序 <strong>大于</strong> 字符串 <code>b</code> 。</p>

<ul>
	<li>例如，<code>"abcd"</code> 的字典序大于 <code>"abcc"</code> ，因为两个字符串出现不同的第一个位置对应第四个字符，而 <code>d</code> 大于 <code>c</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "100011001", k = 3
<strong>输出：</strong>"11001"
<strong>解释：</strong>示例中共有 7 个美丽子字符串：
1. 子字符串 "<u>100011</u>001" 。
2. 子字符串 "<u>1000110</u>01" 。
3. 子字符串 "<u>10001100</u>1" 。
4. 子字符串 "1<u>00011001</u>" 。
5. 子字符串 "10<u>0011001</u>" 。
6. 子字符串 "100<u>011001</u>" 。
7. 子字符串 "1000<u>11001</u>" 。
最短美丽子字符串的长度是 5 。
长度为 5 且字典序最小的美丽子字符串是子字符串 "11001" 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "1011", k = 2
<strong>输出：</strong>"11"
<strong>解释：</strong>示例中共有 3 个美丽子字符串：
1. 子字符串 "<u>101</u>1" 。
2. 子字符串 "1<u>011</u>" 。
3. 子字符串 "10<u>11</u>" 。
最短美丽子字符串的长度是 2 。
长度为 2 且字典序最小的美丽子字符串是子字符串 "11" 。 
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "000", k = 1
<strong>输出：</strong>""
<strong>解释：</strong>示例中不存在美丽子字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以枚举所有子字符串 $s[i: j]$，其中 $i \lt j$，并检查它们是否是美丽子字符串。如果是，我们就更新答案。

时间复杂度 $O(n^3)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

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

#### Java

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
                if (cnt == k
                    && ("".equals(ans) || j - i < ans.length()
                        || (j - i == ans.length() && t.compareTo(ans) < 0))) {
                    ans = t;
                }
            }
        }
        return ans;
    }
}
```

#### C++

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

#### Go

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

#### TypeScript

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

#### Rust

```rust
impl Solution {
    pub fn shortest_beautiful_substring(s: String, k: i32) -> String {
        let n = s.len();
        let mut ans = String::new();

        for i in 0..n {
            for j in i + (k as usize)..=n {
                let t = &s[i..j];
                if (t.matches('1').count() as i32) == k
                    && (ans.is_empty() || j - i < ans.len() || (j - i == ans.len() && t < &ans))
                {
                    ans = t.to_string();
                }
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：双指针

我们也可以用两个指针维护一个滑动窗口，其中指针 $i$ 指向滑动窗口的左端点，指针 $j$ 指向滑动窗口的右端点。初始时 $i = j = 0$。另外，我们用变量 $cnt$ 记录滑动窗口中的 $1$ 的个数。

我们首先将指针 $j$ 向右移动，将 $s[j]$ 加入到滑动窗口中，并更新 $cnt$。如果 $cnt \gt k$，或者 $i \lt j$ 并且 $s[i]=0$，我们就循环将指针 $i$ 往右移动，并且更新 $cnt$。

当 $cnt = k$ 时，我们就找到了一个美丽子字符串。我们将它与当前的答案进行比较，并更新答案。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

#### Rust

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

            if cnt == k
                && (ans.is_empty() || j - i < ans.len() || (j - i == ans.len() && &s[i..j] < &ans))
            {
                ans = s_chars[i..j].iter().collect();
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
