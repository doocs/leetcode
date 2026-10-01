---
comments: true
difficulty: Medium
tags:
    - Tree
    - Array
    - Hash Table
    - Divide and Conquer
    - Binary Tree
---

<!-- problem:start -->

# [105. Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal)

[中文文档](/solution/0100-0199/0105.Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal/README.md)

## Mô tả

<!-- description:start -->

<p>Cho hai mảng số nguyên <code>preorder</code> và <code>inorder</code>, trong đó <code>preorder</code> là phép duyệt preorder của một cây nhị phân còn <code>inorder</code> là phép duyệt inorder của cùng cây đó, hãy xây dựng và trả về <em>cây nhị phân</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0105.Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal/images/tree.jpg" style="width: 277px; height: 302px;" />
<pre>
<strong>Đầu vào:</strong> preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
<strong>Đầu ra:</strong> [3,9,20,null,null,15,7]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> preorder = [-1], inorder = [-1]
<strong>Đầu ra:</strong> [-1]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= preorder.length &lt;= 3000</code></li>
	<li><code>inorder.length == preorder.length</code></li>
	<li><code>-3000 &lt;= preorder[i], inorder[i] &lt;= 3000</code></li>
	<li><code>preorder</code> và <code>inorder</code> chỉ gồm các giá trị <strong>duy nhất</strong>.</li>
	<li>Mỗi giá trị của <code>inorder</code> cũng xuất hiện trong <code>preorder</code>.</li>
	<li><code>preorder</code> được <strong>đảm bảo</strong> là phép duyệt preorder của cây.</li>
	<li><code>inorder</code> được <strong>đảm bảo</strong> là phép duyệt inorder của cây.</li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Bảng băm + Đệ quy

<!-- thinking:start -->

> **Tư duy**
>
> Preorder cho biết thứ tự của các nút gốc; inorder chia mỗi gốc thành các cây con trái và phải. Nếu mỗi lần đều quét inorder để tìm gốc thì chi phí có thể là $O(n^2)$; $n \le 3000$ là khá sát.
>
> Các giá trị là duy nhất, nên chúng ta ánh xạ mỗi giá trị trong inorder tới chỉ số của nó một lần. Giá trị đầu tiên của lát cắt preorder hiện tại là gốc; mọi phần tử bên trái nó trong inorder là cây con trái, phần còn lại là cây con phải. Độ dài của chúng xác định hai lát cắt đệ quy tiếp theo.

<!-- thinking:end -->

Nút đầu tiên $preorder[0]$ trong dãy preorder là nút gốc. Chúng ta tìm vị trí $k$ của nút gốc trong dãy inorder, vị trí này chia dãy inorder thành cây con trái $inorder[0..k]$ và cây con phải $inorder[k+1..]$.

Thông qua các khoảng của cây con trái và phải, chúng ta có thể tính số nút trong cây con trái và phải, lần lượt giả sử là $a$ và $b$. Khi đó trong các nút preorder, $a$ nút sau nút gốc là cây con trái, còn $b$ nút tiếp theo là cây con phải.

Vì vậy, chúng ta thiết kế hàm $dfs(i, j, n)$, trong đó $i$ và $j$ lần lượt biểu diễn vị trí bắt đầu của dãy preorder và dãy inorder, còn $n$ biểu diễn số nút. Giá trị trả về của hàm là cây nhị phân được xây dựng từ $preorder[i..i+n-1]$ làm dãy preorder và $inorder[j..j+n-1]$ làm dãy inorder.

Quá trình thực thi hàm $dfs(i, j, n)$ như sau:

- Nếu $n \leq 0$, nghĩa là không có nút nào, hãy trả về một nút rỗng.
- Lấy nút đầu tiên $v = preorder[i]$ của dãy preorder làm nút gốc, sau đó dùng bảng băm $d$ để tìm vị trí $k$ của nút gốc trong dãy inorder. Khi đó số nút trong cây con trái là $k - j$, còn số nút trong cây con phải là $n - k + j - 1$.
- Đệ quy xây dựng cây con trái $l = dfs(i + 1, j, k - j)$ và cây con phải $r = dfs(i + 1 + k - j, k + 1, n - k + j - 1)$.
- Cuối cùng, trả về cây nhị phân có $v$ là nút gốc, còn $l$ và $r$ lần lượt là các cây con trái và phải.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$. Trong đó, $n$ là số nút trong cây nhị phân.

<!-- tabs:start -->

#### Python3

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        def dfs(i: int, j: int, n: int) -> Optional[TreeNode]:
            if n <= 0:
                return None
            v = preorder[i]
            k = d[v]
            l = dfs(i + 1, j, k - j)
            r = dfs(i + 1 + k - j, k + 1, n - k + j - 1)
            return TreeNode(v, l, r)

        d = {v: i for i, v in enumerate(inorder)}
        return dfs(0, 0, len(preorder))
