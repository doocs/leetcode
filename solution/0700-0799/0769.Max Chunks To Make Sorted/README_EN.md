# [769. Max Chunks To Make Sorted](https://leetcode.com/problems/max-chunks-to-make-sorted)

[中文文档](/solution/0700-0799/0769.Max%20Chunks%20To%20Make%20Sorted/README.md)

## Description

<p>Given an array <code>arr</code> that is a permutation of <code>[0, 1, ..., arr.length - 1]</code>, we split the array into some number of &quot;chunks&quot; (partitions), and individually sort each chunk.&nbsp; After concatenating them,&nbsp;the result equals the sorted array.</p>

<p>What is the most number of chunks we could have made?</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> arr = [4,3,2,1,0]

<strong>Output:</strong> 1

<strong>Explanation:</strong>

Splitting into two or more chunks will not return the required result.

For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn&#39;t sorted.

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> arr = [1,0,2,3,4]

<strong>Output:</strong> 4

<strong>Explanation:</strong>

We can split into two chunks, such as [1, 0], [2, 3, 4].

However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.

</pre>

<p><strong>Note:</strong></p>

<ul>
	<li><code>arr</code> will have length in range <code>[1, 10]</code>.</li>
	<li><code>arr[i]</code> will be a permutation of <code>[0, 1, ..., arr.length - 1]</code>.</li>

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
    const n = arr.length;
    let ans = 0;
    let max = 0;
    for (let i = 0; i < n; i++) {
        let cur = arr[i];
        max = Math.max(cur, max);
        if (max == i) {
            ans++;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
