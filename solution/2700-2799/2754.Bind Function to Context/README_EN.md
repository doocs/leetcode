---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2754.Bind%20Function%20to%20Context/README_EN.md
---

<!-- problem:start -->

# [2754. Bind Function to Context 🔒](https://leetcode.com/problems/bind-function-to-context)

[中文文档](/solution/2700-2799/2754.Bind%20Function%20to%20Context/README.md)

## Description

<!-- description:start -->

<p>Enhance all functions to have the&nbsp;<code>bindPolyfill</code>&nbsp;method. When&nbsp;<code>bindPolyfill</code>&nbsp;is called with a passed&nbsp;object <code>obj</code>, that object becomes the&nbsp;<code>this</code>&nbsp;context for the function.</p>

<p>For example, if you had the code:</p>

<pre>
function f() {
  console.log(&#39;My context is &#39; + this.ctx);
}
f();
</pre>

<p>The output would be <code>&quot;My context is undefined&quot;</code>. However, if you bound the function:</p>

<pre>
function f() {
  console.log(&#39;My context is &#39; + this.ctx);
}
const boundFunc = f.boundPolyfill({ &quot;ctx&quot;: &quot;My Object&quot; })
boundFunc();
</pre>

<p>The output should be&nbsp;<code>&quot;My context is My Object&quot;</code>.</p>

<p>You may assume that a single non-null object will be passed to the&nbsp;<code>bindPolyfill</code> method.</p>

<p>Please solve it without the built-in&nbsp;<code>Function.bind</code> method.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
fn = function f(multiplier) { 
&nbsp; return this.x * multiplier; 
}
obj = {&quot;x&quot;: 10}
inputs = [5]
<strong>Output:</strong> 50
<strong>Explanation:</strong>
const boundFunc = f.bindPolyfill({&quot;x&quot;: 10});
boundFunc(5); // 50
A multiplier of 5 is passed as a parameter.
The context is set to {&quot;x&quot;: 10}.
Multiplying those two numbers yields 50.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
fn = function speak() { 
&nbsp; return &quot;My name is &quot; + this.name; 
}
obj = {&quot;name&quot;: &quot;Kathy&quot;}
inputs = []
<strong>Output:</strong> &quot;My name is Kathy&quot;
<strong>Explanation:</strong>
const boundFunc = f.bindPolyfill({&quot;name&quot;: &quot;Kathy&quot;});
boundFunc(); // &quot;My name is Kathy&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>obj</code> is a non-null object</li>
	<li><code>0 &lt;= inputs.length &lt;= 100</code></li>
</ul>

<p>&nbsp;</p>
<strong>Can you solve it without using any built-in methods?</strong>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### TypeScript

```ts
type Fn = (...args) => any;

declare global {
    interface Function {
        bindPolyfill(obj: Record<any, any>): Fn;
    }
}

Function.prototype.bindPolyfill = function (obj) {
    return (...args) => {
        return this.call(obj, ...args);
    };
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
