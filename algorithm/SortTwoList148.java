package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 排序链表（LeetCode 148）
 *
 * 时间复杂度：O(n log n)
 * - 采用归并排序的思想
 * - 分解过程需要 log n 层
 * - 每层合并需要 O(n) 时间
 * - 总时间复杂度为 O(n log n)
 *
 * 空间复杂度：O(log n)
 * - 递归调用栈的深度为 log n
 */
public class SortTwoList148 {

    /**
     * 链表节点定义
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 对链表进行排序的主方法
     *
     * 算法思路：
     * 使用归并排序的思想对链表进行排序
     * 1. 分解：将链表从中间分成两部分
     * 2. 递归：分别对两部分进行排序
     * 3. 合并：将两个已排序的链表合并成一个有序链表
     *
     * 执行过程分析（以链表 4->2->1->3 为例）：
     *
     * 分解过程：
     * 4->2->1->3
     *    ↓
     * 4->2  1->3
     *   ↓     ↓
     * 4  2  1  3
     *
     * 合并过程：
     * 4  2  1  3
     *   ↓     ↓
     * 2->4  1->3
     *    ↓
     * 1->2->3->4
     *
     * 时间复杂度分析：
     * - 分解过程：O(log n)，其中n为链表长度，递归深度为log n
     * - 每层合并：O(n)，需要遍历当前层的所有节点
     * - 总时间复杂度：O(n log n)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(log n)
     *
     * @param head 链表的头节点
     * @return 排序后的链表头节点
     */
    public ListNode sortList(ListNode head) {
        // 基础情况：如果链表为空或只有一个节点，直接返回
        // 这是递归的终止条件
        if (head == null || head.next == null) {
            return head;
        }

        // 使用快慢指针找到中间节点，将链表分成两部分
        // slow指针每次移动一步，fast指针每次移动两步
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // 切断链表，分成两个独立的链表
        // prev是slow的前一个节点，将其next设为null来断开链表
        prev.next = null;

        // 递归排序左右两部分
        // 左部分从原头节点开始，右部分从中间节点开始
        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        // 合并两个已排序的链表
        return merge(left, right);
    }

    /**
     * 合并两个已排序的链表
     *
     * 算法思路：
     * 使用双指针技术，依次比较两个链表的节点值
     * 将较小的节点连接到结果链表中
     * 最后将剩余的节点直接连接到结果链表
     *
     * 执行过程分析（以 l1=[1,2], l2=[3,4] 为例）：
     *
     * 初始状态：
     * l1: 1 -> 2 -> null
     * l2: 3 -> 4 -> null
     * dummy -> null
     * current -> dummy
     *
     * 第1次比较（1 vs 3）：
     * 选择l1的节点1
     * dummy -> 1 -> null
     * current -> 1
     * l1: 2 -> null
     * l2: 3 -> 4 -> null
     *
     * 第2次比较（2 vs 3）：
     * 选择l1的节点2
     * dummy -> 1 -> 2 -> null
     * current -> 2
     * l1: null
     * l2: 3 -> 4 -> null
     *
     * l1为空，将l2剩余部分连接到结果链表：
     * dummy -> 1 -> 2 -> 3 -> 4 -> null
     *
     * 时间复杂度分析：
     * - 遍历两个链表：O(m + n)，其中m、n分别为两个链表的长度
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param l1 第一个已排序链表的头节点
     * @param l2 第二个已排序链表的头节点
     * @return 合并后的有序链表的头节点
     */
    private ListNode merge(ListNode l1, ListNode l2) {
        // 创建哑节点，简化边界条件的处理
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // 合并两个链表，直到其中一个为空
        while (l1 != null && l2 != null) {
            // 比较两个链表当前节点的值
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // 连接剩余部分（其中一个链表可能还有节点）
        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }

        // 返回合并后的链表的头节点（跳过哑节点）
        return dummy.next;
    }

    /**
     * 创建链表的辅助方法
     *
     * 算法思路：
     * 读取用户输入的节点值，创建对应的链表
     *
     * 时间复杂度分析：
     * - 创建链表：O(n)，其中n为输入节点数
     *
     * 空间复杂度分析：
     * - 创建链表节点：O(n)
     *
     * @return 创建的链表头节点
     */
    public static ListNode createList() {
        // 创建Scanner对象读取用户输入
        Scanner scanner = new Scanner(System.in);
        // 提示用户输入
        System.out.println("请输入链表的节点值，以空格分隔（输入结束后按回车）：");
        // 读取一行输入
        String input = scanner.nextLine();
        // 按空格分割字符串得到字符串数组
        String[] values = input.split(" ");

        // 如果没有输入节点值，返回空链表
        if (values.length == 0 || values[0].isEmpty()) {
            return null;
        }

        // 创建链表头节点
        ListNode head = new ListNode(Integer.parseInt(values[0]));
        // current 当前节点指针
        ListNode current = head;

        // 依次创建并连接后续节点
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(Integer.parseInt(values[i]));
            current = current.next;
        }

        // 返回链表头节点
        return head;
    }

    /**
     * 主函数：处理用户输入并输出排序结果
     *
     * 算法流程：
     * 1. 调用createList方法读取用户输入并创建链表
     * 2. 调用sortList方法对链表进行排序
     * 3. 输出排序后的链表
     */
    public static void main(String[] args) {
        // 注意：原代码中这里有个错误，应该是SortTwoList148而不是Solution
        // 创建解决方案实例
        SortTwoList148 solution = new SortTwoList148();

        // 创建链表
        ListNode head = createList();

        // 对链表进行排序
        ListNode sortedHead = solution.sortList(head);

        // 打印排序后的链表
        System.out.print("排序后的链表为：");
        while (sortedHead != null) {
            System.out.print(sortedHead.val + " ");
            sortedHead = sortedHead.next;
        }
        System.out.println();
    }
}