```

#### Java

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
    private int[] preorder;
    private Map<Integer, Integer> d = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        this.preorder = preorder;
        for (int i = 0; i < n; ++i) {
            d.put(inorder[i], i);
        }
        return dfs(0, 0, n);
    }

    private TreeNode dfs(int i, int j, int n) {
        if (n <= 0) {
            return null;
        }
        int v = preorder[i];
        int k = d.get(v);
        TreeNode l = dfs(i + 1, j, k - j);
        TreeNode r = dfs(i + 1 + k - j, k + 1, n - 1 - (k - j));
        return new TreeNode(v, l, r);
    }
}
```

#### C++

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
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        int n = preorder.size();
        unordered_map<int, int> d;
        for (int i = 0; i < n; ++i) {
            d[inorder[i]] = i;
        }
        function<TreeNode*(int, int, int)> dfs = [&](int i, int j, int n) -> TreeNode* {
            if (n <= 0) {
                return nullptr;
            }
            int v = preorder[i];
            int k = d[v];
            TreeNode* l = dfs(i + 1, j, k - j);
            TreeNode* r = dfs(i + 1 + k - j, k + 1, n - 1 - (k - j));
            return new TreeNode(v, l, r);
        };
        return dfs(0, 0, n);
    }
};
```

#### Go

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func buildTree(preorder []int, inorder []int) *TreeNode {
	d := map[int]int{}
	for i, x := range inorder {
		d[x] = i
	}
	var dfs func(i, j, n int) *TreeNode
	dfs = func(i, j, n int) *TreeNode {
		if n <= 0 {
			return nil
		}
		v := preorder[i]
		k := d[v]
		l := dfs(i+1, j, k-j)
		r := dfs(i+1+k-j, k+1, n-1-(k-j))
		return &TreeNode{v, l, r}
	}
	return dfs(0, 0, len(preorder))
}
```

#### TypeScript

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

function buildTree(preorder: number[], inorder: number[]): TreeNode | null {
    const d: Map<number, number> = new Map();
    const n = inorder.length;
    for (let i = 0; i < n; ++i) {
        d.set(inorder[i], i);
    }
    const dfs = (i: number, j: number, n: number): TreeNode | null => {
        if (n <= 0) {
            return null;
        }
        const v = preorder[i];
        const k = d.get(v)!;
        const l = dfs(i + 1, j, k - j);
        const r = dfs(i + 1 + k - j, k + 1, n - 1 - (k - j));
        return new TreeNode(v, l, r);
    };
    return dfs(0, 0, n);
}
```

#### Rust

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
use std::cell::RefCell;
use std::collections::HashMap;
use std::rc::Rc;
impl Solution {
    pub fn build_tree(preorder: Vec<i32>, inorder: Vec<i32>) -> Option<Rc<RefCell<TreeNode>>> {
        let mut d = HashMap::new();
        for (i, &x) in inorder.iter().enumerate() {
            d.insert(x, i);
        }
        Self::dfs(&preorder, &d, 0, 0, preorder.len())
    }

    pub fn dfs(
        preorder: &Vec<i32>,
        d: &HashMap<i32, usize>,
        i: usize,
        j: usize,
        n: usize,
    ) -> Option<Rc<RefCell<TreeNode>>> {
        if n <= 0 {
            return None;
        }
        let v = preorder[i];
        let k = d[&v];
        let mut root = TreeNode::new(v);
        root.left = Self::dfs(preorder, d, i + 1, j, k - j);
        root.right = Self::dfs(preorder, d, i + k - j + 1, k + 1, n - k + j - 1);
        Some(Rc::new(RefCell::new(root)))
    }
}
```

#### JavaScript

