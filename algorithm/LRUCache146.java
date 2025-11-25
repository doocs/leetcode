package com.funian.algorithm.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * LRU缓存机制（LeetCode 146）
 *
 * 时间复杂度：O(1)
 * - get和put操作的平均时间复杂度都是O(1)
 * - 哈希表的查找、插入和删除操作平均时间复杂度为O(1)
 * - 双向链表的插入和删除操作时间复杂度为O(1)
 *
 * 空间复杂度：O(capacity)
 * - 哈希表和双向链表最多存储capacity个节点
 */
public class LRUCache146 {

    /**
     * 双向链表节点定义
     */
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode() {}

        public DLinkedNode(int _key, int _value) {
            key = _key;
            value = _value;
        }
    }

    // 哈希表：用于快速定位节点
    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;       // 当前缓存大小
    private int capacity;   // 缓存容量
    private DLinkedNode head, tail; // 双向链表的伪头节点和伪尾节点

    /**
     * 构造函数：初始化LRU缓存
     *
     * @param capacity 缓存容量
     */
    public LRUCache146(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        // 使用伪头部和伪尾部节点简化链表操作
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 获取缓存中key对应的value
     *
     * 算法思路：
     * 1. 如果key不存在，返回-1
     * 2. 如果key存在，通过哈希表定位节点，将其移到链表头部，并返回value
     *
     * @param key 要获取的键
     * @return 对应的值，如果不存在返回-1
     */
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }

        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    /**
     * 向缓存中添加或更新键值对
     *
     * 算法思路：
     * 1. 如果key不存在：
     *    - 创建新节点
     *    - 添加到哈希表
     *    - 添加到链表头部
     *    - 如果超出容量，删除链表尾部节点和哈希表中对应项
     * 2. 如果key存在：
     *    - 更新value
     *    - 将节点移到链表头部
     *
     * @param key 键
     * @param value 值
     */
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);

            // 添加进哈希表和双向链表的头部
            cache.put(key, newNode);
            addToHead(newNode);
            ++size;

            // 检查是否超出容量
            if (size > capacity) {
                // 删除双向链表的尾部节点及哈希表中对应项
                DLinkedNode tail = removeTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    /**
     * 辅助方法：将节点添加到链表头部
     *
     * @param node 要添加的节点
     */
    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * 辅助方法：从链表中删除指定节点
     *
     * @param node 要删除的节点
     */
    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 辅助方法：将节点移动到链表头部
     *
     * @param node 要移动的节点
     */
    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    /**
     * 辅助方法：删除链表尾部节点
     *
     * @return 被删除的节点
     */
    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    /**
     * 辅助方法：打印当前缓存状态
     */
    public void printCache() {
        System.out.print("当前缓存内容（从最近使用到最久未使用）: ");
        DLinkedNode current = head.next;
        while (current != tail) {
            System.out.print("(" + current.key + "," + current.value + ") ");
            current = current.next;
        }
        System.out.println();
        System.out.println("当前缓存大小: " + size + "/" + capacity);
    }

    /**
     * 主函数：处理用户输入并演示LRU缓存操作
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入LRU缓存的容量: ");
        int capacity = scanner.nextInt();
        LRUCache146 lruCache = new LRUCache146(capacity);

        System.out.println("LRU缓存初始化完成，容量为: " + capacity);

        while (true) {
            System.out.println("\n请选择操作:");
            System.out.println("1. put(key, value) - 添加或更新缓存");
            System.out.println("2. get(key) - 获取缓存值");
            System.out.println("3. 查看当前缓存状态");
            System.out.println("4. 退出");
            System.out.print("请输入选项(1-4): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("请输入key: ");
                    int putKey = scanner.nextInt();
                    System.out.print("请输入value: ");
                    int putValue = scanner.nextInt();
                    lruCache.put(putKey, putValue);
                    System.out.println("执行 put(" + putKey + ", " + putValue + ")");
                    break;

                case 2:
                    System.out.print("请输入key: ");
                    int getKey = scanner.nextInt();
                    int result = lruCache.get(getKey);
                    if (result == -1) {
                        System.out.println("执行 get(" + getKey + ")，结果: 未找到");
                    } else {
                        System.out.println("执行 get(" + getKey + ")，结果: " + result);
                    }
                    break;

                case 3:
                    lruCache.printCache();
                    break;

                case 4:
                    System.out.println("退出程序");
                    scanner.close();
                    return;

                default:
                    System.out.println("无效选项，请重新输入");
            }
        }
    }
}
