# [2024. 考试的最大困扰度](https://leetcode.cn/problems/maximize-the-confusion-of-an-exam)

[English Version](/solution/2000-2099/2024.Maximize%20the%20Confusion%20of%20an%20Exam/README_EN.md)

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

<!-- 这里可写通用的实现逻辑 -->

思路同 [1004. 最大连续 1 的个数 III](/solution/1000-1099/1004.Max%20Consecutive%20Ones%20III/README.md)

维护一个单调变长的窗口。这种窗口经常出现在寻求“最大窗口”的问题中：因为要求的是“最大”，所以我们没有必要缩短窗口，于是代码就少了缩短窗口的部分；从另一个角度讲，本题里的 K 是资源数，一旦透支，窗口就不能再增长了。

-   l 是窗口左端点，负责移动起始位置
-   r 是窗口右端点，负责扩展窗口
-   k 是资源数，每次要替换，k 减 1，同时 r 向右移动
-   `r++` 每次都会执行，`l++` 只有资源 `k < 0` 时才触发，因此 `r - l` 的值只会单调递增（或保持不变）
-   移动左端点时，如果可以释放一个资源，k 加 1

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxConsecutiveAnswers(self, answerKey: str, k: int) -> int:
        def get(c, k):
            l = r = -1
            while r < len(answerKey) - 1:
                r += 1
                if answerKey[r] == c:
                    k -= 1
                if k < 0:
                    l += 1
                    if answerKey[l] == c:
                        k += 1
            return r - l

        return max(get('T', k), get('F', k))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(get('T', k, answerKey), get('F', k, answerKey));
    }

    public int get(char c, int k, String answerKey) {
        int l = 0, r = 0;
        while (r < answerKey.length()) {
            if (answerKey.charAt(r++) == c) {
                --k;
            }
            if (k < 0 && answerKey.charAt(l++) == c) {
                ++k;
            }
        }
        return r - l;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxConsecutiveAnswers(string answerKey, int k) {
        return max(get('T', k, answerKey), get('F', k, answerKey));
    }

    int get(char c, int k, string& answerKey) {
        int l = 0, r = 0;
        while (r < answerKey.size()) {
            if (answerKey[r++] == c) --k;
            if (k < 0 && answerKey[l++] == c) ++k;
        }
        return r - l;
    }
};
```

### **Go**

```go
func maxConsecutiveAnswers(answerKey string, k int) int {
	get := func(c byte, k int) int {
		l, r := -1, -1
		for r < len(answerKey)-1 {
			r++
			if answerKey[r] == c {
				k--
			}
			if k < 0 {
				l++
				if answerKey[l] == c {
					k++
				}
			}
		}
		return r - l
	}
	return max(get('T', k), get('F', k))
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function maxConsecutiveAnswers(answerKey: string, k: number): number {
    const n = answerKey.length;
    const getMaxCount = (target: 'T' | 'F'): number => {
        let l = 0;
        let u = k;
        for (const c of answerKey) {
            if (c !== target) {
                u--;
            }
            if (u < 0 && answerKey[l++] !== target) {
                u++;
            }
        }
        return n - l;
    };
    return Math.max(getMaxCount('T'), getMaxCount('F'));
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_consecutive_answers(answer_key: String, k: i32) -> i32 {
        let bs = answer_key.as_bytes();
        let n = bs.len();
        let get_max_count = |target| {
            let mut l = 0;
            let mut u = k;
            for b in bs.iter() {
                if b != &target {
                    u -= 1;
                }
                if u < 0 {
                    if bs[l] != target {
                        u += 1;
                    }
                    l += 1;
                }
            }
            n - l
        };
        get_max_count(b'T').max(get_max_count(b'F')) as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
