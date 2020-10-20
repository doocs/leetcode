# [面试题 16.21. 交换和](https://leetcode-cn.com/problems/sum-swap-lcci)

[English Version](/lcci/16.21.Sum%20Swap/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。</p>

<p>返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。若有多个答案，返回任意一个均可。若无满足条件的数值，返回空数组。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]
<strong>输出:</strong> [1, 3]
</pre>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> array1 = <code>[1, 2, 3], array2 = [4, 5, 6]</code>
<strong>输出: </strong>[]</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= array1.length, array2.length &lt;= 100000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先计算两个数组的差值 `diff`，若 `diff` 为奇数，则说明无满足条件的数值，返回空数组。否则，将 `array2` 转为 `set`。然后遍历 `array1` 中的每个数 `e`，若值 `e - diff` 在 `set` 中，则说明找到满足条件的数值对。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findSwapValues(self, array1: List[int], array2: List[int]) -> List[int]:
        diff = sum(array1) - sum(array2)
        if diff & 1: return []
        diff >>= 1
        s = set(array2)
        for e in array1:
            if (e - diff) in s: return [e, e - diff]
        return []
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] findSwapValues(int[] array1, int[] array2) {
        int diff = sum(array1) - sum(array2);
        if ((diff & 1) == 1) {
            return new int[]{};
        }
        diff >>= 1;
        Set<Integer> s = Arrays.stream(array2).boxed().collect(Collectors.toSet());
        for (int e : array1) {
            if (s.contains((e - diff))) {
                return new int[]{e, e - diff};
            }
        }
        return new int[]{};
    }

    private int sum(int[] array) {
        int res = 0;
        for (int e : array) {
            res += e;
        }
        return res;
    }
}s
```

### **...**

```

```

<!-- tabs:end -->
