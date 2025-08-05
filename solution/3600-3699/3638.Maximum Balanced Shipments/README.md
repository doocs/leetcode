---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3638.Maximum%20Balanced%20Shipments/README.md
---

<!-- problem:start -->

# [3638. 平衡装运的最大数量](https://leetcode.cn/problems/maximum-balanced-shipments)

[English Version](/solution/3600-3699/3638.Maximum%20Balanced%20Shipments/README_EN.md)

## 题目描述

<!-- description:start -->

<p data-end="365" data-start="23">给你一个长度为 <code data-end="76" data-start="73">n</code> 的整数数组 <code data-end="62" data-start="54">weight</code>，表示按直线排列的 <code data-end="109" data-start="106">n</code> 个包裹的重量。<b>装运</b>&nbsp;定义为包裹的一个连续子数组。如果一个装运满足以下条件，则称其为 <strong data-end="247" data-start="235">平衡装运</strong>：<strong data-end="284" data-start="269">最后一个包裹的重量</strong> <strong>严格小于&nbsp;</strong>该装运中所有包裹中&nbsp;<strong data-end="329" data-start="311">最大重量&nbsp;</strong>。</p>

<p data-end="528" data-start="371">选择若干个&nbsp;<strong data-end="406" data-start="387">不重叠&nbsp;</strong>的连续平衡装运，并满足&nbsp;<strong data-end="496" data-start="449">每个包裹最多出现在一次装运中</strong>（部分包裹可以不被装运）。</p>

<p data-end="587" data-start="507">返回 <strong data-end="545" data-start="518">可以形成的平衡装运的最大数量&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">weight = [2,5,1,4,3]</span></p>

<p><strong>输出:</strong> <span class="example-io">2</span></p>

<p><strong>解释:</strong></p>

<p data-end="136" data-start="62">我们可以形成最多两个平衡装运：</p>

<ul>
	<li data-end="163" data-start="140">装运 1: <code>[2, 5, 1]</code>

    <ul>
    	<li data-end="195" data-start="168">包裹的最大重量 = 5</li>
    	<li data-end="275" data-start="200">最后一个包裹的重量 = 1，严格小于 5，因此这是平衡装运。</li>
    </ul>
    </li>
    <li data-end="299" data-start="279">装运 2: <code>[4, 3]</code>
    <ul>
    	<li data-end="331" data-start="304">包裹的最大重量 = 4</li>
    	<li data-end="411" data-start="336">最后一个包裹的重量 = 3，严格小于 4，因此这是平衡装运。</li>
    </ul>
    </li>

</ul>

<p data-end="519" data-start="413">无法通过其他方式划分包裹获得超过两个平衡装运，因此答案是 2。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">weight = [4,4]</span></p>

<p><strong>输出:</strong> <span class="example-io">0</span></p>

<p><strong>解释:</strong></p>

<p data-end="635" data-start="574">在这种情况下无法形成平衡装运：</p>

<ul>
	<li data-end="772" data-start="639">装运 <code>[4, 4]</code> 的最大重量为 4，而最后一个包裹的重量也是 4，不严格小于最大重量，因此不是平衡的。</li>
	<li data-end="885" data-start="775">单个包裹的装运 <code>[4]</code> 中，最后一个包裹的重量等于最大重量，因此也不是平衡的。</li>
</ul>

<p data-end="958" data-is-last-node="" data-is-only-node="" data-start="887">由于无法形成任何平衡装运，答案是 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li data-end="8706" data-start="8671"><code data-end="8704" data-start="8671">2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li data-end="8733" data-start="8709"><code data-end="8733" data-start="8709">1 &lt;= weight[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们维护当前遍历的数组的最大值 $\text{mx}$，并遍历数组中的每个元素 $x$。如果 $x < \text{mx}$，则说明当前元素可以作为一个平衡装运的最后一个包裹，因此我们就将答案加一，并将 $\text{mx}$ 重置为 0。否则，我们更新 $\text{mx}$ 为当前元素 $x$ 的值。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$，只使用了常数级别的额外空间。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxBalancedShipments(self, weight: List[int]) -> int:
        ans = mx = 0
        for x in weight:
            mx = max(mx, x)
            if x < mx:
                ans += 1
                mx = 0
        return ans
```

#### Java

```java
class Solution {
    public int maxBalancedShipments(int[] weight) {
        int ans = 0;
        int mx = 0;
        for (int x : weight) {
            mx = Math.max(mx, x);
            if (x < mx) {
                ++ans;
                mx = 0;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxBalancedShipments(vector<int>& weight) {
        int ans = 0;
        int mx = 0;
        for (int x : weight) {
            mx = max(mx, x);
            if (x < mx) {
                ++ans;
                mx = 0;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxBalancedShipments(weight []int) (ans int) {
	mx := 0
	for _, x := range weight {
		mx = max(mx, x)
		if x < mx {
			ans++
			mx = 0
		}
	}
	return
}
```

#### TypeScript

```ts
function maxBalancedShipments(weight: number[]): number {
    let [ans, mx] = [0, 0];
    for (const x of weight) {
        mx = Math.max(mx, x);
        if (x < mx) {
            ans++;
            mx = 0;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
