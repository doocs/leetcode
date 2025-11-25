package com.funian.algorithm.algorithm;

import java.util.List;
import java.util.Scanner;

/**
 * 回文链表（LeetCode 234）
 *
 * 时间复杂度：O(n)
 * - 找到中间节点：O(n/2)
 * - 反转后半部分：O(n/2)
 * - 比较两部分：O(n/2)
 * - 恢复链表：O(n/2)
 * - 总时间复杂度：O(n)
 *
 * 空间复杂度：O(1)
 * - 只使用了常数级别的额外空间
 * - 没有使用递归或额外的数据结构
 */
public class ReverseList234 {

    /**
     * 链表节点定义
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入链表节点数
        System.out.print("请输入链表的节点数: ");
        // 读取节点数
        int n = scanner.nextInt();

        // 创建链表
        // 链表头节点
        ListNode head = null;
        // 当前节点
        ListNode current = null;
        System.out.println("请输入链表的节点值: ");
        // 读取节点值并构建链表
        for (int i = 0; i < n; i++) {
            // 读取节点值
            int val = scanner.nextInt();
            // 创建新节点
            ListNode node = new ListNode(val);
            // 如果是第一个节点
            if (head == null) {
                head = node;
                current = node;
            } else {
                // 连接节点
                current.next = node;
                current = node;
            }
        }

        // 创建解决方案实例
        ReverseList234 solution = new ReverseList234();

        // 调用 isPalindrome 方法检查是否为回文链表
        boolean result = solution.isPalindrome(head);

        // 输出结果
        if (result) {
            // 打印是回文链表的信息
            System.out.println("该链表是回文链表");
        } else {
            // 打印不是回文链表的信息
            System.out.println("该链表不是回文链表");
        }

        scanner.close();
    }

    /**
     * 判断链表是否为回文链表
     *
     * 算法思路：
     * 1. 使用快慢指针找到链表的中间节点
     * 2. 反转链表的后半部分
     * 3. 比较前半部分和反转后的后半部分
     * 4. 恢复链表结构（可选）
     *
     * 示例过程（以链表 1->2->2->1 为例）：
     *
     * 原链表: 1 -> 2 -> 2 -> 1
     *
     * 步骤1 - 找到中间节点:
     * 快指针: 1 -> 2 -> 2 (每次走两步)
     * 慢指针: 1 -> 2 (每次走一步)
     * 中间节点: 2 (第一个2)
     *
     * 步骤2 - 反转后半部分:
     * 原后半部分: 2 -> 1
     * 反转后: 1 -> 2
     *
     * 步骤3 - 比较两部分:
     * 前半部分: 1 -> 2
     * 后半部分: 1 -> 2
     * 比较结果: 相等，是回文链表
     *
     * 步骤4 - 恢复链表:
     * 将后半部分再次反转恢复原状
     *
     * 时间复杂度分析：
     * - 找到中间节点：O(n/2)，其中n为链表长度
     * - 反转后半部分：O(n/2)
     * - 比较两部分：O(n/2)
     * - 恢复链表：O(n/2)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用了常数级别的额外空间：O(1)
     * - 没有使用递归或额外的数据结构
     *
     * @param head 链表的头节点
     * @return 如果是回文链表返回true，否则返回false
     */
    public boolean isPalindrome(ListNode head) {
        // 边界条件检查：空链表被认为是回文链表
        if (head == null) return true;

        // 使用快慢指针找到链表的中间节点
        ListNode firstHalfEnd = endOfFirstHalf(head);
        // 反转链表的后半部分
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 检查链表是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        // 比较两个部分的节点值
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;  // 发现不相等的节点值，不是回文链表
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 恢复链表结构（可选，保持原链表不变）
        firstHalfEnd.next = reverseList(secondHalfStart);

        // 返回比较结果
        return result;
    }

