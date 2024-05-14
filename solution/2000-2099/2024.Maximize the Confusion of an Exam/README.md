# [2024. 考试的最大困扰度](https://leetcode.cn/problems/maximize-the-confusion-of-an-exam)

[English Version](/solution/2000-2099/2024.Maximize%20the%20Confusion%20of%20an%20Exam/README_EN.md)

<!-- tags:字符串,二分查找,前缀和,滑动窗口 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

### 方法一：双指针

我们设计一个函数 $f(c)$，表示最多替换 $k$ 个字符 $c$ 的情况下，最长的连续字符的长度，其中 $c$ 可以是 'T' 或 'F'。答案就是 $\max(f('T'), f('F'))$。

我们使用双指针维护一个区间 $[j, i]$，使得区间内的字符 $c$ 的数量不超过 $k$。当区间内的字符 $c$ 的数量超过 $k$ 时，我们移动左指针 $j$，直到区间内的字符 $c$ 的数量不超过 $k$，然后更新答案 $ans = \max(ans, i - j + 1)$。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def maxConsecutiveAnswers(self, answerKey: str, k: int) -> int:
        def f(c: str) -> int:
            cnt = j = 0
            ans = 0
            for i, ch in enumerate(answerKey):
                cnt += ch == c
                while cnt > k:
                    cnt -= answerKey[j] == c
                    j += 1
                ans = max(ans, i - j + 1)
            return ans

        return max(f("T"), f("F"))
```

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
        int cnt = 0, ans = 0;
        for (int i = 0, j = 0; i < s.length; ++i) {
            cnt += s[i] == c ? 1 : 0;
            while (cnt > k) {
                cnt -= s[j] == c ? 1 : 0;
                ++j;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxConsecutiveAnswers(string answerKey, int k) {
        auto f = [&](char c) {
            int ans = 0, cnt = 0;
            for (int i = 0, j = 0; i < answerKey.size(); ++i) {
                cnt += answerKey[i] == c;
                while (cnt > k) {
                    cnt -= answerKey[j++] == c;
                }
                ans = max(ans, i - j + 1);
            }
            return ans;
        };
        return max(f('T'), f('F'));
    }
};
```

```go
func maxConsecutiveAnswers(answerKey string, k int) int {
	f := func(c byte) int {
		var ans, cnt, j int
		for i := range answerKey {
			if answerKey[i] == c {
				cnt++
			}
			for cnt > k {
				if answerKey[j] == c {
					cnt--
				}
				j++
			}
			ans = max(ans, i-j+1)
		}
		return ans
	}
	return max(f('T'), f('F'))
}
```

```ts
function maxConsecutiveAnswers(answerKey: string, k: number): number {
    const n = answerKey.length;
    const f = (c: string): number => {
        let [ans, cnt, j] = [0, 0, 0];
        for (let i = 0; i < n; ++i) {
            cnt += answerKey[i] === c ? 0 : 1;
            while (cnt > k) {
                cnt -= answerKey[j++] === c ? 0 : 1;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    };
    return Math.max(f('T'), f('F'));
}
```

```rust
impl Solution {
    pub fn max_consecutive_answers(answer_key: String, k: i32) -> i32 {
        let s: Vec<char> = answer_key.chars().collect();
        let f = |c: char| -> i32 {
            let mut cnt = 0;
            let mut j = 0;
            let mut ans = 0;
            for i in 0..s.len() {
                cnt += if s[i] == c { 1 } else { 0 };
                while cnt > k {
                    cnt -= if s[j] == c { 1 } else { 0 };
                    j += 1;
                }
                ans = ans.max((i - j + 1) as i32);
            }
            ans
        };
        f('T').max(f('F'))
    }
}
```

<!-- tabs:end -->

<!-- end -->
