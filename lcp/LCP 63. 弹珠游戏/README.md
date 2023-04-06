# [LCP 63. 弹珠游戏](https://leetcode.cn/problems/EXvqDp)

## 题目描述

<!-- 这里写题目描述 -->

欢迎各位来到「力扣嘉年华」，接下来将为各位介绍在活动中广受好评的弹珠游戏。

`N*M` 大小的弹珠盘的初始状态信息记录于一维字符串型数组 `plate` 中，数组中的每个元素为仅由 `"O"`、`"W"`、`"E"`、`"."` 组成的字符串。其中：

-   `"O"` 表示弹珠洞（弹珠到达后会落入洞中，并停止前进）；
-   `"W"` 表示逆时针转向器（弹珠经过时方向将逆时针旋转 90 度）；
-   `"E"` 表示顺时针转向器（弹珠经过时方向将顺时针旋转 90 度）；
-   `"."` 表示空白区域（弹珠可通行）。

游戏规则要求仅能在边缘位置的 **空白区域** 处（弹珠盘的四角除外）沿 **与边缘垂直** 的方向打入弹珠，并且打入后的每颗弹珠最多能 **前进** `num` 步。请返回符合上述要求且可以使弹珠最终入洞的所有打入位置。你可以 **按任意顺序** 返回答案。

**注意：**

-   若弹珠已到达弹珠盘边缘并且仍沿着出界方向继续前进，则将直接出界。

**示例 1：**

> 输入 ：
> `num = 4` >`plate = ["..E.",".EOW","..W."]`
>
> 输出：`[[2,1]]`
>
> 解释：
> 在 `[2,1]` 处打入弹珠，弹珠前进 1 步后遇到转向器，前进方向顺时针旋转 90 度，再前进 1 步进入洞中。
> ![b054955158a99167b8d51da0e22a54da.gif](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2063.%20%E5%BC%B9%E7%8F%A0%E6%B8%B8%E6%88%8F/images/1630392649-BoQncz-b054955158a99167b8d51da0e22a54da.gif)

**示例 2：**

> 输入 ：
> `num = 5` >`plate = [".....","..E..",".WO..","....."]`
>
> 输出：`[[0,1],[1,0],[2,4],[3,2]]`
>
> 解释：
> 在 `[0,1]` 处打入弹珠，弹珠前进 2 步，遇到转向器后前进方向逆时针旋转 90 度，再前进 1 步进入洞中。
> 在 `[1,0]` 处打入弹珠，弹珠前进 2 步，遇到转向器后前进方向顺时针旋转 90 度，再前进 1 步进入洞中。
> 在 `[2,4]` 处打入弹珠，弹珠前进 2 步后进入洞中。
> 在 `[3,2]` 处打入弹珠，弹珠前进 1 步后进入洞中。
> ![b44e9963239ae368badf3d00b7563087.gif](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2063.%20%E5%BC%B9%E7%8F%A0%E6%B8%B8%E6%88%8F/images/1630392625-rckbdy-b44e9963239ae368badf3d00b7563087.gif)

**示例 3：**

> 输入 ：
> `num = 3` >`plate = [".....","....O","....O","....."]`
>
> 输出：`[]`
>
> 解释：
> 由于弹珠被击中后只能前进 3 步，且不能在弹珠洞和弹珠盘四角打入弹珠，故不存在能让弹珠入洞的打入位置。

**提示：**

-   `1 <= num <= 10^6`
-   `1 <= plate.length, plate[i].length <= 1000`
-   `plate[i][j]` 仅包含 `"O"`、`"W"`、`"E"`、`"."`

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们注意到，从不同的位置打入弹珠，弹珠的前进路线不会重叠。因此，我们可以枚举所有可能的打入位置，然后模拟弹珠的前进过程，判断是否能够入洞，是则将该位置加入答案。

