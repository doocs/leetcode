# [LCP 51. 烹饪料理](https://leetcode.cn/problems/UEcfPD)

## 题目描述

<!-- 这里写题目描述 -->

欢迎各位勇者来到力扣城，城内设有烹饪锅供勇者制作料理，为自己恢复状态。

勇者背包内共有编号为 `0 ~ 4` 的五种食材，其中 `materials[j]` 表示第 `j` 种食材的数量。通过这些食材可以制作若干料理，`cookbooks[i][j]` 表示制作第 `i` 种料理需要第 `j` 种食材的数量，而 `attribute[i] = [x,y]` 表示第 `i` 道料理的美味度 `x` 和饱腹感 `y`。

在饱腹感不小于 `limit` 的情况下，请返回勇者可获得的最大美味度。如果无法满足饱腹感要求，则返回 `-1`。

**注意：**

-   每种料理只能制作一次。

**示例 1：**

> 输入：`materials = [3,2,4,1,2]` >`cookbooks = [[1,1,0,1,2],[2,1,4,0,0],[3,2,4,1,0]]` >`attribute = [[3,2],[2,4],[7,6]]` >`limit = 5`
>
> 输出：`7`
>
> 解释：
> 食材数量可以满足以下两种方案：
> 方案一：制作料理 0 和料理 1，可获得饱腹感 2+4、美味度 3+2
> 方案二：仅制作料理 2， 可饱腹感为 6、美味度为 7
> 因此在满足饱腹感的要求下，可获得最高美味度 7

**示例 2：**

> 输入：`materials = [10,10,10,10,10]` >`cookbooks = [[1,1,1,1,1],[3,3,3,3,3],[10,10,10,10,10]]` >`attribute = [[5,5],[6,6],[10,10]]` >`limit = 1`
>
> 输出：`11`
>
> 解释：通过制作料理 0 和 1，可满足饱腹感，并获得最高美味度 11

**提示：**

-   `materials.length == 5`
-   `1 <= cookbooks.length == attribute.length <= 8`
-   `cookbooks[i].length == 5`
-   `attribute[i].length == 2`
-   `0 <= materials[i], cookbooks[i][j], attribute[i][j] <= 20`
-   `1 <= limit <= 100`

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二进制枚举**

我们注意到，料理的数量 $n$ 不超过 $8$，因此，我们可以使用二进制枚举的方法枚举所有的料理方案。

每种料理都有两种选择：制作或者不制作。因此，我们可以使用一个长度为 $8$ 的二进制数来表示一种方案，其中第 $i$ 位为 $1$ 表示制作第 $i$ 道料理，为 $0$ 表示不制作第 $i$ 道料理。

我们在 $[0, 2^n)$ 的范围内枚举所有料理方案，对于每种方案，我们计算其美味度和饱腹感，如果饱腹感不小于 $limit$，并且食材数量足够制作这些料理，同时其美味度大于当前答案，我们就更新答案。

枚举结束后，我们就可以得到最大的美味度。

时间复杂度 $(2^n \times n)$，其中 $n$ 是料理的数量。我们需要枚举所有的料理方案，对于每种方案，我们需要 $O(n)$ 的时间计算其美味度和饱腹感，因此总时间复杂度为 $O(2^n \times n)$。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def perfectMenu(
        self,
        materials: List[int],
        cookbooks: List[List[int]],
        attribute: List[List[int]],
        limit: int,
    ) -> int:
        n = len(cookbooks)
        ans = -1
        for mask in range(1 << n):
            a = b = 0
            cnt = [0] * 5
            for i in range(n):
                if mask >> i & 1:
                    x, y = attribute[i]
                    a += x
                    b += y
                    for j, v in enumerate(cookbooks[i]):
                        cnt[j] += v
            if b >= limit and ans < a and all(c <= d for c, d in zip(cnt, materials)):
                ans = a
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int perfectMenu(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
        int n = cookbooks.length;
        int ans = -1;
        for (int mask = 0; mask < 1 << n; ++mask) {
            int a = 0, b = 0;
            int[] cnt = new int[5];
            for (int i = 0; i < n; ++i) {
                if ((mask >> i & 1) == 1) {
                    int x = attribute[i][0];
                    int y = attribute[i][1];
                    a += x;
                    b += y;
                    for (int j = 0; j < cookbooks[i].length; ++j) {
                        cnt[j] += cookbooks[i][j];
                    }
                }
            }
            boolean ok = true;
            for (int i = 0; i < 5 && ok; ++i) {
                ok = cnt[i] <= materials[i];
            }
            if (b >= limit && ans < a && ok) {
                ans = a;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int perfectMenu(vector<int>& materials, vector<vector<int>>& cookbooks, vector<vector<int>>& attribute, int limit) {
        int n = cookbooks.size();
        int ans = -1;
        for (int mask = 0; mask < 1 << n; ++mask) {
            int a = 0, b = 0;
            vector<int> cnt(5);
            for (int i = 0; i < n; ++i) {
                if (mask >> i & 1) {
                    int x = attribute[i][0];
                    int y = attribute[i][1];
                    a += x;
                    b += y;
                    for (int j = 0; j < cookbooks[i].size(); ++j) {
                        cnt[j] += cookbooks[i][j];
                    }
                }
                bool ok = true;
                for (int i = 0; i < 5 && ok; ++i) {
                    ok = cnt[i] <= materials[i];
                }
                if (b >= limit && ans < a && ok) {
                    ans = a;
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func perfectMenu(materials []int, cookbooks [][]int, attribute [][]int, limit int) int {
	n := len(cookbooks)
	ans := -1
	for mask := 0; mask < 1<<n; mask++ {
		a, b := 0, 0
		cnt := make([]int, 5)
		for i := 0; i < n; i++ {
			if mask>>i&1 == 1 {
				x, y := attribute[i][0], attribute[i][1]
				a += x
				b += y
				for j, v := range cookbooks[i] {
					cnt[j] += v
				}
			}
			ok := true
			for i := 0; i < 5 && ok; i++ {
				ok = cnt[i] <= materials[i]
			}
			if ok && b >= limit && ans < a {
				ans = a
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function perfectMenu(
    materials: number[],
    cookbooks: number[][],
    attribute: number[][],
    limit: number,
): number {
    const n = cookbooks.length;
    let ans = -1;
    for (let mask = 0; mask < 1 << n; ++mask) {
        let [a, b] = [0, 0];
        const cnt: number[] = Array(5).fill(0);
        for (let i = 0; i < n; ++i) {
            if (((mask >> i) & 1) === 1) {
                const [x, y] = attribute[i];
                a += x;
                b += y;
                for (let j = 0; j < cookbooks[i].length; ++j) {
                    cnt[j] += cookbooks[i][j];
                }
            }
            let ok = true;
            for (let i = 0; i < 5 && ok; ++i) {
                ok = cnt[i] <= materials[i];
            }
            if (b >= limit && ans < a && ok) {
                ans = a;
            }
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
