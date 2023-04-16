# [2622. 有时间限制的缓存](https://leetcode.cn/problems/cache-with-time-limit)

[English Version](/solution/2600-2699/2622.Cache%20With%20Time%20Limit/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>编写一个类，它允许获取和设置键-值对，并且每个键都有一个&nbsp;<strong>过期时间</strong>&nbsp;。</p>

<p>该类有三个公共方法：</p>

<p><code>set(key, value, duration)</code>&nbsp;：接收参数为整型键 <code>key</code> 、整型值 <code>value</code> 和以毫秒为单位的持续时间 <code>duration</code> 。一旦 <code>duration</code>&nbsp;到期后，这个键就无法访问。如果相同的未过期键已经存在，该方法将返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。如果该键已经存在，则它的值和持续时间都应该被覆盖。</p>

<p><code>get(key)</code>&nbsp;：如果存在一个未过期的键，它应该返回这个键相关的值。否则返回&nbsp;<code>-1</code>&nbsp;。</p>

<p><code>count()</code>&nbsp;：返回未过期键的总数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> 
["TimeLimitedCache", "set", "get", "count", "get"]
[[], [1, 42, 100], [1], [], [1]]
[0, 0, 50, 50, 150]
<strong>输出：</strong> [null, false, 42, 1, -1]
<strong>解释：</strong>
在 t=0 时，缓存被构造。
在 t=0 时，添加一个键值对 (1: 42) ，过期时间为 100ms 。因为该值不存在，因此返回false。
在 t=50 时，请求 key=1 并返回值 42。
在 t=50 时，调用 count() ，缓存中有一个未过期的键。
在 t=100 时，key=1 到期。
在 t=150 时，调用 get(1) ，返回 -1，因为缓存是空的。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>
["TimeLimitedCache", "set", "set", "get", "get", "get", "count"]
[[], [1, 42, 50], [1, 50, 100], [1], [1], [1], []]
[0, 0, 40, 50, 120, 200, 250]
<strong>输出：</strong> [null, false, true, 50, 50, -1]
<strong>解释：</strong>
在 t=0 时，缓存被构造。
在 t=0 时，添加一个键值对 (1: 42) ，过期时间为 50ms。因为该值不存在，因此返回false。
当 t=40 时，添加一个键值对 (1: 50) ，过期时间为 100ms。因为一个未过期的键已经存在，返回 true 并覆盖这个键的旧值。
在 t=50 时，调用 get(1) ，返回 50。
在 t=120 时，调用 get(1) ，返回 50。
在 t=140 时，key=1 过期。
在 t=200 时，调用 get(1) ，但缓存为空，因此返回 -1。
在 t=250 时，count() 返回0 ，因为缓存是空的，没有未过期的键。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= key &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= value &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= duration &lt;= 1000</code></li>
	<li><code>方法调用总数不会超过100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
