---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1868.Product%20of%20Two%20Run-Length%20Encoded%20Arrays/README.md
tags:
    - 数组
    - 双指针
---

# [1868. 两个行程编码数组的积 🔒](https://leetcode.cn/problems/product-of-two-run-length-encoded-arrays)

[English Version](/solution/1800-1899/1868.Product%20of%20Two%20Run-Length%20Encoded%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><b>行程编码（</b><strong>Run-length encoding）</strong>是一种压缩算法，能让一个含有许多段<strong>连续重复</strong>数字的整数类型数组 <code>nums</code> 以一个（通常更小的）二维数组 <code>encoded</code> 表示。每个 <code>encoded[i] = [val<sub>i</sub>, freq<sub>i</sub>]</code> 表示 <code>nums</code> 中第 <code>i</code> 段重复数字，其中 <code>val<sub>i</sub></code> 是该段重复数字，重复了 <code>freq<sub>i</sub></code> 次。</p>

<ul>
	<li>例如， <code>nums = [1,1,1,2,2,2,2,2]</code> 可表示称行程编码数组 <code>encoded = [[1,3],[2,5]]</code> 。对此数组的另一种读法是“三个 <code>1</code> ，后面有五个 <code>2</code> ”。</li>
</ul>

<p>两个行程编码数组 <code>encoded1</code> 和 <code>encoded2</code> 的积可以按下列步骤计算：</p>

<ol>
	<li>将 <code>encoded1</code> 和 <code>encoded2</code> 分别<strong>扩展</strong>成完整数组 <code>nums1</code> 和 <code>nums2</code> 。</li>
	<li>创建一个新的数组 <code>prodNums</code> ，长度为 <code>nums1.length</code> 并设 <code>prodNums[i] = nums1[i] * nums2[i]</code> 。</li>
	<li>将 <code>prodNums</code> <strong>压缩</strong>成一个行程编码数组并返回之。</li>
</ol>

<p>给定两个<strong>行程编码</strong>数组 <code>encoded1</code> 和 <code>encoded2</code> ，分别表示完整数组 <code>nums1</code> 和 <code>nums2</code> 。<code>nums1</code> 和 <code>nums2</code> 的<strong>长度相同</strong>。 每一个 <code>encoded1[i] = [val<sub>i</sub>, freq<sub>i</sub>]</code> 表示 <code>nums1</code> 中的第 <code>i</code> 段，每一个 <code>encoded2[j] = [val<sub>j</sub>, freq<sub>j</sub>]</code> 表示 <code>nums2</code> 中的第 <code>j</code> 段。</p>

<p>返回<i> </i><code>encoded1</code> 和 <code>encoded2</code> 的<strong>乘积</strong>。</p>

<p><b>注：</b>行程编码数组需压缩成可能的<strong>最小</strong>长度。</p>

<p> </p>

<p><b>示例 1:</b></p>

<pre><strong>输入:</strong> encoded1 = [[1,3],[2,3]], encoded2 = [[6,3],[3,3]]
<strong>输出:</strong> [[6,6]]
<strong>解释n:</strong> encoded1 扩展为 [1,1,1,2,2,2] ，encoded2 扩展为 [6,6,6,3,3,3]。
prodNums = [6,6,6,6,6,6]，压缩成行程编码数组 [[6,6]]。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> encoded1 = [[1,3],[2,1],[3,2]], encoded2 = [[2,3],[3,3]]
<strong>输出:</strong> [[2,3],[6,1],[9,2]]
<strong>解释:</strong> encoded1 扩展为 [1,1,1,2,3,3] ，encoded2 扩展为 [2,2,2,3,3,3]。
prodNums = [2,2,2,6,9,9]，压缩成行程编码数组 [[2,3],[6,1],[9,2]]。
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= encoded1.length, encoded2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>encoded1[i].length == 2</code></li>
	<li><code>encoded2[j].length == 2</code></li>
	<li>对于每一个 <code>encoded1[i]</code>， <code>1 &lt;= val<sub>i</sub>, freq<sub>i</sub> &lt;= 10<sup>4</sup></code>  </li>
	<li>对于每一个 <code>encoded2[j]</code>， <code>1 &lt;= val<sub>j</sub>, freq<sub>j</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>encoded1</code> 和 <code>encoded2</code> 表示的完整数组长度相同。</li>
</ul>

## 解法

### 方法一：双指针

我们用两个指针 $i$ 和 $j$ 分别指向两个数组的当前位置，然后开始模拟乘法的过程。

对于当前位置 $i$ 和 $j$，我们取 $f=min(encoded1[i][1],encoded2[j][1])$，表示当前位置的乘积的频次，然后将 $v=encoded1[i][0] \times encoded2[j][0]$，表示当前位置的乘积的值。如果当前位置的乘积的值 $v$ 和上一个位置的乘积的值相同，则将当前位置的乘积的频次加到上一个位置的乘积的频次上，否则将当前位置的乘积的值和频次加到答案数组中。然后我们将 $encoded1[i][1]$ 和 $encoded2[j][1]$ 分别减去 $f$，如果 $encoded1[i][1]$ 或 $encoded2[j][1]$ 减为 $0$，则将对应的指针向后移动一位。

最后返回答案数组即可。

时间复杂度 $O(m + n)$，其中 $m$ 和 $n$ 分别是两个数组的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def findRLEArray(
        self, encoded1: List[List[int]], encoded2: List[List[int]]
    ) -> List[List[int]]:
        ans = []
        j = 0
        for vi, fi in encoded1:
            while fi:
                f = min(fi, encoded2[j][1])
                v = vi * encoded2[j][0]
                if ans and ans[-1][0] == v:
                    ans[-1][1] += f
                else:
                    ans.append([v, f])
                fi -= f
                encoded2[j][1] -= f
                if encoded2[j][1] == 0:
                    j += 1
        return ans
```

```java
class Solution {
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> ans = new ArrayList<>();
        int j = 0;
        for (var e : encoded1) {
            int vi = e[0], fi = e[1];
            while (fi > 0) {
                int f = Math.min(fi, encoded2[j][1]);
                int v = vi * encoded2[j][0];
                int m = ans.size();
                if (m > 0 && ans.get(m - 1).get(0) == v) {
                    ans.get(m - 1).set(1, ans.get(m - 1).get(1) + f);
                } else {
                    ans.add(new ArrayList<>(List.of(v, f)));
                }
                fi -= f;
                encoded2[j][1] -= f;
                if (encoded2[j][1] == 0) {
                    ++j;
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
    vector<vector<int>> findRLEArray(vector<vector<int>>& encoded1, vector<vector<int>>& encoded2) {
        vector<vector<int>> ans;
        int j = 0;
        for (auto& e : encoded1) {
            int vi = e[0], fi = e[1];
            while (fi) {
                int f = min(fi, encoded2[j][1]);
                int v = vi * encoded2[j][0];
                if (!ans.empty() && ans.back()[0] == v) {
                    ans.back()[1] += f;
                } else {
                    ans.push_back({v, f});
                }
                fi -= f;
                encoded2[j][1] -= f;
                if (encoded2[j][1] == 0) {
                    ++j;
                }
            }
        }
        return ans;
    }
};
```

```go
func findRLEArray(encoded1 [][]int, encoded2 [][]int) (ans [][]int) {
	j := 0
	for _, e := range encoded1 {
		vi, fi := e[0], e[1]
		for fi > 0 {
			f := min(fi, encoded2[j][1])
			v := vi * encoded2[j][0]
			if len(ans) > 0 && ans[len(ans)-1][0] == v {
				ans[len(ans)-1][1] += f
			} else {
				ans = append(ans, []int{v, f})
			}
			fi -= f
			encoded2[j][1] -= f
			if encoded2[j][1] == 0 {
				j++
			}
		}
	}
	return
}
```

<!-- tabs:end -->

<!-- end -->
