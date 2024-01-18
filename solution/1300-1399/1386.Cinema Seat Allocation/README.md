# [1386. 安排电影院座位](https://leetcode.cn/problems/cinema-seat-allocation)

[English Version](/solution/1300-1399/1386.Cinema%20Seat%20Allocation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1386.Cinema%20Seat%20Allocation/images/cinema_seats_1.png" style="height: 149px; width: 400px;"></p>

<p>如上图所示，电影院的观影厅中有 <code>n</code>&nbsp;行座位，行编号从 1&nbsp;到 <code>n</code>&nbsp;，且每一行内总共有 10 个座位，列编号从 1 到 10 。</p>

<p>给你数组&nbsp;<code>reservedSeats</code>&nbsp;，包含所有已经被预约了的座位。比如说，<code>researvedSeats[i]=[3,8]</code>&nbsp;，它表示第&nbsp;<strong>3</strong>&nbsp;行第&nbsp;<strong>8</strong>&nbsp;个座位被预约了。</p>

<p>请你返回&nbsp;<strong>最多能安排多少个 4 人家庭</strong>&nbsp;。4 人家庭要占据&nbsp;<strong>同一行内连续&nbsp;</strong>的 4 个座位。隔着过道的座位（比方说 [3,3] 和 [3,4]）不是连续的座位，但是如果你可以将 4 人家庭拆成过道两边各坐 2 人，这样子是允许的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1386.Cinema%20Seat%20Allocation/images/cinema_seats_3.png" style="height: 96px; width: 400px;"></p>

<pre><strong>输入：</strong>n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
<strong>输出：</strong>4
<strong>解释：</strong>上图所示是最优的安排方案，总共可以安排 4 个家庭。蓝色的叉表示被预约的座位，橙色的连续座位表示一个 4 人家庭。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
<strong>输出：</strong>4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^9</code></li>
	<li><code>1 &lt;=&nbsp;reservedSeats.length &lt;= min(10*n, 10^4)</code></li>
	<li><code>reservedSeats[i].length == 2</code></li>
	<li><code>1&nbsp;&lt;=&nbsp;reservedSeats[i][0] &lt;= n</code></li>
	<li><code>1 &lt;=&nbsp;reservedSeats[i][1] &lt;= 10</code></li>
	<li>所有&nbsp;<code>reservedSeats[i]</code> 都是互不相同的。</li>
</ul>

## 解法

### 方法一：哈希表 + 状态压缩

我们用哈希表 $d$ 来存储所有已经被预约的座位，其中键为行号，值为该行上已经被预约的座位的状态，即一个二进制数，第 $j$ 位为 $1$ 表示第 $j$ 个座位已经被预约，为 $0$ 表示第 $j$ 个座位尚未被预约。

我们遍历 $reservedSeats$，对于每个座位 $(i, j)$，将第 $j$ 个座位（对应低位的第 $10-j$ 位）的状态加入到 $d[i]$ 中即可。

对于没有出现在哈希表 $d$ 中的行，我们可以任意安排 $2$ 个家庭，因此，初始答案为 $(n - len(d)) \times 2$。

接下来，我们遍历哈希表中每一行的状态，对于每一行，我们依次尝试安排 $1234, 5678, 3456$ 这几种情况，如果某种情况可以安排，我们就将答案加 $1$。

遍历结束后，我们就得到了最终的答案。

时间复杂度 $O(m)$，空间复杂度 $O(m)$，其中 $m$ 是 $reservedSeats$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maxNumberOfFamilies(self, n: int, reservedSeats: List[List[int]]) -> int:
        d = defaultdict(int)
        for i, j in reservedSeats:
            d[i] |= 1 << (10 - j)
        masks = (0b0111100000, 0b0000011110, 0b0001111000)
        ans = (n - len(d)) * 2
        for x in d.values():
            for mask in masks:
                if (x & mask) == 0:
                    x |= mask
                    ans += 1
        return ans
```

```java
class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> d = new HashMap<>();
        for (var e : reservedSeats) {
            int i = e[0], j = e[1];
            d.merge(i, 1 << (10 - j), (x, y) -> x | y);
        }
        int[] masks = {0b0111100000, 0b0000011110, 0b0001111000};
        int ans = (n - d.size()) * 2;
        for (int x : d.values()) {
            for (int mask : masks) {
                if ((x & mask) == 0) {
                    x |= mask;
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxNumberOfFamilies(int n, vector<vector<int>>& reservedSeats) {
        unordered_map<int, int> d;
        for (auto& e : reservedSeats) {
            int i = e[0], j = e[1];
            d[i] |= 1 << (10 - j);
        }
        int masks[3] = {0b0111100000, 0b0000011110, 0b0001111000};
        int ans = (n - d.size()) * 2;
        for (auto& [_, x] : d) {
            for (int& mask : masks) {
                if ((x & mask) == 0) {
                    x |= mask;
                    ++ans;
                }
            }
        }
        return ans;
    }
};
```

```go
func maxNumberOfFamilies(n int, reservedSeats [][]int) int {
	d := map[int]int{}
	for _, e := range reservedSeats {
		i, j := e[0], e[1]
		d[i] |= 1 << (10 - j)
	}
	ans := (n - len(d)) * 2
	masks := [3]int{0b0111100000, 0b0000011110, 0b0001111000}
	for _, x := range d {
		for _, mask := range masks {
			if x&mask == 0 {
				x |= mask
				ans++
			}
		}
	}
	return ans
}
```

```ts
function maxNumberOfFamilies(n: number, reservedSeats: number[][]): number {
    const d: Map<number, number> = new Map();
    for (const [i, j] of reservedSeats) {
        d.set(i, (d.get(i) ?? 0) | (1 << (10 - j)));
    }
    let ans = (n - d.size) << 1;
    const masks = [0b0111100000, 0b0000011110, 0b0001111000];
    for (let [_, x] of d) {
        for (const mask of masks) {
            if ((x & mask) === 0) {
                x |= mask;
                ++ans;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
