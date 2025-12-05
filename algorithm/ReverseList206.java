package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Stack;

/**
 * 链表反转算法（LeetCode 206）
 *
 * 时间复杂度：O(n)
 * - 只需要遍历链表一次
 * - 每个节点只访问一次
 * - 总时间复杂度为 O(n)，其中 n 是链表的长度
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量（prev、curr、next）
 * - 没有使用与输入规模相关的额外空间
 * - 递归调用栈深度为常数级别
 */


public class ReverseList206 {

    // 定义链表节点类
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    /**
     * 主函数：处理用户输入并输出链表反转结果
     *
     * 算法流程：
     * 1. 读取用户输入的链表长度和元素
     * 2. 构建原始链表
     * 3. 调用reverseList方法反转链表
     * 4. 输出反转后的链表
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入链表的长度
        System.out.print("请输入链表的长度: ");
        // 读取链表长度
        int n = scanner.nextInt();
        if (n <= 0) {
            System.out.println("链表长度必须大于0");
            return;
        }

        // 提示用户输入链表的元素
        System.out.println("请输入链表的元素:");
        // 创建链表头节点
        ListNode head = new ListNode(scanner.nextInt());
        // 当前节点指针
        ListNode current = head;

        // 读取剩余元素并构建链表
        for (int i = 1; i < n; i++) {
            // 读取下一个元素并创建节点
            current.next = new ListNode(scanner.nextInt());
            // 移动当前节点指针
            current = current.next;
        }

        // 调用反转链表的方法
        ListNode reversedHead = reverseList(head);

        // 输出反转后的链表
        System.out.println("反转后的链表:");
        printList(reversedHead);

        scanner.close();
    }

    /**
     * 反转链表的核心方法
     *
     * 算法思路：
     * 使用三个指针（prev、curr、next）逐个反转链表中的节点指向关系
     * 通过迭代方式实现链表反转，不使用额外的存储空间
     *
     * 执行过程分析（以链表 1->2->3->4->NULL 为例）：
     *
     * 初始状态：
     * prev = null, curr = 1->2->3->4->NULL
     *
     * 第1次迭代：
     * next = 2->3->4->NULL
     * curr.next = null (1->null)
     * prev = 1->null
     * curr = 2->3->4->NULL
     *
     * 第2次迭代：
     * next = 3->4->NULL
     * curr.next = 1->null (2->1->null)
     * prev = 2->1->null
     * curr = 3->4->NULL
     *
     * 第3次迭代：
     * next = 4->NULL
     * curr.next = 2->1->null (3->2->1->null)
     * prev = 3->2->1->null
     * curr = 4->NULL
     *
     * 第4次迭代：
     * next = null
     * curr.next = 3->2->1->null (4->3->2->1->null)
     * prev = 4->3->2->1->null
     * curr = null
     *
     * 循环结束，返回 prev（即 4->3->2->1->NULL）
     *
     * 时间复杂度分析：
     * - 遍历链表一次：O(n)，其中n为链表节点数
     * - 每个节点只访问一次
     *
     * 空间复杂度分析：
     * - 只使用了常数个额外变量（prev、curr、next）：O(1)
     * - 没有使用与输入规模相关的额外空间
     *
     * @param head 原始链表的头节点
     * @return 反转后链表的头节点
     */
    public static ListNode reverseList(ListNode head) {
        // 指向前一个节点，初始为null
        ListNode prev = null;
        // 指向当前节点，初始为头节点
        ListNode curr = head;

        // 当当前节点不为null时继续循环
        while (curr != null) {
            // 暂存下一个节点，防止丢失
            ListNode next = curr.next;
            // 当前节点的next指向前一个节点
            curr.next = prev;
            // prev前移，指向当前节点
            prev = curr;
            // curr前移，指向下一个节点
            curr = next;
        }

        // 返回新链表的头节点（原链表的最后一个节点）
        return prev;
    }

    /**
     * 打印链表的方法
     *
     * 算法思路：
     * 从头节点开始，依次遍历每个节点并打印其值
     * 直到遇到null为止
     *
     * @param head 链表的头节点
     */
    public static void printList(ListNode head) {
        // 当前节点指针，初始指向头节点
        ListNode curr = head;
        // 当前节点不为空时继续
        while (curr != null) {
            // 打印当前节点的值和空格
            System.out.print(curr.val + " ");
            // 移动到下一个节点
            curr = curr.next;
        }
        // 换行
        System.out.println();
    }

    /**
     * 方法2：递归实现链表反转
     *
     * 算法思路：
     * 1. 递归到链表末尾
     * 2. 在回溯过程中反转节点指向
     *
     * 示例过程（以链表 1->2->3->4->NULL 为例）：
     *
     * 1. 递归调用过程:
     *    reverseListRecursive(1) -> reverseListRecursive(2) -> reverseListRecursive(3) -> reverseListRecursive(4)
     *    到达基础情况: head=4, head.next=null, 返回4
     *
     * 2. 回溯过程:
     *    head=3: head.next.next=3 (4->3), head.next=null (3->null), 返回4
     *    head=2: head.next.next=2 (3->2), head.next=null (2->null), 返回4
     *    head=1: head.next.next=1 (2->1), head.next=null (1->null), 返回4
     *
     * 3. 最终结果: 4->3->2->1->NULL
     *
     * 时间复杂度分析：
     * - 递归深度为n：O(n)，其中n为链表节点数
     * - 每层递归执行常数时间操作
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(n)
     *
     * @param head 原始链表的头节点
     * @return 反转后链表的头节点
     */
    public static ListNode reverseListRecursive(ListNode head) {
        // 基础情况：空链表或只有一个节点
        if (head == null || head.next == null) {
            return head;
        }

        // 递归反转剩余部分
        ListNode newHead = reverseListRecursive(head.next);

        // 反转当前节点和下一个节点的连接
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    /**
     * 方法3：使用栈实现链表反转
     *
     * 算法思路：
     * 1. 将链表所有节点压入栈中
     * 2. 从栈中弹出节点重新构建链表
     *
     * 示例过程（以链表 1->2->3->4->NULL 为例）：
     *
     * 1. 压栈过程:
     *    stack: [1, 2, 3, 4] (4在栈顶)
     *
     * 2. 构建新链表:
     *    newHead = stack.pop() = 4
     *    current = 4
     *    pop 3: current.next = 3, current = 3
     *    pop 2: current.next = 2, current = 2
     *    pop 1: current.next = 1, current = 1
     *    current.next = null
     *
     * 3. 最终结果: 4->3->2->1->NULL
     *
     * 时间复杂度分析：
     * - 遍历链表压栈：O(n)，其中n为链表节点数
     * - 从栈中弹出构建新链表：O(n)
     *
     * 空间复杂度分析：
     * - 栈存储所有节点：O(n)
     *
     * @param head 原始链表的头节点
     * @return 反转后链表的头节点
     */
    public static ListNode reverseListWithStack(ListNode head) {
        if (head == null) return null;

        // 使用栈存储节点
        java.util.Stack<ListNode> stack = new java.util.Stack<>();
        ListNode current = head;

        // 将所有节点压入栈中
        while (current != null) {
            stack.push(current);
            current = current.next;
        }

        // 构建新链表
        ListNode newHead = stack.pop();
        current = newHead;

        // 从栈中弹出节点并连接
        while (!stack.isEmpty()) {
            current.next = stack.pop();
            current = current.next;
        }

        // 设置最后一个节点的next为null
        current.next = null;

        return newHead;
    }
}