---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1086.High%20Five/README.md
rating: 1327
source: 第 2 场双周赛 Q2
tags:
    - 数组
    - 哈希表
    - 排序
    - 堆（优先队列）
---

# [1086. 前五科的均分 🔒](https://leetcode.cn/problems/high-five)

[English Version](/solution/1000-1099/1086.High%20Five/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个不同学生的分数列表 <code>items</code>，其中 <code>items[i] = [ID<sub>i</sub>, score<sub>i</sub>]</code> 表示 <code>ID<sub>i</sub></code> 的学生的一科分数，你需要计算每个学生 <strong>最高的五科 </strong>成绩的 <strong>平均分</strong>。</p>

<p>返回答案 <code>result</code> 以数对数组形式给出<em>，</em>其中<em> </em><code>result[j] = [ID<sub>j</sub>, topFiveAverage<sub>j</sub>]</code><em> </em>表示<em> </em><code>ID<sub>j</sub></code><em> </em>的学生和他 <strong>最高的五科 </strong>成绩的 <strong>平均分</strong><em>。</em><code>result</code><em> </em>需要按<em> </em><code>ID<sub>j</sub></code><em>  </em>递增的 <strong>顺序排列</strong> 。</p>

<p>学生 <strong>最高的五科 </strong>成绩的 <strong>平均分 </strong>的计算方法是将最高的五科分数相加，然后用 <strong>整数除法</strong> 除以 5 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>items = [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
<strong>输出：</strong>[[1,87],[2,88]]
<strong>解释：</strong>
ID = 1 的学生分数为 91、92、60、65、87 和 100 。前五科的平均分 (100 + 92 + 91 + 87 + 65) / 5 = 87
ID = 2 的学生分数为 93、97、77、100 和 76 。前五科的平均分 (100 + 97 + 93 + 77 + 76) / 5 = 88.6，但是由于使用整数除法，结果转换为 88
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>items = [[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100]]
<strong>输出：</strong>[[1,100],[7,100]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= items.length <= 1000</code></li>
	<li><code>items[i].length == 2</code></li>
	<li><code>1 <= ID<sub>i</sub> <= 1000</code></li>
	<li><code>0 <= score<sub>i</sub> <= 100</code></li>
	<li>对于每个 <code>ID<sub>i</sub></code>，<strong>至少</strong> 存在五个分数</li>
</ul>

## 解法

### 方法一：排序

我们先用一个哈希表或数组 $d$ 记录每个学生的分数列表，然后从小到大遍历学生的编号，对于每个学生，我们将他的分数列表排序，然后取最高的五个分数求平均值即可。

时间复杂度 $O(n \log n)$，空间复杂度 $O(n)$。其中 $n$ 是学生的数量。

<!-- tabs:start -->

```python
class Solution:
    def highFive(self, items: List[List[int]]) -> List[List[int]]:
        d = defaultdict(list)
        m = 0
        for i, x in items:
            d[i].append(x)
            m = max(m, i)
        ans = []
        for i in range(1, m + 1):
            if xs := d[i]:
                avg = sum(nlargest(5, xs)) // 5
                ans.append([i, avg])
        return ans
```

```java
class Solution {
    public int[][] highFive(int[][] items) {
        int size = 0;
        PriorityQueue[] s = new PriorityQueue[101];
        int n = 5;
        for (int[] item : items) {
            int i = item[0], score = item[1];
            if (s[i] == null) {
                ++size;
                s[i] = new PriorityQueue<>(n);
            }
            s[i].offer(score);
            if (s[i].size() > n) {
                s[i].poll();
            }
        }
        int[][] res = new int[size][2];
        int j = 0;
        for (int i = 0; i < 101; ++i) {
            if (s[i] == null) {
                continue;
            }
            int avg = sum(s[i]) / n;
            res[j][0] = i;
            res[j++][1] = avg;
        }
        return res;
    }

    private int sum(PriorityQueue<Integer> q) {
        int s = 0;
        while (!q.isEmpty()) {
            s += q.poll();
        }
        return s;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> highFive(vector<vector<int>>& items) {
        vector<int> d[1001];
        for (auto& item : items) {
            int i = item[0], x = item[1];
            d[i].push_back(x);
        }
        vector<vector<int>> ans;
        for (int i = 1; i <= 1000; ++i) {
            if (!d[i].empty()) {
                sort(d[i].begin(), d[i].end(), greater<int>());
                int s = 0;
                for (int j = 0; j < 5; ++j) {
                    s += d[i][j];
                }
                ans.push_back({i, s / 5});
            }
        }
        return ans;
    }
};
```

```go
func highFive(items [][]int) (ans [][]int) {
	d := make([][]int, 1001)
	for _, item := range items {
		i, x := item[0], item[1]
		d[i] = append(d[i], x)
	}
	for i := 1; i <= 1000; i++ {
		if len(d[i]) > 0 {
			sort.Ints(d[i])
			s := 0
			for j := len(d[i]) - 1; j >= len(d[i])-5; j-- {
				s += d[i][j]
			}
			ans = append(ans, []int{i, s / 5})
		}
	}
	return ans
}
```

```ts
function highFive(items: number[][]): number[][] {
    const d: number[][] = Array(1001)
        .fill(0)
        .map(() => Array(0));
    for (const [i, x] of items) {
        d[i].push(x);
    }
    const ans: number[][] = [];
    for (let i = 1; i <= 1000; ++i) {
        if (d[i].length > 0) {
            d[i].sort((a, b) => b - a);
            const s = d[i].slice(0, 5).reduce((a, b) => a + b);
            ans.push([i, Math.floor(s / 5)]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
