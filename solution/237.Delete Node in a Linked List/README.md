## 删除链表中的节点
### 题目描述

请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。

现有一个链表 -- head = [4,5,1,9]，它可以表示为:
```
    4 -> 5 -> 1 -> 9
```    

示例 1:
```
输入: head = [4,5,1,9], node = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
```

示例 2:
```
输入: head = [4,5,1,9], node = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
```

说明:

- 链表至少包含两个节点。
- 链表中所有节点的值都是唯一的。
- 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
- 不要从你的函数中返回任何结果。

### 解法
刚开始看到这道题，有点懵，明明题目给出的输入是 head 跟 node，为什么 solution 中只有 node，后来才明白，只提供 node 依然可以解决此题。只要把下个结点的 值 & next 赋给当前 node，然后删除下个结点，就可以搞定。好题！

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
    public void deleteNode(ListNode node) {
        // 保存下一个结点
        ListNode tmp = node.next;
        
        // 将下个结点的值赋给当前要删除的结点
        node.val = node.next.val;
        node.next = node.next.next;
        
        // tmp 置为空，让 jvm 进行垃圾回收
        tmp = null;
        
    }
}
```