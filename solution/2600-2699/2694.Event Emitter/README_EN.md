---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2694.Event%20Emitter/README_EN.md
---

<!-- problem:start -->

# [2694. Event Emitter](https://leetcode.com/problems/event-emitter)

[中文文档](/solution/2600-2699/2694.Event%20Emitter/README.md)

## Description

<!-- description:start -->

<p>Design an <code>EventEmitter</code> class. This interface&nbsp;is similar (but with some differences) to the one found in Node.js or the Event Target interface of the DOM. The <code>EventEmitter</code> should allow for subscribing to events and emitting them.</p>

<p>Your <code>EventEmitter</code> class should have the following two methods:</p>

<ul>
	<li><strong>subscribe</strong> - This method takes in two arguments: the name of an event as a string and a callback function. This callback function&nbsp;will later be called when the event is emitted.<br />
	An event should be able to have multiple listeners for the same event. When emitting an event with multiple callbacks, each should be called in the order in which they were subscribed. An array of results should be returned. You can assume no callbacks passed to&nbsp;<code>subscribe</code>&nbsp;are referentially identical.<br />
	The <code>subscribe</code> method should also return an object with an <code>unsubscribe</code>&nbsp;method that enables the user to unsubscribe. When it is called, the callback&nbsp;should be removed from the list of subscriptions and&nbsp;<code>undefined</code>&nbsp;should be returned.</li>
	<li><strong>emit</strong> - This method takes in two arguments: the name of an event as a string and an optional array of arguments that will be&nbsp;passed to the callback(s). If there are no callbacks subscribed to the given event, return an empty array. Otherwise, return an array of the results of all callback calls in the order they were subscribed.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
actions = [&quot;EventEmitter&quot;, &quot;emit&quot;, &quot;subscribe&quot;, &quot;subscribe&quot;, &quot;emit&quot;], 
values = [[], [&quot;firstEvent&quot;, &quot;function cb1() { return 5; }&quot;],  [&quot;firstEvent&quot;, &quot;function cb1() { return 6; }&quot;], [&quot;firstEvent&quot;]]
<strong>Output:</strong> [[],[&quot;emitted&quot;,[]],[&quot;subscribed&quot;],[&quot;subscribed&quot;],[&quot;emitted&quot;,[5,6]]]
<strong>Explanation:</strong> 
const emitter = new EventEmitter();
emitter.emit(&quot;firstEvent&quot;); // [], no callback are subscribed yet
emitter.subscribe(&quot;firstEvent&quot;, function cb1() { return 5; });
emitter.subscribe(&quot;firstEvent&quot;, function cb2() { return 6; });
emitter.emit(&quot;firstEvent&quot;); // [5, 6], returns the output of cb1 and cb2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
actions = [&quot;EventEmitter&quot;, &quot;subscribe&quot;, &quot;emit&quot;, &quot;emit&quot;], 
values = [[], [&quot;firstEvent&quot;, &quot;function cb1(...args) { return args.join(&#39;,&#39;); }&quot;], [&quot;firstEvent&quot;, [1,2,3]], [&quot;firstEvent&quot;, [3,4,6]]]
<strong>Output:</strong> [[],[&quot;subscribed&quot;],[&quot;emitted&quot;,[&quot;1,2,3&quot;]],[&quot;emitted&quot;,[&quot;3,4,6&quot;]]]
<strong>Explanation: </strong>Note that the emit method should be able to accept an OPTIONAL array of arguments.

const emitter = new EventEmitter();
emitter.subscribe(&quot;firstEvent, function cb1(...args) { return args.join(&#39;,&#39;); });
emitter.emit(&quot;firstEvent&quot;, [1, 2, 3]); // [&quot;1,2,3&quot;]
emitter.emit(&quot;firstEvent&quot;, [3, 4, 6]); // [&quot;3,4,6&quot;]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> 
actions = [&quot;EventEmitter&quot;, &quot;subscribe&quot;, &quot;emit&quot;, &quot;unsubscribe&quot;, &quot;emit&quot;], 
values = [[], [&quot;firstEvent&quot;, &quot;(...args) =&gt; args.join(&#39;,&#39;)&quot;], [&quot;firstEvent&quot;, [1,2,3]], [0], [&quot;firstEvent&quot;, [4,5,6]]]
<strong>Output:</strong> [[],[&quot;subscribed&quot;],[&quot;emitted&quot;,[&quot;1,2,3&quot;]],[&quot;unsubscribed&quot;,0],[&quot;emitted&quot;,[]]]
<strong>Explanation:</strong>
const emitter = new EventEmitter();
const sub = emitter.subscribe(&quot;firstEvent&quot;, (...args) =&gt; args.join(&#39;,&#39;));
emitter.emit(&quot;firstEvent&quot;, [1, 2, 3]); // [&quot;1,2,3&quot;]
sub.unsubscribe(); // undefined
emitter.emit(&quot;firstEvent&quot;, [4, 5, 6]); // [], there are no subscriptions
</pre>

<p><strong class="example">Example 4:</strong></p>

<pre>
<strong>Input:</strong> 
actions = [&quot;EventEmitter&quot;, &quot;subscribe&quot;, &quot;subscribe&quot;, &quot;unsubscribe&quot;, &quot;emit&quot;], 
values = [[], [&quot;firstEvent&quot;, &quot;x =&gt; x + 1&quot;], [&quot;firstEvent&quot;, &quot;x =&gt; x + 2&quot;], [0], [&quot;firstEvent&quot;, [5]]]
<strong>Output:</strong> [[],[&quot;subscribed&quot;],[&quot;emitted&quot;,[&quot;1,2,3&quot;]],[&quot;unsubscribed&quot;,0],[&quot;emitted&quot;,[7]]]
<strong>Explanation:</strong>
const emitter = new EventEmitter();
const sub1 = emitter.subscribe(&quot;firstEvent&quot;, x =&gt; x + 1);
const sub2 = emitter.subscribe(&quot;firstEvent&quot;, x =&gt; x + 2);
sub1.unsubscribe(); // undefined
emitter.emit(&quot;firstEvent&quot;, [5]); // [7]</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= actions.length &lt;= 10</code></li>
	<li><code>values.length === actions.length</code></li>
	<li>All test cases are valid, e.g. you don&#39;t need to handle scenarios when unsubscribing from a non-existing subscription.</li>
	<li>There are only 4 different actions: <code>EventEmitter</code>, <code>emit</code>, <code>subscribe</code>, and <code>unsubscribe</code>.</li>
	<li>The <code>EventEmitter</code> action doesn&#39;t take any arguments.</li>
	<li>The <code>emit</code>&nbsp;action takes between either 1 or&nbsp;2&nbsp;arguments. The first argument is the name of the event we want to emit, and the 2nd argument is passed to the callback functions.</li>
	<li>The <code>subscribe</code> action takes 2 arguments, where the first one is the event name and the second is the callback function.</li>
	<li>The <code>unsubscribe</code>&nbsp;action takes one argument, which is the 0-indexed order of the subscription made before.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

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
