# [2804. Array Prototype ForEach 🔒](https://leetcode.com/problems/array-prototype-foreach)

[中文文档](/solution/2800-2899/2804.Array%20Prototype%20ForEach/README.md)

<!-- tags: -->

## Description

<p>Write your version of method&nbsp;<code>forEach</code>&nbsp;that enhances all arrays such that you can call the&nbsp;<code>array.forEach(callback, context)</code>&nbsp;method on any array and it will execute <code>callback</code> on each element of the array.&nbsp;Method&nbsp;<code>forEach</code> should not return anything.</p>

<p><code>callback</code> accepts the following arguments:</p>

<ul>
	<li><code>currentValue</code> -&nbsp;represents the current element being processed in the array. It is the value of the element in the current iteration.</li>
	<li><code>index</code> -&nbsp;represents the index of the current element being processed in the array.</li>
	<li><code>array</code> -&nbsp;represents the array itself, allowing access to the entire array within the callback function.</li>
</ul>

<p>The <code>context</code> is the object that should be passed as the function context parameter to the <code>callback</code> function, ensuring that the <code>this</code>&nbsp;keyword within the <code>callback</code> function refers to this <code>context</code> object.</p>

<p>Try to implement it without using the built-in array methods.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
arr = [1,2,3], 
callback = (val, i, arr) =&gt; arr[i] = val * 2, 
context = {&quot;context&quot;:true}
<strong>Output:</strong> [2,4,6]
<strong>Explanation:</strong> 
arr.forEach(callback, context)&nbsp; 
console.log(arr) // [2,4,6]

The callback is executed on each element of the array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
arr = [true, true, false, false], 
callback = (val, i, arr) =&gt; arr[i] = this, 
context = {&quot;context&quot;: false}
<strong>Output:</strong> [{&quot;context&quot;:false},{&quot;context&quot;:false},{&quot;context&quot;:false},{&quot;context&quot;:false}]
<strong>Explanation:</strong> 
arr.forEach(callback, context)&nbsp;
console.log(arr) // [{&quot;context&quot;:false},{&quot;context&quot;:false},{&quot;context&quot;:false},{&quot;context&quot;:false}]

The callback is executed on each element of the array with the right context.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> 
arr = [true, true, false, false], 
callback = (val, i, arr) =&gt; arr[i] = !val, 
context = {&quot;context&quot;: 5}
<strong>Output:</strong> [false,false,true,true]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>arr</code> is a valid JSON array</li>
	<li><code>context</code> is a valid JSON object</li>
	<li><code>fn</code> is a function</li>
	<li><code>0 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```ts
Array.prototype.forEach = function (callback: Function, context: any): void {
    for (let i = 0; i < this.length; ++i) {
        callback.call(context, this[i], i, this);
    }
};

/**
 *  const arr = [1,2,3];
 *  const callback = (val, i, arr) => arr[i] = val * 2;
 *  const context = {"context":true};
 *
 *  arr.forEach(callback, context)
 *
 *  console.log(arr) // [2,4,6]
 */
```

<!-- tabs:end -->

<!-- end -->
