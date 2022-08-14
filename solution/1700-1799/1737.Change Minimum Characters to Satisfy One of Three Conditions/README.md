# [1737. 满足三条件之一需改变的最少字符数](https://leetcode.cn/problems/change-minimum-characters-to-satisfy-one-of-three-conditions)

[English Version](/solution/1700-1799/1737.Change%20Minimum%20Characters%20to%20Satisfy%20One%20of%20Three%20Conditions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串 <code>a</code> 和 <code>b</code> ，二者均由小写字母组成。一步操作中，你可以将 <code>a</code> 或 <code>b</code> 中的 <strong>任一字符</strong> 改变为 <strong>任一小写字母</strong> 。</p>

<p>操作的最终目标是满足下列三个条件 <strong>之一</strong> ：</p>

<ul>
	<li><code>a</code> 中的 <strong>每个字母</strong> 在字母表中 <strong>严格小于</strong> <code>b</code> 中的 <strong>每个字母</strong> 。</li>
	<li><code>b</code> 中的 <strong>每个字母</strong> 在字母表中 <strong>严格小于</strong> <code>a</code> 中的 <strong>每个字母</strong> 。</li>
	<li><code>a</code> 和 <code>b</code> <strong>都</strong> 由 <strong>同一个</strong> 字母组成。</li>
</ul>

<p>返回达成目标所需的 <strong>最少</strong> 操作数<em>。</em></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>a = "aba", b = "caa"
<strong>输出：</strong>2
<strong>解释：</strong>满足每个条件的最佳方案分别是：
1) 将 b 变为 "ccc"，2 次操作，满足 a 中的每个字母都小于 b 中的每个字母；
2) 将 a 变为 "bbb" 并将 b 变为 "aaa"，3 次操作，满足 b 中的每个字母都小于 a 中的每个字母；
3) 将 a 变为 "aaa" 并将 b 变为 "aaa"，2 次操作，满足 a 和 b 由同一个字母组成。
最佳的方案只需要 2 次操作（满足条件 1 或者条件 3）。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>a = "dabadd", b = "cda"
<strong>输出：</strong>3
<strong>解释：</strong>满足条件 1 的最佳方案是将 b 变为 "eee" 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 10<sup>5</sup></code></li>
	<li><code>a</code> 和 <code>b</code> 只由小写字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

前缀和

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
function minCharacters(a: string, b: string): number {
    const m = a.length,
        n = b.length;
    let count1 = new Array(26).fill(0);
    let count2 = new Array(26).fill(0);
    const base = 'a'.charCodeAt(0);

    for (let char of a) {
        count1[char.charCodeAt(0) - base]++;
    }
    for (let char of b) {
        count2[char.charCodeAt(0) - base]++;
    }

    let pre1 = 0,
        pre2 = 0;
    let ans = m + n;
    for (let i = 0; i < 25; i++) {
        pre1 += count1[i];
        pre2 += count2[i];
        // case1， case2， case3
        ans = Math.min(
            ans,
            m - pre1 + pre2,
            pre1 + n - pre2,
            m + n - count1[i] - count2[i],
        );
    }
    ans = Math.min(ans, m + n - count1[25] - count2[25]);

    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
