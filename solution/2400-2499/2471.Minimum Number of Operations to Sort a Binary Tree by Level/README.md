# [2471. 逐层排序二叉树所需的最少操作数目](https://leetcode.cn/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level)

[English Version](/solution/2400-2499/2471.Minimum%20Number%20of%20Operations%20to%20Sort%20a%20Binary%20Tree%20by%20Level/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>值互不相同</strong> 的二叉树的根节点 <code>root</code> 。</p>

<p>在一步操作中，你可以选择 <strong>同一层</strong> 上任意两个节点，交换这两个节点的值。</p>

<p>返回每一层按 <strong>严格递增顺序</strong> 排序所需的最少操作数目。</p>

<p>节点的 <strong>层数</strong> 是该节点和根节点之间的路径的边数。</p>

<p>&nbsp;</p>

<p><strong>示例 1 ：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2471.Minimum%20Number%20of%20Operations%20to%20Sort%20a%20Binary%20Tree%20by%20Level/images/image-20220918174006-2.png" style="width: 500px; height: 324px;">
<pre><strong>输入：</strong>root = [1,4,3,7,6,8,5,null,null,null,null,9,null,10]
<strong>输出：</strong>3
<strong>解释：</strong>
- 交换 4 和 3 。第 2 层变为 [3,4] 。
- 交换 7 和 5 。第 3 层变为 [5,6,8,7] 。
- 交换 8 和 7 。第 3 层变为 [5,6,7,8] 。
共计用了 3 步操作，所以返回 3 。
可以证明 3 是需要的最少操作数目。
</pre>

<p><strong>示例 2 ：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2471.Minimum%20Number%20of%20Operations%20to%20Sort%20a%20Binary%20Tree%20by%20Level/images/image-20220918174026-3.png" style="width: 400px; height: 303px;">
<pre><strong>输入：</strong>root = [1,3,2,7,6,5,4]
<strong>输出：</strong>3
<strong>解释：
</strong>- 交换 3 和 2 。第 2 层变为 [2,3] 。 
- 交换 7 和 4 。第 3 层变为 [4,6,5,7] 。 
- 交换 6 和 5 。第 3 层变为 [4,5,6,7] 。
共计用了 3 步操作，所以返回 3 。 
可以证明 3 是需要的最少操作数目。
</pre>

<p><strong>示例 3 ：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2471.Minimum%20Number%20of%20Operations%20to%20Sort%20a%20Binary%20Tree%20by%20Level/images/image-20220918174052-4.png" style="width: 400px; height: 274px;">
<pre><strong>输入：</strong>root = [1,2,3,4,5,6]
<strong>输出：</strong>0
<strong>解释：</strong>每一层已经按递增顺序排序，所以返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点的数目在范围 <code>[1, 10<sup>5</sup>]</code> 。</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
	<li>树中的所有值 <strong>互不相同</strong> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS + 离散化 + 元素交换**

我们先通过 `BFS` 遍历二叉树，找到每一层的节点值，然后对每一层的节点值进行排序，如果排序后的节点值与原节点值不同，则说明需要交换元素，交换元素的次数即为该层需要的操作数。

时间复杂度 $O(n\times \log n)$。其中 $n$ 为二叉树的节点数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def minimumOperations(self, root: Optional[TreeNode]) -> int:
        def swap(arr, i, j):
            arr[i], arr[j] = arr[j], arr[i]

        def f(t):
            n = len(t)
            m = {v: i for i, v in enumerate(sorted(t))}
            for i in range(n):
                t[i] = m[t[i]]
            ans = 0
            for i in range(n):
                while t[i] != i:
                    swap(t, i, t[i])
                    ans += 1
            return ans

        q = deque([root])
        ans = 0
        while q:
            t = []
            for _ in range(len(q)):
                node = q.popleft()
                t.append(node.val)
                if node.left:
                    q.append(node.left)
                if node.right:
                    q.append(node.right)
            ans += f(t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int minimumOperations(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int ans = 0;
        while (!q.isEmpty()) {
            List<Integer> t = new ArrayList<>();
            for (int n = q.size(); n > 0; --n) {
                TreeNode node = q.poll();
                t.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            ans += f(t);
        }
        return ans;
    }

    private int f(List<Integer> t) {
        int n = t.size();
        List<Integer> alls = new ArrayList<>(t);
        alls.sort((a, b) -> a - b);
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            m.put(alls.get(i), i);
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = m.get(t.get(i));
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            while (arr[i] != i) {
                swap(arr, i, arr[i]);
                ++ans;
            }
        }
        return ans;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
```

### **C++**

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    int minimumOperations(TreeNode* root) {
        queue<TreeNode*> q{{root}};
        int ans = 0;
        auto f = [](vector<int>& t) {
            int n = t.size();
            vector<int> alls(t.begin(), t.end());
            sort(alls.begin(), alls.end());
            unordered_map<int, int> m;
            int ans = 0;
            for (int i = 0; i < n; ++i) m[alls[i]] = i;
            for (int i = 0; i < n; ++i) t[i] = m[t[i]];
            for (int i = 0; i < n; ++i) {
                while (t[i] != i) {
                    swap(t[i], t[t[i]]);
                    ++ans;
                }
            }
            return ans;
        };
        while (!q.empty()) {
            vector<int> t;
            for (int n = q.size(); n; --n) {
                auto node = q.front();
                q.pop();
                t.emplace_back(node->val);
                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);
            }
            ans += f(t);
        }
        return ans;
    }
};
```

### **Go**

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func minimumOperations(root *TreeNode) (ans int) {
	f := func(t []int) int {
		var alls []int
		for _, v := range t {
			alls = append(alls, v)
		}
		sort.Ints(alls)
		m := make(map[int]int)
		for i, v := range alls {
			m[v] = i
		}
		for i := range t {
			t[i] = m[t[i]]
		}
		res := 0
		for i := range t {
			for t[i] != i {
				t[i], t[t[i]] = t[t[i]], t[i]
				res++
			}
		}
		return res
	}

	q := []*TreeNode{root}
	for len(q) > 0 {
		t := []int{}
		for n := len(q); n > 0; n-- {
			node := q[0]
			q = q[1:]
			t = append(t, node.Val)
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		ans += f(t)
	}
	return
}
```

### **TypeScript**

```ts
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

function minimumOperations(root: TreeNode | null): number {
    const queue = [root];
    let ans = 0;
    while (queue.length !== 0) {
        const n = queue.length;
        const row: number[] = [];
        for (let i = 0; i < n; i++) {
            const { val, left, right } = queue.shift();
            row.push(val);
            left && queue.push(left);
            right && queue.push(right);
        }
        for (let i = 0; i < n - 1; i++) {
            let minIdx = i;
            for (let j = i + 1; j < n; j++) {
                if (row[j] < row[minIdx]) {
                    minIdx = j;
                }
            }
            if (i !== minIdx) {
                [row[i], row[minIdx]] = [row[minIdx], row[i]];
                ans++;
            }
        }
    }
    return ans;
}
```

### **Rust**

```rust
// Definition for a binary tree node.
// #[derive(Debug, PartialEq, Eq)]
// pub struct TreeNode {
//   pub val: i32,
//   pub left: Option<Rc<RefCell<TreeNode>>>,
//   pub right: Option<Rc<RefCell<TreeNode>>>,
// }
//
// impl TreeNode {
//   #[inline]
//   pub fn new(val: i32) -> Self {
//     TreeNode {
//       val,
//       left: None,
//       right: None
//     }
//   }
// }
use std::rc::Rc;
use std::cell::RefCell;
use std::collections::VecDeque;
impl Solution {
    pub fn minimum_operations(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        let mut queue = VecDeque::new();
        queue.push_back(root);
        let mut ans = 0;
        while !queue.is_empty() {
            let n = queue.len();
            let mut row = Vec::new();
            for _ in 0..n {
                let mut node = queue.pop_front().unwrap();
                let mut node = node.as_mut().unwrap().borrow_mut();
                row.push(node.val);
                if node.left.is_some() {
                    queue.push_back(node.left.take());
                }
                if node.right.is_some() {
                    queue.push_back(node.right.take());
                }
            }
            for i in 0..n - 1 {
                let mut min_idx = i;
                for j in i + 1..n {
                    if row[j] < row[min_idx] {
                        min_idx = j;
                    }
                }
                if i != min_idx {
                    row.swap(i, min_idx);
                    ans += 1;
                }
            }
        }
        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
