# [1871. Jump Game VII](https://leetcode.com/problems/jump-game-vii)

[中文文档](/solution/1800-1899/1871.Jump%20Game%20VII/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> binary string <code>s</code> and two integers <code>minJump</code> and <code>maxJump</code>. In the beginning, you are standing at index <code>0</code>, which is equal to <code>&#39;0&#39;</code>. You can move from index <code>i</code> to index <code>j</code> if the following conditions are fulfilled:</p>

<ul>
	<li><code>i + minJump &lt;= j &lt;= min(i + maxJump, s.length - 1)</code>, and</li>
	<li><code>s[j] == &#39;0&#39;</code>.</li>
</ul>

<p>Return <code>true</code><i> if you can reach index </i><code>s.length - 1</code><i> in </i><code>s</code><em>, or </em><code>false</code><em> otherwise.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;<u>0</u>11<u>0</u>1<u>0</u>&quot;, minJump = 2, maxJump = 3
<strong>Output:</strong> true
<strong>Explanation:</strong>
In the first step, move from index 0 to index 3. 
In the second step, move from index 3 to index 5.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;01101110&quot;, minJump = 2, maxJump = 3
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li><code>s[0] == &#39;0&#39;</code></li>
	<li><code>1 &lt;= minJump &lt;= maxJump &lt; s.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canReach(self, s: str, minJump: int, maxJump: int) -> bool:
        n = len(s)
        dp = [False] * n
        dp[0] = True
        pre_sum = [0] * (n + 1)
        pre_sum[1] = 1
        for i in range(1, n):
            if s[i] == '0':
                l = max(0, i - maxJump)
                r = i - minJump
                if r >= l and pre_sum[r + 1] - pre_sum[l] > 0:
                    dp[i] = True
            pre_sum[i + 1] = pre_sum[i] + dp[i]
        return dp[n - 1]
```

### **Java**

```java
class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] dp = new boolean[n];
        dp[0] = true;
        int[] preSum = new int[n + 1];
        preSum[1] = 1;
        for (int i = 1; i < n; ++i) {
            if (s.charAt(i) == '0') {
                int l = Math.max(0, i - maxJump);
                int r = i - minJump;
                if (r >= l && preSum[r + 1] - preSum[l] > 0) {
                    dp[i] = true;
                }
            }
            preSum[i + 1] = preSum[i] + (dp[i] ? 1 : 0);
        }
        return dp[n - 1];
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @param {number} minJump
 * @param {number} maxJump
 * @return {boolean}
 */
var canReach = function (s, minJump, maxJump) {
    let n = s.length;
    let dp = new Array(n).fill(0);
    let sum = new Array(n + 1).fill(0);
    dp[0] = 1;
    sum[1] = 1;
    for (let i = 1; i < n; i++) {
        if (s.charAt(i) == '0') {
            let left = Math.max(0, i - maxJump);
            let right = i - minJump;
            if (left <= right && sum[right + 1] - sum[left] > 0) {
                dp[i] = 1;
            }
        }
        sum[i + 1] = sum[i] + dp[i];
    }
    return dp.pop();
};
```

### **...**

```

```

<!-- tabs:end -->
