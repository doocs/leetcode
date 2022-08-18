# [08.11. Coin](https://leetcode.cn/problems/coin-lcci)

[中文文档](/lcci/08.11.Coin/README.md)

## Description

<p>Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and pennies (1 cent), write code to calculate the number of ways of representing n cents.&nbsp;(The result may be large, so you should return it modulo 1000000007)</p>

<p><strong>Example1:</strong></p>

<pre>



<strong> Input</strong>: n = 5



<strong> Output</strong>: 2



<strong> Explanation</strong>: There are two ways:



5=5



5=1+1+1+1+1



</pre>

<p><strong>Example2:</strong></p>

<pre>



<strong> Input</strong>: n = 10



<strong> Output</strong>: 4



<strong> Explanation</strong>: There are four ways:



10=10



10=5+5



10=5+1+1+1+1+1



10=1+1+1+1+1+1+1+1+1+1



</pre>

<p><strong>Notes: </strong></p>

<p>You can assume:</p>

<ul>
	<li>0 &lt;= n&nbsp;&lt;= 1000000</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python


```

### **Java**

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
