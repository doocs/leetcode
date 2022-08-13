# [2024. Maximize the Confusion of an Exam](https://leetcode.com/problems/maximize-the-confusion-of-an-exam)

[中文文档](/solution/2000-2099/2024.Maximize%20the%20Confusion%20of%20an%20Exam/README.md)

## Description

<p>A teacher is writing a test with <code>n</code> true/false questions, with <code>&#39;T&#39;</code> denoting true and <code>&#39;F&#39;</code> denoting false. He wants to confuse the students by <strong>maximizing</strong> the number of <strong>consecutive</strong> questions with the <strong>same</strong> answer (multiple trues or multiple falses in a row).</p>

<p>You are given a string <code>answerKey</code>, where <code>answerKey[i]</code> is the original answer to the <code>i<sup>th</sup></code> question. In addition, you are given an integer <code>k</code>, the maximum number of times you may perform the following operation:</p>

<ul>
	<li>Change the answer key for any question to <code>&#39;T&#39;</code> or <code>&#39;F&#39;</code> (i.e., set <code>answerKey[i]</code> to <code>&#39;T&#39;</code> or <code>&#39;F&#39;</code>).</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of consecutive</em> <code>&#39;T&#39;</code>s or <code>&#39;F&#39;</code>s <em>in the answer key after performing the operation at most</em> <code>k</code> <em>times</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> answerKey = &quot;TTFF&quot;, k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can replace both the &#39;F&#39;s with &#39;T&#39;s to make answerKey = &quot;<u>TTTT</u>&quot;.
There are four consecutive &#39;T&#39;s.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> answerKey = &quot;TFFT&quot;, k = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can replace the first &#39;T&#39; with an &#39;F&#39; to make answerKey = &quot;<u>FFF</u>T&quot;.
Alternatively, we can replace the second &#39;T&#39; with an &#39;F&#39; to make answerKey = &quot;T<u>FFF</u>&quot;.
In both cases, there are three consecutive &#39;F&#39;s.
</pre>

<p><strong>Example 3:</strong></p>

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

<!-- tabs:start -->

### **Python3**

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
