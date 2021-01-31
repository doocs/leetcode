# [面试题 52. 两个链表的第一个公共节点](https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/)

## 题目描述

输入两个链表，找出它们的第一个公共节点。

如下面的两个链表：

![](./images/160_statement.png)

在节点 c1 开始相交。

**示例 1：**

![](./images/160_example_1.png)

```
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
```

**示例  2：**

![](./images/160_example_2.png)

```
输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
```

**示例  3：**

![](./images/160_example_3.png)

```
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
解释：这两个链表不相交，因此返回 null。
```

**注意：**

- 如果两个链表没有交点，返回 `null`。
- 在返回结果后，两个链表仍须保持原有的结构。
- 可假定整个链表结构中没有循环。
- 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。

## 解法

先求出两个链表的长度差 `len1-len2`，之后遍历链表，长的链表先走 `len1-len2` 步。

接着两个链表同时走，当出现相同的节点时，说明两个链表在此节点相交，返回此节点，否则返回 `null`。

<!-- tabs:start -->

### **Python3**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> ListNode:
        if headA is None or headB is None:
            return None
        len1 = len2 = 0
        p, q = headA, headB
        while p:
            p = p.next
            len1 += 1
        while q:
            q = q.next
            len2 += 1
        p, q = headA, headB
        if len1 > len2:
            p, q = q, p
        for _ in range(abs(len1 - len2)):
            q = q.next
        while p and q:
            if p == q:
                return p
            p = p.next
            q = q.next

```

### **Java**

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
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p = headA, q = headB;
        int len1 = len(p), len2 = len(q);
        if (len1 > len2) {
            ListNode t = headA;
            headA = headB;
            headB = t;
        }
        p = headA;
        q = headB;
        for (int i = 0; i < Math.abs(len1 - len2); ++i) {
            q = q.next;
        }
        while (p != null && q != null) {
            if (p == q) {
                return p;
            }
            p = p.next;
            q = q.next;
        }
        return null;
    }

    private int len(ListNode node) {
        int len = 0;
        while (node != null) {
            node = node.next;
            ++len;
        }
        return len;
    }
}
```

### **JavaScript**

```js
/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */

/**
 * @param {ListNode} headA
 * @param {ListNode} headB
 * @return {ListNode}
 */
var getIntersectionNode = function (headA, headB) {
  let h1 = headA;
  let h2 = headB;
  while (h1 !== h2) {
    h1 = h1 === null ? headB : h1.next;
    h2 = h2 === null ? headA : h2.next;
  }
  return h2;
};
```

### **C++**

```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */

class Solution {
public:
    ListNode *getIntersectionNode(ListNode *headA, ListNode *headB) {
        ListNode* a = headA;
        ListNode* b = headB;
        while (a != b) {
            /* 这个循环的思路是，a先从listA往后走，如果到最后，就接着从listB走；b正好相反。
               如果有交集的话，a和b会在分别进入listB和listA之后的循环中项目
               如果没有交集的话，则a和b会同时遍历完listA和listB后，值同时为nullptr */
            a = (a == nullptr) ? headB : a->next;
            b = (b == nullptr) ? headA : b->next;
        }

        return a;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
