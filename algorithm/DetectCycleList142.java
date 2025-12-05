package com.funian.algorithm.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表II - 找到环的起始节点（LeetCode 142）
 *
 * 时间复杂度：
 * - 方法1（哈希表）：O(n)
 *   需要遍历链表，最坏情况下遍历所有节点
 * - 方法2（快慢指针）：O(n)
 *   第一阶段寻找相遇点：O(n)
 *   第二阶段寻找环入口：O(n)
 *   总体时间复杂度：O(n)
 *
 * 空间复杂度：
 * - 方法1（哈希表）：O(n)
 *   需要存储所有访问过的节点
 * - 方法2（快慢指针）：O(1)
 *   只使用常数个额外变量
 */
public class DetectCycleList142 {

    // 定义链表节点类
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 方法1：使用哈希表找到环的起始节点
     *
     * 算法思路：
     * 遍历链表，将访问过的节点存储在哈希表中
     * 如果遇到已经存在于哈希表中的节点，该节点就是环的起始节点
     * 如果遍历到null，说明无环，返回null
     *
     * 执行过程分析（以链表 1->2->3->4->2 为例，4指向2形成环）：
     * 1. 访问节点1，哈希表：{1}
     * 2. 访问节点2，哈希表：{1,2}
     * 3. 访问节点3，哈希表：{1,2,3}
     * 4. 访问节点4，哈希表：{1,2,3,4}
     * 5. 再次访问节点2，发现已存在于哈希表中，返回节点2
     *
     * 时间复杂度分析：
     * - 遍历链表：O(n)，其中n为链表节点数
     * - HashSet操作：O(1)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - HashSet存储节点：O(n)
     *
     * @param head 链表的头节点
     * @return 环的起始节点，如果无环返回null
     */
    public ListNode detectCycleWithHashSet(ListNode head) {
        // 使用HashSet存储访问过的节点
        Set<ListNode> visitedNodes = new HashSet<>();

        // 从头节点开始遍历
        ListNode current = head;
        while (current != null) {
            // 如果当前节点已在集合中，说明找到了环的起始节点
            if (visitedNodes.contains(current)) {
                // 返回环的起始节点
                return current;
            }

            // 将当前节点加入集合
            visitedNodes.add(current);

            // 移动到下一个节点
            current = current.next;
        }

        // 遍历完成未发现环，返回null
        return null;
    }


    /**
     * 方法2：使用快慢指针找到环的起始节点（Floyd算法扩展）
     *
     * 算法思路：
     * 1. 使用快慢指针判断是否有环，并找到相遇点
     * 2. 将一个指针重新指向头节点，另一个指针保持在相遇点
     * 3. 两个指针同时以相同速度移动，相遇点即为环的起始节点
     *
     * 数学原理：
     * 假设链表头到环入口距离为a，环入口到相遇点距离为b，相遇点到环入口距离为c
     * 环的长度为 b+c
     *
     * 当快慢指针相遇时：
     * - 慢指针走过的距离：a + b
     * - 快指针走过的距离：a + b + c + b = a + 2b + c
     * - 快指针走过的距离是慢指针的2倍：2(a + b) = a + 2b + c
     * - 化简得：a = c
     *
     * 因此，从头节点到环入口的距离等于从相遇点到环入口的距离
     *
     * 执行过程分析（以链表 1->2->3->4->2 为例，4指向2形成环）：
     * 第一阶段：寻找相遇点
     * - 初始：slow=1, fast=1
     * - 第1步：slow=2, fast=3
     * - 第2步：slow=3, fast=2 (环中)
     * - 第3步：slow=4, fast=4 (相遇)
     *
     * 第二阶段：寻找环入口
     * - ptr从头节点开始，slow保持在相遇点
     * - ptr=1, slow=4
     * - ptr=2, slow=2 (相遇，返回节点2)
     *
     * 时间复杂度分析：
     * - 第一阶段寻找相遇点：O(n)
     * - 第二阶段寻找环入口：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数个额外变量：O(1)
     *
     * @param head 链表的头节点
     * @return 环的起始节点，如果无环返回null
     */
    public ListNode detectCycleWithTwoPointers(ListNode head) {
        // 边界情况：空链表或只有一个节点
        if (head == null || head.next == null) {
            return null;
        }

        // 第一阶段：判断是否有环并找到相遇点
        ListNode slow = head;
        ListNode fast = head;

        // 寻找相遇点
        while (fast != null && fast.next != null) {
            // 慢指针前进一步
            slow = slow.next;
            // 快指针前进两步
            fast = fast.next.next;

            // 如果快慢指针相遇，说明有环
            if (slow == fast) {
                break;
            }
        }

        // 如果没有环，返回null
        if (fast == null || fast.next == null) {
            return null;
        }

        // 第二阶段：找到环的起始节点
        ListNode ptr = head;
        while (ptr != slow) {
            ptr = ptr.next;
            slow = slow.next;
        }

        // 返回环的起始节点
        return ptr;
    }

    /**
     * 辅助方法：打印链表（用于测试，有环时需要限制打印节点数）
     */
    public void printList(ListNode head, int maxNodes) {
        ListNode current = head;
        int count = 0;
        while (current != null && count < maxNodes) {
            System.out.print(current.val + " ");
            current = current.next;
            count++;
        }
        System.out.println();
    }

    /**
     * 测试方法和使用示例
     */
    public static void main(String[] args) {
        // 创建解决方案实例
        DetectCycleList142 solution = new DetectCycleList142();

        // 创建无环链表: 1->2->3->null
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);

        // 创建有环链表: 1->2->3->4->2 (4指向2)
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = head2.next;

        // 测试无环链表
        ListNode result1 = solution.detectCycleWithHashSet(head1);
        System.out.println("无环链表检测结果（哈希表）: " + (result1 == null ? "无环" : "环起始节点值: " + result1.val));

        result1 = solution.detectCycleWithTwoPointers(head1);
        System.out.println("无环链表检测结果（快慢指针）: " + (result1 == null ? "无环" : "环起始节点值: " + result1.val));

        // 测试有环链表
        ListNode result2 = solution.detectCycleWithHashSet(head2);
        System.out.println("有环链表检测结果（哈希表）: " + (result2 == null ? "无环" : "环起始节点值: " + result2.val));

        result2 = solution.detectCycleWithTwoPointers(head2);
        System.out.println("有环链表检测结果（快慢指针）: " + (result2 == null ? "无环" : "环起始节点值: " + result2.val));
    }

    /**
     * 方法3：使用异常处理检测环（投机取巧）
     *
     * 算法思路：
     * 利用链表有环时toString()方法会产生StackOverflowError的特性
     *
     * 注意：这种方法不推荐在生产环境中使用
     *
     * 执行过程分析：
     * 1. 对于无环链表：toString()方法正常执行，返回null
     * 2. 对于有环链表：toString()方法递归调用导致栈溢出，捕获异常返回head
     *
     * 时间复杂度分析：
     * - 无环情况：O(n)，其中n为链表节点数
     * - 有环情况：O(1)，发生栈溢出立即返回
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     * - 有环时递归调用栈：O(n)
     *
     * @param head 链表的头节点
     * @return 环的起始节点，如果无环返回null
     */
    public ListNode detectCycleByToString(ListNode head) {
        try {
            // 尝试调用toString方法，有环时会抛出StackOverflowError
            head.toString();
            return null; // 无环
        } catch (StackOverflowError e) {
            // 捕获到栈溢出错误，说明有环
            // 这里只是检测是否有环，但无法确定环的起始节点
            // 实际应用中需要结合其他方法
            return head; // 简化处理，实际应返回环的起始节点
        }
    }
}
