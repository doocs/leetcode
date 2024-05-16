---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2949.Count%20Beautiful%20Substrings%20II/README_EN.md
rating: 2444
source: Weekly Contest 373 Q4
tags:
    - Hash Table
    - Math
    - String
    - Number Theory
    - Prefix Sum
---

<!-- problem:start -->

# [2949. Count Beautiful Substrings II](https://leetcode.com/problems/count-beautiful-substrings-ii)

[中文文档](/solution/2900-2999/2949.Count%20Beautiful%20Substrings%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and a positive integer <code>k</code>.</p>

<p>Let <code>vowels</code> and <code>consonants</code> be the number of vowels and consonants in a string.</p>

<p>A string is <strong>beautiful</strong> if:</p>

<ul>
	<li><code>vowels == consonants</code>.</li>
	<li><code>(vowels * consonants) % k == 0</code>, in other terms the multiplication of <code>vowels</code> and <code>consonants</code> is divisible by <code>k</code>.</li>
</ul>

<p>Return <em>the number of <strong>non-empty beautiful substrings</strong> in the given string</em> <code>s</code>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters in a string.</p>

<p><strong>Vowel letters</strong> in English are <code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, and <code>&#39;u&#39;</code>.</p>

<p><strong>Consonant letters</strong> in English are every letter except vowels.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;baeyh&quot;, k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 2 beautiful substrings in the given string.
- Substring &quot;b<u>aeyh</u>&quot;, vowels = 2 ([&quot;a&quot;,e&quot;]), consonants = 2 ([&quot;y&quot;,&quot;h&quot;]).
You can see that string &quot;aeyh&quot; is beautiful as vowels == consonants and vowels * consonants % k == 0.
- Substring &quot;<u>baey</u>h&quot;, vowels = 2 ([&quot;a&quot;,e&quot;]), consonants = 2 ([&quot;b&quot;,&quot;y&quot;]).
You can see that string &quot;baey&quot; is beautiful as vowels == consonants and vowels * consonants % k == 0.
It can be shown that there are only 2 beautiful substrings in the given string.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abba&quot;, k = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 beautiful substrings in the given string.
- Substring &quot;<u>ab</u>ba&quot;, vowels = 1 ([&quot;a&quot;]), consonants = 1 ([&quot;b&quot;]).
- Substring &quot;ab<u>ba</u>&quot;, vowels = 1 ([&quot;a&quot;]), consonants = 1 ([&quot;b&quot;]).
- Substring &quot;<u>abba</u>&quot;, vowels = 2 ([&quot;a&quot;,&quot;a&quot;]), consonants = 2 ([&quot;b&quot;,&quot;b&quot;]).
It can be shown that there are only 3 beautiful substrings in the given string.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bcdf&quot;, k = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are no beautiful substrings in the given string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
	<li><code>s</code> consists of only English lowercase letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Hash Table

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
