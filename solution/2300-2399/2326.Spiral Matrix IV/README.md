# [2326. 螺旋矩阵 IV](https://leetcode.cn/problems/spiral-matrix-iv)

[English Version](/solution/2300-2399/2326.Spiral%20Matrix%20IV/README_EN.md)

<!-- tags:数组,链表,矩阵,模拟 -->

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

### 方法一：模拟

我们定义一个二维数组 $\text{ans}$，用来存放链表中的元素，初始时全部填充为 $-1$。定义三个变量 $i, j, k$，分别表示当前的行、列和方向。定义一个数组 $\text{dirs}$，表示四个方向的偏移量。

然后我们开始遍历链表，每次遍历一个节点，就将当前节点的值填充到 $\text{ans}[i][j]$ 中，然后更新链表的指针，如果链表为空，说明所有的元素都已经填充完毕，退出循环。

否则，我们需要找到下一个元素的位置，我们可以通过当前位置 $(i, j)$ 和当前方向 $k$ 来计算下一个位置 $(x, y)$，如果 $(x, y)$ 在矩阵的范围内，并且 $\text{ans}[x][y]$ 为 $-1$，说明 $(x, y)$ 还没有被填充过，我们就将 $(x, y)$ 作为下一个位置，否则我们需要更换方向。

遍历完链表之后，我们就得到了一个螺旋矩阵，返回即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别表示矩阵的行数和列数。

<!-- tabs:start -->

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def spiralMatrix(self, m: int, n: int, head: Optional[ListNode]) -> List[List[int]]:
        ans = [[-1] * n for _ in range(m)]
        i = j = k = 0
        dirs = (0, 1, 0, -1, 0)
        while 1:
            ans[i][j] = head.val
            head = head.next
            if head is None:
                break
            while 1:
                x, y = i + dirs[k], j + dirs[k + 1]
                if 0 <= x < m and 0 <= y < n and ans[x][y] == -1:
                    i, j = x, y
                    break
                k = (k + 1) % 4
        return ans
```

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
        for (var row : ans) {
            Arrays.fill(row, -1);
        }
        int i = 0, j = 0, k = 0;
        final int[] dirs = {0, 1, 0, -1, 0};
        while (true) {
            ans[i][j] = head.val;
            head = head.next;
            if (head == null) {
                break;
            }
            while (true) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1) {
                    i = x;
                    j = y;
                    break;
                }
                k = (k + 1) % 4;
            }
        }
        return ans;
    }
}
```

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
        int i = 0, j = 0, k = 0;
        const int dirs[5] = {0, 1, 0, -1, 0};
        while (1) {
            ans[i][j] = head->val;
            head = head->next;
            if (!head) {
                break;
            }
            while (1) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1) {
                    i = x;
                    j = y;
                    break;
                }
                k = (k + 1) % 4;
            }
        }
        return ans;
    }
};
```

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
	i, j, k := 0, 0, 0
	dirs := [5]int{0, 1, 0, -1, 0}
	for {
		ans[i][j] = head.Val
		if head = head.Next; head == nil {
			break
		}
		for {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1 {
				i, j = x, y
				break
			}
			k = (k + 1) % 4
		}
	}
	return ans
}
```

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
    const ans: number[][] = Array.from({ length: m }, () => Array(n).fill(-1));
    const dirs: number[] = [0, 1, 0, -1, 0];
    let [i, j, k] = [0, 0, 0];
    while (1) {
        ans[i][j] = head.val;
        head = head.next;
        if (!head) {
            break;
        }
        while (1) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && x < m && y >= 0 && y < n && ans[x][y] === -1) {
                i = x;
                j = y;
                break;
            }
            k = (k + 1) % 4;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
