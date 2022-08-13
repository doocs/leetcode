# [面试题 16.15. 珠玑妙算](https://leetcode.cn/problems/master-mind-lcci)

[English Version](/lcci/16.15.Master%20Mind/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>珠玑妙算游戏（the game of master mind）的玩法如下。</p>
<p>计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。例如，计算机可能有RGGB 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）。作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。要是猜对某个槽的颜色，则算一次“猜中”；要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。</p>
<p>给定一种颜色组合<code>solution</code>和一个猜测<code>guess</code>，编写一个方法，返回猜中和伪猜中的次数<code>answer</code>，其中<code>answer[0]</code>为猜中的次数，<code>answer[1]</code>为伪猜中的次数。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong> solution="RGBY",guess="GGRR"
<strong>输出：</strong> [1,1]
<strong>解释：</strong> 猜中1次，伪猜中1次。
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>len(solution) = len(guess) = 4</code></li>
<li><code>solution</code>和<code>guess</code>仅包含<code>"R"</code>,<code>"G"</code>,<code>"B"</code>,<code>"Y"</code>这4种字符</li>
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

### **JavaScript**

```js
/**
 * @param {string} solution
 * @param {string} guess
 * @return {number[]}
 */
var masterMind = function (solution, guess) {
    let counts1 = { R: 0, G: 0, B: 0, Y: 0 };
    let counts2 = { R: 0, G: 0, B: 0, Y: 0 };
    let res1 = 0;
    for (let i = 0; i < solution.length; i++) {
        let s1 = solution.charAt(i),
            s2 = guess.charAt(i);
        if (s1 == s2) {
            res1++;
        } else {
            counts1[s1] += 1;
            counts2[s2] += 1;
        }
    }
    let res2 = ['R', 'G', 'B', 'Y'].reduce(
        (a, c) => a + Math.min(counts1[c], counts2[c]),
        0,
    );
    return [res1, res2];
};
```

### **...**

```


```

<!-- tabs:end -->
