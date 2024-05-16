---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2326.Spiral%20Matrix%20IV/README_EN.md
rating: 1421
source: Weekly Contest 300 Q2
tags:
    - Array
    - Linked List
    - Matrix
    - Simulation
---

<!-- problem:start -->

# [2326. Spiral Matrix IV](https://leetcode.com/problems/spiral-matrix-iv)

[中文文档](/solution/2300-2399/2326.Spiral%20Matrix%20IV/README.md)

## Description

<p>You are given two integers <code>m</code> and <code>n</code>, which represent the dimensions of a matrix.</p>

<p>You are also given the <code>head</code> of a linked list of integers.</p>

<p>Generate an <code>m x n</code> matrix that contains the integers in the linked list presented in <strong>spiral</strong> order <strong>(clockwise)</strong>, starting from the <strong>top-left</strong> of the matrix. If there are remaining empty spaces, fill them with <code>-1</code>.</p>

<p>Return <em>the generated matrix</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2326.Spiral%20Matrix%20IV/images/ex1new.jpg" style="width: 240px; height: 150px;" />
<pre>
<strong>Input:</strong> m = 3, n = 5, head = [3,0,2,6,8,1,7,9,4,2,5,5,0]
<strong>Output:</strong> [[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
<strong>Explanation:</strong> The diagram above shows how the values are printed in the matrix.
Note that the remaining spaces in the matrix are filled with -1.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2326.Spiral%20Matrix%20IV/images/ex2.jpg" style="width: 221px; height: 60px;" />
<pre>
<strong>Input:</strong> m = 1, n = 4, head = [0,1,2]
<strong>Output:</strong> [[0,1,2,-1]]
<strong>Explanation:</strong> The diagram above shows how the values are printed from left to right in the matrix.
The last space in the matrix is set to -1.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li>The number of nodes in the list is in the range <code>[1, m * n]</code>.</li>
	<li><code>0 &lt;= Node.val &lt;= 1000</code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We define a two-dimensional array $\text{ans}$ to store the elements in the linked list, initially all filled with $-1$. We define three variables $i, j, k$, representing the current row, column, and direction respectively. We define an array $\text{dirs}$ to represent the offsets of the four directions.

Then we start traversing the linked list. Each time we traverse a node, we fill the current node's value into $\text{ans}[i][j]$, then update the linked list pointer. If the linked list is empty, it means all elements have been filled and we exit the loop.

Otherwise, we need to find the position of the next element. We can calculate the next position $(x, y)$ through the current position $(i, j)$ and the current direction $k$. If $(x, y)$ is within the range of the matrix, and $\text{ans}[x][y]$ is $-1$, it means $(x, y)$ has not been filled yet, so we take $(x, y)$ as the next position. Otherwise, we need to change the direction.

After traversing the linked list, we get a spiral matrix and return it.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$, where $m$ and $n$ represent the number of rows and columns of the matrix, respectively.

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

<!-- solution:end -->

<!-- problem:end -->
