# [1888. 使二进制字符串字符交替的最少反转次数](https://leetcode.cn/problems/minimum-number-of-flips-to-make-the-binary-string-alternating)

[English Version](/solution/1800-1899/1888.Minimum%20Number%20of%20Flips%20to%20Make%20the%20Binary%20String%20Alternating/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二进制字符串 <code>s</code> 。你可以按任意顺序执行以下两种操作任意次：</p>

<ul>
	<li><strong>类型 1 ：删除</strong> 字符串 <code>s</code> 的第一个字符并将它 <strong>添加</strong> 到字符串结尾。</li>
	<li><strong>类型 2 ：选择 </strong>字符串 <code>s</code> 中任意一个字符并将该字符 <strong>反转 </strong>，也就是如果值为 <code>'0'</code> ，则反转得到 <code>'1'</code> ，反之亦然。</li>
</ul>

<p>请你返回使 <code>s</code> 变成 <strong>交替</strong> 字符串的前提下， <strong>类型 2 </strong>的 <strong>最少</strong> 操作次数 。</p>

<p>我们称一个字符串是 <strong>交替</strong> 的，需要满足任意相邻字符都不同。</p>

<ul>
	<li>比方说，字符串 <code>"010"</code> 和 <code>"1010"</code> 都是交替的，但是字符串 <code>"0100"</code> 不是。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "111000"
<b>输出：</b>2
<b>解释：</b>执行第一种操作两次，得到 s = "100011" 。
然后对第三个和第六个字符执行第二种操作，得到 s = "10<strong>1</strong>01<strong>0</strong>" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "010"
<b>输出：</b>0
<strong>解释：</strong>字符串已经是交替的。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>s = "1110"
<b>输出：</b>1
<b>解释：</b>对第二个字符执行第二种操作，得到 s = "1<strong>0</strong>10" 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 要么是 <code>'0'</code> ，要么是 <code>'1'</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“滑动窗口”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minFlips(self, s: str) -> int:
        n = len(s)
        target = '01'
        cnt = 0
        for i, c in enumerate(s):
            cnt += c != target[i & 1]
        res = min(cnt, n - cnt)
        for i in range(n):
            cnt -= s[i] != target[i & 1]
            cnt += s[i] != target[(i + n) & 1]
            res = min(res, cnt, n - cnt)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String target = "01";
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            cnt += (s.charAt(i) == target.charAt(i & 1) ? 0 : 1);
        }
        int res = Math.min(cnt, n - cnt);
        for (int i = 0; i < n; ++i) {
            cnt -= (s.charAt(i) == target.charAt(i & 1) ? 0 : 1);
            cnt += (s.charAt(i) == target.charAt((i + n) & 1) ? 0 : 1);
            res = Math.min(res, Math.min(cnt, n - cnt));
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function minFlips(s: string): number {
    const n: number = s.length;
    const target: string[] = ['0', '1'];
    let count: number = 0;
    for (let i: number = 0; i < n; ++i) {
        count += s.charAt(i) == target[i & 1] ? 0 : 1;
    }
    let res = Math.min(count, n - count);
    for (let i: number = 0; i < n; ++i) {
        count -= s.charAt(i) == target[i & 1] ? 0 : 1;
        count += s.charAt(i) == target[(i + n) & 1] ? 0 : 1;
        res = Math.min(res, count, n - count);
    }
    return res;
}
```

### **...**

```

```

<!-- tabs:end -->
