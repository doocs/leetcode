# [1200. 最小绝对差](https://leetcode.cn/problems/minimum-absolute-difference)

[English Version](/solution/1200-1299/1200.Minimum%20Absolute%20Difference/README_EN.md)

<!-- tags:数组,排序 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你个整数数组&nbsp;<code>arr</code>，其中每个元素都 <strong>不相同</strong>。</p>

<p>请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。</p>

<p>每对元素对 <code>[a,b</code>] 如下：</p>

<ul>
	<li><code>a ,&nbsp;b</code>&nbsp;均为数组&nbsp;<code>arr</code>&nbsp;中的元素</li>
	<li><code>a &lt; b</code></li>
	<li><code>b - a</code>&nbsp;等于 <code>arr</code> 中任意两个元素的最小绝对差</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [4,2,1,3]
<strong>输出：</strong>[[1,2],[2,3],[3,4]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,3,6,10,15]
<strong>输出：</strong>[[1,3]]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [3,8,-10,23,19,-4,-14,27]
<strong>输出：</strong>[[-14,-10],[19,23],[23,27]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 10^5</code></li>
	<li><code>-10^6 &lt;= arr[i] &lt;= 10^6</code></li>
</ul>

## 解法

### 方法一：排序

根据题目描述，我们需要找出数组 $arr$ 中任意两个元素的最小绝对差，因此我们可以先对数组 $arr$ 排序，随后遍历相邻元素，得到最小绝对差 $mi$。

最后我们再遍历相邻元素，找出所有最小绝对差等于 $mi$ 的元素对。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $arr$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:
        arr.sort()
        mi = min(b - a for a, b in pairwise(arr))
        return [[a, b] for a, b in pairwise(arr) if b - a == mi]
```

```java
class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int mi = 1 << 30;
        for (int i = 0; i < n - 1; ++i) {
            mi = Math.min(mi, arr[i + 1] - arr[i]);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n - 1; ++i) {
            if (arr[i + 1] - arr[i] == mi) {
                ans.add(List.of(arr[i], arr[i + 1]));
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<vector<int>> minimumAbsDifference(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        int mi = 1 << 30;
        int n = arr.size();
        for (int i = 0; i < n - 1; ++i) {
            mi = min(mi, arr[i + 1] - arr[i]);
        }
        vector<vector<int>> ans;
        for (int i = 0; i < n - 1; ++i) {
            if (arr[i + 1] - arr[i] == mi) {
                ans.push_back({arr[i], arr[i + 1]});
            }
        }
        return ans;
    }
};
```

```go
func minimumAbsDifference(arr []int) (ans [][]int) {
	sort.Ints(arr)
	mi := 1 << 30
	n := len(arr)
	for i := 0; i < n-1; i++ {
		if t := arr[i+1] - arr[i]; t < mi {
			mi = t
		}
	}
	for i := 0; i < n-1; i++ {
		if arr[i+1]-arr[i] == mi {
			ans = append(ans, []int{arr[i], arr[i+1]})
		}
	}
	return
}
```

```ts
function minimumAbsDifference(arr: number[]): number[][] {
    arr.sort((a, b) => a - b);
    let mi = 1 << 30;
    const n = arr.length;
    for (let i = 0; i < n - 1; ++i) {
        mi = Math.min(mi, arr[i + 1] - arr[i]);
    }
    const ans: number[][] = [];
    for (let i = 0; i < n - 1; ++i) {
        if (arr[i + 1] - arr[i] === mi) {
            ans.push([arr[i], arr[i + 1]]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
