# [1762. 能看到海景的建筑物](https://leetcode.cn/problems/buildings-with-an-ocean-view)

[English Version](/solution/1700-1799/1762.Buildings%20With%20an%20Ocean%20View/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code> 座建筑物。给你一个大小为 <code>n</code> 的整数数组 <code>heights</code> 表示每一个建筑物的高度。</p>

<p>建筑物的右边是海洋。如果建筑物可以无障碍地看到海洋，则建筑物能看到海景。确切地说，如果一座建筑物右边的所有建筑都比它 <strong>矮</strong> 时，就认为它能看到海景。</p>

<p>返回能看到海景建筑物的下标列表（下标 <strong>从 <code>0</code> 开始</strong> ），并按升序排列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>heights = [4,2,3,1]
<strong>输出：</strong>[0,2,3]
<strong>解释：</strong>1 号建筑物看不到海景，因为 2 号建筑物比它高
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>heights = [4,3,2,1]
<strong>输出：</strong>[0,1,2,3]
<strong>解释：</strong>所有的建筑物都能看到海景。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>heights = [1,3,2,4]
<strong>输出：</strong>[3]
<strong>解释：</strong>只有 3 号建筑物能看到海景。</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>heights = [2,2,2,2]
<strong>输出：</strong>[3]
<strong>解释：</strong>如果建筑物右边有相同高度的建筑物则无法看到海景。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= heights.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= heights[i] <= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：逆序遍历求右侧最大值

我们逆序遍历数组 $height$ 每个元素 $v$，判断 $v$ 与右侧最大元素 $mx$ 的大小关系，若 $mx \lt v$，说明右侧所有元素都比当前元素小，当前位置能看到海景，加入结果数组 $ans$。然后我们更新 $mx$ 为 $v$。

遍历结束后，逆序返回 $ans$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为数组长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def findBuildings(self, heights: List[int]) -> List[int]:
        ans = []
        mx = 0
        for i in range(len(heights) - 1, -1, -1):
            if heights[i] > mx:
                ans.append(i)
                mx = heights[i]
        return ans[::-1]
```

```java
class Solution {
    public int[] findBuildings(int[] heights) {
        int n = heights.length;
        List<Integer> ans = new ArrayList<>();
        int mx = 0;
        for (int i = heights.length - 1; i >= 0; --i) {
            if (heights[i] > mx) {
                ans.add(i);
                mx = heights[i];
            }
        }
        Collections.reverse(ans);
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
```

```cpp
class Solution {
public:
    vector<int> findBuildings(vector<int>& heights) {
        vector<int> ans;
        int mx = 0;
        for (int i = heights.size() - 1; ~i; --i) {
            if (heights[i] > mx) {
                ans.push_back(i);
                mx = heights[i];
            }
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

```go
func findBuildings(heights []int) (ans []int) {
	mx := 0
	for i := len(heights) - 1; i >= 0; i-- {
		if v := heights[i]; v > mx {
			ans = append(ans, i)
			mx = v
		}
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return
}
```

```ts
function findBuildings(heights: number[]): number[] {
    const ans: number[] = [];
    let mx = 0;
    for (let i = heights.length - 1; ~i; --i) {
        if (heights[i] > mx) {
            ans.push(i);
            mx = heights[i];
        }
    }
    return ans.reverse();
}
```

```js
/**
 * @param {number[]} heights
 * @return {number[]}
 */
var findBuildings = function (heights) {
    const ans = [];
    let mx = 0;
    for (let i = heights.length - 1; ~i; --i) {
        if (heights[i] > mx) {
            ans.push(i);
            mx = heights[i];
        }
    }
    return ans.reverse();
};
```

<!-- tabs:end -->

<!-- end -->
