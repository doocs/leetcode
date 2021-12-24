# [768. Max Chunks To Make Sorted II](https://leetcode.com/problems/max-chunks-to-make-sorted-ii)

[中文文档](/solution/0700-0799/0768.Max%20Chunks%20To%20Make%20Sorted%20II/README.md)

## Description

<p><em>This question is the same as &quot;Max Chunks to Make Sorted&quot; except the integers of the given array are not necessarily distinct, the input array could be up to length <code>2000</code>, and the elements could be up to <code>10**8</code>.</em></p>

<hr />

<p>Given an array <code>arr</code> of integers (<strong>not necessarily distinct</strong>), we split the array into some number of &quot;chunks&quot; (partitions), and individually sort each chunk.&nbsp; After concatenating them,&nbsp;the result equals the sorted array.</p>

<p>What is the most number of chunks we could have made?</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> arr = [5,4,3,2,1]

<strong>Output:</strong> 1

<strong>Explanation:</strong>

Splitting into two or more chunks will not return the required result.

For example, splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2, 3], which isn&#39;t sorted.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> arr = [2,1,3,4,4]

<strong>Output:</strong> 4

<strong>Explanation:</strong>

We can split into two chunks, such as [2, 1], [3, 4, 4].

However, splitting into [2, 1], [3], [4], [4] is the highest number of chunks possible.

</pre>

<p><strong>Note:</strong></p>

<ul>
	<li><code>arr</code> will have length in range <code>[1, 2000]</code>.</li>
	<li><code>arr[i]</code> will be an integer in range <code>[0, 10**8]</code>.</li>
</ul>

<p>&nbsp;</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **TypeScript**

```ts
function maxChunksToSorted(arr: number[]): number {
    let stack = []; // 左进左出
    for (let num of arr) {
        if (stack.length && num < stack[0]) {
            let max = stack.shift();
            while (stack.length && num < stack[0]) {
                stack.shift();
            }
            stack.unshift(max);
        } else {
            stack.unshift(num);
        }
    }
    return stack.length;
}
```

### **...**

```

```

<!-- tabs:end -->
