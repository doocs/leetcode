# [769. 最多能完成排序的块](https://leetcode-cn.com/problems/max-chunks-to-make-sorted)

[English Version](/solution/0700-0799/0769.Max%20Chunks%20To%20Make%20Sorted/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>数组<code>arr</code>是<code>[0, 1, ..., arr.length - 1]</code>的一种排列，我们将这个数组分割成几个&ldquo;块&rdquo;，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。</p>

<p>我们最多能将数组分成多少块？</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> arr = [4,3,2,1,0]
<strong>输出:</strong> 1
<strong>解释:</strong>
将数组分成2块或者更多块，都无法得到所需的结果。
例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> arr = [1,0,2,3,4]
<strong>输出:</strong> 4
<strong>解释:</strong>
我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。
然而，分成 [1, 0], [2], [3], [4] 可以得到最多的块数。
</pre>

<p><strong>注意:</strong></p>

<ul>
	<li><code>arr</code> 的长度在 <code>[1, 10]</code> 之间。</li>
	<li><code>arr[i]</code>是 <code>[0, 1, ..., arr.length - 1]</code>的一种排列。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
