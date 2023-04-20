# [LCP 70. 沙地治理](https://leetcode.cn/problems/XxZZjK)

## 题目描述

<!-- 这里写题目描述 -->

在力扣城的沙漠分会场展示了一种沙柳树，这种沙柳树能够将沙地转化为坚实的绿地。
展示的区域为正三角形，这片区域可以拆分为若干个子区域，每个子区域都是边长为 `1`  的小三角形，其中第  `i` 行有  `2i - 1`  个小三角形。

初始情况下，区域中的所有位置都为沙地，你需要指定一些子区域种植沙柳树成为绿地，以达到转化整片区域为绿地的最终目的，规则如下：

-   若两个子区域共用一条边，则视为相邻；
    > 如下图所示，(1,1)和(2,2)相邻，(3,2)和(3,3)相邻；(2,2)和(3,3)不相邻，因为它们没有共用边。
-   若至少有两片绿地与同一片沙地相邻，则这片沙地也会转化为绿地
-   转化为绿地的区域会影响其相邻的沙地
    <br><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2070.%20%E6%B2%99%E5%9C%B0%E6%B2%BB%E7%90%86/images/1662692397-VlvErS-image.png" style="width: 300px;" />

现要将一片边长为 `size`  的沙地全部转化为绿地，请找到任意一种初始指定 **最少** 数量子区域种植沙柳的方案，并返回所有初始种植沙柳树的绿地坐标。

**示例 1：**

> 输入：`size = 3`
> 输出：`[[1,1],[2,1],[2,3],[3,1],[3,5]]`
> 解释：如下图所示，一种方案为：
> 指定所示的 5 个子区域为绿地。
> 相邻至少两片绿地的 (2,2)，(3,2) 和 (3,4) 演变为绿地。
> 相邻两片绿地的 (3,3) 演变为绿地。
> <br><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2070.%20%E6%B2%99%E5%9C%B0%E6%B2%BB%E7%90%86/images/1662692503-ncjywh-image.png" style="width: 300px;" />

**示例 2：**

> 输入：`size = 2`
> 输出：`[[1,1],[2,1],[2,3]]`
> 解释：如下图所示：
> 指定所示的 3 个子区域为绿地。
> 相邻三片绿地的 (2,2) 演变为绿地。
> <br><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2070.%20%E6%B2%99%E5%9C%B0%E6%B2%BB%E7%90%86/images/1662692507-mgFXRj-image.png" style="width: 300px;" />

**提示：**

-   `1 <= size <= 1000`

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：找规律**

我们画图观察，可以发现，第一行只有一个三角形，一定要涂色，而从最后一行开始，到第二行结束，每四行的涂色方案是一样的：

1. 最后一行涂色坐标为 $(n, 1)$, $(n, 3)$, ..., $(n, 2n - 1)$。
1. 第 $n - 1$ 行涂色坐标为 $(n - 1, 2)$。
1. 第 $n - 2$ 行涂色坐标为 $(n - 2, 3)$, $(n - 2, 5)$, ..., $(n - 2, 2n - 5)$。
1. 第 $n - 3$ 行涂色坐标为 $(n - 3, 1)$。

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2070.%20%E6%B2%99%E5%9C%B0%E6%B2%BB%E7%90%86/images/demo.png" style="width: 50%">

因此，我们可以按照上述规律，先给第一行涂色，然后从最后一行开始，每四行涂色一次，直到第二行结束。

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2070.%20%E6%B2%99%E5%9C%B0%E6%B2%BB%E7%90%86/images/demo2.png" style="width: 80%">

时间复杂度 $(n^2)$，其中 $n$ 为题目给定的参数。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sandyLandManagement(self, size: int) -> List[List[int]]:
        ans = [[1, 1]]
        k = 0
        for i in range(size, 1, -1):
            if k == 0:
                for j in range(1, i << 1, 2):
                    ans.append([i, j])
            elif k == 1:
                ans.append([i, 2])
            elif k == 2:
                for j in range(3, i << 1, 2):
                    ans.append([i, j])
            else:
                ans.append([i, 1])
            k = (k + 1) % 4
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[][] sandyLandManagement(int size) {
        List<int[]> ans = new ArrayList<>();
        ans.add(new int[] {1, 1});
        for (int i = size, k = 0; i > 1; --i, k = (k + 1) % 4) {
            if (k == 0) {
                for (int j = 1; j < i << 1; j += 2) {
                    ans.add(new int[] {i, j});
                }
            } else if (k == 1) {
                ans.add(new int[] {i, 2});
            } else if (k == 2) {
                for (int j = 3; j < i << 1; j += 2) {
                    ans.add(new int[] {i, j});
                }
            } else {
                ans.add(new int[] {i, 1});
            }
        }
        return ans.toArray(new int[0][]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> sandyLandManagement(int size) {
        vector<vector<int>> ans;
        ans.push_back({1, 1});
        for (int i = size, k = 0; i > 1; --i, k = (k + 1) % 4) {
            if (k == 0) {
                for (int j = 1; j < i << 1; j += 2) {
                    ans.push_back({i, j});
                }
            } else if (k == 1) {
                ans.push_back({i, 2});
            } else if (k == 2) {
                for (int j = 3; j < i << 1; j += 2) {
                    ans.push_back({i, j});
                }
            } else {
                ans.push_back({i, 1});
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func sandyLandManagement(size int) (ans [][]int) {
	ans = append(ans, []int{1, 1})
	for i, k := size, 0; i > 1; i, k = i-1, (k+1)%4 {
		if k == 0 {
			for j := 1; j < i<<1; j += 2 {
				ans = append(ans, []int{i, j})
			}
		} else if k == 1 {
			ans = append(ans, []int{i, 2})
		} else if k == 2 {
			for j := 3; j < i<<1; j += 2 {
				ans = append(ans, []int{i, j})
			}
		} else {
			ans = append(ans, []int{i, 1})
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
