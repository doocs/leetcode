# [2286. 以组为单位订音乐会的门票](https://leetcode.cn/problems/booking-concert-tickets-in-groups)

[English Version](/solution/2200-2299/2286.Booking%20Concert%20Tickets%20in%20Groups/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个音乐会总共有&nbsp;<code>n</code>&nbsp;排座位，编号从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n - 1</code>&nbsp;，每一排有&nbsp;<code>m</code>&nbsp;个座椅，编号为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>m - 1</code>&nbsp;。你需要设计一个买票系统，针对以下情况进行座位安排：</p>

<ul>
	<li>同一组的 <code>k</code>&nbsp;位观众坐在<strong> 同一排座位，且座位连续 </strong>。</li>
	<li><code>k</code>&nbsp;位观众中 <strong>每一位</strong>&nbsp;都有座位坐，但他们 <strong>不一定</strong>&nbsp;坐在一起。</li>
</ul>

<p>由于观众非常挑剔，所以：</p>

<ul>
	<li>只有当一个组里所有成员座位的排数都 <strong>小于等于</strong>&nbsp;<code>maxRow</code>&nbsp;，这个组才能订座位。每一组的&nbsp;<code>maxRow</code>&nbsp;可能 <strong>不同</strong>&nbsp;。</li>
	<li>如果有多排座位可以选择，优先选择 <strong>最小</strong>&nbsp;的排数。如果同一排中有多个座位可以坐，优先选择号码 <strong>最小</strong>&nbsp;的。</li>
</ul>

<p>请你实现&nbsp;<code>BookMyShow</code>&nbsp;类：</p>

<ul>
	<li><code>BookMyShow(int n, int m)</code>&nbsp;，初始化对象，<code>n</code>&nbsp;是排数，<code>m</code>&nbsp;是每一排的座位数。</li>
	<li><code>int[] gather(int k, int maxRow)</code>&nbsp;返回长度为 <code>2</code>&nbsp;的数组，表示 <code>k</code>&nbsp;个成员中 <strong>第一个座位</strong>&nbsp;的排数和座位编号，这 <code>k</code>&nbsp;位成员必须坐在 <strong>同一排座位，且座位连续 </strong>。换言之，返回最小可能的&nbsp;<code>r</code> 和&nbsp;<code>c</code>&nbsp;满足第&nbsp;<code>r</code>&nbsp;排中&nbsp;<code>[c, c + k - 1]</code>&nbsp;的座位都是空的，且&nbsp;<code>r &lt;= maxRow</code>&nbsp;。如果&nbsp;<strong>无法</strong>&nbsp;安排座位，返回&nbsp;<code>[]</code>&nbsp;。</li>
	<li><code>boolean scatter(int k, int maxRow)</code>&nbsp;如果组里所有&nbsp;<code>k</code>&nbsp;个成员&nbsp;<strong>不一定</strong>&nbsp;要坐在一起的前提下，都能在第&nbsp;<code>0</code> 排到第&nbsp;<code>maxRow</code>&nbsp;排之间找到座位，那么请返回&nbsp;<code>true</code>&nbsp;。这种情况下，每个成员都优先找排数&nbsp;<strong>最小</strong>&nbsp;，然后是座位编号最小的座位。如果不能安排所有&nbsp;<code>k</code>&nbsp;个成员的座位，请返回&nbsp;<code>false</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["BookMyShow", "gather", "gather", "scatter", "scatter"]
[[2, 5], [4, 0], [2, 0], [5, 1], [5, 1]]
<strong>输出：</strong>
[null, [0, 0], [], true, false]

<strong>解释：</strong>
BookMyShow bms = new BookMyShow(2, 5); // 总共有 2 排，每排 5 个座位。
bms.gather(4, 0); // 返回 [0, 0]
                  // 这一组安排第 0 排 [0, 3] 的座位。
bms.gather(2, 0); // 返回 []
                  // 第 0 排只剩下 1 个座位。
                  // 所以无法安排 2 个连续座位。
bms.scatter(5, 1); // 返回 True
                   // 这一组安排第 0 排第 4 个座位和第 1 排 [0, 3] 的座位。
bms.scatter(5, 1); // 返回 False
                   // 总共只剩下 2 个座位。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m, k &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= maxRow &lt;= n - 1</code></li>
	<li><code>gather</code> 和&nbsp;<code>scatter</code>&nbsp;<strong>总</strong> 调用次数不超过&nbsp;<code>5 * 10<sup>4</sup></code> 次。</li>
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

```

### **...**

```

```

<!-- tabs:end -->
