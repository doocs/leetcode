package com.funian.algorithm.algorithm;

import java.util.*;

/**
 * 验证二叉搜索树（LeetCode 98）
 *
 * 时间复杂度：O(n)
 * - n是二叉树中的节点数
 * - 每个节点都需要被访问一次
 *
 * 空间复杂度：
 * - 方法1（递归）：O(h)
 *   h是二叉树的高度，递归调用栈的深度
 *   最坏情况下（完全不平衡的树）为O(n)，最好情况下（完全平衡的树）为O(log n)
 * - 方法2（迭代）：O(h)
 *   显式栈最多存储h个节点
 * - 方法3（中序遍历）：O(h)
 *   栈空间或递归调用栈
 */
public class IsvalidBST98 {

    /**
     * 二叉树节点定义
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 方法1：递归解法（带上下界约束）
     * <p>
     * 算法思路：
     * 对于二叉搜索树的每个节点，不仅要满足与父节点的大小关系，
     * 还要满足与所有祖先节点的大小关系。
     * 因此，我们需要为每个节点维护一个有效的取值范围（lower, upper）。
     * <p>
     * 执行过程分析（以二叉树 [5,1,4,null,null,3,6] 为例）：
     * <p>
     * 5
     * / \
     * 1   4
     * / \
     * 3   6
     * <p>
     * 递归调用过程：
     * isValidBST(root=5, lower=null, upper=null)
     * ├─ check(5, null, null) -> true
     * ├─ check(1, null, 5) -> true
     * ├─ check(4, 5, null) -> false (4 < 5 不满足上界约束)
     * └─ 返回 false
     * <p>
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)，其中n为二叉树节点数
     * <p>
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)，其中h为树的高度
     * - 最坏情况（链状树）：O(n)
     * - 最好情况（平衡树）：O(log n)
     *
     * @param root 二叉树的根节点
     * @return 如果是有效的二叉搜索树返回true，否则返回false
     */
    public boolean isValidBST(TreeNode root) {
        // 调用递归辅助方法，初始上下界为null
        return isValidBSTHelper(root, null, null);
    }

    /**
     * 递归辅助方法
     * <p>
     * 算法思路：
     * 1. 检查当前节点是否为空，为空则返回true
     * 2. 检查当前节点值是否在有效范围内
     * 3. 递归检查左右子树，更新相应的边界值
     * <p>
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)
     * <p>
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)
     *
     * @param node  当前节点
     * @param lower 下界（当前节点值必须大于下界）
     * @param upper 上界（当前节点值必须小于上界）
     * @return 如果以当前节点为根的子树是有效的BST返回true，否则返回false
     */
    private boolean isValidBSTHelper(TreeNode node, Integer lower, Integer upper) {
        // 基础情况：空节点被认为是有效的BST
        if (node == null) {
            return true;
        }

        // 检查当前节点是否满足约束条件
        int val = node.val;
        // 检查下界约束
        if (lower != null && val <= lower) {
            return false;
        }
        // 检查上界约束
        if (upper != null && val >= upper) {
            return false;
        }

        // 递归检查左右子树
        // 左子树：上界更新为当前节点值
        // 右子树：下界更新为当前节点值
        return isValidBSTHelper(node.left, lower, val)
                && isValidBSTHelper(node.right, val, upper);
    }

    /**
     * 方法2：中序遍历解法
     * <p>
     * 算法思路：
     * 二叉搜索树的中序遍历结果应该是严格递增的序列
     * 因此，我们可以通过中序遍历二叉树，并检查遍历结果是否严格递增
     * <p>
     * 执行过程分析（以二叉树 [2,1,3] 为例）：
     * <p>
     * 2
     * / \
     * 1   3
     * <p>
     * 中序遍历结果：[1, 2, 3]，严格递增，所以是有效的BST
     * <p>
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)
     * <p>
     * 空间复杂度分析：
     * - 显式栈最多存储树的高度个节点：O(h)
     * - 最坏情况：O(n)，最好情况：O(log n)
     *
     * @param root 二叉树的根节点
     * @return 如果是有效的二叉搜索树返回true，否则返回false
     */
    public boolean isValidBSTInorder(TreeNode root) {
        // 创建栈用于迭代中序遍历
        Stack<TreeNode> stack = new Stack<>();
        // 用于记录前一个访问的节点值
        Integer inorder = null;

        while (!stack.isEmpty() || root != null) {
            // 一直向左走，将路径上的节点入栈
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            // 弹出栈顶节点
            root = stack.pop();

            // 检查当前节点是否大于前一个节点
            if (inorder != null && root.val <= inorder) {
                return false;
            }
            inorder = root.val;

            // 处理右子树
            root = root.right;
        }

        return true;
    }

    /**
     * 方法3：递归中序遍历解法
     *
     * 算法思路：
     * 使用递归实现中序遍历，在遍历过程中检查节点值是否严格递增
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)
     *
     * @param root 二叉树的根节点
     * @return 如果是有效的二叉搜索树返回true，否则返回false
     */
    // 用于记录前一个访问的节点值
    private Integer prev;

