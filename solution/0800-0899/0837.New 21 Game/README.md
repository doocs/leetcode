# [837. 新 21 点](https://leetcode-cn.com/problems/new-21-game)

[English Version](/solution/0800-0899/0837.New%2021%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>爱丽丝参与一个大致基于纸牌游戏 &ldquo;21点&rdquo; 规则的游戏，描述如下：</p>

<p>爱丽丝以 <code>0</code> 分开始，并在她的得分少于 <code>K</code> 分时抽取数字。 抽取时，她从 <code>[1, W]</code> 的范围中随机获得一个整数作为分数进行累计，其中 <code>W</code> 是整数。 每次抽取都是独立的，其结果具有相同的概率。</p>

<p>当爱丽丝获得不少于 <code>K</code> 分时，她就停止抽取数字。 爱丽丝的分数不超过 <code>N</code> 的概率是多少？</p>

<p>&nbsp;</p>

<p><strong>示例</strong><strong> 1</strong><strong>：</strong></p>

<pre><strong>输入：</strong>N = 10, K = 1, W = 10
<strong>输出：</strong>1.00000
<strong>说明：</strong>爱丽丝得到一张卡，然后停止。</pre>

<p><strong>示例 </strong><strong>2</strong><strong>：</strong></p>

<pre><strong>输入：</strong>N = 6, K = 1, W = 10
<strong>输出：</strong>0.60000
<strong>说明：</strong>爱丽丝得到一张卡，然后停止。
在 W = 10 的 6 种可能下，她的得分不超过 N = 6 分。</pre>

<p><strong>示例 </strong><strong>3</strong><strong>：</strong></p>

<pre><strong>输入：</strong>N = 21, K = 17, W = 10
<strong>输出：</strong>0.73278</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>0 &lt;= K &lt;= N &lt;= 10000</code></li>
	<li><code>1 &lt;= W &lt;= 10000</code></li>
	<li>如果答案与正确答案的误差不超过 <code>10^-5</code>，则该答案将被视为正确答案通过。</li>
	<li>此问题的判断限制时间已经减少。</li>
</ol>

## 解法

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
function new21Game(n: number, k: number, maxPts: number): number {
    if (!k) return 1.0;
    let dp = new Array(k + maxPts).fill(0.0);
    for (let i = k; i <= n && i < k + maxPts; i++) {
        dp[i] = 1.0;
    }
    dp[k - 1] = 1.0 * Math.min(n - k + 1, maxPts) / maxPts;
    for (let i = k - 2; i >= 0; i--) {
        dp[i] = dp[i + 1] - (dp[i + maxPts + 1] - dp[i + 1]) / maxPts;
    }
    return dp[0];
};
```

### **...**

```

```

<!-- tabs:end -->
