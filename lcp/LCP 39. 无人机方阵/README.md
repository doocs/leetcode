---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2039.%20%E6%97%A0%E4%BA%BA%E6%9C%BA%E6%96%B9%E9%98%B5/README.md
---

# [LCP 39. 无人机方阵](https://leetcode.cn/problems/0jQkd0)

## 题目描述

<!-- 这里写题目描述 -->

在 「力扣挑战赛」 开幕式的压轴节目 「无人机方阵」中，每一架无人机展示一种灯光颜色。 无人机方阵通过两种操作进行颜色图案变换：

-   调整无人机的位置布局
-   切换无人机展示的灯光颜色

给定两个大小均为 `N*M` 的二维数组 `source` 和 `target` 表示无人机方阵表演的两种颜色图案，由于无人机切换灯光颜色的耗能很大，请返回从 `source` 到 `target` 最少需要多少架无人机切换灯光颜色。

**注意：** 调整无人机的位置布局时无人机的位置可以随意变动。

**示例 1：**

> 输入：`source = [[1,3],[5,4]], target = [[3,1],[6,5]]`
>
> 输出：`1`
>
> 解释：
> 最佳方案为
> 将 `[0,1]` 处的无人机移动至 `[0,0]` 处；
> 将 `[0,0]` 处的无人机移动至 `[0,1]` 处；
> 将 `[1,0]` 处的无人机移动至 `[1,1]` 处；
> 将 `[1,1]` 处的无人机移动至 `[1,0]` 处，其灯光颜色切换为颜色编号为 `6` 的灯光；
> 因此从`source` 到 `target` 所需要的最少灯光切换次数为 1。
> ![8819ccdd664e91c78cde3bba3c701986.gif](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2039.%20%E6%97%A0%E4%BA%BA%E6%9C%BA%E6%96%B9%E9%98%B5/images/1628823765-uCDaux-8819ccdd664e91c78cde3bba3c701986.gif){:height=300px}

**示例 2：**

> 输入：`source = [[1,2,3],[3,4,5]], target = [[1,3,5],[2,3,4]]`
>
> 输出：`0`
> 解释：
> 仅需调整无人机的位置布局，便可完成图案切换。因此不需要无人机切换颜色

**提示：**
`n == source.length == target.length`
`m == source[i].length == target[i].length`
`1 <= n, m <=100`
`1 <= source[i][j], target[i][j] <=10^4`

## 解法

### 方法一：哈希表计数

我们可以用哈希表 $cnt$ 统计 $source$ 和 $target$ 中每个数字出现的次数之差。对于 $cnt$ 中的每个数字 $x$，如果 $x$ 的出现次数为正数，那么说明 $x$ 在 $target$ 中出现的次数多，我们需要将 $x$ 出现的次数减少到 $0$。因此，我们只需要累加所有出现次数为正数的数字的出现次数之和，即为答案。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是 $source$ 和 $target$ 的行数和列数。

<!-- tabs:start -->

```python
class Solution:
    def minimumSwitchingTimes(
        self, source: List[List[int]], target: List[List[int]]
    ) -> int:
        cnt = Counter()
        for row in source:
            for x in row:
                cnt[x] += 1
        for row in target:
            for x in row:
                cnt[x] -= 1
        return sum(abs(x) for x in cnt.values()) // 2
```

```java
class Solution {
    public int minimumSwitchingTimes(int[][] source, int[][] target) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int[] row : source) {
            for (int x : row) {
                cnt.merge(x, 1, Integer::sum);
            }
        }
        for (int[] row : target) {
            for (int x : row) {
                cnt.merge(x, -1, Integer::sum);
            }
        }
        int ans = 0;
        for (int v : cnt.values()) {
            ans += Math.abs(v);
        }
        return ans / 2;
    }
}
```

```cpp
class Solution {
public:
    int minimumSwitchingTimes(vector<vector<int>>& source, vector<vector<int>>& target) {
        unordered_map<int, int> cnt;
        for (auto& row : source) {
            for (int& x : row) {
                ++cnt[x];
            }
        }
        for (auto& row : target) {
            for (int& x : row) {
                --cnt[x];
            }
        }
        int ans = 0;
        for (auto& [_, v] : cnt) {
            ans += abs(v);
        }
        return ans / 2;
    }
};
```

```go
func minimumSwitchingTimes(source [][]int, target [][]int) (ans int) {
	cnt := map[int]int{}
	for _, row := range source {
		for _, x := range row {
			cnt[x]++
		}
	}
	for _, row := range target {
		for _, x := range row {
			cnt[x]--
		}
	}
	for _, v := range cnt {
		if v > 0 {
			ans += v
		}
	}
	return
}
```

```ts
function minimumSwitchingTimes(source: number[][], target: number[][]): number {
    const cnt: Map<number, number> = new Map();
    for (const row of source) {
        for (const x of row) {
            cnt.set(x, (cnt.get(x) || 0) + 1);
        }
    }
    for (const row of target) {
        for (const x of row) {
            cnt.set(x, (cnt.get(x) || 0) - 1);
        }
    }
    let ans = 0;
    for (const [_, v] of cnt) {
        ans += Math.abs(v);
    }
    return Math.floor(ans / 2);
}
```

<!-- tabs:end -->

<!-- end -->
