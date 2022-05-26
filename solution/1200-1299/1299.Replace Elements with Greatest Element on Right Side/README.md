# [1299. 将每个元素替换为右侧最大元素](https://leetcode.cn/problems/replace-elements-with-greatest-element-on-right-side)

[English Version](/solution/1200-1299/1299.Replace%20Elements%20with%20Greatest%20Element%20on%20Right%20Side/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组 <code>arr</code> ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 <code>-1</code> 替换。</p>

<p>完成所有替换操作后，请你返回这个数组。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [17,18,5,4,6,1]
<strong>输出：</strong>[18,6,6,6,1,-1]
<strong>解释：</strong>
- 下标 0 的元素 --> 右侧最大元素是下标 1 的元素 (18)
- 下标 1 的元素 --> 右侧最大元素是下标 4 的元素 (6)
- 下标 2 的元素 --> 右侧最大元素是下标 4 的元素 (6)
- 下标 3 的元素 --> 右侧最大元素是下标 4 的元素 (6)
- 下标 4 的元素 --> 右侧最大元素是下标 5 的元素 (1)
- 下标 5 的元素 --> 右侧没有其他元素，替换为 -1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [400]
<strong>输出：</strong>[-1]
<strong>解释：</strong>下标<strong> </strong>0 的元素右侧没有其他元素。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= arr.length <= 10<sup>4</sup></code></li>
	<li><code>1 <= arr[i] <= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def replaceElements(self, arr: List[int]) -> List[int]:
        m = -1
        for i in range(len(arr) - 1, -1, -1):
            t = arr[i]
            arr[i] = m
            m = max(m, t)
        return arr
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] replaceElements(int[] arr) {
        for (int i = arr.length - 1, max = -1; i >= 0; --i) {
            int t = arr[i];
            arr[i] = max;
            max = Math.max(max, t);
        }
        return arr;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
