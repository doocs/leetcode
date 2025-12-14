---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2024.Maximize%20the%20Confusion%20of%20an%20Exam/README.md
rating: 1643
source: 第 62 场双周赛 Q3
tags:
    - 字符串
    - 二分查找
    - 前缀和
    - 滑动窗口
---

<!-- problem:start -->

# [2024. 考试的最大困扰度](https://leetcode.cn/problems/maximize-the-confusion-of-an-exam)

[English Version](/solution/2000-2099/2024.Maximize%20the%20Confusion%20of%20an%20Exam/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一位老师正在出一场由 <code>n</code>&nbsp;道判断题构成的考试，每道题的答案为 true （用 <code><span style="">'T'</span></code> 表示）或者 false （用 <code>'F'</code>&nbsp;表示）。老师想增加学生对自己做出答案的不确定性，方法是&nbsp;<strong>最大化&nbsp;</strong>有 <strong>连续相同</strong>&nbsp;结果的题数。（也就是连续出现 true 或者连续出现 false）。</p>

<p>给你一个字符串&nbsp;<code>answerKey</code>&nbsp;，其中&nbsp;<code>answerKey[i]</code>&nbsp;是第 <code>i</code>&nbsp;个问题的正确结果。除此以外，还给你一个整数 <code>k</code>&nbsp;，表示你能进行以下操作的最多次数：</p>

<ul>
	<li>每次操作中，将问题的正确答案改为&nbsp;<code>'T'</code> 或者&nbsp;<code>'F'</code>&nbsp;（也就是将 <code>answerKey[i]</code> 改为&nbsp;<code>'T'</code>&nbsp;或者&nbsp;<code>'F'</code>&nbsp;）。</li>
</ul>

<p>请你返回在不超过 <code>k</code>&nbsp;次操作的情况下，<strong>最大</strong>&nbsp;连续 <code>'T'</code>&nbsp;或者 <code>'F'</code>&nbsp;的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>answerKey = "TTFF", k = 2
<b>输出：</b>4
<b>解释：</b>我们可以将两个 'F' 都变为 'T' ，得到 answerKey = "<em><strong>TTTT</strong></em>" 。
总共有四个连续的 'T' 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>answerKey = "TFFT", k = 1
<b>输出：</b>3
<b>解释：</b>我们可以将最前面的 'T' 换成 'F' ，得到 answerKey = "<em><strong>FFF</strong></em>T" 。
或者，我们可以将第二个 'T' 换成 'F' ，得到 answerKey = "T<em><strong>FFF</strong></em>" 。
两种情况下，都有三个连续的 'F' 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>answerKey = "TTFTTFTT", k = 1
<b>输出：</b>5
<b>解释：</b>我们可以将第一个 'F' 换成 'T' ，得到 answerKey = "<em><strong>TTTTT</strong></em>FTT" 。
或者我们可以将第二个 'F' 换成 'T' ，得到 answerKey = "TTF<em><strong>TTTTT</strong></em>" 。
两种情况下，都有五个连续的 'T' 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == answerKey.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>answerKey[i]</code>&nbsp;要么是&nbsp;<code>'T'</code> ，要么是&nbsp;<code>'F'</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口

我们设计一个函数 $\textit{f}(c)$，表示最多替换 $k$ 个字符 $c$ 的情况下，最长的连续字符的长度，其中 $c$ 可以是 'T' 或 'F'。答案就是 $\max(\textit{f}('T'), \textit{f}('F'))$。

我们遍历字符串 $\textit{answerKey}$，用一个变量 $\textit{cnt}$ 记录当前窗口内字符 $c$ 的个数，当 $\textit{cnt} > k$ 时，我们将窗口的左指针右移一位。遍历结束后，窗口的长度即为最大连续字符的长度。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(1)$。

相似题目：

- [487. 最大连续 1 的个数 II](https://github.com/doocs/leetcode/blob/main/solution/0400-0499/0487.Max%20Consecutive%20Ones%20II/README.md)
- [1004. 最大连续 1 的个数 III](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1004.Max%20Consecutive%20Ones%20III/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxConsecutiveAnswers(self, answerKey: str, k: int) -> int:
        def f(c: str) -> int:
            cnt = l = 0
            for ch in answerKey:
                cnt += ch == c
                if cnt > k:
                    cnt -= answerKey[l] == c
                    l += 1
            return len(answerKey) - l

        return max(f("T"), f("F"))
```

#### Java

```java
class Solution {
    private char[] s;
    private int k;

    public int maxConsecutiveAnswers(String answerKey, int k) {
        s = answerKey.toCharArray();
        this.k = k;
        return Math.max(f('T'), f('F'));
    }

    private int f(char c) {
        int l = 0, cnt = 0;
        for (char ch : s) {
            cnt += ch == c ? 1 : 0;
            if (cnt > k) {
                cnt -= s[l++] == c ? 1 : 0;
            }
        }
        return s.length - l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxConsecutiveAnswers(string answerKey, int k) {
        int n = answerKey.size();
        auto f = [&](char c) {
            int l = 0, cnt = 0;
            for (char& ch : answerKey) {
                cnt += ch == c;
                if (cnt > k) {
                    cnt -= answerKey[l++] == c;
                }
            }
            return n - l;
        };
        return max(f('T'), f('F'));
    }
};
```

#### Go

```go
func maxConsecutiveAnswers(answerKey string, k int) int {
	f := func(c byte) int {
		l, cnt := 0, 0
		for _, ch := range answerKey {
			if byte(ch) == c {
				cnt++
			}
			if cnt > k {
				if answerKey[l] == c {
					cnt--
				}
				l++
			}
		}
		return len(answerKey) - l
	}
	return max(f('T'), f('F'))
}
```

#### TypeScript

```ts
function maxConsecutiveAnswers(answerKey: string, k: number): number {
    const n = answerKey.length;
    const f = (c: string): number => {
        let [l, cnt] = [0, 0];
        for (const ch of answerKey) {
            cnt += ch === c ? 1 : 0;
            if (cnt > k) {
                cnt -= answerKey[l++] === c ? 1 : 0;
            }
        }
        return n - l;
    };
    return Math.max(f('T'), f('F'));
}
```

#### Rust

```rust
impl Solution {
    pub fn max_consecutive_answers(answer_key: String, k: i32) -> i32 {
        let n = answer_key.len();
        let k = k as usize;
        let s: Vec<char> = answer_key.chars().collect();

        let f = |c: char| -> usize {
            let mut l = 0;
            let mut cnt = 0;
            for &ch in &s {
                cnt += if ch == c { 1 } else { 0 };
                if cnt > k {
                    cnt -= if s[l] == c { 1 } else { 0 };
                    l += 1;
                }
            }
            n - l
        };

        std::cmp::max(f('T'), f('F')) as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