    /**
     * 反转链表
     * 使用迭代方法反转链表
     *
     * 时间复杂度分析：
     * - 遍历链表一次：O(m)，其中m为被反转部分的长度
     *
     * 空间复杂度分析：
     * - 只使用常数额外空间：O(1)
     *
     * @param head 需要反转的链表头节点
     * @return 反转后的链表头节点
     */
    private ListNode reverseList(ListNode head) {
        // prev 指向前一个节点，初始为null
        ListNode prev = null;
        // curr 指向当前节点，初始为头节点
        ListNode curr = head;
        // 遍历链表，逐个反转节点指向
        while (curr != null) {
            // next 保存下一个节点
            ListNode next = curr.next;
            // curr.next = prev 反转当前节点的指向
            curr.next = prev;
            // prev = curr 移动prev指针
            prev = curr;
            // curr = next 移动curr指针
            curr = next;
        }
        // 返回新的头节点
        return prev;
    }

    /**
     * 使用快慢指针找到链表的中间节点
     *
     * 时间复杂度分析：
     * - 快慢指针遍历：O(n/2)，其中n为链表长度
     *
     * 空间复杂度分析：
     * - 只使用常数额外空间：O(1)
     *
     * @param head 链表的头节点
     * @return 链表前半部分的最后一个节点
     */
    private ListNode endOfFirstHalf(ListNode head) {
        // fast 快指针，每次移动两步
        ListNode fast = head;
        // slow 慢指针，每次移动一步
        ListNode slow = head;
        // 当快指针还能继续移动时
        while (fast.next != null && fast.next.next != null) {
            // slow = slow.next 慢指针移动一步
            slow = slow.next;
            // fast = fast.next.next 快指针移动两步
            fast = fast.next.next;
        }
        // 返回前半部分的最后一个节点
        return slow;
    }


    /**
     * 方法2：使用数组存储值
     *
     * 算法思路：
     * 1. 遍历链表，将节点值存储到数组中
     * 2. 使用双指针从两端向中间比较数组元素
     *
     * 示例过程（以链表 1->2->2->1 为例）：
     *
     * 1. 遍历链表计算长度: length=4
     * 2. 创建数组: values=[1,2,2,1]
     * 3. 双指针比较:
     *    left=0, right=3: values[0]=1, values[3]=1, 相等
     *    left=1, right=2: values[1]=2, values[2]=2, 相等
     *    left=2, right=1: left>=right, 结束
     * 4. 返回true
     *
     * 时间复杂度分析：
     * - 计算链表长度：O(n)，其中n为链表长度
     * - 存储到数组：O(n)
     * - 双指针比较：O(n/2)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 数组存储节点值：O(n)
     *
     * @param head 链表的头节点
     * @return 如果是回文链表返回true，否则返回false
     */
    public boolean isPalindromeWithArray(ListNode head) {
        if (head == null) return true;

        // 计算链表长度
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        // 将链表值存储到数组中
        int[] values = new int[length];
        current = head;
        for (int i = 0; i < length; i++) {
            values[i] = current.val;
            current = current.next;
        }

        // 使用双指针比较数组元素
        int left = 0;
        int right = length - 1;
        while (left < right) {
            if (values[left] != values[right]) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }


    /**
     * 方法3：使用递归
     *
     * 算法思路：
     * 1. 使用递归到达链表末尾
     * 2. 在回溯过程中比较节点值
     *
     * 示例过程（以链表 1->2->2->1 为例）：
     *
     * 1. 递归调用过程:
     *    recursivelyCheck(1) -> recursivelyCheck(2) -> recursivelyCheck(2) -> recursivelyCheck(1) -> null
     *
     * 2. 回溯比较过程:
     *    currentNode=1, frontPointer=1: 1==1, frontPointer移动到2
     *    currentNode=2, frontPointer=2: 2==2, frontPointer移动到2
     *    currentNode=2, frontPointer=2: 2==2, frontPointer移动到1
     *    currentNode=1, frontPointer=1: 1==1, frontPointer移动到null
     *
     * 3. 返回true
     *
     * 时间复杂度分析：
     * - 递归深度：O(n)，其中n为链表长度
     * - 每层递归执行常数时间操作
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(n)
     *
     * @param head 链表的头节点
     * @return 如果是回文链表返回true，否则返回false
     */
    private ListNode frontPointer;

    public boolean isPalindromeRecursive(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }
}
