package com.funian.algorithm.algorithm;

import java.util.List;

/**
 * 删除链表的倒数第N个节点（LeetCode 19）
 *
 * 时间复杂度：O(L)
 * - L 是链表的长度
 * - 需要遍历链表一次（双指针法）或两次（计算长度法）
 * - 总体时间复杂度为 O(L)
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 * - 不使用与输入规模相关的额外空间
 */
public class RemoveElementFromList19 {

    /**
     * 链表节点定义
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 方法1：计算链表长度法
     *
     * 算法思路：
     * 1. 先遍历链表计算长度
     * 2. 根据长度和n计算要删除的节点的正数位置
     * 3. 再次遍历到该位置并删除节点
     *
     * 执行过程分析（以链表 1->2->3->4->5，n=2 为例）：
     * 1. 计算链表长度：L = 5
     * 2. 计算要删除节点的正数位置：5 - 2 + 1 = 4（即第4个节点）
     * 3. 遍历到第3个节点（删除节点的前一个节点）
     * 4. 修改指针跳过第4个节点：3->next = 3->next->next（即5）
     * 5. 结果：1->2->3->5
     *
     * 时间复杂度分析：
     * - 第一次遍历计算长度：O(L)，其中L为链表长度
     * - 第二次遍历找到删除位置：O(L-n)
     * - 总时间复杂度：O(L)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param head 链表的头节点
     * @param n 倒数第n个节点
     * @return 删除节点后的链表头节点
     */
    public ListNode removeNthFromEndWithLength(ListNode head, int n) {
        // 创建哑节点，简化头节点删除的情况
        ListNode dummy = new ListNode(0, head);

        // 第一次遍历：计算链表长度
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        // 计算要删除节点的正数位置
        int positionFromStart = length - n;

        // 第二次遍历：找到要删除节点的前一个节点
        current = dummy;
        for (int i = 0; i < positionFromStart; i++) {
            current = current.next;
        }

        // 删除节点：跳过要删除的节点
        current.next = current.next.next;

        // 返回结果链表的头节点
        return dummy.next;
    }

    /**
     * 方法2：双指针法（一次遍历）
     *
     * 算法思路：
     * 1. 使用两个指针，第一个指针先移动n+1步
     * 2. 然后两个指针同时移动，直到第一个指针到达末尾
     * 3. 此时第二个指针指向要删除节点的前一个节点
     *
     * 执行过程分析（以链表 1->2->3->4->5，n=2 为例）：
     *
     * 初始状态：
     * dummy -> 1 -> 2 -> 3 -> 4 -> 5 -> null
     * first -> dummy
     * second -> dummy
     *
     * first指针先移动n+1=3步：
     * first -> 2
     * second -> dummy
     *
     * 两个指针同时移动直到first到达末尾：
     * 第1步：first -> 3, second -> 1
     * 第2步：first -> 4, second -> 2
     * 第3步：first -> 5, second -> 3
     * 第4步：first -> null, second -> 4
     *
     * 此时second指向要删除节点（4）的前一个节点（3）
     * 执行删除操作：3->next = 3->next->next（即5）
     * 结果：1->2->3->5
     *
     * 时间复杂度分析：
     * - 遍历链表一次：O(L)，其中L为链表长度
     * - 总时间复杂度：O(L)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param head 链表的头节点
     * @param n 倒数第n个节点
     * @return 删除节点后的链表头节点
     */
    public ListNode removeNthFromEndWithTwoPointers(ListNode head, int n) {
        // 创建哑节点，简化头节点删除的情况
        ListNode dummy = new ListNode(0, head);

        // 初始化两个指针
        ListNode first = dummy;
        ListNode second = dummy;

        // 第一个指针先移动n+1步
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // 两个指针同时移动，直到第一个指针到达末尾
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // 删除倒数第n个节点
        second.next = second.next.next;

        // 返回结果链表的头节点
        return dummy.next;
    }

    /**
     * 辅助方法：创建链表（用于测试）
     */
    public ListNode createList(int[] values) {
        if (values.length == 0) return null;
        // 创建头节点
        ListNode head = new ListNode(values[0]);
        // current 当前节点指针
        ListNode current = head;
        // for (int i = 1; i < values.length; i++) 遍历数组剩余元素
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        // 返回链表头节点
        return head;
    }

    /**
     * 辅助方法：打印链表（用于测试）
     */
    public void printList(ListNode head) {
        // current 当前节点指针，初始指向头节点
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println(" -> null");
    }

    /**
     * 测试方法和使用示例
     */
    public static void main(String[] args) {
        // 创建解决方案实例
        RemoveElementFromList19 solution = new RemoveElementFromList19();

        // 创建测试链表: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = solution.createList(new int[]{1, 2, 3, 4, 5});

        // 打印原始链表
        System.out.println("原始链表:");
        solution.printList(head);

        // 测试删除倒数第2个节点
        ListNode result1 = solution.removeNthFromEndWithLength(head, 2);
        System.out.println("删除倒数第2个节点后（计算长度法）:");
        solution.printList(result1);

        // 重新创建链表进行测试
        head = solution.createList(new int[]{1, 2, 3, 4, 5});
        // solution.removeNthFromEndWithTwoPointers(head, 2) 使用双指针法删除倒数第2个节点
        ListNode result2 = solution.removeNthFromEndWithTwoPointers(head, 2);
        System.out.println("删除倒数第2个节点后（双指针法）:");
        solution.printList(result2);

        // 测试删除头节点
        head = solution.createList(new int[]{1, 2, 3, 4, 5});
        // solution.removeNthFromEndWithTwoPointers(head, 5) 使用双指针法删除倒数第5个节点（头节点）
        ListNode result3 = solution.removeNthFromEndWithTwoPointers(head, 5);
        System.out.println("删除倒数第5个节点后（删除头节点）:");
        solution.printList(result3);

        // 测试删除尾节点
        head = solution.createList(new int[]{1, 2, 3, 4, 5});
        // solution.removeNthFromEndWithTwoPointers(head, 1) 使用双指针法删除倒数第1个节点（尾节点）
        ListNode result4 = solution.removeNthFromEndWithTwoPointers(head, 1);
        System.out.println("删除倒数第1个节点后（删除尾节点）:");
        solution.printList(result4);
    }
}
