# [2156. Find Substring With Given Hash Value](https://leetcode.com/problems/find-substring-with-given-hash-value)

[中文文档](/solution/2100-2199/2156.Find%20Substring%20With%20Given%20Hash%20Value/README.md)

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

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **JavaScript**

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
    power = BigInt(power);
    modulo = BigInt(modulo);
    hashValue = BigInt(hashValue);
    const n = s.length;
    let pk = 1n;
    let ac = 0n;
    // 倒序滑动窗口
    for (let i = n - 1; i > n - 1 - k; i--) {
        ac = (ac * power + getCode(s, i)) % modulo;
        pk = (pk * power) % modulo;
    }
    let ans = -1;
    if (ac == hashValue) {
        ans = n - k;
    }
    for (let i = n - 1 - k; i >= 0; i--) {
        let pre = (getCode(s, i + k) * pk) % modulo;
        ac = (ac * power + getCode(s, i) - pre + modulo) % modulo;
        if (ac == hashValue) {
            ans = i;
        }
    }
    return ans == -1 ? '' : s.substring(ans, ans + k);
};

function getCode(str, index) {
    return BigInt(str.charCodeAt(index) - 'a'.charCodeAt(0) + 1);
}
```

### **...**

```

```

<!-- tabs:end -->
