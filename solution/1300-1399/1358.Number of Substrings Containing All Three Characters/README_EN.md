---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1358.Number%20of%20Substrings%20Containing%20All%20Three%20Characters/README_EN.md
rating: 1646
source: Biweekly Contest 20 Q3
tags:
    - Hash Table
    - String
    - Sliding Window
---

<!-- problem:start -->

# [1358. Number of Substrings Containing All Three Characters](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters)

[中文文档](/solution/1300-1399/1358.Number%20of%20Substrings%20Containing%20All%20Three%20Characters/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code>&nbsp;consisting only of characters <em>a</em>, <em>b</em> and <em>c</em>.</p>

<p>Return the number of substrings containing <b>at least</b>&nbsp;one occurrence of all these characters <em>a</em>, <em>b</em> and <em>c</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcabc&quot;
<strong>Output:</strong> 10
<strong>Explanation:</strong> The substrings containing&nbsp;at least&nbsp;one occurrence of the characters&nbsp;<em>a</em>,&nbsp;<em>b</em>&nbsp;and&nbsp;<em>c are &quot;</em>abc<em>&quot;, &quot;</em>abca<em>&quot;, &quot;</em>abcab<em>&quot;, &quot;</em>abcabc<em>&quot;, &quot;</em>bca<em>&quot;, &quot;</em>bcab<em>&quot;, &quot;</em>bcabc<em>&quot;, &quot;</em>cab<em>&quot;, &quot;</em>cabc<em>&quot; </em>and<em> &quot;</em>abc<em>&quot; </em>(<strong>again</strong>)<em>. </em>
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaacb&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The substrings containing&nbsp;at least&nbsp;one occurrence of the characters&nbsp;<em>a</em>,&nbsp;<em>b</em>&nbsp;and&nbsp;<em>c are &quot;</em>aaacb<em>&quot;, &quot;</em>aacb<em>&quot; </em>and<em> &quot;</em>acb<em>&quot;.</em><em> </em>
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc&quot;
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 5 x 10^4</code></li>
	<li><code>s</code>&nbsp;only consists of&nbsp;<em>a</em>, <em>b</em> or <em>c&nbsp;</em>characters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Single Pass

We use an array $d$ of length $3$ to record the most recent occurrence of the three characters, initially all set to $-1$.

We traverse the string $s$. For the current position $i$, we first update $d[s[i]]=i$, then the number of valid strings is $\min(d[0], d[1], d[2]) + 1$, which is accumulated to the answer.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfSubstrings(self, s: str) -> int:
        d = {"a": -1, "b": -1, "c": -1}
        ans = 0
        for i, c in enumerate(s):
            d[c] = i
            ans += min(d["a"], d["b"], d["c"]) + 1
        return ans
```

#### Java

```java
class Solution {
    public int numberOfSubstrings(String s) {
        int[] d = new int[] {-1, -1, -1};
        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            d[c - 'a'] = i;
            ans += Math.min(d[0], Math.min(d[1], d[2])) + 1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfSubstrings(string s) {
        int d[3] = {-1, -1, -1};
        int ans = 0;
        for (int i = 0; i < s.size(); ++i) {
            d[s[i] - 'a'] = i;
            ans += min(d[0], min(d[1], d[2])) + 1;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfSubstrings(s string) (ans int) {
	d := [3]int{-1, -1, -1}
	for i, c := range s {
		d[c-'a'] = i
		ans += min(d[0], min(d[1], d[2])) + 1
	}
	return
}
```

#### TypeScript

```ts
function numberOfSubstrings(s: string): number {
    const d: number[] = [-1, -1, -1];
    let ans = 0;

    for (let i = 0; i < s.length; i++) {
        const c = s.charCodeAt(i) - 97;
        d[c] = i;

        ans += Math.min(d[0], Math.min(d[1], d[2])) + 1;
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn number_of_substrings(s: String) -> i32 {
        let bytes = s.as_bytes();
        let mut d = [-1i32; 3];
        let mut ans: i32 = 0;

        for i in 0..bytes.len() {
            let c = (bytes[i] - b'a') as usize;
            d[c] = i as i32;

            let mn = d[0].min(d[1]).min(d[2]);
            ans += mn + 1;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Sliding Window

We can solve this using a sliding window. Maintain a window $[l, r]$ and an array $\textit{cnt}$ recording the frequency of each character in the window.

Traverse the string and keep moving the right boundary $r$ to include $s[r]$. If the window contains at least one $a$, $b$, and $c$, keep moving the left boundary $l$ to the right until the window no longer contains all three characters.

At this point, all substrings ending at $r$ that contain $a$, $b$, and $c$ can start at indices $0, 1, \ldots, l - 1$, giving $l$ valid substrings in total. Add this count to the answer.

The time complexity is $O(n)$, where $n$ is the length of the string $s$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfSubstrings(self, s: str) -> int:
        ans = l = 0
        cnt = Counter()
        for r, c in enumerate(s):
            cnt[c] += 1
            while cnt['a'] and cnt['b'] and cnt['c']:
                cnt[s[l]] -= 1
                l += 1
            ans += l
        return ans
```

#### Java

```java
class Solution {
    public int numberOfSubstrings(String s) {
        int ans = 0, l = 0;
        int[] cnt = new int[3];
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            cnt[c - 'a']++;
            while (cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0) {
                cnt[s.charAt(l) - 'a']--;
                l++;
            }
            ans += l;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfSubstrings(string s) {
        int ans = 0, l = 0;
        int cnt[3] = {0, 0, 0};
        for (int r = 0; r < (int) s.size(); r++) {
            cnt[s[r] - 'a']++;
            while (cnt[0] && cnt[1] && cnt[2]) {
                cnt[s[l] - 'a']--;
                l++;
            }
            ans += l;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfSubstrings(s string) int {
	ans, l := 0, 0
	cnt := [3]int{}

	for r := 0; r < len(s); r++ {
		cnt[s[r]-'a']++

		for cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0 {
			cnt[s[l]-'a']--
			l++
		}

		ans += l
	}

	return ans
}
```

#### TypeScript

```ts
function numberOfSubstrings(s: string): number {
    let ans = 0,
        l = 0;
    const cnt = [0, 0, 0];

    for (let r = 0; r < s.length; r++) {
        cnt[s.charCodeAt(r) - 97]++;

        while (cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0) {
            cnt[s.charCodeAt(l) - 97]--;
            l++;
        }

        ans += l;
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn number_of_substrings(s: String) -> i32 {
        let bytes = s.as_bytes();
        let mut ans = 0;
        let mut l = 0;
        let mut cnt = [0; 3];

        for r in 0..bytes.len() {
            cnt[(bytes[r] - b'a') as usize] += 1;

            while cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0 {
                cnt[(bytes[l] - b'a') as usize] -= 1;
                l += 1;
            }

            ans += l as i32;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
