# [587. 安装栅栏](https://leetcode.cn/problems/erect-the-fence)

[English Version](/solution/0500-0599/0587.Erect%20the%20Fence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个二维的花园中，有一些用 (x, y) 坐标表示的树。由于安装费用十分昂贵，你的任务是先用<strong>最短</strong>的绳子围起所有的树。只有当所有的树都被绳子包围时，花园才能围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]

<strong>输出:</strong> [[1,1],[2,0],[4,2],[3,3],[2,4]]

<strong>解释:</strong>

<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0587.Erect%20the%20Fence/images/erect_the_fence_1.png" style="width: 100%; max-width: 320px">

</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> [[1,2],[2,2],[4,2]]

<strong>输出:</strong> [[1,2],[2,2],[4,2]]

<strong>解释:</strong>

<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0587.Erect%20the%20Fence/images/erect_the_fence_2.png" style="width: 100%; max-width: 320px">

即使树都在一条直线上，你也需要先用绳子包围它们。

</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong></p>

<ol>
    <li>所有的树应当被围在一起。你不能剪断绳子来包围树或者把树分成一组以上。</li>
    <li>输入的整数在 0 到 100 之间。</li>
    <li>花园至少有一棵树。</li>
    <li>所有树的坐标都是不同的。</li>
    <li>输入的点<strong>没有</strong>顺序。输出顺序也没有要求。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：Andrew 算法**

原理：

利用夹角，让整个图形保持左转。先将最左边的前两个点加入栈中，每次加入新点时判断是否左拐（叉积大于 0），如果是就将新点直接加入；如果不是，就弹出栈顶，直到左拐，将新点加入栈中。

流程：

1. 将所有点 `(x, y)` 进行排序，以 x 为第一关键字，y 为第二关键字升序排序；
1. 先从左至右维护凸包的下半部分，然后再从右至左维护上半部分；
1. 将第一个点放入栈中，这个点一定时凸包的最左边的点了，是不会清理掉的，然后再将第二个点放入栈中。当栈中元素大于等于 2 的时候，就要判断栈顶元素是否还要保留：
    - 如果新点在栈顶元素和次栈顶元素所组成的直线的逆时针方向上，那么直接将新点加入栈中；
    - 否则，将栈顶元素不断弹出，直至新点的位置出现在栈顶元素与次栈顶元素所在直线的逆时针方向。

这个过程，是从左往右走的，并且得到的凸包是凸壳的下半部分。求上半部分的时候，从右往左遍历。

时间复杂度 O(nlogn)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def outerTrees(self, trees: List[List[int]]) -> List[List[int]]:
        def cross(i, j, k):
            a, b, c = trees[i], trees[j], trees[k]
            return (b[0] - a[0]) * (c[1] - b[1]) - (b[1] - a[1]) * (c[0] - b[0])

        n = len(trees)
        if n < 4:
            return trees
        trees.sort()
        vis = [False] * n
        stk = [0]
        for i in range(1, n):
            while len(stk) > 1 and cross(stk[-2], stk[-1], i) < 0:
                vis[stk.pop()] = False
            vis[i] = True
            stk.append(i)
        m = len(stk)
        for i in range(n - 2, -1, -1):
            if vis[i]:
                continue
            while len(stk) > m and cross(stk[-2], stk[-1], i) < 0:
                stk.pop()
            stk.append(i)
        stk.pop()
        return [trees[i] for i in stk]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] outerTrees(int[][] trees) {
        int n = trees.length;
        if (n < 4) {
            return trees;
        }
        Arrays.sort(trees, (a, b) -> {
            return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
        });
        boolean[] vis = new boolean[n];
        int[] stk = new int[n + 10];
        int cnt = 1;
        for (int i = 1; i < n; ++i) {
            while (cnt > 1 && cross(trees[stk[cnt - 1]], trees[stk[cnt - 2]], trees[i]) < 0) {
                vis[stk[--cnt]] = false;
            }
            vis[i] = true;
            stk[cnt++] = i;
        }
        int m = cnt;
        for (int i = n - 1; i >= 0; --i) {
            if (vis[i]) {
                continue;
            }
            while (cnt > m && cross(trees[stk[cnt - 1]], trees[stk[cnt - 2]], trees[i]) < 0) {
                --cnt;
            }
            stk[cnt++] = i;
        }
        int[][] ans = new int[cnt - 1][2];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = trees[stk[i]];
        }
        return ans;
    }

    private int cross(int[] a, int[] b, int[] c) {
        return (b[0] - a[0]) * (c[1] - b[1]) - (b[1] - a[1]) * (c[0] - b[0]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> outerTrees(vector<vector<int>>& trees) {
        int n = trees.size();
        if (n < 4) return trees;
        sort(trees.begin(), trees.end());
        vector<int> vis(n);
        vector<int> stk(n + 10);
        int cnt = 1;
        for (int i = 1; i < n; ++i) {
            while (cnt > 1 && cross(trees[stk[cnt - 1]], trees[stk[cnt - 2]], trees[i]) < 0) vis[stk[--cnt]] = false;
            vis[i] = true;
            stk[cnt++] = i;
        }
        int m = cnt;
        for (int i = n - 1; i >= 0; --i) {
            if (vis[i]) continue;
            while (cnt > m && cross(trees[stk[cnt - 1]], trees[stk[cnt - 2]], trees[i]) < 0) --cnt;
            stk[cnt++] = i;
        }
        vector<vector<int>> ans;
        for (int i = 0; i < cnt - 1; ++i) ans.push_back(trees[stk[i]]);
        return ans;
    }

    int cross(vector<int>& a, vector<int>& b, vector<int>& c) {
        return (b[0] - a[0]) * (c[1] - b[1]) - (b[1] - a[1]) * (c[0] - b[0]);
    }
};
```

### **Go**

```go
func outerTrees(trees [][]int) [][]int {
	n := len(trees)
	if n < 4 {
		return trees
	}
	sort.Slice(trees, func(i, j int) bool {
		if trees[i][0] == trees[j][0] {
			return trees[i][1] < trees[j][1]
		}
		return trees[i][0] < trees[j][0]
	})
	cross := func(i, j, k int) int {
		a, b, c := trees[i], trees[j], trees[k]
		return (b[0]-a[0])*(c[1]-b[1]) - (b[1]-a[1])*(c[0]-b[0])
	}
	vis := make([]bool, n)
	stk := []int{0}
	for i := 1; i < n; i++ {
		for len(stk) > 1 && cross(stk[len(stk)-1], stk[len(stk)-2], i) < 0 {
			vis[stk[len(stk)-1]] = false
			stk = stk[:len(stk)-1]
		}
		vis[i] = true
		stk = append(stk, i)
	}
	m := len(stk)
	for i := n - 1; i >= 0; i-- {
		if vis[i] {
			continue
		}
		for len(stk) > m && cross(stk[len(stk)-1], stk[len(stk)-2], i) < 0 {
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, i)
	}
	var ans [][]int
	for i := 0; i < len(stk)-1; i++ {
		ans = append(ans, trees[stk[i]])
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
