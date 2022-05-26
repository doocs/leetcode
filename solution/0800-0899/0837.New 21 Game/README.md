# [837. 新 21 点](https://leetcode.cn/problems/new-21-game)

[English Version](/solution/0800-0899/0837.New%2021%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>爱丽丝参与一个大致基于纸牌游戏 <strong>“21点”</strong> 规则的游戏，描述如下：</p>

<p>爱丽丝以 <code>0</code> 分开始，并在她的得分少于 <code>k</code> 分时抽取数字。 抽取时，她从 <code>[1, maxPts]</code> 的范围中随机获得一个整数作为分数进行累计，其中 <code>maxPts</code> 是一个整数。 每次抽取都是独立的，其结果具有相同的概率。</p>

<p>当爱丽丝获得 <code>k</code> 分 <strong>或更多分</strong> 时，她就停止抽取数字。</p>

<p>爱丽丝的分数不超过 <code>n</code> 的概率是多少？</p>

<p>与实际答案误差不超过&nbsp;<code>10<sup>-5</sup></code> 的答案将被视为正确答案。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 10, k = 1, maxPts = 10
<strong>输出：</strong>1.00000
<strong>解释：</strong>爱丽丝得到一张牌，然后停止。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 6, k = 1, maxPts = 10
<strong>输出：</strong>0.60000
<strong>解释：</strong>爱丽丝得到一张牌，然后停止。 在 10 种可能性中的 6 种情况下，她的得分不超过 6 分。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 21, k = 17, maxPts = 10
<strong>输出：</strong>0.73278
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= k &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= maxPts &lt;= 10<sup>4</sup></code></li>
</ul>

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
    dp[k - 1] = (1.0 * Math.min(n - k + 1, maxPts)) / maxPts;
    for (let i = k - 2; i >= 0; i--) {
        dp[i] = dp[i + 1] - (dp[i + maxPts + 1] - dp[i + 1]) / maxPts;
    }
    return dp[0];
}
```

### **...**

```

```

<!-- tabs:end -->
