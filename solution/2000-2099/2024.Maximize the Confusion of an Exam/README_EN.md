# [2024. Maximize the Confusion of an Exam](https://leetcode.com/problems/maximize-the-confusion-of-an-exam)

[中文文档](/solution/2000-2099/2024.Maximize%20the%20Confusion%20of%20an%20Exam/README.md)

<!-- tags:String,Binary Search,Prefix Sum,Sliding Window -->

<!-- difficulty:Medium -->

## Description

<p>A teacher is writing a test with <code>n</code> true/false questions, with <code>&#39;T&#39;</code> denoting true and <code>&#39;F&#39;</code> denoting false. He wants to confuse the students by <strong>maximizing</strong> the number of <strong>consecutive</strong> questions with the <strong>same</strong> answer (multiple trues or multiple falses in a row).</p>

<p>You are given a string <code>answerKey</code>, where <code>answerKey[i]</code> is the original answer to the <code>i<sup>th</sup></code> question. In addition, you are given an integer <code>k</code>, the maximum number of times you may perform the following operation:</p>

<ul>
	<li>Change the answer key for any question to <code>&#39;T&#39;</code> or <code>&#39;F&#39;</code> (i.e., set <code>answerKey[i]</code> to <code>&#39;T&#39;</code> or <code>&#39;F&#39;</code>).</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of consecutive</em> <code>&#39;T&#39;</code>s or <code>&#39;F&#39;</code>s <em>in the answer key after performing the operation at most</em> <code>k</code> <em>times</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> answerKey = &quot;TTFF&quot;, k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can replace both the &#39;F&#39;s with &#39;T&#39;s to make answerKey = &quot;<u>TTTT</u>&quot;.
There are four consecutive &#39;T&#39;s.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> answerKey = &quot;TFFT&quot;, k = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can replace the first &#39;T&#39; with an &#39;F&#39; to make answerKey = &quot;<u>FFF</u>T&quot;.
Alternatively, we can replace the second &#39;T&#39; with an &#39;F&#39; to make answerKey = &quot;T<u>FFF</u>&quot;.
In both cases, there are three consecutive &#39;F&#39;s.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> answerKey = &quot;TTFTTFTT&quot;, k = 1
<strong>Output:</strong> 5
<strong>Explanation:</strong> We can replace the first &#39;F&#39; to make answerKey = &quot;<u>TTTTT</u>FTT&quot;
Alternatively, we can replace the second &#39;F&#39; to make answerKey = &quot;TTF<u>TTTTT</u>&quot;. 
In both cases, there are five consecutive &#39;T&#39;s.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == answerKey.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>answerKey[i]</code> is either <code>&#39;T&#39;</code> or <code>&#39;F&#39;</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

## Solutions

### Solution 1: Two Pointers

We design a function $f(c)$, which represents the longest length of consecutive characters under the condition that at most $k$ characters $c$ are replaced, where $c$ can be 'T' or 'F'. The answer is $\max(f('T'), f('F'))$.

We use two pointers to maintain a range $[j, i]$ such that the number of characters $c$ in the range does not exceed $k$. When the number of characters $c$ in the range exceeds $k$, we move the left pointer $j$ until the number of characters $c$ in the range does not exceed $k$, then update the answer $ans = \max(ans, i - j + 1)$.

The time complexity is $O(n)$, where $n$ is the length of the string. The space complexity is $O(1)$.

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