    public boolean isValidBSTInorderRecursive(TreeNode root) {
        prev = null;
        return inorderHelper(root);
    }

    /**
     * 中序遍历辅助方法
     *
     * 算法思路：
     * 1. 递归遍历左子树
     * 2. 检查当前节点与前一个节点的关系
     * 3. 递归遍历右子树
     *
     * 时间复杂度分析：
     * - 每个节点访问一次：O(n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(h)
     *
     * @param root 当前节点
     * @return 如果以当前节点为根的子树满足BST性质返回true，否则返回false
     */
    private boolean inorderHelper(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 递归检查左子树
        if (!inorderHelper(root.left)) {
            return false;
        }

        // 检查当前节点
        if (prev != null && root.val <= prev) {
            return false;
        }
        prev = root.val;

        // 递归检查右子树
        return inorderHelper(root.right);
    }

    /**
     * 辅助方法：根据数组创建二叉树（用于测试）
     *
     * 算法思路：
     * 按层序遍历的方式构建二叉树
     * 使用队列来维护当前需要处理子节点的节点
     */
    public static TreeNode createTree() {
        // 创建Scanner对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);
        // 提示用户输入二叉树节点值
        System.out.println("请输入二叉树节点值，按层序遍历输入，null表示空节点，用空格分隔：");
        // 读取用户输入的一行
        String input = scanner.nextLine();
        // 按空格分割输入字符串得到节点值数组
        String[] values = input.split(" ");

        // 检查输入是否为空
        if (values.length == 0 || "null".equals(values[0]) || values[0].isEmpty()) {
            return null;
        }

        // 创建根节点
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        // 创建队列用于构建二叉树
        Queue<TreeNode> queue = new LinkedList<>();
        // 将根节点加入队列
        queue.offer(root);

        // 数组索引，从1开始处理子节点
        int i = 1;
        // 当队列不为空且索引未越界时继续处理
        while (!queue.isEmpty() && i < values.length) {
            // 从队列中取出一个节点
            TreeNode node = queue.poll();

            // 处理左子节点
            // 检查左子节点是否存在且不为null
            if (i < values.length && !"null".equals(values[i]) && !values[i].isEmpty()) {
                // 创建左子节点
                node.left = new TreeNode(Integer.parseInt(values[i]));
                // 将左子节点加入队列
                queue.offer(node.left);
            }
            // 索引递增
            i++;

            // 处理右子节点
            // 检查右子节点是否存在且不为null
            if (i < values.length && !"null".equals(values[i]) && !values[i].isEmpty()) {
                // 创建右子节点
                node.right = new TreeNode(Integer.parseInt(values[i]));
                // 将右子节点加入队列
                queue.offer(node.right);
            }
            // 索引递增
            i++;
        }

        // 返回构建完成的二叉树根节点
        return root;
    }

    /**
     * 辅助方法：中序遍历打印树结构
     *
     * 算法思路：
     * 按照左-根-右的顺序遍历二叉树并打印节点值
     */
    public static void printInorder(TreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(root.val + " ");
            printInorder(root.right);
        }
    }

    /**
     * 辅助方法：层序遍历打印二叉树
     *
     * 算法思路：
     * 使用队列进行层序遍历，依次打印每个节点的值
     */
    public static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("空树");
            return;
        }

        // 创建队列用于层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        // 将根节点加入队列
        queue.offer(root);
        // 打印提示信息
        System.out.print("层序遍历结果: ");

        while (!queue.isEmpty()) {
            // 从队列中取出一个节点
            TreeNode node = queue.poll();
            if (node == null) {
                System.out.print("null ");
            } else {
                System.out.print(node.val + " ");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        System.out.println();
    }

    /**
     * 主函数：处理用户输入并验证二叉搜索树
     *
     * 程序执行流程：
     * 1. 提示用户输入二叉树节点值
     * 2. 根据输入构建二叉树
     * 3. 打印构建的二叉树
     * 4. 调用三种方法验证BST
     * 5. 打印验证结果
     */
    public static void main(String[] args) {
        System.out.println("验证二叉搜索树");

        // 创建二叉树
        TreeNode root = createTree();

        // 检查创建的二叉树是否为空
        if (root == null) {
            // 如果为空，打印提示信息并退出
            System.out.println("创建的二叉树为空，空树被认为是有效的BST");
            System.out.println("结果: true");
            return;
        }

        // 打印创建的二叉树
        System.out.println("创建的二叉树:");
        printLevelOrder(root);

        // 打印中序遍历结果
        System.out.print("中序遍历结果: ");
        printInorder(root);
        System.out.println();

        // 验证BST
        // 创建解决方案实例
        IsvalidBST98 solution = new IsvalidBST98();
        boolean result1 = solution.isValidBST(root);
        boolean result2 = solution.isValidBSTInorder(root);
        boolean result3 = solution.isValidBSTInorderRecursive(root);

        // 打印结果
        System.out.println("方法1（递归+上下界）验证结果: " + result1);
        System.out.println("方法2（迭代中序遍历）验证结果: " + result2);
        System.out.println("方法3（递归中序遍历）验证结果: " + result3);
    }
}
