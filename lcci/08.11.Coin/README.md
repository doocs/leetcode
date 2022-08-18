# [面试题 08.11. 硬币](https://leetcode.cn/problems/coin-lcci)

[English Version](/lcci/08.11.Coin/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>: n = 5
<strong> 输出</strong>：2
<strong> 解释</strong>: 有两种方式可以凑成总金额:
5=5
5=1+1+1+1+1
</pre>

<p> <strong>示例2:</strong></p>

<pre>
<strong> 输入</strong>: n = 10
<strong> 输出</strong>：4
<strong> 解释</strong>: 有四种方式可以凑成总金额:
10=10
10=5+5
10=5+1+1+1+1+1
10=1+1+1+1+1+1+1+1+1+1
</pre>

<p> <strong>说明：</strong></p>

<p>注意:</p>

<p>你可以假设：</p>

<ul>
    <li>0 &lt;= n (总金额) &lt;= 1000000</li>
</ul>

## 解法

完全背包问题

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
function waysToChange(n: number): number {
    const MOD = 10 ** 9 + 7;
    let coins = [1, 5, 10, 25];
    let dp = new Array(n + 1).fill(0);
    dp[0] = 1;
    for (let coin of coins) {
        for (let i = coin; i <= n; ++i) {
            dp[i] += dp[i - coin];
        }
    }
    return dp.pop() % MOD;
}
```

### **...**

```


```

<!-- tabs:end -->
