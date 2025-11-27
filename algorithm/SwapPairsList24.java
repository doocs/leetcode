package com.funian.algorithm.algorithm;

import java.util.List;

/**
 * 两两交换链表中的节点（LeetCode 24）
 *
 * 时间复杂度：O(n)
 * - n 是链表的长度
 * - 需要遍历链表一次，每个节点最多被访问常数次
 * - 总时间复杂度为 O(n)
 *
 * 空间复杂度：
 * - 迭代解法：O(1)
 *   只使用了常数个额外变量
 * - 递归解法：O(n)
 *   递归调用栈的深度最多为 n/2
 */
public class SwapPairsList24 {

    /**
     * 链表节点定义
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    /**
     * 方法1：迭代解法
     *
     * 算法思路：
     * 使用哑节点简化头节点交换的处理
     * 通过三个指针（prev, first, second）来完成节点交换
     * 每次处理两个节点，然后移动指针处理下一对
     *
     * 执行过程分析（以链表 1->2->3->4 为例）：
     *
     * 初始状态：
     * dummy -> 1 -> 2 -> 3 -> 4 -> null
     * prev -> dummy
     * first -> 1
     * second -> 2
     *
     * 第一次交换（节点1和节点2）：
     * 1. 保存节点3：nextPair = 3 -> 4 -> null
     * 2. 交换节点1和节点2：
     *    second.next = first (2 -> 1)
     *    first.next = nextPair (1 -> 3 -> 4 -> null)
     *    prev.next = second (dummy -> 2)
     * 3. 更新指针：
     *    prev -> 1
     *    first -> 3
     *    second -> 4
     * 4. 状态：dummy -> 2 -> 1 -> 3 -> 4 -> null
     *
     * 第二次交换（节点3和节点4）：
     * 1. 保存节点null：nextPair = null
     * 2. 交换节点3和节点4：
     *    second.next = first (4 -> 3)
     *    first.next = nextPair (3 -> null)
     *    prev.next = second (1 -> 4)
     * 3. 更新指针：
     *    first -> null (循环结束)
     * 4. 最终状态：dummy -> 2 -> 1 -> 4 -> 3 -> null
     *
     * 时间复杂度分析：
     * - 遍历链表：O(n)，其中n为链表长度
     * - 每次循环执行常数时间操作
     *
     * 空间复杂度分析：
     * - 只使用常数个额外变量：O(1)
     *
     * @param head 链表的头节点
     * @return 交换后的链表头节点
     */
    public ListNode swapPairsIterative(ListNode head) {
        // 创建哑节点，简化头节点交换的处理
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // prev指向前一对节点交换后的后一个节点
        ListNode prev = dummy;

        // 当还有至少两个节点需要交换时继续循环
        while (head != null && head.next != null) {
            // 定义要交换的两个节点
            ListNode first = head;
            ListNode second = head.next;

            // 保存下一对节点的开始位置
            ListNode nextPair = second.next;

            // 执行交换操作
            second.next = first;
            first.next = nextPair;
            prev.next = second;

            // 更新指针，准备处理下一对节点
            prev = first;
            head = nextPair;
        }

        // 返回交换后的链表头节点
        return dummy.next;
    }

    /**
     * 方法2：递归解法
     *
     * 算法思路：
     * 将问题分解为子问题
     * 1. 交换前两个节点
     * 2. 递归处理剩余的节点
     * 3. 将两部分连接起来
     *
     * 执行过程分析（以链表 1->2->3->4 为例）：
     *
     * 递归调用过程：
     * swapPairs(1->2->3->4)
     * ├─ first = 1
     * ├─ second = 2
     * ├─ nextPair = swapPairs(3->4)
     * │  ├─ first = 3
     * │  ├─ second = 4
     * │  ├─ nextPair = swapPairs(null)
     * │  │  └─ 返回 null
     * │  ├─ second.next = first (4->3)
     * │  ├─ first.next = nextPair (3->null)
     * │  └─ 返回 4->3->null
     * ├─ second.next = first (2->1)
     * ├─ first.next = nextPair (1->4->3->null)
     * └─ 返回 2->1->4->3->null
     *
     * 时间复杂度分析：
     * - 递归深度：O(n/2)，其中n为链表长度
     * - 每层递归执行常数时间操作
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(n/2)
     *
     * @param head 链表的头节点
     * @return 交换后的链表头节点
     */
    public ListNode swapPairsRecursive(ListNode head) {
        // 基础情况：如果节点数少于2个，直接返回
        if (head == null || head.next == null) {
            return head;
        }

        // 定义要交换的两个节点
        ListNode first = head;
        ListNode second = head.next;

        // 递归处理剩余的节点
        ListNode nextPair = swapPairsRecursive(second.next);

        // 执行交换操作
        second.next = first;
        first.next = nextPair;

        // 返回新的头节点（第二个节点）
        return second;
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
        SwapPairsList24 solution = new SwapPairsList24();

        // 测试用例1：偶数个节点
        ListNode head1 = solution.createList(new int[]{1, 2, 3, 4});
        System.out.println("原始链表（偶数个节点）:");
        solution.printList(head1);

        // solution.swapPairsIterative(head1) 使用迭代解法交换节点
        ListNode result1 = solution.swapPairsIterative(head1);
        System.out.println("迭代解法交换后:");
        solution.printList(result1);

        // 测试用例2：奇数个节点
        ListNode head2 = solution.createList(new int[]{1, 2, 3, 4, 5});
        System.out.println("原始链表（奇数个节点）:");
        solution.printList(head2);

        // solution.swapPairsRecursive(head2) 使用递归解法交换节点
        ListNode result2 = solution.swapPairsRecursive(head2);
        System.out.println("递归解法交换后:");
        solution.printList(result2);

        // 测试用例3：单个节点
        ListNode head3 = solution.createList(new int[]{1});
        System.out.println("原始链表（单个节点）:");
        solution.printList(head3);

        // solution.swapPairsIterative(head3) 使用迭代解法交换节点
        ListNode result3 = solution.swapPairsIterative(head3);
        System.out.println("交换后:");
        solution.printList(result3);

        // 测试用例4：空链表
        ListNode head4 = null;
        System.out.println("原始链表（空链表）:");
        solution.printList(head4);

        // solution.swapPairsRecursive(head4) 使用递归解法交换节点
        ListNode result4 = solution.swapPairsRecursive(head4);
        System.out.println("交换后:");
        solution.printList(result4);
    }
}
