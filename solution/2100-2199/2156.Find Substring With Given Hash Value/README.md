---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2156.Find%20Substring%20With%20Given%20Hash%20Value/README.md
rating: 2062
tags:
    - 字符串
    - 滑动窗口
    - 哈希函数
    - 滚动哈希
---

# [2156. 查找给定哈希值的子串](https://leetcode.cn/problems/find-substring-with-given-hash-value)

[English Version](/solution/2100-2199/2156.Find%20Substring%20With%20Given%20Hash%20Value/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定整数 <code>p</code>&nbsp;和 <code>m</code>&nbsp;，一个长度为 <code>k</code>&nbsp;且下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>s</code>&nbsp;的哈希值按照如下函数计算：</p>

<ul>
	<li><code>hash(s, p, m) = (val(s[0]) * p<sup>0</sup> + val(s[1]) * p<sup>1</sup> + ... + val(s[k-1]) * p<sup>k-1</sup>) mod m</code>.</li>
</ul>

<p>其中&nbsp;<code>val(s[i])</code>&nbsp;表示&nbsp;<code>s[i]</code>&nbsp;在字母表中的下标，从&nbsp;<code>val('a') = 1</code> 到&nbsp;<code>val('z') = 26</code>&nbsp;。</p>

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;和整数&nbsp;<code>power</code>，<code>modulo</code>，<code>k</code>&nbsp;和&nbsp;<code>hashValue</code>&nbsp;。请你返回 <code>s</code>&nbsp;中 <strong>第一个</strong> 长度为 <code>k</code>&nbsp;的 <strong>子串</strong>&nbsp;<code>sub</code>&nbsp;，满足<em>&nbsp;</em><code>hash(sub, power, modulo) == hashValue</code>&nbsp;。</p>

<p>测试数据保证一定 <strong>存在</strong>&nbsp;至少一个这样的子串。</p>

<p><strong>子串</strong> 定义为一个字符串中连续非空字符组成的序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "leetcode", power = 7, modulo = 20, k = 2, hashValue = 0
<strong>输出：</strong>"ee"
<strong>解释：</strong>"ee" 的哈希值为 hash("ee", 7, 20) = (5 * 1 + 5 * 7) mod 20 = 40 mod 20 = 0 。
"ee" 是长度为 2 的第一个哈希值为 0 的子串，所以我们返回 "ee" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "fbxzaad", power = 31, modulo = 100, k = 3, hashValue = 32
<b>输出：</b>"fbx"
<b>解释：</b>"fbx" 的哈希值为 hash("fbx", 31, 100) = (6 * 1 + 2 * 31 + 24 * 31<sup>2</sup>) mod 100 = 23132 mod 100 = 32 。
"bxz" 的哈希值为 hash("bxz", 31, 100) = (2 * 1 + 24 * 31 + 26 * 31<sup>2</sup>) mod 100 = 25732 mod 100 = 32 。
"fbx" 是长度为 3 的第一个哈希值为 32 的子串，所以我们返回 "fbx" 。
注意，"bxz" 的哈希值也为 32 ，但是它在字符串中比 "fbx" 更晚出现。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= power, modulo &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= hashValue &lt; modulo</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
	<li>测试数据保证一定 <strong>存在</strong>&nbsp;满足条件的子串。</li>
</ul>

## 解法

### 方法一：滑动窗口 + 倒序遍历

我们可以维护一个长度为 $k$ 的滑动窗口，用来计算子串的哈希值。考虑到如果正序遍历字符串，在哈希值的计算中，涉及到除法取模的操作，处理起来比较麻烦。因此我们可以倒序遍历字符串，这样在计算哈希值的时候，只需要乘法和取模操作。

我们首先计算字符串末尾的 $k$ 个字符的哈希值，然后从字符串末尾开始倒序遍历，每次计算当前窗口的哈希值，如果等于给定的哈希值，我们就找到了一个满足条件的子串，更新答案字符串的起始位置。

最后返回答案字符串即可。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def subStrHash(
        self, s: str, power: int, modulo: int, k: int, hashValue: int
    ) -> str:
        h, n = 0, len(s)
        p = 1
        for i in range(n - 1, n - 1 - k, -1):
            val = ord(s[i]) - ord("a") + 1
            h = ((h * power) + val) % modulo
            if i != n - k:
                p = p * power % modulo
        j = n - k
        for i in range(n - 1 - k, -1, -1):
            pre = ord(s[i + k]) - ord("a") + 1
            cur = ord(s[i]) - ord("a") + 1
            h = ((h - pre * p) * power + cur) % modulo
            if h == hashValue:
                j = i
        return s[j : j + k]
```

```java
class Solution {
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        long h = 0, p = 1;
        int n = s.length();
        for (int i = n - 1; i >= n - k; --i) {
            int val = s.charAt(i) - 'a' + 1;
            h = ((h * power % modulo) + val) % modulo;
            if (i != n - k) {
                p = p * power % modulo;
            }
        }
        int j = n - k;
        for (int i = n - k - 1; i >= 0; --i) {
            int pre = s.charAt(i + k) - 'a' + 1;
            int cur = s.charAt(i) - 'a' + 1;
            h = ((h - pre * p % modulo + modulo) * power % modulo + cur) % modulo;
            if (h == hashValue) {
                j = i;
            }
        }
        return s.substring(j, j + k);
    }
}
```

```cpp
class Solution {
public:
    string subStrHash(string s, int power, int modulo, int k, int hashValue) {
        long long h = 0, p = 1;
        int n = s.size();
        for (int i = n - 1; i >= n - k; --i) {
            int val = s[i] - 'a' + 1;
            h = ((h * power % modulo) + val) % modulo;
            if (i != n - k) {
                p = p * power % modulo;
            }
        }
        int j = n - k;
        for (int i = n - k - 1; i >= 0; --i) {
            int pre = s[i + k] - 'a' + 1;
            int cur = s[i] - 'a' + 1;
            h = ((h - pre * p % modulo + modulo) * power % modulo + cur) % modulo;
            if (h == hashValue) {
                j = i;
            }
        }
        return s.substr(j, k);
    }
};
```

```go
func subStrHash(s string, power int, modulo int, k int, hashValue int) string {
	h, p := 0, 1
	n := len(s)
	for i := n - 1; i >= n-k; i-- {
		val := int(s[i] - 'a' + 1)
		h = (h*power%modulo + val) % modulo
		if i != n-k {
			p = p * power % modulo
		}
	}
	j := n - k
	for i := n - k - 1; i >= 0; i-- {
		pre := int(s[i+k] - 'a' + 1)
		cur := int(s[i] - 'a' + 1)
		h = ((h-pre*p%modulo+modulo)*power%modulo + cur) % modulo
		if h == hashValue {
			j = i
		}
	}
	return s[j : j+k]
}
```

```ts
function subStrHash(
    s: string,
    power: number,
    modulo: number,
    k: number,
    hashValue: number,
): string {
    let h: bigint = BigInt(0),
        p: bigint = BigInt(1);
    const n: number = s.length;
    const mod = BigInt(modulo);
    for (let i: number = n - 1; i >= n - k; --i) {
        const val: bigint = BigInt(s.charCodeAt(i) - 'a'.charCodeAt(0) + 1);
        h = (((h * BigInt(power)) % mod) + val) % mod;
        if (i !== n - k) {
            p = (p * BigInt(power)) % mod;
        }
    }
    let j: number = n - k;
    for (let i: number = n - k - 1; i >= 0; --i) {
        const pre: bigint = BigInt(s.charCodeAt(i + k) - 'a'.charCodeAt(0) + 1);
        const cur: bigint = BigInt(s.charCodeAt(i) - 'a'.charCodeAt(0) + 1);
        h = ((((h - ((pre * p) % mod) + mod) * BigInt(power)) % mod) + cur) % mod;
        if (Number(h) === hashValue) {
            j = i;
        }
    }
    return s.substring(j, j + k);
}
```

```js
/**
 * @param {string} s
 * @param {number} power
 * @param {number} modulo
 * @param {number} k
 * @param {number} hashValue
 * @return {string}
 */
var subStrHash = function (s, power, modulo, k, hashValue) {
    let h = BigInt(0),
        p = BigInt(1);
    const n = s.length;
    const mod = BigInt(modulo);
    for (let i = n - 1; i >= n - k; --i) {
        const val = BigInt(s.charCodeAt(i) - 'a'.charCodeAt(0) + 1);
        h = (((h * BigInt(power)) % mod) + val) % mod;
        if (i !== n - k) {
            p = (p * BigInt(power)) % mod;
        }
    }
    let j = n - k;
    for (let i = n - k - 1; i >= 0; --i) {
        const pre = BigInt(s.charCodeAt(i + k) - 'a'.charCodeAt(0) + 1);
        const cur = BigInt(s.charCodeAt(i) - 'a'.charCodeAt(0) + 1);
        h = ((((h - ((pre * p) % mod) + mod) * BigInt(power)) % mod) + cur) % mod;
        if (Number(h) === hashValue) {
            j = i;
        }
    }
    return s.substring(j, j + k);
};
```

<!-- tabs:end -->

<!-- end -->
