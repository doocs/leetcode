# [2707. 字符串中的额外字符](https://leetcode.cn/problems/extra-characters-in-a-string)

[English Version](/solution/2700-2799/2707.Extra%20Characters%20in%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>s</code>&nbsp;和一个单词字典&nbsp;<code>dictionary</code>&nbsp;。你需要将&nbsp;<code>s</code>&nbsp;分割成若干个 <strong>互不重叠</strong>&nbsp;的子字符串，每个子字符串都在&nbsp;<code>dictionary</code>&nbsp;中出现过。<code>s</code>&nbsp;中可能会有一些&nbsp;<strong>额外的字符</strong>&nbsp;不在任何子字符串中。</p>

<p>请你采取最优策略分割 <code>s</code>&nbsp;，使剩下的字符 <strong>最少</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "leetscode", dictionary = ["leet","code","leetcode"]
<b>输出：</b>1
<b>解释：</b>将 s 分成两个子字符串：下标从 0 到 3 的 "leet" 和下标从 5 到 8 的 "code" 。只有 1 个字符没有使用（下标为 4），所以我们返回 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "sayhelloworld", dictionary = ["hello","world"]
<b>输出：</b>3
<b>解释：</b>将 s 分成两个子字符串：下标从 3 到 7 的 "hello" 和下标从 8 到 12 的 "world" 。下标为 0 ，1 和 2 的字符没有使用，所以我们返回 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 50</code></li>
	<li><code>1 &lt;= dictionary.length &lt;= 50</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 50</code></li>
	<li><code>dictionary[i]</code>&nbsp;和&nbsp;<code>s</code>&nbsp;只包含小写英文字母。</li>
	<li><code>dictionary</code>&nbsp;中的单词互不相同。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minExtraChar(self, s: str, dictionary: List[str]) -> int:
        ss = set(dictionary)
        n = len(s)
        f = [0] * (n + 1)
        for i in range(1, n + 1):
            f[i] = f[i - 1] + 1
            for j in range(i):
                if s[j:i] in ss and f[j] < f[i]:
                    f[i] = f[j]
        return f[n]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minExtraChar(String s, String[] dictionary) {
        Set<String> ss = new HashSet<>();
        for (String w : dictionary) {
            ss.add(w);
        }
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 0;
        for (int i = 1; i <= n; ++i) {
            f[i] = f[i - 1] + 1;
            for (int j = 0; j < i; ++j) {
                if (ss.contains(s.substring(j, i))) {
                    f[i] = Math.min(f[i], f[j]);
                }
            }
        }
        return f[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minExtraChar(string s, vector<string>& dictionary) {
        unordered_set<string> ss(dictionary.begin(), dictionary.end());
        int n = s.size();
        int f[n + 1];
        f[0] = 0;
        for (int i = 1; i <= n; ++i) {
            f[i] = f[i - 1] + 1;
            for (int j = 0; j < i; ++j) {
                if (ss.count(s.substr(j, i - j))) {
                    f[i] = min(f[i], f[j]);
                }
            }
        }
        return f[n];
    }
};
```

### **Rust**

```rust
use std::collections::HashSet;

impl Solution {
    #[allow(dead_code)]
    pub fn min_extra_char(s: String, dictionary: Vec<String>) -> i32 {
        let n = s.len();
        let mut set = dictionary
            .iter()
            .map(|s| s.into())
            .collect::<HashSet<String>>();
        let mut dp = vec![0; n + 1];

        // Initialize the dp vector
        dp[0] = 0;

        // Begin the actual dp process
        for i in 1..=n {
            dp[i] = dp[i - 1] + 1;
            for j in 0..i {
                if set.contains(&s[j..i]) {
                    dp[i] = std::cmp::min(dp[i], dp[j]);
                }
            }
        }

        dp[n]
    }
}
```

### **Go**

```go
func minExtraChar(s string, dictionary []string) int {
	ss := map[string]bool{}
	for _, w := range dictionary {
		ss[w] = true
	}
	n := len(s)
	f := make([]int, n+1)
	for i := 1; i <= n; i++ {
		f[i] = f[i-1] + 1
		for j := 0; j < i; j++ {
			if ss[s[j:i]] && f[j] < f[i] {
				f[i] = f[j]
			}
		}
	}
	return f[n]
}
```

### **TypeScript**

```ts
function minExtraChar(s: string, dictionary: string[]): number {
    const ss = new Set(dictionary);
    const n = s.length;
    const f = new Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        f[i] = f[i - 1] + 1;
        for (let j = 0; j < i; ++j) {
            if (ss.has(s.substring(j, i))) {
                f[i] = Math.min(f[i], f[j]);
            }
        }
    }
    return f[n];
}
```

### **...**

```

```

<!-- tabs:end -->
