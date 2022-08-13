# [1171. 从链表中删去总和值为零的连续节点](https://leetcode.cn/problems/remove-zero-sum-consecutive-nodes-from-linked-list)

[English Version](/solution/1100-1199/1171.Remove%20Zero%20Sum%20Consecutive%20Nodes%20from%20Linked%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个链表的头节点&nbsp;<code>head</code>，请你编写代码，反复删去链表中由 <strong>总和</strong>&nbsp;值为 <code>0</code> 的连续节点组成的序列，直到不存在这样的序列为止。</p>

<p>删除完毕后，请你返回最终结果链表的头节点。</p>

<p>&nbsp;</p>

<p>你可以返回任何满足题目要求的答案。</p>

<p>（注意，下面示例中的所有序列，都是对&nbsp;<code>ListNode</code>&nbsp;对象序列化的表示。）</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>head = [1,2,-3,3,1]
<strong>输出：</strong>[3,1]
<strong>提示：</strong>答案 [1,2,1] 也是正确的。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>head = [1,2,3,-3,4]
<strong>输出：</strong>[1,2,4]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>head = [1,2,3,-3,-2]
<strong>输出：</strong>[1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>给你的链表中可能有 <code>1</code> 到&nbsp;<code>1000</code>&nbsp;个节点。</li>
	<li>对于链表中的每个节点，节点的值：<code>-1000 &lt;= node.val &lt;= 1000</code>.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“前缀和 + 哈希表”实现。

若链表节点的两个前缀和相等，说明两个前缀和之间的连续节点序列的和为 0，那么可以消去这部分连续节点。

第一次遍历链表，用哈希表 `pre_sum_node` 记录前缀和以及对应的链表节点，同一前缀和 s，**后者的链表节点覆盖前者**。

第二次遍历链表，若当前节点 cur 的前缀和 s 在 `pre_sum_node` 出现，说明 cur 与 pre_sum_node[s] 之间的所有节点和为 0，直接修改 cur 的指向，`cur.next = pre_sum_node[s].next`，就删去了这部分和为 0 的连续节点。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def removeZeroSumSublists(self, head: ListNode) -> ListNode:
        dummy = ListNode(0)
        dummy.next = head
        s, cur = 0, dummy
        pre_sum_node = {}
        while cur:
            s += cur.val
            pre_sum_node[s] = cur
            cur = cur.next
        s, cur = 0, dummy
        while cur:
            s += cur.val
            cur.next = pre_sum_node[s].next
            cur = cur.next
        return dummy.next
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        Map<Integer, ListNode> preSumNode = new HashMap<>();
        int s = 0;
        for (ListNode cur = dummy; cur != null; cur = cur.next) {
            s += cur.val;
            preSumNode.put(s, cur);
        }
        s = 0;
        for (ListNode cur = dummy; cur != null; cur = cur.next) {
            s += cur.val;
            cur.next = preSumNode.get(s).next;
        }
        return dummy.next;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
