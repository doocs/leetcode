# [389. 找不同](https://leetcode.cn/problems/find-the-difference)

[English Version](/solution/0300-0399/0389.Find%20the%20Difference/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串 <code>s</code> 和 <code>t</code>&nbsp;，它们只包含小写字母。</p>

<p>字符串 <code>t</code>&nbsp;由字符串 <code>s</code> 随机重排，然后在随机位置添加一个字母。</p>

<p>请找出在 <code>t</code>&nbsp;中被添加的字母。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abcd", t = "abcde"
<strong>输出：</strong>"e"
<strong>解释：</strong>'e' 是那个被添加的字母。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "", t = "y"
<strong>输出：</strong>"y"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 1000</code></li>
	<li><code>t.length == s.length + 1</code></li>
	<li><code>s</code> 和 <code>t</code> 只包含小写字母</li>
</ul>

## 解法

### 方法一：计数

我们可以用一个哈希表或数组 $cnt$ 统计字符串 $s$ 中每个字符出现的次数，再遍历字符串 $t$，对于每个字符，我们在 $cnt$ 中减去一次出现的次数，如果对应次数为负数，则说明该字符在 $t$ 中出现的次数大于在 $s$ 中出现的次数，因此该字符为被添加的字符。

时间复杂度 $O(n)$，空间复杂度 $O(|\Sigma|)$，其中 $n$ 为字符串的长度，而 $\Sigma$ 表示字符集，这里字符集为所有小写字母，所以 $|\Sigma|=26$。

<!-- tabs:start -->

```python
class Solution:
    def findTheDifference(self, s: str, t: str) -> str:
        cnt = Counter(s)
        for c in t:
            cnt[c] -= 1
            if cnt[c] < 0:
                return c
```

```java
class Solution {
    public char findTheDifference(String s, String t) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        for (int i = 0;; ++i) {
            if (--cnt[t.charAt(i) - 'a'] < 0) {
                return t.charAt(i);
            }
        }
    }
}
```

```cpp
class Solution {
public:
    char findTheDifference(string s, string t) {
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        for (char& c : t) {
            if (--cnt[c - 'a'] < 0) {
                return c;
            }
        }
        return ' ';
    }
};
```

```go
func findTheDifference(s, t string) byte {
	cnt := [26]int{}
	for _, ch := range s {
		cnt[ch-'a']++
	}
	for i := 0; ; i++ {
		ch := t[i]
		cnt[ch-'a']--
		if cnt[ch-'a'] < 0 {
			return ch
		}
	}
}
```

```ts
function findTheDifference(s: string, t: string): string {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    for (const c of t) {
        --cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)];
    }
    for (let i = 0; ; ++i) {
        if (cnt[i] < 0) {
            return String.fromCharCode(i + 'a'.charCodeAt(0));
        }
    }
}
```

```rust
impl Solution {
    pub fn find_the_difference(s: String, t: String) -> char {
        let s = s.as_bytes();
        let t = t.as_bytes();
        let n = s.len();
        let mut count = [0; 26];
        for i in 0..n {
            count[(s[i] - b'a') as usize] += 1;
            count[(t[i] - b'a') as usize] -= 1;
        }
        count[(t[n] - b'a') as usize] -= 1;
        char::from(
            b'a' +
                (
                    count
                        .iter()
                        .position(|&v| v != 0)
                        .unwrap() as u8
                )
        )
    }
}
```

```c
char findTheDifference(char* s, char* t) {
    int n = strlen(s);
    int cnt[26] = {0};
    for (int i = 0; i < n; i++) {
        cnt[s[i] - 'a']++;
        cnt[t[i] - 'a']--;
    }
    cnt[t[n] - 'a']--;
    for (int i = 0;; i++) {
        if (cnt[i]) {
            return 'a' + i;
        }
    }
}
```

<!-- tabs:end -->

### 方法二：求和

我们可以将字符串 $t$ 中每个字符的 ASCII 码的值求和，再减去字符串 $s$ 中每个字符的 ASCII 码的值求和，最后的结果即为被添加的字符的 ASCII 码对应的值。

时间复杂度 $O(n)$，其中 $n$ 为字符串的长度，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def findTheDifference(self, s: str, t: str) -> str:
        a = sum(ord(c) for c in s)
        b = sum(ord(c) for c in t)
        return chr(b - a)
```

```java
class Solution {
    public char findTheDifference(String s, String t) {
        int ss = 0;
        for (int i = 0; i < t.length(); ++i) {
            ss += t.charAt(i);
        }
        for (int i = 0; i < s.length(); ++i) {
            ss -= s.charAt(i);
        }
        return (char) ss;
    }
}
```

```cpp
class Solution {
public:
    char findTheDifference(string s, string t) {
        int a = 0, b = 0;
        for (char& c : s) {
            a += c;
        }
        for (char& c : t) {
            b += c;
        }
        return b - a;
    }
};
```

```go
func findTheDifference(s string, t string) byte {
	ss := 0
	for _, c := range s {
		ss -= int(c)
	}
	for _, c := range t {
		ss += int(c)
	}
	return byte(ss)
}
```

```ts
function findTheDifference(s: string, t: string): string {
    return String.fromCharCode(
        [...t].reduce((r, v) => r + v.charCodeAt(0), 0) -
            [...s].reduce((r, v) => r + v.charCodeAt(0), 0),
    );
}
```

```rust
impl Solution {
    pub fn find_the_difference(s: String, t: String) -> char {
        let mut ans = 0;
        for c in s.as_bytes() {
            ans ^= c;
        }
        for c in t.as_bytes() {
            ans ^= c;
        }
        char::from(ans)
    }
}
```

```c
char findTheDifference(char* s, char* t) {
    int n = strlen(s);
    char ans = 0;
    for (int i = 0; i < n; i++) {
        ans ^= s[i];
        ans ^= t[i];
    }
    ans ^= t[n];
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
