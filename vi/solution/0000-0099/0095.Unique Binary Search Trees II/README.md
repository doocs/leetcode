---
comments: true
difficulty: Medium
tags:
    - Tree
    - Binary Search Tree
    - Dynamic Programming
    - Backtracking
    - Binary Tree
---

<!-- problem:start -->

# [95. Unique Binary Search Trees II](https://leetcode.com/problems/unique-binary-search-trees-ii)

[中文文档](/solution/0000-0099/0095.Unique%20Binary%20Search%20Trees%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một số nguyên <code>n</code>, hãy trả về <em>tất cả các <strong>BST&#39;</strong> khác nhau về cấu trúc (cây tìm kiếm nhị phân), mỗi cây có đúng </em><code>n</code><em> nút với các giá trị phân biệt từ</em> <code>1</code> <em>đến</em> <code>n</code>. Trả về đáp án theo <strong>bất kỳ thứ tự nào</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0095.Unique%20Binary%20Search%20Trees%20II/images/uniquebstn3.jpg" style="width: 600px; height: 148px;" />
<pre>
<strong>Đầu vào:</strong> n = 3
<strong>Đầu ra:</strong> [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> n = 1
<strong>Đầu ra:</strong> [[1]]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 8</code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: DFS (Duyệt theo chiều sâu)

<!-- thinking:start -->

> **Tư duy**
>
> Chúng ta cần mọi BST trên $1..n$. Khi nút gốc là $v$, cây con trái chỉ sử dụng các giá trị trong $[1,v-1]$ còn cây con phải chỉ sử dụng các giá trị trong $[v+1,n]$; hai phía độc lập với nhau. Chia theo khoảng: liệt kê nút gốc, sinh mọi cấu trúc cây con trái và phải, sau đó lấy tích Descartes của chúng. Một khoảng rỗng là một cây null. Vì $n \le 8$, các số Catalan vẫn nhỏ, nên tìm kiếm là đủ.

<!-- thinking:end -->

Chúng ta xây dựng một hàm $dfs(i, j)$ trả về tất cả các cây tìm kiếm nhị phân khả thi được tạo thành từ $[i, j]$, nên đáp án là $dfs(1, n)$.

Các bước thực thi của hàm $dfs(i, j)$ như sau:

1. Nếu $i > j$, điều đó có nghĩa là hiện không có số nào để tạo thành một cây tìm kiếm nhị phân, nên trả về một danh sách chỉ gồm một nút null.
2. Nếu $i \leq j$, chúng ta liệt kê các số $v$ trong $[i, j]$ làm nút gốc. Cây con trái của nút gốc $v$ được tạo thành từ $[i, v - 1]$, còn cây con phải được tạo thành từ $[v + 1, j]$. Cuối cùng, chúng ta lấy tích Descartes của mọi tổ hợp của các cây con trái và phải, tức là $left \times right$, thêm nút gốc $v$, và thu được tất cả các cây tìm kiếm nhị phân có $v$ làm nút gốc.

Độ phức tạp thời gian là $O(n \times G(n))$, còn độ phức tạp không gian là $O(n \times G(n))$. Trong đó $G(n)$ là số Catalan.

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
    def generateTrees(self, n: int) -> List[Optional[TreeNode]]:
        def dfs(i: int, j: int) -> List[Optional[TreeNode]]:
            if i > j:
                return [None]
            ans = []
            for v in range(i, j + 1):
                left = dfs(i, v - 1)
                right = dfs(v + 1, j)
                for l in left:
                    for r in right:
                        ans.append(TreeNode(v, l, r))
            return ans

        return dfs(1, n)
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
    public List<TreeNode> generateTrees(int n) {
        return dfs(1, n);
    }

    private List<TreeNode> dfs(int i, int j) {
        List<TreeNode> ans = new ArrayList<>();
        if (i > j) {
            ans.add(null);
            return ans;
        }
        for (int v = i; v <= j; ++v) {
            var left = dfs(i, v - 1);
            var right = dfs(v + 1, j);
            for (var l : left) {
                for (var r : right) {
                    ans.add(new TreeNode(v, l, r));
                }
            }
        }
        return ans;
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
    vector<TreeNode*> generateTrees(int n) {
        function<vector<TreeNode*>(int, int)> dfs = [&](int i, int j) {
            if (i > j) {
                return vector<TreeNode*>{nullptr};
            }
            vector<TreeNode*> ans;
            for (int v = i; v <= j; ++v) {
                auto left = dfs(i, v - 1);
                auto right = dfs(v + 1, j);
                for (auto l : left) {
                    for (auto r : right) {
                        ans.push_back(new TreeNode(v, l, r));
                    }
                }
            }
            return ans;
        };
        return dfs(1, n);
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
func generateTrees(n int) []*TreeNode {
	var dfs func(int, int) []*TreeNode
	dfs = func(i, j int) []*TreeNode {
		if i > j {
			return []*TreeNode{nil}
		}
		ans := []*TreeNode{}
		for v := i; v <= j; v++ {
			left := dfs(i, v-1)
			right := dfs(v+1, j)
			for _, l := range left {
				for _, r := range right {
					ans = append(ans, &TreeNode{v, l, r})
				}
			}
		}
		return ans
	}
	return dfs(1, n)
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

function generateTrees(n: number): Array<TreeNode | null> {
    const dfs = (i: number, j: number): Array<TreeNode | null> => {
        if (i > j) {
            return [null];
        }
        const ans: Array<TreeNode | null> = [];
        for (let v = i; v <= j; ++v) {
            const left = dfs(i, v - 1);
            const right = dfs(v + 1, j);
            for (const l of left) {
                for (const r of right) {
                    ans.push(new TreeNode(v, l, r));
                }
            }
        }
        return ans;
    };
    return dfs(1, n);
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
use std::rc::Rc;
impl Solution {
    pub fn generate_trees(n: i32) -> Vec<Option<Rc<RefCell<TreeNode>>>> {
        Self::dfs(1, n)
    }

    fn dfs(i: i32, j: i32) -> Vec<Option<Rc<RefCell<TreeNode>>>> {
        let mut ans = Vec::new();
        if i > j {
            ans.push(None);
            return ans;
        }
        for v in i..=j {
            let left = Self::dfs(i, v - 1);
            let right = Self::dfs(v + 1, j);
            for l in &left {
                for r in &right {
                    ans.push(Some(Rc::new(RefCell::new(TreeNode {
                        val: v,
                        left: l.clone(),
                        right: r.clone(),
                    }))));
                }
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
