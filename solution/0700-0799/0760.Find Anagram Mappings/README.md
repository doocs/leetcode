# [760. 找出变位映射](https://leetcode.cn/problems/find-anagram-mappings)

[English Version](/solution/0700-0799/0760.Find%20Anagram%20Mappings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个列表 <code>A</code>and <code>B</code>，并且 <code>B</code> 是 <code>A</code> 的变位（即&nbsp;<code>B</code> 是由&nbsp;<code>A</code> 中的元素随机排列后组成的新列表）。</p>

<p>我们希望找出一个从 <code>A</code> 到 <code>B</code>&nbsp;的索引映射 <code>P</code> 。一个映射 <code>P[i] = j</code>&nbsp;指的是列表&nbsp;<code>A</code> 中的第 <code>i</code> 个元素出现于列表&nbsp;<code>B</code> 中的第 <code>j</code> 个元素上。</p>

<p>列表 <code>A</code> 和 <code>B</code> 可能出现重复元素。如果有多于一种答案，输出任意一种。</p>

<p>例如，给定</p>

<pre>A = [12, 28, 46, 32, 50]
B = [50, 12, 32, 46, 28]
</pre>

<p>&nbsp;</p>

<p>需要返回</p>

<pre>[1, 4, 3, 2, 0]
</pre>

<p><code>P[0] = 1</code>&nbsp;，因为 <code>A</code> 中的第 <code>0</code> 个元素出现于 <code>B[1]</code>，而且 <code>P[1] = 4</code> 因为 <code>A</code> 中第 <code>1</code> 个元素出现于 <code>B[4]</code>，以此类推。</p>

<p>&nbsp;</p>

<p><strong>注：</strong></p>

<ol>
	<li><code>A, B</code>&nbsp;有相同的长度，范围为&nbsp;<code>[1, 100]</code>。</li>
	<li><code>A[i], B[i]</code> 都是范围在&nbsp;<code>[0, 10^5]</code> 的整数。</li>
</ol>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def anagramMappings(self, nums1: List[int], nums2: List[int]) -> List[int]:
        mapper = defaultdict(set)
        for i, num in enumerate(nums2):
            mapper[num].add(i)
        return [mapper[num].pop() for num in nums1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] anagramMappings(int[] nums1, int[] nums2) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums2.length; ++i) {
            map.computeIfAbsent(nums2[i], k -> new HashSet<>()).add(i);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; ++i) {
            int idx = map.get(nums1[i]).iterator().next();
            res[i] = idx;
            map.get(nums1[i]).remove(idx);
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
