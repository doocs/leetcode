# [2251. 花期内花的数目](https://leetcode.cn/problems/number-of-flowers-in-full-bloom)

[English Version](/solution/2200-2299/2251.Number%20of%20Flowers%20in%20Full%20Bloom/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>flowers</code>&nbsp;，其中&nbsp;<code>flowers[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;朵花的 <strong>花期</strong>&nbsp;从&nbsp;<code>start<sub>i</sub></code>&nbsp;到&nbsp;<code>end<sub>i</sub></code>&nbsp;（都 <strong>包含</strong>）。同时给你一个下标从 <strong>0</strong>&nbsp;开始大小为 <code>n</code>&nbsp;的整数数组&nbsp;<code>persons</code>&nbsp;，<code>persons[i]</code>&nbsp;是第&nbsp;<code>i</code>&nbsp;个人来看花的时间。</p>

<p>请你返回一个大小为 <code>n</code>&nbsp;的整数数组<em>&nbsp;</em><code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code>是第&nbsp;<code>i</code>&nbsp;个人到达时在花期内花的&nbsp;<strong>数目</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2251.Number%20of%20Flowers%20in%20Full%20Bloom/images/ex1new.jpg" style="width: 550px; height: 216px;"></p>

<pre><b>输入：</b>flowers = [[1,6],[3,7],[9,12],[4,13]], persons = [2,3,7,11]
<b>输出：</b>[1,2,2,2]
<strong>解释：</strong>上图展示了每朵花的花期时间，和每个人的到达时间。
对每个人，我们返回他们到达时在花期内花的数目。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2251.Number%20of%20Flowers%20in%20Full%20Bloom/images/ex2new.jpg" style="width: 450px; height: 195px;"></p>

<pre><b>输入：</b>flowers = [[1,10],[3,3]], persons = [3,3,2]
<b>输出：</b>[2,2,1]
<b>解释：</b>上图展示了每朵花的花期时间，和每个人的到达时间。
对每个人，我们返回他们到达时在花期内花的数目。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= flowers.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>flowers[i].length == 2</code></li>
	<li><code>1 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= persons.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= persons[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

离散差分+离散查询

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
function fullBloomFlowers(flowers: number[][], persons: number[]): number[] {
    // 离散差分
    let hashMap = new Map();
    for (let [start, end] of flowers) {
        end++;
        hashMap.set(start, (hashMap.get(start) || 0) + 1);
        hashMap.set(end, (hashMap.get(end) || 0) - 1);
    }
    for (let p of persons) {
        if (!hashMap.has(p)) {
            hashMap.set(p, 0);
        }
    }
    let keys = Array.from(hashMap.keys()).sort((a, b) => a - b);
    let pre = 0;
    for (let k of keys) {
        pre += hashMap.get(k);
        hashMap.set(k, pre);
    }
    // 离散查询
    let ans = persons.map(v => hashMap.get(v));
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
