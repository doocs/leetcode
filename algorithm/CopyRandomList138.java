package com.funian.algorithm.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 复制带随机指针的链表（LeetCode 138）
 *
 * 时间复杂度：O(n)
 * - 方法1（哈希表）：需要遍历链表两次，O(2n) = O(n)
 * - 方法2（原地复制）：需要遍历链表三次，O(3n) = O(n)
 *
 * 空间复杂度：
 * - 方法1（哈希表）：O(n)
 *   需要哈希表存储所有节点的映射关系
 * - 方法2（原地复制）：O(1)
 *   只使用常数个额外变量，不使用额外的数据结构
 */
public class CopyRandomList138 {

    /**
     * 节点定义：带随机指针的链表节点
     */
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 方法1：使用哈希表复制链表
     *
     * 算法思路：
     * 1. 第一次遍历：创建所有节点的副本，并建立原节点到新节点的映射
     * 2. 第二次遍历：根据映射关系设置新节点的next和random指针
     *
     * 执行过程分析（以链表 A(1)->B(2)->C(3)，其中A.random=C, B.random=A 为例）：
     *
     * 第一次遍历：创建节点副本
     * 原链表：A(1)->B(2)->C(3)
     * 哈希表：{A->A', B->B', C->C'}
     *
     * 第二次遍历：设置指针关系
     * A'.next = 哈希表[A.next] = 哈希表[B] = B'
     * A'.random = 哈希表[A.random] = 哈希表[C] = C'
     * B'.next = 哈希表[B.next] = 哈希表[C] = C'
     * B'.random = 哈希表[B.random] = 哈希表[A] = A'
     * C'.next = 哈希表[C.next] = 哈希表[null] = null
     * C'.random = 哈希表[C.random] = 哈希表[null] = null
     *
     * 结果链表：A'(1)->B'(2)->C'(3)，其中A'.random=C', B'.random=A'
     *
     * 时间复杂度分析：
     * - 第一次遍历创建节点：O(n)，其中n为链表长度
     * - 第二次遍历设置指针：O(n)
     * - 哈希表操作：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 哈希表存储映射关系：O(n)
     * - 新链表节点：O(n)
     * - 总空间复杂度：O(n)
     *
     * @param head 原链表的头节点
     * @return 复制后的链表头节点
     */
    public Node copyRandomListWithHashMap(Node head) {
        if (head == null) return null;

        // 创建哈希表存储原节点到新节点的映射
        Map<Node, Node> map = new HashMap<>();

        // 第一次遍历：创建所有节点的副本
        Node current = head;
        while (current != null) {
            map.put(current, new Node(current.val));
            current = current.next;
        }

        // 第二次遍历：设置新节点的next和random指针
        current = head;
        while (current != null) {
            // 设置next指针
            map.get(current).next = map.get(current.next);

            // 设置random指针
            map.get(current).random = map.get(current.random);

            current = current.next;
        }

        // 返回复制链表的头节点
        return map.get(head);
    }

    /**
     * 方法2：原地复制法（三步法）
     *
     * 算法思路：
     * 1. 第一遍遍历：将每个节点的副本插入到原节点后面
     * 2. 第二遍遍历：设置副本节点的random指针
     * 3. 第三遍遍历：将副本节点从原链表中分离出来
     *
     * 执行过程分析（以链表 A(1)->B(2)->C(3)，其中A.random=C, B.random=A 为例）：
     *
     * 初始状态：
     * A(1) -> B(2) -> C(3) -> null
     *  |       |       |
     * random   |      random
     *  --------+--------
     *          |
     *        random
     *
     * 第一遍遍历后（复制节点并插入）：
     * A(1) -> A'(1) -> B(2) -> B'(2) -> C(3) -> C'(3) -> null
     *
     * 第二遍遍历后（设置random指针）：
     * 对于A'：A'.random = A.random.next = C' （因为C'紧跟在C后面）
     * 对于B'：B'.random = B.random.next = A' （因为A'紧跟在A后面）
     * 对于C'：C'.random = C.random.next = null
     *
     * 第三遍遍历后（分离链表）：
     * 原链表：A(1) -> B(2) -> C(3) -> null
     * 新链表：A'(1) -> B'(2) -> C'(3) -> null
     * 其中：A'.random = C', B'.random = A'
     *
     * 时间复杂度分析：
     * - 第一遍遍历复制节点：O(n)
     * - 第二遍遍历设置random指针：O(n)
     * - 第三遍遍历分离链表：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     * - 新链表节点：O(n)
     * - 总空间复杂度：O(1)（不考虑输出空间）
     *
     * @param head 原链表的头节点
     * @return 复制后的链表头节点
     */
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // 第一遍遍历：复制每个节点并插入到原节点后面
        Node current = head;
        while (current != null) {
            Node newNode = new Node(current.val);
            newNode.next = current.next;
            current.next = newNode;
            current = newNode.next;
        }

        // 第二遍遍历：设置新节点的 random 指针
        current = head;
        while (current != null) {
            // 如果原节点的random不为空，则新节点的random指向原节点random的下一个节点
            // （因为原节点random的下一个节点就是原节点random的副本）
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }

        // 第三遍遍历：将新节点从原节点中分离出来
        Node newHead = head.next;
        current = head;
        while (current != null) {
            Node newNode = current.next;

            // 恢复原链表的next指针
            current.next = newNode.next;

            // 设置新链表的next指针
            if (newNode.next != null) {
                newNode.next = newNode.next.next;
            }

            current = current.next;
        }

        // 返回新链表的头节点
        return newHead;
    }

    /**
     * 辅助方法：创建带随机指针的链表（用于测试）
     */
    public Node createList(int[] values, int[] randomIndices) {
        if (values.length == 0) return null;

        // 创建所有节点
        Node[] nodes = new Node[values.length];
        for (int i = 0; i < values.length; i++) {
            nodes[i] = new Node(values[i]);
        }

        // 设置next指针
        for (int i = 0; i < values.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }

        // 设置random指针
        for (int i = 0; i < values.length; i++) {
            if (randomIndices[i] != -1) {
                nodes[i].random = nodes[randomIndices[i]];
            }
        }

        // 返回链表头节点
        return nodes[0];
    }

    /**
     * 辅助方法：打印链表（用于测试）
     */
    public void printList(Node head) {
        // 先建立节点到索引的映射
        Map<Node, Integer> nodeToIndex = new HashMap<>();
        Node current = head;
        int index = 0;
        while (current != null) {
            nodeToIndex.put(current, index++);
            current = current.next;
        }

        // 打印节点信息
        current = head;
        index = 0;
        while (current != null) {
            // randomIndex random指针指向节点的索引
            int randomIndex = (current.random != null) ? nodeToIndex.get(current.random) : -1;
            System.out.println("Node " + index + ": val=" + current.val +
                             ", random->Node " + (randomIndex == -1 ? "null" : randomIndex));
            current = current.next;
            index++;
        }
    }
}
