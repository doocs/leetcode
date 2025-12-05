package com.funian.algorithm.algorithm;

import java.rmi.server.RemoteRef;
import java.util.*;

/**
 * 二叉树的中序遍历（LeetCode 94）
 *
 * 时间复杂度：O(n)
 * - n是二叉树中的节点数
 * - 每个节点都需要被访问一次
 *
 * 空间复杂度：
 * - 递归解法：O(h)
 *   h是二叉树的高度，递归调用栈的深度
 *   最坏情况下（完全不平衡的树）为O(n)，最好情况下（完全平衡的树）为O(log n)
 * - 迭代解法：O(h)
 *   显式栈最多存储h个节点
 */
public class InOrderTraversal94 {

    /**
     * 二叉树节点定义
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    /**
     * 方法1：递归解法
     *
     * 算法思路：
     * 中序遍历的顺序是：左子树 -> 根节点 -> 右子树
     * 使用递归实现，先递归遍历左子树，再访问根节点，最后递归遍历右子树
     *
     * 执行过程分析（以二叉树 [1,null,2,3] 为例）：
     *
     *     1
     *      \
     *       2
     *      /
     *     3
     *
     * 递归调用过程：
     * inorderTraversal(1)
     * ├─ inorderTraversal(null) -> 返回 []
     * ├─ 访问节点1 -> result=[1]
     * └─ inorderTraversal(2)
     *    ├─ inorderTraversal(3)
     *    │  ├─ inorderTraversal(null) -> 返回 []
     *    │  ├─ 访问节点3 -> result=[1,3]
     *    │  └─ inorderTraversal(null) -> 返回 []
     *    ├─ 访问节点2 -> result=[1,3,2]
     *    └─ inorderTraversal(null) -> 返回 []
     *
     * 最终结果：[1,3,2]
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)，其中n为节点数
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)，其中h为树的高度
     * - 最坏情况（链状树）：O(n)
     * - 最好情况（平衡树）：O(log n)
     *
     * @param root 二叉树的根节点
     * @return 中序遍历的结果列表
     */
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        // 创建结果列表
        List<Integer> result = new ArrayList<>();
        // 调用递归辅助方法
        inorderHelper(root, result);
        // 返回结果列表
        return result;
    }

    /**
     * 递归辅助方法
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)
     *
     * @param root 当前节点
     * @param result 结果列表
     */
    private void inorderHelper(TreeNode root, List<Integer> result) {
        // 基础情况：如果节点为空，直接返回
        if (root == null) {
            return;
        }

        // 递归遍历左子树
        inorderHelper(root.left, result);

        // 访问根节点
        result.add(root.val);

        // 递归遍历右子树
        inorderHelper(root.right, result);
    }

    /**
     * 方法2：迭代解法
     *
     * 算法思路：
     * 使用显式栈模拟递归过程
     * 1. 从根节点开始，沿着左子树一直向下，将路径上的节点入栈
     * 2. 弹出栈顶节点并访问，然后处理其右子树
     * 3. 重复上述过程直到栈为空且当前节点为空
     *
     * 执行过程分析（以二叉树 [1,null,2,3] 为例）：
     *
     *     1
     *      \
     *       2
     *      /
     *     3
     *
     * 执行步骤：
     * 1. current=1, stack=[]
     *    1->left=null，所以弹出1，result=[1]，current=1->right=2
     *
     * 2. current=2, stack=[]
     *    2->left=3，入栈，stack=[2]，current=3
     *
     * 3. current=3, stack=[2]
     *    3->left=null，所以弹出3，result=[1,3]，current=3->right=null
     *
     * 4. current=null, stack=[2]
     *    弹出2，result=[1,3,2]，current=2->right=null
     *
     * 5. current=null, stack=[]
     *    循环结束
     *
     * 最终结果：[1,3,2]
     *
     * 时间复杂度分析：
     * - 每个节点入栈和出栈一次：O(n)，其中n为节点数
     *
     * 空间复杂度分析：
     * - 栈最多存储树的高度个节点：O(h)
     * - 最坏情况：O(n)，最好情况：O(log n)
     *
     * @param root 二叉树的根节点
     * @return 中序遍历的结果列表
     */
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        // 创建结果列表
        List<Integer> result = new ArrayList<>();
        // 创建栈用于模拟递归
        Stack<TreeNode> stack = new Stack<>();
        // current 当前节点指针，初始指向根节点
        TreeNode current = root;

        // 当栈不为空或当前节点不为空时继续循环
        while (current != null || !stack.isEmpty()) {
            // 一直向左走，将路径上的节点入栈
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // 弹出栈顶节点并访问
            current = stack.pop();
            result.add(current.val);

            // 处理右子树
            current = current.right;
        }

        // 返回结果列表
        return result;
    }

    /**
     * 方法3：Morris遍历（线索二叉树）
     *
     * 算法思路：
     * 利用叶子节点的空指针建立线索，避免使用栈或递归
     * 1. 如果当前节点无左子树，访问该节点并移向右子树
     * 2. 如果当前节点有左子树，找到其在中序遍历中的前驱节点
     *    - 如果前驱节点的右指针为空，将其指向当前节点，然后移向左子树
     *    - 如果前驱节点的右指针指向当前节点，说明左子树已遍历完，断开连接，访问当前节点，移向右子树
     *
     * 时间复杂度分析：
     * - 每个节点最多被访问3次：O(n)，其中n为节点数
     *
     * 空间复杂度分析：
     * - 只使用常数额外空间：O(1)
     *
     * @param root 二叉树的根节点
     * @return 中序遍历的结果列表
     */
    public List<Integer> inorderTraversalMorris(TreeNode root) {
        // 创建结果列表
        List<Integer> result = new ArrayList<>();
        // current 当前节点指针，初始指向根节点
        TreeNode current = root;

        while (current != null) {
            if (current.left == null) {
                // 如果没有左子树，访问当前节点并移向右子树
                result.add(current.val);
                current = current.right;
            } else {
                // 找到中序遍历中的前驱节点
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    // 建立线索
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // 断开线索，访问节点，移向右子树
                    predecessor.right = null;
                    result.add(current.val);
                    current = current.right;
                }
            }
        }

        // 返回结果列表
        return result;
    }

    /**
     * 辅助方法：根据数组创建二叉树（用于测试）
     * 注意：这里采用层序遍历的输入方式，null表示空节点
     */
    public TreeNode createTree(Scanner scanner) {
        // 提示用户输入
        System.out.println("请输入二叉树节点值，按层序遍历输入，null表示空节点，用空格分隔：");
        // 读取一行输入
        String input = scanner.nextLine();
        // 按空格分割字符串得到字符串数组
        String[] values = input.split(" ");

        if (values.length == 0 || "null".equals(values[0])) {
            return null;
        }

        // 创建根节点
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        // 创建队列用于层序遍历
        Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);

        // i 数组索引
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode node = queue.poll();

            // 处理左子节点
            if (i < values.length && !"null".equals(values[i])) {
                node.left = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(node.left);
            }
            i++;

            // 处理右子节点
            if (i < values.length && !"null".equals(values[i])) {
                node.right = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(node.right);
            }
            i++;
        }

        // 返回根节点
        return root;
    }

    /**
     * 辅助方法：打印遍历结果
     */
    public void printTraversalResult(List<Integer> result, String method) {
        System.out.println(method + "遍历结果: " + result);
    }

    /**
     * 主函数：处理用户输入并演示中序遍历
     */
    public static void main(String[] args) {
        // 创建解决方案实例
        InOrderTraversal94 solution = new InOrderTraversal94();
        // 创建Scanner对象读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 创建二叉树
        TreeNode root = solution.createTree(scanner);

        if (root == null) {
            System.out.println("创建的二叉树为空");
            return;
        }

        System.out.println("二叉树创建成功");

        // 演示三种遍历方法
        while (true) {
            // 打印操作选项
            System.out.println("\n请选择遍历方法:");
            System.out.println("1. 递归解法");
            System.out.println("2. 迭代解法");
            System.out.println("3. Morris遍历");
            System.out.println("4. 退出");
            System.out.print("请输入选项(1-4): ");

            // 读取用户选择
            int choice = scanner.nextInt();
            scanner.nextLine();

            List<Integer> result;
            // 根据用户选择执行相应操作
            switch (choice) {
                case 1:
                    result = solution.inorderTraversalRecursive(root);
                    solution.printTraversalResult(result, "递归");
                    break;

                case 2:
                    result = solution.inorderTraversalIterative(root);
                    solution.printTraversalResult(result, "迭代");
                    break;

                case 3:
                    result = solution.inorderTraversalMorris(root);
                    solution.printTraversalResult(result, "Morris");
                    break;

                case 4:
                    // 退出程序
                    System.out.println("退出程序");
                    scanner.close();
                    return;

                default:
                    // 处理无效选项
                    System.out.println("无效选项，请重新输入");
            }
        }
    }
}
