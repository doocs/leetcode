---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1647.Minimum%20Deletions%20to%20Make%20Character%20Frequencies%20Unique/README.md
rating: 1509
source: 第 214 场周赛 Q2
tags:
    - 贪心
    - 哈希表
    - 字符串
    - 排序
---

<!-- problem:start -->

# [1647. 字符频次唯一的最小删除次数](https://leetcode.cn/problems/minimum-deletions-to-make-character-frequencies-unique)

[English Version](/solution/1600-1699/1647.Minimum%20Deletions%20to%20Make%20Character%20Frequencies%20Unique/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果字符串 <code>s</code> 中 <strong>不存在</strong> 两个不同字符 <strong>频次</strong> 相同的情况，就称 <code>s</code> 是 <strong>优质字符串</strong> 。</p>

<p>给你一个字符串 <code>s</code>，返回使 <code>s</code> 成为 <strong>优质字符串</strong> 需要删除的 <strong>最小</strong> 字符数。</p>

<p>字符串中字符的 <strong>频次</strong> 是该字符在字符串中的出现次数。例如，在字符串 <code>"aab"</code> 中，<code>'a'</code> 的频次是 <code>2</code>，而 <code>'b'</code> 的频次是 <code>1</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aab"
<strong>输出：</strong>0
<strong>解释：</strong><code>s</code> 已经是优质字符串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aaabbbcc"
<strong>输出：</strong>2
<strong>解释：</strong>可以删除两个 'b' , 得到优质字符串 "aaabcc" 。
另一种方式是删除一个 'b' 和一个 'c' ，得到优质字符串 "aaabbc" 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "ceabaacb"
<strong>输出：</strong>2
<strong>解释：</strong>可以删除两个 'c' 得到优质字符串 "eabaab" 。
注意，只需要关注结果字符串中仍然存在的字符。（即，频次为 0 的字符会忽略不计。）
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅含小写英文字母</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数组 + 排序

我们先用一个长度为 $26$ 的数组 $\textit{cnt}$ 统计字符串 $s$ 中每个字母出现的次数。

然后我们对数组 $\textit{cnt}$ 进行倒序排序。定义一个变量 $\textit{pre}$ 记录当前字母的出现次数。

接下来，遍历数组 $\textit{cnt}$ 每个元素 $v$，如果当前 $\textit{pre}$ 等于 $0$，我们直接将答案加上 $v$；否则，如果 $v \geq \textit{pre}$，我们将答案加上 $v-\textit{pre}+1$，并且将 $\textit{pre}$ 减去 $1$，否则，我们直接将 $\textit{pre}$ 更新为 $v$。然后继续遍历下个元素。

遍历结束，返回答案即可。

时间复杂度 $O(n + |\Sigma| \times \log |\Sigma|)$，空间复杂度 $O(|\Sigma|)$。其中 $n$ 是字符串 $s$ 的长度，而 $|\Sigma|$ 为字母集的大小。本题中 $|\Sigma|=26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDeletions(self, s: str) -> int:
        cnt = Counter(s)
        ans, pre = 0, inf
        for v in sorted(cnt.values(), reverse=True):
            if pre == 0:
                ans += v
            elif v >= pre:
                ans += v - pre + 1
                pre -= 1
            else:
                pre = v
        return ans
```

#### Java

```java
class Solution {
    public int minDeletions(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        Arrays.sort(cnt);
        int ans = 0;
        for (int i = 24; i >= 0; --i) {
            while (cnt[i] >= cnt[i + 1] && cnt[i] > 0) {
                --cnt[i];
                ++ans;
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
    int minDeletions(string s) {
        vector<int> cnt(26);
        for (char& c : s) ++cnt[c - 'a'];
        sort(cnt.rbegin(), cnt.rend());
        int ans = 0;
        for (int i = 1; i < 26; ++i) {
            while (cnt[i] >= cnt[i - 1] && cnt[i] > 0) {
                --cnt[i];
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minDeletions(s string) (ans int) {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	sort.Sort(sort.Reverse(sort.IntSlice(cnt)))
	for i := 1; i < 26; i++ {
		for cnt[i] >= cnt[i-1] && cnt[i] > 0 {
			cnt[i]--
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function minDeletions(s: string): number {
    let map = {};
    for (let c of s) {
        map[c] = (map[c] || 0) + 1;
    }
    let ans = 0;
    let vals: number[] = Object.values(map);
    vals.sort((a, b) => a - b);
    for (let i = 1; i < vals.length; ++i) {
        while (vals[i] > 0 && i != vals.indexOf(vals[i])) {
            --vals[i];
            ++ans;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn min_deletions(s: String) -> i32 {
        let mut cnt = vec![0; 26];
        let mut ans = 0;

        for c in s.chars() {
            cnt[((c as u8) - ('a' as u8)) as usize] += 1;
        }

        cnt.sort_by(|&lhs, &rhs| rhs.cmp(&lhs));

        for i in 1..26 {
            while cnt[i] >= cnt[i - 1] && cnt[i] > 0 {
                cnt[i] -= 1;
                ans += 1;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDeletions(self, s: str) -> int:
        cnt = Counter(s)
        vals = sorted(cnt.values(), reverse=True)
        ans = 0
        for i in range(1, len(vals)):
            while vals[i] >= vals[i - 1] and vals[i] > 0:
                vals[i] -= 1
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int minDeletions(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        Arrays.sort(cnt);
        int ans = 0, pre = 1 << 30;
        for (int i = 25; i >= 0; --i) {
            int v = cnt[i];
            if (pre == 0) {
                ans += v;
            } else if (v >= pre) {
                ans += v - pre + 1;
                --pre;
            } else {
                pre = v;
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
    int minDeletions(string s) {
        vector<int> cnt(26);
        for (char& c : s) ++cnt[c - 'a'];
        sort(cnt.rbegin(), cnt.rend());
        int ans = 0, pre = 1 << 30;
        for (int& v : cnt) {
            if (pre == 0) {
                ans += v;
            } else if (v >= pre) {
                ans += v - pre + 1;
                --pre;
            } else {
                pre = v;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minDeletions(s string) (ans int) {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	sort.Sort(sort.Reverse(sort.IntSlice(cnt)))
	pre := 1 << 30
	for _, v := range cnt {
		if pre == 0 {
			ans += v
		} else if v >= pre {
			ans += v - pre + 1
			pre--
		} else {
			pre = v
		}
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
