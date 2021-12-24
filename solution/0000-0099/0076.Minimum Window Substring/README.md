# [76. 最小覆盖子串](https://leetcode-cn.com/problems/minimum-window-substring)

[English Version](/solution/0000-0099/0076.Minimum%20Window%20Substring/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 、一个字符串 <code>t</code> 。返回 <code>s</code> 中涵盖 <code>t</code> 所有字符的最小子串。如果 <code>s</code> 中不存在涵盖 <code>t</code> 所有字符的子串，则返回空字符串 <code>""</code> 。</p>

<p><strong>注意：</strong>如果 <code>s</code> 中存在这样的子串，我们保证它是唯一的答案。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "ADOBECODEBANC", t = "ABC"
<strong>输出：</strong>"BANC"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a", t = "a"
<strong>输出：</strong>"a"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length, t.length <= 10<sup>5</sup></code></li>
	<li><code>s</code> 和 <code>t</code> 由英文字母组成</li>
</ul>

<p> </p>
<strong>进阶：</strong>你能设计一个在 <code>o(n)</code> 时间内解决此问题的算法吗？

## 解法

滑动窗口

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **TypeScript**

```ts
function minWindow(s: string, t: string): string {
    let n1 = s.length,
        n2 = t.length;
    if (n1 < n2) return "";
    let need = new Array(128).fill(0);
    let window = new Array(128).fill(0);
    for (let i = 0; i < n2; ++i) {
        ++need[t.charCodeAt(i)];
    }

    let left = 0,
        right = 0;
    let res = "";
    let count = 0;
    let min = n1 + 1;
    while (right < n1) {
        let cur = s.charCodeAt(right);
        ++window[cur];
        if (need[cur] > 0 && need[cur] >= window[cur]) {
            ++count;
        }
        while (count == n2) {
            cur = s.charCodeAt(left);
            if (need[cur] > 0 && need[cur] >= window[cur]) {
                --count;
            }
            if (right - left + 1 < min) {
                min = right - left + 1;
                res = s.slice(left, right + 1);
            }
            --window[cur];
            ++left;
        }
        ++right;
    }
    return res;
}
```

### **...**

```

```

<!-- tabs:end -->
