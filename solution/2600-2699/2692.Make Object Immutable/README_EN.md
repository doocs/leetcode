# [2692. Make Object Immutable 🔒](https://leetcode.com/problems/make-object-immutable)

[中文文档](/solution/2600-2699/2692.Make%20Object%20Immutable/README.md)

<!-- tags: -->

## Description

<p>Write a function that takes an object&nbsp;<code>obj</code> and returns a new&nbsp;<strong>immutable</strong>&nbsp;version of this object.</p>

<p>An&nbsp;<strong>immutable&nbsp;</strong>object is an object that can&#39;t be altered and will throw an error if any attempt is made to alter it.</p>

<p>There are three types of error messages that can be produced from this new object.</p>

<ul>
	<li>Attempting to modify a key on the object will result in this&nbsp;error message: <code>`Error Modifying: ${key}`</code>.</li>
	<li>Attempting to modify an index on an array will result in this error message: <code>`Error Modifying&nbsp;Index: ${index}`</code>.</li>
	<li>Attempting to call a method that mutates an array will result in this error message: <code>`Error Calling Method: ${methodName}`</code>. You may assume the only methods that can mutate&nbsp;an array are&nbsp;<code>[&#39;pop&#39;, &#39;push&#39;, &#39;shift&#39;, &#39;unshift&#39;, &#39;splice&#39;, &#39;sort&#39;, &#39;reverse&#39;]</code>.</li>
</ul>

<p><code>obj</code>&nbsp;is a valid JSON object or array, meaning it is the output of <code>JSON.parse()</code>.</p>

<p>Note that a string literal should be thrown, not an&nbsp;<code>Error</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
obj = {
&nbsp; &quot;x&quot;: 5
}
fn = (obj) =&gt; { 
&nbsp; obj.x = 5;
&nbsp; return obj.x;
}
<strong>Output:</strong> {&quot;value&quot;: null, &quot;error&quot;: &quot;Error Modifying:&nbsp;x&quot;}
<strong>Explanation: </strong>Attempting to modify a key on an object resuts in a thrown error. Note that it doesn&#39;t matter that the value was set to the same value as it was before.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
obj = [1, 2, 3]
fn = (arr) =&gt; { 
&nbsp; arr[1] = {}; 
&nbsp; return arr[2]; 
}
<strong>Output:</strong> {&quot;value&quot;: null, &quot;error&quot;: &quot;Error Modifying&nbsp;Index: 1&quot;}
<strong>Explanation: </strong>Attempting to modify an array results in a thrown error.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> 
obj = {
&nbsp; &quot;arr&quot;: [1, 2, 3]
}
fn = (obj) =&gt; { 
&nbsp; obj.arr.push(4);
&nbsp; return 42;
}
<strong>Output:</strong> { &quot;value&quot;: null, &quot;error&quot;: &quot;Error Calling Method: push&quot;}
<strong>Explanation: </strong>Calling a method that can result in a mutation results in a thrown error.
</pre>

<p><strong class="example">Example 4:</strong></p>

<pre>
<strong>Input:</strong> 
obj = {
&nbsp; &quot;x&quot;: 2,
&nbsp; &quot;y&quot;: 2
}
fn = (obj) =&gt; { 
&nbsp; return Object.keys(obj);
}
<strong>Output:</strong> {&quot;value&quot;: [&quot;x&quot;, &quot;y&quot;], &quot;error&quot;: null}
<strong>Explanation: </strong>No mutations were attempted so the function returns as normal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>obj</code>&nbsp;is a valid JSON object or array</li>
	<li><code>2 &lt;= JSON.stringify(obj).length &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1

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
