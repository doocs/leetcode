# [3029. Minimum Time to Revert Word to Initial State I](https://leetcode.com/problems/minimum-time-to-revert-word-to-initial-state-i)

[中文文档](/solution/3000-3099/3029.Minimum%20Time%20to%20Revert%20Word%20to%20Initial%20State%20I/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>word</code> and an integer <code>k</code>.</p>

<p>At every second, you must perform the following operations:</p>

<ul>
	<li>Remove the first <code>k</code> characters of <code>word</code>.</li>
	<li>Add any <code>k</code> characters to the end of <code>word</code>.</li>
</ul>

<p><strong>Note</strong> that you do not necessarily need to add the same characters that you removed. However, you must perform <strong>both</strong> operations at every second.</p>

<p>Return <em>the <strong>minimum</strong> time greater than zero required for</em> <code>word</code> <em>to revert to its <strong>initial</strong> state</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;abacaba&quot;, k = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> At the 1st second, we remove characters &quot;aba&quot; from the prefix of word, and add characters &quot;bac&quot; to the end of word. Thus, word becomes equal to &quot;cababac&quot;.
At the 2nd second, we remove characters &quot;cab&quot; from the prefix of word, and add &quot;aba&quot; to the end of word. Thus, word becomes equal to &quot;abacaba&quot; and reverts to its initial state.
It can be shown that 2 seconds is the minimum time greater than zero required for word to revert to its initial state.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;abacaba&quot;, k = 4
<strong>Output:</strong> 1
<strong>Explanation:</strong> At the 1st second, we remove characters &quot;abac&quot; from the prefix of word, and add characters &quot;caba&quot; to the end of word. Thus, word becomes equal to &quot;abacaba&quot; and reverts to its initial state.
It can be shown that 1 second is the minimum time greater than zero required for word to revert to its initial state.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;abcbabcd&quot;, k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> At every second, we will remove the first 2 characters of word, and add the same characters to the end of word.
After 4 seconds, word becomes equal to &quot;abcbabcd&quot; and reverts to its initial state.
It can be shown that 4 seconds is the minimum time greater than zero required for word to revert to its initial state.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 50 </code></li>
	<li><code>1 &lt;= k &lt;= word.length</code></li>
	<li><code>word</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

### Solution 1

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
