# [2631. Group By](https://leetcode.com/problems/group-by)

[中文文档](/solution/2600-2699/2631.Group%20By/README.md)

## Description

<p>Write code that enhances all arrays such that you can call the&nbsp;<code>array.groupBy(fn)</code>&nbsp;method on any array and it will return a <strong>grouped</strong>&nbsp;version of the array.</p>

<p>A&nbsp;<strong>grouped</strong>&nbsp;array is an object where each&nbsp;key&nbsp;is&nbsp;the output of&nbsp;<code>fn(arr[i])</code>&nbsp;and each&nbsp;value is an array containing all items in the original array with that key.</p>

<p>The provided callback&nbsp;<code>fn</code>&nbsp;will accept an item in the array and return a string key.</p>

<p>The order of each value list should be the order the items&nbsp;appear in the array. Any order of keys is acceptable.</p>

<p>Please solve it without lodash&#39;s&nbsp;<code>_.groupBy</code> function.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> 
array = [
&nbsp; {&quot;id&quot;:&quot;1&quot;},
&nbsp; {&quot;id&quot;:&quot;1&quot;},
&nbsp; {&quot;id&quot;:&quot;2&quot;}
], 
fn = function (item) { 
&nbsp; return item.id; 
}
<strong>Output:</strong> 
{ 
&nbsp; &quot;1&quot;: [{&quot;id&quot;: &quot;1&quot;}, {&quot;id&quot;: &quot;1&quot;}], &nbsp; 
&nbsp; &quot;2&quot;: [{&quot;id&quot;: &quot;2&quot;}] 
}
<strong>Explanation:</strong>
Output is from array.groupBy(fn).
The selector function gets the &quot;id&quot; out of each item in the array.
There are two objects with an &quot;id&quot; of 1. Both of those objects are put in the first array.
There is one object with an &quot;id&quot; of 2. That object is put in the second array.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> 
array = [
&nbsp; [1, 2, 3],
&nbsp; [1, 3, 5],
&nbsp; [1, 5, 9]
]
fn = function (list) { 
&nbsp; return String(list[0]); 
}
<strong>Output:</strong> 
{ 
&nbsp; &quot;1&quot;: [[1, 2, 3], [1, 3, 5], [1, 5, 9]] 
}
<strong>Explanation:</strong>
The array can be of any type. In this case, the selector function defines the key as being the first element in the array. 
All the arrays have 1 as their first element so they are grouped together.
{
  &quot;1&quot;: [[1, 2, 3], [1, 3, 5], [1, 5, 9]]
}
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> 
array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
fn = function (n) { 
&nbsp; return String(n &gt; 5);
}
<strong>Output:</strong>
{
&nbsp; &quot;true&quot;: [6, 7, 8, 9, 10],
&nbsp; &quot;false&quot;: [1, 2, 3, 4, 5]
}
<strong>Explanation:</strong>
The selector function splits the array by whether each number is greater than 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= array.length &lt;= 10<sup>5</sup></code></li>
	<li><code>fn returns a string</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **TypeScript**

```ts
declare global {
    interface Array<T> {
        groupBy(fn: (item: T) => string): Record<string, T[]>;
    }
}

Array.prototype.groupBy = function (fn) {
    return this.reduce((acc, item) => {
        const key = fn(item);
        if (acc[key]) {
            acc[key].push(item);
        } else {
            acc[key] = [item];
        }
        return acc;
    }, {});
};

/**
 * [1,2,3].groupBy(String) // {"1":[1],"2":[2],"3":[3]}
 */
```

### **...**

```

```

<!-- tabs:end -->
