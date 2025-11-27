package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * K个一组翻转链表（LeetCode 25）
 *
 * 时间复杂度：O(n)
 * - n 是链表的长度
 * - 需要遍历链表一次，每个节点最多被访问常数次
 * - 总时间复杂度为 O(n)
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 * - 不使用与输入规模相关的额外空间
 */
public class ReverseKGroup25 {

    /**
     * 链表节点定义
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * K个一组翻转链表
     *
     * 算法思路：
     * 1. 使用哑节点简化头节点处理
     * 2. 每次确定k个节点的范围
     * 3. 翻转这k个节点
     * 4. 连接翻转后的子链表与前后部分
     * 5. 继续处理下一组
     *
     * 执行过程分析（以链表 1->2->3->4->5，k=2 为例）：
     *
     * 初始状态：
     * dummy -> 1 -> 2 -> 3 -> 4 -> 5 -> null
     *
     * 第一组翻转（节点1和2）：
     * 1. 确定范围：1 -> 2
     * 2. 翻转：2 -> 1
     * 3. 连接：dummy -> 2 -> 1 -> 3 -> 4 -> 5 -> null
     *
     * 第二组翻转（节点3和4）：
     * 1. 确定范围：3 -> 4
     * 2. 翻转：4 -> 3
     * 3. 连接：dummy -> 2 -> 1 -> 4 -> 3 -> 5 -> null
     *
     * 第三组（不足k个节点）：
     * 1. 节点数不足k个，不翻转
     * 2. 最终结果：2 -> 1 -> 4 -> 3 -> 5 -> null
     *
     * 时间复杂度分析：
     * - 遍历链表确定每组范围：O(n)，其中n为链表长度
     * - 翻转每组k个节点：O(k) × O(n/k) = O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数个额外变量：O(1)
     *
     * @param head 链表的头节点
     * @param k 每组翻转的节点数
     * @return 翻转后的链表头节点
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 创建哑节点，简化头节点处理
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // prevGroupEnd 指向已处理部分的最后一个节点
        ListNode prevGroupEnd = dummy;

        // 无限循环，直到处理完所有节点
        while (true) {
            // kGroupStart 当前组的第一个节点
            ListNode kGroupStart = prevGroupEnd.next;
            // kGroupEnd 当前组的最后一个节点
            ListNode kGroupEnd = prevGroupEnd;

            // 确定当前组的结束节点
            for (int i = 0; i < k; i++) {
                kGroupEnd = kGroupEnd.next;
                // 检查是否不足k个节点
                if (kGroupEnd == null) {
                    // 不足k个节点，返回结果
                    return dummy.next;
                }
            }

            // nextGroupStart 下一组的第一个节点
            ListNode nextGroupStart = kGroupEnd.next;
            // 断开当前组
            kGroupEnd.next = null;

            // 反转当前组
            ListNode reversedGroup = reverse(kGroupStart);
            // 连接已处理部分和当前组
            prevGroupEnd.next = reversedGroup;
            // 连接当前组和下一组
            kGroupStart.next = nextGroupStart;

            // 更新 prevGroupEnd 为当前组的最后一个节点（翻转前的第一个节点）
            prevGroupEnd = kGroupStart;
        }
    }

    /**
     * 翻转链表
     *
     * 算法思路：
     * 使用三个指针（prev、curr、nextTemp）逐个翻转节点指向
     *
     * 时间复杂度分析：
     * - 遍历链表一次：O(m)，其中m为链表长度
     *
     * 空间复杂度分析：
     * - 只使用常数个额外变量：O(1)
     *
     * @param head 要翻转的链表头节点
     * @return 翻转后的链表头节点
     */
    private ListNode reverse(ListNode head) {
        // prev 指向前一个节点，初始为null
        ListNode prev = null;
        // curr 指向当前节点，初始为头节点
        ListNode curr = head;
        while (curr != null) {
            // nextTemp 暂存下一个节点
            ListNode nextTemp = curr.next;
            // 反转当前节点的指向
            curr.next = prev;
            // 移动prev到当前节点
            prev = curr;
            // 移动curr到下一个节点
            curr = nextTemp;
        }
        // 返回新的头节点
        return prev;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入链表节点数
        System.out.print("请输入链表节点数：");
        // 读取节点数
        int n = scanner.nextInt();

        // 创建链表
        ListNode head = null;
        ListNode tail = null;

        // 输入链表节点值
        System.out.println("请输入链表节点值（以空格分隔）：");
        for (int i = 0; i < n; i++) {
            // 读取节点值
            int value = scanner.nextInt();
            // 创建新节点
            ListNode newNode = new ListNode(value);
            // 检查是否为第一个节点
            if (head == null) {
                // 设置头节点
                head = newNode;
                // 设置尾节点
                tail = newNode;
            } else {
                // 连接新节点
                tail.next = newNode;
                // 更新尾节点
                tail = newNode;
            }
        }

        // 输入k值
        System.out.print("请输入每组翻转的节点数 k：");
        // 读取k值
        int k = scanner.nextInt();

        // 创建解决方案实例并调用方法
        ReverseKGroup25 solution = new ReverseKGroup25();
        ListNode newHead = solution.reverseKGroup(head, k);

        // 打印结果链表
        System.out.println("翻转后的链表节点值为：");
        // curr 当前节点指针，初始指向新链表头节点
        ListNode curr = newHead;
        while (curr != null) {
            // 打印当前节点值
            System.out.print(curr.val + " ");
            // 移动到下一个节点
            curr = curr.next;
        }
        // 关闭scanner
        scanner.close();
    }
}
