# [2692. 使对象不可变 🔒](https://leetcode.cn/problems/make-object-immutable)

[English Version](/solution/2600-2699/2692.Make%20Object%20Immutable/README_EN.md)

<!-- tags: -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>请你编写一个函数，该函数接收一个对象 <code>obj</code> ，并返回该对象的一个新的 <strong>不可变</strong> 版本。</p>

<p><strong>不可变</strong> 对象是指不能被修改的对象，如果试图修改它，则会抛出错误。</p>

<p>此新对象可能产生三种类型的错误消息。</p>

<ul>
	<li>如果试图修改对象的键，则会产生以下错误消息： <code>`Error Modifying: ${key}`</code> 。</li>
	<li>如果试图修改数组的索引，则会产生以下错误消息： <code>`Error Modifying Index: ${index}`</code> 。</li>
	<li>如果试图调用会改变数组的方法，则会产生以下错误消息： <code>`Error Calling Method: ${methodName}`</code> 。你可以假设只有以下方法能够改变数组： <code>['pop', 'push', 'shift', 'unshift', 'splice', 'sort', 'reverse']</code> 。</li>
</ul>

<p><code>obj</code> 是一个有效的 JSON 对象或数组，也就是说，它是 <code>JSON.parse()</code> 的输出结果。</p>

<p>请注意，应该抛出字符串字面量，而不是 <code>Error</code> 对象。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>
obj = {
&nbsp; "x": 5
}
fn = (obj) =&gt; { 
&nbsp; obj.x = 5;
&nbsp; return obj.x;
}
<b>输出：</b>{"value": null, "error": "Error Modifying:&nbsp;x"}
<b>解释：</b>试图修改对象的键会导致抛出错误。请注意，是否将值设置为与之前相同的值并不重要。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong> 
obj = [1, 2, 3]
fn = (arr) =&gt; { 
&nbsp; arr[1] = {}; 
&nbsp; return arr[2]; 
}
<b>输出：</b>{"value": null, "error": "Error Modifying&nbsp;Index: 1"}
<strong>解释：</strong>试图修改数组会导致抛出错误。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>
obj = {
&nbsp; "arr": [1, 2, 3]
}
fn = (obj) =&gt; { 
&nbsp; obj.arr.push(4);
&nbsp; return 42;
}
<b>输出：</b>{ "value": null, "error": "Error Calling Method: push"}
<strong>解释：</strong>调用可能导致修改的方法会导致抛出错误。
</pre>

<p><strong class="example">示例 4：</strong></p>

<pre>
<b>输入：</b>
obj = {
&nbsp; "x": 2,
&nbsp; "y": 2
}
fn = (obj) =&gt; { 
&nbsp; return Object.keys(obj);
}
<b>输出：</b>{"value": ["x", "y"], "error": null}
<strong>解释：</strong>没有尝试进行修改，因此函数正常返回。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= JSON.stringify(obj).length &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```ts
type Obj = Array<any> | Record<any, any>;

function makeImmutable(obj: Obj): Obj {
    const arrayHandler: ProxyHandler<Array<any>> = {
        set: (_, prop) => {
            throw `Error Modifying Index: ${String(prop)}`;
        },
    };
    const objectHandler: ProxyHandler<Record<any, any>> = {
        set: (_, prop) => {
            throw `Error Modifying: ${String(prop)}`;
        },
    };
    const fnHandler: ProxyHandler<Function> = {
        apply: target => {
            throw `Error Calling Method: ${target.name}`;
        },
    };
    const fn = ['pop', 'push', 'shift', 'unshift', 'splice', 'sort', 'reverse'];
    const dfs = (obj: Obj) => {
        for (const key in obj) {
            if (typeof obj[key] === 'object' && obj[key] !== null) {
                obj[key] = dfs(obj[key]);
            }
        }
        if (Array.isArray(obj)) {
            fn.forEach(f => (obj[f] = new Proxy(obj[f], fnHandler)));
            return new Proxy(obj, arrayHandler);
        }
        return new Proxy(obj, objectHandler);
    };
    return dfs(obj);
}

/**
 * const obj = makeImmutable({x: 5});
 * obj.x = 6; // throws "Error Modifying x"
 */
```

<!-- tabs:end -->

<!-- end -->
