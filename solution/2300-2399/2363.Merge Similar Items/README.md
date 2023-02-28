# [2363. 合并相似的物品](https://leetcode.cn/problems/merge-similar-items)

[English Version](/solution/2300-2399/2363.Merge%20Similar%20Items/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个二维整数数组&nbsp;<code>items1</code> 和&nbsp;<code>items2</code>&nbsp;，表示两个物品集合。每个数组&nbsp;<code>items</code>&nbsp;有以下特质：</p>

<ul>
	<li><code>items[i] = [value<sub>i</sub>, weight<sub>i</sub>]</code> 其中&nbsp;<code>value<sub>i</sub></code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;件物品的&nbsp;<strong>价值</strong>&nbsp;，<code>weight<sub>i</sub></code>&nbsp;表示第 <code>i</code>&nbsp;件物品的 <strong>重量</strong>&nbsp;。</li>
	<li><code>items</code>&nbsp;中每件物品的价值都是 <strong>唯一的</strong>&nbsp;。</li>
</ul>

<p>请你返回一个二维数组&nbsp;<code>ret</code>，其中&nbsp;<code>ret[i] = [value<sub>i</sub>, weight<sub>i</sub>]</code>，&nbsp;<code>weight<sub>i</sub></code>&nbsp;是所有价值为&nbsp;<code>value<sub>i</sub></code><sub>&nbsp;</sub>物品的&nbsp;<strong>重量之和</strong>&nbsp;。</p>

<p><strong>注意：</strong><code>ret</code>&nbsp;应该按价值 <strong>升序</strong>&nbsp;排序后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>items1 = [[1,1],[4,5],[3,8]], items2 = [[3,1],[1,5]]
<b>输出：</b>[[1,6],[3,9],[4,5]]
<b>解释：</b>
value = 1 的物品在 items1 中 weight = 1 ，在 items2 中 weight = 5 ，总重量为 1 + 5 = 6 。
value = 3 的物品再 items1 中 weight = 8 ，在 items2 中 weight = 1 ，总重量为 8 + 1 = 9 。
value = 4 的物品在 items1 中 weight = 5 ，总重量为 5 。
所以，我们返回 [[1,6],[3,9],[4,5]] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>items1 = [[1,1],[3,2],[2,3]], items2 = [[2,1],[3,2],[1,3]]
<b>输出：</b>[[1,4],[2,4],[3,4]]
<b>解释：</b>
value = 1 的物品在 items1 中 weight = 1 ，在 items2 中 weight = 3 ，总重量为 1 + 3 = 4 。
value = 2 的物品在 items1 中 weight = 3 ，在 items2 中 weight = 1 ，总重量为 3 + 1 = 4 。
value = 3 的物品在 items1 中 weight = 2 ，在 items2 中 weight = 2 ，总重量为 2 + 2 = 4 。
所以，我们返回 [[1,4],[2,4],[3,4]] 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>items1 = [[1,3],[2,2]], items2 = [[7,1],[2,2],[1,4]]
<b>输出：</b>[[1,7],[2,4],[7,1]]
<strong>解释：
</strong>value = 1 的物品在 items1 中 weight = 3 ，在 items2 中 weight = 4 ，总重量为 3 + 4 = 7 。
value = 2 的物品在 items1 中 weight = 2 ，在 items2 中 weight = 2 ，总重量为 2 + 2 = 4 。
value = 7 的物品在 items2 中 weight = 1 ，总重量为 1 。
所以，我们返回 [[1,7],[2,4],[7,1]] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= items1.length, items2.length &lt;= 1000</code></li>
	<li><code>items1[i].length == items2[i].length == 2</code></li>
	<li><code>1 &lt;= value<sub>i</sub>, weight<sub>i</sub> &lt;= 1000</code></li>
	<li><code>items1</code>&nbsp;中每个 <code>value<sub>i</sub></code>&nbsp;都是 <b>唯一的</b>&nbsp;。</li>
	<li><code>items2</code>&nbsp;中每个 <code>value<sub>i</sub></code>&nbsp;都是 <b>唯一的</b>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表或数组**

我们可以用哈希表或数组 `cnt` 统计 `items1` 和 `items2` 中每个物品的总重量，然后从小到大遍历价值，将每个价值以及对应的总重量加入结果数组即可。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别是 `items1` 和 `items2` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def mergeSimilarItems(
        self, items1: List[List[int]], items2: List[List[int]]
    ) -> List[List[int]]:
        cnt = Counter()
        for v, w in chain(items1, items2):
            cnt[v] += w
        return sorted(cnt.items())
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        int[] cnt = new int[1010];
        for (var x : items1) {
            cnt[x[0]] += x[1];
        }
        for (var x : items2) {
            cnt[x[0]] += x[1];
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < cnt.length; ++i) {
            if (cnt[i] > 0) {
                ans.add(List.of(i, cnt[i]));
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
    vector<vector<int>> mergeSimilarItems(vector<vector<int>>& items1, vector<vector<int>>& items2) {
        int cnt[1010]{};
        for (auto& x : items1) {
            cnt[x[0]] += x[1];
        }
        for (auto& x : items2) {
            cnt[x[0]] += x[1];
        }
        vector<vector<int>> ans;
        for (int i = 0; i < 1010; ++i) {
            if (cnt[i]) {
                ans.push_back({i, cnt[i]});
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func mergeSimilarItems(items1 [][]int, items2 [][]int) (ans [][]int) {
	cnt := [1010]int{}
	for _, x := range items1 {
		cnt[x[0]] += x[1]
	}
	for _, x := range items2 {
		cnt[x[0]] += x[1]
	}
	for i, x := range cnt {
		if x > 0 {
			ans = append(ans, []int{i, x})
		}
	}
	return
}
```

### **TypeScript**

```ts
function mergeSimilarItems(items1: number[][], items2: number[][]): number[][] {
    const count = new Array(1001).fill(0);
    for (const [v, w] of items1) {
        count[v] += w;
    }
    for (const [v, w] of items2) {
        count[v] += w;
    }
    return [...count.entries()].filter(v => v[1] !== 0);
}
```

### **Rust**

```rust
impl Solution {
    pub fn merge_similar_items(items1: Vec<Vec<i32>>, items2: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let mut count = [0; 1001];
        for item in items1.iter() {
            count[item[0] as usize] += item[1];
        }
        for item in items2.iter() {
            count[item[0] as usize] += item[1];
        }
        count
            .iter()
            .enumerate()
            .filter_map(|(i, &v)| {
                if v == 0 {
                    return None;
                }
                Some(vec![i as i32, v])
            })
            .collect()
    }
}
```

### **C**

```c
/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
int **mergeSimilarItems(int **items1, int items1Size, int *items1ColSize, int **items2, int items2Size,
                        int *items2ColSize, int *returnSize, int **returnColumnSizes) {
    int count[1001] = {0};
    for (int i = 0; i < items1Size; i++) {
        count[items1[i][0]] += items1[i][1];
    }
    for (int i = 0; i < items2Size; i++) {
        count[items2[i][0]] += items2[i][1];
    }
    int **ans = malloc(sizeof(int *) * (items1Size + items2Size));
    *returnColumnSizes = malloc(sizeof(int) * (items1Size + items2Size));
    int size = 0;
    for (int i = 0; i < 1001; i++) {
        if (count[i]) {
            ans[size] = malloc(sizeof(int) * 2);
            ans[size][0] = i;
            ans[size][1] = count[i];
            (*returnColumnSizes)[size] = 2;
            size++;
        }
    }
    *returnSize = size;
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
