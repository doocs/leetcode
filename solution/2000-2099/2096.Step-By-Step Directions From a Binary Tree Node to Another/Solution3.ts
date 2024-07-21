/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */
export function getDirections(root: TreeNode | null, start: number, dest: number): string {
    const dfs = (node: TreeNode | null, x: number, path: string[] = []): boolean => {
        if (!node) return false;
        if (node.val === x) return true;

        path.push('L');
        if (dfs(node.left, x, path)) return true;

        path[path.length - 1] = 'R';
        if (dfs(node.right, x, path)) return true;
        path.pop();

        return false;
    };

    const startPath: string[] = [];
    const destPath: string[] = [];
    dfs(root, start, startPath);
    dfs(root, dest, destPath);

    let i = 0;
    while (startPath[i] === destPath[i]) i++;

    return (
        Array(startPath.length - i)
            .fill('U')
            .join('') + destPath.slice(i).join('')
    );
}
