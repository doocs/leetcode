# [面试题 02.07. 链表相交](https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定两个（单向）链表，判定它们是否相交并返回交点。请注意相交的定义基于节点的引用，而不是基于节点的值。换句话说，如果一个链表的第k个节点与另一个链表的第j个节点是同一节点（引用完全相同），则这两个链表相交。</p><br><p><strong>示例 1：</strong><pre><strong>输入：</strong>intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3<br><strong>输出：</strong>Reference of the node with value = 8<br><strong>输入解释：</strong>相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。</pre></p><br><p><strong>示例 2：</strong><pre><strong>输入：</strong>intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1<br><strong>输出：</strong>Reference of the node with value = 2<br><strong>输入解释：</strong>相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。</pre></p><br><p><strong>示例 3：</strong><pre><strong>输入：</strong>intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2<br><strong>输出：</strong>null<br><strong>输入解释：</strong>从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。<br><strong>解释：</strong>这两个链表不相交，因此返回 null。</pre></p><br><p><strong>注意：</strong></p><ul><li>如果两个链表没有交点，返回 <code>null</code> 。</li><li>在返回结果后，两个链表仍须保持原有的结构。</li><li>可假定整个链表结构中没有循环。</li><li>程序尽量满足 O(<em>n</em>) 时间复杂度，且仅用 O(<em>1</em>) 内存。</li></ul>

## 解法
<!-- 这里可写通用的实现逻辑 -->
先求两链表的长度差 `differ`，接着较长的链表先走 `differ` 步，之后两链表同时走，若相遇，则说明相交。

### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> ListNode:
        len1, len2 = self._length(headA), self._length(headB)
        if len1 < len2:
            headA, headB = headB, headA
        differ = abs(len1 - len2)
        for _ in range(differ):
            headA = headA.next
        while headA:
            if headA == headB:
                return headA
            headA = headA.next
            headB = headB.next
        return None

    def _length(self, node: ListNode) -> int:
        n = 0
        while node:
            node = node.next
            n += 1
        return n
```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1 = len(headA), len2 = len(headB);
        int differ = Math.abs(len1 - len2);
        if (len1 < len2) {
            ListNode t = headA;
            headA = headB;
            headB = t;
        }
        while (differ-- > 0) {
            headA = headA.next;
        }
        while (headA != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    private int len(ListNode node) {
        int n = 0;
        while (node != null) {
            node = node.next;
            ++n;
        }
        return n;
    }
}
```

### ...
```

```
