---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1358.Number%20of%20Substrings%20Containing%20All%20Three%20Characters/README.md
rating: 1646
source: 第 20 场双周赛 Q3
tags:
    - 哈希表
    - 字符串
    - 滑动窗口
---

<!-- problem:start -->

# [1358. 包含所有三种字符的子字符串数目](https://leetcode.cn/problems/number-of-substrings-containing-all-three-characters)

[English Version](/solution/1300-1399/1358.Number%20of%20Substrings%20Containing%20All%20Three%20Characters/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code>&nbsp;，它只包含三种字符 a, b 和 c 。</p>

<p>请你返回 a，b 和 c 都&nbsp;<strong>至少&nbsp;</strong>出现过一次的子字符串数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;abcabc&quot;
<strong>输出：</strong>10
<strong>解释：</strong>包含 a，b 和 c 各至少一次的子字符串为<em> &quot;</em>abc<em>&quot;, &quot;</em>abca<em>&quot;, &quot;</em>abcab<em>&quot;, &quot;</em>abcabc<em>&quot;, &quot;</em>bca<em>&quot;, &quot;</em>bcab<em>&quot;, &quot;</em>bcabc<em>&quot;, &quot;</em>cab<em>&quot;, &quot;</em>cabc<em>&quot; </em>和<em> &quot;</em>abc<em>&quot; </em>(<strong>相同</strong><strong>字符串算多次</strong>)<em>。</em>
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;aaacb&quot;
<strong>输出：</strong>3
<strong>解释：</strong>包含 a，b 和 c 各至少一次的子字符串为<em> &quot;</em>aaacb<em>&quot;, &quot;</em>aacb<em>&quot; </em>和<em> &quot;</em>acb<em>&quot; 。</em>
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;abc&quot;
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 5 x 10^4</code></li>
	<li><code>s</code>&nbsp;只包含字符 a，b 和 c 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们用一个长度为 $3$ 的数组 $d$ 记录三种字符最近一次出现的位置，初始时均为 $-1$。

遍历字符串 $s$，对于当前位置 $i$，我们先更新 $d[s[i]]=i$，然后合法的字符串个数为 $\min(d[0], d[1], d[2]) + 1$，累加到答案中。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

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

### 方法二：滑动窗口

我们可以使用滑动窗口的方法求解。维护一个窗口 $[l, r]$ 和一个数组 $\textit{cnt}$ 记录窗口内各字符的出现次数。

遍历字符串，不断右移右边界 $r$，将 $s[r]$ 加入窗口。若窗口内 $a$、$b$、$c$ 均至少出现一次，则不断右移左边界 $l$，直到窗口内不再同时包含三种字符为止。

此时，所有以 $r$ 为右端点且包含 $a$、$b$、$c$ 的子字符串的左端点可取 $0, 1, \ldots, l - 1$，共 $l$ 个，累加到答案中。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

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
