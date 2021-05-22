# [350. 两个数组的交集 II](https://leetcode-cn.com/problems/intersection-of-two-arrays-ii)

[English Version](/solution/0300-0399/0350.Intersection%20of%20Two%20Arrays%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个数组，编写一个函数来计算它们的交集。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums1 = [1,2,2,1], nums2 = [2,2]
<strong>输出：</strong>[2,2]
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入：</strong>nums1 = [4,9,5], nums2 = [9,4,9,8,4]
<strong>输出：</strong>[4,9]</pre>

<p>&nbsp;</p>

<p><strong>说明：</strong></p>

<ul>
	<li>输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。</li>
	<li>我们可以不考虑输出结果的顺序。</li>
</ul>

<p><strong><strong>进阶</strong>：</strong></p>

<ul>
	<li>如果给定的数组已经排好序呢？你将如何优化你的算法？</li>
	<li>如果&nbsp;<em>nums1&nbsp;</em>的大小比&nbsp;<em>nums2&nbsp;</em>小很多，哪种方法更优？</li>
	<li>如果&nbsp;<em>nums2&nbsp;</em>的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？</li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

“哈希表”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def intersect(self, nums1: List[int], nums2: List[int]) -> List[int]:
        counter = collections.Counter(nums1)
        res = []
        for num in nums2:
            if counter[num] > 0:
                res.append(num)
                counter[num] -= 1
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums1) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        List<Integer> intersection = new ArrayList<>();
        for (int num : nums2) {
            int val = counter.getOrDefault(num, 0);
            if (val > 0) {
                intersection.add(num);
                counter.put(num, val - 1);
            }
        }
        int i = 0;
        int[] res = new int[intersection.size()];
        for (int num : intersection) {
            res[i++] = num;
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var intersect = function(nums1, nums2) {
    const counter = {};
    for (const num of nums1) {
        counter[num] = (counter[num] || 0) + 1;
    }
    let res = [];
    for (const num of nums2) {
        if (counter[num] > 0) {
            res.push(num);
            counter[num] -= 1;
        }
    }
    return res;
};
```

### **...**

```

```

<!-- tabs:end -->