在实现上，我们定义一个方向数组 $dirs=[0,1,0,-1,0]$，对于 $d \in [0,..3]$，其中 $(dirs[d], dirs[d + 1])$ 表示弹珠的前进方向，分别对应 “右、下、左、上”四个方向。如果弹珠遇到 “W” 转向器，则 $d=(d+3) \bmod 4$，如果遇到 “E” 转向器，则 $d=(d+1) \bmod 4$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别为弹珠盘的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def ballGame(self, num: int, plate: List[str]) -> List[List[int]]:
        def check(i, j, d):
            k = num
            while plate[i][j] != 'O':
                if k == 0:
                    return False
                if plate[i][j] == 'W':
                    d = (d + 3) % 4
                elif plate[i][j] == 'E':
                    d = (d + 1) % 4
                i, j = i + dirs[d], j + dirs[d + 1]
                if not (0 <= i < m and 0 <= j < n):
                    return False
                k -= 1
            return True

        dirs = (0, 1, 0, -1, 0)
        m, n = len(plate), len(plate[0])
        ans = []
        for i in range(1, m - 1):
            if plate[i][0] == '.' and check(i, 0, 0):
                ans.append([i, 0])
            if plate[i][n - 1] == '.' and check(i, n - 1, 2):
                ans.append([i, n - 1])
        for j in range(1, n - 1):
            if plate[0][j] == '.' and check(0, j, 1):
                ans.append([0, j])
            if plate[m - 1][j] == '.' and check(m - 1, j, 3):
                ans.append([m - 1, j])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private String[] plate;
    private int num;
    private int m;
    private int n;
    private final int[] dirs = {0, 1, 0, -1, 0};

    public int[][] ballGame(int num, String[] plate) {
        this.num = num;
        this.plate = plate;
        this.m = plate.length;
        this.n = plate[0].length();
        List<int[]> ans = new ArrayList<>();
        for (int i = 1; i < m - 1; ++i) {
            if (plate[i].charAt(0) == '.' && check(i, 0, 0)) {
                ans.add(new int[]{i, 0});
            }
            if (plate[i].charAt(n - 1) == '.' && check(i, n - 1, 2)) {
                ans.add(new int[]{i, n - 1});
            }
        }
        for (int j = 1; j < n - 1; ++j) {
            if (plate[0].charAt(j) == '.' && check(0, j, 1)) {
                ans.add(new int[]{0, j});
            }
            if (plate[m - 1].charAt(j) == '.' && check(m - 1, j, 3)) {
                ans.add(new int[]{m - 1, j});
            }
        }
        return ans.toArray(new int[0][]);
    }

    private boolean check(int i, int j, int d) {
        int k = num;
        while (plate[i].charAt(j) != 'O') {
            if (k == 0) {
                return false;
            }
            if (plate[i].charAt(j) == 'W') {
                d = (d + 3) % 4;
            } else if (plate[i].charAt(j) == 'E') {
                d = (d + 1) % 4;
            }
            i = i + dirs[d];
            j = j + dirs[d + 1];
            if (i < 0 || i >= m || j < 0 || j >= n) {
                return false;
            }
            --k;
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> ballGame(int num, vector<string>& plate) {
        int m = plate.size(), n = plate[0].size();
        vector<vector<int>> ans;
        int dirs[5] = {0, 1, 0, -1, 0};
        auto check = [&](int i, int j, int d) -> bool {
            int k = num;
            while (plate[i][j] != 'O') {
                if (k == 0) {
                    return false;
                }
                if (plate[i][j] == 'W') {
                    d = (d + 3) % 4;
                } else if (plate[i][j] == 'E') {
                    d = (d + 1) % 4;
                }
                i += dirs[d];
                j += dirs[d + 1];
                if (i < 0 || i >= m || j < 0 || j >= n) {
                    return false;
                }
                --k;
            }
            return true;
        };
        for (int i = 1; i < m - 1; ++i) {
            if (plate[i][0] == '.' && check(i, 0, 0)) {
                ans.push_back({i, 0});
            }
            if (plate[i][n - 1] == '.' && check(i, n - 1, 2)) {
                ans.push_back({i, n - 1});
            }
        }
        for (int j = 1; j < n - 1; ++j) {
            if (plate[0][j] == '.' && check(0, j, 1)) {
                ans.push_back({0, j});
            }
            if (plate[m - 1][j] == '.' && check(m - 1, j, 3)) {
                ans.push_back({m - 1, j});
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func ballGame(num int, plate []string) (ans [][]int) {
	dirs := [5]int{0, 1, 0, -1, 0}
	m, n := len(plate), len(plate[0])
	check := func(i, j, d int) bool {
		k := num
		for plate[i][j] != 'O' {
			if k == 0 {
				return false
			}
			if plate[i][j] == 'W' {
				d = (d + 3) % 4
			} else if plate[i][j] == 'E' {
				d = (d + 1) % 4
			}
			i += dirs[d]
			j += dirs[d+1]
			if i < 0 || i >= m || j < 0 || j >= n {
				return false
			}
			k--
		}
		return true
	}
	for i := 1; i < m-1; i++ {
		if plate[i][0] == '.' && check(i, 0, 0) {
			ans = append(ans, []int{i, 0})
		}
		if plate[i][n-1] == '.' && check(i, n-1, 2) {
			ans = append(ans, []int{i, n - 1})
		}
	}
	for j := 1; j < n-1; j++ {
		if plate[0][j] == '.' && check(0, j, 1) {
			ans = append(ans, []int{0, j})
		}
		if plate[m-1][j] == '.' && check(m-1, j, 3) {
			ans = append(ans, []int{m - 1, j})
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
