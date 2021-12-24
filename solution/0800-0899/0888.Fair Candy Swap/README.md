# [888. 公平的糖果棒交换](https://leetcode-cn.com/problems/fair-candy-swap)

[English Version](/solution/0800-0899/0888.Fair%20Candy%20Swap/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>爱丽丝和鲍勃有不同大小的糖果棒：<code>A[i]</code> 是爱丽丝拥有的第 <code>i</code> 根糖果棒的大小，<code>B[j]</code> 是鲍勃拥有的第 <code>j</code> 根糖果棒的大小。</p>

<p>因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。<em>（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）</em></p>

<p>返回一个整数数组 <code>ans</code>，其中 <code>ans[0]</code> 是爱丽丝必须交换的糖果棒的大小，<code>ans[1]</code> 是 Bob 必须交换的糖果棒的大小。</p>

<p>如果有多个答案，你可以返回其中任何一个。保证答案存在。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>A = [1,1], B = [2,2]
<strong>输出：</strong>[1,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>A = [1,2], B = [2,3]
<strong>输出：</strong>[1,2]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>A = [2], B = [1,3]
<strong>输出：</strong>[2,3]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>A = [1,2,5], B = [2,4]
<strong>输出：</strong>[5,4]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= A.length <= 10000</code></li>
	<li><code>1 <= B.length <= 10000</code></li>
	<li><code>1 <= A[i] <= 100000</code></li>
	<li><code>1 <= B[i] <= 100000</code></li>
	<li>保证爱丽丝与鲍勃的糖果总量不同。</li>
	<li>答案肯定存在。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

哈希表实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
}
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
