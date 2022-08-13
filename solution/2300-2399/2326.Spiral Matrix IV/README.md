# [2326. 螺旋矩阵 IV](https://leetcode.cn/problems/spiral-matrix-iv)

[English Version](/solution/2300-2399/2326.Spiral%20Matrix%20IV/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数：<code>m</code> 和 <code>n</code> ，表示矩阵的维数。</p>

<p>另给你一个整数链表的头节点 <code>head</code> 。</p>

<p>请你生成一个大小为 <code>m x n</code> 的螺旋矩阵，矩阵包含链表中的所有整数。链表中的整数从矩阵 <strong>左上角</strong> 开始、<strong>顺时针 </strong>按 <strong>螺旋</strong> 顺序填充。如果还存在剩余的空格，则用 <code>-1</code> 填充。</p>

<p>返回生成的矩阵。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2326.Spiral%20Matrix%20IV/images/ex1new.jpg" style="width: 240px; height: 150px;">
<pre><strong>输入：</strong>m = 3, n = 5, head = [3,0,2,6,8,1,7,9,4,2,5,5,0]
<strong>输出：</strong>[[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
<strong>解释：</strong>上图展示了链表中的整数在矩阵中是如何排布的。
注意，矩阵中剩下的空格用 -1 填充。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2326.Spiral%20Matrix%20IV/images/ex2.jpg" style="width: 221px; height: 60px;">
<pre><strong>输入：</strong>m = 1, n = 4, head = [0,1,2]
<strong>输出：</strong>[[0,1,2,-1]]
<strong>解释：</strong>上图展示了链表中的整数在矩阵中是如何从左到右排布的。 
注意，矩阵中剩下的空格用 -1 填充。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li>链表中节点数目在范围 <code>[1, m * n]</code> 内</li>
	<li><code>0 &lt;= Node.val &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def spiralMatrix(self, m: int, n: int, head: Optional[ListNode]) -> List[List[int]]:
        ans = [[-1] * n for _ in range(m)]
        i = j = p = 0
        dirs = [[0, 1], [1, 0], [0, -1], [-1, 0]]
        while 1:
            ans[i][j] = head.val
            head = head.next
            if not head:
                break
            while 1:
                x, y = i + dirs[p][0], j + dirs[p][1]
                if x < 0 or y < 0 or x >= m or y >= n or ~ans[x][y]:
                    p = (p + 1) % 4
                else:
                    i, j = x, y
                    break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] ans = new int[m][n];
        for (int[] row : ans) {
            Arrays.fill(row, -1);
        }
        int i = 0, j = 0, p = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (true) {
            ans[i][j] = head.val;
            head = head.next;
            if (head == null) {
                break;
            }
            while (true) {
                int x = i + dirs[p][0], y = j + dirs[p][1];
                if (x < 0 || y < 0 || x >= m || y >= n || ans[x][y] >= 0) {
                    p = (p + 1) % 4;
                } else {
                    i = x;
                    j = y;
                    break;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    vector<vector<int>> spiralMatrix(int m, int n, ListNode* head) {
        vector<vector<int>> ans(m, vector<int>(n, -1));
        int i = 0, j = 0, p = 0;
        vector<vector<int>> dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (1) {
            ans[i][j] = head->val;
            head = head->next;
            if (!head) break;
            while (1) {
                int x = i + dirs[p][0], y = j + dirs[p][1];
                if (x < 0 || y < 0 || x >= m || y >= n || ans[x][y] >= 0)
                    p = (p + 1) % 4;
                else {
                    i = x, j = y;
                    break;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func spiralMatrix(m int, n int, head *ListNode) [][]int {
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, n)
		for j := range ans[i] {
			ans[i][j] = -1
		}
	}
	i, j, p := 0, 0, 0
	dirs := [][]int{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}
	for {
		ans[i][j] = head.Val
		head = head.Next
		if head == nil {
			break
		}
		for {
			x, y := i+dirs[p][0], j+dirs[p][1]
			if x < 0 || y < 0 || x >= m || y >= n || ans[x][y] >= 0 {
				p = (p + 1) % 4
			} else {
				i, j = x, y
				break
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

function spiralMatrix(m: number, n: number, head: ListNode | null): number[][] {
    const dirs = [
        [0, 1],
        [1, 0],
        [0, -1],
        [-1, 0],
    ];
    let ans = Array.from({ length: m }, v => new Array(n).fill(-1));
    let i = 0,
        j = 0,
        k = 0;
    while (head) {
        ans[i][j] = head.val;
        head = head.next;
        let x = i + dirs[k][0];
        let y = j + dirs[k][1];
        if (x < 0 || x > m - 1 || y < 0 || y > n - 1 || ans[x][y] != -1) {
            k = (k + 1) % 4;
        }
        i = i + dirs[k][0];
        j = j + dirs[k][1];
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
