---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2622.Cache%20With%20Time%20Limit/README_EN.md
tags:
    - JavaScript
---

<!-- problem:start -->

# [2622. Cache With Time Limit](https://leetcode.com/problems/cache-with-time-limit)

[中文文档](/solution/2600-2699/2622.Cache%20With%20Time%20Limit/README.md)

## Description

<!-- description:start -->

<p>Write a class that allows getting and setting&nbsp;key-value pairs, however a&nbsp;<strong>time until expiration</strong>&nbsp;is associated with each key.</p>

<p>The class has three public methods:</p>

<p><code>set(key, value, duration)</code>:&nbsp;accepts an integer&nbsp;<code>key</code>, an&nbsp;integer&nbsp;<code>value</code>, and a <code>duration</code> in milliseconds. Once the&nbsp;<code>duration</code>&nbsp;has elapsed, the key should be inaccessible. The method should return&nbsp;<code>true</code>&nbsp;if the same&nbsp;un-expired key already exists and <code>false</code> otherwise. Both the value and duration should be overwritten if the key already exists.</p>

<p><code>get(key)</code>: if an un-expired key exists, it should return the associated value. Otherwise it should return&nbsp;<code>-1</code>.</p>

<p><code>count()</code>: returns the count of un-expired keys.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
actions = [&quot;TimeLimitedCache&quot;, &quot;set&quot;, &quot;get&quot;, &quot;count&quot;, &quot;get&quot;]
values = [[], [1, 42, 100], [1], [], [1]]
timeDelays = [0, 0, 50, 50, 150]
<strong>Output:</strong> [null, false, 42, 1, -1]
<strong>Explanation:</strong>
At t=0, the cache is constructed.
At t=0, a key-value pair (1: 42) is added with a time limit of 100ms. The value doesn&#39;t exist so false is returned.
At t=50, key=1 is requested and the value of 42 is returned.
At t=50, count() is called and there is one active key in the cache.
At t=100, key=1 expires.
At t=150, get(1) is called but -1 is returned because the cache is empty.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
actions = [&quot;TimeLimitedCache&quot;, &quot;set&quot;, &quot;set&quot;, &quot;get&quot;, &quot;get&quot;, &quot;get&quot;, &quot;count&quot;]
values = [[], [1, 42, 50], [1, 50, 100], [1], [1], [1], []]
timeDelays = [0, 0, 40, 50, 120, 200, 250]
<strong>Output:</strong> [null, false, true, 50, 50, -1, 0]
<strong>Explanation:</strong>
At t=0, the cache is constructed.
At t=0, a key-value pair (1: 42) is added with a time limit of 50ms. The value doesn&#39;t exist so false is returned.
At t=40, a key-value pair (1: 50) is added with a time limit of 100ms. A non-expired value already existed so true is returned and the old value was overwritten.
At t=50, get(1) is called which returned 50.
At t=120, get(1) is called which returned 50.
At t=140, key=1 expires.
At t=200, get(1) is called but the cache is empty so -1 is returned.
At t=250, count() returns 0 because the cache is empty.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= key, value &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= duration &lt;= 1000</code></li>
	<li><code>1 &lt;= actions.length &lt;= 100</code></li>
	<li><code>actions.length === values.length</code></li>
	<li><code>actions.length === timeDelays.length</code></li>
	<li><code>0 &lt;= timeDelays[i] &lt;= 1450</code></li>
	<li><code>actions[i]</code>&nbsp;is one of &quot;TimeLimitedCache&quot;, &quot;set&quot;, &quot;get&quot; and&nbsp;&quot;count&quot;</li>
	<li>First action is always &quot;TimeLimitedCache&quot; and must be executed immediately, with a 0-millisecond delay</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

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
