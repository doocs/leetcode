---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2949.Count%20Beautiful%20Substrings%20II/README.md
rating: 2444
source: 第 373 场周赛 Q4
tags:
    - 哈希表
    - 数学
    - 字符串
    - 数论
    - 前缀和
---

<!-- problem:start -->

# [2949. 统计美丽子字符串 II](https://leetcode.cn/problems/count-beautiful-substrings-ii)

[English Version](/solution/2900-2999/2949.Count%20Beautiful%20Substrings%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和一个正整数 <code>k</code> 。</p>

<p>用 <code>vowels</code> 和 <code>consonants</code> 分别表示字符串中元音字母和辅音字母的数量。</p>

<p>如果某个字符串满足以下条件，则称其为 <strong>美丽字符串</strong> ：</p>

<ul>
	<li><code>vowels == consonants</code>，即元音字母和辅音字母的数量相等。</li>
	<li><code>(vowels * consonants) % k == 0</code>，即元音字母和辅音字母的数量的乘积能被 <code>k</code> 整除。</li>
</ul>

<p>返回字符串 <code>s</code> 中 <strong>非空美丽子字符串</strong> 的数量。</p>

<p>子字符串是字符串中的一个连续字符序列。</p>

<p>英语中的<strong> 元音字母 </strong>为 <code>'a'</code>、<code>'e'</code>、<code>'i'</code>、<code>'o'</code> 和 <code>'u'</code> 。</p>

<p>英语中的<strong> 辅音字母 </strong>为除了元音字母之外的所有字母。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "baeyh", k = 2
<strong>输出：</strong>2
<strong>解释：</strong>字符串 s 中有 2 个美丽子字符串。
- 子字符串 "b<em><strong>aeyh</strong></em>"，vowels = 2（["a","e"]），consonants = 2（["y","h"]）。
可以看出字符串 "aeyh" 是美丽字符串，因为 vowels == consonants 且 vowels * consonants % k == 0 。
- 子字符串 "<em><strong>baey</strong></em>h"，vowels = 2（["a","e"]），consonants = 2（["b","y"]）。
可以看出字符串 "baey" 是美丽字符串，因为 vowels == consonants 且 vowels * consonants % k == 0 。
可以证明字符串 s 中只有 2 个美丽子字符串。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abba", k = 1
<strong>输出：</strong>3
<strong>解释：</strong>字符串 s 中有 3 个美丽子字符串。
- 子字符串 "<strong><em>ab</em></strong>ba"，vowels = 1（["a"]），consonants = 1（["b"]）。
- 子字符串 "ab<strong><em>ba</em></strong>"，vowels = 1（["a"]），consonants = 1（["b"]）。
- 子字符串 "<em><strong>abba</strong></em>"，vowels = 2（["a","a"]），consonants = 2（["b","b"]）。
可以证明字符串 s 中只有 3 个美丽子字符串。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "bcdf", k = 1
<strong>输出：</strong>0
<strong>解释：</strong>字符串 s 中没有美丽子字符串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
	<li><code>s</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：前缀和 + 哈希表 + 分解质因子

<!-- tabs:start -->

```ts
function beautifulSubstrings(s: string, k: number): number {
    const l = pSqrt(k * 4);
    const n = s.length;
    let sum = n;
    let ans = 0;
    const counter = new Map();
    counter.set(((l - 1) << 17) | sum, 1);
    for (let i = 0; i < n; i++) {
        const char = s[i];
        const bit = (AEIOU_MASK >> (char.charCodeAt(0) - 'a'.charCodeAt(0))) & 1;
        sum += bit * 2 - 1; // 1 -> 1    0 -> -1
        const key = (i % l << 17) | sum;
        ans += counter.get(key) || 0; // ans += cnt[(i%k,sum)]++
        counter.set(key, (counter.get(key) ?? 0) + 1);
    }
    return ans;
}
const AEIOU_MASK = 1065233;

function pSqrt(n: number) {
    let res = 1;
    for (let i = 2; i * i <= n; i++) {
        let i2 = i * i;
        while (n % i2 == 0) {
            res *= i;
            n /= i2;
        }
        if (n % i == 0) {
            res *= i;
            n /= i;
        }
    }
    if (n > 1) {
        res *= n;
    }
    return res;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
