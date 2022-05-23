# [817. 链表组件](https://leetcode.cn/problems/linked-list-components)

[English Version](/solution/0800-0899/0817.Linked%20List%20Components/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定链表头结点&nbsp;<code>head</code>，该链表上的每个结点都有一个 <strong>唯一的整型值</strong> 。同时给定列表&nbsp;<code>nums</code>，该列表是上述链表中整型值的一个子集。</p>

<p>返回列表&nbsp;<code>nums</code>&nbsp;中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表&nbsp;<code>nums</code>&nbsp;中）构成的集合。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0817.Linked%20List%20Components/images/lc-linkedlistcom1.jpg" /></p>

<pre>
<strong>输入:</strong> head = [0,1,2,3], nums = [0,1,3]
<strong>输出:</strong> 2
<strong>解释:</strong> 链表中,0 和 1 是相连接的，且 nums 中不包含 2，所以 [0, 1] 是 nums 的一个组件，同理 [3] 也是一个组件，故返回 2。</pre>

<p><strong>示例 2：</strong></p>

<p><strong>&nbsp;</strong><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0817.Linked%20List%20Components/images/lc-linkedlistcom2.jpg" /></p>

<pre>
<strong>输入:</strong> head = [0,1,2,3,4], nums = [0,3,1,4]
<strong>输出:</strong> 2
<strong>解释:</strong> 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表中节点数为<code>n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= Node.val &lt; n</code></li>
	<li><code>Node.val</code>&nbsp;中所有值 <strong>不同</strong></li>
	<li><code>1 &lt;= nums.length &lt;= n</code></li>
	<li><code>0 &lt;= nums[i] &lt; n</code></li>
	<li><code>nums</code> 中所有值 <strong>不同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

定义 pre 表示是否可加 1，初始为 true。

遍历链表各个结点：

-   若当前结点值在 nums 中，并且当前为可加 1 的状态，则 `res++`，并且将 pre 状态置为 false；
-   若当前结点值不在 nums 中，则将 pre 置为 true，表示可加 1。

最后返回 res 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def numComponents(self, head: ListNode, nums: List[int]) -> int:
        s = set(nums)
        res, pre = 0, True
        while head:
            if head.val in s:
                if pre:
                    res += 1
                    pre = False
            else:
                pre = True
            head = head.next
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            s.add(num);
        }
        int res = 0;
        boolean pre = true;
        while (head != null) {
            if (s.contains(head.val)) {
                if (pre) {
                    ++res;
                    pre = false;
                }
            } else {
                pre = true;
            }
            head = head.next;
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
