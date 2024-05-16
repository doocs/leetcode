---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2694.Event%20Emitter/README.md
---

<!-- problem:start -->

# [2694. 事件发射器](https://leetcode.cn/problems/event-emitter)

[English Version](/solution/2600-2699/2694.Event%20Emitter/README_EN.md)

## 题目描述

<!-- description:start -->

<p>设计一个 <code>EventEmitter</code> 类。这个接口与 Node.js 或 DOM 的 Event Target 接口相似，但有一些差异。<code>EventEmitter</code> 应该允许订阅事件和触发事件。</p>

<p>你的 <code>EventEmitter</code> 类应该有以下两个方法：</p>

<ul>
	<li><strong>subscribe</strong> - 这个方法接收两个参数：一个作为字符串的事件名和一个回调函数。当事件被触发时，这个回调函数将被调用。 一个事件应该能够有多个监听器。当触发带有多个回调函数的事件时，应按照订阅的顺序依次调用每个回调函数。应返回一个结果数组。你可以假设传递给 <code>subscribe</code> 的回调函数都不是引用相同的。 <code>subscribe</code> 方法还应返回一个对象，其中包含一个 <code>unsubscribe</code> 方法，使用户可以取消订阅。当调用 <code>unsubscribe</code> 方法时，回调函数应该从订阅列表中删除，并返回 undefined。</li>
	<li><strong>emit</strong> - 这个方法接收两个参数：一个作为字符串的事件名和一个可选的参数数组，这些参数将传递给回调函数。如果没有订阅给定事件的回调函数，则返回一个空数组。否则，按照它们被订阅的顺序返回所有回调函数调用的结果数组。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：
</b>actions = ["EventEmitter", "emit", "subscribe", "subscribe", "emit"], 
values = [[], ["firstEvent", "function cb1() { return 5; }"],  ["firstEvent", "function cb1() { return 5; }"], ["firstEvent"]]
<b>输出：</b>[[],["emitted",[]],["subscribed"],["subscribed"],["emitted",[5,6]]]
<b>解释：</b>
const emitter = new EventEmitter();
emitter.emit("firstEvent"); // [], 还没有订阅任何回调函数
emitter.subscribe("firstEvent", function cb1() { return 5; });
emitter.subscribe("firstEvent", function cb2() { return 6; });
emitter.emit("firstEvent"); // [5, 6], 返回 cb1 和 cb2 的输出
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：
</b>actions = ["EventEmitter", "subscribe", "emit", "emit"], 
values = [[], ["firstEvent", "function cb1(...args) { return args.join(','); }"], ["firstEvent", [1,2,3]], ["firstEvent", [3,4,6]]]
<b>输出：</b>[[],["subscribed"],["emitted",["1,2,3"]],["emitted",["3,4,6"]]]
<strong>解释：</strong>注意 emit 方法应该能够接受一个可选的参数数组。

const emitter = new EventEmitter();
emitter.subscribe("firstEvent, function cb1(...args) { return args.join(','); });
emitter.emit("firstEvent", [1, 2, 3]); // ["1,2,3"]
emitter.emit("firstEvent", [3, 4, 6]); // ["3,4,6"]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：
</b>actions = ["EventEmitter", "subscribe", "emit", "unsubscribe", "emit"], 
values = [[], ["firstEvent", "(...args) =&gt; args.join(',')"], ["firstEvent", [1,2,3]], [0], ["firstEvent", [4,5,6]]]
<b>输出：</b>[[],["subscribed"],["emitted",["1,2,3"]],["unsubscribed",0],["emitted",[]]]
<b>解释：</b>
const emitter = new EventEmitter();
const sub = emitter.subscribe("firstEvent", (...args) =&gt; args.join(','));
emitter.emit("firstEvent", [1, 2, 3]); // ["1,2,3"]
sub.unsubscribe(); // undefined
emitter.emit("firstEvent", [4, 5, 6]); // [], 没有订阅者
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<b>输入：
</b>actions = ["EventEmitter", "subscribe", "subscribe", "unsubscribe", "emit"], 
values = [[], ["firstEvent", "x =&gt; x + 1"], ["firstEvent", "x =&gt; x + 2"], [0], ["firstEvent", [5]]]
<b>输出：</b>[[],["subscribed"],["emitted",["1,2,3"]],["unsubscribed",0],["emitted",[7]]]
<b>解释：</b>
const emitter = new EventEmitter();
const sub1 = emitter.subscribe("firstEvent", x =&gt; x + 1);
const sub2 = emitter.subscribe("firstEvent", x =&gt; x + 2);
sub1.unsubscribe(); // undefined
emitter.emit("firstEvent", [5]); // [7]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= actions.length &lt;= 10</code></li>
	<li><code>values.length === actions.length</code></li>
	<li>所有测试用例都是有效的。例如，你不需要处理取消一个不存在的订阅的情况。</li>
	<li>只有 4 种不同的操作：<code>EventEmitter</code>、<code>emit</code>、<code>subscribe</code> 和 <code>unsubscribe</code>&nbsp;。 <code>EventEmitter</code> 操作没有参数。</li>
	<li><code>emit</code> 操作接收 1 或 2 个参数。第一个参数是要触发的事件名，第二个参数传递给回调函数。</li>
	<li><code>subscribe</code> 操作接收 2 个参数，第一个是事件名，第二个是回调函数。</li>
	<li><code>unsubscribe</code> 操作接收一个参数，即之前进行订阅的顺序（从 0 开始）。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

```ts
type Callback = (...args: any[]) => any;
type Subscription = {
    unsubscribe: () => void;
};

class EventEmitter {
    private d: Map<string, Set<Callback>> = new Map();

    subscribe(eventName: string, callback: Callback): Subscription {
        this.d.set(eventName, (this.d.get(eventName) || new Set()).add(callback));
        return {
            unsubscribe: () => {
                this.d.get(eventName)?.delete(callback);
            },
        };
    }

    emit(eventName: string, args: any[] = []): any {
        const callbacks = this.d.get(eventName);
        if (!callbacks) {
            return [];
        }
        return [...callbacks].map(callback => callback(...args));
    }
}

/**
 * const emitter = new EventEmitter();
 *
 * // Subscribe to the onClick event with onClickCallback
 * function onClickCallback() { return 99 }
 * const sub = emitter.subscribe('onClick', onClickCallback);
 *
 * emitter.emit('onClick'); // [99]
 * sub.unsubscribe(); // undefined
 * emitter.emit('onClick'); // []
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
