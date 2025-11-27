package com.funian.algorithm.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表检测算法（LeetCode 141）
 *
 * 时间复杂度：
 * - 方法1（哈希表）：O(n)
 *   每个节点最多访问一次，哈希表查找和插入操作平均时间复杂度为O(1)
 * - 方法2（快慢指针）：O(n)
 *   在最坏情况下，快指针需要遍历整个链表一次
 *
 * 空间复杂度：
 * - 方法1（哈希表）：O(n)
 *   需要存储所有访问过的节点
 * - 方法2（快慢指针）：O(1)
 *   只使用了常数个额外变量
 */
public class HaveCycleList141 {

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
     * 方法1：使用哈希表检测环
     *
     * 算法思路：
     * 遍历链表，将访问过的节点存储在哈希表中
     * 如果遇到已经存在于哈希表中的节点，说明有环
     * 如果遍历到null，说明无环
     *
     * 执行过程分析（以链表 1->2->3->4->2 为例，4指向2形成环）：
     * 1. 访问节点1，哈希表：{1}
     * 2. 访问节点2，哈希表：{1,2}
     * 3. 访问节点3，哈希表：{1,2,3}
     * 4. 访问节点4，哈希表：{1,2,3,4}
     * 5. 再次访问节点2，发现已存在于哈希表中，返回true
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
     * @return 如果有环返回true，否则返回false
     */
    public boolean hasCycleWithHashSet(ListNode head) {
        // 使用HashSet存储访问过的节点
        Set<ListNode> visitedNodes = new HashSet<>();

        // 从头节点开始遍历
        ListNode current = head;
        while (current != null) {
            // 如果当前节点已在集合中，说明有环
            if (visitedNodes.contains(current)) {
                return true;
            }

            // 将当前节点加入集合
            visitedNodes.add(current);

            // 移动到下一个节点
            current = current.next;
        }

        // 遍历完成未发现环，返回false
        return false;
    }

    /**
     * 方法2：使用快慢指针（Floyd判圈算法）
     *
     * 算法思路：
     * 使用两个指针，快指针每次移动两步，慢指针每次移动一步
     * 如果链表有环，快指针最终会追上慢指针
     * 如果链表无环，快指针会先到达链表末尾
     *
     * 执行过程分析（以链表 1->2->3->4->2 为例，4指向2形成环）：
     * 初始：slow=1, fast=1
     * 第1步：slow=2, fast=3
     * 第2步：slow=3, fast=2 (环中)
     * 第3步：slow=4, fast=4 (相遇，返回true)
     *
     * 数学原理：
     * 假设链表头到环入口距离为a，环长度为b
     * 当慢指针进入环时，快指针已在环中
     * 快指针追赶慢指针，每次缩短1步距离
     * 最多经过b步后两者相遇
     *
     * 时间复杂度分析：
     * - 快指针遍历链表：O(n)，其中n为链表节点数
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用两个指针变量：O(1)
     *
     * @param head 链表的头节点
     * @return 如果有环返回true，否则返回false
     */
    public boolean hasCycleWithTwoPointers(ListNode head) {
        // 边界情况：空链表或只有一个节点
        if (head == null || head.next == null) {
            return false;
        }

        // 初始化快慢指针
        ListNode slow = head;
        ListNode fast = head;

        // 循环直到快指针到达链表末尾
        while (fast != null && fast.next != null) {
            // 慢指针前进一步
            slow = slow.next;
            // 快指针前进两步
            fast = fast.next.next;

            // 如果快慢指针相遇，说明有环
            if (slow == fast) {
                return true;
            }
        }

        // 快指针到达末尾，说明无环
        return false;
    }

    /**
     * 测试方法和使用示例
     */
    public static void main(String[] args) {
        HaveCycleList141 solution = new HaveCycleList141();

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
        System.out.println("无环链表检测结果（哈希表）: " + solution.hasCycleWithHashSet(head1));
        System.out.println("无环链表检测结果（快慢指针）: " + solution.hasCycleWithTwoPointers(head1));

        // 测试有环链表
        System.out.println("有环链表检测结果（哈希表）: " + solution.hasCycleWithHashSet(head2));
        System.out.println("有环链表检测结果（快慢指针）: " + solution.hasCycleWithTwoPointers(head2));
    }

    /**
     * 方法3：修改节点值标记法（破坏性方法）
     *
     * 算法思路：
     * 遍历链表，将访问过的节点值修改为特殊标记值
     * 如果遇到已被标记的节点，说明有环
     *
     * 注意：这种方法会修改原链表的节点值，实际应用中不推荐
     *
     * 执行过程分析（以链表 1->2->3->4->2 为例，4指向2形成环）：
     * 1. 访问节点1，标记为MAX_VALUE，链表变为 MAX_VALUE->2->3->4->2
     * 2. 访问节点2，标记为MAX_VALUE，链表变为 MAX_VALUE->MAX_VALUE->3->4->2
     * 3. 访问节点3，标记为MAX_VALUE，链表变为 MAX_VALUE->MAX_VALUE->MAX_VALUE->4->2
     * 4. 访问节点4，标记为MAX_VALUE，链表变为 MAX_VALUE->MAX_VALUE->MAX_VALUE->MAX_VALUE->2
     * 5. 再次访问节点2，发现值为MAX_VALUE，返回true
     *
     * 时间复杂度分析：
     * - 遍历链表：O(n)，其中n为链表节点数
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param head 链表的头节点
     * @return 如果有环返回true，否则返回false
     */
    public boolean hasCycleByMarking(ListNode head) {
        // 特殊标记值，需要确保不会与原链表中的值冲突
        final int MARK = Integer.MAX_VALUE;

        ListNode current = head;
        while (current != null) {
            // 如果当前节点已被标记，说明有环
            if (current.val == MARK) {
                return true;
            }

            // 标记当前节点
            current.val = MARK;

            // 移动到下一个节点
            current = current.next;
        }

        // 遍历完成未发现环，返回false
        return false;
    }

    /**
     * 方法4：使用异常处理检测环（投机取巧）
     *
     * 算法思路：
     * 利用链表有环时toString()方法会产生StackOverflowError的特性
     *
     * 注意：这种方法不推荐在生产环境中使用
     *
     * 执行过程分析：
     * 1. 对于无环链表：toString()方法正常执行，返回false
     * 2. 对于有环链表：toString()方法递归调用导致栈溢出，捕获异常返回true
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
     * @return 如果有环返回true，否则返回false
     */
    public boolean hasCycleByToString(ListNode head) {
        try {
            // 尝试调用toString方法，有环时会抛出StackOverflowError
            head.toString();
            return false;
        } catch (StackOverflowError e) {
            // 捕获到栈溢出错误，说明有环
            return true;
        }
    }
}
