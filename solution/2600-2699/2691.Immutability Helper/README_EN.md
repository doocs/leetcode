# [2691. Immutability Helper 🔒](https://leetcode.com/problems/immutability-helper)

[中文文档](/solution/2600-2699/2691.Immutability%20Helper/README.md)

<!-- tags: -->

<!-- difficulty:Hard -->

## Description

<p>Creating clones of immutable objects with minor alterations can be a tedious process. Write a class&nbsp;<code>ImmutableHelper</code>&nbsp;that serves as a tool to help with this requirement. The constructor accepts an immutable object&nbsp;<code>obj</code>&nbsp;which will be a JSON object or array.</p>

<p>The class has a single method&nbsp;<code>produce</code>&nbsp;which&nbsp;accepts a&nbsp;function&nbsp;<code>mutator</code>. The function returns a new object which is similar to the original except it has&nbsp;those mutations applied.</p>

<p><code>mutator</code>&nbsp;accepts a&nbsp;<strong>proxied</strong>&nbsp;version of&nbsp;<code>obj</code>. A user of this function can (appear to) mutate this object, but the original object&nbsp;<code>obj</code>&nbsp;should&nbsp;not actually be&nbsp;effected.</p>

<p>For example, a user could write code like this:</p>

<pre>
const originalObj = {&quot;x&quot;: 5};
const helper = new ImmutableHelper(originalObj);
const newObj = helper.produce((proxy) =&gt; {
  proxy.x = proxy.x + 1;
});
console.log(originalObj); // {&quot;x&quot;: 5}
console.log(newObj); // {&quot;x&quot;: 6}</pre>

<p>Properties of the&nbsp;<code>mutator</code>&nbsp;function:</p>

<ul>
	<li>It will always return <code>undefined</code>.</li>
	<li>It will never access keys that don&#39;t exist.</li>
	<li>It will never delete keys (<code>delete obj.key</code>)</li>
	<li>It will never call methods on a proxied object (<code>push</code>, <code>shift</code>, etc).</li>
	<li>It will never set keys to objects (<code>proxy.x = {}</code>)</li>
</ul>

<p><strong>Note on how the solution will be tested:</strong>&nbsp;the solution validator will only analyze&nbsp;differences between what was returned and the original&nbsp;<code>obj</code>. Doing a full comparison would be too computationally expensive. Also, any mutations to the original object will result in a wrong answer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
obj = {&quot;val&quot;: 10}, 
mutators = [
&nbsp; proxy =&gt; { proxy.val += 1; },
&nbsp; proxy =&gt; { proxy.val -= 1; }
]
<strong>Output:</strong> 
[
  {&quot;val&quot;: 11},
&nbsp; {&quot;val&quot;: 9}
]
<strong>Explanation:</strong>
const helper = new ImmutableHelper({val: 10});
helper.produce(proxy =&gt; { proxy.val += 1; }); // { &quot;val&quot;: 11 }
helper.produce(proxy =&gt; { proxy.val -= 1; }); // { &quot;val&quot;: 9 }
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
obj = {&quot;arr&quot;: [1, 2, 3]} 
mutators = [
&nbsp;proxy =&gt; { 
&nbsp;  proxy.arr[0] = 5; 
&nbsp;  proxy.newVal = proxy.arr[0] + proxy.arr[1];
 }
]
<strong>Output:</strong> 
[
  {&quot;arr&quot;: [5, 2, 3], &quot;newVal&quot;: 7 } 
]
<strong>Explanation: </strong>Two edits were made to the original array. The first element in the array was to set 5. Then a new key was added with a value of 7.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> 
obj = {&quot;obj&quot;: {&quot;val&quot;: {&quot;x&quot;: 10, &quot;y&quot;: 20}}}
mutators = [
&nbsp; proxy =&gt; { 
&nbsp;   let data = proxy.obj.val; 
&nbsp;   let temp = data.x; 
&nbsp;   data.x = data.y; 
&nbsp;   data.y = temp; 
&nbsp; }
]
<strong>Output:</strong> 
[
  {&quot;obj&quot;: {&quot;val&quot;: {&quot;x&quot;: 20, &quot;y&quot;: 10}}}
]
<strong>Explanation:</strong> The values of &quot;x&quot; and &quot;y&quot; were swapped.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= JSON.stringify(obj).length &lt;= 4 * 10<sup>5</sup></code></li>
	<li><code>mutators</code> is an array of functions</li>
	<li><code><font face="monospace">total calls to produce() &lt; 10<sup>5</sup></font></code></li>
</ul>

## Solutions

<!-- end -->
