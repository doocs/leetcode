---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3638.Maximum%20Balanced%20Shipments/README_EN.md
rating: 1463
source: Weekly Contest 461 Q2
tags:
    - Stack
    - Greedy
    - Array
    - Dynamic Programming
    - Monotonic Stack
---

<!-- problem:start -->

# [3638. Maximum Balanced Shipments](https://leetcode.com/problems/maximum-balanced-shipments)

[中文文档](/solution/3600-3699/3638.Maximum%20Balanced%20Shipments/README.md)

## Description

<!-- description:start -->

<p data-end="365" data-start="23">You are given an integer array <code data-end="62" data-start="54">weight</code> of length <code data-end="76" data-start="73">n</code>, representing the weights of <code data-end="109" data-start="106">n</code> parcels arranged in a straight line. A <strong data-end="161" data-start="149">shipment</strong> is defined as a contiguous subarray of parcels. A shipment is considered <strong data-end="247" data-start="235">balanced</strong> if the weight of the <strong data-end="284" data-start="269">last parcel</strong> is <strong>strictly less</strong> than the <strong data-end="329" data-start="311">maximum weight</strong> among all parcels in that shipment.</p>

<p data-end="528" data-start="371">Select a set of <strong data-end="406" data-start="387">non-overlapping</strong>, contiguous, balanced shipments such that <strong data-end="496" data-start="449">each parcel appears in at most one shipment</strong> (parcels may remain unshipped).</p>

<p data-end="587" data-start="507">Return the <strong data-end="545" data-start="518">maximum possible number</strong> of balanced shipments that can be formed.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">weight = [2,5,1,4,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p data-end="136" data-start="62">We can form the maximum of two balanced shipments as follows:</p>

<ul>
	<li data-end="163" data-start="140">Shipment 1: <code>[2, 5, 1]</code>

    <ul>
    	<li data-end="195" data-start="168">Maximum parcel weight = 5</li>
    	<li data-end="275" data-start="200">Last parcel weight = 1, which is strictly less than 5. Thus, it&#39;s balanced.</li>
    </ul>
    </li>
    <li data-end="299" data-start="279">Shipment 2: <code>[4, 3]</code>
    <ul>
    	<li data-end="331" data-start="304">Maximum parcel weight = 4</li>
    	<li data-end="411" data-start="336">Last parcel weight = 3, which is strictly less than 4. Thus, it&#39;s balanced.</li>
    </ul>
    </li>

</ul>

<p data-end="519" data-start="413">It is impossible to partition the parcels to achieve more than two balanced shipments, so the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">weight = [4,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p data-end="635" data-start="574">No balanced shipment can be formed in this case:</p>

<ul>
	<li data-end="772" data-start="639">A shipment <code>[4, 4]</code> has maximum weight 4 and the last parcel&#39;s weight is also 4, which is not strictly less. Thus, it&#39;s not balanced.</li>
	<li data-end="885" data-start="775">Single-parcel shipments <code>[4]</code> have the last parcel weight equal to the maximum parcel weight, thus not balanced.</li>
</ul>

<p data-end="958" data-is-last-node="" data-is-only-node="" data-start="887">As there is no way to form even one balanced shipment, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li data-end="8706" data-start="8671"><code data-end="8704" data-start="8671">2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li data-end="8733" data-start="8709"><code data-end="8733" data-start="8709">1 &lt;= weight[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

We maintain the maximum value $\text{mx}$ of the currently traversed array, and iterate through each element $x$ in the array. If $x < \text{mx}$, it means the current element can serve as the last parcel of a balanced shipment, so we increment the answer by one and reset $\text{mx}$ to 0. Otherwise, we update $\text{mx}$ to the value of the current element $x$.

After the traversal, we return the answer.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$, using only constant extra space.

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
