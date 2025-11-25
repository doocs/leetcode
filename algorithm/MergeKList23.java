package com.funian.algorithm.algorithm;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 合并K个升序链表（LeetCode 23）
 *
 * 时间复杂度：O(N * log k)
 * - N是所有节点的总数，k是链表的数量
 * - 每个节点需要进行一次插入和删除操作，复杂度为O(log k)
 *
 * 空间复杂度：O(k)
 * - 优先队列最多存储k个节点
 */
public class MergeKList23 {

    /**
     * 链表节点定义
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 使用优先队列（最小堆）合并K个升序链表
     *
     * 算法思路：
     * 1. 将K个链表的头节点放入最小堆中
     * 2. 每次取出值最小的节点，加入结果链表
     * 3. 然后将该节点的下一个节点（如果存在）加入堆中
     * 4. 重复步骤2-3直到堆为空
     *
     * 执行过程分析（以 lists = [[1,4,5],[1,3,4],[2,6]] 为例）：
     *
     * 初始状态：
     * 堆：{1(来自list1), 1(来自list2), 2(来自list3)}
     * result: null
     *
     * 第1次操作：取出1(来自list1)
     * 堆：{1(来自list2), 2(来自list3), 4(来自list1)}
     * result: 1
     *
     * 第2次操作：取出1(来自list2)
     * 堆：{2(来自list3), 3(来自list2), 4(来自list1)}
     * result: 1->1
     *
     * 第3次操作：取出2(来自list3)
     * 堆：{3(来自list2), 4(来自list1), 6(来自list3)}
     * result: 1->1->2
     *
     * 以此类推，直到堆为空
     * 最终结果：1->1->2->3->4->4->5->6
     *
     * 时间复杂度分析：
     * - 初始化堆：O(k)，其中k为链表数量
     * - 每个节点的插入和删除操作：O(log k)
     * - 总共有N个节点，总时间复杂度：O(N * log k)
     *
     * 空间复杂度分析：
     * - 优先队列存储最多k个节点：O(k)
     * - 虚拟头节点和指针变量：O(1)
     * - 总空间复杂度：O(k)
     *
     * @param lists K个升序链表的数组
     * @return 合并后的升序链表
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // 使用优先队列（最小堆）来帮助合并链表
        // 比较器定义：按照节点值进行比较，构建最小堆
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 将所有链表的头节点加入堆中（只加入非空节点）
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        // 创建一个虚拟头节点，用于构建结果链表
        // 使用虚拟头节点可以简化链表操作，避免处理头节点的特殊情况
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // 从堆中取出最小节点并加入结果链表
        // 当堆不为空时继续处理
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            current.next = node;
            current = current.next;

            // 如果该节点还有下一个节点，则将下一个节点加入堆中
            // 这样可以保证每个链表的节点都能被处理
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }

        // 返回合并后的链表头（跳过虚拟头节点）
        return dummy.next;
    }

    /**
     * 主函数：处理用户输入并输出合并结果
     *
     * 算法流程：
     * 1. 读取用户输入的链表数量
     * 2. 依次读取每个链表的节点数和节点值
     * 3. 调用mergeKLists方法合并所有链表
     * 4. 输出合并后的链表
     */
    public static void main(String[] args) {
        // 注意：原代码中这里有个错误，应该是MergeKList23而不是Solution
        // 创建解决方案实例
        MergeKList23 solution = new MergeKList23();
        // 创建Scanner对象读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 读取链表的数量
        System.out.print("请输入链表的数量：");
        // 读取链表数量
        int k = scanner.nextInt();

        // 创建链表数组
        ListNode[] lists = new ListNode[k];

        // 依次读取每个链表的信息
        for (int i = 0; i < k; i++) {
            // 读取第i+1个链表的节点数
            System.out.print("请输入第 " + (i + 1) + " 个链表的节点数：");
            // 读取节点数
            int n = scanner.nextInt();

            // 创建哑节点简化链表构建过程
            ListNode dummy = new ListNode(0);
            ListNode current = dummy;

            // 读取第i+1个链表的节点值
            System.out.print("请输入第 " + (i + 1) + " 个链表的节点值（空格分隔）：");
            for (int j = 0; j < n; j++) {
                current.next = new ListNode(scanner.nextInt());
                current = current.next;
            }

            // 将构建好的链表赋值给链表数组
            lists[i] = dummy.next;
        }

        // 调用合并方法合并所有链表
        ListNode mergedList = solution.mergeKLists(lists);

        // 打印合并后的链表
        System.out.print("合并后的链表为：");
        while (mergedList != null) {
            System.out.print(mergedList.val + " ");
            mergedList = mergedList.next;
        }
        System.out.println();
    }
}
