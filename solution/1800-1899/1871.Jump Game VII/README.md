# [1871. 跳跃游戏 VII](https://leetcode.cn/problems/jump-game-vii)

[English Version](/solution/1800-1899/1871.Jump%20Game%20VII/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0 </strong>开始的二进制字符串 <code>s</code> 和两个整数 <code>minJump</code> 和 <code>maxJump</code> 。一开始，你在下标 <code>0</code> 处，且该位置的值一定为 <code>'0'</code> 。当同时满足如下条件时，你可以从下标 <code>i</code> 移动到下标 <code>j</code> 处：</p>

<ul>
	<li><code>i + minJump <= j <= min(i + maxJump, s.length - 1)</code> 且</li>
	<li><code>s[j] == '0'</code>.</li>
</ul>

<p>如果你可以到达 <code>s</code> 的下标<i> </i><code>s.length - 1</code> 处，请你返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "<strong>0</strong>11<strong>0</strong>1<strong>0</strong>", minJump = 2, maxJump = 3
<b>输出：</b>true
<strong>解释：</strong>
第一步，从下标 0 移动到下标 3 。
第二步，从下标 3 移动到下标 5 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "01101110", minJump = 2, maxJump = 3
<b>输出：</b>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= s.length <= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 要么是 <code>'0'</code> ，要么是 <code>'1'</code></li>
	<li><code>s[0] == '0'</code></li>
	<li><code>1 <= minJump <= maxJump < s.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“动态规划 + 前缀和”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
