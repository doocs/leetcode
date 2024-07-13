---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1668.Maximum%20Repeating%20Substring/README.md
rating: 1395
source: 第 40 场双周赛 Q1
tags:
    - 字符串
    - 动态规划
    - 字符串匹配
---

<!-- problem:start -->

# [1668. 最大重复子字符串](https://leetcode.cn/problems/maximum-repeating-substring)

[English Version](/solution/1600-1699/1668.Maximum%20Repeating%20Substring/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>sequence</code> ，如果字符串 <code>word</code> 连续重复 <code>k</code> 次形成的字符串是 <code>sequence</code> 的一个子字符串，那么单词 <code>word</code> 的 <strong>重复值为 <code>k</code></strong><strong> </strong>。单词 <code>word</code> 的 <strong>最</strong><strong>大重复值</strong> 是单词 <code>word</code> 在 <code>sequence</code> 中最大的重复值。如果 <code>word</code> 不是 <code>sequence</code> 的子串，那么重复值 <code>k</code> 为 <code>0</code> 。</p>

<p>给你一个字符串 <code>sequence</code> 和 <code>word</code> ，请你返回 <strong>最大重复值 <code>k</code> </strong>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>sequence = "ababc", word = "ab"
<b>输出：</b>2
<strong>解释：</strong>"abab" 是 "<strong>abab</strong>c" 的子字符串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>sequence = "ababc", word = "ba"
<b>输出：</b>1
<strong>解释：</strong>"ba" 是 "a<strong>ba</strong>bc" 的子字符串，但 "baba" 不是 "ababc" 的子字符串。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>sequence = "ababc", word = "ac"
<b>输出：</b>0
<strong>解释：</strong>"ac" 不是 "ababc" 的子字符串。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= sequence.length <= 100</code></li>
	<li><code>1 <= word.length <= 100</code></li>
	<li><code>sequence</code> 和 <code>word</code> 都只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：直接枚举

注意到字符串长度不超过 $100$，我们直接从大到小枚举 `word` 的重复次数 $k$，判断 `word` 重复该次数后是否是 `sequence` 的子串，是则直接返回当前的重复次数 $k$。

时间复杂度为 $O(n^2)$，其中 $n$ 为 `sequence` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxRepeating(self, sequence: str, word: str) -> int:
        for k in range(len(sequence) // len(word), -1, -1):
            if word * k in sequence:
                return k
```

#### Java

```java
class Solution {
    public int maxRepeating(String sequence, String word) {
        for (int k = sequence.length() / word.length(); k > 0; --k) {
            if (sequence.contains(word.repeat(k))) {
                return k;
            }
        }
        return 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxRepeating(string sequence, string word) {
        int ans = 0;
        string t = word;
        int x = sequence.size() / word.size();
        for (int k = 1; k <= x; ++k) {
            // C++ 这里从小到大枚举重复值
            if (sequence.find(t) != string::npos) {
                ans = k;
            }
            t += word;
        }
        return ans;
    }
};
```

#### Go

```go
func maxRepeating(sequence string, word string) int {
	for k := len(sequence) / len(word); k > 0; k-- {
		if strings.Contains(sequence, strings.Repeat(word, k)) {
			return k
		}
	}
	return 0
}
```

#### TypeScript

```ts
function maxRepeating(sequence: string, word: string): number {
    let n = sequence.length;
    let m = word.length;
    for (let k = Math.floor(n / m); k > 0; k--) {
        if (sequence.includes(word.repeat(k))) {
            return k;
        }
    }
    return 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_repeating(sequence: String, word: String) -> i32 {
        let n = sequence.len();
        let m = word.len();
        if n < m {
            return 0;
        }
        let mut dp = vec![0; n - m + 1];
        for i in 0..=n - m {
            let s = &sequence[i..i + m];
            if s == word {
                dp[i] = (if (i as i32) - (m as i32) < 0 {
                    0
                } else {
                    dp[i - m]
                }) + 1;
            }
        }
        *dp.iter().max().unwrap()
    }
}
```

#### C

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))

int findWord(int i, char* sequence, char* word) {
    int n = strlen(word);
    for (int j = 0; j < n; j++) {
        if (sequence[j + i] != word[j]) {
            return 0;
        }
    }
    return 1 + findWord(i + n, sequence, word);
}

int maxRepeating(char* sequence, char* word) {
    int n = strlen(sequence);
    int m = strlen(word);
    int ans = 0;
    for (int i = 0; i <= n - m; i++) {
        ans = max(ans, findWord(i, sequence, word));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
