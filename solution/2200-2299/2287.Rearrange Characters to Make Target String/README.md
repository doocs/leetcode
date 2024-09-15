---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2287.Rearrange%20Characters%20to%20Make%20Target%20String/README.md
rating: 1299
source: 第 295 场周赛 Q1
tags:
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [2287. 重排字符形成目标字符串](https://leetcode.cn/problems/rearrange-characters-to-make-target-string)

[English Version](/solution/2200-2299/2287.Rearrange%20Characters%20to%20Make%20Target%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个下标从 <strong>0</strong> 开始的字符串 <code>s</code> 和 <code>target</code> 。你可以从 <code>s</code> 取出一些字符并将其重排，得到若干新的字符串。</p>

<p>从 <code>s</code> 中取出字符并重新排列，返回可以形成 <code>target</code> 的 <strong>最大</strong> 副本数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "ilovecodingonleetcode", target = "code"
<strong>输出：</strong>2
<strong>解释：</strong>
对于 "code" 的第 1 个副本，选取下标为 4 、5 、6 和 7 的字符。
对于 "code" 的第 2 个副本，选取下标为 17 、18 、19 和 20 的字符。
形成的字符串分别是 "ecod" 和 "code" ，都可以重排为 "code" 。
可以形成最多 2 个 "code" 的副本，所以返回 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abcba", target = "abc"
<strong>输出：</strong>1
<strong>解释：</strong>
选取下标为 0 、1 和 2 的字符，可以形成 "abc" 的 1 个副本。 
可以形成最多 1 个 "abc" 的副本，所以返回 1 。
注意，尽管下标 3 和 4 分别有额外的 'a' 和 'b' ，但不能重用下标 2 处的 'c' ，所以无法形成 "abc" 的第 2 个副本。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "abbaccaddaeea", target = "aaaaa"
<strong>输出：</strong>1
<strong>解释：</strong>
选取下标为 0 、3 、6 、9 和 12 的字符，可以形成 "aaaaa" 的 1 个副本。
可以形成最多 1 个 "aaaaa" 的副本，所以返回 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>1 &lt;= target.length &lt;= 10</code></li>
	<li><code>s</code> 和 <code>target</code> 由小写英文字母组成</li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong>本题与&nbsp;<a href="https://leetcode.cn/problems/maximum-number-of-balloons/">1189. “气球” 的最大数量</a> 相同。</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们统计字符串 `s` 和 `target` 中每个字符出现的次数，记为 `cnt1` 和 `cnt2`。对于 `target` 中的每个字符，我们计算 `cnt1` 中该字符出现的次数除以 `cnt2` 中该字符出现的次数，取最小值即可。

时间复杂度 $O(n + m)$，空间复杂度 $O(C)$。其中 $n$ 和 $m$ 分别是字符串 `s` 和 `target` 的长度。而 $C$ 是字符集的大小，本题中 $C=26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rearrangeCharacters(self, s: str, target: str) -> int:
        cnt1 = Counter(s)
        cnt2 = Counter(target)
        return min(cnt1[c] // v for c, v in cnt2.items())
```

#### Java

```java
class Solution {
    public int rearrangeCharacters(String s, String target) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt1[s.charAt(i) - 'a'];
        }
        for (int i = 0; i < target.length(); ++i) {
            ++cnt2[target.charAt(i) - 'a'];
        }
        int ans = 100;
        for (int i = 0; i < 26; ++i) {
            if (cnt2[i] > 0) {
                ans = Math.min(ans, cnt1[i] / cnt2[i]);
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
    int rearrangeCharacters(string s, string target) {
        int cnt1[26]{};
        int cnt2[26]{};
        for (char& c : s) {
            ++cnt1[c - 'a'];
        }
        for (char& c : target) {
            ++cnt2[c - 'a'];
        }
        int ans = 100;
        for (int i = 0; i < 26; ++i) {
            if (cnt2[i]) {
                ans = min(ans, cnt1[i] / cnt2[i]);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func rearrangeCharacters(s string, target string) int {
	var cnt1, cnt2 [26]int
	for _, c := range s {
		cnt1[c-'a']++
	}
	for _, c := range target {
		cnt2[c-'a']++
	}
	ans := 100
	for i, v := range cnt2 {
		if v > 0 {
			ans = min(ans, cnt1[i]/v)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function rearrangeCharacters(s: string, target: string): number {
    const idx = (s: string) => s.charCodeAt(0) - 97;
    const cnt1 = new Array(26).fill(0);
    const cnt2 = new Array(26).fill(0);
    for (const c of s) {
        ++cnt1[idx(c)];
    }
    for (const c of target) {
        ++cnt2[idx(c)];
    }
    let ans = 100;
    for (let i = 0; i < 26; ++i) {
        if (cnt2[i]) {
            ans = Math.min(ans, Math.floor(cnt1[i] / cnt2[i]));
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn rearrange_characters(s: String, target: String) -> i32 {
        let mut count1 = [0; 26];
        let mut count2 = [0; 26];
        for c in s.as_bytes() {
            count1[(c - b'a') as usize] += 1;
        }
        for c in target.as_bytes() {
            count2[(c - b'a') as usize] += 1;
        }
        let mut ans = i32::MAX;
        for i in 0..26 {
            if count2[i] != 0 {
                ans = ans.min(count1[i] / count2[i]);
            }
        }
        ans
    }
}
```

#### C

```c
#define min(a, b) (((a) < (b)) ? (a) : (b))

int rearrangeCharacters(char* s, char* target) {
    int count1[26] = {0};
    int count2[26] = {0};
    for (int i = 0; s[i]; i++) {
        count1[s[i] - 'a']++;
    }
    for (int i = 0; target[i]; i++) {
        count2[target[i] - 'a']++;
    }
    int ans = INT_MAX;
    for (int i = 0; i < 26; i++) {
        if (count2[i]) {
            ans = min(ans, count1[i] / count2[i]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