```js
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {number[]} preorder
 * @param {number[]} inorder
 * @return {TreeNode}
 */
var buildTree = function (preorder, inorder) {
    const d = new Map();
    const n = inorder.length;
    for (let i = 0; i < n; ++i) {
        d.set(inorder[i], i);
    }
    const dfs = (i, j, n) => {
        if (n <= 0) {
            return null;
        }
        const v = preorder[i];
        const k = d.get(v);
        const l = dfs(i + 1, j, k - j);
        const r = dfs(i + 1 + k - j, k + 1, n - 1 - (k - j));
        return new TreeNode(v, l, r);
    };
    return dfs(0, 0, n);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Bảng băm + Đệ quy (Giá trị trùng lặp)

<!-- thinking:start -->

> **Tư duy**
>
> Lời giải 1 cần các giá trị duy nhất để một chỉ số có thể xác định vị trí của gốc. Nếu các giá trị có thể lặp lại, một giá trị có thể có nhiều vị trí hợp lệ trong inorder. Chúng ta lưu mọi chỉ số, thử từng chỉ số nằm trong khoảng hiện tại, đệ quy trên cả hai phía rồi lấy tích Descartes. Bài toán này không cần nhánh đó; nhánh này cho thấy cách liệt kê mọi cây khả thi khi cho phép các giá trị trùng lặp.

<!-- thinking:end -->

Bài toán này đảm bảo các giá trị của nút là duy nhất. Nếu cho phép các giá trị trùng lặp, bảng băm phải lưu mọi chỉ số của mỗi giá trị, đồng thời chúng ta liệt kê mọi vị trí gốc có thể có trong khoảng hiện tại để xây dựng tất cả các cây nhị phân hợp lệ.

Độ phức tạp thời gian và không gian đều phụ thuộc vào số lượng cây hợp lệ (chúng ta xây dựng và lưu tất cả các cây), và trong trường hợp xấu nhất là cấp số mũ. Ngăn xếp đệ quy sử dụng thêm $O(n)$ không gian.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getBinaryTrees(self, preOrder: List[int], inOrder: List[int]) -> List[TreeNode]:
        def dfs(i: int, j: int, n: int) -> List[TreeNode]:
            if n <= 0:
                return [None]
            v = preOrder[i]
            ans = []
            for k in d[v]:
                if j <= k < j + n:
                    for l in dfs(i + 1, j, k - j):
                        for r in dfs(i + 1 + k - j, k + 1, n - 1 - (k - j)):
                            ans.append(TreeNode(v, l, r))
            return ans

        d = defaultdict(list)
        for i, x in enumerate(inOrder):
            d[x].append(i)
        return dfs(0, 0, len(preOrder))
```

#### Java

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
    private int[] preorder;
    private Map<Integer, Integer> d = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        this.preorder = preorder;
        for (int i = 0; i < n; ++i) {
            d.put(inorder[i], i);
        }
        return dfs(0, 0, n);
    }

    private TreeNode dfs(int i, int j, int n) {
        if (n <= 0) {
            return null;
        }
        int v = preorder[i];
        int k = d.get(v);
        TreeNode l = dfs(i + 1, j, k - j);
        TreeNode r = dfs(i + 1 + k - j, k + 1, n - 1 - (k - j));
        return new TreeNode(v, l, r);
    }
}
```

#### C++

```cpp
/**
 * struct TreeNode {
 *	int val;
 *	struct TreeNode *left;
 *	struct TreeNode *right;
 *	TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 * };
 */
class Solution {
public:
    vector<TreeNode*> getBinaryTrees(vector<int>& preOrder, vector<int>& inOrder) {
        int n = inOrder.size();
        unordered_map<int, vector<int>> d;
        for (int i = 0; i < n; ++i) {
            d[inOrder[i]].push_back(i);
        }
        function<vector<TreeNode*>(int, int, int)> dfs = [&](int i, int j, int n) -> vector<TreeNode*> {
            vector<TreeNode*> ans;
            if (n <= 0) {
                ans.push_back(nullptr);
                return ans;
            }
            int v = preOrder[i];
            for (int k : d[v]) {
                if (k >= j && k < j + n) {
                    auto lefts = dfs(i + 1, j, k - j);
                    auto rights = dfs(i + 1 + k - j, k + 1, n - 1 - (k - j));
                    for (TreeNode* l : lefts) {
                        for (TreeNode* r : rights) {
                            TreeNode* node = new TreeNode(v);
                            node->left = l;
                            node->right = r;
                            ans.push_back(node);
                        }
                    }
                }
            }
            return ans;
        };
        return dfs(0, 0, n);
    }
};
```

#### Go

```go
func getBinaryTrees(preOrder []int, inOrder []int) []*TreeNode {
	n := len(preOrder)
	d := map[int][]int{}
	for i, x := range inOrder {
		d[x] = append(d[x], i)
	}
	var dfs func(i, j, n int) []*TreeNode
	dfs = func(i, j, n int) []*TreeNode {
		ans := []*TreeNode{}
		if n <= 0 {
			ans = append(ans, nil)
			return ans
		}
		v := preOrder[i]
		for _, k := range d[v] {
			if k >= j && k < j+n {
				lefts := dfs(i+1, j, k-j)
				rights := dfs(i+1+k-j, k+1, n-1-(k-j))
				for _, left := range lefts {
					for _, right := range rights {
						ans = append(ans, &TreeNode{v, left, right})
					}
				}
			}
		}
		return ans
	}
	return dfs(0, 0, n)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
