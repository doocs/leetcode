# [888. Fair Candy Swap](https://leetcode.com/problems/fair-candy-swap)

[中文文档](/solution/0800-0899/0888.Fair%20Candy%20Swap/README.md)

## Description

<p>Alice and Bob have candy bars of different sizes: <code>A[i]</code> is the size of the <code>i</code>-th bar of candy that Alice has, and <code>B[j]</code> is the size of the <code>j</code>-th bar of candy that Bob has.</p>



<p>Since they are friends, they would like to exchange one candy bar each so that after the exchange, they both have the same total&nbsp;amount of candy.&nbsp; (<em>The total amount of candy&nbsp;a person has is the sum of the sizes of candy&nbsp;bars they have.</em>)</p>



<p>Return an integer array <code>ans</code>&nbsp;where <code>ans[0]</code> is the size of the candy bar that Alice must exchange, and <code>ans[1]</code> is the size of the candy bar that Bob must exchange.</p>



<p>If there are multiple answers, you may return any one of them.&nbsp; It is guaranteed an answer exists.</p>



<p>&nbsp;</p>



<div>

<p><strong>Example 1:</strong></p>



<pre>

<strong>Input: </strong>A = <span id="example-input-1-1">[1,1]</span>, B = <span id="example-input-1-2">[2,2]</span>

<strong>Output: </strong><span id="example-output-1">[1,2]</span>

</pre>



<div>

<p><strong>Example 2:</strong></p>



<pre>

<strong>Input: </strong>A = <span id="example-input-2-1">[1,2]</span>, B = <span id="example-input-2-2">[2,3]</span>

<strong>Output: </strong><span id="example-output-2">[1,2]</span>

</pre>



<div>

<p><strong>Example 3:</strong></p>



<pre>

<strong>Input: </strong>A = <span id="example-input-3-1">[2]</span>, B = <span id="example-input-3-2">[1,3]</span>

<strong>Output: </strong><span id="example-output-3">[2,3]</span>

</pre>



<div>

<p><strong>Example 4:</strong></p>



<pre>

<strong>Input: </strong>A = <span id="example-input-4-1">[1,2,5]</span>, B = <span id="example-input-4-2">[2,4]</span>

<strong>Output: </strong><span id="example-output-4">[5,4]</span>

</pre>



<p>&nbsp;</p>



<p><strong><span>Note:</span></strong></p>



<ul>
	<li><span><code>1 &lt;= A.length &lt;= 10000</code></span></li>
	<li><span><code>1 &lt;= B.length &lt;= 10000</code></span></li>
	<li><code><span>1 &lt;= A[i] &lt;= 100000</span></code></li>
	<li><code><span>1 &lt;= B[i] &lt;= 100000</span></code></li>
	<li>It is guaranteed that Alice and Bob have different total amounts of&nbsp;candy.</li>
	<li>It is guaranteed there exists an&nbsp;answer.</li>
</ul>

</div>

</div>

</div>

</div>



## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def fairCandySwap(self, aliceSizes: List[int], bobSizes: List[int]) -> List[int]:
        diff = (sum(aliceSizes) - sum(bobSizes)) >> 1
        s = set(bobSizes)
        for a in aliceSizes:
            target = a - diff
            if target in s:
                return [a, target]
```

### **Java**

```java
class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int s1 = 0, s2 = 0;
        Set<Integer> s = new HashSet<>();
        for (int a : aliceSizes) {
            s1 += a;
        }
        for (int b : bobSizes) {
            s.add(b);
            s2 += b;
        }
        int diff = (s1 - s2) >> 1;
        for (int a : aliceSizes) {
            int target = a - diff;
            if (s.contains(target)) {
                return new int[]{a, target};
            }
        }
        return null;
    }
}
```

### **TypeScript**

```ts
function fairCandySwap(aliceSizes: number[], bobSizes: number[]): number[] {
    let s1 = aliceSizes.reduce((a, c) => a + c, 0);
    let s2 = bobSizes.reduce((a, c) => a + c, 0);
    let diff = (s1 - s2) >> 1;
    for (let num of aliceSizes) {
        let target = num - diff;
        if (bobSizes.includes(target)) {
            return [num, target];
        }
    }
};
```

### **C++**

```cpp
class Solution {
public:
    vector<int> fairCandySwap(vector<int>& aliceSizes, vector<int>& bobSizes) {
        int s1 = accumulate(aliceSizes.begin(), aliceSizes.end(), 0);
        int s2 = accumulate(bobSizes.begin(), bobSizes.end(), 0);
        int diff = (s1 - s2) >> 1;
        unordered_set<int> s(bobSizes.begin(), bobSizes.end());
        vector<int> ans;
        for (int& a : aliceSizes) {
            int target = a - diff;
            if (s.count(target)) {
                ans = vector<int>{a, target};
                break;
            }
        }
        return ans;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
