# [3029. 将单词恢复初始状态所需的最短时间 I](https://leetcode.cn/problems/minimum-time-to-revert-word-to-initial-state-i)

[English Version](/solution/3000-3099/3029.Minimum%20Time%20to%20Revert%20Word%20to%20Initial%20State%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的字符串 <code>word</code> 和一个整数 <code>k</code> 。</p>

<p>在每一秒，你必须执行以下操作：</p>

<ul>
	<li>移除 <code>word</code> 的前 <code>k</code> 个字符。</li>
	<li>在 <code>word</code> 的末尾添加 <code>k</code> 个任意字符。</li>
</ul>

<p><strong>注意 </strong>添加的字符不必和移除的字符相同。但是，必须在每一秒钟都执行 <strong>两种 </strong>操作。</p>

<p>返回将 <code>word</code> 恢复到其 <strong>初始 </strong>状态所需的 <strong>最短 </strong>时间（该时间必须大于零）。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>word = "abacaba", k = 3
<strong>输出：</strong>2
<strong>解释：</strong>
第 1 秒，移除 word 的前缀 "aba"，并在末尾添加 "bac" 。因此，word 变为 "cababac"。
第 2 秒，移除 word 的前缀 "cab"，并在末尾添加 "aba" 。因此，word 变为 "abacaba" 并恢复到始状态。
可以证明，2 秒是 word 恢复到其初始状态所需的最短时间。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>word = "abacaba", k = 4
<strong>输出：</strong>1
<strong>解释：
</strong>第 1 秒，移除 word 的前缀 "abac"，并在末尾添加 "caba" 。因此，word 变为 "abacaba" 并恢复到初始状态。
可以证明，1 秒是 word 恢复到其初始状态所需的最短时间。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>word = "abcbabcd", k = 2
<strong>输出：</strong>4
<strong>解释：</strong>
每一秒，我们都移除 word 的前 2 个字符，并在 word 末尾添加相同的字符。
4 秒后，word 变为 "abcbabcd" 并恢复到初始状态。
可以证明，4 秒是 word 恢复到其初始状态所需的最短时间。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 50</code></li>
	<li><code>1 &lt;= k &lt;= word.length</code></li>
	<li><code>word</code>仅由小写英文字母组成。</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def minimumTimeToInitialState(self, word: str, k: int) -> int:
        n = len(word)
        for i in range(1, 10001):
            re = i * k
            if re >= n:
                return i
            if word[re:] == word[:n - re]:
                return i
        return 0

```

```java
class Solution {
    public int minimumTimeToInitialState(String word, int k) {
        int n = word.length();
        for (int i = 1; i <= 10000; i++) {
            int re = i * k;
            if (re >= n) {
                return i;
            }
            String str = word.substring(re);
            boolean flag = true;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) != word.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return 0;
    }
}
```

```cpp
class Solution {
public:
    int minimumTimeToInitialState(string word, int k) {
        int n = word.length();
        for (int i = 1; i <= 10000; i++) {
            int re = i * k;
            if (re >= n) {
                return i;
            }
            string str = word.substr(re);
            bool flag = true;
            for (int j = 0; j < str.length(); j++) {
                if (str[j] != word[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return 0;
    }
};
```

```go
func minimumTimeToInitialState(word string, k int) int {
	n := len(word)
	for i := 1; i <= 10000; i++ {
		re := i * k
		if re >= n {
			return i
		}
		str := word[re:]
		flag := true
		for j := 0; j < len(str); j++ {
			if str[j] != word[j] {
				flag = false
				break
			}
		}
		if flag {
			return i
		}
	}
	return 0
}
```

```ts
function minimumTimeToInitialState(word: string, k: number): number {
    const n = word.length;
    for (let i = 1; i <= 10000; i++) {
        const re = i * k;
        if (re >= n) {
            return i;
        }
        const str = word.substring(re);
        let flag = true;
        for (let j = 0; j < str.length; j++) {
            if (str[j] !== word[j]) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return i;
        }
    }
    return 0;
}
```

<!-- tabs:end -->

<!-- end -->
