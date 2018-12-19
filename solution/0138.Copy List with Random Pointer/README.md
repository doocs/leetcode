## 复制带随机指针的链表

### 题目描述

给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。

![random-list.png](/img/random-list.png)

要求返回这个链表的深度拷贝。 

### 解法
- 第一步，在每个节点的后面插入复制的节点；
![random-list-step1.png](/img/random-list-step1.png)

- 第二步，对复制节点的 random 链接进行赋值；
![random-list-step2.png](/img/random-list-step2.png)

- 第三步，分离两个链表。
![random-list-step3.png](/img/random-list-step3.png)

```java
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        
        // step1
        RandomListNode cur = head;
        while (cur != null) {
            RandomListNode node = new RandomListNode(cur.label);
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
        }
        
        // step2
        cur = head;
        while (cur != null) {
            RandomListNode clone = cur.next;
            if (cur.random != null) {
                clone.random = cur.random.next;   
            }
            cur = clone.next;
        }
        
        // step3
        cur = head;
        RandomListNode cloneHead = head.next;
        while (cur.next != null) {
            RandomListNode clone = cur.next;
            cur.next = clone.next;
            cur = clone;
        }
        
        return cloneHead;
    }
}
```