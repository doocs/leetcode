package com.funian.algorithm.algorithm;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 相交链表（LeetCode 160）
 *
 * 时间复杂度：O(m + n)
 * - 需要遍历两个链表各一次
 * - 最坏情况下两个指针都要走完两个链表
 *
 * 空间复杂度：O(1)
 * - 只使用了常数级别的额外空间
 * - 没有使用与链表长度相关的额外存储空间
 */
public class GetIntersectionNode160 {

    /**
     * 链表节点定义
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入链表A
        System.out.println("请输入链表A的节点数：");
        // 读取链表A的节点数
        int aLength = scanner.nextInt();
        // 链表A的头节点
        ListNode headA = null;
        // 链表A的当前节点
        ListNode currentA = null;

        System.out.println("请输入链表A的节点值：");
        // 读取链表A的节点值
        for (int i = 0; i < aLength; i++) {
            // 读取节点值
            int val = scanner.nextInt();
            // 创建新节点
            ListNode node = new ListNode(val);
            // 如果是第一个节点
            if (headA == null) {
                headA = node;
                currentA = node;
            } else {
                // 连接节点
                currentA.next = node;
                currentA = node;
            }
        }

        // 提示用户输入链表B
        System.out.println("请输入链表B的节点数：");
        // 读取链表B的节点数
        int bLength = scanner.nextInt();
        // 链表B的头节点
        ListNode headB = null;
        // 链表B的当前节点
        ListNode currentB = null;

        System.out.println("请输入链表B的节点值：");
        // 读取链表B的节点值
        for (int i = 0; i < bLength; i++) {
            // 读取节点值
            int val = scanner.nextInt();
            // 创建新节点
            ListNode node = new ListNode(val);
            // 如果是第一个节点
            if (headB == null) {
                headB = node;
                currentB = node;
            } else {
                // 连接节点
                currentB.next = node;
                currentB = node;
            }
        }

        // 输入相交节点位置（可选）
        System.out.println("请输入链表A中相交节点的位置（从0开始，-1表示无相交）：");
        // 读取相交节点位置
        int intersectPos = scanner.nextInt();

        // 创建相交节点
        if (intersectPos >= 0) {
            // 找到链表A的相交节点
            ListNode intersectNode = headA;
            // 遍历到相交节点位置
            for (int i = 0; i < intersectPos && intersectNode != null; i++) {
                intersectNode = intersectNode.next;
            }

            // 将链表B的尾部连接到相交节点
            if (intersectNode != null) {
                // 找到链表B的尾节点
                ListNode tailB = headB;
                while (tailB.next != null) {
                    tailB = tailB.next;
                }
                // 连接链表B的尾节点到链表A的相交节点
                tailB.next = intersectNode;
            }
        }

        // 调用 getIntersectionNode 方法查找相交节点
        ListNode result = getIntersectionNode(headA, headB);

        // 输出结果
        if (result != null) {
            // 打印相交节点值
            System.out.println("两个链表相交于节点值: " + result.val);
        } else {
            // 打印无相交信息
            System.out.println("两个链表不相交");
        }
    }

    /**
     * 找到两个单链表相交的起始节点
     *
     * 算法思路：
     * 使用双指针技巧，让两个指针分别遍历两个链表
     * 当一个指针到达链表末尾时，让它从另一个链表的头部开始
     * 这样两个指针最终会在相交节点相遇，或者同时到达末尾（无相交）
     *
     * 核心原理：
     * 假设链表A长度为a+c，链表B长度为b+c（c为相交部分长度）
     * 指针A走过的路径：a+c+b
     * 指针B走过的路径：b+c+a
     * 两者相等，如果存在相交节点，必然在相交节点相遇
     *
     * 示例过程（链表A: 4->1->8->4->5, 链表B: 5->6->1->8->4->5）：
     *
     * 初始状态:
     * 指针A: 4->1->8->4->5->5->6->1->8->4->5 (到达末尾后从B开始)
     * 指针B: 5->6->1->8->4->5->4->1->8->4->5 (到达末尾后从A开始)
     *
     * 在第8个节点处相遇，即相交节点
     *
     * 时间复杂度分析：
     * - 遍历两个链表：O(m + n)，其中m为链表A的长度，n为链表B的长度
     * - 最坏情况下两个指针都要走完两个链表
     *
     * 空间复杂度分析：
     * - 只使用了常数级别的额外空间：O(1)
     * - 没有使用与链表长度相关的额外存储空间
     *
     * @param headA 链表A的头节点
     * @param headB 链表B的头节点
     * @return 如果存在相交节点，返回相交节点；否则返回null
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 边界条件检查
        if (headA == null || headB == null) {
            return null;
        }

        // 初始化两个指针
        ListNode pointerA = headA;
        ListNode pointerB = headB;

        // 当两个指针不相等时继续循环
        while (pointerA != pointerB) {
            // 如果指针A到达链表末尾，则从链表B的头部开始
            if (pointerA == null) {
                pointerA = headB;
            } else {
                pointerA = pointerA.next;
            }

            // 如果指针B到达链表末尾，则从链表A的头部开始
            if (pointerB == null) {
                pointerB = headA;
            } else {
                pointerB = pointerB.next;
            }
        }

        // 返回相交节点（如果无相交则返回null）
        return pointerA;
    }

    /**
     * 方法2：计算长度差法
     *
     * 算法思路：
     * 1. 计算两个链表的长度
     * 2. 让较长链表的指针先走长度差步
     * 3. 两个指针同时移动，直到相遇
     *
     * 示例过程（链表A: 4->1->8->4->5 (长度5), 链表B: 5->6->1->8->4->5 (长度6)）：
     *
     * 1. 计算长度: lenA=5, lenB=6
     * 2. 链表B较长，长度差为1，让currentB先走1步
     *    currentB指向6节点
     * 3. 两个指针同时移动:
     *    currentA=1, currentB=1
     *    currentA=8, currentB=8
     *    currentA=4, currentB=4
     *    currentA=5, currentB=5
     *    两指针相遇，返回相交节点
     *
     * 时间复杂度分析：
     * - 计算链表长度：O(m + n)，其中m为链表A的长度，n为链表B的长度
     * - 指针移动：O(max(m, n))
     * - 总时间复杂度：O(m + n)
     *
     * 空间复杂度分析：
     * - 只使用了常数级别的额外空间：O(1)
     *
     * @param headA 链表A的头节点
     * @param headB 链表B的头节点
     * @return 如果存在相交节点，返回相交节点；否则返回null
     */
    public static ListNode getIntersectionNodeByLength(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        // 计算链表A的长度
        int lenA = 0;
        ListNode currentA = headA;
        while (currentA != null) {
            lenA++;
            currentA = currentA.next;
        }

        // 计算链表B的长度
        int lenB = 0;
        ListNode currentB = headB;
        while (currentB != null) {
            lenB++;
            currentB = currentB.next;
        }

        // 重置指针
        currentA = headA;
        currentB = headB;

        // 让较长链表的指针先走长度差步
        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++) {
                currentA = currentA.next;
            }
        } else {
            for (int i = 0; i < lenB - lenA; i++) {
                currentB = currentB.next;
            }
        }

        // 两个指针同时移动，直到相遇
        while (currentA != currentB) {
            currentA = currentA.next;
            currentB = currentB.next;
        }

        return currentA;
    }

    /**
     * 方法3：使用HashSet
     *
     * 算法思路：
     * 1. 遍历链表A，将所有节点存入HashSet
     * 2. 遍历链表B，检查节点是否在HashSet中
     *
     * 示例过程（链表A: 4->1->8->4->5, 链表B: 5->6->1->8->4->5）：
     *
     * 1. 遍历链表A，将节点[4,1,8,4,5]存入HashSet
     * 2. 遍历链表B:
     *    检查节点5: 不在HashSet中
     *    检查节点6: 不在HashSet中
     *    检查节点1: 在HashSet中，返回该节点
     *
     * 时间复杂度分析：
     * - 遍历链表A：O(m)，其中m为链表A的长度
     * - 遍历链表B：O(n)，其中n为链表B的长度
     * - HashSet操作：O(1)
     * - 总时间复杂度：O(m + n)
     *
     * 空间复杂度分析：
     * - HashSet存储链表A的所有节点：O(m)
     *
     * @param headA 链表A的头节点
     * @param headB 链表B的头节点
     * @return 如果存在相交节点，返回相交节点；否则返回null
     */
    public static ListNode getIntersectionNodeByHashSet(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        // 使用HashSet存储链表A的所有节点
        java.util.Set<ListNode> visited = new java.util.HashSet<>();
        ListNode current = headA;
        while (current != null) {
            visited.add(current);
            current = current.next;
        }

        // 遍历链表B，检查节点是否在HashSet中
        current = headB;
        while (current != null) {
            if (visited.contains(current)) {
                return current;
            }
            current = current.next;
        }

        return null;
    }
}
