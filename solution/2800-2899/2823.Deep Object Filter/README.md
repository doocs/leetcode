---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2823.Deep%20Object%20Filter/README.md
tags:
    - JavaScript
---

<!-- problem:start -->

# [2823. 深度对象筛选 🔒](https://leetcode.cn/problems/deep-object-filter)

[English Version](/solution/2800-2899/2823.Deep%20Object%20Filter/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个对象 <code>obj</code> 和一个函数 <code>fn</code>，返回一个经过筛选的对象 <code>filteredObject</code>。</p>

<p>函数 <code>deepFilter</code> 应该在对象 <code>obj</code> 上执行深度筛选操作。深度筛选操作应该移除筛选函数 <code>fn</code> 输出为 <code>false</code> 的属性，以及在键被移除后仍然存在的任何空对象或数组。</p>

<p>如果深度筛选操作导致对象或数组为空，没有剩余属性，<code>deepFilter</code> 应该返回 <code>undefined</code>，表示在 <code>filteredObject</code> 中没有有效数据。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>
obj = [-5, -4, -3, -2, -1, 0, 1], 
fn = (x) =&gt; x &gt; 0
<strong>输出：</strong>[1]
<b>解释：</b>所有不大于 0 的值都被移除。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>
obj = {"a": 1, "b": "2", "c": 3, "d": "4", "e": 5, "f": 6, "g": {"a": 1}}, 
fn = (x) =&gt; typeof x === "string"
<b>输出：</b>{"b":"2","d":"4"}
<b>解释：</b>所有值不是字符串的键都被移除。在筛选过程中移除键后，任何导致为空的对象也被移除。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>
obj = [-1, [-1, -1, 5, -1, 10], -1, [-1], [-5]], 
fn = (x) =&gt; x &gt; 0
<b>输出：</b>[[5,10]]
<b>解释：</b>所有不大于 0 的值都被移除。在筛选过程中移除值后，任何导致为空的数组也被移除。</pre>

<p><strong class="example">示例 4：</strong></p>

<pre>
<b>输入：</b>
obj = [[[[5]]]], 
fn = (x) =&gt; Array.isArray(x)
<b>输出：</b>undefined
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>fn</code> 是一个返回布尔值的函数</li>
	<li><code>obj</code> 是一个有效的 JSON 对象</li>
	<li><code>2 &lt;= JSON.stringify(obj).length &lt;= 10**5</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归

我们先判断当前对象是否为数组，如果是数组，我们就对数组中的每一个元素进行递归调用，然后过滤掉返回值为 `undefined` 的元素，最后返回过滤后的数组。

如果当前对象不是数组，我们就判断当前对象是否为对象，如果是对象，我们就对对象中的每一个属性值进行递归调用，然后过滤掉返回值为 `undefined` 的属性值，最后返回过滤后的对象。

如果当前对象既不是数组也不是对象，我们就直接返回 `fn(obj) ? obj : undefined`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为对象中的元素个数。

<!-- tabs:start -->

#### TypeScript

```ts
function deepFilter(obj: Record<string, any>, fn: Function): Record<string, any> | undefined {
    const dfs = (data: any): any => {
        if (Array.isArray(data)) {
            const res = data.map(dfs).filter((item: any) => item !== undefined);
            return res.length > 0 ? res : undefined;
        }
        if (typeof data === 'object' && data !== null) {
            const res: Record<string, any> = {};
            for (const key in data) {
                if (data.hasOwnProperty(key)) {
                    const filteredValue = dfs(data[key]);
                    if (filteredValue !== undefined) {
                        res[key] = filteredValue;
                    }
                }
            }
            return Object.keys(res).length > 0 ? res : undefined;
        }
        return fn(data) ? data : undefined;
    };

    return dfs(obj);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
