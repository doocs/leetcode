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

<!-- tabs:start -->

### **Python3**

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
