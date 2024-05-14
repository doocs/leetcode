# [1869. 哪种连续子字符串更长](https://leetcode.cn/problems/longer-contiguous-segments-of-ones-than-zeros)

[English Version](/solution/1800-1899/1869.Longer%20Contiguous%20Segments%20of%20Ones%20than%20Zeros/README_EN.md)

<!-- tags:字符串 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二进制字符串 <code>s</code> 。如果字符串中由 <code>1</code> 组成的 <strong>最长</strong> 连续子字符串 <strong>严格长于</strong> 由 <code>0</code> 组成的 <strong>最长</strong> 连续子字符串，返回 <code>true</code> ；否则，返回 <code>false</code><em> </em>。</p>

<ul>
	<li>例如，<code>s = "<strong>11</strong>01<strong>000</strong>10"</code> 中，由 <code>1</code> 组成的最长连续子字符串的长度是 <code>2</code> ，由 <code>0</code> 组成的最长连续子字符串的长度是 <code>3</code> 。</li>
</ul>

<p>注意，如果字符串中不存在 <code>0</code> ，此时认为由 <code>0</code> 组成的最长连续子字符串的长度是 <code>0</code> 。字符串中不存在 <code>1</code> 的情况也适用此规则。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "1101"
<strong>输出：</strong>true
<strong>解释：</strong>
由 <code>1</code> 组成的最长连续子字符串的长度是 2："<strong>11</strong>01"
由 <code>0</code> 组成的最长连续子字符串的长度是 1："11<strong>0</strong>1"
由 1 组成的子字符串更长，故返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "111000"
<strong>输出：</strong>false
<strong>解释：</strong>
由 <code>1</code> 组成的最长连续子字符串的长度是 3："<strong>111</strong>000"
由<code> 0</code> 组成的最长连续子字符串的长度是 3："111<strong>000</strong>"
由 1 组成的子字符串不比由 0 组成的子字符串长，故返回 false 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "110100010"
<strong>输出：</strong>false
<strong>解释：</strong>
由 <code>1</code> 组成的最长连续子字符串的长度是 2："<strong>11</strong>0100010"
由 <code>0</code> 组成的最长连续子字符串的长度是 3："1101<strong>000</strong>10"
由 1 组成的子字符串不比由 0 组成的子字符串长，故返回 false 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 100</code></li>
	<li><code>s[i]</code> 不是 <code>'0'</code> 就是 <code>'1'</code></li>
</ul>

## 解法

### 方法一：两次遍历

我们设计一个函数 $f(x)$，表示字符串 $s$ 中由 $x$ 组成的最长连续子字符串的长度。如果 $f(1) \gt f(0)$，那么返回 `true`，否则返回 `false`。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def checkZeroOnes(self, s: str) -> bool:
        def f(x: str) -> int:
            cnt = mx = 0
            for c in s:
                if c == x:
                    cnt += 1
                    mx = max(mx, cnt)
                else:
                    cnt = 0
            return mx

        return f("1") > f("0")
```

```java
class Solution {
    public boolean checkZeroOnes(String s) {
        return f(s, '1') > f(s, '0');
    }

    private int f(String s, char x) {
        int cnt = 0, mx = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == x) {
                mx = Math.max(mx, ++cnt);
            } else {
                cnt = 0;
            }
        }
        return mx;
    }
}
```

```cpp
class Solution {
public:
    bool checkZeroOnes(string s) {
        auto f = [&](char x) {
            int cnt = 0, mx = 0;
            for (char& c : s) {
                if (c == x) {
                    mx = max(mx, ++cnt);
                } else {
                    cnt = 0;
                }
            }
            return mx;
        };
        return f('1') > f('0');
    }
};
```

```go
func checkZeroOnes(s string) bool {
	f := func(x rune) int {
		cnt, mx := 0, 0
		for _, c := range s {
			if c == x {
				cnt++
				mx = max(mx, cnt)
			} else {
				cnt = 0
			}
		}
		return mx
	}
	return f('1') > f('0')
}
```

```ts
function checkZeroOnes(s: string): boolean {
    const f = (x: string): number => {
        let [mx, cnt] = [0, 0];
        for (const c of s) {
            if (c === x) {
                mx = Math.max(mx, ++cnt);
            } else {
                cnt = 0;
            }
        }
        return mx;
    };
    return f('1') > f('0');
}
```

```js
/**
 * @param {string} s
 * @return {boolean}
 */
var checkZeroOnes = function (s) {
    const f = x => {
        let [mx, cnt] = [0, 0];
        for (const c of s) {
            if (c === x) {
                mx = Math.max(mx, ++cnt);
            } else {
                cnt = 0;
            }
        }
        return mx;
    };
    return f('1') > f('0');
};
```

<!-- tabs:end -->

<!-- end -->
