---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2622.Cache%20With%20Time%20Limit/README.md
tags:
    - JavaScript
---

<!-- problem:start -->

# [2622. 有时间限制的缓存](https://leetcode.cn/problems/cache-with-time-limit)

[English Version](/solution/2600-2699/2622.Cache%20With%20Time%20Limit/README_EN.md)

## 题目描述

<!-- description:start -->

<p>编写一个类，它允许获取和设置键-值对，并且每个键都有一个&nbsp;<strong>过期时间</strong>&nbsp;。</p>

<p>该类有三个公共方法：</p>

<p><code>set(key, value, duration)</code>&nbsp;：接收参数为整型键 <code>key</code> 、整型值 <code>value</code> 和以毫秒为单位的持续时间 <code>duration</code> 。一旦 <code>duration</code>&nbsp;到期后，这个键就无法访问。如果相同的未过期键已经存在，该方法将返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。如果该键已经存在，则它的值和持续时间都应该被覆盖。</p>

<p><code>get(key)</code>&nbsp;：如果存在一个未过期的键，它应该返回这个键相关的值。否则返回&nbsp;<code>-1</code>&nbsp;。</p>

<p><code>count()</code>&nbsp;：返回未过期键的总数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> 
actions = ["TimeLimitedCache", "set", "get", "count", "get"]
values = [[], [1, 42, 100], [1], [], [1]]
timeDeays = [0, 0, 50, 50, 150]
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
actions = ["TimeLimitedCache", "set", "set", "get", "get", "get", "count"]
values = [[], [1, 42, 50], [1, 50, 100], [1], [1], [1], []]
timeDelays = [0, 0, 40, 50, 120, 200, 250]
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
	<li><code>0 &lt;= key, value &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= duration &lt;= 1000</code></li>
	<li><code>1 &lt;= actions.length &lt;= 100</code></li>
	<li><code>actions.length === values.length</code></li>
	<li><code>actions.length === timeDelays.length</code></li>
	<li><code>0 &lt;= timeDelays[i] &lt;= 1450</code></li>
	<li><code>actions[i]</code> 是 "TimeLimitedCache"、"set"、"get" 和 "count" 中的一个。</li>
	<li>第一个操作始终是 "TimeLimitedCache" 而且一定会以 0 毫秒的延迟立即执行</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们用哈希表 $cache$ 记录键值对，其中键为整型键 $key$，值为一个数组，数组的第一个元素为整型值 $value$，第二个元素为元素的过期时间 $expire$。

我们实现一个 `removeExpire` 方法，用于删除过期的键值对。在 `set`、`get` 和 `count` 方法中，我们先调用 `removeExpire` 方法，然后再进行相应的操作。

时间复杂度为 $O(1)$，空间复杂度为 $O(n)$。其中 $n$ 为哈希表 $cache$ 的大小。

<!-- tabs:start -->

#### TypeScript

```ts
class TimeLimitedCache {
    #cache: Map<number, [value: number, expire: number]> = new Map();

    set(key: number, value: number, duration: number): boolean {
        const isExist = this.#cache.has(key);

        if (!this.#isExpired(key)) {
            this.#cache.set(key, [value, Date.now() + duration]);
        }

        return isExist;
    }

    get(key: number): number {
        if (this.#isExpired(key)) return -1;
        const res = this.#cache.get(key)?.[0] ?? -1;
        return res;
    }

    count(): number {
        const xs = Array.from(this.#cache).filter(([key]) => !this.#isExpired(key));
        return xs.length;
    }

    #isExpired = (key: number) =>
        this.#cache.has(key) &&
        (this.#cache.get(key)?.[1] ?? Number.NEGATIVE_INFINITY) < Date.now();
}

/**
 * Your TimeLimitedCache object will be instantiated and called as such:
 * var obj = new TimeLimitedCache()
 * obj.set(1, 42, 1000); // false
 * obj.get(1) // 42
 * obj.count() // 1
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
