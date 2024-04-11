# [2156. Find Substring With Given Hash Value](https://leetcode.com/problems/find-substring-with-given-hash-value)

[中文文档](/solution/2100-2199/2156.Find%20Substring%20With%20Given%20Hash%20Value/README.md)

<!-- tags:String,Sliding Window,Hash Function,Rolling Hash -->

## Description

<p>The hash of a <strong>0-indexed</strong> string <code>s</code> of length <code>k</code>, given integers <code>p</code> and <code>m</code>, is computed using the following function:</p>

<ul>
	<li><code>hash(s, p, m) = (val(s[0]) * p<sup>0</sup> + val(s[1]) * p<sup>1</sup> + ... + val(s[k-1]) * p<sup>k-1</sup>) mod m</code>.</li>
</ul>

<p>Where <code>val(s[i])</code> represents the index of <code>s[i]</code> in the alphabet from <code>val(&#39;a&#39;) = 1</code> to <code>val(&#39;z&#39;) = 26</code>.</p>

<p>You are given a string <code>s</code> and the integers <code>power</code>, <code>modulo</code>, <code>k</code>, and <code>hashValue.</code> Return <code>sub</code>,<em> the <strong>first</strong> <strong>substring</strong> of </em><code>s</code><em> of length </em><code>k</code><em> such that </em><code>hash(sub, power, modulo) == hashValue</code>.</p>

<p>The test cases will be generated such that an answer always <strong>exists</strong>.</p>

<p>A <b>substring</b> is a contiguous non-empty sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetcode&quot;, power = 7, modulo = 20, k = 2, hashValue = 0
<strong>Output:</strong> &quot;ee&quot;
<strong>Explanation:</strong> The hash of &quot;ee&quot; can be computed to be hash(&quot;ee&quot;, 7, 20) = (5 * 1 + 5 * 7) mod 20 = 40 mod 20 = 0. 
&quot;ee&quot; is the first substring of length 2 with hashValue 0. Hence, we return &quot;ee&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;fbxzaad&quot;, power = 31, modulo = 100, k = 3, hashValue = 32
<strong>Output:</strong> &quot;fbx&quot;
<strong>Explanation:</strong> The hash of &quot;fbx&quot; can be computed to be hash(&quot;fbx&quot;, 31, 100) = (6 * 1 + 2 * 31 + 24 * 31<sup>2</sup>) mod 100 = 23132 mod 100 = 32. 
The hash of &quot;bxz&quot; can be computed to be hash(&quot;bxz&quot;, 31, 100) = (2 * 1 + 24 * 31 + 26 * 31<sup>2</sup>) mod 100 = 25732 mod 100 = 32. 
&quot;fbx&quot; is the first substring of length 3 with hashValue 32. Hence, we return &quot;fbx&quot;.
Note that &quot;bxz&quot; also has a hash of 32 but it appears later than &quot;fbx&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= power, modulo &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= hashValue &lt; modulo</code></li>
	<li><code>s</code> consists of lowercase English letters only.</li>
	<li>The test cases are generated such that an answer always <strong>exists</strong>.</li>
</ul>

## Solutions

### Solution 1

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
